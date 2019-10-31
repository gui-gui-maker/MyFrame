<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="edit">
<title>系统界面设置</title>
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="pub/fileviewer/fileviewer.js"></script>
<script type="text/javascript">
/*kFrameConfig.skinObject={
	skinShow:[
		{"imgx":"k/kui/skins/default/images/basic/skin.png",name:"蓝色之恋",skinName:"default"},
		{"imgx":"k/kui/skins/green/images/basic/skin.png",name:"绿色之心",skinName:"green"},
		{"imgx":"k/kui/skins/gray/images/basic/skin.png",name:"黑色幽默",skinName:"gray"},
		{"imgx":"k/kui/skins/red/images/basic/skin.png",name:"中国红",skinName:"red"},
		{"imgx":"k/kui/skins/yellow/images/basic/skin.png",name:"黄色之金",skinName:"yellow"}
	],
	menuShow:[
		{"imgx":"k/kui/images/menu-skin1.png",name:"系统默认",skinName:"1"},
		{"imgx":"k/kui/images/menu-skin2.png",name:"outlook",skinName:"outlook"}*//*,
		{"imgx":"k/kui/images/menu-skin3.png",name:"树形结构",skinName:"tree"}*//*
	]
};*/
$(function(){



	/*$("#formObj2").initForm({
		success : function(response) {//处理成功
			if (response.success) {
				top.location.reload();
			}
		},
		getSuccess:function(res){
			if(res.data){
				$("#userHeadIcon").attr('src',res.data.userHeadIcon);
			}
		},
		toolbar : [
			{text:"还原默认值",id:"skinSetDe",click:function(){
				var url=top.location.href.split("?")[0];
				top.location=url;
				$("#formObj2").attr("action","pub/sysPersonalize/deletePersonalize.do");
				$("#formObj2").submit();
			}},
			{text : "保存", id:"save", icon : "save", click : function() {
				//alert(top.location.href.split("?")[0])
				var url=top.location.href.split("?")[0];
				//top.location=url+"?skin="+$("#skinShow1_text").val()+"&menu="+$("#menuShow1_text").val()+"";
				$("#formObj2").submit();
			}}, {
				text : "关闭", icon : "cancel", click : function() {
					api.close();
				}
			}
		]
	});*/

	$("#formObj2").initForm({
		success : function(response) {//处理成功
			if (response.success) {
				top.location.reload();
			}
		},
		getSuccess:function(res){
			if(res.data){
				$("#userHeadIcon").attr('src',res.data.userHeadIcon);
			}
		},
		toolbar : [
			{text:"还原默认值",id:"skinSetDe",click:function(){
				var url=top.location.href.split("?")[0];
				top.location=url;
				$("#formObj2").attr("action","pub/sysPersonalize/deletePersonalize.do");
				$("#formObj2").submit();
			}},
			{text : "保存", id:"save", icon : "save", click : function() {
				//alert(top.location.href.split("?")[0])
				var url=top.location.href.split("?")[0];
				//top.location=url+"?skin="+$("#skinShow1_text").val()+"&menu="+$("#menuShow1_text").val()+"";
				if($("#formObj2").validate().form()){
					$("#formObj2").submit();
				}
			}}, {
				text : "关闭", icon : "cancel", click : function() {
					api.close();
				}
			}
		]
	});

	var tabId=$.kh.request("tabId") || 1;//alert(tabId);
	//tabId=5;

	var navtab = $("#tab1").ligerTab();
	navtab.selectTabItem("tabItem"+tabId);


	//=======================================================================================
	//2014-2-14 09:46 lybide
	//=======================================================================================
	//系统样式
	var skin=kFrameConfig.skinObject.skinShow;
	var skin2=$("#skinShow1_text").val() || top.kFrameConfig.mainStyle;
	var str1="";
	for (var i = 0; i < skin.length; i++) {
		var skinItems=skin[i];
		var classStr1="lusswli";
		if (skinItems.skinName==skin2) {
			classStr1+=" selected";
		}
		str1+='<li data-skinName="'+skinItems.skinName+'" class="'+classStr1+'"><div><em></em><img src="'+skinItems.imgx+'" alt=""/><b>'+skinItems.name+'</b></div></li>';
	}
	str1='<div class="l-user-skin-select-wrap"><ul>'+str1+'</ul></div>';
	$("#skinShow1").append(str1);
	$("#skinShow1").find("li").click(function(){
		$("#skinShow1 li").removeClass("selected");
		var that=$(this);
		that.addClass("selected");
		$("#skinShow1_text").val(that.attr("data-skinName"));
	});
	$("#skinShow1_text").val(top.kFrameConfig.mainStyle);

	//系统菜单样式
	var skin=kFrameConfig.skinObject.menuShow;
	var skin2=$("#menuShow1_text").val() || top.kFrameConfig.menu.style;
	var str1="";
	for (var i = 0; i < skin.length; i++) {
		var skinItems=skin[i];
		var classStr1="lusswli";
		if (skinItems.skinName==skin2) {
			classStr1+=" selected";
		}
		str1+='<li data-skinName="'+skinItems.skinName+'" class="'+classStr1+'"><div><em></em><img src="'+skinItems.imgx+'" alt=""/><b>'+skinItems.name+'</b></div></li>';
	}
	str1='<div class="l-user-menu-select-wrap"><ul>'+str1+'</ul></div>';
	$("#menuShow1").append(str1);
	$("#menuShow1").find("li").click(function(){
		$("#menuShow1 li").removeClass("selected");
		var that=$(this);
		that.addClass("selected");
		$("#menuShow1_text").val(that.attr("data-skinName"));
	});
	$("#menuShow1_text").val(top.kFrameConfig.menu.style);

	//=======================================================================================
	//2014-3-19 下午5:29 lybide
	//=======================================================================================
	//桌面背景
	var skin=kFrameConfig.skinObject.sysDeskBg;
	skin.unshift({"imgx":"k/kui/images/sys-desktop/bg/bgnonem.jpg",name:"默认",skinName:"",imgUrl:""});
	var skin2=$("#sysDeskBg1_text").val() || top.kFrameConfig.sysDeskBg;
	var str1="";var classStr1="";
	for (var i = 0; i < skin.length; i++) {
		var skinItems=skin[i];
		var classStr1="lusswli";
		if (skinItems.imgUrl==skin2) {
			classStr1+=" selected";
		}
		str1+='<li data-skinName="'+skinItems.imgUrl+'" class="'+classStr1+'"><div><em></em><img src="'+skinItems.imgx+'" alt=""/><b>'+skinItems.name+'</b></div></li>';
	}
	str1='<div class="l-user-menu-select-wrap"><ul>'+str1+'</ul></div>';
	$("#sysDeskBg1").append(str1);
	$("#sysDeskBg1").find("li").click(function(){
		$("#sysDeskBg1 li").removeClass("selected");
		var that=$(this);
		that.addClass("selected");
		$("#sysDeskBg1_text").val(that.attr("data-skinName"));
	});
	$("#sysDeskBg1_text").val(top.kFrameConfig.sysDeskBg);

	//系统背景
	var skin=kFrameConfig.skinObject.sysMainBg;
	skin.unshift({"imgx":"k/kui/images/sys-desktop/bg/bgnonem.jpg",name:"默认",skinName:"",imgUrl:""});
	var skin2=$("#sysMainBg1_text").val() || top.kFrameConfig.sysMainBg;
	var str1="";
	for (var i = 0; i < skin.length; i++) {
		var skinItems=skin[i];
		var classStr1="lusswli";
		if (skinItems.imgUrl==top.kFrameConfig.sysMainBg) {
			classStr1+=" selected";
		}
		str1+='<li data-skinName="'+skinItems.imgUrl+'" class="'+classStr1+'"><div><em></em><img src="'+skinItems.imgx+'" alt=""/><b>'+skinItems.name+'</b></div></li>';
	}
	str1='<div class="l-user-menu-select-wrap"><ul>'+str1+'</ul></div>';
	$("#sysMainBg1").append(str1);
	$("#sysMainBg1").find("li").click(function(){
		$("#sysMainBg1 li").removeClass("selected");
		var that=$(this);
		that.addClass("selected");
		$("#sysMainBg1_text").val(that.attr("data-skinName"));
	});
	$("#sysMainBg1_text").val(top.kFrameConfig.sysMainBg);

	//首页背景
	var skin=kFrameConfig.skinObject.welcomeMainBg;
	skin.unshift({"imgx":"k/kui/images/sys-desktop/bg/bgnonem.jpg",name:"默认",skinName:"",imgUrl:""});
	var skin2=$("#welcomeMainBg1_text").val() || top.kFrameConfig.welcomeMainBg;
	var str1="";
	for (var i = 0; i < skin.length; i++) {
		var skinItems=skin[i];
		var classStr1="lusswli";
		if (skinItems.imgUrl==top.kFrameConfig.welcomeMainBg) {
			classStr1+=" selected";
		}
		str1+='<li data-skinName="'+skinItems.imgUrl+'" class="'+classStr1+'"><div><em></em><img src="'+skinItems.imgx+'" alt=""/><b>'+skinItems.name+'</b></div></li>';
	}
	str1='<div class="l-user-menu-select-wrap"><ul>'+str1+'</ul></div>';
	$("#welcomeMainBg1").append(str1);
	$("#welcomeMainBg1").find("li").click(function(){
		$("#welcomeMainBg1 li").removeClass("selected");
		var that=$(this);
		that.addClass("selected");
		$("#welcomeMainBg1_text").val(that.attr("data-skinName"));
	});
	$("#welcomeMainBg1_text").val(top.kFrameConfig.sysMainBg);
	//=======================================================================================


	pageTitle({to:"#title",text:"系统界面选择",note:'请选择您喜欢的界面。',icon:"k/kui/images/icons/32/places.png",show:false});
	

});
/**
 function showFiles(val,e,srcObj){
	getFilesPath('pub/sysPersonalize/getHeadPath.do',e);
}
 */

