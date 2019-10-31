/**
 * IE外壳JS库。
 * 包含了系统中需要使用的外壳扩展功能JS接口。
 * 本JS库需要JQuery支持
 */

//个性配置文件名称
var PROFILE_FILE = "profile.ini";
//配置文件配置段标识
var PROFILE_CONFIG = "u_main";
//窗口关闭按钮功能配置KEY值
var PROFILE_KEY_CLOSE_BOX_REF = "CLOSE_BOX_REF";
//窗口关闭按钮功能配置值：退出程序
var PROFILE_VAL_CLOSE_BOX_QUIT = "quit";
//窗口关闭按钮功能配置KEY值: 隐藏窗口
var PROFILE_VAL_CLOSE_BOX_HIDE = "hide";
//窗口最大化值
var EXTERNAL_WIN_MAX = 2;
//窗口最小化值
var EXTERNAL_WIN_MIN = 1;
//窗口正常大小值
var EXTERNAL_WIN_NORMAL = 0;
//用户登录帐号
var PROFILE_KEY_USER_ACCOUNT = "USER_ACCOUNT";
// 用户登录密码
var PROFILE_KEY_USER_PWD = "SYS_PWD";
// 记住密码KEY
var PROFILE_KEY_REMEMBER_ME = "SYS_REMEMBER_ME";
// 自动登录KEY
var PROFILE_KEY_AUTO_LOGIN = "SYS_AUTO_LOGIN";
// 自动启动KEY
var PROFILE_KEY_AUTO_RUN = "SYS_AUTO_RUN";
// 属性值VAL：true
var PROFILE_VAL_TRUE = "true";
// 属性值VAL：false
var PROFILE_VAL_FALSE = "false";
// 系统主页 KEY
var PROFILE_KEY_SYS_INDEX = "SYS_INDEX";
// 系统登录URL KEY
var PROFILE_KEY_SYS_LOGIN_ACTION = "LOGIN_ACTION";

//弹出普通窗口，屏幕居中
function openExtDialog(url,title,w,h){
	var url=kui["base"] + url;
	url=encodeURIComponent(url);
	url=kui["base"]+"pub/explorer/shell_page.jsp?url="+url;

	var dh,dw;
	if(!$.kh.isNull(w) && !$.kh.isNull(h)){
	    dh = h;
	    dw = w;
	}else if(kFrameConfig.external){
	    dh = kFrameConfig.external.dialogHeight;
	    dw = kFrameConfig.external.dialogWidth;
	}
	if(/^\d+%$/.test(dw) && /^\d+%$/.test(dh) && window.screen && window.screen.availWidth){
	    //按屏幕尺寸比例设置窗口大小
	    var hr = parseInt(dh.replace(/%$/,""));
        var wr = parseInt(dw.replace(/%$/,""));
		dw = window.screen.availWidth/100*wr;
		dh = window.screen.availHeight/100*hr;
	}else{
	    dh=parseInt(dh);dw=parseInt(dw);
	}
    if(!dh) dh=600;
    if(!dw) dw=900;
	var ret = window.external.PopupMessageEx("sysMsgDetail",url,1,dw,dh);
	if (!ret) {
		alert(window.external.GetLastErrorMessage());
	}
}

//弹出右下角小窗口，用于消息即时提醒
function openExtMsgbox(url,title){
	//window.external.ClosePopup();
	window.external.PopupMessageEx(title,$("base").attr("href") + url,0,300,185);
	//window.external.ShowPopup();
}

//隐藏右下角小窗口，用于消息即时提醒
function hideExtMsgbox(){
	window.external.HidePopup();
}

//任务栏图标提示信息
function setTrayTooltips(msg){
	window.external.SetTrayTooltips(msg);
}
//webIm消息提醒
function showHideWebimTips(s){
	if (s) {
		window.external.StartTrayFlicker("Skin/mz.ico;Skin/mz1.ico;",400,"您有新的短消息");
		window.external.SetTrayTooltips("您有新的短消息");
	} else {
		window.external.StopTrayFlicker();
		window.external.SetTrayTooltips("");
	}
}
//任务栏图标闪烁
function startTrayFlicker(bl){
	if (bl) {
		$("#mToolBar_webimicon em").addClass("flash");
		window.external.StartTrayFlicker("Skin/mz.ico;Skin/mz1.ico;",400,"您有新的消息");
	} else {
		$("#mToolBar_webimicon em").removeClass("flash");
		window.external.SetTrayTooltips("");
		window.external.StopTrayFlicker();
	}
}

// ======================================== 以下为外壳接口 =================================
//外壳独有接口，隐藏窗口，在系统托盘显示
function hideFrameWindow(){
	var ref = getProfile(PROFILE_KEY_CLOSE_BOX_REF);
	var coh = false;
	if(ref) coh = (ref == PROFILE_VAL_CLOSE_BOX_QUIT);
	else{
		coh = window.confirm("请确认点击此按钮是“关闭窗口并退出系统”还是“隐藏到右下角系统托盘”？\n\n关闭并退出系统请点击“确定”\n隐藏到系统托盘请点击:“取消”");
		if(coh)
			writeProfile(PROFILE_KEY_CLOSE_BOX_REF,PROFILE_VAL_CLOSE_BOX_QUIT);
		else
			writeProfile(PROFILE_KEY_CLOSE_BOX_REF,PROFILE_VAL_CLOSE_BOX_HIDE);
	}
	doCloseBoxFunc(coh);
}

function getAutoStartup(){
	return window.external.IsAutoStartup();
}

function setAutoStartup(yon){
	var ret = window.external.SetAutoStartup(yon);
	if (!ret) {
		alert(window.external.GetLastError());
	}
}

function doCloseBoxFunc(bl){
	if(bl){
		location = $("base").attr("href") + "/j_spring_security_logout";
		window.external.Quit();
	}
	else
		window.external.HideFrameWindow();
}

//读取配置信息，根据键值KEY
function getProfile(key){
	return window.external.ReadIni(PROFILE_CONFIG,key,PROFILE_FILE);
}

//写入配置信息，参数为KEY,VAL
function writeProfile(key,val){
	window.external.WriteIni(PROFILE_CONFIG,key,val,PROFILE_FILE);
}

//锁定屏幕
function lockScreenState(){
	var state = window.external.GetLockScreenState();
	window.external.LockScreen(state?0:1);
}

//软件设定
function profileConfig(){
	window.open($("base").attr("href") + "pub/explorer/profileConfig.jsp");
}

//软件首页
function openKhntWeb(){
	window.external.SetWindowState(2);
	location=$("base").attr("href");
	window.external.ShowFrameWindow();
}

function showSystemWindow(){
	window.external.ShowFrameWindow();
}

//设置窗体标题
function setWindowTitle(title){
	window.external.SetWindowText(title);
}

//设置窗体状态(最大化2，最小化1，正常0)
function extMaxMinWindow(val){
	window.external.SetWindowState(val);
}

//客户端更新
function updateClient(){
	window.external.UpdateFrame();
}