$(function(){
	$(window).data("newMsgCount",0);
	$("head").append('<link rel="stylesheet" type="text/css" href="pub/webim/css/face.css" id="mainCSS">');
	window.setInterval(function() {
		$.getJSON("webimcon?__method=getNewMsgCount", function(data) {
			var _c_win_nmc = $(window).data("newMsgCount");
			$(window).data("newMsgCount",data.newMsgCount);
			if (data.newMsgCount > 0) {
				if(data.newMsgCount > _c_win_nmc){;
					showHideWebimTips(true);
					$("#imTipsSound").remove();
					$("#imTipsAudio").remove();
					var musicStr = '<audio id="imTipsAudio" src="pub/webim/wav/80.wav" autoplay="autoplay"></audio><bgsound id="imTipsSound" src="pub/webim/wav/80.wav" autostart="true" loop="1" name="gq"/>';
					$("body").append(musicStr);
				}
			} else {
				showHideWebimTips(false);
			}
		});
	}, 10000);
});

function showWebimDialog(){
    if ($.browser.msie && parseFloat($.browser.version)>10) {
    	if(window.external.IsShell){
    		var dh = 600,dw = 1000;
    		if(window.screen && window.screen.availWidth){
    			dw = window.screen.availWidth - 100;
    			dh = window.screen.availHeight - 50;
    		}
    		window.external.PopupMessageEx("sysMsgDetail","pub/webim/im.jsp",1,dw,dh);
    	}
    	else
    		window.open(($("base").attr("href") ? $("base").attr("href") : "")+"pub/webim/im.jsp","webim");
		return;
	}
	top.$.dialog({
		width:"100%",
		height:"100%",
		lock:true,
		title:"即时消息-WEBIM",
		content:"url:pub/webim/im.jsp"
	});
}