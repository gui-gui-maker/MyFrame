<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.utils.DateUtil"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
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
	//alert(11);
	 $("#formObj").initForm({
			toolbar: [{ text: '保存', id: 'submit', icon: 'submit', click:save},
			          {text: '关闭', id: 'close', icon: 'cancel', click: function () {
						api.close();
						}}],
		
	}); 
});
function save(){
	$.ligerDialog.confirm("确认提交？", function(yes){
		if(yes){
			var opinion=$("#opinion").ligerGetRadioGroupManager().getValue();
			var userId=$("#userId").val();
			var userName=encodeURI($("#userName").val());
			var op="";
			var ids="${param.ids}";
			var status="${param.step}";
			if(opinion==""){
				$.ligerDialog.warn("请选择意见！！！");
				return;
			}
			var next_op_name=encodeURI($("#next_op_name").val());
			var next_op_id=$("#next_op_id_val").val();
			if(status!="sign"&&opinion=="0"&&(next_op_id==""||next_op_id==null)){
				$.ligerDialog.warn("请选择下一步处理人！！！");
				return;
			}
			if(opinion=="0"){
				op=encodeURI("同意");				
			}
			if(opinion=="1"){
				op=encodeURI("不同意");
			}
			var auditDate=$("#date").val();
			$.getJSON ("qualityFilesUpdateAction/saveOpinion.do?ids="+ids
					 +"&userId="+userId,{opinion:op,status:status,userName:userName,auditDate:auditDate,next_op_id:next_op_id,next_op_name:next_op_name},function(res){
				 if(res.success){
					 top.$.notice("保存成功！");					
					 api.close();
				 }
			 });
		}
	});
}
function setValue(valuex,name){
	if(valuex==""){
		return;
	}
	if(name=='next_op_name'){
		$('#next_op_name').val(valuex)
	}
}
//改变结论事件
function changeFlag(flag) {		
	if (flag == "0") {
		$("#next_op").show();
	} else {
		$("#next_op").hide();
	}
}
</script>
</head>
<body>
	<form name="formObj" id="formObj">
	<input id="userId" type="hidden" value="<%=userId%>"/>
	<input id="userName" type="hidden" value="<%=userName%>"/>
	<table  border="1" cellpadding="3" cellspacing="0"
			class="l-detail-table">
		<tr>
			<c:if test="${param.step=='audit'}">
				<td class="l-t-td-left">审核意见：</td>
			</c:if>
			<c:if test="${param.step=='sign'}">
				<td class="l-t-td-left">签发意见：</td>
			</c:if>
			<td class="l-t-td-right">
			<input id="opinion" name="opinion" type="radio" ltype='radioGroup' 
			 ligerui="{initValue:'0',onChange:changeFlag,data:[{id:'0',text:'同意'},{id:'1',text:'不同意'}]}"/>
			</td>
		</tr>
		<tr>
			<c:if test="${param.step=='audit'}">
				<td class="l-t-td-left">审核时间：</td>
			</c:if>
			<c:if test="${param.step=='sign'}">
				<td class="l-t-td-left">签发时间：</td>
			</c:if>
            <td class="l-t-td-right">
           		<input type="text" ltype='date' id="date" name="date"
					validate="{required:true}" ligerui="{format:'yyyy-MM-dd hh:mm'}" value="<%=new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date())%>"/>
            </td>
        </tr>
        <c:if test="${param.step=='audit'}">
	        <tr id="next_op">
	        	<td class="l-t-td-left">下一步操作人：</td>
	        	 <td class="l-t-td-right">
		        	<input  name ="next_op_name" id="next_op_name" type="hidden">
		        	<input type="text"  name="next_op_id" id="next_op_id"  ltype="select"  
							onchange="setValue(this.value,'next_op_name')"
								ligerui="{
								readonly:true,
								data: <u:dict sql="select t.id code,t.name text from SYS_USER t ,sys_role r,sys_user_role ur where t.id = ur.user_id and r.id = ur.role_id and r.name like '%体系文件修改批准%'"/>
								}"/></td>
	        </tr>				
		</c:if>
	</table>
	</form>
</body>
</html>
