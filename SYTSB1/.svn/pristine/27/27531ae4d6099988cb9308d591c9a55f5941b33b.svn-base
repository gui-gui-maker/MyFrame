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
			/* { text:'录入报告', id:'input',icon:'modify', click: input},
			"-", */
			{ text:'新增', id:'add',icon:'add', click: add},
			"-",
			{ text:'修改', id:'modify',icon:'modify', click: modify}, 
			"-",
			{ text:'提交', id:'sub',icon:'submit', click: sub},
			"-",
			{ text:'打印', id:'print',icon:'print', click: print},
			"-",
			{ text:'删除', id:'del',icon:'del', click: del}
			
		],
		listeners: {
    		selectionChange : function(rowData,rowIndex){
		    	var count=Qm.getSelectedCount();//行选择个数
		    	var count=Qm.getSelectedCount();//行选择个数
		    	var flag = Qm.getValueByName("status");
				Qm.setTbar({
					detail : count==1,
					flow : count==1,
					modify : count==1,
					sub : count>0,
					del : count>0&&flag=="0",
					print: count==1
				});	
    		},
    		 rowDblClick: function(rowData, rowIndex) {
   		      detail();
   	        },
			rowAttrRender : function(rowData, rowid) {
				var flag = rowData.status;
            	var fontColor="#000000";
            	//已提交到流程
            	if(flag=="1"){
        			fontColor="blue";
        		}
            	if(flag=="2"){
        			fontColor="orange";
        		}
            	if(flag=="3"){
        			fontColor="purple";
        		}
            	if(flag=="4"){
        			fontColor="green";
        		}
            	if(flag=="5"){
        			fontColor="red";
        		}
        		
        		return "style='color:"+fontColor+"'";
			}
		}
	};
	function detail(){
		var id = Qm.getValueByName("id");
		top.$.dialog({
			width : 800, 
			height : 750, 
			lock : true, 
			title : "详情", 
			data : {"window" : window},
			content : 'url:app/fwxm/scientific/personalTraining/personal_training_apply_modify.jsp?status=detail&step=detail&id='+id,
			close:function(){
				Qm.refreshGrid();
			}
		});
	}
	function add(){
		top.$.dialog({
			width : 800, 
			height : 500, 
			lock : true, 
			title : "新增", 
			data : {"window" : window},
			content : 'url:app/fwxm/scientific/personalTraining/personal_training_apply_detail.jsp?status=modify',
			close:function(){
				Qm.refreshGrid();
			}
			});
	}
	function modify(){
		var status=Qm.getValuesByName("status");
		for(var i=0;i<status.length;i++){
			if(status[i]!=="0"){
				$.ligerDialog.alert("所选数据已提交审核的，不能修改，请重新选择！！！");
				return;
			}
		}
		var id = Qm.getValueByName("id");
		top.$.dialog({
			width : 800, 
			height : 500, 
			lock : true, 
			title : "修改", 
			data : {"window" : window},
			content : 'url:app/fwxm/scientific/personalTraining/personal_training_apply_modify.jsp?status=modify&step=apply&id='+id,
			close:function(){
				Qm.refreshGrid();
			}
		});
	}
	function print(){
		var status=Qm.getValueByName("status");
		if(status=="5"){
			$.ligerDialog.alert("审批未通过，请重新选择！！！");
			return;
		}
		var id=Qm.getValueByName("id");
		top.$.dialog({
 			width : 1000, 
 			height : 800, 
 			lock : true, 
 			title:"打印业务培训申请表",
 			content: 'url:app/fwxm/scientific/personalTraining/personal_training_apply_doc.jsp?id='+id,
 			data :{"window" : window}
 		}).max();
	}

	function sub(){
		var status=Qm.getValuesByName("status");
		for(var i=0;i<status.length;i++){
			if(status[i]!=="0"){
				$.ligerDialog.alert("所选数据有已提交审核的，不能重复提交，请重新选择！！！");
				return;
			}
		}
		var ids=Qm.getValuesByName("id");
		top.$.dialog({
			width : 400, 
			height : 200, 
			lock : true, 
			title:"选择审核人员",
			content: 'url:app/fwxm/scientific/personalTraining/choose_audit_op.jsp?step=org_audit&ids='+ids,
			data : {"window" : window}
		});
	}
	function del(){		
		  $.del("确定删除?",
		    		"personalTrainingAction/del.do",
		    		{"ids":Qm.getValuesByName("id").toString()});
  }
</script>
</head>
<body>
	<div class="item-tm" id="titleElement">
		<div class="l-page-title">
			<div class="l-page-title-note">
			
				提示：
				<font color="black">“黑色”</font>代表正在编制，<font color="blue">“蓝色”</font>代表部门审核，
					<font color="orange">“橙色”</font>代表待审核，
					<!-- <font color="purple">“紫色”</font>代表待审批， -->
					<font color="green">“绿色”</font>代表审批通过，
					<font color="red">“红色”</font>代表审核不通过。
			</div>
		</div>
	</div>
	<qm:qm pageid="personal_training" script="false" singleSelect="false">
		<qm:param name="create_op_id" value="<%=userId%>" compare="=" />
	</qm:qm>
</body>
</html>
