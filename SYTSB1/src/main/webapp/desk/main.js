//屏蔽所有JS错误，使得出错的情况下不会弹出调试信息，建议在生产环境使用
//window.onerror = function(e) {return true;};

//=======================================================================================
//document.write('<script type="text/javascript" src="k/kui/frame/core.js"><\/script>');
//=======================================================================================
//=======================================================================================
document.write('<script type="text/javascript" src="k/kui/frame/config.js"><\/script>');
//=======================================================================================
$.ajaxSetup({
	cache: false
});

/**
 * 加载js文件函数，过程就是动态创建一个script标签，然后添加到head标签中去。
 * 有一个问题是监听了script标签的两个事件函数，一个是onload，另一个是onreadystatechange，这个数要是针对IE和非IE浏览器准备的
 * 万恶的IE浏览器！！！
 */

function loadJs(url, callback) {
	var done = false;
	var script = document.createElement('script');
	script.type = 'text/javascript';
	script.language = 'javascript';
	script.src = url;
	script.onload = script.onreadystatechange = function() {
		if (!done && (!script.readyState || script.readyState == 'loaded' || script.readyState == 'complete')) {
			done = true;
			script.onload = script.onreadystatechange = null;
			if (callback) {
				callback.call(script);
			}
		}
	}
	document.getElementsByTagName("head")[0].appendChild(script);
}

/**
 * 执行js文件。就是把js文件加载进来，再remove掉
 * @param url js的url
 * @callback 回调函数，执行完js时会调用这个函数
 */
function runJs(url, callback) {
	loadJs(url,
	function() {
		document.getElementsByTagName("head")[0].removeChild(this);
		if (callback) {
			callback();
		}
	});
}

/**
 * 加载css文件。和加载js文件一样，动态创建一个link标签，然后追加到head标签中去
 * @param url css的url
 * @param callback 回调函数，加载完成后调用此函数
 * @param attr css的其它属性，json
 */
function loadCss(url, callback, attr) {
	var link = document.createElement('link');
	link.rel = 'stylesheet';
	link.type = 'text/css';
	link.media = 'screen';
	link.href = url;
	if (attr) {
		for (item in attr) {
			link[item] = attr[item];
		};
	}

	document.getElementsByTagName('head')[0].appendChild(link);
	if (callback) {
		callback.call(link);
	}
}

var api = undefined,
W = undefined,
D = undefined,
win = undefined;
if (window.frameElement) {
	api = win = window.frameElement.api;
	if (api) {
		W = api.opener;
		D = W.document;
	}
}
//PNG透明
document.write('<!--[if IE 6]><script src="k/kui/frame/pngfix/DD_belatedPNG.js"><\/script><![endif]-->');
//截入js配置文件
//document.write('<script src="k/kui/frame/config.jsp"><\/script>');

// khjs
/**
 * kh - jquery
 *
 * Licensed under the GPL:
 *   http://www.khnt.com/licenses/gpl.txt
 *
 * Copyright khnt.com [ idea@khnt.com ]
 *
 * Dependencies:
 *
 */
(function($) {
	$.kh = {};
	$.kh.getFormatDate = function(date, dateformat) {
		if (isNaN(date)) {
			if (typeof date === "string") {
				date = new Date(date.replace(/-/g, "/"));
			}
		} else {
			date = new Date(date);
		}
		if (!date instanceof Date) return null;
		var format = dateformat || "yyyy-MM-dd";
		var o = {
			"M+": date.getMonth() + 1,
			"d+": date.getDate(),
			"h+": date.getHours(),
			"m+": date.getMinutes(),
			"s+": date.getSeconds(),
			"q+": Math.floor((date.getMonth() + 3) / 3),
			"S": date.getMilliseconds()
		}
		if (/(y+)/.test(format)) {
			format = format.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
		}
		for (var k in o) {
			if (new RegExp("(" + k + ")").test(format)) {
				format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
			}
		}
		return format;
	}
	$.kh.mainPagetop = function(o) {
		var str = "";
		str += '<div position="top" class="m_top">' + '	<!-- 框架头部 start -->' + '	<div class="m_top_bg">' + '		<div class="m_caption">' + o.title + '</div>' + '		<div class="m_sys_icn"><a href="#" class="sin_hm">系统首页</a><a href="#" class="sin_pw">修改密码</a><a href="#" class="sin_hp">帮助中心</a><a href="#" class="sin_ex">安全退出</a>' + '		</div>' + '		<div class="m_username">欢迎您，<a>Administrator</a></div>' + '	</div>' + '	<!-- 框架头部 over -->' + '</div>';
		return str;
	}
	//插件的定义
	$.kh.isNull = function(s) { //检查值是否为空
		return s == "" || s === undefined || s === null;
	}
	$.kh.note = function() {

}
	$.kh.getBytesLength = function(str) { //获取真的字符串长度
		var re = /[\x00-\xff]/g;
		var len = str.length;
		var array = str.match(re);
		if (array == null) {
			array = "";
		}
		return len * 2 - array.length;
	}
	$.fn.outerHTML = function(s) {
		return (s) ? this.before(s).remove() : $("p").append(this.eq(0).clone()).html();
	}
	$.kh.getKeyDown = function(e) {
		var ev = e || window.event; //获取event对象
		var obj = ev.target || ev.srcElement; //获取事件源
		var t = obj.type || obj.getAttribute('type'); //获取事件源类型
		//alert(e.which+"===="+e.keyCode);
		var k1 = e.keyCode;
		//e.preventDefault();
		//e.stopPropagation();//阻止js事件冒泡的作用
		return {
			eType: t,
			keyCode: k1
		};
	}
	$.kh.getBase = function() { //取得base值
		return $.kh.isNull($("base").attr("href")) ? "": $("base").attr("href");
	}
	$.kh.request = function(sName) {

		var sURL = new String(window.location);
		var iQMark = sURL.lastIndexOf('?');
		var iLensName = sName.length;

		//retrieve loc. of sName
		var iStart = sURL.indexOf('?' + sName + '=') //limitation 1
		if (iStart == -1) { //not found at start
			iStart = sURL.indexOf('&' + sName + '=') //limitation 1
			if (iStart == -1) { //not found at end
				return ""; //not found
			}
		}

		iStart = iStart + +iLensName + 2;
		var iTemp = sURL.indexOf('&', iStart); //next pair start
		if (iTemp == -1) { //EOF
			iTemp = sURL.length;
		}
		return sURL.slice(iStart, iTemp);
		sURL = null; //destroy String
	}
	$.kh.loadCoreLibrary = function(s) {
		loadCoreLibrary(s);
	}
	$.k = $.kh;
})(jQuery);

