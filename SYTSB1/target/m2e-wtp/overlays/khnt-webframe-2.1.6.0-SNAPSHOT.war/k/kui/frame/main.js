
//=======================================================================================
//document.write('<script type="text/javascript" src="'+kui["kuiBase"]+'kui/frame/core.js"><\/script>');
//=======================================================================================
//=======================================================================================
//document.write('<script type="text/javascript" src="'+kui["kuiBase"]+'kui/frame/config.js"><\/script>');
//=======================================================================================

var KUI_VERSION="2.1.5.2";

//配置项递归
//==========================================================================================================
//var kFrameConfig=window['__systemParams'];

if ( window.JSON && window.JSON.parse ) {
	var _JSON_PARSE=JSON.parse;
	JSON.parse=function(a){
		try {
			return _JSON_PARSE(a + "");
		}catch(e){
			return eval("("+a+")");
		}
	}
}

if (typeof KUI_TREND_CONFIG!="undefined") {
	kFrameConfig=$.extend(true,{},kFrameConfig,KUI_TREND_CONFIG);
}

//重写 console 以适应<ie8 2017年06月13日 17:35:38 星期二 lybide
if (typeof console=="undefined") {
	var console=function(str){alert(str);}
	console.log=function(str){
		//alert("console.log 打印事件\n\n===============================\n\n" + str + "\n\n===============================\n\n");
	}
}


var BROWSER_INFO = getBrowserInfo();

//var kui = kFrameConfig;

//改变全局变量用户信息 2016年10月26日 13:36:43 星期三 lybide
function kuiConfigSetAttribute(userConfig,user,userMenu) {
	//用户配置项覆盖全局配置项 2014-3-19 下午5:07 lybide
	//此用户配置文件，在 system-config.jsp 中根据页面 head pageKeys="sysMain" 进行读取 pub/sysPersonalize/getPersonalize.do
	if (typeof LOGIN_SUCCESS_USER_INFO!="undefined") {
		//kFrameConfig=$.extend(true,kFrameConfig,LOGIN_SUCCESS_USER_INFO["user"]["config"]);
		//kFrameConfig["user"]=$.extend(kFrameConfig["user"],LOGIN_SUCCESS_USER_INFO["user"]);
		//kFrameConfig["menu"]=$.extend(true,kFrameConfig["menu"],LOGIN_SUCCESS_USER_INFO["user"]["menu"]);
	} else {
		/*$.getScript("pub/sysPersonalize/getPersonalize.do",function(){
		 kFrameConfig=$.extend(true,kFrameConfig,LOGIN_SUCCESS_USER_INFO["user"]["config"]);
		 kFrameConfig["user"]=$.extend(kFrameConfig["user"],LOGIN_SUCCESS_USER_INFO["user"]);
		 kFrameConfig["menu"]=$.extend(true,kFrameConfig["menu"],LOGIN_SUCCESS_USER_INFO["user"]["menu"]);
		 kui=kFrameConfig;
		 })*/
		//document.write(unescape('%3Cscript type="text/javascript" src="pub/sysPersonalize/getPersonalize.do"%3E%3C/script%3E'));
	}
	//2015/1/27 15:26 lybide 用户配置文件，只在main.jsp或单独窗口中的页面中执行一次
	//2016年10月26日 13:37:17 星期三 lybide 改进
	try{
		userConfig=userConfig||top.LOGIN_SUCCESS_USER_INFO["user"]["config"];
		user=user||top.LOGIN_SUCCESS_USER_INFO["user"];
		userMenu=userMenu||top.LOGIN_SUCCESS_USER_INFO["user"]["menu"];

		kFrameConfig=$.extend(true,top.kFrameConfig,userConfig);
		kFrameConfig["user"]=$.extend(top.kFrameConfig["user"],user);
		kFrameConfig["menu"]=$.extend(true,top.kFrameConfig["menu"],userMenu);
	}catch (e){}
	kui=typeof kFrameConfig == "undefined"?{}:kFrameConfig;
};
kuiConfigSetAttribute();
//==========================================================================================================


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

//PNG透明
document.write('<!--[if IE 6]>'+unescape('%3Cscript type="text/javascript" src="'+kui["kuiBase"]+'kui/frame/pngfix/DD_belatedPNG.js"%3E%3C/script%3E')+'<![endif]-->');
//JSON
document.write('<!--[if lte IE 7]>'+unescape('%3Cscript type="text/javascript" src="'+kui["kuiBase"]+'kui/frame/json2.js"%3E%3C/script%3E')+'<![endif]-->');

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
} else {
	//api=[];
	//api["data"]=[];
}

//如果是从外壳打开的页面，api需要重新定义，这样是不行的，外壳程序就找不到api了。不能打开哟。
//if(typeof parent.IS_SHELL_PAGE_WINDOW!=="undefined") {api=parent.window;}
//if ($.kh.request("isShellWindow")=="1") {
	//2014/12/1 11:30 lybide
	//api=parent.window;
//}
var m_isShellPageWindow=parent.document.getElementById("isShellPageWindow");
if (m_isShellPageWindow) {
	if (m_isShellPageWindow.getAttribute("src")==location.href) {
		//alert([m_isShellPageWindow.getAttribute("src"),location.href])
	}
}


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
 * 年(y)、月(m)、日(d)、小时(h)、分(n)、秒(s)、周(w)、季度(q)
 * eg:
 * new Date("2013/1/15 10:09:54").add("y",1) ==> 2014/2/15 10:09:54
 */
