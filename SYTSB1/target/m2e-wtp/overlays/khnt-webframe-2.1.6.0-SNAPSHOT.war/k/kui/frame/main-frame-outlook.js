
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
				var eStr1 = "系统菜单，获取数据出错，请联系系统管理员，或重新刷新页面。";
				eStr1 += XMLHttpRequest + "====" + textStatus + "====" + errorThrown;
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
	var mul=$("#mMenu1Div > ul");$("#mMenu1Div").removeClass().addClass("m-menu-outlook");
	var TREEDATALen=TREEDATA.length;
	for (var i = 0; i < TREEDATALen; i++) {
		loopCount++;
		var mData=TREEDATA[i];
		var t1ObjId=mData["code"] || mData["id"] || "__M0_aId__"+i;
		mData["code"]=t1ObjId;//2017年12月13日 14:07:22 把值存入自身
		var text=mData["text"]+"";//2017年06月20日 00:02:32 星期二 lybide
		var textLenW1=$.kh.getBytesLength(text);//计算字的真实长度
		var bigTextClass=textLenW1>12?" mg":"";//超过6个字，有换行。2017年06月20日 00:02:53 星期二 lybide for lasa
		var oneMenu=$('<li id="mMenu_li_'+t1ObjId+'" class="m-menu1-item" code="'+t1ObjId+'"><div id="mMenu_'+t1ObjId+'" menuid="'+t1ObjId+'" image16="'+mData["image16"]+'" image32="'+mData["image32"]+'" iconcss="'+mData["iconCss"]+'" class="m-menu1-wrap" ext-children="'+(mData["children"]?"1":"0")+'"><a class="m-menu1-a">'+(!kui["menu"]["topIconShowOutLook"]?"":mGetMenuImg(mData["image32"],null,mData["iconCss"]))+'<span class="text'+bigTextClass+'">'+text+'</span></a><div class="m-menu1-left"></div><div class="m-menu1-right"></div><div class="m-menu1-3j"></div></div></li>');
		$("#mMenu_"+t1ObjId,oneMenu).attr("url",(!mData["url"]?"":mData["url"]));
		//treeHTML += ;
		mul.append(oneMenu);
	}
	if (!kui["menu"]["topIconShowOutLook"]) {
		$("#mMenu1").addClass("m-menu1-is-outlook-noicon");
	}
	//alert(loopCount);
	//$("#mMenu1Div ul").html(treeHTML);//parseMenu();

	var sub2Key=kui["menu"]["menuSub2switch"]!==false ? true : kui["menu"]["menuSub2switch"];
	if (sub2Key) {
		//右键二级菜单 鼠标移到一级菜单，绑定显示快速二级菜单 2014年11月06日 17:40:14 星期四 lybide
		$(">li",mul).mousedown(
			function (e) {
				var ew=e.which;
				if(3 == ew){//鼠标左键 2014/11/8 11:59 lybide
					var _this=$(this);
					getMenubswssss(_this);
					return;
				}
			}
		);
	}

	//一级主菜单事件绑定
	var menuOne=$("#mMenu1 .m-menu1-wrap");
	menuOne.hover(
		function () {
			var $this=$(this);
			$this.addClass("m-menu1-hover");
		},
		function () {
			var $this=$(this);
			if ($("#menuSub_"+MENU_SUB2_CHECKED).is(":visible")) {
				panelElementChangeEvent();
				//console.log(MENU_SUB2_CHECKED);
				//$("#menuSub_"+MENU_SUB2_CHECKED).mouseout();
			}
			if (!($this.hasClass("m-menu1-click"))) { // && !($this.attr("menu2")=="1")
				$this.removeClass("m-menu1-hover");
			}
		}
	);
	menuOne.click(function(){
		var $this=$(this);
		var url=$this.attr("url");
		var text=$this.text();
		var icon=$this.attr("image16");
		//13-3-12 下午6:13 lybide
		//menuOne.removeClass("m-menu1-click m-menu1-hover m-menu1-down");
		var menuId=$this.attr("menuid");
		$("#mMenu1 .m-menu1-wrap").removeClass("m-menu1-hover m-menu1-click m-menu1-down");
		var mMenuOne=$("#mMenu_"+menuId).addClass("m-menu1-click");
		Cookies.setCookie("mMenu1",menuId);//2017年06月22日 17:13:39 星期四 lybide 新增
		//有二级菜单，或没有二级菜单 //2017年11月24日 14:54:37 星期五 lyibde
		if ($this.attr("ext-children")=="1") {
			mMenu2Create1(menuId);
		} else {
			mPanelDispay({panel:'left',display:false},"mPanelSize5");
		}
		mGetUrlGo(url,menuId,text,icon,1);
		//2014/11/11 11:07 lybide 选中所有菜单中的主菜单
		//var group=$this.parent().attr("group");
		menuGroupStauts(menuId);
		panelElementChangeEvent();
	});
	menuOne.mousedown(function(e){
		if (e.which==1) {
			var $this=$(this);
			$this.addClass("m-menu1-down");
		}
	});
	$("#mMenu2").show();

	//2013-11-7 上午10:26 lybide outlook 效果来了
	//补充页面元素
	//$("#mMenu1").html($("#Menu1").html());
	//$("#mMenu2").html($("#Menu2").html());
	$("#mUserSetMenu").appendTo("#sysMain");
	$("#mUserInfo").after('<div id="mWhitebgBar" class="m-whitebg-bar"></div>');
	$("#framecenter").css("width","auto");
	//$("#home").attr("src","demo/bd1.jsp");

	menuBeginSelectMenu();
	sysMenuCreateComplete();
}

