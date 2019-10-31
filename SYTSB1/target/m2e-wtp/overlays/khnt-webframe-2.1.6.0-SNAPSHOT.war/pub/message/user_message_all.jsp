<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>所有消息</title>
<%@include file="/k/kui-base-list.jsp"%>
<script test="text/javascript">
	var qmUserConfig = {
		sp_fields: [{
			name: "sender_name",
			compare:"like",
			labelWidth:60,
			columnWidth: 0.2
		},{
			name: "stxt",
			compare: "=",
			labelWidth: 60,
			value: "${param.all=='yes'?'':'2'}",
			xtype: "radioGroup",
			columnWidth: 0.25
		},{
			group: [{
				label: "发送时间",
				name: "send_time",
				compare: ">=",
				labelWidth: 60
			},{
				label: "到",
				labelWidth: 20,
				labelAlign: "center",
				name: "send_time",
				compare: ">="
			}]
		}],
		tbar:[
	        { text:'查看内容', id:'detail', icon:'detail', click: detail }, "-",
            { text:'标为已读', id:'mark', icon:'ok', click: markAsRead }, "-",
            { text:'全部标为已读', id:'markAll',icon:'check-more', click: markAllAsRead }
       	],
		listeners : {
			rowDblClick: detail,
			selectionChange: function() {
				var count = Qm.getSelectedCount();
				Qm.setTbar({
					detail: count==1,
					mark: count==1 && Qm.getValueByName("status")=="2"
				});
			},
			rowAttrRender : function(rowData) {
				if(rowData.status=="3")return "style='color:green'";
			}
		}
	};

	function detail(){
		winOpen({
			width: 550,
			height: 350,
			lock: true,
			parent: api,
			title: "查看消息",
			content: Qm.getValueByName("content")
		});
		if(Qm.getValueByName("status")=="2") markAsRead();
	}

	function markAsRead(){
		$.getJSON("pub/message/markRead.do?msId=" + Qm.getValueByName("id"),function(resp){
			if(resp.success){
				Qm.refreshGrid();
			}
		});
	}

	function markAllAsRead(){
		$.getJSON("pub/message/markAllRead.do",function(){
			Qm.refreshGrid();
		});
	}
</script>
<style type="text/css">
.l-grid-row-cell-inner{height:auto!important;white-space:normal!important;max-height:66px;}
</style>
</head>
<body>
	<qm:qm pageid="pub_message_user" singleSelect="true" />
</body>
</html>