/**
 * Created by lybide on 2017/6/24.
 */

/* 框架函数 2017年06月16日 10:05:22 星期五 lybide */

kFrameConfig.mainEndOnload=function(){
	//return;
	//mPanelDispay({panel:"left",display:false});
	//mPanelDispay({panel:"top",display:true});
	//$(".m-user-info-name div").html(loginUserName);

	var userHeadImage=kFrameConfig.user.icon.replace("default.gif","default.png");

	var str1='';
	str1+='<div id="userinfodrop" class="userinfodrop">';
	str1+='	<div class="avatar">';
	//str1+='		<a class="ihead" href="javascript:void(0);"><img src="'+userHeadImage+'" alt=""></a>';
	//userHeadImage="http://img6.cache.netease.com/photo/0001/2017-07-11/CP230CIE6VVV0001.jpg";
	str1+='		<a class="ihead" href="javascript:void(0);" style="background-image:url(\''+userHeadImage+'\')"></a>';
	str1+='		<div class="changetheme" id="changetheme">改变主题:';
	str1+='			<br>';
	str1+='			<a class="fdefault" class1="fdefault" title="默认"><span></span></a>';
	str1+='			<a class="blueline" class1="blueline" title="蓝色"><span></span></a>';
	str1+='			<a class="greenline" class1="greenline" title="绿色"><span></span></a>';
	//str1+='			<a class="contrast" class1="contrast" title="高反差"></a>';
	str1+='			<a class="pinkline" class1="pinkline" title="粉色"><span></span></a>';
	str1+='			<a class="custombg" class1="custombg" title="透明背景"><span></span></a>';
	str1+='			<a class="cyanline" class1="cyanline" title="青色"><span></span></a>';
	str1+='			<a class="redline" class1="redline" title="红色"><span></span></a>';
	str1+='			<a class="highline" class1="highline" title="高光色"><span></span></a>';
	str1+='			<a class="kyunline" class1="kyunline" title="黑色"><span></span></a>';
	str1+='			<a class="brownline" class1="brownline" title="褐色"><span></span></a>';
	str1+='		</div>';
	str1+='	</div>';
	str1+='	<!--avatar-->';
	str1+='	<div class="userdata">';
	str1+='	<h4>'+loginUserName["name"]+'</h4>';
	str1+='	<span class="email">'+loginUserName["org"]+'</span>';
	str1+='		<ul>';
	str1+='			<li><a href="javascript:void(0);" keys="topMIndex" id="topMIndex">系统主页</a></li>';
	str1+='			<li><a href="javascript:void(0);" keys="seUserPass">修改密码</a></li>';
	//var userSkinSwitch=kui["userSkinSwitch"]!==false ? true : kui["userSkinSwitch"];
	if (kui["userSkinSwitch"]) {
		str1 += '			<li><a href="javascript:void(0);" keys="setSoftSkin" id="topMSet">个性化管理</a></li>';
	}
	if(kui["ENTRUST_OPEN"]) {
		str1 += '			<li><a href="javascript:void(0);" keys="setEntrust">职权委托</a></li>';
	}
	if(kui["SYS_POSITION_SET"]) {
		str1 += '			<li><a href="javascript:void(0);" keys="setPosition">岗位设置</a></li>';
	}
	str1+='			<li><a href="javascript:void(0);" keys="topMOutSystem" id="topMOutSystem">退出系统</a></li>';
	str1+='		</ul>';
	str1+='	</div>';
	str1+='	<!--userdata-->';
	str1+='</div>';
	$("#mUserSetMenu").append(str1);
	$("#mcsfw1").appendTo("#userinfodrop").prepend("字体大小：<br>");
	if (kui["sysMsgSwitchShowIcon"]) {
		$("#sys-message-bar").show();
	}
	//return;

	$("#changetheme a").click(function(){
		$('#changetheme a').removeClass("hover");
		$(this).addClass("hover");
		var c = $(this).attr('class1');
		if($('#addonstyle').length == 0) {
			if(c != 'default') {
				//$('head').append('<link rel="stylesheet" type="text/css" href="k/kui/skins/extflat/css/style.'+c+'.css" id="addonstyle"/>');
				$.cookie.set("addonstyle",c);//Cookies.setCookie("addonstyle",c);
			}
		} else {
			if(c != 'default') {
				//$('#addonstyle').attr('href','k/kui/skins/extflat/css/style.'+c+'.css');
				$.cookie.set("addonstyle",c);//Cookies.setCookie("addonstyle",c);
			} else {
				//$('#addonstyle').remove();
				$.cookie.set("addonstyle",null);//Cookies.setCookie("addonstyle","");
			}
		}
		window.location.reload();//2017年07月12日 17:35:40 星期三 lybide
	});
	var addonstyle=$.cookie.get("addonstyle") || "fdefault";//Cookies.getCookie("addonstyle");
	if (addonstyle) {
		$("#changetheme a[class="+addonstyle+"]").addClass("hover");
	}

	//右上角系统图标
	var str1='';
	//str1+='<li><a id="xgmm" keys="seUserPass" href="javascript:void(0);" style="background-position:-2040px 0px;" class="m-system-item-a" title="修改密码"><span>修改密码</span></a></li>';$("#mUserInfo").css("right","200px");
	str1+='<li><a id="topMIndex" keys="topMIndex" href="javascript:void(0);" style="background-position:0px 0px;" class="m-system-item-a" title="返回首页"><span>首页</span></a></li>';
	var userSkinSwitch=kui["userSkinSwitch"]!==false ? true : kui["userSkinSwitch"];
	if (userSkinSwitch) {
		str1+='<li><a id="topMSet" keys="setSoftSkin" href="javascript:void(0);" style="background-position:-72px 0px;" class="m-system-item-a"><span>设置</span></a>'+mMBTMenuStr('','<!--<a href="javascript:void(0);" keys="seUserPass">修改密码</a>--><a href="javascript:void(0);" keys="setSoftSkin">个性化管理</a><!--<a href="javascript:void(0);" keys="setUserHead">更改头像</a>-->')+'</li>';
	}
	str1+='<li><a href="javascript:void(0);" style="background-position:-48px 0px;" class="m-system-item-a" title="退出系统"><span>退出系统</span></a></li>';
	//$(".m-system-item ul").append(str1);

	//头像下拉菜单
	var temp = '<a href="javascript:void(0);" keys="seUserPass">修改密码</a>';
	if(kui["ENTRUST_OPEN"]){
		temp = temp +'<a href="javascript:void(0);" keys="setEntrust">委托设置</a>';
	}
	if(kui["SYS_POSITION_SET"]){
		temp = temp +'<a href="javascript:void(0);" keys="setPosition">岗位设置</a>'
	}
	str1=''+mMBTMenuStr('',temp)+'';
	//$(".user-set-menu").append(str1);
	//$("#sysMain").append(str1);

	//头都右上角菜单事件绑定
	$(".userdata a").click(function(){
		var keys=$(this).attr("keys");//alert(keys);
		if (keys=="topMIndex") {
			welComeIndexShow();
		} else if (keys=="setUserIndividuation" || keys=="seUserPass") {
			winOpen({
				width:450,
				height:300,
				lock:true,
				title:"修改密码",
				content:"url:pub/rbac/user_config.jsp"
			});
		} else if (keys=="setUserHead") {

		} else if(keys=="setEntrust"){
			winOpen({
				width:450,
				height:250,
				lock:true,
				title:"委托设置",
				content:"url:pub/entrust/entrust_detail.jsp"
			});
		}else if(keys=="setPosition"){
			winOpen({
				width:300,
				height:170,
				lock:true,
				max:false,
				min:false,
				title:"岗位设置",
				parent:api,
				content:"url:pub/rbac/user_position_detail.jsp"
			});
		}
		else if (keys=="setSoftSkin") {
//				top.$.dialog({
//					width:600,
//					height:500,
//					lock:true,
//					title:"系统界面设置",
//					content:"url:pub/rbac/user_skin.jsp?tabId=2"
//				});
			//add by alibaba 2014-06-12
			//if(!window["_userSetInit"]){
			//    $.getScript("k/kui/public/sys-set/userset.js", function(){
			//        window["_userSetInit"]=true;
			//        userSetInit();
			//    });
			//}else{
			userSetInit();
			//}
		} else if (keys=="topMOutSystem") {
			//2014/12/9 11:04 lybide 新增外壳退出
			if (getkIsShell()) {
				var pwKeys=window.external.GetPopupWindowKeywords();
				pwKeys=pwKeys.split(";");
				for (var i = 0, l=pwKeys.length; i < l; i++) {
					window.external.ClosePopup(pwKeys[i]);
				}
				window.external.StopTrayFlicker();//取消闪烁
			}

			//清除SSL状态
			try{
				if(window.location.href.indexOf("https")==0 && window.document.execCommand){
			        document.execCommand('ClearAuthenticationCache');
					window.location=kFrameConfig["base"];
			    }else{
					window.location=kFrameConfig["base"]+"security/authentication/logout.do";
			    }
			}catch(e){
			}
		}
		return false;
	});

	return false;
}

