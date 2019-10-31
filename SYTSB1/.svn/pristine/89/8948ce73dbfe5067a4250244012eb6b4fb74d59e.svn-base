<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/k/kui-base-list.jsp"%>
<title>人事管理</title>
<script type="text/javascript">
	var orgTreeManager;
	var unitId = "<sec:authentication property="principal.unit.id" />";
		var unitName = "<sec:authentication property="principal.unit.orgName" htmlEscape="false"/>";
		var levelCode = "<sec:authentication property="principal.unit.levelCode" />";
		<sec:authorize ifAnyGranted="sys_administrate,super_administrate">
			unitId = "";
		</sec:authorize>
	$(function() {
		$("#layout1").ligerLayout({
			leftWidth : 250,
			sapce : 5,
			allowLeftCollapse : false,
			allowRightCollapse : false
		});
		orgTreeManager = $("#tree1").ligerTree({
			checkbox : false,
			selectCancel : false,
            iconFieldName : "level",
            iconParse : function(data){
            	if(data["level"]==0)
               		return "k/kui/images/icons/16/home.png";
				else if (data["property"] == 'unit')
					return "k/kui/images/icons/16/org.png";
				else if (data["property"] == 'dep')
                	return "k/kui/images/icons/16/group.png";
				else
                	return "k/kui/images/icons/16/folders_explorer.png";
            },
			data : [],
			onSelect : function(node) {
				var win = $("#rightFrame").get(0).contentWindow.window;
				win.s_org_id = node.data.id;
				if(win.loadGridData) win.loadGridData(node.data.levelCode);
			},
            onBeforeExpand: function(node){
                if (node.data.children && node.data.children.length == 0)
					this.loadData(node.data,"rbac/org/getSubordinate.do?levelCode=" + node.data.levelCode + "&orgid=" + node.data.id);
            }
		});
		if(unitId==""){
			$.getJSON("rbac/org/getTopOrg.do",function(res){
				if(res.success){
					setOrgTree(res.data.id,res.data.orgName,res.data.levelCode)
				}else{
					$.ligerDialog.warn("获取机构信息出错！");
				}
			});
		}else{
			setOrgTree(unitId,unitName,levelCode);
		}
	});
	
	function setOrgTree(unitId,unitName,levelCode){
        $("body").mask("正在加载数据，请稍候....");
		$.getJSON("rbac/org/getSubordinate.do?orgid=" + unitId,function(dataList){
			orgTreeManager.append(unitId,[{
				id : unitId,
				text : unitName,
				level : "0",
				levelCode : levelCode,
				children : dataList
			}]);
            $("body").unmask();
			orgTreeManager.selectNode(unitId);
			$("#rightFrame").attr("src","pub/rbac/employee_list.jsp?levelCode=" + levelCode);
		});
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
		<div position="left" title="组织机构" class="overflow-auto">
			<ul id="tree1"></ul>
		</div>
		<div position="center">
			<iframe id="rightFrame" frameborder="0" name="userFrame" width="100%" height="100%" scrolling="no" src="" /></iframe>
		</div>
	</div>
</body>
</html>
