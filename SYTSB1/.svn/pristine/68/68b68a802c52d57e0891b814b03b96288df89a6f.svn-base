<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="main_head">
<%@include file="/k/kui-base-list.jsp"%>
<title></title>
<script type="text/javascript">
	$(function() {
		$("#layout1").ligerLayout({
			leftWidth : 250,
			allowLeftCollapse : false,
			allowRightCollapse : false
		});
		$("#tree1").ligerTree(
				{
					checkbox : false,
					url : 'bpm/flowType/viewTree.do?id=top',
					nodeWidth : 250,
					attribute : [ "url" ],
					onSelect : function(node) {
						$("#rightFrame").attr(
								"src",
								"pub/bpm/flowAction_list.jsp?flowtype="
										+ node.data.id);
					}
				});
	});
</script>
</head>
<body>
	<div id="layout1">
		<div position="left" title="流程表单">
			<ul id="tree1">
			</ul>
		</div>
		<div position="center">
			<iframe marginwidth="0" id="rightFrame" marginheight="0" frameborder="0" valign=top src="" name="rtree" width=100%
				height=100% scrolling="no" allowTransparency></iframe>
		</div>
	</div>
</body>
</html>