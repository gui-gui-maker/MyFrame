<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>demo-list</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
	var qmUserConfig = {
		sp_defaults:{columnWidth:0.33,labelAlign:'right',labelSeparator:''},//可以自己定义 layout:column,float,
		sp_fields: [ 
			{name: "driver",compare: "like"}
		],
		<tbar:toolBar type="tbar">
		</tbar:toolBar>,
		listeners: {
			rowClick: function(rowData, rowIndex) {
			},
			rowDblClick: function(rowData, rowIndex) {
				detail();
			},
			selectionChange: function(rowData, rowIndex) {
				var count = Qm.getSelectedCount();
				Qm.setTbar({
					detail: count==1,
					edit: count==1,
					del: count>0
				});
			}
		}
	};
	function add() {
		top.$.dialog({
			width: 500,
			height: 350,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "新增",
			content: 'url:app/oa/car/driver_plan_detail.jsp?pageStatus=add'
		});
	}
	function edit() {
		var id = Qm.getValueByName("id");
		top.$.dialog({
			width: 500,
			height: 350,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "修改",
			content: 'url:app/oa/car/driver_plan_detail.jsp?id=' + id + '&pageStatus=modify'
		});
	}
	function del() {
		$.del(kFrameConfig.DEL_MSG, "oa/car/driverplan/delete.do", {
			"ids": Qm.getValuesByName("id").toString()
		});
	}
	function detail() {
		var id = Qm.getValueByName("id");
		top.$.dialog({
			width: 500,
			height: 200,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "详情",
			content: 'url:app/oa/car/driver_plan_detail.jsp?id='+id+'&pageStatus=detail'
		});
	}
</script>
</head>
<%
	String unitId = SecurityUtil.getSecurityUser().getUnit().getId();
%>
<body>
	<qm:qm pageid="oa_car_driver_plan">
		<qm:param name="unitid" value="<%=unitId %>" compare="=" />
	</qm:qm>
</body>
</html>