//2017年06月22日 17:33:46 星期四 lybide
//初始化选中所有菜单中的主菜单
function menuGroupStauts(menuId) {
	var mMoreObj=$("#mMenu1More .mbt-menu");
	var group=$("#mMenu_"+menuId).parent().attr("group");
	$("a",mMoreObj).removeClass("a-selected g-selected");
	$("a[group="+group+"]",mMoreObj).addClass("g-selected");
	$("a[code="+menuId+"]",mMoreObj).addClass("a-selected");
}

//2017年06月22日 17:33:14 星期四 lybide
//初始化一级菜单。
function menuBeginSelectMenu() {
	//kFrameConfig.menu["showWelcomeIndex"]=true;
	//kFrameConfig.menu["beginSelectMenu"]=false;
	var menuId=null;
	if (kFrameConfig.menu["showWelcomeIndex"]) {
		var oneExe1=setTimeout(function(){
			welComeIndexShow("pageInit");
		},1);
		return;
	}
	if (kFrameConfig.menu["beginSelectMenu"]) {
		if (!$.kh.isNull(Cookies.getCookie("mMenu1"))) {//初始化一级菜单。
			var c=Cookies.getCookie("mMenu1");//13-3-26 下午8:38 lybide
			if ($("#mMenu_"+c+"").size()>0) {
				menuId=c;
			}
		}
	}
	if (!menuId) {
		menuId=$("#mMenu1 .m-menu1-wrap:first").attr("menuid");
	}
	//必须延迟一定时间，来初始化点击一级菜单 2017年06月22日 17:46:59 星期四 lybide
	var oneExe1=setTimeout(function(){
		//$("#mMenu_"+menuId+"").click();
		$("#menuAll_"+menuId+"").click();//直接通过全菜单进行点击 2017年06月22日 17:51:29 星期四 lybide
	},1);
};

//右键二级菜单 ========================================================================================
var MENU_SUB2_CHECKED;
function getMenubswssss(obj) {
	var _this=obj;
	var code=_this.attr("code");
	var subObj=$("#menuSub_"+code);
	if (MENU_SUB2_CHECKED==code) {
		//subObj.show();return;
	}
	if (subObj.length==0) {
		//鼠标点击时才显示子菜单 2014/11/8 14:29 lybide
		for (var i = 0, l=TREEDATA.length; i < l; i++) {
			var mData=TREEDATA[i];
			var t1ObjId=mData["code"] || mData["id"];
			if (t1ObjId==code) {
				createSubMenu(mData);
			}
		}
	}
	subObj=$("#menuSub_"+code+"");
	$("#sysMain > .m-menu1-sub2").stop().hide();
	var offset=_this.offset();
	//console.log([subObj.width(),subObj.height()]);
	var osls;
	var wi1=$(window).width()-offset.left;
	var jbch=$(window).width()-subObj.width();
	if (subObj.width()>wi1) {
		osls={top:offset.top+_this.height()-0,left:parseInt(jbch)-10,jj3jLeft:parseInt(offset.left-jbch+10)+(_this.width()/2-3)}
	} else {
		osls={top:offset.top+_this.height()-0,left:offset.left,jj3jLeft:(_this.width()/2-3)};
	}
	var he1=$(window).height()-offset.top-150;subObj.css("height","auto");
	$(".wrap",subObj).css("height","auto");
	if (subObj.height()>he1) {
		//osls=$.extend(osls,{height:he1});
		$(".wrap",subObj).height(he1);
	} else {
		//osls=$.extend(osls,{height:"auto"});
	}
	$(".jj3j",subObj).css({"top":0,"left":osls["jj3jLeft"]}).show();
	subObj.css(osls).show("fast");
	$("#mMenu_"+code).attr("menu2","1");
	MENU_SUB2_CHECKED=code;
};
function getMenuSubHasChildren(datas) {
	//判断同层子有无子 2014/11/10 15:15 tm
	if(datas){
		for(var i= 0,l=datas.length;i<l;i++){
			if(datas[i]["children"]){
				return true;
			}
		}
	}
	return false;
}
function getMenuSubStr(mData,deep) {
	//2014/11/10 20:14 lybide 多次递归 lyb tm 完成
	var children=mData["children"];
	var str1="";
	if(children && children.length>0){
		deep++;
		var hasChildren=getMenuSubHasChildren(children);
		if (deep>1) {
			str1= '<div class="'+(hasChildren?"item-null":'item d' + deep)+'">';
		}
		for (var i = 0, l=children.length; i < l; i++) {
			var item=children[i];
			var image16=item["image"];
			var t1ObjId=item["code"] || item["id"];
			var aAttribute=' id="menu_c2_'+t1ObjId+'" url="' + item["url"] + '" menuid="'+t1ObjId+'" image16="'+item["image16"]+'" image32="'+item["image32"]+'" iconcss="'+item["iconCss"]+'" href="javascript:void(0);" winwh="'+item["winwh"]+'"';
			if (item["children"] && item["children"].length>0) {
				str1+='<div class="item d' + deep + '">';
				str1+='<div class="f1"><a class="a1"'+aAttribute+'>'+mGetMenuImg(item["image16"],true,item["iconCss"])+'<span class="text">' + item["text"] + '</span></a></div>';
				str1+=getMenuSubStr(item,deep);
				str1+="</div>";
			} else {
				if (hasChildren) {
					str1+='<div class="item d' + deep + '">';
				}
				str1 += '<a class="ad'+deep+'"'+aAttribute+'>'+mGetMenuImg(item["image16"],true,item["iconCss"])+'<span class="text">' + item["text"] + '</span></a>';
				if (hasChildren) {
					str1+='</div>';
				}
			}
		}
		if (deep>1) {
			str1+='</div>';
		}
	}
	return str1;
}
function createSubMenu(mData) {
	var swss_oneExe1;
	//初始化子菜单 2014/7/10 13:16 lybide
	if (mData["children"] && mData["children"].length>0) {
		//2014/11/10 17:19 lybide
	} else {
		return;
	}
	//2014/11/8 9:26 lybide 子菜单
	var t1ObjId=mData["code"] || mData["id"];
	var onMenuSub=$('<div id="menuSub_'+t1ObjId+'" class="m-menu1-sub2" code="'+t1ObjId+'" text="'+mData["text"]+'"><div class="jj3j"></div><div class="wrap">'+getMenuSubStr(mData,0)+'</div></div>');
	$("a",onMenuSub).click(function(){
		var _this=$(this);
		//2014/12/4 17:27 lybide 修改为通用方法
		goSubMenuClickAction(_this);
		return false;
	});
	onMenuSub.hover(
		function () {
			clearTimeout(swss_oneExe1);
			var _this=$(this);
			var code=_this.attr("code");
			$("#mMenu_"+code).addClass("m-menu1-hover");
			_this.show();
		},
		function () {
			var _this=$(this);
			var code=_this.attr("code");
			swss_oneExe1=setTimeout(function(){
				$("#mMenu_"+code).attr("menu2","0").removeClass("m-menu1-hover");
				_this.hide();
			},400);
		}
	);
	$("#sysMain").append(onMenuSub);
};
function panelElementChangeEvent() {
	$("#sysMain > .m-menu1-sub2").stop().hide();
	$("#mMenu_"+MENU_SUB2_CHECKED).attr("menu2","0").removeClass("m-menu1-hover");
}
//右键二级菜单 ========================================================================================

