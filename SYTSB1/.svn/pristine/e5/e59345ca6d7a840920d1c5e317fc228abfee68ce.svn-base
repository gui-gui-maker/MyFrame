<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head pageKeys="sysDesktop">
	<meta http-equiv="content-type" content="text/html; charset=utf-8"/>
	<title>Loading...</title>
	<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>
	<script type="text/javascript" src="k/kui/frame/core.js"></script>
	<script type="text/javascript" src="k/kui/frame/main.js"></script>
	<script type="text/javascript" src="k/kui/frame/sys-desktop.js"></script>

	<script type="text/javascript">
	<%
	CurrentSessionUser user = SecurityUtil.getSecurityUser();
	String orgName = user.getUnit().getOrgName();
	if(!user.getUnit().getId().equals(user.getDepartment().getId()))
		orgName += ("-" + user.getDepartment().getOrgName());
	%>
	var loginUserName={org:"<%=orgName%>",name:"<%=user.getName()%>"};
	var systemUserName="${pageContext.request.scheme}_${pageContext.request.serverName}_${pageContext.request.serverPort}${pageContext.request.contextPath}_"+loginUserName;
	</script>
	</head>
	<body>
	<div id="sysDesktop">

	</div>
</body>
</html>


