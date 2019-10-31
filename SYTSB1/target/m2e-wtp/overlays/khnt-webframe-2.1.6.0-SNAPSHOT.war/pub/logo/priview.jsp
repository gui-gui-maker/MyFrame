<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ include file="/k/kui-base-form.jsp"%>
<style type="text/css">
body {
	text-align: center;
}
</style>
<script type="text/javascript">
	$(function(){
		$("img").attr("src","${param.path}");
	})
</script>
</head>
<body>
	<img id="img" src=""/>
</body>
</html>
