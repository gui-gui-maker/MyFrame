<%@page import="com.khnt.base.Factory"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<%@include file="/k/kui-base-form.jsp" %>
	<script src="pub/xiuxiu/xiuxiu.js"
	type="text/javascript"></script>
	<script type="text/javascript">
	window.onload = function() {
		/*第1个参数是加载编辑器div容器，第2个参数是编辑器类型，第3个参数是div容器宽，第4个参数是div容器高*/
		xiuxiu.embedSWF("altContent", 5, "100%", "100%");
		//修改为您自己的图片上传接口
		var fname = '<sec:authentication property="principal.id" ></sec:authentication>';
		xiuxiu.setUploadURL("<%=basePath%>/fileupload/upload.do?folder=head&fname="+fname+'.jpg');
		xiuxiu.setUploadType(2);
		xiuxiu.setUploadDataFieldName("upload_file");
		xiuxiu.onInit = function() {
			//xiuxiu.loadPhoto("<%=basePath%>/k/kui/images/sys-desktop/bg/bg014m.jpg");
		}
		xiuxiu.onUploadResponse = function(data) {
			data = data.replace("\\","/");
			data = $.parseJSON(data);
			api.data.window.setIcon("fileupload/download.do?id="+data.data.id);
			api.close();
		}
	}
</script>
<style type="text/css">
html,body {
	height: 100%;
	overflow: hidden;
}

body {
	margin: 0;
}
</style>
</head>
<body>
	<div id="altContent">
		<h1>美图秀秀</h1>
	</div>
</body>
</html>