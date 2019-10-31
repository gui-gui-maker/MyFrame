<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/k/kui-base-list.jsp"%>
<title>公共通讯录</title>
<script type="text/javascript">
	var orgTreeManager;
	var addressListId;
	$(function(){
		$("#layout1").ligerLayout({
			leftWidth : 250,
			sapce : 5,
			allowLeftCollapse : true,
			allowRightCollapse : false
		});
		var unitId = "<sec:authentication property="principal.unit.id" />";
		var unitName = "<sec:authentication property="principal.unit.orgName" />";
		var levelCode = "<sec:authentication property="principal.unit.levelCode" />";
		orgTreeManager = $("#tree1").ligerTree({
			checkbox : false,
			selectCancel : false,
            iconFieldName : "level",
            url:'rbac/pubAddressType/addressListTreePublic.do',
            iconParse : function(data){ 
            	if(data["level"]==1)
               		return "k/kui/images/icons/16/home.png";
				else if (data["level"] == 2)
					return "k/kui/images/icons/16/org.png";
				else if (data["level"] == 3)
                	return "k/kui/images/icons/16/group.png";
				else
                	return "k/kui/images/icons/16/folders_explorer.png";
            },
			onSelect : function(node) {
				var win = $("#rightFrame").get(0).contentWindow.window;
				win.s_org_id = node.data.id;
				addressListId=node.data.id;
				if(win.loadGridData) win.loadGridData(node.data.id);
			},onAfterAppend:function(data){
			}
		});
		$("#rightFrame").attr("src","pub/addressBook/public_look_org.jsp");		
	}); 
	//码表新增更新回刷 
	function refreshTree(data) {
		orgTreeManager.clear();
		orgTreeManager.loadData("","rbac/pubAddressType/addressListTreePublic.do","");
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
		<div position="left" title="类别" class="overflow-auto">
			<ul id="tree1"></ul>
		</div>
		<div position="center">
			<iframe id="rightFrame" frameborder="0" name="userFrame" width="100%" height="100%" scrolling="no" src="" /></iframe>
		</div>
	</div>
</body>
</html>