//表单取值、赋值
(function($) {
	$.fn.setValues = function(obj, onSuccess, bxpepei) {
		if (!obj) {
			$("body").unmask();
			return;
		};
		bxpepei = bxpepei || false;
		return this.each(function() {
			var type = typeof obj;
			var $this = $(this);
			var status = $this.attr("pageStatus") || $("head").attr("pageStatus");
			var suc = function(d) {};
			var url = $this.attr("getAction");
			if (type == "string") {
				url = obj;
			} else if (type == "object") {
				if (!obj) return;
				if (status == "detail") $.fn.setDivValues(obj, $this);
				else $.fn.setValues.setWithData(obj, $this, bxpepei);
				return;
			} else if (type == "function") {
				suc = obj;
			}
			if (typeof onSuccess == "function") {
				suc = onSuccess;
			}
			if (url) {
				$.ajax({
					url: url,
					$this: $this,
					dataType: 'json',
					success: function(data) {
						if (data.success) {
							if (status == "detail") $.fn.setDivValues(data.data, this.$this);
							else $.fn.setValues.setWithData(data.data, this.$this);
						} else {
							var msg = data.msg || data.data;
							$.ligerDialog.warn(msg);
						}
						$("body").unmask();
						suc(data);
					},
					error: function(XMLHttpRequest, textStatus, errorThrown) {
						$("body").unmask();
						$.ligerDialog.error('加载数据失败！' + textStatus, "error");
					}
				});
			} else { //13-5-2 下午9:18 lybide
				$("body").unmask();
			}

		});
	};
	$.fn.getValues = function() {
		var data = {};
		$(":input", this).not(":submit, :reset, :image,:button, [disabled],[groupChild=true]").each(function() {
			var ele = $(this);
			var manager;
			var ltype = ele.attr("ltype");
			var name = ele.attr("name");
			if (!name) return;
			if ((ele.is(":hidden") || ele.is("textarea")) && !ele.attr("ligeruiid")) {
				if (data[name] === undefined) {
					data[name] = ele.val();
				} else {
					if (!$.isArray(data[name])) {
						data[name] = [data[name]];
					}
					data[name].push(ele.val());
				}
				return;
			} else if (ele.is("textarea")) {
				data[name] = ele.val();
			} else if (ele.is(":text")) {
				switch (ltype) {
				case "select":
					manager = ele.ligerComboBox();
					break;
				case "spinner":
					manager = ele.ligerSpinner();
					break;
				case "date":
					//                        manager = ele.ligerDateEditor();
					data[name] = ele.val();
					break;
				default:
					manager = ele.ligerTextBox();
					break;
				}
			} else if (ele.is(":password")) {
				data[name] = ele.val();
			} else if (ele.is(":radio")) {
				if (ltype == "radioGroup") manager = ele.ligerRadioGroup();
				else manager = ele.ligerRadio();
			} else if (ele.is(":checkbox")) {
				if (ltype == "checkboxGroup") {
					manager = ele.ligerCheckBoxGroup();
				} else manager = ele.ligerCheckBox();
			}
			if (manager) {
				if (data[name] === undefined) {
					data[name] = manager.getValue();
				} else {
					if (!$.isArray(data[name])) {
						data[name] = [data[name]];
					}
					data[name].push(manager.getValue());
				}
			}
		});
		return data;
	};
	function parseName(name, data) {
		if (!data) {
			return undefined;
		}
		if (data[name] !== undefined) return data[name];
		var names = name.split(".");
		var value = data;
		for (var i = 0; i < names.length; i++) {
			if (value[names[i]] != undefined) value = value[names[i]];
			else return undefined;
		}
		return value;
	}

	function parseValues(value, ligerData) {
		if (value == undefined) return "&nbsp;";
		var str = [];
		var values = value.toString().split(KH__valueSplit); //alert(JSON.stringify(ligerData));
		var data = ligerData.data;
		for (var i = 0; i < values.length; i++) {
			if (ligerData.tree) {
				var valueField = "id";
				if (ligerData.valueField) valueField = ligerData.valueField;
				str.push(getTextById(valueField, values[i], data));
			} else {
				$.each(data,
				function(index, item) {
					if (values[i] == item["id"]) {
						if (ligerData && ligerData.lineWrap === false) str += ("<li class='l-lineWrap1-li'><div class='l-lineWrap1-li-div'>" + item["text"] + "</div></li>");
						else str.push(item["text"]);
					}
				});
			}
		}
		if (ligerData && ligerData.lineWrap === false) str = "<ul class='l-lineWrap1'>" + str + "</ul>";
		else str = str.join(";");
		return str == "" ? "&nbsp;": str;
	}

	function getTextById(valueField, id, data) {
		for (var i in data) {
			var temp = null;
			if (id == data[i][valueField]) {
				return data[i]["text"];
			}
			if (data[i]["children"]) {
				temp = getTextById(valueField, id, data[i]["children"]);
			}
			if (temp != null) return temp;
		}
		return null;
	}

	$.fn.setValues.setWithData = function(data, t, bxpepei) {
		if (data === undefined) return;
		function parseName(name, data) {
			if (!data) {
				return undefined;
			}
			if (data[name] !== undefined) return data[name];
			var names = name.split(".");
			var value = data;
			for (var i = 0; i < names.length; i++) {
				if (value[names[i]] != undefined) {
					value = value[names[i]];
				} else {
					return undefined;
				}
			}
			return value;
		}

		$(":input", t).not(":submit, :reset, :image,:button, [disabled]").each(function() {

			var ele = $(this);

			var manager;
			var ltype = ele.attr("ltype");
			var name = ele.attr("name");
			if (ltype == "select") {
				if (ele.attr("id")) name = ele.attr("id").replace(/-txt$/, "");
			}
			if (!name) return;
			var v = parseName(name, data);
			if (v === null) v = "";
			if (v == undefined && bxpepei) { //下拉自选，无值时退出 13-4-15 下午11:11 lybide
				return;
			}
			if ((ele.is(":hidden")) && !ele.attr("ligeruiid")) {
				if (v !== undefined) ele.val(v);
				return;
			} else if (ele.is("textarea")) { //2013-5-27 上午10:20 lybide
				manager = ele.ligerTextBox();
			} else if (ele.is(":text")) {
				switch (ltype) {
				case "select":
					manager = ele.ligerComboBox();
					break;
				case "radioGroup":
					manager = ele.ligerRadioGroup();
					break;
				case "checkboxGroup":
					manager = ele.ligerCheckBoxGroup();
					break;
				case "spinner":
					manager = ele.ligerSpinner();
					break;
				case "date":
					manager = ele.ligerDateEditor();
					break;
				case "text":
					manager = ele.ligerTextBox();
					break;
				}
			} else if (ele.is(":radio")) {
				if (ltype == "radioGroup") {
					if (ele.attr("groupChild") == "true") {
						return;
					}
					manager = ele.ligerRadioGroup();
				} else {
					manager = ele.ligerRadio();
				}
			} else if (ele.is(":checkbox")) {
				if (ltype == "checkboxGroup") {
					if (ele.attr("groupChild") == "true") {
						return;
					}
					manager = ele.ligerCheckBoxGroup();
				} else {
					manager = ele.ligerCheckBox();
				}
			} else if (ele.is("select") && !ltype) { //2013-9-11 上午9:29 lybide 原生下拉框
				if (v !== undefined) {
					ele.val(v);
				}
				return;
			}
			if (manager) {
				if (v !== undefined) manager.setValue(v);
			} else {
				ele.val(v);
			}
		});
	};
	$.fn.setDivValues = function(data, t) {
		if (data == undefined) return;

		$("div.input", t).each(function() {
			var ele = $(this);
			var name = ele.attr("name");
			if (!name) return;
			var v = parseName(name, data);
			if (v === undefined) return;
			$(this).setDivValue(v);
		});

	};
	$.fn.setDivValue = function(v) {
		var ele = $(this);
		var xtype = ele.attr("xtype");
		//        var name = ele.attr("name");
		//        if (!name)return;
		//        var v = parseName(name, data);
		//        if (v === undefined) return;
		if (v === null) {
			ele.html("&nbsp;");
			return;
		}
		if (xtype == "combobox" || xtype == "select" || xtype == "checkboxGroup" || xtype == "radioGroup") {
			var ligerData = ele.data("ligerui");
			if (ligerData.tree) {
				ligerData.data = ligerData.tree.data;
			}
			var bindData = ligerData.data;
			if (bindData) {
				v = parseValues(v, ligerData);
			}
		} else if (xtype == "date") {
			if (isNaN(v)) {
				var flength = ele.data("format").length;
				if (v.length > flength) {
					v = v.substring(0, flength);
				}
			} else {
				v = $.kh.getFormatDate(new Date(v), ele.data("format"));
			}
		} else if (xtype == "spinner") { //2013-9-12 下午4:21 lybide
			var ligerData = ele.data("ligerui");
			if (ligerData.type == "int") {
				v = parseInt(v);
			} else if (ligerData.type == "float") {
				v = new Number(v).toFixed(ligerData.decimalplace || 2);
			}
		}
		if (isNaN(v)) {
			ele.html(v == "" ? "&nbsp;": v.replace(/\n/g, "<br>"));
		} else {
			ele.html(v + "&nbsp;");
		}

	};

	$.transValues = function(datas) {
		var dataArray = [];
		$.each(datas,
		function(index, d) {
			if ($.isArray(d)) {
				$.each(d,
				function(i, dd) {
					dataArray.push({
						name: index,
						value: dd
					});
				});
			} else {
				dataArray.push({
					name: index,
					value: d
				});
			}
		});
		return dataArray;
	}

	$.fn.activeCurrentTab = function() { //邵林添加：激活当前元素所在的tab
		var currentObject = $(this);
		var divTabObj = currentObject.parents("div .navtab");
		if (divTabObj.length > 0) { //当前元素是否在一个Tab里面
			var ligerTab = divTabObj.ligerTab(); //实例化Tab；
			var selectedTabItemId = ligerTab.getSelectedTabItemID(); //当前选中的Tab的id
			if (currentObject.parents("div[tabid='" + selectedTabItemId + "']").get(0) === undefined) {
				//如果当前元素不在当前选中的Tab里面，则激活当前元素所在的Tab
				var thisTabItemId = currentObject.parents("div[tabid]").attr("tabid");
				ligerTab.selectTabItem(thisTabItemId);
			}
		}
	}
	$.fn.unmask = function() {
		var id = $(this).attr("mask_id");
		$("#" + id).hide();
		$("#" + id + "_loading").hide();
	}
	$.fn.mask = function(title) {
		var $this = this;
		title = title || "正在加载数据，请稍候……";
		this.id = "_global_mask_div";
		$(this).attr("mask_id", this.id);
		if ($("#" + this.id).size() > 0) {
			$("#" + this.id).show();
			$("#" + this.id + "_loading").html(title).show();
			//$("#"+this.id+"_text").html(title);
			return;
		}
		this.maskDiv = function() {
			var wnd = $(window),
			doc = $(document);
			if (wnd.height() > doc.height()) {
				wHeight = wnd.height();
			} else {
				wHeight = doc.height();
			}
			//创建遮罩背景
			$("body").append("<div id='" + this.id + "' style='width:100%;'></div>");
			//$("#" + this.id).height(wHeight).css({position:"absolute", top:"0px", left:"0px", background:"#fff", filter:"Alpha(opacity=90);", opacity:"0.3", zIndex:"10000", display:"block"});
			$("#" + this.id).height(wHeight).addClass("l-mask-bg");
		}
		this.sPosition = function(obj) {
			var w, h;
			var mw = obj.outerWidth(true);
			var mh = obj.outerHeight(true);
			var ww = $(window).width();
			var wh = $(window).height();
			var body = $("body");
			var bw = body.width();
			var bh = body.height();
			var st = body.scrollTop();
			var sl = body.scrollLeft();
			w = bw > ww ? bw: ww;
			h = bh > wh ? bh: wh;
			var top = 0;
			var left = 0;
			left = sl > ww ? sl: 0;
			top = st > wh ? st: 0;

			var mTop = parseInt(top + ((h - mh) / 2)); //计算上边距
			var mLeft = parseInt(left + ((w - mw) / 2)); //计算左边距
			return {
				w: w,
				h: h,
				top: mTop,
				left: mLeft
			}; //13-5-2 下午3:07 lybide
			var MyDiv_w = parseInt(obj.width());
			var MyDiv_h = parseInt(obj.height());
			var width = parseInt($(document).width());
			var height = parseInt($(window).height());
			var left = parseInt($(document).scrollLeft());
			var top = parseInt($(document).scrollTop());

			var Div_topposition = top + (height / 2) - (MyDiv_h / 2); //计算上边距
			var Div_leftposition = left + (width / 2) - (MyDiv_w / 2); //计算左边距
			return [Div_topposition, Div_leftposition];
		}
		//this.maskDiv();
		var obj = $("<div class='l-mask-text' id='" + this.id + "_loading'>" + title + "</div>");
		$("body").append(obj);
		obj.show();
		var pos = this.sPosition(obj);
		obj.css({
			top: pos.top + "px",
			left: pos.left + "px"
		});

		var mask = $("<div id='" + this.id + "' class='l-mask-bg'></div>");
		$("body").append(mask);
		mask.width(pos.w);
		mask.height(pos.h);
		var fsdw = 0;
		$(window).resize(function() { //13-5-2 下午3:50 lybide
			if ($("#" + $this.id).is(":visible") == true) {
				var pos = $this.sPosition(obj);
				obj.css({
					top: pos.top + "px",
					left: pos.left + "px"
				});
				mask.width(pos.w);
				mask.height(pos.h);

				fsdw++;
			}
		});
		return this;
	}

	//公用删除功能
	$.del = function(des, url, data) {
		$.ligerDialog.confirm(des,
		function(yes) {
			if (yes) {
				$.post(url, data,
				function(data) {
					if (data.success) {
						Qm.refreshGrid();
					} else {
						var msg = "删除失败！";
						if (data.msg) {
							msg += "<br>" + data.msg;
						} else if (data.data) {
							msg += "<br>" + data.data;
						}
						top.$.dialog.notice({
							icon: '32X32/fail.png',
							content: msg
						});
					}
				},
				"json");
			}
		});
	};

})(jQuery);

