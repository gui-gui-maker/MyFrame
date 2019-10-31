<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>报告配置</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">
	var qmUserConfig = {
		sp_defaults:{columnWidth:0.45,labelAlign:'right',labelSeparator:'',labelWidth:100},	// 默认值，可自定义
		sp_fields:[
			{name:"gysmc",compare:"like",value:""},
			{name:"lxr",compare:"like",value:""}
		],
	    listeners: {
	    	selectionChange : function(rowData,rowIndex){
	    		
	      	}
		}
	};

	function loadGridData(nodeId,unitId,url){
		Qm.searchData();
	}
	
	function submitAction(o) {
		Qm.refreshGrid();
	}
	function getSelected(){
		if(Qm.getSelectedCount()!=1){
			$.ligerDialog.alert('提示：选择一行！');
            return null;
        }else{
            return {
                id: Qm.getValueByName("id"),
                gysmc:Qm.getValueByName("gysmc"),
                lxr:Qm.getValueByName("lxr")
            };
        }
	}
	function f_select()
    { 
		var rows = Qm.getQmgrid().getSelectedRows();
        return rows; 
    }
</script>
</head>
<body>
	<qm:qm pageid="ch_gys" script="false" singleSelect="true">
	</qm:qm>
</body>
</html>