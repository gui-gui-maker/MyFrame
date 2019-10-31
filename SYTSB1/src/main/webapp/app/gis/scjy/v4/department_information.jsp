<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd"); 
	String nowTime=""; 
	nowTime = dateFormat.format(new Date());%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
var orgTreeManager = null;
var unitId ="<sec:authentication property="principal.unit.id" />";
var unitName = "<sec:authentication property="principal.unit.orgName" />";
$(function() {
	//页面布局
	$("#layout1").ligerLayout({
		leftWidth : 279,
		topHeight: 30,
		space: 5,
		allowTopResize : false,
		allowLeftCollapse : false,
		allowRightCollapse : false
	});
	//组织机构树
	orgTreeManager = $("#tree1").ligerTree({
		checkbox : false,
        iconFieldName : "level",
        iconParse : function(data){
        	if(data["level"]==0)
				return "k/kui/images/icons/16/home.png";
			else if (data["level"] == 1)
				return "k/kui/images/icons/16/org-2.png";
        	else
            	return "k/kui/images/icons/16/user.png";
        },
		data : [],
		attribute : [ "url" ],
		onSelect : function(node) {
			ztreeClick(node.data.id);
		}
	});
	 $.getJSON("rbac/org/getSubordinate.do?orgid=" + unitId,function(dataList){
		orgTreeManager.append(unitId,[{
			id : unitId,
			text : unitName,
			level : "0",
			children : dataList
		}]);
		orgTreeManager.selectNode(unitId);
	}); 
});

function ztreeClick(treeId){
	rightFrame.loadGridData(treeId);
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
<body>
<div id="layout1">
    <div position="left" title="组织机构" style="overflow:auto;">
			<ul id="tree1"></ul>
	</div>
	
	<div position="center" align="center" title="在职人员信息">
		<iframe id="rightFrame" name="rightFrame" src="app/gis/scjy/v4/employee_active.jsp?type=${param.type}" frameborder="0" 
			width="100%" height="100%" scrolling="no"></iframe>
	</div>
       </div>
</body>
</html>