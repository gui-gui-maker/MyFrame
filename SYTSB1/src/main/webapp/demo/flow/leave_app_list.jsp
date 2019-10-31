﻿<%@page
	import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@page import="com.khnt.security.CurrentSessionUser" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>工作流演示-起草</title>
<%@include file="/k/kui-base-list.jsp" %>
<script type="text/javascript" src="pub/bpm/js/util.js"></script>
<script type="text/javascript">
	var qmUserConfig = {
		sp_fields: [
   			{name: "person", compare: "like"},
   			{group: [
   					{label: "休假日期从",name: "starts_date", compare: ">="},
   					{label: "到", name: "starts_date", compare: "<=", labelAlign: "center", labelWidth: 20}
   			]},
   			{name: "types", compare: "="},
   			{name: "creater", compare: "like"},
   			{label:"状态",name: "state", compare: "="},
   			{name: "dept", compare: "like"}
   		],
		tbar: [ {
			text: '详情',
			disabled:true,
			id: 'detail',
			icon: 'detail',
			click: function () {
				var st = Qm.getValueByName("state");
				var sid = Qm.getValueByName("id");
				var url = "url:demo/flow/leave_app_draft.jsp?pageStatus=detail&id=" + sid;
				if(st!="0")
					url = "url:demo/flow/leave_app_view.jsp?tid=" + sid;
				top.$.dialog({
					width: 600,
					height: 300,
					lock: true,
					title: "查看-请假详情",
					content: url
				});
			}
		} , "-", {
			text: '申请',
			id: 'add',
			icon: 'add',
			click: function () {
				top.$.dialog({
					width: 600,
					height: 300,
					lock: true,
					data:{Qm:Qm},
					title: "请假申请",
					content: "url:demo/flow/leave_app_draft.jsp?pageStatus=add"
				});
			}
		} , "-", {
			disabled:true,
			text: '修改',
			id: 'modify',
			icon: 'modify',
			click: function () {
				top.$.dialog({
					width: 600,
					height: 300,
					lock: true,
					data:{Qm:Qm},
					title: "请假申请",
					content: "url:demo/flow/leave_app_draft.jsp?pageStatus=modify&id=" + Qm.getValueByName("id")
				});
			}
		} , "-", {
			disabled:true,
			text: '删除',
			id: 'del',
			icon: 'del',
			click: function () {
				$.del(kFrameConfig.DEL_MSG, "demo/bpm/bussiness/delete.do", {
					"ids": Qm.getValuesByName("id").toString()
				});
			}
		} , "-", {
			disabled:true,
			text: '提交审批',
			id: 'submit',
			icon: 'submit',
			click: readyStart
		}, "-", {
			text: '实例说明',
			icon: 'help',
			click: function(){
				top.$.dialog({
					width: 500,
					height: 300,
					lock: true,
					title: "示例说明",
					content: $("#help").html()
				});
			}
		}],
		listeners: {
			selectionChange: function (rowData, rowIndex) {
				var count = Qm.getSelectedCount();//行选择个数
				if(count==1){
					var state = Qm.getValuesByName("state");
					Qm.setTbar({
						detail:true,modify: state == "0", del: state == "0", submit: state == "0"
					});
				}
				else{
					Qm.setTbar({
						detail:false,modify: false, del: false, submit: false
					});
				}
			},
			rowAttrRender: function (item, rowid) {
				if (item.state == "1")
					return 'style="color:blue"';
				else if (item.state == "2")
					return 'style="color:green"';
				else
					return 'style="color:red"';
			}
		}
	};

	function readyStart(){
		$("body").mask("正在处理，请稍后！");
		// 准备启动流程，首先检查申请人的身份
		$.getJSON("demo/bpm/bussiness/readyStart.do?personId=" + Qm.getValueByName("person_id"), function(resp){
			if(resp.success){
				//当申请人是部门负责人时，需要选择分管领导来审批
				if(resp.personType!="dm"){
					saveAndSubmit(resp.personType);
				}
				else{//否则，其他类型人员直接提交
					selectPersonAndStart(resp.personType);
				}
			}
		});
	}
	
	function selectPersonAndStart(personType){
		selectUnitOrUser(1, 0, '', '',function(data){
			saveAndSubmit(personType,data.code,data.name);
		});
	}
	
	function saveAndSubmit(pt,pid,pname){
		getServiceFlowConfig("demo-bpm-leave","",function(rst,fb){
			if(!rst){
				$("body").unmask();
				$.ligerDialog.error("没有配置该业务对应的工作流！");
				return;
			}
			$.getJSON("demo/bpm/bussiness/start.do",{
				id : Qm.getValueByName("id"),
				flowId : fb.id,
				personType: pt,
				nextPersonId: pid,
				nextPersonName: pname
			},
			function(resp){
				if(resp.success){
					top.$.notice("提交成功！",3);
					Qm.refreshGrid();
				}else{
					$.ligerDialog.error("操作失败！<br/>"+resp.msg);
				}
				$("body").unmask();
			});
		});
	}
</script>
</head>
<body>
	<qm:qm pageid="demo_bpm" script="false" singleSelect="true" />
	<div id="help" style="display:none">
		<p>本示例是一个请假业务审批流程，其规则是：</p>
		<ol>
			<li>当请假人是一般员工时，其上级审批者是部门负责人。</li>
			<li>当请假人是部门负责人时，其上级审批者是公司分管该部门的副总。</li>
			<li>当请假人是副总时，其上级审批者是公司总经理。</li>
			<li>所有人请假如果超过2天，在经过直接上级审批后必须提交公司总经理审批（副总无论几天都是总经理审批！）</li>
		</ol>
	</div>
</body>
</html>
