var TREEDATA;var loopCount=0;var lastDeep=0;var mFontSize=null;

//loadCoreLibrary("main");

function init_sysMain() {
	mainInit();
};

var M_TAB_WINDOW_MODE=kui["tabWindowMode"];

//动态改变工作区的宽、高状态
function mPanelSize(obj) {
	//mPanelSize({leftWidth:345});
	//obj= $.extend(obj,{leftWidth:0})
	if (obj.leftWidth) {
		if (obj.leftWidth<=0) {
			mPanelDispay({panel:'left',display:false},"mPanelSize1");
		} else {
			mPanelDispay({panel:'left',display:true,width:obj.leftWidth},"mPanelSize2");
		}
		//$(".m-menu2").css({"width":obj.leftWidth});
		//$(".m-center").css({"left":obj.leftWidth});
		//mPanelDispay({panel:'left',display:true});
	}

	//if ($.browser.msie && parseFloat($.browser.version) <=7) {mWinChangeSize();}
	//mWinChangeSize();
};

//插件，任何元素都可具有下拉菜单功能。
(function ($) {//13-3-18 下午9:01 lybide
	// 插件的定义
	$.fn.khMenu = function (options) {
		//debug(this);
		// 建造插件默认值迭代
		var opts = $.extend({}, $.fn.khMenu.defaults, options);
		// 为每一个匹配插件进行迭代和改造
		return this.each(function () {
			$this = $(this);
			// 建造插件特定选项
			var o = $.meta ? $.extend({}, opts, $this.data()) : opts;
			// 更新插件风格
			/*$this.css({
				backgroundColor:o.background,
				color:o.foreground
			});
			var markup = $this.html();
			// 调用格式化函数
			markup = $.fn.khMenu.format(markup);
			$this.html(markup);*/
			//$this.find(".m-m-children").append('<div class="m-m-children-area"></div>');//2013-5-27 上午11:28 lybide
			var kMenu_oneExe1;
			$this.mouseenter(function(){
				//$(".m-m-children").hide();
				clearTimeout(kMenu_oneExe1);//进行清空延时 2014/11/8 14:39 lybide
				panelElementChangeEvent();
				var $this=$(this);
				$this.find("a:first").addClass("hover");
				var mc1=$(".m-m-children",$this);//13-3-12 上午10:55 lybide
				mc1.show();
				var jj=$(".m-m-children-div-3j",$this);
				var yz=$(".m-m-children-div-yz",$this);
				var area=$(".m-m-children-area",$this);
				if (jj.length==0) {
					jj=$('<div class="m-m-children-div-3j"></div>').appendTo(mc1);
					//mc1.append(jj);
					yz=$('<div class="m-m-children-div-yz"></div>').appendTo(yz);
					//mc1.append(yz);
				}
				//13-3-11 下午4:51 lybide
				var w1=$this.width();
				var h1=$this.height();
				var posi=mc1.attr("m-m-position");
				var mmcd=$(".m-m-children-div",$this);
				yz.css({height:10,width:mmcd.outerWidth(),left:mmcd.css("left")});//13-3-19 上午11:39 lybide
				var offset=$this.offset();
				var osls={};
				var wi1=$(window).width()-offset.left;
				//console.log(mmcd.width()>wi1);
				if (mmcd.width()>wi1) {
					osls=$.extend(osls,{right:0,left:"auto"});
				} else {
					//osls={top:offset.top+_this.height(),left:offset.left};
				}
				var he1=$(window).height()-offset.top-100;mmcd.css("height","auto");
				if (mmcd.height()>he1) {
					osls=$.extend(osls,{height:he1});
				} else {
					//osls=$.extend(osls,{height:"auto"});
				}
				mmcd.css(osls);
				//13-3-12 上午11:47 lybide
				if (posi!="bottom") {//顶部下拉，位于元素下方
					var top1=h1+10;
					//mc1.css("top",top1);
					mc1.width(w1);
					area.width(mmcd.outerWidth());
					area.css("left",mmcd.css("left"));//area.height(20);
					mc1.height(10);
					mc1.css({"top":top1-10});
					mmcd.css({"top":10,"overflow":"auto"});
					jj.css("left",parseFloat(w1)/2-jj.width()/2);//13-3-12 上午11:40 lybide
					jj.css("top",10-9);
				} else {//底部下拉，位于元素上方
					var bottom1=h1+10;
					//mc1.css("bottom",bottom1);
					mc1.width(w1);
					mc1.height(10);
					mc1.css("top",-10);
					mmcd.css({"bottom":10});
					jj.css("left",parseFloat(w1)/2-jj.width()/2);//13-3-12 下午3:58 lybide
					jj.css("background-position","-50px -110px");
					jj.css("top",-5);
				}
				//$(".m-user-info-name > div").html("w1="+w1+" h1="+h1+" top1="+top1+" bottom1="+bottom1);
				/*var shimmer = document.createElement('iframe');
				shimmer.id='shimmer';
				shimmer.style.position='absolute';
				shimmer.style.width='1000px';
				shimmer.style.height='80px';
				shimmer.style.top='100px';
				shimmer.style.left='80px';
				shimmer.style.zIndex='999';
				shimmer.setAttribute('frameborder','1');
				shimmer.setAttribute('src','javascript:false;');
				document.body.appendChild(shimmer);*/
			}).mouseleave(function(){
				var $this=$(this);
				kMenu_oneExe1=setTimeout(function(){
					$this.find("a:first").removeClass("hover");
					$(".m-m-children",$this).hide();
				},400);
				//$("#shimmer").remove();
				return;
				//setTimeout(function(){$(".m-m-children",$this).hide();},400);
			});
			$(".m-m-children-div a",$this).click(function(){//alert(2222);
				$(this).parents(".m-m-children").hide();
			});
			//13-5-7 下午11:48 lybide 通过live事件绑定
			$(".m-m-children-div a",$this).live("click",function(){//alert(3333);
				$(this).parents(".m-m-children").hide();
			});
		});
	};
	// 私有函数：debugging
	function debug($obj) {
		if (window.console && window.console.log) {
			window.console.log('khMenu selection count: ' + $obj.size());
		}
	};
	// 定义暴露format函数
	$.fn.khMenu.format = function (txt) {
		return '<strong>' + txt + '</strong>';
	};
	// 插件的defaults
	$.fn.khMenu.defaults = {
		//renderTo:$(document.body),
		//initPosition:"max",
		foreground:'red',
		background:'yellow',
		onChange:function(){

		}
	};
	// 闭包结束
})(jQuery);

var cookieCase=new ArrayObj();//全局cookies临时对象，main.js -> Cookies 变量 CreateCookies() 调用

//退出页面进行事件绑定，把cookies值直接存入真正的cookies。
/*
* 2009年09月09日 星期三 16:37:41 lybide
*/
function exit() {
	/*2009年09月09日 星期三 16:34:39 lybide
	Firefox和Safari允许cookie多达4097个字节，包括名（name）、值（value）和等号。
	Opera允许cookie多达4096个字节，包括：名（name）、值（value）和等号。
	InternetExplorer允许cookie多达4095个字节，包括：名（name）、值（value）和等号。
	*/
	var cookieValue=Cookies.getCookieValue();//alert(cookieValue+"\n\n退出")
	if (cookieValue.length<3000) {
		$.cookie("c_allCookieValue",cookieValue,{expires:7,path:"/",domain:"",secure:false});
		$.cookie("c_userName",systemUserName,{expires:7,path:"/",domain:"",secure:false});
	}
	return;
}

