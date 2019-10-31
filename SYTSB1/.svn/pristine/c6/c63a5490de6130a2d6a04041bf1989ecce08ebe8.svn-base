<%@page import="com.lsts.IdFormat"%>
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
<title>系统自动分配报告签发日志列表</title>
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
	
	String info_ids = request.getParameter("info_ids");
%>
<script type="text/javascript">
	var w = window.screen.availWidth;
	var h = window.screen.availHeight;
	var qmUserConfig = {
		sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:100},	// 默认值，可自定义
		sp_fields : [ 
			{name:"report_com_name", id:"report_com_name", compare:"like"},
			{name:"report_sn", id:"report_sn", compare:"like"},
			{name:"op_user_name", id:"op_user_name", compare:"like"}
		],
		tbar : [
			{ text : '单份撤回', id:'qfBack', icon:'back', click: qfBack},"-",
			{ text : '批量撤回', id:'qfBacks', icon:'back', click: qfBacks},"-",
			{ text : '流转过程', id:'turnHistory', icon:'follow', click: turnHistory}
		],
		listeners : {
			rowClick : function(rowData, rowIndex) {
			},
			rowDblClick : function(rowData, rowIndex) {
				createReport();
			},
			selectionChange : function(rowData, rowIndex) {
				selectionChange();
			},
			rowAttrRender : function(rowData, rowid) {
				var fontColor="black";
		    	return "style='color:"+fontColor+"'";
			}
		}
	};

	//行选择改变事件
	function selectionChange() {
		var count = Qm.getSelectedCount();//行选择个数
		if (count == 0) {
			Qm.setTbar({
				turnHistory : false,
				qfBack : false,
				qfBacks : false
			});
		} else if (count == 1) {
			Qm.setTbar({
				turnHistory : true,
				qfBack : true,
				qfBacks : false
			});
		} else {
			Qm.setTbar({
				turnHistory : false,
				qfBack : false,
				qfBacks : true
			});
		}
	}


	// 流转过程
	function turnHistory() {
		top.$.dialog({
			width : 400,
			height : 700,
			lock : true,
			title : "流程卡",
			content : 'url:department/basic/getFlowStep.do?ins_info_id='
					+ Qm.getValueByName("business_id"),
			data : {
				"window" : window
			}
		});
	}
	
	function qfBack(){
		var canBack = true;
		var flow_note_name = Qm.getValueByName("flow_note_name");	// 当前流程
		var op_user_name = Qm.getValueByName("op_user_name");		// 提交人

		if(flow_note_name != "报告签发" && flow_note_name != "报告审批"){
			canBack = false;
			$.ligerDialog.error("只能撤回待签发的报告，请重新选择！");
			return;
		}

		if(op_user_name != "<%=user.getName() %>"){
			canBack = false;
			$.ligerDialog.error("只能撤回自己提交的报告，请重新选择！");
			return;
		}

		if(canBack){
			$.ligerDialog.confirm('确定撤回所选报告？', function (yes) { 	
				if(yes){
					$("body").mask("系统正在努力为您撤回报告，请耐心等待！");
					$.getJSON('department/basic/qf_backs.do?infoId='+Qm.getValueByName("business_id")+'&flow_num='+Qm.getValueByName("flow_note_id")+'&acc_id='+Qm.getValueByName("activity_id")+'&device_types='+Qm.getValuesByName("device_type"),function(data){
						$("body").unmask();
						if(data.success){
							top.$.notice("撤回成功！");
							refreshGrid();
						}else{
							$.ligerDialog.error(data.msg);
						}
					});
				}
			});
		}
	}
	
	function qfBacks(){
		var canBack = true;
		var flowArr = Qm.getValuesByName("flow_note_name");	// 当前流程
		var op_user_name = Qm.getValuesByName("op_user_name");	// 提交人

		for(var i=0;i<flowArr.length-1;i++){
			if(flowArr[i] != "报告签发" && flowArr[i] != "报告审批"){
				canBack = false;
				$.ligerDialog.error("只能撤回待签发的报告，请重新选择！");
				return;
			}
		}
		for(var i=0;i<op_user_name.length-1;i++){
			if(op_user_name[i] != "<%=user.getName() %>"){
				canBack = false;
				$.ligerDialog.error("只能撤回自己提交的报告，请重新选择！");
				return;
			}
		}
		
		if(canBack){
			$.ligerDialog.confirm('确定撤回所选报告？', function (yes) { 	
				if(yes){
					$("body").mask("系统正在努力为您撤回报告，请耐心等待！");
					$.getJSON('department/basic/qf_backs.do?infoId='+Qm.getValuesByName("business_id")+'&flow_num='+Qm.getValueByName("flow_note_id")+'&acc_id='+Qm.getValuesByName("activity_id")+'&device_types='+Qm.getValuesByName("device_type"),function(data){	
						$("body").unmask();
						if(data.success){
							refreshGrid();
							top.$.notice("撤回成功！");
						}else{
							$.ligerDialog.error(data.msg);
						}
					});
				}
			});
		}
	}
	
	function refreshGrid() {
		Qm.refreshGrid();
	}
</script>
</head>
<body>
	<qm:qm pageid="report_issue_list">
		 <%
			if(!"100082".equals(org_id) && !"100029".equals(org_id) && !"100026".equals(org_id) && !"100028".equals(org_id)){
				%>
				<qm:param name="check_unit_id" value="<%=org_id %>" compare="=" />
				<%
			}
			
			if(StringUtil.isNotEmpty(info_ids)){
				String ids = IdFormat.formatIdStr(info_ids);
				ids = "("+ids+")";
				%>
				<qm:param name="business_id" value="<%=ids %>" compare="in" dataType="user"/>
				<%
			}
		%>
	</qm:qm>
	<script type="text/javascript">
	Qm.config.columnsInfo.check_unit_id.binddata=<u:dict sql="select id code, ORG_NAME text from SYS_ORG where ORG_CODE like 'jd%' and ORG_CODE!='jd6' order by orders "></u:dict>;
	</script>
</body>
</html>
