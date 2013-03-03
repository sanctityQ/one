package com.sinosoft.one.monitor.db.oracle.domain;

import com.sinosoft.one.monitor.db.oracle.model.*;
import com.sinosoft.one.monitor.db.oracle.monitorSql.OracleMonitorSql;
import com.sinosoft.one.monitor.db.oracle.repository.InfoRepository;
import com.sinosoft.one.monitor.db.oracle.repository.LasteventRepository;
import com.sinosoft.one.monitor.db.oracle.utils.DBUtil4Monitor;
import com.sinosoft.one.monitor.db.oracle.utils.db.DBUtil;
import com.sinosoft.one.monitor.db.oracle.utils.db.SqlObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * User: Chunliang.Han
 * Date: 13-3-1
 * Time: 下午3:06
 */
@Component
public class OraclePreviewServiceImpl implements OraclePreviewService {

    @Autowired
    private LasteventRepository lasteventRepository;
    @Autowired
    private InfoRepository infoRepository;

    @Override
    public EventInfoModel[] viewConnectInfo(String monitorId) {
        Long time = System.currentTimeMillis();
        Date end = new Date(time);
        Date start = new Date(time - 3600 * 1000);
        List<Lastevent> activeConnectList = lasteventRepository.findLastEventList(monitorId, start, end);
        EventInfoModel[] eventInfoModel = new EventInfoModel[2];
        eventInfoModel[0].setStartTime(start.getTime() + "");
        eventInfoModel[0].setEndTime(end.getTime() + "");
        eventInfoModel[0].setEventName("连接时间");
        eventInfoModel[1].setStartTime(start.getTime() + "");
        eventInfoModel[1].setEndTime(end.getTime() + "");
        eventInfoModel[1].setEventName("用户数");
        if (activeConnectList == null || activeConnectList.size() == 0) {
            return eventInfoModel;
        } else {
            long connect = activeConnectList.get(activeConnectList.size() - 1).getConnectTime();
            int active = activeConnectList.get(activeConnectList.size() - 1).getActiveCount();
            eventInfoModel[0].setEventValue(connect + " ms");
            eventInfoModel[1].setEventValue(active + "");
            int size = activeConnectList.size();

            //连接时间points
            List<Point> connectTimePoints = new ArrayList<Point>();
            //用户数points
            List<Point> activeCountPoints = new ArrayList<Point>();
            SimpleDateFormat sdf = new SimpleDateFormat("HH：mm");
            //"yyyy-MM-dd HH:mm:ss E"
            SimpleDateFormat sdf2 = new SimpleDateFormat("E,dd日-MM月-yyyy年 HH:mm");
            for (int i = 0; i < size; i++) {
                Lastevent event = activeConnectList.get(i);

                Point connectTimePoint = new Point();
                connectTimePoint.setxAxis(event.getRecordTime());
                connectTimePoint.setyAxis(Integer.parseInt(event.getConnectTime() + ""));
                connectTimePoint.setDescription("连接时间" + "(" + sdf2.format(event.getRecordTime()) + " ," + event.getConnectTime() + ")");

                connectTimePoints.add(connectTimePoint);

                Point activeCountPoint = new Point();
                activeCountPoint.setxAxis(event.getRecordTime());
                activeCountPoint.setyAxis(Integer.parseInt(event.getActiveCount() + ""));
                activeCountPoint.setDescription("用户数" + "(" + sdf2.format(event.getRecordTime()) + " ," + event.getActiveCount() + ")");

                activeCountPoints.add(activeCountPoint);
            }
            eventInfoModel[0].setPoints(connectTimePoints);
            eventInfoModel[1].setPoints(activeCountPoints);
        }
        return eventInfoModel;
    }

    @Override
    public OracleDetailModel viewDbDetail(String monitorId) {
        OracleDetailModel oracleDetailModel = new OracleDetailModel();
        DBUtil4Monitor.changeConnection(monitorId);
        String sql = OracleMonitorSql.dbInfo;
        List<Map<String, String>> rsList = DBUtil.queryStrMaps(SqlObj.newInstance(sql));
        Map<String, String> rsObj = rsList.get(0);
        // CREATED,OPEN_MODE,LOG_MODE
        String created = rsObj.get("CREATED");
        String openMode = rsObj.get("OPEN_MODE");
        String logMode = rsObj.get("LOG_MODE");
        oracleDetailModel.setDbCreateTime(created);
        oracleDetailModel.setLogType(logMode);
        oracleDetailModel.setOpenType(openMode);
        return oracleDetailModel;
    }
}