//页面载入时，取得真正的cookies，放入页面cookies中。
/*
* 2009年09月09日 星期三 16:37:45 lybide
*/
function loadCookie() {
	var clipData=$.cookie("c_allCookieValue");
	var c_userName=$.cookie("c_userName");
	//2014年06月30日 13:35:27 星期一 lybide 变量赋值完成后，删除cookie
	$.cookie("c_allCookieValue",null,{path:"/"});
	$.cookie("c_userName",null,{path:"/"});
	//alert("开始取cookies:clipData\n\n"+clipData+"\n\nc_userName="+c_userName+"\n\nsystemUserName="+systemUserName);//2013-04-29 22:34:10 lybide
	if (clipData=="" || clipData==null) {
		return;
	}
	if ((c_userName==systemUserName) && (clipData!="" && clipData.indexOf(kui.cookiesNamePre)>-1)) {
		//2009年12月08日 星期二 00:16:32 lybide
		//登录用户不一样，就不载入老的cookie
		//alert("老用户登录");
		var a=clipData;
		a=a.replace(/\n/ig,"`||`");
		var cutMemory=a.split("`||`");
		for (var i=0;i<cutMemory.length ;i++ ) {
			if (cutMemory[i].indexOf(kui.cookiesNamePre)>-1) {
				var cutMemoryArr=cutMemory[i].split("`=`");
				Cookies.setCookie(cutMemoryArr[0],cutMemoryArr[1]);
			}
		}
	}
	//$.cookie("c_allCookieValue",null);
	//$.cookie("c_userName",null);
	//alert("结束取cookies:\n\n"+$.cookie("c_allCookieValue")+"="+$.cookie("c_userName")+"\n\nsystemUserName="+systemUserName);
}

//ie6窗口改变事件
function mWinChangeSize() {
	//if ($.browser.msie && parseFloat($.browser.version) <=7) {
		var boH1=kFrameConfig["mainToolbar"] ? (parseInt($("#mFoot").css("bottom"))>=0 ? $("#mFoot").height() : 0) : 0;
		var h1=$("body").height()-$("#mTop").height()-boH1;//alert(h1+"px");
		var w1=$("body").width()-$("#mMenu2").width()-$("#mRight").width();
		//$("#mMenu2,#framecenter").css({"height":h1});//alert(w1+"=="+h1);
	//}
	//$("#mMenu2").css({"height":h1});
	mGetUrlGoTabSizeChange(1);
	//$(".m-center").css({"width":w1});
	//$("#systemTitle").html(w1)
	//$(".m-menu2").get(0).style.height="300px";
}

//系统右键菜单通用调用方法
function goSubMenuClickAction(_this,id1,id2) {
	menuRelocation(id1,id2);return;//直接点击栏目 2017年08月11日 11:49:42 星期五 lybide
	var url=_this.attr("url");
	if (url) {
		var tabWindowMode=M_TAB_WINDOW_MODE;
		if (tabWindowMode=="yesWin") {//alert(_this.attr("url"));
			var url=_this.attr("url");
			var menuId=_this.attr("menuid");
			var text=_this.text();
			var icon=_this.attr("image16");
			//alert([url,menuId,text,icon])
			mGetUrlGo(url,menuId,text,icon,2);
			return;
		}
		var wObj={content:"url:"+url,lock:true,parent:api};
		var winwh=_this.attr("winwh");
		if (winwh) {
			winwh=winwh.split(",");
			$.extend(wObj,{width:parseInt(winwh[0]),height:parseInt(winwh[1])});
		}
		winOpen(wObj);
	}
};

function getMainMenuObject(menuId,text) {
	//找到所有父名称 2014/12/3 13:30 lybide
	var pp=$("#mMenu2_d_"+menuId).parents(".m-menu2-1-li-hasChildren");
	var pa=[];
	//var text="";
	pa.push(text);
	pp.each(function(i){
		var _this=$(this);
		var t=_this.find(">div>a>span").text();
		pa.push(t);
	});
	var pa1=$("#mMenu2_d_"+menuId).parents(".m-menu2-1-panel").find(">.m-menu2-1-title>.m-menu2-1-title-text").text();
	if (pa1) {
		pa.push(pa1);
	}
	var liTitle=pa.reverse().join(" > ");
	return {title:liTitle};
};

//2017年07月07日 16:42:14 星期五 lybide 添加工作区遮罩，原采用 MAIN_OPEN_IFRAME.show() || hide() 会造成iframe页面执行两次resize事件。
function mCenterMaskChange(keys,n) {
	keys=keys || "show";
	if (keys=="show") {
		$("#framecenter-mask").show().css({"top":$("#framecenter").css("top"),"right":$("#framecenter").css("right"),"bottom":$("#framecenter").css("bottom"),"left":$("#framecenter").css("left")});
	} else {
		$("#framecenter-mask").hide();
	}
};

