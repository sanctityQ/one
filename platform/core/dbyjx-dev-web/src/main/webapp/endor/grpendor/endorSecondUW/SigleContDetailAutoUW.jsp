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
    
    <title>���˺˱���Ϣ</title>
    
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
				<td class="formtitle" colspan="6"><img src="${ctx}/images/bgformtitle.gif" style="cursor:hand;">     �����˲�ѯ</td>
			</tr>
			<tr> 
				<td class="left">�ֵ��ţ�</td>
				<td class="right"><input name="GrpName" class="common" type="text"></td>
				<td class="left">�����˺ţ�</td>
				<td class="right"><input name="GrpName" class="common" type="text"></td>
				<td class="left">Ա����ţ�</td>
				<td class="right"><input name="GrpName" class="common" type="text"></td>				
			</tr>
			 <tr>
			 	<td class="left">�������Ա�</td>
				<td class="right"><input name="GrpName" class="common" type="text"></td>
				<td class="left">�Ա�</td>
				<td class="right">
					<input name="ApplyType" class="codecode" type="text" onchange="clickable()" style="width:20%"><input name="comName" class="common" type="text" onchange="clickable()" style="width:68%">
				</td>
				<td class="left">�������ڣ�</td>
				<td class="right">
					<input name="ApplyDate" id="ApplyDate" class="common" type="text" onchange="clickable()" style="width: 73%" value='2012-04-28'>
					<img style='cursor: hand' align="absmiddle" src="${ctx}/images/bgcalendar.gif"  onClick="WdatePicker({el:'ApplyDate',startDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd',minDate:'#{%y-10}-%M-%d',maxDate:'#{%y+10}-%M-%d',alwaysUseStartDate:true})">
				</td>
			 </tr>
			 <tr>
				<td class="left">֤�����ͣ�</td>
				<td class="right">
					<input name="ApplyType" class="codecode" type="text" onchange="clickable()" style="width:20%"><input name="comName" class="common" type="text" onchange="clickable()" style="width:68%">
				</td>
			 	<td class="left">֤�����룺</td>
				<td class="right"><input name="GrpName" class="common" type="text"></td>
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
					<td class="formtitle" colspan="6"><img src="${ctx}/images/bgformtitle.gif" style="cursor:hand;">     �����˲�ѯ���</td>
				</tr>
				<tr class="tableHead">
					<td width="3%">&nbsp;</td>
					<td width="5%">���</td>
					<td width="15%">�ֵ���</td>
					<td width="11%">�����˺�</td>
					<td width="11%">����������</td>
					<td width="11%">�Ա�</td>					
					<td width="11%">��������</td>
					<td width="11%">֤������</td>					
					<td width="11%">֤������</td>
					<td width="11%">�˱�����</td>					
				</tr>
			</thead>
			<tbody>
				<tr class="content">
					<td><input name="CheckRadio" type="radio" value="" /></td>
					<td>1</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
			</tbody>
		</table>
		<table id="scanQueryInfo" class="common" cellpadding="3" cellspacing="0">
			<tr>
				<td class="formtitle" colspan="6"><img src="${ctx}/images/bgformtitle.gif" style="cursor:hand;">     ��������Ϣ</td>
			</tr>
			<tr> 
				<td class="left">�����˿ͻ��ţ�</td>
				<td class="right"><input name="GrpName" class="common" type="text"></td>
				<td class="left">������������</td>
				<td class="right"><input name="GrpName" class="common" type="text"></td>
				<td class="left">�������Ա�</td>
				<td class="right"><input name="GrpName" class="common" type="text"></td>				
			</tr>
			 <tr>
				<td class="left">�������ڣ�</td>
				<td class="right">
					<input name="ApplyDate" id="ApplyDate" class="common" type="text" onchange="clickable()" style="width: 73%" value='2012-04-28'>
					<img style='cursor: hand' align="absmiddle" src="${ctx}/images/bgcalendar.gif"  onClick="WdatePicker({el:'ApplyDate',startDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd',minDate:'#{%y-10}-%M-%d',maxDate:'#{%y+10}-%M-%d',alwaysUseStartDate:true})">
				</td>
				<td class="left">֤�����ͣ�</td>
				<td class="right">
					<input name="ApplyType" class="codecode" type="text" onchange="clickable()" style="width:20%"><input name="comName" class="common" type="text" onchange="clickable()" style="width:68%">
				</td>
			 	<td class="left">֤�����룺</td>
				<td class="right"><input name="GrpName" class="common" type="text"></td>				
			 </tr>
		</table>
		<table id="ApplyButtonInfo" class="common" cellpadding="3" cellspacing="0">
			<tr>
				<td class="formtitle" colspan="6"><img src="${ctx}/images/bgformtitle.gif" style="cursor:hand;">    �������˸�����Ϣ��ѯ</td>
			</tr>		
			<tr>
				<td colspan="6">
					<input type = "button" class="cssbutton" name="EndorAccept" value="���˱�����ϸ��Ϣ" onclick="">
					<input type = "button" class="cssbutton" name="EndorAccept" value="�����˽�����֪" onclick="">
					<input type = "button" class="cssbutton" name="EndorAccept" value="�������������" onclick="">
					<input type = "button" class="cssbutton" name="EndorAccept" value="�����˱����ۼ�" onclick="">					
				</td>
			</tr>
			<tr>
				<td colspan="6">
					<input type = "button" class="cssbutton" name="EndorAccept" value="�������ѳб�����" onclick="">
					<input type = "button" class="cssbutton" name="EndorAccept" value="������δ�б�����" onclick="">
					<input type = "button" class="cssbutton" name="EndorAccept" value="�����˼�����ȫ" onclick="">
					<input type = "button" class="cssbutton" name="EndorAccept" value="�����˼�������" onclick="">					
				</td>
			</tr>			
		</table>		
		<table id="PublicInfo" class="common" cellpadding="3" cellspacing="0">
			<thead>
				<tr>
					<td class="formtitle" colspan="6"><img src="${ctx}/images/bgformtitle.gif" style="cursor:hand;">     ���˱���������Ϣ</td>
				</tr>
				<tr class="tableHead">
					<td width="3%">&nbsp;</td>
					<td width="5%">���</td>
					<td width="15%">���ִ���</td>
					<td width="15%">��������</td>
					<td width="15%">��������</td>
					<td width="15%">Ͷ������</td>
					<td width="15%">��׼����</td>
					<td width="17%">�˱�����</td>
				</tr>
			</thead>
			<tbody>
				<tr class="content">
					<td><input name="CheckRadio" type="radio" value="" /></td>
					<td>1</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
			</tbody>
		</table>
		<table id="ApplyButtonInfo" class="common" cellpadding="3" cellspacing="0">
			<tr>
				<td class="formtitle" colspan="6"><img src="${ctx}/images/bgformtitle.gif" style="cursor:hand;">     ��ȫ�˱�����</td>
			</tr>		
			<tr>
				<td colspan="6">
					<input type = "button" class="cssbutton" name="EndorAccept" value="���¼��" onclick="location.href='../../../prpall/group/artificalUW/PhysicalExamInput.jsp'">
					<input type = "button" class="cssbutton" name="EndorAccept" value="����¼��" onclick="location.href='../../../prpall/group/artificalUW/ContSearchDataInput.jsp'">
					<input type = "button" class="cssbutton" name="EndorAccept" value="��Լ¼��" onclick="location.href='./SpecialPromiseInput.jsp'">
				</td>
			</tr>
		</table>
		<table id="PublicInfo" class="common" cellpadding="3" cellspacing="0">
			<tr>
				<td class="formtitle" colspan="6"><img src="${ctx}/images/bgformtitle.gif" style="cursor:hand;">     �������ֺ˱����� </td>
			</tr>
			<tr>
				<td class="left">�˱����ۣ�</td>
				<td class="right">
					<input name="ApplyType" class="codecode" type="text" onchange="clickable()" style="width:20%"><input name="comName" class="common" type="text" onchange="clickable()" style="width:68%" readonly>
				</td>
				<td class="common"></td>
				<td class="common"></td>
				<td class="common"></td>
				<td class="common"></td>
			</tr>
			<tr>
				<td colspan="6">�˱���� </td>
			</tr>
			<tr>
				<td colspan="4"><textarea name="UWIdea" cols="100%" rows="5"></textarea></td>
			</tr>
		</table>
		<table id="PublicInfo" class="common" cellpadding="3" cellspacing="0">
			<tr>
				<td colspan="6">
					<input type = "button" class="cssbutton" name="EndorAccept" value="ȷ  �� " onclick="">
					<input type = "button" class="cssbutton" name="EndorAccept" value="��  ��" onclick="">
					<input type = "button" class="cssbutton" name="EndorAccept" value="��  ��" onclick="javascript:history.go(-1);">
				</td>
			</tr>
		</table>							
	</div>
	</form>
  </body>
</html>