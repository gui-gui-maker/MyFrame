var setting = {
	data: {
		key: {
			name: "orgName"
		}
	},
	async: {
		enable: true,
		url: getUrl
	},
	view: {
		expandSpeed: ""
	},
	callback: {
		onClick: ztreeClick,
		onNodeCreated:function(event, treeId, treeNode){
			var treeObj = $.fn.zTree.getZTreeObj(treeId);
			if(treeNode["level"]==0){
				treeNode.icon = "k/kui/images/icons/16/home.png";
			}
			else if (treeNode["property"] == 'unit')
				treeNode.icon = "k/kui/images/icons/16/org.png";
			else if (treeNode["property"] == 'dep')
				treeNode.icon = "k/kui/images/icons/16/group.png";
			else
				treeNode.icon = "k/kui/images/icons/16/folders_explorer.png";
			treeObj.updateNode(treeNode);
		},
		onAsyncSuccess: onAsyncSuccess,
		onAsyncError: onAsyncError,
		beforeDrag: beforeDrag,
		beforeDrop: beforeDrop,
		beforeDragOpen: beforeDragOpen,
		onDrag: onDrag,
		onDrop: onDrop
	}
	,edit:
	{
		drag: {
			autoExpandTrigger: true,
			prev: dropPrev,
			inner: dropInner,
			next: dropNext,
			isCopy:true,
			isMove:true
		},
		enable: true,
		showRemoveBtn: false,
		showRenameBtn: false
	}
};
	
function ztreeClick(event,treeId,treeNode){
	setformData(treeNode);
}
function getUrl(treeId, treeNode) {
	return "rbac/org/getSubordinate.do?orgid=" + treeNode.id;
}
function onAsyncSuccess(event, treeId, treeNode, msg) {
	if (!msg || msg.length == 0) {
		return;
	}
	var zTree = $.fn.zTree.getZTreeObj("tree1");
	zTree.updateNode(treeNode);
}
function onAsyncError(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown) {
	var zTree = $.fn.zTree.getZTreeObj("tree1");
	zTree.updateNode(treeNode);
}

$(function() {
	// 页面布局
	$("#layout1").ligerLayout({
		leftWidth : 250,
		topHeight: 30,
		space: 5,
		allowTopResize : false,
		allowLeftCollapse : false,
		allowRightCollapse : false
	});
	if(unitId==""){
		$.getJSON("rbac/org/getTopOrg.do",function(res){
			if(res.success){
				$("body").mask("正在加载数据，请稍候....");
				getChildList(unitId,function(dataList){
					$("body").unmask();
					var strData = JSON.stringify(dataList);
					strData = strData.replace(/isexpand/g, "open");
					zNodes = eval(strData)
					$.fn.zTree.init($("#tree1"), setting, zNodes);
					var treeObj = $.fn.zTree.getZTreeObj("tree1");
					var nodes = treeObj.getNodes();
					if (nodes.length>0) {
						treeObj.selectNode(nodes[0]);
						setTimeout(function(){setformData(nodes[0]);},100);
					}
				})
			}else{
				$.ligerDialog.warn("获取机构信息出错！");
			}
		})
	}else{
		$("body").mask("正在加载数据，请稍候....");
		getChildList(unitId,function(dataList){
			$("body").unmask();
			var zNodes = {
				id: unitId,
				text: unitName,
				orgName: unitName,
				orgCode: orgCode,
				sort: sort=="null"?"":sort,
				tellphone: tellphone=="null"?"":sort,
				property: property,
			    type:type,
			    areaName:areaName,
				discrible: "",
				level: "0",
				isexpand:true,
				open:true
			}
			zNodes.children = dataList;
			$.fn.zTree.init($("#tree1"), setting, zNodes);
			var treeObj = $.fn.zTree.getZTreeObj("tree1");
			var nodes = treeObj.getNodes();
			if (nodes.length>0) {
				treeObj.selectNode(nodes[0]);
				setTimeout(function(){setformData(nodes[0]);},100);
			}
		});
	}
});

function getChildList(unitId,callback){
	$.getJSON("rbac/org/getSubordinate.do?orgid=" + unitId,function(dataList){
		if(callback){
			callback(dataList);
		}
	})
}

function refresh(data, status) {
	var ztree = $.fn.zTree.getZTreeObj("tree1");
	var node = ztree.getSelectedNodes();
	if (status == "add") {
		var newNode=[];
		newNode.push({
			id: data.id,
			text: data.orgName,
			orgName: data.orgName,
			orgCode: data.orgCode,
			sort: data.sort,
			tellphone: data.tellphone,
			property: data.property,
			discrible: data.discrible,
			level: data.levelCode,
			type:data.type,
			areaName:data.areaName

		})
		if (node){
			ztree.addNodes(node[0], newNode,false);
		}
		else
		{
			ztree.addNodes(null, newNode,false);
		}
	} else {
		if (node){
			node[0].id= data.id;
			node[0].text= data.orgName;
			node[0].orgName= data.orgName;
			node[0].orgCode= data.orgCode;
			node[0].sort= data.sort;
			node[0].tellphone= data.tellphone;
			node[0].property= data.property;
			node[0].discrible= data.discrible;
			node[0].level= data.levelCode;
			node[0].type=data.type;
			node[0].areaName=data.areaName;
			if (node.length>0) {
				ztree.updateNode(node[0]);
			}
		}
	}
	ztree.selectNode(ztree.getNodeByParam("id", data.id, null));
	setformData(data)
}

