package com.sinosoft.one.monitor.attribute.domain;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.sinosoft.one.monitor.attribute.model.Attribute;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

/**
 * 属性缓存.
 * User: carvin
 * Date: 13-3-1
 * Time: 下午4:01
 *
 */
@Component
public class AttributeCache {
	private static Logger logger = LoggerFactory.getLogger(AttributeCache.class);
	@Autowired
	private AttributeService attributeService;
	private LoadingCache<String, Attribute> attributeCache = CacheBuilder.newBuilder().maximumSize(1024)
			.build(new CacheLoader<String, Attribute>() {
				@Override
				public Attribute load(String key) throws Exception {
					logger.info("fetch attribute from database");
					String[] keys = key.split("#");
					return attributeService.getAttribute(keys[0], keys[1]);
				}
			});

	public Attribute getAttributeId(String key) {
		try {
			return attributeCache.get(key);
		} catch (ExecutionException e) {
			logger.warn("get attribute id exception : " + e.getMessage());
			return Attribute.EMPTY;
		}
	}

	public void removeAttributeId(String key) {
		attributeCache.invalidate(key);
	}

	public void clearUp() {
		attributeCache.cleanUp();
	}
}