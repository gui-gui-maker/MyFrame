/**
 * 默认原生消息精灵JS扩展库。
 */

//弹出普通窗口，屏幕居中
function openExtDialog(url,title){
	//全局检查TOP对象是否注册了_hideNTKODialogEditor接口，用于隐藏已经打开的窗口中的NTKO编辑器，防止编辑器遮挡马上要打开的窗口内容
	if(typeof top["_hideNTKODialogEditor"] == "function"){
		top._hideNTKODialogEditor();
	}
	top.$.dialog({
		width: 1000,
		height: $(top).height()>=550?500:$(top).height()-50,
		lock : true,
		title: title,
		content : 'url:' + url,
		close: function(){
			// 关闭时，检查top是否注册_showNTKODialogEditor接口，用于显示已经打开的窗口中的NTKO编辑器
			if(typeof top["_showNTKODialogEditor"] == "function"){
				top._showNTKODialogEditor();
			}
		}
	});
}

//弹出右下角小窗口，用于消息即时提醒
function openMsgbox(msgText,title){
	var bb=startTrayFlicker;
	winOpen({
	    id: 'msg',
	    title: "任务消息",
	    content: msgText,
	    lock: false,
	    width: 300,
	    height: 185,
	    left: '100%',
	    top: '100%',
	    fixed: true,
	    resize: false,
	    max: false,
	    min: false,
	    skin:"win-flat noborder close-hide",//窗口无边框。关闭按钮不存在
		bb:startTrayFlicker,
		data:{window:window},
		close:function(){
			startTrayFlicker(false);
		}
	});
}

//弹出右下角小窗口，用于消息即时提醒
function openExtMsgbox(url,title){
	openMsgbox("url:" + url,title);
}
//隐藏右下角小窗口，用于消息即时提醒
function hideExtMsgbox(){
	try{
		api.close();
	}
	catch(err){
	}
}

//任务栏图标提示信息
function setTrayTooltips(msg){
}

function startTrayFlicker(bl){
	//var sysMsgFlicker=$("#sys-message-bar");
	//if (sysMsgFlicker.length>0) {
	//
	//} else {
	//	sysMsgFlicker=$('<div id="sys-message-bar" class="sys-message-bar"><div class="msg"></div></div>');
	//	$("#sysMain").append(sysMsgFlicker);
	//}
	if (bl) {
		//window.external.StartTrayFlicker("Skin/kh1.ico;Skin/kh2.ico;",400,"您有新的消息");
		$("#sys-message-bar .msg").removeClass("no-msg").addClass("flicker");
	} else {
		//window.external.SetTrayTooltips("科鸿任务管理系统");
		//window.external.StopTrayFlicker();
		$("#sys-message-bar .msg").removeClass("flicker").addClass("no-msg");
	}
}

function showHideWebimTips(s){
	if (s) {
		$("#mToolBar_webimicon em").addClass("flash");
	} else {
		$("#mToolBar_webimicon em").removeClass("flash");
	}
}

function showSystemWindow(){
	
}