function setformData(data){
	$("#form1").setValues("rbac/org/detail.do?id="+data.id,function(res){})
}

function authorizePerBatch(item){
	top.$.dialog({
		width : $(top).width()-100,
		height : $(top).height()-150,
		title : "组织机构授权配置！",
		lock : true,
		cancel : true,
		content : 'url:pub/rbac/org_Authorized_per_batch.jsp',
		ok : function(w){
			var iframe = this.iframe.contentWindow;
			iframe.authorizedPermission();
			return false;
		}
	});
}

function authorizePer(item){
	var ztree = $.fn.zTree.getZTreeObj("tree1");
	var actionNode = ztree.getSelectedNodes();
	if(!actionNode){
		$.ligerDialog.error("请选择一个组织机构！");
		return;
	}
	top.$.dialog({
		width : 1100,
		height : $(top).height()-150,
		title : "组织机构授权配置",
		lock : true,
		cancel : true,
		content : 'url:rbac/org/initAuthorizedPermission.do?orgId=' + actionNode[0].id,
		ok : function(w){
			var iframe = this.iframe.contentWindow;
			iframe.authorizedPermission();
			return false;
		}
	});
}

function authorizeRoleBatch(item){
	top.$.dialog({
		width : $(top).width()-100,
		height : $(top).height()-150,
		title : "批量组织机构角色配置！",
		lock : true,
		cancel : true,
		content : 'url:pub/rbac/org_Authorized_role_batch.jsp',
		ok : function(w){
			var iframe = this.iframe.contentWindow;
			iframe.authorizedPermission();
			return false;
		}
	});
}

function authorizeRole(item){
	var ztree = $.fn.zTree.getZTreeObj("tree1");
	var actionNode = ztree.getSelectedNodes();
	if(!actionNode){
		$.ligerDialog.error("请选择一个组织机构！");
		return;
	}
	top.$.dialog({
		width : 1100,
		height : $(top).height()-150,
		title : "组织机构角色配置",
		lock : true,
		cancel : true,
		content : 'url:rbac/org/initAuthorizedRole.do?orgId=' + actionNode[0].id,
		ok : function(w){
			var iframe = this.iframe.contentWindow;
			iframe.authorizedPermission();
			return false;
		}
	});
}

function config(){
	var ztree = $.fn.zTree.getZTreeObj("tree1");
	var actionNode = ztree.getSelectedNodes();
	if(!actionNode){
		$.ligerDialog.error("请选择要调整的组织机构！");
		return;
	}
	if(actionNode[0].id==unitId){
		$.ligerDialog.alert("不能调整该组织机构！");
		return;
	}
	selectUnitOrUser('000', 0, null, null, function(data){
		if(data.code){
			if(data.code==actionNode[0].id){
				$.ligerDialog.alert("不能选择要调整的组织机构！");
				return;
			}
			$.getJSON("rbac/org/changeParent.do",{orgId:actionNode[0].id,parentId:data.code},function(resp){
				if(resp.success){
					// var pnode = ztree.getNodeByParam("id", data.code,
					// null);
					// ztree.addNodes(pnode[0], actionNode[0],false);
					// ztree.removeNode(actionNode[0]);
					top.$.notice("调整成功！",2);
					window.location.reload();
				}
			});
		}
	});
}
function newOrg(){
	var treeObj = $.fn.zTree.getZTreeObj("tree1");
	var actionNode = treeObj.getSelectedNodes();
	if(!actionNode){
		$.ligerDialog.error("请选择一个组织机构！");
		return;
	}
	var pid = actionNode[0].id;// 父id
	top.$.dialog({
		width : 650,
		height : 500,
		lock : true,
		title : "添加组织机构",
		data : { "window" : window },
		content : 'url:pub/rbac/org_detail.jsp?status=add&orgid=' + pid + "&division=" + _org_division
	});
}

function modify(){
	var treeObj = $.fn.zTree.getZTreeObj("tree1");
	var actionNode = treeObj.getSelectedNodes();
	if (actionNode == null) {
		$.ligerDialog.error('请选择需要修改的组织机构！')
		return false;
	}
	top.$.dialog({
		width : 650,
		height : 500,
		lock : true,
		title : "修改组织机构信息",
		data : { "window" : window },
		content : 'url:pub/rbac/org_detail.jsp?status=modify&orgid=' +actionNode[0].id + "&division=" + _org_division
	});
}

