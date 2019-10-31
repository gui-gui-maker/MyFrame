<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>demo-list</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
	var qmUserConfig = {
		sp_defaults: {columnWidth: 0.5, labelAlign: 'right', labelSeparator: '', labelWidth: 80, layout: "column"},//可以自己定义 layout:column,float
		sp_fields: [ 
			{group: [
				{name: "tag_code", compare: ">="},
				{label: "到", name: "tag_code", compare: "<=", labelAlign: "center"}
			]},
			{group: [
				{name: "sale_time", compare: ">="},
				{label: "到", name: "sale_time", compare: "<=", labelAlign: "center", labelWidth: 20}
			]}
		],
		tbar: [ {
			text: '详情', id: 'detail', icon: 'detail', click: detail
		}, "-", {
			text: '设置产品信息', id: 'set', icon: 'add', click: set
		}, "-", {
			text: '修改', id: 'edit', icon: 'modify', click: edit
		}, "-", {
			text: '删除', id: 'del', icon: 'delete', click: del
		}, "-", {
			text: '标签导入', id: 'imp', icon: 'excel-import', click: impbean
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
					set: count>0,
					del: count>0
				});
			}
		}
	};
	function impbean() {
		top.$.dialog({
			width: 450,
			height: 250,
			lock: true,
			title: "EXCEL数据导入",
			content: "url:pub/expimp/import.jsp?cfg=rfid_imp",
			data: {callback:impcallback}
		});
	}
	function impcallback(){
		Qm.refreshGrid();
	}
	function set() {
		top.$.dialog({
			width: 600,
			height: 350,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "产品信息设置",
			content: 'url:app/rfid/product_set.jsp?pageStatus=add&ids='+Qm.getValuesByName("id")
		});
	}
	function edit() {
		var id = Qm.getValueByName("id");
		top.$.dialog({
			width: 600,
			height: 420,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "修改",
			content: 'url:app/rfid/product_detail.jsp?id=' + id + '&pageStatus=modify'
		});
	}
	function detail() {
		var id = Qm.getValueByName("id");
		top.$.dialog({
			width: 600,
			height: 420,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "详情",
			content: 'url:app/rfid/product_detail.jsp?id=' + id + '&pageStatus=detail'
		});
	}
	function del() {
		$.del(kFrameConfig.DEL_MSG, "product/delete.do", {
			"ids": Qm.getValuesByName("id").toString()
		});
	}
</script>
</head>
<body>
	<qm:qm pageid="rfid_01"></qm:qm>
</body>
</html>