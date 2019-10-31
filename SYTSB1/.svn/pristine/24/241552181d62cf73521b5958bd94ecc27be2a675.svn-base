<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>已出库的设备箱</title>

<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="app/common/js/render.js"></script>
<script type="text/javascript">
	
	function selectValue(){
		api.data.parentWindow.callBack(Qm.getValueByName("id"));
		api.close();
	}
	
	function submitAction() {
   		Qm.refreshGrid();
   	}

   	function close(){	
   		 api.close();
   	}
   	function f_select(){ 
   		var rows = Qm.getQmgrid().getSelectedRows();
        return rows; 
    } 
    
    // 刷新Grid
    function refreshGrid() {
        Qm.refreshGrid();
    }
	
</script>

</head>
<body>
	<qm:qm pageid="TJY2_EQUIPMENT_BOX1" singleSelect="false"></qm:qm>
	
</body>
</html>