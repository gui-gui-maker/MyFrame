<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Enumeration en = request.getParameterNames();
	String dataParam = "";
	while(en.hasMoreElements()){
		Object o = en.nextElement();
		String param = o==null?"":o.toString();
		if(!("_".equals(param)||"chartNum".equals(param))){
			dataParam += request.getParameter(param)+",";
		}
	}
	if(!"".equals(dataParam)){
		dataParam = dataParam.substring(0,dataParam.length()-1);
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ include file="/k/kui-base-form.jsp"%>
<script src="pub/chart/js/chart_show.js"></script>
<style type="text/css">
body {
	text-align: center;
}
</style>
<script type="text/javascript">
$(function(){
	var chartNum = "${param.chartNum}";
	RenderChart(chartNum,"<%=dataParam%>","","chart","ylChartId"+Math.random(100));
})
</script>
</head>
<body>
	<div id="chart">
	</div>
</body>
</html>
