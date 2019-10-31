<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="main_head">
<title>数据字典管理</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
	var treeManager = null, gridManager = null, toolbarManager = null;
	var treeData;
	$(function() {
		//按钮工具栏
		toolbarManager = $("#toptoolbar").ligerToolBar(
				{
			items: [{
				text: '增加值',id: 'addValue',click: addValue,icon: 'info-add',disable: true
			}, "-", {
				text: '修改值',id: 'modifyValue',click: modifyValue,icon: "info-edit",disable: true
			}, "-", {
				text: '删除值',id: 'delValue',click: delValue,icon: "info-del",disable: true
			},
			"-", {
				text: '禁用值',id: 'jy',click: change,icon: "info-del",disable: true
			},"-",
			{
				text: '启用值',id: 'qy',click: change,icon: "info-del",disable: true
			},"-", {
				text: '刷新缓存',id: 'refreshCache',click: refreshCache,icon: 'refurbish'
			}, "-", {
				text: '<span style="color:red">更新码表后，必须执行【刷新码表】同步系统缓存！</span>',id: 'refreshCache'
			} ]
			} 
		);

		//页面布局
		$("#layout").ligerLayout({
			leftWidth: 350,
			topHeight: 30,
			space: 3,
			allowTopResize: false
		});
		
		$.getJSON("pub/codetable/codetableTreePermission.do",function(data){
			treeData = data;
			createTree(treeData);
		});
		
		//添加码表搜索框
		addSearchBar();
		createGrid();
	});
	var administrate = "<sec:authorize access="hasRole('super_administrate')">true</sec:authorize>";
	function changeToolbarStatus() {
		var actionNode = treeManager.getSelected();
		if(actionNode==null || actionNode.data.type=="kind"){
			toolbarManager.setEnabled({modify:false,del:false,addValue:false,modifyValue:false,delValue:false,jy:false,qy:false,per:false});
		}
		else if ((((actionNode.data.isSystem != "true") || administrate == 'true') && actionNode.data.id != "top")){
			toolbarManager.setEnabled({modify:true,del:true,addValue:true,modifyValue:true,delValue:true,jy:true,qy:true,per:true});
		}
		else {
			toolbarManager.setEnabled({modify:false,del:false,addValue:false,modifyValue:false,delValue:false,jy:false,qy:false});
		}
	}

	//字典树
	var imgPath = "k/kui/images/icons/16/";
	function createTree(dataset){
		treeManager = $("#tree1").ligerTree({
			checkbox: true,
			selectCancel: false,
			data: dataset,
			iconFieldName: "type",
			iconParse: function(data) {
				var image;
				if(data["id"]=="system_code_table") image = "lock.png";
				else if(data["id"]=="common_code_table") image = "lock-3-open.png";
				else if(data["type"]=="1") image = "node-tree.png";
				else image = "table.png";
				return imgPath + image;
			},
			onSelect: function(node) {
				changeToolbarStatus(node);
				reloadDataGrid(node);
			},
			onCheck:function(node, checked){
				if(treeManager.getChecked()!=null&&treeManager.getChecked()!=undefined&&treeManager.getChecked()==""){
					toolbarManager.setEnabled({exp:false});
				}else{
					toolbarManager.setEnabled({exp:true});
				}
			}
		});
	}
	
	function change(obj){
		var row = gridManager.getSelected();
		if (row) {
			var title = "";
			var status = "";
			var t="";
			if(obj.id=='jy'){
				title = "确定要禁用码值"+row.code+"吗？";
				t = "禁用成功";
				status = 0;
			}else{
				title = "确定要启用码值"+row.code+"吗？";
				t = "启用成功";
				status = 1;
			}
			$.ligerDialog.confirm(title,function(yes) {
				if (yes) {
					$.getJSON("pub/codetablevalue/changeStatus.do",{status:status,ids:row.id,codeTabledId:treeManager.getSelected().data.id},function(res){
						if(res.success){
							top.$.notice(t,4);
							reloadDataGrid();
						}else{
							$.ligerDialog.error('操作失败');
							return false;
						}
					})
				}
			});
		} else {
			$.ligerDialog.warn('请选择您要操作的码表值！')
		}
	}
	
	function add() {
		top.$.dialog({
			width: 450,
			height: 260,
			lock: true,
			data: {"window": window},
			title: "新增码表",
			content: 'url:pub/codetable/codetable_detail.jsp?status=add'
		});
	}

	function modify() {
		var actionNode = treeManager.getSelected();
		if (actionNode == null) {
			$.ligerDialog.error('请选择需要修改的项!')
			return false;
		}
		top.$.dialog({
			width: 450,
			height: 260,
			lock: true,
			data: {"window": window},
			title: "修改码表",
			content: 'url:pub/codetable/codetable_detail.jsp?status=modify&id='
					+ actionNode.data.id
		});
	}

	function del() {
		var actionNode = treeManager.getSelected();
		if (actionNode == null) {
			$.ligerDialog.warn('请选择您要删除的数据!', "提示")
			return false;
		} else {
			$.ligerDialog.confirm(kFrameConfig.DEL_MSG, function(yes) {
				if (yes) {
					$.post("pub/codetable/delete.do", {
						"ids": actionNode.data.id
					}, function(response) {
						if (response.success) {
							treeManager.remove(actionNode.data);
							gridManager.loadData([]);
						} else {
							$.ligerDialog.error('删除失败！');
						}
					});
				}
			});
		}
	}

	//刷新码表缓存
	function refreshCache(){
		$("body").mask("正在刷新...");
		 $.getJSON("pub/codetable/refreshCache.do",function(resp){
         	if(resp.success){
         		top.$.notice('刷新缓存成功！',4)
         		treeManager.clear();
         		$.getJSON("pub/codetable/codetableTree.do",function(response){
         			reCreateTree(response);
         		});
         	}
         	else
         		$.ligerDialog.error('刷新缓存失败！请稍后再试。');
			$("body").unmask();
    	});
	}
	
	//构建码表值列表
	function createGrid() {
		gridManager = $("#dataGridDiv").ligerGrid({
			title: administrate=="true"?"您是超级管理员，可以修改系统码表。":"说明：超级管理员能够修改系统级码表。",
			columns: [ {
				display: '名称', name: 'name', width: '30%', align: 'left'
			}, {
				display: '值', name: 'code', width: '30%', align: 'left'
			}, {
				display: '排序', name: 'sort', width: '10%', align: 'left'
			},{
				display: '说明', name: 'remark', width: '20%', align: 'left'
			},{
				display: '状态', name: 'status', width: '10%', align: 'center',render:function(row){
					return row.status=='0'?"禁用":"启用";
				}
			} ],
			usePager: false,
			height: '100%',
			alternatingRow: false,
			tree: { columnName: 'name' },
			checkbox: false,
			autoCheckChildren: false
		});
	}

	//增加值
	function addValue() {
		var actionNode = treeManager.getSelected();
		if (!actionNode) {
			$.ligerDialog.warn("请从左边码表树中选择一项!");
			return;
		}
		var actionNodeID2 = "";
		if(actionNode.data.type=="1"){
			var row = gridManager.getSelectedRow();
			if (row) actionNodeID2 = row.id;
		}
		top.$.dialog({
			width: 450,
			height: 220,
			lock: true,
			title: "新增表值",
			content: 'url:pub/codetable/codetablevalue_detail.jsp?status=add&id='
					+ actionNode.data.id
					+ '&code_table_value_id='
					+ actionNodeID2,
			data: {
				"window": window
			}
		});
	}

	//修改值
	function modifyValue() {
		var row = gridManager.getSelectedRow();
		if (row) {
			var actionNodeID2 = row.id;
			top.$.dialog({
				width: 450,
				height: 220,
				lock: true,
				title: "修改表值",
				content: 'url:pub/codetable/codetablevalue_detail.jsp?status=modify&id='
						+ actionNodeID2,
				data: {
					"window": window
				}
			});
		} else {
			$.ligerDialog.warn('请选择需要修改的值!')
		}
	}

	//删除值
	function delValue() {
		var row = gridManager.getSelected();
		if (row) {
			$.ligerDialog.confirm("删除码值将删除自身值和子码值，确定要删除？",function(yes) {
				if (yes) {
					$.post("pub/codetablevalue/deleteValue.do",{
						"ids": row.id,
						codeTabledId: treeManager.getSelected().data.id
					}, function(resp) {
						if (resp.success) {
							gridManager.remove(row);//执行删除
						} else {
							$.ligerDialog.error('删除失败');
							return false;
						}
					}, "json");
				}
			});
		} else {
			$.ligerDialog.warn('请选择您要删除的码表值！')
		}
	}

	//刷新码表值数据
	function reloadDataGrid(node) {
		var sNode = node;
		if(!sNode) sNode = treeManager.getSelected()
		$.getJSON('pub/codetablevalue/getCodetableValues.do?id=' + sNode.data.id, function(data) {
			gridManager.loadData(data);
		});
	}

	//码表新增更新回刷 
	function refreshTree(data, status) {
		var parentData = treeManager.getDataByID(data.isSystem=="true"?"system_code_table":"common_code_table");
		var parentNode = treeManager.getNodeDom(parent);
		//节点数据
		var newNodes = [ {
			text: data.name + "［" + data.code + "］",
			id: data.id,
			type: data.type,
			isSystem: data.isSystem,
			ischecked:false
		} ];
		//更新节点，先删除原来节点
		if (status != "add"){
			var node = treeManager.getSelected();
			treeManager.remove(node.data);
		}
		//插入新节点
		treeManager.append(parentData,newNodes);
		//treeManager.selectNode(data.id);
		//treeManager.onClick(newNodes)
	}

	function addSearchBar() {
		$(".l-layout-header-inner").append(
			'<div class="search-div"><input type="text" style="width:100px" id="code_search_val" onkeyup="doKeyupEvent(event)" title="按回车执行搜索" />'
					+ '<input type="button" class="l-button" value="搜索" onclick="doSearch()" /></div>');
	}
	
	function doKeyupEvent(event){
		if(event.keyCode==13 || event.keyCode==108)doSearch();
	}

	function doSearch() {
		filterTreeSysData = [];
		filterTreeCommData = [];
		var str = $("#code_search_val").val();
		treeManager.clear();
		if(!str||str=="top")
			createTree(treeData);
		else if(treeData){
			searchData(treeData,str);
			var searchResult = [
			    {"id":"system_code_table","text":"系统码表","type":"kind","children":filterTreeSysData},
			    {"id":"common_code_table","text":"普通码表","type":"kind","children":filterTreeCommData}
			];
			reCreateTree(searchResult)
		}
		changeToolbarStatus();
	}
	
	//重新构建树 
	function reCreateTree(treeData){
		$("#tree1").empty();
		//重新构建树，避免jquery事件重叠
		//此处如果直接使用前面定义的树创建方法，会发生jquery事件重复绑定,这是liger tree的bug
		treeManager = $("#tree1").ligerTree({
			checkbox: true,
			selectCancel: false,
			data: treeData,
			iconFieldName: "type",
			iconParse: function(data) {
				var image;
				if(data["id"]=="system_code_table") image = "lock.png";
				else if(data["id"]=="common_code_table") image = "lock-3-open.png";
				else if(data["type"]=="1") image = "node-tree.png";
				else image = "table.png";
				return imgPath + image;
			}
		});
	}
	
	var filterTreeSysData;
	var filterTreeCommData;
	function searchData(dataArr,str){
		$.each(dataArr,function(i){
			if(dataArr[i].text.indexOf(str)>-1){
				if(dataArr[i].isSystem=="true")
					filterTreeSysData.push(dataArr[i]);
				else
					filterTreeCommData.push(dataArr[i]);
			}
			if(dataArr[i].children)
				searchData(dataArr[i].children,str);
		});
	}
	
	function importCode(){
		top.$.dialog({
				width:'40%',
				height:'30%',
         	lock:true,
         	title:"导入",
         	content: 'url:pub/codetable/import.jsp',
         	data: {"window": window}
         });
	}
	function exportCode(){
		var nodes = treeManager.getChecked();
		var ids = "";
		for(var i in nodes){
			ids = ids +nodes[i].data.id+",";
		}
		if(ids==""||ids==undefined||ids==null){
			$.ligerDialog.warn("请先勾选你要导出的数据字典！");
			return;
		}
		ids = ids.substring(0,ids.length-1);
		$("body").mask("正在导出请稍后...");
  		$("#down").attr('src',"pub/codetable/export.do?ids="+ids);
  		$("body").unmask();
	}
	
	
	function authorized(){
		var actionNode = treeManager.getSelected();
		if(!actionNode){
			$.ligerDialog.alert("请选择要授权的数据字典！");
			return;
		}
		top.$.dialog({
			width: 920,
			height: 450,
			lock: true,
			title: "数据字典授权",
			content: "url:pub/codetable/code_Authorized_permission.jsp?cid=" + actionNode.data.id
		});
	}
	
</script>
<style type="text/css">
.l-tree .l-tree-icon-none img {
	height: 16px;
	margin: 3px;
	width: 16px;
}

.search-div {
	width: 160px;
	float: right;
	padding-top: 2px;
}
.l-button {
    background: #f7f7f7 none repeat scroll 0 0;
    border: 1px solid #ccc;
    border-radius: 2px;
    color: #666;
    height: auto;
    line-height: normal;
    margin: 0;
    padding: 0 8px;
}
</style>
</head>
<body class="p5">
	<div id="layout">
		<div position="top" id="toptoolbar"></div>
		<div position="left" title="数据字典" class="overflow-auto">
			<ul id="tree1"></ul>
		</div>
		<div position="center" id="dataGridDiv"></div>
	</div>
	<iframe id="down" style="display:none"></iframe>
</body>
</html>
