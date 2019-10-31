﻿<%@page import="com.khnt.rtbox.template.constant.RtPath"%>
<%@page import="com.khnt.utils.StringUtil"%>
<%@page import="java.util.Map"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>报告打印</title>
<%@include file="/k/kui-base-list.jsp"%>
<%
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		Map<String, String> roles = user.getRoles();
		String role_name = "";
		String role_id1 = "";
		String role_id2 = "";
		String role_id3 = "";
		for(String roleid : roles.keySet()){
			Object obj = roles.get(roleid);
			if(StringUtil.isNotEmpty(role_name)){
				role_name+= ",";
			}
			role_name+= obj;
			if("报告打印".equals(obj)){
				role_id1 = roleid;
			}
			if("西藏报告打印".equals(obj)){
				role_id3 = roleid;
			}
			if("大厅管理人员".equals(obj)){
				role_id2 = roleid;
			}
		}
		String userId = user.getId();
		String user_name = user.getName(); 
	%>

<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript" src="rtbox/app/templates/rtbox.js"></script>
<script type="text/javascript">
	var userId = "<sec:authentication property='principal.id' htmlEscape='false' />"
	var w=window.screen.availWidth;
	var h=(window.screen.availHeight);

	var	bar = [
		{ text:'查看', id:'showReport',icon:'detail', click: showReport},"-",
		<sec:authorize ifAnyGranted="REPORT_DZYZ_GZ,xz_report_print,sys_administrate">
		{ text:'一键盖章打印', id:'rootPrint',icon:'print', click: rootPrint},"-",
		{ text:'调试一键盖章打印', id:'rootPrint2',icon:'print', click: rootPrint2},"-",
      	{ text:'盖章', id:'pdf',icon:'save', click: reportPdf},"-",
      	</sec:authorize>
      	<sec:authorize ifAnyGranted="report_print,xz_report_print">
      	{ text:'打印', id:'print',icon:'print', click: doPrintReport},"-",
      	</sec:authorize>
      	//{text :'报告作废',id : 'del',icon : 'delete',click : del},
      	{ text:'下载附件', id:'download',icon:'excel-export', click: downloadAttachments},"-",
      	<sec:authorize ifAnyGranted="sys_administrate">
      	{ text:'提交流程', id:'printSubFlows',icon:'detail', click: printSubFlows},"-",
      	</sec:authorize>
     	{ text:'流转过程', id:'flow_note',icon:'follow', click: getFlow}
	];
	
	//var	step_name="打印报告";
	var qmUserConfig = {
		sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	// 默认值，可自定义
		sp_fields:[
			{name:"report_sn",compare:"like"},
			{name:"device_name",compare:"like"},
			{name:"report_com_name",compare:"like"},
			{name :"device_qr_code",compare : "like"},
			{name:"maintain_unit_name",compare:"like"},
			{name:"check_unit_id", id:"check_unit_id", compare:"="},
			{name:"enter_op_name",compare:"like"}
	    ],
		tbar:bar,
        listeners: {
			selectionChange :function(rowData,rowIndex){
     			var count = Qm.getSelectedCount();//行选择个数
               	Qm.setTbar({print:count>0,flow_note:count==1,showReport:count==1,download:count==1,pdf : count > 0, printSubFlows : count > 0});
			},
			rowAttrRender : function(rowData, rowid) {
				 var fontColor="black";
				if (rowData.export_pdf != '' && rowData.export_pdf != 'null'){
		        	fontColor="blue";
				}
		   		return "style='color:"+fontColor+"'";
			},
	  		pageSizeOptions:[10,20,30,50,100,200]
		}
	};

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
		var ispdf =  Qm.getValuesByName("export_pdf").toString();
		var report_id = Qm.getValueByName("report_type").toString();	// 报告类型
		var reportType = Qm.getValueByName("reportType").toString();	// 新老报表识别代码
		
		var to_swf = Qm.getValueByName("to_swf").toString();
		var inspect_date = Qm.getValueByName("advance_time").toString();
		var date = inspect_date.substring(0,4)+inspect_date.substring(5,7)+inspect_date.substring(8,10);
		var report_sn = Qm.getValueByName("report_sn").toString();
		if("2" == reportType){
			var pdf_export_ps =  Qm.getValuesByName("pdf_export_ps").toString();	// 新报表导出pdf标记
			var pdf_export_att =  Qm.getValuesByName("pdf_export_att").toString();	// 新报表导出pdfid
			if(pdf_export_ps == "1" || (ispdf!=null&&ispdf!=""&&ispdf!="undefined")){
				if(ispdf!=null&&ispdf!=""&&ispdf!="undefined"){
					showFile(id,to_swf,date,ispdf,report_sn);
				}else{
					top.$.dialog({
						width : 800, 
						height : 400, 
						lock : true, 
						title:"查看报告",
						content: 'url:app/flow/reportPdfPrint/report_doc.jsp?status=detail&id='+id,
						data : {"window" : window,"pdf_export_att":pdf_export_att}
					}).max();
				}
			}else{
				//科鸿rtbox报表
				var code = Qm.getValueByName("rtbox_code");
				var param=[
					{name:"id",value:id},
					{name:"fk_report_id",value: report_id},
					{name:"status",value:"detail"},
					{name:"pageStatus",value:"detail"}];
				opRt(code,param,"<%=RtPath.tplPageDir%>");
			}
		}else{
			if(ispdf!=null&&ispdf!=""&&ispdf!="undefined"){
				showFile(id,to_swf,date,ispdf,report_sn);
			}else{
				var w=window.screen.availWidth;
				var h=(window.screen.availHeight);
				var url = "report/query/showReport.do?id="+id+"&report_id="+report_id;
				top.$.dialog({
					width : w, 
					height : h, 
					lock : true, 
					title:"查看报告",
					content: 'url:'+url,
					data : {"window" : window}
				}).max();
			}
		}
	}
		
	function showNewReport(inspection_info_ids,flow_num,activity_id){
		top.$.dialog({
			width : 850, 
			height : 600, 
			lock : true, 
			title: "报告打印",
			content: "url:app/flow/rtcommon/report_print_index_doc.jsp",
			data : {
					"window" : window,
					"infoIds" : inspection_info_ids,
					"flow_num" : flow_num,
					"activityIds" : activity_id
			},
			close: function(data){
				Qm.refreshGrid();
			}
		}).max(); 
	}
	// 打印报告
	function doPrintReport(){	
		var ids = Qm.getValuesByName("id").toString();
		// 验证报告是否报送且已签收状态，只有已报送且已签收才能打印（2017-12-27业务服务部马竹君提出需求）
		$.getJSON("report/print/validateInfos.do?ids="+ids, function(resp){
			if (resp["success"]) {
				var ispdf =  Qm.getValuesByName("export_pdf").toString();
				var pdf = "";
				if(ispdf!=null){
					var pdflist = ispdf.split(",");
					for(var i=0;i<pdflist.length;i++){
						pdf += pdflist[i];
					}
				}
				if(ispdf==null || ispdf=="" || pdf==""){
					$.post("report/error/record/queryReportErrors.do?ids="+ids, function(resp) {
						if (resp.success) {
							top.$.dialog({
								width : w, 
								height :h, 
								lock : true, 
								title:"打印报告",
								content: 'url:app/query/report_print_index.jsp?printType=0',
								data : {
									"window" : window,
									"id":ids,
									"acc_id":Qm.getValuesByName("activity_id").toString(),
									"flow_num":Qm.getValueByName("flow_num_id").toString(),
									"print_type":"0",
									"print_remark":"打印报告"
								}
							}).max();
						}else{
							$.ligerDialog.error(resp.msg);
						}
					});
				}else{
					$.post("report/error/record/queryReportErrors.do?ids="+ids, function(resp) {
						if (resp.success) {
							/* printPdf(); */
							top.$.dialog({
								width : w, 
								height :h, 
								lock : true, 
								title:"打印报告",
								content: 'url:app/flow/rtcommon/report_print_index_doc.jsp?re_print=0&record=',
								data : {
									"window" : window,
									"infoIds":ids,
									"activityIds":Qm.getValuesByName("activity_id").toString(),
									"flow_num":Qm.getValueByName("flow_num_id").toString(),
								}
							}).max();
						}else{
							$.ligerDialog.error(resp.msg);
						}
					});
				}
	     	}else{
	      		$.ligerDialog.error('亲：' + resp.msg);
	    	}
		})
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
			content: 'url:app/flow/export/report_export_index.jsp?id='+ids+'&acc_id='+Qm.getValuesByName("activity_id").toString()+'&flow_num='+Qm.getValueByName("flow_num_id").toString()+'&printType=0&rootPrint=0',
			data : {"window" : window}
		}).max();
	}
	
	// 一键盖章打印
	function rootPrint() {
		var ids = Qm.getValuesByName("id").toString();
		// 验证报告是否报送且已签收状态，只有已报送且已签收才能打印（2017-12-27业务服务部马竹君提出需求）
		$.getJSON("report/print/validateInfos.do?ids="+ids, function(resp){
			if (resp["success"]) {
				var w=window.screen.availWidth;
				var h=(window.screen.availHeight);				
				addReportTransferInfos(ids);
				top.$.dialog({
					width : w, 
					height :h, 
					lock : true, 
					title:"盖章",
					content: 'url:app/flow/export/report_export_index.jsp?id='+ids+'&printType=0&rootPrint=1',
					data : {
						"window" : window, 
						"id" : Qm.getValuesByName("id"),
						"acc_id" : Qm.getValuesByName("activity_id"),
						"flow_num" : Qm.getValuesByName("flow_num_id"),
						"report_sn" : Qm.getValuesByName("report_sn"),
						"export_pdf" : Qm.getValuesByName("export_pdf1"),
						"printcopies" : Qm.getValuesByName("printcopies")
					}
				}).max();
	     	}else{
	      		$.ligerDialog.error('亲：' + resp.msg);
	    	}
		})
	}
	
	// 调试一键盖章打印
	function rootPrint2() {
		var ids = Qm.getValuesByName("id").toString();
		// 验证报告是否报送且已签收状态，只有已报送且已签收才能打印（2017-12-27业务服务部马竹君提出需求）
		$.getJSON("report/print/validateInfos.do?ids="+ids, function(resp){
			if (resp["success"]) {
				var w=window.screen.availWidth;
				var h=(window.screen.availHeight);				
				addReportTransferInfos(ids);
				top.$.dialog({
					width : w, 
					height :h, 
					lock : true, 
					title:"盖章",
					content: 'url:app/flow/export/report_export_index.jsp?id='+ids+'&printType=0&rootPrint=1&type=test',
					data : {
						"window" : window, 
						"id" : Qm.getValuesByName("id"),
						"acc_id" : Qm.getValuesByName("activity_id"),
						"flow_num" : Qm.getValuesByName("flow_num_id"),
						"report_sn" : Qm.getValuesByName("report_sn"),
						"export_pdf" : Qm.getValuesByName("export_pdf1"),
						"printcopies" : Qm.getValuesByName("printcopies")
					}
				}).max();
	     	}else{
	      		$.ligerDialog.error('亲：' + resp.msg);
	    	}
		})
	}
	
	//调试pdf打印
	function printPdfTest(ids,report_sns,export_pdfs,acc_id,flow_num,printcopies) {
		if(ids == "" || ids == null || ids == "undefined"){
			var ids = Qm.getValuesByName("id");
			var report_sns = Qm.getValuesByName("report_sn");
			var export_pdfs = Qm.getValuesByName("export_pdf1");
			var acc_id = Qm.getValuesByName("activity_id");
			var flow_num = Qm.getValuesByName("flow_num_id");
			var printcopies = Qm.getValuesByName("printcopies");
			var l = ids.length;
			top.$.dialog({
				width : 1000,
				height : 800,
				lock : true,
				title : "打印报告",
				content : 'url:app/flow/reportPdfPrint/report_doc_cyc.jsp?status=modify&print_types=1',
				data:{"window" : window,
					"report_sns":report_sns,
					"date":export_pdfs,
					"type":1,
					"printcopies":printcopies,
					"print_type":"0",
					"print_remark":"打印报告"}
			});		
		}else{
			var l = ids.length;
			top.$.dialog({
				width : 1000,
				height : 800,
				lock : true,
				title : "打印报告",
				content : 'url:app/flow/reportPdfPrint/report_doc_cyc.jsp?status=modify&print_types=1',
				data:{"window" : window,
					"report_sns":api.data.report_sns,
					"date":api.data.export_pdfs,
					"type":1,
					"printcopies":print_count,
					"print_type":"0",
					"print_remark":"打印报告"}
			});		
		}
	}
	
	
	function printPdf(ids,report_sns,export_pdfs,acc_id,flow_num,printcopies) {
		if(ids == "" || ids == null || ids == "undefined"){
			var ids = Qm.getValuesByName("id");
			var report_sns = Qm.getValuesByName("report_sn");
			var export_pdfs = Qm.getValuesByName("export_pdf1");
			var acc_id = Qm.getValuesByName("activity_id");
			var flow_num = Qm.getValuesByName("flow_num_id");
			var printcopies = Qm.getValuesByName("printcopies");
			var l = ids.length;
			openN(0, ids, report_sns, export_pdfs, l, acc_id, flow_num,
					printcopies, 1,"0","打印报告");
		}else{
			
			
			var l = ids.length;
			openN(0, ids, report_sns, export_pdfs, l, acc_id, flow_num,
					printcopies, 1,"0","打印报告");
		}
	}
	
	function openN(i, ids, report_sns, export_pdfs, l, acc_id, flow_num, 
			printcopies, type,print_type,print_remark) {	
		//为了解决马竺君打印报告少一份问题 
		if("<%=user_name%>"=="马竺君 "){
			var printcopies="4"
		}
		top.$.dialog({
			width : 800,
			height : 400,
			lock : true,
			title : "打印报告",
			content : 'url:app/flow/reportPdfPrint/report_doc.jsp?status=detail&print=true&id='+ ids[i],
			data : {
				"window" : window,
				"report_sn" : report_sns[i],
				"date" : export_pdfs[i],
				"acc_id" : acc_id[i],
				"flow_num" : flow_num[i],
				"printcopies" : printcopies[i],
				"type" : 1,
				"print_type":print_type,
				"print_remark":print_remark
			},
			close : function() {
				i++;
				if (i < l) {
					openN(i, ids, report_sns, export_pdfs, l, acc_id, flow_num, printcopies, type,print_type,print_remark);
				} else {		
					Qm.refreshGrid();
				}
			}
		}).max();
	}
	
	function addReportTransferInfos(ids){
		$.getJSON("report/transfer/autoCommit.do?ids="+ids, function(resp){
			if (resp["success"]) {
	         	//top.$.dialog.notice({content:'保存成功！'});
	     		//api.close();
	     	}else{
	      		$.ligerDialog.error('提示：' + resp.msg);
	    	}
		})
	}
	
	function printSubFlows(){
		if(confirm("亲，确定提交流程吗？提交后无法撤回哦！")){
			$("body").mask("正在提交数据，请稍后！");
			$.getJSON("inspectionInfo/basic/printSubFlow.do?infoId="+Qm.getValuesByName("id").toString()+"&acc_id="+Qm.getValuesByName("activity_id").toString()+"&flow_num="+Qm.getValueByName("flow_num_id").toString(), function(resp){
				if (resp["success"]) {
					$("body").unmask();
		         	top.$.dialog.notice({content:'流程提交成功！'});
					Qm.refreshGrid();
		     	}else{
		     		$("body").unmask();
		      		$.ligerDialog.error('提示：' + resp.msg);
		    	}
			})
		}
	}

	// 下载附件
	function downloadAttachments() {
		var is_upload = Qm.getValueByName("is_upload").toString();
		if(is_upload != "1"){
			$.ligerDialog.alert('亲，系统暂不支持该类报告的附件下载功能哦！', "提示");
			return;
		}
		top.$.dialog({
			width : 600, 
			height : 400, 
			lock : true, 
			title : "下载附件", 
			data : {"window" : window},
			cancel : true,
			content : 'url:app/flow/report_attachment_upload.jsp?status=detail&info_id=' + Qm.getValueByName("id").toString()
		});
	}
	
	// 报告作废
	function del(){	
		 $.del("确定作废？",
		    		"department/basic/delReport.do",
		    		{"ids":Qm.getValuesByName("id").toString()});
	}
	
	// 查看报告文档
    function showFile(id,to_swf,date,ispdf,report_sn){
    	if(to_swf==null||to_swf==""||to_swf=="undefined"){
			$("body").mask(" 第一次查看该报告，正在准备文档，请稍后......");
			$.post("inspectionInfo/basic/pdf2Swf.do",{"pdfPath":date+"/"+report_sn+"/"+report_sn+".pdf","swfPath":date+"/"+report_sn,"swfName":report_sn},function(res){
				if(res.success){
        			$("body").unmask();
        			top.$.dialog({
        				width : 800, 
        				height : 400, 
        				lock : true, 
        				title:"查看报告",
        				content: 'url:app/query/showFile.jsp?status=detail&path='+date+"/"+report_sn+"/"+report_sn+".swf"+'&id='+id,
        				data : {"window" : window,"report_sn":report_sn,"date":ispdf},
        				close:function(res){
        					Qm.refreshGrid();
        					}
					}).max(); 
				}
			}) 
		}else{
			 top.$.dialog({
				width : 800, 
				height : 400, 
				lock : true, 
				title:"查看报告",
				content: 'url:app/query/showFile.jsp?status=detail&path='+date+"/"+report_sn+"/"+report_sn+".swf"+'&id='+id,
				data : {"window" : window,"report_sn":report_sn,"date":ispdf}
			}).max(); 
		}
    }
	// 刷新Grid
	function submitAction() {
   		Qm.refreshGrid();
	}
