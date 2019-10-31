<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://khnt.com/tags/qm" prefix="q"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>公式管理</title>
<%@include file="/k/kui-base-list.jsp"%>
<script test="text/javascript">
	var qmUserConfig = {
		sp_defaults : {
			labelWidth : 70
		},
		sp_fields : [
			{label:"业务名称",name:"service_name",compare:"like"},
			{label:"公式名称",name:"name",compare:"like"}
		],
		tbar : [ {
			text : '详情',
			id : 'detail',
			icon : 'detail',
			click : detail
		}, "-", {
			text : '新增',
			id : 'add',
			icon : 'add',
			click : add
		}, "-", {
			text : '修改',
			id : 'edit',
			icon : 'modify',
			click : edit
		}, "-", {
			text : '删除',
			id : 'del',
			icon : 'delete',
			click : del
		} ],
		listeners : {
			rowDblClick : function(rowData, rowIndex) {
				detail();
			},
			selectionChange : function(rowData, rowIndex) {
				var count = Qm.getSelectedCount();
				Qm.setTbar({
					detail : count == 1,
					edit : count == 1,
					del : count >= 1 
				});
			}
		}
	};

	function add() {
		top.$.dialog({
			width : 600,
			height : 250,
			lock : true,
			data : {
				window : window
			},
			title : "新增配置",
			content : "url:pub/formula/formula_service_config_detail.jsp?status=add"
		});
	}

	function edit() {
		top.$.dialog({
			width : 600,
			height : 250,
			lock : true,
			data : {
				window : window
			},
			title : "修改配置",
			content : 'url:pub/formula/formula_service_config_detail.jsp?status=modify&id=' + Qm.getValueByName("id")
		});
	}

	function detail() {
		top.$.dialog({
			width : 600,
			height : 250,
			lock : true,
			title : "公式详情",
			content : 'url:pub/formula/formula_service_config_detail.jsp?id=' + Qm.getValueByName("id"),
			cancel : true
		});
	}

	function del() {
		$.del("确定要删除选中的数据吗？删除后不可恢复！", "pub/formula/serviceConfig/delete.do", {
			"ids" : Qm.getValuesByName("id").toString()
		});
	}
	
	function submitAction() {
		Qm.refreshGrid();
	}
</script>
</head>
<body>
	<q:qm pageid="formula_03"></q:qm>
</body>
</html>