<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>报告归档</title>
<%String userId=SecurityUtil.getSecurityUser().getId();%>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">
	var userId = "<sec:authentication property='principal.id' htmlEscape='false' />"
	var flow_note_name = api.data.flow_note_name;
	var bar = [];
	
		if(flow_note_name=="报告录入"){
			bar = [{
				text : '删除',
				id : 'del',
				icon : 'delete',
				click : del
			} ];
		}
	
	
	var step_name = "报告归档";
	var qmUserConfig = {
		sp_defaults : {
			columnWidth : 0.3,
			labelAlign : 'right',
			labelSeparator : '',
			labelWidth : 100
		}, // 默认值，可自定义
		sp_fields : [ {
			name : "bhg_name",
			compare : "="
		}, {
			name : "bhg_value",
			compare : "like"
		}, {
			name : "last_mdy_uname",
			compare : "like"
		} ],
		tbar : bar,
		listeners : {
			selectionChange : function(rowData, rowIndex) {
				var count = Qm.getSelectedCount();//行选择个数
				Qm.setTbar({
					del : count > 0
				}); 
			}/* ,
			afterQmReady : function() {
				Qm.setCondition([ {
					name : "activity_name",
					compare : "=",
					value : step_name
				}, {
					name : "handler_id",
					compare : "=",
					value : userId
				}
				]);
				Qm.searchData();
			},
			 */
		}
	};

	function del(){
		  $.del("确定删除?",
		    		"reportBHGRecordAction/del.do",
		    		{"ids":Qm.getValuesByName("id").toString()});
  }
	
	// 刷新Grid
	function submitAction() {
		Qm.refreshGrid();
	}
</script>
</head>
<body>
	<qm:qm pageid="error_resource_lsit"  singleSelect="false" >
		<qm:param name="fk_inspection_info_id" value="${param.id}" compare="=" />
	</qm:qm>
</body>
</html>
