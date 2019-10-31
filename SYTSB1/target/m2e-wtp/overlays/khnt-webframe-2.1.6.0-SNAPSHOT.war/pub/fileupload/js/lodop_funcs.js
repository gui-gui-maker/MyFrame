var CreatedOKLodop7766 = null;

// ====判断是否需要安装CLodop云打印服务器:====
function needCLodop() {
	try {
		var ua = navigator.userAgent;
		if (ua.match(/Windows\sPhone/i) != null)
			return true;
		if (ua.match(/iPhone|iPod/i) != null)
			return true;
		if (ua.match(/Android/i) != null)
			return true;
		if (ua.match(/Edge\D?\d+/i) != null)
			return true;
		if (ua.match(/QQBrowser/i) != null)
			return false;
		var verTrident = ua.match(/Trident\D?\d+/i);
		var verIE = ua.match(/MSIE\D?\d+/i);
		var verOPR = ua.match(/OPR\D?\d+/i);
		var verFF = ua.match(/Firefox\D?\d+/i);
		var x64 = ua.match(/x64/i);
		if ((verTrident == null) && (verIE == null) && (x64 !== null))
			return true;
		else if (verFF !== null) {
			verFF = verFF[0].match(/\d+/);
			if (verFF[0] >= 42)
				return true;
		} else if (verOPR !== null) {
			verOPR = verOPR[0].match(/\d+/);
			if (verOPR[0] >= 32)
				return true;
		} else if ((verTrident == null) && (verIE == null)) {
			var verChrome = ua.match(/Chrome\D?\d+/i);
			if (verChrome !== null) {
				verChrome = verChrome[0].match(/\d+/);
				if (verChrome[0] >= 42)
					return true;
			}
		}
		return false;
	} catch (err) {
		return true;
	}
};

// ====页面引用CLodop云打印必须的JS文件：====
if (needCLodop()) {
	// 让其它电脑的浏览器通过本机打印（适用例子）：
	//$("head").append("<script type='text/javascript' src='/CLodopfuncs.js'></script>");
	// 让本机浏览器打印(更优先)：
	$("head").append("<script type='text/javascript' src='http://localhost:8000/CLodopfuncs.js?priority=1'></script>");
};

var strHtmInstall = "<h3>打印控件未安装！</h3><p>点击这里<a href='javascript:downloadLodop(\"aW5zdGFsbF9sb2RvcDMyLmV4ZQ==\")'>下载并安装</a>，安装后请刷新页面或重新进入。</p>";
var strHtmUpdate = "<h3>打印控件需要升级！</h3><p>点击这里<a href='javascript:downloadLodop(\"aW5zdGFsbF9sb2RvcDMyLmV4ZQ==\")'>下载并升级</a>，升级后请重新进入。</p>";
var strHtm64_Install = "<h3>打印控件未安装！</h3><p>点击这里<a href='javascript:downloadLodop(\"aW5zdGFsbF9sb2RvcDY0LmV4ZQ==\")'>下载并安装</a>，安装后请刷新页面或重新进入。</p>";
var strHtm64_Update = "<h3>打印控件需要升级！</h3><p>点击这里<a href='javascript:downloadLodop(\"aW5zdGFsbF9sb2RvcDY0LmV4ZQ==\")'>下载并升级</a>，升级后请重新进入。</p>";
var strCLodopInstall = "<h3>CLodop云打印服务(localhost本地)未安装启动！</h3><p>点击这里<a href='javascript:downloadLodop(\"Q0xvZG9wUHJpbnRfU2V0dXBfZm9yX1dpbjMyTlQuZXhl\")'>下载并安装</a>，安装后请刷新页面。</p>";
var strCLodopUpdate = "<h3>CLodop云打印服务需升级！</h3><p>点击这里<a href='javascript:downloadLodop(\"Q0xvZG9wUHJpbnRfU2V0dXBfZm9yX1dpbjMyTlQuZXhl\")'>下载并升级</a>，升级后请刷新页面。</p>";

