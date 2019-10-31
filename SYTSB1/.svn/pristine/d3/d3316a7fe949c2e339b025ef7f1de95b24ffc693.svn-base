var bigInit = "chart1";
// 部门收入统计
var chart1 = {};
var rows1 = [];
var clsses1 = [];
var depts1 = [];
function getRows1() {
	var startDate = $("#startDate").datebox('getValue');
	var endDate = $("#endDate").datebox('getValue');
	var dept = $("#dept").val();
	$.post("feeStatisticsAction/statisticsIncome.do", {
		"startDate" : startDate,
		"endDate" : endDate,
		'dept' : dept
	},
			function(res) {
				rows1 = [];
				clsses1 = [];
				depts1 = [];
				// 接收数据
				var data = res.rows;
				clsses1 = res.clsses;
				depts1 = res.depts;
				// 组装数据
				for (var i = 0; i < depts1.length; i++) {
					var row = {};
					row['dept'] = depts1[i];
					row['total'] = 0;
					for (var j = 0; j < clsses1.length; j++) {
						row[clsses1[j]] = 0;
						for (var k = 0; k < data.length; k++) {
							if (data[k].DEPT == depts1[i]
									&& data[k].CLSS == clsses1[j]) {
								row[clsses1[j]] = data[k].MONEY;
								row['total'] += data[k].MONEY;
							}
						}
					}
					rows1.push(row);
				}
				drawChart1(document.getElementById('chart1'), clsses1,
						rows1, 2, "s");

				// 初始化时的大图为第一幅
				if(bigInit == 'chart1'){
					$(".m-a-boxes-big .m-a-boxes-tit").html("部门收入统计");
					drawChart1(document.getElementById('bigChart'), clsses1, rows1,2, "l");
				}
				
			});
}
/**
 * 
 * @param div
 *            要加载的窗口
 * @param clsses
 * @param rows
 * @param fix
 *            根据单位换算小数
 * @param size
 *            大图or小图
 */
function drawChart1(container, clsses, rows, fix, size) {
	$(container).find("div").remove();
	var div = document.createElement('div');
	$(container).append(div);
	$(div).css({
		width : '100%',
		height : '100%'
	});
	if (fix == 4) {
		rows = tenThousand(rows);
	}
	chart1 = echarts.init(div);
	var depts = [];
	var ss = [];
	for (var i = 0; i < clsses.length; i++) {
		var s = {};
		s.name = clsses[i];
		s.type = 'bar';
		s.stack = 'income';
		s.yAxisIndex = 0;
		s.data = [];
		for (var j = 0; j < rows.length; j++) {
			if (depts.length < rows.length) {
				depts.push(rows[j]['dept']);
			}
			s.data.push(rows[j][clsses[i]] ? rows[j][clsses[i]] : 0);
		}
		ss.push(s);
	}
	var option = {
		tooltip : {
			trigger : 'axis',
			axisPointer : { // 坐标轴指示器，坐标轴触发有效
				type : 'shadow' // 默认为直线，可选为：'line' | 'shadow'
			},
			formatter : function(params, ticket, callback) {
				var res = params[0].axisValue + '<br>';
				var tvalue = 0;
				for (var i = 0; i < params.length; i++) {
					if (params[i].value == 0) {
						continue;
					} else {
						res +=  params[i].marker + params[i].seriesName
								+ ':' + params[i].value + '<br>';
						tvalue += parseFloat(params[i].value);
					}
				}
				res += "合计" + ':' + tvalue + '<br>';
				return res;
			}
		},
		legend : {
			color : [ '#80FF80', '#FF8096', '#800080' ],
			left : '40%',
			data : clsses,
			textStyle : {
				color : '#fff'
			}
		},
		textStyle : {
			color : '#fff'
		},
		grid : {
			left : '12%',
			right : '2%',
			bottom : '18%'/*,
			containLabel : true*/
		},

		"xAxis" : [ {
			"type" : "category",
			"splitLine" : {
				"show" : false
			},
			"axisTick" : {
				"show" : false
			},
			"splitArea" : {
				"show" : false
			},
			"axisLabel" : {
				"interval" : 0,
				"rotate" : 45,
				"show" : true,
				"splitNumber" : 15,
				"textStyle" : {
					"fontFamily" : "微软雅黑",
					"fontSize" : 12
				}
			},
			"data" : depts
		} ],
		"yAxis" : [ {
			"type" : "value",
			"splitLine" : {
				"show" : false
			},
			"axisLine" : {
				"show" : true
			},
			"axisTick" : {
				"show" : false
			},
			"splitArea" : {
				"show" : false
			}
		} ],
		series : ss
	};
	if (size == 'l') {
		option.dataZoom = [ {
             show: true,
              start: 0,
              end: rows.length>8?60:100,
              top: '95%'
          },
          {
              type: 'inside',
              start: 0,
              end: rows.length>8?60:100,
          }];
	}
	chart1.setOption(option);
	chart1.on('click', function(param) {
		// 画出饼图
		for (var i = 0; i < rows.length; i++) {
			if (rows[i].dept == param.name) {
				drawPie("chart1s",rows[i]);
				break;
			}
		}
	});
}

