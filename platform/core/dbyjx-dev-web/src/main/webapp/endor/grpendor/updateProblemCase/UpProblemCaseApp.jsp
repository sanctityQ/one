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
    
    <title>��ȫ��������޸�</title>
    
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
	<div style = "width:100%">
		<table id="scanQueryInfo" class="common" cellpadding="3" cellspacing="0">
			<tr>
				<td class="formtitle" colspan="6"><img src="${ctx}/images/bgformtitle.gif" style="cursor:hand;">     �������ѯ����</td>
			</tr>
			<tr> 
				<td  class="left">��ȫ�����ţ�</td>
				<td  class="right"><input name="RepApplyNo" class="common" type="text"></td>
				<td class="left">�������ͣ�</td>
				<td class="right">
					<input name="ApplyType" class="codecode" type="text" onchange="clickable()" style="width:20%"><input name="comName" class="common" type="text" onchange="clickable()" style="width:65%">
				</td>
				<td  class="left">���屣���ţ�</td>
				<td  class="right"><input name="RepApplyNo" class="common" type="text"></td>
			</tr>
			<tr> 
				<td  class="left">�����ˣ�</td>
				<td  class="right"><input name="RepApplyNo" class="common" type="text"></td>
				<td class="left">���뷽ʽ��</td>
				<td class="right">
					<input name="ApplyType" class="codecode" type="text" onchange="clickable()" style="width:20%"><input name="comName" class="common" type="text" onchange="clickable()" style="width:65%">
				</td>
				<td class="left">ɨ¼�����ڣ�</td>
					<td class="right">
						<input name="ApplyDate" id="ApplyDate" class="common" type="text" onchange="clickable()" style="width: 73%" value='2012-04-28'>
						<img style='cursor: hand' align="absmiddle" src="${ctx}/images/bgcalendar.gif"  onClick="WdatePicker({el:'ApplyDate',startDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd',minDate:'#{%y-10}-%M-%d',maxDate:'#{%y+10}-%M-%d',alwaysUseStartDate:true})">
					</td>
			</tr>
			<tr> 
				<td class="left">����������</td>
				<td class="right">
					<input name="ApplyType" class="codecode" type="text" onchange="clickable()" style="width:20%"><input name="comName" class="common" type="text" onchange="clickable()" style="width:65%">
				</td>
				<td class="common"></td>
				<td class="common"></td>
				<td class="common"></td>
				<td class="common"></td>
			</tr>						
		</table>
		<table id="QueryScanInfo" class="common" cellpadding="3" cellspacing="0">
			<tr>
				<td colspan="6">
					<input type = "button" class="cssbutton" name="QueryButton" value="��  ѯ" onclick="">
				</td>
			</tr>
		</table>
		<table id="PublicInfo" class="common" cellpadding="3" cellspacing="0">
			<thead>
				<tr>
					<td class="formtitle" colspan="6"><img src="${ctx}/images/bgformtitle.gif" style="cursor:hand;">     ����������</td>
				</tr>
				<tr class="tableHead">
					<td width="3%">&nbsp;</td>
					<td width="5%">���</td>
					<td width="14%">��ȫ������</td>
					<td width="13%">���屣����</td>
					<td width="13%">Ͷ����λ</td>
					<td width="13%">���뷽ʽ</td>
					<td width="13%">��������</td>	
					<td width="13%">¼����Ա</td>	
					<td width="13%">¼������</td>								
				</tr>
			</thead>
			<tbody>
				<tr class="content">
					<td><input name="CheckRadio" type="radio" value="" /></td>
					<td>1</td>
					<td>SE86012012005</td>
					<td>P5646860101</td>
					<td>�»����</td>
					<td>11</td>
					<td>860101</td>
					<td>����</td>
					<td>2012-05-18</td>
				</tr>					
				</tr>
			</tbody>
			<table id="PageInfo1" class="common" cellpadding="3" cellspacing="0">
				<tr>
					<td width="45%" align='right'><input type = "button" class="cssbutton" value="��  ҳ" onclick="location.href='ReportAuditDeal.jsp'"></td>
					<td width="5%"><input type = "button" class="cssbutton" value="��һҳ"></td>
					<td width="5%"><input type = "button" class="cssbutton" value="��һҳ"></td>
					<td width="45%"><input type = "button" class="cssbutton" value="β  ҳ"></td>
				</tr>
			</table>
		</table>
		<table id="ApplyButtonInfo" class="common" cellpadding="3" cellspacing="0">
			<tr>
				<td colspan="6">
					<input type = "button" class="cssbutton" name="EndorAccept" value="��  ��" onclick="">
				</td>
			</tr>
		</table>
		<table id="PublicInfo" class="common" cellpadding="3" cellspacing="0">
			<thead>
				<tr>
					<td class="formtitle" colspan="6"><img src="${ctx}/images/bgformtitle.gif" style="cursor:hand;">     �ҵ�����</td>
				</tr>
				<tr class="tableHead">
					<td width="3%">&nbsp;</td>
					<td width="5%">���</td>
					<td width="13%">��ȫ������</td>
					<td width="13%">���屣����</td>
					<td width="14%">Ͷ����λ</td>
					<td width="13%">���뷽ʽ</td>
					<td width="13%">��������</td>
					<td width="13%">¼��Ա</td>
					<td width="13%">¼������</td>					
				</tr>
			</thead>
			<tbody>
				<tr class="content">
					<td><input name="CheckRadio" type="radio" value="" /></td>
					<td>1</td>
					<td>SE86012012005</td>
					<td>P154635464646</td>
					<td>��·��ͨ����</td>
					<td>�ͻ����԰���</td>
					<td>����չ�˾</td>
					<td>����</td>
					<td>2012-05-18</td>
				</tr>
			</tbody>
			<table id="PageInfo1" class="common" cellpadding="3" cellspacing="0">
				<tr>
					<td width="45%" align='right'><input type = "button" class="cssbutton" value="��  ҳ" onclick=""></td>
					<td width="5%"><input type = "button" class="cssbutton" value="��һҳ"></td>
					<td width="5%"><input type = "button" class="cssbutton" value="��һҳ"></td>
					<td width="45%"><input type = "button" class="cssbutton" value="β  ҳ"></td>
				</tr>
			</table>
		</table>
		<table id="ApplyButtonInfo" class="common" cellpadding="3" cellspacing="0">
			<tr>
				<td colspan="6">
					<input type = "button" class="cssbutton" name="EndorAccept" value="������޸�" onclick="location.href='../endorNoScanInput/GrpEndorApplyAccept.jsp'">
					<input type = "button" class="cssbutton" name="EndorAccept" value="���±��鿴" onclick="">
				</td>
			</tr>
		</table>				
	</div>
	</form>
  </body>
</html>