//打开系统
var MAIN_OPEN_IFRAME,MAIN_LINK_SERVER_STATUS_COUNT= 0,MAIN_LINK_SERVER_STATUS_COUNT_LOOP_FUNCTION;
function mGetUrlGo(url,menuId,text,icon,keys) {
	//$("#framecenter").css({"z-index":"-1"});
	//var url=$(obj).attr("url");
	//var menuId=obj.parent().attr("menuid") || obj.parent().attr("code");//alert([text,menuId]);
	//alert([url,menuId,text,icon,keys])

	//2015/1/29 14:25 lybide
	//url="http://urlIsBad";
	//url="demo/demo_index2j.jsp";
	if (!url) {
		return;
	}

	//支持多标签 2014/12/1 13:49 lybide
	//====================================================================================================
	var tabWindowMode=M_TAB_WINDOW_MODE;
	if (tabWindowMode=="yesWin") {
		$("#m-panel-tab").show();
		//var text=obj.find(">a").text();
		/*if (!url) {
			return;
		}*/

		var liTitle=getMainMenuObject(menuId,text)["title"];
		//alert(liTitle);
		var tabIcon=icon || kui["menu"]["defaultIconSrc"] || kui["menu"]["defaultIconSrc32"] || "";//取得图标
		var mm2d=$("#mMenu2_d_"+menuId);
		var image16=mm2d.attr("image16");
		var iconCss=mm2d.attr("iconCss");
		var tabIcon=mGetMenuImg(image16,true,iconCss);

		//document.title=kui["name"]+" "+liTitle;

		//标签、工作区存在
		if ($("#mTab_"+menuId).length>0) {
			$("#mTab_"+menuId).show();
			$("#framecenter >.item").hide();
			$("#mTab_panel_"+menuId).show();
			$("#m-panel-tab-ul>li").removeClass("selected");
			$("#mTab_"+menuId).addClass("selected");
			welComeIndexShowYesWin(menuId,true,url);
			mGetUrlGoTabSizeChange(2);
			return;
		}
		//setTimeout(function(){
			mCenterMaskChange("show",2);
		//},1);

		//只在固定的标签里打开 2014/12/3 17:51 lybide
		var _openLock=$("#m-panel-tab-ul>li.openLock");
		if (_openLock.length>0 && menuId!=="s_welcome_desktop") {
			//$("#mTab_panel_s_welcome_desktop").hide();
			$("#framecenter >.item").hide();
			$("#m-panel-tab-ul>li").removeClass("selected");
			var oldMenuId=_openLock.attr("menuid");
			_openLock.attr("menuid",menuId);
			_openLock.attr("title",liTitle);
			_openLock.attr("id","mTab_"+menuId);
			_openLock.addClass("selected");
			var _openLockA=_openLock.find(".item-text>a");
			_openLockA.attr("id","mTab_"+menuId+"_a");
			_openLockA.attr("menuid",menuId);
			_openLockA.find(">span").text(text);
			_openLockA.find(".hasIcon").remove();
			_openLockA.prepend(tabIcon);
			/*if (_openLockA.find(">em").size()>0) {
				_openLockA.find(">em").css({"background-image":"url("+tabIcon+")"});
			}*/
			mGetUrl(url,menuId,text);
			$("#mTab_panel_"+oldMenuId).show();
			$("#mTab_panel_"+oldMenuId+"_iframe").attr("src",url).show();
			$("#mTab_panel_"+oldMenuId).attr("id","mTab_panel_"+menuId);
			$("#mTab_panel_"+oldMenuId+"_wrap").attr({"id":"mTab_panel_"+menuId+"_wrap","title":text});
			$("#mTab_panel_"+oldMenuId+"_iframe").attr({"id":"mTab_panel_"+menuId+"_iframe","name":"mTab_panel_"+menuId+"_iframe"});
			return;
		}

		//创建标签，绑定事件，开始
		//tabIcon=tabIcon?'<em class="tab-icon" style="background-image:url('+tabIcon+');"></em>':'';
		var isTab=$('<li class="tab-item" id="mTab_'+menuId+'" menuid="'+menuId+'" title="'+liTitle+'"><div class="item-wrap"><div class="item-text"><a id="mTab_'+menuId+'_a" menuid="'+menuId+'" class="a">'+tabIcon+'<span class="text">'+text+'</span></a></div><div class="tool"><a class="close" title="关闭标签页"><span>X</span></a><a class="f5" title="刷新标签页"><span>F</span></a><a class="closeOther" title="关闭其它标签"><span>A</span></a><a class="openLock" title="只在此标签打开系统"><span>O</span></a><a class="tabFixedWidth" title="固定标签宽度"><span>W</span></a></div><div class="jj3j"></div><div class="openLockIcon"></div></div></li>').hover(
			function () {
				var _this=$(this);
				_this.addClass("hover");
				if (getkIsShell()) {//getkIsShell()
					_this.addClass("hover-isshell");
				}
			},
			function () {
				var _this=$(this);
				_this.removeClass("hover");
				if (getkIsShell()) {//getkIsShell()
					_this.removeClass("hover-isshell");
				}
			}
		);
		//鼠标右键 2014/12/3 10:20 lybide
		isTab.bind("contextmenu",function(){return false;}).mousedown(function (e) {
			var ew=e.which;
			//支持鼠标中键关闭 2014/12/3 15:57 lybide
			if(2 == ew){
				isTab.find(".tool>.close").click();
			}
			if(3 == ew){
				var _this=$(this);
				//console.log(_this.attr("id"));
				var menuId=_this.attr("menuid");
				//var rMenu=$("#mRightMenu_"+menuId);
				var rMenu=$("#mRightMenu");//2016年12月28日 17:15:46 星期三 lybide
				//$("#sysMain >.m-right-menu").hide();
				if (rMenu.length>0) {
					//rMenu.show();
					rMenu.attr("menuId",menuId);
				} else {
					rMenu=$('<div class="m-right-menu" id="mRightMenu" menuId="'+menuId+'"><ul><li><div><a class="close"><em></em><span>关闭</span></a></div></li><li><div><a class="f5"><em></em><span>刷新</span></a></div></li><li class="line"><u></u></li><li><div><a class="closeOther"><em></em><span>关闭其它</span></a></div></li><li><div><a class="openLock"><em></em><span>只在这里打开</span></a></div></li><li><div><a class="tabFixedWidth"><em></em><span>固定标签宽度</span></a></div></li></ul></div>').bind("contextmenu",function(){return false;});
					//2016年12月28日 17:15:15 星期三 lybide 只需要调用一个右键菜单
					rMenu.find("a").click(function(){
						var _this=$(this);
						var menuId=_this.parents(".m-right-menu").attr("menuid");
						var name=_this.attr("class");
						//isTab.find(".tool>."+name).click();
						$("#mTab_"+menuId+"").find(".tool>."+name).click();
						_this.parents(".m-right-menu").hide();
					});
					//2014/12/3 10:55 lybide
					var swss_oneExe1;
					rMenu.hover(
						function () {
							clearTimeout(swss_oneExe1);
							var _this=$(this);
							var menuId=_this.attr("menuId");
							//$("#mMenu_"+code).addClass("m-menu1-hover");
							$("#mTab_"+menuId+"").addClass("hover");
							_this.show();
						},
						function () {
							var _this=$(this);
							var menuId=_this.attr("menuId");
							//var code=_this.attr("code");
							swss_oneExe1=setTimeout(function(){
								//$("#mMenu_"+code).attr("menu2","0").removeClass("m-menu1-hover");
								$("#mTab_"+menuId+"").removeClass("hover");
								_this.hide();
							},400);
						}
					);
					$("#sysMain").append(rMenu);
				}

				rMenu.show();
				var opt={offsetX:0,offsetY:0};
				var l = e.pageX + opt.offsetX,
					t = e.pageY+opt.offsetY,
					p={
						wh:$(window).height(),
						ww:$(window).width(),
						mh:rMenu.height(),
						mw:rMenu.width()
					}
				t=(t+p.mh)>=p.wh?(t-=p.mh):t;//当菜单超出窗口边界时处理
				l=(l+p.mw)>=p.ww?(l-=p.mw):l;
				//console.log(e.pageX,e.pageY,t,l);
				rMenu.css({left:l, top:t});

				return;
			}
		});
		//鼠标左键点击标签
		isTab.find(".item-text .a").click(function(){
			var _this=$(this);
			var menuId=_this.attr("menuid");
			$("#mTab_"+menuId).show();
			$("#framecenter >.item").hide();
			$("#mTab_panel_"+menuId).show();
			$("#m-panel-tab-ul>li").removeClass("selected");
			$("#mTab_"+menuId).addClass("selected");
			//document.title=kui["name"]+" "+liTitle;
			welComeIndexShowYesWin(menuId,true,url);
		});
		//关闭标签
		isTab.find(".tool>.close").click(function(){
			var _this=$(this);
			var menuId=_this.parents(".tab-item").attr("menuid"),menuId2="";
			var next=isTab.next();
			var prev=isTab.prev();
			$("#mTab_"+menuId).remove();//标签名称
			$("#mTab_panel_"+menuId+"_iframe").attr("src","about:blank");
			//$("#mTab_panel_"+menuId+"_iframe")[0].contentWindow.document.write("");
			//$("#mTab_panel_"+menuId+"_iframe")[0].contentWindow.document.close();
			$("#mTab_panel_"+menuId+"_iframe").remove();
			//CollectGarbage();
			//$("#mTab_panel_"+menuId).append('<iframe id="mTab_panel_'+menuId+'_iframe" src="about:blank" class="m-iframe" frameborder="0" name="mTab_panel_'+menuId+'_iframe" frameborder="0" scrolling="auto" allowTransparency="true" style="background-color:transparent;"></iframe>');
			$("#mTab_panel_"+menuId).remove();//标签iframe外层div

			$("#mRightMenu_"+menuId).remove();//标签右键
			mGetUrlGoTabSizeChange(3);

			var s1=$("#m-panel-tab li.selected");
			if (s1.length>0) {
				//2014/12/8 11:55 lybide 新增功能
				menuId2=s1.attr("menuid");
				//$("#mTab_"+menuId).prev().find(".item-text .a").click();
				return;
				//$("#mTab_"+menuId1).find(".item-text .a").click();
			} else {
				//alert(111)
				//$("#mTab_"+menuId).prev().find(".item-text .a").click();
			}
			//alert([s1.length,next.size(),prev.size()])
			//2014/12/23 16:58 lybide 优化
			if (next.size()>0) {
				next.find(".item-text .a").click();
				return;
			}
			if (prev.size()>0) {
				prev.find(".item-text .a").click();
				return;
			}
			if (menuId==menuId2) {
				//$("#mTab_"+menuId).prev().find(".item-text .a").click();
			}

		});
		//刷新标签
		isTab.find(".tool>.f5").click(function(){
			var _this=$(this);
			var menuId=_this.parents(".tab-item").attr("menuid");
			$('#mTab_panel_'+menuId+'_iframe')[0].contentWindow.location.reload();
			//mGetUrl(url,menuId,text);
		});
		//关闭其它标签 2014/12/3 16:45 lybide
		isTab.find(".tool>.closeOther").click(function(){
			var _this=$(this);
			var menuId=_this.parents(".tab-item").attr("menuid");
			$("#m-panel-tab").find("li:not([menuid="+menuId+"],.openLock,[menuid=s_welcome_desktop]) .tool>.close").click();
		});
		//只这里打开 2014/12/3 17:26 lybide
		isTab.find(".tool>.openLock").click(function(){
			var _this=$(this);
			var ok=_this.parents(".tab-item").hasClass("openLock");
			$("#m-panel-tab-ul>li").removeClass("openLock");
			if (ok) {
				isTab.removeClass("openLock");
			} else {
				isTab.addClass("openLock");
			}
		});
		//固定标签宽度 2014/12/4 17:00 lybide
		isTab.find(".tool>.tabFixedWidth").click(function(){
			var _this=$(this);
			var ok=_this.parents(".m-panel-tab-ul").hasClass("tabFixedWidth");
			if (ok) {
				ok=0;
				isTab.parent().removeClass("tabFixedWidth");
			} else {
				ok=1;
				isTab.parent().addClass("tabFixedWidth");
			}
			mGetUrlGoTabSizeChange(4);
			Cookies.setCookie("cTabWindowMode",ok);
		});
		//$("#m-panel-tab-ul").append(isTab);
		if ($("#m-panel-tab-ul li").length>0) {
			$("#m-panel-tab-ul li.selected").after(isTab);
		} else {
			$("#m-panel-tab-ul").append(isTab);
		}
		//记住用户是否配置了固定宽度 2016年10月21日 17:54:56 星期五 lybide
		var ok=Cookies.getCookie("cTabWindowMode");
		if (ok=="1") {
			$("#m-panel-tab-ul").addClass("tabFixedWidth");
		}
		//2014/12/2 16:39 lybide 计算标签高度
		//$("#m-panel-tab").css({"top":$("#mTop").height()-1});
		mGetUrlGoTabSizeChange(5);
		$("#framecenter >.item").hide();
		var iframe=$('<div class="item" id="mTab_panel_'+menuId+'">'+
		'		<div title="'+text+'" class="myhome" id="mTab_panel_'+menuId+'_wrap">' +
		'			<iframe id="mTab_panel_'+menuId+'_iframe" src="about:blank" class="m-iframe" frameborder="0" name="mTab_panel_'+menuId+'_iframe" frameborder="0" scrolling="auto" allowTransparency="true" style="background-color:transparent;"></iframe>' +
		'		</div>'+
		'</div>');
		$("#framecenter").append(iframe);//2017年06月24日 19:32:29 星期六 lybide 注意，有两个相同的iframe存在。
		//页面载入完成监听 2014/12/3 15:35 lybide
		MAIN_OPEN_IFRAME=$('#mTab_panel_'+menuId+'_iframe');
		$('#mTab_panel_'+menuId+'_iframe').bind("load",function(){//标签工作区
			var _this=$(this);
			//if (_this.is(":visible")) {//添加判断是否已打开

			//} else {//在没有引入kui时
				closeMenuLoading("main");
				//_this.show();//2017年07月07日 12:02:36 星期五 lybide 删除
			//}
			//alert(document.getElementById('home').contentWindow.PAGE_STATUS_END)
			//closeMenuLoading();
			//$("#myhome").show();
		});


		$('#mTab_panel_'+menuId+'_iframe').attr("src",url);
		//2014/12/10 17:03 lybide 不存在url，直接写iframe写值
		/*if (url) {
		} else {
			var oneExe1=setTimeout(function(){
				//暂不开启，因为所有菜单有可能都为空。
				document.getElementById("mTab_panel_"+menuId+"_iframe").contentWindow.document.body.innerHTML='E5291 页面不存在......';
			},1);
		}*/
		$("#m-panel-tab-ul>li").removeClass("selected");
		$("#mTab_"+menuId).addClass("selected");

		welComeIndexShowYesWin(menuId);
		//return;
	} else {
		//$("#home").attr("src","about:blank");winOpen.loading();
		if (menuId.indexOf("s_welcome_desktop")!=-1) {//2017年07月07日 12:55:52 星期五 lybide
			mPanelDispay({panel: 'left', display: false, close: false},"mGetUrlGo");
		}
		//setTimeout(function(){
			mCenterMaskChange("show",3);
		//},1);
		MAIN_OPEN_IFRAME=$("#myhome");
	}
	//====================================================================================================


	if (url.indexOf("javascript:")>=0) {
		eval(url);//执行自定义javascript。eval("javascript:doMind(this);dews();");
	} else {
		mGetUrl(url,menuId,text);
	}

	//return;
	var sysMainBadLinkSwitch=kui["sysMainBadLinkSwitch"]!==false ? true : kui["sysMainBadLinkSwitch"];
	if (!sysMainBadLinkSwitch) {
		return;
	}
	//alert("开启了");
	//2015/1/29 15:30 lybide 检查网络连接，如超20秒，还没有反应，需要友情提示
	MAIN_LINK_SERVER_STATUS_COUNT=0;
	var linkTime=kui["sysMainBadLinkCueHH"] || 20;//20秒没有反应，提示网络不能连接。
	linkTime=parseInt(linkTime);
	clearInterval(MAIN_LINK_SERVER_STATUS_COUNT_LOOP_FUNCTION);//2015/2/9 15:23 lybide 先进行清空
	MAIN_LINK_SERVER_STATUS_COUNT_LOOP_FUNCTION=setInterval(function(){
		var obj=$("#mLoading");
		if (obj.is(":visible") && MAIN_LINK_SERVER_STATUS_COUNT>linkTime) {
			//alert("连接超时，请重新连接");
			MAIN_LINK_SERVER_STATUS_COUNT=0;
			clearInterval(MAIN_LINK_SERVER_STATUS_COUNT_LOOP_FUNCTION);
			closeMenuLoading("main");
			winOpen.loading('<span style="color:red;font-size:18px;">连接超时，请重试。</span>');
		}
		if (obj.is(":hidden")) {
			clearInterval(MAIN_LINK_SERVER_STATUS_COUNT_LOOP_FUNCTION);
		}
		//console.log(MAIN_LINK_SERVER_STATUS_COUNT);
		MAIN_LINK_SERVER_STATUS_COUNT++;
	},1000);//总变量，这个必须不写或者写在startX函数外，stopX才有效
};

