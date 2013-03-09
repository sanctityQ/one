<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://mvc.one.sinosoft.com/tags/pipe" prefix="mvcpipe"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>业务仿真</title>
<link href="${ctx}/global/css/base.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/global/css/style.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/global/css/timeinfo/timeinfo.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/global/css/sinosoft.grid.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/global/css/status.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/global/css/sinosoft.tabs.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/global/css/oracle.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/global/css/sinosoft.window.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="${ctx}/global/js/jquery-1.7.1.js"></script>
<script language="javascript" src="${ctx}/global/js/sinosoft.grid.js"></script>
<script language="javascript" src="${ctx}/global/js/sinosoft.layout.js"></script>
<script language="javascript" src="${ctx}/global/js/highcharts.src.js"></script>
<script language="javascript" src="${ctx}/global/js/timeinfo/timeinfo.js"></script>
<script language="javascript" src="${ctx}/global/js/sinosoft.window.js"></script>
<script language="javascript" src="${ctx}/global/js/sinosoft.tabs.js"></script>
</head>

<body>
<%@include file="/WEB-INF/layouts/menu.jsp"%>
<div id="layout_center">
	<div class="time_info_all">
  	<ul class="crumbs">
      <li><a href="#">管理</a> ></li>
      <li><b>配置告警</b></li>
    </ul>
    <div class="time_info_top">
      <div class="time_info_content">
      	<table class="time_info_top_table" cellspacing="0" cellpadding="0" border="0">
        	<tr>
          	<td class="time_info_head" colspan="5">URL信息</td>
          </tr>
        	<tr class="time_info_title">
          	<td>健康状况</td>
            <td>可用性</td>
            <td>今日可用性</td>
            <td>今日运行时间</td>
            <td>最后故障时间</td>
          </tr>
          <tr class="time_info_text">
          	<td align="center"><div class="up"></div></td>
            <td align="center"><div class="cando"></div></td>
            <td><a href="#">100%</a></td>
            <td><a href="#">24 分 10 秒</a></td>
            <td><a href="#">2013 年 1 月 1 日</a></td>
          </tr>
          
        </table>
      </div>
      <div style=" height:15px;background: url(${ctx}/global/images/timeinfo/conf-shadow.png) center no-repeat"></div>
     <div id="tabs">
        <ul>
          <li class="tabs_select">概览</li>
          <li>事件日志</li>
        </ul>
  	 <br />
     
      <div class="overview" >
      	 <div class="time_info_overview">
      		<table class="time_info_overview_table">
          <tr>
          	<td style="width:45.5%;height:150px;">
            	<table class="bar_table" cellpadding="0" cellspacing="0" style="height:150px;">
                <tr>
                  <td class="overview_table_head" style="font-size:15px; font-weight:bold;">过去6小时的可用性</td>
                </tr>	
                <tr>
                	<td>
                    <ul class="show_bar">
                      <li class="ky"></li>
                      <li class="wran"></li>
                      <li class="bky"></li>
                      <li class="wran"></li>
                      <li class="bky"></li>
                      <li class="ky"></li>
                    </ul>
                   
                  </td>
                  <td> 100.0</td>
                </tr>
                <tr>
                	<td>
                  	<img  src="${ctx}/global/images/timeinfo/ruler.jpg" />
                  </td>
                </tr>
                <tr>
                	<td>
                  	<ul class="show_time">
                    	<li>00:00</li>
                      <li>01:00</li>
                      <li>02:00</li>
                      <li>03:00</li>
                      <li>04:00</li>
                      <li>05:00</li>
                    </ul>
                  </td>
                </tr>
                <tr>
                	<td>
                  	<ul class="show_mark">
                    	<li><div class="iky"></div>可用</li>
                      <li><div class="ibky"></div>不可用</li>
                      <li><div class="iwran"></div>警告</li>
                    </ul>
                  </td>
                </tr>
              </table>
              	
              
            </td>
            <td style="height:150px">
            	<table class="time_info_performance_table" cellpadding="0" cellspacing="0" style="margin:0 auto;height:150px;">
	               <tr>
                 	<td colspan="6" style="font-size:15px; font-weight:bold;">过去6小时的健康性</td>
                 </tr>
              	<tr class="show_mark_time" >
                	<td><b>00:00</b></td>
                  <td><b>01:00</b></td>
                  <td><b>02:00</b></td>
                  <td><b>03:00</b></td>
                  <td><b>04:00</b></td>
                  <td><b>05:00</b></td>
                </tr>
                <tr >
                	<td ><div class="unknow mark_show"></div></td>
                  <td><a href="#"><div class="normal mark_show"></div></a></td>
                  <td><a href="#"><div class="warning mark_show"></div></a></td>
                  <td><a href="#"><div class="serious mark_show"></div></a></td>
                  <td><a href="#"><div class="normal mark_show"></div></a></td>
                  <td><a href="#"><div class="normal mark_show"></div></a></td>
                </tr>
                <tr>
                	<td colspan="6" class="mark_info">
                    <div class="unknow"></div><div>未知</div>
                    <div class="normal"></div><div>健康</div>
                    <div class="warning"></div><div>警告</div>
                    <div class="serious"></div><div>不健康</div>
                  </td>
                  
                </tr>
              </table>
            </td>
          </tr>
        </table>
      	 </div>
        <div class="column_chart">
        	 <div class="threshold_file" style="height:400px;width:49%;">
            <div class="sub_title">url访问次数</div>
            <div class="days_data">
              <a onclick="createThirtyURLTime(this);"><div class="thirty_days"></div></a>
            	<a onclick="createSevenURLTime(this);"><div class="seven_days"></div></a>
            </div>
            <div id="time_times" style="height:300px;"></div>
          </div>
           <div class="threshold_file" style="height:400px;width:49%;">
            <div class="sub_title">url响应时间</div>
            <div class="days_data">
              <a onclick="createThirtyDayResponseTime(this);"><div class="thirty_days"></div></a>
            	<a onclick="createSevenDayResponseTime(this);"><div class="seven_days"></div></a>
            </div>
           <div id="time_response_time" style="height:300px;width:98%">1</div>
          </div>
          
        </div>
        <div style="clear:both;"></div>
        <div class="type_list">
          <div class="threshold_file">
            <div class="tool_bar_top">方法列表</div>
            <div id="list_table" ></div>
          </div>
        </div>
        <div class="stuats_mark">
          <ul>
            <li><div class="red_status"></div><div>严重</div></li>
            <li><div class="yellow_status"></div><div>警告</div></li>
            <li><div class="green_status"></div><div>正常</div></li>
          </ul>
        </div>
      </div>
 
 
       
      
    	<div class="event_log">
        <div class="threshold_file">
          <div class="tool_bar_top">事件日志</div>
          <div id="event_log_grid"></div>
        </div>
           <div class="stuats_mark">
            <ul>
              <li><div class="red_status"></div><div>严重</div></li>
              <li><div class="yellow_status"></div><div>警告</div></li>
              <li><div class="green_status"></div><div>正常</div></li>
            </ul>
          </div>
      </div>  
     
  	  </div>
     </div>
  </div>
</div>

	<div id="layout_bottom">
      <p class="footer">Copyright &copy; 2013 Sinosoft Co.,Lt</p>
    </div>
</body>
</html>
