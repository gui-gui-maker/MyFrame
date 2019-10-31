<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="main_head">
<!-- 每个页面引入，页面编码、引入js，页面标题 -->
<%@include file="/k/kui-base-list.jsp"%>
<title></title>
<script type="text/javascript">
	var actionNodeID = "";
	/***新增成功和修改成功的返回函数**/
	var manager = null;
	$(function() {
		$("#layout1").ligerLayout({
			leftWidth : 250,
			allowLeftCollapse : false,
			allowRightCollapse : false
		});
		manager = $("#tree1").ligerTree({
			checkbox : false,
            url : 'rbac/org/orgTree.do?orgid=<sec:authentication property="principal.unit.id" />',
			attribute : [ "url" ],
            onAfterAppend:function (parentNode, newdata) {
                manager.selectNode(newdata);			//初始化
            },
			onSelect : function(node) {
				$("#rightFrame").attr("src","app/webmanage/userPermission/user_Authorized_list.jsp?orgid=" + node.data.id);
			}
		});
	});
</script>
</head>
<body>
	<div id="layout1">
		<div position="left" title="组织机构" style="overflow: auto; height:96%;">
			<ul id="tree1">
			</ul>
		</div>
		<div position="center" style="overflow: auto; height: 92%;">
			<iframe marginwidth="0" id="rightFrame" marginheight="0"
				frameborder="0" valign=top src="" name="rightFrame" width=100%
				height=100% scrolling="no" allowTransparency></iframe>
		</div>
	</div>
</body>
</html>