Date.prototype.add = function(strInterval, Number) {
	//var dtTmp=new Date(dtDate);
	var dtTmp = this;
	if (isNaN(dtTmp)) {
		dtTmp = new Date();
	}
	switch (strInterval) {
		case 's' :
			return new Date(Date.parse(dtTmp) + (1000 * Number));
		case 'n' :
			return new Date(Date.parse(dtTmp) + (60000 * Number));
		case 'h' :
			return new Date(Date.parse(dtTmp) + (3600000 * Number));
		case 'd' :
			return new Date(Date.parse(dtTmp) + (86400000 * Number));
		case 'w' :
			return new Date(Date.parse(dtTmp) + ((86400000 * 7) * Number));
		case 'q' :
			return new Date(dtTmp.getFullYear(), (dtTmp.getMonth()) + Number * 3, dtTmp.getDate(), dtTmp.getHours(), dtTmp.getMinutes(), dtTmp.getSeconds());
		case 'm' :
			return new Date(dtTmp.getFullYear(), (dtTmp.getMonth()) + Number, dtTmp.getDate(), dtTmp.getHours(), dtTmp.getMinutes(), dtTmp.getSeconds());
		case 'y' :
			return new Date((dtTmp.getFullYear() + Number), dtTmp.getMonth(), dtTmp.getDate(), dtTmp.getHours(), dtTmp.getMinutes(), dtTmp.getSeconds());
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
	fmt=fmt || "yyyy-MM-dd hh:mm:ss";
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
//alert(new Date("2017/07/15 10:09:54").add("d",-6).format());

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

//取得地址书签 2016年08月3日 14:32:35 星期三 lybide
function getHashFragment( url ) {
	url = url || location.href;
	return '#' + url.replace( /^[^#]*#?(.*)$/, '$1' );
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
//2009年09月09日 星期三 16:32:37 lybide
$.cookie = function(name, value, options) {
	if (typeof value != 'undefined') { // name and value given, set cookie
		$.cookie.set(name, value, options);
	} else { // only name given, get cookie
		return $.cookie.get(name);
	}
};

$.cookie.set=function(name, value, options){
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
	var path = options.path ? '; path=' + options.path : '; path=/';//不指定path，cookies为整个项目公用
	var domain = options.domain ? '; domain=' + options.domain : '';
	var secure = options.secure ? '; secure' : '';
	document.cookie = [name, '=', encodeURIComponent(value), expires, path, domain, secure].join('');
}

$.cookie.get=function(name){
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
	this.getAll = this.getCookieValue = function() {
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

var L_MAIN_SKIN_NAME = top.$.kh.request("skin");
var L_MENU_SKIN_NAME = top.$.kh.request("menu");

function loadDialog() {
	if (typeof top.lhgdialog=='undefined') {
		var skinName=L_MAIN_SKIN_NAME || top.kui["frameStyle"];
		var windowSkinCustom=top.$.kh.request("wskin") || top.kui["windowSkinCustom"];//自定义窗口样式 2017年02月27日 16:38:06 星期一 lybide
		//console.log(skinName,"---"+top.$.kh.request("wskin") , "---"+top.kui["windowSkinCustom"] , "---"+top.kui["windowStyle"]);
		var str1='';
		if (windowSkinCustom) {
			windowSkinCustom= $.trim(windowSkinCustom);
			str1='<link rel="stylesheet" type="text/css" href="'+kui["kuiBase"]+'kui/skins/common/lhgdialog/'+windowSkinCustom+'.css'+'" type="text/css" media="all" id="windowCss"/>';
		} else {
			str1='<link rel="stylesheet" type="text/css" href="'+kui["kuiBase"]+'kui/skins/'+skinName+'/css/lhg-dialog.css'+'" type="text/css" media="all" id="windowCss"/>';
		}
		str1+='<link rel="stylesheet" type="text/css" href="'+kui["kuiBase"]+'kui/skins/common/lhgdialog/khext.css'+'" type="text/css" media="all" id="windowCssKHExt"/>';
		str1+=unescape('%3Cscript type="text/javascript" src="'+kui["kuiBase"]+'kui/frame/lhgdialog.min.js"%3E%3C/script%3E');
		str1+=unescape('%3Cscript type="text/javascript" src="'+kui["kuiBase"]+'kui/frame/lhgdialog.ext.js"%3E%3C/script%3E');
		document.write(str1);
		top.kui["windowStyle"]=skinName;
	}
}

//载入核心css样式表与js库
//13-3-29 上午11:49 lybide
var PAGE_TYPE=null;
var PAGE_MAIN_BE_CSSNAME=null;
var PAGE_WORK_BE_CSSNAME=null;
function loadCoreLibrary(keys) {
	var str1='';
	var skinName=L_MAIN_SKIN_NAME || kui["frameStyle"];
	if (kui["frameProjectCssUrl"]) {//存在自定义皮肤时，总框架采用默认皮肤 2017年07月17日 17:30:40 星期一 lybide
		skinName="default";
	}
	//全局图标css类
	str1+='<link rel="stylesheet" type="text/css" href="'+kui["kuiBase"]+'kui/skins/ligerui-icons.css" id="allIconsCSS"/>';
	str1+='<link rel="stylesheet" type="text/css" href="'+kui["kuiBase"]+'kui/images/icons/fonticon/fontsbase/iconfont.css" id="fontIconBaseCSS"/>';
	//基本css类，包含框架，列表、详情等通用的css类 2014年06月17日 18:18:38 星期二 lybide
	str1+='<link rel="stylesheet" type="text/css" href="'+kui["kuiBase"]+'kui/skins/common/common.css" id="commonCSS"/>';
	//animate如每页面调用，ie11中按F12调试模式会卡死浏览器。还是只做到 main 主框架引用。2017年09月11日 14:43:00 星期一 lybide
	//str1+='<link rel="stylesheet" type="text/css" href="'+kui["kuiBase"]+'kui/skins/common/animate.css" id="animateCSS"/>';
	str1+='<link rel="stylesheet" type="text/css" href="'+kui["kuiBase"]+'kui/skins/'+(skinName)+'/css/basic.css" id="basicCSS"/>';
	str1+='<link rel="stylesheet" type="text/css" href="'+kui["kuiBase"]+'kui/skins/'+(skinName)+'/css/basic-color.css" id="basicColorCSS"/>';
	$("html").addClass("k-skin-"+skinName);//给每个页面添加总皮肤名 2017年03月03日 14:57:47 星期五 lybide
	//var kSysRoot=kui["sysRoot"];
	var ligerVer="ligerui.all.js"
	var ligerVer="ligerui/all/ligerui.all.js";
	//var ligerVer="ligerui1.1.9/all/ligerui.all.js";kui["frameProjectCssUrl"]="";kui["frameProjectJsUrl"]="";
	//var ligerVer="ligerui1.3.3/all/ligerui.all.js";
	if (typeof keys == "string") {
		//PAGE_TYPE = keys.toLowerCase();
		PAGE_TYPE = keys;
		if (keys == "form" || keys == "list") {
			//var skinName=L_MAIN_SKIN_NAME || kui["frameStyle"];
			str1+='<link rel="stylesheet" type="text/css" href="'+kui["kuiBase"]+'kui/skins/'+skinName+'/css/ligerui-all.css" id="ligeruiCSS"/>';
			var skinNameSub=kui["frameStyleSub"];
			if (skinNameSub) {
				str1+='<link rel="stylesheet" type="text/css" href="'+kui["sysRoot"]+'/kui/public/skins/'+skinNameSub+'/kui-frame.css" id="ligeruiCSSSub"/>';
				kui["frameStyleSub"]=skinNameSub;
			}
			str1+=unescape('%3Cscript type="text/javascript" src="'+kui["kuiBase"]+'kui/frame/lib.js"%3E%3C/script%3E');
			str1+=unescape('%3Cscript type="text/javascript" src="'+kui["kuiBase"]+'kui/frame/'+ligerVer+'"%3E%3C/script%3E');//ligerui'+ligerVer+'/all/ligerui.all.js
			str1+=unescape('%3Cscript type="text/javascript" src="'+kui["kuiBase"]+'kui/frame/jquery.autocomplete.js"%3E%3C/script%3E');
			str1+=unescape('%3Cscript type="text/javascript" src="'+kui["kuiBase"]+'kui/frame/jquery.form.js"%3E%3C/script%3E');
			//loadCss("'+kui["kuiBase"]+'kui/skins/Aqua/css/jquery.autocomplete.css");//ie6下有问题，要终止解析
			str1+='<!--[if lt IE 8]><link rel="stylesheet" type="text/css" href="'+kui["kuiBase"]+'kui/skins/'+skinName+'/css/ligerui-ie6-7.css" /><![endif]-->';
			str1+='<!--[if IE 8]><link rel="stylesheet" type="text/css" href="'+kui["kuiBase"]+'kui/skins/'+skinName+'/css/ligerui-ie8.css" /><![endif]-->';
			str1+='<!--[if IE 9]><link rel="stylesheet" type="text/css" href="'+kui["kuiBase"]+'kui/skins/'+skinName+ '/css/ligerui-ie9.css" /><![endif]-->';
			str1+='<!--[if IE 10]><![endif]-->';

			//document.write(str1);
			kui["frameStyle"]=skinName;
			if (keys == "form") {
				str1+=unescape('%3Cscript type="text/javascript" src="'+kui["kuiBase"]+'kui/frame/jquery.validate.min.js"%3E%3C/script%3E');
				str1+=unescape('%3Cscript type="text/javascript" src="'+kui["kuiBase"]+'kui/frame/messages_cn.js"%3E%3C/script%3E');
				str1+=unescape('%3Cscript type="text/javascript" src="'+kui["kuiBase"]+'kui/frame/form.js"%3E%3C/script%3E');
			}
			//自定义载入编辑区域相应资源 2016年2月24日 16:27:15 lybide
			var workAreaIncludeCSS=kui["workAreaIncludeCSS"];
			var workAreaIncludeJS=kui["workAreaIncludeJS"];
			if (workAreaIncludeCSS) {
				workAreaIncludeCSS=workAreaIncludeCSS.split(",");
				for (var item in workAreaIncludeCSS) {
					var str2='<link rel="stylesheet" type="text/css" href="'+workAreaIncludeCSS[item]+'"/>';
					document.write(str2);
				};
			}
			if (workAreaIncludeJS) {
				workAreaIncludeJS=workAreaIncludeJS.split(",");
				for (var item in workAreaIncludeJS) {
					var str2=unescape('%3Cscript type="text/javascript" src="'+workAreaIncludeJS[item]+'"%3E%3C/script%3E');
					document.write(str2);
				};
			}
			var bc = BROWSER_INFO;
			var ie = bc.ie,
			xt = bc.system,
			xtms = bc.systemx,
			dm = bc.docMode,
			iever = bc.ieversion;
			if (xt == "winxp" && iever == 8) { //2013-5-30 下午10:11 lybide 解决input透明后，ie8点击后会激活窗口下方框架的一些input框。
				str1+='<link rel="stylesheet" type="text/css" href="'+kui["kuiBase"]+'kui/skins/'+skinName+'/css/ligerui-xpie8.css" />';
			}
			str1+=unescape('%3Cscript type="text/javascript" src="'+kui["kuiBase"]+'kui/frame/jquery.ztree.js"%3E%3C/script%3E');
			str1+='<link rel="stylesheet" type="text/css" href="'+kui["kuiBase"]+'kui/skins/zTreeStyle/zTreeStyle.css" id="ztreeCSS"/>';
			if (typeof top.lhgdialog=='undefined') {//2013-11-19 下午1:19 lybide
				loadDialog();
			}
			var sysMainBgAllTrans=kui["sysMainBgAllTrans"];
			//if (kui["sysMainBg"]) {
			//	kui["sysMainBgAllTrans"]=true;
			//} else {
			//	kui["sysMainBgAllTrans"]=false;
			//}
			if (sysMainBgAllTrans && typeof api=="undefined") {
				var str2='';
				str2+='html {background:none!important;}';
				str2+='';
				str2+='';
				document.write(unescape('%3Cstyle type="text/css"%3E'+str2+'%3C/style%3E'));
				//列表页面相应元素透明 css
				document.write('<link rel="stylesheet" type="text/css" href="'+kui["kuiBase"]+'kui/skins/'+(skinName)+'/css/ligerui-trans.css" id="listTransCSS"/>');
				var sysMainBg=kui["sysMainBg"];
				var mainBg=kui["skinObject"]["sysMainBg"];
				var mainBgLen=mainBg.length;
				for (var i = 0; i < mainBgLen; i++) {
					var items=mainBg[i];
					if (sysMainBg==items["imgUrl"]) {
						PAGE_WORK_BE_CSSNAME=items["skinName"];
					}
				}
				if (PAGE_WORK_BE_CSSNAME) {
					$(function(){
						$("body").addClass(PAGE_WORK_BE_CSSNAME);
					});
				}
			}
		} else if (keys == "withoutForm") {
			str1+='<link rel="stylesheet" type="text/css" href="'+kui["kuiBase"]+'kui/skins/'+skinName+'/css/ligerui-all.css" id="ligeruiCSS"/>';
			str1+=unescape('%3Cscript type="text/javascript" src="'+kui["kuiBase"]+'kui/frame/'+ligerVer+'"%3E%3C/script%3E');
			str1+=unescape('%3Cscript type="text/javascript" src="'+kui["kuiBase"]+'kui/frame/jquery.autocomplete.js"%3E%3C/script%3E');
			str1+=unescape('%3Cscript type="text/javascript" src="'+kui["kuiBase"]+'kui/frame/jquery.validate.min.js"%3E%3C/script%3E');
			str1+=unescape('%3Cscript type="text/javascript" src="'+kui["kuiBase"]+'kui/frame/messages_cn.js"%3E%3C/script%3E');
			str1+=unescape('%3Cscript type="text/javascript" src="'+kui["kuiBase"]+'kui/frame/jquery.ztree.js"%3E%3C/script%3E');
			str1+='<link rel="stylesheet" type="text/css" href="'+kui["kuiBase"]+'kui/skins/zTreeStyle/zTreeStyle.css" id="ztreeCSS"/>';
			if (typeof top.lhgdialog=='undefined') {//2013-11-19 下午1:19 lybide
				loadDialog();
			}
		} else if (keys == "main") {
			str1+='<link rel="stylesheet" type="text/css" href="'+kui["kuiBase"]+'kui/skins/common/animate.css" id="animateCSS"/>';
			if (getkIsShell() && (PAGE_KEYS=="sysMain" || PAGE_KEYS=="login")) {
				str1+=unescape('%3Cscript type="text/javascript" src="'+kui["kuiBase"]+'kui/frame/explorer.js"%3E%3C/script%3E');
			}
			//var skinName = L_MAIN_SKIN_NAME || kui["mainStyle"];
			str1+='<link rel="stylesheet" type="text/css" href="'+kui["kuiBase"]+'kui/public/sys-set/basic.css" id="sysSetBasicCSS"/>';
			str1+='<link rel="stylesheet" type="text/css" href="'+kui["kuiBase"]+'kui/skins/'+(skinName)+'/shell/main-frame.css" id="mainCSS"/>';
			kui["mainStyle"]=skinName;
			var skinNameSub=kui["mainStyleSub"];
			if (skinNameSub) {
				str1+='<link rel="stylesheet" type="text/css" href="'+kui["sysRoot"]+"/kui/public/skins/"+skinNameSub+'/main-frame.css" id="mainCSSSub"/>';
				kui["mainStyleSub"]=skinNameSub;
			}
			str1+=unescape('%3Cscript type="text/javascript" src="'+kui["kuiBase"]+'kui/frame/main-frame.js"%3E%3C/script%3E');
			//载入用户个性化配置面版调用方法 2016年10月26日 14:19:03 星期三 lybides 从 k-frame-main.js 移值到此处
			str1+=unescape('%3Cscript type="text/javascript" src="'+kui["kuiBase"]+'kui/public/sys-set/userset.js"%3E%3C/script%3E');
			//2015年6月1日 16:55:17 lybide 从 main-frame.js 移值到此处
			//=======================================================================================
			L_MENU_SKIN_NAME=L_MENU_SKIN_NAME || kFrameConfig["menu"]["style"];
			if (kui["frameProjectCssUrl"]) {
				L_MENU_SKIN_NAME="1";
			}
			//2013-11-5 下午2:09 lybide 框架主入口程序
			var menulibJs="";
			var menuSkinName="";
			if (L_MENU_SKIN_NAME=="1" || L_MENU_SKIN_NAME=="2") {//default
				menulibJs+=unescape('%3Cscript type="text/javascript" src="'+kui["kuiBase"]+'kui/frame/main-frame-1.js"%3E%3C/script%3E');
				var menuLibCss1='<link rel="stylesheet" type="text/css" href="'+kui["kuiBase"]+'kui/skins/'+skinName+"/shell/main-frame-menu-1.css"+'" id="frameMenuCSS"/>';
				menuSkinName="default";
			} else if (L_MENU_SKIN_NAME=="outlook") {//default2
				menulibJs+=unescape('%3Cscript type="text/javascript" src="'+kui["kuiBase"]+'kui/frame/main-frame-outlook.js"%3E%3C/script%3E');
				var menuLibCss1='<link rel="stylesheet" type="text/css" href="'+kui["kuiBase"]+'kui/skins/'+skinName+"/shell/main-frame-menu-outlook.css"+'" id="frameMenuCSS"/>';
				menuSkinName="outlook";
			} else if (L_MENU_SKIN_NAME=="tree") {//tree
				menulibJs+=unescape('%3Cscript type="text/javascript" src="'+kui["kuiBase"]+'kui/frame/jquery.ztree.js"%3E%3C/script%3E');
				menulibJs+=unescape('%3Cscript type="text/javascript" src="'+kui["kuiBase"]+'kui/frame/main-frame-tree.js"%3E%3C/script%3E');
				var menuLibCss1='<link rel="stylesheet" type="text/css" href="'+kui["kuiBase"]+'kui/skins/'+skinName+"/shell/main-frame-menu-tree.css"+'" id="frameMenuCSS"/>';
				menuSkinName="tree";
			}
			$("html").addClass("k-menu-"+menuSkinName);

			kFrameConfig["menu"]["style"]=L_MENU_SKIN_NAME;

			str1+=(menuLibCss1);//2014-2-11 下午3:45 lybide
			str1+=(menulibJs);
			//=======================================================================================
			//str1+=unescape('%3Cscript type="text/javascript" src="'+kui["kuiBase"]+'kui/frame/main-frame.js.slj.js"%3E%3C/script%3E');
			str1+=unescape('%3Cscript type="text/javascript" src="'+kui["sysRoot"]+'k-frame-main.js"%3E%3C/script%3E');
			//2013-11-12 下午5:46 lybide 判断当前皮肤所对应的菜单
			if (skinName == "default") {
				//kui.menu.style="1";
				//kui.menu.topIconShow=false;
				//kui.menu.menuIconShow=false;
			} else if (skinName == "default2") {
				//kui.menu.style="outlook";
				//kui.menu.topIconShow=true;
				//kui.menu.menuIconShow=true;
			}

			//2014-3-19 下午5:38 lybide
			var sysMainBg=kui["sysMainBg"];
			if (sysMainBg) {
				var str2='';
				str2+='#sysMain {background-image:url("'+sysMainBg+'");background-size:100%;background-size:cover;background-position:center center;background-repeat:no-repeat;}';
				str2+='.m-top {background:none!important;}.m-foot {background:none!important;}.m-center {background:none!important;}.m-menu2 {background:none!important;}.m-top-left {display:none;}.m-top-right {display:none;}';
				str2+='';
				document.write(unescape('%3Cstyle type="text/css"%3E'+str2+'%3C/style%3E'));
				var mainBg=kui["skinObject"]["sysMainBg"];
				var mainBgLen=mainBg.length;
				for (var i = 0; i < mainBgLen; i++) {
					var items=mainBg[i];
					if (sysMainBg==items["imgUrl"]) {
						PAGE_MAIN_BE_CSSNAME=items["skinName"];
					}
				}
				if (PAGE_MAIN_BE_CSSNAME) {
					$(function(){
						$("body").addClass(PAGE_MAIN_BE_CSSNAME);
					});
				}
			}
			//消息提醒接口
			str1+=unescape('%3Cscript type="text/javascript" src="pub/explorer/ui-ext/msg_loader.js"%3E%3C/script%3E');
			//通用查询下载消息提醒
			str1+=unescape('%3Cscript type="text/javascript" src="k/qm/ligerui/down/msg_loader.js"%3E%3C/script%3E');
			loadDialog();
			$(function(){
				var menuOpen=kui["MENUOPEN"]!==false ? true : kui["MENUOPEN"];
				if (menuOpen) {
					if(!window["shortcutmenu"]){
						$.getScript("k/kui/public/shortcut-menu/main-shortcut-menu.js", function(){
							window["shortcutmenu"] = true;
							initShotcutMenu()
						});
					}
				} else {
					$("#dvDock").hide();
				}
			});
		} else if (keys == "sysDesktop") {
			str1+='<link rel="stylesheet" type="text/css" href="'+kui["kuiBase"]+'kui/skins/'+(skinName)+'/shell/sys-desktop.css" id="sysDesktopCSS"/>';
			if (kui["sysDeskBg"]) {

			}
			loadDialog();
		} else if (keys == "login") {
			var loginCssSrc = kui["loginStyle"];
			var c_skinStyle=$.cookie("c_skinStyle");
			if ($.kh.request("skins") != "") {
				loginCssSrc = ($.kh.request("skins"));
				c_skinStyle="";
			}
			if (c_skinStyle) {
				loginCssSrc = c_skinStyle;
			}
			str1+='<link rel="stylesheet" type="text/css" href="'+kui["kuiBase"]+"/kui/public/login/"+""+loginCssSrc+"/k-login.css"+'" id="loginCss"/>';
			loadDialog();
			str1+=unescape('%3Cscript type="text/javascript" src="'+kui["kuiBase"]+'kui/frame/login-frame.js?_r=' + Math.random() + '"%3E%3C/script%3E');
			if (typeof K_FRAME_LOGIN_JS=="undefined") {
				str1+=unescape('%3Cscript type="text/javascript" src="'+kui["sysRoot"]+'k-frame-login.js?_r=' + Math.random() + '"%3E%3C/script%3E');
			}
			if (getkIsShell() && (PAGE_KEYS=="sysMain" || PAGE_KEYS=="login")) {
				str1+=unescape('%3Cscript type="text/javascript" src="'+kui["kuiBase"]+'kui/frame/explorer.js"%3E%3C/script%3E');
			}
		} else if (keys == "desktop") {
			str1+='<link rel="stylesheet" type="text/css" href="'+kui["sysRoot"]+"/kui/public/skins/"+kui["mainStyle"]+'/main-desktop.css" id="desktopCSS"/>';
		} else if (keys == "dialog") {
			loadDialog();
		} else if (keys == "extjs") {
			str1+=unescape('%3Cscript type="text/javascript" src="'+kui["kuiBase"]+'kui/frame/extjs/ext-base.js"%3E%3C/script%3E');
			str1+=unescape('%3Cscript type="text/javascript" src="'+kui["kuiBase"]+'kui/frame/extjs/ext-all.js"%3E%3C/script%3E');
			str1+=unescape('%3Cscript type="text/javascript" src="'+kui["kuiBase"]+'kui/frame/extjs/ext-lang-zh_CN.js"%3E%3C/script%3E');
			str1+=unescape('%3Cscript type="text/javascript" src="'+kui["kuiBase"]+'kui/frame/extjs/ext-bug.js"%3E%3C/script%3E');
		} else if (keys == "nav-process") {
			str1+=unescape('%3Cscript type="text/javascript" src="'+kui["kuiBase"]+'kui/frame/nav-process.js"%3E%3C/script%3E');
			str1+='<link rel="stylesheet" type="text/css" href="'+kui["kuiBase"]+'kui/skins/'+kui["frameStyle"]+'/css/nav-process.css" id="navProcessCSS"/>';
		}
		if (kui["frameProjectCssUrl"]) {
			str1+='<link rel="stylesheet" type="text/css" href="'+kui["frameProjectCssUrl"].replace("[!--frameStyle--]",skinName)+'" id="cssframeProjectCssUrl"/>';
		}
		if (kui["frameProjectJsUrl"]) {
			str1+=unescape('%3Cscript type="text/javascript" src="'+kui["frameProjectJsUrl"]+'"%3E%3C/script%3E');
		}
		if (kui["fontIconLIcon"]) {
			str1+='<link rel="stylesheet" type="text/css" href="'+kui["kuiBase"]+'kui/images/icons/fonticon/l-icon/iconfont.css" id="fontIconCSS1"/>';
		}
		document.write(str1);
		return;
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
						js = ''+kui["kuiBase"]+'kui/frame/' + js;
					}
					str1+=unescape('%3Cscript type="text/javascript" src="'+js+'"%3E%3C/script%3E');
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
						css = kui["sysRoot"] +"kui/public/skins/"+ kui["mainStyle"] + "/" + css;
					}
					if (/\/?/.test(css)) {//只出现一次 "shell/aaa.css" 2014年05月04日 13:41:16 星期日 lybide
						css = kui["sysRoot"] +"kui/skins/"+ kui["mainStyle"] + "/" + css;
					}
					//alert(css);
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
						css = ''+kui["kuiBase"]+'kui/skins/'+kui["frameStyle"]+'/css/' + css;
					}
					str1+='<link rel="stylesheet" href="'+css+""+'" type="text/css" media="all"/>';
				}
			};
		}
		document.write(str1);
		return;
	}
	document.write(str1);
}
var loadComp = loadCoreLibrary;

/**
* 页面载入事件
* */

$(function() {
	//检查整个软件的字体大小 13-4-20 下午3:46 lybide
	var font=$.cookie.get("mSysFont");//Cookies.getCookie("mSysFont");//$.cookie.get("mSysFont");
	if (font) {
		//document.write('<style type="text/css">body,input,button,select,textarea {font-size:' + top.mFontSize + '!important;}</style>');
		//document.write(unescape('%3Cstyle type="text/css"%3E'+str2+'%3C/style%3E'));
		//document.body.style.fontSize=font+"px";
		$("html").addClass("k-page-size-"+font+"px");
	}
	//if (PAGE_TYPE=="list" || PAGE_TYPE=="form" || PAGE_TYPE=="sysWelcome") {
	closeMenuLoading(1);//已移到 main-frame.js $("#home").bind("load",function(){}
	//}
	//2017年06月12日 12:39:21 星期一 lybide for lasa
	mainWindowClass();
	var resizeTimer=null;
	$(window).on("resize",function(){
		if (resizeTimer) {
			clearTimeout(resizeTimer)
		}
		resizeTimer = setTimeout(function(){
			mainWindowClass("window");
		},400);
	});
});

//须放在这里，因为都在调用这个方法。
function closeMenuLoading(keys) {
	try {
		//top.$("#mLoading,#mLoadingMask").hide();
		winOpen.loadingClose();
		/*var loadingMaskObj=top.$.dialog.list["loadingMask"];//alert(top.top$DialogLoading);
		if (top.top$DialogLoading) {
			loadingMaskObj.close();
			top.top$DialogLoading=false;

		}*/
		//列表页面相应元素透明 2014年06月13日 09:50:22 星期五 lybide
		//parent.$("#myhome").show();

		if (keys=="main") {
			parent.MAIN_LINE_SERVER_STATUS_COUNT=0;
			parent.mCenterMaskChange("hide",1);
			//parent.MAIN_OPEN_IFRAME.show();//2014/12/3 15:50 lybide
		}

		try {
			jsEndTime("点击菜单后，进度条完成");
		} catch(e) {}
	} catch(e) {}
}

function jsEndTime(str) {
	d1 = new Date();
	endTime = d1.getTime();
	var endStr = ">> " + str + "，耗时： " + (endTime - startTime) / 1000 + " 秒";
	//console.log(endStr);
	//alert(str+"，耗时： "+(endTime-startTime)/1000+" 秒");
}

//取得页面head属性pageKeys，执行每个页面单独响应事件，如：init_login();其中init_为定数，login为变数。
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

//2013-5-8 下午9:33 lybide
//通用打开窗口的方法
//项目中，所有 top.$.dialog 方法，最好都统一为：winOpen 方法。可采用一次性替换。
var winOpen=function(options) {
	if (!options) {
		return;
	}
	//2013-5-30 下午8:35 lybide
	//2016-1-6 17:00 zhp --- start
	if(options.height) options.height = $(top).height()<options.height?$(top).height():options.height;
	if(options.width) options.width = $(top).width()<options.width?$(top).width():options.width;
	//2016-1-6 17:00 zhp --- end
	if (options["skin"]) {
		//top.$("#windowCss").attr("href","")
		options["title"]="";
	}
	if (options["reload"]) { //2013-6-5 上午9:52 lybide
		options = $.extend({
			content: "url:http://www.khnt.com",
			width: 800,
			height: 400
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
			height: 400,
			parent: api,
			data: {
				"window": window
			}
		},
		options);
		return top.$.dialog(options);
	}
}

//简化 top.$.dialog 的所有写法 2017年06月06日 17:08:17 星期二 lybide
winOpen.notice=function(content,time,icon,fun,tops){
	return top.$.dialog.tips(content,time?time:1,icon?icon:'k/kui/images/icons/dialog/32X32/succ.png',fun?fun:null,tops?tops:5);
}

winOpen.loading=function(str){
	return top.$.dialog.loading(str);
}

winOpen.loadingClose=function(){
	return top.$.dialog.loadingClose();
}

winOpen.closeAll=function(){
	return top.$.dialog.closeAll();
}

winOpen.success=function(content, callback, parent){
	return top.$.dialog.success(content, callback, parent);
}

winOpen.alert=function(content, callback, parent){
	return top.$.dialog.alert(content, callback, parent);
}

winOpen.error=function(content, callback, parent){
	return top.$.dialog.error(content, callback, parent);
}

winOpen.confirm=function(content, yes, no, parent){
	return top.$.dialog.confirm(content, yes, no, parent);
}

winOpen.prompt=function(content, yes, value, parent){
	return top.$.dialog.prompt(content, yes, value, parent);
}

winOpen.tips=function(content, time, icon, callback, tops){
	return top.$.dialog.tips(content, time, icon, callback, tops);
}

winOpen.focus=function(){
	return top.$.dialog.focus();
}

winOpen.list=function(options){
	return top.$.dialog.list(options);
}

//通用关闭窗口的方法
//winClose("windowId");
function winClose(options) {
	return top.$.dialog.list[options].close();
}

function mainWindowClass(arg) {
	//var w1=$(window).width();
	//var h1=$(window).height();
	//2017年06月12日 17:10:11 星期一 lybide 采用top.window
	var w1=$(top.window).width();
	var h1=$(top.window).height();

	var body=$("html");
	body.removeClass("window-w-320 window-w-480 window-w-650 window-w-720 window-w-800 window-w-960 window-w-1024 window-w-1280 window-w-1366 window-w-1440 window-w-1680 window-w-1920 window-w-2048 window-w-2560 window-w-3200 window-w-3840 window-w-5120 window-w-6400 window-w-7680");
	body.removeClass("window-w-320-480 window-w-650-1024 window-w-1280-1440 window-w-1680-2048 window-w-2048-3200 window-w-3840-5120 window-w-6400-7680 window-w-big10000");
	var class1="";
	var class2="";
	if (w1 >240 && w1 <= 320) {
		class1="window-w-320";
		class2="window-w-320-480";
	}
	if (w1 >320 && w1 <= 480) {
		class1="window-w-480";
		class2="window-w-320-480";
	}
	if (w1 >480 && w1 <= 650) {
		class1="window-w-650";
		class2="window-w-650-1024";
	}
	if (w1 >650 && w1 <= 720) {
		class1="window-w-720";
		class2="window-w-650-1024";
	}
	if (w1 >720 && w1 <= 800) {
		class1="window-w-800";
		class2="window-w-650-1024";
	}
	if (w1 >800 && w1 <= 960) {
		class1="window-w-960";
		class2="window-w-650-1024";
	}
	if (w1 >960 && w1 <= 1152) {
		class1="window-w-1024";
		class2="window-w-650-1024";
	}
	/*if (w1 >1024 && w1 <= 1152) {
		class1="window-w-1136";
		class2="window-w-650-1024";
	}*/
	if (w1 >1152 && w1 <= 1280) {
		class1="window-w-1280";
		class2="window-w-1280-1440";
	}
	if (w1 > 1280 && w1 <= 1366) {
		class1="window-w-1366";
		class2="window-w-1280-1440";
	}
	if (w1 > 1366 && w1 <= 1440) {
		class1="window-w-1440";
		class2="window-w-1280-1440";
	}
	if (w1 > 1440 && w1 <= 1680) {
		class1="window-w-1680";
		class2="window-w-1680-2048";
	}
	if (w1 > 1680 && w1 <= 1920) {
		class1="window-w-1920";
		class2="window-w-1680-2048";
	}
	if (w1 > 1920 && w1 <= 2048) {
		class1="window-w-2048";
		class2="window-w-2048-3200";
	}
	if (w1 > 2048 && w1 <= 2560) {
		class1="window-w-2560";
		class2="window-w-2048-3200";
	}
	if (w1 > 2560 && w1 <= 3200) {
		class1="window-w-3200";
		class2="window-w-2048-3200";
	}
	if (w1 > 3200 && w1 <= 3840) {
		class1="window-w-3840";
		class2="window-w-3840-5120";
	}
	if (w1 > 3840 && w1 <= 4096) {
		class1="window-w-4096";
		class2="window-w-3840-5120";
	}
	if (w1 > 4096 && w1 <= 5120) {
		class1="window-w-5120";
		class2="window-w-3840-5120";
	}
	if (w1 > 5120 && w1 <= 6400) {
		class1="window-w-6400";
		class2="window-w-6400-7680";
	}
	if (w1 > 6400 && w1 <= 7680) {
		class1="window-w-7680";
		class2="window-w-6400-7680";
	}
	if (w1 > 7680) {
		class1="window-w-big10000";
		class2="window-w-big10000";
	}
	body.addClass(class1);
	body.addClass(class2);
	//2017年08月08日 12:34:53 星期二 lybide 给文档添加常用高度
	body.removeClass("window-h-300 window-h-400 window-h-500 window-h-600 window-h-700 window-h-800 window-h-900 window-h-1000 window-h-1100 window-h-1200 window-h-1300 window-h-1400 window-h-1500 window-h-big");
	body.removeClass("window-h-300-600 window-h-600-900 window-h-1000-1500");
	var class1="";
	var class2="";
	if (h1 <= 300) {
		class1="window-h-300";
		class2="window-h-300-600";
	}
	if (h1 >300 && h1 <= 400) {
		class1="window-h-400";
		class2="window-h-300-600";
	}
	if (h1 >400 && h1 <= 500) {
		class1="window-h-500";
		class2="window-h-300-600";
	}
	if (h1 >500 && h1 <= 600) {
		class1="window-h-600";
		class2="window-h-300-600";
	}
	if (h1 >600 && h1 <= 700) {
		class1="window-h-700";
		class2="window-h-600-900";
	}
	if (h1 >700 && h1 <= 800) {
		class1="window-h-800";
		class2="window-h-600-900";
	}
	if (h1 >800 && h1 <= 900) {
		class1="window-h-900";
		class2="window-h-600-900";
	}
	if (h1 >900 && h1 <= 1000) {
		class1="window-h-1000";
		class2="window-h-1000-1500";
	}
	if (h1 >1000 && h1 <= 1100) {
		class1="window-h-1100";
		class2="window-h-1000-1500";
	}
	if (h1 >1100 && h1 <= 1200) {
		class1="window-h-1200";
		class2="window-h-1000-1500";
	}
	if (h1 >1200 && h1 <= 1300) {
		class1="window-h-1300";
		class2="window-h-1000-1500";
	}
	if (h1 >1300 && h1 <= 1400) {
		class1="window-h-1400";
		class2="window-h-1000-1500";
	}
	if (h1 >1400 && h1 <= 1500) {
		class1="window-h-1500";
		class2="window-h-1000-1500";
	}
	if (h1 >1500) {
		class1="window-h-big1500";
		class2="window-h-big1500";
	}
	body.addClass(class1);
	body.addClass(class2);
	var bc = BROWSER_INFO;
	var ie = bc.ie,
		xt = bc.system,
		xtms = bc.systemx,
		dm = bc.docMode,
		bsType=bc.type,
		iever = bc.ieversion;//alert(ie+"++"+iever+"=="+bsType)
	if (ie) {
		if (ie) {// && parseFloat(iever) <= 8
			var ies="window-is-ie"+parseInt(iever);
			$("html").addClass(ies);//ie兼容性 2017年07月20日 17:17:07 星期四 lybide
		}
	}
}

function getBrowserInfo() {
	if (typeof $.browser == "undefined") {
		$.browser={};
		$.browser.mozilla = /firefox/.test(navigator.userAgent.toLowerCase());
		$.browser.webkit = /webkit/.test(navigator.userAgent.toLowerCase());
		$.browser.opera = /opera/.test(navigator.userAgent.toLowerCase());
		$.browser.msie = /msie/.test(navigator.userAgent.toLowerCase());
	}
	
	//浏览器判断 2013-5-15 下午10:07 lybide
	var ie = $.browser.msie;
	var iever = $.browser.version;
	var dm = document.documentMode; //文档模式
	var agent = navigator.userAgent.toLowerCase(); //window.prompt("lybide提示",agent);
	//console.log(agent);
	var xtms = agent.indexOf("win64") >= 0 || agent.indexOf("wow64") >= 0 ? "x64": "x86"; //操作系统类型
	//ie11 mozilla/5.0 (windows nt 6.1; wow64; trident/7.0; slcc2; .net clr 2.0.50727; .net clr 3.5.30729; .net clr 3.0.30729; media center pc 6.0; .net4.0c; .net4.0e; infopath.3; rv:11.0) like gecko
	//chrome mozilla/5.0 (windows nt 6.1; wow64) applewebkit/535.20 (khtml, like gecko) chrome/19.0.1036.7 safari/535.20
	//firefox mozilla/5.0 (windows nt 6.1; wow64; rv:20.0) gecko/20100101 firefox/20.0
	//ie10 mozilla/5.0 (compatible; msie 10.0; windows nt 6.1; wow64; trident/6.0; slcc2; .net clr 2.0.50727; .net clr 3.5.30729; .net clr 3.0.30729; media center pc 6.0; infopath.3)
	//mozilla/5.0 (windows nt 10.0; wow64) applewebkit/537.36 (khtml, like gecko) chrome/50.0.2661.102 safari/537.36
	//mozilla/5.0 (windows nt 10.0; win64; x64) applewebkit/537.36 (khtml, like gecko) chrome/51.0.2704.79 safari/537.36 edge/14.14393
	/*if (agent.indexOf("chrome") > 0) {
	 var osVersion = agent.split(";")[0];
	 } else if (agent.indexOf("firefox") > 0) {
	 var osVersion = agent.split(";")[0];
	 } else {
	 var osVersion = agent.split(";")[2];
	 }*/
	//浏览器检查改进 2017年06月13日 17:46:44 星期二 lybide
	var s,bsver,bs={};
	(s = agent.match(/(msie\s|trident.*rv:)([\w.]+)/)) ? bs.ie = s[1] :
	(s = agent.match(/edge\/([\d.]+)/)) ? bs.edge = s[1] :
	(s = agent.match(/firefox\/([\d.]+)/)) ? bs.firefox = s[1] :
	(s = agent.match(/chrome\/([\d.]+)/)) ? bs.chrome = s[1] :
	(s = agent.match(/opera.([\d.]+)/)) ? bs.opera = s[1] :
	(s = agent.match(/version\/([\d.]+).*safari/)) ? bs.safari = s[1] : 0;
	//以下进行测试
	var bsType="null";
	if (bs.ie) {bsType="ie";ie=true;bsver=s[1];}
	if (bs.firefox) {bsType="firefox";ie=false;bsver=s[1];}
	if (bs.chrome) {bsType="chrome";ie=false;bsver=s[1];}
	if (bs.opera) {bsType="opera";ie=false;bsver=s[1];}
	if (bs.safari) {bsType="safari";ie=false;bsver=s[1];}
	if (bs.edge) {bsType="edge";ie=false;bsver=s[1];}
	var pt = navigator.platform.toLowerCase();//操作平台 ipad iphone win32 linux armv7i
	var xt,xtv; //系统名称
	if (/windows/.test(agent)) {
		xt="win";
		var osV = agent.match(/windows (.+?)(\)|;)/)[1];
		if (osV == "nt 5.0") {
			xtv = "win2000";
		} else if (osV == "nt 5.1") {
			xtv = "winxp";
		} else if (osV == "nt 5.2") {
			xtv = "Win2003";
		} else if (osV == "nt 6.2") {
			xtv = "win8";
		} else if (osV == "nt 6.1") {
			xtv = "win7";
		} else if (osV == "nt 6.3" || osV == "nt 10.0") {
			xtv = "win10";
		} else {
			xtv = osV;
		};
	} else if (/android/.test(agent)) {//"安桌手机"
		xt="android";
	} else if (/ipad/.test(agent)) {//"苹果ipad"
		xt="ipad";
	} else if (/iphone/.test(agent)) {//"苹果手机"
		xt="iphone";
	} else if (/mac/.test(agent)) {//"苹果操作系统"
		xt="mac";
	}
	var r={
		type:bsType,
		version: bsver,
		ie: ie,
		ieversion: iever,
		system: xt,
		systemVersion: xtv,
		systemx: xtms,
		docMode: dm
	};//alert(JSON.stringify(r));
	//console.log(r);
	return r;
}



