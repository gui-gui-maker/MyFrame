<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="content-type" content="text/html; charset=utf-8"/>
	<title></title>
	<script type="text/javascript" src="${pageContext.request.contextPath}/app/js/jquery.min.js" ></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/k/kui/frame/ligerui.all.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/app/gisydzf/video/swfobject.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/app/gisydzf/video/jweixin-1.0.0.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/app/gisydzf/video/video.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/app/gisydzf/video/videojs-contrib-hls.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/app/gisydzf/gs_ydzf/sbxx_video.js"></script>
	<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />
	<script type="text/javascript">
		var jsession = "";
		var devIdno = "${param.devIdno}";
		$(function() {
			//与北斗视频对接
			//获取回话id
			getJsession();
			var url = "app/gisydzf/gs_ydzf/sbxx_video2.jsp?devIdno="+devIdno+"&jsession="+jsession+"&videoOpenType=2";
			$("#video_Iframe").attr("src", url);
		})
		
		/**
		 * 通过用户名密码获取jsession
		 */
		function getJsession(){
			var account = "cdkh";
			var password = "cdkh";
			 $.ajax({
		         type:'POST',
		         url: "http://124.129.19.117:8088/StandardApiAction_login.action?account="+account+"&password="+password+"",
		         cache:false,
		         dataType:'JSONP', 
		         success: function (data) {
		        	 data = eval('(' + data + ')');
		            if(data.result == 0){
		            	jsession = data.jsession;
		            } else {
		            	$.ligerDialog.warn("获取会话ID失败","提示");
		            }
		    },
		 });
			
		}
	</script>	
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" >
<div style="overflow:hidden;">
<form name="formObj" id="formObj" getAction="">
	<iframe name="video_Iframe" id="video_Iframe" src="" width="1188" height="518" scrolling="auto" frameborder="0" border="0" onload="video_Iframe.focus()"></iframe>
</form>
</div>
</body>
</html>