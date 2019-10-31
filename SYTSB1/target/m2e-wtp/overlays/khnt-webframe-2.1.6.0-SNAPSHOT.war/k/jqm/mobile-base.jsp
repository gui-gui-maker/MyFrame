<%@ page import="com.khnt.base.Factory" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://khnt.com/tags/qm" prefix="qm" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>
<%
String uriPre= Factory.getSysPara().getProperty("APP_RES_URI_PRE","content://com.khnt.ytmzzd.activity");
String userAgent=request.getHeader("user-agent");
%>
<style>
.l-text-invalid {border-color:#FF7777;background:#FFEEEE url(${pageContext.request.contextPath}/k/kui/skins/default/images/common/invalid-line.gif) repeat-x bottom !important;}
.requiredstar {background:url(${pageContext.request.contextPath}/k/kui/skins/default/images/common/required2.gif) no-repeat right center !important;}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no"/>
<meta name="apple-mobile-web-app-capable" content="yes"/>
<meta name="apple-mobile-web-app-status-bar-style" content="black"/>

<link href="k/jqm/themes/default/jquery.mobile.min.css" media="screen" rel="stylesheet" type="text/css"/>
<link href="k/jqm/themes/default/jquery.mobile.iscrollview.css" media="screen" rel="stylesheet" type="text/css"/>
<link href="k/jqm/themes/default/jquery.mobile.iscrollview-pull.css" media="screen" rel="stylesheet" type="text/css"/>
<link href="k/jqm/themes/jquery.mobile.datepicker.css" media="screen" rel="stylesheet" type="text/css"/>
<link href="k/jqm/themes/jquery.mobile.datepicker.theme.css" media="screen" rel="stylesheet" type="text/css"/>

<script type="text/javascript" charset="utf-8" src="k/jqm/jquery2.js"></script>
<script type="text/javascript" charset="utf-8" src="k/jqm/jquery.mobile.js"></script>
<script type="text/javascript" charset="utf-8" src="k/jqm/jquery.mobile.datepicker.js"></script>
<script type="text/javascript" charset="utf-8" src="k/jqm/datepicker.js"></script>
<script type="text/javascript" charset="utf-8" src="k/jqm/iscroll.js"></script>
<script type="text/javascript" charset="utf-8" src="k/jqm/jquery.mobile.iscrollview.js"></script>
<script type="text/javascript" charset="utf-8" src="k/jqm/mobile.lib.js"></script>
<script src="k/kui/frame/jquery.validate.min.js" type="text/javascript"></script>
<script src="k/kui/frame/messages_cn.js" type="text/javascript"></script>


