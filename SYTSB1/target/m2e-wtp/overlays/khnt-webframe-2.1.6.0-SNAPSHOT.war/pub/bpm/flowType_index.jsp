<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="main_head" pageStatus="detail">
<%@include file="/k/kui-base-form.jsp"%>
<title>工作流分类管理</title>
<script type="text/javascript">
	function detailBack(data, status) {
		data.parentId = data.flowType==null?"":data.flowType.id;
		if (status == "add") {
			var node = treeMgr.getSelected();
			var nodes = [];
			nodes.push(data);
			if (node)
				treeMgr.append(node.target, nodes);
			else
				treeMgr.append(null, nodes);
		} else {
			var node = treeMgr.getSelected();
			if (node)
				treeMgr.update(node.target, data);
		}
		treeMgr.selectNode(data.id);
	}
	
	function showDetail(ndata){
		$("#formObj").setValues(ndata);
	}
	var treeMgr;
	$(function() {
		treeMgr = $("#tree1").ligerTree({
			checkbox : false,
			selectCancel : false,
			idFieldName : "id",
			parentIDFieldName : "parentId",
			textFieldName : "typeName",
			url : 'bpm/flowType/viewTree.do',
			onSelect : function(node) {
				showDetail(node.data);
			},
			onAfterAppend : function(parentNode, newdata) {
				if(newdata.length>0){
					treeMgr.selectNode(newdata[0].id);
					showDetail(newdata[0]);
				}
			}
		});
		
		$("#toptoolbar").ligerToolBar(
			<sec:authorize ifNotGranted="super_administrate">
				<tbar:toolBar type="ligerToolBar" code="liucsort">
				</tbar:toolBar>
			</sec:authorize>
			<sec:authorize access="hasRole('super_administrate')">
				{
				items : [ {
				text: '新增', click: add, icon: 'add'
				}, "-", {
				text: '修改', click: modify, icon: "modify"
				}, "-", {
				text: '删除', click: del, icon: "del"
				} ]
				}
			</sec:authorize>
		);
		$("#layout1").ligerLayout({
			leftWidth: 250,
			topHeight: 30,
			space: 5
		});
	});

	function add() {
		var node = treeMgr.getSelected();
		var pid = "";
		if(node!=null) pid = node.data.id;
		top.$.dialog({
			width: 420,
			height: 280,
			lock: true,
			title: "新增类别",
			data: {"window": window},
			content: "url:pub/bpm/flowType_detail.jsp?status=add&pid=" + pid
		});
	}
	function modify() {
		var node = treeMgr.getSelected();
		if (node==null) {
			$.ligerDialog.error('请选择需要修改的类型!')
			return false;
		}
		top.$.dialog({
			width: 420,
			height: 280,
			lock: true,
			title: "修改类别",
			data: {"window": window},
			content: "url:pub/bpm/flowType_detail.jsp?status=modify&id="+ node.data.id
		});
	}
	function del() {
		var node = treeMgr.getSelected();
		if (node == null) {
			$.ligerDialog.error("请选择要删除的分类", "error");
			return;
		}
		var msg = "确定要删除？";
		if(node.data.children) msg = "该分类下还有子分类，确定要全部删除？";
		$.ligerDialog.confirm(kFrameConfig.DEL_MSG,function(r){
			if(!r)return;
			$.post("bpm/flowType/delete.do", {ids:node.data.id}, function(data) {
				if (data.success) {
					treeMgr.remove(node.target);
					showDetail(null);
				} else {
					$.ligerDialog.alert(data.msg, "error");
				}
			});
		});
	}
</script>
</head>
<body class="p5">
	<div id="layout1">
		<div position="top" id="toptoolbar"></div>
        <div position="left" title="流程分类" class="overflow-auto">
			<ul id="tree1"></ul>
		</div>
		<div position="center">
			<form name="formObj" id="formObj" method="post">
				<table cellpadding="3" cellspacing="0" class="l-detail-table">
					<tr>
						<td class="l-t-td-left">分类编号：</td>
						<td class="l-t-td-right"><input name="typeCode" type="text"
							ltype='text' validate="{required:true,maxlength:32}" /></td>
					</tr>
					<tr>
						<td class="l-t-td-left">分类名称：</td>
						<td class="l-t-td-right"><input name="typeName" type="text"
							ltype='text' validate="{required:true,maxlength:40}" /></td>
					</tr>
					<tr>
						<td class="l-t-td-left">排序：</td>
						<td class="l-t-td-right"><input name="sort" type="text"
							ltype='text' validate="{maxlength:32}" /></td>
					</tr>
					<tr>
						<td class="l-t-td-left">描述：</td>
						<td class="l-t-td-right"><textarea name="remark" rows="4"
								class="l-textarea" validate="{maxlength:120}"></textarea></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>
