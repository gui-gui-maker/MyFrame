<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.rbac.impl.bean.Org"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="main_head">
<title>区划地址管理</title>
<%@include file="/k/kui-base-list.jsp"%>
    <%
        Org org = (Org)SecurityUtil.getSecurityUser().getDepartment();
        String code = org.getAreaCode();
    %>
<script type="text/javascript">
	var tree = null;
    var curNode=null;
    var imgUrl="pub/rbac/img/";
     function initPage(){
         $("#layout1").ligerLayout({
             leftWidth : 220,
             sapce : 5,
             allowLeftCollapse : false,
             allowRightCollapse : false
         });

         //菜单树
         tree = $("#tree1").ligerTree({
             checkbox : false,
             url : 'rbac/area/getAreaTreeWithAsync.do?code=<%=code%>',
             attribute : [ "pid" ],
             iconFieldName:"levels",
             iconParse:function(data){
                 return imgUrl+data["levels"]+".png";
             },
//			onAfterAppend : function() {
//				tree.selectNode("51"); //初始化
//			},
             onSelect: function (node) {
                 if ($("#rightFrame")[0]) {
                     var rWindow = $("#rightFrame")[0].contentWindow;
                     if (rWindow.Qm) {
                         var Qm = rWindow.Qm;
                         Qm.setCondition([
                             {name: "code", compare: "like", value: node.data.code},
                             {name: "code", compare: "!=", value: node.data.code}
                         ]);
                         Qm.searchData();
                     }
                     rWindow.parentNode=node.data;
                 }
             },
             onBeforeExpand: function(node){
                 if (node.data.children && node.data.children.length == 0)
                 {
                     tree.loadData(node.target,"rbac/area/getAreaTreeByPid.do?id="+node.data.id);
                 }
             }
         });
     }
    function reloadNode(){
        if(curNode!=null){
            if(!tree.hasChildren(curNode) ){
                tree.upgrade(curNode);
            }
            tree.loadData(curNode.target,"rbac/area/getAreaTreeByPid.do?id="+curNode.data.id,{},true);
        }
    }
    function removeNode(id){
        var node=tree.getDataByID(id);
        if(node){
            var parent=tree.getParentTreeItem(node);
            tree.remove(node);
            if(!tree.hasChildren(parent) ){
                tree.demotion(curNode);
            }
        }
    }
	$(function() {
		//页面布局

        initPage()
	});

	function addMenu(){
		var actionNode = tree.getSelected();
		var pid = (actionNode == null ? "00" : actionNode.data.id);
		$("#rightFrame").attr("src","pub/rbac/menu_detail.jsp?status=add&pid=" + pid);
	}

	function modify(){
		var actionNode = tree.getSelected();
		if (actionNode == null) {
			$.ligerDialog.error('请选择需要修改的机构!')
			return false;
		}
		$("#rightFrame").attr("src","pub/rbac/menu_detail.jsp?status=modify&pid=" + actionNode.data.id);
	}

	function remove() {
		var actionNode = tree.getSelected();
		if (actionNode == null) {
			$.ligerDialog.error('请选择您要删除的菜单!', "提示")
			return false;
		} else {
			if (tree.hasChildren(actionNode.data)) {
				$.ligerDialog.error('该节点下含有子节点，请先删除子节点!', "提示")
				return false;
			}
			if (actionNode.data.id=="root") {
				$.ligerDialog.error("不能删除根节点!");
				return false;
			}
			var tips = "你确定要删除菜单【" + actionNode.data.text + "】吗？\n删除后不能恢复！";
			$.ligerDialog.confirm(tips, function(yes) {
				if (yes) {
					$.post("rbac/resource/delete.do", {ids:actionNode.data.id}, function(res) {
						if (res.success) {
							rightFrame.src = "";
							tree.remove(actionNode.target);
						} else {
							$.ligerDialog.alert('删除失败！', "error");
						}
					});
				}
			});
		}
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
        <div id="toptoolbar"></div>
		<div position="left" title="区划地址" style="overflow: auto;">
			<ul id="tree1"></ul>
		</div>
		<div position="center">
			<iframe id="rightFrame" frameborder="0" src="pub/rbac/area_list.jsp" name="rtree" width="100%" height="100%" scrolling="no"></iframe>
		</div>
	</div>
</body>
</html>
