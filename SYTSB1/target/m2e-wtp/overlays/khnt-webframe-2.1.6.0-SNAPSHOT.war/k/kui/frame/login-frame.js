//判断浏览器
var browser={
    versions:function(){
        var u = navigator.userAgent, app = navigator.appVersion;
        return {         //移动终端浏览器版本信息
             trident: u.indexOf('Trident') > -1, //IE内核
            presto: u.indexOf('Presto') > -1, //opera内核
            webKit: u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核
            gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1, //火狐内核
            mobile: !!u.match(/AppleWebKit.*Mobile.*/), //是否为移动终端
            ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
            android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android终端或uc浏览器
            iPhone: u.indexOf('iPhone') > -1 , //是否为iPhone或者QQHD浏览器
            iPad: u.indexOf('iPad') > -1, //是否iPad
            webApp: u.indexOf('Safari') == -1 //是否web应该程序，没有头部与底部
        };
     }(),
     language:(navigator.browserLanguage || navigator.language).toLowerCase()
}

$.ajaxSetup({
	cache: false
});

function init_login(){
	if (top.location!==self.location) {
		top.location=self.location;
	}
	if (kFrameConfig.loginOnLoadBefore()) {

	} else {
		var sysName=kFrameConfig["name"];
		sysName=sysName.replace(/<[^>].*?>/g," ");
		document.title=sysName;
		$("#topSoftName").html("欢迎使用"+sysName);
		$("html").css("overflow","hidden");//必须，解决窗口拖动时，滚动条的问题。
		wChange();
		$(window).resize(function(){
			wChange();
		});
		if ($.browser.msie && parseFloat($.browser.version) <=6) {//浏览器检测 2012年06月19日 星期二 14:04:54 lybide
			$(document.body).append('<div id="bosCk1" style="border:1px solid #000000;background:#ff0000;padding:15px;position:absolute;top:10px;right:10px;z-index:9999;color:#ffffff;">提示：您的浏览器版本太低，请立即升级您的浏览器。推荐使用：<a href="http://www.microsoft.com/ie" target="_blank" style="color:#ffff00">（最新IE浏览器）</a>。<a href="javascript:void(0);" onclick="$(\'#bosCk1\').hide();" style="color:#ffffff">[关闭此提示]</a></div>');
			try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}//ie6不缓存背景图片
			DD_belatedPNG.fix("*");
		}
	}

	$("#k-login-box").hide();
	//2018年03月05日 15:12:55 星期一 lybide
	//===========================================================
	if (true == kFrameConfig["j_login_strategy_qrcode"]) {
		$("head").append('<script type="text/javascript" src="k/kui/frame/jquery.qrcode.min.js"></script>');
		var str1='';
		str1+='<div id="k-caption" class="k-tabeffect">';
		str1+='<ul>';
		str1+='<li id="k-smdl" class="k-smdl"><span>扫码登录</span><a href="javascript:void(0);" title="" tabId="tablink1"></a></li>';
		str1+='<li id="k-zhdl" class="k-zhdl k-tabactive"><span>帐号登录</span><a href="javascript:void(0);" title="" tabId="tablink2"></a></li>';
		str1+='</ul>';
		str1+='</div>';
		str1+='<div class="k-tent-list" id="tablink1" >';
		str1+='<div class="k-qrcode-mod">';
		str1+='<div class="k-qrcode-main">';
		str1+='<div class="k-qrcode-img" id="k-qrcode"></div>';
		str1+='</div>';
		str1+='<p class="k-qrcode-desc">打开手机APP，扫一扫登录</p>';
		str1+='<p class="k-qrcode-confirm">扫码成功，请在手机APP上点击确认！</p>';
		str1+='<p class="k-qrcode-link"><a href="javascript:showAppDownDialog(1);">下载手机端APP</a></p>';
		str1+='</div>';
		/*str1+='<div class="k-qrcode-other">';
		str1+='<div><a href="#" target="_blank" >下载手机端APP</a></div>';
		str1+='</div>';*/
		str1+='</div>';
		$("#k-login-box").prepend(str1);
		$("#loginForm").wrap('<div class="k-tent-list" id="tablink2"></div>');

		$(".k-tabeffect li a ").click(
			function () {//2012年09月13日 星期四 16:43:01 lybide
				var that=$(this);
				setTimeout(function(){
					that.parent().siblings().removeClass("k-tabactive");
					that.parent().addClass("k-tabactive");
					var tabId = that.attr("tabId");
					if(tabId == "tablink1"){
						$("#tablink1").hide();
						$("#tablink2").show();
						kFrameConfig.cancelQrcodeLogin();
					}else{
						$("#tablink1").show();
						$("#tablink2").hide();
						kFrameConfig.createLoginQrcode();
					}
				},100);
			}
		);
		$("#k-caption li a:first").trigger("click");
		$("#sysNamePanel").hide();
	} else {
		$("#sysNamePanel").show();
	}
	$("#k-login-box").show(500);
	if (true == kFrameConfig["j_login_strategy_confirm"]) {
		var str1 = '<div id="k-login-check-mobile" class="k-login-check-mobile">' +
				'<div class="k-lcm-img"></div>' +
				'<div class="k-lcm-txt">请在手机上确认登录操作</div>' +
				'<div class="k-lcm-txt"><a href="javascript:showAppDownDialog(2);">下载手机端APP</a></div>'+
				'</div>';
		$("#k-login-box").append(str1);
	}

	//===========================================================

	function wChange(){
		var dh1=$(window).height();
		var dw1=$(window).width();

		var h1=$(".k-login-top").height()+$(".k-login-middle").height()+$(".k-login-foot").height();//alert(h1);

		if (h1>dh1) {
			$(".k-login-layer").addClass("o1");
		} else {
			$(".k-login-layer").removeClass("o1");
		}
	}

	if (kFrameConfig.loginOnLoadEnd()) {

	} else {

	}
};

