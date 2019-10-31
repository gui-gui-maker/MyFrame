<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>节假日收车</title>
<%@ include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="app/oa/select/org_select.js"></script>
<script type="text/javascript">
	var qmUserConfig = {
		sp_defaults:{columnWidth:0.33,labelAlign:'right',labelSeparator:'',labelWidth:100},//可以自己定义 layout:column,float,
		sp_fields : [
				{
					name : "car_num", compare : "like"
				},{
					name : "driver", compare : "like"
				},{
					name : "manager_room_code", compare : "=",xtype:"combo",onBeforeOpen:showDepartList
				}
		], 
		listeners : {
			rowClick : function(rowData, rowIndex) {
			}, rowDblClick : function(rowData, rowIndex) {
			}, selectionChange : function(rowData, rowIndex) {
				//selectionChange();
			}, rowAttrRender : function(rowData, rowid) {
				if (rowData.change_num != "")
					rowData.car_num = rowData.car_num + "(" + rowData.change_num + ")";
			}
		}
	};
	//显示部门选择列表
	function showDepartList(){
		var unitId = "<sec:authentication property="principal.unit.id" />";
		var unitName = "<sec:authentication property="principal.unit.orgName" htmlEscape="false"/>";
		$(this).data('unitId',unitId);
		$(this).data('unitName',unitName);
		showOrgList.call(this);
	}
</script>
</head>
<%
	String unitId = SecurityUtil.getSecurityUser().getUnit().getId();
%>
<body>
	<qm:qm pageid="oa_car_unpack" script="false" singleSelect="true">
		<qm:param name="org_code" value="<%=unitId %>" compare="="/>
	</qm:qm>
</body>
</html>