</script>
</head>
<body>
	
	<div class="item-tm" id="titleElement">
		    <div class="l-page-title">
				<div class="l-page-title-note">提示：列表数据项 
					<font color="black">“黑色”</font>代表未盖电子印章，
					<font color="blue">“蓝色”</font>代表已盖电子印章。
				</div>
			</div>
		</div>
	<qm:qm pageid="report_print_list2" singleSelect="false" >
		<qm:param name="activity_name" value="打印报告" compare="=" />
		<%
			if(StringUtil.isNotEmpty(role_name)){
				if(role_name.contains("报告打印") || role_name.contains("西藏报告打印") || role_name.contains("大厅管理人员")){
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
					if(StringUtil.isNotEmpty(role_id3)){
						%>
						<qm:param name="handler_id" value="<%=role_id3 %>" compare="=" logic="or"/>
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
	<script type="text/javascript">
		Qm.config.columnsInfo.check_unit_id.binddata=<u:dict sql="select id code, ORG_NAME text from SYS_ORG where ORG_CODE like 'jd%' or ( ORG_CODE like 'cy%' and ORG_CODE != 'cy4_1' and ORG_CODE != 'cy8') order by orders "></u:dict>;
		Qm.config.columnsInfo.report_type.binddata=<u:dict sql="select id,report_name from base_reports where report_name like '%梯%' "></u:dict>;
	</script>
</body>
</html>