//框架面版变换
function mPanelDispay(obj,ckeys) {
	if (obj["panel"]=="left") {
		if (obj["close"]) {//13-4-16 下午9:36 lybide
			$("#mFoldLeft").hide();
		} else {
			$("#mFoldLeft").show();
		}
		var width=obj["width"] || kFrameConfig["menu"]["style1Width"];
		//console.log($("#mMenu2").width()>width,$("#mMenu2").width(),width,obj["width"],kFrameConfig["menu"]["style1Width"]);
		if ($("#mMenu2").hasClass("fix-width")) {//2017年06月19日 09:48:53 星期一 lybide
			width=$("#mMenu2").width();
		}
		if (obj["display"]) {
			$("#mFoldLeft").attr("display","0");
			$("#mFoldLeft").removeClass("m-fold-left2");
			$("#mFoldLeft").attr("title","收缩菜单工作区");
			$("#mMenu2").css({"width":width+"px"});
			$("#mMenu2").show();
			$("#framecenter,#m-panel-tab").css({"left":width+"px"});
		} else {
			$("#mFoldLeft").attr("display","1");
			$("#mFoldLeft").addClass("m-fold-left2");
			$("#mFoldLeft").attr("title","打开菜单工作区");
			//$("#mMenu2").css({"width":"0"});
			$("#mMenu2").hide();
			$("#framecenter,#m-panel-tab").css({"left":"0"});
		}
	}

	if (obj["panel"]=="top") {
		if (obj["display"]) {
			$("#mFoot").show();
			$("#mFoldTop").attr("display","0");
			$("#mFoldTop").removeClass("m-fold-top2");
			$("#mFoldTop").attr("title","收缩顶部工作区");
			$("#mTop").css({"height":""});
			$("#mTopLeft").css({"height":""});
			$("#mTopRight").css({"height":""});
			$("#mMenu2").css({"top":""});
			$("#framecenter").css({"top":""});
			$("#m-panel-tab").css({"top":""});
			$("#mFoldTop").css({"top":""});
			$("#mFoldLeft").css({"top":""});
			$("#mMenu1More").css({"top":""});
			$("#mMenu1").css({"top":""});
			$("#mUserInfo,#mSystemItem,#systemTitle,#mcsfw1,#mFoot,#mTopDiv").show();
		} else {
			$("#mFoldTop").attr("display","1");
			$("#mFoldTop").addClass("m-fold-top2");
			$("#mFoldTop").attr("title","打开顶部工作区");
			$("#mTop").css({"height":"100px"});
			$("#mTopLeft").css({"height":"100px"});
			$("#mTopRight").css({"height":"100px"});
			$("#mMenu2").css({"top":"100px"});
			$("#framecenter").css({"top":"100px"});
			//$("#m-panel-tab").css({"top":"39px"});
			$("#mFoldTop").css({"top":"55px"});
			$("#mFoldLeft").css({"top":"0"});
			$("#mMenu1More").css({"top":"10px"});
			$("#mMenu1").css({"top":"0px"});
			$("#mUserInfo,#mSystemItem,#systemTitle,#mcsfw1,#mFoot,#mTopDiv").hide();
		}
	}
	//收缩右工作区
	//收缩底工作区
	mWinChangeSize();
}