/* 对数值进行补零与四舍五入方法，此方法正确。 */
//重写toFixed方法 ,避免四舍六入五取双的问题,直接实现四舍五入
Number.prototype.toFixed = function(d) {
	var s = this + "";
	if (!d) d = 0;
	if (s.indexOf(".") == -1) s += ".";
	s += new Array(d + 1).join("0");
	if (new RegExp("^(-|\\+)?(\\d+(\\.\\d{0," + (d + 1) + "})?)\\d*$").test(s)) {
		var s = "0" + RegExp.$2,
		pm = RegExp.$1,
		a = RegExp.$3.length,
		b = true;
		if (a == d + 2) {
			a = s.match(/\d/g);
			if (parseInt(a[a.length - 1]) > 4) {

				for (var i = a.length - 2; i >= 0; i--) {
					a[i] = parseInt(a[i]) + 1;
					if (a[i] == 10) {
						a[i] = 0;
						b = i != 1;
					} else break;
				}
			}
			s = a.join("").replace(new RegExp("(\\d+)(\\d{" + d + "})\\d$"), "$1.$2");
		}
		if (b) s = s.substr(1);
		return (pm + s).replace(/\.$/, "");
	}
	return this + "";
};

/**
 * 对Date的扩展，增加时间
 * 月(M)、日(d)、12小时(h)、24小时(H)、分(m)、秒(s)、周(E)、季度(q) 可以用 1-2 个占位符
 * 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
 * eg:
 * new Date("2013/1/15 10:09:54").add("y",1) ==> 2013/2/15 10:09:54
 */
Date.prototype.add = function(strInterval, NumDay) {
	//var dtTmp=new Date(dtDate);
	var dtTmp = this;
	if (isNaN(dtTmp)) {
		dtTmp = new Date();
	}
	switch (strInterval) {
	case "s":
		return new Date(Date.parse(dtTmp) + (1000 * NumDay));
	case "n":
		return new Date(Date.parse(dtTmp) + (60000 * NumDay));
	case "h":
		return new Date(Date.parse(dtTmp) + (3600000 * NumDay));
	case "d":
		return new Date(Date.parse(dtTmp) + (86400000 * NumDay));
	case "w":
		return new Date(Date.parse(dtTmp) + ((86400000 * 7) * NumDay));
	case "m":
		return new Date(dtTmp.getFullYear(), (dtTmp.getMonth()) + NumDay, dtTmp.getDate(), dtTmp.getHours(), dtTmp.getMinutes(), dtTmp.getSeconds());
	case "y":
		return new Date((dtTmp.getFullYear() + NumDay), dtTmp.getMonth(), dtTmp.getDate(), dtTmp.getHours(), dtTmp.getMinutes(), dtTmp.getSeconds());
	}
}

/**
 * 对Date的扩展，将 Date 转化为指定格式的String
 * 月(M)、日(d)、12小时(h)、24小时(H)、分(m)、秒(s)、周(E)、季度(q) 可以用 1-2 个占位符
 * 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
 * eg:
 * (new Date()).format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
 * (new Date()).format("yyyy-MM-dd E HH:mm:ss") ==> 2009-03-10 二 20:09:04
 * (new Date()).format("yyyy-MM-dd EE hh:mm:ss") ==> 2009-03-10 周二 08:09:04
 * (new Date()).format("yyyy-MM-dd EEE hh:mm:ss") ==> 2009-03-10 星期二 08:09:04
 * (new Date()).format("yyyy-M-d h:m:s.S") ==> 2006-7-2 8:9:4.18
 */
Date.prototype.format = function(fmt) {
	var o = {
		"M+": this.getMonth() + 1,
		//月份
		"d+": this.getDate(),
		//日
		"h+": this.getHours() % 12 == 0 ? 12 : this.getHours() % 12,
		//小时
		"H+": this.getHours(),
		//小时
		"m+": this.getMinutes(),
		//分
		"s+": this.getSeconds(),
		//秒
		"q+": Math.floor((this.getMonth() + 3) / 3),
		//季度
		"S": this.getMilliseconds() //毫秒
	};
	var week = {
		"0": "/u65e5",
		"1": "/u4e00",
		"2": "/u4e8c",
		"3": "/u4e09",
		"4": "/u56db",
		"5": "/u4e94",
		"6": "/u516d"
	};
	if (/(y+)/.test(fmt)) {
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	}
	if (/(E+)/.test(fmt)) {
		fmt = fmt.replace(RegExp.$1, ((RegExp.$1.length > 1) ? (RegExp.$1.length > 2 ? "/u661f/u671f": "/u5468") : "") + week[this.getDay() + ""]);
	}
	for (var k in o) {
		if (new RegExp("(" + k + ")").test(fmt)) {
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
		}
	}
	return fmt;
}

function dateNs(keys, d1, d2, dateObj) {
	var m = new getDateBBUtil(dateObj),
	mg = [];
	if (keys == "bz") {
		mg = m.getCurrentWeek();
	} else if (keys == "by") {
		mg = m.getCurrentMonth();
	} else if (keys == "bjd") {
		mg = m.getCurrentSeason();
	} else if (keys == "bn") {
		mg = m.getCurrentYear();
	} else if (keys == "sz") {
		mg = m.getPreviousWeek();
	} else if (keys == "sy") {
		mg = m.getPreviousMonth();
	} else if (keys == "sjd") {
		mg = m.getPreviousSeason();
	} else if (keys == "sn") {
		mg = m.getPreviousYear();
	} else {
		mg[0] = m.date();
		mg[1] = m.date();
	}
	if (typeof d1 == "boolean" && d1) {//d1为true
		//return {date1:mg[0],date2:mg[1]};
	} else {
		$("#" + d1).ligerDateEditor().setValue(mg[0].format("yyyy-MM-dd"));
		$("#" + d2).ligerDateEditor().setValue(mg[1].format("yyyy-MM-dd"));
	}
	return {date1:mg[0],date2:mg[1]};
}

