<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="com.khnt.security.CurrentSessionUser"%>
<%@ page
	import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

CurrentSessionUser sessionUser=(CurrentSessionUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
String user=sessionUser.getSysUser().getName();
String userBm= sessionUser.getDepartment().getId(); 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="description" content="">
<meta name="keywords" content="">
<base href="<%=basePath%>">
<title>四川省特种设备检验云</title>
<link rel="stylesheet" type="text/css"
	href="app/gis/scjy/v4/mapNew/stylegx.css" media="all" />
<link rel="stylesheet" type="text/css"
	href="app/gis/scjy/v4/mapNew/bm.css" media="all" />
<link rel="stylesheet" type="text/css"
	href="app/gis/scjy/v4/mapNew/animate.css" media="all" />
<style type="text/css">
#miBackground {
	position: absolute;
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	background: url(app/gis/scjy/v4/images/bg02.jpg);
}

.anchorBL {
	display: none !important;
}
</style>
<script src="app/gis/scjy/v4/mapNew/json.js"></script>
<script src="app/gis/scjy/v4/mapNew/jquery-1.8.3.min.js"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=FDmrHK5fKK7ALGXCHRY4BMoT1ys8XO5U"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/library/AreaRestriction/1.2/src/AreaRestriction_min.js"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/library/GeoUtils/1.2/src/GeoUtils_min.js"></script>
<script src="app/gis/scjy/v4/mapNew/echarts.min.js"></script>
<script src="app/gis/scjy/v4/mapNew/initEchart.js"></script>
<script src="app/gis/scjy/v4/mapNew/main.js"></script>
<script src="app/gis/scjy/v4/mapNew/classie.js"></script>
<script src="app/gis/scjy/v4/mapNew/wow.min.js"></script>
<script src="app/gis/scjy/v4/mapNew/maxPopUpBox.js"></script>
<script src="app/gis/scjy/v4/mapNew/map.js"></script>
<script src="app/gis/scjy/v4/mapNew/MyOverlay.js"></script>
<script src="app/gis/scjy/v4/mapNew/initQuery.js"></script>

<script test="text/javascript">

var unit_id ="<%=userBm%>";
var unit_ids ="<%=userBm%>";
var name="<%=user%>"
var flg=0;
if(unit_id=="100082"){
	unit_id="100020";
}
var lboxes1 = "";
var lboxes2 = "";
var lboxes3 = "";
var rboxes1 = "";
var rboxes2 = "";
var rboxes3 = "";
$(function () {
	lboxes1 = $("div.m-area-left").find("div.m-a-boxes").eq(0);
	lboxes2 = $("div.m-area-left").find("div.m-a-boxes").eq(1);
	lboxes3 = $("div.m-area-left").find("div.m-a-boxes").eq(2);
	rboxes1 = $("div.m-area-right").find("div.m-a-boxes").eq(0);
	rboxes2 = $("div.m-area-right").find("div.m-a-boxes").eq(1);
	rboxes3 = $("div.m-area-right").find("div.m-a-boxes").eq(2);
 	var data = <u:dict sql="select t.id as code,t.org_name as text from SYS_ORG t where t.parent_id = '100000' and t.id not in ('100032', '100039', '100069', '100048', '100070', '100038', '100059', '100061', '100051','100047','100044','100042') and t.status = 'used' order by t.orders2"/>;
    for(var i in data){
	 	$("#bmxz").append(' <li><a href="javascript:getUnitId(\''+data[i].id+'\',\''+data[i].text+'\')">'+data[i].text+'</a></li>');
    }
});

