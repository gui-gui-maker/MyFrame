<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.utils.StringUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>检验资料报送打印签收信息列表</title>
		<%@include file="/k/kui-base-list.jsp"%>
		<%
			String type = request.getParameter("type");
		%>
		<script type="text/javascript">
	//var userId = "<sec:authentication property='principal.id' htmlEscape='false' />";
	var bar =[];
	//var condition=[];
 	bar =[
 		<%
 			if(StringUtil.isEmpty(type)){
 				%>
 				{ text:'录入', id:'add',icon:'add', click: add},"-", 	  
			 	{ text:'详情', id:'detail',icon:'detail', click: detail},"-",     
			 	{ text:'修改', id:'modify',icon:'modify', click: modify},"-",
			 	{ text:'删除', id:'del',icon:'delete', click: del},"-",
			 	{ text:'提交', id:'commit',icon:'submit', click: commit}//,'-',
			 	//{ text:'打印', id:'print',icon:'print', click: doPrint}
 				<%
 			}else{
 				%>	  
			 	{ text:'详情', id:'detail',icon:'detail', click: detail},"-",   
			 	{ text:'接收', id:'receive',icon:'modify', click: receive}
			 	<%
 			}
 		%>
 	]
 	//condition=[{name : "enter_op_id", compare : "=", value : userId}]
 	
 	var qmUserConfig = {
 		sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},			
		sp_fields:[
			{name:"com_name", id:"com_name", compare:"like"},    
			{name:"report_sn", id:"report_sn", compare:"like"},    
			{group:[
				{name:"enter_date", id:"enter_date1", compare:">="},
				{label:"到", name:"enter_date", id:"enter_date2", compare:"<=", labelAlign:"center", labelWidth:20}
			]},
			{group:[
				{name:"commit_date", id:"commit_date1", compare:">="},
				{label:"到", name:"commit_date", id:"commit_date2", compare:"<=", labelAlign:"center", labelWidth:20}
			]}
	    ],
		tbar:bar,
	   	listeners: {
	    	selectionChange : function(rowData,rowIndex){
	       		var count=Qm.getSelectedCount();//行选择个数
	         	Qm.setTbar({modify:count==1,detail:count==1,del:count>0,commit:count>0,receive:count>0,print:count==1});
	     	}/*,
	    	afterQmReady:function(){
	        	//Qm.setCondition(condition);
	      		Qm.searchData();
	   		}*/
		}
	};
	
	// 详情
	function detail(id){
		if($.type(id)!="string"){
			id = Qm.getValueByName("id").toString();
		}		
		top.$.dialog({
			width : 1000, 
			height : 600, 
			lock : true,
			title : "报送打印详情",
			content : 'url:app/report/report_print_detail.jsp?status=detail&id='+ id,
			data : {
				"window" : window
			}
		});
	}
	
	function add(){	
		top.$.dialog({
			width : 1000, 
			height : 600, 
			lock : true, 
			title : "新增报送打印", 
			data : {"window" : window},
			content : 'url:app/report/report_print_detail.jsp?status=add'
		});
	}

	function modify(){
		var statusArr = Qm.getValuesByName("data_status");	// 数据状态
		for(var i=0;i<statusArr.length;i++){
			if(statusArr[i] != '创建'){
				$.ligerDialog.error("亲，您所选的数据，已提交或已签收，不能删除哦，请重新选择！");
				return;
			}
		}
		top.$.dialog({
			width : 1000, 
			height : 600, 
			lock : true, 
			title:"修改报送打印信息",
			content: 'url:app/report/report_print_detail.jsp?status=modify&id='+Qm.getValueByName("id"),
			data : {"window" : window}
		});
	}
	
	function del(){
		var statusArr = Qm.getValuesByName("data_status");	// 数据状态
		for(var i=0;i<statusArr.length;i++){
			if(statusArr[i] == '已签收'){
				$.ligerDialog.error("亲，您所选的数据中，包含已签收的数据，不能删除哦，请重新选择！");
				return;
			}
		}
		$.del("亲，确定删除此次报送打印的信息吗？删除后系统无法恢复数据哦！",
	    	"report/print/del.do",
	    		{"ids":Qm.getValuesByName("id").toString()},function(result){alert('删除成功！')});
	}
	
	function commit(){
		var statusArr = Qm.getValuesByName("data_status");	// 数据状态
		var ids = Qm.getValuesByName("id").toString();
		for(var i=0;i<statusArr.length;i++){
			if(statusArr[i] != '创建'){
				$.ligerDialog.error("亲，您所选的数据中，包含已提交的数据，不能重复提交哦，请重新选择！");
				return;
			}
		}
		if(confirm("亲，确认提交此次报送打印信息？提交后无法修改数据哦！")){
				$.getJSON("report/print/commit.do?ids="+ids, function(resp){
					if(resp.success){
						top.$.dialog.notice({
				             content: "提交成功！"
						});
						refreshGrid();
						doExport(ids);
					}else{
						$.ligerDialog.error("提交失败，未找到系统相关业务流程，请联系系统管理员！");
					}
			})
		}
	}
	
	function receive(){
		var statusArr = Qm.getValuesByName("data_status");	// 数据状态
		for(var i=0;i<statusArr.length;i++){
			if(statusArr[i] != '已提交'){
				$.ligerDialog.error("亲，您所选的数据中，包含已接收的数据，不能重复接收哦，请重新选择！");
				return;
			}
		}
		if(confirm("亲，确认接收此次报送打印信息吗？接收后无法回退数据哦！")){
				$.getJSON("report/print/receive.do?ids="+Qm.getValuesByName("id").toString(), function(resp){
					if(resp.success){
						top.$.dialog.notice({
				             content: "操作成功！"
						});
						refreshGrid();
					}else{
						$.ligerDialog.error("操作失败，未找到系统相关业务流程，请联系系统管理员！");
					}
			})
		}
	}
	
	//打印报送签收表
	function doPrint() {
		var id = Qm.getValueByName("id");
		$.getJSON("report/print/getPrintDetail.do?status=detail&id="+id, function(resp){
			if(resp.success){
				top.$.dialog({
					width : $(top).width(),
					height :  $(top).height()-40,
					lock : true,
					title : "打印报送签收表",
					parent: api,
					content : 'url:app/report/docEditor.jsp?status=modify',	
					data: {pwindow: window, bean: resp.reportPrintRecord}
				}).max();
			}
		})
	}
	
	function refreshGrid() {
		Qm.refreshGrid();
	}
	//新报表导出pdf报告
    function doExport(reportPrintIds) {
		if(reportPrintIds != null && reportPrintIds != "undefined"){
			$.post("report/print/queryReportPrints.do", {"id": reportPrintIds,"reportType": "2"}, function (response) {
				if(response["success"]){
					var inspectionInfos = response["data"];
					if(inspectionInfos != null && inspectionInfos != "undefined"){
						var infoIds;
						for(var i = 0; i < inspectionInfos.length; i++){
							var inspectionInfo = inspectionInfos[i];
							if(infoIds != null && infoIds != "undefined"){
								infoIds += ","+inspectionInfo.id;
							}else{
								infoIds = inspectionInfo.id;
							}
						}
						if(infoIds != null && infoIds != "undefined"){
							$.post("com/rt/printPdfTask/saveTask.do", {"fkInspectionInfoId": infoIds}, function (res) {
					        })
						}
					}
				}
	        })
		}
    }
</script>
	</head>
	<body>
		<qm:qm pageid="report_print_list" singleSelect="false"><!-- script="false"  -->
			<%
				if(StringUtil.isEmpty(type)){
					String userId = SecurityUtil.getSecurityUser().getId();
					%>
					<qm:param name="enter_op_id" value="<%=userId%>" compare="=" />
					<%
				}else{
					%>
					<qm:param name="data_status" compare="in" value="('1','2')" dataType="user"/>	
					<%
				}
			%>
			
		</qm:qm>
	</body>
</html>
