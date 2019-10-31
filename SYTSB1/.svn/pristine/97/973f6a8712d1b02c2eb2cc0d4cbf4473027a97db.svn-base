<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>资料确认</title>
	<%@include file="/k/kui-base-list.jsp"%>
	<%@page import="com.khnt.security.util.SecurityUtil"%>
	<%@page import="com.khnt.security.CurrentSessionUser"%>
	<%@page import="com.khnt.rbac.impl.bean.User"%>
	<script type="text/javascript" src="pub/bpm/js/util.js"></script>
	<script type="text/javascript" src="pub/worktask/js/worktask.js"></script>   
	<script type="text/javascript">
		var qmUserConfig = {
				sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	// 默认值，可自定义
	            sp_fields:[
						   {name:'emp_name',compare:'like'},
	                       {name:'WORK_DEPARTMENT_NAME',compare:'like'},
	                       {name:'EMP_POSITION',compare:'like'},
	                       {name:'ischeck',compare:'like'},
	                       {name:'brqr',compare:'like'},
	       				   {name:"yes_no", compare:'like'}
	            ],
				tbar:[ 
		            ],
		            listeners: {
		                selectionChange : function(rowData,rowIndex){
		                },
		        		rowDblClick :function(rowData,rowIndex){
		        			detail(rowData.id);
		        		}
		            }
		};
	</script>
<div class="item-tm" id="titleElement">

<body>
	
	<qm:qm pageid="TJY2_RL_ZLQR" singleSelect="true">
	</qm:qm> 
</body>
</html>