var getDateBBUtil = function(d) {
	this.date = function() { //13-5-7 上午10:49 lybide
		var rd;
		if (d) {
			var ds = d.split('-');
			rd = new Date(ds[0], ds[1] - 1, ds[2]);
		} else {
			rd = new Date();
		}
		return rd;
	};

	this.getCurrentDate = function() {
		return this.date();
	};
	/***
	 * 获得当前时间
	 */
	/*this.getCurrentDate = function () {
		return new Date();
	};*/
	/***
	 * 获得本周起止时间
	 */
	this.getCurrentWeek = function() {
		//起止日期数组
		var startStop = new Array();
		//获取当前时间
		var currentDate = this.getCurrentDate();
		//返回date是一周中的某一天
		var week = currentDate.getDay();
		//返回date是一个月中的某一天
		var month = currentDate.getDate();

		//一天的毫秒数
		var millisecond = 1000 * 60 * 60 * 24;
		//减去的天数
		var minusDay = week != 0 ? week - 1 : 6;
		//alert(minusDay);
		//本周 周一
		var monday = new Date(currentDate.getTime() - (minusDay * millisecond));
		//本周 周日
		var sunday = new Date(monday.getTime() + (6 * millisecond));
		//添加本周时间
		startStop.push(monday); //本周起始时间
		//添加本周最后一天时间
		startStop.push(sunday); //本周终止时间
		//返回
		return startStop;
	};

	/***
	 * 获得本月的起止时间
	 */
	this.getCurrentMonth = function() {
		//起止日期数组
		var startStop = new Array();
		//获取当前时间
		var currentDate = this.getCurrentDate();
		//获得当前月份0-11
		var currentMonth = currentDate.getMonth();
		//获得当前年份4位年
		var currentYear = currentDate.getFullYear();
		//求出本月第一天
		var firstDay = new Date(currentYear, currentMonth, 1);

		//当为12月的时候年份需要加1
		//月份需要更新为0 也就是下一年的第一个月
		if (currentMonth == 11) {
			currentYear++;
			currentMonth = 0; //就为
		} else {
			//否则只是月份增加,以便求的下一月的第一天
			currentMonth++;
		}

		//一天的毫秒数
		var millisecond = 1000 * 60 * 60 * 24;
		//下月的第一天
		var nextMonthDayOne = new Date(currentYear, currentMonth, 1);
		//求出上月的最后一天
		var lastDay = new Date(nextMonthDayOne.getTime() - millisecond);

		//添加至数组中返回
		startStop.push(firstDay);
		startStop.push(lastDay);
		//返回
		return startStop;
	};

	/**
	 * 得到本季度开始的月份
	 * @param month 需要计算的月份
	 ***/
	this.getQuarterSeasonStartMonth = function(month) {
		var quarterMonthStart = 0;
		var spring = 0; //春
		var summer = 3; //夏
		var fall = 6; //秋
		var winter = 9; //冬
		//月份从0-11
		if (month < 3) {
			return spring;
		}

		if (month < 6) {
			return summer;
		}

		if (month < 9) {
			return fall;
		}

		return winter;
	};

	/**
	 * 获得该月的天数
	 * @param year年份
	 * @param month月份
	 * */
	this.getMonthDays = function(year, month) {
		//本月第一天 1-31
		var relativeDate = new Date(year, month, 1);
		//获得当前月份0-11
		var relativeMonth = relativeDate.getMonth();
		//获得当前年份4位年
		var relativeYear = relativeDate.getFullYear();

		//当为12月的时候年份需要加1
		//月份需要更新为0 也就是下一年的第一个月
		if (relativeMonth == 11) {
			relativeYear++;
			relativeMonth = 0;
		} else {
			//否则只是月份增加,以便求的下一月的第一天
			relativeMonth++;
		}
		//一天的毫秒数
		var millisecond = 1000 * 60 * 60 * 24;
		//下月的第一天
		var nextMonthDayOne = new Date(relativeYear, relativeMonth, 1);
		//返回得到上月的最后一天,也就是本月总天数
		return new Date(nextMonthDayOne.getTime() - millisecond).getDate();
	};

	/**
	 * 获得本季度的起止日期
	 */
	this.getCurrentSeason = function() {
		//起止日期数组
		var startStop = new Array();
		//获取当前时间
		var currentDate = this.getCurrentDate();
		//获得当前月份0-11
		var currentMonth = currentDate.getMonth();
		//获得当前年份4位年
		var currentYear = currentDate.getFullYear();
		//获得本季度开始月份
		var quarterSeasonStartMonth = this.getQuarterSeasonStartMonth(currentMonth);
		//获得本季度结束月份
		var quarterSeasonEndMonth = quarterSeasonStartMonth + 2;

		//获得本季度开始的日期
		var quarterSeasonStartDate = new Date(currentYear, quarterSeasonStartMonth, 1);
		//获得本季度结束的日期
		var quarterSeasonEndDate = new Date(currentYear, quarterSeasonEndMonth, this.getMonthDays(currentYear, quarterSeasonEndMonth));
		//加入数组返回
		startStop.push(quarterSeasonStartDate);
		startStop.push(quarterSeasonEndDate);
		//返回
		return startStop;
	};

	/***
	 * 得到本年的起止日期
	 *
	 */
	this.getCurrentYear = function() {
		//起止日期数组
		var startStop = new Array();
		//获取当前时间
		var currentDate = this.getCurrentDate();
		//获得当前年份4位年
		var currentYear = currentDate.getFullYear();

		//本年第一天
		var currentYearFirstDate = new Date(currentYear, 0, 1);
		//本年最后一天
		var currentYearLastDate = new Date(currentYear, 11, 31);
		//添加至数组
		startStop.push(currentYearFirstDate);
		startStop.push(currentYearLastDate);
		//返回
		return startStop;
	};

	/**
	 * 返回上一个月的第一天Date类型
	 * @param year 年
	 * @param month 月
	 **/
	this.getPriorMonthFirstDay = function(year, month) {
		//年份为0代表,是本年的第一月,所以不能减
		if (month == 0) {
			month = 11; //月份为上年的最后月份
			year--; //年份减1
			return new Date(year, month, 1);
		}
		//否则,只减去月份
		month--;
		return new Date(year, month, 1);;
	};

	/**
	 * 获得上一月的起止日期
	 * ***/
	this.getPreviousMonth = function() {
		//起止日期数组
		var startStop = new Array();
		//获取当前时间
		var currentDate = this.getCurrentDate();
		//获得当前月份0-11
		var currentMonth = currentDate.getMonth();
		//获得当前年份4位年
		var currentYear = currentDate.getFullYear();
		//获得上一个月的第一天
		var priorMonthFirstDay = this.getPriorMonthFirstDay(currentYear, currentMonth);
		//获得上一月的最后一天
		var priorMonthLastDay = new Date(priorMonthFirstDay.getFullYear(), priorMonthFirstDay.getMonth(), this.getMonthDays(priorMonthFirstDay.getFullYear(), priorMonthFirstDay.getMonth()));
		//添加至数组
		startStop.push(priorMonthFirstDay);
		startStop.push(priorMonthLastDay);
		//返回
		return startStop;
	};

	/**
	 * 获得上一周的起止日期
	 * **/
	this.getPreviousWeek = function() {
		//起止日期数组
		var startStop = new Array();
		//获取当前时间
		var currentDate = this.getCurrentDate();
		//返回date是一周中的某一天
		var week = currentDate.getDay();
		//返回date是一个月中的某一天
		var month = currentDate.getDate();
		//一天的毫秒数
		var millisecond = 1000 * 60 * 60 * 24;
		//减去的天数
		var minusDay = week != 0 ? week - 1 : 6;
		//获得当前周的第一天
		var currentWeekDayOne = new Date(currentDate.getTime() - (millisecond * minusDay));
		//上周最后一天即本周开始的前一天
		var priorWeekLastDay = new Date(currentWeekDayOne.getTime() - millisecond);
		//上周的第一天
		var priorWeekFirstDay = new Date(priorWeekLastDay.getTime() - (millisecond * 6));

		//添加至数组
		startStop.push(priorWeekFirstDay);
		startStop.push(priorWeekLastDay);

		return startStop;
	};

	/**
	 * 得到上季度的起始日期
	 * year 这个年应该是运算后得到的当前本季度的年份
	 * month 这个应该是运算后得到的当前季度的开始月份
	 * */
	this.getPriorSeasonFirstDay = function(year, month) {
		var quarterMonthStart = 0;
		var spring = 0; //春
		var summer = 3; //夏
		var fall = 6; //秋
		var winter = 9; //冬
		//月份从0-11
		switch (month) { //季度的其实月份
		case spring:
			//如果是第一季度则应该到去年的冬季
			year--;
			month = winter;
			break;
		case summer:
			month = spring;
			break;
		case fall:
			month = summer;
			break;
		case winter:
			month = fall;
			break;

		}
		return new Date(year, month, 1);
	};

	/**
	 * 得到上季度的起止日期
	 * **/
	this.getPreviousSeason = function() {
		//起止日期数组
		var startStop = new Array();
		//获取当前时间
		var currentDate = this.getCurrentDate();
		//获得当前月份0-11
		var currentMonth = currentDate.getMonth();
		//获得当前年份4位年
		var currentYear = currentDate.getFullYear();
		//上季度的第一天
		var priorSeasonFirstDay = this.getPriorSeasonFirstDay(currentYear, currentMonth);
		//上季度的最后一天
		var priorSeasonLastDay = new Date(priorSeasonFirstDay.getFullYear(), priorSeasonFirstDay.getMonth() + 2, this.getMonthDays(priorSeasonFirstDay.getFullYear(), priorSeasonFirstDay.getMonth() + 2));
		//添加至数组
		startStop.push(priorSeasonFirstDay);
		startStop.push(priorSeasonLastDay);
		return startStop;
	};

	/**
	 * 得到去年的起止日期
	 * **/
	this.getPreviousYear = function() {
		//起止日期数组
		var startStop = new Array();
		//获取当前时间
		var currentDate = this.getCurrentDate();
		//获得当前年份4位年
		var currentYear = currentDate.getFullYear();
		currentYear--;
		var priorYearFirstDay = new Date(currentYear, 0, 1);
		var priorYearLastDay = new Date(currentYear, 11, 31);
		//添加至数组
		startStop.push(priorYearFirstDay);
		startStop.push(priorYearLastDay);
		return startStop;
	};
};

//自定义数组键字对。公用类，创建页面cookies的功能，采用数组行式记住，这样做不用频繁真正的cookies。
/*
* 2009年09月01日 星期二 15:03:11 lybide 2009年09月08日 星期二 16:02:29 2013-06-28 11:58:15 lybide
*/
function ArrayObj() {
	this.arr = new Array();
	this.Add = function(str1, str2) {
		//str1为key值，str2为value值
		//this.arr[this.arr.length]=new Array(str1,str2);
		this.arr.push(new Array(str1, str2));
		//alert(this.arr)
	}
	this.Exists = function(str) {
		var a = this.arr;
		var l = a.length;
		for (var i = 0; i < l; i++) {
			if (a[i][0] == str) {
				return true;
			}
		}
		return false;
	}
	this.Remove = function(str) {
		var a = this.arr;
		var l = a.length;
		for (var i = 0; i < l; i++) {
			if (a[i][0] == str) {
				a.splice(i, 1);
				return true;
			}
		}
	}
	this.RemoveAll = function(str) {
		var a = this.arr;
		var l = a.length;
		for (var i = 0; i < l; i++) {
			a.splice(i, 1);
		}
		return true;
	}
	this.Item = function(str) {
		var a = this.arr;
		var l = a.length;
		for (var i = 0; i < l; i++) {
			if (a[i][0] == str) {
				return a[i][1];
			}
		}
	}
}

