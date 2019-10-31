﻿<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page
	import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@page import="com.khnt.security.CurrentSessionUser" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>工作流演示-请假待审批</title>
<%@include file="/k/kui-base-list.jsp" %>
<script type="text/javascript" src="pub/bpm/js/util.js"></script>
<script type="text/javascript" src="pub/worktask/js/worktask.js"></script>
<script type="text/javascript">
	var qmUserConfig = {
		sp_fields: [
   			{name: "person", compare: "like"},
   			{group: [
   					{label: "休假日期从",name: "starts_date", compare: ">="},
   					{label: "到", name: "starts_date", compare: "<=", labelAlign: "center", labelWidth: 20}
   			]},
   			{name: "types", compare: "="},
   			{name: "creater", compare: "like"},
   			{name: "status", compare: "="},
   			{name: "dept", compare: "like"}
   		],
		tbar: [{
			text: '处理',
			id: 'deal',
			icon: 'modify',
			click: function () {
				openWorktask({id:Qm.getValueByName("id"),title:"请假审批",width:750,height:450});
			}
		}],
		listeners: {
			selectionChange: function (rowData, rowIndex) {
				var count = Qm.getSelectedCount();//行选择个数
				Qm.setTbar({
					deal: count == 1
				});
			},
			rowAttrRender: function (item, rowid) {
				if (item.status == "0")
					return 'style="color:#990000"';
				else if (item.status == "2")
					return 'style="color:red"'
				else if (item.status == "3")
					return 'style="color:green"';
				else if (item.status == "4")
					return 'style="color:gray"';
			}
		}
	};
</script>
</head>
<body>
	<qm:qm pageid="demo_bpm_handle" script="false" singleSelect="true" />
</body>
</html>
