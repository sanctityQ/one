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
    
	    <title>退回案件查询</title>
	    
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="${ctx}/common/css/Standard.css" rel="stylesheet" type="text/css" />
		<script src="${ctx}/common/calender/WdatePicker.js"></script>

	</head>
<body>
	<form name="fm" method="post" onkeypress="KeyDown()">
	<div style="width:100%">
		<table id="BeforeOverViewMain" class="common" cellpadding="3" cellspacing="0">
			<thead>
				<tr>
					<td class="formtitle" colspan="10"><img src="${ctx}/images/bgformtitle.gif" style="cursor:hand;">赔案列表</td>
				</tr>
				<tr class="tableHead">
					<td width="2%">&nbsp;</td>
					<td width="4%">序号</td>
					<td width="15%">立案号</td>
					<td width="10%">委托岗位</td>
					<td width="10%">委托日期</td>
					<td width="10%">委托操作员</td>
					<td width="15%">委托机构</td>
					<td width="10%">被委托岗位</td>
					<td width="15%">被委托机构</td>
					<td width="9%">委托标志</td>
				</tr>
		    </thead>
		</table>
		<table id="PageInfo" class="common" cellpadding="3" cellspacing="0">
			<tr>
				<td width="45%" align='right'><input type = "button" class="cssbutton" value="首  页"></td>
				<td width="5%"><input type = "button" class="cssbutton" value="上一页"></td>
				<td width="5%"><input type = "button" class="cssbutton" value="下一页"></td>
				<td width="45%"><input type = "button" class="cssbutton" value="尾  页"></td>
			</tr>
		</table>
		<table id="BeforeOverViewMain" class="common" cellpadding="3" cellspacing="1">
			<tr>
				<td class="formtitle" colspan="6"><img src="${ctx}/images/bgformtitle.gif" style="cursor:hand;">退回案件相关信息</td>
			</tr>
			<tr>
				<td class="left">立案号：</td>
				<td class="right"><input name="ClaimNum" class="common" type="text" onchange="clickable()"></td>
				<td class="left">委托岗位：</td>
				<td class="right"><input name="ClaimNum" class="common" type="text" onchange="clickable()"></td>
				<td class="left">委托日期：</td>
				<td class="right"><input name="ClaimNum" class="common" type="text" onchange="clickable()"></td>
			</tr>
			<tr>
				<td class="left">委托操作员：</td>
				<td class="right"><input name="ClaimNum" class="common" type="text" onchange="clickable()"></td>
				<td class="left">委托岗位：</td>
				<td class="right"><input name="ClaimNum" class="common" type="text" onchange="clickable()"></td>
				<td class="left">被委托岗位：</td>
				<td class="right"><input name="ClaimNum" class="common" type="text" onchange="clickable()"></td>
			</tr>
			<tr>
				<td class="left">被委托机构：</td>
				<td class="right"><input name="ClaimNum" class="common" type="text" onchange="clickable()"></td>
				<td class="common"> </td>
				<td class="common"></td>
				<td class="common"> </td>
				<td class="common"> </td>
			</tr>
			<tr>
				<td>退回原因</td>
			</tr>
			<tr>
				<td colspan="6"><textarea name="AccidentDesc" cols="" rows="4" style="width:100%"></textarea></td>
			</tr>
		</table>
	</div>
	</form>
</body>
</html>