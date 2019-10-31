<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>报告归档</title>
<%String userId=SecurityUtil.getSecurityUser().getId();%>
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
		text : '打印报告PDF',
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
			name : "sn",
			compare : "="
		}, {
			name : "device_registration_code",
			compare : "like"
		}, {
			name : "com_name",
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

	function openN(i,ids,report_sns,export_pdfs,l,acc_id,flow_num){
		top.$.dialog({
			width : 800, 
			height : 400, 
			lock : true, 
			title:"查看修改报告",
			content: 'url:app/flow/reportPdfPrint/report_doc.jsp?status=detail&print=true&id='+ids[i],
			data : {"window" : window,"report_sn":report_sns[i],"date":export_pdfs[i],"acc_id":acc_id[i],"flow_num":flow_num[i]},
			close:function(){
				i++;
				if(i<l){
					openN(i,ids,report_sns,export_pdfs,l,acc_id,flow_num);
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
	var l = ids.length;
	openN(0,ids,report_sns,export_pdfs,l,acc_id,flow_num);
	
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
		title:"查看修改报告",
		content: 'url:app/flow/reportPdfPrint/report_doc.jsp?status=detail&id='+id,
		data : {"window" : window,"report_sn":report_sn,"date":export_pdf}
	}).max();
}
</script>
</head>
<body>
	<qm:qm pageid="report_print_pdf2" script="false" singleSelect="false" seachOnload="true">
		<qm:param name="activity_name" value="打印报告" compare="=" />	
		<qm:param name="handler_id" value="<%=userId %>" compare="=" />
		
	</qm:qm>
</body>
</html>
