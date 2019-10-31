<%@page import="util.ReportUtil"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String today = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="description" content="">
<meta name="keywords" content="">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<base href="<%=basePath%>">
<title>四川省特种设备检验大数据</title>

<link rel="stylesheet" type="text/css" href="app/gis/scjy/v2/css/stylegx.css" media="all" />
<link rel="stylesheet" type="text/css" href="app/gis/scjy/v2/css/animate.css" media="all" />
<link href="app/gis/scjy/v2/css/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css">
<script src="app/gis/scjy/v2/js/jquery.min.js"></script>
<script src="app/gis/scjy/v2/js/main.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=FDmrHK5fKK7ALGXCHRY4BMoT1ys8XO5U"></script> 
<!-- <script type="text/javascript" src="http://api.map.baidu.com/library/LuShu/1.2/src/LuShu_min.js"></script> -->
<script type="text/javascript" src="http://api.map.baidu.com/library/AreaRestriction/1.2/src/AreaRestriction_min.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/library/GeoUtils/1.2/src/GeoUtils_min.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/library/TextIconOverlay/1.2/src/TextIconOverlay_min.js"></script>
<script type="text/javascript" src="app/gis/scjy/v2/map/run.js"></script>
<script type="text/javascript" src="app/gis/scjy/v2/map/AddLushu.js"></script>
<script type="text/javascript" src="app/gis/scjy/v2/map/MarkerClusterer_min.js"></script>
<script type="text/javascript" src="app/gis/scjy/v2/map/json.js"></script>
<script type="text/javascript" src="app/gis/scjy/v2/map/initQuery.js"></script>
<script type="text/javascript" src="app/gis/scjy/v2/map/clientQuery.js"></script>
<script type="text/javascript" src="app/gis/scjy/v2/map/rtQuery.js"></script>
<script type="text/javascript" src="app/gis/scjy/v2/map/wxQuery.js"></script>
<script type="text/javascript" src="app/gis/scjy/v2/map/map.js"></script>
<script type="text/javascript" src="app/gis/scjy/v2/map/MyOverlay.js"></script>
<script type="text/javascript" src="app/gis/scjy/v2/map/SmallMap.js"></script>
<style type="text/css">
     #miBackground{
     	position: absolute;
     	left: 0;
     	top: 0;
     	width: 100%;
     	height: 100%;
     	background: url(/app/gis/scjy/v2/images/bg.jpg);
     }
     .anchorBL{
            	display: none !important;
            }	
