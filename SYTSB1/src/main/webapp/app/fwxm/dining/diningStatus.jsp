<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
    <title></title>
    <%@include file="/k/kui-base-form.jsp" %>
<script type="text/javascript" src="app/fwxm/dining/js-chart/highcharts.js"></script>
</head>
<body>
<div id="container" style="width: 550px; height: 400px; margin: 0 auto"></div>
<script language="JavaScript">

$.post("dining/foodOrder/typeOrder.do",function(resp){
	if(!resp.success){
		$("#container").append("当前食堂未发布！");
		return;
	}
	var data = resp.data;
	var meal = resp.dataMeal;
	var usedate = resp.dataDate;
	var seriesName = (meal==0?'早餐':meal==1?'午餐':'晚餐')+' '+ usedate.substr(0,10);
	hchart(data,seriesName);
}); 
function hchart(data,seriesName) { 
	var chartCate = [];
	var chartData = [];
for(var i=0;i<data.length;i++){
	if(data[i][0]==0){
		chartCate.push('预定');
		chartData.push(data[i][1]);
	}else if(data[i][0]==1){
		chartCate.push('备餐');
		chartData.push(data[i][1]);
	}else if(data[i][0]==2){
		chartCate.push('就餐');
		chartData.push(data[i][1]);
	}
}
   var chart = {
      type: 'bar'
   };
   var title = {
      text: '当前食堂状态'   
   };
   var subtitle = {
      text: ''  
   };
   var xAxis = {
      categories: chartCate,
      title: {
         text: null
      }
   };
   var yAxis = {
      min: 0,
      title: {
         text: 'Population (份)',
         align: 'high'
      },
      labels: {
         overflow: 'justify'
      }
   };
   var tooltip = {
      valueSuffix: ' 份'
   };
   var plotOptions = {
      bar: {
         dataLabels: {
            enabled: true
         }
      }
   };
   var legend = {
      layout: 'vertical',
      align: 'right',
      verticalAlign: 'top',
      x: -40,
      y: 100,
      floating: true,
      borderWidth: 1,
      backgroundColor: ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
      shadow: true
   };
   var credits = {
      enabled: false
   };
   
   var series= [{
            name: seriesName,
            data: chartData     
	    }
   ];     
      
   var json = {};   
   json.chart = chart; 
   json.title = title;   
   json.subtitle = subtitle; 
   json.tooltip = tooltip;
   json.xAxis = xAxis;
   json.yAxis = yAxis;  
   json.series = series;
   json.plotOptions = plotOptions;
   json.legend = legend;
   json.credits = credits;
   $('#container').highcharts(json);
  
}
</script>
</body>
</html>