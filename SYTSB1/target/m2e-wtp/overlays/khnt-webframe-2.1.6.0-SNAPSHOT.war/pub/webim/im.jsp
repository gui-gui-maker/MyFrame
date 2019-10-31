<%@page import="com.khnt.base.UserInf"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	//设置session 新框架去掉了原有的currentsession
	if (request.getSession().getAttribute(UserInf.NAME) == null) {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		request.getSession().setAttribute(UserInf.NAME, user);
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>WEBIM1.0(Web即时消息)</title>
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>
    <script type="text/javascript" src="pub/webim/js/base.js"></script>
    <script type="text/javascript">
    //window.onerror=function(){return true;}
    </script>
</head>
<body></body>
<html>