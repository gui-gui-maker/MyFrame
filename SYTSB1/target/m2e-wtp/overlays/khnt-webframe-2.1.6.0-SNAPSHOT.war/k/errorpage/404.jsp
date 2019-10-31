<%@ page language="java" pageEncoding="utf-8" trimDirectiveWhitespaces="true"%>
<%
	String xRequestedWith = request.getHeader("X-Requested-With");
	if (xRequestedWith != null
			&& xRequestedWith.equals("XMLHttpRequest")) {
		out.print("{\"404\":true}");
	} else {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.startsWith("application/json")) {
			response.setContentType("application/json;charset=UTF-8");
			out.print("{\"404\":true}");
		} else if (accept != null && accept.startsWith("text/xml")) {
			response.setContentType("text/xml;charset=UTF-8");
			out.print("<request><404>true</404></request>");
		} else {
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/k/kui-base.jsp"%>
<title>Loading...</title>
<script type="text/javascript">
loadCoreLibrary("login");
</script>
</head>
<body>
	<div class="timeout">
		<div class="caption" id="oSystemName">
			<div>
				<h1>
					<span>系统名称</span>
				</h1>
			</div>
		</div>
		<div class="tmot_center">
			<p>哎呦！服务器找不到给定的资源；文件不存在</p>
			<p>
				<a href="javascript:void(0);" onClick="javascript:history.go(-1);">点击这里返回</a>
			</p>
		</div>
	</div>
</body>
</html>
<%
	}
	}
%>