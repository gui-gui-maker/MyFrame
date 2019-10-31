<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
	var qmUserConfig = {
		
		tbar: [ {
			text: '详情', id: 'detail', icon: 'detail', click: detail
		}, "-", {
			text: '新增', id: 'add', icon: 'add', click: add
		}, "-", {
			text: '修改', id: 'edit', icon: 'modify', click: edit
		}, "-", {
			text: '删除', id: 'del', icon: 'delete', click: del
		}/*, "-", {
			text: '启用', id: 'qishi', icon: 'modify', click: qishi
		}*/],
		listeners: {
			rowClick: function(rowData, rowIndex) {
			},
			rowDblClick: function(rowData, rowIndex) {
				detail();
			},
			selectionChange: function(rowData, rowIndex) {
				var count = Qm.getSelectedCount();
				Qm.setTbar({
					detail: count==1,
					edit: count==1,
					qishi: count==1,
					del: count>0
				});
			}
		}
	};
	function qishi(){
		var id = Qm.getValueByName("id");
		var up_status = Qm.getValueByName("state");
        $.ligerDialog.confirm('是否要启用？', function (yes){
            top.$.ajax({
                 url: "mobileMessage/nk/qiyong.do?id="+id,
                 type: "GET",
                 dataType:'json',
                 async: false,
                 success:function (data) {
                	 if("启用"==up_status){
                		 $.ligerDialog.warn("此条已启用！");
                	 }else{
	                	 if(data.success){
	                         $.ligerDialog.success("启用成功！");
	                         Qm.refreshGrid();//刷新
	                     }else{
	                         $.ligerDialog.warn(data.msg);
	                     }
                	 }
                 },
                 error:function () {
                     $.ligerDialog.warn("启用失败！");
                 }
             });
        });
	}
	function add() {
		top.$.dialog({
			width: 600,
			height: 350,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "新增",
			content: 'url:app/message/mobile_message_datail.jsp?pageStatus=add'
		});
	}
	function edit() {
		var id = Qm.getValueByName("id");
		top.$.dialog({
			width: 600,
			height: 350,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "修改",
			content: 'url:app/message/mobile_message_datail.jsp?id=' + id + '&pageStatus=modify'
		});
	}
	function detail() {
		var id = Qm.getValueByName("id");
		top.$.dialog({
			width: 600,
			height: 350,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "详情",
			content: 'url:app/message/mobile_message_datail.jsp?id=' + id + '&pageStatus=detail'
		});
	}
	function del() {
		$.del(kFrameConfig.DEL_MSG, "mobileMessage/nk/delete.do", {
			"ids": Qm.getValuesByName("id").toString()
		});
	}
</script>
</head>
<body>
	<qm:qm pageid="TJY2_NK_MESSAGE"></qm:qm>
</body>
</html>