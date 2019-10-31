// 请勿修改，否则可能出错
function uaMatch() {
	var ua = navigator.userAgent, rMsie = /(msie\s|trident.*rv:)([\w.]+)/, rFirefox = /(firefox)\/([\w.]+)/, rOpera = /(opera).+version\/([\w.]+)/, rChrome = /(chrome)\/([\w.]+)/, rSafari = /version\/([\w.]+).*(safari)/;
	ua = ua.toLowerCase();
	var match = rMsie.exec(ua);
	if (match != null) {
		return {
			browser : "IE",
			version : match[2] || "0"
		};
	}
	var match = rFirefox.exec(ua);
	if (match != null) {
		return {
			browser : match[1] || "",
			version : match[2] || "0"
		};
	}
	var match = rOpera.exec(ua);
	if (match != null) {
		return {
			browser : match[1] || "",
			version : match[2] || "0"
		};
	}
	var match = rChrome.exec(ua);
	if (match != null) {
		return {
			browser : match[1] || "",
			version : match[2] || "0"
		};
	}
	var match = rSafari.exec(ua);
	if (match != null) {
		return {
			browser : match[2] || "",
			version : match[1] || "0"
		};
	}
	if (match != null) {
		return {
			browser : "",
			version : "0"
		};
	}
}
function _ntko_get_browser(){
	var browser;
	var version;
	var browserMatch = uaMatch();
	if (browserMatch.browser) {
		browser = browserMatch.browser;
		version = browserMatch.version;
	}
	return browser;
}

window["_browser_type"] = _ntko_get_browser();

function _ntko_onSavedToUrl(type, code, html) {
	_onSavedToUrl(html);
}

function _ntko_onPublishAdPdfToUrl(type, code, html) {
	_onPublishAdPdfToUrl(html);
}

function _ntko_onDocumentOpened(){
	var tocx = document.getElementById("TANGER_OCX");
	tocx.activeDocument.saved=true;
	onDocumentOpened();
}

function addPdfPlugin(){
	document.getElementById("TANGER_OCX").AddDocTypePlugin(".pdf","PDF.NtkoDocument","4.0.0.2","pub/office/ntkooledocall.cab",51,false);
}

