<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>查看预警配置文件</title>
<link href="${ctx}/global/css/base.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/global/css/style.css" rel="stylesheet"
	type="text/css" />
<link href="${ctx}/global/css/sinosoft.message.css" rel="stylesheet"
	type="text/css" />
<link href="${ctx}/global/css/sinosoft.grid.css" rel="stylesheet"
	type="text/css" />
<link href="${ctx}/global/css/sinosoft.window.css" rel="stylesheet"
	type="text/css" />
<link href="${ctx}/global/css/sinosoft.tabs.css" rel="stylesheet"
	type="text/css" />
<script language="javascript" src="${ctx}/global/js/jquery-1.7.1.js"></script>
<script language="javascript" src="${ctx}/global/js/sinosoft.layout.js"></script>
<script language="javascript" src="${ctx}/global/js/sinosoft.grid.js"></script>
<script language="javascript" src="${ctx}/global/js/sinosoft.window.js"></script>
<script language="javascript" src="${ctx}/global/js/sinosoft.message.js"></script>
<script language="javascript" src="${ctx}/global/js/sinosoft.tabs.js"></script>
<script language="javascript" src="${ctx}/global/js/highcharts.src.js"></script>
<script language="javascript" src="${ctx}/global/js/os/HighchartsTest.js"></script>
<script type="text/javascript">
$(function(){
	$("body").layout({
		top:{topHeight:100},
		bottom:{bottomHeight:30}
	});
	$("#thresholdList").Grid({
		url : " ",  
		dataType: "json",
		colDisplay: false,  
		clickSelect: true,
		draggable:false,
		height: "auto",  
		colums:[  
			{id:'1',text:'名称',name:"appellation",index:'1',align:''},
			{id:'2',text:'可用性',name:"appellation",index:'1',align:''},
			{id:'3',text:'健康状态',name:"appellation",index:'1',align:''}
		],  
		rowNum:9999,
		pager : false,
		number:false,  
		multiselect: true  
	});
	$("#healthList").Grid({
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
	});
	
	
	$("#tabs").tabs({closeTab:false});
	$("#myDesk").height($("#layout_center").height());
	$("#nav").delegate('li', 'mouseover mouseout', navHover);
	$("#nav,#menu").delegate('li', 'click', navClick);
	
});
function navHover(){
	$(this).toggleClass("hover")
}
function navClick(){
	$(this).addClass("seleck").siblings().removeClass("seleck");
	if($(this).hasClass('has_sub')){
		var subMav = $(this).children("ul.add_sub_menu");
		var isAdd = false;
		if($(this).parent().attr("id") == "menu"){
			isAdd = true;
		};
		subMav.slideDown('fast',function(){
			$(document).bind('click',{dom:subMav,add:isAdd},hideNav);
			return false;
		});		
	};
}
function hideNav(e){
	var subMenu = e.data.dom;
	var isAdd = e.data.add;
	subMenu.slideUp('fast',function(){
		if(isAdd){
			subMenu.parent().removeClass('seleck');
		};
	});	
	$(document).unbind();
}
function delRow(e){
	var rows = $(e).parent().parent();
	var id = rows.attr('id');
	msgConfirm('系统消息','确定要删除该条配置文件吗？',function(){
		msgSuccess("系统消息", "操作成功，配置已删除！");
		alert(id);
		rows.remove();
	});
}
function batchDel(){
	var $g = $("#thresholdList div.grid_view > table");
	var selecteds = $("td.multiple :checked",$g);
	if(selecteds.length > 0){
		msgConfirm('系统消息','确定要删除该条配置文件吗？',function(){
			var checks = [];
			selecteds.each(function(){
				var rows = $(this).parent().parent();
				checks.push(rows.attr('id'));
				rows.remove();
			});
			alert(checks);
			msgSuccess("系统消息", "操作成功，配置已删除！");
		});
	}else{
		msgAlert('系统消息','没有选中的文件！<br />请选择要删除的文件后，继续操作。')
	};
}
function viewRelevance(){
	var temWin = $("body").window({
		"id":"window",   
        "title":'根本原因分析',  
		"url":"basicReaon.html",   
        "hasIFrame":true,
		"width": 740,
		"height":440,
		"diyButton":[{
			"id": "btOne",
			"btClass": "buttons",
			"value": "关闭",
			"onclickEvent" : "selectLear",
			"btFun": function() {
					temWin.closeWin();
				}
			}
		]
	});
}
</script>
</head>