// 部门费用统计
var rows2 = [];
var clsses2 = [];
var depts2 = [];
// 获取数据
function getRows2() {
	var startDate = $("#startDate").datebox('getValue');
	var endDate = $("#endDate").datebox('getValue');
	var unit = "特检院";
	var dept = $("#dept").val();
	$.post("feeStatisticsAction/statisticsFee.do", {
		"start" : startDate,
		"end" : endDate,
		"unit" : unit,
		'dept' : dept
	},
			function(res) {
				rows2 = [];
				clsses2 = [];
				depts2 = [];
				// 接收数据
				var data = res.rows;
				clsses2 = res.clsses;
				depts2 = res.depts;
				// 组装数据
				for (var i = 0; i < depts2.length; i++) {
					var row = {};
					row['dept'] = depts2[i];
					row['total'] = 0;
					for (var j = 0; j < clsses2.length; j++) {
						row[clsses2[j]] = 0;
						for (var k = 0; k < data.length; k++) {
							if (data[k].DEPT == depts2[i]
									&& data[k].CLSS == clsses2[j]) {
								row[clsses2[j]] = data[k].MONEY;
								row['total'] += data[k].MONEY;
							}
						}
					}
					rows2.push(row);
				}
				drawChart2(document.getElementById("chart2"), clsses2, rows2,2, "s");
				// 初始化时的大图为第一幅
				if(bigInit == 'chart2'){
					$(".m-a-boxes-big .m-a-boxes-tit").html("部门支出统计");
					drawChart2(document.getElementById("bigChart"), clsses2, rows2,2, "l");
				}
			});
}

