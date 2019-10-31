<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.utils.StringUtil"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>业务服务部前后台报告交接业务信息签收列表</title>
		<%@include file="/k/kui-base-list.jsp"%>
		<script type="text/javascript">
	//var userId = "<sec:authentication property='principal.id' htmlEscape='false' />";
	var bar =[];
	//var condition=[];
 	bar =[ 
			 	{ text:'详情', id:'detail',icon:'detail', click: detail},"-",
			 	{ text:'签收', id:'receive',icon:'modify', click: receive},"-",      
			 	{ text:'批量签收', id:'receives',icon:'modify', click: receives}
 	]
 	//condition=[{name : "enter_op_id", compare : "=", value : userId}]
 	
 	var qmUserConfig = {
 		sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},				
		sp_fields:[
	         		{name:"org_id", id:"org_id", compare:"="},
	         		{name:"com_name", id:"com_name", compare:"like"},     
					{name:"report_sn",compare:"like"},
					{name:"commit_user_name",compare:"like"},
					{name:"commit_date",compare:"="},
					{name:"data_status", id:"data_status", compare:"="}
	            ],
		tbar:bar,
	   	listeners: {
	    	selectionChange : function(rowData,rowIndex){
	       		var count=Qm.getSelectedCount();//行选择个数
	         	Qm.setTbar({detail:count==1,receive:count==1,receives:count>0});
	     	},
			rowDblClick : function(rowData, rowIndex) {
				Qm.getQmgrid().selectRange("id", [rowData.id]);
				detail();
			},
	    	afterQmReady:function(){
	        	//Qm.setCondition(condition);
	      		Qm.searchData();
	   		}
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
			title : "检验报告交接业务详情",
			content : 'url:app/report/report_transfer_detail.jsp?status=detail&id='+ id,
			data : {
				"window" : window
			}
		});
	}
	
	// 签收
	function receive(id){
		var statusArr = Qm.getValuesByName("data_status");	// 数据状态
		for(var i=0;i<statusArr.length;i++){
			if(statusArr[i] != '未签收'){
				$.ligerDialog.error("亲，您所选的数据中，包含已签收的数据，请重新选择！");
				return;
			}
		}
		if($.type(id)!="string"){
			id = Qm.getValueByName("id").toString();
		}	
		/*
		top.$.dialog({
			width : 1000, 
			height : 600, 
			lock : true,
			title : "前台检验报告签收",
			content : 'url:app/report/report_transfer_detail2.jsp?status=modify&id='+ id,
			data : {
				"window" : window
			}
		});
		*/
		top.$.dialog({
			width : 1000, 
			height : 600, 
			lock : true,
			title : "前台检验报告签收",
			content : 'url:app/report/receive_report_transfer_list.jsp?report_transfer_id='+ id,
			data : {
				"window" : window
			}
		});
	}
	
	// 批量签收
	function receives(){
		var statusArr = Qm.getValuesByName("data_status");	// 数据状态
		for(var i=0;i<statusArr.length;i++){
			if(statusArr[i] != '未签收'){
				$.ligerDialog.error("亲，您所选的数据中，包含已签收的数据，请重新选择！");
				return;
			}
		}
		if(confirm("亲，确认签收所选数据吗？签收后无法退回哦！")){
				$.getJSON("report/transfer/receives.do?ids="+Qm.getValuesByName("id").toString(), function(resp){
					if(resp.success){
						refreshGrid();
						if(resp.info_id!=""){
							if(resp.bhg_report_sn!=""){
								top.$.dialog.notice({
						             content: "本次包含不合格的报告（报告编号："+resp.bhg_report_sn+"），不合格的报告不打印合格证！"
								});
							}/*else{
								top.$.dialog.notice({
						             content: "操作成功！正在跳转进行合格证打印..."
								});
							}*/
							
							/* var w=window.screen.availWidth;
							var h=(window.screen.availHeight);		
							top.$.dialog({ 
								width : w, 
								height :h, 
								lock : true, 
								title:"打印标签",
								content: 'url:app/query/report_print_index_tags.jsp?flag=yes&type=2',
								data : {
									"pwindow" : window,
									"id":resp.info_id
								}
							}).max(); */
							
							$.ligerDialog.confirm("亲，确定要打印合格证吗？", function(yes) {
								if (yes) {
									var w = 400 ;
									var h = 200
									top.$.dialog({ 
										width : w, 
										height :h, 
										lock : true, 
										title:"打印标签",
										content: 'url:app/query/report_sign_pirnt.jsp',
										data : {
											"pwindow" : window,
											"id":resp.info_id,
											"device_ids":resp.device_ids,
											"report_types":resp.report_types,
											"bigClasss":resp.bigClasss
										}
									});
								}
							});
						}else{
							top.$.dialog.notice({
					             content: "操作成功！"
							});
						}
					}else{
						$.ligerDialog.error("操作失败，未找到系统相关业务流程，请联系系统管理员！");
					}
			})
		}
	}

	function del(){
		var statusArr = Qm.getValuesByName("data_status");	// 数据状态
		for(var i=0;i<statusArr.length;i++){
			if(statusArr[i] == '已签收'){
				$.ligerDialog.error("亲，您所选的数据中，包含已签收的数据，不能删除哦，请重新选择！");
				return;
			}
		}
		$.del("亲，确定删除所选报告交接业务信息吗？删除后系统无法恢复数据哦！",
	    	"report/transfer/del.do",
	    		{"ids":Qm.getValuesByName("id").toString()},function(result){alert('删除成功！')});
	}
	
	function refreshGrid() {
		Qm.refreshGrid();
	}
</script>
	</head>
	<body>
		<qm:qm pageid="report_transfer_list" script="false" singleSelect="false">
				<qm:param name="data_status" compare="in" value="('1','2')" dataType="user"/>	
		</qm:qm>
		<script type="text/javascript">
		Qm.config.columnsInfo.org_id.binddata=<u:dict sql="select id code, ORG_NAME text from SYS_ORG where ORG_CODE like 'jd%' or ( ORG_CODE like 'cy%' and ORG_CODE != 'cy4_1' and ORG_CODE != 'cy8') order by orders "></u:dict>;
		</script>
	</body>
</html>
