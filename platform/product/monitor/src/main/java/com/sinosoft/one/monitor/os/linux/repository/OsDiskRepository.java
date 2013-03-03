package com.sinosoft.one.monitor.os.linux.repository;
// Generated 2013-2-27 21:43:52 by One Data Tools 1.0.0

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.sinosoft.one.data.jade.annotation.SQL;
import com.sinosoft.one.monitor.os.linux.model.OsDisk;

public interface OsDiskRepository extends PagingAndSortingRepository<OsDisk, String> {
	
	//根据时间
	@Query("from OsDisk o where o.sampleDate between to_date(?2,?4) and to_date(?3,?4) and o.os.osInfoId= ?1 ORDER by o.sampleDate")
	public List<OsDisk> findOsDiskByDate(String osid,String beginTime,String endTime,String dateFormat);

	//磁盘利用率最大值
	@SQL("select MAX(USED_UTILI_ZATION) from GE_MONITOR_OS_DISK where SAMPLE_DATE between  to_date(?2,?4) and to_date(?3,?4) and OS_INFO_ID= ?1 ")
	public String findMaxDiskUtilZation(String osInfoId,String begin,String end,String dateFormat);
	
	//磁盘利用率最小值
	@SQL("select MIN(USED_UTILI_ZATION)  from GE_MONITOR_OS_DISK where SAMPLE_DATE between  to_date(?2,?4) and to_date(?3,?4) and OS_INFO_ID= ?1 ")
	public String findMinDiskUtilZation(String osInfoId,String begin,String end,String dateFormat);

}
