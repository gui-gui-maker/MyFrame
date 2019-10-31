<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/k/kui-base.jsp"%>
<link rel="stylesheet" type="text/css" href="k/libs/vedio.js/video-js.css">
<script type="text/javascript" src="k/libs/vedio.js/videojs-ie8.min.js"></script>
<script type="text/javascript" src="k/libs/vedio.js/video.js"></script>
<script type="text/javascript">
	$(function() {
		$("#myvideo").height($(window).height()*0.9).width($(window).width()*0.9);;
	});
</script>
<style type="text/css">
.video-js{
	display:block;margin-left:auto;margin-right:auto;margin-top:20px;
}
.video-js .vjs-big-play-button{
	left: 48%!important;top: 48%!important;
}
</style>
</head>
<body style="padding:0;margin:0;text-align:center;">
	<video id="myvideo" class="video-js" controls preload="auto" width="800" height="500" 
		poster="MY_VIDEO_POSTER.jpg" data-setup="{}">
		<source src="fileupload/download.do?id=${param.fid}" type='video/mp4'>
		<p class="vjs-no-js">
			To view this video please enable JavaScript, and consider upgrading to a web browser that <a
				href="http://videojs.com/html5-video-support/" target="_blank">supports HTML5 video</a>
		</p>
	</video>
</body>
</html>