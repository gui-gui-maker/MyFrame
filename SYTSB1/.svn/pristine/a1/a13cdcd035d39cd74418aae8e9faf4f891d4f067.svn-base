<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%
	String sql = "select 'all' id, '' pid, 'all' code, '全部' text from OA_CAR_INFO where  rownum = 1 "
		      +"union all "
		      +"select type id, 'all' pid,type code, case when type ='1' then '一类车辆'  "
		      +"when type ='2' then '二类车辆'   "
		      +"when type ='3' then '三类车辆'   "
		      +"when type ='4' then '其他类车辆'  else '' end  "
		      +"text from (select type from OA_CAR_INFO where type is not null group by type order by type)  "
		      +"union all "
		      +"select id, '1' pid, id code, car_num text from OA_CAR_INFO where type ='1' "
		      +"union all "
		      +"select id, '2' pid, id code, car_num text from OA_CAR_INFO where type ='2' "
		      +"union all "
		      +"select id, '3' pid, id code, car_num text from OA_CAR_INFO where type ='3' "
		      +"union all "
		      +"select id, '4' pid, id code, car_num text from OA_CAR_INFO where type ='4' ";
%>
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
	var treeData=<u:dict sql="<%=sql%>" />;
	ztree = $.fn.zTree.init($("#tree1"), setting, treeData);
});
function ztreeClick(event,treeId,treeNode){
	var win = $("#rightFrame").get(0).contentWindow.window;
	if(win.loadGridData) win.loadGridData(treeNode.id,treeNode,treeNode.getParentNode());
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
		<div position="left" title="车辆列表" class="overflow-auto">
			<ul id="tree1" class="ztree"></ul>
		</div>
		<c:if test='${param.pageType=="wbRecord"}'>
			<div position="center" align="center" title="车辆维保记录" >
			<iframe id="rightFrame" frameborder="0" name="rightFrame" width="100%" height="100%" scrolling="no" src="app/car/car_wb/pc/carWb_record_list.jsp" /></iframe>
			</div>
		</c:if>
	</div>
</body>
</html>
