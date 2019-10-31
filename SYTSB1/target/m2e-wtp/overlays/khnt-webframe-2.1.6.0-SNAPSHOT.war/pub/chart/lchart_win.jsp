<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<!-- 每个页面引入，页面编码、引入js，页面标题 -->
<%@ include file="/k/kui-base-form.jsp"%>
<%@ include file="/k/kui-base-chart.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script language="javascript">
function chartLoad()
{
	var chartid = '${param.c}';
	$("body").append("<div id='"+chartid+"_div' style='width:100%; height:100%; border:hidden;' align='center'><br><br><font color='#999999'>正在加载图形数据,请稍候...</font></div>");	
	RenderChart(chartid,chartid+"_div",JSON.stringify(api.data.param));
}
</script>
</head>

<body onload="chartLoad()">
</body>
</html>