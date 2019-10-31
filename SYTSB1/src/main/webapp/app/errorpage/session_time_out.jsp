<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">


<head pageKeys="sessionTimeOut">
<title></title>
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>

<script type="text/javascript" src="k/kui/frame/core.js"></script>
<script type="text/javascript" src="k/kui/frame/main.js"></script>
<script type="text/javascript" src="app/public/k-frame-login.js"></script>

</head>
<body>
<div class="timeout">
	<div class="caption2"></div>
	<div class="tmot_center">
		<p>登录已过期，请重新登录！</p>
		<p>或者<a href="javascript:void(0);" onClick="sysReloadLogin();">请点击这里</a>重新登录</p>
	</div>
</div>
</body>
</html>
