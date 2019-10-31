var TREEDATA;
function aGuideMenuInit(arg) {
	var url="rbac/user/userMenuTree.do";//"xxx.php?x=111&r="+Math.random();
	$.ajax({
		url:url,
		cache:false,
		dataType:"json", //XML、html、json、jsonp、script、text
		/*beforeSend:function (XMLHttpRequest) {
		 XMLHttpRequest.setRequestHeader("device", "mobile");
		 },*/
		success:function (data, textStatus, jqXHR) {
			//alert(JSON.stringify(data));
			createMenu(data,1,arg);
		},
		error:function (XMLHttpRequest, textStatus, errorThrown) {
			var eStr1 = "获取数据出错，请联系系统管理员";
			eStr1 += XMLHttpRequest + "====" + textStatus + "====" + errorThrown;
			alert(eStr1);
		}
	});
};
function createMenu(data,deep,resCode) {
	$(".a-guide-menu .a-menu-loading").hide();
	var d=data;
	var treeData = d[0].children;
	var code1=resCode;
	for (var i = 0, l=treeData.length; i < l; i++) {
		if (treeData[i]["code"]==code1) {
			treeData=treeData[i].children;
			break;
		}
	}
	TREEDATA=treeData;
	var data=treeData;
	//$("#app-menu").html("");
	createMenuItem(code1,data,1);
	if (deep==1) {
		$("#a-menu-nav").append('<div class="d-big" id="aMenu_big_nav"><a class="a">菜单主页 > </a></div>').find(">.d-big").click(function(){
			var _this=$(this);
			$("#a-menu-nav").find(".d").remove();
			createMenuItem(code1);
		});
	}
};

function getMenuData(menuid,data) {
	if (typeof data=="undefined") {
		data=TREEDATA;
	} else {

	}
	for (var i = 0, l=data.length; i < l; i++) {
		var item=data[i];
		if (item["code"]==menuid) {
			return item.children;
		} else {

			return getMenuData(menuid,item.children);
		}
	}
	return "";
	
};

//2014/12/12 14:31 lybide
function createMenuItem(menuid,data,deep) {
	var aGM=$(".a-guide-menu");
	aGM.find(">.bar").hide();
	var panel=$("#a_menu_item_"+menuid);
	if (panel.length>0) {
		panel.show();
		return;
	}
	panel=$('<div id="a_menu_item_'+menuid+'" class="bar bar02"></div>');
	aGM.append(panel);
	for (var i = 0, l=data.length-1; i <= l; i++) {
		var liFirstOrLast="";
		if (i==0 && l>1) {
			liFirstOrLast="first";
		}
		if (i==l && l>1) {
			liFirstOrLast="last";
		}
		var item=data[i];
		var t1ObjId=item["code"] || item["id"];
		var menu1Str1='<div class="item">' +
			'			<div id="aMenu_'+t1ObjId+'" class="a" href="javascript:void(0);" menuid="'+t1ObjId+'" url="'+item["url"]+'">' +
			'				<div class="icon'+(item["bgcss"]?" "+item["bgcss"]:" clr_"+(i+1))+'" style___="background-color:'+getMDColor(100)+';"><img src="'+(item["image"] || "k/kui/images/icons/64/m1-default.jpg")+'" alt=""></div>' +
			'				<div class="bartxt">'+item["text"]+'</div>' +
			'			</div>' +
			'		</div>';

		//alert(menu1Str1);
		var menu1=$(menu1Str1);
		var a=menu1.find(".a").data({"children":item["children"],deep:deep+1});
		//console.log(item["children"]);
		a.click(function(){
			var _this=$(this);
			var url=_this.attr("url");
			var menuid=_this.attr("menuid");
			var text=_this.text();
			if (url) {
				//window.location=$("base").attr("href")+url;
				window.KHANJS.intentNext(text,$("base").attr("href")+url,"",true);
				return false;
			}
			var children=_this.data("children");
			var deep=_this.data("deep");
			//console.log(children);
			//$("#app-menu").html("");
			createMenuItem(menuid,children,deep);
			if (deep<2) {
				$("#a-menu-nav").find(".d").remove();
			}
			var nav1=$('<div class="d"><a id="aMenu_nav_'+menuid+'" class="a" href="javascript:void(0);" menuid="'+menuid+'">'+text+' ></a></div>');
			var a=nav1.find(".a");
			a.data({"children":children,deep:deep});
			a.click(function(){
				var _this=$(this);
				var url=_this.attr("url");
				var menuid=_this.attr("menuid");//alert(menuid);
				var text=_this.text();
				createMenuItem(menuid);
			})
			$("#a-menu-nav").append(nav1);
		});
		panel.append(menu1);
		if (i==l && l>1) {
			//panel.append('<div style="clear:both;">wefwewef</div>');
		}
	}
};

function getMDColor(arg) {
	return '#'+('00000'+(Math.random()*0x1000000<<0).toString(16)).slice(-6);
};