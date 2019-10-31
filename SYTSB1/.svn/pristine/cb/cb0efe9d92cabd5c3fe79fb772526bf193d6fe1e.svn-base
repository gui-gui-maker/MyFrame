<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true"%>
<%
	String xRequestedWith = request.getHeader("X-Requested-With");
	if (xRequestedWith != null
			&& xRequestedWith.equals("XMLHttpRequest")) {
		out.print("{\"sessionTimeout\":true}");
	} else {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.startsWith("application/json")) {
			response.setContentType("application/json;charset=UTF-8");
			out.print("{\"sessionTimeout\":true}");
		} else if (accept != null && accept.startsWith("text/xml")) {
			response.setContentType("text/xml;charset=UTF-8");
			out.print("<request><session-timeout>true</session-timeout></request>");
		} else {
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageKeys="sessionTimeOut">
<%@include file="/k/kui-base.jsp"%>
<title>Loading...</title>
<script type="text/javascript">
loadCoreLibrary("login");
</script>
</head>
<body>
	<div class="timeout">
		<div class="caption2" id="oSystemName">
			<div>
				<h1>
					<span>系统名称</span>
				</h1>
			</div>
		</div>
		<div class="tmot_center">
			<p>登录已过期，请重新登录！</p>
			<p>
				或者<a href="javascript:void(0);" onClick="sysReloadLogin();">请点击这里</a>重新登录
			</p>
		</div>
	</div>
</body>
</html>
<%
	}
	}
%>