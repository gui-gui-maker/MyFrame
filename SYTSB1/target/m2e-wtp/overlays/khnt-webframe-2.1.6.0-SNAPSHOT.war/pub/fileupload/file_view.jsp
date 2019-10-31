<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@include file="/k/kui-base-form.jsp" %>
	<title>文件预览</title>
	<link rel="stylesheet" type="text/css" href="pub/fileupload/css/file-view.css" />
</head>
<body>
<div class="backgroud"></div>
<div class="file-viewer-wrap" >
	<a class="file-viewer-pre">&lt;</a>
	<div id="file-viewer">
		<div id="image-viewer">
			<div class="image-wrap">
				<div class="image-drag">
					<div class="image-rotate"></div>
				</div>
			</div>
			<div class="zoom-text"></div>
			<div class="esc-tips">按ESC键退出</div>
			<div class="file-name"></div>
			<div class="image-tbar"></div>
		</div>
		<div id="online-viewer"></div>
		<div id="download-viewer"></div>
		<div id="video-player">
			<video id="my-video-player" class="video-js" controls="true" preload="auto" width="800" height="500" autoplay="false">
				<p class="vjs-no-js">您的浏览器不支持播放此视频文件，请启用javascript</p>
			</video>
			<div class="video-title"></div>
		</div>
		<div id="audio-player">
			<div class="player-title"></div>
			<div class="player-btn player-btn-play" title="点击播放"></div>
			<!-- <div class="player">
				<div class="play-btn">
					<div class="play-btn-pause"></div>
				</div>
				<div class="timer">0:00:00</div>
				<div class="process-bar"></div>
				<div class="volume-btn"></div>
				<div class="volume-bar"></div>
			</div> -->
		</div>
	</div>
	<a class="file-viewer-next">&gt;</a>
</div>
<!--导航小图-->
<div class="small-img">
	<a class="small-img-pre"></a>
	<div class="small-img-box">
		<ul id="file-list" class="small-img-list"></ul>
	</div>
	<a class="small-img-next"></a>
</div>
<script type="text/javascript" src="k/kui/frame/jquery.mousewheel.js"></script>
<script type="text/javascript" src="pub/fileupload/js/file_view.js"></script>
</body>
</html>