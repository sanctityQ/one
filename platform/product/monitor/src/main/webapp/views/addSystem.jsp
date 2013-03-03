<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:c="http://www.springframework.org/schema/beans">
<head>
    <title>新建监视器</title>
    <link href="${ctx}/global/css/base.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/global/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/global/css/sinosoft.message.css" rel="stylesheet" type="text/css"/>
    <script language="javascript" src="${ctx}/global/js/jquery-1.7.1.js"></script>
    <script language="javascript" src="${ctx}/global/js/sinosoft.layout.js"></script>
    <script language="javascript" src="${ctx}/global/js/sinosoft.message.js"></script>
    <script type="text/javascript">
        $(function () {
            $("body").layout({
                top:{topHeight:100},
                bottom:{bottomHeight:30}
            });
            $("#myDesk").height($("#layout_center").height());
            $("#nav").delegate('li', 'mouseover mouseout', navHover);
            $("#nav,#menu").delegate('li', 'click', navClick);
        });
        function navHover() {
            $(this).toggleClass("hover")
        }
        function navClick() {
            $(this).addClass("seleck").siblings().removeClass("seleck");
            if ($(this).hasClass('has_sub')) {
                var subMav = $(this).children("ul.add_sub_menu");
                var isAdd = false;
                if ($(this).parent().attr("id") == "menu") {
                    isAdd = true;
                }
                ;
                subMav.slideDown('fast', function () {
                    $(document).bind('click', {dom:subMav, add:isAdd}, hideNav);
                    return false;
                });
            }
            ;
        }
        function hideNav(e) {
            var subMenu = e.data.dom;
            var isAdd = e.data.add;
            subMenu.slideUp('fast', function () {
                if (isAdd) {
                    subMenu.parent().removeClass('seleck');
                }
                ;
            });
            $(document).unbind();
        }
        function save() {
            msgSuccess("系统消息", "操作成功，监视器已保存！");
        }
    </script>
</head>

<body>
<div id="layout_top">
    <div class="header">
        <p class="user">您好,系统管理员 <span>|</span> <a href="#">退出系统</a></p>

        <div class="menu_box">
            <ul class="nav" id="nav">
                <li><a href="index.html">首页</a></li>
                <li class="has_sub">
                    <a href="javascript:viod(0)">监视器</a><span class="show_sub_anv"></span>
                    <ul class="add_sub_menu" id="subNav">
                        <li class="action"><span class="sever">操作系统</span>
                            <ul class="list">
                                <li><a href="javascript:viod(0)">操作系统1</a></li>
                                <li><a href="javascript:viod(0)">操作系统2</a></li>
                            </ul>
                        </li>
                        <li class="action"><span class="system">应用系统</span>
                            <ul class="list">
                                <li><a href="javascript:viod(0)">在线投保</a></li>
                                <li><a href="javascript:viod(0)">在线查询</a></li>
                                <li><a href="javascript:viod(0)">应急处置</a></li>
                                <li><a href="javascript:viod(0)">人员角色管理</a></li>
                            </ul>
                        </li>
                        <li class="action" style="border:none"><span>数据库</span>
                            <ul class="list">
                                <li><a href="javascript:viod(0)">SQL DBA</a></li>
                                <li><a href="javascript:viod(0)">SQL SYS</a></li>
                            </ul>
                        </li>
                        <li class="clear"></li>
                    </ul>

                </li>
                <li><a href="${ctx}/application/manager/appmanager/applist">应用性能</a></li>
                <li><a href="javascript:viod(0)">业务仿真</a></li>
                <li><a href="javascript:viod(0)">告警</a></li>
            </ul>
        </div>
        <ul class="add_menu" id="menu">
            <li><a href="addMonitorList.html">新建监视器</a></li>
            <li><a href="javascript:viod(0)">配置监视器</a></li>
            <li class="has_sub">
                <a href="javascript:viod(0)"><span>预警对象管理</span></a>
                <ul class="add_sub_menu">
                    <li class="title"><a href="javascript:viod(0)">显示动作</a></li>
                    <li class="action">创建新动作</li>
                    <li><a class="sms" href="javascript:viod(0)">短信动作</a></li>
                    <li><a class="email" href="javascript:viod(0)">邮件动作</a></li>
                </ul>
            </li>
        </ul>
    </div>
</div>
<div id="layout_center">
    <div class="main">
        <div class="add_monitor">
            <h2 class="title2"><b>新建监视器类型　</b>
                <select name="" class="diySelect" onchange="top.location=this.value;">
                    <optgroup label="应用服务器">
                        <option selected="selected" value="addSystem.html">应用系统　　</option>
                    </optgroup>
                    <optgroup label="数据库">
                        <option value="addOracle.html">Oracle</option>
                    </optgroup>
                    <optgroup label="操作系统">
                        <option value="addLinux.html">Linux</option>
                    </optgroup>
                </select>
            </h2>
            <form id="addSystem" action="${ctx}/application/manager/appmanager/add" method="post"
                  class="form-horizontal">
                <table width="100%" border="0" cellspacing="0" cellpadding="0" class="add_monitor_box add_form">
                    <tr>
                        <td colspan="2" class="group_name">基本信息</td>
                    </tr>
                    <tr>
                        <td width="25%">显示名称<span class="mandatory">*</span></td>
                        <td><input id="applicationName" name="applicationName" type="text" class="formtext"/></td>
                    </tr>
                    <tr>
                        <td width="25%">中文名称<span class="mandatory"></span></td>
                        <td><input id="cnName" name="cnName" type="text" class="formtext"/></td>
                    </tr>
                    <tr>
                        <td>主机IP地址<span class="mandatory">*</span></td>
                        <td><input id="applicationIp" name="applicationIp" type="text" class="formtext" size="30"/></td>
                    </tr>
                    <!--<tr>
                      <td>子网掩码<span class="mandatory">*</span></td>
                      <td><input name="input3" type="text" class="formtext" value="255.255.255.0" size="30" /></td>
                    </tr>-->
                    <tr>
                        <td>端口<span class="mandatory">*</span></td>
                        <td><input id="applicationPort" name="applicationPort" type="text" class="formtext" size="8"/></td>
                    </tr>
                    <tr>
                        <td class="group_name">&nbsp;</td>
                        <td class="group_name">
                            <!--<input type="button" class="buttons" value="确定添加" onclick="save()" />　-->
                            <input id="submit" type="submit" class="buttons" value="确定添加"/>　
                            <input type="reset" class="buttons" value="重 置"/>　
                            <input type="button" class="buttons" value="取 消" onclick="window.history.back()"/>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>
<div id="layout_bottom">
    <p class="footer">Copyright &copy; 2013 Sinosoft Co.,Lt</p>
</div>
</body>
</html>