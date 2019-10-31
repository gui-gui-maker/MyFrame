<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*" %> 
<%@taglib uri="http://bpm.khnt.com" prefix="bpm" %>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!--获取当前登录人  -->
<%
	CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
	User uu = (User)curUser.getSysUser();
	com.khnt.rbac.impl.bean.Employee e = (com.khnt.rbac.impl.bean.Employee)uu.getEmployee();
	String userId=e.getId();//员工ID
	String uId = SecurityUtil.getSecurityUser().getId();//用户ID
%>
<head pageStatus="${param.pageStatus}">
<title>车辆维修单审核</title>
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="pub/bpm/js/util.js"></script>
<script type="text/javascript" src="pub/worktask/js/worktask.js"></script>
<script type="text/javascript">
var pageStatus = "${param.pageStatus}";
var tbar="";
var isChecked="${param.isChecked}";
var serviceId = "${requestScope.serviceId}";//提交数据的id
var activityId = "${requestScope.activityId}";//流程id
var processId = "${requestScope.processId}";//过程
var areaFlag;//改变状态
<bpm:ifPer function="CARREPAIR_BMFZR" activityId = "${requestScope.activityId}">areaFlag="2";</bpm:ifPer>//部门负责人审核
<bpm:ifPer function="CARREPAIR_CDFZR" activityId = "${requestScope.activityId}">areaFlag="3";</bpm:ifPer>//车队负责人审核
<bpm:ifPer function="CARREPAIR_GLBM" activityId = "${requestScope.activityId}">areaFlag="4";</bpm:ifPer>//管理部门审核
	$(function() {
		/* alert(isChecked);
		alert(serviceId);
		alert(activityId);
		alert(processId);
		alert(areaFlag); */
		tbar=[{ text: '审核不通过', id: 'shbtg', icon: 'del', click: shbtg},
            { text: '审核通过', id: 'shtg', icon: 'submit', click: shtg},
            { text: '关闭', id: 'close', icon:'cancel', click:function(){api.close();}}];
	    $("#formObj").initForm({
			success: function (response) {//处理成功
	    		if (response.success) {
			      	if(api.data.window.Qm)
			    	{
			         	api.data.window.Qm.refreshGrid();
			       	}
					top.$.dialog.notice({
	             		content: "保存成功！"
	            	});	
			   		api.data.window.api.close();
			     	api.close();  
			     	api.data.window.api.data.window.Qm.refreshGrid();
	    		} else {
	           		$.ligerDialog.error('保存失败！<br/>' + response.msg);
	      		}
			}, 
			getSuccess: function (response){

			}, 
			showToolbar: true,
            toolbarPosition: "bottom",
            toolbar: tbar
		});
	});
	//审核通过
	function shtg(){
     	var formData=$("#formObj").getValues();
     	var carrepairNote=$.ligerui.toJSON(formData)
        $.ligerDialog.confirm('是否审核通过？', function (yes){
	        if(!yes){return false;}
	        $("body").mask("提交中...");
	        getServiceFlowConfig("carrepair_flow","",function(result,data){
	        	if(result){
	            	top.$.ajax({
	                	url: "CarrepairNoteAction/shtg.do?id="+serviceId+
	                    	"&typeCode=carrepair_flow"+"&activityId="+activityId+"&areaFlag="+areaFlag,
	                    type: "POST",
	                    dataType:'json',
	          	 		data: {"carrepairNote":carrepairNote},
	                    async: false,
	                    success:function (data) {
	                    	if (data) {
	                    		 api.close();
	                        	 api.data.window.api.close();
	                        	 api.data.window.api.data.window.Qm.refreshGrid();
	                        }
	                    }
	                });
	             }else{
	                  $.ligerDialog.alert("出错了！请重试！");
	                  $("body").unmask();
	                 }
	         });
         });
	}
  	//审核不通过
	function shbtg(){
       	var formData=$("#formObj").getValues();
       	var carrepairNote=$.ligerui.toJSON(formData)
    	 $.ligerDialog.confirm('是否要不通过？', function (yes){
	         if(!yes){return false;}
	    	 $("body").mask("正在处理，请稍后！");
	    	 getServiceFlowConfig("carrepair_flow","",function(result,data){
	    		 if(result){
	                 top.$.ajax({
	                     url: "CarrepairNoteAction/shbtg.do?id="+serviceId+
	                    		 "&typeCode=carrepair_flow"+"&activityId="+activityId+"&areaFlag="+areaFlag+"&processId="+processId,
	                     type: "POST",
	                     dataType:'json',
	                     data:{"carrepairNote":carrepairNote},
	                     async: false,
	                     success:function (data) {
	                         if (data) {
	                        	 api.close();
	                        	 api.data.window.api.close();
	                        	 api.data.window.api.data.window.Qm.refreshGrid();
	                         }
	                     }
	                 });
	            }else{
	              $.ligerDialog.alert("出错了！请重试！");
	              $("body").unmask();
	             }
	         });
     	});
   }
</script>
</head>
<body>
<div>
<form name="formObj" id="formObj" method="post" action="">
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>维修单审核</div>
			</legend>
			<table cellpadding="3" cellspacing="0" class="l-detail-table">
				<tr>
					<td class="l-t-td-left">审核意见：</td>
					<td class="l-t-td-right">
						<textarea name="useDepartmentManagerOpinion" id="useDepartmentManagerOpinion" rows="5" cols="25" class="l-textarea" validate="{maxlength:500}"></textarea>
					</td>	
				</tr>
			</table>
		</fieldset>
	</form>
</div>
</body>
</html>