var chart2 = null;
function drawChart2(container, clsses, rows, fix, size) {
	$(container).find("div").remove();
	var div = document.createElement('div');
	$(container).append(div);
	$(div).css({
		width : '100%',
		height : '100%'
	});
	if (fix == 4) {
		rows = tenThousand(rows);
	}
	chart2 = echarts.init(div);
	var depts = [];
	var ss = [];
	var colors = [ '#0000FF', '#3D59AB', '#1E90FF', '#0B1746', '#03A89E',
			'#191970', '#33A1C9', '#8A2BE2', '#DA70D6', '#F0E68C', '#BC8F8F',
			'#C76114', '#734A12', '#5E2612', '#2E8B57', '#00FF7F', '#40E0D0',
			'#6A5ACD', '#4682B4', '#1E90FF', '#483D8B', '#CD5555', '#FF1493',
			'#8B8989', '#CD6889', '#CDB5CD' ]
	for (var i = 0; i < clsses.length; i++) {
		var s = {};
		s.name = clsses[i];
		s.type = 'bar';
		s.stack = 'fee';
		s.yAxisIndex = 0;
		s.barMaxWidth = 50;
		s.barGap = '10%';

		if (clsses[i] == "差旅费") {
			s.color = "#FF0000";
		} else {
			s.color = colors[i]
		}
		s.itemStyle = {
			"normal" : {
				"barBorderRadius" : 0,
				/* 是否显示数值 */
				"label" : {
					"show" : false,
					"textStyle" : {
						"color" : "rgba(0,0,0,1)"
					},
					"position" : "insideTop",
					formatter : function(p) {
						return p.value > 0 ? (p.value) : '';
					}
				}
			}
		}, s.data = [];
		for (var j = 0; j < rows.length; j++) {
			if (depts.length < rows.length) {
				depts.push(rows[j]['dept']);
			}
			s.data.push(rows[j][clsses[i]] ? rows[j][clsses[i]] : 0);
		}
		ss.push(s);
	}
	option = {
		"tooltip" : {
			trigger : 'axis',
			axisPointer : { // 坐标轴指示器，坐标轴触发有效
				type : 'shadow' // 默认为直线，可选为：'line' | 'shadow'
			},
			formatter : function(params, ticket, callback) {
				var res = params[0].axisValue + '<br>';
				var tvalue = 0;
				for (var i = 0; i < params.length; i++) {
					if (params[i].value == 0) {
						continue;
					} else {
						res += params[i].marker + params[i].seriesName + ':'
								+ params[i].value + '<br>';
						tvalue += parseFloat(params[i].value);
					}
				}
				res += "合计" + ':' + tvalue + '<br>';
				return res;
			}
		},
		grid : {

			left : 70,
			top : 30
		// containLabel : true
		},
		legend : {
			type : 'scroll',
			top : 5,
			data : clsses,
			textStyle : {
				color : '#fff'
			}
		},
		textStyle : {
			color : '#fff'
		},
		"calculable" : true,
		"xAxis" : [ {
			"type" : "category",
			"splitLine" : {
				"show" : false
			},
			"axisTick" : {
				"show" : false
			},
			"splitArea" : {
				"show" : false
			},
			"axisLabel" : {
				"interval" : (size == "l" ? 0 : 1),
				formatter : function(value) {
					return value.split("").join("\n");
				}

			},
			"data" : depts,
			axisLine : {
				lineStyle : {
					color : "#5793f3"
				}
			}
		} ],
		"yAxis" : [ {
			"type" : "value",
			"splitLine" : {
				"show" : false
			},
			"axisLine" : {
				"show" : true
			},
			"axisTick" : {
				"show" : false
			},
			"splitArea" : {
				"show" : false
			},
			axisLine : {
				lineStyle : {
					color : "#5793f3"
				}
			}
		} ],

		"series" : ss
	}
	if (size == 'l') {
		option.dataZoom = [ {
			"show" : true,
			"height" : 30,
			"xAxisIndex" : [ 0 ],
			bottom : 10,
			"start" : 0,
			"end" : 80
		}, {
			"type" : "inside",
			"show" : true,
			"height" : 15,
			"xAxisIndex" : [ 0 ],
			"start" : 1,
			"end" : 35
		} ]
	}
	chart2.setOption(option);
	chart2.on('click', function(param) {
		// 画出饼图
		for (var i = 0; i < rows.length; i++) {
			if (rows[i].dept == param.name) {
				drawPie2("chart2s",rows[i]);
				break;
			}
		}

	});
}

