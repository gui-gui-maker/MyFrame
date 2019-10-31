<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/k/kui-base-list.jsp"%>
<title>选择机构</title>
<script type="text/javascript">
var ztree;
var zNodes;
var result;
$(function(){
	 var errorStatus=<u:dict code="error_status"/>; 
	 $("#orgTree").ligerTree(
			            {
			                data:errorStatus,  
			                  nodeWidth : 400,
			                  selectCancel : false,
			                  iconFieldName : "level",
			                  checkbox:false,
			                  onSelect:getSelect
			             });
});
function getSelect(data){

	 result = {id:data.data.id,text:data.data.text};
	
}
function getSelectResult(){
	return result;
}
</script>
<style type="text/css">
    .l-tree .l-tree-icon-none img {
        height: 16px;
        margin: 3px;
        width: 16px;
    }
</style>
</head>
<body style="overflow:auto">
    <ul id="orgTree" class="ztree"></ul>
</body>
</html>
