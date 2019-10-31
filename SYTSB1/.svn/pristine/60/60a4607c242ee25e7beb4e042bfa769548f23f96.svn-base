function tenThousand(rows) {
	var data = [];
	for (var i = 0; i < rows.length; i++) {
		var row = rows[i];
		var item = {};
		item.dept = row.dept;
		$.each(row, function(k, v) {
			if (typeof v == 'number') {
				item[k] = parseFloat(v / 10000).toFixed(4);
			}
		});
		data.push(item);
	}
	return data;
}

var pie1 = null;
function drawPie(id,row) {
	madeMaxPopUpBox(id);
	openMaxPopUpBox(id);
	var data = {};
	data.legendData = [];
	data.seriesData = [];
	data.selected = {};
	$.each(row, function(k, v) {
		for (var i = 0; i < clsses.length; i++) {
			if (v == 0 || k == 'total') {
				break;
			} else if (k == 'other') {
				var obj = {};
				obj.name = '其他';
				obj.value = v;
				data.seriesData.push(obj);
				data.legendData.push('其他');
				data.selected['其他'] = true;
				break;
			} else if (k == clsses[i]) {
				var obj = {};
				obj.name = k;
				obj.value = v;
				data.seriesData.push(obj);
				data.legendData.push(clsses[i]);
				data.selected[clsses[i]] = true;
				break;
			}
		}
	});
	$("#"+id).find("div").remove();
	var div = document.createElement('div');
	$("#"+id).append(div);
	$(div).css({
		width : '100%',
		height : '100%'
	});
	pie1 = echarts.init(div);
	var option = {
		title : {
			text : row.dept,
			subtext : '部门收费统计',
			x : 'center',
			textStyle : {
				color : '#ccc'
			}
		},
		tooltip : {
			trigger : 'item',
			formatter : "{a} <br/>{b} : {c} ({d}%)"
		},
		legend : {
			type : 'scroll',
			orient : 'vertical',
			right : 20,
			top : 100,
			bottom : 20,
			data : data.legendData,
			textStyle : {
				color : '#fff'
			}
		},
		textStyle : {
			color : '#fff'
		},
		series : [ {
			name : '开票类型',
			type : 'pie',
			radius : '55%',
			center : [ '40%', '50%' ],
			data : data.seriesData.sort(function(a, b) {
				return a.value - b.value;
			}),
			itemStyle : {
				emphasis : {
					shadowBlur : 10,
					shadowOffsetX : 0,
					shadowColor : 'rgba(0, 0, 0, 0.5)'
				}
			}
		} ]
	};
	pie1.setOption(option);
	var startDate = $("#startDate").datebox('getValue');
	var endDate = $("#endDate").datebox('getValue');
	//财务详细列表
	pie1.on('click', function (param) {
		top.$.dialog({
			width : 900,
			height : 600,
			lock : true,
			title : "部门收入详细列表",
			data : {
				"window" : window
			}, 
			content : 'url:'+encodeURI("app/finance/statistics/statistics_income_detailed.jsp?dept="+row.dept+"&clss="+param.name
			 						+"&startDate="+startDate+"&endDate="+endDate)
		});
	});
}

var pie2 = null;
function drawPie2(id,row) {
	madeMaxPopUpBox(id);
	openMaxPopUpBox(id);
	var data = {};
	data.legendData = [];
	data.seriesData = [];
	data.selected = {};
	$.each(row, function(k, v) {
		for (var i = 0; i < clsses.length; i++) {
			if (v == 0 || k == 'total') {
				break;
			} else if (k == 'other') {
				var obj = {};
				obj.name = '其他';
				obj.value = v;
				data.seriesData.push(obj);
				data.legendData.push('其他');
				data.selected['其他'] = true;
				break;
			} else if (k == clsses[i]) {
				var obj = {};
				obj.name = k;
				obj.value = v;
				data.seriesData.push(obj);
				data.legendData.push(clsses[i]);
				data.selected[clsses[i]] = true;
				break;
			}
		}
	});
	$("#"+id).find("div").remove();
	var div = document.createElement('div');
	$("#"+id).append(div);
	$(div).css({
		width : '100%',
		height : '100%'
	});
	pie2 = echarts.init(div);
	var option = {
		title : {
			text : row.dept,
			subtext : '部门各经济类型报销费用',
			x : 'center',
			textStyle : {
				color : '#ccc'
			}
		},
		tooltip : {
			trigger : 'item',
			formatter : "{a} <br/>{b} : {c} ({d}%)"
		},
		legend : {
			type : 'scroll',
			orient : 'vertical',
			right : 20,
			top : 100,
			bottom : 20,
			data : data.legendData,
			textStyle : {
				color : '#fff'
			}
		},
		textStyle : {
			color : '#fff'
		},
		series : [ {
			name : '经济类型',
			type : 'pie',
			radius : '55%',
			center : [ '40%', '50%' ],
			data : data.seriesData.sort(function(a, b) {
				return a.value - b.value;
			}),
			itemStyle : {
				emphasis : {
					shadowBlur : 10,
					shadowOffsetX : 0,
					shadowColor : 'rgba(0, 0, 0, 0.5)'
				}
			}
		} ]
	};
	pie2.setOption(option);
	//财务支出详细列表
	var startDate = $("#startDate").datebox('getValue');
    var endDate = $("#endDate").datebox('getValue');
	pie2.on('click', function (param) {
		top.$.dialog({
			width : 900,
			height : 600,
			lock : true,
			title : "财务报账费用统计",
			data : {
				"window" : window
			}, 
			content : 'url:'+encodeURI("app/finance/statistics/statistics_fee_detailed.jsp?dept="+row.dept+"&clss="+param.name
			 						+"&startDate="+startDate+"&endDate="+endDate)
		});
	});
}