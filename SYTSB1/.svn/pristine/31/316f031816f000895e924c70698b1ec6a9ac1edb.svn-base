<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>demo-list</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
	var qmUserConfig = {
		sp_fields: [ 
			{label: "字符：",name: "str_val",compare: "like", labelWidth: 50}, 
			{group: [
				{label: "数字从", name: "int_val", compare: ">=", labelWidth: 50},
				{label: "到", name: "int_val", compare: "<=", labelAlign: "center", labelWidth: 20}
			]},
			{group: [
				{label: "日期从", name: "date_val", compare: ">=", labelWidth: 50},
				{label: "到", name: "date_val", compare: "<=", labelAlign: "center", labelWidth: 20}
			]}
		],
		tbar: [ {
			text: '详情', id: 'detail', icon: 'detail', click: detail
		}, "-", {
			text: '新增', id: 'add', icon: 'add', click: add
		}, "-", {
			text: '修改', id: 'edit', icon: 'modify', click: edit
		}, "-", {
			text: '删除', id: 'del', icon: 'delete', click: del
		}, "-", {
			text: '示例说明', id: 'note', icon: 'help', click: note
		}],
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
					del: count>0
				});
			}
		}
	};
	function note(){
		top.$.dialog({
			width: 500,
			height: 200,
			lock: true,
			parent: api,
			title: "示例说明",
			content: $("#note").html()
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
			content: 'url:demo/crud/demo_detail.jsp?pageStatus=add'
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
			content: 'url:demo/crud/demo_detail.jsp?id=' + id + '&pageStatus=modify'
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
			content: 'url:demo/crud/demo_detail.jsp?id=' + id + '&pageStatus=detail'
		});
	}
	function del() {
		$.del(kFrameConfig.DEL_MSG, "demo/crud/delete.do", {
			"ids": Qm.getValuesByName("id").toString()
		});
	}
</script>
</head>
<body>
	<qm:qm pageid="demo_crud_list"></qm:qm>
	<div style="display:none" id="note">
		<p>本例子演示了一个简单的CRUD（增删改查）业务，涉及的相关资源如下：</p>
		<ul>
			<li>list页面jsp：demo/crud/demo_list.jsp</li>
			<li>detail页面jsp：demo/crud/demo_detail.jsp</li>
			<li>java包：demo.crud，下面分为四个子包：bean、dao、service、web</li>
		</ul>
	</div>
</body>
</html>