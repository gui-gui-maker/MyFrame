
$(window).load(function(){
	/*if ($("#systemTitleText").length>0) {
			document.title=$("#systemTitleText").text()+" - "+kFrameConfig.name;
		}*/
});

var imTimeoutID;

//右下角消息窗口
function openMsgDialog(){
	try{
		if(window.external.isMsgWin()==1){
			window.external.openMsgUrl($("base").attr("href") + '/webim/message.jsp');
			window.external.setMsgPos(3,45,5,350,150);
		}
	}
	catch(err){
	}
}

//取消消息精灵任务栏图标闪动
function cancelIMMsgIconFlash(){
	try{
		if(imTimeoutID){
			window.clearTimeout(imTimeoutID);
			imTimeoutID==null;
			if(window.external.isMsgWin()==1){
				window.external.ChangeTrayIcon(1);
			}
		}
	}
	catch(err){
	}
}

//消息精灵任务栏图标闪动 
function showIMMsgIcon(){
	try{
		if(window.external.isMsgWin()==1){
			window.external.ChangeTrayIcon(2);
			imTimeoutID = window.setTimeout(function(){
				window.external.ChangeTrayIcon(1);
				imTimeoutID = window.setTimeout(showIMMsgIcon,400);
			},400);
		}
	}
	catch(err){
	}
}
