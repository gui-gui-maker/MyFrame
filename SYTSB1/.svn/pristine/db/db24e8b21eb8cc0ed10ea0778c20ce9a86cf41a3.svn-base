
//13-2-5 下午4:03 lybide%>
//13-3-28 下午2:41 lybide 登录皮肤载入

//=======================================================================================
//document.write('<script src="k/kui/frame/login-frame.js"><\/script>');
//=======================================================================================
var LOGIN_DTL_LENGTH=0;
kFrameConfig=$.extend(true,{},kFrameConfig,{
	loginOnLoadBefore:function(){//登录页面载入事件 2013-01-31 15:59:59 lybide

		//皮扶切换
		//================================================================================
		/*var cssChange=$("body").append('<div id="cssChangePanel" style="position:absolute;right:5px;top:5px;height:16px;z-index:10;border:1px solid #96c2f1;background:#eff7ff;padding:5px;text-align:center;"><a href="javascript:void(0);" skin="1">登录样式1</a>　<a href="javascript:void(0);" skin="2">登录样式2</a>　<a href="javascript:void(0);" skin="3">登录样式3</a>　<a href="javascript:void(0);" skin="5">登录样式5</a>　<a href="javascript:void(0);" skin="close">[关闭]</a></div>');
		cssChange.find("a").click(function(){
			if ($(this).attr("skin")=="close") {
				$(this).parent().hide("flow");
			} else {
				window.location=kFrameConfig["base"]+"app/index.jsp?skin="+$(this).attr("skin");return false;
			}
		});*/
		//================================================================================

		var skinKey=$.kh.request("skin") || "1";

		//开启验证码
		//kFrameConfig["loginYZM"]=true;
		if(kFrameConfig["loginYZM"]){
			var yzm=$('<img width="80" height="37" src="security/web/validateCodeImg.do?isNew=true" id="_yzm_img" />');
			yzm.click(function(){
				var $this=$(this);
				$this.attr("src","security/web/validateCodeImg.do?isNew=true"+"&r="+Math.random());
			})
			$("#userCodePanel").show();
			$("#k-login-inputyzimg").html(yzm);
			$("#userButtonPanel").show().addClass("is-yzm");//登录，取消等，显示
			$("#breset-plane").hide();
			//$("#userButtonPanel").html("正在登陆系统……");
		} else {
			$("#userCodePanel").hide().html("");//验证码
			//$("#userButtonPanel").css("display","block");//登录，取消等，关闭
			$("#userButtonPanel").show();
		}

		//安全证书登录
		//kFrameConfig["loginCert"].isCert=true;sdeee
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
			var isCertStr1='<div class="l-userName-zs"><div>'+(dm1[ca1]) + ""
			 + cretUName + ""+'</div></div>';
			//isCertStr1+='<input id="TxtUserName" type="hidden" value="admin" name="j_username"title="请输入您的用户名"/>';
			$("#userNamePanel").html(isCertStr1);
			$("#TxtUserName").attr("readonly",true);
            $(".k-login-inputname").hide();
			$("body").addClass("is-cert");
		}

		//自动登录
		if(kFrameConfig["autoLogin"]==true){
			$("#userButtonPanel").css({"margin":"40px 0px 0px 90px","position":"relative"});
			$(".k-login-inputbox").css({"margin":"0px 0px 10px 0px"});
			$("#userPassPanel").css({"position":"relative"});
			$("#userPassPanel").append('<div id="iCKwoDiv" style="border:0px solid #FF0000;position:absolute;left:90px;top:40px;"><input type="checkbox" name="remember_me" value="" id="remember_me"/> <label for="remember_me">两周内自动登录</label></div>');
		}

		//系统名称
		$("#sysNamePanel span").html(kFrameConfig["loginSub"]);//标题
		$("#loginCopy span").html(kFrameConfig["copyCom"]);//版权所有
		$("#loginTech span").html(kFrameConfig["techCom"]);//技术支持

		if (skinKey=="5" || kFrameConfig["loginStyle"]=="login5") {
			return;
		}

		var bs=getBrowserInfo();//2013-6-5 下午9:25 lybide 新增浏览器检测

		function _fLeftRightBg() {
			var oMain=$("#mainBg");
			var nWidth = (oMain.width() - 900) / 2;
			if(oMain.width() < 900){
				return;
			}
			var oDvBg1 = $('#dvbgleft');
			var oDvBg2 = $('#dvbgright');
			if (oDvBg1.length>0 && oDvBg2.length>0) {
				oDvBg1.width(nWidth);
				oDvBg2.width(nWidth);
			}
		}

		if (skinKey=="1") {
			//kFrameConfig["loginBtImageName"]="k/kui/public/images/login1/dt1.png";
			if (kFrameConfig["loginBtImageName"]) {
				$(".k-login-custom_img").append('<div class="k-login-dt-image-loading" id="k-login-dt-image-loading"><div><span>loading...</span></div></div>');
				login1ImageDt();
			}
			$(".k-login-layout-content").after('<div class="k-login-layout-left" id="dvbgleft"></div>');
			$(".k-login-layout-content").after('<div class="k-login-layout-right" id="dvbgright"></div>');
			//_fLeftRightBg();
		}



		var dtClickCount=0;
		var keys1Old=0;
		var loopkeys1=1;
		//var LOGIN_DTL_LENGTH=0;
		var loopExe1;
		function login1ImageDt() {
			var imgObj=kFrameConfig["loginBtImageName"] || "";
			var dlImgPane='<div id="k-login-dt-image" class="k-login-dt-image"><ul></ul></div>';
			$(".k-login-custom_img").append(dlImgPane);
			var ul=$("#k-login-dt-image ul");
			if (imgObj.indexOf(",")!=-1) {
				//焦点 2014年10月18日 14:50:16 星期六 lybide
				var dlImgCount='<div id="k-login-dt-image-count" class="k-login-dt-image-count"><ul></ul></div>';
				$(".k-login-custom_img").append(dlImgCount);
				var ul2=$("#k-login-dt-image-count ul");

				imgObj=imgObj.split(",");
				for (var i = 0, l=imgObj.length; i < l; i++) {
					var imgLi=$('<li></li>').css("background-image","url('"+imgObj[i]+"')");
					ul.append(imgLi);
					ul2.append('<li><div><a href="javascript:void(0);" idKeys="'+i+'"><span></span></a></div></li>');
				}
				LOGIN_DTL_LENGTH=l;
				//ul2a.eq(0).click();
			} else {
				LOGIN_DTL_LENGTH=1;
				var imgLi=$('<li></li>').css("background-image","url('"+imgObj+"')");
				ul.append(imgLi);

			}
			//alert(LOGIN_DTL_LENGTH+"====");
			//var onea1=$("#k-login-dt-image-count ul li a:first").click();

		}

		//2014年10月18日 18:04:26 星期六 lybide 分离
		function wefsdfwe() {
			var ul2=$("#k-login-dt-image-count ul");
			//给焦点图绑定事件 2014年10月18日 15:23:26 星期六 lybide
			var ul2a=ul2.find("a").click(function(){//mouseover click
				//$("#k-login-dt-image ul li").stop(null,true,true);
				var _this=$(this);
				var keys1=_this.attr("idKeys");
				keys1=parseInt(keys1);
				//alert([loopkeys1,keys1]);
				//keys1Old=keys1-1;
				//if (keys1==0) {
				//	keys1Old=l-1;
				//}
				loopkeys1=keys1;//alert(loopkeys1);
				sdfsdf(loopkeys1);
				clearInterval(loopExe1);
				loopkeys1++;//2014年10月18日 17:46:30 星期六 lybide
				asdfwefwef();
			});
		}

		function asdfwefwef(next) {
			loopExe1=setInterval(function(){
				if (next) {
					loopkeys1=next;
				}//alert([loopkeys1,LOGIN_DTL_LENGTH]);
				if (loopkeys1>=LOGIN_DTL_LENGTH) {
					loopkeys1=0;
				}
				sdfsdf(loopkeys1);
				loopkeys1++;
			},3000);
		}

		function sdfsdf(keys1) {
			$("#k-login-dt-image ul li").stop(null,true,true);
			if (dtClickCount>0) {
				$("#k-login-dt-image ul li:eq("+keys1Old+")").animate({left:-580},400,function(){
					$(this).css({"left":"580px"});//2014年10月18日 14:31:27 星期六 lybide
				});
			}

			//$("#k-login-dt-image ul li").hide();
			var bb=$("#k-login-dt-image ul li:eq("+keys1+")")
			if (bs.ie && bs.ieversion<=8) {
				//bb.show();
				bb.show().animate({left:0},400);
			} else {
				//bb.fadeIn();
				bb.show().animate({left:0},400);
			}
			var aAll=$("#k-login-dt-image-count ul li a").removeClass("hover");
			aAll.eq(keys1).addClass("hover");
			keys1Old=keys1;
			dtClickCount++;
		}

		//jQuery页面所有元素载入后执行事件
		$(window).load(function(){
			if (kFrameConfig["loginBtImageName"]) {
				//$(".k-login-layout-content").css("background-image","url('"+kFrameConfig["loginBtImageName"]+"')");
				//2014年10月18日 13:10:03 星期六 lybide
				//return;
				$("#k-login-dt-image-loading").hide();
				if (LOGIN_DTL_LENGTH>1) {
					wefsdfwe()
					sdfsdf(0);
					asdfwefwef();
				} else {
					var one1=$("#k-login-dt-image ul li:first");
					one1.show().animate({left:0},400);
				}
			}
		});

		//登录框内右上角图片区域动画 2013-6-5 下午9:25 lybide
		if ((skinKey=="1" || skinKey=="2" || skinKey=="")) {
			var ldhArr=kFrameConfig["loginBtFSArr"] || "";
			if (ldhArr.indexOf(",")!=-1) {
				ldhArr=ldhArr.split(",");
				var ldhArrLen=ldhArr.length || 0;
				if (ldhArrLen>1) {
					if (kFrameConfig["loginBtFS"]) {
						if (bs.ie && bs.ieversion<7) {

						} else {
							var ldhKey=0;
							$("#sysNamePanel").css("background-image","url('"+ldhArr[ldhKey]+"')");
							ldhKey++;
							setInterval(function(){
								$("#sysNamePanel").css("background-image","url('"+ldhArr[ldhKey]+"')");
								if (ldhKey<ldhArrLen) {
									ldhKey++;
								}
								if (ldhKey==ldhArrLen) {
									ldhKey=0;
								}
							},5000);
						}
					} else {
						$("#sysNamePanel").css("background-image","url('"+ldhArr[0]+"')");
					}
				}
			} else {
				$("#sysNamePanel").css("background-image","url('"+ldhArr+"')");
			}
			if (bs.ie && bs.ieversion<=8) {
				$(".k-login-box").addClass("k-login-box-b-ie8");
			}
		}

		//系统标题
		//$(".k-login-top-logo").css(this.loginLogo[loginCssSrc]);//alert(JSON.stringify(this.loginLogo[loginCssSrc]));

		$(".k-login-top").append('<div class="k-login-top-logo-text" id="sysNameText"><div><a><span>'+kFrameConfig["name"]+'</span></a></div></div>');
		if (kFrameConfig["logoMode"]=="img") {
			$(".k-login-top-logo").css("background","url('"+this.loginLogo["src"]+"') no-repeat center center");
			if (this.loginLogo["style"]) {
				$(".k-login-top-logo").css(this.loginLogo["style"]);
			}
		} else {
			$(".k-login-top-logo").hide();
			$("#sysNameText").show();
		}

		//系统出错提示
		if ($("#loginReturnMsg").length>0) {
			//alert($("#loginReturnMsg").html());
			$.dialog({max:false,min:false,lock:true,width:350,height:100,ok:function(){$("#TxtUserName").focus();},title:'系统提示',content:$("#loginReturnMsg").html()});
		} else {
			$("#TxtUserName").focus();
		}

		if ($.browser.msie && parseFloat($.browser.version) <=6) {
			$(".k-login-inputbtn button").hover(function(){$(this).addClass("k-login-inputbtn-button-hover");},function(){$(this).removeClass("k-login-inputbtn-button-hover");});
		}


		//右上角系统说明图标
		//$(".k-login-top-logo-btnbox").css({"width":"450px","border":"1px solid #FF0000"});
		/*$(".k-login-top-logo-btnbox").append('<div id="question"><a href="javascript:void(0);"><span>常见问题</span></a></div>');
		$(".k-login-top-logo-btnbox").append('<div id="ckeckHelp"><a href="javascript:void(0);"><span>点击帮助</span></a></div>');
		$(".k-login-top-logo-btnbox").append('<div id="indexUrl"><a href="javascript:void(0);"><span>系统首页</span></a></div>');
		$(".k-login-top-logo-btnbox").append('<div id="aboutUs"><a href="javascript:void(0);"><span>关于我们</span></a></div>');
		$(".k-login-top-logo-btnbox a").click(function(){
			$.dialog({max:false,min:false,lock:true,ok:function(){$("#TxtUserName").focus();},title:$(this).text(),content:"资料建设中。"});
		});
		$("#question").css({"background-position":"0px 0px"});
		$("#ckeckHelp").css({"background-position":"-64px 0px"});
		$("#indexUrl").css({"background-position":"-128px 0px"});
		$("#aboutUs").css({"background-position":"-192px 0px"});*/



		//绑定关闭登录框，遮罩层，键盘事件
		$(document).keydown(function(e){
			e=$.kh.getKeyDown(e);
			var t= e.eType;var k1= e.keyCode;

			if ((t=="password" || t=="text" || t=="textarea")) {//输入框中，不执行
				return true;
			}
			if (k1==83) {//s键
				$(".k-login-box").toggleClass("hide");
				$(".k-login-btnbox").toggleClass("hide");
			}
			if (k1==77) {//m键
				$(".k-login-custom_mask").toggleClass("hide");
			}
			if (k1==68) {//d键
				if ($(".k-login-style").css("display")!="none") {
					$(".k-login-style").css("display","none");
				} else {
					$(".k-login-style").css("display","");
				}
			}
			if (k1==67) {//c键
				$.cookie("loginBgKeys",null,{path:"/"});
			}
			if (k1==37) {
				$(".k-login-style-select-prev a").click();
			}
			if (k1==39) {
				$(".k-login-style-select-next a").click();
			}
		});

		//登录底图切换效果
		var bgKeys=0;
		var bgImgLen=0;
		var cookies_loginBgKeys=null;//记住登录底图
		var bgImg=[];//图片地址
		bgImg.push({"default":true,imgX:"app/public/skins/default/stylebg/style1.jpg",imgD:"app/public/skins/default/stylebg/style1d.png"});
		bgImg.push({imgX:"app/public/skins/default/stylebg/style2.jpg",imgD:"app/public/skins/default/stylebg/style2d.jpg"});
		bgImg.push({imgX:"app/public/skins/default/stylebg/style3.jpg",imgD:"app/public/skins/default/stylebg/style3d.jpg"});
		bgImg.push({imgX:"app/public/skins/default/stylebg/style4.jpg",imgD:"app/public/skins/default/stylebg/style4d.jpg"});
		bgImg.push({imgX:"app/public/skins/default/stylebg/style5.jpg",imgD:"app/public/skins/default/stylebg/style5d.jpg"});
		bgImg.push({imgX:"app/public/skins/default/stylebg/style6.jpg",imgD:"app/public/skins/default/stylebg/style6d.jpg"});
		bgImg.push({imgX:"app/public/skins/default/stylebg/style7.jpg",imgD:"app/public/skins/default/stylebg/style7d.jpg"});

		//$(".k-login-box").after('<div class="k-login-box k-login-box-base"></div>')

		function bgLoop(i){
			bgKeys=i;//alert(bgKeys);
			$(".k-login-custom_img").stop();//停止所有现有动画
			//clearInterval(bgImageCh);
			//var className=$(this).attr("id").toString();
			$(".k-login-custom_img").css({"background-image":"url('"+bgImg[i].imgD.toString()+"')"});
			$(".k-login-custom_img").css({"width":"1%",height:"1%"}).animate({width:"100%",height:"100%"},500,function(){}).show();
			//$(".k-login-custom_mask").hide();
			//$(".k-login-custom_mask div").addClass(className).hide().fadeIn();
			//if ($.browser.msie && parseFloat($.browser.version) <=6) {
				//alert("您的浏览器不支持动态换图，请升级您的浏览器。")
				//DD_belatedPNG.fix(".k-login-custom_mask div");
				//alert($(".k-login-custom_mask div").attr("class"));
				//$(".k-login-custom_mask div").css({"position":"absolute","top":"10",left:"10","z-index":8})
			//}
			//13-2-4 下午2:34 lybide
			$(".k-login-style-select-prev a").attr("bgKeys",bgKeys==0?bgImgLen-1:bgKeys-1);
			$(".k-login-style-select-next a").attr("bgKeys",(bgKeys+1)>=bgImgLen?0:bgKeys+1);//alert((bgKeys+1)+"="+bgImgLen);
			$(".k-login-style_btn").removeClass("k-login-style_btn-hover").end().find("[bgKeys="+i+"]").addClass("k-login-style_btn-hover");
		};
		if ($(".k-login-style").css("display")!="none") {
			$.each(bgImg,function(i,obj){
				cookies_loginBgKeys=$.cookie("loginBgKeys");
				var defaultKey=(obj["default"]==true?"1":"0");
				if (cookies_loginBgKeys!==null) {
					if (cookies_loginBgKeys.toString()==i.toString()) {
						defaultKey=1;
					}
				}
				$(".k-login-style").append('<div class="k-login-style_btn" id="loginBGStyle'+i+'" default="'+defaultKey+'" bgKeys="'+i+'" imgD="'+obj.imgD+'" style="background-image:url(\''+obj.imgX+'\')"></div>');
				$(".k-login-style").css({"width":"200px","bottom":"120px"});//控制小图片显示区域的宽度
				bgImg[i].bgKeys=i;
			});
			bgImgLen=bgImg.length;//alert($(".k-login-style").html())
			$(".k-login-style").hide();

			if ($.browser.msie && parseFloat($.browser.version) <=6) {
				$(".k-login-style div").hover(function(){$(this).addClass("k-login-style_btn-hover");},function(){$(this).removeClass("k-login-style_btn-hover");});
			}
			$(".k-login-layout-content").append('<div class="k-login-style-select"><div class="k-login-style-select-confirm"><a href="javascript:void(0);"><span>记住选择</span></a></div><div class="k-login-style-select-prev"><a href="javascript:void(0);" title="上一个背景图"><span>上一个</span></a></div><div class="k-login-style-select-next"><a href="javascript:void(0);" title="下一个背景图"><span>上一个</span></a></div></div>');
			$(".k-login-style div").click(function(){
				var bgKeys=parseInt($(this).attr("bgKeys"));
				bgLoop(bgKeys);
				//clearInterval(bgImageCh);
			}).end().find("[default='1']").click();


			/*var bgImageCh=setInterval(function(){
				$(".k-login-style-select-next a").click();
			},1000);*/

			$(".k-login-style-select-confirm a").click(function(){
				//alert("确定选择");
				$.cookie("loginBgKeys",bgKeys,{expires:7,path:"/",domain:"",secure:false});
				$(".k-login-style-select-confirm").hide();
			});
			$(".k-login-style-select-prev a").click(function(){
				var bgKeys=parseInt($(this).attr("bgKeys"));
				bgLoop(bgKeys);
				$(".k-login-style-select-confirm").show();
			});
			$(".k-login-style-select-next a").click(function(){
				var bgKeys=parseInt($(this).attr("bgKeys"));
				bgLoop(bgKeys);
				$(".k-login-style-select-confirm").show();
			});
			if (cookies_loginBgKeys!==null) {
				$(".k-login-style-select-confirm").hide();
			}
		}

		//多个子系统切换
		var cookies_selectSystem=null;
		if ($(".k-login-btnbox").is(":visible")) {
			var sysMenu=[];
			sysMenu.push({"default":true,id:"sq1",text:"社区(村)管理系统",titleImg:"app/public/skins/default/login3/title_img_1.png",sysIcon:"app/public/skins/default/login3/login_btn1.png"});
			sysMenu.push({id:"sq2",text:"乡镇管理系统",titleImg:"app/public/skins/default/login3/title_img_2.png",sysIcon:"app/public/skins/default/login3/login_btn2.png"});
			sysMenu.push({id:"sq3",text:"民政局管理系统",titleImg:"app/public/skins/default/login3/title_img_3.png",sysIcon:"app/public/skins/default/login3/login_btn3.png"});
			sysMenu.push({id:"sq4",text:"福利院管理系统",titleImg:"app/public/skins/default/login3/title_img_4.png",sysIcon:"app/public/skins/default/login3/login_btn4.png"});
			sysMenu.push({id:"sq5",text:"慈善管理系统",titleImg:"app/public/skins/default/login3/title_img_5.png",sysIcon:"app/public/skins/default/login3/login_btn5.png"});
			sysMenu.push({id:"sq6",text:"敬老院管理系统",titleImg:"app/public/skins/default/login3/title_img_6.png",sysIcon:"app/public/skins/default/login3/login_btn6.png"});
			sysMenu.push({id:"sq7",text:"公墓管理系统",titleImg:"app/public/skins/default/login3/title_img_7.png",sysIcon:"app/public/skins/default/login3/login_btn7.png"});
			sysMenu.push({id:"sq8",text:"殡葬管理系统",titleImg:"app/public/skins/default/login3/title_img_8.png",sysIcon:"app/public/skins/default/login3/login_btn8.png"});

			$.each(sysMenu,function(i,obj){
				cookies_selectSystem=$.cookie("loginSelectSys");
				var defaultKey=(obj["default"]==true?"1":"0");
				if (cookies_selectSystem!==null) {
					if (cookies_selectSystem.toString()==obj["id"].toString()) {
						defaultKey=1;
					}
				}
				$(".k-login-btnbox ul").append('<li id="'+obj.id+'" default="'+defaultKey+'">' +
					'								<div class="k-login-btnimg" style="background-image:url('+obj.sysIcon+')"></div>' +
					'								<div class="k-login-btnname">' +
					'									<div><span>'+obj.text+'</span></div>' +
					'								</div>' +
					'							</li>');

			});
			if ($.browser.msie && parseFloat($.browser.version) <=6) {
				$(".k-login-btnbox li").hover(function(){$(this).addClass("k-login-btnbox-li-hover");},function(){$(this).removeClass("k-login-btnbox-li-hover");});
			};
			$(".k-login-btnbox li").click(function(){
				var id=$(this).attr("id");
				var selectObj=null;
				$.each(sysMenu,function(i,obj){
					if (obj["id"].toString()==id.toString()) {
						selectObj=obj;return false;
					}
				});
				$(".k-login-btnbox li").removeClass("k-login-btnon");
				$(this).addClass("k-login-btnon");
				$("#sysNamePanel").html(selectObj["text"]);
				$(".k-login-box-title").css({"background-image":'url('+selectObj.titleImg+')'});
				cookies_selectSystem=$(this).attr("id");
				$.cookie("loginSelectSys",cookies_selectSystem,{expires:7,path:"/",domain:"",secure:false});
			}).end().find("[default='1']").click();
			$(".k-login-btnbox li").first().css({"border-radius":"5px 0px 0px 0px"}).end().last().css({"border-radius":"0px 0px 0px 5px"});
		}

		//外壳程序专用 2014/11/13 10:45 lybide
		var isShell=getkIsShell();
		//isShell=true;
		if (isShell) {
			var shellStr='<div class="k-login-isshell" id="k-login-isshell">'+
				'<span class="a" title="记住密码可以在下次自动填写用户名和密码！">'+
				'<input type="checkbox" name="shell-autoRememberPass" id="shell-autoRememberPass" value="1"/><label for="shell-autoRememberPass">记住密码</label>'+
				'</span>'+
				'<span class="b" title="下次自动登录！">'+
				'<input type="checkbox" name="shell-autoLogin" id="shell-autoLogin" value="1"/><label for="shell-autoLogin">自动登录</label>'+
				'</span>'+
				'<span  class="c" title="下次开机之后自动启动！">'+
				'<input type="checkbox" name="shell-autoStart" id="shell-autoStart" value="1" /><label for="shell-autoStart">自动启动</label>'+
				'</span>'+
				'</div>';
			shellStr=$(shellStr);
			$("#userButtonPanel").after(shellStr);
			if (kFrameConfig["loginYZM"]) {
				$(".b",shellStr).hide();
				window.external.WriteIni("user", "autoLogin","0", "confing.user.ini");
			}
			var setting = {};
			var autoStart=window.external.ReadIni("user", "autoStart", "confing.user.ini");
			setting["autoStart"]=autoStart;
			var autoLogin=window.external.ReadIni("user", "autoLogin", "confing.user.ini");
			setting["autoLogin"]=autoLogin;
			var autoRememberPass=window.external.ReadIni("user", "autoRememberPass", "confing.user.ini");
			setting["autoRememberPass"]=autoRememberPass;
			for (var item in setting) {
				if ((item=="autoRememberPass" || item=="autoLogin" || item=="autoStart") && setting[item]=="1") {
					$("#shell-"+item).attr("checked",true);
				}
			};
			if (autoLogin=="1" || autoRememberPass=="1") {
				//var oneExe1=setTimeout(function(){
					var base64=new Base64();
					if (!kFrameConfig["loginCert"]["isCert"]) {
						var autoRememberUserName=window.external.ReadIni("user", "autoRememberUserName", "confing.user.ini");
						autoRememberUserName=base64.decode(autoRememberUserName);
						$("#TxtUserName").val(autoRememberUserName);
					}
					var autoRememberUserPass=window.external.ReadIni("user", "autoRememberUserPass", "confing.user.ini");
					autoRememberUserPass=base64.decode(autoRememberUserPass);
					//alert([autoRememberUserName,autoRememberUserPass])
					$("#TxtPassword").val(autoRememberUserPass);
				//},10);
			}
			if (autoLogin=="1") {
				var oneExe1=setTimeout(function(){
					$("#loginForm").submit();
				},50);
			}
		}

		return false;
	},
	loginOnLoadEnd:function(){
		//登录页面载入事件
		//添加登录表单回车键事件，因为登录按钮没有使用type='submit'，因此需要自行绑定
		/*$("#loginForm input[type!='button']").bind("keyup",function(e){
			e= $.kh.getKeyDown(e)
			var t= e.eType;var k1= e.keyCode;
			if(k1==108 || k1==13){
				//sysLoginDoSubmit($("#loginForm"));
				$(".k-login-inputbtn button:first").click();return false;
			}
		});*/
		return false;
	},

	end:null
});

$(function(){//jQuery页面载入事件
	$("#oSystemName span").html(kFrameConfig["name"]);
});