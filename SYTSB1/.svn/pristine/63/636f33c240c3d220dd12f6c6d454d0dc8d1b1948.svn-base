<%@ page import="com.khnt.base.Factory" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>
<%
    String uriPre= Factory.getSysPara().getProperty("APP_RES_URI_PRE","content://com.khnt.ytmzzd.activity");
    String userAgent=request.getHeader("user-agent");
%>
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no"/>
<meta name="apple-mobile-web-app-capable" content="yes"/>
<meta name="apple-mobile-web-app-status-bar-style" content="black"/>

<link rel="stylesheet" href="k/jqm/skins/default.css"/>
<%if(userAgent.indexOf("Android 4.4")!=-1){%>
<script type="text/javascript" charset="utf-8" src="k/jqm/jquery2.js"></script>
<script type="text/javascript" charset="utf-8" src="k/jqm/jquery.mobile.js"></script>
<%}else{%>
<script type="text/javascript" charset="utf-8" src="<%=uriPre%>/jquery2.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=uriPre%>/jquery_mobile.js"></script>
<%}%>
<script type="text/javascript" charset="utf-8" src="k/jqm/a-main.js"></script>