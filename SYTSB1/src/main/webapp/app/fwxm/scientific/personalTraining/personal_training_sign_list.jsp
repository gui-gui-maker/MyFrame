<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.base.Factory"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%
	String userId = SecurityUtil.getSecurityUser().getId();
%>
<head>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u"%>
<%@include file="/k/kui-base-list.jsp"%>
<%
	Boolean isCheckError = false;
	/* if ("true".equals(Factory.getSysPara().getProperty(
			"REPORT_ERROR_CHECK")))
		isCheckError = true; */
%>
<!-- 需要导入的特种设备的公用js -->
<title>报告纠错列表</title>
<script type="text/javascript" src="app/fwxm/scientific/common/common.js"></script>
<script type="text/javascript">

	var userId = "<sec:authentication property='principal.id' htmlEscape='false' />"
	var employeeId = "<sec:authentication property='principal.sysUser.id' htmlEscape='false' />";
	var unitId="<sec:authentication property='principal.department.id'/>";
	var qmUserConfig = {
			sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	
			sp_fields:[						
				{name:"apply_org",compare:"like",value:""},
				{name:"apply_op",compare:"like",value:""},
				{group: [{name:"apply_date",compare:">=",value:""},
					{label:"到",name:"apply_date",compare:"<",value:"",labelWidth:20} ]
				 }],
		tbar:[
			{ text:'详情', id:'detail',icon:'detail', click: detail},"-",
			{ text:'流转过程', id:'flow',icon:'follow', click: getFlow},
			"-",
			{ text:'审批', id:'audit',icon:'detail', click: audit},
			"-",
			{ text:'退回', id:'back',icon:'back', click: back}
		],
		listeners: {
    		selectionChange : function(rowData,rowIndex){
   				var count=Qm.getSelectedCount();//行选择个数
				Qm.setTbar({
					detail : count==1,
					audit : count==1,
					flow : count==1,
					sub : count>0,
					back : count>0
				});				
    		},
			rowAttrRender : function(rowData, rowid) {
				
			}
		}
	};
	
	function detail(){
		var id = Qm.getValueByName("id");
		top.$.dialog({
			width : 800, 
			height : 750, 
			lock : true, 
			title : "申请", 
			data : {"window" : window},
			content : 'url:app/fwxm/scientific/personalTraining/personal_training_apply_modify.jsp?status=detail&step=detail&id='+id,
			close:function(){
				Qm.refreshGrid();
			}
		});
	}
	function audit(){
		var id = Qm.getValueByName("id");
		top.$.dialog({
			width : 800, 
			height : 750, 
			lock : true, 
			title : "申请", 
			data : {"window" : window},
			content : 'url:app/fwxm/scientific/personalTraining/personal_training_apply_modify.jsp?status=modify&step=sign&id='+id,
			close:function(){
				Qm.refreshGrid();
			}
		});
	}
	function sub(){
		var audit_opinion=Qm.getValuesByName("audit_opinion");
		for(var i=0;i<audit_opinion.length;i++){
			if(audit_opinion[i]==""||audit_opinion[i]==null){
				$.ligerDialog.alert("所选数据有还未审核的数据,请重新选择！！！");
				return;
			}
		}
		if(audit_opinion.indexOf("通过")==-1){
			var ids=Qm.getValuesByName("id");
			 $.getJSON("personalTrainingAction/sub.do?ids="+ids,
		    			{op_id:"",op:"",step:"sign"},function(resp){
						if(resp.success){
							top.$.notice("提交成功！");
							api.close();
							api.data.window.Qm.refreshGrid();
						}else{
							top.$.notice("提交失败！");
						}
					});		
		}else{
			var ids=Qm.getValuesByName("id");
			top.$.dialog({
				width : 400, 
				height : 200, 
				lock : true, 
				title:"选择审核人员",
				content: 'url:app/fwxm/scientific/personalTraining/choose_audit_op.jsp?step=sign&ids='+ids,
				data : {"window" : window}
			});
		}
		
	}
	function submitAction(o) {
		Qm.refreshGrid();
	}
	
	function beforeClose(){
		Qm.refreshGrid()
	}
	function back(){
		var ids=Qm.getValuesByName("id");
		top.$.dialog({
			width : 400, 
			height : 200, 
			lock : true, 
			title:"选择审批人员",
			content: 'url:app/fwxm/scientific/personalTraining/choose_return_step.jsp?step=sign&ids='+ids,
			data : {"window" : window}
		});
	}
	//标记是否弹出录入界面
</script>
</head>
<body>
	<qm:qm pageid="personal_training" script="false" singleSelect="false">
		<qm:param name="status" value="3" compare="=" />
		<qm:param name="sign_op_id" value="<%=userId%>" compare="=" />
	</qm:qm>
</body>
</html>