function ieChk() {
	var bc = BROWSER_INFO;
	var ie = bc.ie,
	xt = bc.system,
	xtms = bc.systemx,
	dm = bc.docMode,
	bsType=bc.type,
	iever = bc.ieversion;
	if (ie) {
		if (ie && parseFloat(iever) <= 7) { //浏览器检测 2012年06月19日 星期二 14:04:54 lybide
			var str1 = '提示：您的浏览器版本太低（Internet Explorer ' + iever + '），请立即升级您的浏览器。推荐使用：<a href="http://www.microsoft.com/ie" target="_blank" style="color:#ffff00">（最新IE浏览器）</a>。';
			if ((xt == "win7" || xt == "win8") && iever <= 7) {
				str1 = '提示：请按键 F12，选择“浏览器模式”，必须设置至 Internet Explorer 8 或以上。'
			}
			$("body").append('<div id="bosCk1" style="border:1px solid #000000;background:#ff0000;padding:15px;position:absolute;top:10px;right:10px;z-index:55;color:#ffffff;">' + str1 + '<a href="javascript:void(0);" onclick="$(\'#bosCk1\').hide();" style="color:#ffffff">[关闭此提示]</a></div>');
			if (parseFloat(iever)<=6) {
				try {document.execCommand("BackgroundImageCache", false, true);} catch(e) {} //ie6不缓存背景图片
				DD_belatedPNG.fix("*");
			}
		}; //alert([iever,dm])
		if (ie && (parseInt(iever) > 6 && parseInt(iever) < 11) && (7 > parseInt(dm))) {
			//alert("亲爱的，请不要使用ie怪义模式嘛");
			//$("body").append('<div id="bosCk2" class="l-ie-ms1">尊敬的用户，您当前的浏览器模式不正常，请按键盘上的 F12 键，简单设置一下即可。请选择：“浏览器模式”与“文档模式”，两种模式必须一致，建议设置至最高模式。<a href="javascript:void(0);" onclick="$(\'#bosCk2\').hide();" style="color:#ffffff">[关闭此提示]</a></div>');
			winOpen({
				id: "sss",
				content: '<img src="'+kui["kuiBase"]+'kui/images/iebug1.gif" border="0"/>',
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



/*
title效果，开始
调用方法：在页面上全局引用：var ImportAltJS=true;
元素加属性：ext_alt="{我们要提示}中华人民共和国<br>四川省"
==============================================
*/
//title 效果 2014年06月17日 16:09:32 星期二 lybide 优化版
jQuery.fn.titleTip = function(options) {
	var defaults = {
		speed: 500,
		xOffset: 15,
		yOffset: 15
	};
	var options = $.extend(defaults, options);

	var tipObj = $("#keTooltip");
	var tipObjTrans = $("#keTooltipTrans");
	if (tipObj.size()>0) {
		
	} else {
		tipObj=$('<div id="keTooltip" class="k-e-tooltip"><div id="keTooltipMain" class="k-e-tooltip-wrap"></div></div>').hide();
		tipObjTrans=$('<div id="keTooltipTrans" class="k-e-tooltip-trans"></div>').hide();
		$("body").append(tipObj,tipObjTrans);
		/*tipObj.css({
			"position": "absolute",
			"z-index": "9999",
			"background": "#D3DDF5",
			"background-image": "url(../../Quber_Image/Quber_Common/Quber_TB_TitltBG.png)",
			"padding": "5px",
			"opacity": "0.9",
			"border": "1px solid #A3C0E8",
			"-moz-border-radius": "3px",
			"border-radius": "3px",
			"-webkit-border-radius": "3px",
			"font-weight": "normal",
			"font-size": "12px",
			"display": "none",
			"width": 200
		});*/
	}

	function thisElementSW(keys,e){
		if (keys==1) {
			
			//2014年06月17日 15:59:01 星期二 lybide
			if (true) {
				var tH1=tipObj.outerHeight();
				
				var bodyHeight = $(document.body).outerHeight(true) < $(window).height() ? $(window).height() : $(document.body).outerHeight(true);

				/*if (tH1>bodyHeight) {
					tH1=bodyHeight-100;
					$(tipObj,tipObjTrans).height(tH1);console.log(tH1);//调试信息
				} else {
					$(tipObj,tipObjTrans).css({"height":"auto"});
				}*/

				var calTtop = e.pageY + tH1 + options.yOffset;
				
				if (calTtop > bodyHeight) {
					calTtop = e.pageY - tH1 - options.yOffset;
				} else {
					calTtop = e.pageY + defaults.xOffset;
				}
				
			}
			//2014年06月17日 15:21:21 星期二 lybide
			if (true) {
				var calLeft = e.pageX + tipObj.outerWidth() + options.xOffset;
				var bodyWidth = document.body.offsetWidth;
				
				if (calLeft > bodyWidth) {
					calLeft = e.pageX - tipObj.outerWidth();
				} else {
					calLeft = e.pageX + defaults.yOffset;
				}
			}
			tipObj.css("top", (calTtop) + "px").css("left", (calLeft) + "px").fadeIn(options.speed);
			tipObjTrans.css("top", (calTtop) + "px").css("left", (calLeft) + "px").css({"width":tipObj.outerWidth(),"height":tipObj.outerHeight()}).fadeIn(options.speed);
			//tipObj.css("top", (e.pageY + defaults.xOffset) + "px").css("left", (e.pageX + defaults.yOffset) + "px").fadeIn(options.speed);
		} else {
			tipObj.hide();
			tipObjTrans.hide();
		}
	}
				
	return this.each(function() {
		var $this = $(this);
		//console.log($this.attr('title'));
		if ($this.attr('title')) {

			//Pass the title to a variable and then remove it from DOM
			if ($this.attr('title') != '') {
				var tipTitle = ($this.attr('title'));
			} else {
				var tipTitle = 'QuberTip';
			}
			if($this.attr('title').indexOf("#")==0) {
				var tipTitle = $($this.attr('title')).html();
			}
			//Remove title attribute
			$this.removeAttr('title');
			$(this).hover(function(e) {
				//$(this).css('cursor', 'pointer');
				$("#keTooltipMain").html(tipTitle);
				//tipObj.css("top", (e.pageY + defaults.xOffset) + "px").css("left", (e.pageX + defaults.yOffset) + "px").fadeIn(options.speed);
				thisElementSW(1,e);
			}, function(e) {
				//Remove the tooltip from the DOM
				thisElementSW(0,e);
			});
			$(this).mousemove(function(e) {
				thisElementSW(1,e);
			});
		}
	});
};
/*
title效果，结束
==============================================
*/

//是否在外壳程序中 2014/11/12 20:37 lybide
function getkIsShell() {
	var b=false;
	try{
		//采用try方式，可在全浏览器，包括手机端，通过
		b=window.external.IsShell();
	}catch (e){}
	return b;
	/*if (typeof window.external.IsShell!=="undefined") {
		//手机端 window.external 已不存在。
		b=true;
	}
	return b;*/
	/*try {
		b=window.external.IsShell();
	} catch (e) {}
	if (!b) {
		//return;
	}
	return b;*/
}
/*if (getkIsShell() && (PAGE_KEYS=="sysMain" || PAGE_KEYS=="login")) {
	document.write(unescape('%3Cscript type="text/javascript" src="'+kui["kuiBase"]+'kui/frame/explorer.js"%3E%3C/script%3E'));
}*/

