<%@page import="com.khnt.rtbox.template.constant.RtPath"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.utils.StringUtil"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>业务信息查询</title>
		<%@include file="/k/kui-base-list.jsp"%>
		<script type="text/javascript" src="pub/bpm/js/util.js"></script>
		<script type="text/javascript" src="pub/worktask/js/worktask.js"></script>
		<script language="javascript" src="app/flow/report/report.js"></script>
		<script type="text/javascript" src="rtbox/app/templates/rtbox.js"></script>s
<%
	CurrentSessionUser user = SecurityUtil.getSecurityUser();
	String userId = user.getId();
	String user_account = user.getSysUser().getAccount();
	String userType = ((com.khnt.rbac.impl.bean.Org)(user.getDepartment())).getProperty();
	String org_code = ((com.khnt.rbac.impl.bean.Org)(user.getDepartment())).getOrgCode();
	String isDt = "";
%>
		<script type="text/javascript">
		var w = window.screen.availWidth;
		var h = window.screen.availHeight;
		var deviceType=<u:dict sql="select t.value id,t.name text from PUB_CODE_TABLE_VALUES t,pub_code_table t1 where t.code_table_id=t1.id and   t1.code = 'device_classify' "/>
		var areas = <u:dict sql="select id,regional_name text from V_AREA_CODE where ID in ('510104','510106','510109','510122','510189')"></u:dict>;
		var check_types = <u:dict sql="select t.value id, t.name text from PUB_CODE_TABLE_VALUES t, pub_code_table t1 where t.code_table_id = t1.id and t1.code = 'BASE_CHECK_TYPE'"></u:dict>;
		var check_deps = <u:dict sql="select id code, ORG_NAME text from SYS_ORG where ORG_CODE like 'jd%' or ORG_CODE like 'cy%' order by orders "></u:dict>;
		var reports = <u:dict sql='select id,report_name from base_reports'></u:dict>;
		var payments = <u:dict code="PAYMENT_STATUS"></u:dict>;

        var qmUserConfig = {
        	sp_defaults:{columnWidth:0.25,labelAlign:'right',labelSeparator:'',labelWidth:100},	// 默认值，可自定义
            sp_fields:[
            	{name:"device_registration_code", id:"device_registration_code", compare:"like"},
            	{name:"device_name", id:"device_name", compare:"like"},
            	{name:"report_com_name", id:"report_com_name", compare:"like"},
            	{name:"report_sn", id:"report_sn", compare:"like"},
				{name:"internal_num", id:"internal_num", compare:"like"},
				{name : "device_qr_code", id:"device_qr_code", compare : "like"},           	
            	<%
					if(!"region".equals(userType)){	// 行政区划（区县）
						%>
						{name:"area_id", id:"area_id", compare:"="},
						<%
					}
				%>
            	{name:"make_units_name", id:"make_units_name", compare:"like"},
            	{name:"maintain_unit_name", id:"maintain_unit_name", compare:"like"},
            	{name:"check_unit_id", id:"check_unit_id", compare:"="},
            	{name:"inspection_conclusion", id:"inspection_conclusion", compare:"like"},
            	{name:"lrry", id:"lrry", compare:"like"},
            	{name:"issue_name", id:"issue_name", compare:"like"},
            	{name:"report_name", id:"report_name", compare:"like"},
            	{name:"make_date", id:"make_date", compare:"like"},
            	{group:[
					{name:"advance_time", id:"advance_time", compare:">="},
					{label:"到", name:"advance_time", id:"advance_time1", compare:"<=", labelAlign:"center", labelWidth:20}
				]},
				{group:[
					{name:"issue_Date", id:"issue_Date", compare:">="},
					{label:"到", name:"issue_Date", id:"issue_Date1", compare:"<=", labelAlign:"center", labelWidth:20}
				]},
            	{group:[
					{name:"print_time", id:"print_time", compare:">="},
					{label:"到", name:"print_time", id:"print_time1", compare:"<=", labelAlign:"center", labelWidth:20}
				]},
				{name:"is_mobile", id:"is_mobile", compare:"="},
				{name:"flow_note_name", id:"flow_note_name", compare:"="}
            ],
            tbar:[
				//{text:'详情', id:'detail',icon:'detail', click: detail},
				//'-', 
				{ text:'查看报告', id:'showReport',icon:'detail', click: showReport},
				<sec:authorize ifNotGranted="qxj_query_report">		
 				"-",
 				{ text:'流转过程', id:'turnHistory',icon:'follow', click: turnHistory},
 				'-', 
 				{text : '应收金额合计', id : 'payTotal', icon : 'help', click : payTotal},
 				</sec:authorize>
 				'-', 
 				{text : '清空', id : 'empty', icon : 'modify', click : empty}                 
            ],
            listeners: {
                selectionChange : function(rowData,rowIndex){
                	selectionChange();
                },
        		rowDblClick :function(rowData,rowIndex){
        			detail(rowData.inspection_info_id);
        		},
        		rowClick:function(rowData){
        			setConditionValues(rowData);
        		},
    			rowAttrRender : function(rowData, rowid) {
   				 	var fontColor="black";
   		         	// （is_mobile 1：移动检验）
   		            if (rowData.is_mobile == '1'){
   		            	fontColor="blue";
   		            }
   		         	if (rowData.data_status == '99'){
		            	fontColor="red";
		            }
   		         	return "style='color:"+fontColor+";'";
   		            //return "style='color:"+fontColor+";font-weight: bold;'";
   				},
   	            pageSizeOptions:[10,20,30,50,100,200]
            }
        };
        
        // 行选择改变事件
		function selectionChange() {
	   		var count = Qm.getSelectedCount(); // 行选择个数
	   		var big_class = Qm.getValuesByName("big_class"); // 设备类别
	   		var big_classArr = big_class.split(",");
	   		var allow = true; // 初始化游乐设施为允许打印检验申请表
	   		for(var i=0;i<big_classArr.length;i++){
				if(big_classArr[i] != "6"){
					allow = false;
					return;
				}
			}
	     	if(count == 1){
	            Qm.setTbar({detail: true, turnHistory: true, showReport: true, print: true,printTags:true,printEnd:true,payTotal:true,printInfo:true,printTagsE:true, download:true, export_pdf:true, export_pdf2:true, startFlows:true});            	
	 		}else if(count > 1){
	 			if(allow){
	 				Qm.setTbar({detail: false, turnHistory: false, showReport: false, print: true,printTags:true,printEnd:true,payTotal:true,printInfo:true,printTagsE:true, download:false, export_pdf:true, export_pdf2:true, startFlows:true});
	 			}else{
	 				Qm.setTbar({detail: false, turnHistory: false, showReport: false, print: true,printTags:true,printEnd:true,payTotal:true,printInfo:true,printTagsE:true, download:false, export_pdf:true, export_pdf2:true, startFlows:true});
	 			}
	    	}else{
	    		Qm.setTbar({detail: false, turnHistory: false, showReport: false, print: false,printTags:false,printEnd:false,payTotal:false,printInfo:false,printTagsE:false, download:false, export_pdf:false, export_pdf2:false, startFlows:false});
	    	}
		}
		
		function setConditionValues(rowData){
			//$('#device_registration_code1').val(Qm.getValueByName("device_registration_code").toString());	// 设备注册代码
			$("#qm-search-p input").each(function(){
				var name = $(this).attr("id");
				if(!$.kh.isNull(name)){
					$(this).val(rowData[name]);
				}
			})
			//$("input[name='big_class-txt']").ligerComboBox().selectValue(render(rowData["big_class"],deviceType));	// 设备类别
			$("input[name='area_id-txt']").ligerComboBox().selectValue(render(rowData["area_id"],areas));	// 设备所在地
			//$("input[name='check_type-txt']").ligerComboBox().selectValue(render(rowData["check_type"],check_types));	// 检验类别
			$("input[name='check_unit_id-txt']").ligerComboBox().selectValue(render(rowData["check_unit_id"],check_deps));	// 检验部门
			
			//$("input[name='report_id-txt']").ligerComboBox().selectValue(render(rowData["report_id"],reports));	// 报告类型
			//$("input[name='fee_status-txt']").ligerComboBox().selectValue(render(rowData["fee_status"],payments));	// 收费状态
		}
		
		function empty(){
			$("#qm-search-p input").each(function(){
				$(this).val("");
			})
			//$("input[name='fee_status-txt']").ligerComboBox().selectValue('');	// 收费状态
			//$("input[name='big_class-txt']").ligerComboBox().selectValue('');	// 设备类别
			//$("input[name='check_type-txt']").ligerComboBox().selectValue('');	// 检验类别
			$("input[name='check_unit_id-txt']").ligerComboBox().selectValue('');	// 检验部门
			$("input[name='area_id-txt']").ligerComboBox().selectValue('');	// 设备所在地
			$("input[name='data_status-txt']").ligerComboBox().selectValue('');	// 报告状态
			$("input[name='is_mobile-txt']").ligerComboBox().selectValue('');	// 出具方式
			//$("input[name='error_id-txt']").ligerComboBox().selectValue('');	// 是否纠错报告
		}
	
		// 详情
		function detail(id){
			if($.type(id)!="string"){
				id = Qm.getValueByName("inspection_info_id").toString();
			}	
			top.$.dialog({
				width : 800,
				height : 500,
				lock : true,
				title : "业务详情",
				content : 'url:app/flow/info_detail.jsp?status=detail&id='+ id,
				data : {
					"window" : window
				}
			});
		}
		
		// 流转过程
		function turnHistory(){	
			top.$.dialog({
	   			width : 400, 
	   			height : 700, 
	   			lock : true, 
	   			title: "流程卡",
	   			content: 'url:department/basic/getFlowStep.do?ins_info_id='+Qm.getValueByName("inspection_info_id"),
	   			data : {"window" : window}
	   		});
		}
		
		// 查看报告
		function showReport(){
			var id = Qm.getValueByName("inspection_info_id").toString();
			var ispdf =  Qm.getValuesByName("export_pdf").toString();
			var report_id = Qm.getValueByName("report_id").toString();	// 报告类型
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
		
		function export_pdf(){
			var ids = Qm.getValuesByName("inspection_info_id").toString();
			top.$.dialog({
				width : w, 
				height :h, 
				lock : true, 
				title:"报告盖章",
				content: 'url:app/flow/export/report_export_index.jsp?id='+ids+'&re_export=1&printType=0',
				data : {"window" : window}
			}).max();
		}
		
		// 报告导出
		function export_pdf2(){
			if(confirm("亲，请先在本地磁盘D盘中创建文件夹D:/TEMP（注意大写），系统将自动导出报告成pdf文件到该目录！确定文件夹已创建？")){
				var ids = Qm.getValuesByName("inspection_info_id").toString();
				top.$.dialog({
					width : w, 
					height :h, 
					lock : true, 
					title:"报告导出",
					content: 'url:app/query/report_export_index.jsp?id='+ids,
					data : {"window" : window}
				}).max();
			}
		}

		function export_pdfNoz(){
			var ids = Qm.getValuesByName("inspection_info_id").toString();
			top.$.dialog({
				width : w, 
				height :h, 
				lock : true, 
				title:"报告导出",
				content: 'url:app/flow/export/report_export_index2.jsp?id='+ids,
				data : {"window" : window}
			}).max();
		}
		
		
		// 打印报告
		function doPrintReport(){	
			var ids = Qm.getValuesByName("inspection_info_id").toString();
			var flow_note_name = Qm.getValuesByName("flow_note_name").toString();	// 当前步骤
			var ispdf =  Qm.getValuesByName("export_pdf").toString();
			if(flow_note_name.indexOf("录入")!=-1 || flow_note_name.indexOf("审核")!=-1 || flow_note_name.indexOf("签发")!=-1){
				$.ligerDialog.error("所选报告中包含有未签发的报告，不能打印，请重新选择！");
				return;
			}else{
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
								height : h, 
								lock : true, 
								title:"打印报告",
								content: 'url:app/query/report_print_index.jsp?printType=1',
								data : {
									"window" : window,
									"id":ids
								}
							}).max();
						}else{
							$.ligerDialog.error(resp.msg);
						}
					});
				}else{
					$.post("report/error/record/queryReportErrors.do?ids="+ids, function(resp) {
						if (resp.success) {
							//var ids = Qm.getValuesByName("id");
							var report_sns = Qm.getValuesByName("report_sn");
							var export_pdfs = Qm.getValuesByName("export_pdf");
							//var acc_id=Qm.getValuesByName("activity_id");
							//var flow_num=Qm.getValuesByName("flow_num_id");
							//var printcopies=Qm.getValuesByName("printcopies");
							var l = ids.length;
							// 补打报告只打印一份
							openN(0,ids,report_sns,export_pdfs,l,1,2);
						}else{
							$.ligerDialog.error(resp.msg);
						}
					})
				}
			}
		}
		
		function openN(i,ids,report_sns,export_pdfs,l,printcopies,type){
			top.$.dialog({
				width : 800, 
				height : 400, 
				lock : true, 
				title:"打印报告",
				content: 'url:app/flow/reportPdfPrint/report_doc.jsp?status=detail&print=true&id='+ids[i],
				data : {"window" : window,"report_sn":report_sns[i],"date":export_pdfs[i],"type":2,"printcopies":1},
				close:function(){
					i++;
					if(i<l){
						if(report_sns[i]!=null && report_sns[i] != "" && report_sns[i] != "undefined"){
							openN(i,ids,report_sns,export_pdfs,l,1,type);
						}
					}else{
						Qm.refreshGrid();
					}
				}
			}).max();	
		}
        
        // 打印标签
		function doPrintTags(){	
			var id = Qm.getValuesByName("inspection_info_id").toString();
			var conclusions = Qm.getValuesByName("inspection_conclusion").toString();
			if(conclusions.indexOf("不合格")!=-1){
				$.ligerDialog.error("所选报告中包含有检验不合格的报告，不能打印标签，请重新选择！");
				return;
			}
			var w=window.screen.availWidth;
			var h=(window.screen.availHeight);			
			top.$.dialog({
				width : w, 
				height :h, 
				lock : true, 
				title:"打印标签",
				content: 'url:app/query/report_print_index_tags.jsp?id='+id,
				data : {"window" : window}
			}).max();
		}
		
		// lodop打印二维码标签
		function printTagsEBylodop(){	
			var bigClassifys = Qm.getValuesByName("big_class");
			var conclusions = Qm.getValuesByName("inspection_conclusion").toString();
			var ids = Qm.getValuesByName("id");
			var report_ids = Qm.getValuesByName("report_id");
			var info_ids = Qm.getValuesByName("inspection_info_id");
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
						content : "url:app/device/usesign/device_other_usesign.jsp?device_id="+ids[i]+'&report_id='+report_ids[i]+'&inspection_info_id='+info_ids[i],
						data : {
							"window" : window
						}
					});
				}
			}
			Qm.refreshGrid();
		}
		// 打印二维码标签
		function printTagsE(){	
			var device_id = Qm.getValuesByName("id").toString();
			var conclusions = Qm.getValuesByName("inspection_conclusion").toString();
			if(conclusions.indexOf("不合格")!=-1){
				$.ligerDialog.error("所选报告中包含有检验不合格的报告，不能打印标签，请重新选择！");
				return;
			}
			var ids = Qm.getValuesByName("inspection_info_id").toString();
			var w=window.screen.availWidth;
			var h=(window.screen.availHeight);			
			top.$.dialog({ 
				width : w, 
				height :h, 
				lock : true, 
				title:"打印标签",
				content: 'url:app/query/report_print_index_tags.jsp?flag=yes&device_id='+device_id,
				data : {
					"window" : window,
					"id":ids
				}
			}).max();
		}
		
		// 打印检验资料归档目录
		function doPrintEnd(){	
			var report_sn = Qm.getValuesByName("report_sn").toString();		
			var big_class = Qm.getValueByName("big_class").toString();	
			
			var comArr = Qm.getValuesByName("report_com_name");	// 使用单位	
			var first_com = comArr[0];
			var diff_com = false;
			
			for(var i=1;i<comArr.length;i++){
				if(comArr[i] != first_com){
					diff_com = true;
					$.ligerDialog.error("只能打印使用单位一致的报告，请重新选择！");
					return;
				}
			}
			
			if(!diff_com){
				top.$.dialog({
					width : $(top).width(),
					height :  $(top).height()-40,
					lock : true,
					title : "打印检验资料归档目录",
					parent: api,
					content : 'url:app/query/docEditor.jsp?status=modify',	
					data: {pwindow: window, report_sn: report_sn, device_type: big_class, report_com_name: first_com}
				}).max();
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
				content : 'url:app/flow/report_attachment_upload.jsp?status=detail&info_id=' + Qm.getValueByName("inspection_info_id").toString()
			});
		}
		
		function printInfo(){
			var report_types = Qm.getValuesByName("report_type").toString();
			var report_type = "";
			if(report_types.indexOf(",")!=-1){
				report_type = report_types.split(",");
				for(var i = 0 ; i < report_type.length ; i++){
					for(var j = 1 ; j < report_type.length ; j++){
						if(report_type[i]!=report_type[j]){
							$.ligerDialog.alert("只能选择同一个模板的报告");
							return;
						}
					}
				}
				report_types = report_type[0];
			} 
	
			$.getJSON('report/query/queryModle.do?report_type='+report_types,function(data){
				if(data){
					var ids = Qm.getValuesByName("inspection_info_id").toString();
					var w=window.screen.availWidth;
					var h=(window.screen.availHeight);			
					top.$.dialog({
						width : w, 
						height :h, 
						lock : true, 
						title:"打印信息表",
						content: 'url:app/query/report_print_index_message.jsp?report_type='+report_types+'&ids='+ids,
						data : {"window" : window}
					}).max();
				}else{
					$.ligerDialog.alert("没有找到信息表模板！");
				}
			});
		}
		
		// 启动流程
		function startFlows(){
			if(confirm("亲，确定启动流程吗？启动后无法撤回哦！")){
				$("body").mask("正在启动流程，请稍后！");
				$.getJSON("inspectionInfo/basic/startFlows.do?infoId="+Qm.getValuesByName("inspection_info_id").toString()+"&big_class="+Qm.getValuesByName("big_class").toString()+"&check_type_code="+Qm.getValuesByName("check_type_code").toString(), function(resp){
					if (resp["success"]) {
						$("body").unmask();
			         	top.$.dialog.notice({content:'流程启动成功！'});
						Qm.refreshGrid();
			     	}else{
			     		$("body").unmask();
			      		$.ligerDialog.error('提示：' + resp.msg);
			    	}
				})
			}
		}
		
		// 预收金额合计
		function payTotal(){
			var amount = Qm.getValuesByName("advance_fees").toString();
			doTotal(amount,"预收金额");
		}
		
		function doTotal(amount,title){
			var str = new Array();
			str = amount.split(",");
			var total = 0;
			if (str != null && str.length > 0) {
				for ( var i = 0; i < str.length; i++) {
					if(str[i]==''||str[i]==null){
							str[i]=0;
					}
					total = total + parseFloat(str[i]);
				}
				$.ligerDialog.alert(title+'合计：' + total + "元。");
			}
		}
		
		function render(value,data){
		    for (var i in data) {
		    	if (data[i]["text"] == value)
		        {
		    		
		        	return data[i]['id'];
		        }
				if(data[i].children)
				{
					for(var j in data[i].children)
					{
						if(data[i].children[j]["text"] ==value)
							return data[i].children[j]['id'];
						if(data[i].children[j].children)
						{
							for(var k in data[i].children[j].children)
								if(data[i].children[j].children[k]["text"]==value)
								{
									return data[i].children[j].children[k]["id"];
								}
						}
					}
				}
		    }
		    return value;
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
        
        function closewindow(){
			api.close();
		}
    </script>
	</head>
	<body>	
		<div class="item-tm" id="titleElement">
		    <div class="l-page-title">
				<div class="l-page-title-note">提示：列表数据项 
					<font color="black">“黑色”</font>代表电脑出具报告，
					<font color="blue">“蓝色”</font>代表平板出具报告。
				</div>
			</div>
		</div>
		<qm:qm pageid="report_query_dzyz" singleSelect="false">
		</qm:qm>
		<script type="text/javascript">
//    根据 sql或码表名称获得Json格式数据
<%--var aa=<u:dict code="community_type"></u:dict>;--%>
/*Qm.config.columnsInfo.big_class.binddata=<u:dict sql="select substr(t.value,0,1) code, t.name text from pub_code_table_values t,pub_code_table t2 where t.code_table_id=t2.id and t2.code='device_classify' and t.value like '%000' order by t.sort"></u:dict>;*/
<%
	if(StringUtil.isNotEmpty(isDt)){
		%>
		Qm.config.columnsInfo.report_id.binddata=<u:dict sql="select id,report_name from base_reports where report_name like '%梯%' and flag='1'"></u:dict>;
		<%
	}else{
		%>
		Qm.config.columnsInfo.report_id.binddata=<u:dict sql="select id,report_name from base_reports where flag='1'"></u:dict>;
		<%
	}
%>

Qm.config.columnsInfo.area_id.binddata=<u:dict sql="select id,parent_id pid,regional_code code, regional_name text from V_AREA_CODE where parent_id='510100' or parent_id='510000'"></u:dict>;
Qm.config.columnsInfo.check_unit_id.binddata=<u:dict sql="select id code, ORG_NAME text from SYS_ORG where ORG_CODE like 'jd%' or ( ORG_CODE like 'cy%' and ORG_CODE != 'cy4_1' and ORG_CODE != 'cy8') order by orders "></u:dict>;
/*Qm.config.columnsInfo.fee_status.binddata=<u:dict sql="select t.name code, t.name text from pub_code_table_values t, pub_code_table t2 where t.code_table_id = t2.id and t2.code = 'PAYMENT_STATUS'"></u:dict>;*/
Qm.config.columnsInfo.flow_note_name.binddata = [
		{id: '报告录入', text: '报告录入'},
		{id: '报告签发', text: '报告已审核'},
		{id: '打印报告', text: '封存未打印'},
		{id: '报告领取', text: '封存已打印'},
		{id: '报告归档', text: '报告已领取'}

];
Qm.config.columnsInfo.data_status.binddata = [
	{id: '1', text: '正常'},
	{id: '99', text: '已作废'}
];
Qm.config.columnsInfo.is_mobile.binddata = [
	{id: '0', text: '电脑出具'},
	{id: '1', text: '平板出具'}
];
/*
Qm.config.columnsInfo.error_id.binddata = [
	{id: '1', text: '是'},
	{id: '0', text: '否'}
];*/
</script>
	</body>
</html>