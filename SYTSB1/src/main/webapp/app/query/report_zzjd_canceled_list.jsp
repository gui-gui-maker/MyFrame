<%@page import="com.khnt.rtbox.template.constant.RtPath"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>已封存</title>
		<%@include file="/k/kui-base-list.jsp"%>
		<script type="text/javascript" src="rtbox/app/templates/rtbox.js"></script>
		<script type="text/javascript">
		var w = window.screen.availWidth;
		var h = window.screen.availHeight;
        var qmUserConfig = {
        	sp_defaults:{columnWidth:0.33,labelAlign:'right',labelSeparator:'',labelWidth:100},	// 默认值，可自定义
            sp_fields:[
            	{name:"sn", compare:"like"},
            	{name:"report_sn", compare:"like"},
            	{name:"device_type_code", compare:"="},
            	{name:"device_code", compare:"="},
            	{name:"made_unit_name", compare:"like"},
            	{name:"inspection_unit_name", compare:"like"},
            	{name:"check_op_name", compare:"like"},
            	{name:"lrry", compare:"like"},
            	{name:"enter_time", compare:"like"}
            ],
            tbar:[
            	{ text:'查看报告', id:'showReport',icon:'detail', click: showReport},
            	{ text:'流转过程', id:'turnHistory',icon:'follow', click: getFlow}
            ],
            listeners: {
                selectionChange : function(rowData,rowIndex){
                	selectionChange();
                },
        		rowDblClick :function(rowData,rowIndex){
        		}
            }
        };
        
        // 行选择改变事件
		function selectionChange() {
	   		var count = Qm.getSelectedCount(); // 行选择个数
	     	if(count == 1){
	            Qm.setTbar({del:true,turnHistory: true, showReport: true, print: true});            	
	 		}else if(count > 1){
	       		Qm.setTbar({del:true, turnHistory: false, showReport: false, print: false});
	    	}else{
	    		Qm.setTbar({del:false, turnHistory: false, showReport: false, print: false});
	    	}
		}
		
		// 查看报告
		function showReport(){
			var id = Qm.getValueByName("inspection_info_id").toString();
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
	
		//流转过程
		function  getFlow(){
			 top.$.dialog({
		   		width : 400, 
		   		height : 700, 
		   		lock : true, 
		   		title: "流程卡",
		   		content: 'url:inspection/zzjd/getFlowStep.do?ins_info_id='+Qm.getValueByName("inspection_info_id"),
		   		data : {"window" : window}
		   	});
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
        function refreshGrid() {
            Qm.refreshGrid();
        }
    </script>
	</head>
	<body>	
		<qm:qm pageid="zzjd_canceled_list" singleSelect="false">
		</qm:qm>
	</body>
</html>