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
			{name:"internal_num", id:"internal_num", compare:"like"},
			{name:"device_qr_code", id:"device_qr_code", compare:"like"},
			<%
			if(!org_code.contains("jd")){
				%>
				{name:"check_unit_id", id:"check_unit_id", compare:"="},
				<%
			}
			%>
			{group:[
				{name:"advance_time", id:"advance_time", compare:">="},
				{label:"到", name:"advance_time", id:"advance_time1", compare:"<=", labelAlign:"center", labelWidth:20}
			]},
			{name:"checkstatus", id:"check_status", compare:"="},
			{name:"info_status", id:"info_status", compare:"="}
		],
		tbar : [
			{ text:'校核', id:'batchCheck',icon:'search', click: batchCheck2},'-', 
			//{ text:'单份校核', id:'check',icon:'search', click: check},'-', 
			{ text : '预览', id : 'showRecord', click : showRecord, icon : 'detail' },"-",
			//{ text:'查看报告', id:'showReport',icon:'detail', click: showReport},"-",
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
				/*
				 var fontColor="black";
		            if (rowData.is_report_confirm == '未校核' || rowData.is_report_confirm == '' || rowData.is_report_confirm == 'null'){
		            	fontColor="black";
		            }
		            if (rowData.is_report_confirm == '通过'){
		            	fontColor="green";
		            }
		            if (rowData.is_report_confirm == '未通过'){
		            	fontColor="red";
		            }
		            return "style='color:"+fontColor+"'";
		            */
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
		var url = "report/item/record/showRecord.do?id="+id+"&report_id="+report_id;
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
	function batchCheck(){	
		var ids = Qm.getValuesByName("id").toString();
		top.$.dialog({ 	
			width : w, 
			height : h, 
			lock : true, 
			title:"批量校核",
			content: 'url:app/mobile/record_check_index.jsp?id='+ids,
			data : {"window" : window}
		}).max();
	}
	// 单份校核
	function check(){	
		var ids = Qm.getValueByName("id").toString();
		top.$.dialog({ 	
			width : w, 
			height : h, 
			lock : true, 
			title:"单份校核",
			content: 'url:app/mobile/record_check_index.jsp?id='+ids,
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
			content: 'url:app/mobile/record_batch_index.jsp?ids='+ids,
			data : {"window" : window}
		}).max();
	}
	
	// 查看报告
	function showReport(){	
		var id = Qm.getValueByName("id").toString();
		var ispdf =  Qm.getValuesByName("export_pdf").toString();
		if(ispdf==null||ispdf==""){
			var report_id = Qm.getValueByName("report_id").toString();	// 报告类型
			var url = "report/query/showReport.do?id="+id+"&report_id="+report_id;
			top.$.dialog({
				width : w, 
				height : h, 
				lock : true, 
				title:"查看报告",
				content: 'url:'+url,
				data : {"window" : window}
			}).max();
		}else{
			var report_sn = Qm.getValueByName("report_sn").toString();
			top.$.dialog({
				width : 800, 
				height : 400, 
				lock : true, 
				title:"查看报告",
				content: 'url:app/flow/reportPdfPrint/report_doc.jsp?status=detail&id='+id,
				data : {"window" : window,"report_sn":report_sn,"date":ispdf}
			}).max();
		}				
	}
	// 历史纪录
	function history(){	
		top.$.dialog({ 	
			width : w, 
			height : h, 
			lock : true, 
			title:"历史纪录",
			content: 'url:app/mobile/report_item_record_check_history.jsp',
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
	<!-- 
	<div class="item-tm" id="titleElement">
	    <div class="l-page-title">
			<div class="l-page-title-note">提示：列表数据项
				<font color="black">“黑色”</font>代表原始记录未校核；
				<font color="green">“绿色”</font>代表原始记录校核通过；
				<font color="red">“红色”</font>代表原始记录校核未通过。
			</div>
		</div>
	</div>
	 -->
	<qm:qm pageid="report_record_check">
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
