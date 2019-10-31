<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
	var ztree;
	var setting = {
		data: {
			key: {
				name: "text",
				url:""
			}
		},
		callback: {
			onNodeCreated:function(event, treeId, treeNode){
				var treeObj = $.fn.zTree.getZTreeObj(treeId);
				if(treeNode.image!="null" && treeNode.image!="" && treeNode.image!=undefined){
					treeNode.icon = treeNode.image;
					treeObj.updateNode(treeNode)
				}
			}
		}
	};
	$(function() {
		var type="0,1,2";
		$.getJSON("rbac/user/getUserResources.do",{userId:"${param.userId}",type:type},function(data){
			var strData = JSON.stringify(data);
			strData = strData.replace(/isexpand/g, "open");
			var zNodes = eval(strData)
			ztree = $.fn.zTree.init($("#tree1"), setting, zNodes);
		});
	});
</script>
</head>
<body class="p5">
			<ul id="tree1" class="ztree"></ul>
</body>
</html>
