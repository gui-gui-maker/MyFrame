﻿<%@page
	import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@page import="com.khnt.security.CurrentSessionUser" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>工作流演示</title>
<%@include file="/k/kui-base-list.jsp" %>
<script type="text/javascript" src="pub/bpm/js/util.js"></script>
<script type="text/javascript">
	var qmUserConfig = {
		tbar: [ "-", {
			text: '详情',
			id: 'detail',
			icon: 'detail',
			click: function () {
				top.$.dialog({
					width: 600,
					height: 300,
					lock: true,
					title: "测试流程业务",
					content: "url:app/demo/flow/service_view.jsp?id=" + Qm.getValueByName("id")
				});
			}
		} , "-", {
			text: '新增',
			id: 'add',
			icon: 'add',
			click: function () {
				top.$.dialog({
					width: 600,
					height: 300,
					lock: true,
					title: "测试流程业务",
					content: "url:app/demo/flow/test_detail.jsp?status=add"
				});
			}
		} , "-", {
			text: '修改',
			id: 'modify',
			icon: 'modify',
			click: function () {
				top.$.dialog({
					width: 600,
					height: 300,
					lock: true,
					title: "测试流程业务",
					content: "url:app/demo/flow/test_detail.jsp?status=modify&id=" + Qm.getValueByName("id")
				});
			}
		} , "-", {
			text: '提交',
			id: 'submit',
			icon: 'submit',
			click: function () {

			}
		} ],
		listeners: {
			selectionChange: function (rowData, rowIndex) {
				var count = Qm.getSelectedCount();//行选择个数
				Qm.setTbar({
					process: count == 1
				});
			}
		}
	};
</script>
</head>
<body>
	<qm:qm pageid="test_flow" script="false" singleSelect="true"></qm:qm>
</body>
</html>
