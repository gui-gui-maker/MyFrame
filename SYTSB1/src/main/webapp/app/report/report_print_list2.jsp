<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.utils.StringUtil"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>检验资料报送打印签收信息列表</title>
		<%@include file="/k/kui-base-list.jsp"%>
		<script type="text/javascript">
	//var userId = "<sec:authentication property='principal.id' htmlEscape='false' />";
	var bar =[];
	//var condition=[];
 	bar =[ 
			 	{ text:'详情', id:'detail',icon:'detail', click: detail},"-",
			 	{ text:'签收', id:'receive',icon:'modify', click: receive},"-",      
			 	{ text:'批量签收', id:'receives',icon:'modify', click: receives},'-', 
				//{ text:'打印', id:'print',icon:'print', click: doPrint},'-',
				{ text:'删除', id:'del',icon:'delete', click: del}
 	]
 	//condition=[{name : "enter_op_id", compare : "=", value : userId}]
 	
 	var qmUserConfig = {
 		sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},			
		sp_fields:[
	         		{name:"org_id", id:"org_id", compare:"="},
	         		{name:"com_name", id:"com_name", compare:"like"},  
	         		{name:"report_sn", id:"report_sn", compare:"like"}, 
					{name:"commit_user_name",compare:"like"},
					{name:"commit_date",compare:"=",format:"yyyy-MM-dd"},
					{name:"data_status", id:"data_status", compare:"="}
	            ],
		tbar:bar,
	   	listeners: {
	    	selectionChange : function(rowData,rowIndex){
	       		var count=Qm.getSelectedCount();//行选择个数
	         	Qm.setTbar({detail:count==1,receive:count==1,receives:count>0,print:count==1});
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
	
	// 签收
	function receive(id){
		if($.type(id)!="string"){
			id = Qm.getValueByName("id").toString();
		}		
		top.$.dialog({
			width : 1000, 
			height : 600, 
			lock : true,
			title : "签收报送打印",
			content : 'url:app/report/receive_report_print_list.jsp?report_print_id='+ id,
			data : {
				"window" : window
			}
		});
	}
	
	// 批量签收
	function receives(){
		var statusArr = Qm.getValuesByName("data_status");	// 数据状态
		for(var i=0;i<statusArr.length;i++){
			if(statusArr[i] != '已提交'){
				$.ligerDialog.error("亲，您所选的数据中，包含已签收的数据，不能重复签收哦，请重新选择！");
				return;
			}
		}
		top.$.dialog({
			width : 600,
			height : 300,
			lock : true,
			title : "签收",
			content : 'url:app/report/receives_report_print.jsp?status=modify&ids='
						+ Qm.getValuesByName("id").toString() + '&org_id=' + Qm.getValueByName("org_id").toString(),
			data : {
				"window" : window
			}
		});	
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
	
	function refreshGrid() {
		Qm.refreshGrid();
	}
</script>
	</head>
	<body>
		<qm:qm pageid="report_print_list" singleSelect="false"><!-- script="false"  -->
				<qm:param name="data_status" compare="in" value="('1','2')" dataType="user"/>	
		</qm:qm>
		<script type="text/javascript">
		Qm.config.columnsInfo.org_id.binddata=<u:dict sql="select id code, ORG_NAME text from SYS_ORG where ORG_CODE like 'jd%' or ( ORG_CODE like 'cy%' and ORG_CODE != 'cy4_1') order by orders "></u:dict>;
		</script>
	</body>
</html>
