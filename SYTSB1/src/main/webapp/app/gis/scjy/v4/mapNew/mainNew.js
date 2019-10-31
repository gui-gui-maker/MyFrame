$(function(){
madeMaxPopUpBox("ztclChart");
openMaxPopUpBox("ztclChart");
	var months = ['1','2','3'];
	var counts = ['1','2','3'];
	var title=""; //数据钻取标题
	 option = {
			 color: ['#3398DB'],
			 title: {
			        text: title,
			        subtext: '按月份进行统计，统计该年每月的特种设备检验情况',
			        left : 'center',
			        top : '10',
			        textStyle : {
			        	color : '#FFFFFF'
			        }
			    },
		  tooltip: {
		        trigger: 'axis',
		        formatter: '{b} 月<br /><span style="display:inline-block;margin-right:5px;border-radius:10px;width:10px;height:10px;background-color:#3398DB"></span>{c} 次'
		    },
		/*legend: {
		 data:['销量']
		 },*/
		grid: {
			 left: '10%',
	        right: '10%',
	        bottom: '3%',
	        top: '20%',
	        containLabel: true
		},
		xAxis: {
			data: months,
			name:'单位（月）',
			axisLine:{
       		lineStyle:{
               color:'#FFFFFF',
       		}
			}
		},
		yAxis: {
		/*	splitLine : {show: false},*/
			  name: '使用单位数量',
			axisLine:{
       		lineStyle:{
               color:'#FFFFFF',
       		}
			}
		},
		textStyle:{color:"#fff"},
		series: [{
			name: '监督抽查',

			type: 'bar',
			barWidth: '60%',
			data: counts
		}]
	};
	 var ztclChart = echarts.init(document.getElementById('ztclChart'));
	 ztclChart.setOption(option);  
});