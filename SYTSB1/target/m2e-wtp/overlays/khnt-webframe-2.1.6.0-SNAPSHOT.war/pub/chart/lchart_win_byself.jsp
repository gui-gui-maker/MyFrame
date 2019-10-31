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
	var chartid = '${param.c}';
	var params = '${param.p}';
	var olddata = "";
	$(function(){
		//按钮工具栏
		var toolbarManager = $("#toptoolbar").ligerToolBar(
			{
				items: [ 
					{text: '刷新',id: 'refresh',click: refresh,icon: 'refresh'},
					"-", 
					{text: '还原',id: 'reload',click: reload,icon: "return"}
				]
			} 
		);
		//页面布局
		$("#layout").ligerLayout({
			leftWidth: 400,
			topHeight: 30,
			space: 3,
			allowTopResize: false,
			allowLeftResize: true
		});
		chartLoad();
	})
	function chartLoad()
	{
		$("#chartDiv").append("<div id='"+chartid+"_div' style='float:right;width:100%; height:100%; border:hidden;' align='center'><br><br><font color='#999999'>正在加载图形数据,请稍候...</font></div>");	
		RenderChart(chartid,chartid+"_div",params,"",function(data){
			$("#resourceCode").text(data)
			olddata = data;
		});
	}
	function refresh(){
		var newxml = $("#resourceCode").val();
		RefreshChartByXml(chartid,newxml);
	}
	function reload(){
		RefreshChartByXml(chartid,olddata);
		$("#resourceCode").val(olddata);
	}
</script>
</head>

<body class="p5">
	<div id="layout">
		<div position="top" id="toptoolbar"></div>
		<div position="left" title="图表数据">
			<textarea id="resourceCode" style="width:100%;height:100%;"></textarea>
		</div>
		<div position="center" id="chartDiv"></div>
	</div>
</body>
</html>