function lskdfjwef(arg) {
	var tabWindowMode=M_TAB_WINDOW_MODE;
	if (tabWindowMode=="yesWin") {

	}
};

function mGetUrlGoTabSizeChange(arg) {
	//2015/1/26 11:53 lybide 优化
	var tabWindowMode=M_TAB_WINDOW_MODE;
	if (tabWindowMode!="yesWin") {
		//2017年06月25日 17:25:41 星期日 lybide
		var boH1=kFrameConfig["mainToolbar"] ? (parseInt($("#mFoot").css("bottom"))>=0 ? $("#mFoot").height() : 0) : 0;
		$("#framecenter").css({"bottom":boH1});
		return;
	}
	var pos1=kui["tabWindowModePosition"] || "bottom" || "top" || "left" || "right";
	//pos1="bottom";
	var boH1=kFrameConfig["mainToolbar"] ? (parseInt($("#mFoot").css("bottom"))>=0 ? $("#mFoot").height() : 0) : 0;
	if (pos1=="bottom") {
		if ($("#m-panel-tab").is(":visible")) {
			$("#m-panel-tab").css({"bottom":boH1,top:""}).addClass("bottom");
			var h1=$("#m-panel-tab").height();
			$("#framecenter").css({"bottom":boH1+h1});
		} else {
			$("#framecenter").css({"bottom":boH1});
		}
		return;
	}
	if (pos1=="right") {
		if ($("#m-panel-tab").is(":visible")) {
			$("#m-panel-tab").css({"bottom":boH1,"top":$("#mTop").height(),right:0,left:"","overflow-y":"auto"}).width(150).addClass("right");
			$("#m-panel-tab-ul").addClass("tabFixedWidth posi-right");
			var h1=$("#m-panel-tab").height();
			$("#framecenter").css({right:150});
		} else {
			$("#framecenter").css({"right":""});
		}
		return;
	}
	//2014/12/23 11:41 lybide 改进
	if ($("#m-panel-tab").is(":visible")) {
		$("#m-panel-tab").css({"top":$("#mTop").height()-1,right:0});
		var h1=$("#m-panel-tab").height();
		$("#framecenter").css({"top":$("#mTop").height()+h1});
	} else {
		$("#framecenter").css({"top":""});
	}
};

