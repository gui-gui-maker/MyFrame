var type = "";
//初始化统计
$(function() {
	//初始化地圖
	getZtYear(unit_id);
	getZjTj(unit_id);
	getMtMoney(unit_id);
	//1小时刷新数据
	setInterval(function() {
		getZtYear(unit_id);
		getZjTj(unit_id);
		getMtMoney(unit_id);
	}, 3600000);
});

// 月份统计数据
function getZtYear(unit_id) {
	var years = [];
	var counts = [];
	var counts1 = [];
	if (unit_id != "100030" && unit_id != "100041") {
		$.getJSON("deviceTjAction/tj/getTjYF.do?unit_id=" + unit_id, function(
				res) {
			if (res.success) {
				var data = res.list2;
				var data1 = res.list3;
				var data2 = res.list9;
				var data3 = res.list10;
				if (data.length > 0 || unit_id == "100034"
						|| unit_id == "100035" || unit_id == "100035"
						|| unit_id == "100033" || unit_id == "100036"
						|| unit_id == "100037" || unit_id == "100065"
						|| unit_id == "100066" || unit_id == "100084"
						|| unit_id == "100068") {
					$("#ydbfx").html("月收入对比分析")
					if (data.length > 0) {
						for (x in data) {
							years.push(data1[x].month);
							counts.push(data[x].money);
							counts1.push(data1[x].money);
						}
					}
				} else if (data2.length > 0) {
					$("#ydbfx").html("月支出对比分析")
					// 改变左上角统计数据
					$("#m-ds4-name").html("访问总数");
					$("#m-ds1-name").html("差旅费");
					$("#m-ds2-name").html("培训费");
					$("#m-ds3-name").html("费用报销");
					$("#m-ds5-name").html("今年报销");
					$(".date_money").remove();
					type = "综合部门";
					for (x in data2) {
						years.push(data2[x].month);
						counts.push(data2[x].money);
						counts1.push(data3[x].money);
					}
				}
				draw1(years, counts, counts1);
			} else {

			}
		})
	} else {
		
		//人力资源职称比列
		$.getJSON("deviceTjAction/tj/getTjZj.do?unit_id=" + unit_id, function(res) {
			if (res.success) {
				$("#m-ds4-name").html("访问总数");
				$("#m-ds1-name").html("差旅费");
				$("#m-ds2-name").html("培训费");
				$("#m-ds3-name").html("费用报销");
				$("#m-ds5-name").html("今年报销");
				$(".date_money").remove();
				type = "综合部门";
				var data13 = res.list13;
				var data14 = res.list14;
				var year = new Date().getFullYear(); // 获取年份
				if (unit_id == "100030") {//科研技术管理部
					$("#ydbfx").html("机电持证比例")
					$("#mtdbfx").html("承压持证比例")
					$("#bmryqk").html("科研项目分析")
					var data = res.list5;
					for (x in data) {
						years.push(data[x].type);
						var count = {};
						count['name'] = data[x].type;
						count['value'] = data[x].num;
						counts.push(count);
					}
				} else {
					$("#ydbfx").html("职称比例")
					for (x in data14) {
						years.push(data14[x].type);
						var count = {};
						count['name'] = data14[x].type;
						count['value'] = data14[x].num;
						counts.push(count);
					}
					if (years.toString().indexOf("高级工程师（副高级）") < 0) {
						years.push('高级工程师（副高级）');
						counts.push({
							name : '高级工程师（副高级）',
							value : '0'
						});
					}
					if (years.toString().indexOf("教授级高级工程师（正高级）") < 0) {
						years.push('教授级高级工程师（正高级）');
						counts.push({
							name : '教授级高级工程师（正高级）',
							value : '0'
						});
					}
				}
				draw3Jd(years, counts);
			} 
		})
	}
}
// 每天统计数据
function getMtMoney(unit_id) {
	var years = [];
	var counts = [];
	if (unit_id != "100030") {
		$.getJSON("deviceTjAction/tj/getTjZb.do?unit_id=" + unit_id, function(
				res) {
			if (res.success) {
				var data = res.list1;
				var data1 = res.list11;
				if (data.length > 0 || unit_id == "100034"
						|| unit_id == "100035" || unit_id == "100035"
						|| unit_id == "100033" || unit_id == "100036"
						|| unit_id == "100037" || unit_id == "100065"
						|| unit_id == "100066" || unit_id == "100084"
						|| unit_id == "100068") {
					if (data.length > 0) {
						for (var x = 6; x >= 0; x--) {
							$("#mtdbfx").html("收入周报")
							var d = data[x].dates;
							years.push(d.substring(5, 10));
							counts.push(data[x].money);
						}
					}
				} else if (data1.length > 0) {
					$("#mtdbfx").html("支出周报")
					for (var x = 6; x >= 0; x--) {
						var d = data1[x].month;
						years.push(d.substring(5, 10));
						counts.push(data1[x].money);
					}
				}
				//console.log(years);
				//console.log(counts);
				draw2(years, counts);
			}
		})
	} else {
		$.getJSON("deviceTjAction/tj/getTjZj.do?unit_id=" + unit_id, function(res) {
			if (res.success) {
				var data = res.list6;
				for (x in data) {
					years.push(data[x].type);
					var count = {};
					if (data[x].type == "MsS磁致伸缩超声导波检测") {
						count['name'] = "MsS";
					} else {
						count['name'] = data[x].type;
					}
					count['value'] = data[x].num;
					counts.push(count);
				}
				draw3Cy(years, counts);
				//画出无损持证情况
				var items = res.wusun;
				var labels = [],vals = [];
				for (y in items) {
					labels.push(items[y].type);
					vals.push({"name":items[y].type,"value":items[y].num});
				}
				drawWuSunChat(labels,vals);
			} else {

			}
		})
	}
}
function drawWuSunChat(labels,data){
	$("#wusunChart-container").find("div").remove();
	var div = document.createElement("div");
	$("#wusunChart-container").append(div);
	$(div).css({
		width : '100%',
		height : '100%'
	}).attr("id","wusunChart");
	var myChart = echarts.init(div);
	var option = {
		title : {
			text : '',
			subtext : '',
			x : 'left',
			y : 'left',
			textStyle : {
				fontWeight : 'normal',
				fontSize : 16,
				color:'#fff'
			}
		},
		tooltip : {
			trigger : 'item',
			formatter : "{b}: {c} ({d}%)"
		},
		legend : {
			orient : 'vertical',
			x : 'left',
			data : labels,
			textStyle : {
				color : '#fff' // 图例文字颜色
			}
		},
		series : [ {
			type : 'pie',
			selectedMode : 'single',
			radius : [ '35%', '90%' ],
			color : [ '#86D560', '#AF89D6', '#59ADF3', '#FF999A', '#FFCC67',
					'#f845f1', '#ad46f3', '#5045f6', '#4777f5', '#44aff0',
					'#45dbf7', '#ff4343' ],
			center : [ '70%', '50%' ],
			label : {
				normal : {
					position : 'inner',
					formatter : '{d}%',

					textStyle : {
						color : '#fff',
						fontWeight : 'bold',
						fontSize : 14
					}
				}
			},
			labelLine : {
				normal : {
					show : false
				}
			},
			data : data
		} ]
	};
	myChart.setOption(option);
	myChart.on('click', function(param) {
		maxWusunChart(labels,data);
	});
}
//放大无损持证统计图
function maxWusunChart(labels,data){
	madeMaxPopUpBox("bigWusunChart");
	openMaxPopUpBox("bigWusunChart");
	var myChart = echarts.init(document.getElementById("bigWusunChart"));
	var option = {
		title : {
			text : '',
			subtext : '',
			x : 'left',
			y : 'left',
			textStyle : {
				color:'#fff',
				fontWeight : 'normal',
				fontSize : 16
			}
		},
		tooltip : {
			trigger : 'item',
			formatter : "{b}: {c} ({d}%)"
		},
		legend : {
			orient : 'vertical',
			x : 'left',
			data : labels,
			textStyle : {
				color : '#fff' // 图例文字颜色
			}
		},
		series : [ {
			type : 'pie',
			selectedMode : 'single',
			radius : [ '35%', '90%' ],
			color : [ '#86D560', '#AF89D6', '#59ADF3', '#FF999A', '#FFCC67',
					'#f845f1', '#ad46f3', '#5045f6', '#4777f5', '#44aff0',
					'#45dbf7', '#ff4343' ],
			center : [ '70%', '50%' ],
			label : {
				normal : {
					position : 'inner',
					formatter : '{d}%',

					textStyle : {
						color : '#fff',
						fontWeight : 'bold',
						fontSize : 14
					}
				}
			},
			labelLine : {
				normal : {
					show : false
				}
			},
			data : data
		} ]
	};
	myChart.setOption(option);
}
//持证统计数据
function getZjTj(unit_id) {
	var years = [];
	var counts = [];
	if (unit_id != "100030") {
		$.getJSON("deviceTjAction/tj/getTjZj.do?unit_id=" + unit_id, function(res) {
			if (res.success) {
				var data = res.list5;
				var data1 = res.list8;
				var data2 = res.list2;
				if (data2.length > 0 || unit_id == "100034"
						|| unit_id == "100035" || unit_id == "100035"
						|| unit_id == "100033" || unit_id == "100036"
						|| unit_id == "100037" || unit_id == "100065"
						|| unit_id == "100066" || unit_id == "100084"
						|| unit_id == "100068" || unit_id == "100030") {
					$("#bmryqk").html("人员持证比例")
					for (x in data) {
						years.push(data[x].type);
						var count = {};
						count['name'] = data[x].type;
						count['value'] = data[x].num;
						counts.push(count);
					}
				} else {
					$("#bmryqk").html("人员学历比例")
					for (x in data1) {
						years.push(data1[x].name);
						var count = {};
						count['name'] = data1[x].name;
						count['value'] = data1[x].num;
						counts.push(count);
					}
				}
				draw3(years, counts);
			} else {

			}
		})
	} else {
		$.getJSON("deviceTjAction/tj/getTjZj.do?unit_id=" + unit_id, function(
				res) {
			if (res.success) {

				var data13 = res.list13;
				var year = new Date().getFullYear(); //获取年份
				years.push((year - 3).toString());
				years.push((year - 2).toString());
				years.push((year - 1).toString());
				years.push((year).toString());
				counts = [ '0', '0', '0', '0' ]
				for (x in data13) {
					if (data13[x].month == (year - 3).toString()) {
						counts.splice(0, 1, data13[x].num);
					}
					if (data13[x].month == (year - 2).toString()) {
						counts.splice(1, 1, data13[x].num);
					}
					if (data13[x].month == (year - 1).toString()) {
						counts.splice(2, 1, data13[x].num);
					}
					if (data13[x].month == (year).toString()) {
						counts.splice(3, 1, data13[x].num);
					}
				}
				;

				draw5(years, counts);
			} else {

			}
		})
	}
}

