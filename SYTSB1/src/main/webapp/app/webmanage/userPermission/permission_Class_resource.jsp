<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="main_head">
<!-- 每个页面引入，页面编码、引入js，页面标题 -->
<%@include file="/k/kui-base-list.jsp"%>
<title></title>
<script type="text/javascript">
	//var api = frameElement.api, W = api.opener, D = W.document;
	var tree = null;
	$(function() {
		$("#layout1").ligerLayout({ leftWidth : 200, allowLeftCollapse : false, allowRightCollapse : false });
		tree = $("#tree1").ligerTree({
			url : '/userPermission/ClassViewTree.do',
			nodeWidth : 200,
			attribute : [ "url" ],
			autoCheckParent: true,
			onAfterAppend : init
		});
	});

	function init() {
		$.post("/userPermission/inituserClass.do", { perId : '${param.perId}' }, function(data) {
			if (data.success) {
				var tp = data.data;
				var parm = function(data) {
					for ( var i = 0; i < tp.length; i++) {
						if (data.id == tp[i].fkClasstypeId)
							return true;
					}
					return false;
				};
				tree.selectNode(parm);
			} else {
				$.ligerDialog.success(data.msg);
			}
		});
	}

	//设置资源权限
	function setUserClass() {
		var notes = tree.getChecked();
		var ids = "";
		for ( var i = 0; i < notes.length; i++) {
			ids += "|" + notes[i].data.id;
		}
		if (ids.length > 1)
			ids = ids.substring(1);
		$.post("/userPermission/setUserClassPerMission.do", { perId : '${param.perId}', classIds : ids }, function(data) {
			if (data.success) {
				$.ligerDialog.success('设置成功', function() {
                    api.close()
				})
			} else {
				$.ligerDialog.success('设置失败');
			}
		});
	}
</script>
</head>
<body>
	<div id="layout1">
		<div position="center" style="overflow: auto; height: 92%;">
			<ul id="tree1">
			</ul>
		</div>
	</div>
</body>
</html>