//根据传入的值，在工作区打开系统
var top$DialogLoading=false;
function mGetUrl(url,menuId,text) {
	//console.log("111111111111111",url,menuId,text,kFrameConfig.menu.clickUseMask);
	//var url=$(obj).attr("url");
	//$(".m-c-loading").show();
	//alert("adfasdfsadf");return;
	if (!$.kh.isNull(url) && url!="null") {
		if (kFrameConfig.menu.clickUseMask) {
			//MAIN_OPEN_IFRAME.hide();//2017年07月03日 15:59:55 星期一 lybide 关闭，以解决首页执行两次resize效果
			winOpen.loading();
			top$DialogLoading=true;
		}
		var tabWindowMode=M_TAB_WINDOW_MODE;
		if (tabWindowMode=="yesWin") {//有标签时，不执行以下代码
			return;
		}
		//var liTitle=getMainMenuObject(menuId,text)["title"];
		//document.title=kui["name"]+" "+liTitle;
		if (kFrameConfig.mGetUrl) {
			kFrameConfig.mGetUrl(url,menuId);
			return;
		}
		mfGetUrl(url,menuId,text);//2017年06月21日 12:31:35 星期三 lybide
	}
};

var homeFrameHtml = '<iframe src=":content_url" frameborder="0" name="home" id="home" class="m-iframe" frameborder="0" scrolling="auto" allowTransparency="true" style="background-color:transparent;"></iframe>';
function mfGetUrl(url,menuId,text){//系统默认跳转url 2017年06月25日 17:02:45 星期日 lybide
	$("#home").attr("src","");
	alert("change menu");
	setTimeout(function(){//13-3-21 下午4:19 lybide 增加延迟，解决每次点击时，锁定浏览器的问题。
		$("#home").remove();
		$("myhome").html(homeFrameHtml.replace(':content_url',url));
	},50);
}

//页面生成，第一步，主框架载入事件
var mainInit=function() {
	loadCookie();
	var mi=mainInit.initFunction;
	if (mi.length>0) {
		for (items in mi) {
			var item=mi[items];
			item.call();
		};
	}
	var sysName=kFrameConfig["name"];
	sysName=sysName.replace(/<[^>].*?>/g," ");
	document.title = sysName;
	mainElementInfoSet();
	kFrameConfig.mainBeginOnload && kFrameConfig.mainBeginOnload();
	$(window).unload(function(){exit();});
	ieChk();
	mOparLoad();
};
mainInit.initFunction=[];//2017年06月23日 12:58:37 星期五 lybide
mainInit.lastFunction=[];//2017年06月18日 22:51:28 星期日 lybide

function mainElementInfoSet() {
	$("#mFoldLeft").attr("display","0");
	$("#mFoldTop").attr("display","0");
};

var SYS_MAIN_SHOW=0;
function mOparLoad(){//2014-2-20 下午6:20 lybide
	/*$("#sysDesktop").append('<div id="dvDock" class="qo"><div hidefocus="true" tabindex="0" title="邮箱触点" id="dvDockStartOuter" class="mI"><div id="dvDockStart" class="ps"><span class="nm lq"></span><span class="nm kY"></span><span class="nm kZ"></span><span class="nm la"></span><span style="display:none" id="spnDockNew" class="qg"></span></div></div>');
	$("#dvDock").toggle(
		function () {
			$("#sysMain").hide("right");
		},
		function () {
			e();
			$("#sysMain").show("left");
		}
	);*/
	/*$("#dvDock").click(function(){

	});*/
	e();
	function e(){
		if (SYS_MAIN_SHOW==0) {

			createMainFrame();//生成主框架元素
			//2015/1/22 18:03 lybide 优化，待整个页面载入完成后，再执行菜单生成器
			//$(window).load(function(){//此方法，在ch浏览器有可能不执行
				//getMainMenuSrc();
			//});
			SYS_MAIN_SHOW++;
		}
	}
}

