<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="main_head">
<%@include file="/k/kui-base-list.jsp"%>
<title>用户授权管理</title>
<script type="text/javascript">
	var orgTreeManager = null;
	var unitId = "<sec:authentication property="principal.department.id" />";
		var unitName = "<sec:authentication property="principal.department.orgName" htmlEscape="false"/>";
		<sec:authorize access="hasRole('unit_administrate')">
			unitId = "<sec:authentication property="principal.unit.id" />";
			unitName = "<sec:authentication property="principal.unit.orgName" htmlEscape="false"/>";
		</sec:authorize>
		<sec:authorize ifAnyGranted="sys_administrate,super_administrate">
			unitId = "";
		</sec:authorize>
	$(function() {
		$("#layout1").ligerLayout({
			leftWidth : 250,
        	space : 5,
			allowLeftCollapse : false,
			allowRightCollapse : false
		});
		orgTreeManager = $("#tree1").ligerTree({
			checkbox : false,
            iconFieldName : "level",
            iconParse : function(data){
            	if(data["level"]==0)
               		return "k/kui/images/icons/16/home.png";
            	else if(data["level"]==1)
					return "k/kui/images/icons/16/org.png";
            	else
                	return "k/kui/images/icons/16/group.png";
            },
			data : [],
			onSelect : function(node) {
				var win = $("#rightFrame").get(0).contentWindow.window;
				if(win.loadUserList) win.loadUserList(node.data.id);
			},
            onBeforeExpand: function(node){
                if (node.data.children && node.data.children.length == 0)
					this.loadData(node.data,"rbac/org/getSubordinate.do?orgid=" + node.data.id);
            }
		});
		if(unitId==""){
			$.getJSON("rbac/org/getTopOrg.do",function(res){
				if(res.success){
					setOrgTree(res.data.id,res.data.orgName)
				}else{
					$.ligerDialog.warn("获取机构信息出错！");
				}
			});
		}else{
			setOrgTree(unitId,unitName);
		}
	});
	function setOrgTree(unitId,unitName){
        $("body").mask("正在加载数据，请稍候....");
		$.getJSON("rbac/org/getSubordinate.do?orgid=" + unitId,function(dataList){
			orgTreeManager.append(unitId,[{
				id : unitId,
				text : unitName,
				level : "0",
				children : dataList
			}]);
            $("body").unmask();
			$("#rightFrame").attr("src","pub/rbac/user_Authorized_list.jsp");
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
			<iframe id="rightFrame" frameborder="0" src="" 
				name="rightFrame" width="100%" height="100%" scrolling="no"></iframe>
		</div>
	</div>
</body>
</html>
