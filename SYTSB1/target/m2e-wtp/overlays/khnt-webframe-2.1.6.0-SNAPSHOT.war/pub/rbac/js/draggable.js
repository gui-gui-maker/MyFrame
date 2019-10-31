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

	if(treeNodes[0].type=='2'&&targetNode.type=='2'&&moveType=='inner'){
		$.ligerDialog.alert("不能将按钮资源移动到按钮资源内部！")
		return false;
	}
	if(treeNodes[0].type=='2'&&targetNode.type=='2'&&moveType!='inner'&&targetNode.getParentNode()!=treeNodes[0].getParentNode()){
		for(var i in targetNode.getParentNode().children){
			if(targetNode.getParentNode().children[i].code==treeNodes[0].code){
				$.ligerDialog.alert("该资源下已经存在相同编号的按钮资源！")
				return false;
			}
		}
	}
	if(treeNodes[0].type=='2'&&targetNode.type!='2'){
		for(var i in targetNode.getParentNode().children){
			if(targetNode.children[i].code==treeNodes[0].code){
				$.ligerDialog.alert("该资源下已经存在相同编号的按钮资源！")
				return false;
			}
		}
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
	if(!targetNode.isParent){
		for(var i in targetNode.getParentNode().children){
			sortIds+=targetNode.getParentNode().children[i].id+",";
		}
	}else{
		if(moveType=="inner"){
			for(var i in targetNode.children){
				sortIds+=targetNode.children[i].id+",";
			}
		}else{
			for(var i in targetNode.getParentNode().children){
				sortIds+=targetNode.getParentNode().children[i].id+",";
			}
		}
	}
	if(sortIds!=""){
		//这里做后台操作
		$.getJSON("rbac/resource/adjustResource.do",{sourceId:treeNodes[0].id,targetId:targetNode.id,moveType:moveType,sortIds:sortIds},function(res){
			if(res.success){
				$.getJSON("rbac/resource/refreshCache.do",function(data){
					if(data.success){
						return true;
					}else{
						return false;
					}
				})
			}else {
				return false;
			}
		})
	}
}
function onExpand(event, treeId, treeNode) {
	if (treeNode === autoExpandNode) {
		className = (className === "dark" ? "":"dark");
	}
}