// 收支对比
var chart3 = null;
var rows = [];
var clsses = [];
var depts = [];
var incomeClasses = [];
var costClasses = [];
function getRows3() {
	var startDate = $("#startDate").datebox('getValue');
	var endDate = $("#endDate").datebox('getValue');
	var dept = $("#dept").val();
	$.post("feeStatisticsAction/compareFee.do", {
		"startDate" : startDate,
		"endDate" : endDate,
		"dept" : dept
	},
			function(res) {
				if (res.success) {
					rows = [];
					clsses = [];
					depts = [];
					incomeClasses = [];
					costClasses = [];
					// 接收数据
					var rows_in = res.rows_in;
					var rows_out = res.rows_out;
					data = rows_in.concat(rows_out);
					incomeClasses = res.clsses_in;
					costClasses = res.clsses_out;
					clsses = clsses.concat(costClasses, incomeClasses);
					depts = res.depts;
					// 组装数据
					for (var i = 0; i < depts.length; i++) {
						var row = {};
						row['dept'] = depts[i];
						row['cost_total'] = 0;
						row['income_total'] = 0;
						for (var j = 0; j < clsses.length; j++) {
							row[clsses[j]] = 0;
							for (var k = 0; k < data.length; k++) {
								if (data[k].DEPT == depts[i]
										&& data[k].CLSS == clsses[j]) {
									var mny = parseFloat(parseFloat(
											data[k].MONEY).toFixed(2));
									row[clsses[j]] = mny;
									if (data[k].CLSS == '一般'
											|| data[k].CLSS == '税票') {
										row['income_total'] += mny;
									} else {
										row['cost_total'] += mny;
									}
								}
							}
						}
						row['cost_total'] = parseFloat(row['cost_total']
								.toFixed(2));
						row['income_total'] = parseFloat(row['income_total']
								.toFixed(2));
						rows.push(row);
					}

					drawChart3(document.getElementById('chart3'), rows, 2);
					// 初始化时的大图为第一幅
					if(bigInit == 'chart3'){
						$(".m-a-boxes-big .m-a-boxes-tit").html("部门收支对比统计");
						drawChart3(document.getElementById('bigChart'), rows, 2);
					}
				} else {
					alert(res.msg);
				}
			});
}
function drawChart3(container, rows, fix) {
	if (fix == 4) {
		rows = tenThousand(rows);
	}
	$(container).find("div").remove();
	var div = document.createElement('div');
	$(container).append(div);
	$(div).css({
		width : '100%',
		height : '100%'
	});
	chart3 = echarts.init(div);
	var dpt = [];
	var cst = [];
	var inc = [];
	var pft = [];
	for (var i = 0; i < rows.length; i++) {
		dpt.push(rows[i].dept);
		cst.push(rows[i].cost_total);
		inc.push(rows[i].income_total);
		pft.push(rows[i].income_total - rows[i].cost_total);
	}
	var option = {
		tooltip : {
			trigger : 'axis',
			axisPointer : { // 坐标轴指示器，坐标轴触发有效
				type : 'shadow' // 默认为直线，可选为：'line' | 'shadow'
			}
		},
		legend : {
			data : [ '收入' , '支出'],
			textStyle : {
				color : '#fff'
			}
		},
		grid : {
			left : '100',
			right : '30',
			bottom : '20'/*
							 * , containLabel : true
							 */
		},
		textStyle : {
			color : '#fff'
		},
		xAxis : [ {
			type : 'value'
		} ],
		yAxis : [ {
			type : 'category',
			axisTick : {
				show : false
			},
			data : dpt
		} ],
		dataZoom : [ {
			show : true,
			yAxisIndex : 0,
			filterMode : 'empty',
			width : 24,
			height : '90%',
			right : 3,
			bottom : 20,
			start : 0,
			end : (rows.length < 10 ? 100 : rows.length > 10
					&& rows.length < 30 ? 40 : 20)
		} ],
		series : [ /*{
			name : '利润',
			type : 'bar',
			label : {
				normal : {
					show : true,
					position : 'inside'
				}
			},
			data : pft
		},*/{
			name : '收入',
			type : 'bar',
			stack : '1',
			label : {
				normal : {
					show : true,
					position : 'right'
				}
			},
			itemStyle: {   
                //通常情况下：
                normal:{  
                    color: function (params){
                        return 'green';
                    }
                }
            },
			data : inc
		}, {
			name : '支出',
			type : 'bar',
			stack : '2',
			label : {
				normal : {
					show : true,
					position : 'right'
				}
			},
			itemStyle: {   
                //通常情况下：
                normal:{  
                    color: function (params){
                        return 'red';
                    }
                }
            },
			data : cst
		} ]
	};

	chart3.setOption(option);

}

