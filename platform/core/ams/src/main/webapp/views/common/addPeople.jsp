<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>权限管理-功能菜单管理</title>
<link type="text/css" rel="stylesheet" href="../css/sinosoft.base.css" />
<link type="text/css" rel="stylesheet" href="../css/sinosoft.tree2.css" />
<script type="text/javascript" src="../js/jquery-1.7.1.js"></script>
<script type="text/javascript" src="../js/sinosoft.tree.js"></script>
<script type="text/javascript" src="../js/sinosoft.mouseoutclick.js"></script>
<script type="text/javascript">
$(function(){
	$("#treeTow").jstree({ 
			"themes" : {
				"theme" : "default",
				"dots" : false,
			},
			"json_data" : {
				"ajax" : {
						"url" : "tree.json"
				}
			},
			"plugins" : [ "themes", "json_data", "checkbox", "ui" ]
		});
	fitHeight();
});
function fitHeight(){
	var pageHeight = $(document).height() - 102;
	$("#treeOne").height(pageHeight);
	$("#treeTow").height(pageHeight);
};
</script>
</head>

<body>
<table border="0" cellspacing="0" cellpadding="0" class="authorize">
  <tr>
    <td width="269" valign="top">
      <div class="title2"><b>角色</b></div>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="info_form">
          <tr>
            <td align="right">角色名称：</td>
            <td><input type="text" style="width:160px;" /></td>
        </tr>
          <tr>
            <td align="right">类型：</td>
            <td>
            	<select name="select">
                  <option>默认类型</option>
                  <option>所有可见类型</option>
                </select>
            </td>
        </tr>
          <tr>
            <td align="right">角色描述：</td>
            <td>
            	<textarea name="textarea" cols="30" rows="4" style="width:160px;"></textarea>
            </td>
          </tr>
      </table>
    </td>
    <td width="30" valign="top">&nbsp;</td>
    <td width="269" valign="top">
        <div class="title2"><b>权限列表</b></div>
        <div id="treeTow" class="tree_view"></div>
    </td>
  </tr>
</table>
</body>
</html>