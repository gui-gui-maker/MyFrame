<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="detail">
<title></title>
<!-- 每个页面引入，页面编码、引入js，页面标题 -->
<%@include file="/k/kui-base-form.jsp"%>
<%@ taglib uri="http://khnt.com/tags/chart" prefix="chart" %>
<script type="text/javascript">
</script>
</head>
<body>
	<div id="tbyl" style="height:100%">
		<font color="#999999">正在加载图形数据,请稍候...</font>
       	<chart:chart chartNum="${param.chartNum }" renderAt="tbyl" width="100%" height="100%"/>
	</div>
</body>
</html>