//2009年09月09日 星期三 16:32:37 lybide
/*
$.cookie("bfss",null);
$.cookie("c_userName",null);
$.cookie("wefwefwef","11111");
$.cookie("wefwefwef",null);
$.cookie("loginBgKeys",null);
* */
//(function ($) {
$.cookie = function(name, value, options) {
	if (typeof value != 'undefined') { // name and value given, set cookie
		options = options || {};
		if (value === null) {
			value = '';
			options.expires = -1;
		}
		var expires = '';
		if (options.expires && (typeof options.expires == 'number' || options.expires.toUTCString)) {
			var date;
			if (typeof options.expires == 'number') {
				date = new Date();
				date.setTime(date.getTime() + (options.expires * 24 * 60 * 60 * 1000));
			} else {
				date = options.expires;
			}
			expires = '; expires=' + date.toUTCString(); // use expires attribute, max-age is not supported by IE
		}
		var path = options.path ? '; path=' + options.path: '; path=/'; //不指定path，cookies为整个项目公用
		var domain = options.domain ? '; domain=' + options.domain: '';
		var secure = options.secure ? '; secure': '';
		document.cookie = [name, '=', encodeURIComponent(value), expires, path, domain, secure].join('');
	} else { // only name given, get cookie
		var cookieValue = null;
		if (document.cookie && document.cookie != '') {
			var cookies = document.cookie.split(';');
			for (var i = 0; i < cookies.length; i++) {
				var cookie = jQuery.trim(cookies[i]);
				// Does this cookie string begin with the name we want?
				if (cookie.substring(0, name.length + 1) == (name + '=')) {
					cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
					break;
				}
			}
		}
		return cookieValue;
	}
};
//})(jQuery);

/*
Cookies.setCookie("aaaaa","123");
Cookies.setCookie("bbbb","123");
Cookies.setCookie("cccc","123");
Cookies.setCookie("cccc",null);//删除cookies
alert(Cookies.getCookieValue());
* */

//13-3-13 下午7:57 lybide
function CreateCookies() {
	this.getCookiesKey = function() { //检查全局cookies开关
		var cookieKey;
		if (typeof kui.cookiesKey == "undefined") {
			cookieKey = true;
		} else {
			cookieKey = kui.cookiesKey;
		}
		return cookieKey;
	}
	this.getSrcObj = function() {
		var cookieObj = null;
		if (!this.getCookiesKey()) {
			return null;
		}
		if (typeof top.cookieCase == "undefined") {
			cookieObj = null;
		} else {
			cookieObj = top.cookieCase;
		}
		//alert(cookieObj+"=========")
		return cookieObj;
	}
	this.set = this.setCookie = function(cookieName, cookieValues) {
		var d = this.getSrcObj();
		if (d === null) {
			return;
		};
		if (cookieName.indexOf(kui.cookiesNamePre) < 0) {
			cookieName = kui.cookiesNamePre + cookieName;
		}
		if (d.Exists(cookieName)) {
			d.Remove(cookieName);
		}
		if (cookieValues === null) {
			d.Remove(cookieName);
		} else {
			d.Add(cookieName, cookieValues);
		}

	}
	this.get = this.getCookie = function(cookieName) {
		var d = this.getSrcObj();
		if (d === null) {
			return "";
		};
		if (cookieName.indexOf(kui.cookiesNamePre) < 0) {
			cookieName = kui.cookiesNamePre + cookieName;
		}
		var s = "";
		if (d.Exists(cookieName)) {
			s = d.Item(cookieName);
		} else {
			s = "";
		}
		return s;
	}
	this.getCookieValue = function() {
		var d = this.getSrcObj();
		if (d === null) {
			return "";
		};
		var s = "";
		var a = d.arr;
		var aLen = a.length;
		var at = [];
		for (var i = 0; i < aLen; i++) {
			at.push(a[i][0] + "`=`" + a[i][1]);
		}
		s = at.join("`||`");
		return s;
	}
}

var Cookies = new CreateCookies();

/*
很多框架存在父子关系，操作起来十分麻烦，经常出现这样悲催的代码：
window.parent.document.getElementById("main")
.contentWindow.document.getElementById('input').value =
document.getElementById('myIframe')
.contentWindow.document.getElementById('s0').value;
其实这个问题可以被大大的简化，框架应用中有一个固定不变的窗口叫window.top，如果我们把数据缓存到这个页面，其下所有框架都可以获取到，无论子页面如何变幻。不需要采用Cookie，也不需要采用什么HTML5本地数据库策略，你只需要每个页面引用一个js文件，内容如下：var share= {}
这个寥寥数行的方法可以共享任意类型的数据供各个框架页面读取，它与页面名称、层级毫无关系，就算哪天框架页面层级被修改，你也完全不用担心，它可正常工作。

share.data('myblog', 'http://www.wod.cn');
share.data('editTitle', function (val) {
document.title = val;
});

alert('博客地址是： ' + share.data('myblog');
var editTitle = share.data('editTitle');
editTitle('我已经获取到了数据');

*/
var share = {

	/**
	 * 跨框架数据共享接口
	 * @param	{String}	存储的数据名
	 * @param	{Any}		将要存储的任意数据(无此项则返回被查询的数据)
	 */
	data: function(name, value) {
		var top = window.top,
		cache = top['_CACHE'] || {};
		top['_CACHE'] = cache;

		return value !== undefined ? cache[name] = value: cache[name];
	},

	/**
	 * 数据共享删除接口
	 * @param	{String}	删除的数据名
	 */
	removeData: function(name) {
		var cache = window.top['_CACHE'];
		if (cache && cache[name]) delete cache[name];
	}

};

function closeMenuLoading() {
	try {
		//top.$("#mLoading,#mLoadingMask").hide();
		top.$.dialog.loadingClose();
		/*var loadingMaskObj=top.$.dialog.list["loadingMask"];//alert(top.top$DialogLoading);
		if (top.top$DialogLoading) {
			loadingMaskObj.close();
			top.top$DialogLoading=false;

		}*/
		top.$("#myhome").show();
		try {
			jsEndTime("点击菜单后，进度条完成");
		} catch(e) {}
	} catch(e) {}
}

$(function() {
	createPageTitle();
	closeMenuLoading();
});

function createPageTitle() {
	//列表标题与说明 2013-8-27 上午11:52 lybide
	//============================================================================
	var titleObj = $("#titleElement");
	if (titleObj.length < 0) {
		return;
	}
	/*var note=$("#titleElementNote").html();//Qm.config.titleElement.note ||
	var text=$("#titleElementText").html();//Qm.config.pageTitle Qm.config.titleElement.text ||
	var icon=$("#titleElement").attr("icon");//k/kui/images/menu-icon/32/shopping.png  Qm.config.titleElement.icon ||
	if (!note || !text || !icon) {
		return;
	}*/
	var gtSize = function() {
		if (typeof Qm !== "undefined") {
			var g = Qm.getQmgrid();
			g._onResize();
		}
		$.cSize();
	}
	//alert(PAGE_KEYS);
	//var d1=$('<div class="item-tm" id="lPageMain1" style="cursor:default;"></div>');
	//d1.append(pageTitle({to:null,text:text,icon:icon,note:note,style:"1"})).children().append('<div id="plClose1" title="关闭页面标题" class="l-page-title1-bs" style="display:none;"><div></div></div>');
	var d1 = $('<div id="plClose1" title="关闭页面标题" class="l-page-title-bs" style="display:none;"><div></div></div>');
	titleObj.append(d1);
	titleObj.hover(function() {
		var $this = $(this);
		$("#plClose1").show(); //.animate({"width":"show","height":"show","opacity":"show"}, "400");
	},
	function() {
		var $this = $(this);
		$("#plClose1").hide();
	});

	var gts = $(".l-page-title-note", titleObj);
	if (gts.length > 0) {
		gts.parent().addClass("has-note");
	} else {
		$(".l-page-title", titleObj).addClass("no-note");
	}

	var gts = $(".l-page-title-icon", titleObj);
	if (gts.length > 0) {
		gts.parent().addClass("has-icon");
	} else {
		$(".l-page-title", titleObj).addClass("no-icon");
	}

	var gts = $(".l-page-title-text", titleObj);
	if (gts.length > 0) {
		gts.parent().addClass("has-text");
	} else {
		$(".l-page-title", titleObj).addClass("no-text");
	}

	//$(".l-page-title-note",titleObj).parent().addClass("has-note");
	//$(".l-page-title-icon",titleObj).parent().addClass("has-icon");
	//$(".l-page-title-text",titleObj).parent().addClass("has-text");
	//$("#__qmtoolbar").before(d1);
	//$("body").prepend(d1);
	var d2 = $('<div id="lPageDs1" title="打开页面标题" class="l-page-title-ds" style="display:none;"><div></div></div>').click(function() {
		$("#titleElement,div[isTitle=true]").show();
		$("#lPageDs1").hide();
		gtSize();
	});
	$("body").prepend(d2);
	$("#plClose1").click(function() {
		$("#titleElement,div[isTitle=true]").hide();
		$("#lPageDs1").show();
		gtSize();
		return;
		var $this = $(this);
		var target = $('<div style="padding:10px;"><input type="radio" name="sdf1" checked value="zs">暂时</div>');
		//$.ligerDialog.prompt('是否关闭', function (type) { alert(type); });
		var btnclick = function(item, Dialog, index) {
			Dialog.close();
			if (item.type == 'yes') {
				var sa = target.find(":checked").val(); //alert(sa);
				$("#lPageMain1").hide();
				$("#lPageDs1").show();
				if (typeof Qm !== "undefined") {
					var g = Qm.getQmgrid();
					g._onResize();
				}
				$.cSize();
			}
			if (item.type == 'no') {

}
		}
		var p = {
			title: "是否关闭",
			//target:$('<input id="vali" name="vali" type="radio" ltype="radioGroup" ligerui="{data:[{id:\'1\',text:\'设置为必填\'},{id:\'2\',text:\'设置为选填\'}]}"/>').find("input:radio").ligerRadio(),
			target: target,
			//<br><input type="radio" name="sdf1">一年
			width: 320,
			buttons: [{
				text: $.ligerDefaults.DialogString.ok,
				onclick: btnclick,
				type: 'yes'
			},
			{
				text: $.ligerDefaults.DialogString.cancel,
				onclick: btnclick,
				type: 'cancel'
			}]
		};
		$.ligerDialog(p);
	});
	//============================================================================
}

