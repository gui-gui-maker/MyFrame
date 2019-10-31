<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://khnt.com/tags/qm" prefix="q"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>可选择项目管理列表页面</title>
<%@include file="/k/kui-base-list.jsp"%>
<script test="text/javascript">
	var qmUserConfig = {
		sp_defaults : {
			labelWidth : 70
		},
		sp_fields : [ {
			label : "项目名称",
			name : "variable_name",
			compare : "like"
		}, {
			label : "变量名",
			name : "variable",
			compare : "like"
		}],
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
					detail : count==1,
					edit : count==1,
					del : count>=1
				});
			}
		}
	};

	function add() {
		var node = parent.treeManager.getSelected();
		if(!node){
			$.ligerDialog.alert("请从左边树中选择公式类别！");
			return;
		}
		top.$.dialog({
			width : 500,
			height : 230,
			lock : true,
			data : {
				window : window
			},
			title : "新增",
			content : 'url:pub/formula/formula_item_detail.jsp?pageStatus=add&typeCode=' + node.data.id
		});
	}

	function edit() {
		top.$.dialog({
			width : 500,
			height : 230,
			lock : true,
			data : {
				window : window
			},
			title : "修改",
			content : 'url:pub/formula/formula_item_detail.jsp?pageStatus=edit&id='
					+ Qm.getValueByName("id")
		});
	}

	function detail() {
		top.$.dialog({
			width : 500,
			height : 230,
			lock : true,
			data : {
				window : window
			},
			title : "详情",
			content : 'url:pub/formula/formula_item_detail.jsp?pageStatus=detail&id='
					+ Qm.getValueByName("id")
		});
	}

	function del() {
		$.del("确定要删除选中的数据吗？删除后不可恢复！", "pub/formula/item/delete.do", {
			"ids" : Qm.getValuesByName("id").toString()
		});
	}
	
	function submitAction(condition) {
		if (condition)
			Qm.setCondition(condition);
		Qm.refreshGrid();
	}
</script>
<style type="text/css">
.l-layout-center{border:none}
</style>
</head>
<body class="p0">
	<input type="hidden" id="type_code" />
	<q:qm pageid="formula_01" singleSelect="true">
		<qm:param name="type_code" compare="in" value="${param.formulaType }"  dataType="user" />
	</q:qm>
</body>
</html>