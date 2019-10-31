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
 		{ text:'新增', id:'add',icon:'add', click: add},"-", 	  
		{ text:'修改', id:'modify',icon:'modify', click: modify},"-",
		{ text:'提交', id:'commits',icon:'submit', click: commits},"-", 
		{ text:'一测情况', id:'test1',icon:'modify', click: test1},"-", 
		{ text:'二测情况', id:'test2',icon:'modify', click: test2},"-", 
		{ text:'三测情况', id:'test3',icon:'modify', click: test3},"-", 
		{ text:'流转过程', id:'turnHistory',icon:'follow', click: turnHistory},"-",   
		{ text:'删除', id:'del',icon:'delete', click: del}
 	]
 	var qmUserConfig = {
		sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	// 默认值，可自定义
		sp_fields:[
			{ name:"sn", id:"sn", compare:"like"},
			{name:"task_name", id:"task_name", compare:"like"},
			{ name:"task_type", id:"task_type", compare:"like"},
			{name:"task_category", id:"task_category", compare:"like"},
			{name:"advance_org_id",compare:"="},
			{name:"advance_user_name", id:"advance_user_name", compare:"like"}
	    ],
		tbar:bar,
	   	listeners: {
	    	selectionChange : function(rowData,rowIndex){
	       		var count=Qm.getSelectedCount();//行选择个数
	         	Qm.setTbar({modify:count==1,detail:count==1,turnHistory:count==1,commits:count>0,del:count>0,test1:count>0,test2:count>0,test3:count>0});
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
	         	// 9：任务退回
	            if (rowData.status == '9'){
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
	
	function add(){	
		top.$.dialog({
			width : 800, 
			height : 600, 
			lock : true, 
			title : "新增任务书", 
			data : {"window" : window},
			content : 'url:app/func_task/func_task_detail.jsp?status=add'
		});
	}

	function modify(){
		var statusArr = Qm.getValueByName("data_status");	// 数据状态
		if(statusArr == '未提交' || statusArr == '任务退回'){
			top.$.dialog({
				width : 1000, 
				height : 600, 
				lock : true, 
				title:"修改任务书",
				content: 'url:app/func_task/func_task_detail.jsp?status=modify&id='+Qm.getValueByName("id"),
				data : {"window" : window}
			});
		}else{
			$.ligerDialog.error("亲，您所选的数据中，包含已提交的数据，不能修改哦，请重新选择！");
			return;
		}
	}
	
	function commits(){
		var statusArr = Qm.getValuesByName("data_status");	// 数据状态
		for(var i=0;i<statusArr.length;i++){
			if(statusArr[i] != '未提交' && statusArr[i] != '任务退回'){
				$.ligerDialog.error("亲，您所选的数据中，包含已提交的数据，不能重复操作哦，请重新选择！");
				return;
			}
		}
		if(confirm("亲，确认提交所选任务书吗？提交后无法撤回数据哦！")){
				$.getJSON("functionTaskInfo/commits.do?ids="+Qm.getValuesByName("id").toString(), function(resp){
					if(resp.success){
						top.$.dialog.notice({
				             content: "提交成功！"
						});
						refreshGrid();
					}else{
						$.ligerDialog.error("签收失败，未找到系统相关业务流程，请联系系统管理员！");
					}
			})
		}
	}
	
	function del(){
		var statusArr = Qm.getValuesByName("data_status");	// 数据状态
		for(var i=0;i<statusArr.length;i++){
			if(statusArr[i] != '任务下达'){
				$.ligerDialog.error("亲，您所选的数据中，包含“"+statusArr[i]+"”的数据，不能删除哦，请重新选择！");
				return;
			}
		}
		$.del("亲，确定删除所选任务书吗？删除后系统无法恢复数据哦！",
	    	"functionTaskInfo/del.do",
	    		{"ids":Qm.getValuesByName("id").toString()},function(result){alert('删除成功！')});
	}
	
	// 一测情况
	function test1(){
		var test_user_name1Arr = Qm.getValuesByName("test_user_name1").toString();	// 一测用户姓名
		for(var i=0;i<test_user_name1Arr.length;i++){
			if(test_user_name1Arr[i] != '' || test_user_name1Arr[i] != null){
				$.ligerDialog.error("亲，您所选的数据中，包含已一测的数据，不能重复操作哦，请重新选择！");
				return;
			}
		}
		top.$.dialog({
			width : 750,
			height : 300,
			lock : true,
			title : "一测情况",
			content : 'url:app/func_task/func_task_test1.jsp?status=modify&ids='
						+ Qm.getValuesByName("id").toString(),
			data : {
				"window" : window
			}
		});	
	}
	
	// 二测情况
	function test2(){
		var test_user_name2Arr = Qm.getValuesByName("test_user_name2").toString();	// 二测用户姓名
		for(var i=0;i<test_user_name2Arr.length;i++){
			if(test_user_name2Arr[i] != '' || test_user_name2Arr[i] != null){
				$.ligerDialog.error("亲，您所选的数据中，包含已二测的数据，不能重复操作哦，请重新选择！");
				return;
			}
		}
		top.$.dialog({
			width : 750,
			height : 300,
			lock : true,
			title : "二测情况",
			content : 'url:app/func_task/func_task_test2.jsp?status=modify&ids='
						+ Qm.getValuesByName("id").toString(),
			data : {
				"window" : window
			}
		});	
	}
	
	// 三测情况
	function test3(){
		var test_user_name3Arr = Qm.getValuesByName("test_user_name3").toString();	// 三测用户姓名
		for(var i=0;i<test_user_name3Arr.length;i++){
			if(test_user_name3Arr[i] != '' || test_user_name3Arr[i] != null){
				$.ligerDialog.error("亲，您所选的数据中，包含已三测的数据，不能重复操作哦，请重新选择！");
				return;
			}
		}
		top.$.dialog({
			width : 750,
			height : 300,
			lock : true,
			title : "一测情况",
			content : 'url:app/func_task/func_task_test3.jsp?status=modify&ids='
						+ Qm.getValuesByName("id").toString(),
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
					<font color="red">“红色”</font>代表任务退回。
				</div>
			</div>
		</div>
		<qm:qm pageid="func_task_list" singleSelect="false"><!-- script="false"  -->
		</qm:qm>
		<script type="text/javascript">
			Qm.config.columnsInfo.advance_org_id.binddata=<u:dict sql="select t.id code, t.ORG_NAME text from SYS_ORG t where t.parent_id='100000' and t.property='dep' and t.status='used' order by orders "></u:dict>;
		</script>
	</body>
</html>
