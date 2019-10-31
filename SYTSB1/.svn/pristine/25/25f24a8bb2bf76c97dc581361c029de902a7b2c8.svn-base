<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>报告归档</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">
	var userId = "<sec:authentication property='principal.id' htmlEscape='false' />"

	var bar = [ {
		text : '盖章',
		id : 'pdf',
		icon : 'save',
		click : reportPdf
	}];
	
	var step_name = "报告归档";
	var qmUserConfig = {
		sp_defaults : {
			columnWidth : 0.25,
			labelAlign : 'right',
			labelSeparator : '',
			labelWidth : 100
		}, // 默认值，可自定义
		sp_fields : [{
				name : "report_sn",
				compare : "like"
			}, {
				name : "device_registration_code",
				compare : "like"
			}, {
				name : "report_com_name",
				compare : "like"
			}
		],
		tbar : bar,
		listeners : {
			selectionChange : function(rowData, rowIndex) {
				var count = Qm.getSelectedCount();//行选择个数
				Qm.setTbar({
					pdf : count > 0
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

	//流转过程
	function getFlow() {
		top.$.dialog({
			width : 400,
			height : 700,
			lock : true,
			title : "流程卡",
			content : 'url:department/basic/getFlowStep.do?ins_info_id='
					+ Qm.getValuesByName("id"),
			data : {
				"window" : window
			}
		});
	}

	function reportPdf() {
		var ids = Qm.getValuesByName("id").toString();
		var w=window.screen.availWidth;
		var h=(window.screen.availHeight);
		top.$.dialog({
			width : w, 
			height :h, 
			lock : true, 
			title:"盖章",
			content: 'url:app/flow/export/report_export_index.jsp?id='+ids+'&acc_id='+Qm.getValuesByName("activity_id").toString()+'&flow_num='+Qm.getValueByName("flow_num_id").toString()+'&printType=0',
			data : {"window" : window}
		}).max();
	}
	
	
	// 刷新Grid
	function submitAction() {
		Qm.refreshGrid();
	}
	
</script>
</head>
<%
	CurrentSessionUser user = SecurityUtil.getSecurityUser();
	String userId = user.getId();
	String org_code = ((com.khnt.rbac.impl.bean.Org)(user.getDepartment())).getProperty();
%>
<body>
	<qm:qm pageid="report_print_pdf" script="false" singleSelect="false" seachOnload="true">
		<qm:param name="activity_name" value="打印报告" compare="=" />
		<qm:param name="handler_id" value="<%=userId %>" compare="=" />
		<qm:param name="export_pdf" value="" compare="=" />		
	</qm:qm>
	<script type="text/javascript">
	// 根据 sql或码表名称获得Json格式数据
	Qm.config.columnsInfo.org_id.binddata=<u:dict sql="select id code, ORG_NAME text from SYS_ORG where ORG_CODE like 'jd%' or ( ORG_CODE like 'cy%' and ORG_CODE != 'cy4_1' and ORG_CODE != 'cy8') order by orders "></u:dict>;
</script>
</body>
</html>
