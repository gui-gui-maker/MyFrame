<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8"/>
	<%@include file="/k/kui-base.jsp" %>
<script type="text/javascript">
function downloadLodop(fileName){
	window.open("<%=request.getContextPath().equals("/")?"":(request.getContextPath()+"/")%>pub/tools/dowload_soft.jsp?fpath=" + fileName);
}
$(function(){
	$("body").html(api.data.content);
});
</script>
<style type="text/css">
	h3{font-size: 18px;font-family: '微软雅黑','黑体';}
	p{font-size:14px;font-family: '微软雅黑','宋体';}
	p a{color:red;font-size:18px;font-weight:bold;}
</style>
</head>
<body>
</body>
</html>