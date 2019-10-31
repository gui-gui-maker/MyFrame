<%@ page contentType="text/html;charset=UTF-8"%>
	<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>制造监督检验报告提交记录</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
	var	bar = [
		{ text:'流转过程', id:'flow_note',icon:'follow', click: getFlow}, "-",
	  	{ text:'撤回', id:'back',icon:'return', click: back}
	];
	
	var qmUserConfig = {
		sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:100},	// 默认值，可自定义
		sp_fields:[
			{name:"sn", compare:"="},
			{name:"made_unit_name", compare:"like"},
			{name:"device_code", compare:"like"}
			
	    ],
		tbar:bar,
        listeners: {
			selectionChange :function(rowData,rowIndex){
     			var count = Qm.getSelectedCount();//行选择个数
     			Qm.setTbar({back:count>0, flow_note : count==1});
			}
		}
	};
	
	//退回录入修改
	function back(){
		$.ligerDialog.confirm('确定撤回所选报告？', function (yes) { 
			if(yes){
				$.getJSON('inspection/zzjd/revokeReport.do?infoId='+Qm.getValuesByName("id")+'&flow_num='+Qm.getValueByName("flow_note_id")+'&acc_id='+Qm.getValuesByName("activity_id"),function(data){
					
					if(data.success){
						top.$.notice("撤销成功！");
						refreshGrid();
						api.close();
						api.data.window.refreshGrid();
					}
				
				});
			}
		});
	}
	
	//流转过程
	function  getFlow(){	
		 top.$.dialog({
	   		width : 400, 
	   		height : 700, 
	   		lock : true, 
	   		title: "流程卡",
	   		content: 'url:inspection/zzjd/getFlowStep.do?ins_info_id='+Qm.getValuesByName("id"),
	   		data : {"window" : window}
	   	});
	}
	
	function refreshGrid() {
   		Qm.refreshGrid();
	}
</script>
</head>
<body>

<%
CurrentSessionUser user = SecurityUtil.getSecurityUser();
User uu = (User)user.getSysUser();
com.khnt.rbac.impl.bean.Employee e = (com.khnt.rbac.impl.bean.Employee)uu.getEmployee();
e.getId();
String userId=e.getId();
String uId = SecurityUtil.getSecurityUser().getId();
%>
	<qm:qm pageid="report_zzjd_list" script="false" singleSelect="false" >
		<qm:param name="flow_note_name" value="报告审核" compare="=" />
		<qm:param name="enter_op_id" value="<%=userId%>" compare="=" />
	</qm:qm>
</body>
</html>
