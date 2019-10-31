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
	var org_id = '${param.org_id}';
	var treeData=<u:dict sql="select t.id code,t.name text from employee t,sys_user u,sys_org o where t.org_id = o.id and t.id=u.employee_id and u.status='1' order by u.sort asc"/>;

	if(org_id!=""){
		if(org_id=="100091"){
			treeData = <u:dict sql="select t.id, t.pid, t.code, t.text from (select o.id as id,o.id as code, o.org_code  as tcode, o.ORG_NAME as text,o.PARENT_ID as pid from sys_org o union select e.id as id, e.id as code, e.code as tcode, e.NAME as text, e.ORG_ID as pid from employee e union select e2.id as id, e2.id as code,e2.code as tcode, e2.NAME as text, p2.ORG_ID as pid from employee e2, SYS_EMPLOYEE_POSITION p1, SYS_POSITION p2 where p1.position_id = p2.id and p1.employee_id = e2.id and p2.org_id = '100091') t start with t.id = '100091' connect by t.pid = prior t.id ORDER BY T.TCODE"/>;
		}else{ 
			treeData = <u:dict sql="select t.id code,t.name text from employee t,sys_user u where t.org_id = '${param.org_id}' and t.id=u.employee_id and u.status='1' order by u.sort asc"/>;
		}
	}
	
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
