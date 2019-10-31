<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>机构人员列表</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
	var qmUserConfig = {
		sp_fields: [ {
			label: "姓名",
			name: "name",
			compare: "like",
			labelAlign: "right",
			labelWidth: 40,
			width: 100
		},{
			name: "Status",
			compare: "=",
			value: "1",
			width: 100
		}],
		listeners : {
			onAfterShowData : function() {
				initGridSelectRange();
			},
			<c:if test="${param.multiple}">
			onCheckRow : function(checked, rowdata, rowindex, rowDomElement){
				if(checked) 
					parent.addChoosedItem(rowdata.id,rowdata.name);
				else
					parent.removeChoosedItem(rowdata.id);
			},
			rowAttrRender:function(rowData,rowIndex){
				if(rowData.Status=="否"){
					  return 'style="color:#e6640d;"';
				}
			},
			onCheckAllRow:function(checked,row){
				var data = Qm.getQmgrid().getData();
				for(var i in data){
					if(checked)
						parent.addChoosedItem(data[i].id,data[i].name);
					else
						parent.removeChoosedItem(data[i].id);
				}
			}
			</c:if>
			<c:if test="${!param.multiple}">
			onSelectRow: function(rowdata, rowindex, rowDomElement){
				parent.addChoosedItem(rowdata.id,rowdata.name);
			},
			onUnSelectRow: function(rowdata, rowindex, rowDomElement){
				parent.removeChoosedItem(rowdata.id);
			}</c:if>
		}
	};
	
	function loadGridData(levelCode) {
		Qm.config.defaultSearchCondition[0].value = levelCode;
		Qm.searchData();
	}
	
	//表格渲染时，将被选择的角色勾选
	function initGridSelectRange(){
		var crt = parent.chooseResult();
		if(!crt) return;
		var idRange = crt.code.split(",");
		Qm.getQmgrid().selectRange("id", idRange);
	}
</script>
</head>
<body>
	<qm:qm pageid="user_sel" singleSelect="${!param.multiple}" pagesize="100">
		<qm:param name="level_code" value="${param.levelCode}" compare="llike" />
	</qm:qm>
</body>
</html>
