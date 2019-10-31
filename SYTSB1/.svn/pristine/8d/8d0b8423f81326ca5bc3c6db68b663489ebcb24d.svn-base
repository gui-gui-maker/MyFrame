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
			{label:"公式名称",name:"name",compare:"like"},
			{label:"中文描述",name:"formula_desc",compare:"like"}
		],
		tbar : [ {
			text : '详情',
			id : 'detail',
			icon : 'detail',
			click : detail
		}],
		listeners : {
			rowDblClick : function(rowData, rowIndex) {
				detail();
			},
			selectionChange : function(rowData, rowIndex) {
				var count = Qm.getSelectedCount();
				Qm.setTbar({
					detail : count == 1
				});
			}
		}
	};

	function detail() {
		top.$.dialog({
			width : 700,
			height : 400,
			lock : true,
			parent : parent.api,
			title : "公式详情——" + Qm.getValueByName("name"),
			content : 'url:pub/formula/formula_detail.jsp?id=' + Qm.getValueByName("id"),
			cancel : true
		});
	}
	
	//选择返回一个公式
	function chooseFormula() {
		var id = Qm.getValueByName("id");
		if(id){
			var name = Qm.getValueByName("name");
			var formula = Qm.getValueByName("formula");
			var formulaDesc = Qm.getValueByName("formula_desc");
			return {
				'id' : id,
				'name' : name,
				'formula' : formula,
				'formulaDesc' : formulaDesc
			};
		}
		else{
			$.ligerDialog.alert("您还未选择一个公式！");
			return null;
		}
	}
</script>
</head>
<body>
	<q:qm pageid="formula_02" singleSelect="true">
		<q:param name="type_code" value="${param.type}" compare="=" />
	</q:qm>
</body>
</html>