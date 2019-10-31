<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<head pageStatus="${param.status}">
<%@ taglib uri="http://khnt.com/tags/chart" prefix="chart" %>
<link rel="stylesheet" type="text/css" href="pub/chart/css/chart.css" />
<%
	CurrentSessionUser user = SecurityUtil.getSecurityUser();
	String userid= user.getId();


%>
<%@ include file="/k/kui-base-form.jsp"%>
<link rel="stylesheet" href="app/cloud_platform/upload/bootstrap/easyui.css" type="text/css"></link>
<!-- <script type="text/javascript" src="app/cloud_platform/upload/jquery-1.8.0.min.js"></script> -->
<script type="text/javascript" src="app/cloud_platform/owner/js/selectUnitOrUser.js"></script>
<script type="text/javascript" src="app/cloud_platform/owner/js/jquery.contextmenu.r2.js"></script>
<script type="text/javascript" src="app/cloud_platform/upload/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript">
var pageStatus = "${param.status}";	
$(function() {
	
		
	})
	
</script>
</head>
<body>
	<form name="formObj" id="formObj" method="post"><div style="width:100%; height:220px;" align="right">
			<div id="div1" style="width:40%; height:100%; border:hidden; background: white;"><br><br><font color="#999999">正在加载图形数据,请稍候...</font>
				<chart:chart chartNum="tjypt_space_chart" renderAt="div1" paramValue="4028809c581d6e7d01581eec62a20011"/>
				</div>
			</div>
	</form>
</body>
</html>
