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
<title>移动端原始记录数据列表（罐车）</title>
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
%>
<script type="text/javascript">
	var w = window.screen.availWidth;
	var h = window.screen.availHeight;
	var qmUserConfig = {
		sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:100},	// 默认值，可自定义
		sp_fields : [ 
			{name:"device_registration_code", id:"device_registration_code", compare:"like"},
			{name:"device_name", id:"device_name", compare:"like"},
			{name:"report_sn", id:"report_sn", compare:"like"},
			{name:"internal_num", id:"internal_num", compare:"like"},
			{name:"report_com_name", id:"report_com_name", compare:"like"},
			<%
			if(!org_code.contains("cy")){
				%>				
				{name:"enter_op_name", id:"enter_op_name", compare:"like"},
				<%
			}
			%>
			{group:[
				{name:"advance_time", id:"advance_time", compare:">="},
				{label:"到", name:"advance_time", id:"advance_time1", compare:"<=", labelAlign:"center", labelWidth:20}
			]},
			{name:"info_status", id:"info_status", compare:"="},
			{name:"is_report_confirm", id:"is_report_confirm", compare:"="}
		],
		tbar : [
			<%
			if(org_code.contains("cy")){
				%>
				{ text : '生成报告', id : 'startFlow', click : startFlow, icon : 'submit' },"-",
				<%
			}
			%>
			{ text : '预览', id : 'showRecord', click : showRecord, icon : 'detail' },"-",
			{ text : '修改', id : 'modifyRecord', click : modifyRecord, icon : 'modify' },"-",
			{ text:'打印', id:'print',icon:'print', click: doPrintRecord},'-', 
			{ text:'查看报告', id:'showReport',icon:'detail', click: showReport},'-', 
			{ text:'查看图片', id:'showPics',icon:'detail', click: showPics},'-', 
			{ text : '流转过程', id:'turnHistory',icon:'follow', click: turnHistory}
			<%
			if("admin".equals(user.getAccount())){
				%>
				, '-', { text : '删除', id : 'del', click : del, icon : 'del'}
				<%
			}
			%>
		],
		listeners : {
			rowClick : function(rowData, rowIndex) {
			},
			rowDblClick : function(rowData, rowIndex) {
				//createReport();
			},
			selectionChange : function(rowData, rowIndex) {
				selectionChange();
			},
			rowAttrRender : function(rowData, rowid) {
				 var fontColor="black";
		         	// 数据状态（0：未生成报告）
		            if (rowData.info_status == '0'){
		            	fontColor="blue";
		            }
		            // 数据状态（1：已生成报告）
		            if (rowData.info_status == '1'){
		            	fontColor="green";
		            }
		         	// 数据状态（2：原始记录已修改）
		            if (rowData.info_status == '2'){
		            	fontColor="orange";
		            }
		         	// 数据状态（99：原始记录已修改）
		            if (rowData.info_status == '99'){
		            	fontColor="red";
		            }
		            return "style='color:"+fontColor+"'";
			}
		}
	};

	//行选择改变事件
	function selectionChange() {
		var count = Qm.getSelectedCount();//行选择个数
		if (count == 0) {
			Qm.setTbar({
				startFlow : false,
				showRecord : false,
				modifyRecord : false,
				showReport : false,
				showPics : false,
				turnHistory : false,
				print : false,
				del : false
			});
		} else if (count == 1) {
			Qm.setTbar({
				startFlow : true,
				showRecord : true,
				modifyRecord : true,
				showReport : true,
				showPics : true,
				turnHistory : true,
				print : true,
				del : true
			});
		} else {
			Qm.setTbar({
				startFlow : true,
				showRecord : false,
				modifyRecord : false,
				showReport : false,
				showPics : false,
				turnHistory : false,
				print : true,
				del : true
			});
		}
	}
	
	// 生成报告
	function startFlow() {
		var isStart = false;
		var isStarted = false;
		var toStart = true;
		var check_status = Qm.getValuesByName("checkstatus");
		var is_report_confirm = Qm.getValuesByName("is_report_confirm");
		var check_type_code=Qm.getValuesByName("check_type_code");
		var info_status = Qm.getValuesByName("info_status");
		var inspection_id= Qm.getValuesByName("inspection_id");
	    var id=Qm.getValuesByName("id");
		
		for(var j=0;j<check_status.length;j++){
			if(check_status[j] == "1"){
				toStart = false;
				$.ligerDialog.alert("亲，您所选的原始记录中包含中止检验的记录，不能生成报告哦，请检查！", "提示");
			}
		}
		for(var k=0;k<is_report_confirm.length;k++){
			if(is_report_confirm[k] != "1"){
				toStart = false;
				$.ligerDialog.alert("亲，您所选的原始记录中包含未校核或校核未通过的记录，暂不能生成报告哦！", "提示");
			}
		}
		
		if(toStart){
			for(var i=0;i<info_status.length;i++){
				if(info_status[i] == "1"){
					isStart = true;
				}else if(info_status[i] == "2"){
					isStarted = true;
				}
			}
			if(isStart){
				$.ligerDialog.alert("亲，您所选的原始记录中包含已生成报告的记录，不能重复生成哦，请检查！", "提示");
				toStart = false;
			}
			
			if(toStart){
				var tips = "亲，确定设备数据已录入完整要生成报告吗？";
				if(isStarted){
					tips = "亲，您所选的原始记录中包含已生成报告的记录哦，确定要覆盖所有数据重新生成报告吗？覆盖操作请谨慎操作哦！";
				}
				if(confirm(tips)){
					$("body").mask("正在提交数据，请稍后！");
					$("#startFlow").attr("disabled","disabled");
					$.getJSON("report/item/record/startFlowTanker.do?ids="
							+ inspection_id
							+ "&info_ids="
							+ id
							+ "&checktypes="
							+check_type_code,
							function(resp) {
								if (resp) {
									$("body").unmask();
									$.ligerDialog.alert(resp.msg, "提示");
									$("#startFlow").removeAttr("disabled");
									refreshGrid();
								}
					});
				}
			}
		}
	}
	
	//删除
	function del() {
		$.ligerDialog.confirm("亲，确定要删除所选原始记录吗？删除后不可恢复哦！请谨慎操作！", function(yes) {
			if (yes) {
				$.ajax({
					url : "report/item/record/del.do?ids="
							+ Qm.getValuesByName("id").toString(),
					type : "post",
					async : false,
					success : function(data) {
						if (data.success) {
							top.$.notice("删除成功！");
							Qm.refreshGrid();
						} else {
							top.$.notice("删除失败！" + data.msg);
						}
					}
				});
			}
		});
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
	
	// 查看原始记录
	function showRecord() {
		var id = Qm.getValueByName("id").toString();
		var report_id = Qm.getValueByName("report_id").toString();	// 报告类型
		var url = "report/item/record/showGcRecord.do?id="+id+"&report_id="+report_id;
		top.$.dialog({
			width : w, 
			height : h, 
			lock : true, 
			title:"查看原始记录",
			content: 'url:'+url,
			data : {"window" : window}
		}).max();
	}
	
	// 修改原始记录
	function modifyRecord() {
		var id = Qm.getValueByName("id").toString();
		var report_id = Qm.getValueByName("report_id").toString();	// 报告类型
		var device_id = Qm.getValueByName("device_id").toString();	// 设备ID
		var flow_note_name = Qm.getValueByName("flow_note_name").toString();	// 当前流程
		if(flow_note_name!=null && flow_note_name!="" && flow_note_name!="报告录入"){
			$.ligerDialog.alert("亲，您所选的报告当前正在审批中不能修改，请先确保报告已退回至“报告录入”阶段后再进行修改操作！如报告已签发请提交纠错申请再重新上传原始记录！", "提示");
		}else{
			var url = "report/item/record/openGcCatalog.do?id="+id+"&report_id="+report_id+"&device_id="+device_id;
			top.$.dialog({
				width : 600, 
				height : 500, 
				lock : true, 
				title:"修改原始记录",
				content: 'url:'+url,
				data : {"window" : window}
			});
		}
	}
	
	function saveRecordDetail(infoId, reportId, device_id) {
		top.$.dialog({
			width : w, 
			height : h,  
			lock : true, 
			title:"修改原始记录",
			content: "url:report/item/record/modifyRecord.do?isSub=yes&id="+infoId+"&report_id="+reportId+"&device_id="+device_id,
			data : {"window" : window}
		}).max();
	}
	
	// 打印原始记录
	function doPrintRecord(){	
		var ids = Qm.getValuesByName("id").toString();
		top.$.dialog({ 	
			width : w, 
			height : h, 
			lock : true, 
			title:"打印原始记录",
			content: 'url:app/tanker/gc_record_print_index.jsp?id='+ids,
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
			var inspect_date = Qm.getValueByName("advance_time").toString();
			var date = inspect_date.substring(0,4)+inspect_date.substring(5,7)+inspect_date.substring(8,10);
			var to_swf = Qm.getValueByName("to_swf").toString();
			if(to_swf==null||to_swf==""){
				$("body").mask(" 第一次查看该报告，正在准备文档，请稍后......");
				 $.post("inspectionInfo/basic/pdf2Swf.do",{"pdfPath":date+"/"+report_sn+"/"+report_sn+".pdf","swfPath":date+"/"+report_sn,"swfName":report_sn},function(res){
	        		   if(res.success){
	        			   $("body").unmask();
	        				//alert(date+"/"+report_sn+"/"+report_sn+".swf")
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
				//alert(date+"/"+report_sn+"/"+report_sn+".swf")
				 top.$.dialog({
					width : 800, 
					height : 400, 
					lock : true, 
					title:"查看报告",
					content: 'url:app/query/showFile.jsp?status=detail&path='+date+"/"+report_sn+"/"+report_sn+".swf"+'&id='+id,
					data : {"window" : window,"report_sn":report_sn,"date":ispdf}
				}).max(); 
			}
			
			/* var report_sn = Qm.getValueByName("report_sn").toString();
			top.$.dialog({
				width : 800, 
				height : 400, 
				lock : true, 
				title:"查看报告",
				content: 'url:app/flow/reportPdfPrint/report_doc.jsp?status=detail&id='+id,
				data : {"window" : window,"report_sn":report_sn,"date":ispdf}
			}).max(); */
		}				
	}
	
	// 预览并下载图片
	function showPics() {
		top.$.dialog({
			width : 800, 
			height : 500, 
			lock : true, 
			title : "查看图片", 
			data : {"window" : window},
			cancel : true,
			content : 'url:app/mobile/record_pic_list.jsp?info_id=' + Qm.getValueByName("id").toString()
		});
	}
	
	function refreshGrid() {
		Qm.refreshGrid();
	}
</script>
</head>
<body>
	<div class="item-tm" id="titleElement">
	    <div class="l-page-title">
			<div class="l-page-title-note">提示：列表数据项
				<font color="blue">“蓝色”</font>代表原始记录未生成报告；
				<font color="green">“绿色”</font>代表原始记录已生成报告；
				<font color="orange">“桔色”</font>代表原始记录已修改，未生成报告；
				<font color="red">“红色”</font>代表原始记录已作废。
			</div>
		</div>
	</div>
	<qm:qm pageid="gc_record_list">
		<%
			if(StringUtil.isNotEmpty(emp_id) && !"100082".equals(org_id) && !"100027".equals(org_id)){
				%>
				<qm:param name="enter_op_id" value="<%=emp_id %>" compare="=" />
				<%
				if(org_code.contains("cy")){
					%>
					<qm:param name="check_unit_id" value="<%=org_id %>" compare="=" />
					<%
				}
			}
			if("100027".equals(org_id)){
				%>
				<qm:param name="flow_note_name" value="打印报告" compare="=" />
				<%
			}
		%>
	</qm:qm>
	<script type="text/javascript">
	Qm.config.columnsInfo.info_status.binddata = [
		{id: '0', text: '未生成报告'},
		{id: '1', text: '已生成报告'},
		{id: '2', text: '已修改，未生成报告'},
		{id: '99', text: '已作废'}
	];
	Qm.config.columnsInfo.is_report_confirm.binddata = [
	                                      		{id: '0', text: '未校核'},
	                                      		{id: '1', text: '校核通过'},
	                                      		{id: '2', text: '校核未通过'}
	                                      	];
	</script>
</body>
</html>
