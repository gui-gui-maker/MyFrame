<%@page import="java.util.Map"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.utils.StringUtil"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>检验和质量管理软件功能开发（修改）任务书列表</title>
		<%@include file="/k/kui-base-list.jsp"%>
		<script type="text/javascript">
	var bar =[	
		{ text:'选择', id:'choose',icon:'modify', click: choose},"-", 
		{ text:'详情', id:'detail',icon:'detail', click: detail},"-",
		{ text:'流转过程', id:'turnHistory',icon:'follow', click: turnHistory} 
 	]
 	var qmUserConfig = {
		sp_defaults:{columnWidth:0.25,labelAlign:'right',labelSeparator:'',labelWidth:100},	// 默认值，可自定义
		sp_fields:[
			{name:"sn", id:"sn", compare:"like"},
			{name:"task_name", id:"task_name", compare:"like"}
	    ],
		tbar:bar,
	   	listeners: {
	    	selectionChange : function(rowData,rowIndex){
	       		var count=Qm.getSelectedCount();//行选择个数
	         	Qm.setTbar({choose:count==1,detail:count==1,turnHistory:count==1});
	     	},
			rowDblClick : function(rowData, rowIndex) {
				Qm.getQmgrid().selectRange("id", [rowData.id]);
				detail();
			}/*,
	    	afterQmReady:function(){
	      		Qm.searchData();
	   		}*/
		}
	};
	
	function choose(){
		api.data.parentWindow.callBackTask(Qm.getValueByName("id"),Qm.getValueByName("task_name"),Qm.getValueByName("sn"));
		api.close();
	}
	
	// 详情
	function detail(id){
		if($.type(id)!="string"){
			id = Qm.getValueByName("id").toString();
		}		
		top.$.dialog({
			width : 1000, 
			height : 600, 
			lock : true,
			title : "任务书详情",
			content : 'url:app/func_task/func_task_detail.jsp?status=detail&id='+ id,
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
	   		content: 'url:functionTaskInfo/getFlowStep.do?id='+Qm.getValueByName("id").toString(),
	   		data : {"window" : window}
	   	});
	}
	
	function refreshGrid() {
		Qm.refreshGrid();
	}
</script>
	</head>
	<body>
		<qm:qm pageid="func_task_list4" singleSelect="true"><!--  script="false" -->
		</qm:qm>
		<script type="text/javascript">
			Qm.config.columnsInfo.advance_org_id.binddata=<u:dict sql="select t.id code, t.ORG_NAME text from SYS_ORG t where t.parent_id='100000' and t.property='dep' and t.status='used' order by orders "></u:dict>;
		</script>
	</body>
</html>
