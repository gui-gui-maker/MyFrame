<%@page import="com.khnt.utils.StringUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title>机构查询列表</title>
<%@include file="/k/kui-base-list.jsp"%>
<%
	CurrentSessionUser user = SecurityUtil.getSecurityUser();
	String userId = user.getId();
%>
<script type="text/javascript">
	var qmUserConfig = {
			sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},		
			sp_fields:[
					{name:"other_applicants", id:"other_applicants", compare:"like"},
					{name:"department", id:"department", compare:"like"},
					{name:"overtime_type", id:"overtime_type", compare:"like"}
        ],
        tbar:[
            { text:'详情', id:'detail',icon:'detail', click: detail},
            "-",
	        { text:'新增', id:'add',icon:'add',click:add},
	        "-",
			{ text:'修改', id:'modify',icon:'modify', click: modify},
			"-",
			{ text:'提交', id:'submit',icon:'submit', click: submit},
			"-",
			{ text:'删除', id:'del',icon:'delete', click: del}, "-",
			{ text:'加班意见详情', id:'overTime',icon:'detail', click: overTime} ,"-",
			{ text:'打印补助通知单', id:'printOverTime',icon:'print', click: printOverTime} 
        ],
        listeners: {
            selectionChange : function(rowData,rowIndex){
            	var count=Qm.getSelectedCount();//行选择个数
                Qm.setTbar({select:count==1,modify:count==1,detail:count==1,del:count>0,submit:count==1,printPlan:count==1,overTime:count==1,printOverTime:count==1});
            },
	
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
	
	
	function add(){
		top.$.dialog({
			width : 1024, 
			height : 620, 
			lock : true, 
			title:"新增",
			parent:api,
			content: 'url:app/humanresources/employee_overtime_allowance_detail.jsp?status=add',
			data : {"window" : window}
		});
	}
	
	function close(){
		api.close();
	}
	function modify(){
		var enter_op_id = Qm.getValueByName("enter_op_id");
		if(enter_op_id!="<%=userId%>"){
			$.ligerDialog.warn('提示：亲，你只能修改自己填写的申请！');
			return;
		}
		var step = Qm.getValueByName("flowStep");	// 数据状态
		if(step == '0'){
			top.$.dialog({
				width : 800, 
				height : 700, 
				lock : true, 
				title:"修改信息",
				content: 'url:app/humanresources/employee_overtime_allowance_detail.jsp?status=modify&id='+Qm.getValueByName("id"),
				data : {"window" : window}
			});
		}else{
			$.ligerDialog.error("亲，您所选的数据中，包含已提交的数据，不能修改哦，请重新选择！");
			return;
		}
	}
	
	function del(){
		var enter_op_id = Qm.getValuesByName("enter_op_id");
		for (var i = 0; i < enter_op_id.length; i++) {
			if(enter_op_id[i]!="<%=userId%>"){
				$.ligerDialog.warn('提示：亲，你只能删除自己填写的申请！');
				return;
			}
		}var subStatus = Qm.getValueByName("sub_status");
		if(subStatus == '1') {
			$.ligerDialog.warn('删除失败：所选数据中包含已提交的数据','提示');
			return;
		} else {
		
		  $.del("确定删除?",
				  "employeeallowanceAction/delete.do",
		    		{"ids":Qm.getValuesByName("id").toString()});
		}
    }
	
	function detail(){
		top.$.dialog({
			width : 800, 
			height : 700, 
			lock : true, 
			title:"详情",
			content: 'url:app/humanresources/employee_overtime_allowance_detail.jsp?status=detail&id='+Qm.getValueByName("id"),
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
		var enter_op_id = Qm.getValueByName("enter_op_id");
		if(enter_op_id!="<%=userId%>"){
			$.ligerDialog.warn('提示：亲，你只能提交自己填写的申请！');
			return;
		}
		if("5" == flow_step) {
			$.ligerDialog.warn('提示：亲，已通过审核啦！');
			return;
		}
		if(count1 == 0) {
			$.ligerDialog.warn('提示：没有选中数据！');
			return;
		}
		if("1"==subStatus){
			$.ligerDialog.warn('提示：已经提交过了，不能再次提交！');
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
			/* $.post("requestForOvertimeAction/subAuditOp.do", {
				"applicantsId":encodeURI(applicantsId),"flow_step":flow_step,"ids":ids},function(res){
					
					var dept_Type = res.name;
					var flow_step = res.flow_step;
					
					if("人事查看" == dept_Type) {
						nextStep = "3";
					}  */
					var nextStep = (flow_step-0)+1;
					top.$.dialog({
						width : 800,
						height : 300,
						lock : true,
						title : "提交审核",
						content : 'url:app/humanresources/em_allowance_step.jsp?status=add&id='
							+ Qm.getValueByName("id")+"&step="+flow_step+"&nextStep="+nextStep+"&dept_id="+dept_id+"&startFlow="+startFlow,
						data : {
							"window" : window
						},
						close:function(){
							refreshGrid();
						}
					});
	}
	
	
	
	
	
	//列表刷新
//	function submitAction(o) {
//	    Qm.refreshGrid();
//	}
	
	function refreshGrid() {
		Qm.refreshGrid();
	}
	
	function overTime(){
		top.$.dialog({
			width : 1000, 
			height : 800, 
			lock : true, 
			title:"加班人意见",
			content: 'url:app/humanresources/overtime_allowance/allowance_op_list.jsp?id='+Qm.getValueByName("id"),
			data : {"window" : window,startDate:Qm.getValueByName("startdate"),endDate:Qm.getValueByName("enddate")}
		});
	}
	
	// 打印申请表
	function printOverTime(){
		var role_flag = Qm.getValueByName("role_flag");
		var role_status = Qm.getValueByName("role_status");
		var url = "app/humanresources/overtime_allowance/overtime_allowance_print_1.jsp";

		if(0==role_status){
			url =  "app/humanresources/overtime_allowance/overtime_allowance_print_0.jsp"
		}
		top.$.dialog({
			width : 1000, 
			height : 800, 
			lock : true, 
			title:"打印加班补助通知单",
			content: 'url:'+url+'?status=detail&id='+Qm.getValueByName("id")
					+"&role_flag="+role_flag,
			data : {"window" : window,startDate:Qm.getValueByName("startdate"),endDate:Qm.getValueByName("enddate")}
		});
	}
	
	</script>
</head>
<body>
<div class="item-tm" id="titleElement">
		<div class="l-page-title">
			<div class="l-page-title-note">提示：状态
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
	<qm:qm pageid="overtime_allowance" script="false" singleSelect="false"   >
	<qm:param name="data_status" value="1" compare="=" />
		<sec:authorize access="!hasRole('hr_draw')">
			<qm:param name="enter_op_id" value="<%=user.getId() %>" compare="=" />
		</sec:authorize>
	</qm:qm>
	
	
</body>
</html>