function getUnitId(unitId,unitName){
	//科研部门图像交换位子
	if(unitId=='100030'){
		$("div.m-area-left div.m-a-boxes").remove();
		$("div.m-area-right div.m-a-boxes").remove();
		$("div.m-area-left").append(lboxes1).append(rboxes1).append(rboxes2);
		$("div.m-area-right").append(lboxes2).append(lboxes3)
			.append('<div class="m-a-boxes">'+
						'<div class="m-a-boxes-tit" id="bmryqk">无损持证统计</div>'+
						'<div id="wusunChart-container" class="m-a-main">'+
							'<div id="wusunChart" style="width:100%;height:100%;"></div> '+
						'</div>'+
						'<div class="m-a-pl1"></div>'+
						'<div class="m-a-pl2"></div>'+
						'<div class="m-a-pr1"></div>'+
						'<div class="m-a-pr2"></div>'+
						'<div class="m-a-beij"></div>'+
						'<div class="m-a-border"></div>'+
					'</div>');
	}else{
		$("div.m-area-left div.m-a-boxes").remove();
		$("div.m-area-right div.m-a-boxes").remove();
		$("div.m-area-left").append(lboxes1).append(lboxes2).append(lboxes3);
		$("div.m-area-right").append(rboxes1).append(rboxes2).append(rboxes3);
	}
	if(unit_ids!="100082"&&unit_ids!="100029"){
		alert("对不起，暂时不能查看其它部门统计！");
		return;
	}else{
		if(name=="林涛"){
			if(unitId!="100025"||unitId!="100045"||unitId!="100021"||unitId!="100022"||unitId!="100035"||unitId!="100037"){
				alert("对不起，只能查看分管部门统计！");
				return;
			}
		}
		if(name=="熊荣"){
			if(unitId!="100030"||unitId!="100068"||unitId!="100066"||unitId!="100020"||unitId!="100062"){
				alert("对不起，只能查看分管部门统计！");
				return;
			}
		}
		if(name=="杜艳雄"){
			if(unitId!="100026"||unitId!="100046"||unitId!="100065"||unitId!="100033"||unitId!="100036"||unitId!="100078"||unitId!="100024"){
				alert("对不起，只能查看分管部门统计！");
				return;
			}
		}
		if(name=="黄坚"){
			if(unitId!="100040"||unitId!="100041"||unitId!="100043"||unitId!="100028"||unitId!="100082"||unitId!="100023"||unitId!="100063"){
				alert("对不起，只能查看分管部门统计！");
				return;
			}
		}
		if(name=="孙宇艺"){
			if(unitId!="100043"||unitId!="100045"||unitId!="100083"){
				alert("对不起，只能查看分管部门统计！");
				return;
			}
		}
		if(name=="邱兆蓉"){
			if(unitId!="100026"||unitId!="100035"||unitId!="100034"){
				alert("对不起，只能查看分管部门统计！");
				return;
			}
		}
	}
	if(unitId!=""){
		if(rtCache[unitId] && rtCache[unitId].length>0){
			var devs = rtCache[unitId];
			//但画出部门检验区划
			closeCdMap();
			for(var i=0;i<CDAREAS.length;i++){
				if(CDAREAS[i].code == devs[0].DEVICE_AREA_CODE){
					drawCd(CDAREAS[i]);
				}
			}  
			stopShowRtCache();
			startShowRtCache(6,5000,unitId);
		}else{
			closeCdMap();
			map.centerAndZoom(new BMap.Point(103.945494,30.746935),10);
			//缩放到省级地图
			stopShowRtCache();
			startShowRtCache(1,5000);
		}
	}
	$(".m-a-tit").html(unitName);
	unit_id=unitId;
	flg=1;
	getZtYear(unitId);
	getZjTj(unitId);
	getMtMoney(unitId);
	ta1(unitId);
	ta2(unitId);
	timeCount(unitId);
	
}

</script>
<!--[if IE 8]>
<link rel="stylesheet" type="text/css" href="css/style-ie8.css" media="all" />
<![endif]-->

