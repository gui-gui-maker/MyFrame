<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<%
CurrentSessionUser user = SecurityUtil.getSecurityUser();
User uu = (User)user.getSysUser();
com.khnt.rbac.impl.bean.Employee e = (com.khnt.rbac.impl.bean.Employee)uu.getEmployee();
e.getId();
String userId=e.getId();
String uId = SecurityUtil.getSecurityUser().getId();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>提交记录</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">
var userId = "<sec:authentication property='principal.id' htmlEscape='false' />"



	var	bar =[{text : '详情',id : 'detail',icon : 'detail ',click : detail}, "-",{ text:'流转过程', id:'flow_note',icon:'follow', click: getFlow},
	  		{text : '设备情况',id : 'device',icon : 'detail',click : device}, "-",{ text:'撤销', id:'back',icon:'return', click: back}];
	





	var qmUserConfig = {
		sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:100},	// 默认值，可自定义
		sp_fields:[
			{name:"sn",compare:"="},
			{name:"device_registration_code",compare:"like"},
			{name:"com_name",compare:"like"}
	    ],
		tbar:bar,
        listeners: {
			selectionChange :function(rowData,rowIndex){
     			var count = Qm.getSelectedCount();//行选择个数
     			Qm.setTbar({back:count>0,getinput : count==1,flow_note : count==1,returnIput: count==1,print: count==1,showReport : count==1,history : count==1,input : count == 1,detail : count == 1,device : count == 1, check : count == 1,copy : count > 0});
			}
			
			
			
			
			
		}
	};

	
	function detail() {

		top.$.dialog({
			width : 800,
			height : 500,
			lock : true,
			title : "业务详情",
			content : 'url:app/flow/info_detail.jsp?status=detail&id='+ Qm.getValueByName("id"),
			data : {
				"window" : window
			}
		});

	}
	
//退回录入修改
	function back(){
	
		$.ligerDialog.confirm('确定撤销所选数据?', function (yes) { 
	
			
			if(yes){
				$.getJSON('department/basic/backModify.do?infoId='+Qm.getValuesByName("id")+'&flow_num='+Qm.getValueByName("flow_note_id")+'&acc_id='+Qm.getValuesByName("activity_id"),function(data){
					
					if(data.success){
						top.$.notice("撤销成功！");
						submitAction();
						api.close();
						api.data.window.submitAction();
					}
				
				});
			}
		});
	}
	
	

	
	

	
	
	
	
	

	function device() {
		
	
		//查询设备ID和设备种类
		$.getJSON('department/basic/getDeviceType.do',{infoId:Qm.getValueByName("id")},function(data){
			
			if(data.success){
				
				top.$.dialog({
					width : 1000,
					height : 600,
					lock : true,
					title : "设备信息",
					content : 'url:app/device/device_detail.jsp?status=detail&id='+data.device_id+'&device_type='+data.device_type,
					data : {
						"window" : window
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
	   			content: 'url:department/basic/getFlowStep.do?ins_info_id='+Qm.getValuesByName("id"),
	   			data : {"window" : window}
	   		});
	
		
	}
	
	

	function submitAction(o) {
   		Qm.refreshGrid();
	}
</script>
</head>
<body>
	<qm:qm pageid="report_enter" script="false" singleSelect="false" >
		<qm:param name="flow_note_name" value="报告审核" compare="=" />
		<qm:param name="enter_op_id" value="<%=userId%>" compare="=" />
	</qm:qm>
</body>
</html>
