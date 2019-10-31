<%@ page contentType="text/html;charset=UTF-8"%>
	<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>报告页单独审核</title>
<%@include file="/k/kui-base-list.jsp"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<script type="text/javascript">
	var w = window.screen.availWidth;
	var h = (window.screen.availHeight);
	var	bar = [
		{ text:'查看报告', id:'showReport',icon:'detail', click: showReport},"-",
		{ text:'批量审核', id:'batchCheck',icon:'search', click: batchCheck},"-",
		{ text:'单份审核', id:'check',icon:'search', click: check},"-",
		{ text:'流转过程', id:'flow_note',icon:'follow', click: getFlow}
	];
	var qmUserConfig = {
		sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:100},	// 默认值，可自定义
		sp_fields:[
			{name:"report_sn",compare:"like"},
			{name:"device_registration_code",compare:"like"},
			{name:"report_com_name",compare:"like"},
			{name:"make_units_name",compare:"like"},
			{name:"maintain_unit_name",compare:"like"},	
			{name:"enter_op_name",compare:"like"}
	    ],
		tbar:bar,
        listeners: {
			selectionChange :function(rowData,rowIndex){
     			selectionChange();
			}
		}
	};
	
	// 行选择改变事件
	function selectionChange() {
	   	var count = Qm.getSelectedCount(); // 行选择个数
	    if(count == 1){
	    	Qm.setTbar({flow_note: true, showReport: true, batchCheck: false, check: true});            	
	 	}else if(count > 1){
	       	Qm.setTbar({flow_note: false, showReport: false, batchCheck: true, check: false});
	    }else{
	    	Qm.setTbar({flow_note: false, showReport: false, batchCheck: false, check: false});
	    }
	}

	//流转过程
	function  getFlow(){
		 top.$.dialog({
	   		width : 400, 
	   		height : 700, 
	   		lock : true, 
	   		title: "流程卡",
	   		content: 'url:department/basic/getFlowStep.do?ins_info_id='+Qm.getValuesByName("id"),
	   		data : {"window" : window}
	   	});
	}
	// 查看报告
	function showReport(){	
		var id = Qm.getValueByName("id").toString();
		var report_id = Qm.getValueByName("report_type").toString();	// 报告类型
		var url = "report/query/showReport.do?id="+id+"&report_id="+report_id;
		top.$.dialog({
			width : w, 
			height : h, 
			lock : true, 
			title:"报告信息",
			content: 'url:'+url,
			data : {"window" : window}
		}).max();
	}	
	
	// 批量审核
	function batchCheck(){	
		var ids = Qm.getValuesByName("id").toString();
		var device_type = Qm.getValueByName("device_sort_code").toString().substring(0, 1);
		var count = ids.split(",");
		if(count.length>50){
			$.ligerDialog.alert("请勿一次批量操作大于50份报告！");
			return;
		}			
		top.$.dialog({
			width : w, 
			height :h, 
			lock : true, 
			title:"批量审核",
			content: 'url:app/flow/report/page/report_batch_index.jsp?ids='+ids+'&type=04&device_type='+device_type,
			data : {"window" : window}
		}).max();
	}
	
	// 单份审核报告
	function check(){
		var device_type = Qm.getValueByName("device_sort_code").toString().substring(0, 1);
		   top.$.dialog({
				width : 800, 
				height : 500, 
				lock : true, 
				title: "报告审核",
				content: 'url:department/basic/reportPageInfoLoad.do?type=04&device_type='+device_type+'&report_type='+ Qm.getValuesByName("report_type")+'&ins_info_id='+Qm.getValuesByName("id")+'&device_id='+Qm.getValueByName("fk_tsjc_device_document_id"),
				data : {"window" : window}
			}).max();
		
		
	}

	// 刷新Grid
	function refreshGrid() {
   		Qm.refreshGrid();
	}
</script>
</head>

<body>

<%
CurrentSessionUser user = SecurityUtil.getSecurityUser();
User uu = (User)user.getSysUser();
com.khnt.rbac.impl.bean.Employee e = (com.khnt.rbac.impl.bean.Employee)uu.getEmployee();
e.getId();
String userId=e.getId();
String uId = SecurityUtil.getSecurityUser().getId();
%>
	<qm:qm pageid="report_page_check">
		<qm:param name="audit_user_id" value="<%=userId%>" compare="=" />
	</qm:qm>
</body>
</html>
