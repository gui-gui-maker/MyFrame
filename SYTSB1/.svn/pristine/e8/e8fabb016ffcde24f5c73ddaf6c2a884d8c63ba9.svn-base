<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/k/kui-base-list.jsp"%>
<title>部门人员信息</title>
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
		leftWidth : 190,
    	space : 5,
    	allowLeftCollapse : false,
		allowRightCollapse : false
	});

	var treeData=<u:dict sql="select t.id code,t.name text from sys_user t where t.org_id = '${param.org_id}' and t.status='1' order by t.sort asc"/>;	
	ztree = $.fn.zTree.init($("#tree1"), setting, treeData);
	ztree.expandAll(true);
});

function ztreeClick(event,treeId,treeNode){
	api.data.window.callUser(treeNode.id,treeNode.text);
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
		<div position="left" title="部门人员" class="overflow-auto">
			<ul id="tree1" class="ztree"></ul>
		</div>
	</div>
</body>
</html>
