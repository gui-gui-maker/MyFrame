function adjustHeight() {
	var bodyHeight = $("body").height();
	var height = bodyHeight - $("#mFoot").height() - $("#mTop").height();
	$("#userSettingConId").height(bodyHeight);
	$("#userSettingId").height(height);
}

function userSetInit(arg) {
	//2016年7月27日 16:40:36 lybide
	var option = {"infoSetting": true, "skinSetting": true, "welcomeMainBgSetting": true, "sysMainBgSetting": true, "bgTransparentSetting": true};
	if (arg) {
		option = $.extend(option, arg);
	}


	//背景
	function parseBg(id) {
		var kv = {
			"indexBg": {"name": "welcomeMainBg", "data": kui.skinObject.sysDeskBg, "def": kui.welcomeMainBg},
			"systemBg": {"name": "sysMainBg", "data": kui.skinObject.sysMainBg, "def": kui.sysMainBg},
			"desktopBg": {"name": "sysDeskBg", "data": kui.skinObject.welcomeMainBg, "def": kui.sysDeskBg}
		};
		if (!$("#" + id).attr("init")) {
			var table = $('<table width="100%" border="1" cellspacing="0" cellpadding="0" class="bgset-table">' +
			'<tr>' +
			'<td colspan="2" rowspan="2" class="bgm"></td>' +
			'<td  class="bgs"></td>' +
			'<td  class="bgs"></td>' +
			'<td  class="bgs"></td>' +
			'<td  class="bgs"></td>' +
			'<td colspan="2" rowspan="2" class="bgm"></td>' +
			'<td  class="bgs"></td>' +
			'</tr>' +
			'<tr>' +
			'<td  class="bgs"></td>' +
			'<td colspan="2" rowspan="2" class="bgm"></td>' +
			'<td  class="bgs"></td>' +
			'<td  class="bgs"></td>' +
			'</tr>' +
			'<tr>' +
			'<td  class="bgs"></td>' +
			'<td  class="bgs"></td>' +
			'<td  class="bgs"></td>' +
			'<td  class="bgs"></td>' +
			'<td  class="bgs"></td>' +
			'<td  class="bgs"></td>' +
			'<td  class="bgs"></td>' +
			'</tr>' +
			'</table>');
			var data = kv[id]["data"];
			var def = kv[id]["def"];
			var name = kv[id]["name"];
			table.find("td").each(function(i) {
				if (i < data.length) {
					$(this).append('<div data-skinname="' + data[i].imgUrl + '"><img src="' + data[i].imgx + '"/><span class="slj-ok"></span></div>')
						.attr("name", name)
						.click(function() {
							table.find("td").not(this).removeClass("selected");
							$(this).toggleClass("selected");
						});
					if (data[i].imgUrl == def) {
						$(this).addClass("selected");
					}
				}

			});
			$("#" + id).append(table).attr("init", true);
		}
	}

	var data = kui["user"];
	if ($("#userSettingConId").length == 0) {
		var top = $("#mTop").height();
		var html = '<div id="userSettingConId" style="position:absolute;display:none;left:0;top:-700px;width: 100%;height: 100%;z-index: 1000">';
		html += '<div id="userSettingId" class="slj-skin-wrap" style="position:relative;left:0;top:' + 0 + 'px;width: 100%;height:100%;background-color:#ffffff;">';//
		html += '<div class="slj-close" title="关闭" onclick="closeSetting();"><div class="iconfont icon-esc"></div></div>';
		html += '<div class="slj-skin-wrap-inner" id="slj-skin-wrap-inner" style="height:545px;">';//padding:10px;border: 1px solid #888888;border-radius:8px 8px 8px 8px;box-shadow:3px 3px 5px 0px #8E8E8E;filter:progid:DXImageTransform.Microsoft.shadow(direction=135,color=#8E8E8E,strength=5);background:#ffffff;


		html += '<div class="slj-skin-tab" id="slj-skin-tab">';
		html += '<div class="slj-skin-name" tabId="1"><div><i class="t_icon01"></i><span>系统设置</span></div></div>';
		html += '<div class="slj-skin-name" tabId="2"><div><i class="t_icon02"></i><span>扩展设置</span></div></div>';
		html += '</div>';
		html += '<div class="slj-skin-top"><a href="javascript:void(0);" onclick="setDefaultPersonalizeSetting(this);" class="slj-redft"><span class="slj-skin-top-icon iconfont icon-fanhui"></span><span class="slj-skin-top-tit">还原默认设置</span></a><a href="javascript:void(0);" onclick="saveSetting(this);" class="slj-save"><span class="slj-skin-top-icon iconfont icon-floppyo"></span><span class="slj-skin-top-tit">保存</span></a></div>';
		html += '<div class="slj-skin-tab-item" tabId="1">';
		if (option["infoSetting"]) {
			html += '<div class="slj-skin-boxes">';
			html += '<h3 class="gexinset"><div class="iconfont icon-touxiang"></div><div>个性设置</div></h3>';
			html += '<div class="slj-skin-boxes-wrap">';
			html += '<div class="slj-skin-gexin">';
			html += '<span class="w01">头像：<img id="userHeadIcon" src="' + (data["icon"]?data["icon"].replace("default.gif", "default.png"):"") + '" onclick="showFiles(this)"  title="点击修改头像" style="cursor: pointer;"/>';
			html += '<input name ="userHeadIcon" type="hidden" value="' + data["icon"] + '"/>';
			html += '</span><span class="w02"> 头像显示：';
			html += '<input type="radio" name="userHeadDisplay" value="true" id="userHeadDisplay1"' + (data["show"] ? " checked" : "") + '/><label for="userHeadDisplay1">是</label> <input type="radio" name="userHeadDisplay" value="false" id="userHeadDisplay0"' + (!data["show"] ? " checked" : "") + '/><label for="userHeadDisplay0">否</label></span>';
			html += '<span class="w03"> 昵称：<input name="userNc" type="text" class="niname" value="' + (data["nickName"] ? data["nickName"] : "") + '"/></span><span class="w04"> 备注：<input name="remark" type="text" class="beizhu"  value="' + (data["remark"] ? data["remark"] : "") + '"/></span></div></div></div>';
		}
		if (option["skinSetting"]) {
			html += '<div class="slj-skin-boxes"><h3 class="skinset"><div class="iconfont icon-pifu"></div><div>皮肤设置</div></h3><div class="alltrans" id="MMpifuSelect">图标方式：<input type="radio" name="fontIconLIcon" value="true" id="fontIconLIcon1"' + (kui["fontIconLIcon"] ? " checked" : "") + '/><label for="fontIconLIcon1">是</label><input type="radio" name="fontIconLIcon" value="false" id="fontIconLIcon0"' + (!kui["fontIconLIcon"] ? " checked" : "") + '/><label for="fontIconLIcon0">否</label></div><div class="slj-skin-boxes-wrap">';
			html += '<div class="slj-skin-sort">';
			html += '<ul class="slj-system" id="colorSetId">';
			html += '<div class="slj-sytit">色彩方案</div>';
			//系统样式
			var skin = kui.skinObject.skinShow;
			for (var i = 0; i < skin.length; i++) {
				var skinItems = skin[i];
				html += '<li name="skinStyle" data-skinname="' + skinItems.skinName + '" ' + (skinItems.skinName == kui.mainStyle ? 'class="selected"' : '') + '><img src="' + skinItems.imgx + '" />' + skinItems.name + '<span class="slj-ok"></span></li>';
			}

			html += '</ul>' +
			'<ul class="slj-menu" id="menuSetId">' +
			'<div class="slj-sytit">菜单风格</div>';

			//系统菜单样式
			skin = kui.skinObject.menuShow;
			for (var i = 0; i < skin.length; i++) {
				var skinItems = skin[i];
				html += '<li name="menuStyle" data-skinname="' + skinItems.skinName + '" ' + (skinItems.skinName == kui.menu.style ? 'class="selected"' : '') + '><img src="' + skinItems.imgx + '" />' + skinItems.name + '<span class="slj-ok"></span></li>';
			}

			html += '</ul>';
			html += '</div>';
			html += '</div>';
			html += '</div>';
		}
		html += '<div class="slj-skin-boxes">';
		html += '<h3 class="beigset" id="bgTitle"><span class="iconfont icon-tupian"></span>';
		if (option["welcomeMainBgSetting"]) {
			html += '';
			html += '<div extId="indexBg">首页背景</div>';
		}
		if (option["sysMainBgSetting"]) {
			html += '<div extId="systemBg">系统背景</div>';
		}
		//'html +=<div extId="desktopBg">桌面背景</div>';
		html += '</h3>';
		if (option["bgTransparentSetting"]) {
			html += '<div class="alltrans">背景透明：<input type="radio" name="sysMainBgAllTrans" value="true" id="sysMainBgAllTrans1"' + (kui["sysMainBgAllTrans"] ? " checked" : "") + '/><label for="sysMainBgAllTrans1">是</label><input type="radio" name="sysMainBgAllTrans" value="false" id="sysMainBgAllTrans0"' + (!kui["sysMainBgAllTrans"] ? " checked" : "") + '/><label for="sysMainBgAllTrans0">否</label></div>';
		}
		html += '</div>';
		html += '<div id="bgBody">';
		if (option["welcomeMainBgSetting"]) {
			html += '<div id="indexBg" class="slj-skin-boxes-wrap"></div>';
		}
		if (option["sysMainBgSetting"]) {
			html += '<div id="systemBg" class="slj-skin-boxes-wrap"></div>';
		}
		//html += '<div id="desktopBg" class="slj-skin-boxes-wrap"></div>';
		html += '</div>';

		html += '</div>';
		html += '<div class="slj-skin-tab-item" id="dd-user-expand-setting" tabId="2"></div>';
		html += '</div>';
		html += '</div>';
		html += '</div>';

		//2016年08月24日 14:11:41 星期三 lybide 格式化样式
		function getStyle(element) {
			var style = element["style"];
			if (!style) {
				return;
			}
			var str1 = "";
			$.each(style, function(name, value) {
				str1 += name + ":" + value + ";";
			});
			return str1 ? " style='" + str1 + "'" : ""
		};
		function getRemarks(item) {
			return (item["remarks"] ? '<div class="dd-user-es-remarks"><div class="dd-user-es-remarks-wrap">' + item["remarks"] + '</div></div>' : '');
		};

		//2016年08月18日 15:53:01 星期四 lybide 读取菜单数据，并生成菜单
		var url = "pub/sysPersonalize/getUserExtSetting.do";//getUserExtSetting.do
		$.getJSON(url, function(data) {
			var obj = $("#dd-user-expand-setting");
			$.each(data.data.items, function(name, item) {
				if (obj.size() > 0) {
					obj.append('<div class="dd-user-es-tr e-type-' + item["element"]["type"] + '" element-type="' + item["element"]["type"] + '" element-name="' + (item["element"]["name"]) + '"  element-id="' + (item["element"]["name"] || item["element"]["id"]) + '" id="dd-user-es-' + item["id"] + '"></div>');
					var wrap = $("#dd-user-es-" + item["id"] + "");
					if (item["disabled"]) {
						wrap.hide();
						return;
					}
					var element = item["element"];
					var str1 = '';
					if (item["text"]) {
						str1 += '<div class="dd-user-es-td1"><div class="dd-user-es-label">' + item["text"] + '</div></div>';
					}
					str1 += '<div class="dd-user-es-td2">';
					str1 += '<div class="dd-user-es-ele">';
					str1 += '<div class="dd-user-es-ele-wrap">';
					if (element) {
						var eid = (element["name"] || element["id"]);
						if (element["type"] == "html") {
							str1 += '<input type="hidden" name="' + element["name"] + '" value="' + element["value"] + '" id="dd-user-es-ele-input-' + eid + '" size="20" class="dd-user-input"' + getStyle(element) + '/>';
							str1 += '' + element["html"] + '';
						} else if (element["type"] == "text") {
							str1 += '<input type="text" name="' + element["name"] + '" value="' + element["value"] + '" id="dd-user-es-ele-input-' + eid + '" size="20" class="dd-user-input"' + getStyle(element) + '/>';
						} else if (element["type"] == "file") {
							str1 += '<input type="file" name="' + element["name"] + '" value="' + element["value"] + '" id="dd-user-es-ele-input-' + eid + '" size="20" class="dd-user-input"' + getStyle(element) + '/>';
						} else if (element["type"] == "textarea") {
							str1 += '<textarea name="' + element["name"] + '" id="dd-user-es-ele-input-' + eid + '" cols="' + (element["cols"] || "30") + '" rows="' + (element["rows"] || "5") + '" class="dd-user-input dd-user-textarea""' + getStyle(element) + '>' + element["value"] + '</textarea>';
						} else if (element["type"] == "checkbox") {
							var element_items = element["items"];
							for (var j = 0 in element_items) {
								var eItem = element_items[j];
								var eid = (eItem["name"] || eItem["id"] || element["name"] + j);
								var checked = "";
								if (("," + element["value"] + ",").indexOf("," + eItem["value"] + ",") >= 0) {
									checked = " checked";
								}
								str1 += '<input type="checkbox" id="dd-user-es-ele-input-' + eid + '" name="' + element["name"] + '" value="' + eItem["value"] + '"' + checked + '/><label for="dd-user-es-ele-input-' + eid + '">' + eItem["text"] + '</label>';
							}
						} else if (element["type"] == "radio") {
							var element_items = element["items"];
							for (var j = 0 in element_items) {
								var eItem = element_items[j];
								var eid = (eItem["name"] || eItem["id"] || element["name"] + j);
								var checked = "";
								if (("," + element["value"] + ",").indexOf("," + eItem["value"] + ",") >= 0) {
									checked = " checked";
								}
								str1 += '<input type="radio" id="dd-user-es-ele-input-' + eid + '" name="' + element["name"] + '" value="' + eItem["value"] + '"' + checked + '/><label for="dd-user-es-ele-input-' + eid + '">' + eItem["text"] + '</label>';
							}
						} else if (element["type"] == "select") {
							str1 += '<select name="' + element["name"] + '" id="dd-user-es-ele-input-' + eid + '" class="dd-user-input-select">';
							var element_items = element["items"];
							for (var j = 0 in element_items) {
								var eItem = element_items[j];
								var eid = (eItem["name"] || eItem["id"] || element["name"] + j);
								var checked = "";
								if (("," + element["value"] + ",").indexOf("," + eItem["value"] + ",") >= 0) {
									checked = " selected";
								}
								str1 += '<option id="' + eid + '" value="' + eItem["value"] + '"' + checked + '>' + eItem["text"] + '</option>';
							}
							str1 += '</select>';
						}
					}
					str1 += '</div>';
					str1 += '</div>';
					str1 += getRemarks(item);
					str1 += '</div>';
					wrap.append(str1);
				}
			});
		});


		$(html).appendTo("body").click(closeSetting);
		$("#bgTitle div").click(function() {
			var id = $(this).attr("extId");
			$(this).addClass("selected").siblings().removeClass("selected");
			parseBg(id);
			$("#" + id).siblings().hide();
			$("#" + id).show();
		});
		$("#colorSetId li,#menuSetId li").click(function() {
			$(this).addClass("selected").siblings().removeClass("selected");
		});
		$("#bgTitle div:first").trigger("click");
		$("#userSettingId .slj-skin-wrap-inner").click(function(event) {
			event.stopPropagation();
		});
		//adjustHeight();
		//tab事件绑定 2016年08月17日 14:59:00 星期三 lybide
		$("#slj-skin-tab > div").click(function() {
			var _this = $(this);
			$("#slj-skin-tab > div").removeClass("hover");
			_this.addClass("hover");
			$("#slj-skin-wrap-inner > .slj-skin-tab-item").hide();
			$("#slj-skin-wrap-inner > [tabid=" + _this.attr("tabid") + "]").show();
		});
		$("#slj-skin-tab > div:eq(0)").click();


	}
	adjustHeight();
	var container = $("#userSettingConId");
	container.animate({top: '0', opacity: 'show'}, "slow");
	//2016年08月24日 13:47:19 星期三 lybide 控制面版大小
	$("#slj-skin-wrap-inner >.slj-skin-tab-item").height($("#slj-skin-wrap-inner").height() - $("#slj-skin-tab").height()).css("overflow", "auto");
}
function closeSetting() {
	closeUserHeadSetting();
	var container = $("#userSettingConId");
	container.animate({top: (0 - container.height()), opacity: 'hide'}, "slow");
}

