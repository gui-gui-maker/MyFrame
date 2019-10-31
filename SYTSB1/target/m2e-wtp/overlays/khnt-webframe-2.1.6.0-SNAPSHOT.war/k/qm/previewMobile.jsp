<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://khnt.com/tags/qm" prefix="qm" %>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <title>iscrollview Demo</title>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black"/>

    <link href="k/jqm/themes/default/jquery.mobile.min.css" media="screen" rel="stylesheet" type="text/css"/>
    <link href="k/jqm/themes/default/jquery.mobile.iscrollview.css" media="screen" rel="stylesheet" type="text/css"/>
    <link href="k/jqm/themes/default/jquery.mobile.iscrollview-pull.css" media="screen" rel="stylesheet" type="text/css"/>

    <script src="k/jqm/jquery2.js" type="text/javascript"></script>
    <script src="k/jqm/jquery.mobile.js" type="text/javascript"></script>
    <script src="k/jqm/iscroll.js" type="text/javascript"></script>
    <script src="k/jqm/jquery.mobile.iscrollview.js" type="text/javascript"></script>
</head>

<body>
<div class="qm-page" data-role="page">
    <qm:qm pageid="${param.pageid}"/></div>
</body>
</html>