function draw2(years, counts) {
	
	$("#m-a-main-2").find("div").remove();
	var div = document.createElement('div');
	$("#m-a-main-2").append(div);
	$(div).css({
		width : '100%',
		height : '100%'
	}).attr("id","main2");
	var myChart = echarts.init(div);
	var data_val = [ 2220, 1682, 2791, 3000, 4090, 3230, 2910 ], xAxis_val = [
			'Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat' ];
	var data_val1 = [ 0, 0, 0, 0, 0, 0, 0 ];

	var option = {
		grid : {
			left : 10,
			top : '10%',
			bottom : 0,
			right : 40,
			containLabel : true
		},
		tooltip : {
			show : true,
			backgroundColor : '#384157',
			borderColor : '#384157',
			borderWidth : 1,
			formatter : '{b}:{c}',
			extraCssText : 'box-shadow: 0 0 5px rgba(0, 0, 0, 1)'
		},

		title : {
			text : '',
			x : '4.5%',
			top : '1%',
			textStyle : {
				color : '#fff'
			}
		},
		xAxis : {
			data : years,
			//boundaryGap : false,
			axisLine : {
				lineStyle : {
					color : '#fff'
				}
			},
			axisLabel : {
				textStyle : {
					color : '#fff'
				}
			},
			/*axisTick : {
				show : false
			},*/
			nameTextStyle:{
				color:'#fff'
			}
		},
		yAxis : {
			ayisLine : {
				show : false
			},
			axisLabel : {
				textStyle : {
					color : '#fff'
				}
			},
			splitLine : {
				show : true,
				lineStyle : {
					color : '#fff'
				}
			},
			axisLine : {
				lineStyle : {
					color : '#fff'
				}
			},
			nameTextStyle:{
				color:'#fff'
			}
		},

		series : [ {
			type : 'bar',
			name : 'linedemo',

			tooltip : {
				show : false
			},
			animation : false,
			barWidth : 1.4,
			hoverAnimation : false,
			data : counts,
			itemStyle : {
				normal : {
					color : '#f17a52',
					opacity : 0.6,
					label : {
						show : false
					}
				}
			}
		}, {
			type : 'line',
			name : '距离',

			animation : false,
			symbol : 'circle',

			hoverAnimation : false,
			data : counts,
			itemStyle : {
				normal : {
					color : '#fff',
					opacity : 0,
				}
			},
			lineStyle : {
				normal : {
					width : 1,
					color : '#fff',
					opacity : 1
				}
			}
		}, {
			type : 'line',
			name : 'linedemo',
			smooth : true,
			symbolSize : 10,
			animation : false,
			lineWidth : 1.2,
			hoverAnimation : false,
			data : counts,
			symbol : 'circle',
			itemStyle : {
				normal : {
					color : '#f17a52',
					shadowBlur : 40,
					label : {
						show : true,
						position : 'top',
						textStyle : {
							color : '#fff',

						}
					}
				}
			},
			areaStyle : {
				normal : {
					color : '#f17a52',
					opacity : 0.08
				}
			}

		} ]
	};
	myChart.setOption(option);
	myChart.on('click', function(param) {
		getDraw2();

	});
}

