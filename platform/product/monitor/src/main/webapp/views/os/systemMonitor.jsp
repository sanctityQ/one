<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>查看预警配置文件</title>
<%@ include file="/WEB-INF/layouts/base.jsp" %>
<link href="${ctx }/global/css/sinosoft.tabs.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="${ctx }/global/js/sinosoft.tabs.js"></script>
<script language="javascript" src="${ctx }/global/js/highcharts.src.js"></script>
<script language="javascript" src="${ctx }/global/js/os/systemMonitor.js"></script>
<script type="text/javascript">
$(function(){

	$("body").layout({
		top : {
			topHeight : 100
		},
		bottom : {
			bottomHeight : 30
		}
	});
	

	getForm();
	
	/*$("#healthList").Grid({
		url : " ",  
		dataType: "json",
		colDisplay: false,  
		clickSelect: true,
		draggable:false,
		height: 'auto',  
		colums:[  
			{id:'1',text:'名称',name:"appellation",index:'1',align:'',width:'100'},
			{id:'2',text:'01',name:"appellation",index:'1',align:''},
			{id:'3',text:'02',name:"appellation",index:'1',align:''},
			{id:'4',text:'03',name:"appellation",index:'1',align:''},
			{id:'5',text:'04',name:"appellation",index:'1',align:''},
			{id:'6',text:'05',name:"appellation",index:'1',align:''},
			{id:'7',text:'06',name:"appellation",index:'1',align:''},
			{id:'8',text:'07',name:"appellation",index:'1',align:''},
			{id:'9',text:'08',name:"appellation",index:'1',align:''},
			{id:'10',text:'09',name:"appellation",index:'1',align:''},
			{id:'11',text:'10',name:"appellation",index:'1',align:''},
			{id:'12',text:'11',name:"appellation",index:'1',align:''},
			{id:'13',text:'12',name:"appellation",index:'1',align:''},
			{id:'14',text:'13',name:"appellation",index:'1',align:''},
			{id:'16',text:'15',name:"appellation",index:'1',align:''},
			{id:'17',text:'16',name:"appellation",index:'1',align:''},
			{id:'19',text:'18',name:"appellation",index:'1',align:''},
			{id:'20',text:'19',name:"appellation",index:'1',align:''},
			{id:'21',text:'20',name:"appellation",index:'1',align:''},
			{id:'22',text:'21',name:"appellation",index:'1',align:''},
			{id:'23',text:'22',name:"appellation",index:'1',align:''},
			{id:'24',text:'23',name:"appellation",index:'1',align:''},
			{id:'25',text:'24',name:"appellation",index:'1',align:''}
		],  
		rowNum:9999,
		pager : false,
		number:false,  
		multiselect: false  
	});*/
	
	$("#tabs").tabs({closeTab : false});
	$("#myDesk").height($("#layout_center").height());
	$("#nav").delegate('li', 'mouseover mouseout', navHover);
	$("#nav,#menu").delegate('li', 'click', navClick);
});
</script>
</head>

<body>
<%@include file="/WEB-INF/layouts/menu.jsp" %>
	<div id="layout_center">
		<div class="main">
			<ul class="crumbs">
				<li><a href="#">监视器</a> ></li>
				<li><b> Linux - 批量配置视图 (总计 3 监视器)</b>
				</li>
			</ul>
			<hr class="top_border" />
			<div id="tabs">
				<ul>
					<li class="tabs_select">可用性</li>
					<li>性能</li>
					<li>列表视图</li>
				</ul>
				<div>
					<br />
					<div class="threshold_file availability">
						<h2 class="title2">
							<b>可用性历史纪录- Linux </b> <select name="" class="diySelect">
								<option value="">最近24小时</option>
								<option value="">最近30天</option>
							</select>
						</h2>
						<div id="systemMonitorTable"></div>
						<div class="explain">
							<ul>
								<li><span class="ex_no"></span>不可用</li>
								<li><span class="ex_is"></span>可用</li>
								<li><span class="ex_xp"></span>预警</li>
							</ul>
							查看监视器/业务组最近24小时或30天的可用性状态
						</div>
					</div>
				</div>
				<div>
					<br />
					<div class="threshold_file">
						<table width="100%" border="0" cellspacing="0" cellpadding="0"
							class="my_table">
							<tr>
								<td class="group_name"><h3>物理内存利用率</h3>
								</td>
								<td class="group_name"><h3>交换内存利用率</h3>
								</td>
							</tr>
							<tr>
								<td width="50%"><div id="chartMem"></div>
								</td>
								<td width="50%"><div id="chartSwap"></div>
								</td>
							</tr>
							<tr>
								<td class="group_name"><h3>CPU利用率</h3>
								</td>
								<td class="group_name"><h3>应答时间</h3>
								</td>
							</tr>
							<tr>
								<td><div id="chartCpu"></div>
								</td>
								<td><div id="chartReply"></div>
								</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
							</tr>
						</table>
						<div class="threshold_file availability">
							<h2 class="title2">
								<b> 健康状态操控板</b> <select name="select" class="diySelect">
									<option value="">最近24小时</option>
									<option value="">最近30天</option>
								</select>
							</h2>
							<div id="healthList"></div>
							<div class="explain">
								<ul>
									<li><span class="ex_is"></span>正常</li>
									<li><span class="ex_xp"></span>警告</li>
									<li><span class="ex_no"></span>严重</li>
								</ul>
								查看监视器/业务组过去24小时或30天的健康状态告警
							</div>
						</div>
					</div>
				</div>
				<div>
					<br />
					<div class="threshold_file">
						<h2 class="title2">
							<b>操作系统列表视图 </b>
						</h2>
						<div class="tool_bar_top">
							<a href="javascript:void(0);" class="batch_del"
								onclick="batchDel()">批量删除</a>
						</div>
						<div id="thresholdList"></div>
						<div class="tool_bar"></div>
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
