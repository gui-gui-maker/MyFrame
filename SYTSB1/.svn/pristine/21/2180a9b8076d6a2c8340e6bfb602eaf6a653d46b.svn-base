<%@page import="com.khnt.rbac.bean.User"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="util.ReportUtil"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
User user =SecurityUtil.getSecurityUser().getSysUser();

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<%@include file="/k/kui-base-list.jsp"%>
<title>四川省特种设备检验大数据</title>
<meta name="description" content="">
<meta name="keywords" content="">
<link rel="stylesheet" type="text/css"
	href="app/gis/scjy/v1/css/stylegx.css" media="all" />
<link rel="stylesheet" type="text/css"
	href="app/gis/scjy/v1/css/animate.css" media="all" />
<link href="app/gis/scjy/v1/css/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css">
<link href="app/gis/scjy/v1/css/jqcloud.css" rel="stylesheet" type="text/css">
<style type="text/css">
#miBackground {
	position: absolute;
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	background: url(/app/gis/scjy/v1/images/bg.jpg);
}

.anchorBL {
	display: none !important;
}
.wxewm {
	position: fixed;
    z-index: 100000;
    right: 0px;
    bottom: 0px; 
    /*  background:#123E9C;  */
    text-align:center;
    width:270px;
}

.wxcon { 
	width: 260px;
    /* margin: 0 auto; */
    overflow: hidden;
    padding:10px 0;
}
.wxewm span {
	display:block;
	float:left;
	margin: 0 10px 0 0;
}    
.wxewm img {      
	width: 140px;
    height: 140px;
    vertical-align: middle;
}
.gzhname { 
	width: 75px; 
	font-size:16px;
	color:#fff; 
	padding:0 5px;
	padding-top:35px; 
}
.append_div {
	background: black;color:#FFFFFF; opacity:0.5; text-align: center; position: absolute; height: 100px; width: 80px; 
}