function remove() {
	var treeObj = $.fn.zTree.getZTreeObj("tree1");
	var actionNode = treeObj.getSelectedNodes();
	if (actionNode == null) {
		$.ligerDialog.error('请选择您要删除的组织机构！', "提示")
		return false;
	} else {
		if (actionNode[0].isParent) {
			$.ligerDialog.error('该节点下含有子节点，请先删除子节点！', "提示")
			return false;
		}
		if (actionNode[0].id==unitId) {
			$.ligerDialog.error("不能删除根节点！");
			return false;
		}
		var tips = "你确定要删除组织机构【" + actionNode[0].orgName + "】吗？\n删除后不能恢复！";
		$.ligerDialog.confirm(tips, function(yes) {
			if (yes) {
				deleteOrgTreeNode(actionNode);
			}
		});
	}
}

// 删除树节点
function deleteOrgTreeNode(node) {
	var treeObj = $.fn.zTree.getZTreeObj("tree1");
	$.post("rbac/org/deletenode.do", {"ids" : node[0].id}, function(data) {
		if (data.success) {
			var nodes = treeObj.getNodes();
			if (nodes.length>0) {
				treeObj.selectNode(nodes[0]);
				setTimeout(function(){setformData(nodes[0]);},100);
			}
			treeObj.removeNode(node[0]);
		} else {
			$.ligerDialog.alert('删除失败！', "error");
		}
	});
}

// --------------------------------------------------------------------------------------------
// ztree 机构树拖拽

function dropPrev(treeId, nodes, targetNode) {
	var pNode = targetNode.getParentNode();
	if (pNode && pNode.dropInner === false) {
		return false;
	} else {
		for (var i=0,l=curDragNodes.length; i<l; i++) {
			var curPNode = curDragNodes[i].getParentNode();
			if (curPNode && curPNode !== targetNode.getParentNode() && curPNode.childOuter === false) {
				return false;
			}
		}
	}
	return true;
}
function dropInner(treeId, nodes, targetNode) {
	if (targetNode && targetNode.dropInner === false) {
		return false;
	} else {
		for (var i=0,l=curDragNodes.length; i<l; i++) {
			if (!targetNode && curDragNodes[i].dropRoot === false) {
				return false;
			} else if (curDragNodes[i].parentTId && curDragNodes[i].getParentNode() !== targetNode && curDragNodes[i].getParentNode().childOuter === false) {
				return false;
			}
		}
	}
	return true;
}
function dropNext(treeId, nodes, targetNode) {
	var pNode = targetNode.getParentNode();
	if (pNode && pNode.dropInner === false) {
		return false;
	} else {
		for (var i=0,l=curDragNodes.length; i<l; i++) {
			var curPNode = curDragNodes[i].getParentNode();
			if (curPNode && curPNode !== targetNode.getParentNode() && curPNode.childOuter === false) {
				return false;
			}
		}
	}
	return true;
}
var log, className = "dark", curDragNodes, autoExpandNode;
function beforeDrag(treeId, treeNodes) {
	className = (className === "dark" ? "":"dark");
	for (var i=0,l=treeNodes.length; i<l; i++) {
		if (treeNodes[i].drag === false) {
			curDragNodes = null;
			return false;
		} else if (treeNodes[i].parentTId && treeNodes[i].getParentNode().childDrag === false) {
			curDragNodes = null;
			return false;
		}
	}
	curDragNodes = treeNodes;
	return true;
}
function beforeDragOpen(treeId, treeNode) {
	autoExpandNode = treeNode;
	return true;
}
function beforeDrop(treeId, treeNodes, targetNode, moveType, isCopy) {
	if(targetNode.parentTId!=treeNodes[0].parentTId){
		//$.ligerDialog.warn("只能平级移动改变顺序！")
		return false;
	}
	if(moveType=="inner"){
		//$.ligerDialog.warn("只能平级移动改变顺序！")
		return false;
	}
	var parentNode = targetNode.getParentNode();
	if(parentNode.sort==""||parentNode.sort==undefined||parentNode.sort==null){
		$.ligerDialog.warn("父节点顺序不能为空！");
		return false;
	}
	className = (className === "dark" ? "":"dark");
}
function onDrag(event, treeId, treeNodes) {
	className = (className === "dark" ? "":"dark");
}
function onDrop(event, treeId, treeNodes, targetNode, moveType, isCopy) {
	className = (className === "dark" ? "":"dark");
	var sortIds="";
	if(!targetNode){
		return;
	}
	for(var i in targetNode.getParentNode().children){
		sortIds+=targetNode.getParentNode().children[i].id+",";
	}
	if(sortIds!=""){
		$.getJSON("rbac/org/adjustSort.do",{sortIds:sortIds},function(res){
			
		})
	}
}
function onExpand(event, treeId, treeNode) {
	if (treeNode === autoExpandNode) {
		className = (className === "dark" ? "":"dark");
	}
}