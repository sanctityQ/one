/*
 * Copyright 2009-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License i distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sinosoft.one.data.jade.dataaccess;

import com.sinosoft.one.data.jade.statement.pagesqlsuite.PageSqlFactory;
import com.sinosoft.one.data.jade.statement.pagesqlsuite.SuiteDataSourcePageSql;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 王志亮 [qieqie.wang@gmail.com]
 * @author 廖涵 [in355hz@gmail.com]
 */
@Transactional(readOnly = true)
public class DataAccessImpl implements DataAccess, Repository {

	private final EntityManager em;
	public DataAccessImpl(EntityManager em) {
		this.em = em;
	}

	/**
	 * 普通查询 2012-08-16
	 */
	public<T> List<T> select(String sql,Object[] args,RowMapper<?> rowMapper) {
		Session session = em.unwrap(Session.class);
		SelectWork<T> work = new SelectWork<T>(sql,args,rowMapper);
		session.doWork(work);
		return work.results;
	}

	/**
	 * 分页查询 2012-08-16
	 */
	public<T> Page<T> selectByPage(Pageable pageable,String sql,String countSql, Object[] args, RowMapper<?> rowMapper) {
		Session session = em.unwrap(Session.class);
		SingleColumnRowMapper<BigDecimal> scrm = new SingleColumnRowMapper<BigDecimal>();
		List<BigDecimal> totals = select(countSql,args,scrm);
		PageSqlWork psw = new PageSqlWork(sql,pageable) ;
		session.doWork(psw);
		sql = psw.getSql();
		List<T> content = select(sql,args,rowMapper);
		if(content == null){
			content = new ArrayList<T>();
		}
		Object o = totals.get(0);
		Number num = (Number)o;
		Page<T> page = new PageImpl<T>(content, pageable, num.longValue());
		return page;
	}

	/**
	 * 更新 2012-08-16
	 */
    @Transactional
	public int update(String sql, Object[] args, KeyHolder generatedKeyHolder) {
		Session session = em.unwrap(Session.class);
		UpdateWork work = new UpdateWork(sql, args, generatedKeyHolder);
		session.doWork(work);
		return work.number;
	}

	// TODO: 批量处理
	public int[] batchUpdate(String sql, List<Object[]> argsList) {
		int[] updated = new int[argsList.size()];
		int i = 0;
		for (Object[] args : argsList) {
			updated[i++] = update(sql, args, null);
		}
		return updated;
	}

	private void setParams(PreparedStatement ps,Object[] args) throws SQLException{
		if (args != null) {
			for (int i = 0; i < args.length; i++) {
				Object arg = args[i];
				if (arg instanceof SqlParameterValue) {
					SqlParameterValue paramValue = (SqlParameterValue) arg;
					StatementCreatorUtils.setParameterValue(ps, i + 1, paramValue,
							paramValue.getValue());
				} else {
					StatementCreatorUtils.setParameterValue(ps, i + 1,
							SqlTypeValue.TYPE_UNKNOWN, arg);
				}
			}
		}
	}

	/**
	 * Connection查询操作的类
	 */
	private class SelectWork<T> implements Work {
		public String sql;
		public Object[] args;
		public RowMapper<?> rowMapper;
		List<T> results = new ArrayList<T>();
		public SelectWork(String sql,Object[] args,RowMapper<?> rowMapper){
			this.sql = sql;
			this.args = args;
			this.rowMapper = rowMapper;
		}
		@SuppressWarnings("unchecked")
		public void execute(Connection con) throws SQLException {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = null;
			setParams(ps,args);
			int rowNum = 0;
			rs = ps.executeQuery();
			while (rs.next()) {
				results.add((T) rowMapper.mapRow(rs, rowNum++)) ;
			}
		}
	}
	/**
	 * Connection查询操作的类
	 */
	private class UpdateWork implements Work {
		public String sql;
		public Object[] args;
		public KeyHolder generatedKeyHolder;
		int number;
		public UpdateWork(String sql,Object[] args,KeyHolder generatedKeyHolder){
			this.sql = sql;
			this.args = args;
			this.generatedKeyHolder = generatedKeyHolder;
		}
		public void execute(Connection con) throws SQLException {
			boolean returnKeys = generatedKeyHolder != null;
			PreparedStatement ps = con.prepareStatement(sql);
			if (returnKeys) {
				ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			} else {
				ps = con.prepareStatement(sql);
			}
			setParams(ps,args);
			number = ps.executeUpdate();
		}
	}

	private class PageSqlWork implements Work {
		private String sql;
		private Pageable pageable;

		public String getSql(){
			return sql;
		}
		PageSqlWork(String sql,Pageable pageable){
			this.sql = sql;
			this.pageable = pageable;
		}
		public void execute(Connection con) throws SQLException {
			String URl = con.getMetaData().getURL();
			SuiteDataSourcePageSql dps = PageSqlFactory.createPageSql(URl);
			sql = dps.suiteSql(sql,pageable);
		}
	}

}
