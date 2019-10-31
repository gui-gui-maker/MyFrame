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
			{name:"device_name", id:"device_name", compare:"like"},
			{name:"internal_num", id:"internal_num", compare:"like"},	
			{name:"report_com_name", id:"report_com_name", compare:"like"},					
			<%
			if(!org_code.contains("cy")){
				%>
				{name:"check_unit_id", id:"check_unit_id", compare:"="},
				<%
			}
			%>
			{group:[
				{name:"advance_time", id:"advance_time", compare:">="},
				{label:"到", name:"advance_time", id:"advance_time1", compare:"<=", labelAlign:"center", labelWidth:20}
			]}
		],
		tbar : [
			{ text:'校核', id:'batchCheck',icon:'search', click: batchCheck2},'-', 
			{ text : '预览', id : 'showRecord', click : showRecord, icon : 'detail' },"-",
			{ text : '流转过程', id:'turnHistory',icon:'follow', click: turnHistory},"-",
			{ text:'历史纪录', id:'history',icon:'view', click: history}
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
        var is_report_confirm = Qm.getValuesByName("is_report_confirm");
        var allowCheck =  true;
        for(var i=0;i<is_report_confirm.length;i++){
       	 if(is_report_confirm[i]=="通过"){
       		allowCheck =  false;
       	 }
        }
        Qm.setTbar({
        	batchCheck : count>0&&allowCheck,
			check : count==1&&allowCheck,
			showRecord : count==1,
			showReport : count==1,
			turnHistory : count==1,
			history : true
      });
	}
	// 查看原始记录
	function showRecord() {
		var id = Qm.getValueByName("id").toString();
		var report_id = Qm.getValueByName("report_id").toString();	// 报告类型
		var url = "report/item/record/showGcRecord.do?id="+id+"&report_id="+report_id;
		top.$.dialog({
			width : w, 
			height : h, 
			lock : true, 
			title:"查看原始记录",
			content: 'url:'+url,
			data : {"window" : window}
		}).max();
	}
	
	// 批量校核
	function batchCheck2(){		
		var ids = Qm.getValuesByName("id").toString();
		var count = ids.split(",");
		if(count.length>50){
			$.ligerDialog.alert("请勿一次批量操作大于50份原始记录！");
			return;
		}
		var w=window.screen.availWidth;
		var h=(window.screen.availHeight);	
		top.$.dialog({
			width : w, 
			height :h, 
			lock : true, 
			title:"批量校核",
			content: 'url:app/tanker/gc_record_batch_index.jsp?ids='+ids,
			data : {"window" : window}
		}).max();
	}
	
	// 历史纪录
	function history(){	
		top.$.dialog({ 	
			width : w, 
			height : h, 
			lock : true, 
			title:"历史纪录",
			content: 'url:app/tanker/gc_record_check_history.jsp',
			data : {"window" : window}
		}).max();			
	}
	
	// 流转过程
	function turnHistory() {
		top.$.dialog({
			width : 400,
			height : 700,
			lock : true,
			title : "流程卡",
			content : 'url:department/basic/getFlowStep.do?ins_info_id='
					+ Qm.getValueByName("id"),
			data : {
				"window" : window
			}
		});
	}
	
	function refreshGrid() {
		Qm.refreshGrid();
	}
</script>
</head>
<body>
	<qm:qm pageid="gc_record_check_list">
		<%
			if(StringUtil.isNotEmpty(emp_id) && !"100028".equals(org_id) && !"100027".equals(org_id)){
				%>
				<qm:param name="enter_op_id" value="<%=emp_id %>" compare="not like" />
				<qm:param name="check_op_id" value="<%=emp_id %>" compare="like" />
				<qm:param name="check_unit_id" value="<%=org_id %>" compare="=" />
				<%
			}
		%>
	</qm:qm>
	<script type="text/javascript">
	Qm.config.columnsInfo.check_unit_id.binddata=<u:dict sql="select id code, ORG_NAME text from SYS_ORG where id='100036' "></u:dict>;
	Qm.config.columnsInfo.info_status.binddata = [
		{id: '0', text: '未生成报告'},
		{id: '1', text: '已生成报告'},
		{id: '2', text: '已修改，未生成报告'}
	];
	</script>
</body>
</html>
