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
			{label:"公式名称",name:"name",compare:"like"}
// 			{label:"中文描述",name:"formula_desc",compare:"like"}
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
		var node = parent.treeManager.getSelected();
		if(!node){
			$.ligerDialog.alert("请从左边树中选择公式类别！");
			return;
		}
		top.$.dialog({
			width : 900,
			height : 500,
			lock : true,
			data : {
				window : window
			},
			title : "新增公式",
			content : 'url:pub/formula/formula_editor.jsp?status=add&type='+node.data.id
		});
	}

	function edit() {
		top.$.dialog({
			width : 900,
			height : 500,
			lock : true,
			data : {
				window : window
			},
			title : "修改公式——" + Qm.getValueByName("name"),
			content : 'url:pub/formula/formula_editor.jsp?status=modify&id=' + Qm.getValueByName("id") + "&type=" + Qm.getValueByName("type_code")
		});
	}

	function detail() {
		top.$.dialog({
			width : 700,
			height : 400,
			lock : true,
			title : "公式详情——" + Qm.getValueByName("name"),
			content : 'url:pub/formula/formula_detail.jsp?id=' + Qm.getValueByName("id"),
			cancel : true
		});
	}

	function del() {
		$.del("确定要删除选中的数据吗？删除后不可恢复！", "pub/formula/delete.do", {
			"ids" : Qm.getValuesByName("id").toString()
		});
	}
	
	function submitAction(condition) {
		if (condition)
			Qm.setCondition(condition);
		Qm.refreshGrid();
	}
</script>
</head>
<body>
	<q:qm pageid="formula_02">
		<qm:param name="type_code" compare="in" value="${param.formulaType }"  dataType="user" />
	</q:qm>
</body>
</html>