//框架面版变换
function mPanelDispay(obj,ckeys) {//13-3-21 下午8:13 lybide
	if (obj["panel"]=="left") {
		if (obj["close"]) {//13-4-16 下午9:36 lybide
			$("#mFoldLeft").hide();
		} else {
			$("#mFoldLeft").show();
		}
		var width=obj["width"] || kFrameConfig["menu"]["style1Width"];
		//console.log($("#mMenu2").width()>width,$("#mMenu2").width(),width);
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
			$("#mFoldTop").attr("display","0");
			$("#mFoldTop").removeClass("m-fold-top2");
			$("#mFoldTop").attr("title","收缩顶部工作区");
			$("#mTop").css({"height":""});
			//$("#mTopLeft").css({"height":"60px"});
			//$("#mTopRight").css({"height":"60px"});
			$("#mMenu2").css({"top":""});
			$("#framecenter").css({"top":""});
			$("#m-panel-tab").css({"top":""});
			$("#mFoldTop").css({"top":""});
			$("#mFoldLeft").css({"top":""});
			$("#mUserInfo").css({"top":""});
			$("#mSystemItem").css({"top":""});
			$("#mcsfw1").css({"top":""});
			$("#mMenu1More").css({"top":""});

			//$("#mMenu1").css({"top":kFrameConfig.menu.topIconShowOutLook?"18px":"57px"});
			if (kFrameConfig.menu.topIconShowOutLook) {
				$("#mMenu1").css({"top":"18px"});$("#mMenu1").removeClass("m-menu1-is-outlook-noicon");
			} else {
				$("#mMenu1").css({"top":"57px"});
			}
			$("#mWhitebgBar").css({"top":""});
			$("#systemTitle").show();
			$("#mUserSetMenu").show();
		} else {
			$("#mFoldTop").attr("display","1");
			$("#mFoldTop").addClass("m-fold-top2");
			$("#mFoldTop").attr("title","打开顶部工作区");
			$("#mTop").css({"height":"60px"});
			//$("#mTopLeft").css({"height":"60px"});
			//$("#mTopRight").css({"height":"60px"});
			$("#mMenu2").css({"top":"60px"});
			$("#framecenter").css({"top":"60px"});
			$("#m-panel-tab").css({"top":"59px"});
			$("#mFoldTop").css({"top":"38px"});
			$("#mFoldLeft").css({"top":"38px"});
			$("#mUserInfo").css({"top":"35px"});
			$("#mSystemItem").css({"top":"35px"});
			$("#mcsfw1").css({"top":"35px"});
			$("#mMenu1More").css({"top":"5px"});

			//$("#mMenu1").css({"top":kFrameConfig.menu.topIconShowOutLook?"-40px":"4px"});
			$("#mMenu1").css({"top":"4px"});$("#mMenu1").addClass("m-menu1-is-outlook-noicon");
			$("#mWhitebgBar").css({"top":"34px"});
			$("#systemTitle").hide();
			$("#mUserSetMenu").hide();
		}
	}
	//收缩右工作区
	//收缩底工作区
	mWinChangeSize();
}

