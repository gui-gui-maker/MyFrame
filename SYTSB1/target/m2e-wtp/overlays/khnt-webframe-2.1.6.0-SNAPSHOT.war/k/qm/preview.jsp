<%@ page import="com.khnt.qm.QmController" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://khnt.com/tags/qm" prefix="qm" %>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>通用查询预览</title>
    <style type="text/css">
        .l-icon {width:22px;height:22px;overflow:hidden;background-position:center center;background-repeat:no-repeat;}
    </style>
    <script type="text/javascript">
        var qmUserConfig = {
            listeners: {
                beforeQmReady :function(){
                    Qm.pagingUrlPre = "<%=QmController.servletName%>?isPreview=true&__method=";
                }
            }
        };
    </script>
</head>
<body>
<qm:qm script="true" pageid="${param.pageid}" clientModel="ligerui"/>
</body>
</html>
