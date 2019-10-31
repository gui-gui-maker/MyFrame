<%@ page import="com.khnt.security.util.SecurityUtil" %>
<%@ page import="com.khnt.security.CurrentSessionUser" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    CurrentSessionUser user = SecurityUtil.getSecurityUser();
    String userid = user.getId();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>地图展示</title>
	<%@include file="/k/kui-base-list.jsp"%>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="x-ua-Compatible" content="ie=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
	<style type="text/css">
		body, html,#frameDiv,#loader {width: 100%;height: 100%;overflow: hidden;margin:0;font-size:14px; font-family: "Microsoft Yahei", "黑体", "宋体";}
	</style>
	<link rel="stylesheet" type="text/css" href="app/gisydzf/css/main_style.css" media="all" />
	<link rel="stylesheet" type="text/css" href="app/gisydzf/css/bootstrap-switch.min.css">
	<link rel="stylesheet" type="text/css" href="app/common/css/viewer.min.css"/>
	 <script type="text/javascript" src="app/common/js/viewer-jquery.min.js"></script>
	<script type="text/javascript" src="app/gisydzf/js/bootstrap-switch.min.js"></script>
	<script type="text/javascript" src="app/gisydzf/js/highlight.js"></script>
	<script language="JavaScript">
		var USERID = "<%=userid%>";
		var sy_flag;
		var viewer;
		var rightWidth = 300; //右侧菜单宽度，收缩后变为0,根据是否收缩跳转图片弹出框宽度
		if(api){
			sy_flag= api.data.sy_flag;
		}
		$(function(){
			viewer = $('#viewerimgctr').viewer({
					title : false,
					show : function(){
										if(rightWidth==300){
											$(".viewer-container").attr("style","z-index: 2015;margin:0px 300px 0px 0px;")
										}else{
											$(".viewer-container").attr("style","z-index: 2015;margin:0px 0px 0px 0px;")
											if($(".ui_title_buttons",window.parent.document)){
												$(".ui_title_buttons",window.parent.document).attr("style","display: none;");
											}
										}
					},
					hidden : function(){
											if($(".ui_title_buttons",window.parent.document)){
												$(".ui_title_buttons",window.parent.document).attr("style","");
											}
					}
			});
	    });
		 function showImageViewer(src){
		         $("#viewerimg").attr("src",src).trigger("click");
		 }

	</script>
	<script type="text/javascript" src="app/gisydzf/js/main.js"></script>
<!-- <style type="text/css" id="style">
.viewer-container{
margin : 0px 300px 0px 0px;
}
	</style> -->
</head>
<body>
<div id="loader" class="loader">
	<div class="load-center">
		<div class="ball-spin-fade-loader">
			<div></div>
			<div></div>
			<div></div>
			<div></div>
			<div></div>
			<div></div>
			<div></div>
			<div></div>
		</div>
		<div class="load-txt">地图加载中，请稍候...</div>
	</div>
</div>
<div id="frameDiv">
	<iframe id="mapFrame" src="app/gisydzf/bmap.jsp" style="width: 100%; height: 100%; border: 0px;"></iframe>
	<div id="viewerimgctr" style="display:none;">
		<img src="" id="viewerimg" />
	</div>
</div>
<div class="page_main">
	<div class="side_left">
		<div class="mnbgone"></div>
		<div id="menu">
			<ul id="nav">
			</ul>
		</div>
	<div class="shrinkLeft"><a href="javascript:void(0)" onclick="setShrinkLeft()"></a></div>
</div>
<div id="layerTitle" class="layerTitle">空白图层</div>
</body>
</html>
