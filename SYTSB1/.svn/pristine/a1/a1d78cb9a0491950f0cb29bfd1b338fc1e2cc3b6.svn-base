<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.utils.StringUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>加班申请审核</title>
<%@include file="/k/kui-base-list.jsp"%>
<%
	CurrentSessionUser user = SecurityUtil.getSecurityUser();
%>
<link type="text/css" rel="stylesheet"
	href="app/qualitymanage/css/form_detail.css" />
<script type="text/javascript">
       var qmUserConfig = {
       	sp_defaults:{columnWidth:0.33,labelAlign:'right',labelSeparator:'',labelWidth:100},	// 默认值，可自定义
       	sp_fields:[
					{name:"other_applicants", id:"other_applicants", compare:"like"},
					{name:"department", id:"department", compare:"like"},
					{name:"overtime_type", id:"overtime_type", compare:"like"}
		        ],
			        
           tbar:[
            { text:'详情', id:'detail',icon:'detail', click: detail},"-",
           	{ text:'审核', id:'submit',icon:'submit', click: submit}
           	
           ],
           listeners: {
               selectionChange : function(rowData,rowIndex){
               	selectionChange();
               },
       		rowDblClick :function(rowData,rowIndex){
       			detail(rowData.id);
       		},
			rowAttrRender : function(rowData, rowid) {
				var step = rowData.flowStep;
		       		var fontColor="black";
		       		/* if (step!="0"){
		       			fontColor="green"; */
	       		return "style='color:"+fontColor+"'";
			//} 
           }
       }
       }  
       // 行选择改变事件
	function selectionChange() {
   		var count = Qm.getSelectedCount(); // 行选择个数
     	if(count == 1){
            Qm.setTbar({detail: true,  modify: true, print: true ,del: true,planDoc:true,submit:true});            	
 		}else if(count > 1){
       		Qm.setTbar({detail: false, modify: false,del: true,planDoc:false,submit:false});
    	}else{
    		Qm.setTbar({detail: false,  modify: false,del: false,planDoc:false,submit:false});
    	}
	}
	
	function detail(){
		top.$.dialog({
			width : 800, 
			height : 600, 
			title:"详情",
			content: 'url:app/humanresources/employee_overtime_allowance_detail.jsp?status=detail&id='+Qm.getValueByName("id"),
			data : {"window" : window}
		});
	}

	function submitAction() {
		Qm.refreshGrid();
	}
       
	function closewindow(){
		api.close();
	}
	
/*	function submit(){
		var flow_step = Qm.getValueByName("flowStep");
		
		var depart_id = Qm.getValueByName("department_id");
		var nextStep = (flow_step-0)+1;
		
		var role_flag = Qm.getValueByName("role_flag");
		if("leader_manager"==role_flag) {
			step = "3";
			nextStep = "4";
		}
		top.$.dialog({
			width : 800,
			height : 300,
			lock : true,
			title : "提交",
			content : 'url:app/humanresources/em_allowance_step.jsp?status=add&id='
					+ Qm.getValueByName("id")+"&step="+flow_step+"&nextStep="+nextStep+"&dept_id="+depart_id,
			data : {
				"window" : window
			},
			close:function(){
				submitAction();
			}
		});
	}
*/

function submit(){
	var flow_step = Qm.getValueByName("flowStep");
	var depart_id = Qm.getValueByName("department_id");
	var applicantsId=Qm.getValueByName("applicants_id");
	var nextStep = (flow_step-0)+1;
	var role_flag = Qm.getValueByName("role_flag");
	/* if("leader_manager"==role_flag) {
		if("3" == flow_step) {
			flow_step = "4";
			nextStep = "5";
		} else {
			flow_step = "3";
			nextStep = "3";
		}
	} */
	top.$.dialog({
		width : 800,
		height : 300,
		lock : true,
		title : "审核",
		content : 'url:app/humanresources/em_allowance_step.jsp?status=add&id='
				+ Qm.getValueByName("id")+"&step="+flow_step+"&nextStep="+nextStep+"&dept_id="+depart_id,
		data : {
			"window" : window
		},
		close:function(){
			submitAction();
		}
	});
}	
	
</script>
</head>
<body>	
	<!-- <div class="item-tm" id="titleElement">
	    <div class="l-page-title">
			<div class="l-page-title-note">提示：列表数据项
				<font color="green">“绿色”</font>代表已提交审核的申请表。
			</div>
		</div>
	</div>  -->
	<qm:qm pageid="overtime_allowance" singleSelect="false" >
		<qm:param name="flowStep" value="('1','2','3','4')" compare="in" dataType="user"/>
		<qm:param name="handle_id" value="<%=user.getId() %>" compare="=" />
	</qm:qm>
</body>
</html>