var L_MAIN_SKIN_NAME = $.kh.request("skin");

function loadDialog() {
	var str1='<link rel="stylesheet" type="text/css" href="' + "k/kui/skins/lhgdialog/" + kFrameConfig["windowStyle"] + ".css" + '" type="text/css" media="all" id="windowCss"/>';
	str1+='<script type="text/javascript" src="k/kui/frame/lhgdialog.min.js"><\/script>';
	str1+='<script type="text/javascript" src="k/kui/frame/lhgdialog.ext.js"><\/script>';
	document.write(str1);
}

//载入核心css样式表与js库
//13-3-29 上午11:49 lybide
function loadCoreLibrary(keys) {
	var str1='';
	if (typeof keys == "string") {
		keys = keys.toLowerCase();
		str1='<link rel="stylesheet" type="text/css" href="k/kui/skins/ligerui-icons.css" />';
		if (keys == "form" || keys == "list") {
			str1+='<link rel="stylesheet" type="text/css" href="' + "k/kui/skins/" + kFrameConfig["frameStyle"] + "/css/ligerui-all.css" + '" id="ligeruiCSS"/>';
			str1+='<link rel="stylesheet" type="text/css" href="' + kFrameConfig["appSkinsPath"] + kFrameConfig["frameStyle"] + "/css/ligerui-all.css" + '" id="kuiCSS"/>';
			if (kFrameConfig["frameStyleSub"]) {
				str1+='<link rel="stylesheet" type="text/css" href="' + "k/kui/skins/" + kFrameConfig["frameStyleSub"] + "/css/all.css" + '" id="ligeruiCSSSub"/>';
			}
			str1+='<script src="k/kui/frame/ligerui.all.js"><\/script>';
			str1+='<script src="k/kui/frame/jquery.autocomplete.js"><\/script>'; //直接调用
			str1+='<link rel="stylesheet" type="text/css" href="k/kui/skins/Aqua/css/jquery.autocomplete.css"/>';
			//loadCss("k/kui/skins/Aqua/css/jquery.autocomplete.css");//ie6下有问题，要终止解析
			str1+= '<!--[if IE 6]><![endif]-->' + '<!--[if IE 8]><link rel="stylesheet" type="text/css" href="k/kui/skins/' + kFrameConfig["frameStyle"] + '/css/ligerui-ie8.css" /><![endif]-->' + '<!--[if IE 9]><link rel="stylesheet" type="text/css" href="k/kui/skins/' + kFrameConfig["frameStyle"] + '/css/ligerui-ie9.css" /><![endif]-->' + '<!--[if IE 10]><![endif]-->';
			document.write(str1);
			if (keys == "form") {
				str1+='<script src="k/kui/frame/jquery.validate.min.js"><\/script>';
				//str1+='<script src="k/kui/frame/jquery.metadata.js"><\/script>';
				str1+='<script src="k/kui/frame/messages_cn.js"><\/script>';
				str1+='<script src="k/kui/frame/form.js"><\/script>';
			}
			var bc = BROWSER_INFO;
			var ie = bc.ie,
			xt = bc.system,
			xtms = bc.systemx,
			dm = bc.docMode,
			iever = bc.ieversion;
			if (xt == "winxp" && iever == 8) { //2013-5-30 下午10:11 lybide 解决input透明后，ie8点击后会激活窗口下方框架的一些input框。
				str1+='<link rel="stylesheet" type="text/css" href="k/kui/skins/' + kFrameConfig["frameStyle"] + '/css/ligerui-xpie8.css" />';
			}
			str1+='<script src="k/kui/frame/jquery.ztree.js"><\/script>';
			str1+='<link rel="stylesheet" type="text/css" href="' + "k/kui/skins/zTreeStyle/zTreeStyle.css" + '" id="ztreeCSS"/>';
			if (typeof top.lhgdialog=='undefined') {//2013-11-19 下午1:19 lybide
				loadDialog();
			}
		} else if (keys == "main") {
			var skinName = L_MAIN_SKIN_NAME || kFrameConfig["mainStyle"];
			str1+='<link rel="stylesheet" type="text/css" href="' + kFrameConfig["appSkinsPath"] + (skinName) + "/main-frame.css" + '" id="mainCSS"/>';
			str1+='<link rel="stylesheet" type="text/css" href="k/kui/skins/ligerui-icons.css"/>';
			//2013-11-12 下午5:46 lybide 判断当前皮肤所对应的菜单
			if (skinName == "default") {
				//kFrameConfig.menu.style="1";
				//kFrameConfig.menu.topIconShow=false;
				//kFrameConfig.menu.menuIconShow=false;
			} else if (skinName == "default2") {
				//kFrameConfig.menu.style="outlook";
				//kFrameConfig.menu.topIconShow=true;
				//kFrameConfig.menu.menuIconShow=true;
			}
			loadDialog();
		} else if (keys == "login") {
			var loginCssSrc = kFrameConfig["loginStyle"];
			if ($.kh.request("skin") !== "") {
				loginCssSrc = ("login" + $.kh.request("skin"));
			}
			str1+='<link rel="stylesheet" href="' + kFrameConfig["appSkinsPath"] + kFrameConfig["mainStyle"] + "/" + loginCssSrc + "/k-login.css" + '" type="text/css" media="all" id="loginCss"/>';
			loadDialog();
		} else if (keys == "desktop") {
			str1+='<link rel="stylesheet" type="text/css" href="app/public/skins/' + kFrameConfig["mainStyle"] + '/main-desktop.css"/>';
		} else if (keys == "dialog") {
			loadDialog();
		} else if (keys == "extjs") {
			str1+='<script src="k/kui/frame/extjs/ext-base.js"><\/script>';
			str1+='<script src="k/kui/frame/extjs/ext-all.js"><\/script>';
			str1+='<script src="k/kui/frame/extjs/ext-lang-zh_CN.js"><\/script>';
			str1+='<script src="k/kui/frame/extjs/ext-bug.js"><\/script>';
		} else if (keys == "nav-process") {
			str1+='<script src="k/kui/frame/nav-process.js"><\/script>';
			str1+='<link rel="stylesheet" type="text/css" href="' + "k/kui/skins/" + kFrameConfig["frameStyle"] + "/css/nav-process.css" + '"/>';
		}
		document.write(str1);
	} else if (typeof keys == "object") {
		//loadCoreLibrary({js:"lhgdialog.min.js xxxxxxx.js",css:"111111.css 2222222.css",style:"111111.css 2222222.css"});
		//13-4-2 下午2:47 lybide
		var jsArr = keys["js"];
		if (!$.kh.isNull(jsArr)) {
			jsArr = jsArr.split(",");
			for (c in jsArr) {
				var js = jsArr[c];
				if (!$.kh.isNull(js)) {
					if (!/\//.test(js)) {
						js = "k/kui/frame/" + js;
					}
					str1+='<script type="text/javascript" src="' + js + '"><\/script>';
				}
			};
		}

		var cssArr = keys["css"];
		if (!$.kh.isNull(cssArr)) {
			cssArr = cssArr.split(",");
			for (c in cssArr) {
				var css = cssArr[c];
				if (!$.kh.isNull(css)) {
					if (!/\//.test(css)) {
						css = kFrameConfig["appSkinsPath"] + kFrameConfig["mainStyle"] + "/" + css;
					}
					str1+='<link rel="stylesheet" href="' + css + "" + '" type="text/css" media="all"/>';
				}
			};
		}

		var cssArr = keys["style"];
		if (!$.kh.isNull(cssArr)) {
			cssArr = cssArr.split(",");
			for (c in cssArr) {
				var css = cssArr[c];
				if (!$.kh.isNull(css)) {
					if (!/\//.test(css)) {
						css = "k/kui/skins/" + kFrameConfig["frameStyle"] + "/css/" + css;
					}
					str1+='<link rel="stylesheet" href="' + css + "" + '" type="text/css" media="all"/>';
				}
			};
		}
		document.write(str1);
	}
}
var loadComp = loadCoreLibrary;