function createMainFrame(){
	$("#sysMain").show();
	if (kFrameConfig["user"]["show"]) {
		//$("#userHeadImg").css({'background-image':'url("'+this.user.icon+'")','width':'40px','height':'40px','background-position':''+this.user.iconLeft+'px '+this.user.iconTop+'px'});
		//$("#userHeadImg").html('<img src="'+this.user.icon+'" border="0" />');
		var userHead='<a id="userHeadImg" href="javascript:void(0);" class="user-set-menu-a" keys="setUserIndividuation">';
		//userHead+='<img src="'+kFrameConfig.user.icon+'" border="0" />';
		var userHeadImage=kFrameConfig.user.icon.replace("default.gif","default.png");//2016年7月27日 15:07:14 lybide
		if ($.browser.msie && parseFloat($.browser.version) <=8) {
			userHead+='<img src="'+userHeadImage+'" border="0" style="width:40px;height:40px;"/>';
		} else {
			userHead+='<em style="display:block;height:100%;height:100%;background-position:center center;background-size:40px 40px;background-image:url(\''+userHeadImage+'\');"></em>';
		}
		userHead+='<span>我的头像</span></a>';
		$(".user-set-menu").html(userHead);
	} else {
		$(".user-set-menu").hide();
		$(".m-user-info").css({"right":"120px"});
	}

	if (kFrameConfig["mainSub"]) {
		$("#systemTitle").after('<div class="m-top-logo-sub" id="m-top-logo-sub">'+kFrameConfig["mainSub"]+'</div>');
	}

	if (kFrameConfig["techComUrl"]) {
		$("#sysMain").append('<div id="m-foot-copyright" class="m-foot-copyright"><div class="m-foot-copyright-div"><a id="techComUrl" href="'+kFrameConfig["techComUrl"]+'" target="_blank"><span>'+kFrameConfig["techCom"]+'</span></a></div></div>');
		//存在外壳程序 2014/11/12 19:30 lybide
		if (getkIsShell()) {
			$("#techComUrl").click(function(){
				var _this=$(this);
				window.external.OpenUrl(_this.attr("href"),0);
				return false;
			});
		}
	}

	//mPanelSize({leftWidth:"0"});

	//$("body").append('<div id="bodyLoading" style="top:50%;left:50%;width:300px;line-height:22px;text-align:center;margin:-60px 0px 0px -150px;border:1px solid #FF0000;position:absolute;">正在加载系统</div>');
	//$(".m-top-logo").css(kFrameConfig.mainLogo.style);
	//kFrameConfig["logoMode"]="text";
	if (kFrameConfig["logoMode"]=="img") {
		//$("#systemTitleText").html(kFrameConfig["name"]);
		/*$(".m-top-logo").css("background","url('"+kFrameConfig.mainLogo["src"]+"') no-repeat");
		if (kFrameConfig.mainLogo["style"]) {
			$(".m-top-logo").css(kFrameConfig.mainLogo["style"]);
		}*/
		var img=$('<div class="img-div" id="systemTitle"><img src="'+kFrameConfig.mainLogo["src"]+'" id="systemTitleImg" border="0" class="m-top-logo-image"></div>');
		$("#systemTitle").append(img);
	} else if (kFrameConfig["logoMode"]=="text") {
		$("#systemTitle").html('<div class="text-div" id="systemTitleText">'+kFrameConfig["name"]+'</div>').addClass("m-top-logo-text");
		//$("#systemTitle").remove();
		//$(".m-top-div").append('<div class="m-top-logo-text" id="systemTitle"><div class="text-div" id="systemTitleText"></div></div>');
		//$("#systemTitleText").html(kFrameConfig["name"]);
	}
	//2015/1/22 15:37 lybide 新增至此，算出标题的长度，以调整一级菜单的显示范围
	$(window).load(function(){
		var smT=$("#systemTitle");//2015/1/16 17:58 lybide
		var logoW1=smT.width() || $("#systemTitleImg").width();
		var logoH1=smT.height() || $("#systemTitleImg").height();
		//console.log([smT.width(),smT.height(),logoW1,logoH1]);
		if (L_MENU_SKIN_NAME=="outlook") {
			$("#systemTitle").css({"top":(87/2-logoH1/2),"position":"absolute"});
			$("#mMenu1").css({"left":logoW1});
		} else if (L_MENU_SKIN_NAME=="1") {
			if (logoH1<65) {

			} else {
				$("#mMenu1").css({"left":logoW1});
				//$(".m-menu1-a").css({"border":"1px solid #FF0000","font-size":"18px","position":"relative","bottom":"-15px","padding":"0 10px"});
			}
		} else if (L_MENU_SKIN_NAME=="tree") {
			if (logoH1<65) {
				$("#systemTitle").css({"top":(87/2-logoH1/2),"position":"absolute"});
			} else {

			}
		}

		if (kFrameConfig["mainSub"]) {
			$("#m-top-logo-sub").css({"top":"10px","left":logoW1});
		}
		$("#systemTitle").show();
		getMainMenuSrc();//生成菜单 2015/1/29 11:40 lybide 移值在这里
	});
	var tabWindowMode=M_TAB_WINDOW_MODE;

	var str1="";
	str1='<div class="m-c-loading"><div class="m-c-loading-panel"><span>正在加载……</span></div></div>';
	$("#framecenter").append(str1);
	if ($("#framecenter-mask").size()==0) {
		$("#sysMain").append('<div id="framecenter-mask" class="m-center-mask"><div class="m-center-mask-wrap"></div></div>');
	}
	if (tabWindowMode=="yesWin") {
		var isTab=$('<div id="m-panel-tab" class="m-panel-tab"><div class="m-panel-tab-warp"><ul id="m-panel-tab-ul" class="m-panel-tab-ul"></ul></div></div>');
		$("#sysMain").append(isTab);
	} else {
		//2017年06月24日 19:44:55 星期六 lybide 删除以下代码。因为存在两个相同的iframe
		str1 = '<div class="item" id="mTab_panel_home">';
		str1 += '		<div title="工作区" class="myhome" id="myhome">';
		str1 += '			<iframe src="about:blank" frameborder="0" name="home" id="home" class="m-iframe" frameborder="0" scrolling="auto" allowTransparency="true" style="background-color:transparent;"></iframe>';
		str1 += '		</div>';
		str1 += '</div>';
		$("#framecenter").append(str1);
		$("#home").bind("load", function() {//单工作区 页面载入完成监听 2014-4-1 下午6:01 lybide
			var _this=$(this);
			//if ($("#myhome").is(":visible")) {//2014-4-2 下午6:27 lybide 添加判断是否已打开

			//} else {//在没有引入kui时
				closeMenuLoading("main");
				//$("#myhome").show();//2017年07月07日 12:02:36 星期五 lybide 删除
			//}
			//alert(document.getElementById('home').contentWindow.PAGE_STATUS_END)
			//closeMenuLoading();
			//$("#myhome").show();
		});
	}

	if (tabWindowMode=="yesWin") {

	} else {

	}

	//系统底部
	//kFrameConfig["mainToolbar"]="";
	if (kFrameConfig["mainToolbar"]) {
		var mFootToolBarObj=kFrameConfig["mainToolbar"]["footBar"];
		str1='';
		/*str1+='<div class="m-foot-menu">' +
		 '			<div class="m-foot-toolbar-div">' +
		 '				<ul>' +
		 '					<li><em style="background-position:-1728px 0px;"></em><div class="m-foot-toolbar-li-div"><a href="javascript:void(0);"><span>快捷方式</span></a></div></li>' +
		 '				</ul>' +
		 '			</div>' +
		 '		</div>';*/
		str1+='		<div class="m-foot-toolbar" id="mFootToolbar">' +
		'			<div class="m-foot-toolbar-div">' +
		'				<ul class="m-foot-toolbar-div-ul"></ul>' +
		'			</div>' +
		'		</div>';
		$(".m-foot").append(str1);
		var menuOpen=kui["MENUOPEN"]!==false ? true : kui["MENUOPEN"];
		if (menuOpen) {
			$(".m-foot-toolbar").css({"left":60});
		} else {
			$(".m-foot-toolbar").css({"left":10});
		}

		$(".m-foot-menu").css({"left":20,"bottom":90});
		//$(".m-foot").html('<div style="line-height:29px;color:#FFFFFF;">　'+kFrameConfig.copyCom+'　　'+kFrameConfig.techCom+'</div>');
		//2014-2-20 上午11:43 lybide 解析来自配置文件的底部工具条
		var mFootToolbar=$("#mFootToolbar ul");
		if (mFootToolBarObj && mFootToolBarObj.length>0) {
			for (var i = 0; i < mFootToolBarObj.length; i++) {
				var items=mFootToolBarObj[i];
				//<li id="mToolBar1"><em style="background-position:-2832px 0px"></em><div class="m-foot-toolbar-li-div"><a href="javascript:void(0);" keys="seCalendar"><span>日历</span></a></div></li>
				var icon="";
				var id=(items["id"] || i);
				if (items["iconStyle"]) {
					icon='<em class="si iconfont l-icon-'+id+'" style="'+items["iconStyle"]+'"></em>';
				}
				if (items["image"]) {
					icon='<em class="zdy iconfont l-icon-'+id+'" style="background:url('+items["image"]+') no-repeat center center"></em>';
				}
				/*if (items["iconfont"]) {
					icon='<em class="si" style="'+items["iconStyle"]+'"></em>';
				}*/
				var addStr1=$('<li id="mToolBar_'+id+'" class="m-foot-toolbar-item" title="'+items["text"]+'"><div class="m-foot-toolbar-item-wrap">'+icon+'<div class="m-foot-toolbar-li-div"><a style="cursor:pointer;"><span>'+items["text"]+'</span></a></div></div></li>');
				if (items["click"]) {
					addStr1.bind("click",items["click"]);
				}
				mFootToolbar.append(addStr1);
			};
		}
	} else {
		//2014/11/8 10:59 lybide 没有主框架 - 工具条
		$("#mFoot").hide();
		$("#framecenter,#mMenu2").css("bottom",0);
	}



	return;
}

