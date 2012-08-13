/*
 * Copyright 2008-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sinosoft.one.data.jpa.repository.query;

import org.springframework.data.jpa.repository.query.*;
import org.springframework.data.repository.core.NamedQueries;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.query.QueryLookupStrategy;
import org.springframework.data.repository.query.QueryLookupStrategy.Key;
import org.springframework.data.repository.query.RepositoryQuery;

import javax.persistence.EntityManager;
import java.lang.reflect.Method;

/**
 * Query lookup strategy to execute finders.
 * 
 * @author Oliver Gierke
 */
public final class OneJpaQueryLookupStrategy {

	/**
	 * Private constructor to prevent instantiation.
	 */
	private OneJpaQueryLookupStrategy() {

	}

	/**
	 * Base class for {@link org.springframework.data.repository.query.QueryLookupStrategy} implementations that need access to an {@link javax.persistence.EntityManager}.
	 *
	 * @author Oliver Gierke
	 */
	private abstract static class OneAbstractQueryLookupStrategy implements QueryLookupStrategy {


        private Method method;
        private RepositoryMetadata metadata;
		private final EntityManager em;
		private final QueryExtractor provider;

        protected Method getMethod() {
            return method;
        }
        protected RepositoryMetadata getMetadata() {
            return metadata;
        }

        public void setMethod(Method method) {
            this.method = method;
        }

        public void setMetadata(RepositoryMetadata metadata) {
            this.metadata = metadata;
        }

        public OneAbstractQueryLookupStrategy(EntityManager em, QueryExtractor extractor) {

			this.em = em;
			this.provider = extractor;
		}

		/*
		 * (non-Javadoc)
		 *
		 * @see org.springframework.data.repository.query.QueryLookupStrategy#
		 * resolveQuery(java.lang.reflect.Method,
		 * org.springframework.data.repository.core.RepositoryMetadata,
		 * org.springframework.data.repository.core.NamedQueries)
		 */
		public final RepositoryQuery resolveQuery(Method method, RepositoryMetadata metadata, NamedQueries namedQueries) {
            this.method = method;
            this.metadata = metadata;
			return resolveQuery(new JpaQueryMethod(method, metadata, provider), em, namedQueries);
		}

		protected abstract RepositoryQuery resolveQuery(JpaQueryMethod method, EntityManager em, NamedQueries namedQueries);
	}

	/**
	 * {@link org.springframework.data.repository.query.QueryLookupStrategy} to create a query from the method name.
	 *
	 * @author Oliver Gierke
	 */
	private static class OneCreateQueryLookupStrategy extends OneAbstractQueryLookupStrategy {

		public OneCreateQueryLookupStrategy(EntityManager em, QueryExtractor extractor) {

			super(em, extractor);
		}

		@Override
		protected RepositoryQuery resolveQuery(JpaQueryMethod method, EntityManager em, NamedQueries namedQueries) {

			try {
				return new PartTreeJpaQuery(method, em);
			} catch (IllegalArgumentException e) {
				throw new IllegalArgumentException(String.format("Could not create query metamodel for method %s!",
						method.toString()), e);
			}
		}
	}

	/**
	 * {@link org.springframework.data.repository.query.QueryLookupStrategy} that tries to detect a declared query declared via {@link javax.persistence.Query} annotation followed by
	 * a JPA named query lookup.
	 *
	 * @author Oliver Gierke
	 */
	private static class OneDeclaredQueryLookupStrategy extends OneAbstractQueryLookupStrategy {

		public OneDeclaredQueryLookupStrategy(EntityManager em, QueryExtractor extractor) {

			super(em, extractor);
		}

