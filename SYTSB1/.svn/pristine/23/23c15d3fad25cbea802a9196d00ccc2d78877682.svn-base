<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.rtbox.template.constant.RtPath"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>报告归档</title>
<%String userId=SecurityUtil.getSecurityUser().getId();%>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript" src="rtbox/app/templates/rtbox.js"></script>
<script type="text/javascript">
	var userId = "<sec:authentication property='principal.id' htmlEscape='false' />"

	var bar = [ {
		text : '报告归档',
		id : 'check',
		icon : 'table-save',
		click : reportEnd
	}, {
		text : '报告生成PDF',
		id : 'pdf',
		icon : 'save',
		click : reportPdf
	}, {
		text : '查看报告',
		id : 'showReport',
		icon : 'detail',
		click : showReport
	}, {
		text : '流转过程',
		id : 'flow_note',
		icon : 'follow',
		click : getFlow
	}, {
		text : '批量退回',
		id : 'toBack',
		icon : 'back',
		click : toBack
	}];
	
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
		},{
			name : "org_name",
			compare : "like"
		}, {
			name : "device_registration_code",
			compare : "like"
		}, {
			name : "com_name",
			compare : "like"
		} ,{group:[
					{name:"pulldown_time", compare:">="},
					{label:"到", name:"pulldown_time",  compare:"<=", labelAlign:"center", labelWidth:20}
			]}
		],
		tbar : bar,
		listeners : {
			selectionChange : function(rowData, rowIndex) {
				var count = Qm.getSelectedCount();//行选择个数
				Qm.setTbar({
					check : count > 0,
					//pdf : count > 0,
					flow_note : count == 1,
					showReport : count == 1,
					toBack : count > 0
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

	//流转过程
	function getFlow() {
		top.$.dialog({
			width : 400,
			height : 700,
			lock : true,
			title : "流程卡",
			content : 'url:department/basic/getFlowStep.do?ins_info_id='
					+ Qm.getValuesByName("id"),
			data : {
				"window" : window
			}
		});
	}

	function reportPdf() {
		var ids = Qm.getValuesByName("id").toString();
		var w=window.screen.availWidth;
		var h=(window.screen.availHeight);
		top.$.dialog({
			width : w, 
			height :h, 
			lock : true, 
			title:"导出报告",
			content: 'url:app/flow/export/report_export_index.jsp?id='+ids+'&acc_id='+Qm.getValuesByName("activity_id").toString()+'&flow_num='+Qm.getValueByName("flow_num_id").toString()+'&printType=0',
			data : {"window" : window}
		}).max();
	}
	// 查看报告
	function showReport() {
		var w = window.screen.availWidth;
		var h = (window.screen.availHeight);
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
	function reportEnd() {
		$.ligerDialog.confirm('确定归档该数据?', function(yes) {
			if (yes) {
				$("body").mask("正在提交数据，请稍后！");
				$.getJSON('department/basic/flow_reportEnd.do', {
					infoId : Qm.getValuesByName("id").toString(),
					flow_num : Qm.getValueByName("flow_num_id"),
					process_id : Qm.getValuesByName("process_id").toString()
				}, function(data) {
					if (data) {
						$("body").unmask();
						top.$.notice("归档成功！");
						submitAction();
					}
				});
			}
		});
	}

	function toBack(){
		top.$.dialog({
			width : 600, 
			height : 150, 
			lock : true, 
			title:"报告归档时退回",
			parent:api,
			content: 'url:app/flow/report_end_batch_back.jsp?infoId='+Qm.getValuesByName("id")+'&flow_num='+Qm.getValuesByName("flow_note_id")+'&acc_id='+Qm.getValuesByName("activity_id"),
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
	function submitAction() {
		Qm.refreshGrid();
	}
</script>
</head>
<body>
	<qm:qm pageid="report_end_list" script="false" singleSelect="false" seachOnload="true">
		<qm:param name="activity_name" value="报告归档" compare="=" />
		<qm:param name="handler_id" value="<%=userId %>" compare="=" />	
	</qm:qm>
</body>
</html>