</style>
<script type="text/javascript">
var today = '<%=today%>';
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
	<div id="loading" style="display:none">
		<div class="loading" > 
		 <iframe src="app/gis/scjy/v2/svg.html" frameborder="0" width="300" height="300"></iframe>
		</div>
		<div  class="black2" ></div>
	</div>

	<div id="mapv1" class="data-box1 clearfix smap_container">
		<div class="small_map_close">
			<a class="iconfont icon-esc" href="javascript:void(0)" title="关闭">x</a>
		</div>
		<div class="small_map_bg"></div>
		<div id="smap1"></div>
		<i class="topL"></i>
		<i class="topR"></i>
		<i class="bottomL"></i>
		<i class="bottomR"></i>
		<div class="small_map_desc"></div>
	</div>
	<div id="mapv2" class="data-box1 clearfix smap_container">
		<div class="small_map_close">
			<a class="iconfont icon-esc" href="javascript:void(0)" title="关闭">x</a>
		</div>
		
		<div class="small_map_bg"></div>
		<div id="smap2"></div>
		<i class="topL"></i>
		<i class="topR"></i>
		<i class="bottomL"></i>
		<i class="bottomR"></i>
		<div class="small_map_desc"></div>
	</div>
	<div id="mapv3" class="data-box1 clearfix smap_container">
		<div class="small_map_close">
			<a class="iconfont icon-esc" href="javascript:void(0)" title="关闭">x</a>
		</div>
		<div class="small_map_bg"></div>
		<div id="smap3"></div>
		<i class="topL"></i>
		<i class="topR"></i>
		<i class="bottomL"></i>
		<i class="bottomR"></i>
		<div class="small_map_desc"></div>
	</div>
	<div id="mapv4" class="data-box1 clearfix smap_container">
		<div class="small_map_close">
			<a class="iconfont icon-esc" href="javascript:void(0)" title="关闭">x</a>
		</div>
		<div class="small_map_bg"></div>
		<div id="smap4"></div>
		<i class="topL"></i>
		<i class="topR"></i>
		<i class="bottomL"></i>
		<i class="bottomR"></i>
		<div class="small_map_desc"></div>
	</div>
	<div id="mapv5" class="data-box1 clearfix smap_container">
		<div class="small_map_close">
			<a class="iconfont icon-esc" href="javascript:void(0)" title="关闭">x</a>
		</div>
		<div class="small_map_bg"></div>
		<div id="smap5"></div>
		<i class="topL"></i>
		<i class="topR"></i>
		<i class="bottomL"></i>
		<i class="bottomR"></i>
		<div class="small_map_desc"></div>
	</div>
	<div id="mapv6" class="data-box1 clearfix smap_container">
		<div class="small_map_close">
			<a class="iconfont icon-esc" href="javascript:void(0)" title="关闭">x</a>
		</div>
		<div class="small_map_bg"></div>
		<div id="smap6"></div>
		<i class="topL"></i>
		<i class="topR"></i>
		<i class="bottomL"></i>
		<i class="bottomR"></i>
		<div class="small_map_desc"></div>
	</div>
	<!-- 滚动字幕 -->
	<div id="scroll_box">
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
	<!--总的统计数据-->
	<div class="m-counts">
		<div class="m_counts_bg">
			<div class="m-c-clumn">
				<div class="m-c-icon">
					<img src="app/gis/scjy/v2/images/in_ico01.png">
				</div>
				<div class="m-c-tit">今年报检</div>
				<div class="m-c-data fco1" id="m-ds1-num">0</div>
			</div>
			<div class="m-c-clumn">
				<div class="m-c-icon">
					<img src="app/gis/scjy/v2/images/in_ico02.png">
				</div>
				<div class="m-c-tit">昨日检验</div>
				<div class="m-c-data fco2" id="m-ds2-num">0</div>
			</div>
			<div class="m-c-clumn">
				<div class="m-c-icon">
					<img src="app/gis/scjy/v2/images/in_ico03.png">
				</div>
				<div class="m-c-tit">今日检验</div>
				<div class="m-c-data fco3" id="m-ds3-num">0</div>
			</div>
			<div class="m-c-clumn">
				<div class="m-c-icon">
					<img src="app/gis/scjy/v2/images/wx2.png">
				</div>
				<div class="m-c-tit">微信查询</div>
				<div class="m-c-data fco4" id="m-ds0-num">0</div>
			</div>
		</div>
	</div>
	<!-- 	add-end -->
	<div id="sysMain" class="sysmain systongji">
		<div class="s-skin-container s-skin-container-default"></div>
		<div id="systemTitle" class="m-top-logo">
			<div class="m-top-logo-txt">
				<span id="systemTitleText" class="text1">
					<span class="m-top-logo-img">
						<img src="app/gis/scjy/v2/images/tjy_lo.png">
					</span>四川省特检院智慧特检大数据-D1B
				</span>
			</div>
			<div class="tj-logo-bg"></div>
		</div>
		<div id="map" class="main-map" style="height: 100%;"></div>
	</div>
<script type="text/javascript" src = 'app/gis/scjy/v2/js/nav.js'></script>
<script>
/**
 * 右侧滚动信息
 */
var scroll_height = 600;      // 一个完整滚动条的长度
var scroll_x = 0;//偏移量
var scroll_t;//定时器
$(function(){
	 var $col1 = $("#scroll_box #col1")[0];
	 $("#scroll_box #col2").html($("#scroll_box #col1").html());
     var $col2 = $("#scroll_box #col2")[0];

     var fun = function(){
         $col1.style.top = scroll_x + 'px';
         $col2.style.top = (scroll_x + scroll_height) + 'px';
         scroll_x -= 4;
         if( (scroll_x + scroll_height) == 0 ){
        	 scroll_x = 0;
         }
     };
     scroll_t = setInterval(fun,50);
     
     function chain(){
   		clearInterval(scroll_t);
   		$.post("gis/device/timer.do",{},function(res){
   			scroll_t = setInterval(fun,50);
   			setTimeout(chain,5000+Math.floor(Math.random()*5000));
   		});
   	 }
     setTimeout(chain, 10000);
     //事件注册
   /*   var $box = $("#scroll_box")[0];
     $box.onmouseover = function(){
    	 clearInterval(scroll_t);
     }
     $box.onmouseout = function(){
    	 scroll_t = setInterval(fun,100);
     } */
});
function changeScrollContent(str){
	var arr = $("#scroll_box #col1 div").get();
	var flag = 0;
	for(var i=0;i<arr.length;i++){
		if($(arr[i]).hasClass("chg")){
			flag = i;
		}
	}
	$(arr).removeClass("chg");
	if(flag == arr.length-1){
		$(arr[0]).addClass("chg").html(str);
	}else{
		$(arr[flag+1]).addClass("chg").html(str);
	}
	$("#scroll_box #col2").html($("#scroll_box #col1").html());
}
</script>
</body>
</html>