function draw1(year, count, count1) {
	$("#m-a-main-1").find("div").remove();
	var div = document.createElement('div');
	$("#m-a-main-1").append(div);
	$(div).css({
		width : '100%',
		height : '100%'
	}).attr("id","main1");
	var myChart = echarts.init(div);
	option = {
		title : {
			text : '',
			subtext : ''
		},
		tooltip : {
			trigger : 'axis'
		},
		legend : {
			data : [ '2017', '2018' ],
			textStyle : {
				color : '#fff' // 图例文字颜色
			}
		},

		calculable : true,
		xAxis : {

			type : 'category',
			data : year,
			axisLabel : {
				textStyle : {
					color : '#fff'
				}
			},
			axisLine:{
				lineStyle:{
					color:'#fff'
				}
			}
		},
		yAxis : {
			splitLine : {
				show : false
			},
			type : 'value',
			axisLabel : {
				textStyle : {
					color : '#fff'
				}
			},
			axisLine:{
				lineStyle:{
					color:'#fff'
				}
			}
		},
		series : [ {
			name : '2017',
			type : 'bar',
			data : count1,
			itemStyle : {
				normal : {
					color : '#01949B'
				}
			},
			markPoint : {
				data : [ {
					type : 'max',
					name : '最大值'
				}, {
					type : 'min',
					name : '最小值'
				} ]
			},
			markLine : {
				data : [ {
					type : 'average',
					name : '平均值'
				} ]
			}
		}, {
			name : '2018',
			type : 'bar',
			data : count,
			markPoint : {
				data : [ {
					name : '年最高',
					value : 182.2,
					xAxis : 7,
					yAxis : 183
				}, {
					name : '年最低',
					value : 2.3,
					xAxis : 11,
					yAxis : 3
				} ]
			},
			markLine : {
				data : [ {
					type : 'average',
					name : '平均值'
				} ]
			}
		} ]
	};
	myChart.setOption(option);
	myChart.on('click', function(param) {
		getDraw1(param.name);

	});

}
function draw4() {
	var myChart = echarts.init(document.getElementById('main4'));
	option = {
		title : {
			text : '金额',
			textStyle : {
				fontWeight : 'normal',
				fontSize : 16,
				color : '#fff'
			},
			left : '6%'
		},
		tooltip : {
			trigger : 'axis',
			axisPointer : {
				lineStyle : {
					color : '#57617B'
				}
			}
		},
		legend : {
			icon : 'rect',
			itemWidth : 14,
			itemHeight : 5,
			itemGap : 13,
			data : [ '2016', '2017', '2018' ],
			right : '4%',
			textStyle : {
				fontSize : 12,
				color : '#fff'
			}
		},
		grid : {
			left : 10,
			top : '10%',
			bottom : 0,
			right : 40,
			containLabel : true
		},
		xAxis : [
				{
					type : 'category',
					boundaryGap : false,
					axisLine : {
						lineStyle : {
							color : '#57617B'
						}
					},
					data : [ '1月1日', '1月2日', '1月3日', '1月4日', '1月5日', '1月6日',
							'1月7日', '1月8日', '1月9日', '1月10日', '1月11日', '1月12日' ]
				}, {
					axisPointer : {
						show : false
					},
					axisLine : {
						lineStyle : {
							color : '#57617B'
						}
					},
					axisTick : {
						show : false
					},

					position : 'bottom',
					offset : 20,
					data : [ '', '', '', '', '', '', '', '', '', '', {
						value : '',
						textStyle : {
							align : 'left'
						}
					}, '' ]
				} ],
		yAxis : [ {
			type : 'value',
			name : '单位（%）',
			axisTick : {
				show : false
			},
			axisLine : {
				lineStyle : {
					color : '#fff'
				}
			},
			axisLabel : {
				margin : 10,
				textStyle : {
					fontSize : 14,
					color:'#fff'
				}
			},
			splitLine : {
				lineStyle : {
					color : '#57617B'
				}
			}
		} ],
		series : [
				{
					name : '2016',
					type : 'line',
					smooth : true,
					symbol : 'circle',
					symbolSize : 5,
					showSymbol : false,
					lineStyle : {
						normal : {
							width : 1
						}
					},
					areaStyle : {
						normal : {
							color : new echarts.graphic.LinearGradient(0, 0, 0,
									1, [ {
										offset : 0,
										color : 'rgba(137, 189, 27, 0.3)'
									}, {
										offset : 0.8,
										color : 'rgba(137, 189, 27, 0)'
									} ], false),
							shadowColor : 'rgba(0, 0, 0, 0.1)',
							shadowBlur : 10
						}
					},
					itemStyle : {
						normal : {
							color : 'rgb(137,189,27)',
							borderColor : 'rgba(137,189,2,0.27)',
							borderWidth : 12

						}
					},
					data : [ 220, 182, 191, 134, 150, 120, 110, 125, 145, 122,
							165, 122 ]
				},
				{
					name : '2017',
					type : 'line',
					smooth : true,
					symbol : 'circle',
					symbolSize : 5,
					showSymbol : false,
					lineStyle : {
						normal : {
							width : 1
						}
					},
					areaStyle : {
						normal : {
							color : new echarts.graphic.LinearGradient(0, 0, 0,
									1, [ {
										offset : 0,
										color : 'rgba(0, 136, 212, 0.3)'
									}, {
										offset : 0.8,
										color : 'rgba(0, 136, 212, 0)'
									} ], false),
							shadowColor : 'rgba(0, 0, 0, 0.1)',
							shadowBlur : 10
						}
					},
					itemStyle : {
						normal : {
							color : 'rgb(0,136,212)',
							borderColor : 'rgba(0,136,212,0.2)',
							borderWidth : 12

						}
					},
					data : [ 120, 110, 125, 145, 122, 165, 122, 220, 182, 191,
							134, 150 ]
				},
				{
					name : '2018',
					type : 'line',
					smooth : true,
					symbol : 'circle',
					symbolSize : 5,
					showSymbol : false,
					lineStyle : {
						normal : {
							width : 1
						}
					},
					areaStyle : {
						normal : {
							color : new echarts.graphic.LinearGradient(0, 0, 0,
									1, [ {
										offset : 0,
										color : 'rgba(219, 50, 51, 0.3)'
									}, {
										offset : 0.8,
										color : 'rgba(219, 50, 51, 0)'
									} ], false),
							shadowColor : 'rgba(0, 0, 0, 0.1)',
							shadowBlur : 10
						}
					},
					itemStyle : {
						normal : {

							color : 'rgb(219,50,51)',
							borderColor : 'rgba(219,50,51,0.2)',
							borderWidth : 12
						}
					},
					data : [ 220, 182, 125, 145, 122, 191, 134, 150, 120, 110,
							165, 122 ]
				}, ]
	};

	myChart.setOption(option);

}
function draw3(type, num) {
	$("#m-a-main-3").find("div").remove();
	var div = document.createElement('div');
	$("#m-a-main-3").append(div);
	$(div).css({
		width : '100%',
		height : '100%'
	}).attr("id","main3");
	var myChart = echarts.init(div);
	//var myChart = echarts.init(document.getElementById('main3'));
	
	option = {
		title : {
			text : '',
			subtext : '',
			x : 'left',
			y : 'left',
			textStyle : {
				fontWeight : 'normal',
				fontSize : 16,
				color:'#fff'
			}
		},
		tooltip : {
			trigger : 'item',
			formatter : "{b}: {c} ({d}%)"
		},
		legend : {
			orient : 'vertical',
			x : 'left',
			data : type,
			textStyle : {
				color : '#fff' // 图例文字颜色
			}
		},
		series : [ {
			type : 'pie',
			selectedMode : 'single',
			radius : [ '35%', '90%' ],
			color : [ '#86D560', '#AF89D6', '#59ADF3', '#FF999A', '#FFCC67',
					'#f845f1', '#ad46f3', '#5045f6', '#4777f5', '#44aff0',
					'#45dbf7', '#ff4343' ],
			center : [ '70%', '50%' ],
			label : {
				normal : {
					position : 'inner',
					formatter : '{d}%',

					textStyle : {
						color : '#fff',
						fontWeight : 'bold',
						fontSize : 14
					}
				}
			},
			labelLine : {
				normal : {
					show : false
				}
			},
			data : num
		} ]
	};
	myChart.setOption(option);
	myChart.on('click', function(param) {
		getZjTjEchart();

	});
}
function draw3Jd(type, num) {
	$("#m-a-main-1").find("div").remove();
	var div = document.createElement('div');
	$("#m-a-main-1").append(div);
	$(div).css({
		width : '100%',
		height : '100%'
	}).attr("id","main1");
	var myChart = echarts.init(div);
	option = {
		title : {
			text : '',
			subtext : '',
			x : 'left',
			y : 'left',
			textStyle : {
				fontWeight : 'normal',
				fontSize : 16,
				color:'#fff'
			}
		},
		tooltip : {
			trigger : 'item',
			formatter : "{b}: {c} ({d}%)"
		},
		legend : {
			orient : 'vertical',
			x : 'left',
			data : type,
			textStyle : {
				color : '#fff' // 图例文字颜色
			}
		},
		series : [ {
			type : 'pie',
			selectedMode : 'single',
			radius : [ '35%', '90%' ],
			color : [ '#86D560', '#AF89D6', '#59ADF3', '#FF999A', '#FFCC67',
					'#f845f1', '#ad46f3', '#5045f6', '#4777f5', '#44aff0',
					'#45dbf7', '#ff4343' ],
			center : [ '70%', '50%' ],
			label : {
				normal : {
					position : 'inner',
					formatter : '{d}%',

					textStyle : {
						color : '#fff',
						fontWeight : 'bold',
						fontSize : 14
					}
				}
			},
			labelLine : {
				normal : {
					show : false
				}
			},
			data : num
		} ]
	};
	myChart.setOption(option);
	myChart.on('click', function(param) {
		madeMaxPopUpBox("draw3Echart");
		openMaxPopUpBox("draw3Echart");
		draw3Echart(type, num);
	});
}
function draw3Cy(clss, data) {
	$("#m-a-main-2").find("div").remove();
	var div = document.createElement('div');
	$("#m-a-main-2").append(div);
	$(div).css({
		width : '100%',
		height : '100%'
	}).attr("id","main2");
	var myChart = echarts.init(div);
	option = {
		title : {
			text : '',
			subtext : '',
			x : 'left',
			y : 'left',
			textStyle : {
				fontWeight : 'normal',
				fontSize : 16,
				color:'#fff'
			}
		},
		tooltip : {
			trigger : 'item',
			formatter : "{b}: {c} ({d}%)"
		},
		legend : {
			orient : 'vertical',
			x : 'left',
			data : clss,
			textStyle : {
				color : '#fff' // 图例文字颜色
			}
		},
		series : [ {
			type : 'pie',
			selectedMode : 'single',
			radius : [ '35%', '90%' ],
			color : [ '#86D560', '#AF89D6', '#59ADF3', '#FF999A', '#FFCC67',
					'#f845f1', '#ad46f3', '#5045f6', '#4777f5', '#44aff0',
					'#45dbf7', '#ff4343' ],
			center : [ '70%', '50%' ],
			label : {
				normal : {
					position : 'inner',
					formatter : '{d}%',

					textStyle : {
						color : '#fff',
						fontWeight : 'bold',
						fontSize : 14
					}
				}
			},
			labelLine : {
				normal : {
					show : false
				}
			},
			data : data
		} ]
	};
	myChart.setOption(option);
	myChart.on('click', function(param) {
		madeMaxPopUpBox("draw3Echart");
		openMaxPopUpBox("draw3Echart");
		draw3Echart(clss, data);
	});
}
function draw5(year, count) {
	$("#m-a-main-3").find("div").remove();
	var div = document.createElement('div');
	$("#m-a-main-3").append(div);
	$(div).css({
		width : '100%',
		height : '100%'
	}).attr("id","main3");
	var myChart = echarts.init(div);
	//var myChart = echarts.init(document.getElementById('main3'));
	option = {

		tooltip : {
			trigger : 'axis',
			axisPointer : { // 坐标轴指示器，坐标轴触发有效
				type : 'shadow' // 默认为直线，可选为：'line' | 'shadow'
			}
		},
		grid : {
			left : '3%',
			right : '4%',
			bottom : '3%',
			containLabel : true
		},
		xAxis : [ {
			type : 'category',
			data : year,
			axisTick : {
				alignWithLabel : true
			},
			axisLabel : {
				textStyle : {
					color : '#fff'
				}
			},
			axisLine:{
				lineStyle:{
					color:'#fff'
				}
			}
		} ],
		yAxis : [ {
			splitLine : {
				show : false
			},
			type : 'value',
			axisLabel : {
				textStyle : {
					color : '#fff'
				}
			},
			axisLine:{
				lineStyle:{
					color:'#fff'
				}
			}
		} ],
		series : [ {
			name : '科研项目',
			type : 'bar',
			barWidth : '60%',
			data : count
		} ]
	};
	myChart.setOption(option);
	myChart.on('click', function(param) {
		getDraw5(param.name);

	});

}

