<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>选择子流程流程</title>
		<%@include file="/k/kui-base-list.jsp"%>
		<script type="text/javascript">
        function getSelected(){
            var count=Qm.getSelectedCount();
            if(count!=1){
            	$.ligerDialog.warn("请选择一个流程！");
            	return null;
            }
        	var fid = Qm.getValueByName("id");
        	var fname = Qm.getValueByName("flowname");
        	return {id:fid,name:fname};
        }
	</script>
	</head>
	<body class="p0">
		<qm:qm pageid="bpm_3" singleSelect="true" />
	</body>
</html>
