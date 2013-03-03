package com.sinosoft.one.monitor.application.repository;
// Generated 2013-2-27 18:41:37 by One Data Tools 1.0.0

import com.sinosoft.one.data.jade.annotation.SQL;
import com.sinosoft.one.monitor.application.model.Method;
import com.sinosoft.one.mvc.web.annotation.Param;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface MethodRepository extends PagingAndSortingRepository<Method, String> {

    Method findByMethodName(String methodName);

    @SQL("select * from GE_MONITOR_METHOD a where a.ID in (select b.METHOD_ID from GE_MONITOR_URL_METHOD b right join GE_MONITOR_URL c on b.URL_ID=?1)")
    List<Method> selectMethodsOfUrlById(@Param("urlId") String urlId);
}