function initNtkoEditorContainer(containerId,cuser){
	var _browser = _ntko_get_browser();
	var _html_str="";
	if (_browser == "IE") {
		_html_str = '<!-- 用来产生编辑状态的ActiveX控件的JS脚本-->' +
		'<!-- 因为微软的ActiveX新机制，需要一个外部引入的js-->' +
		'<object id="TANGER_OCX" classid="clsid:C39F1330-3322-4a1d-9BF0-0BA2BB90E970"' +
		'codebase="pub/office/ofctnewclsid.cab#version=5,0,2,7" width="100%" height="100%">' +
		'<param name="IsUseUTF8URL" value="-1">' +
		'<param name="IsUseUTF8Data" value="-1">' +
		'<param name="BorderStyle" value="1">' +
		'<param name="BorderColor" value="14402205">' +
		'<param name="TitlebarColor" value="15658734">' +
		'<param name="isoptforopenspeed" value="0">' +
	
		'<param name="MakerCaption" value="成都川大科鸿新技术研究所">' +
		'<param name="MakerKey" value="5659A5E96C504E9506C566FB329BAF7F4C7D6306">' +
		'<param name="ProductCaption" value="' + kFrameConfig.ntkoProductCaption + '"> ' +
		'<param name="ProductKey" value="' + kFrameConfig.ntkoProductKey + '">' +

		'<param name="TitlebarTextColor" value="0"> ' +
		'<param name="MenubarColor" value="14402205"> ' +
		'<param name="MenuButtonColor" VALUE="16180947"> ' +
		'<param name="MenuBarStyle" value="3"> ' +
		'<param name="MenuButtonStyle" value="7"> ' +
		'<param name="WebUserName" value="' + cuser + '"> ' +
		'<param name="Caption" value="成都川大科鸿新技术研究所"> ' +
		'<SPAN STYLE="color:red">不能装载文档控件。请在检查浏览器的选项中检查浏览器的安全设置。如果您尚未安装此控件，<a href="app/pub/office/NtkoAllControlSetup.msi">请点击这里下载</a></SPAN> ' +
		'</object>';
	} else if (_browser == "firefox") {
		_html_str = '<object id="TANGER_OCX" type="application/ntko-plug"  codebase="pub/office/ofctnewclsid.cab#version=5,0,2,7" width="100%" height="100%" ' + 
		'ForOnSaveToURL="_ntko_onSavedToUrl" ' + 
		'ForOndocumentopened="_ntko_onDocumentOpened" ' + 
		'ForOnpublishAspdftourl="_ntko_onPublishAdPdfToUrl" ' +
		'_IsUseUTF8URL="-1" ' +
	
		'_MakerCaption="成都川大科鸿新技术研究所" ' +
		'_MakerKey="5659A5E96C504E9506C566FB329BAF7F4C7D6306" ' +
		'_ProductCaption="' + kFrameConfig.ntkoProductCaption + '" ' +
		'_ProductKey="' + kFrameConfig.ntkoProductKey + '"' +
	
		'_IsUseUTF8Data="-1" ' +
		'_BorderStyle="1" ' +
		'_BorderColor="14402205" ' +
		'_MenubarColor="14402205" ' +
		'_MenuButtonColor="16180947" ' +
		'_MenuBarStyle="3" ' +
		'_MenuButtonStyle="7" ' +
		'_WebUserName="' + cuser + '" ' +
		'clsid="{C39F1330-3322-4a1d-9BF0-0BA2BB90E970}">' +
		'<SPAN STYLE="color:red">尚未安装NTKO Web FireFox跨浏览器插件。请点击<a href="pub/tools/dowload_soft.jsp?fpath=T0ZGSUNFzsS1tb/YvP6wstews8zQ8i5tc2k=">安装组1件</a></SPAN>' +
		'</object>';
	} else if (_browser == "chrome") {
		_html_str = '<object id="TANGER_OCX" clsid="{C39F1330-3322-4a1d-9BF0-0BA2BB90E970}" ' +
		'ForOnSaveToURL="_ntko_onSavedToUrl" ' +
		'ForOndocumentopened="_ntko_onDocumentOpened" ' + 
		'ForOnpublishAspdftourl="_ntko_onPublishAdPdfToUrl" ' +
	
		'_MakerCaption="成都川大科鸿新技术研究所"' +
		'_MakerKey="5659A5E96C504E9506C566FB329BAF7F4C7D6306" ' +
		'_ProductCaption="' + kFrameConfig.ntkoProductCaption + '" ' +
		'_ProductKey="' + kFrameConfig.ntkoProductKey + '"' +
	
		'codebase="pub/office/ofctnewclsid.cab#version=5,0,2,7" width="100%" height="100%" type="application/ntko-plug" ' +
		'_IsUseUTF8URL="-1" ' +
		'_IsUseUTF8Data="-1" ' +
		'_BorderStyle="1" ' +
		'_BorderColor="14402205" ' +
		'_MenubarColor="14402205" ' +
		'_MenuButtonColor="16180947" ' +
		'_MenuBarStyle="3" ' +
		'_MenuButtonStyle="7" ' +
		'_WebUserName="' + cuser + '" ' +
		'_Caption="成都川大科鸿新技术研究所">' +
		'<SPAN STYLE="color:red">尚未安装NTKO Web Chrome跨浏览器插件。请点击<a href="pub/tools/dowload_soft.jsp?fpath=T0ZGSUNFzsS1tb/YvP6wstews8zQ8i5tc2k=">安装组件</a></SPAN>'+
		'</object>';
	} else if (Sys.opera) {
		alert("对不起,文档控件暂时不支持opera!");
	} else if (Sys.safari) {
		alert("对不起,文档控件暂时不支持safari!");
	}
	$("#"+containerId).html(_html_str);
}