(function($) {
	//13-1-30 上午10:55 lybide
	//控制详情页面，窗口改变时，页面相应元素一起改变高度，大小。
	//13-1-31 上午11:17 lybide
	var cSizeLoadCount = 0;
	$.cSize = function(arg) {
		if (PAGE_KEYS == "sysMain") {
			return;
		}
		if (arg && cSizeLoadCount > 0) {
			return;
		}
		cSizeLoadCount++; //alert(cSizeLoadCount);
		//console.log(cSizeLoadCount);
		var body = $("body");
		var bigTab = $(">.navtab", body); //alert(bigTab);
		var nav;
		if (typeof liger !== "undefined") {
			nav = $(".navtab").ligerTab()
		}
		var cs = (parseInt(body.css("paddingTop")) + parseInt(body.css("paddingBottom")) + parseInt(body.css("marginTop")) + parseInt(body.css("marginBottom")));
		var h1 = $(window).height() - cs;
		var titleTm = $(".title-tm:visible", body);
		if (titleTm.length > 0) {
			//h1-=titleTm.outerHeight(true) || 0;
			$.each(titleTm,
			function() {
				h1 -= $(this).outerHeight(true);
			})
		}
		var itemTm = $(".item-tm:visible", body);
		if (itemTm.length > 0) {
			$.each(itemTm,
			function() {
				var $this = $(this);
				h1 -= $this.outerHeight(true);
				//2013-5-25 下午3:31 lybide
				if ($this.children().is("fieldset")) { //有fieldset，fieldset有margin
					h1 -= parseInt($this.children().css("margin-top")) + parseInt($this.children().css("margin-bottom"));
				}
			})
		}

		var bigTabLen = bigTab.length;
		if (bigTabLen > 0) {
			if (nav) {
				nav.setHeight(h1);
			}
			//$(".l-tab-content-item").css({"overflow":"auto"});
			//h1=bigTab.find(".l-tab-content").height() || 0;
			h1 -= bigTab.find(":visible>.toolbar-tm").height() || 0;
			h1 -= bigTab.find(".l-tab-links").height() || 0;
			//$("li[tabid=tabitem1] a").html(h1);
			bigTab.find(":visible>.scroll-tm").height(h1).css({
				"overflow": "auto"
			});
		} else {
			var toolbarTm = $(".toolbar-tm", body);
			if (toolbarTm.length > 0) {
				h1 -= toolbarTm.outerHeight(true) || 0;
			}
			$(".scroll-tm").height(h1);
			//var nav = $(".navtab").ligerTab();
			//$("button[ligeruiid=save]").html(h1);
			if (nav) {
				nav.setHeight(h1);
				//$(".scroll-tm").css({"overflow":"hidden"});
				$(".l-tab-content-item").css({
					"overflow": "auto"
				});

			}
		}
		//解决>ie8滚动条的问题。2013-7-12 下午4:59 lybide
		var bc = BROWSER_INFO;
		if (bc.ie) {
			$("iframe").each(function(i) {
				$(this).parent(".scroll-tm,.l-tab-content-item").css({
					"overflow": "hidden"
				});
			});
		}
	};
})(jQuery);

//jQuery页面所有元素载入后执行事件
$(window).load(function() {
	//$(function(){
	$(window).resize(function() {
		$.cSize();
	});
	$.cSize(true);
});

//取得页面head属性pageKeys，执行每个页面单独响应事件，如：init_login();其中init_为定数，login为变数。
if ($.browser.msie && parseFloat($.browser.version) <= 8) {
	var console = {};
	console.log = function(s) {
		alert("console.log 打印事件\n\n=====================================================\n\n" + s + "\n\n=====================================================\n\n");
	};
}
var PAGE_KEYS = document.getElementsByTagName("head")[0].getAttribute("pageKeys");
if (PAGE_KEYS !== null && PAGE_KEYS !== undefined && PAGE_KEYS !== "") {
	$(function() { //jQuery页面载入事件
		var str1 = "init_" + PAGE_KEYS + "();";
		try {
			eval(str1);
		} catch(e) {
			//console.log("Javascript 出错："+str1+"，不存在没有执行或者此方法运行错误，请检查。");
		}
		//console.log("执行："+str1);
	});
}

//检查整个软件的字体大小 13-4-20 下午3:46 lybide
if (typeof top.mFontSize !== "undefined") {
	if (top.mFontSize) {
		document.write('<style>body,input,button,select,textarea{font-size:' + top.mFontSize + '!important;}</style>');
	}
}

//在页面上输出标题
//13-5-2 下午1:50 lybide
//pageTitle({to:"#title",text:"软件开发管理系统——编辑",note:'<a href="javascript:void(0);">点击操作1</a><a href="javascript:void(0);">点击操作2</a><a href="javascript:void(0);">点击操作3</a><a href="javascript:void(0);">点击操作4</a><a href="javascript:void(0);">点击操作5</a><a href="javascript:void(0);">点击操作6</a>这里是副标题，可带操作说明',icon:"k/kui/images/menu-icon/32/places.png"});
function pageTitle(options) {
	var defaults = {
		text: "页面标题",
		note: "",
		icon: "",
		to: "body",
		style: "" //采用模板，空或1，2，3，4，5，6
	};
	var o = $.extend({},
	defaults, options);
	var class1 = "l-page-title" + o.style;
	var lpt = $('<div class="' + class1 + '"><div class="' + class1 + '-div"></div></div>');
	if (o.text) {
		lpt.append('<div class="' + class1 + '-text"><h1>' + o.text + '</h1></div>');
	} else {
		lpt.addClass("no-text");
	}
	if (o.note) {
		lpt.append('<div class="' + class1 + '-note">' + o.note + '</div>');
		lpt.addClass("has-note");
	} else {
		lpt.addClass("no-note");
	}
	if (o.icon) {
		lpt.append('<div class="' + class1 + '-icon"><img src="' + o.icon + '" border="0"></div>');
		lpt.addClass("has-icon");
	} else {
		lpt.addClass("no-icon");
	}
	if (options.to) {
		$(options.to).append(lpt);
	}
	return lpt;
};

//2013-5-8 下午9:33 lybide
//通用打开窗口的方法
function winOpen(options) {
	//2013-5-30 下午8:35 lybide
	if (options["reload"]) { //2013-6-5 上午9:52 lybide
		options = $.extend({
			content: "url:http://www.khnt.com",
			width: 800,
			height: 500
		},
		options);
		options["content"] = options["content"].replace(/url\:/g, "");
		var url = options["content"].indexOf("http") > 0 ? options["content"] : kui["base"] + options["content"];
		api.reload(window, url).size(options["width"], options["height"])._reset() //.position("50%","50%");
		$("body").append('<div class="l-body-mask-reload"></div>');
		//top.$.dialog.loading();
		return api;
	} else {
		options = $.extend({
			content: "打开了一个新窗口",
			width: 800,
			height: 500,
			parent: api,
			data: {
				"window": window
			}
		},
		options);
		return top.$.dialog(options);
	}
}

//通用关闭窗口的方法
//winClose("windowId");
function winClose(options) {
	return top.$.dialog.list[options].close();
}

var __tree_dlg;
//弹出新窗口选择树形数据
//data为备选的树形数据
function showTreeDialog(dialogTreeData, isCheckbox, isCheckParent, callback) {
	if (__tree_dlg) {
		__tree_dlg.show();
		$("#dialogComboTree").ligerGetTreeManager().clear();
	} else {
		__tree_dlg = $.ligerDialog.open({
			title: '选择',
			width: 350,
			height: 300,
			content: "<div id='dialogComboTree'></div>",
			buttons: [{
				text: '确定',
				onclick: function(item, dialog) {
					var treeManager = $("#dialogComboTree").ligerGetTreeManager();
					var rstData = isCheckbox ? treeManager.getChecked() : treeManager.getSelected();
					if (!isCheckParent && treeManager.hasChildren(rstData.data)) {
						$.ligerDialog.warn("请选择叶节点！");
						return;
					}
					if (rstData) {
						callback(rstData.data);
						dialog.hide();
					} else {
						$.ligerDialog.warn("您没有选择任何数据！");
						dialog.hide();
					}
				}
			},
			{
				text: '取消',
				onclick: function(item, dialog) {
					dialog.hide();
				}
			}]
		});
	}
	$("#dialogComboTree").ligerTree({
		data: dialogTreeData,
		checkbox: isCheckbox,
		idFieldName: 'id',
		parentIDFieldName: 'pid'
	});
}

function getBrowserInfo() {
	//浏览器判断 2013-5-15 下午10:07 lybide
	var ie = $.browser.msie;
	var iever = $.browser.version;
	var dm = document.documentMode; //文档模式
	var agent = navigator.userAgent.toLowerCase(); //window.prompt("lybide提示",agent);
	var xtms = agent.indexOf("win64") >= 0 || agent.indexOf("wow64") >= 0 ? "x64": "x86"; //操作系统类型
	if (agent.indexOf("chrome") > 0) {
		//mozilla/5.0 (windows nt 6.1; wow64) applewebkit/535.20 (khtml, like gecko) chrome/19.0.1036.7 safari/535.20
		var osVersion = agent.split(";")[0];
	} else if (agent.indexOf("firefox") > 0) {
		//mozilla/5.0 (windows nt 6.1; wow64; rv:20.0) gecko/20100101 firefox/20.0
		var osVersion = agent.split(";")[0];
	} else {
		//mozilla/5.0 (compatible; msie 10.0; windows nt 6.1; wow64; trident/6.0; slcc2; .net clr 2.0.50727; .net clr 3.5.30729; .net clr 3.0.30729; media center pc 6.0; infopath.3)
		var osVersion = agent.split(";")[2];
	}
	var osV = osVersion.substr(osVersion.length - 3, 3); //alert(osV);
	var xt; //系统名称
	if (osV == "5.0") {
		xt = "win2000";
	} else if (osV == "5.1") {
		xt = "winxp";
	} else if (osV == "5.2") {
		xt = "Win2003";
	} else if (osV == "6.2") {
		xt = "win8";
	} else if (osV == "6.1") {
		xt = "win7";
	} else {
		xt = "other";
	};
	return {
		ie: ie,
		system: xt,
		systemx: xtms,
		docMode: dm,
		ieversion: iever
	};
}

