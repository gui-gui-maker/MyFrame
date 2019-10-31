﻿<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%
	String checkbox = request.getParameter("checkbox");
%>
<title>机构人员列表</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
 var checkbox="${param.checkbox}";
 var isCheckbox=false;
 
 if(checkbox==0){
	 isCheckbox=true;
 }
 
 if(checkbox==1){
	 isCheckbox=false;
 }
 
 var qmUserConfig = {
	 sp_defaults:{columnWidth:1,labelAlign:'right',labelSeparator:'',labelWidth:30},//可以自己定义 layout:column,float, 
	listeners: {
	    selectionChange : function(rowData,rowIndex){
	    	if(rowData==null)return;
	    	var grid = Qm.getQmgrid();
	    	if(grid.isSelected(rowData)){
	    		parent.addUserItem(rowData.id,rowData.office_tel,rowData.name,"${param.checkbox}");
	    	}
	    	else{
	    		parent.removeUserItem(rowData.name);
	    	}
	    }
	}
 };
</script>
</head>
<body>

	<qm:qm pageid="oa_sel_" script="false" singleSelect="false"
		pagesize="100">
		<qm:param name="org_id" value="${param.orgId}" compare="llike" />
	</qm:qm>
	
</body>
</html>