function cssweofsl(arg) {
	var addonstyle=$.cookie.get("addonstyle") || "fdefault";//Cookies.getCookie("addonstyle");
	$("html").addClass("k-skin-"+addonstyle);
	if (addonstyle) {
		//return;
		//ie8不支持，故采用下面直接打印 2017年07月20日 17:40:42 星期四 lybide
		//$('head').append('<link rel="stylesheet" type="text/css" href="k/kui/skins/extflat/css/style.'+addonstyle+'.css" id="addonstyle"/>');
		document.write('<link rel="stylesheet" type="text/css" href="k/kui/skins/extflat/css/style.'+addonstyle+'.css" id="addonstyle"/>');
	}
};cssweofsl();

function mainExtLoad(arg) {
	//2017年06月23日 12:53:30 星期五 lybide
	//主框架载入事件扩展
	mainInit.initFunction.push(function(){
		//cssweofsl();
	});
	//2017年06月21日 12:39:51 星期三 lybide
	//主框架载入事件扩展
	mainInit.lastFunction.push(function(){
		$("#dvDock").hide();
		$("#userHeadImg span").html(userName);
		var onMenuSub=$("#mUserSetMenu");
		var swss_oneExe1;
		onMenuSub.click(function(){
			$("#userinfodrop").addClass("hover");
		});
		onMenuSub.hover(
			function () {
				clearTimeout(swss_oneExe1);
				var _this=$(this);
				//var code=_this.attr("code");
				$("#userinfodrop").addClass("hover");
				//_this.show();
			},
			function () {
				var _this=$(this);
				//var code=_this.attr("code");
				swss_oneExe1=setTimeout(function(){
					$("#userinfodrop").removeClass("hover");
					//_this.hide();
				},400);
			}
		);

		//2017年12月01日 14:42:19 星期五 lyibde 调整
		if (kui["sysMsgSwitchShowIcon"]) {
			if ($("#sys-message-bar").size() > 0) {
				var bb = $("#sys-message-bar").css("position", "static").show().wrap("<li id='mToolBar_sys-message-bar' class='m-foot-toolbar-item' title='系统消息'></li>");
				bb.addClass("m-foot-toolbar-item-wrap");
				bb.find("div").removeClass("no-msg").addClass("iconfont l-icon-sys-message");
				$("#mToolBar_sys-message-bar").appendTo("#mFootToolbar ul");
			}
		}
		//$("#sys-message-bar").appendTo("#mTopDiv");
		$("#mFootToolbar").appendTo("#mUserInfo");
		if ($("#m-big-data-menu").size()>0) {
			var bb=$("#m-big-data-menu").wrap("<li id='mToolBar_bigdata' class='m-foot-toolbar-item' title='大数据展示'></li>");$("#mToolBar_bigdata").prependTo("#mFootToolbar ul");
			//$("#mFootToolbar ul").append('<li id="mToolBar_cysoft" class="m-foot-toolbar-item" title="常用软件"><div class="m-foot-toolbar-item-wrap"><em class="si" style="background-position:-1128px 0px;"></em><div class="m-foot-toolbar-li-div"><a style="cursor:pointer;"><span>常用软件</span></a></div></div></li>');
		}

		function fweofkjosjdf(arg) {

			$("#mMenu2").addClass("fix-width");
			var w0=$(window).width();
			var w1=$("#mUserInfo").width();
			var w2=$("#systemTitleImg").width();
			if (w1+w2+45>w0) {
				$("#mFootToolbar").hide();
			} else {
				$("#mFootToolbar").show();
			}
		};
		fweofkjosjdf();
		var resizeTimer=null;
		$(window).on("resize",function(){
			if (resizeTimer) {
				clearTimeout(resizeTimer)
			}
			resizeTimer = setTimeout(function(){
				fweofkjosjdf();
			},400);
		});
	});

};

function listFformExtLoad() {
	//cssweofsl();
}

if (PAGE_TYPE=="main") {
	mainExtLoad();
} else {
	listFformExtLoad();
}

//console.log(PAGE_TYPE);
if (PAGE_TYPE=="list" || PAGE_TYPE=="form") {
	$(function(){//jQuery页面载入事件
		//listFformExtLoad();
	});
}