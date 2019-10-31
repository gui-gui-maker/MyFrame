var is_window_externall=false,
	is_window_externall_msgboxAutoHide=false,
	QQ_msgCountOld= 0,
	QQ_msgCountNew= 0,
	QQ_ReaderIntervalMS=30000,
	is_window_externall_msgUserClose="";

$(function(){//jQuery页面载入事件

	msg_loader();
});

function msg_loader() {
	$.ajaxSetup({
		cache: false
	});
	if (typeof window.external.IsShell=="undefined") {
		var sysMsgSwitch=kui["sysMsgSwitch"]!==false ? true : kui["sysMsgSwitch"];//系统消息 - 提醒功能是否开启
		if (!sysMsgSwitch) {
			//return;
		} else {
			var sysMsgBar=$("#sys-message-bar");
			if (sysMsgBar.length>0) {

			} else {
				sysMsgBar=$('<div id="sys-message-bar" class="sys-message-bar"><div class="msg"></div></div>');
				sysMsgBar.click(function(){
					openExtMsgbox("pub/explorer/message.jsp","testkey");
				});
				$("#sysMain").append(sysMsgBar);
			}
		}

		$.getScript("pub/explorer/ui-ext/ui-default.js",startInterval);
	} else {
		is_window_externall = true;
		is_window_externall_msgboxAutoHide=window.external.ReadIni("user", "msgboxAutoHide", "confing.user.ini");//消息窗口自动隐藏
		//window.external.WriteIni("user", "msgBoxUserClose", "0", "confing.user.ini");

		/*if (is_window_externall_msgboxAutoHide=="1") {

		} else {

		}*/
		$.getScript("pub/explorer/ui-ext/ui-ie-external.js",startInterval);
	}

}


//消息和任务提醒
function startInterval(){
	var sysMsgSwitch=kui["sysMsgSwitch"]!==false ? true : kui["sysMsgSwitch"];//系统消息 - 提醒功能是否开启
	if (!sysMsgSwitch) {
		return;
	}
	$.getJSON("pub/message/readSystemMsgCount.do",function(resp){
		var oneExe1=setTimeout(function(){
			startInterval();
		},kui["sysMsgReaderIntervalMS"] || QQ_ReaderIntervalMS);
    	if(!resp.success || resp.data == 0){
        	startTrayFlicker(false);
    		return;
    	}
        if(resp.data > 0){
			QQ_msgCountNew=resp.data;
			if (is_window_externall) {
				is_window_externall_msgUserClose=window.external.ReadIni("user", "msgBoxUserClose", "confing.user.ini");

				if (is_window_externall_msgUserClose=="1" && QQ_msgCountOld==QQ_msgCountNew) {
					return;
				}
				//startTrayFlicker(true);
			}
			//alert([is_window_externall_msgUserClose,QQ_msgCountOld,QQ_msgCountNew]);
			if (QQ_msgCountOld==QQ_msgCountNew) {
				return;
			} else {
				//window.external.WriteIni("user", "msgBoxUserClose", "1", "confing.user.ini");
			}

			QQ_msgCountOld=QQ_msgCountNew;
        	//startTrayFlicker(true);
        	openExtMsgbox("pub/explorer/message.jsp?count=" + resp.data,"testkey");
        }
    });
}