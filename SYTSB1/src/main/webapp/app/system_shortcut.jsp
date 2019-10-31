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
		text: "生成快捷方式", 
		icon: "k/kui/images/icons/32/places.png"
	});
	$("#formObj").initForm({
		success : function(response) {//处理成功
			if (response.success) {
				top.$.notice("快捷方式生成成功!",4);
				api.close();
			} else {
				$.ligerDialog.error(response.msg)
			}
		}, 
		toolbar : [{
			text : "提交", icon : "save", click : function() {
				if($("#name").val()=="输入手机号或用户名"){
					$.ligerDialog.warn('请输入用户名！');
					return;
				}
				
				//$("#formObj").submit();
				   var name = $("#name").val();
                   var password = $("#password").val();
                   window.location.href=$("base").attr("href")+"employee/basic/createLoginFile.do?name="+name+"&password="+password;
					//api.close();
			}}, {
				text : "关闭", icon : "cancel", click : function() {
					api.close();
				}
			}
		]
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
		action="employee/basic//createLoginFile.do" style="margin:2em">
		<table id="tab1" border="0" cellpadding="3" cellspacing="0" width=""
			class="l-detail-table">
			<tr>
				<td class="l-t-td-left">用户名：</td>
				<td class="l-t-td-right">
				 <input id="name" name="name" type="text"  value="输入手机号或用户名"
				  style="color:#9C9A9C;" onfocus="if(this.value=='输入手机号或用户名'){this.value='';this.style.color = '#000'};" onblur="if(this.value==''){this.value='输入手机号或用户名';this.style.color='#9C9A9C'};" /></td>
			</tr>
			<tr>
				<td class="l-t-td-left">密码：</td>
				<td class="l-t-td-right"><input name="password" id="password" type="password" ltype="password" 
					validate="{required:true,maxlength:16}" /></td>
			</tr>
		</table>
	</form>
</body>
</html>