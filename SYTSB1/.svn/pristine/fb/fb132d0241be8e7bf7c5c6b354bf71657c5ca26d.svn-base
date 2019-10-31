<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
    Enumeration en = request.getParameterNames();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ include file="/k/kui-base-form.jsp"%>
<style type="text/css">
body {
	text-align: center;
}
</style>
<script type="text/javascript">
</script>
</head>
<body>
	<div style="text-align:left">
		<%
			while(en.hasMoreElements()){
				String param = en.nextElement().toString();
				%>
				钻取获取的参数：<%=param %> 参数值：<%=request.getParameter(param) %></br>
				<%
			}
		%>
	</div>
</body>
</html>
