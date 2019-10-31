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



	var qmUserConfig = {
			sp_fields:[
	    ],
		//tbar:bar,
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
	     	/* if(count == 1){
	            Qm.setTbar({history: true, flow_note: true, showReport: true, input: true, printTags:true,printTagsE:true, del: true, download:true});            	
	 		}else if(count > 1){
	       		Qm.setTbar({history: true, flow_note: false, showReport: false, input: true, printTags:true,printTagsE:true, del: true, download:false});
	    	}else{
	    		Qm.setTbar({history: true, flow_note: false, showReport: false, input: false, printTags:false, printTagsE:false, del: false, download:false});
	    	} */
		}

	// 刷新Grid
	function submitAction() {
   		Qm.refreshGrid();
	}
	
	
</script>
</head>

<body>

	<qm:qm pageid="integral_total" singleSelect="false">
	</qm:qm>
	<!-- 
	<script type="text/javascript">
		Qm.config.columnsInfo.area_id.binddata=<u:dict sql="select id,parent_id pid,regional_code code, regional_name text from V_AREA_CODE where ID in ('510104','510106','510109','510122','510189')"></u:dict>;
	</script>
	 -->
</body>
</html>