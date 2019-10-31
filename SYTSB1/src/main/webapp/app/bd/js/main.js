
function windowSize(arg) {
	//var w1=$(window).width();
	//var h1=$(window).height();
	//2017年06月12日 17:10:11 星期一 lybide 采用top.window
	var w1=$(top.window).width();
	var h1=$(top.window).height();
	var body=$("html");
	body.removeClass("window-w-320 window-w-480 window-w-650 window-w-720 window-w-800 window-w-960 window-w-1024 window-w-1280 window-w-1366 window-w-1440 window-w-1680 window-w-1920 window-w-2048 window-w-2560 window-w-3200 window-w-3840 window-w-5120 window-w-6400 window-w-7680");
	if (w1 >240 && w1 <= 320) {
		body.addClass("window-w-320");
	}
	if (w1 >320 && w1 <= 480) {
		body.addClass("window-w-480");
	}
	if (w1 >480 && w1 <= 650) {
		body.addClass("window-w-650");
	}
	if (w1 >650 && w1 <= 720) {
		body.addClass("window-w-720");
	}
	if (w1 >720 && w1 <= 800) {
		body.addClass("window-w-800");
	}
	if (w1 >800 && w1 <= 960) {
		body.addClass("window-w-960");
	}
	if (w1 >960 && w1 <= 1152) {
		body.addClass("window-w-1024");
	}
	/*if (w1 >1024 && w1 <= 1152) {
		body.addClass("window-w-1136");
	}*/
	if (w1 >1152 && w1 <= 1280) {
		body.addClass("window-w-1280");
	}
	if (w1 > 1280 && w1 <= 1366) {
		body.addClass("window-w-1366");
	}
	if (w1 > 1366 && w1 <= 1440) {
		body.addClass("window-w-1440");
	}
	if (w1 > 1440 && w1 <= 1680) {
		body.addClass("window-w-1680");
	}
	if (w1 > 1680 && w1 <= 1920) {
		body.addClass("window-w-1920");
	}
	if (w1 > 1920 && w1 <= 2048) {
		body.addClass("window-w-2048");
	}
	if (w1 > 2048 && w1 <= 2560) {
		body.addClass("window-w-2560");
	}
	if (w1 > 2560 && w1 <= 3200) {
		body.addClass("window-w-3200");
	}
	if (w1 > 3200 && w1 <= 3840) {
		body.addClass("window-w-3840");
	}
	if (w1 > 3840 && w1 <= 4096) {
		body.addClass("window-w-4096");
	}
	if (w1 > 4096 && w1 <= 5120) {
		body.addClass("window-w-5120");
	}
	if (w1 > 5120 && w1 <= 6400) {
		body.addClass("window-w-6400");
	}
	if (w1 > 6400 && w1 <= 7680) {
		body.addClass("window-w-7680");
	}
	if (w1 > 7680) {
		body.addClass("window-w-big10000");
	}
	var bc = BROWSER_INFO;
	var ie = bc.ie,
		xt = bc.system,
		xtms = bc.systemx,
		dm = bc.docMode,
		bsType=bc.type,
		iever = bc.ieversion;//alert(ie+"++"+iever+"=="+bsType)
	if (ie) {
		if (ie) {// && parseFloat(iever) <= 8
			$("html").addClass("window-is-ie"+parseInt(iever));//ie兼容性 2017年07月20日 17:17:07 星期四 lybide
		}
	}
}

function getBrowserInfo() {
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

var BROWSER_INFO = getBrowserInfo();

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




$(function(){
	windowSize();

});