// 部门工资占比图
var labels = [];
var salary = [];
function getRows4() {
	var startDate = $("#startDate").datebox('getValue');
	var endDate = $("#endDate").datebox('getValue');
	var unit = "检验院";
	var dept = $("#dept").val();
	$.post("finance/feeSalaryAction/salaryFeeUnit.do", {
		"start" : startDate,
		"end" : endDate,
		'dept' : dept,
		'unit' : unit,
		'gzItem' : 'FSALARY',
		'types' : '应发合计'
	}, function(res) {
		// 接收数据
		var data = res.data;
		labels = [];
		salary = [];
		for (var i = 0; i < data.length; i++) {
			labels.push(data[i][0]);
			salary.push({
				name : data[i][0],
				value : data[i][1]
			});
		}
		drawChart4(document.getElementById("chart4"), labels, salary);
		// 初始化时的大图为第一幅
		if(bigInit == 'chart4'){
			$(".m-a-boxes-big .m-a-boxes-tit").html("部门总工资对比统计");
			drawChart4(document.getElementById("bigChart"), labels, salary);
		}
	});
}
var chart4 = null;
function drawChart4(container, clsses, rows, fix) {
	$(container).find("div").remove();
	var div = document.createElement('div');
	$(container).append(div);
	$(div).css({
		width : '100%',
		height : '100%'
	});
	if (fix == 4) {
		rows = tenThousand(rows);
	}
	chart4 = echarts.init(div);

	var option = {
		tooltip : {
			trigger : 'item',
			formatter : "{a} <br/>{b} : {c} ({d}%)"
		},
		legend : {
			orient : 'vertical',
			type : 'scroll',
			left : 3,
			// left: '40%',
			data : clsses,
			textStyle : {
				color : '#fff'
			}
		},
		series : [ {
			name : '总工资',
			type : 'pie',
			radius : '55%',
			center : [ '60%', '55%' ],
			data : rows,
			itemStyle : {
				emphasis : {
					shadowBlur : 10,
					shadowOffsetX : 0,
					shadowColor : 'rgba(0, 0, 0, 0.5)'
				}
			}
		} ]
	};
	chart4.setOption(option);
}
// 个人工资分布统计
var rows5 = [];
var yAxisCls = [];
var xAxisCls = [];
function getRows5() {
	var startDate = $("#startDate").datebox('getValue');
	var endDate = $("#endDate").datebox('getValue');
	$.post("finance/feeSalaryAction/statisticsSalary.do", {
		"start" : startDate,
		"end" : endDate,
		'dept' : '',
		'unit' : ''
	}, function(res) {
		rows5 = [];
		yAxisCls = [];
		xAxisCls = [];
		// 接收数据
		var data = res.data;
		var cls = res.yAxis;
		for(var l in cls){
			yAxisCls.push(cls[l].substring(0,7));
		}
		yAxisCls.sort(function(a,b){
			a = parseInt(a.replace("-",""));
			b = parseInt(b.replace("-",""));
			return a>b?1:-1;
		});
		xAxisCls = [ 1000,2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000,'>10k'];
		for(var i=0; i < xAxisCls.length; i++){
			for(var j=0; j < yAxisCls.length; j++){
				var item = [i,j];
				var c = 0;
				for(k in data){
					if(yAxisCls[j] == data[k][0].substring(0,7)){
						if(i==0&&parseFloat(data[k][2])<=1000){
							c++;
						}else if(i==10&&parseFloat(data[k][2])>10000){
							c++;
						}else if(parseFloat(data[k][2])>xAxisCls[i-1]&&parseFloat(data[k][2])<=xAxisCls[i]){
							c++;
						}
					}
				}
				item[2] = c;
				rows5.push(item);
			}
		}
		drawChart5(document.getElementById("chart5"),rows5,yAxisCls,xAxisCls);
		// 初始化时的大图为第一幅
		if(bigInit == 'chart5'){
			$(".m-a-boxes-big .m-a-boxes-tit").html("个人工资水平分布");
			drawChart5(document.getElementById("bigChart"),rows5,yAxisCls,xAxisCls);
		}
	});
}
var chart5 = null;
function drawChart5(container,data,yAxisCls,xAxisCls) {
	$(container).find("div").remove();
	var div = document.createElement('div');
	$(container).append(div);
	$(div).css({
		width : '100%',
		height : '100%'
	});
	chart5 = echarts.init(div);
	
	data = data.map(function(item) {
		return [ item[0], item[1], item[2] || '-' ];
	});
	option = {
		tooltip : {
			position : 'top',
			formatter : function(params, ticket, callback) {
				var res = params.marker + "年月："+yAxisCls[params.value[1]]  + '<br>';
				if(params.value[0]==0){
					res +=  params.marker + "工资：>"  + xAxisCls[params.value[0]] + '<br>';
				}else if(params.value[0]==10){
					res +=  params.marker + "工资："  + xAxisCls[params.value[0]] + '<br>';
				}else{
					res +=  params.marker + "工资："  + xAxisCls[params.value[0]-1]+"-"+ xAxisCls[params.value[0]] + '<br>';
				}
				res +=  params.marker + params.seriesName + '：' + params.value[2] + '<br>';
				return res;
			}
		},
		animation : false,
		grid : {
			height : '50%',
			y : '10%'
		},
		textStyle : {
			color : '#fff'
		},
		xAxis : {
			type : 'category',
			data : xAxisCls,
			splitArea : {
				show : true
			}
		},
		yAxis : {
			type : 'category',
			data : yAxisCls,
			splitArea : {
				show : true
			}
		},
		visualMap : {
			min : 0,
			max : 120,
			calculable : true,
			orient : 'horizontal',
			left : 'center',
			bottom : '5%',
			itemWidth:20,
			itemHeight:200,
			textStyle:{
				color:'#fff'
			}
		},
		series : [ {
			name : '人数',
			type : 'heatmap',
			data : data,
			label : {
				normal : {
					show : true
				}
			},
			itemStyle : {
				emphasis : {
					shadowBlur : 10,
					shadowColor : 'rgba(0, 0, 0, 0.5)'
				}
			}
		} ]
	};
	chart5.setOption(option);
}