<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/k/kui-base-list.jsp"%>
<title>选择机构</title>
<script type="text/javascript">
var ztree;
var zNodes;
$(function(){
	var unitId = ${param.type=='00'}?'':"<sec:authentication property="principal.unit.id" />";
	if(${param.type=='000'}){
		unitId = "<sec:authentication property="principal.department.id" />";
		<sec:authorize access="hasRole('unit_administrate')">
			unitId = "<sec:authentication property="principal.unit.id" />";
		</sec:authorize>
	}
	<sec:authorize ifAnyGranted="sys_administrate,super_administrate">
		unitId = '';
	</sec:authorize>
    var setting = {
        data: {
            key: {
                name: "text"
            }
        },callback: {
    		onClick: ztreeClick,
    	}
//     ,callback: {
//             onNodeCreated:function(event, treeId, treeNode){
//                 var treeObj = $.fn.zTree.getZTreeObj(treeId);
//                 if(treeNode.level=="0"){
//                     treeNode.icon = "k/kui/images/icons/16/home.png";
//                 }
//                 else if(treeNode.level=='1'){
//                     treeNode.icon = "k/kui/images/icons/16/org.png";
//                 }
//                 else{
//                     treeNode.icon = "k/kui/images/icons/16/group.png";
//                 }
//             }
//         }
    };
	$.getJSON("rbac/org/orgTree.do?orgid=" + unitId,function(dataList){
		
		var strData = JSON.stringify(dataList);
		strData = strData.replace(/isexpand/g, "open");
		zNodes = eval(strData)
		ztree = $.fn.zTree.init($("#orgTree"), setting, zNodes);
		
       // ztree = $.fn.zTree.init($("#orgTree"), setting, dataList);
        //ztree.expandAll(true);
	}); 
});
function getSelectResult(){
	var isCheckBox = ${param.checkbox=='1'};
	var nodes = (isCheckBox?ztree.getCheckedNodes():ztree.getSelectedNodes());
	if(!nodes){
		top.$.dialog.alert("您没有选择任何节点！");
		return null;
	}
	var result = {code:"",name:""};
	$.each(nodes,function(i,el){
		result.code += (i==0?"":",") + this.id;
		result.name += (i==0?"":",") + this.text;
	});
	return result;
}

function ztreeClick(event,treeId,treeNode){
	api.data.window.callUnit(treeNode.id,treeNode.text);
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
<body style="overflow:auto">
    <ul id="orgTree" class="ztree"></ul>
</body>
</html>