var strHtmFireFox = "<h3>（注意：如曾安装过Lodop旧版附件npActiveXPLugin,请在【工具】->【附加组件】->【扩展】中先卸它）</h3>";
var strHtmChrome = "<h3>（如果此前正常，仅因浏览器升级或重安装而出问题，需重新执行以上安装）</h3>";

// ====获取LODOP对象的主过程：====
function getLodop(oOBJECT, oEMBED) {
	var LODOP;
	try {
		var isIE = (navigator.userAgent.indexOf('MSIE') >= 0)
				|| (navigator.userAgent.indexOf('Trident') >= 0);
		if (needCLodop()) {
			try {
				LODOP = getCLodop();
			} catch (err) {
			}
			if (!LODOP && document.readyState !== "complete") {
				alert("C-Lodop没准备好，请稍后再试！");
				return;
			}
			if (!LODOP) {
				if (isIE)
					showLodopDownload(strCLodopInstall);
				else
					showLodopDownload(strCLodopInstall);
				return;
			} else {
				if (CLODOP.CVERSION < "2.0.4.2") {
					if (isIE)
						showLodopDownload(strCLodopUpdate);
					else
						showLodopDownload(strCLodopUpdate);
				}
				if (oEMBED && oEMBED.parentNode)
					oEMBED.parentNode.removeChild(oEMBED);
				if (oOBJECT && oOBJECT.parentNode)
					oOBJECT.parentNode.removeChild(oOBJECT);
			}
		} else {
			var is64IE = isIE && (navigator.userAgent.indexOf('x64') >= 0);
			// =====如果页面有Lodop就直接使用，没有则新建:==========
			if (oOBJECT != undefined || oEMBED != undefined) {
				if (isIE)
					LODOP = oOBJECT;
				else
					LODOP = oEMBED;
			} else if (CreatedOKLodop7766 == null) {
				LODOP = document.createElement("object");
				LODOP.setAttribute("width", 0);
				LODOP.setAttribute("height", 0);
				LODOP.setAttribute("style","position:absolute;left:0px;top:-100px;width:0px;height:0px;");
				if (isIE)
					LODOP.setAttribute("classid","clsid:2105C259-1E0C-4534-8141-A753534CB4CA");
				else
					LODOP.setAttribute("type", "application/x-print-lodop");
				document.documentElement.appendChild(LODOP);
				CreatedOKLodop7766 = LODOP;
			} else
				LODOP = CreatedOKLodop7766;
			
			// =====Lodop插件未安装时提示下载地址:==========
			if ((LODOP == null) || (typeof (LODOP.VERSION) == "undefined")) {
				if (navigator.userAgent.indexOf('Chrome') >= 0)
					showLodopDownload(strHtmChrome);
				if (navigator.userAgent.indexOf('Firefox') >= 0)
					showLodopDownload(strHtmFireFox);
				if (is64IE)
					showLodopDownload(strHtm64_Install);
				else if (isIE)
					showLodopDownload(strHtmInstall);
				else
					showLodopDownload(strHtmInstall);
				return LODOP;
			}
		}
		if (LODOP.VERSION < "6.2.0.3") {
			if (needCLodop())
				showLodopDownload(strCLodopUpdate);
			else if (is64IE)
				showLodopDownload(strHtm64_Update);
			else if (isIE)
				showLodopDownload(strHtmUpdate);
			else
				showLodopDownload(strHtmUpdate);
			return LODOP;
		}
		// ===如下空白位置适合调用统一功能(如注册语句、语言选择等):===
		return LODOP;
	} catch (err) {
		alert("getLodop出错:" + err);
	}
}

function showLodopDownload(htmlstr){
	top.$.dialog({
		title: "下载LODOP安装文件",
		width: 450,
		height: 200,
		lock: true,
		parent: api,
		content: "url:pub/fileupload/lodop_down.jsp",
		cancel: true,
		data: {
			content: htmlstr
		}
	});
}