$(function() {//jQuery页面载入事件
	adjustHeight();
	$(window).resize(function() {
		adjustHeight();
	});
});
//还原默认值
function setDefaultPersonalizeSetting() {

	$.getJSON("pub/sysPersonalize/deletePersonalize.do", function(data) {
		if (data["success"]) {
			var url = window.location.href.split("?")[0];
			window.location = url;
		} else {
			top.$.dialog.notice({
				icon: 'k/kui/images/icons/dialog/32X32/fail.png', content: "操作失败，请重试！"
			});
		}
	});
	$.cookie("c_skinStyle", null);
}
function closeUserHeadSetting() {
	$("#userHeadIconSettingConId").hide("slow", "swing");
}
var ICONDATA = {};
function showFiles() {
	var container = $("#userHeadIconSettingConId");
	if (container.length == 0) {
		var userContainer = $("#userSettingConId .slj-skin-wrap-inner");
		//var height = userContainer.height();

		var html = '<div id="userHeadIconSettingConId" style="position:absolute;display:none;left:0;top:0;width: 100%;height:100%;z-index: 1005;">' +
			'<div class="user-head-skin-wrap" style="position:relative;left:150px;top:30px;width:750px;height:380px;background-color:#fff;border:4px solid #666;">' +

			'<div class="user-head-skin-top"><a href="javascript:upFileList();" class="slj-return">返回上一层</a> <a href="javascript:upfile();"  class="self_upload">自定义头像</a><a href="javascript:closeUserHeadSetting();" title="关闭" class="slj-close"><div class="iconfont l-icon-esc"></div></a></div>' +
			'<ul id="userHeadFileList" style="background-color: white"></ul>' +
			'</div></div>';

		container = $(html).appendTo(userContainer).click(closeUserHeadSetting);

		$("#userHeadIconSettingConId .user-head-skin-wrap").click(function(event) {
			event.stopPropagation();
		});
		getFileList("");
	}
	container.show("slow", "swing");
}

