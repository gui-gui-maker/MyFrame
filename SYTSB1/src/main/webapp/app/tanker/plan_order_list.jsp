<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>设备信息列表</title>
<%@include file="/k/kui-base-list.jsp"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<%
CurrentSessionUser user = SecurityUtil.getSecurityUser();
User uu = (User)user.getSysUser();
com.khnt.rbac.impl.bean.Employee e = (com.khnt.rbac.impl.bean.Employee)uu.getEmployee();
%>
<%String empId=e.getId();%> 
<script type="text/javascript">
	
	var unitcode = 	<sec:authentication property='principal.department.id' htmlEscape='false' />
	var qmUserConfig = {
			sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},				
		sp_fields : [
		{
			name : "device_name",
			compare : "like",
			value : ""
		},{
			name : "com_name",
			compare : "like",
			value : ""
		}
		
		],
		tbar : [
		
		{
			text : '确认',
			id : 'modify',
			icon : 'modify',
			click : confirm
		},"-",
		
		{
			text : '回退',
			id : 'back',
			icon : 'back',
			click : back
		}

	
		],
	
	
		listeners : {
			selectionChange : function(rowData, rowIndex) {
				var count = Qm.getSelectedCount();//行选择个数
				Qm.setTbar({
					add : count == 1,
					back : count == 1,
					copy : count == 1,
					editcom : count>0,
					back : count>0,
					modify : count > 0,
					enable:count>0,
					disable:count>0,
					useless:count>0,
					modifyDevice:count>0
				});
			}
		}
	};

	function confirm() {
	
		var infoId=Qm.getValuesByName("infoId"); 
		var reportId=Qm.getValuesByName("report_id"); 
		var deviceId=Qm.getValuesByName("id"); 
		$("body").mask("正在提交数据，请稍后！");
		$.getJSON('inspectionInfo/basic/subFlow.do?infoId='+infoId,function(data){
			$("body").unmask();
			if(data){
				top.$.notice("任务领取成功！");
				//判断是否选择一份报告，是提示进入报告录入环节
				//if(infoId.indexOf(",")==-1){
					
				//	$.ligerDialog.confirm('是否开始录入报告？', function (yes) { 
					//	if(yes){
					//		var flow_num = data.flow_num;
							
					//		openReportI(infoId,reportId,flow_num,deviceId);
											
						
					//	}else{
							
							
						//	submitAction();
							
							
							
					//	}
				//	});
					
				//}else{
					
					submitAction();
						
			//	}
				
				
			}else{
				top.$.notice("任务领取失败！"+data.message);
				
			}
						
		});
	
		
		
		
		
		}
	function back() {

		var ywid=Qm.getValuesByName("infoId"); 
		$.ligerDialog.confirm('确定要回退？', function (yes) { 
			if(yes){
				$.getJSON('inspectionInfo/basic/backPlan.do?infoId='+ywid,function(data){
					if(data){
						top.$.notice("退回成功！");
						submitAction();
					}
								
				});
			}
		});
			
		}



	function submitAction() {
		Qm.refreshGrid();
	}
</script>
</head>
<body>
	<qm:qm pageid="plan_order_tanker" script="false" singleSelect="false">
	
	<qm:param name="item_op_id" value="<%=empId%>" compare="=" />
	</qm:qm>
	
</body>
</html>
