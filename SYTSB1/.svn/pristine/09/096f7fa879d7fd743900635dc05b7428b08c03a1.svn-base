<%@page import="com.khnt.rtbox.template.constant.RtPath"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://bpm.khnt.com" prefix="bpm" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>报告录入</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript" src="rtbox/app/templates/rtbox.js"></script>
<script type="text/javascript" src="pub/bpm/js/util.js"></script>
<script type="text/javascript">
	var w = window.screen.availWidth;
	var h = window.screen.availHeight;
	var userId = "<sec:authentication property='principal.id' htmlEscape='false' />";
	var	unitcode="";
	var type = "";
	var flow_num ="${param.flow_num}";
	var buff = "${param.function}";
	var bar =[];
	var top_name="";
	var type_name="";
	// 报告送审、报告录入
	if(buff.indexOf("_tjsh")!=-1){
		bar = [ {text : '修改报告',id : 'input',icon : 'modify',click : modifyReport}, "-",
		        {text : '修改金额',id : 'modifyMoney',icon : 'modify',click : modifyMoney}, "-",
		        {text : '自编号',id : 'self_sn',icon : 'modify',click : self_sn}, "-",
 		       	{text : '流转过程', id:'flow_note',icon:'follow', click: getFlow}, "-",
				{text : '报告送审',id : 'submit',icon : 'search',click : subCheck},"-",
				{text : '提交记录',id : 'subHistory',icon : 'detail',click : subHistory}, "-",
				{text : '报告作废',id : 'del',icon : 'delete',click : del}, "-",
				{text : '返回', id:'return',icon:'return', click: back}
		];
	}
	
	// 报告审核
	if(buff.indexOf("_bgsh")!=-1){
		bar =[{ text:'批量审核', id:'batchCheck',icon:'search', click: batchCheck},"-",{ text:'单份审核', id:'check',icon:'search', click: check},"-",
			{ text:'批量退回', id:'subBack',icon:'back', click: subBack},"-",
			{ text:'流转过程', id:'flow_note',icon:'follow', click: getFlow},"-",
			{ text:'返回', id:'back',icon:'back', click: back}
		];
		type="04";
		top_name="报告审核";
		type_name="审核";
	}
	
	// 报告签发
	if(buff.indexOf("_bgqf")!=-1){
		bar =[{ text:'批量签发', id:'batchCheck',icon:'search', click: batchCheck},"-",{ text:'单份签发', id:'check',icon:'search', click: check},"-",
			{ text:'批量退回', id:'qfBack',icon:'back', click: qfBack},"-",
			{ text:'流转过程', id:'flow_note',icon:'follow', click: getFlow},"-",
			{ text:'返回', id:'back',icon:'back', click: back}
		];
		type="05";
		top_name="报告签发";
		type_name="签发";
	}
	
	// 报告打印
	if(buff.indexOf("_dybg")!=-1){
		bar =[{ text:'查看报告', id:'showReport',icon:'detail', click: showReport}, "-",
      		{ text:'打印报告', id:'print',icon:'print', click: doPrintReport}, "-",
      		//{text :'报告作废',id : 'del',icon : 'delete',click : del},
          	{ text:'流转过程', id:'flow_note',icon:'follow', click: getFlow}
        ];
	}

	// 报告领取 
	if(buff.indexOf("_bglq")!=-1){
		bar =[ { text:'查看报告', id:'showReport',icon:'detail', click: showReport},"-",
		       { text:'报告领取', id:'getReport',icon:'modify', click: getReport},"-",
		       //{text :'报告作废',id : 'del',icon : 'delete',click : del},
		       { text:'流转过程', id:'flow_note',icon:'follow', click: getFlow},"-",
	           { text:'领取记录', id:'history',icon:'detail', click: history},"-",
	           { text:'返回', id:'back',icon:'back', click: back}
		];
	}

	// 报告归档
	if(buff.indexOf("_bggd")!=-1){
		bar =[{ text:'查看报告', id:'showReport',icon:'detail', click: showReport},"-",
		      { text:'流转过程', id:'flow_note',icon:'follow', click: getFlow},"-",
		      { text:'报告归档', id:'reportEnd',icon:'info-save', click: reportEnd},"-",
		      { text:'返回', id:'back',icon:'back', click: back}
		];
	}

	var qmUserConfig = {
		sp_fields : [
		{
			name : "sn",
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
			name : "com_name",
			compare : "like",
			value : ""
		},
		{
			name : "device_name",
			compare : "like",
			value : ""
		}
		],
		tbar :bar,
		listeners : {
			selectionChange : function(rowData, rowIndex) {
				var count = Qm.getSelectedCount();//行选择个数
				if(buff.indexOf("dt_receive")!=-1){
					selectionChange();
				}else{
					Qm.setTbar({submit:count>0,subBack:count>0,qfBack:count>0,del:count>0,getReport : count>0,flow_note : count==1,print: count>0,showReport : count==1,input : count == 1,check : count == 1,batchCheck:count>1,reportEnd:count>0,self_sn : count==1,modifyMoney:count>0});
				}
				
			},
			rowAttrRender : function(rowData, rowid) {
				var fontColor="black";
		     	// 数据状态（1：初始导入2：已修改）
				if (rowData.is_report_input == '2'){
		       		fontColor="blue";
		  		}
		        return "style='color:"+fontColor+"'";
			}
		}
	};
	
	// 行选择改变事件
	function selectionChange() {
   		var count = Qm.getSelectedCount(); // 行选择个数
     	if(count == 1){
            Qm.setTbar({history: true, flow_note: true, showReport: true, getReport: true, back:true, modifyMoney:true});            	
 		}else if(count > 1){
       		Qm.setTbar({history: true, flow_note: false, showReport: false, getReport: true, back:true, modifyMoney:true});
    	}else{
    		Qm.setTbar({history: true, flow_note: false, showReport: false, getReport: false, back:true, modifyMoney:false});
    	}
	}

	// 返回到【待处理业务】列表页
	function back(){
		window.location.href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/inspection/zzjd/getFlowInfo.do";
	}

	// 报告送审（提交初审）
	function subCheck(){
		var is_input = Qm.getValuesByName("is_report_input").toString();
		var examine_names = Qm.getValuesByName("examine_name").toString();
		if(is_input == "" || is_input == null){
			$.ligerDialog.alert("所选数据报告未录入，请重新选择！");
			return;
		}
		if(is_input.indexOf(",")!= -1){
			var temp = is_input.split(",");
			var names = examine_names.split(",");
			for(var i = 0 ; i < temp.length ; i++){
				if(temp[i] != "2"){
					if(temp[i] == "1" && names[i]==""){
						//$.ligerDialog.alert("所选数据报告未录入，请重新选择！");
						//return;
					}
					
				}
			}
		}else{
			if(is_input != "2"){
				if(is_input == "1" && examine_names==""){
					//$.ligerDialog.alert("所选数据报告未录入，请重新选择！");
					//return;
				}
			}
		}
		
		var check_flows = Qm.getValuesByName("check_flow");
		var check_flow1 = check_flows[0];
		for (var i = 1; i < check_flows.length; i++) {
			if(check_flows[i]!=check_flow1){
				$.ligerDialog.error('提示：请选择相同审批级别的报告！');
				return;
			}
		}
		top.$.dialog({
			width : 600, 
			height : 400, 
			lock : true, 
			title:"报告送审",
			parent:api,
			content: 'url:app/flow/zzjd_audit_submit.jsp?infoId='+Qm.getValuesByName("id")+'&flow_num='+flow_num+'&check_flow='+check_flow1+'&acc_id='+Qm.getValuesByName("activity_id"),
			data : {"window" : window}
		});
	}

	// 审核报告时，退回报告
	function subBack(){
		top.$.dialog({
			width : 600, 
			height : 150, 
			lock : true, 
			title:"报告审核时，退回报告",
			parent:api,
			content: 'url:app/flow/report_zzjd_batch_back.jsp?type='+type+'&infoId='+Qm.getValuesByName("id")+'&flow_num='+flow_num+'&acc_id='+Qm.getValuesByName("activity_id"),
			data : {"window" : window}
		});
	}

	// 签发报告时，退回报告
	function qfBack(){
		top.$.dialog({
			width : 600, 
			height : 200, 
			lock : true, 
			title:"报告签发时，退回报告",
			parent:api,
			content: 'url:app/flow/report_zzjd_qf_back.jsp?type='+type+'&infoId='+Qm.getValuesByName("id")+'&flow_num='+flow_num+'&acc_id='+Qm.getValuesByName("activity_id"),
			data : {"window" : window}
		});
	}

	// 报告提交记录
	function subHistory(){
		top.$.dialog({
			width : 800,
			height : 600,
			lock : true,
			title : "提交记录",
			content : 'url:app/flow/report_zzjd_subHistory_list.jsp',
			data : {
				"window" : window
			}
		});
	}

	// 报告归档
	function reportEnd(){
		$.ligerDialog.confirm('确定归档所选报告？', function (yes) { 
			if(yes){
				$.getJSON('inspection/zzjd/flow_reportEnd.do',{infoId:Qm.getValuesByName("id").toString(),flow_num:flow_num ,process_id:Qm.getValuesByName("process_id").toString()},function(data){
					if(data){
						top.$.notice("归档成功！");
						refreshGrid();
					}
				});
			}
		});
	}

	// 单份报告审核、报告签发
	function check(){
		var reportType = Qm.getValueByName("reportType") ;
		if(reportType == "2"){
			// 获取报告检验部门
			top.$.dialog({
				width : 800, 
				height : 400,
				lock : true, 
				title:"报告"+(type=="04"?"审核":"签发"),
				content: "url:app/flow/rtcommon/single_audit_detail.jsp"
						+'?status=modify'
						+'&device_type=' + Qm.getValueByName("device_type_code").toString().substring(0, 1)
						+'&device_sort_code=' + Qm.getValueByName("device_type_code").toString()
						+'&report_sn=' + Qm.getValueByName("report_sn")
						+'&fk_report_id=' + Qm.getValueByName("report_type")
						+'&org_id=' + Qm.getValueByName("enter_unit_id").toString()
						//id 就是inspectionInfo.id 
						+'&id='+ Qm.getValueByName("id")
						// 获取报告审批流程（0：两级审核 1：一级审核）
						+'&check_flow='+ Qm.getValueByName("check_flow").toString()
						+'&type='+type
						+"&code="+Qm.getValueByName("rtbox_code")
						+"&flow_num="+flow_num
						+"&activityId="+Qm.getValueByName("activity_id"),
				data : {"window" : window}
			}).max();
		}else{
			var device_type = Qm.getValueByName("device_type_code").toString().substring(0, 1);
			// 获取报告审批流程（0：两级审核 1：一级审核）
			var check_flow = Qm.getValueByName("check_flow").toString();
			top.$.dialog({
				width : 800, 
				height : 500, 
				lock : true, 
				title: top_name,
				content: 'url:inspection/zzjd/reportInfoLoad.do?type='+type+'&device_type='+device_type+'&check_flow='+check_flow+'&flow_num='+flow_num+'&acc_id='+Qm.getValuesByName("activity_id")+'&report_type='+ Qm.getValuesByName("report_type")+'&ins_info_id='+Qm.getValuesByName("id"),
				data : {"window" : window}
			}).max();
		}
	}
	
	// 批量审核或批量签发报告
	function batchCheck(){	
		//判断是不是相同的报告
		var reportTypeArr = Qm.getValuesByName("reportType") ;
		for(var r=1;r<reportTypeArr.length;r++){
			if(reportTypeArr[0]!=reportTypeArr[1]){
				$.ligerDialog.alert("请选择相同类型的报告（新报告|老报告）！");
				return;
			}
		}
		
		//一次审核分数不能超过50
		var ids = Qm.getValuesByName("id");
		if(ids.length>50){
			$.ligerDialog.alert("请勿一次批量操作大于50份报告！");
			return;
		}
		// 获取报告审批流程（0：两级审核 1：一级审核）
		var check_flows = Qm.getValuesByName("check_flow").toString();
		check_flows = check_flows.split(",");
		var first_check_flow = check_flows[0];
		for(var i=1;i<check_flows.length;i++){
			if(check_flows[i] != first_check_flow){
				$.ligerDialog.error("亲，您所选的报告中存在审核流程不一致的报告哦，请重新选择！");
				return;
			}
		}
		// 获取检验部门
		var org_ids = Qm.getValuesByName("enter_unit_id");
		var first_org_id = org_ids[0];
		for(var i=1;i<org_ids.length;i++){
			if(org_ids[i] != first_org_id){
				$.ligerDialog.error("亲，您所选的报告中存在不同检验部门的报告哦，请重新选择！");
				return;
			}
		}
		//设备类别
		var device_type = Qm.getValueByName("device_type_code").toString().substring(0, 1);
		var device_sort_code = Qm.getValueByName("device_type_code").toString();
		//浏览器高宽
		var w=window.screen.availWidth;
		var h=(window.screen.availHeight);	
		//地址
		var url = "";
		var params = {};
		if(reportTypeArr[0] == "2"){//新报告
			url = "url:app/flow/rtcommon/batch_index_detail.jsp?status=modify&type="+type;
			params['ids']=ids;
			//审核or签发
			params['type']=type;
			params['device_type']=device_type;
			params['device_sort_code']=device_sort_code;
			// 获取报告审批流程（0：两级审核 1：一级审核）
			params['check_flow']=first_check_flow;
			params['acc_id'] = Qm.getValuesByName("activity_id");
			params['flow_num']=flow_num;
			params['org_id']=first_org_id;
			params['codes']=Qm.getValuesByName("rtbox_code");
			params['report_sns']=Qm.getValuesByName("report_sn");
			params['fk_report_ids']=Qm.getValuesByName("report_type");
			params['is_batch']=1;
			params['isBatchBusiness']=1;
		}else{//老报告
			var report_name = Qm.getValueByName("report_name");
			url = 'url:app/flow/report/report_zzjd_batch_index.jsp?ids='+ids
			+'&type='+type
			+'&report_name='+report_name
			+'&check_flow='+first_check_flow
			+'&device_type='+device_type
			+'&acc_id='+Qm.getValuesByName("activity_id").toString()
			+'&flow_num='+flow_num;
		}
		//打开窗口
		top.$.dialog({
			width : w, 
			height :h, 
			lock : true, 
			title:"批量"+type_name,
			content: url,
			data : {"window" : window,"params":params}
		}).max();
	}

	// 打开报告、加载报告
	function saveReport(infoId,reportId) {
		top.$.dialog({
			width : 800, 
			height : 500, 
			lock : true, 
			title:"报告信息",
			content: 'url:inspection/zzjd/reportInfoLoad.do?isSub=yes&type=input'
					+'&report_type='+reportId+'&ins_info_id='+infoId,
			data : {"window" : window}
		}).max();
	}

	// 修改报告（报告录入）
	function modifyReport() {
		var reportType = Qm.getValueByName("reportType") ;
		if(reportType == "2"){
			var reportPath = "<%=RtPath.tplPageDir%>";
            var id = Qm.getValueByName("id");
            var code = Qm.getValueByName("rtbox_code");
            var check_op = Qm.getValueByName("check_op_id");
            var fk_report_id = Qm.getValueByName("report_type");
			var param=[{name:"id",value:id},
				{name:"fk_inspection_info_id",value:id},
                {name:"fk_report_id",value:fk_report_id},
                {name:"input",value:"1"},
                {name:"status",value:"add"},
                {name:"pageStatus",value:"modify"},
                {name:"check_op",value:check_op},
                {name:"isBatchBusiness",value:"1"}];
			opRt(code,param,reportPath);
		}else{
			top.$.dialog({
				width : 800,
				height : 600,
				lock : true,
				title : "报告项目",
				content : 'url:inspection/zzjd/flow_reportInput.do?id='
						+ Qm.getValueByName("id")+'&report_type='
						+ Qm.getValueByName("report_type") +'&device_type_code='+Qm.getValueByName("device_type_code"),
				data : {
					"window" : window
				}
			});
		}
	}

	//报告领取
	function getReport() {
		var report_com_name = Qm.getValueByName("made_unit_name");	// 制造单位
		var comArr = Qm.getValuesByName("made_unit_name");			// 制造单位列表
		var first_com = comArr[0];
		for(var i=1;i<comArr.length;i++){
			if(comArr[i] != first_com){
				$.ligerDialog.error("亲，所选报告中包含不同制造单位的报告，不能同时领取哦，请重新选择！");
				return;
			}
		}
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
			data : {"window" : window},
			content : 'url:app/flow/report_zzjd_draw_detail.jsp?status=add&id=' + Qm.getValuesByName("id")+'&acc_id='+Qm.getValuesByName("activity_id")+'&flow_num='+flow_num+'&report_com_name='+encodeURIComponent(report_com_name)
		});
	}

	//报告领取记录
	function history() {
		top.$.dialog({
			width : 700, 
			height : 500, 
			lock : true, 
			title : "报告领取记录", 
			data : {"window" : window},
			content : 'url:app/flow/report_zzjd_draw_history.jsp'
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
		var w = window.screen.availWidth;
		var h = window.screen.availHeight;			
		top.$.dialog({
			width : w, 
			height : h, 
			lock : true, 
			title : "打印报告",
			content: 'url:app/query/report_zzjd_print_index.jsp?id='+ids+'&acc_id='+Qm.getValuesByName("activity_id").toString()+'&flow_num='+flow_num,
			data : {"window" : window}
		}).max();
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

	// 报告作废
	function del(){	
		 $.del("确定作废？",
		    		"inspection/zzjd/delReport.do",
		    		{"ids":Qm.getValuesByName("id").toString()});
	}
	
	// 自编号
	function self_sn(){
		var is_self_sn = Qm.getValueByName("is_self_sn").toString();
		if("否" == is_self_sn){
			$.ligerDialog.alert("所选报告不支持自编号，请核实！");
			return;
		}else{
			top.$.dialog({
				width : 600,
				height : 200,
				lock : true,
				title : "自编号",
				content : 'url:app/flow/report_zzjd_self_sn.jsp?status=modify&id='
							+ Qm.getValuesByName("id").toString(),
				data : {
					"window" : window
				}
			});	
		}
	}
	
	// 修改报告收费金额
	function modifyMoney(){
		var do_modify = true;		
		var modify_moneys = Qm.getValuesByName("modify_money").toString();
		if(modify_moneys.indexOf("0") != -1){
			$.ligerDialog.error("所选报告不支持收费金额修改！");
			do_modify = false;
			return;
		}
		
		if(do_modify){
			var info_ids = Qm.getValuesByName("id").toString();
			var flow_note_names = Qm.getValuesByName("flow_note_name");	
			for(var i=0;i<flow_note_names.length;i++){
				if(flow_note_names[i] != "报告录入" && flow_note_names[i] != "报告送审"){
					$.ligerDialog.error("所选报告正在审批流程中不能修改收费金额，如需修改，请退回至报告录入环节由编制人进行修改操作！");
					do_modify = false;
					return;
				}
			}		
			if(do_modify){
				$.ligerDialog.prompt('请输入报告收费金额，金额必须是数字！', '0.00', function (yes, value){
			   		if (yes){
			   			if(isNaN(value)){
			   				$.ligerDialog.error("注意：只能输入数字！");
			   				return;
			   			}else{
			   				$.ajax({
								url : "inspectionInfo/basic/modifyMoneys.do?info_ids=" + info_ids + "&money="+value,
								type : "post",
								async : false,
								success : function(data) {
									if (data.success) {
										top.$.notice("修改成功！");
										Qm.refreshGrid();
									} else {
										top.$.notice("修改失败！" + data.msg);
									}
								}
							});
						}
			   		}
				})
			}
		}
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
<%
	String userId = SecurityUtil.getSecurityUser().getId();
%>
<div class="item-tm" id="titleElement">
	    <div class="l-page-title">
			<div class="l-page-title-note">提示：列表数据项
				<font color="black">“黑色”</font>代表初始导入的报告，
				<font color="blue">“蓝色”</font>代表已修改的报告。
			</div>
		</div>
	</div>
<qm:qm pageid="report_zzjd_list" script="false" singleSelect="false">
	<qm:param name="flow_note_id" value="${param.flow_num}" compare="=" />
	<qm:param name="fk_flow_index_id" value="${param.flowId}" compare="=" />
	<qm:param name="handler_id" value="<%=userId%>" compare="=" />
</qm:qm>
</body>
</html>
