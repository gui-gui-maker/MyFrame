<%@page import="com.khnt.certificate.util.CertificateUtil"%>
<%@page import="java.security.cert.X509Certificate"%>
<%@page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true"%>
<%
	boolean isCertError = false;
	Object userName = "";
	if (request.isSecure()){
		X509Certificate cert = CertificateUtil.extractClientCertificate(request);
		try {
			userName = CertificateUtil.extractPrincipal(cert,"CN=(.*?),");
		} catch (Exception e) {
			isCertError = true;
		}
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageKeys="login">
	<%@include file="/k/kui-base.jsp"%>
	<title>Loading...</title>
	<meta HTTP-EQUIV="Page-Exit" CONTENT="blendTrans(Duration=0.5)">
	<script type="text/javascript">
		var LOGIN_CERT = {
			uname : "<%=userName%>",//用户账号 admin
			isCertErr : <%=isCertError%>//是否证书读取
		};
	</script>
	<link rel="stylesheet" type="text/css" href="k/kui/public/login/default2/login.css" media="all" />
	<link rel="stylesheet" type="text/css" href="k/kui/public/login/default2/login2.css" media="all" />
	<script type="text/javascript" src="k/kui/frame/login-frame.js"></script>


	<script type="text/javascript">
	//大图切换
	var imageJson=[
		//{img:"k/kui/public/images/login2/loginbg_00.jpg"},
//		{img:"k/kui/public/images/login2/loginbg_01.jpg"},
//		{img:"k/kui/public/images/login2/loginbg_02.jpg"},
//		{img:"k/kui/public/images/login2/loginbg_03.jpg"},
//		{img:"k/kui/public/images/login2/loginbg_04.jpg"},
		{img:"k/kui/images/sys-desktop/bg/bg014.jpg"},
		{img:"k/kui/images/sys-desktop/bg/bg009.jpg"},
		{img:"k/kui/images/sys-desktop/bg/bg012.jpg"}
	];
//	$(function(){//jQuery页面载入事件
//		var oneExe1=setTimeout(function(){
//			$("#k-login-top-logo").css("background-image","url(app/szh/logo-login.png)");
//		},10);
//
//	});
	(function($){
		//拖拽插件,参数:id或object
		$.Move = function(_this){
			if(typeof(_this)=='object'){
				_this=_this;
			}else{
				_this=$("#"+_this);
			}
			if(!_this){return false;}
			var _this2=$("#login-box");

			//_this.css({'position':'absolute'}).hover(function(){$(this).css("cursor","move");},function(){$(this).css("cursor","default");})
			_this.mousedown(function(e){//e鼠标事件
				var _this3=$(this);
				//var oneExe1=setTimeout(function(){
					var offset = _this3.offset();console.log();//调试信息
					var x = e.pageX - offset.left-Math.abs(parseInt(_this3.css("margin-left")));
					var y = e.pageY - offset.top-Math.abs(parseInt(_this3.css("margin-top")));
					_this.css({'opacity':'0.3'});
					_this2.css({'opacity':'0.3'});
					$(document).bind("mousemove",function(ev){//绑定鼠标的移动事件，因为光标在DIV元素外面也要有效果，所以要用doucment的事件，而不用DIV元素的事件
						_this.bind('selectstart',function(){return false;});
						var _x = ev.pageX - x;//获得X轴方向移动的值
						var _y = ev.pageY - y;//获得Y轴方向移动的值
						_this.css({'left':_x+"px",'top':_y+"px"});
						_this2.css({'left':_x+"px",'top':_y+"px"});
						P_IS_MOUSE_DRAY=true;//2015/2/2 17:25 lybide
					});
				//},1000);
			});

			$(document).mouseup(function(){
				$(this).unbind("mousemove");
				_this.css({'opacity':''});
				_this2.css({'opacity':''});
			})
		};
	})(jQuery);
	$(function(){
		//$("#login-box2").css({"width":$("#login-box").width(),"height":$("#login-box").height()});
		//$("#login-box,#login-box2").hide();
		$("#login-start2").click(function(){
			var _this=$(this);
			$("#login-box,#login-box2").show();
			_this.hide();
		});
		$("#login-start2").click();
		$("#login-box-close").click(function(){
			var _this=$(this);
			$("#login-box,#login-box2").hide();
			$("#login-start2").show();
		});
		kLogin2cSize();
		$(window).resize(function(){
			kLogin2cSize();
		});
		function kLogin2cSize(){
			var w=$("#login-box").width();
			w=790;
			//$("#login-box,#login-box2").width(w).css("margin-left",-w/2)
		}

		//系统名称
		$("#loginCopy span").html(kFrameConfig["copyCom"]);//版权所有
		$("#loginTech span").html(kFrameConfig["techCom"]);//技术支持
		$("#TxtUserName,#TxtPassword,#validateCode").val("");

		$.Move($('#login-box2'));

		//开启验证码
		//kFrameConfig["loginYZM"]=true;
		if(kFrameConfig["loginYZM"]){
			var yzm=$('<img src="security/web/validateCodeImg.do?isNew=true" />');
			yzm.click(function(){
				var $this=$(this);
				$this.attr("src","security/web/validateCodeImg.do?isNew=true"+"&r="+Math.random());
			})
			$("#userCodePanel").show();
			$("#k-login-inputyzimg").html(yzm);
			$("#userButtonPanel").hide();//登录，取消等，显示
		} else {
			$("#userCodePanel").hide().html("");//验证码
			$("#useryzm-plane").hide();
			$("#login-box,#login-box2").addClass("no-yzm")
		}

		//安全证书登录
		//kFrameConfig["loginCert"].isCert=true;
		if(kFrameConfig["loginCert"].isCert){
			var cretUName=typeof LOGIN_CERT =="undefined"?"":LOGIN_CERT["uname"];
			cretUName=cretUName||"没有读出用户名";//2015/1/5 10:58 lybide
			var dm1=["早上好，","上午好，","中午好，","下午好，","晚上好，",""];//2015/2/11 15:15 lybide
			var ca1,cc1=new Date().getHours();
			if (cc1>=19 && cc1<=24) {
				ca1=4;
			} else if (cc1>=5 && cc1<=8) {
				ca1=0;
			} else if (cc1>=9 && cc1<=11) {
				ca1=1;
			} else if (cc1>=12 && cc1<=13) {
				ca1=2;
			} else if (cc1>=14 && cc1<=18) {
				ca1=3;
			} else {
				ca1=5;
			};
			ca1=5;
			var isCertStr1='<div class="l-userName-zs"><div>'+(dm1[ca1])+""
					+ cretUName + ""+'</div></div>';
			//isCertStr1+='<input id="TxtUserName" type="hidden" value="admin" name="j_username"title="请输入您的用户名"/>';
			$("#TxtUserName").remove();
			$("#username-plane").append(isCertStr1);
			$("#TxtUserName").attr("readonly",true);
			$(".k-login-inputname").hide();
			$("body").addClass("is-cert");
		}

		//打印标题
		$("#").append('<div class="k-login-top-logo-text" id="sysNameText"><div><a><span>'+kFrameConfig["name"]+'</span></a></div></div>');
		if (kFrameConfig["logoMode"]=="img") {
			$("#k-login-top-logo").css("background","url('"+kFrameConfig.loginLogo["src"]+"') no-repeat center center");
			if (kFrameConfig.loginLogo["style"]) {
				$("#k-login-top-logo").css(this.loginLogo["style"]);
			}
		} else {
			$("#k-login-top-logo").hide();
			$("#sysNameText").show();
		}

		for (var i = 0, l=imageJson.length; i < l; i++) {
			var item=imageJson[i];
			var str1='<div class="i-main-datu" style="background-image:url('+item["img"]+');"></div>';
			if (BROWSER_INFO.ie && BROWSER_INFO.ieversion<=8) {
				str1='<div class="i-main-datu"><img src="'+item["img"]+'" alt=""/></div>';
			}
			$("#loop1").append(str1);
		};
		if (BROWSER_INFO.ie && BROWSER_INFO.ieversion<=8) {
			$("#loginForm .input").each(function (i) {
				var _this = $(this);
				var id = _this.attr("id");
				_this.attr("data-placeholder",_this.attr("placeholder"))
				_this.removeAttr("placeholder");
				var placeholder = _this.attr("data-placeholder");
				var divObj = $('<div class="l-u-msg s-' + id + '-msg" id="' + id + '-msg">' + placeholder + '</div>');
				divObj.click(function () {
					_this.focus();
					divObj.hide();
				});
				_this.after(divObj);
			}).focus(function () {
				var _this = $(this);
				var id = _this.attr("id");
				var placeholder = _this.attr("data-placeholder");
				var divObj = $("#" + id + "-msg");
				if (_this.val() == "" || _this.val() == placeholder) {
					divObj.hide();
				}

			}).blur(function () {
				var _this = $(this);
				var id = _this.attr("id");
				var placeholder = _this.attr("data-placeholder");
				var divObj = $("#" + id + "-msg");
				if (_this.val() == "") {
					divObj.show();
				}
			});
			$("#bsubmit-plane input").click(function(){
				$("#loginForm .l-u-msg").show();
			});
		}

	});
	</script>
	<script type="text/javascript" src="k/kui/public/login/default2/login.js"></script>
	<link rel="stylesheet" type="text/css" href="k/kui/images/icons/fonticon/fontsbase/iconfont.css" id="allFontIconsCSS">
<style>


.logo-plane{display:none;}

.m-top-logo {position:absolute; left:50%; top:0; z-index:1; width:800px; height:230px; margin:0 0 0 -400px; padding-top:60px; text-align:center;}
.m-top-logo-img {font-size:6.8rem!important; *font-size:80px; *width:96px; *height:96px;  *background:url(../images/logo-white.png) no-repeat; color:#fff; animation: bounceIn 1s ease-out 0.3s forwards;}
.m-top-logo-txt { margin-top:5px; font-size:2.9rem; *font-size:40px; font-weight: bold; color: #fff; letter-spacing:7px; animation: bounceIn 1s ease-out 0.3s forwards; /*background-image: -webkit-gradient(linear, 0 0, 0 bottom, from(rgba(207, 43, 43, 1)), to(rgba(166, 0, 0, 1)));-webkit-background-clip: text;-webkit-text-fill-color: transparent;*/ text-shadow:1px 1px 10px #333;}
@-webkit-keyframes bounceIn {
	0% {opacity:0;-webkit-transform:scale(.3);transform:scale(.3);}
	50% {opacity:1;-webkit-transform:scale(1.05);transform:scale(1.05);}
	70% {-webkit-transform:scale(.9);transform:scale(.9);}
	100% {opacity:1;-webkit-transform:scale(1);transform:scale(1);}
}
@keyframes bounceIn {
	0% {opacity:0;-webkit-transform:scale(.3);-ms-transform:scale(.3);transform:scale(.3);}
	50% {opacity:1;-webkit-transform:scale(1.05);-ms-transform:scale(1.05);transform:scale(1.05)}
	70% {-webkit-transform:scale(.9);-ms-transform:scale(.9);transform:scale(.9)}
	100% {opacity:1;-webkit-transform:scale(1);-ms-transform:scale(1);transform:scale(1)}
}

.bounceIn { -webkit-animation-name: bounceIn; animation-name: bounceIn }

.m-top-logo .icon{
font-size: 100px;
color:#ff0402;
text-shadow:#fff 1px 0 0,#fff 0 1px 0,#fff -1px 0 0,#fff 0 -1px 0,#333 1px 1px 10px;
-webkit-text-shadow:#fff 1px 0 0,#fff 0 1px 0,#fff -1px 0 0,#fff 0 -1px 0,#333 1px 1px 10px;
-moz-text-shadow:#fff 1px 0 0,#fff 0 1px 0,#fff -1px 0 0,#fff 0 -1px 0,#333 1px 1px 10px;
*filter: Glow(color=#fff, strength=1);
	}

</style>

</head>
<body>
<div class="login-wrap k-login-layer" id="login-wrap">
	<div class="bg-container" id="bg-container">
		<div class="i-main-content-wrap index-dt" id="loop1"></div>
	</div>
	<!--顶部-->
	<div class="login-head" id="login-head">
		<div class="login-hd-bg" id="login-hd-bg"></div>
	</div>
	<!--logo 标志-->
	<div class="logo-plane" id="logo-plane">
		<div class="logo" title="" id="k-login-top-logo"></div>
	</div>

	<div id="systemTitle" class="m-top-logo">
		<div id="systemTitleImg" class="m-top-logo-img"><i class="icon iconfont">&#xe600;</i></div>
		<div id="systemTitleText" class="m-top-logo-txt">川大科鸿新技术研究所</div>
	</div>

	<div class="login-box" id="login-box">
		<div class="login-box-wrap">
			<div id="login-box-close" class="login-box-close"><a href="javascript:void(0);"><span>关闭</span></a></div>
			<form name="loginForm" id="loginForm" action="j_spring_security_check" method="post" onsubmit="return sysLoginDoSubmit(this)">
				<div class="ipt username-plane" id="username-plane">
					<input type="text" name="j_username" id="TxtUserName" class="input" placeholder="请输入用户名" maxlength="255"/>
					<em class="i1"></em>
				</div>
				<div class="ipt userpass-plane" id="userpass-plane">
					<input type="password" name="j_password" id="TxtPassword" class="input" placeholder="请输入密码" maxlength="255"/>
					<em class="i2"></em>
				</div>
				<div class="ipt useryzm-plane" id="useryzm-plane">
					<input type="text" name="j_validate_code" id="validateCode" class="input" placeholder="请输入验证码" maxlength="9"/>
					<em class="i3"></em>
					<div class="yzm-img" id="k-login-inputyzimg"></div>
				</div>
				<div class="userButtonPanel" id="userButtonPanel">
					<div class="btn bsubmit-plane" id="bsubmit-plane">
						<input type="submit" value="登 陆" class="a-button a-black submit" id="lFormSubmit"/>
					</div>
					<div class="btn breset-plane" id="breset-plane">
						<input type="reset" value="清 空" class="a-button a-black reset" id="lFormReset"/>
					</div>
				</div>
			</form>
		</div>
	</div>
	<div class="login-box box2" id="login-box2"></div>

	<div class="login-adorn" id="login-adorn"></div>

	<div class="login-text" id="login-text">
		<h1>选择KFrame框架的理由?</h1>
		<h2>Select reason KFrame framework</h2>
		<p>FR框架的组件库包含近百种组件，并且为每一种组件都制作了大量的典型示例，充分展示组件的各种应用场景，完全能够满足企业前端应用的各种需要。她是所有前端框架中组件库最全的框架之一。并且提供非常详尽的使用文档，目前包含642个组件，涉及框架使用中可能会遇到的方方面面。……</p>
	</div>


	<!--四个切换点-->
	<div class="switch-point" id="switch">
		<div class="switch-point-wrap" id="loop1b">
			<ul>
			</ul>
		</div>
	</div>
	<!--版权-->
	<div class="copyright-wrap" id="copyright">
		<div class="copyright">
			<div class="a" id="loginCopy"><span>Copyright khnt.com All rights reserved.</span></div>
			<div class="b" id="loginTech"><span></span></div>
		</div>
	</div>
	<!--底部-->
	<div class="login-foot" id="login-foot">
		<div class="login-ft-bg" id="login-ft-bg"></div>
	</div>

	<div class="login-off" id="login-off">
		<div class="loa" id="loa"><a id="login-start2" href="javascript:void(0);" class="a-button a-black"><span>进行登陆</span></a></div>
	</div>
</div>
</body>
</html>
