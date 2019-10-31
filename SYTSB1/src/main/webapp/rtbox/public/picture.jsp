<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>

<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
var imgId = "${param.imgId}";

function chosePic(type){
	try {
		api.data.window.changeCallback(type,api.data.up);
		api.close();
	} catch (e) {
		// TODO: handle exception
	}
	
}

</script>
</head>
<body>
	<div align="center" style="margin-top: 20px;">
		<div
			style="width: 100px; float: left; margin-right: 15px; margin-left: 20px;">
			<input id="btn1" type="button" value="上传" style="width: 100%; height: 40px;" onclick="chosePic(1)" />
		</div>
		<div style="width: 100px; float: left;">
			<input id="btn1" type="button" value="绘制" style="width: 100%; height: 40px;"  onclick="chosePic(2)" />
		</div>
	</div>

</body>

</html>
