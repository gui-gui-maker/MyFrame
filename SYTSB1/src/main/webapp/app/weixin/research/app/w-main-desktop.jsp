<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>
		<meta charset="utf-8">
		<title>云检索</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">

		<!--标准mui.css-->
		<link rel="stylesheet" href="app/weixin/research/css/mui.min.css">
		<!--App自定义的css-->
		<link rel="stylesheet" type="text/css" href="app/weixin/research/css/app.css" />
<style>
	#searchContain{position:relative;}
	#searchButton{
					position:absolute;
					background-color:initial;
					width:30px;
					height:30px;
					border:0px;
					left:2px;
					top:2px;
				}
	.mui-bar .mui-btn{
		padding: 6px 6px;
		margin-top: 4px;
	}
	.mui-slider-indicator .mui-icon {
		border: 0px;
	}
	.mui-input-row.mui-search .mui-icon-clear {
		  top: 0px; 
	}
	.mui-search:before {
		top: 25px;
	}
	input[type=color], input[type=date], input[type=datetime-local], input[type=datetime], input[type=email], input[type=month], input[type=number], input[type=password], input[type=search], input[type=tel], input[type=text], input[type=time], input[type=url], input[type=week], select, textarea {
		margin-bottom: 0px;
	}
	.mui-btn, button, input[type=button], input[type=reset], input[type=submit] {
		  padding: 6px 0px;
	}
	.mui-bar~.mui-content .mui-fullscreen {
		top: 44px;
		height: auto;
	}
	.mui-pull-top-tips {
		position: absolute;
		top: -20px;
		left: 50%;
		margin-left: -25px;
		width: 40px;
		height: 40px;
		border-radius: 100%;
		z-index: 1;
	}
	.mui-bar~.mui-pull-top-tips {
		top: 24px;
	}
	.mui-pull-top-wrapper {
		width: 42px;
		height: 42px;
		display: block;
		text-align: center;
		background-color: #efeff4;
		border: 1px solid #ddd;
		border-radius: 25px;
		background-clip: padding-box;
		box-shadow: 0 4px 10px #bbb;
		overflow: hidden;
	}
	.mui-pull-top-tips.mui-transitioning {
		-webkit-transition-duration: 200ms;
		transition-duration: 200ms;
	}
	.mui-pull-top-tips .mui-pull-loading {
		/*-webkit-backface-visibility: hidden;
		-webkit-transition-duration: 400ms;
		transition-duration: 400ms;*/
		
		margin: 0;
	}
	.mui-pull-top-wrapper .mui-icon,
	.mui-pull-top-wrapper .mui-spinner {
		margin-top: 7px;
	}
	.mui-pull-top-wrapper .mui-icon.mui-reverse {
		/*-webkit-transform: rotate(180deg) translateZ(0);*/
	}
	.mui-pull-bottom-tips {
		text-align: center;
		background-color: #efeff4;
		font-size: 15px;
		line-height: 40px;
		color: #777;
	}
	.mui-pull-top-canvas {
		overflow: hidden;
		background-color: #fafafa;
		border-radius: 40px;
		box-shadow: 0 4px 10px #bbb;
		width: 40px;
		height: 40px;
		margin: 0 auto;
	}
	.mui-pull-top-canvas canvas {
		width: 40px;
	}
	.mui-slider-indicator.mui-segmented-control {
		background-color: #efeff4;
	}
	
</style>
		<script type="text/javascript" src="app/weixin/research/js/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="app/js/jQuery.md5.js"></script>
		<script type="text/javascript">
		//自动登录
			var username = '${username}';
			var password = '${password}';
			$(function(){
				if(username!='' && password!=''){
					$.post($("base").attr("href")+"j_spring_security_check",{j_username:username,j_password:$.md5("password")},function(data){
						/* if(data.msg=='密码不正确'){
							$.post($("base").attr("href")+"j_spring_security_check",{j_username:"admin",j_password:"scjy1qa"},function(data){
								//alert(data.msg);
							}); 
						}else{
							alert(data.msg);
						}; */
					});
				}else{
					/* $.post($("base").attr("href")+"j_spring_security_check",{j_username:"admin",j_password:"scjy1qa"},function(data){
						//alert(data.msg);
					}); */ 
					document.write("没有权限，请联系管理员！");
					//$("#body").html("没有权限，请联系管理员！");
				}
			});
								
		
			
			function indexSearchClick(){
				if($("#search").val()==""){
					alert("请输入查询条件！")
					return;
				}
				window.location.href= $("base").attr("href")+"enterSearchAction/wSearchAll.do?name="+$("#search").val();
			}
		</script>
	</head>

	<body id="body">
		<header class="mui-bar mui-bar-nav">
			<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
			<h1 class="mui-title">云检索</h1>
		</header>
		<div class="mui-content">
		<div id="slider" class="mui-slider mui-fullscreen">
		<!-- 搜索框 -->
		<div id="sliderSegmentedControl" class="mui-scroll-wrapper mui-slider-indicator mui-segmented-control mui-segmented-control-inverted">
			<div style="background-color: white;padding-bottom: 5px;padding-top: 5px;">
				<div class="mui-input-row mui-search" style="margin-right: 60px;">
					<input id="search" type="search" class="mui-input-clear" placeholder="输入查询内容" value="">
				</div>
				<button type="button" class="mui-btn" style="position: absolute;top:6px;right: 5px;width: 45px;" onclick="indexSearchClick();">
					查询
				</button>
			</div>
		</div>
		<!-- 内容 -->
		<div class="mui-slider-group" style="margin-top: 10px;">
		<div id="item1mobile" class="mui-slider-item mui-control-content mui-active">
		<div id="scroll1" class="mui-scroll-wrapper">
			<div class="mui-scroll">
			</div>
		</div>
		</div>
		</div>
		</div>
		</div>
		
	</body>
	<script src="app/weixin/research/js/mui.min.js"></script>
	<script src="app/weixin/research/js/mui.pullToRefresh.js"></script>
	<script src="app/weixin/research/js/mui.pullToRefresh.material.js"></script>
	<script>
		mui.init();
		//滚动，固定搜索框
		(function($) {
			//阻尼系数
			var deceleration = mui.os.ios?0.003:0.0009;
			$('.mui-scroll-wrapper').scroll({
				bounce: false,
				indicators: true, //是否显示滚动条
				deceleration:deceleration
			});
			$.ready(function() {
			});
		})(mui);
	</script>
</html>