var interval; // 控制循环
jnjy = 0;// 今年检验
jnsk = 0;// 今年收款
jnbx = 0;// 今年报销
jrsk = 0;// 今日收款
jrjy = 0;// 今日检验
jrdy = 0;// 今日打印
$(function() {
	$(".addnum").hide();
	scrollOption(); // 列表滚动效果

	ta1(unit_id);
	ta2(unit_id);
	timeCount(unit_id);
	// 加载左上角数据 1分钟刷新一次
	setInterval(function() {
		timeCount(unit_id);
	}, 60000);
	setInterval(function() {
		$(".addnum").hide();
	}, 10000);
	$("#date_year").html(getNowFormatDate());
	// 一小时刷新数据
	setInterval(function() {
		// ta1(unit_id);
		ta2(unit_id);
	}, 3600000);
});
function scrollOption() {
	$.fn.myScroll = function(options) {
		var bowls;// 2017年03月21日 11:52:01 星期二 lybide 改进，必须每次都清空
					// clearInterval(bowls);
		// 默认配置
		var defaults = {
			speed : 40,
			// 滚动速度,值越大速度越慢
			rowHeight : 24
		// 每行的高度
		};
		var opts = $.extend({}, defaults, options), intId = [];

		function marquee(obj, step) {
			obj.find("ul").animate({
				marginTop : '-=1'
			}, 0, function() {
				var s = Math.abs(parseInt($(this).css("margin-top")));
				if (s >= step) {
					$(this).find("li").slice(0, 1).appendTo($(this));
					$(this).css("margin-top", 0);
				}
			});
		}

		this.each(function(i) {
			var sh = opts["rowHeight"], speed = opts["speed"], _this = $(this);
			_this.find("ul").stop(true).css("margin-top", "0");
			clearInterval(bowls);// 2017年03月21日 12:06:20 星期二 lybide
			bowls = setInterval(function() {
				if (_this.find("ul").height() <= _this.height()) {
					clearInterval(bowls);
				} else {
					marquee(_this, sh);
				}
			}, speed);
			_this.hover(function() {
				clearInterval(bowls);
			}, function() {
				clearInterval(bowls);
				bowls = setInterval(function() {
					if (_this.find("ul").height() <= _this.height()) {
						clearInterval(bowls);
					} else {
						marquee(_this, sh);
					}
				}, speed);
			});
		});

	}
}
function ta1(unit_id) {
	$
			.getJSON(
					"/deviceTjAction/tj/getTjJq.do?unit_id=" + unit_id,
					function(res) {
						if (res.success) {
							var data = res.list6;
							var data1 = res.list12;
							if (data.length > 0) {

							} else {
								$("#qxj").html("请休假");
								$("#qxj1").html("姓名");
								$("#qxj2").html("请假类型");
								$("#qxj3").html("日期");
								$("#qxj4").html("申请状态");
								data = data1
							}
							var dul = $("#list1 > ul");
							dul.html("");
							$(".ta-list-cont").height(
									$("#m-r-list-tab").height() - 40);
							window.onresize = function() {
								$(".ta-list-cont").height(
										$("#m-r-list-tab").height() - 40);
							}
							/*
							 * var sum=0; if(data.length>10){ sum=10; }else{
							 * sum=data.length; }
							 */
							for (var i = 0; i < data.length; i++) {
								var sn = data[i].report_sn;
								if (sn.indexOf("CO-") > 0) {
									sn = sn.substring(3);
								}
								var ele = $("<li id='" + data[i].num
										+ "'><div><div><span>" + sn
										+ "</span></div></div><div><div><span>"
										+ data[i].com_name
										+ "</span></div></div><div><div><span>"
										+ data[i].advance_time.substring(0, 10)
										+ "</span></div></div><div><div><span>"
										+ data[i].inspection_conclusion
										+ "</span></div></div><div><div><span>");
								ele.click(function(e) {
									var _this = $(this);
									/* var html=$("#detail1").html(); */
									// listDetail(listId,this.id);
									queryFlow1($(this).attr('id'))
								});
								dul.append(ele);
							}
						} else {

						}
					})

	$(".ta-list-cont").height($("#m-r-list-tab").height() - 40);
	var liHeight = $('.ta-list-cont').find('li').eq(0).outerHeight(true);
	$("#demohq_a").myScroll({
		speed : 100,
		// 数值越大，速度越慢
		rowHeight : liHeight
	// li的高度
	});
}
//
function ta2(unit_id) {
	//如果是科研部门画无损部门持证的饼图
	if(unit_id!="100030"){
		//其他部门则显示报账
		$.getJSON("/deviceTjAction/tj/getTjFy.do?unit_id=" + unit_id,
				function(res) {
					if (res.success) {
						var data = res.list7;
						var dul = $("#list2 > ul");
						dul.html("");
						$(".ta-list-cont2").height(
								$("#m-r-list-tab1").height() - 40);
						window.onresize = function() {
							$(".ta-list-cont2").height(
									$("#m-r-list-tab1").height() - 40);
						}
						for (var i = 0; i < data.length; i++) {
							var status = "";
							if (data[i].inspection_conclusion == "CSTG") {
								status = "审批通过";
							}
							if (data[i].inspection_conclusion == "BXYES") {
								status = "已报销";
							}
							if (data[i].inspection_conclusion == "SUBMIT") {
								status = "未审核";
							}
							if (data[i].inspection_conclusion == "PRINT") {
								status = "已打印";
							}
							if (data[i].inspection_conclusion == "BXNO") {
								status = "未报销";
							}
							var ele = $("<li><div><div><span>" + data[i].com_name
									+ "</span></div></div><div>" + "<div><span>"
									+ data[i].type + "</span></div></div><div>"
									+ " <div><span>"
									+ data[i].dates.substring(0, 10)
									+ "</span></div></div><div>"
									+ " <div><span><span >" + status
									+ "</span></span></div></div></li>")
							ele.click(function(e) {
								/*
								 * var _this=$(this); var html=$("#detail1").html();
								 */
								// listDetail(listId,this.id);
							});
							dul.append(ele);
						}
					} else {

					}
				})

		$(".ta-list-cont2").height($("#m-r-list-tab1").height() - 40);
		var liHeight = $('.ta-list-cont2').find('li').eq(0).outerHeight(true);
		$("#demohq_b").myScroll({
			speed : 100,
			// 数值越大，速度越慢
			rowHeight : liHeight
			// li的高度
		});
	}
}
// 获取左上角统计数据
function timeCount(unit_id) {
	// 先保存上一次获取数据
	var jnjys = jnjy;// 今年检验
	var jnsks = jnsk;// 今年收款
	var jnbxs = jnbx;// 今年报销
	var jrsks = jrsk;// 今日收款
	var jrjys = jrjy;// 今日检验
	var jrdys = jrdy;// 今日打印

	$.ajaxSettings.async = false;
	$.getJSON("/deviceTjAction/tj/initCount.do?unit_id=" + unit_id, function(
			res) {
		if (res.success) {
			var data = res.data;
			$("#m-ds1-num").html(data.annual);
			$("#m-ds2-num").html(tenThousand(data.yyMoney) + "万");
			$("#m-ds3-num").html(tenThousand(data.yyBxMoney) + "万");
			$("#m-ds4-num").html(data.today);
			$("#m-ds5-num").html(data.todayDevices);
			if (type == "综合部门") {
				$("#m-ds1-num").html(data.clfsl + "笔");
				$("#m-ds2-num").html(data.pxsl + "笔");
				$("#m-ds3-num").html(data.fysl + "笔");
				$("#m-ds4-num").html("0");
				$("#m-ds5-num").html(tenThousand(data.yyBxMoney) + "万");
			}
			jnjy = data.annual;// 今年检验
			jnsk = data.yyMoney;// 今年收款
			jnbx = data.yyBxMoney;// 今年报销
			jrjy = data.today;// 今日检验
			jrdy = data.todayDevices;// 今日打印
		} else {

		}
	})
	// 获取今日收款
	$.getJSON("/deviceTjAction/tj/getTjZb.do?unit_id=" + unit_id,
			function(res) {
				if (res.success) {
					var data = res.list1;
					if (data.length > 0) {
						$(".today_data").html(data[0].money);
					} else {
						$(".today_data").html("0");
					}

					jrsk = data[0]==undefined?0:data[0].money;// 今日收款
				} else {

				}
			})
	// 查询是否有增加的
	var jnjyss = jnjys - jnjy;// 今年检验
	var jnskss = jnsks - jnsk;// 今年收款
	var jnbxss = jnbxs - jnbx;// 今年报销
	var jrskss = jrsks - jrsk;// 今日收款
	var jrjyss = jrjys - jrjy;// 今日检验
	var jrdyss = jrdys - jrdy;// 今日打印
	/*
	 * jnjyss=10; jnskss=10; jnbxss=10; jrskss=10; jrjyss=10; jrdyss=10;
	 */
	if (flg == 0) {
		if (jnjyss > 0) {
			$("#addnum0").show();
			$("#anum0").html("+" + jnjyss);
		}
		if (jnskss > 0) {
			$("#addnum1").show();
			$("#anum1").html("+" + jnskss);
		}
		if (jnbxss > 0) {
			$("#addnum2").show();
			$("#anum2").html("+" + jnbxss);
		}
		if (jrjyss > 0) {
			$("#addnum3").show();
			$("#anum3").html("+" + jrjyss);
		}
		if (jrskss > 0) {
			$("#addnum5").show();
			$("#anum5").html("+" + jrskss);
		}
		if (jrdyss > 0) {
			$("#addnum4").show();
			$("#anum4").html("+" + jrdyss);
		}
	}
}
// 将数据转成“万元”
function tenThousand(rows) {
	var data = 0;
	$.each(rows, function(k, v) {
		if (typeof v == 'number') {
			data = parseFloat(v / 10000).toFixed(2);
		}
	});
	return data;
}
function getNowFormatDate() {
	var date = new Date();
	var seperator1 = "-";
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var strDate = date.getDate();
	if (month >= 1 && month <= 9) {
		month = "0" + month;
	}
	if (strDate >= 0 && strDate <= 9) {
		strDate = "0" + strDate;
	}
	var currentdate = year + seperator1 + month + seperator1 + strDate;
	return currentdate;
}
//
function clickCount(id) {
	switch (id) {
	case "m-ds1-num":
		openDialog(
				"url:app/gis/scjy/v1/report_query_list.jsp?type=jnjy&unit_id="
						+ unit_id, "今年检验列表");
		break;
	case "m-ds2-num":
		openDialog(
				"url:app/gis/scjy/v1/report_query_list.jsp?type=jnsk&unit_id="
						+ unit_id, "今年收款列表");
		break;
	/*
	 * case "m-ds3-num" :
	 * openDialog("url:app/gis/scjy/v1/report_query_list.jsp?type=jnbx&unit_id="+unit_id,"今年报销列表");
	 * break;
	 */
	case "m-ds4-num":
		openDialog(
				"url:app/gis/scjy/v1/report_query_list.jsp?type=jrjy&unit_id="
						+ unit_id, "今日检验列表");
		break;
	case "m-ds5-num":
		openDialog(
				"url:app/gis/scjy/v1/report_query_list.jsp?type=jrdy&unit_id="
						+ unit_id, "今日打印列表");
		break;
	default:
		break;
	}
}
function openDialog(url, title) {
	top.$.dialog({
		width : 1000,
		height : 800,
		lock : true,
		title : title,
		content : url,
		data : {
			window : window
		}
	});
}
