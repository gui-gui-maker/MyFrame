<%@ page import="com.khnt.security.CurrentSessionUser" %>
<%@ page import="com.khnt.security.util.SecurityUtil" %><%--
  Created by IntelliJ IDEA.
  User: songxuemao
  Date: 16/5/25
  Time: 19:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<% CurrentSessionUser user = SecurityUtil.getSecurityUser();%>
<html>
<head pageStatus="${param.status}">
    <title></title>
    <%@ include file="/k/kui-base-form.jsp" %>
    <script type="text/javascript" src="pub/bpm/js/util.js"></script>
    <script type="text/javascript">
        $(function () {
            var tbar = [
                {text: '是', id: 'up', icon: 'save', click: submit_bzb},
                {text: '否', id: 'sss', icon: 'save', click: submit},
                {
                    text: '关闭', id: 'close', icon: 'cancel', click: function () {
                    api.close();
                }
                }];
            $("#form1").initForm({
                toolbar: tbar,
                success: function (resp) {
                    if ( resp.success ) {

                    } else {
                        $.ligerDialog.error(resp.msg);
                    }
                },
                getSuccess: function (resp) {
                }
            });
        });

        function submit_bzb() {
            api.close();
            api.data.callback(true);
        }

        function submit() {
            api.close();
            api.data.callback(false);
        }


    </script>
</head>
<body>
<div class="title-tm">
    <div class="l-page-title has-icon no-note">
        <div class="l-page-title-div"></div>
        <div class="l-page-title-text"><h1>是否需要提交到纪检分管院领导审核？</h1>
            <font size="3px"><b>说明：</b></font><br>选择“是”，提交到纪检分管院领导审核<br>
            选择“否”，审核流程结束。
        </div>
        <div class="l-page-title-icon"><img src="k/kui/images/icons/32/places.png" border="0"></div>
    </div>
</div>
<form name="form1" id="form1" method="post" action="" getAction="">
</form>
</body>
</html>