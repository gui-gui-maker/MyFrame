<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>短信接收</title>
<%@include file="/k/kui-base-list.jsp"%>
<script test="text/javascript">
	var qmUserConfig = {
		sp_defaults : {
			labelWidth : 70
		},
		sp_fields : [
			{name:"phone_num", compare:"like"},
			{name:"content", compare:"like"},
			{group:[{
				label:"发送时间从", labelWidth:90,
				name:"send_time", compare:">="
			},{
				label:"到", labelWidth:20, labelAlign:"center",
				name:"send_time", compare:">="
			}]}
		],
		tbar : [{
			text : '同步短信',
			id : 'sync',
			icon : 'refresh',
			click : doSync
		}],
		listeners : {
			rowAttrRender: function (rd, rowid) {
				rd.content = "<a href='javascript:showMsg(\"" + rd.content + "\")' title='点击查看'> " + rd.content + " </a>";
			}
		}
	};
	function showMsg(mtext){
		top.$.dialog({
			width : 350,
			height : 150,
			cancel : true,
			parent : api,
			title : "查看短信",
			content : "<textarea style='width:350px;height:150px;line-height:1.5em;'>" + mtext + "</textarea>"
		});
	}
	
	function doSync() {
		$("body").mask("正在同步中。。。");
		$.getJSON("pub/message/sms/doSync.do",function(response){
			if(response.success){
				top.$.notice("同步成功！",3);
				Qm.refreshGrid();
			}
			else{
				$.ligerDialog.error("同步失败！<br/>"+response.msg);
			}
			$("body").unmask();
		})
	}
</script>
</head>
<body>
	<qm:qm pageid="pub_message_sms" singleSelect="true" />
</body>
</html>