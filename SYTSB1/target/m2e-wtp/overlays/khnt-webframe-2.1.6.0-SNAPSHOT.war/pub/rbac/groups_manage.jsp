<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="detail">
<title>组织机构管理</title>
<%@include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
	var setting = {
		callback: {
			onClick: setFormData,
			onNodeCreated:function(event, treeId, treeNode){
				var treeObj = $.fn.zTree.getZTreeObj(treeId);
				treeNode.icon = "k/kui/images/icons/16/group.png";
				treeObj.updateNode(treeNode);
			}
		}
	};
	$(function(){
		$("#toptoolbar").ligerToolBar({
			items : [ {
				text : '新增',
				click : newGroups,
				icon : 'add'
			}, "-", {
				text : '修改',
				click : modify,
				icon : "modify"
			}, "-", {
				text : '删除',
				click : remove,
				icon : "del"
			}]
		});
		$("#layout1").ligerLayout({
			leftWidth : 250,
			topHeight: 30,
			space: 5,
			allowTopResize : false,
			allowLeftCollapse : false,
			allowRightCollapse : false
		});
		$("#gu_list").ligerGrid({
			data: {Rows:[]},
			height: 200,
			usePager: false,
			columns: [{
				display:"姓名", name:"memberName", width:150
			},{
				display:"是否负责人", name:"leader", width:80, render:function(row){
					return row.leader?"是":"否";
				}
			}]
		});
		$.getJSON("rbac/groups/groups_tree.do",function(resp){
			if(!resp.success)return;
			$.fn.zTree.init($("#tree1"), setting, resp.data);
		});
	});
	
	function newGroups(){
		var treeObj = $.fn.zTree.getZTreeObj("tree1");
		var snode = treeObj.getSelectedNodes();
		var pid = snode.length==0?"":snode[0].id;
		winOpen({
			title: "新建群组",
			width: 600,
			height: 450,
			lock: true,
			content: "url:pub/rbac/groups_detail.jsp?pageStatus=add&pid=" + pid,
			data: {
				callback: function(groups){
					treeObj.addNodes(snode.length>0?snode[0]:null,groups,false);
					treeObj.selectNode(groups);
					$("#gu_list").ligerGetGridManager().loadData({Rows:groups.users});
				}
			}
		});
	}
	
	function modify(){
		var treeObj = $.fn.zTree.getZTreeObj("tree1");
		var snode = treeObj.getSelectedNodes();
		if(snode.length==0){
			$.ligerDialog.warn("请选择要修改的群组!");
			return;
		}
		var pnode = snode[0].getParentNode();
		winOpen({
			title: "修改群组",
			width: 600,
			height: 450,
			lock: true,
			content: "url:pub/rbac/groups_detail.jsp?pageStatus=edit&id=" + snode[0].id + "&pid=" + (pnode==null?"":pnode.id),
			data: {
				callback: function(groups){
					$("#form1").setValues(groups);
					$("#gu_list").ligerGetGridManager().loadData({Rows:groups.users});
				}
			}
		});
	}

	function remove(){
		var treeObj = $.fn.zTree.getZTreeObj("tree1");
		var snode = treeObj.getSelectedNodes();
		if(snode.length==0){
			$.ligerDialog.warn("请选择要修改的群组!");
			return;
		}
		$.ligerDialog.confirm("确定要解散该群组？提示：删除后数据不可恢复。",function(rst){
			if(!rst) return;
			$.post("rbac/groups/delete.do?ids=" + snode[0].id,function(resp){
				if(resp.success){
					treeObj.removeNode(snode[0]);
					$("#form1").setValues({code:"",name:"",remark:""});
					$("#gu_list").ligerGetGridManager().loadData({Rows:[]});
				}
			},"json");
		});
	}
	
	function setFormData(event,tid,data,flag){
		$("#form1").setValues("rbac/groups/detail.do?id="+data.id,function(res){
			$("#gu_list").ligerGetGridManager().loadData({Rows:res.data.users});
		});
	}
</script>
</head>
<body class="p5">
	<div id="layout1">
		<div position="top" id="toptoolbar"></div>
		<div position="left" title="群组管理" style="overflow:auto;">
			<ul id="tree1" class="ztree"></ul>
		</div>
		<div position="center">
			<form name="form1" id="form1" method="post">
				<table class="l-detail-table">
					<tr>
						<td class="l-t-td-left" style="width:50px;">编号：</td>
						<td class="l-t-td-right"><input name="code" type="text"	ltype='text' /></td>
						<td class="l-t-td-left" style="width:50px;">名称：</td>
						<td class="l-t-td-right"><input name="name" type="text" ltype='text' /></td>
						<td class="l-t-td-left" style="width:50px;">简介：</td>
						<td class="l-t-td-right"><input name="remarks" type="text" ltype='text' /></td>
					</tr>
				</table>
				<fieldset class="l-fieldset">
					<legend class="l-legend"><div>群组成员</div></legend>
					<div id="gu_list"></div>	
				</fieldset>
			</form>
		</div>
	</div>
</body>
</html>
