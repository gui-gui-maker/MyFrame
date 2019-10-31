<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="add">
<title>修改密码</title>
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
$(function(){
	pageTitle({
		to: "#title", 
		text: "修改密码", 
		icon: "k/kui/images/icons/32/places.png"
	});
	$("#formObj").initForm({
		success : function(response) {//处理成功
			if (response.success) {
				top.$.notice("密码修改成功!",4);
				if(api.data.callback){
					api.data.callback();
				}else{
					api.close();
				}
			} else {
				$.ligerDialog.error(response.msg)
			}
		}, 
		toolbar : [{
			text : "提交", icon : "save", click : function() {
				$("#formObj").submit();
			}}, {
				text : "关闭", icon : "cancel", click : function() {
					api.close();
				}
			}
		]
	});
	jQuery.extend(jQuery.validator.messages, {
		equalTo: "两次密码输入不一致，请重新输入！"
	});
});
</script>
<style type="text/css">
	td,input{font-size:14px;}
</style>
</head>
<body>
	<div class="title-tm">
	<div id="title"></div>
	</div>
	<form name="formObj" id="formObj" method="post"
		action="rbac/user/resetPassword.do" style="margin:2em">
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
					<input name="newPwdPre" id="newPwdPre" type="password" ltype="password" 
						validate="{required:true,maxlength:16,equalTo:'#newPwd'}" /></td>
			</tr>
		</table>
	</form>
</body>
</html>