function getMainMenuSrc(arg) {
	sysMenuCreateComplete();
};

//页面生成，第二步，菜单创建完成回调函数
function sysMenuCreateComplete(){
	//winOpen({content:$("#sysMain"),lock:false});
	//2014-3-14 下午1:15 lybide 从菜单过程，移值过来
	//=======================================================
	if (L_MENU_SKIN_NAME==1 || L_MENU_SKIN_NAME=="tree") {
		$("#mUserInfoName div").html(loginUserName["org"]+"<br>"+loginUserName["name"]);
	} else {
		$("#mUserInfoName div").html(loginUserName["org"]+" - "+loginUserName["name"]);
	}
	var f5link=kui["f5linkIframe"] || kui["kuiBase"]+"f5link.jsp";
	if (f5link) {
		$("body").append('<div class="f5link"><iframe name="f5linkIframe" id="f5linkIframe" width="100%" height="100%" scrolling="auto" frameborder="0" border="0"></iframe></div>');
		setInterval(function(){$("#f5linkIframe").attr("src",f5link);},180000)//13-3-14 下午2:54 lybide
	}

	//菜单，不能选择，右键，拖动
	$("#mMenu1,#mMenu2,#mLoading,#mLoadingMask,#m-panel-tab").attr({"oncontextmenu":"return false;","onselectstart":"return false;","ondragstart":"return false;"}).css("-moz-user-select","none");

	if (kFrameConfig.menu["menuLeftPanelCss"]) {//自定义二级菜单主层样式
		$("#mMenu2").css(kFrameConfig.menu["menuLeftPanelCss"]);//alert(JSON.stringify(kFrameConfig.menu["menuLeftPanelCss"]));
	}

	//alert(typeof kFrameConfig["user"]["show"]);
	if (kFrameConfig["user"]["show"]) {
		$("#mUserSetMenu").show();
		$("#mUserSetMenu").khMenu();
	} else {
		$("#mUserSetMenu").hide();
	}


	//任何下拉 (13-3-12 上午11:40 lybide 基本定型)
	$("#mSystemItem li").khMenu();

	//$(".m-foot-toolbar-div li").khMenu();

	$("#mFoldLeft").click(function(){
		var $this=$(this);
		mPanelDispay({panel:"left",display:$this.attr("display")=="1"?true:false},"mFoldLeft");
	});
	$("#mFoldTop").click(function(){
		var $this=$(this);
		mPanelDispay({panel:"top",display:$this.attr("display")=="1"?true:false},"mFoldTop");
	});

	mWSize();$(window).resize(function(){mWSize();});
	$("#mMenu1More").khMenu();//13-5-7 下午11:50 lybide
	//mPanelSize({leftWidth:345});

	if ($.browser.msie && parseFloat($.browser.version) <=6) {//浏览器检测 2012年06月19日 星期二 14:04:54 lybide
		try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}//ie6不缓存背景图片
		DD_belatedPNG.fix("*");
	}
	mWinChangeSize();$(window).resize(function(){mWinChangeSize();});
	//$("#bodyLoading").hide();
	//$("#sysMain").show();

	//整个框架可控制字体大小
	//=============================================================
	var cf=$('<div id="mcsfw1" class="m-change-style-font-wrap"><a href="javascript:void(0);" font="18"><span>特</span></a><a href="javascript:void(0);" font="16"><span>大</span></a><a href="javascript:void(0);" font="14"><span>中</span></a><a href="javascript:void(0);" font="12"><span>小</span></a></div>');
	//var str1='<a href="javascript:void(0);" keys="dddd">xxxxxxxxx</a>';
	//$(".m-system-item ul li:eq(1) .m-m-children-div").append(str1);
	$("a",cf).click(function(){
		var _this=$(this);
		var fontOld=$.cookie.get("mSysFont");//$.cookie.get("mSysFont");//Cookies.getCookie("mSysFont");
		var font=_this.attr("font");
		if (fontOld==font) {
			font="";//2017年06月12日 16:46:15 星期一 lybide
		}
		$.cookie.set("mSysFont",font);//Cookies.setCookie("mSysFont",font);
		//winOpen.confirm("是否确认改变字体大小",function(){});
		window.location.reload();return;//由于字体大小重建计算的太多，改变字体大小时，直接重新载入框架 2017年06月23日 11:59:59 星期五 lybide
		return;
		/*setSystemFontType(font);
		winOpen.loading();//13-4-20 下午4:03 lybide
		var iframe1=$("#framecenter iframe");
		iframe1.each(function(i,obj){
			$(this)[0].contentWindow.location.reload();
		});
		mWSize();
		Cookies.setCookie("mSysFont",font);
		mGetUrlGoTabSizeChange(6);*/
	});
	$("#mTop").after(cf);
	$("#mMenu2,#framecenter").show();

	//初始化系统字体大小 2016年10月20日 16:33:02 星期四 lybide
	var font=$.cookie.get("mSysFont");//Cookies.getCookie("mSysFont");//$.cookie.get("mSysFont");
	if (font) {
		setSystemFontType(font);
	}
	//=======================================================

	kFrameConfig.mainEndOnload && kFrameConfig.mainEndOnload();

	//整个页面完成处理后。
	//=============================================================
	if (typeof sysMainPageComplate!="undefined") {
		sysMainPageComplate();
	}
	//新写法，可执行多个页面载入事件 2017年06月18日 22:57:03 星期日 lybide
	/*mainInit.lastFunction.push(function(){
		alert("载入了执行多个页面载入事件");
	});*/
	var mi=mainInit.lastFunction;
	if (mi.length>0) {
		for (items in mi) {
			var item=mi[items];
			item.call();
		};
	}
}