//窗口改变事件绑定
function mWSize() {
	//一级菜单调整
	var mObj=$("#mMenu1Div");
	var mmW1=mObj.width();
	//var text1=$("li>a>span",mObj).text();//alert(text1+"=="+$.kh.getBytesLength(text1)*12);
	var m1Obj=[];
	var aw1=0;
	var group=1;//202014/11/10 22:02 lybide 算出组数
	var b=$("li",mObj).each(function(i){
		var $this=$(this);
		//var margin=$this.css("margin");//不能使用此方式，ie可以得到：auot 6px auto auto，但ff却是空字符串。
		//var mr=$this.css("margin-right");if (mr=="auto") {mr=0;}
		//var ml=$this.css("margin-left");if (ml=="auto") {ml=0;}
		var w1=$this.outerWidth(true);//+parseInt(mr)+parseInt(ml)+0;//13-3-18 下午8:26 lybide 这里是关键
		$this.attr("group",group);//2014/11/10 22:18 lybide
		var divfirst=$(">div:first",$this);
		m1Obj.push({menuid:divfirst.attr("menuid"),width:w1,text:$(".m-menu1-a>span",$this).text(),group:group,image16:divfirst.attr("image16"),iconCss:divfirst.attr("iconCss")});
		aw1=aw1+w1;
		if (aw1+($this.next().outerWidth(true))>mmW1) {
			aw1=0;
			group++;
		}
	});
	$("li",mObj).hide();$("li[group=1]",mObj).show();//2014/11/11 11:21 lybide
	var bb=0;var xxx=0;var xxx2=0;var xxx3=0;
	var m1ObjLen=m1Obj.length;
	for (var i = 0; i < m1ObjLen; i++) {
		xxx+=m1Obj[i]["width"]+0;
		if (xxx>mmW1) {
			xxx2=xxx-mmW1;
			xxx3=m1Obj[i]["width"];
			bb=i;
			break;
		}
	}
	var cc="";
	bb=bb;//alert(m1Obj.slice(bb));
	//m1Obj=m1Obj.slice(bb);
	var textMaxLength=120;
	var m1ObjLen=m1Obj.length;
	for (var i = 0; i < m1ObjLen; i++) {
		//cc+=m1Obj[i]["text"]+"=";
		var item=m1Obj[i];//alert(JSON.stringify(m1obji));
		//2013-8-7 下午4:34 lybide 计算字的个数得到宽度
		var textLenW1=$.kh.getBytesLength(item["text"])*(mFontSize?parseInt(mFontSize)/2:6)+60;
		if (textLenW1>textMaxLength) {
			textMaxLength=textLenW1;
		}
		cc+='<a href="javascript:void(0);" id="menuAll_'+item["menuid"]+'" class="g'+item["group"]+'" group="'+item["group"]+'" menuid="'+item["menuid"]+'" code="'+item["menuid"]+'" >'+mGetMenuImg(item["image16"],true,item["iconCss"])+'<span class="text">'+item["text"]+'</span></a>';

		//$("#mMenu_"+m1obji["menuid"],mObj).parent().hide();
	}
	var left1="left:-"+(textMaxLength/2)+"px;";//2014/11/19 20:19 lybide 算出left
	//添加横排效果 2014年11月05日 22:03:39 星期三 lybide
	var addClass="mbt-menu mbt-menu-more1";
	if (i>15) {
		left1="";
		addClass="mbt-menu mbt-menu-more2";
	}
	$("#mMenu1More").html('<a href="javascript:void(0);" class="m-menu1-more-a"><span></span></a>'+mMBTMenuStr("padding:5px;width:"+textMaxLength+"px;"+left1+"",'<div class="m-menu1-more-title"><div>更多的系统</div></div>'+cc,addClass)).show();
	$("#mMenu1More a").click(function(){
		var _this=$(this);
		var mMoreObj=$("#mMenu1More .mbt-menu");
		$("a",mMoreObj).removeClass("a-selected g-selected");
		$("#mMenu1Div li").hide();
		//只显示当前菜单组 2014/11/10 22:25 lybide
		$("#mMenu1Div li[group="+_this.attr("group")+"]").show();
		$("a[group="+_this.attr("group")+"]",mMoreObj).addClass("g-selected");
		_this.addClass("a-selected");
		//mMenu2Create1(_this.attr("code"));
		$("#mMenu_"+_this.attr("code")).click();
	});
	if (bb>0 || xxx>mmW1) {
		$("#mMenu1More").show();
	} else {
		$("#mMenu1More").hide();
	}
	//$(".m-user-info-name > div").html(""+xxx+"|"+mmW1+"|"+xxx2+"|"+aw1+"|"+cc);
	panelElementChangeEvent();
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
			icon1='<div class="hasIcon m-menu1-icon-'+iconSize+'"><div class="m-img" style="background-image:url('+kFrameConfig["base"]+t1Image+');"></div></div>';
		}
	} else {
		//<div class="m-menu1-icon" style="background-position:-16px 0px;"></div>
		//icon1='<div class="hasIcon m-menu1-icon-'+iconSize+'"><img src="'+kFrameConfig["menu"]["defaultIconSrc"+(iconSize=="16"?"":iconSize)]+'" border="0"/></div>';
		icon1='<div class="hasIcon m-menu1-icon-'+iconSize+'"><div class="m-img" style="background-image:url('+kFrameConfig["menu"]["defaultIconSrc"+(iconSize=="16"?"":iconSize)]+');"></div></div>';
	}
	//2017年10月23日 15:04:35 星期一 lybide 增加字体文件
	if (iconCss) {
		icon1='<div class="hasIcon m-menu1-icon-'+iconSize+'"><div class="l-icon iconfont '+iconCss+'"></div></div>';//outlook 关闭字体图标
	}
	//icon1='<div class="menu1-icon" style="background:url(\''+icon1+'\') no-repeat center center;"></div>';
	return icon1;
}

