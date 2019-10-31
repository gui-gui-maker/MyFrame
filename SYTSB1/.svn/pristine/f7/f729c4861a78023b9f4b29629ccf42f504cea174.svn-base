<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
   var beanData;
	$(function() {
		$.getJSON("pub/sysMessage/detail.do?id=${param.id}",function(response) {
			beanData=response.data;
			if (!response.success){
				$.ligerDialog.alert("获取数据错误!");
				return;
			}
			$("#sender_name").text(beanData.senderName);
			var sendTime=beanData.createTime;
			if(sendTime!=null){
				sendTime=sendTime.substr(0,sendTime.length-3);
				$("#send_time").text(sendTime);
				//var d = new Date(sendTime);
				//$("#send_time").text(d.getFullYear() + "." + d.getMonth()+ "." + d.getDate());
			}
			$("#title").text("任务消息");
			$("#content").html(beanData.content);
		});
	});
</script>
<style type="text/css">
.fileList li{
	margin-left : 1em;
	list-style: decimal;
	line-height: 2em;
}
.page_view{
	max-width: 1024px;
	margin-left: auto;
	margin-right: auto;
}
</style>
</head>
<body class="oa_global">
	<div class="page_view">
	<h1 id="title" class="oa_nt_tit"></h1>
	<div class="oa_nt_edit">
		<table cellspacing="0" border="0" cellpadding="0" align="center">
			<tr>
				<td>发布人：<span id="sender_name"></span></td>
				<td>发布时间：<span id="send_time"></span></td>
			</tr>
		</table>
	</div>
	<div id="content" class="oa_nt_con"></div>
	</div>
</body>
</html>
