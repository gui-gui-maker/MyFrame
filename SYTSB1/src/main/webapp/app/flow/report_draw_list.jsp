﻿<%@page import="java.util.Map"%>
<%@page import="com.khnt.rbac.impl.bean.Role"%>
<%@page import="com.khnt.utils.StringUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.rtbox.template.constant.RtPath"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>报告领取</title>
<%@include file="/k/kui-base-list.jsp"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript" src="rtbox/app/templates/rtbox.js"></script>
<script type="text/javascript">
var userId = "<sec:authentication property='principal.id' htmlEscape='false' />"



	var	bar = [//{ text:'报告领取', id:'input',icon:'modify', click: getReport},"-",
				//{ text:'打印标签', id:'printTags',icon:'print', click: doPrintTags},
				{ text:'报告领取', id:'getReportNew',icon:'modify', click: getReportNew},"-",
				//{ text:'新报告领取', id:'mobileGetReport',icon:'modify', click: mobileGetReport},"-",
				//{ text:'二维码标签', id:'printTagsE',icon:'print', click: printTagsE},"-",
				{ text:'二维码标签', id:'printTagsE',icon:'print', click: printTagsEBylodop},"-",
				//{text :'报告作废',id : 'del',icon : 'delete',click : del},
	             { text:'领取记录', id:'history',icon:'detail', click: history},"-",
	             { text:'查看报告', id:'showReport',icon:'detail', click: showReport},"-",
	            // { text:'测试生成报告书编号', id:'generateReportSn',icon:'detail', click: generateReportSn},
	            { text:'下载附件', id:'download',icon:'excel-export', click: downloadAttachments},"-",
	             { text:'流转过程', id:'flow_note',icon:'follow', click: getFlow}
	             ];
	             
	var	step_name="报告领取";
	
	var qmUserConfig = {
		sp_defaults:{columnWidth:0.25,labelAlign:'right',labelSeparator:'',labelWidth:100},	// 默认值，可自定义
		sp_fields:[
			{name:"invoice_no",compare:"like"},
			{name:"report_com_name",compare:"like"},
			{name:"maintain_unit_name",compare:"like"},
			{name:"report_sn",compare:"like"},
			{name:"fee_status",compare:"="},
			{name:"org_name",compare:"like"},
			{group:[
					{name:"print_time", id:"print_time", compare:">="},
					{label:"到", name:"print_time", id:"print_time1", compare:"<=", labelAlign:"center", labelWidth:20}
			]}
	    ],
		tbar:bar,
        listeners: {
			selectionChange :function(rowData,rowIndex){
				selectionChange();
			},
			rowAttrRender : function(rowData, rowid) {

        	}/*,
           
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
			},
			
			
			*/
		}
	};
	
	// 行选择改变事件
		function selectionChange() {
	   		var count = Qm.getSelectedCount(); // 行选择个数
	     	if(count == 1){
	            Qm.setTbar({history: true,input:true,getReportNew:true,flow_note: true, showReport: true, printTags:true,printTagsE:true, del: true, download:true,mobileGetReport:true});            	
	 		}else if(count > 1){
	       		Qm.setTbar({history: true,input:true,getReportNew:true, flow_note: false, showReport: false,printTags:true,printTagsE:true, del: true, download:false,mobileGetReport:true});
	    	}else{
	    		Qm.setTbar({history: true,input:false,getReportNew:false, flow_note: false, showReport: false,printTags:false, printTagsE:false, del: false, download:false,mobileGetReport:false});
	    	}
		}

	//报告领取
	function getReport() {
		var report_com_name = Qm.getValueByName("report_com_name");	// 报告书使用单位
		var area_name = Qm.getValueByName("regional_name");	// 安装区域
		var comArr = Qm.getValuesByName("report_com_name");	// 报检单位
		var fee_statusArr = Qm.getValuesByName("fee_status");
		for(var i=0;i<fee_statusArr.length;i++){
			if("待收费" == fee_statusArr[i]){
				$.ligerDialog.alert("待收费的报告，不能领取！");
				return;
			}
		}
		top.$.dialog({
			width : 700, 
			height : 300, 
			lock : true, 
			title : "报告领取", 
			content : 'url:app/flow/report_draw_detail.jsp?status=add',
			data : {
				"window" : window,
				"id":Qm.getValuesByName("id").toString(),
				"acc_id":Qm.getValuesByName("activity_id").toString(),
				"flow_num":Qm.getValueByName("flow_num_id").toString(),
				"report_com_name":report_com_name,
				"area_name":area_name
			},		
		});
	}
	
	//新的报告领取
	function getReportNew() {
		var report_com_name = Qm.getValueByName("report_com_name");	// 报告书使用单位
		var area_name = Qm.getValueByName("regional_name");	// 安装区域
		var comArr = Qm.getValuesByName("report_com_name");	// 报检单位
		var fee_statusArr = Qm.getValuesByName("fee_status");
		for(var i=0;i<fee_statusArr.length;i++){
			if("待收费" == fee_statusArr[i]){
				$.ligerDialog.alert("待收费的报告，不能领取！");
				return;
			}
		}
		var invoice_no=Qm.getValuesByName("invoice_no");	// 发票编号
		var invoiceNo="";
		for(var i=0;i<invoice_no.length;i++){
			if(i==0){
				invoiceNo=invoice_no[i];
			}else{
				if(invoiceNo!=invoice_no[i]){
					$.ligerDialog.alert("亲，你选择的发票编号不一样，请重新选择！");
					return;
				}
			}
		}
		top.$.dialog({
			width : 700, 
			height : 500, 
			lock : true, 
			title : "报告领取", 
			content : 'url:app/flow/report_draw_new_list.jsp?status=add',
			parent: api,
			data : {
				"window" : window,
				"id":Qm.getValuesByName("id").toString(),
				"report_com_name":report_com_name,
				"invoice_no":Qm.getValueByName("invoice_no"),
				"area_name":area_name,
				"report_sn":Qm.getValuesByName("report_sn").toString()
			},		
		});
	}
	
	//报告领取
	function mobileGetReport() {
		var fee_statusArr = Qm.getValuesByName("fee_status");
		for(var i=0;i<fee_statusArr.length;i++){
			if("待收费" == fee_statusArr[i]){
				$.ligerDialog.alert("待收费的报告，不能领取！");
				return;
			}
		}
		top.$.dialog({
			width : 700, 
			height : 500, 
			lock : true, 
			title : "报告领取", 
			content : 'url:app/flow/report_draw_qrcode.jsp?status=add',
			data : {
				"window" : window,
				"ids":Qm.getValuesByName("id").toString(),
				"report_sns":Qm.getValuesByName("report_sn").toString()
			}	
		});
	}
	//报告领取记录
	function history() {
		top.$.dialog({
			width : 900, 
			height : 600, 
			lock : true, 
			title : "报告领取记录", 
			data : {"window" : window},
			content : 'url:app/flow/report_draw_history.jsp'
		});
	}
	
	// 报告作废
	function del(){	
		  $.del("确定作废？",
		    		"department/basic/delReport.do",
		    		{"ids":Qm.getValuesByName("id").toString()});
	}
	
	// 测试生成报告编号
	function generateReportSn(){
		$.getJSON("report/basic/generateReportCode.do", function(resp){
			$.ligerDialog.alert(resp.message);		
		})
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
	
	// 打印标签
	function doPrintTags(){	
		var device_id = Qm.getValuesByName("fk_tsjc_device_document_id").toString();
		var conclusions = Qm.getValuesByName("inspection_conclusion").toString();
		if(conclusions.indexOf("不合格")!=-1){
			$.ligerDialog.error("所选报告中包含有检验不合格的报告，不能打印标签，请重新选择！");
			return;
		}
		var ids = Qm.getValuesByName("id").toString();
		var w=window.screen.availWidth;
		var h=(window.screen.availHeight);			
		top.$.dialog({ 
			width : w, 
			height :h, 
			lock : true, 
			title:"打印标签",
			content: 'url:app/query/report_print_index_tags.jsp?flag=no&id='+ids+'&device_id='+device_id,
			data : {"window" : window}
		}).max();
	}
	
	// 打印二维码标签
	function printTagsE(){	
		var device_id = Qm.getValuesByName("fk_tsjc_device_document_id").toString();
		var conclusions = Qm.getValuesByName("inspection_conclusion").toString();
		if(conclusions.indexOf("不合格")!=-1){
			$.ligerDialog.error("所选报告中包含有检验不合格的报告，不能打印标签，请重新选择！");
			return;
		}
		var ids = Qm.getValuesByName("id").toString();
		var w=window.screen.availWidth;
		var h=(window.screen.availHeight);			
		top.$.dialog({ 
			width : w, 
			height :h, 
			lock : true, 
			title:"打印标签",
			content: 'url:app/query/report_print_index_tags.jsp?flag=yes',
			data : {
				"window" : window,
				"id":ids
			}
		}).max();
	}
	
	// lodop打印二维码标签
	function printTagsEBylodop(){	
		var bigClassifys = Qm.getValuesByName("big_class");
		var conclusions = Qm.getValuesByName("inspection_conclusion").toString();
		var ids = Qm.getValuesByName("fk_tsjc_device_document_id");
		var report_ids = Qm.getValuesByName("report_type");
		var info_ids = Qm.getValuesByName("id");
		if(conclusions.indexOf("不合格")!=-1){
			$.ligerDialog.error("所选报告中包含有检验不合格的报告，不能打印标签，请重新选择！");
			return;
		}
		for(var i = 0 ; i < ids.length; i++ ){
			if(bigClassifys[i]=="3"){
				top.$.dialog({
					width : 1000,
					height : 800,
					lock : true,
					title : "特种设备使用标志",
					content : "url:app/device/usesign/device_elevator_usesign.jsp?device_id="+ids[i]+'&report_id='+report_ids[i]+'&inspection_info_id='+info_ids[i],
					data : {
						"window" : window
					}
				});
			}else if(bigClassifys[i]=="6" || bigClassifys[i]=="9" ){
				top.$.dialog({
					width : 1000,
					height : 800,
					lock : true,
					title : "特种设备使用标志",
					content : "url:app/device/usesign/device_yl_usesign.jsp?device_id="+ids[i]+'&report_id='+report_ids[i]+'&inspection_info_id='+info_ids[i],
					data : {
						"window" : window
					}
				});
			}else{
				top.$.dialog({
					width : 1000,
					height : 800,
					lock : true,
					title : "特种设备使用标志",
					content : "url:app/device/usesign/device_other_usesign.jsp?device_id="+ids[i]+'&report_id='+report_ids[i]+'&inspection_info_id='+info_ids[i]+'&bigClassifys='+bigClassifys[i],
					data : {
						"window" : window
					}
				});
			}
		}
		Qm.refreshGrid();
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
	
	function parseIs_borrow(thisValue){
		var str = "";
		if("2" == thisValue){
	    	str = "是";
		}else{
			str = "否";
		}
		return str;
	}
	
	// 刷新Grid
	function submitAction() {
   		Qm.refreshGrid();
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
	function reportReceive(){
		top.$.dialog({
			width : 900, 
			height : 600, 
			lock : true, 
			title : "快速领取", 
			data : {"window" : window},
			content : 'url:app/flow/report_draw_new_list.jsp'
		});
	}
</script>
</head>

<body>
	<%
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		Map<String, String> roles = user.getRoles();
		String role_name = "";
		String role_id = "";
		for(String roleid : roles.keySet()){
			Object obj = roles.get(roleid);
			if(StringUtil.isNotEmpty(role_name)){
				role_name+= ",";
			}
			role_name+= obj;
			if("大厅管理人员".equals(obj)){
				role_id = roleid;
			}
		}
		String userId = user.getId();
	%>
	<!-- defaultOrder="[{'field':'invoice_date','direction':'DESC'}]" -->
	<qm:qm pageid="report_draw_listN" singleSelect="false"  defaultOrder="[{'field':'invoice_date','direction':'DESC'},{'field':'report_com_name','direction':'ASC'},{'field':'report_sn','direction':'ASC'}]" >
		<%-- <qm:param name="activity_name" value="报告领取" compare="=" /> --%>
		<%-- <%
			if(StringUtil.isNotEmpty(role_name)){
				if(role_name.contains("大厅管理人员")){
					%>
					<qm:param name="handler_id" value="<%=userId %>" compare="=" />
					<qm:param name="handler_id" value="<%=role_id %>" compare="=" logic="or"/>
					<%
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
		
		 --%>
	</qm:qm>
	<!-- 
	<script type="text/javascript">
		Qm.config.columnsInfo.area_id.binddata=<u:dict sql="select id,parent_id pid,regional_code code, regional_name text from V_AREA_CODE where ID in ('510104','510106','510109','510122','510189')"></u:dict>;
	</script>
	 -->
</body>
</html>