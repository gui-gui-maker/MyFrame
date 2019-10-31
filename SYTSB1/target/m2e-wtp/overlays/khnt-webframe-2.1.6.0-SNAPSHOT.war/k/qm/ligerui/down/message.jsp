<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="zh-cn" xml:lang="zh-cn">
	<head>
		<%@include file="/k/kui-base-form.jsp"%>
		<script type="text/javascript">
			/* var Grid;
			$(function(){
				Grid = $("#qmGrid").ligerGrid({
					columns : [ 
						{display:'主键',name:'id',hide:true},
		                {display:'名称',name:'pageTitle',align:'left',width:180}, 
						{display:'操作',name:'cz',align:"center",width:60,
							render: function(rowdata, rowindex, value){
								return "<a href='javascript:download(\""+rowdata.id+"\")'>下载</a>"
							}
				        }
					],
					usePager: false,
					height:100,
					data:{Rows:api.data.data}
				});
			})
			*/
			
			$(function(){
				$("#s-b-msg-page-main").width($(window).width());
				$("#s-b-msg-page-main").height($(window).height());
				var h1=$("#s-b-msg-page-main").height()-$("#s-b-msg-nav").outerHeight();
				$(".s-b-msg-list").height(h1);
				loadData();
			})
			
			function loadData(){
				$.getJSON("qm?__method=getDownLoadInfo",{pageid:'QM999999999'},function(resp){
					if(!resp.success){$("#s-b-msg-main").html("<div class='s-b-msg-error'>获取信息失败！</div>");}
					if (!resp.data) {return;}
					if(resp.data.length==0){
						/* $("#noread").hide();
						$("#s-b-msg-list").html("<div class='s-b-msg-error'><p>暂无信息！</p></div>"); */
						setTimeout(function(){
							api.close();
						},3000)
						return;
					}
					//将消息集合存入本地全局
					$(window).data("allMsg",resp.data);
					$("#c_count").text(resp.data.length);
					$("#c_no").text("1");
					var idx = resp.data.length-1;
					var item =resp.data[idx];
					var content  = item.pageTitle;
					var page = item.exportAll=='1'?"全部数据":"第"+item.currentPageSize+"页数据";
					var str1="<div class='msg-item'  idx='" + idx + 
						"' read='0'><div class='msg-content'><a href='javascript:void(0);' onclick='downloadFile(\""+item.id+"\");'>【下载】:" + "“"+content+"”&nbsp;"+page +
						"</a></div><div class='msg-info'><div class='a'>" + item.startDate + "</div><div class='b'>耗时：" + item.costTime + "秒</div></div></div>";
					$("#s-b-msg-list").html(str1);

					if (resp.data.length>0) {
						$("#msg-btn-next,#msg-btn-no,#msg-btn-prev").show();
					}
					$("#s-b-msg-list .msg-item:first").show().addClass("msg-show");//.height($(window).height()-35);
				})
			}
			
			function showMsgContent(idx){
				var msgData = $(window).data("allMsg")[idx];
				if(msgData){
					var content  = msgData.pageTitle;
					var page = msgData.exportAll=='1'?"全部数据":"第"+msgData.currentPageSize+"页数据";
					$(".msg-item").attr("url",msgData.pageTitle);
					$(".msg-item").attr("msgId",msgData.id);
					$(".msg-item").attr("idx",idx);
					$(".msg-item").attr("read",msgData.isDown);
					$(".msg-item a").text("【"+content+"】"+page);
					$(".msg-info .a").text(msgData.startDate);
					$(".msg-info .b").text("耗时："+msgData.costTime+"秒");
					$("#c_no").text($(window).data("allMsg").length - idx);
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
			function downloadFile(id){
				$.getJSON("qm?__method=setDownloadInfo&pageid=QM999999999",{pageDownId:id},function(res){
					if(res.success){
						$("#download").attr("src","qm?__method=download&pageid=QM999999999&pageDownId="+id);
						reloadMsg();
					}
				})
				
			} 
			function reloadMsg(arg) {
				$("#s-b-msg-list").empty();
				loadData();
				//window.location.reload();
			};
		</script>
		<style type="text/css">
			.s-b-msg-nav-wrap a.l-button{margin:0 0 5px 10px;border:none!important;text-decoration:none;}
			.l-button.no-read{background:url("k/kui/images/icons/16/check.png") no-repeat center;}
			.l-button.refresh{background:url("k/kui/images/icons/16/f5.png") no-repeat center;}
			.l-button.all-message{background:url("k/kui/images/icons/16/discuss.png") no-repeat center;}
		</style>
	</head>
	<body>
		<div id="s-b-msg-page-main" class="s-b-msg-page-main isshell" style="">
			<div id="s-b-msg-page-wrap" class="s-b-msg-page-wrap">
				<div id="s-b-msg-title" class="s-b-msg-title hide"><div class="title">系统消息</div><div class="close"
						id="s-b-msg-title-close"><a href="#khsoft"><span>x</span></a></div></div>
				<div id="s-b-msg-main" class="s-b-msg-main">
					<div class="s-b-msg-list" id="s-b-msg-list"></div>
					<div class="s-b-msg-nav" id="s-b-msg-nav">
						<div id="s-b-msg-nav-wrap" class="s-b-msg-nav-wrap">
							<!-- <a href="javascript:markread(false)" class="mark l-button no-read" id="noread" title="标为已读">&nbsp;&nbsp;</a>
							<a class="readed" id="readed">已读</a> -->
							<a class="mark l-button refresh" onclick="location.reload()" title="刷新">&nbsp;&nbsp;</a>
                            <!-- <a href="javascript:allMessage();" class="mark l-button all-message" title="所有消息">&nbsp;&nbsp;</a> -->
							<a id="msg-btn-next" class="next" href="javascript:nextMsg();" title="后一个"></a>
							<div id="msg-btn-no" class="no"><span id="c_no">0</span>/<span id="c_count" class="count">0</span></div>
							<a id="msg-btn-prev" class="prev" href="javascript:preMsg();" title="前一个"></a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<iframe id="download" style="display:none"></iframe>
	</body>
</html>