<%@page import="com.khnt.rbac.impl.bean.Employee"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="add">
<title>重置印章独立密码</title>
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
$(function(){
	$("#formObj2").initForm({
		success : function(response) {//处理成功
			
		}, 
		toolbar : [{
				text : "关闭", icon : "close", click : function() {
					api.close();
				}
			}
		]
	});
});

function sendMsg(){
	var step = 59;
	$("#sendwxBtn").val(" 重新发送  60  秒");
    var _res = setInterval(function()
    {   
        $("#sendwxBtn").attr("disabled", true);//设置disabled属性
        $("#sendwxBtn").val(" 重新发送 "+step+" 秒");
        step-=1;
        if(step <= 0){
        $("#sendwxBtn").removeAttr("disabled"); //移除disabled属性
        $("#sendwxBtn").val("  发送  ");
        clearInterval(_res);//清除setInterval
        }
    },1000);
    
	var send_msg_type =$('#send_msg_type').ligerGetRadioGroupManager().getValue();
	$.post("employee/basic/resetPrintPwd.do?send_msg_type="+send_msg_type, function(resp) {
		if (resp.success) {
			top.$.dialog.notice({content:'发送成功'});
		}else{
			$.ligerDialog.error(resp.msg);
		}
	});
}
</script>
<style type="text/css">
</style>
</head>
<%
	CurrentSessionUser user = SecurityUtil.getSecurityUser();
	User uu = (User)user.getSysUser();
	Employee emp = (Employee)uu.getEmployee();
%>
<body>
	<form name="formObj2" id="formObj2" method="post"
		action="employee/basic/resetPrintPwd.do" style="margin:2em">
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>重置印章密码</div>
			</legend>
			<table id="tab1" border="0" cellpadding="3" cellspacing="0" width="" align="center">
				<tr>
					<td class="l-t-td-left">手机号码：</td>
					<td class="l-t-td-right" colspan="3"><input name="mobile"
						id="mobile" type="text" disabled="disabled"
						value="<%=emp.getMobileTel() %>" /></td>
				</tr>
				<tr>
					<td class="l-t-td-left">发送信息：</td>
					<td class="l-t-td-right"><input type="radio"
						name="send_msg_type" id="send_msg_type" ltype="radioGroup"
						validate="{required:false}"
						ligerui="{value:'1',data: [ { text:'微信', id:'1' }, { text:'短信', id:'2' }, { text:'微信和短信', id:'3' } ] }" />
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">说明：</td>
					<td class="l-t-td-right"><c:out
							value="点击【发送】按钮，系统会生成新的印章密码发送至您的手机，请注意查收！"></c:out><br />
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">&nbsp;</td>
					<td class="l-t-td-right"><input type="button" id="sendwxBtn"
						name="sendwxBtn" value="&nbsp;&nbsp;发送&nbsp;&nbsp;" onclick="javascript:sendMsg();" /></td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>