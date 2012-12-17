<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@include file="/common/taglibs.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>投保单查询任务申请</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="${ctx}/common/css/Standard.css" rel="stylesheet" type="text/css" />
	<script src="${ctx}/common/calender/WdatePicker.js"></script>
	<script type="text/javascript">ctx="${ctx}"</script>
	<script type="text/javascript" src="${ctx}/common/js/jquery-1.7.1.js"></script>
	<script type="text/javascript" src="${ctx}/common/js/QueryCodeAll.js"></script>
  </head>
  <body>
    <form name="fm" method="post" onkeypress="KeyDown()">
	<div style = "width:100%">
		<table id="scanQueryInfo" class="common" cellpadding="3" cellspacing="0">
			<tr>
				<td class="formtitle" colspan="6"><img src="${ctx}/images/bgformtitle.gif" style="cursor:hand;">     请输入查询条件</td>
			</tr>
			<tr> 
				<td class="left">投保单号：</td>
				<td class="right"><input name="ManageCom" class="common" type="text"></td>
				<td class="left">管理机构：</td>
				<td class="right"><input class="codecode" id="manageCom" name="lcReport.manageCom" class="common" type="text" value="2000000122" style="width:20%" ondblclick="queryCode('manageCom','comName','PrpDcompany','{comCode:21102}');"><input id="comName" name="comName" class="common" type="text" onchange="clickable()" style="width:68%" value="都邦北京分公司"></td>
				<td class="left">业务员编码：</td>
				<td class="right"><input class="codecode" id="manageCom" name="lcReport.manageCom" class="common" type="text" value="2000000122" style="width:20%" ondblclick="queryCode('manageCom','comName','PrpDcompany','{comCode:21102}');"><input id="comName" name="comName" class="common" type="text" onchange="clickable()" style="width:68%" value="李四"></td>
			</tr>
			<tr>
				<td class="left">投保单位名称：</td>
				<td class="right"><input name="GrpName" class="common" type="text"></td>
				<td class="common"></td>
				<td class="common"></td>
				<td class="common"></td>
				<td class="common"></td>
			</tr>
		</table>
		<br>
		<table id="QueryScanInfo" class="common" cellpadding="3" cellspacing="0">
			<tr>
				<td><input type = "button" class="cssbutton" name="QueryButton" value="查  询" onclick=""></td>
			</tr>
		</table>
		<br>
		<div id="ListInfo" style="width:65%">
			<table id="PublicInfo" class="common" cellpadding="3" cellspacing="0">
				<thead>
					<tr>
						<td class="formtitle" colspan="6"><img src="${ctx}/images/bgformtitle.gif" style="cursor:hand;">     投保单信息</td>
					</tr>
					<tr class="tableHead">
						<td width="3%">&nbsp;</td>
						<td width="5%">序号</td>
						<td width="31%">投保单号</td>
						<td width="31%">投保单位名称</td>
						<td width="30%">业务员代码</td>
					</tr>
				</thead>
				<tbody>
					<tr class="content">
						<td><input name="CheckRadio" type="radio" value="" /></td>
						<td>1</td>
						<td>G86012012005</td>
						<td>都邦保险公司北京分公司</td>
						<td>22341231</td>
					</tr>
				</tbody>
			</table>
			<table id="PageInfo1" class="common" cellpadding="3" cellspacing="0">
				<tr>
					<td width="45%" align='right'><input type = "button" class="cssbutton" value="首  页" onclick="location.href='ReportAuditDeal.jsp'"></td>
					<td width="5%"><input type = "button" class="cssbutton" value="上一页"></td>
					<td width="5%"><input type = "button" class="cssbutton" value="下一页"></td>
					<td width="45%"><input type = "button" class="cssbutton" value="尾  页"></td>
				</tr>
			</table>
		</div>
		<br>
		<table id="ApplyButtonInfo" class="common" cellpadding="3" cellspacing="0">
			<tr>
				<td>
					<input type = "button" class="cssbutton" name="ApplyButton" value="状态查询" onclick="location.href='./StateQuery.jsp'">
					<input type = "button" class="cssbutton" name="ApplyButton" value="集体投保单明细" onclick="location.href='../imageInput/ImageInput.jsp'">
					<input type = "button" class="cssbutton" name="ApplyButton" value="团体扫描件查询" onclick="">
					<input type = "button" class="cssbutton" name="ApplyButton" value="团体已承保保单查询" onclick="">
					<input type = "button" class="cssbutton" name="ApplyButton" value="团体未承保保单查询" onclick="location.href='./GroupNoPrpallProposalQuery.jsp'">
				</td>
			</tr>
			<tr>
				<td><input type = "button" class="cssbutton" name="ApplyButton" value="操作履历查询" onclick="location.href='OperationRecordQuery.jsp'"></td>
			</tr>
		</table>
	</div>
	</form>
  </body>
</html>
