
//选中父节点  
function checkParent(node) {
    /*$('#tree').treeview('checkNode', node.nodeId, {
        silent: true
    });*/
	var pNode = $('#tree').treeview('getParent', node.nodeId);
	if(pNode){
		var siblings = $('#tree').treeview('getSiblings', node.nodeId);
		var flag = true;
		for(var i=0;i<siblings.length;i++){
			if(!siblings[i].state.checked){
				flag = false;
				break;
			}
		}
		if(flag){
			$('#tree').treeview('checkNode', pNode.nodeId, {
				silent: true
			});
		}
	}
}

//级联选中所有子节点  
function checkAllSon(node) {
	$('#tree').treeview('checkNode', node.nodeId, {
		silent: true
	});
	//node.state.checked=true;
	if (node.nodes != null && node.nodes.length > 0) {
		for (var i in node.nodes) {
			checkAllSon(node.nodes[i]);
		}
	}
}
//取消父节点  
function uncheckParent(node) {
    /*$('#tree').treeview('uncheckNode', node.nodeId, {
        silent: true
    });*/
    var pNode = $('#tree').treeview('getParent', node.nodeId);
    if(pNode){
    	/*$('#tree').treeview('uncheckNode', pNode.nodeId, {
    		silent: true
    	});*/
    	pNode.state.checked=false;
    }
}
//级联取消所有子节点  
function uncheckAllSon(node) {
    $('#tree').treeview('uncheckNode', node.nodeId, {
        silent: true
    });
	//node.state.checked=false;
    if (node.nodes != null && node.nodes.length > 0) {
        for (var i in node.nodes) {
            uncheckAllSon(node.nodes[i]);
        }
    }
}
