<%@page import="util.TS_Util"%>
<%@page import="com.khnt.rbac.impl.bean.Org"%>
<%@page import="com.khnt.rbac.impl.bean.Employee"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.utils.StringUtil"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="main_head">
<title>移动端原始记录数据校核列表</title>
<%@include file="/k/kui-base-list.jsp"%>
<%
	CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
	User user = (User)curUser.getSysUser();
	Org org = (Org)TS_Util.getCurOrg(curUser);
	Employee e = (Employee)user.getEmployee();

	String org_code = org.getOrgCode();	// 部门编号
	String org_id = org.getId();
	String user_id = user.getId();
	String emp_id = e.getId();
	String type = request.getParameter("type");
%>
<script type="text/javascript">
	var w = window.screen.availWidth;
	var h = window.screen.availHeight;
	var qmUserConfig = {
		sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:100},	// 默认值，可自定义
		sp_fields : [ 
			{name:"device_registration_code", id:"device_registration_code", compare:"like"},
			{name:"report_com_name", id:"report_com_name", compare:"like"},
			{name:"check_unit_id", id:"check_unit_id", compare:"="},
			{group:[
				{name:"advance_time", id:"advance_time", compare:">="},
				{label:"到", name:"advance_time", id:"advance_time1", compare:"<=", labelAlign:"center", labelWidth:20}
			]},
			{name:"checkstatus", id:"check_status", compare:"="},
			{name:"info_status", id:"info_status", compare:"="}
		],
		tbar : [
			{ text:'流转过程', id:'turnHistory',icon:'follow', click: turnHistory}
		],
		listeners : {
			rowClick : function(rowData, rowIndex) {
			},
			rowDblClick : function(rowData, rowIndex) {
			},
			selectionChange : function(rowData, rowIndex) {
				selectionChange();
			},
			rowAttrRender : function(rowData, rowid) {
				 
			}
		}
	};

	//行选择改变事件
	function selectionChange() {
		var count = Qm.getSelectedCount();
		Qm.setTbar({
			turnHistory : count==1
      });
	}
	// 流转过程
	function turnHistory(){	
		top.$.dialog({
   			width : 400, 
   			height : 700, 
   			lock : true, 
   			title: "流程卡",
   			content: 'url:department/basic/getFlowStep.do?ins_info_id='+Qm.getValueByName("id"),
   			data : {"window" : window}
   		});
	}
	function refreshGrid() {
		Qm.refreshGrid();
	}
</script>
</head>
<body>
	<qm:qm pageid="record_checked">
		<%
			if(org_code.contains("jd")){
				if(StringUtil.isNotEmpty(emp_id)){
					%>
					<qm:param name="confirm_id" value="<%=emp_id %>" compare="=" />
					<%
				}
			}
		%>
	</qm:qm>
	<script type="text/javascript">
	Qm.config.columnsInfo.check_unit_id.binddata=<u:dict sql="select id code, ORG_NAME text from SYS_ORG where ORG_CODE like 'jd%' and ORG_CODE!='jd6' order by orders "></u:dict>;
	Qm.config.columnsInfo.info_status.binddata = [
		{id: '0', text: '未生成报告'},
		{id: '1', text: '已生成报告'},
		{id: '2', text: '已修改，未生成报告'}
	];
	Qm.config.columnsInfo.checkstatus.binddata = [
		{id: '0', text: '正常检验'},
		{id: '1', text: '中止检验'}
	];
	</script>
</body>
</html>
