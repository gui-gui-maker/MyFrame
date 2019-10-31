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
			onNodeCreated:function(event, treeId, treeNode){
				var treeObj = $.fn.zTree.getZTreeObj(treeId);
				if(treeNode.image!="null" && treeNode.image!="" && treeNode.image!=undefined){
					treeNode.icon = treeNode.image;
					treeObj.updateNode(treeNode)
				}
				if(treeNode.id=='root'){
					treeObj.selectNode(treeNode);
					$("#rightFrame").attr("src","pub/chart/chart/chart_list.jsp?typeId="+treeNode.id);
				}
			}
		}
};
$(function() {
	$("#layout1").ligerLayout({
		leftWidth : 250,
    	space : 5,
		allowLeftCollapse : false,
		allowRightCollapse : false
	});


		var treeData=<u:dict code="device_classify" />;
		ztree = $.fn.zTree.init($("#tree1"), setting, treeData);
	
	
});
function ztreeClick(event,treeId,treeNode){
	var win = $("#rightFrame").get(0).contentWindow.window;
	if(win.loadGridData) win.loadGridData(treeNode.id,treeNode.code);
	$("#typepic").attr('src',treeNode.icon)
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
		<div position="left" title="设备类别" class="overflow-auto">
		
			<ul id="tree1" class="ztree"></ul>
		</div>
		<c:if test='${param.type=="1"}'>
		<div position="center" align="center" title="设备列表">
			<iframe id="rightFrame" frameborder="0" name="userFrame" width="100%" height="100%" scrolling="no" src="app/device/device_list.jsp?mdy_drc=${param.mdy_drc}" /></iframe>
		</div>
		</c:if>
		<c:if test='${param.type=="2"}'>
		<div position="center" align="center" title="设备预警列表">
			<iframe id="rightFrame" frameborder="0" name="userFrame" width="100%" height="100%" scrolling="no" src="app/device/device_waring_list.jsp" /></iframe>
		</div>
		</c:if>
		<c:if test='${param.type=="3"}'>
		<div position="center" align="center" title="重点监控设备信息列表">
			<iframe id="rightFrame" frameborder="0" name="userFrame" width="100%" height="100%" scrolling="no" src="app/device/device_priority_list.jsp" /></iframe>
		</div>
		</c:if>
	</div>
</body>
</html>
