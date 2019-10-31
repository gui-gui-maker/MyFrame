<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="zh-cn" xml:lang="zh-cn">
	<head>
		<%@include file="/k/kui-base-form.jsp"%>
		<title>消息提醒</title>
		<script type="text/javascript">
			$.ajaxSetup({
				cache: false
			});
			var isShell1=[false,2,15];
			try{
				if(window.external.IsShell()){//外壳
					$.getScript("pub/explorer/ui-ext/ui-ie-external.js");
					isShell1=[true,2,15];
				}
				else{//非外壳
					$.getScript("pub/explorer/ui-ext/ui-default.js");
				}
			}
			catch(e){//非外壳
				$.getScript("pub/explorer/ui-ext/ui-default.js");
			}

			function openMsgUrl(){
				markRead();
				var current = $(".msg-item");
				var url = current.attr("url");
				if(url && url != "null") {
					openExtDialog(url,"消息处理");
				};
			}

			$(function(){
				$("#s-b-msg-page-main").addClass("border00");
				$("#s-b-msg-title-close").click(function(){
					// 关闭消息小窗
					if (isShell1[0]) {
						var ret=window.external.WriteIni("user", "msgBoxUserClose", "1", "confing.user.ini");
						var ret=window.external.StopTrayFlicker();
						ret=window.external.ClosePopup("testkey");
						if (!ret) {
							alert(window.external.GetLastErrorMessage());
						}
					}else{
						api.close();
					}
				});
				$("#s-b-msg-page-main").width($(window).width()-isShell1[1]);
				$("#s-b-msg-page-main").height($(window).height()-isShell1[1]);
				var h1=$("#s-b-msg-page-main").height()-$("#s-b-msg-nav").outerHeight()-isShell1[2];
				$(".s-b-msg-list").height(h1);
				
				reloadMsg();
			});

			function reloadMsg(){
				$("body").mask("正在获取数据...");
				$.getJSON("pub/message/readSystemMsg.do",function(resp){
					$("body").unmask();
					if(!resp.success){$("#s-b-msg-main").html("<div class='s-b-msg-error'>获取消息失败！</div>");}
					if (!resp.data) {return;}
					if(resp.data.length==0){
						$("#noread").hide();
						$("#allreaded").hide();
						$("#s-b-msg-list").html("<div class='s-b-msg-error'><p>没有未读消息！</p></div>");
						return;
					}
					
					$("#noread").show();
					$("#allreaded").show();
					//将消息集合存入本地全局
					$(window).data("allMsg",resp.data);

					$("#c_count").text(resp.data.length);
					var musicStr='<bgsound src="pub/explorer/img/newsms.wav" autostart="true" loop="1" name="gq"/>' +
							'<audio src="pub/explorer/img/newsms.wav" autoplay="autoplay"></audio>';
					$("body").append(musicStr);
					if (isShell1[0]) {
						window.external.StartTrayFlicker("Skin/kh1.ico;Skin/kh2.ico;",400,"您有新的消息");
					} else {
						api.data.window.startTrayFlicker(true);
					}
					$("#c_no").text("1");
					var idx = resp.data.length-1;
					var item = resp.data[idx];
					var content  = $.kh.isNull(item.content)?item.msgContent:item.content;
					var str1="<div class='msg-item' read='0' url='" + item.msgUrl + "' msgId='" + item.id + "' idx='" + idx +
							"' read='0'><div class='msg-content'><span class='status noread'>未读</span><a href='javascript:void(0);' onclick='openMsgUrl(this);'>" + content +
							"</a></div><div class='msg-info'><div class='a'>" + item.msgSender + "</div><div class='b'>" + item.sendTime + "</div></div></div>";
					$("#s-b-msg-list").html(str1);
					if (resp.data.length>1) {
						$("#msg-btn-next,#msg-btn-no,#msg-btn-prev").show();
					}
					$("#s-b-msg-list .msg-item:first").show().addClass("msg-show");
				});
			}
			
			function showMsgContent(idx){
				var msgData = $(window).data("allMsg")[idx];
				$(".msg-item").attr("url",msgData.msgUrl);
				$(".msg-item").attr("msgId",msgData.id);
				$(".msg-item").attr("idx",idx);
				$(".msg-item").attr("read",msgData.status);
				$(".msg-item a").text($.kh.isNull(msgData.content)?msgData.msgContent:msgData.content);
				$(".msg-info .a").text(msgData.msgSender);
				$(".msg-info .b").text($.kh.isNull(msgData.sendTime)?msgData.msgSendTime:msgData.sendTime);
				$("#c_no").text($(window).data("allMsg").length - idx);
				if(msgData.read==1){
					$("#noread").hide();
					$(".msg-item span.status").text("已读").addClass("read");
				}else{
					$("#noread").show();
					$(".msg-item span.status").text("未读").removeClass("read");
				}
			}

			function preMsg(){
				var idx = parseInt($(".msg-item").attr("idx"));
				if(idx==$(window).data("allMsg").length-1) return;
				showMsgContent(idx+1);
			}

			function nextMsg(){
				var idx = parseInt($(".msg-item").attr("idx"));
				if(idx==0) return;
				showMsgContent(idx-1);
			}

			function markRead(){
				var current = $(".msg-item");
				current.find("span.status").text("已读").addClass("read");
				var msgData = $(window).data("allMsg")[current.attr("idx")];
				if(msgData.read == 1)return;
				$.getJSON("pub/message/markRead.do?msId=" + msgData.id,function(resp){
					if(resp.success){
						msgData.read = 1;
						$("#noread").hide();
						if($("#s-b-msg-list>div[read='0']").size()==0) {
							startTrayFlicker(false);
						};
					}
				});
			}
			
			function markAllread(){
				$("body").mask("正在处理，请稍候...");
				$.getJSON("pub/message/markAllRead.do",function(){
					$("#noread").hide();
					$("#allreaded").hide();
					$("#msg-btn-next,#msg-btn-no,#msg-btn-prev").hide();
					$("#s-b-msg-list").html("<div class='s-b-msg-error'><p>没有未读消息！</p></div>");
					$("body").unmask();
				});
			}

			function allMessage(){
				openExtDialog("pub/message/user_message_all.jsp","所有消息",1000,550);
			}
		</script>
		<style type="text/css">
			.s-b-msg-nav-wrap a.mark{margin:0 0 0 0px;border:none!important;text-decoration:none; display:inline-block; width:16px!important; height:32px!important; padding:0 8px!important;}
			.no-read{background:url("k/kui/images/icons/16/check_bph.png") no-repeat center;}
			.no-read:hover {background:#e5e5e5 url("k/kui/images/icons/16/check_bph.png") no-repeat center;}
			.refresh{background:url("k/kui/images/icons/16/f5_bph.png") no-repeat center;}
			.refresh:hover {background:#e5e5e5 url("k/kui/images/icons/16/f5_bph.png") no-repeat center;}
			.all-message{background:url("k/kui/images/icons/16/discuss_bph.png") no-repeat center;}
			.all-message:hover {background:#e5e5e5 url("k/kui/images/icons/16/discuss_bph.png") no-repeat center;}
			.all-readed{background:url("k/kui/images/icons/16/allmsg_bph.png") no-repeat center;}
			.all-readed:hover {background:#e5e5e5 url("k/kui/images/icons/16/allmsg_bph.png") no-repeat center;}
			.msg-content span.status{display:inline-block;padding:0 3px;margin-right:5px;border-radius:2px;color:white;background:#666666;}
			.msg-content span.read{background:green;}
		</style>
	</head>
	<body>
		<div id="s-b-msg-page-main" class="s-b-msg-page-main isshell bo" style="">
			<div id="s-b-msg-page-wrap" class="s-b-msg-page-wrap">
				<div id="s-b-msg-title" class="s-b-msg-title"><div class="title">系统消息</div><div class="close"
						id="s-b-msg-title-close"><a href="javascript:void(0);"><span>x</span></a></div></div>
				<div id="s-b-msg-main" class="s-b-msg-main">
					<div class="s-b-msg-list" id="s-b-msg-list"></div>
					<div class="s-b-msg-nav" id="s-b-msg-nav">
						<div id="s-b-msg-nav-wrap" class="s-b-msg-nav-wrap">
							<a href="javascript:markRead(false);" class="mark no-read" id="noread" title="标为已读"></a>
							<a href="javascript:markAllread(false);" class="mark all-readed" id="allreaded" title="全部标为已读"></a>
							<a href="javascript:reloadMsg();" class="mark refresh" title="刷新"></a>
                            <a href="javascript:allMessage();" class="mark all-message" title="所有消息"></a>
							<a href="javascript:nextMsg();" id="msg-btn-next" class="next page-nav" title="后一个消息"></a>
							<div id="msg-btn-no" class="no page-nav"><span id="c_no">0</span>/<span id="c_count" class="count">0</span></div>
							<a href="javascript:preMsg();" id="msg-btn-prev" class="prev page-nav" title="前一个消息"></a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>