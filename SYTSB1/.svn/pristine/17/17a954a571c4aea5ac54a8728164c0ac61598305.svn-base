<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="add">
<title>修改独立密码</title>
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
$(function(){
	$("#formObj2").initForm({
		success : function(response) {//处理成功
			if (response.success) {
				top.$.notice("独立密码修改成功!",4);
				api.close();
			} else {
				$.ligerDialog.error(response.msg)
			}
		}, 
		toolbar : [{
			text : "修改", icon : "save", click : function() {
				if ($("#newPwd").val() != $("#newPwdPre").val()) {
					$.ligerDialog.warn("<p>两次输入的新密码不一样！<br/>请重新输入！</p>");
					$("#newPwd1").val("");
					$("#newPwd2").val("");
					return false;
				}
				$("#formObj2").submit();
			}
		}, {
			text : "重置", icon : "modify", click : function() {
				doReset();	
			}
		}, {
			text : "关闭", icon : "close", click : function() {
				api.close();
			}
		}]
	});
});

function doReset(){
	var url = 'url:app/secondpwd/reset_second_pwd.jsp';
	top.$.dialog({
		width : 400,
		height : 280,
		lock : true,
		title : "重置独立密码",
		data : {
			"window" : window
		},
		content : url	
	});
}
</script>
<style type="text/css">
	.mdy a{text-decoration:underline;}
</style>
</head>
<body>
	<form name="formObj2" id="formObj2" method="post"
		action="employee/basic/mdySecondPwd.do" style="margin:2em">
		<table id="tab1" border="0" cellpadding="3" cellspacing="0" width=""
			class="l-detail-table">
			<tr>
				<td class="l-t-td-left">原密码：</td>
				<td class="l-t-td-right"><input name="oldPwd" id="oldPwd" type="password" ltype="password" 
					validate="{required:true,maxlength:16}" /></td>
			</tr>
			<tr>
				<td class="l-t-td-left">新密码：</td>
				<td class="l-t-td-right"><input name="newPwd" id="newPwd" type="password" ltype="password" 
					validate="{required:true,maxlength:16}" /></td>
			</tr>
			<tr>
				<td class="l-t-td-left">再输一次：</td>
				<td class="l-t-td-right">
					<input name="newPwd2" id="newPwdPre" type="password" ltype="password" 
						validate="{required:true,maxlength:16}" /></td>
			</tr>
			<tr>
				<td class="l-t-td-left">忘记密码：</td>
				<td class="l-t-td-right">
					<div class="mdy">
					方法一：联系系统管理员重置。系统会将独立密码恢复成初始密码；<br/>
					方法二：直接点击<a href="javascript:void();" onclick="javascript:doReset();">重置</a>。系统会生成新的独立密码，并以微信和短信的形式发送至您的手机。
					</div>
				</td>	
			</tr>
		</table>
	</form>
</body>
</html>