//创建二级菜单
function mMenu2Create1(thisMenuId,autoClickMenu) {//2013-11-7 下午3:09 lybide
	autoClickMenu=autoClickMenu!==false ? true : autoClickMenu;//2017年08月11日 12:23:31 星期五 lybide 菜单是否自动点击第一菜单
	lastDeep=0;
	//$("#mMenu_"+thisMenuId+" .m-menu1-wrap").removeClass("m-menu1-hover m-menu1-click");
	var mMenuOneUrl=$("#mMenu_"+thisMenuId).attr("url");
	//Cookies.setCookie("mMenu",thisMenuId);
	var data;//window.prompt("对话框",JSON.stringify(TREEDATA));
	var TREEDATALen=TREEDATA.length;
	for (var i = 0; i < TREEDATALen; i++) {
		var mData=TREEDATA[i];
		var t1ObjId=mData["code"] || mData["id"];
		if (thisMenuId==t1ObjId) {
			data=mData;
			break;
		}
	}
	var selectMenuObj=data;//13-3-18 下午3:46 lybide
	var menuWidth=data["width"];//alert(menuWidth);
	//alert(data["children"].length);
	data=data["children"] || data["html"];
	if (typeof data=="undefined" && typeof data!="object") {//13-3-22 上午11:50 lybide
		return;
	}
	if (data.length<=0) {
		return;
	}
	//13-3-13 下午6:30 lybide
	$("#mMenu2Div > div").hide();
	var m2Obj=$("#mPanel_"+thisMenuId);
	var m2ObjLength=m2Obj.length;
	if (m2ObjLength==0) {//13-3-13 下午6:48 lybide 完成缓存分离效果
		if (typeof data=="string") {//自定义html
			var m2Obj=$('<div class="m-menu2-1-panel" id="mPanel_'+thisMenuId+'">' +data+'</div>');
			if (menuWidth) {
				m2Obj.attr("data-menu2-width",menuWidth);
			}
			$("#mMenu2Div").append(m2Obj);
		} else {
			var thisMenuCount=0;

			//===============================================================
			//二级菜单格式化
			function mMenu2liStr(mData,deep,liFirstOrLast,t1ChildrenIs,i){
				thisMenuCount++;
				var deepCss="";var clickCss="";
				if ($.kh.isNull(deep)) {
					//deep="";
				} else {
					deepCss=" mm-deep-"+deep+"-li";//13-3-20 下午8:59 lybide
				}
				if (mMenuOneUrl!=="null" && mMenuOneUrl==mData["url"]) {
					clickCss=" m-menu2-1-li-div-click";
				}
				var html='';
				if (mData.text=="-") {
					html='<li class="m-menu2-1-li-line'+deepCss+'"><div class="m-menu2-1-li-line-panle"><div class="m-menu2-1-li-line-left"></div><div class="m-menu2-1-li-line-right"></div></div>';
				} else {
					var isText="";//只显示文字与小图标
					var mImg=[mData["image32"],deep==1?true:false];//16 deep==1?true:false //2014-3-10 上午11:38 lybide
					if (deep==1) {
						mImg[0]=mData["image"];
					}
					if (deep>=2 && mData["children"]) {
						mImg=[mData["image"],true];
					}
					var t1ObjId=mData["code"] || mData["id"] || thisMenuId+"__D"+deep+"_"+i;
					mData["code"]=t1ObjId;//2017年12月13日 14:07:22 把值存入自身
					if (mData["isText"] || (!kui["menu"]["menuIconShowOutLook"] && deep>1)) {//2014/12/24 13:11 lybide 再次优化没有图标
						isText=" is-text";
						if (kui["menu"]["menuIconShowOutLook"]) {
							isText+=" text-hasIcon"
						}
						mImg=[mData["image"],true];
					}
					html='<li class="m-menu2-1-li'+(liFirstOrLast?" "+liFirstOrLast:"")+''+(t1ChildrenIs?" "+" m-menu2-1-li-hasChildren":"")+'" menuId="'+t1ObjId+'">' +
					'<div id="mMenu2_d_'+t1ObjId+'" class="m-menu2-1-li-div m-mdeep'+deep+clickCss+isText+'" image16="'+mData["image16"]+'" image32="'+mData["image32"]+'" iconcss="'+mData["iconCss"]+'" url="'+(!mData["url"]?"":mData["url"])+'" menuId="'+t1ObjId+'">' +
					'<a title="'+mData["text"]+'">'+(!kui["menu"]["menuIconShowOutLook"]?"":mGetMenuImg(mImg[0],mImg[1],mData["iconCss"]))+'<span>'+mData["text"]+'</span></a>' +
					'<div class="m-menu2-1-li-div-left"></div>' +
					'<div class="m-menu2-1-li-div-right"></div>' +
					'<div class="m-menu2-1-li-div-3j"></div>' +
					'<div class="m-t-line'+deepCss+'"></div>' +
					'</div>';
				}
				return html;
			};

			//二级菜单递归函数
			function parseTree(mData,deep,liFirstOrLast,i){
				//var childrenLoopCount=0;
				var t1Children=mData["children"];
				var t1ChildrenIs=t1Children && t1Children.length>0;
				var html=mMenu2liStr(mData,deep,liFirstOrLast,t1ChildrenIs,i);
				//if (typeof t1Children!="undefined" && typeof t1Children=="object") {
					if(t1ChildrenIs){
						deep++;
						html+='<ul class="m-menu2-1-li-ul mm-deep-'+deep+'">'//2012年06月26日 星期二 10:13:33 lybide
						for(var i = 0, l=mData.children.length-1; i <= l; i++){
							//childrenLoopCount++;
							if (deep>lastDeep) {
								lastDeep=deep;
							}
							liFirstOrLast="";
							if (i==0 && l>0) {
								liFirstOrLast="first";
							}
							if (i==l && l>0) {
								liFirstOrLast="last";
							}
							html+=parseTree(mData.children[i],deep,liFirstOrLast,i);
						}
						html+='</ul>';loopCount++;
					}
				//}
				html+='</li>';
				return html;
			}
			//===============================================================

			var treeHTML="";
			for (var i = 0, l=data.length-1; i <= l; i++) {
				var deep=1;
				var mData=data[i],liFirstOrLast="";
				if (i==0 && l>0) {
					liFirstOrLast="first";
				}
				if (i==l && l>0) {
					liFirstOrLast="last";
				}
				var t1Children = mData["children"];
				var t1ChildrenIs=t1Children && t1Children.length>0;
				treeHTML+=mMenu2liStr(mData,deep,liFirstOrLast,t1ChildrenIs,i);
				//if (typeof t1Children!="undefined" && typeof t1Children=="object") {
					if (t1ChildrenIs) {
						deep++;
						//treeHTML=treeHTML.replace(/class=\"m-menu2-1-li\"/ig,"class=\"m-menu2-1-li sssss\"");
						treeHTML += '<ul class="m-menu2-1-li-ul mm-deep-'+deep+'">';
						//var childrenLoopCount=0;
						for (var t2 = 0, l2=t1Children.length-1; t2 <= l2; t2++) {
							loopCount++;
							//childrenLoopCount++;
							liFirstOrLast="";
							if (t2==0 && l2>0) {
								liFirstOrLast="first";
							}
							if (t2==l2 && l2>0) {
								liFirstOrLast="last";
							}
							treeHTML += parseTree(t1Children[t2],deep,liFirstOrLast,t2);
						}
						treeHTML += '</ul>';
					}
				//}
				treeHTML+="</li>";//13-3-14 下午5:31 lybide 完成对多级菜单数据解析
			};//alert(JSON.stringify(selectMenuObj))


			//二级菜单事件绑定
			var m2Obj=$('<div class="m-menu2-1-panel" id="mPanel_'+thisMenuId+'">' +
				'<div class="m-menu2-1-title">' +
				'<div class="m-menu2-1-title-text">'+selectMenuObj["text"]+'</div>'+
				(!kui["menu"]["menuIconShowOutLook"]?"":mGetMenuImg(selectMenuObj["image"],null,selectMenuObj["iconCss"]))+
				//mGetMenuImg(selectMenuObj["image"],true)+//2013-11-12 下午2:35 lybide 删除
				'<div class="m-menu2-1-title-text-left"></div>' +
				'<div class="m-menu2-1-title-text-right"></div>' +
				'<div class="m-menu2-1-title-text-3j"></div>' +
				'</div>' +
				'<ul class="m-menu2-1-div-ul'+(kui["menu"]["menuIconShowOutLook"]?"":" m-no-icon")+'">'+treeHTML+'</ul></div>');
			$("#mMenu2Div").append(m2Obj).removeClass().addClass("m-menu2-outlook-wrap");
			//document.write('<ul id="mUl_'+thisMenuId+'">'+treeHTML+'</ul>');
			//window.prompt("sdfsdf",'<ul id="mmPanel_'+thisMenuId+'">'+treeHTML+'</ul>');
			if (menuWidth) {
				m2Obj.attr("data-menu2-width",menuWidth);
			}
			m2Obj.attr("deep",lastDeep);

			var hasul=$("li",m2Obj).has("ul");
			hasul.find("ul").hide();
			//var nre=$("ul li.m-menu2-1-li-hasChildren",m2Obj);
			//nre.find(":first-child").addClass("first-child");
			//nre.find(":last-child").addClass("last-child");
			//$("ul li.m-menu2-1-li-hasChildren:first-child",m2Obj).addClass("first-child");
			//$("ul li.m-menu2-1-li-hasChildren:last-child",m2Obj).addClass("last-child");
			//hasul.find("ul").find("ul").hide();
			//二级菜单事件绑定
			hasul.find(">div>a:first").append('<div class="m-menu2-1-li-div-hasChildren"></div>').parent().attr("isexpand","0").click(function(e){
				//取消原来的 toggle 事件绑定，使用常用方法绑定，主要用于自动打开树。13-3-27 下午3:29 lybide
				var $this=$(this);
				/*if ($this.next().is("ul.mm-deep-3")) {//2013-11-22 下午4:04 lybide
					$this.next().show();return;
				}*/
				//2014/11/11 17:13 lybide
				/*if (!e.which) {
					$this.attr("isexpand","1");
					$this.next().fadeIn();
					return;
				}*/
				if ($this.is(".m-mdeep1")) {
					if ($this.next().is(":visible")) {//2013-11-22 下午4:04 lybide
						$this.next().hide();
						return;
					}
					//hasul.find("ul").hide();
					m2Obj.find(".mm-deep-2").hide();
					$this.next().fadeIn();
				} else {
					//$this.parent().parent().parent().find(".m-menu2-1-li-hasChildren>div").removeClass("selected");
					if ($this.next().is(":visible")) {//2013-11-22 下午4:04 lybide
						$this.next().hide();
						$this.removeClass("mm-deep-2-prve-select");
					} else {
						$this.next().show();
						$this.addClass("mm-deep-2-prve-select");
					}
				}

				//$this.attr("isexpand")=="0"?$this.attr("isexpand","1"):$this.attr("isexpand","0")
				/*if ($this.attr("isexpand")=="0") {
					$this.attr("isexpand","1");
					$this.next().fadeIn();
					$this.addClass("m-menu2-1-li-div-not-bb");
					$this.find(".m-menu2-1-li-div-hasChildren").addClass("m-menu2-1-li-div-hasChildren2");
				} else {
					$this.attr("isexpand","0");
					$this.next().hide();
					$this.removeClass("m-menu2-1-li-div-not-bb");
					$this.find(".m-menu2-1-li-div-hasChildren").removeClass("m-menu2-1-li-div-hasChildren2");
				}*/
				//记住点击的节点 13-3-27 下午4:39 lybide
				var tPid=[];
				$("#mMenu2 > div > div .m-menu2-1-li-hasChildren-one").each(function(){
					var $this=$(this);
					$this.removeClass("is-text");
					if ($this.attr("isexpand")=="1") {
						tPid.push($this.attr("id"));
					}
				});
				tPid=tPid.join(",");//alert(m2Obj.attr("id")+"\n\n"+tPid);
				Cookies.setCookie("mMenuOpenTreeNode",tPid);
			});
			$("li:last > div > .m-t-line",hasul).addClass("m-t-line-last");//13-3-27 下午3:20 lybide
			//13-3-13 下午3:29 lybide
			//$("li .m-menu2-1-li-div-hasChildren",m2Obj).parent().find(".hasIcon").hide();//13-3-15 下午3:57 lybide 完成对下拉图标的重组 2013-11-7 下午7:00 lybide 关闭
			//$("li:has()",m2Obj).has("ul").find(".m-menu2-1-li-div > a > div").hide();
			//13-3-14 上午11:40 lybide
			//$("li .m-menu2-1-li-div-hasChildren",m2Obj).parent().parent().parent().addClass("m-menu2-1-li-hasChildren").find(">.m-menu2-1-li-div").addClass("m-menu2-1-li-hasChildren-one").append('<div class="m-menu2-1-li-div-hasChildren-div"></div>')//.before(mMenu2liStr({text:"-"}));
			//$("li .m-menu2-1-li-div-hasChildren:last",m2Obj).parent().parent().after(mMenu2liStr({text:"-"}));
			//2014/11/25 14:17 lybide 新写法
			$("li.m-menu2-1-li-hasChildren >.m-menu2-1-li-div",m2Obj).addClass("m-menu2-1-li-hasChildren-one").append('<div class="m-menu2-1-li-div-hasChildren-div"></div>');

			//13-3-15 上午11:09 lybide 移至所有完成后绑定事件
			$(".m-menu2-1-li-div:not(.m-menu2-1-li-hasChildren-one)",m2Obj).click(function(){
				var $this=$(this);
				$("div",m2Obj).removeClass("m-menu2-1-li-div-click m-menu2-1-li-div-over m-menu2-1-li-div-down");
				$this.addClass("m-menu2-1-li-div-click");//window.prompt("对话框",thisMenuId+"|"+$(this).parent().attr("id"));
				Cookies.setCookie("mMenu2",$(this).attr("id"));
				$(".mm-deep-2",m2Obj).prev().removeClass("mm-deep-2-prve-select");
				$this.parents(".mm-deep-2").prev().addClass("mm-deep-2-prve-select");
				var url=$this.attr("url");
				var menuId=$this.attr("menuid");
				var text=$this.text();
				var icon=$this.attr("image16");
				mGetUrlGo(url,menuId,text,icon,2);
			}).hover(function () {
				$(this).addClass("m-menu2-1-li-div-over");
			}, function () {
				$(this).removeClass("m-menu2-1-li-div-over");
			}).mousedown(function(e){
				if (e.which==1) {
					$(this).addClass("m-menu2-1-li-div-down");
				}
			});
			//$(".m-menu2-1-li-hasChildren-one",m2Obj).hover(function () {
			$(".m-menu2-1-li-hasChildren-one",m2Obj).hover(function () {
				$(this).addClass("m-menu2-1-li-hasChildren-one-over");
			}, function () {
				$(this).removeClass("m-menu2-1-li-hasChildren-one-over");
			});
		}
		//m2Obj.hide();
		m2Obj.show();
		mMenu2Resize(m2Obj);

		if (!autoClickMenu) {
			return;
		}

		var isNotShow=true;
		//if (kFrameConfig.menu.beginSelectMenu) {//13-3-27 下午5:49 lybide
			var c=Cookies.getCookie("mMenu2");
			if (c) {
				var o1=$("#"+c+"",m2Obj);//alert(o1.html())
				if (o1.length>0) {
					o1.click();
					isNotShow=false;
					o1.parents(".m-menu2-1-li-hasChildren").find(">div:eq(0)").click();//三级栏目所属二级菜单展开 2013-11-8 下午4:43 lybide
				}
			}
			var tn=Cookies.getCookie("mMenuOpenTreeNode");//alert(tn);
			tn=tn.split(",");
			$.each(tn,function(i,n){
				//alert(tn[i]);
				var tni=tn[i];
				if (!$.kh.isNull(tni)) {
					var obj=$("#"+tni+"",m2Obj);
					if (obj.length>0) {obj.click();}
				}
			});
		//}
		if (isNotShow) {
			//菜单生成时，自动选择子第一个节点 13-4-16 下午2:21 lybide
			var x=$(".m-menu2-1-li-div:first",m2Obj);
			while(x.length>0){
				x.click();
				x=x.next().find(".m-menu2-1-li-div:first");
				x.parents(".m-menu2-1-li-hasChildren").find(">div:first").click();//三级栏目所属二级菜单展开 2013-11-8 下午4:43 lybide
			}
			//x.click();
			//x.next().find(".m-menu2-1-li-div:first").click();
			//x.next().find(".m-menu2-1-li-div:first").click();
		}


		//alert("生成")
	} else {
		m2Obj.show();
		mMenu2Resize(m2Obj);
		if (!autoClickMenu) {
			return;
		}
		$(".m-menu2-1-li-div-click",m2Obj).click();//菜单切换时，显示已被选择的节点。13-4-16 上午10:49 lybide
		$(".m-menu2-1-li-div-click",m2Obj).parents(".m-menu2-1-li-hasChildren").find(">div:first").click();//三级栏目所属二级菜单展开 2013-11-8 下午4:43 lybide
		//alert("显示")
	}
	outlookMenuReSize();
	//2018年07月09日 09:36:32 星期一 lybide 每个二级菜单生成后，回调函数。对此菜单元素进行控制
	if (typeof mMenu2Create1Complate!="undefined") {
		mMenu2Create1Complate(thisMenuId,m2ObjLength);
	}
	//2018年01月16日 10:48:34 星期二 lybide
	if (typeof sysMainPageMenuAllComplate!="undefined") {
		var ms1=parseInt($("#mMenu2").attr("sysMainPageMenuAllComplate") || 0)+1;
		$("#mMenu2").attr("sysMainPageMenuAllComplate",ms1);
		if (ms1>1) {
			return;
		}
		sysMainPageMenuAllComplate();
	}
}

