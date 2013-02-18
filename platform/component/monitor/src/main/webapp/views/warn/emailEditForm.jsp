<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="msg" uri="http://mvc.one.sinosoft.com/validation/msg" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>预警对象配置</title>
    <%@ include file="/WEB-INF/layouts/base.jsp" %>
    <script type="text/javascript">
        $(document).ready(function () {
            //聚焦第一个输入框
            $("#emailAndSms-tab").addClass("active");
        });

    </script>

    <script language="javascript">
        function isValid(form) {
            if (form.newAddress.value.length == 0) {
                alert("邮箱地址不能为空！");
                return false;
            }
            else if (form.newAddress.value != form.addressConfirm.value) {
                alert("两次输入的邮箱不同！");
                return false;
            }
            return true;
        }
    </script>


</head>
<%--action="${ctx}/warn/email/save/${email.id}--%>
<body>
<div class="container">
    <%@ include file="/WEB-INF/layouts/header.jsp" %>
    <div id="content" class="span12">
        <form:form id="inputForm" modelAttribute="user"
                   action="${ctx}/warn/email/save/${email.id}" method="post" enctype="multipart/form-data"
                   class="form-horizontal" onsubmit="return isValid(this);">
            <fieldset>
                <legend>
                    <small>编辑邮箱</small>
                </legend>
                <div id="messageBox" class="alert alert-error" style="display:none">输入有误，请先更正。</div>
                <div class="control-group">
                    <div class="control-group">
                        <label for="address" class="control-label">原邮箱:</label>
                        <div class="controls">
                            <input type="address" id="address" name="address" size="100" value="${email.address}"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="newAddress" class="control-label">新邮箱:</label>

                        <div class="controls">
                            <input type="newAddress" id="newAddress" name="newAddress" size="100"
                                   class="required"/>
                            <msg:errorMsg property="address" type="message"/></div>
                    </div>
                    <div class="control-group">
                        <label for="addressConfirm" class="control-label">确认邮箱:</label>
                        <div class="controls">
                            <input type="addressConfirm" id="addressConfirm" name="addressConfirm" size="100"
                                   class="required"/>
                        </div>
                    </div>
                    <div class="form-actions">
                        <input id="submit" class="btn btn-primary" type="submit" value="保存"/>&nbsp;
                        <input id="cancel" class="btn" type="button" value="返回" onclick="history.back()"/>
                    </div>
                </div>
            </fieldset>
        </form:form>
    </div>
    <%@ include file="/WEB-INF/layouts/footer.jsp" %>
</div>
</body>
</html>
