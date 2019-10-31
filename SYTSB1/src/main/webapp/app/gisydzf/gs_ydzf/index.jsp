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
	<link rel="stylesheet" type="text/css"
	href="app/gisydzf/css/main_style.css" media="all" />
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
			//setInfoWindow(devIdno,jsession);
		}
    </script>
	<style type="text/css">
	.BMap_cpyCtrl{
		display:none !important;
	}
	</style>
</head>
<body>
<div id="mapDiv"></div>
<div class="page_main">
	<div class="side_left">
			<div class="mnbgone"></div>
			<div id="menu">
				<ul id="nav">
					<li class="mainlevel"><a href="javascript:void(0);"
						onclick="f_toOpenVideo('80014082');"
						class="xxx"><em class="i1"></em>设备-82</a> 
						<!-- 
						<div class="sub_nav_01" style="height: 474px; display: none;">
							<ul>
								<li><a href="javascript:void(0);"
									onclick="setLayer('青白江-执法信息','app/gisydzf/gs_ztxx/index.jsp?')"><em
										class="ia"></em>执法信息</a></li>
							</ul>
							<div class="subgtwo"></div>
						</div>
						 -->
					</li>
					<li class="mainlevel"><a href="javascript:void(0);"
						onclick="f_toOpenVideo('80014089');" class="xxx"><em class="i1"></em>设备-89</a> 
						<!-- 
						<div class="sub_nav_01" style="height: 474px; display: none;">
							<ul>
								<li><a href="javascript:void(0);"
									onclick="setLayer('青白江-执法信息','app/gisydzf/gs_ztxx/index.jsp?')"><em
										class="ia"></em>执法信息</a></li>
							</ul>
							<div class="subgtwo"></div>
						</div>
						 -->
					</li>
				</ul>
			</div>
			<!-- <div class="shrinkLeft">
				<a href="javascript:void(0)" onclick="setShrinkLeft()"></a>
			</div> -->
		</div>
	<div class="side_right">
	</div>
	<div class="shrinkRight"><a href="javascript:void(0)" onclick="_pub_setShrinkRight()"></a></div>
</div>
<div id="picView" class="picView"><div class="picClose" onclick="closeImgViewer()" title="关闭"></div><img id="imgView" src=""/></div>
<div id="chartView" class="chartView"><div class="picClose" onclick="closeChartViewer()" title="关闭"></div><div id="echartV" style="float:left;width:100%;height:100%;"></div><div id="echartV2" style="float:left;width:0%;height:100%;"></div></div>
<div id="spanel" style="position:absolute; width:180px; height:600px; left:202px; top:2px; z-index:9999; background-color:#000; filter:alpha(Opacity=80);-moz-opacity:0.9;opacity: 0.9; text-align:center; display: none;"></div>
	
</body>
</html>