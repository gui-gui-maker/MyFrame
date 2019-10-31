<%@page import="com.khnt.utils.StringUtil"%>
<%@page import="java.util.Map"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>报告打印</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">
	var userId = "<sec:authentication property='principal.id' htmlEscape='false' />"

	var bar = [ {
		text : '查看报告',
		id : 'detail',
		icon : 'detail',
		click : detail
	},{
		text : '打印报告',
		id : 'reportPdf',
		icon : 'print',
		click : printPdf
	}
	//,
	//{
	//	text : '提交到领取',
		//id : 'reportPdf',
		//icon : 'sub',
		//click : subDraw
	//}
	];
	
	var step_name = "报告归档";
	var qmUserConfig = {
		sp_defaults : {
			columnWidth : 0.3,
			labelAlign : 'right',
			labelSeparator : '',
			labelWidth : 100
		}, // 默认值，可自定义
		sp_fields : [ {
			name : "report_sn",
			compare : "like"
		}, {
			name : "device_registration_code",
			compare : "like"
		}, {
			name : "report_com_name",
			compare : "like"
		} ],
		tbar : bar,
		listeners : {
			selectionChange : function(rowData, rowIndex) {
				var count = Qm.getSelectedCount();//行选择个数
				Qm.setTbar({
					detail:count==1,
					reportPdf : count > 0
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

	function openN(i,ids,report_sns,export_pdfs,l,acc_id,flow_num,printcopies,type,print_type,print_remark){
		top.$.dialog({
			width : 800, 
			height : 400, 
			lock : true, 
			title:"查看报告",
			content: 'url:app/flow/reportPdfPrint/report_doc.jsp?status=detail&print=true&id='+ids[i],
			data : {
				"window" : window,
				"report_sn":report_sns[i],
				"date":export_pdfs[i],
				"acc_id":acc_id[i],
				"flow_num":flow_num[i],
				"printcopies":printcopies[i],
				"type":1,
				"print_type":print_type,
				"print_remark":print_remark
			},
			close:function(){
				i++;
				if(i<l){
					openN(i,ids,report_sns,export_pdfs,l,acc_id,flow_num,printcopies,type,print_type,print_remark);
				}else{
					Qm.refreshGrid();
				}
				}
			}).max();
	
}

function printPdf(){
	var ids = Qm.getValuesByName("id");
	var report_sns = Qm.getValuesByName("report_sn");
	var export_pdfs = Qm.getValuesByName("export_pdf");
	var acc_id=Qm.getValuesByName("activity_id");
	var flow_num=Qm.getValuesByName("flow_num_id");
	var printcopies=Qm.getValuesByName("printcopies");
	var l = ids.length;
	openN(0,ids,report_sns,export_pdfs,l,acc_id,flow_num,printcopies,1,"0","打印报告");
	
}
	

	// 刷新Grid
	function submitAction() {
		Qm.refreshGrid();
	}

function detail(){
	var id = Qm.getValueByName("id");
	var report_sn = Qm.getValueByName("report_sn");
	var export_pdf = Qm.getValueByName("export_pdf");
	top.$.dialog({
		width : 800, 
		height : 400, 
		lock : true, 
		title:"查看报告",
		content: 'url:app/flow/reportPdfPrint/report_doc.jsp?status=detail&id='+id,
		data : {"window" : window,"report_sn":report_sn,"date":export_pdf}
	}).max();
}
</script>
</head>
<body>
	<%
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		Map<String, String> roles = user.getRoles();
		String role_name = "";
		String role_id1 = "";
		String role_id2 = "";
		for(String roleid : roles.keySet()){
			Object obj = roles.get(roleid);
			if(StringUtil.isNotEmpty(role_name)){
				role_name+= ",";
			}
			role_name+= obj;
			if("报告打印".equals(obj)){
				role_id1 = roleid;
			}
			if("大厅管理人员".equals(obj)){
				role_id2 = roleid;
			}
		}
		String userId = user.getId();
	%>
	<qm:qm pageid="report_print_gc_pdf2" singleSelect="false">
		<qm:param name="activity_name" value="打印报告" compare="=" />	
		<%
			if(StringUtil.isNotEmpty(role_name)){
				if(role_name.contains("报告打印") || role_name.contains("大厅管理人员")){
					%>
					<qm:param name="handler_id" value="<%=userId %>" compare="=" />
					<%
					if(StringUtil.isNotEmpty(role_id1)){
						%>
						<qm:param name="handler_id" value="<%=role_id1 %>" compare="=" logic="or"/>
						<%
					}
					if(StringUtil.isNotEmpty(role_id2)){
						%>
						<qm:param name="handler_id" value="<%=role_id2 %>" compare="=" logic="or"/>
						<%
					}
				}else{
					%>
					<qm:param name="handler_id" value="<%=userId %>" compare="=" />
					<%
				}
			}else{
				%>
				<qm:param name="handler_id" value="<%=userId %>" compare="=" />
				<%
			}
		%>
	</qm:qm>
</body>
</html>
