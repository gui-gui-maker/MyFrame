<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/k/kui-base-list.jsp"%>
<title>设备信息</title>
<script type="text/javascript">
var ztree;
var zNodes;
var setting = {
		data: {
			key: {
				name: "text",
				url:""
			}
		},callback: {
			onClick: ztreeClick,
			
		}
};
$(function() {
	$("#layout1").ligerLayout({
		leftWidth : 250,
    	space : 5,
    	allowLeftCollapse : false,
		allowRightCollapse : false
	});
		
	   
   
		var treeData=<u:dict sql="select t.id,t.code_table_values_id pid,t.value code,t.name text from PUB_CODE_TABLE_VALUES t,pub_code_table t1 where t.code_table_id=t1.id and   t1.code = 'device_classify'  and t.value like '${param.type}%' order by t.value"/>;
		if('${param.category}'=='7310'){
			treeData=<u:dict sql="select t.id,t.code_table_values_id pid,t.value code,t.name text from PUB_CODE_TABLE_VALUES t,pub_code_table t1 where t.code_table_id=t1.id and   t1.code = 'device_classify'  and t.value like '7310%' "/>;
		}
		
		
		ztree = $.fn.zTree.init($("#tree1"), setting, treeData);
		
		
		ztree.expandAll(true);
	
});
function ztreeClick(event,treeId,treeNode){
	
	api.data.window.callOK(treeNode.id,treeNode.text);

	api.close();

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
<body class="p5">
	<div id="layout1">
		<div position="left" title="设备名称" class="overflow-auto">
		
			<ul id="tree1" class="ztree"></ul>
		</div>
		
		
</body>
</html>
