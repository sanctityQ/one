package com.sinosoft.one.data.jade.statement.pagesqlsuite;

import org.springframework.data.domain.Pageable;

/**
 * Created with IntelliJ IDEA.
 * User: kylin
 * Date: 12-8-16
 * Time: 下午2:41
 * To change this template use File | Settings | File Templates.
 */
public interface SuiteDataSourcePageSql {
   String suiteSql(String sql, Pageable pageable);
}
