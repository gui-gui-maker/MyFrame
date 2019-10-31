<%@page import="java.util.Map"%>
<%@page import="util.ReportUtil"%>
<%@page import="java.util.HashMap"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>你的2016特检院大数据</title>
<!-- <embed src="app/statistics/weixin/music/music1.ac3" hidden="true" autostart="true" loop="true"> </embed> -->
<%@include file="/k/kui-base-list.jsp"%>
<meta http-equiv="cache-control" content="no-cache" />
<meta name="format-detection" content="telephone=no" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="description" content="luofanting.com.cn">  
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0"> 
<link rel="stylesheet" href="app/statistics/weixin/css/style.css">
<link rel="stylesheet" href="app/statistics/weixin/css/swiper.min.css">
<link rel="stylesheet" href="app/statistics/weixin/css/animate.css">
<link rel="stylesheet" href="app/statistics/weixin/css/animate.min.css">
<script src="app/statistics/js/echarts.js"></script>
<script src="app/statistics/weixin/js/china.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.2"></script>
<style>
*{
	margin:0;
	padding:0;
}
html,body{
	height:100%;
}
body{
	font-family:"microsoft yahei";
}
.swiper-container {
  /*  width: 320px;
    height: 480px;*/
	width: 100%;
    height: 100%;
	background:#000;

  
}  

.swiper-slide{
	width:100%;
	height:100%;
	background:url(upload/bg.jpg) no-repeat left top;
	background-size:100% 100%;

}
img{
	/*display:block;*/
}
.swiper-pagination-bullet {
width: 8px;
height: 8px;
background: #000;
opacity: .4;
}
.swiper-pagination-bullet-active {
	    background: #e77917;
opacity: 1;
}
@-webkit-keyframes start {
	0%,30% {opacity: 0;-webkit-transform: translate(10px,0);}
	40% {opacity: .5;-webkit-transform: translate(5px,0);}
	80% {opacity: 1;-webkit-transform: translate(0,0);}
	90% {opacity: .5;-webkit-transform: translate(-4px,0);}
	100% {opacity: 0;-webkit-transform: translate(-8px,0);}
}
@-moz-keyframes start {
	0%,30% {opacity: 0;-moz-transform: translate(10px,0);}
	40% {opacity: .5;-moz-transform: translate(5px,0);}
	80% {opacity: 1;-moz-transform: translate(0,0);}
	90% {opacity: .5;-moz-transform: translate(-4px,0);}
	100% {opacity: 0;-moz-transform: translate(-8px,0);}
}
@keyframes start {
	0%,30% {opacity: 0;transform: translate(10px,0);}
	40% {opacity: .5;transform: translate(5px,0);}
	80% {opacity: 1;transform: translate(0,0);}
	90% {opacity: .5;transform: translate(-4px,0);}
	100% {opacity: 0;transform: translate(-8px,0);}
}
.ani{
/*	position:absolute;*/
	}
.txt{
	position:absolute;
}
.array{
	position:absolute;z-index:999;-webkit-animation: start 1.5s infinite ease-in-out;
}
</style>

</head>

<body >
<div class="layout">

	<div class="swiper-container" id="swiper-container">
		
			<!-------------slide1----------------->
			<section class="swiper-slide bg01">
			
				<div class="container "   >
					<div style="margin-top: 200px;font-size: 20px;" align="center">抱歉，没有你的数据！</div>
				</div>
			
			</section>
		</div>

</div>




<script type="text/javascript" src="app/statistics/weixin/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="app/statistics/weixin/js/script.js"></script>
<script src="app/statistics/weixin/js/swiper.min.js"></script>
<script src="app/statistics/weixin/js/swiper.animate1.0.2.min.js"></script>


</body>

</html>

