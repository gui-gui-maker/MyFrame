<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">	
 
  <head>
 <%--   <%@include file="/k/kui-base-list.jsp"%>  --%>
    <title>在线阅读</title>
        <style type="text/css" media="screen"> 
			html, body	{ height:100%; }
			body { margin:0; padding:0; overflow:auto; }   
			#flashContent { display:none; }
        </style>  
		<script type="text/javascript" src="js/flexpaper_flash.js"></script>
  </head>
  <body>  

    <div style="position:absolute;left:0px;top:0px;width:100%;height:100%">
	        <a id="viewerPlaceHolder" style="width:100%;height:100%;display:block"></a> 
	        <script type="text/javascript">
	      //alert("http://localhost:9081/upload/TEST/123.swf")
	     // SwfFile : escape("http://"+window.location.host+"/upload/${param.path}"),
	     		var fp = new FlexPaperViewer(
						 'FlexPaperViewer',
						 'viewerPlaceHolder', { config : {
						 SwfFile : escape("http://kh.scsei.org.cn/upload/20170504/CO-TA201701797/CO-TA201701797.swf"),
						 Scale : 1.5,
						 ZoomTransition : 'easeOut',
						 ZoomTime : 0.5,
						 ZoomInterval : 0.2,
						 FitPageOnLoad : false,
						 FitWidthOnLoad : false,
						 PrintEnabled : false,
						 FullScreenAsMaxWindow : false,
						 ProgressiveLoading : true,
						 MinZoomSize : 0.2,
						 MaxZoomSize : 5,
						 SearchMatchAll : false,
						 InitViewMode : 'TwoPage',//设置启动模式如"Portrait" or "TwoPage". 
						 ViewModeToolsVisible : true,
						 ZoomToolsVisible : true,
						 NavToolsVisible : true,
						 CursorToolsVisible : true,
						 SearchToolsVisible : true, 
  						 localeChain: 'zh_CN'  
						 }});
						 
	        </script>
        </div>
   </body>
</html>
