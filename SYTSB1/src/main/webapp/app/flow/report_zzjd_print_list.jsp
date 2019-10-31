<%@page import="com.khnt.rtbox.template.constant.RtPath"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://bpm.khnt.com" prefix="bpm" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>报告打印</title>
<%@include file="/k/kui-base-list.jsp"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript" src="rtbox/app/templates/rtbox.js"></script>
<script type="text/javascript" src="pub/bpm/js/util.js"></script>
<script type="text/javascript">
	var w = window.screen.availWidth;
	var h = window.screen.availHeight;
	var userId = "<sec:authentication property='principal.id' htmlEscape='false' />";
	var bar =[
		{ text:'查看报告', id:'showReport',icon:'detail', click: showReport}, "-",
   		{ text:'打印报告', id:'print',icon:'print', click: doPrintReport}, "-",
   		//{ text:'下载附件', id:'download',icon:'excel-export', click: downloadAttachments},"-",
   		//{text :'报告作废',id : 'del',icon : 'delete',click : del},
 		{ text:'流转过程', id:'flow_note',icon:'follow', click: getFlow}
	];

	var	step_name="打印报告";
	var qmUserConfig = {
		sp_fields : [
		{
			name : "report_sn",
			compare : "like",
			value : ""
		},
		{
			name : "com_name",
			compare : "like",
			value : ""
		},
		{
			name : "made_unit_name",
			compare : "like",
			value : ""
		},
		{
			name : "install_unit_name",
			compare : "like",
			value : ""
		},
		{
			name : "construction_unit_name",
			compare : "like",
			value : ""
		},
		{
			name : "device_name",
			compare : "like",
			value : ""
		},
		{name:"check_unit_id", id:"check_unit_id", compare:"="},
		{name:"enter_op_name",compare:"like"},
		{name:"report_type", id:"report_type", compare:"=", value: "", treeLeafOnly: false}
		],
		tbar :bar,
		listeners : {
			selectionChange : function(rowData, rowIndex) {
				var count = Qm.getSelectedCount();//行选择个数
				Qm.setTbar({flow_note : count==1,print: count>0,showReport : count==1,download:count==1});
			},
			afterQmReady : function() {
				Qm.setCondition([ {
						name : "activity_name",
						compare : "=",
						value : step_name
					},{
						name : "handler_id",
						compare : "=",
						value : userId
					}
				]);
				Qm.searchData();
			}
		}
	};

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
					{name:"pageStatus",value:"detail"},
					{name:"isBatchBusiness",value:"1"}];
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

	// 打印报告
	function doPrintReport(){	
		var ids = Qm.getValuesByName("id").toString();
		var ispdf =  Qm.getValuesByName("export_pdf").toString();
		var pdf = "";
		if(ispdf!=null){
			var pdflist = ispdf.split(",");
			for(var i=0;i<pdflist.length;i++){
				pdf += pdflist[i];
			}
		}
		if(ispdf==null || ispdf=="" || pdf==""){
			var w = window.screen.availWidth;
			var h = window.screen.availHeight;			
			top.$.dialog({
				width : w, 
				height : h, 
				lock : true, 
				title : "打印报告",
				content: 'url:app/query/report_zzjd_print_index.jsp?id='+ids+'&acc_id='+Qm.getValuesByName("activity_id").toString()+'&flow_num='+Qm.getValueByName("flow_num_id").toString(),
				data : {"window" : window}
			}).max();
		}else{
			printPdf();
		}
	}

	//流转过程
	function  getFlow(){
		 top.$.dialog({
	   		width : 400, 
	   		height : 700, 
	   		lock : true, 
	   		title: "流程卡",
	   		content: 'url:inspection/zzjd/getFlowStep.do?ins_info_id='+Qm.getValuesByName("id"),
	   		data : {"window" : window}
	   	});
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
		    		"inspection/zzjd/delReport.do",
		    		{"ids":Qm.getValuesByName("id").toString()});
	}

	function printPdf(ids,report_sns,export_pdfs,acc_id,flow_num,printcopies) {
		if(ids == "" || ids == null || ids == "undefined"){
			var ids = Qm.getValuesByName("inspection_info_id");
			var report_sns = Qm.getValuesByName("report_sn");
			var export_pdfs = Qm.getValuesByName("export_pdf");
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
					//addReportTransferInfos(ids);
					Qm.refreshGrid();
				}
			}
		}).max();
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
	function refreshGrid() {
		Qm.refreshGrid();
	}
</script>
</head>
<body>
<qm:qm pageid="report_zzjd_list" script="false" singleSelect="false" seachOnload="false">
</qm:qm>
<script type="text/javascript">
	Qm.config.columnsInfo.check_unit_id.binddata=<u:dict sql="select id code, ORG_NAME text from SYS_ORG where ORG_CODE like 'jd%' or ORG_CODE like 'cy%' order by orders "></u:dict>;
	Qm.config.columnsInfo.report_type.binddata=<u:dict sql="select id,report_name from base_reports where report_name like '%梯%' "></u:dict>;
</script>
</body>
</html>
