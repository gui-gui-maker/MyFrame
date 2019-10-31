//获取部门收入数据
function getBmsr(){
    var unit ="";
   var dept = "";
    $.post("feeStatisticsAction/statisticsIncome.do",
    		{"startDate":startDate,"endDate":endDate,"unit":unit,'dept':dept}, 
    		function(res){
    			rows = []; 
	            clsses = [];
	            depts = [];
    			//接收数据
	            var data = res.rows;
	            clsses = res.clsses;
	            depts = res.depts;
	           /*  if(isInitSelect){
		            initSelect(depts);
	            } */
	            //组装数据
	            for(var i=0;i<depts.length;i++){
	            	var row = {};
	            	row['dept'] = depts[i];
	            	row['total'] = 0;
	            	for(var j=0;j<clsses.length;j++){
	            		row[clsses[j]] = 0;
	            		for(var k=0;k<data.length;k++){
	            			if(data[k].DEPT == depts[i] && data[k].CLSS==clsses[j]){
                      			row[clsses[j]] = data[k].MONEY;
	                      		row['total'] += data[k].MONEY;
	                      	}	
	            		}
	                }
	            	rows.push(row);
	            }
	            
	            drawBmsrTj(clsses,rows,2);
	            
	            //loadGrid(rows,clsses,2);
	           // drawChart(clsses,rows);
    });
}
//部门收入
function drawBmsrTj(clsses,rows,fix){
		$("#bmsrtj").find("div").remove();
		var div = document.createElement('div');
		$("#bmsrtj").append(div);
		$(div).css({width:'100%',height:'90%'});
		barChart = echarts.init(div);
		if(fix == 4){
			rows = tenThousand(rows);
		}
		var depts = [];
		var ss = [];
		for(var i=0;i<clsses.length;i++){
			var s = {};
			s.name = clsses[i];
		s.type = 'bar';
		s.stack = 'income';
		s.yAxisIndex = 0;
		/*	if('一般'==clsses[i]){
 			//s.yAxisIndex = 0;
 			s.color='red'
			}else{
				//s.yAxisIndex = 1;
				s.color='green'
				
			}*/
			s.data = [];
			for(var j=0;j<rows.length;j++){
				if(depts.length<rows.length){
 				depts.push(rows[j]['dept']);
				}
				s.data.push(rows[j][clsses[i]]?rows[j][clsses[i]]:0);
			}
			ss.push(s);
		}
		//合计
		/* var tt = [];
		for(var i=0;i<rows.length;i++){
			tt.push(rows[i]['total']);
	}
		clsses[clsses.length]="合计";
		ss.push({
			name : '合计',
 		type :'bar',
		yAxisIndex : 2,
		data : tt
		});  */
		var option = {
				tooltip : {
			        trigger: 'axis',
			        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
			            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
			        },
			       formatter: function (params,ticket,callback) {
		                var res = params[0].name + '<br>';
		                var tvalue = 0;
		                 for(var i=0;i<params.length;i++){
		                	if(params[i].value == 0){
		                		continue;
		                	}else{
		                		res += params[i].seriesName + ':' + params[i].value +'<br>';
		                		tvalue += parseFloat(params[i].value);
		                	}
		                 }
		                res += "合计" + ':' + tvalue +'<br>';
	 		             return res;  
		             }  
			    },
			   toolbox: {
			        show : true,
			        feature : {
			            mark : {show: false},
			            dataView : {show: false, readOnly: false},
			            magicType : {
			                show: false,
			                type: ['pie', 'funnel']
			            },
			            restore : {show: false},
			            saveAsImage : {show: true}
			        }
			    },
			    legend: {
		        left: '40%',
		        color:['#80FF80','#FF8096','#800080'],
		        textStyle: {
		            color: '#90979c',
		        },
		        data: clsses
		    },
			    grid: {
			        left: '6%',
			        right: '2%',
			        bottom: '5%',
			        containLabel: true
			    },
			    dataZoom: [//给x轴设置滚动条
			               {
			                start:20,//默认为0
			                   end: 90,//默认为100
			                   type: 'slider',
			                   show: true,
			                   xAxisIndex: [0],
			                   handleSize: 0,//滑动条的 左右2个滑动条的大小
			                   height: 5,//组件高度
			                   left: 92, //左边的距离
			                   right: 40,//右边的距离
			                   bottom: 26,//右边的距离
			                   handleColor: '#ddd',//h滑动图标的颜色
			                   handleStyle: {
			                       borderColor: "#cacaca",
			                       borderWidth: "1",
			                       shadowBlur: 2,
			                       background: "#ddd",
			                       shadowColor: "#ddd",
			                   },
			                   fillerColor: new echarts.graphic.LinearGradient(1, 0, 0, 0, [{
			                       //给颜色设置渐变色 前面4个参数，给第一个设置1，第四个设置0 ，就是水平渐变
			                       //给第一个设置0，第四个设置1，就是垂直渐变
			                       offset: 0,
			                       color: '#1eb5e5'
			                   }, {
			                       offset: 1,
			                       color: '#5ccbb1'
			                   }]),
			                   backgroundColor: '#ddd',//两边未选中的滑动条区域的颜色
			                   showDataShadow: false,//是否显示数据阴影 默认auto
			                   showDetail: false,//即拖拽时候是否显示详细数值信息 默认true
			                   handleIcon: 'M-292,322.2c-3.2,0-6.4-0.6-9.3-1.9c-2.9-1.2-5.4-2.9-7.6-5.1s-3.9-4.8-5.1-7.6c-1.3-3-1.9-6.1-1.9-9.3c0-3.2,0.6-6.4,1.9-9.3c1.2-2.9,2.9-5.4,5.1-7.6s4.8-3.9,7.6-5.1c3-1.3,6.1-1.9,9.3-1.9c3.2,0,6.4,0.6,9.3,1.9c2.9,1.2,5.4,2.9,7.6,5.1s3.9,4.8,5.1,7.6c1.3,3,1.9,6.1,1.9,9.3c0,3.2-0.6,6.4-1.9,9.3c-1.2,2.9-2.9,5.4-5.1,7.6s-4.8,3.9-7.6,5.1C-285.6,321.5-288.8,322.2-292,322.2z',
			                   filterMode: 'filter',
			               },
			               //下面这个属性是里面拖到
			               {
			                   type: 'inside',
			                   show: true,
			                   xAxisIndex: [0],
			                   start: 54,//默认为1
			                   end: 100,//默认为100
			               },
			           ],
			   "xAxis": [
    	        {
    	            "type": "category", 
    	            "splitLine": {
    	                "show": false
    	            }, 
    	            "axisTick": {
    	                "show": false
    	            }, 
    	            "splitArea": {
    	                "show": false
    	            }, 
    	            "axisLabel": {
    	                "interval": 0, 
    	                "rotate": 45, 
    	                "show": true, 
    	                "splitNumber": 15, 
    	                "textStyle": {
    	                    "fontFamily": "微软雅黑", 
    	                    "fontSize": 12
    	                }
    	            }, 
    	            "data": depts,
    	            axisLine: {
    	                lineStyle: {
    	                	 color: "#5793f3"
    	                }
    	            }
    	        }
    	    ], 
    	    "yAxis": [
	    	        {
	    	            "type": "value", 
	    	            "splitLine": {
	    	                "show": false
	    	            }, 
	    	            "axisLine": {
	    	                "show": true
	    	            }, 
	    	            "axisTick": {
	    	                "show": false
	    	            }, 
	    	            "splitArea": {
	    	                "show": false
	    	            },
	    	            axisLine: {
	    	                lineStyle: {
	    	                    color: "#5793f3"
	    	                }
	    	            }
	    	        }
	    	    ], 
			   /*  yAxis : [
	 			        {
	 			            type : 'value',
	 			            name: '一般',
	 			            position: 'left',
	 			            offset: 80
	 			        }  ,
	 			        {
	 			            type : 'value',
	 			            name: '税票',
	 			            position: 'left'
	 			        },
	 			      {
	 			            type: 'value',
	 			            name: '合计',
	 			            position: 'right',
	 			            offset: 80
	 			        } 
	 			    ], */
			 /*   dataZoom: [
			             {
			                 show: true,
			                 start: 0,
			                 end: rows.length>8?60:100,
			                 top: '95%'
			             },
			             {
			                 type: 'inside',
			                 start: 0,
			                 end: rows.length>8?60:100,
			             }
			         ], */
			    series : ss
		    };

		barChart.setOption(option);
	}