//2017年08月09日 17:45:06 星期三 lybide 分离菜单监听事件
function mMenu2Resize(m2Obj,menuWidth){
	//13-3-19 下午7:31 lybide
	var m2ObjDeep=parseInt(m2Obj.attr("deep"));//alert(m2Obj.attr("deep"));
	var menuWidth=m2Obj.attr("data-menu2-width");
	kFrameConfig["menu"]["style1Width"]=140;
	var msw1=parseInt(kFrameConfig["menu"]["style1Width"]);
	mPanelSize({leftWidth:msw1});return;
	if (m2ObjDeep>=2) {
		mPanelSize({leftWidth:msw1+(m2ObjDeep*15)});
	}
	if (msw1) {
		mPanelSize({leftWidth:msw1});
	}
	if (!$.kh.isNull(menuWidth)) {//可自定义子菜单的宽度，在json中配置："width":"200"
		mPanelSize({leftWidth:parseInt(menuWidth)});
	}
}

//2013-11-8 上午11:28 lybide
//计算菜单高度
function outlookMenuReSize(){
	var mObj=$("#mMenu2");
	var h1=mObj.height()-$(".m-menu2-1-title:visible",mObj).height();//2014-2-18 下午6:13 lybide
	var cObj1=$("#mMenu2Div > div:visible");
	var mm2ObjDeep1=cObj1.find(".m-mdeep1");
	var len1=mm2ObjDeep1.length;
	var itemH1=mm2ObjDeep1.eq(0).outerHeight();
	var jgH1=h1-itemH1*len1;
	if (jgH1>90) {
		cObj1.find(".mm-deep-2").height(jgH1).css("overflow","auto");
		$("#mMenu2Div").css("overflow","hidden");
	} else {
		cObj1.find(".mm-deep-2").height(170).css("overflow","auto");
		$("#mMenu2Div").css("overflow","auto");
	}

}

//ie6窗口改变事件
function mWinChangeSize() {
	if ($.browser.msie && parseFloat($.browser.version) <=7) {
		var h1=$("body").height()-$("#mTop").height()-$("#mFoot").height();//alert(h1+"px");
		var w1=$("body").width()-$("#mMenu2").width()-$("#mRight").width();
		$("#mMenu2,#framecenter").css({"height":h1});//alert(w1+"=="+h1);
	}
	mGetUrlGoTabSizeChange(1);
	//$(".m-center").css({"width":w1});
	//$("#systemTitle").html(w1)
	//$(".m-menu2").get(0).style.height="300px";
	outlookMenuReSize();
}

//===================================================================================

