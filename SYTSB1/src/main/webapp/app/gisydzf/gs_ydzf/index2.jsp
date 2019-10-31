<%@ page import="com.khnt.security.util.SecurityUtil" %>
<%@ page import="com.khnt.security.CurrentSessionUser" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    //CurrentSessionUser user = SecurityUtil.getSecurityUser();
    //String userid = user.getId();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>主体信息</title>
	<%@include file="/k/kui-base-form.jsp" %>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="x-ua-Compatible" content="ie=edge"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no"/>
	<style type="text/css">
		body, html,#mapDiv{width: 100%;height: 100%;overflow: hidden;margin:0;font-size:14px; font-family: "Microsoft Yahei", "黑体", "宋体";}
	</style>
	<link rel="stylesheet" type="text/css" href="app/gisydzf/css/style.css" media="all" />
	<link rel="stylesheet" type="text/css" href="app/gisydzf/css/TrafficControl_min.css"/>
	<link rel="stylesheet" type="text/css" href="app/gisydzf/css/DrawingManager_min.css"/>
	<link rel="stylesheet" type="text/css" href="app/gisydzf/css/SearchInfoWindow_min.css"/>
	<link rel="stylesheet" type="text/css"href="app/gisydzf/css/main_style.css" media="all" />
	<script type="text/javascript" src="app/gisydzf/js/areas.js"></script>
	<script type="text/javascript" src="app/gisydzf/js/echarts.min.js"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=CHez3CZ4U2fXRWykH0mtxQaeTULkn8Bp"></script>
	<script type="text/javascript" src="app/gisydzf/js/TrafficControl_min.js"></script>
	<script type="text/javascript" src="app/gisydzf/js/GeoUtils_min.js"></script>
	<script type="text/javascript" src="app/gisydzf/js/DrawingManager_min.js"></script>
	<script type="text/javascript" src="app/gisydzf/js/SearchInfoWindow_min.js"></script>
	<script type="text/javascript" src="app/gisydzf/js/TextIconOverlay_min.js"></script>
	<script type="text/javascript" src="app/gisydzf/js/MarkerClusterer_min.js"></script>
	<script type="text/javascript" src="app/gisydzf/js/pub.js"></script>
	<script type="text/javascript" src="app/gisydzf/gs_ydzf/star.js"></script>
    <script type="text/javascript" src="app/gisydzf/gs_ydzf/index.js"></script>
	<script type="text/javascript">
		var jsession = "";
	    //var searchData = "${param.userId}";
	    function f_toOpenVideo(devIdno){		
			getJsession();
			//获取设备位置等信息
			setMapCenter(devIdno);
			setInfoWindow(devIdno,jsession);
		}
    </script>
</head>
<body>
<div id="mapDiv"></div>
<div class="page_main">
	<div class="side_left2">
			<div class="mnbgone"></div>
			<div id="menu">
				<ul id="nav">
					<li class="mainlevel"><a href="javascript:void(0);"
						onclick="openVideoViewer();"
						class="xxx"><em class="i1"></em>打开视频</a> 
					</li>
				</ul>
			</div>
			<div class="shrinkLeft">
				<a href="javascript:void(0)" onclick="setShrinkLeft()"></a>
			</div>
		</div>
	<div class="side_right">
	</div>
	<div class="shrinkRight"><a href="javascript:void(0)" onclick="_pub_setShrinkRight()"></a></div>
</div>
	<div id="picView" class="picView" style="z-index: 999;border:1px solid #084b89;background-color: #ffffff">
		<div class="picClose" onclick="closeVideoViewer()" title="关闭"></div>
		<div id="c01" style="width: 50%; height: 100%; float: left;">
			<iframe id="leftVideo" style="height: 100%; width: 100%; border: 0;" scrolling="no"
				src=""></iframe>
		</div>
		<div id="c02"
			style="width: 50%; height: 100%; float: left; ">
			<iframe id="rightVideo" style="height: 100%; width: 100%; border: 0;" scrolling="no"
				src=""></iframe>
		</div>
	</div>
	<div id="chartView" class="chartView"><div class="picClose" onclick="closeChartViewer()" title="关闭"></div><div id="echartV" style="float:left;width:100%;height:100%;"></div><div id="echartV2" style="float:left;width:0%;height:100%;"></div></div>
<div id="spanel" style="position:absolute; width:180px; height:600px; left:202px; top:2px; z-index:9999; background-color:#000; filter:alpha(Opacity=80);-moz-opacity:0.9;opacity: 0.9; text-align:center; display: none;"></div>
	
</body>
</html>