function getDraw1(name) {
	//alert(name);
	madeMaxPopUpBox("draw1Chart");
	openMaxPopUpBox("draw1Chart");
	var years = [];
	var counts = [];
	$.getJSON(
			"deviceTjAction/tj/getYf.do?unit_id=" + unit_id + "&name=" + name+"&num="+Math.random(),
			function(res) {
				if (res.success) {
					var data = res.list;
					for (x in data) {
						years.push(data[x].dates);
						counts.push(data[x].month);
					}
					draw1Echart(years, counts);
				} 
			})

}
function getDraw2() {
	top.$.dialog({
		width : 1000,
		height : 800,
		lock : true,
		title : "收款统计",
		content : "url:app/gis/scjy/v4/tj_list.jsp?unit_id=" + unit_id,
		data : {
			window : window
		}
	});
	
}
function getDraw5() {
	top.$.dialog({
		width : 1000,
		height : 800,
		lock : true,
		title : "科研项目",
		content : "url:app/fwxm/scientific/scientific_list.jsp",
		data : {
			window : window
		}
	});
}
function draw1Echart(year, count) {
	var myChart = echarts.init(document.getElementById('draw1Chart'));
	option = {
		color : [ '#3398DB' ],
		title : {
			text : '检验收费情况',
			subtext : '按月份进行统计，统计该月的检验收费情况',
			left : 'center',
			top : '10',
			textStyle : {
				color : '#FFF'
			}
		},
		tooltip : {
			trigger : 'axis',
			formatter : '{b} 月<br /><span style="display:inline-block;margin-right:5px;border-radius:10px;width:10px;height:10px;background-color:#3398DB"></span>{c} 元'
		},
		grid : {
			left : 10,
			top : '10%',
			bottom : 0,
			right : 40,
			containLabel : true
		},
		xAxis : {
			data : year,
			name : '日期',
			axisLine : {
				lineStyle : {
					color : '#FFFFFF',
				}
			}
		},
		yAxis : [ {
			name : '金额',
			type : 'value'
		} ],
		textStyle : {
			color : "#fff"
		},
		series : [ {
			name : '金额',
			type : 'bar',
			barWidth : '30%',
			data : count,
			markPoint : {
				data : [ {
					type : 'max',
					name : '最大值'
				}, {
					type : 'min',
					name : '最小值'
				} ]
			},
			markLine : {
				data : [ {
					type : 'average',
					name : '平均值'
				} ]
			}
		},

		]
	};

	myChart.setOption(option);

}
function getZjTjEchart() {
	madeMaxPopUpBox("draw3Echart");
	openMaxPopUpBox("draw3Echart");
	var years = [];
	var counts = [];
	$.getJSON("deviceTjAction/tj/getTjZj.do?unit_id=" + unit_id, function(res) {
		if (res.success) {
			if (unit_id == "100030") {
				var data = res.list5;
				for (x in data) {
					years.push(data[x].type);
					var count = {};
					count['name'] = data[x].type;
					count['value'] = data[x].num;
					counts.push(count);
				}
				;
			} else {
				var data = res.list8;
				for (x in data) {
					years.push(data[x].name);
					var count = {};
					count['name'] = data[x].name;
					count['value'] = data[x].num;
					counts.push(count);
				}
				;
			}

			draw3Echart(years, counts);
		} 
	})
}
function draw3Echart(type, num) {
	var myChart = echarts.init(document.getElementById('draw3Echart'));
	option = {
		title : {
			text : '',
			subtext : '',
			x : 'left',
			y : 'left',
			textStyle : {
				fontWeight : 'normal',
				fontSize : 16,
				color:'#fff'
			}
		},
		tooltip : {
			trigger : 'item',
			formatter : "{b}: {c} ({d}%)"
		},
		legend : {
			orient : 'vertical',
			x : 'left',
			data : type,
			textStyle : {
				color : '#fff' // 图例文字颜色
			}
		},
		series : [ {
			type : 'pie',
			selectedMode : 'single',
			radius : [ '35%', '90%' ],
			color : [ '#86D560', '#AF89D6', '#59ADF3', '#FF999A', '#FFCC67' ],
			center : [ '70%', '50%' ],
			label : {
				normal : {
					position : 'inner',
					formatter : '{d}%',

					textStyle : {
						color : '#fff',
						fontWeight : 'bold',
						fontSize : 14
					}
				}
			},
			labelLine : {
				normal : {
					show : false
				}
			},
			data : num
		} ]
	};
	myChart.setOption(option);
	if (unit_id == "100041") {
		myChart.on('click', function(param) {
			var type = "";
			if (param.name == "技术员") {
				type = "1";
			}
			if (param.name == "助理工程师") {
				type = "2";
			}
			if (param.name == "工程师") {
				type = "3";
			}
			if (param.name == "高级工程师（副高级）") {
				type = "4";
			}
			if (param.name == "教授级高级工程师（正高级）") {
				type = "5";
			}
			if (param.name == "其他") {
				type = "6";
			}
			top.$.dialog({
				width : 1200,
				height : 800,
				lock : true,
				title : "人员信息",
				content : "url:app/gis/scjy/v4/employee_active.jsp?type="
						+ type,
				data : {
					window : window
				}
			});
		});
	}
}