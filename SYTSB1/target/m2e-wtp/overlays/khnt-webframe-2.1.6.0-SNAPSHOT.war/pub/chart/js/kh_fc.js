document.write(" <scr"+"ipt lanague=\"javascript\" src=\"pub\/chart\/FusionCharts\/Charts\/FusionCharts.js\" > <\/scri"+"pt>");
/**
 * 与后台方法通讯，获取图表XML
 * @param chartid 在配置中定义的图表ID
 * @param sql 自定义取数SQL语句，若不为空，覆盖配置中定义的取数SQL语句
 * @param target 用于展现图表的DIV的id
 * @param chartswfid 图表的ID（同一页面上若有多个图表的，必须保证此ID唯一）
 * @return
 * @throws Exception
 */
function RenderChart(chartid,sql,target,chartswfid)
{
	$.post("chart/getChartXml.do",{chartid:chartid,sql:sql}, function(res) {
		if (res.success) {
			var chart = new FusionCharts("pub/chart/FusionCharts/Charts/"+res.type+".swf", chartswfid, "100%", "100%", "0", "1");
			chart.setDataXML(res.xml);
			chart.render(target);
		}
		else
		{
			alert("数据处理错误");
		}
	});
}