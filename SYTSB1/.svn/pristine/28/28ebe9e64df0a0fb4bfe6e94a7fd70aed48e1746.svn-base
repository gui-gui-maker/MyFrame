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
		<title>加班申请</title>
		<%@include file="/k/kui-base-list.jsp"%>
<%
	CurrentSessionUser user = SecurityUtil.getSecurityUser();
	String userId = user.getId();
%>
		<script type="text/javascript">
	var bar =[
		{ text:'详情', id:'detail',icon:'detail', click: detail},"-",   
 		{ text:'新增', id:'add',icon:'add', click: add},"-", 
		{ text:'修改', id:'modify',icon:'modify', click: modify},"-",
		{ text:'提交', id:'submit',icon:'submit', click: submit},"-", 
		{ text:'删除', id:'del',icon:'delete', click: del},"-",
		{ text:'打印申请表', id:'printOverTime',icon:'print', click: printOverTime}
 	]
 	var qmUserConfig = {
			sp_defaults:{columnWidth:0.33,labelAlign:'right',labelSeparator:'',labelWidth:100},	// 默认值，可自定义
	       	sp_fields:[
				{name:"other_applicants", id:"other_applicants", compare:"like"},
				{name:"department", id:"department", compare:"like"},
				{name:"overtime_type", id:"overtime_type", compare:"like"}
	        ],
				        
		tbar:bar,
	   	listeners: {
	    	selectionChange : function(rowData,rowIndex){
	       		var count=Qm.getSelectedCount();//行选择个数
	         	Qm.setTbar({modify:count==1,detail:count==1,turnHistory:count==1,commits:count>0,del:count>0,submit:count==1,printOverTime:count==1});
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
	            if (rowData.flowStep == '1'){
	            	fontColor="maroon";
	            }
	            if (rowData.flowStep == '2'){
	            	fontColor="blue";
	            }
	            if (rowData.flowStep == '3'){
	            	fontColor="orange";
	            }
	            if (rowData.flowStep == '4'){
	            	fontColor="pink";
	            }
	            if (rowData.flowStep == '5'){
	            	fontColor="purple";
	            }
	            if (rowData.flowStep == '6'){
	            	fontColor="red";
	            }
	            return "style='color:"+fontColor+"'";
			}
		}
	};
	
	
    // 行选择改变事件
	function selectionChange() {
   		var count = Qm.getSelectedCount(); // 行选择个数
         Qm.setTbar({
        	 detail: count==1,
        	 submit: count>0,
        	 add: true, 
        	 modify: count==1,
        	 del: count>0,
        	 submit:count==1,
        	 printOverTime:count==1
         });            	
	}
	
	// 详情
	function detail(id){
		if($.type(id)!="string"){
			id = Qm.getValueByName("id").toString();
		}		
		top.$.dialog({
			width : 800, 
			height : 600, 
			lock : true,
			title : "申请详情",
			content : 'url:app/humanresources/request_for_overtime/request_for_overtime_detail.jsp?status=detail&id='+ id,
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
			title : "新增加班申请", 
			data : {"window" : window},
			content : 'url:app/humanresources/request_for_overtime/request_for_overtime_detail.jsp?status=add'
		});
	}

	function modify(){
		var enter_op_id = Qm.getValueByName("applicants_id");
		if(enter_op_id!="<%=userId%>"){
			$.ligerDialog.warn('提示：亲，你只能修改自己填写的申请！');
			return;
		}
		var step = Qm.getValueByName("flowStep");	// 数据状态
		if(step == '0'){
			top.$.dialog({
				width : 1000, 
				height : 600, 
				lock : true, 
				title:"修改申请信息",
				content: 'url:app/humanresources/request_for_overtime/request_for_overtime_detail.jsp?status=modify&id='+Qm.getValueByName("id"),
				data : {"window" : window}
			});
		}else{
			$.ligerDialog.error("亲，您所选的数据中，包含已提交的数据，不能修改哦，请重新选择！");
			return;
		}
	}
	
	// 打印申请表
	function printOverTime(){
		var role_flag = Qm.getValueByName("role_flag");
		var role_status = Qm.getValueByName("role_status");
		var url = "app/humanresources/request_for_overtime/overtime_print.jsp";

		if(0==role_status){
			url =  "app/humanresources/request_for_overtime/overtime_print_0.jsp"
		}
		top.$.dialog({
			width : 1000, 
			height : 800, 
			lock : true, 
			title:"打印加班申请表",
			content: 'url:'+url+'?status=detail&id='+Qm.getValueByName("id")
					+"&role_flag="+role_flag,
			data : {"window" : window}
		});
	}
	
	//提交审核
	function submit(){
		var flow_step = Qm.getValueByName("flowStep");
		var subStatus = Qm.getValueByName("sub_status");
		var ids = Qm.getValueByName("id");
		var dept_id = Qm.getValueByName("department_id");
		var count1 = Qm.getSelectedCount(); 
		var applicantsId=Qm.getValueByName("applicants_id")
		var startFlow = Qm.getValueByName("start_flow");
		var enter_op_id = Qm.getValueByName("applicants_id");
		if(enter_op_id!="<%=userId%>"){
			$.ligerDialog.warn('提示：亲，你只能提交自己填写的申请！');
			return;
		}
		if("5" == flow_step) {
			$.ligerDialog.warn('提示：亲，已通过审核啦！');
			return;
		}
		if("1"==subStatus){
			$.ligerDialog.warn('提示：正在审核中的数据不能再次提交！');
			return;
		}
		var role_flag = Qm.getValueByName("role_flag");
		if("dean_manager" == role_flag) {
			$.ligerDialog.warn('院长不需要提交审核');
			return;
		}
		/* if("depart_manager" == role_flag) {
			flow_step = "1";
		}
		else if("narmal_person" == role_flag) {
			flow_step = "0";
		}
		else if("personnel_manager" == role_flag) {
			flow_step = "3";
			nextStep = "4";
		}
		else if("leader_manager" == role_flag) {
			flow_step = "1";
			nextStep = "2";
		} */
					var nextStep = (flow_step-0)+1;
					top.$.dialog({
						width : 800,
						height : 300,
						lock : true,
						title : "提交审核",
						content : 'url:app/humanresources/request_for_overtime/overtime_audit.jsp?status=add&id='
							+ Qm.getValueByName("id")+"&step="+flow_step+"&nextStep="+nextStep+"&dept_id="+dept_id+"&startFlow="+startFlow,
						data : {
							"window" : window
						},
						close:function(){
							refreshGrid();
						}
					});
	}
				
	function del(){
		var enter_op_id = Qm.getValuesByName("applicants_id");
		for (var i = 0; i < enter_op_id.length; i++) {
			if(enter_op_id[i]!="<%=userId%>"){
				$.ligerDialog.warn('提示：亲，你只能删除自己填写的申请！');
				return;
			}
		}
		var subStatus = Qm.getValueByName("sub_status");
		if(subStatus == '1') {
			$.ligerDialog.warn('删除失败：所选数据中包含已提交的数据','提示');
			return;
		} else {
		$.ligerDialog.confirm("是否确定删除所选文件？", function (yes) {
			if(yes){
				var ids = Qm.getValuesByName("id").toString();
				var url = "requestForOvertimeAction/deleteOvertime.do";
				$("body").mask("正在处理数据，请稍后！");
				$.getJSON(url,{ids:ids},function(res){
					$("body").unmask();
	                if (res["success"]) {
	                	refreshGrid();
	                    top.$.dialog.notice({content:'删除成功'});
	                } else {
	                	$.ligerDialog.error('提示：删除失败');
	                }
				})
			}
		});
	}
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
					<font color="maroon">“褐红色”</font>代表部门审核流程；
					<font color="blue">“蓝色”</font>代表人事审核流程；
					<font color="orange">“橘色”</font>代表院领导审核流程；
					<font color="pink">“粉色”</font>代表院长审核流程；
					<font color="purple">“紫色”</font>代表通过；
					<font color="red">“红色”</font>代表不通过。
				</div>
			</div>
		</div>
		<qm:qm pageid="request_for_overtime" singleSelect="false"><!-- script="false"  -->
			<qm:param name="data_status" value="1" compare="=" />
		<sec:authorize access="!hasRole('hr_draw')">
			<qm:param name="applicants_id" value="<%=user.getId() %>" compare="=" />
		</sec:authorize>
		</qm:qm>
	</body>
</html>