function showAppDownDialog(frm){
	var txt;
	if(kFrameConfig['app_client_download_addr']){
		txt = "<div style='text-align:center;padding-top:20px;'><span id='cldown_qrcode' style='display:inline-block;width:130px;height:130px;'></span></div><div style='text-align:center;color:red;padding-top:5px;'>请用移动端设备扫码下载</div>";
	}else{
		txt = "<p style='font-size:14px;color:red;'>暂无资源可供下载</p>";
	}
	if(frm==1){
		kFrameConfig.cancelQrcodeLogin();
		$("#k-qrcode").html("<p class='k-qrcode-invalid'>正在下载客户端<br/>请稍候！</p>");
		$(".k-qrcode-desc,.k-qrcode-link,.k-qrcode-confirm").hide();
	}
	winOpen({
		width: 200,
		height: 200,
		lock: true,
		max: false,
		min: false,
		title: "下载APP",
		content: txt,
		close: function(){
			if(frm==1){
				kFrameConfig.createLoginQrcode();
			}
		}
	});
	if(kFrameConfig['app_client_download_addr'])
		kFrameConfig.createQrcode("#cldown_qrcode",kFrameConfig['app_client_download_addr']);
}

function init_sessionTimeOut(){
	$("html body").css("overflow","auto");//alert(kFrameConfig["loginLogo"]["src"]);
	$(".caption2").css({"background-image":"url('"+kFrameConfig["loginLogo"]["src"]+"')"});
	if (top.location!=self.location) {
		top.location=self.location;
	}
	window.setTimeout(function(){sysReloadLogin();},4000);
};

function sysReloadLogin() {
	//判断是否外壳
	if(browser.versions.android) {
		try {
			window.KHANJS.backLogin();
			return;
		} catch (e) {}
	}
	top.location.href=kFrameConfig["base"];
	return false;
}

