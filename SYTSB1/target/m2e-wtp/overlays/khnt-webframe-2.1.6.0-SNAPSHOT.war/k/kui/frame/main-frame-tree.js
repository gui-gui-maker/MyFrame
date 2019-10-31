
//===================================================================================

//开始创建菜单，整个main程序第一入口
function getMainMenuSrc() {
	//$("#framecenter").show();
	top.$.dialog.loading();//2013-7-11 下午6:02 lybide
	//var url = "rbac/user/userMenuTree.do";
	var src = kFrameConfig.menu.src;
	var url=src;
	if (typeof src=="string") {
		$.ajax({
			url:url,
			cache:false,
			dataType:"json", //XML、html、json、jsonp、script、text
			async:true,
			success:function (data, textStatus, jqXHR) {
				try{
					data=kFrameConfig.menu.dataAdd(data);
				}catch (e){}
				createMenu(data);
			},
			error:function (XMLHttpRequest, textStatus, errorThrown) {
				var eStr1 = "菜单加载失败，请刷新页面重试，或者联系技术支持人员！";
				//eStr1 += XMLHttpRequest + "====" + textStatus + "====" + errorThrown;
				alert(eStr1);
			}
		});
	} else if (typeof src=="object") {//直接json
		//13-3-22 上午10:23 lybide
		var data=src;
		data=kFrameConfig.menu.dataAdd(data);
		createMenu(data);//创建一级栏目，第二入口
	}
};

//===================================================================================

//创建一级栏目，第二入口
function createMenu(data) {
	var loopCount=0;
	var d=data;
	var deep = 0;
	var treeHTML = "";
	IS_AM=d[0]["am1"];
	var treeData = d[0].children;
	TREEDATA=treeData;
	var mul=$('<div id="mMenu2Div" class="m-menu-tree"><div class="m-menu2-tree-wrap"><ul id="tree" class="ztree"></ul></div></div>');$("#mMenu2").html(mul);
	//$("#mMenu2Div").append('<ul id="tree" class="ztree"></ul>');
	$("#mMenu1").hide();


	var zTree;
	var demoIframe;
	//console.log(d[0].children);

	var zNodes=TREEDATA;


	var setting = {
		view: {
			dblClickExpand: false,
			showLine: true,
			selectedMulti: false,
			expandSpeed: ($.browser.msie && parseInt($.browser.version)<=6)?"":"fast"
			,cookieKey:true,cookieName:"kFrame_TreeMenu"//2014年08月07日 16:51:10 星期四 lybide
		},
		data: {
			key:{
				name: "text",
				url:"xxxx"
			}
		},
		callback: {
			onClick: function(event,treeId, treeNode) {
				var zTree = $.fn.zTree.getZTreeObj(treeId);
				var url=treeNode.url ? treeNode.url : "";
				//alert(JSON.stringify(treeNode));
				var memuId=treeNode["code"] || treeNode["id"];
				var text=treeNode["text"];
				var icon=treeNode["image16"];
				mGetUrlGo(url,memuId,text,icon,2);
				return;
				if (url.indexOf("javascript:")>=0) {
					eval(url);//执行自定义javascript。eval("javascript:doMind(this);dews();");
				} else {
					mGetUrl(url);
				}
				//top.$.cookie(zTree.setting.view.cookieName+"2",treeNode.id);
				//var zTree = $.fn.zTree.getZTreeObj("tree");
				//zTree.expandNode(treeNode);
			}
		}
	};

	//2014年08月07日 16:56:45 星期四 lybide 新写法
	var treeId="tree";
	var zTree=$.fn.zTree.init($("#"+treeId), setting, zNodes);
	zTree.initSelected();

	$("#mMenu2").show();
	if ($("#mFoldLeft").attr("display")=="0") {//2014-3-24 下午6:07 lybide 如果是系统首页，将不显示左边栏
		mPanelSize({leftWidth:200});
	}

	sysMenuCreateComplete();
	//2018年01月16日 10:48:34 星期二 lybide
	if (typeof sysMainPageMenuAllComplate!="undefined") {
		var ms1=parseInt($("#mMenu2").attr("sysMainPageMenuAllComplate") || 0)+1;
		$("#mMenu2").attr("sysMainPageMenuAllComplate",ms1);
		if (ms1>1) {
			return;
		}
		sysMainPageMenuAllComplate();
	}

	return;
}

