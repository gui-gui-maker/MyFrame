<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/k/kui-base-list.jsp"%>
<title>质量信息</title>
<script type="text/javascript">
var ztree;
var zNodes;
var setting = {
		data: {key: {name: "text",url:""}
		},callback: {
			onClick: ztreeClick,
			onNodeCreated:function(event, treeId, treeNode){
				var treeObj = $.fn.zTree.getZTreeObj(treeId);
				if(treeNode.image!="null" && treeNode.image!="" && treeNode.image!=undefined){
					treeNode.icon = treeNode.image;
					treeObj.updateNode(treeNode)
				}
				if(treeNode.id=='zltxfl'){
					treeObj.selectNode(treeNode);
					$("#rightFrame").attr("src","app/qualitymanage/qualityFiles_list.jsp?file_type="+treeNode.id);
				}
			}
		}
};
$(function() {
	$("#layout1").ligerLayout({
		leftWidth : 250,
    	space : 5,
		allowLeftCollapse : true,
		allowRightCollapse : false
	});
		var treeData=<u:dict code="tzsb_quality_classify" />;
		ztree = $.fn.zTree.init($("#tree1"), setting, treeData);
});
function ztreeClick(event,treeId,treeNode){
	var win = $("#rightFrame").get(0).contentWindow.window;
	if(win.loadGridData){
		if(treeNode.id=='zltxfl'){
			win.loadGridData(null,treeNode.text);
		}else{
			win.loadGridData(treeNode.id,treeNode.text);
		}
		
	}
	$("#typepic").attr('src',treeNode.icon)
}
function refreshGrid() {
    Qm.refreshGrid();
}
function close(){
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
		<div position="left" title="质量体系文件" class="overflow-auto">
		
			<ul id="tree1" class="ztree"></ul>
		</div>
		<div position="center" align="center" title="文件列表">
			<iframe id="rightFrame" frameborder="0" name="userFrame" width="100%" height="100%" scrolling="no" src="app/qualitymanage/qualityFiles_list.jsp" /></iframe>
		</div>
	</div>
</body>
</html>
