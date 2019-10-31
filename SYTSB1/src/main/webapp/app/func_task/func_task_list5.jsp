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
		{ text:'详情', id:'detail',icon:'detail', click: detail},"-",   
		{ text:'流转过程', id:'turnHistory',icon:'follow', click: turnHistory},"-",  
		{ text:'历史台账记录', id:'history',icon:'detail', click: history}
 	]
 	var qmUserConfig = {
		sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	// 默认值，可自定义
		sp_fields:[
			{name:"sn", id:"sn", compare:"like"},
			{name:"task_name", id:"task_name", compare:"like"},
			{name:"task_type", id:"task_type", compare:"like"},
			{name:"task_category", id:"task_category", compare:"like"},
			{name:"advance_org_id",compare:"="},
			{name:"advance_user_name", id:"advance_user_name", compare:"like"}
	    ],
		tbar:bar,
	   	listeners: {
	    	selectionChange : function(rowData,rowIndex){
	       		var count=Qm.getSelectedCount();//行选择个数
	         	Qm.setTbar({detail:count==1,turnHistory:count==1,history:count==1});
	     	},
			rowDblClick : function(rowData, rowIndex) {
				Qm.getQmgrid().selectRange("id", [rowData.id]);
				detail();
			},
	    	/*afterQmReady:function(){
	      		Qm.searchData();
	   		},*/
			rowAttrRender : function(rowData, rowid) {
	            var fontColor="black";
	            // 0：任务下达
	            if (rowData.status == '0'){
	            	fontColor="maroon";
	            }
	         	// 1：任务已签发
	            if (rowData.status == '1'){
	            	fontColor="blue";
	            }
	            // 2：任务已接收
	            if (rowData.status == '2'){
	            	fontColor="purple";
	            }
	        	// 4：任务开发完成
	            if (rowData.status == '4'){
	            	fontColor="orange";
	            }
	         	// 5：任务测试中
	            if (rowData.status == '5'){
	            	fontColor="grey";
	            }
	         	// 6：任务测试完成
	            if (rowData.status == '6'){
	            	fontColor="pink";
	            }
	         	// 7：任务确认完成
	            if (rowData.status == '7'){
	            	fontColor="green";
	            }
	         	// 8：未提交
	            if (rowData.status == '8'){
	            	fontColor="black";
	            }
	         	// 99：已作废
	            if (rowData.status == '99'){
	            	fontColor="red";
	            }
	            return "style='color:"+fontColor+"'";
			}
		}
	};
	
	// 详情
	function detail(id){
		if($.type(id)!="string"){
			id = Qm.getValueByName("id").toString();
		}		
		top.$.dialog({
			width : 800, 
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
	
	//历史台账记录
	function history() {
		top.$.dialog({
			width : 900, 
			height : 600, 
			lock : true, 
			title : "历史台账记录", 
			data : {"window" : window},
			content : 'url:app/func_task/func_task_history.jsp?id='+Qm.getValueByName("id").toString()
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
					<font color="black">“黑色”</font>代表任务未提交；
					<font color="maroon">“褐红色”</font>代表任务下达；
					<font color="blue">“蓝色”</font>代表任务已签发；
					<font color="purple">“紫色”</font>代表任务已接收；
					<font color="orange">“橘色”</font>代表任务开发完成；
					<font color="grey">“灰色”</font>代表任务测试中；
					<font color="pink">“粉色”</font>代表任务测试完成；
					<font color="green">“绿色”</font>代表任务确认完成；
					<font color="red">“红色”</font>代表任务已作废。
				</div>
			</div>
		</div>
		<qm:qm pageid="func_task_list" script="false" singleSelect="false">
		</qm:qm>
		<script type="text/javascript">
			Qm.config.columnsInfo.advance_org_id.binddata=<u:dict sql="select t.id code, t.ORG_NAME text from SYS_ORG t where t.parent_id='100000' and t.property='dep' and t.status='used' order by orders "></u:dict>;
		</script>
	</body>
</html>