/* 流程 */
.stepInfo{position:relative;height:70%; margin-left: 60px; margin-top: 30px;}
.stepInfo ul { height: 100%;}
.stepInfo li{ height:12.2%;width:2px;background:#fff; list-style: none; position: relative;}
.stepIco{text-align:center;position:absolute; }
.stepIco i { display: block; height: 36px; width: 36px;;border-radius:36px;background:#fff; padding: 6px; float: left; } 
.stepText{color:#fff;font-size:14px; font-weight: bold; float: right; margin-top: 8px; margin-left: 10px;}
.stepIco1{top:-2%;left:-18px; }
.stepIco2{top:10%;left:-18px; }
.stepIco3{top:22%;left:-18px; }
.stepIco4{top:34%;left:-18px; }
.stepIco5{top:46%;left:-18px; }
.stepIco6{top:58%;left:-18px; }
.stepIco7{top:70%;left:-18px; }
.stepIco8{top:82%;left:-18px; }
.stepIco9{top:94%;left:-18px; }

.stepIco1 i {background:#fff url("app/gis/scjy/v1/images/lc_01b.png") no-repeat center center;}
.stepIco2 i {background:#fff url("app/gis/scjy/v1/images/lc_02b.png") no-repeat center center;}
.stepIco3 i {background:#fff url("app/gis/scjy/v1/images/lc_03b.png") no-repeat center center;}
.stepIco4 i {background:#fff url("app/gis/scjy/v1/images/lc_04b.png") no-repeat center center;}
.stepIco5 i {background:#fff url("app/gis/scjy/v1/images/lc_05b.png") no-repeat center center;}
.stepIco6 i {background:#fff url("app/gis/scjy/v1/images/lc_06b.png") no-repeat center center;}
.stepIco7 i {background:#fff url("app/gis/scjy/v1/images/lc_07b.png") no-repeat center center;}
.stepIco8 i {background:#fff url("app/gis/scjy/v1/images/lc_08b.png") no-repeat center center;}
.stepIco9 i {background:#fff url("app/gis/scjy/v1/images/lc_09b.png") no-repeat center center;}

.stepIco1 i.active {background:#0090ff url("app/gis/scjy/v1/images/lc_01.png") no-repeat center center;}
.stepIco2 i.active {background:#0090ff url("app/gis/scjy/v1/images/lc_02.png") no-repeat center center;}
.stepIco3 i.active {background:#0090ff url("app/gis/scjy/v1/images/lc_03.png") no-repeat center center;}
.stepIco4 i.active {background:#0090ff url("app/gis/scjy/v1/images/lc_04.png") no-repeat center center;}
.stepIco5 i.active {background:#0090ff url("app/gis/scjy/v1/images/lc_05.png") no-repeat center center;}
.stepIco6 i.active {background:#0090ff url("app/gis/scjy/v1/images/lc_06.png") no-repeat center center;}
.stepIco7 i.active {background:#0090ff url("app/gis/scjy/v1/images/lc_07.png") no-repeat center center;}
.stepIco8 i.active {background:#0090ff url("app/gis/scjy/v1/images/lc_08.png") no-repeat center center;}
.stepIco9 i.active {background:#0090ff url("app/gis/scjy/v1/images/lc_09.png") no-repeat center center;}

.stepInfo li.active { background:#247cfa; }
.bewm { color: #fff; font-size: 16px; text-align: center; }
.bewm .kbox {
	padding: 5px;
    background: #fff;
    width: 114px;
    height: 114px;
    margin: 10px auto;

}
.bewm .kbox #current_device_qrcode{ width: 104px; height: 104px;  display:block; }




/*箭头*/
.arrjt{ position:absolute;top:12px;left:-7px;z-index:999;-webkit-animation: start 4s 1s infinite ease-in-out;opacity:0;  }
@-webkit-keyframes start {
	0% {opacity: .5;-webkit-transform: translate(0,0);}
	20% {opacity:1;-webkit-transform: translate(0,1px);}
	50%,60%,70% {opacity: 1;-webkit-transform: translate(0,4px);}
	80% {opacity: 1;-webkit-transform: translate(0,20px);}
	90% {opacity: .5;-webkit-transform: translate(0,30px);}
	100% {opacity: 0;-webkit-transform: translate(0,30px);}
}
@-moz-keyframes start {
	0% {opacity: .5;-moz-transform: translate(0,0);}
	20% {opacity: 1;-moz-transform: translate(0,1px);}
	50%,60%,70% {opacity: 1;-moz-transform: translate(0,4px);}
	80% {opacity: 1;-moz-transform: translate(0,20px);}
	90% {opacity: .5;-moz-transform: translate(0,30px);}
	100% {opacity: 0;-moz-transform: translate(0,30px);}
}
@keyframes start {
	0% {opacity: .5;transform: translate(0,0);}
	20% {opacity: 1;transform: translate(0,1px);}
	50%,60%,70% {opacity: 1;transform: translate(0,4px);}
	80% {opacity: 1;transform: translate(0,20px);}
	90% {opacity: .5;transform: translate(0,30px);}
	100% {opacity: 0;transform: translate(0,30px);}
}
</style>
<script type="text/javascript" src="app/gis/scjy/v1/js/qrcode.min.js"></script>
<script src="app/gis/scjy/v1/js/main.js"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=FDmrHK5fKK7ALGXCHRY4BMoT1ys8XO5U"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/library/AreaRestriction/1.2/src/AreaRestriction_min.js"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/library/GeoUtils/1.2/src/GeoUtils_min.js"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/library/TextIconOverlay/1.2/src/TextIconOverlay_min.js"></script>
<script type="text/javascript" src="app/gis/scjy/v1/map/run.js"></script>
<script type="text/javascript"
	src="app/gis/scjy/v1/map/MarkerClusterer_min.js"></script>
<script type="text/javascript" src="app/gis/scjy/v1/map/json.js"></script>
<script type="text/javascript" src="app/gis/scjy/v1/map/initQuery.js"></script>
<script type="text/javascript" src="app/gis/scjy/v1/map/clientQuery.js"></script>
<script type="text/javascript" src="app/gis/scjy/v1/map/rtQuery.js"></script>
<script type="text/javascript" src="app/gis/scjy/v1/map/wxQuery.js"></script>
<script type="text/javascript" src="app/gis/scjy/v1/map/map.js"></script>
<script type="text/javascript" src="app/gis/scjy/v1/map/MyOverlay.js"></script>
<script type="text/javascript" src="app/gis/scjy/v1/map/SmallMap.js"></script>
<script type="text/javascript" src="app/gis/scjy/v1/js/jqcloud-1.0.4.js"></script>
<script type="text/javascript" src='app/gis/scjy/v1/js/nav.js'></script>
<script type="text/javascript" src="app/gis/scjy/v1/map/hot.js"></script>

<script type="text/javascript">
var current_device_qrcode = false;

$(function(){
 	$(".livemap-hostname").mouseover(function(){
		$(".white_content").addClass("md-show");
		$("#light,#tccontent").show();
		var ligh=$(window).height()*0.8-155;
		$(".tankuang").css({"height":ligh+"px"});
		$("#fade").show();
		var oneExe1=setTimeout(function(){
			$(".livemap-hostname").addClass("hover");
		},1);
	});  
	
	$("#fade,#t-close-btn").click(function(){
		$("#light,#tccontent,#fade").hide();
		$("div.livemap-mark").removeClass("hover");
	});

	$("#light").mouseleave(function(){
		$("#light,#tccontent,#fade").hide();
		$("div.livemap-mark").removeClass("hover");
	}); 
	
}); 
function closeFlowWindow(){
	$("#light,#tccontent,#fade").hide();
	$("div.livemap-mark").removeClass("hover");
}
/*
 * 打开加载等待图层
 */
function openLoading()
{
	$("#loading").show();
}
//关闭加载等待图层
function closeLoading()
{
	$("#loading").hide();
	
} 
</script>
<!--[if IE 8]>
<link rel="stylesheet" type="text/css" href="css/style-ie8.css" media="all" />
<![endif]-->



</head>
<body>
	<div id="loading" style="display: none">
		<div class="loading">

			<iframe src="app/gis/scjy/v1/svg.html" frameborder="0" width="300"
				height="300"></iframe>

		</div>
		<div class="black2"></div>
	</div>
	<div id="tccontent">
		<div id="light" class="white_content md-show">
			<div class="close">
				<a id="t-close-btn" class="iconfont icon-esc"
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
		<div id="fade" class="black_overlay" style="display: none;"></div>
	</div>
	<div id="tccontent2">
		<div class="small_map_close">
			<a class="iconfont icon-esc" href="javascript:void(0)" title="关闭">x</a>
		</div>
		<div id="small_map"></div>
	</div>
	<div id="sysMain" class="sysmain systongji">
	 <div class="wxewm">
	   <div class="wxcon"><span><img src="app/gis/scjy/v1/images/sctj.jpg" ></span> 
	   <span class="gzhname">四川省特检院微信公众平台</span>	</div>
	  
	 </div>
	
	
		<!-- nav-left -->
		<div class="nav ">
			<div style="height:80%;">
				<div class="nav-top">
					<i></i><span>当前报告流程</span>
				</div>
				<div id="stepInfo" class="stepInfo">
					<ul id="report_flow_info">
						<li name="设备报检" class="active"></li>
						<li name="任务分配" class="active"></li>
						<li name="报告录入" class="active"></li>
						<li name="报告审核" class=""> <span class="arrjt"><img src="app/gis/scjy/v1/images/lc_jt.png"></span></li>
						<li name="报告签发" class=""></li>
						<li name="报告盖章" class=""></li>
						<li name="报告打印" class=""></li>
						<li name="报告领取" class=""></li>
					</ul>
					<div class="stepIco stepIco1 ">
						<i class="active"></i>
						<div class="stepText">设备报检</div>
					</div>
					
					<div class="stepIco stepIco2 ">
						<i class="active"></i>
						<div class="stepText">任务分配</div>
					</div>
					
					<div class="stepIco stepIco3 "><i class="active"></i>
						<div class="stepText">报告录入</div>
					</div>
					<div class="stepIco stepIco4 "><i class="active"></i>
						<div class="stepText">报告审核</div>
					</div>
					
					<div class="stepIco stepIco5 "><i class=""></i>
						<div class="stepText">报告签发</div>
					</div>
					
					<div class="stepIco stepIco6 "><i class=""></i>
						<div class="stepText">报告盖章</div>
					</div>

					<div class="stepIco stepIco7 "><i class=""></i>
						<div class="stepText">报告打印</div>
					</div>

					<div class="stepIco stepIco8 "><i class=""></i>
						<div class="stepText">报告领取</div>
					</div>

					<div class="stepIco stepIco9 "><i class=""></i>
						<div class="stepText">结束</div>
					</div>					
				</div>
				<div class="bewm">
					<div id="qrcode_box" class="kbox"><div id="current_device_qrcode"></div></div>
					<div id="current_company_name">使用单位</div>
					<div id="report_sn_box">报告编号：<span id="report_sn">CO-TD201636077</span></div>
				</div>
			</div>
			<div class="current_data" style="margin-top:10px;height:20%;">
				<!-- <div class="current_data_header">
					<i></i><span>实时数据</span>
				</div> -->
				<div id="tzgglist" class="tzgglist" style="overflow: hidden;">
					<div class="col1" id="notice_col1">
						<div id="nowbaogao" ></div>
						<div id="nowweixin" ></div>
						<!-- <div class="maxtt"></div>
						<div class="maxtt"></div>
						<div class="maxtt"></div>
						<div class="maxtt"></div>
						<div class="maxtt"></div>
						<div class="maxtt"></div> -->
					</div>
					<div class="col2" id="notice_col2"></div>
				</div>
			</div>
		</div>
		<div class="s-skin-container s-skin-container-default"></div>
		<div id="systemTitle" class="m-top-logo">
			<div class="m-top-logo-txt">
				<span id="systemTitleText" class="text1"> <span
					class="m-top-logo-img"> <img
						src="app/gis/scjy/v1/images/tjy_lo.png">
				</span>四川省特检院智慧特检大数据-D1A
				</span>
			</div>
			<div class="tj-logo-bg"></div>
		</div>
		<div id="map" class="main-map" style="height: 100%;"></div>
		<!--总的统计数据-->
		<div class="m-counts">
			<div class="m_counts_bg">
				<div class="m-c-clumn">
					<div class="m-c-icon">
						<img src="app/gis/scjy/v1/images/in_ico01.png">
					</div>
					<div class="m-c-tit">今年报检</div>
					<div class="m-c-data fco1" id="m-ds1-num">0</div>
				</div>
				<div class="m-c-clumn">
					<div class="m-c-icon">
						<img src="app/gis/scjy/v1/images/in_ico02.png">
					</div>
					<div class="m-c-tit">昨日检验</div>
					<div class="m-c-data fco2" id="m-ds2-num">0</div>
				</div>
				<div class="m-c-clumn">
					<div class="m-c-icon">
						<img src="app/gis/scjy/v1/images/in_ico03.png">
					</div>
					<div class="m-c-tit">今日检验</div>
					<div class="m-c-data fco3" id="m-ds3-num">0</div>
				</div>
				<div class="m-c-clumn">
					<div class="m-c-icon">
						<img src="app/gis/scjy/v1/images/in_ico04.png">
					</div>
					<div class="m-c-tit">微信查询</div>
					<div class="m-c-data fco4" id="m-ds0-num">0</div>
				</div>
				<div id="scroll_box" class="m-c-clumn">
					<div class="col1" id="col1">
						<div></div>
						<div></div>
						<div></div>
						<div></div>
						<div></div>
						<div></div>
						<div></div>
						<div></div>
						<div></div>
						<div></div>
						<div></div>
						<div></div>
						<div></div>
						<div></div>
						<div></div>
						<div></div>
						<div></div>
						<div></div>
						<div></div>
						<div></div>
					</div>
					<div class="col2" id="col2"></div>
				</div>
			</div>
		</div>
		<div class="J-global-toolbar">
			<div class="toolbar-wrap J-wrap toolbar-open">
				<div class="toolbar">
					<div class="toolbar-panels J-panel" style="height:83%;">
						<div style="visibility: hidden;"
							class="J-content toolbar-panel tbar-panel-cart toolbar-animate-out">

							<div class="serch_wrap" style="height:42%;">

								<div class="tit tbar-panel-header">
									<i></i> <span>报告查询</span>
									<!-- <span class="close-panel J-close"></span> -->
								</div>
								<div class="item_box">

									<div class="in_serform">
										<div style="display: none;">
											<span>区划： </span> <select id="area" name="area"
												style="width: 160px; height: 24px;">
											</select>
										</div>
										<br>
										<div>
											<span>设备代码： </span><input id="device_registration_code"
												name="device_registration_code" type="text" />
										</div>
										<br>
										<div>
											<span>金属二维码： </span><input id="device_qr_code"
												name="device_qr_code" type="text" />
										</div>
										<br>
										<div>
											<span>使用单位： </span><input id="company_name"
												name="company_name" type="text" />
										</div>
										<br>
										<div class="btns">
											<a href="javascript:void(0)" onclick="query();"
												class="btn btn-big bt1" style="color:#fff;">查询</a> 
											<a href="javascript:void(0)" onclick="clearQuery();" 
												class="btn btn-big btn-gray bt1" style="left:150px;color:#fff;">重置</a>
										</div>
									</div>
								</div>
							</div>

							<div id="my_favorite_latin_words"  class="yw_item" style="height:58%;overflow:hidden;">
								<!-- <div class="tit tbar-panel-header">
									<i></i> <span id="need_deal">历史查询 </span>
									<span class="close-panel J-close"></span>
								</div> -->
								<!-- <div class="item_box dcl_yw" 
									
									style="height: 90%;">
								</div> -->
							</div>
							<!-- <div class="yw_item" style="height:30%;"></div> -->
						</div>
						<div style="visibility: hidden;" data-name="follow"
							class="J-content toolbar-panel tbar-panel-follow">
							<h3 class="tbar-panel-header J-panel-header">
								<i></i> <em class="title">操作手册</em> <span
									class="close-panel J-close"></span>
							</h3>
							<div class="tbar-panel-main">
								<div class="czsc">1.通过设备注册代码查询：输入设备注册代码，点击查询按钮即可查询指定的设备并显示到地图相应的设备使用地址的位子。</div>
								<div class="czsc">2.通过金属二维码查询：输入设备金属二维码，点击查询按钮即可查询指定的设备并显示到地图相应的设备使用地址的位子。</div>
								<div class="czsc">3.通过使用单位查询：在使用单位栏输入使用单位名称，点击查询按钮，后台将通过模糊查询查询该单位的所有设备，
									并以列表的形式展示设备报检信息。</div>
								<div class="czsc">说明：查询条件至少输入一个才能查询，同时输入多个查询条件时，优先以使用单位查询，同时只输入设备注册代码和金属二维码时，
									后台以并且做处理，所以要通过设备注册代码或金属二维码查询时，只需输入其中一个。重置按钮清除所有已输入的条件。</div>
							</div>
						</div>
					</div>
					<div class="toolbar-header"></div>
					<!-- <div class="toolbar-tabs J-tab">
						<div class="toolbar-tab tbar-tab-cart tbar-tab-click-selected">
							<i class="tab-ico"></i> <em class="tab-text ">特检云</em>
						</div>
						<div class=" toolbar-tab tbar-tab-follow">
							<i class="tab-ico"></i> <em class="tab-text">操作手册</em>
						</div>
					</div> -->
					<div class="toolbar-mini"></div>
				</div>
				<div id="J-toolbar-load-hook"></div>
			</div>
		</div>
	</div>


</body>
</html>