//格式化菜单图标
function mGetMenuImg(t1Image,simIconKey,iconCss) {
	var icon1='';
	var iconSize=simIconKey?"16":"32";//2013-11-7 下午5:10 lybide
	if (!$.kh.isNull(t1Image) && t1Image.toLowerCase()!="null") {
		if (t1Image.toString().indexOf("{")>=0) {
			//alert(JSON.stringify(t1Image));
			//13-3-8 下午5:01 lybide
			eval("attroptions = " + t1Image + ";");
			if (attroptions["imgstyle"]) {//{'imgstyle':'background:url()','imgcss':'m-menu1-icon-16'}
				icon1='<div class="hasIcon '+attroptions["imgcss"]+'" style="'+attroptions["imgstyle"]+'"></div>';
			} else if (attroptions["imgcss"]) {//{'imgcss':'m-menu1-icon-16 icon-16-01-001',imgx:-32,imgy:0}
				icon1='<div class="hasIcon '+attroptions["imgcss"]+'" style="background-position:'+attroptions["imgx"]+' '+attroptions["imgy"]+'px;"></div>';
			}
		} else if(t1Image.toString().indexOf(".")>0){
			//icon1='<div class="m-menu1-icon-32"><img src=""/></div>';
			//icon1='<div class="hasIcon m-menu1-icon-'+iconSize+'"><img src="'+kFrameConfig["base"]+t1Image+'" border="0"/></div>';
			//2017年04月01日 14:00:18 星期六 lybide 图标采用背景图方式，以支持 32 48
			icon1='<div class="hasIcon m-menu1-icon-'+iconSize+'"><div style="background-image:url('+kFrameConfig["base"]+t1Image+');"></div></div>';
		}
	} else {
		//<div class="m-menu1-icon" style="background-position:-16px 0px;"></div>
		//icon1='<div class="hasIcon m-menu1-icon-'+iconSize+'"><img src="'+kFrameConfig["menu"]["defaultIconSrc"+(iconSize=="16"?"":iconSize)]+'" border="0"/></div>';
		icon1='<div class="hasIcon m-menu1-icon-'+iconSize+'"><div style="background-image:url('+kFrameConfig["menu"]["defaultIconSrc"+(iconSize=="16"?"":iconSize)]+');"></div></div>';
	}
	//2017年10月23日 15:04:35 星期一 lybide 增加字体文件
	if (iconCss) {
		icon1='<div class="hasIcon m-menu1-icon-'+iconSize+'"><div class="l-icon iconfont '+iconCss+'"></div></div>';
	}
	//icon1='<div class="menu1-icon" style="background:url(\''+icon1+'\') no-repeat center center;"></div>';
	icon1="";//tree 没有图标
	return icon1;
}

//框架面版变换
function mPanelDispay(obj,ckeys) {//13-3-21 下午8:13 lybide
	if (obj["panel"]=="left") {
		if (obj["close"]) {//13-4-16 下午9:36 lybide
			//$("#mFoldLeft").hide();//2014-3-21 上午11:31 lybide 树形菜单中不隐藏左上角收缩按钮
		} else {
			$("#mFoldLeft").show();
		}
		var width=obj["width"] || kFrameConfig["menu"]["style1Width"];
		if (obj["display"]) {
			$("#mFoldLeft").attr("display","0");
			$("#mFoldLeft").removeClass("m-fold-left2");
			$("#mFoldLeft").attr("title","收缩菜单工作区");
			$("#mMenu2").css({"width":width+"px"});
			$("#framecenter,#m-panel-tab").css({"left":width+"px"});
		} else {
			$("#mFoldLeft").attr("display","1");
			$("#mFoldLeft").addClass("m-fold-left2");
			$("#mFoldLeft").attr("title","打开菜单工作区");
			$("#mMenu2").css({"width":"0"});
			$("#framecenter,#m-panel-tab").css({"left":"0"});
		}
	}

	if (obj["panel"]=="top") {
		if (obj["display"]) {
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
			//$("#mMenu1").css({"top":"31px"});//tree效果中不需要
			$("#mUserInfo,#mSystemItem,#systemTitle,#mcsfw1").show();
		} else {
			$("#mFoldTop").attr("display","1");
			$("#mFoldTop").addClass("m-fold-top2");
			$("#mFoldTop").attr("title","打开顶部工作区");
			$("#mTop").css({"height":"40px"});
			$("#mTopLeft").css({"height":"40px"});
			$("#mTopRight").css({"height":"40px"});
			$("#mMenu2").css({"top":"40px"});
			$("#framecenter").css({"top":"40px"});
			$("#m-panel-tab").css({"top":"39px"});
			$("#mFoldTop").css({"top":"15px"});
			$("#mFoldLeft").css({"top":"15px"});
			$("#mMenu1More").css({"top":"10px"});
			//$("#mMenu1").css({"top":"-9px"});
			$("#mUserInfo,#mSystemItem,#systemTitle,#mcsfw1").hide();
		}
	}
	//收缩右工作区
	//收缩底工作区
	mWinChangeSize();
}



//===================================================================================