function upFileList() {
	HEADICONLASTDIR = HEADICONLASTDIR.substring(0, HEADICONLASTDIR.lastIndexOf("/"));
	if (HEADICONLASTDIR.length < DEFAULTHEADDIR.length) {
		HEADICONLASTDIR = "";
	}
	getFileList(HEADICONLASTDIR);
}
function getFileList(dir) {
	HEADICONLASTDIR = dir;
	if (ICONDATA[dir]) {
		parseFileList(ICONDATA[dir]);
	} else {
		if (dir != null && dir != '' && dir != undefined) {
			dir = encodeURIComponent(dir);
			dir = dir.replace(/%2F/g, '/').replace(/%3F/g, '?').replace(/%3D/g, '=');
		}
		$.getJSON("pub/sysPersonalize/getHeadPath.do?relatePath=" + dir, function(res) {
			if (res["success"]) {
				parseFileList(res["data"]);
				ICONDATA[dir] = res["data"];
				if (dir == "" && res["data"][0]) {
					DEFAULTHEADDIR = res["data"][0]["uprealtePath"];
				}
			}
		});
	}
}
var DEFAULTHEADSRC = "k/kui/images/head/default.png";
var HEADICONLASTDIR = "";
var DEFAULTHEADDIR = "";
function parseFileList(data) {
	var dir = $("#userHeadFileList");
	dir.empty();
	for (var i in data) {
		var file = data[i];
		var tempSrc = "k/kui/public/sys-set/loading.gif";
		var src = file.relatePath;
		var imageName = src.substring(src.lastIndexOf("/") + 1);
		if (file.isDirectory) {
			src = "k/kui/images/file-type/48/folder.png";
		}
		dir.append("<li><img _src='" + src + "' isdir='" + file.isDirectory + "' " + (file.isDirectory ? "dir='" + file.relatePath + "'" : "") + " src='" + tempSrc + "'/>" + (file.isDirectory ? "<span class='dir-name'>" + imageName + "</span>" : "<span class='slj-ok'></span>") + "</li>");
	}

	$("img", dir).each(function() {
		var img = $(this);
		img.attr("src", $(this).attr("_src"));
		img.on("load", function() {
			if (img.width() > 80) {
				img.width(80)
			}
		});
	});
	$("img", dir).click(function() {
		var t = $(this);
		if (t.attr("isdir") == "true") {
			getFileList(t.attr("dir"));
		} else {
			var p = t.parent();
			p.siblings().not(this).removeClass("selected");
			p.toggleClass("selected");
			var src = t.attr("_src");
			if (!p.hasClass("selected")) {
				src = DEFAULTHEADSRC;
			}
			$("#userHeadIcon").attr("src", src);
			$("input[name=userHeadIcon]").val(src);
		}
	});
	$("img", dir).dblclick(function() {
		var t = $(this);
		if (t.attr("isdir") == "true") {
			getFileList(t.attr("dir"));
		} else {
			var p = t.parent();
			p.siblings().not(this).removeClass("selected");
			p.addClass("selected");
			var src = t.attr("_src");

			$("#userHeadIcon").attr("src", src);
			$("input[name=userHeadIcon]").val(src);
		}
	});
}