		@Override
		protected RepositoryQuery resolveQuery(JpaQueryMethod method, EntityManager em, NamedQueries namedQueries) {

			RepositoryQuery query = getSpringDataJpaAdapter().simpleJpaQueryFromQueryAnnotation(method, em);

			if (null != query) {
				return query;
			}

			String name = method.getNamedQueryName();
			if (namedQueries.hasQuery(name)) {
				return getSpringDataJpaAdapter().newSimpleJpaQuery(method, em, namedQueries.getQuery(name));
			}

			query = getSpringDataJpaAdapter().namedQueryLookupFrom(method, em);

			if (null != query) {
				return query;
			}

			throw new IllegalStateException(String.format(
					"Did neither find a NamedQuery nor an annotated query for method %s!", method));
		}

	}

	/**
	 * {@link org.springframework.data.repository.query.QueryLookupStrategy} to try to detect a declared query first (
	 * {@link org.springframework.data.jpa.repository.Query}, JPA named query). In case none is found we fall back on
	 * query creation.
	 *
	 * @author Oliver Gierke
	 */
	private static class OneCreateIfNotFoundQueryLookupStrategy extends OneAbstractQueryLookupStrategy {

		private final OneDeclaredQueryLookupStrategy strategy;
		private final OneCreateQueryLookupStrategy createStrategy;
        private final OneJadeQueryLookupStrategy jadeStrategy;

		public OneCreateIfNotFoundQueryLookupStrategy(EntityManager em, QueryExtractor extractor) {

			super(em, extractor);
			this.strategy = new OneDeclaredQueryLookupStrategy(em, extractor);
			this.createStrategy = new OneCreateQueryLookupStrategy(em, extractor);
            this.jadeStrategy = new OneJadeQueryLookupStrategy(em, extractor);
		}

		@Override
		protected RepositoryQuery resolveQuery(JpaQueryMethod method, EntityManager em, NamedQueries namedQueries) {
            try {
                this.jadeStrategy.setMethod(getMethod());
                this.jadeStrategy.setMetadata(getMetadata());
                return jadeStrategy.resolveQuery(method, em, namedQueries);
            } catch (IllegalStateException e) {
                try {
                    return strategy.resolveQuery(method, em, namedQueries);
                } catch (IllegalStateException e1) {
                    return createStrategy.resolveQuery(method, em, namedQueries);
                }
            }
		}
	}

    private static class OneJadeQueryLookupStrategy  extends OneAbstractQueryLookupStrategy {

        public OneJadeQueryLookupStrategy(EntityManager em, QueryExtractor extractor) {
            super(em, extractor);
        }

        @Override
        protected RepositoryQuery resolveQuery(JpaQueryMethod method, EntityManager em, NamedQueries namedQueries) {

            RepositoryQuery query = OneJadeRepositoryQuery.fromSQLAnnotation(new OneJadeQueryMethod(getMethod(), getMetadata()), em);

            if (null != query) {
                return query;
            }
            throw new IllegalStateException(String.format(
                    "Did not find an annotated SQL for method %s!", method));

        }
    }
	/**
	 * Creates a {@link org.springframework.data.repository.query.QueryLookupStrategy} for the given {@link javax.persistence.EntityManager} and {@link org.springframework.data.repository.query.QueryLookupStrategy.Key}.
	 * 
	 * @param em
	 * @param key
	 * @return
	 */
	public static QueryLookupStrategy create(EntityManager em, Key key, QueryExtractor extractor) {

		if (key == null) {
			return new OneCreateIfNotFoundQueryLookupStrategy(em, extractor);
		}

		switch (key) {
		case CREATE:
			return new OneCreateQueryLookupStrategy(em, extractor);
		case USE_DECLARED_QUERY:
			return new OneDeclaredQueryLookupStrategy(em, extractor);
		case CREATE_IF_NOT_FOUND:
			return new OneCreateIfNotFoundQueryLookupStrategy(em, extractor);
		default:
			throw new IllegalArgumentException(String.format("Unsupported query lookup strategy %s!", key));
		}
	}

    public static SpringDataJpaQueryAdapter getSpringDataJpaAdapter() {
        return SpringDataJpaQueryAdapter.getInstance();
    }
}