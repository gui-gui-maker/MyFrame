<%@page import="java.util.Map"%>
<%@page import="com.khnt.rbac.impl.bean.Role"%>
<%@page import="com.khnt.utils.StringUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>报告领取</title>
<%@include file="/k/kui-base-list.jsp"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">
var userId = "<sec:authentication property='principal.id' htmlEscape='false' />"


	var	bar = [/* { text:'审核', id:'audit',icon:'submit', click: audit} */];
	             
	var	step_name="报告领取";





	var qmUserConfig = {
		sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:100},	// 默认值，可自定义
		sp_fields:[
			
	    ],
		tbar:bar,
        listeners: {
			selectionChange :function(rowData,rowIndex){
				selectionChange();
			},
			rowAttrRender : function(rowData, rowid) {

        	}
		}
	};
	
	// 行选择改变事件
		function selectionChange() {
	   		var count = Qm.getSelectedCount(); // 行选择个数
	     	if(count == 1){
	            Qm.setTbar({audit:true});            	
	 		}else if(count > 1){
	       		Qm.setTbar({audit:false});
	    	}else{
	    		Qm.setTbar({audit:false});
	    	}
		}

	
	// 刷新Grid
	function submitAction() {
   		Qm.refreshGrid();
	}
	
	function audit(){
		
	}
</script>
</head>

<body>
	<%
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		Map<String, String> roles = user.getRoles();
		String role_name = "";
		String role_id = "";
		for(String roleid : roles.keySet()){
			Object obj = roles.get(roleid);
			if(StringUtil.isNotEmpty(role_name)){
				role_name+= ",";
			}
			role_name+= obj;
			if("大厅管理人员".equals(obj)){
				role_id = roleid;
			}
		}
		String userId = user.getId();
	%>
	<qm:qm pageid="search_device_com" singleSelect="true">
		<qm:param name="com_id" value="${param.com_id }" compare="=" />
	</qm:qm>
	<!-- 
	<script type="text/javascript">
		Qm.config.columnsInfo.area_id.binddata=<u:dict sql="select id,parent_id pid,regional_code code, regional_name text from V_AREA_CODE where ID in ('510104','510106','510109','510122','510189')"></u:dict>;
	</script>
	 -->
</body>
</html>