<body>
	<div id="layout_top">
		<div class="header">
			<p class="user">
				您好,系统管理员 <span>|</span> <a href="#">退出系统</a>
			</p>
			<div class="menu_box">
				<ul class="nav" id="nav">
					<li><a href="index.jsp">首页</a>
					</li>
					<li class="has_sub seleck"><a href="javascript:viod(0)">监视器</a><span
						class="show_sub_anv"></span>
						<ul class="add_sub_menu" id="subNav">
							<li class="action"><span class="sever">操作系统</span>
								<ul class="list">
									<li><a href="systemMonitor.html"> Linux(2)</a>
									</li>
								</ul></li>
							<li class="action"><span class="system">应用系统</span>
								<ul class="list">
									<li><a href="performance.html">在线查询</a>
									</li>
								</ul></li>
							<li class="action" style="border: none"><span>数据库</span>
								<ul class="list">
									<li><a href="oracleMonitor.html">oracle</a>
									</li>
								</ul></li>
							<li class="clear"></li>
						</ul></li>
					<li><a href="performance.html">应用性能</a>
					</li>
					<li><a href="BusinessSimulation.html">业务仿真</a>
					</li>
					<li><a href="alertList.html">告警</a>
					</li>
					<li><a href="userManager.html">用户管理</a>
					</li>
				</ul>
			</div>
			<ul class="add_menu" id="menu">
				<li><a href="addMonitorList.html">新建监视器</a>
				</li>
				<li class="has_sub"><a href="javascript:viod(0)"><span>阈值配置文件</span>
				</a>
					<ul class="add_sub_menu">
						<li><a class="addThreshold" href="addThreshold.html">新建阈值文件</a>
						</li>
						<li><a class="thresholdFile" href="thresholdFile.html">查看阈值配置文件</a>
						</li>
					</ul></li>
				<li><a href="deployMonitor.html">配置监视器</a>
				</li>
				<li class="has_sub"><a href="javascript:viod(0)"><span>动作</span>
				</a>
					<ul class="add_sub_menu">
						<li class="title"><a href="showMotion.html">显示动作</a>
						</li>
						<li class="action">创建新动作</li>
						<li><a class="sms" href="message.html">短信动作</a>
						</li>
						<li><a class="email" href="mail.html">邮件动作</a>
						</li>
					</ul></li>
				<li><a href="setEmergency.html">配置告警</a>
				</li>
			</ul>
		</div>
	</div>
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
						<table width="100%" border="0" cellspacing="0" cellpadding="0"
							class="my_table">
							<tr>
								<th width="20%">名称</th>
								<th width="75%" style="text-align: center">可用性</th>
								<th>可用性%</th>
							</tr>
							<%-- <c:forEach items="${map }" var="entry">
								<tr>
									<td><a href="Linuxcentos.html">${entry.key}</a></td>
									<td><table width="100%" border="0" cellspacing="0"
											cellpadding="0" class="green_bar">
											<tr>
												<c:forEach items="${entry.value }" var="model">
													<c:if test="${model.status == '0'}">
														<td class="not_available" width="${model.percentage }%"></td>
													</c:if>
													<c:if test="${model.status == '2' }">
														<td class="not_available" width="${model.percentage }%"></td>
													</c:if>
													<c:if test="${model.status == '1' }">
														<td width="${model.percentage }%"></td>
													</c:if>
												</c:forEach>
											</tr>
										</table>
									</td>
									<td>100</td>
								</tr>

							</c:forEach> --%>
							<c:forEach items="${maplist }" var="list">
								
								<tr>
									<td><a href="${ctx}/os/linuxcentos/${list.id}">${list.name}</a></td>
									<td><table width="100%" border="0" cellspacing="0"
											cellpadding="0" class="green_bar">
											<tr>
												<c:forEach items="${list.list}" var="model">
											  	
													<c:if test="${model.status == '0'}">
														<td class="not_available" width="${model.percentage }%"></td>
													</c:if>
													<c:if test="${model.status == '2' }">
														<td class="not_available" width="${model.percentage }%"></td>
													</c:if>
													<c:if test="${model.status == '1' }">
														<td width="${model.percentage }%"></td>
													</c:if>  
												</c:forEach>
											</tr>
										</table>
									</td>
									<td>100</td>
								</tr>
								
								 
							</c:forEach> 
							<tr class="last_row">
								<td>&nbsp;</td>
								<td><table width="100%" border="0" cellspacing="0"
										cellpadding="0" class="ruler_bar">
										<tr>
											<td>&nbsp</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
										</tr>
									</table>
								</td>
								<td>&nbsp;</td>
							</tr>
							<tr class="last_row">
								<td>&nbsp;</td>
								<td><table width="100%" border="0" cellspacing="0"
										cellpadding="0" class="time_bar">
										<tr>
											<c:forEach items="${timeList }" var="time">
												<td>${time}</td>
											</c:forEach> 
										</tr>
									</table>
								</td>
								<td>&nbsp;</td>
							</tr>
						</table>
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
