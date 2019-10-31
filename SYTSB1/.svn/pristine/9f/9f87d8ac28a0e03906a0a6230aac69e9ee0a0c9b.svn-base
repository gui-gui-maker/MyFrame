<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%
	CurrentSessionUser user = SecurityUtil.getSecurityUser();
	String userId = user.getId();
	String userName = user.getName();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
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
	var step=$("#step").ligerGetComboBoxManager().getValue();
	$.getJSON("personalTrainingAction/back.do?ids=${param.ids}",{step:step},function(resp){
		if(resp.success){
			top.$.notice("提交成功！");
			api.close();
			api.data.window.Qm.refreshGrid();
		}else{
			top.$.notice("提交失败！");
		}
	});		
}
</script>
</head>
<body>
	<form name="formObj" id="formObj">
	<table  border="1" cellpadding="3" cellspacing="0"
			class="l-detail-table">
		<c:if test="${param.step=='org_audit' }">
		<tr>
			<td class="l-t-td-left">退回步骤：</td>
			<td class="l-t-td-right">
			<input type="radio"  name="step" id="step"  ltype="radioGroup" validate="{required:true}"
			 ligerui="{initValue:'apply',data:[{id:'apply',text:'申请'}]}"/>	
			</td>
		</tr>
		</c:if>
		<c:if test="${param.step=='audit' }">
		<tr>
			<td class="l-t-td-left">退回步骤：</td>
			<td class="l-t-td-right">
			<input type="radio"  name="step" id="step"  ltype="radioGroup" validate="{required:true}"
			 ligerui="{initValue:'org_audit',data:[{id:'org_audit',text:'部门审核'},{id:'apply',text:'申请'}]}"/>	
			</td>
		</tr>
		</c:if>
		<c:if test="${param.step=='sign'}">
		<tr>
			<td class="l-t-td-left">退回步骤：</td>
				<td class="l-t-td-right">
				<input type="radio"  name="step" id="step"  ltype="radioGroup" validate="{required:true}"
				 ligerui="{initValue:'audit',data:[{id:'audit',text:'审核'},{id:'org_audit',text:'部门审核'},{id:'apply',text:'申请'}]}"/>	
				</td>
		</tr>
		</c:if>
	</table>
	</form>
</body>
</html>
