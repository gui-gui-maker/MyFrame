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
			{name: "name",compare: "like"}, 
			{name: "idno",compare: "like"}
		],
		listeners: {
			rowClick: function(rowData, rowIndex) {
			},
			rowDblClick: function(rowData, rowIndex) {
				detail();
			},
			selectionChange: function(rowData, rowIndex) {
			}
		}
	};
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
			content: 'url:app/oa/car/driver_detail.jsp?id='+id+'&pageStatus=detail'
		});
	}
	function driverSelect(){
		var data={};
		var id = Qm.getValueByName("id");
		var name = Qm.getValueByName("name");
		var telphone = Qm.getValueByName("telphone")
		data["id"]=id;
		data["name"]=name;
		data["telphone"]=telphone;
		return data;
	}
</script>
</head>
<%
	String unitId = SecurityUtil.getSecurityUser().getUnit().getId();
%>
<body>
	<qm:qm pageid="oa_car_driver" singleSelect="true">
		<qm:param name="unit_id" value="<%=unitId %>" compare="=" />
	</qm:qm>
</body>
</html>