function sysLoginDoSubmit(fObj) {
	if (kFrameConfig.loginSubmitDiy) {
		return kFrameConfig.loginSubmit($("#loginForm"));
	} else {
		if($("#TxtUserName").val()==""){
			alert("请输入用户名！");$("#TxtUserName").focus();return false;
		}

		if($("#TxtPassword").val()==""){
			alert("请输入密码！");$("#TxtPassword").focus();return false;
		}

		if(kFrameConfig["loginYZM"].toString().toLowerCase()=="false"){
			if($("#validateCode").val()==""){
				alert("请输入验证码！");$("#validateCode").focus();return false;
			}
		}
		$("#loginLiButton").html("正在登录中……");
		return false;
	}
	return false;
}

//2015/2/6 15:44 lybide	初始定义 再在 k-frame-login.js 里自定义
kFrameConfig=$.extend(true,{},kFrameConfig,{
	loginOnLoadBefore:function(){
		return false;
	},
	loginOnLoadEnd:function(){
		return false;
	},
	loginSubmitDiy: true,//是否自定义登录提交事件
	loginSubmit: function(fObj){//登录提交事件
		if($("#TxtUserName").val()==""){
			alert("请输入用户名！");$("#TxtUserName").focus();return false;
		}
		if($("#TxtPassword").val()==""){
			alert("请输入密码！");$("#TxtPassword").focus();return false;
		}
		if(kFrameConfig["loginYZM"]){
			if($("#validateCode").val()==""){
				alert("请输入验证码！");$("#validateCode").focus();return false;
			}
		}
		$("#userButtonPanel").hide().after('<div class="k-login-inputbox-only"><div class="k-login-submit-msg" id="k-login-submit-msg"><div>正在登录中……</div></div></div>');
		$("#TxtUserName-msg,#TxtPassword-msg").hide();//2017年08月08日 15:54:48 星期二 lybide
		try{
			this.readyToLogin();
		}catch(e){
			alert("操作失败！\n请刷新页面或清理浏览器临时文件再重试。");
			$("#userButtonPanel").show().next().remove();
			return false;
		}
		return false;
	},
	readyToLogin: function(){
		var returnVal = {
			j_username: $("#TxtUserName").val(),
			j_password: $("#TxtPassword").val(),
			j_validate_code: $("#validateCode").val(),
			_spring_security_remember_me : $("#remember_me").attr("checked")?true:false
		};
		$("body").append('<script type="text/javascript" src="'+kui["kuiBase"]+'libs/jsencrypt.min.js"></script>');
		// 监测当前环境是否支持JS加密
		if(window['JSEncrypt']){
			this._doEncryptLogin(returnVal);
		}else{
			this._doAjaxLogin(returnVal);
		}
	},
	_doEncryptLogin: function(loginData){
		var $this = this;
		$.getJSON("user/login/rsa_key.do?_r="+Math.random(),function(resp){
			var rsa_pub_key = null;
			if(resp.success){
				rsa_pub_key = resp.data;
			}
			if(rsa_pub_key){
				var encrypt = new JSEncrypt();
			    encrypt.setPublicKey(rsa_pub_key);
				loginData['j_username'] = encrypt.encrypt(loginData['j_username']);//用户名密文
				loginData['j_password'] = encrypt.encrypt(loginData['j_password']);//密码密文
				if(loginData['j_validate_code'])
					loginData['j_validate_code'] = encrypt.encrypt(loginData['j_validate_code']);//验证码密文
			}
			
			//执行登录
			$this._doAjaxLogin(loginData);
		});
	},
	_doAjaxLogin: function(lformData){
		$.post($("#loginForm").attr("action"),lformData,function(resp){
			if(resp.success) {
				// 检查密码策略
				kFrameConfig.checkLoginStrategy(resp.data);
			} else {
				alert(resp.msg);
				if(kFrameConfig["loginYZM"]){
					$("#k-login-inputyzimg").find("img").attr("src","security/web/validateCodeImg.do?isNew=true&r="+Math.random());
				}
				$("#TxtPassword").focus();
				$("#userButtonPanel").show().next().remove();
				//20150824 zhp 动态刷新验证码
				if(kFrameConfig["loginYZM"]){
					$("#_yzm_img").attr("src","security/web/validateCodeImg.do?isNew=true&r="+Math.random());
				}
			}
		});
	},
	checkLoginStrategy: function(respData){
		if(true == respData["appConfirm"]){
			$("#k-login-box>div").hide();
			$("#k-login-check-mobile").show();
			// 需要app确认，跳转到等待界面，启动轮询
			this.checkLonginConfirmStatus();
		}else{
			this.checkPasswordStrategy(respData);
		}
	},
	checkLonginConfirmStatus: function(){
		$.getJSON("security/authentication/checkLoginConfirm.do", function(resp){
			if(resp.success){
				if(resp.result==-2){
					$.dialog.alert("确认超时，请重新登陆！",function(){
						top.location = $("base").attr("href") + "security/authentication/logout.do";
					});
				}else if(resp.result==-1){
					$.dialog.alert("登录被拒绝，请确认您的凭据是否正确有效！",function(){
						top.location = $("base").attr("href") + "security/authentication/logout.do";
					});
				}else if(resp.result==1){
					kFrameConfig.checkPasswordStrategy(resp.data);
					//top.location = $("base").attr("href") + "user/main.do";
				}else{
					setTimeout(kFrameConfig.checkLonginConfirmStatus,2000);
				}
			}else{
				$.dialog.alert("确认失败，请重新登陆！",function(){
					top.location = $("base").attr("href") + "security/authentication/logout.do";
				});
			}
		});
	},
	cancelQrcodeLogin: function(){
		if(window["qrLoginCheckTimer"]) 
			window.clearTimeout(window["qrLoginCheckTimer"]);
	},
	createLoginQrcode: function(){
		if(window["qrLoginCheckTimer"]) 
			window.clearTimeout(window["qrLoginCheckTimer"]);
		$.getJSON("user/login/qrcode.do",function(resp){
			if(resp.success){
				kFrameConfig.showLoginQrcode(resp.data);
				kFrameConfig.checkQrcodeLoginStatus(resp.data);
			}else{
				$.dialog.alert("创建二维码失败，请稍后重试！");
			}
		});
	},
	showLoginQrcode: function(qcode){
        $("#k-qrcode").empty();
		$(".k-qrcode-desc,.k-qrcode-link").show();
		$(".k-qrcode-confirm").hide();
        var urltxt = $("base").attr("href") + qcode;
        this.createQrcode("#k-qrcode", urltxt);
	},
	createQrcode: function(el, qrcode){
        var ie = $.browser.msie;
        var iever = $.browser.version;
        var dm = document.documentMode; //文档模式
		if (ie && (parseInt(iever) <= 8 || parseInt(dm) <= 8)) {
			$(el).html("<img src='pub/tools/qrcode.jsp?code_text=" + qrcode + "' />");
		} else {
			try {
				$(el).qrcode({
					width : 130,
					height : 130,
					text : qrcode
				});
			} catch (err) {
				$(el).html("<img src='pub/tools/qrcode.jsp?code_text=" + qrcode + "' />");
			}
		}
	},
	checkQrcodeLoginStatus: function(rcode){
		$.getJSON("user/login/qrcode/check.do?rcode=" + rcode, function(resp){
			if(resp.success){
				if(resp.status==1){
					$(".k-qrcode-desc").hide();
					$(".k-qrcode-confirm").show();
				}else if(resp.status==2){
					$(".k-qrcode-confirm").text("登录成功，正在跳转！");
					kFrameConfig._doAjaxLogin({
						j_username: resp.user,
						j_password: rcode
					});
					return;
				}
				window["qrLoginCheckTimer"] = setTimeout(function(){
					kFrameConfig.checkQrcodeLoginStatus(rcode);
				},1000);
			}else{
				kFrameConfig.cancelQrcodeLogin();
				$("#k-qrcode").html("<p class='k-qrcode-invalid'>二维码已失效<br/>请点击刷新！</p>").one("click",kFrameConfig.createLoginQrcode);
				$(".k-qrcode-desc").hide();
				$(".k-qrcode-confirm").hide();
			}
		});
	},
	// 2015-01-15  zhp
	checkPasswordStrategy: function(respData){
		$(".k-login-layer").hide();
		if(respData.pwdModify==0){
			$.dialog.confirm("您的密码已经过期，是否马上设置新密码？",function(y){
				if(y){
					kFrameConfig.modifyUserPassword(respData);
				}else{
					kFrameConfig.afterLoginSuccess(respData);
				}
			});
		}else if(respData.pwdModify==1){
			$.dialog.alert("您的密码已经过期，请重设密码！",function(){
				kFrameConfig.modifyUserPassword(respData);
			});
		}else if(respData.pwdModify==2){
			$.dialog.alert("您的密码是初始密码，请修改！",function(tt){
				kFrameConfig.modifyUserPassword(respData);
			});
		}else{
			kFrameConfig.afterLoginSuccess(respData);
		}
	},
	// 2015-01-15  zhp
	modifyUserPassword: function(respData){
		window.setTimeout(function(){
			$.dialog({
				width: 350,
				height: 250,
				title: "修改密码",
				reSize: false,
				max: false,
				min: false,
				lock: true,
				content: "url:pub/rbac/user_config.jsp",
				data:{callback:function(){
					$.dialog.alert("密码修改成功，请重新登陆！",function(){
						window.location = kFrameConfig["base"]+"security/authentication/logout.do";
					});
				}},
				close: function(){
					window.location = kFrameConfig["base"]+"security/authentication/logout.do";
				}
			});
		},100);
	},
	// 2015-01-15  zhp 修改
	afterLoginSuccess: function(respData){
		//外壳专用 2014/11/13 11:45 lybide
		var isShell=getkIsShell();
		if (isShell) {
			var autoRememberPass=$("#shell-autoRememberPass:checked").val();
			if (autoRememberPass=="1") {
				var base64=new Base64();
				var u=base64.encode($("#TxtUserName").val()||"");
				var p=base64.encode($("#TxtPassword").val()||"");//alert([u,p]);
				window.external.WriteIni("user", "autoRememberPass","1", "confing.user.ini");
				window.external.WriteIni("user", "autoRememberUserName",u, "confing.user.ini");
				window.external.WriteIni("user", "autoRememberUserPass",p, "confing.user.ini");
			} else {
				window.external.WriteIni("user", "autoRememberPass","0", "confing.user.ini");
				window.external.WriteIni("user", "autoRememberUserName","", "confing.user.ini");
				window.external.WriteIni("user", "autoRememberUserPass","", "confing.user.ini");
			}
			var autoLogin=$("#shell-autoLogin:checked").val();
			if (autoLogin=="1") {
				window.external.WriteIni("user", "autoLogin","1", "confing.user.ini");
			} else {
				window.external.WriteIni("user", "autoLogin","0", "confing.user.ini");
			}
			var autoStart=$("#shell-autoStart:checked").val();
			if (autoStart=="1") {
				window.external.WriteIni("user", "autoStart","1", "confing.user.ini");
				window.external.SetAutoStartup(true);
			} else {
				window.external.WriteIni("user", "autoStart","0", "confing.user.ini");
				window.external.SetAutoStartup(false);
			}
			var softAutoHide = window.external.ReadIni("user", "softAutoHide", "confing.user.ini");
			if (softAutoHide=="1") {
				window.external.HideFrameWindow();
			}
		}
		if(kui["ENTRUST_OPEN"]){
			kFrameConfig.entrustLogin(respData);
		}else if(kui["SYS_POSITION_SET"]){
			kFrameConfig.positionLogin(respData);
		}else{
			var baseUrl = "";
			if(kFrameConfig["base"])
				baseUrl = kFrameConfig["base"];
			baseUrl=baseUrl?baseUrl:"";
			location = baseUrl + "user/main.do";
		}
		return false;
	},
	positionLogin:function(respData,positionId){
		$.getJSON("rbac/user/getUserPosition.do",{userId:respData.userId,positionId:positionId},function(pos){
			if(pos.success&&pos.data&&pos.data.length>0){
				if(pos.data.length>1){
					top.$.dialog({
						width: 300,
						height: 130,
						title: "选择岗位",
						reSize: false,
						max: false,
						min: false,
						lock: true,
						content: "url:pub/rbac/position_select.jsp",
						data : {"data" : pos.data},
						cancel:true,
						close:function(){
							location = kFrameConfig["base"]+"security/authentication/logout.do";
							return false;
						},
						ok:function(){
							var data = this.iframe.contentWindow.getPostionId();
							if(data==""||data==undefined||data==null){
								$.dialog.alert("请选择要登录的岗位!");
								return false;
							}else{
								$.getJSON("rbac/position/positionLogin.do",{positionId:data},function(res){
									if(res.success){
										location = kFrameConfig["base"]+"user/main.do";
										return false;
									}else{
										$.dialog.alert("登录失败!");
										location = kFrameConfig["base"]+"security/authentication/logout.do";
										return false;
									}
								})
							}
							return false;
						},okVal:'确定'
					});
				}else{
					$.getJSON("rbac/position/positionLogin.do",{positionId:pos.data[0].id},function(res){
						if(res.success){
							location = kFrameConfig["base"]+"user/main.do";
							return false;
						}else{
							$.dialog.alert("登录失败!");
							location = kFrameConfig["base"]+"security/authentication/logout.do";
							return false;
						}
					})
				}
			}
			else{
				//没有岗位直接登陆哇
				location = kFrameConfig["base"]+"user/main.do";
			}
		})
	},
	entrustLogin:function(respData){
		$.getJSON("entrust/user/getEntrustUser.do",{userId:respData.userId},function(data){
			if(data.success){
				top.$.dialog({
					max:false,min:false,lock:true,width:250,height:100,
					title:'委托登录',
					lock:true,
					reSize: false,
					max: false,
					min: false,
					content:"url:pub/entrust/entrust_user.jsp?userid="+respData.userId,
					button:[{
						name: '委托登录',
						callback: function () {
							var data = this.iframe.contentWindow.getAccount();
							if(data==""||data==undefined||data==null||"{}"==JSON.stringify(data)){
								$.dialog.alert("选择委托项！");
								return false;
							}else{
								$.getJSON("security/authentication/switchUser.do",{entrustId:respData.userId},function(res){
									if(res.success){
										//切换用户
										$.post("j_spring_security_switch_user",{j_username:data.userAccount},function(){
											if(kui["SYS_POSITION_SET"]){
												kFrameConfig.positionLogin(data,data.positionId);
											}else{
												location = kFrameConfig["base"]+"user/main.do";
											}
											return;
										});
									}
								})
							}
							return false;
						}
					},{
						name: '个人登录',
						callback: function () {
							if(kui["SYS_POSITION_SET"]){
								kFrameConfig.positionLogin(respData);
							}else{
								location = kFrameConfig["base"]+"user/main.do";
							}
							return false;
						}
					}],close:function(){
						window.location = kFrameConfig["base"]+"security/authentication/logout.do";
						return false;
					}
				});
				return false;
			}
			else{
				if(kui["SYS_POSITION_SET"]){
					kFrameConfig.positionLogin(respData);
				}else{
					location = kFrameConfig["base"] + "user/main.do";
				}
			}
		})
	},
	end:null
});