</head>
<body>
	<div id="tccontent">
		<div id="light" class="white_content md-show wow bounceIn">
			<div class="wtctbg"></div>
			<div class="tankuang">
				<div class="close">
					<a id="t-close-btn" class="iconfont icon-esc"
						href="javascript:void(0)" title="关闭">x</a>
				</div>
				<!--中间主要内容区  start -->

				<script src="app/gis/scjy/v4/js/wow.min.js"></script>
				<script>
				if (!(/msie [6|7|8|9]/i.test(navigator.userAgent))){
					new WOW().init();
				};
			</script>
				<div class="m-center-wrap">
					<div class="m-center-main">
						<div class="max_chart"></div>
					</div>
				</div>
			</div>
		</div>
		<div id="fade" class="black_overlay" style="display: none;"></div>
	</div>
	<div id="sysMain" class="sysmain systongji">

		<div class="s-skin-container s-skin-container-bm"></div>

		<div id="systemTitle" class="m-top-logo">
			<div class="m-top-logo-txt">
				<span id="systemTitleText" class="text1"><span
					class="m-top-logo-img"><img
						src="app/gis/scjy/v4/images/tjy_lo.png"></span>四川省特检院智慧特检大数据-D2(测试版)</span>
			</div>
		</div>


		<div id="map" class="main-map" style="height: 100%;"></div>

		<div class="m-area-left">
			<div class="m-a-boxes">
				<!--总的统计数据-->
				<div class="m-counts">
					<div class="m_counts_bg2">
						<div class="m-c-clumn">
							<div class="m-c-data" id="m-ds1-num"
								onclick="clickCount(this.id)" style="cursor: pointer">123
							</div>
							<div class="m-c-tit" id="m-ds1-name">今年检验</div>
							<div class="addnum" id="tp">
								<span class="anum0">+5</span>
							</div>
						</div>
						<div class="m-c-clumn">
							<div class="m-c-data" id="m-ds2-num"
								onclick="clickCount(this.id)" style="cursor: pointer">222</div>
							<div class="m-c-tit" id="m-ds2-name">今年收款</div>
						</div>
						<div class="m-c-clumn">
							<div class="m-c-data" id="m-ds3-num"
								onclick="clickCount(this.id)" style="cursor: pointer">2123</div>
							<div class="m-c-tit" id="m-ds3-name">今年报销</div>
						</div>
					</div>
					<div class="m_counts_bg">
						<div class="m-c-clumn">
							<div class="m-c-data" id="m-ds4-num"
								onclick="clickCount(this.id)" style="cursor: pointer">108</div>
							<div class="m-c-tit" id="m-ds4-name">今日检验</div>
						</div>

						<div class="m-c-clumn">
							<div class="m-c-data" id="m-ds5-num"
								onclick="clickCount(this.id)" style="cursor: pointer">40</div>
							<div class="m-c-tit" id="m-ds5-name">今日打印</div>
						</div>
					</div>
				</div>
			</div>
			<div class="m-a-boxes">
				<div class="m-a-boxes-tit" id="ydbfx">月收入对比分析</div>
				<div id="m-a-main-1" class="m-a-main">
					<div id="main1" style="width: 100%; height: 100%;"></div>
				</div>
				<div class="m-a-pl1"></div>
				<div class="m-a-pl2"></div>
				<div class="m-a-pr1"></div>
				<div class="m-a-pr2"></div>
				<div class="m-a-beij"></div>
				<div class="m-a-border"></div>
			</div>
			<div class="m-a-boxes">
				<div class="m-a-boxes-tit" id="mtdbfx">收入周报</div>
				<div id="m-a-main-2" class="m-a-main">
					<div id="main2" style="width: 100%; height: 100%;"></div>
					<!-- <div id="chart3" class="chart-panel" style="background:url(images/chart3.png) no-repeat center center; background-size: contain; "></div> -->
				</div>
				<div class="m-a-pl1"></div>
				<div class="m-a-pl2"></div>
				<div class="m-a-pr1"></div>
				<div class="m-a-pr2"></div>
				<div class="m-a-beij"></div>
				<div class="m-a-border"></div>
			</div>
		</div>
		<div class="m-area-right">
			<div class="m-a-boxes">
				<div class="m-a-boxes-tit" id="bmryqk">人员持证比例</div>
				<div id="m-a-main-3" class="m-a-main">
					<div id="main3" style="width: 100%; height: 100%;"></div>
				</div>
				<div class="m-a-pl1"></div>
				<div class="m-a-pl2"></div>
				<div class="m-a-pr1"></div>
				<div class="m-a-pr2"></div>
				<div class="m-a-beij"></div>
				<div class="m-a-border"></div>
			</div>
			<div class="m-a-boxes">
				<div class="m-a-boxes-tit" id="qxj">报告书出具情况</div>
				<div class="m-a-main" id="m-r-list-tab">
					<div class="m-r-list-tab">
						<div class="ta-list-head">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td id="qxj1">报告编号</td>
									<td id="qxj2">企业名称</td>
									<td id="qxj3">日期</td>
									<td id="qxj4">检验结论</td>
								</tr>
							</table>
						</div>
						<div class="ta-list-cont" id="demohq_a" style="overflow: hidden;">
							<div id="list1" class="table-list1">
								<ul>
									<li>
										<div>
											<div>
												<span>简阳市</span>
											</div>
										</div>
										<div>
											<div>
												<span>简阳三好农牧科技有限公司</span>
											</div>
										</div>
										<div>
											<div>
												<span>02-18</span>
											</div>
										</div>
										<div>
											<div>
												<span><span style="color: #FFFF33">合格</span></span>
											</div>
										</div>
									</li>
									<li>
										<div>
											<div>
												<span>邛崃市</span>
											</div>
										</div>
										<div>
											<div>
												<span>金利养猪场</span>
											</div>
										</div>
										<div>
											<div>
												<span>02-18</span>
											</div>
										</div>
										<div>
											<div>
												<span><span style="color: #FFFF33">合格</span></span>
											</div>
										</div>
									</li>
									<li>
										<div>
											<div>
												<span>金堂县</span>
											</div>
										</div>
										<div>
											<div>
												<span>金堂县星洋水产养殖专业合作社</span>
											</div>
										</div>
										<div>
											<div>
												<span>02-10</span>
											</div>
										</div>
										<div>
											<div>
												<span><span style="color: #FFFF33">合格</span></span>
											</div>
										</div>
									</li>
									<li>
										<div>
											<div>
												<span>新都区</span>
											</div>
										</div>
										<div>
											<div>
												<span>曾章洪奶牛养殖场</span>
											</div>
										</div>
										<div>
											<div>
												<span>02-07</span>
											</div>
										</div>
										<div>
											<div>
												<span><span style="color: #FFFF33">合格</span></span>
											</div>
										</div>
									</li>
									<li>
										<div>
											<div>
												<span>邛崃市</span>
											</div>
										</div>
										<div>
											<div>
												<span>成都市稼兴种植专业合作社</span>
											</div>
										</div>
										<div>
											<div>
												<span>02-12</span>
											</div>
										</div>
										<div>
											<div>
												<span><span style="color: #33FF99">合格</span></span>
											</div>
										</div>
									</li>
									<li>
										<div>
											<div>
												<span>金堂县</span>
											</div>
										</div>
										<div>
											<div>
												<span>金堂县广兴镇德安养殖场</span>
											</div>
										</div>
										<div>
											<div>
												<span>02-12</span>
											</div>
										</div>
										<div>
											<div>
												<span><span style="color: #FFFF33">合格</span></span>
											</div>
										</div>
									</li>
									<li>
										<div>
											<div>
												<span>邛崃市</span>
											</div>
										</div>
										<div>
											<div>
												<span>成都市稼兴种植专业合作社</span>
											</div>
										</div>
										<div>
											<div>
												<span>02-12</span>
											</div>
										</div>
										<div>
											<div>
												<span><span style="color: #33FF99">合格</span></span>
											</div>
										</div>
									</li>
									<li>
										<div>
											<div>
												<span>金堂县</span>
											</div>
										</div>
										<div>
											<div>
												<span>金堂县广兴镇德安养殖场</span>
											</div>
										</div>
										<div>
											<div>
												<span>02-12</span>
											</div>
										</div>
										<div>
											<div>
												<span><span style="color: #FFFF33">合格</span></span>
											</div>
										</div>
									</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
				<div class="m-a-pl1"></div>
				<div class="m-a-pl2"></div>
				<div class="m-a-pr1"></div>
				<div class="m-a-pr2"></div>
				<div class="m-a-beij"></div>
				<div class="m-a-border"></div>
			</div>
			<div class="m-a-boxes">
				<div class="m-a-boxes-tit">财务情况</div>
				<div class="m-a-main" id="m-r-list-tab1">
					<div class="m-r-list-tab">
						<div class="ta-list-head">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td>姓名</td>
									<td>报账类型</td>
									<td>日期</td>
									<td>结论</td>
								</tr>
							</table>
						</div>
						<div class="ta-list-cont2" id="demohq_b" style="overflow: hidden;">
							<div id="list2" class="table-list1">
								<ul>
									<li>
										<div>
											<div>
												<span>张三</span>
											</div>
										</div>
										<div>
											<div>
												<span>差旅费</span>
											</div>
										</div>
										<div>
											<div>
												<span>02-18</span>
											</div>
										</div>
										<div>
											<div>
												<span><span style="color: #33FF99">通过</span></span>
											</div>
										</div>
									</li>
									<li>
										<div>
											<div>
												<span>李四</span>
											</div>
										</div>
										<div>
											<div>
												<span>差旅费</span>
											</div>
										</div>
										<div>
											<div>
												<span>02-18</span>
											</div>
										</div>
										<div>
											<div>
												<span><span style="color: #FFFF33">不通过</span></span>
											</div>
										</div>
									</li>
									<li>
										<div>
											<div>
												<span>金堂</span>
											</div>
										</div>
										<div>
											<div>
												<span>培训费</span>
											</div>
										</div>
										<div>
											<div>
												<span>02-10</span>
											</div>
										</div>
										<div>
											<div>
												<span><span style="color: #33FF99">通过</span></span>
											</div>
										</div>
									</li>
									<li>
										<div>
											<div>
												<span>李四</span>
											</div>
										</div>
										<div>
											<div>
												<span>差旅费</span>
											</div>
										</div>
										<div>
											<div>
												<span>02-18</span>
											</div>
										</div>
										<div>
											<div>
												<span><span style="color: #FFFF33">不通过</span></span>
											</div>
										</div>
									</li>
									<li>
										<div>
											<div>
												<span>李四</span>
											</div>
										</div>
										<div>
											<div>
												<span>差旅费</span>
											</div>
										</div>
										<div>
											<div>
												<span>02-18</span>
											</div>
										</div>
										<div>
											<div>
												<span><span style="color: #FFFF33">不通过</span></span>
											</div>
										</div>
									</li>
									<li>
										<div>
											<div>
												<span>李四</span>
											</div>
										</div>
										<div>
											<div>
												<span>差旅费</span>
											</div>
										</div>
										<div>
											<div>
												<span>02-18</span>
											</div>
										</div>
										<div>
											<div>
												<span><span style="color: #FFFF33">不通过</span></span>
											</div>
										</div>
									</li>
									<li>
										<div>
											<div>
												<span>李四</span>
											</div>
										</div>
										<div>
											<div>
												<span>差旅费</span>
											</div>
										</div>
										<div>
											<div>
												<span>02-18</span>
											</div>
										</div>
										<div>
											<div>
												<span><span style="color: #FFFF33">不通过</span></span>
											</div>
										</div>
									</li>
									<li>
										<div>
											<div>
												<span>李四</span>
											</div>
										</div>
										<div>
											<div>
												<span>差旅费</span>
											</div>
										</div>
										<div>
											<div>
												<span>02-18</span>
											</div>
										</div>
										<div>
											<div>
												<span><span style="color: #FFFF33">不通过</span></span>
											</div>
										</div>
									</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
				<div class="m-a-pl1"></div>
				<div class="m-a-pl2"></div>
				<div class="m-a-pr1"></div>
				<div class="m-a-pr2"></div>
				<div class="m-a-beij"></div>
				<div class="m-a-border"></div>
			</div>
		</div>

		<div class="m-area-top">

			<div class="m-a-tit">机电设备检验一部</div>
			<div class="manysys" id="manysys"
				style="left: 200px !important; top: 10px; height: 80px;">
				<div class="sys-text" id="sys-text"></div>
				<div class="sys-data" id="sys-data"
					style="overflow-y: auto; height: 600px; width: 300px">
					<div class="msjt"></div>
					<ul id="bmxz">
					</ul>
				</div>
			</div>
			<div class="m-top-l-img"></div>
			<div class="m-top-r-img"></div>

			<div class="tjcount">

				<div class="date_year">
					<span id="date_year">2017-12</span>
					<div class="m-a-pl1"></div>
					<div class="m-a-pl2"></div>
					<div class="m-a-pr1"></div>
					<div class="m-a-pr2"></div>
					<div class="m-a-beij"></div>
					<div class="m-a-border"></div>
				</div>
				<div class="date_money">
					<span>今日收款:</span> <span class="today_data">24,566</span>
				</div>
				<div></div>

			</div>




		</div>
	</div>
	<div id="tccontent_v4">
		<div id="light_v4" class="white_content md-show">
			<div class="close">
				<a id="t-close-btn-v4" class="iconfont icon-esc"
					href="javascript:void(0)" title="关闭">x</a>
			</div>
			<div class="pro_head">
				<div class="pro_bod">
					<ul class="flow">
						<li class="first">
							<div class="f-box">
								<span></span>
							</div>
							<div class="x-tt">开始</div>
						</li>
						<li>
							<div class="f-box">
								<span></span>
							</div>
							<div class="x-tt">任务分配</div>
						</li>
						<li>
							<div class="f-box">
								<span></span>
							</div>
							<div class="x-tt">报告录入</div>
						</li>
						<li>
							<div class="f-box ">
								<span></span>
							</div>
							<div class="x-tt">报告审核</div>
						</li>
						<li>
							<div class="f-box ">
								<span></span>
							</div>
							<div class="x-tt ">报告签发</div>
						</li>
						<li>
							<div class="f-box">
								<span></span>
							</div>
							<div class="x-tt">报告领取</div>
						</li>
						<li>
							<div class="f-box">
								<span></span>
							</div>
							<div class="x-tt">报告归档</div>
						</li>
						<li class="last">
							<div class="f-box">
								<span></span>
							</div>
							<div class="x-tt">结束</div>
						</li>
					</ul>
					<div id="drCode" class="st1">注册代码：21335550000442</div>
					<div id="qrCode" class="st1" style="margin-top: 5px;">二维码：21335550000442</div>
				</div>
				<div class="pro_bg"></div>
			</div>
			<div class="tankuang">
				<!-- 流程 -->
				<div class="progress">
					<div class="ywlsh">
						<table border="0" cellpadding="0" cellspacing="0" width=""
							height="" align="center" class="ywlc_s">
							<tbody>
								<tr class="d_tr">
									<td align="center">
										<div class="st2">业务流水号：212542</div>
										<div class="clear">
											<div class="show0">
												<div class="show1">操作步骤: 报告录入</div>
												<div class="show1">操作人员: 王仁生</div>
												<div class="show1">操作时间: 2017-12-07 16:49:11.0</div>
												<div class="show1">操作说明: 从【报告录入】环节进入【报告送审】环节。</div>
											</div>
											<div class="jt1">
												<img src="app/gis/scjy/v1/images/jt2.png">
											</div>
											<div class="show0">
												<div class="show1">操作步骤: 报告送审</div>
												<div class="show1">操作人员: 王仁生</div>
												<div class="show1">操作时间: 2017-12-11 15:31:15.0</div>
												<div class="show1">操作说明: 从【报告送审】环节进入【报告审批】环节。</div>
											</div>
										</div>

										<div class="clear">
											<div class="show0">
												<div class="show1">操作步骤: 【报告送审】环节提交</div>
												<div class="show1">操作人员: 王仁生</div>
												<div class="show1">操作时间: 2017-12-11 15:31:15.0</div>
												<div class="show1">操作说明: 提交至邹益平</div>
											</div>
											<div class="jt1">
												<img src="app/gis/scjy/v1/images/jt2.png">
											</div>
											<div class="show0">
												<div class="show1">操作步骤: 报告审批</div>
												<div class="show1">操作人员: 邹益平</div>
												<div class="show1">操作时间: 2017-12-12 11:37:52.0</div>
												<div class="show1">操作说明: 从报告审批环节进入打印报告环节。结论：通过无</div>
											</div>
										</div>

										<div class="clear">
											<div class="show0">
												<div class="show1">操作步骤: 打印报告</div>
												<div class="show1">操作人员: 王川</div>
												<div class="show1">操作时间: 2017-12-20 14:57:35.0</div>
												<div class="show1">操作说明: 打印报告</div>
											</div>
											<div class="jt1">
												<img src="app/gis/scjy/v1/images/jt2.png">
											</div>
											<div class="show0">
												<div class="show1">操作步骤: 打印报告</div>
												<div class="show1">操作人员: 王川</div>
												<div class="show1">操作时间: 2017-12-20 14:57:35.0</div>
												<div class="show1">操作说明: 从【打印报告】环节进入【报告领取】环节。</div>
											</div>
										</div>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="wtctbg"></div>
		</div>
		<div id="fade_v4" class="black_overlay" style="display: none;"></div>
	</div>
</body>
</html>
