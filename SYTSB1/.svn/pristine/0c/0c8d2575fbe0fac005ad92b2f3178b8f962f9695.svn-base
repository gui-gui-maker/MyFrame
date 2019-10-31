<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="main_head">
<%@include file="/k/kui-base-list.jsp"%>
<title></title>
<script type="text/javascript">
	var treeManager;
	$(function() {
		treeManager = $("#tree1").ligerTree({
			checkbox : false,
			url : 'bpm/flowType/viewTree.do?id=top',
			onAfterAppend : function(parentNode, newdata) {
				tree.selectNode("top")
			}
		});
	});
	api.button({
		id : "ok",
		icon : "save",
		name : "确定", 
		callback : function(){
			var node = treeManager.getSelected();
			if(node){
				api.data.callback(node.data);
				api.close();
			}
			else{
				top.$.notice("请选择一个类别！",2);
				return false;
			}
		}
	});
	api.button({
		id : "cancel",
		name : "取消", 
		icon : "cancel",
		callback : function(){
			api.close();
		}
	});
</script>
</head>
<body class="p5">
	<ul id="tree1"></ul>
</body>
</html>
