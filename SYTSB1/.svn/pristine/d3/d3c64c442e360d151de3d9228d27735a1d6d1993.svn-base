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
	var type = '${param.type}';
	//var area_code = '${param.area_code}';
	var treeData=<u:dict sql="select id,parent_id pid,regional_code code, regional_name text from BASE_ADMINISTRATIVE_DIVISIONS where parent_id='510100' or parent_id='510000' or id='540100' or id='650000' or id='652200' or id='513225'"/>;
	if('3' != type.substring(0,1)){
		if("2200" == type){
			treeData = <u:dict sql="select id,parent_id pid,regional_code code, regional_name text from BASE_ADMINISTRATIVE_DIVISIONS where parent_id='510100' or id='510000' or parent_id='510000' or id='340000' or parent_id='340000'"/>;
		}else if("7310" == type || "2220" == type){
			treeData = <u:dict sql="select id,parent_id pid,regional_code code, regional_name text from BASE_ADMINISTRATIVE_DIVISIONS where parent_id='510100' or id='510000' or parent_id='510000' or id='340000' or parent_id='340000' or regional_code like '%65%'"/>;
		}else{
			treeData = <u:dict sql="select id,parent_id pid,regional_code code, regional_name text from BASE_ADMINISTRATIVE_DIVISIONS where parent_id='510100' or parent_id='510000'"/>;
		}
	}/*else{
		if(area_code!=""){
			treeData = <u:dict sql="select id,parent_id pid,regional_code code, regional_name text from BASE_ADMINISTRATIVE_DIVISIONS where parent_id='${param.area_code}'"/>;
		}
	}*/
	ztree = $.fn.zTree.init($("#tree1"), setting, treeData);
	ztree.expandAll(true);
});

function ztreeClick(event,treeId,treeNode){	
	api.data.window.callArea(treeNode.id,treeNode.text);
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
		<div position="left" title="设备所在区域" class="overflow-auto">
		
			<ul id="tree1" class="ztree"></ul>
		</div>
		
		
</body>
</html>
