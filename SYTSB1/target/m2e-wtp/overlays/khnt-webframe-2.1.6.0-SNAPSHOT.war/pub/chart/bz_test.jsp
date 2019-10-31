<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="detail">
<title></title>
<!-- 每个页面引入，页面编码、引入js，页面标题 -->
<%@include file="/k/kui-base-form.jsp"%>
<link rel="stylesheet" type="text/css" href="pub/chart/css/chart.css" />
<script lanague="javascript" src="pub/chart/ECharts/esl.js" > </script>;
<script type="text/javascript">
function render(){
	
}

</script>
</head>
<body class="chart_global" style="margin-top:30px" onload="render()">
	<div id="div1" style="height:400px;width:100%; border:hidden;" align="center"><br><br><font color="#999999">正在加载图形数据,请稍候...</font></div>
</body>
</html>