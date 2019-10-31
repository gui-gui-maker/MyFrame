document.write(" <scr"+"ipt lanague=\"javascript\" src=\"pub\/chart\/FusionCharts\/Charts\/FusionCharts.js\" > <\/scri"+"pt>");
document.write(" <scr"+"ipt lanague=\"javascript\" src=\"pub\/chart\/ECharts\/esl.js\" > <\/scri"+"pt>");
/**
 * 与后台方法通讯，获取图表XML
 * @param chartid 在配置中定义的图表ID
 * @param dataParam 图表参数
 * @param target 用于展现图表的DIV的id
 * @param chartswfid 图表的ID（同一页面上若有多个图表的，必须保证此ID唯一）
 * @return
 * @throws Exception
 */
function RenderChart(chartid,dataParam,sql,target,chartswfid)
{
	$.post("chart/chartinfo/getChartXml.do",{chartNum:chartid,dataParam:dataParam,sql:sql}, function(res) {
		if (res.success) {
			//var chart = new FusionCharts("pub/chart/FusionCharts/Charts/"+res.data.chartType.swfName, chartswfid, "100%", "100%", "0", "1");
			//chart.setDataXML(res.xml);
			//chart.render(target);
			var type = res.data.chartType.swfName;
			if(type.indexOf(".")!=-1){
				type = type.substring(0,type.indexOf("."));
				var myChart = new FusionCharts({
				      "type": type,
				      "renderAt": target,
				      "width": "100%",
				      "height": "100%",
				      "dataFormat": "xml",
				      "dataSource": res.xml
				});
				myChart.render();
			}else{
				var map = false;
				 // 路径配置
				if(type=='map'){
					map = true;
				}
				var fileLocation = map ? 'pub/chart/ECharts/echarts-map' : 'pub/chart/ECharts/echarts';
			    require.config({
			        paths:{ 
			            'echarts': fileLocation,
			            'echarts/chart/line': fileLocation,
			            'echarts/chart/bar': fileLocation,
			            'echarts/chart/scatter': fileLocation,
			            'echarts/chart/k': fileLocation,
			            'echarts/chart/pie': fileLocation,
			            'echarts/chart/radar': fileLocation,
			            'echarts/chart/map': fileLocation,
			            'echarts/chart/chord': fileLocation,
			            'echarts/chart/force': fileLocation,
			            'echarts/chart/gauge': fileLocation,
			            'echarts/chart/funnel': fileLocation
			        }
			    });
		        // 使用
		        require(
		            [
		                'echarts',
		                'echarts/chart/line',
		                'echarts/chart/bar',
		                'echarts/chart/scatter',
		                'echarts/chart/k',
		                'echarts/chart/pie',
		                'echarts/chart/radar',
		                'echarts/chart/force',
		                'echarts/chart/chord',
		                'echarts/chart/gauge',
		                'echarts/chart/funnel',
		                 map ? 'echarts/chart/map' : 'echarts'
		            ],
		            function (ec) {
		                // 基于准备好的dom，初始化echarts图表
		                var myChart = ec.init(document.getElementById(target));
		                eval(res.event);
		                var option = $.parseJSON(res.xml);
		                myChart.showLoading({
		                    text: '正在努力的读取数据中...'   //loading话术
		                });
		                myChart.setOption(option); 
		                myChart.hideLoading();
		            }
		        );
			}
		}
		else
		{
			alert("数据处理错误");
		}
	});
}
function testEvent(param){
	var selected = param.selected;
	for(var i in selected){
		if (selected[i]) {
           alert(i)
        }
	}
	//alert(option)
	//option.series[0].mapType = '四川';
   // option.title.subtext = mt + ' （滚轮或点击切换）';
   // myChart.setOption(option, true);
}
function openurl(url){
	top.$.dialog({width:600,height:400,lock:true,parent:api,data:{window:window},content:'url:'+url}).max()
}
