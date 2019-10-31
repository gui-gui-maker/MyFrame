<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%
	CurrentSessionUser user = SecurityUtil.getSecurityUser();
	String userId = user.getId();
	String userName = user.getName();
	String sql="select t.id code,t.name text from SYS_USER t where t.org_id='"+user.getDepartment().getId()+"'";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<script type="text/javascript" src="/pub/selectUser/selectUnitOrUser.js"></script>
<head pageStatus="${param.status}">
<%@ include file="/k/kui-base-form.jsp"%>
<title>选择审核意见</title>
<script type="text/javascript">
$(function() {
	$("#formObj").initForm({
		toolbar: [{ text: '保存', id: 'submit', icon: 'submit', click:save},
		          {text: '关闭', id: 'close', icon: 'cancel', click: function () {
		        	api.close();
		        	}
		          }],
		      	getSuccess : function(res) {
		      	}
		
	});
	
});
function save(){
	if($('#formObj').validate().form()){
		var auditId=$("#auditId").ligerGetComboBoxManager().getValue();
		var auditName = $("#auditName").val();
		  $.getJSON("cwBillAction/subAudit.do?ids=${param.ids}",
		    			{op_id:auditId,op:auditName},function(resp){
						if(resp.success){
							top.$.notice("提交成功！");
							api.close();
							api.data.window.Qm.refreshGrid();
						}else{
							top.$.notice("提交失败！");
						}
					});		
	}
	
}
function setValue(valuex,name){
	if(valuex==""){
		return;
	}
	if(name=='auditName'){
		$('#auditName').val(valuex)
	}
}
</script>
</head>
<body>
	<form name="formObj" id="formObj">
	<table  border="1" cellpadding="3" cellspacing="0"
			class="l-detail-table">
		<tr>
			<td class="l-t-td-left">审核人：</td>
				<td class="l-t-td-right">
				<input type="text"  name="auditId" id="auditId"  ltype="select" onchange="setValue(this.value,'auditName')" validate="{required:true}"
				ligerui="{
				data:<u:dict sql="select t.id code,t.name text from SYS_USER t ,sys_role r,sys_user_role ur where t.id = ur.user_id and r.id = ur.role_id and r.name like '%票据申请审核%'"/>
				}"				
				/>
				<input type="hidden" name="auditName" id="auditName"/>
				</td>
		</tr>
	</table>
	</form>
</body>
</html>