var SETTINGDATA = {
	"id": "",
	"userHeadIcon": kui["user"]["icon"],
	"userHeadDisplay": kui["user"]["show"],
	"userNc": kui["nickName"],
	"remark": kui["remark"],
	"menuStyle": kui["menu"] ? kui["menu"]["style"] : "1",
	"fontIconLIcon": kui["fontIconLIcon"],
	"sysMainBgAllTrans": kui["sysMainBgAllTrans"],
	"skinStyle": kui["config"] ? kui["config"]["mainStyle"] : "default",
	"sysDeskBg": kui["sysDeskBg"],
	"sysMainBg": kui["sysMainBg"],
	"welcomeMainBg": kui["welcomeMainBg"]
};
function setSetting(keys) {
	$.getJSON("pub/sysPersonalize/detailPersonalize.do", function(data) {
		if (data["success"]) {
			$.extend(SETTINGDATA, data["data"]);
			var context = $("#userSettingConId");
			$("input[name=userHeadIcon]", context).val(SETTINGDATA["userHeadIcon"]);
			$("#userHeadIcon").attr("src", SETTINGDATA["userHeadIcon"] == "" ? "k/kui/images/head/default.png" : SETTINGDATA["userHeadIcon"]);

			$("input[name=userNc]", context).val(SETTINGDATA["userNc"]);
			$("input[name=remark]", context).val(SETTINGDATA["remark"]);
			$("input[name=userHeadDisplay]", context).each(function() {
				var t = $(this);
				t.attr("checked", t.val() == SETTINGDATA["userHeadDisplay"]);
			});
			$("input[name=sysMainBgAllTrans]", context).each(function() {
				var t = $(this);
				t.attr("checked", t.val() == SETTINGDATA["sysMainBgAllTrans"]);
			});
			$("input[name=fontIconLIcon]", context).each(function() {
				var t = $(this);
				t.attr("checked", t.val() == SETTINGDATA["fontIconLIcon"]);
			});
		}
	});
}
function setSelectedValue(id) {
	var obj = $("#" + id + " .selected");
	if (obj.length == 0) {
		obj = $("#" + id + " td:first");
		if (obj.length > 0) {
			obj.each(function() {
				SETTINGDATA[$(this).attr("name")] = "";
			});
		}
	} else {
		obj.each(function() {
			if (this.tagName.toLowerCase() == "li") {
				SETTINGDATA[$(this).attr("name")] = $(this).attr("data-skinname");
			} else if (this.tagName.toLowerCase() == "td") {
				SETTINGDATA[$(this).attr("name")] = $("div", this).attr("data-skinname");
			}

		});
	}
}
function saveSetting() {
	var context = $("#userSettingConId");
	/*$("input:checked", context).each(function () {
	 SETTINGDATA[$(this).attr("name")] = $(this).val();
	 });*/
	setSelectedValue("colorSetId");
	setSelectedValue("menuSetId");
	setSelectedValue("indexBg");
	setSelectedValue("systemBg");
	setSelectedValue("desktopBg");
	SETTINGDATA["userHeadIcon"] = $("input[name=userHeadIcon]", context).val();
	SETTINGDATA["userNc"] = $("input[name=userNc]", context).val();
	SETTINGDATA["remark"] = $("input[name=remark]", context).val();
	SETTINGDATA["sysMainBgAllTrans"] = $("input[name=sysMainBgAllTrans]:checked", context).val();
	SETTINGDATA["fontIconLIcon"] = $("input[name=fontIconLIcon]:checked", context).val();
	var extConfig = "{";
	//2016年08月24日 15:41:35 星期三 lybide 操作扩展设置值
	$("#dd-user-expand-setting >.dd-user-es-tr").each(function() {
		var _this = $(this);
		var eleType = _this.attr("element-type");
		var eleName = _this.attr("element-name");
		var eleId = "dd-user-es-ele-input-" + _this.attr("element-id");
		var value = "";
		if (eleType == "select") {
			value = $("#" + eleId).val();
		} else if (eleType == "radio") {
			value = $(":checked", _this).val();
		} else if (eleType == "checkbox") {
			value = [];
			var cObj=$(":checkbox", _this);
			if (cObj.size()==1) {
				//只有一个ckeckbox时，checkbox只有两种值，1    or     0      2016年12月01日 15:00:31 星期四 lybide
				$(":checkbox", _this).each(function(i) {
					var __this=$(this);
					if (__this.attr("checked")) {
						value.push("1");
					} else {
						value.push("0");
					}
				});
			} else {
				$(":checked", _this).each(function(i) {
					value.push($(this).val());
				});
			}
			value = value.join(",");
		} else {
			value = $("#" + eleId).val();
		}
		value = $.kh.isNull(value) ? "" : value;
		extConfig += "\"" + eleName + "\"" + ":" + "\"" + value + "\"" + ",";
		SETTINGDATA[""+eleName]=value;
		//console.log(eleId,eleName,value);
	});
	if (extConfig.length > 1)
		extConfig = extConfig.substring(0, extConfig.length - 1)
	SETTINGDATA["extConfig"] = extConfig + "}";
	kuiConfigSetAttribute(SETTINGDATA);
	//console.log(SETTINGDATA);
	//return;
	top.$.dialog.loading("正在保存数据……");
	//Cookies.setCookie("skinStyle", SETTINGDATA["skinStyle"]);
	$.cookie("c_skinStyle", SETTINGDATA["skinStyle"], {expires: 365});
	if (SETTINGDATA["skinStyle"] == "default") {
		$.cookie("c_skinStyle", null);
	}
	//console.log(SETTINGDATA);return;
	$.post("pub/sysPersonalize/savePersonalize.do", SETTINGDATA, function() {
		var url = window.location.href.split("?")[0];
		window.location = url;
	});
}
//接口美图秀秀
function upfile() {
	top.$.dialog({
		width: 500,
		height: 220,
		lock: true,
		title: "自定义头像",
		max: false,
		min: false,
		data: {"window": window},
		content: 'url:pub/xiuxiu/head.jsp'
	}).max();
}
function setIcon(src) {
	closeUserHeadSetting();
	//删除原来的头像
	/*var old = $("input[name=userHeadIcon]").val();
	 if(old.indexOf("fileupload/download.do")!=-1){
	 var tempid = old.substring(old.indexOf('=')+1,old.length);
	 $.getJSON("fileupload/deleteAtt.do",{id:tempid},function(res){})
	 }*/
	$("#userHeadIcon").attr("src", src);
	$("input[name=userHeadIcon]").val(src);
}