function showFiles(e){
	getFilesPath('pub/sysPersonalize/getHeadPath.do',e);
}
</script>

<style type="text/css" media="screen" id="test">
#skinSetDe {float:left;}
</style>

</head>
<body>
<!-- 提示文字 -->
<div class="item-tm" id="title">

</div>
<!-- 提示文字 -->
<form name="formObj2" id="formObj2" method="post" action="pub/sysPersonalize/savePersonalize.do"
 getAction="pub/sysPersonalize/detailPersonalize.do">
	<input id="skinShow1_text" type="hidden" name="skinStyle" value=""/>
	<input id="menuShow1_text" type="hidden" name="menuStyle" value=""/>
	<input id="sysDeskBg1_text" type="hidden" name="sysDeskBg" value=""/>
	<input id="sysMainBg1_text" type="hidden" name="sysMainBg" value=""/>
	<input id="welcomeMainBg1_text" type="hidden" name="welcomeMainBg" value=""/>
	<input id="userid" type="hidden" name="userid" value="<sec:authentication property='principal.id' />"/>
	<input id="id" type="hidden" name="id"/>
	
<div id="tab1" class="navtab">

	<div title="个性设置" tabid="tabItem1">
		<table cellpadding="3" cellspacing="0" class="l-detail-table">
			<tr>
				<td class="l-t-td-left">头像：</td>
				<td class="l-t-td-right">
					<img id="userHeadIcon" src="k/kui/images/head/default.png" onclick="showFiles(this)" style="cursor: pointer;"/>
					<input name ="userHeadIcon" type="hidden" value="k/kui/images/head/default.png"/>
				</td>
			</tr>
			<tr>
				<td class="l-t-td-left">头像显示：</td>
				<td class="l-t-td-right">
					<input name="userHeadDisplay" ltype="select" ligerui="{initValue:'true',data:[{id:'true',text:'是'},{id:'false',text:'否'}]}" validate="{required:true}"/>
				</td>
			</tr>
			<tr>
				<td class="l-t-td-left">昵称：</td>
				<td class="l-t-td-right"><input name="userNc" type="text" ltype='text' validate="{maxlength:32}" /></td>
			</tr>
			<tr>
				<td class="l-t-td-left">备注：</td>
				<td class="l-t-td-right"><textarea name="remark" ltype='l-textarea' rows="4" validate="{maxlength:256}"></textarea></td>
			</tr>
		</table>
	</div>

	<div title="皮肤设置" tabid="tabItem2">
		<fieldset class="l-fieldset" id="ct1">
			<legend class="l-legend">
				<div>系统样式</div>
			</legend>
			<div id="skinShow1" class="l-user-skin-select"></div>
			<input type="text" name=""  value="" id="" size="20" class="k-form-input-1" ltype="text" ligerui="" validate="{required:true,maxlength:255}"/>
		</fieldset>

		<fieldset class="l-fieldset" id="ct2">
			<legend class="l-legend">
				<div>系统菜单样式</div>
			</legend>
			<div id="menuShow1" class="l-user-skin-select"></div>
		</fieldset>

	</div>
	<div title="桌面背景" tabid="tabItem3">
		<fieldset class="l-fieldset" id="ct3">
			<legend class="l-legend">
				<div>桌面背景</div>
			</legend>
			<div id="sysDeskBg1" class="l-user-skin-select"></div>
		</fieldset>
	</div>
	
	<div title="系统背景" tabid="tabItem4">
		<fieldset class="l-fieldset" id="ct4">
			<legend class="l-legend">
				<div>系统背景</div>
			</legend>
			<div id="sysMainBg1" class="l-user-skin-select"></div>
			<table cellpadding="3" cellspacing="0" class="l-detail-table">
				<tr>
					<td class="l-t-td-left">系统全透明：</td>
					<td class="l-t-td-right">
						<input type="radio" name="sysMainBgAllTrans" ltype="radioGroup" ligerui="{initValue:'0',data:[{id:'1',text:'是'},{id:'0',text:'否'}]}"/>
					</td>
				</tr>
			</table>
		</fieldset>
	</div>

	<div title="首页背景" tabid="tabItem5">
		<fieldset class="l-fieldset" id="welcomeMainBg">
			<legend class="l-legend">
				<div>首页背景</div>
			</legend>
			<div id="welcomeMainBg1" class="l-user-skin-select"></div>
		</fieldset>
	</div>

</div>

</form>

</body>
</html>