var BROWSER_INFO = getBrowserInfo();

function ieChk() {
	var bc = BROWSER_INFO;
	var ie = bc.ie,
	xt = bc.system,
	xtms = bc.systemx,
	dm = bc.docMode,
	iever = bc.ieversion;
	if (ie) {
		if (ie && parseFloat(iever) <= 7) { //浏览器检测 2012年06月19日 星期二 14:04:54 lybide
			var str1 = '提示：您的浏览器版本太低（Internet Explorer ' + iever + '），请立即升级您的浏览器。推荐使用：<a href="http://www.microsoft.com/ie" target="_blank" style="color:#ffff00">（最新IE浏览器）</a>。';
			if ((xt == "win7" || xt == "win8") && iever <= 7) {
				str1 = '提示：请按键 F12，选择“浏览器模式”，必须设置至 Internet Explorer 8 或以上。'
			}
			$("body").append('<div id="bosCk1" style="border:1px solid #000000;background:#ff0000;padding:15px;position:absolute;top:10px;right:10px;z-index:55;color:#ffffff;">' + str1 + '<a href="javascript:void(0);" onclick="$(\'#bosCk1\').hide();" style="color:#ffffff">[关闭此提示]</a></div>');
			try {
				document.execCommand("BackgroundImageCache", false, true);
			} catch(e) {} //ie6不缓存背景图片
		}; //alert([iever,dm,agent])
		if (ie && parseInt(iever) > 6 && (parseInt(iever) != parseInt(dm))) {
			//alert("亲爱的，请不要使用ie怪义模式嘛");
			//$("body").append('<div id="bosCk2" class="l-ie-ms1">尊敬的用户，您当前的浏览器模式不正常，请按键盘上的 F12 键，简单设置一下即可。请选择：“浏览器模式”与“文档模式”，两种模式必须一致，建议设置至最高模式。<a href="javascript:void(0);" onclick="$(\'#bosCk2\').hide();" style="color:#ffffff">[关闭此提示]</a></div>');
			winOpen({
				id: "sss",
				content: '<img src="k/kui/images/iebug1.gif" border="0"/>',
				width: 750,
				height: 500,
				lock: true,
				title: "友情提示",
				max: false,
				min: false
			});
		}
	}
};

function jsEndTime(str) {
	d1 = new Date();
	endTime = d1.getTime();
	var endStr = ">> " + str + "，耗时： " + (endTime - startTime) / 1000 + " 秒";
	//console.log(endStr);
	//alert(str+"，耗时： "+(endTime-startTime)/1000+" 秒");
}

/*
title效果，开始
调用方法：在页面上全局引用：var ImportAltJS=true;
元素加属性：ext_alt="{我们要提示}中华人民共和国<br>四川省"
==============================================
*/
document.write('<div id="pltsTipLayer" style="display:none;position:absolute;z-index:10001;"></div>');
var pltsTitle = "";

function pltsinits() {
	$(document).mouseover(function(e) {
		plts(e);
	});
	$(document).mousemove(function(e) {
		moveToMouseLoc(e);
	});
	//document.onmouseover = plts;
	//document.onmousemove = moveToMouseLoc;
}
function plts(e) {
	//兼容不同浏览器的事件和对象获取
	var ev = e || window.event; //获取event对象
	var o = ev.target || ev.srcElement; //获取事件源
	//var Event = window.event || ev;
	//var o = Event.srcElement || Event.target;
	//var o = event.srcElement;
	//if (o.alt != null && o.alt != "") {
	//	o.dypop = o.alt;
	//	o.alt = ""
	//};
	//if (o.title != null && o.title != "") {
	//	o.dypop = o.title;
	//	o.title = ""
	//};
	if (!o) {
		return;
	}

	o = $(o);
	if (o.attr("ext_alt") != null && o.attr("ext_alt") != "") {
		o.attr("dypop", o.attr("ext_alt"));
		o.attr("ext_alt", "")
	};
	pltsPop = o.attr("dypop"); //console.log(pltsPop);
	if (pltsPop != null && ipltsPop != "" && typeof(pltsPop) != "undefned") {
		pltsTipLayer.style.left = -1000;
		pltsTipLayer.style.display = '';
		var Msg = pltsPop.replace(/\n/g, "<br>");
		//Msg = Msg.replace(/\0x13/g, "<br>");
		var re = /\{(.[^\{]*)\}/ig;
		if (!re.test(Msg)) pltsTitle = "提示";
		else {
			re = /\{(.[^\{]*)\}(.*)/ig;
			pltsTitle = Msg.replace(re, "$1") + " ";
			re = /\{(.[^\{]*)\}/ig;
			Msg = Msg.replace(re, "");
			Msg = Msg.replace("<br>", "");
		}
		var content = '<div class="pltsTipLayer-div"><table class="toolTipTalbe" id="toolTipTalbe" cellspacing="0" cellpadding="0" border="0"><tr><td width="100%" class="toolTipTalbe-td"><table class="toolTipTalbe-table" cellspacing="0" cellpadding="0" border="0">' + '<tr id="pltsPoptop"><td class="toolTipTalbe-table-top"><font color="#ffffff"><b><p id="topleft" align="left">↖' + pltsTitle + '</p><p id="topright" align="right" style="display:none">' + pltsTitle + '↗</font></b></font></td></tr>' + '<tr><td "+attr+" style="padding-left:10px;padding-right:10px;padding-top: 8px;padding-bottom:6px;line-height:140%">' + Msg + '</td></tr>' + '<tr id="pltsPopbot" style="display:none"><td class="toolTipTalbe-table-bot"><font color="#ffffff"><b><p id="botleft" align="left">↙' + pltsTitle + '</p><p id="botright" align="right" style="display:none">' + pltsTitle + '↘</font></b></font></td></tr>' + '</table></td></tr></table></div>';
		pltsTipLayer.innerHTML = content;
		//设置提示框宽度，它的大小是提示框自身大小和浏览器可见窗口大小一半两者中的最小值，即在浏览器窗口小过提示框本身宽度后，会自动调整提示框大小到一个新的宽度
		toolTipTalbe.style.width = Math.min(pltsTipLayer.clientWidth, document.body.clientWidth / 2.2);
		moveToMouseLoc(e);
		return true;
	} else {
		pltsTipLayer.innerHTML = '';
		pltsTipLayer.style.display = 'none';
		return true;
	}
}
function moveToMouseLoc(e) {
	var pltsoffsetX = 12; // 弹出窗口位于鼠标左侧或者右侧的距离；3-12 合适
	var pltsoffsetY = 12; // 弹出窗口位于鼠标下方的距离；3-12 合适
	if (pltsTipLayer.innerHTML == '') return true;
	//var MouseX = event.x;
	//var MouseY = event.y;
	//var Event = window.event || ev;
	var Event = e;
	//获取光标当前X、Y坐标
	var MouseX = Event.clientX;
	var MouseY = Event.clientY;
	//parent.smbt_l.innerText=MouseX+"="+MouseY
	var popHeight = pltsTipLayer.clientHeight;
	var popWidth = pltsTipLayer.clientWidth;
	if (MouseY + pltsoffsetY + popHeight > document.body.clientHeight) {
		popTopAdjust = -popHeight - pltsoffsetY * 1.5;
		pltsPoptop.style.display = "none";
		pltsPopbot.style.display = "";
	} else {
		popTopAdjust = 0;
		pltsPoptop.style.display = "";
		pltsPopbot.style.display = "none";
	}
	if (MouseX + pltsoffsetX + popWidth > document.body.clientWidth) {
		popLeftAdjust = -popWidth - pltsoffsetX * 2;
		topleft.style.display = "none";
		botleft.style.display = "none";
		topright.style.display = "";
		botright.style.display = "";
	} else {
		popLeftAdjust = 0;
		topleft.style.display = "";
		botleft.style.display = "";
		topright.style.display = "none";
		botright.style.display = "none";
	}
	pltsTipLayer.style.left = MouseX + pltsoffsetX + document.body.scrollLeft + popLeftAdjust;
	pltsTipLayer.style.top = MouseY + pltsoffsetY + document.body.scrollTop + popTopAdjust;
	return true;
}
//格式化时间
function FormatDate(s)
{
	var d;
	s=s.replace(/\.0$/,"");
	s=s.replace(/\-/ig,"/");
	s=s.replace(/\:/ig,"/");
	s=s.replace(/\s/ig,"/");
	s=s.split("/");
	if(s[0]==undefined)	s[0]="0000"
	if(s[1]==undefined)	s[1]="00"
	if(s[2]==undefined)	s[2]="00"
	if(s[3]==undefined)	s[3]="00"
	if(s[4]==undefined)	s[4]="00"
	if(s[5]==undefined)	s[5]="00"
	d=new Date(s[0],s[1]-1,s[2],s[3],s[4],s[5]);
	return d;//返回时间
}
//try{ImportAltJS==false;}catch (e){ImportAltJS=true;}
//if (ImportAltJS) {pltsinits();}
/*
title效果，结束
==============================================
*/