//菜单全部初始化完成 2018年01月16日 10:16:00 星期二 lybide
function sysMainPageMenuAllComplate() {
	//console.log("sysMainPageMenuAllComplate");
}

function setSystemFontType(font) {
	$("html").removeClass("k-page-size-18px k-page-size-16px k-page-size-14px k-page-size-12px k-page-size-");
	$("#mcsfw1 a").removeClass("hover");
	$("#mcsfw1 a[font="+font+"]").addClass("hover");
	if (font) {
		//document.body.style.fontSize=font+"px";
		//$(".m-menu1-a").css("padding-top","16px");
		mFontSize=font;
	} else {
		//document.body.style.fontSize="";
		//$(".m-menu1-a").css("padding-top","23px");
		mFontSize="";
	}
	if (font) {
		$("html").addClass("k-page-size-"+font+"px");
	}
}

function mPanelDispay(obj,ckeys) {

};

function mWSize(arg) {

};

function panelElementChangeEvent(arg) {

};

function welComeIndexShow(arg) {
	//arg == "pageInit" 表示系统在打开时，打开系统
	//if (!arg) {
	//	alert("我需要自定义回到首页");
	//	mGetUrl("http://www.khnt.com");
	//	return;
	//}
	var menuId=null;
	//2014年08月07日 17:14:54 星期四 lybide 2017年06月23日 10:33:50 星期五 lybide
	if ($("#menuAll_s_welcome_desktop").length>0) {
		menuId="s_welcome_desktop";
	}
	if (!menuId) {
		menuId=$("#mMenu1 .m-menu1-wrap:first").attr("menuid");
	}
	//必须延迟一定时间，来初始化点击一级菜单 2017年06月22日 17:46:59 星期四 lybide
	var oneExe1=setTimeout(function(){
		//$("#mMenu_"+menuId+"").click();
		$("#menuAll_"+menuId+"").click();//直接通过全菜单进行点击 2017年06月22日 17:51:29 星期四 lybide
	},1);

}

//多标签时，首页特殊处理 2014/12/4 16:37 lybide 2017年08月10日 17:59:56 星期四 lybide 改进
function welComeIndexShowYesWin(menuId,tabIsExist,url) {
	//console.log(menuId,tabIsExist,url);//return;
	//window.prompt("lybide提示",menuId);
	//document.title=$("#mTab_"+menuId).attr("title");
	if (menuId.indexOf("s_welcome_desktop")!=-1) {
		//alert([menuId,t,url]);
		//welComeIndexShow();return;
		mPanelDispay({panel: 'left', display: false, close: false}, "welComeIndexShowYesWin1");
		$("#m-panel-tab").hide();
	}
	if (menuId=="s_welcome_desktop") {
		$("#mTab_s_welcome_desktop").hide();
		mPanelDispay({panel: 'left', display: false, close: false}, "welComeIndexShowYesWin2");
		$("#m-panel-tab").hide();//2014/12/4 11:18 lybide 标签栏
		var boH1=kFrameConfig["mainToolbar"] ? (parseInt($("#mFoot").css("bottom"))>=0 ? $("#mFoot").height() : 0) : 0;
		//$("#framecenter").css({"top":"","bottom":boH1,"right":0});
		//$('#mTab_panel_'+menuId+'_iframe')[0].contentWindow.location.reload();alert($('#mTab_panel_'+menuId+'_iframe').length);
		//return;
		//2017年08月10日 17:39:05 星期四 lybide 为什么这么做，有可能首页很复杂，会导致其它页面卡死。固不为首页时，把首页src清空
		if (menuId=="s_welcome_desktop" && $("#mTab_panel_s_welcome_desktop_iframe").attr("src")!="about:blank") {
			//$("#mTab_panel_s_welcome_desktop_iframe").attr("data-src",$("#mTab_panel_s_welcome_desktop_iframe").attr("src"));
		}
		if (tabIsExist && menuId=="s_welcome_desktop") {
			//$('#mTab_panel_'+menuId+'_iframe').hide();

			//重新打开首页，先删除，再调用
			//$("#mTab_s_welcome_desktop").find(".tool>.close").click();
			//var oneExe1=setTimeout(function(){welComeIndexShow();},1);
			//mMenu2Create1(menuId);

			//2014/12/23 11:18 lybide 第二次优化 2017年08月10日 17:27:57 星期四 lybide第三次优化
			$("#mTab_panel_s_welcome_desktop").hide();
			$("#mMenu1 .m-menu1-wrap").removeClass("m-menu1-hover m-menu1-click m-menu1-down");
			var mMenuOne=$("#mMenu_"+menuId).addClass("m-menu1-click");
			var mMoreObj=$("#mMenu1More .mbt-menu");
			$("a",mMoreObj).removeClass("a-selected g-selected");
			$("a[group="+$this.parent().attr("group")+"]",mMoreObj).addClass("g-selected");
			$("a[code="+menuId+"]",mMoreObj).addClass("a-selected");
			panelElementChangeEvent();
			//$("mTab_panel_s_welcome_desktop_iframe")[0].contentWindow.location.reload();
			//$("#mTab_panel_s_welcome_desktop_iframe").attr("src",$("#mTab_panel_s_welcome_desktop_iframe").attr("data-src"));//2017年08月10日 18:01:11 星期四 lybide
			mGetUrl(url,menuId);
			var oneExe1=setTimeout(function(){
				$("#mTab_panel_s_welcome_desktop").show();
				//$("#framecenter").css("top","");
			},1);
			//welComeIndexShow();

		}
		return;
	}
	//$("#mTab_panel_s_welcome_desktop_iframe").attr("src","about:blank");
};

//格式化自定义下拉菜单
function mMBTMenuStr(style,aStr,addClass) {
	var str1='<div class="m-m-children">' +
			'	<div class="m-m-children-div '+(addClass || "")+'" style="'+style+'">' +aStr+ '</div>' +
			'	<div class="m-m-children-area" style="'+style+'"></div>' +
			'</div>';
	return str1;
}

//自定义菜单位置 跳转 2014/11/11 17:42 lybide
//例子：menuRelocation("m001","d_sys_param");
function menuRelocation(id1,id2) {
	//[id1 大菜单id 必填],[id2 子菜单id]
	$("#menuAll_"+id1).click();//为所有系统菜单中的a标记
	//mMenu2Create1(id1,false);//采用菜单生成法 2017年08月11日 12:27:57 星期五 lybide
	//$("#mMenu_"+id1).click();
	if (id2) {
		$("#mMenu2_d_"+id2).parents(".hasChildren").find("div:first").attr("isexpand","0").click();
		$("#mMenu2_d_"+id2).click();
	}
};

//绑定键盘事件
$(document).keydown(function(e){
	e=$.kh.getKeyDown(e);
	var t= e.eType;var k1= e.keyCode;
	if ((t=="password" || t=="text" || t=="textarea")) {//输入框中，不执行
		return true;
	}
	if (k1==83) {//s键
		//alert("你点了s键");
	}
	if (k1==77) {//m键

	}
	if (k1==68) {//d键

	}
	if (k1==67) {//c键

	}
	if (k1==37) {//左键

	}
	if (k1==39) {//右键

	}
});