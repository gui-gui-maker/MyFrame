<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="add">
<title>验证打印独立密码</title>
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
$(function(){
	$("#formObj2").initForm({
		success : function(response) {//处理成功
			if (!response.success) {
				$.ligerDialog.error(response.msg)
			}else{
				window.location=response.data;
			}
		}, 
		toolbarPosition : "bottom",
		toolbar : false
	});
});

function mdyPrintPwd(){
	top.$.dialog({
		width:450,
		height:300,
		lock:true,
		title:"修改独立密码",
		content:"url:app/flow/export/mdy_print_pwd.jsp"
	});
}

function doReset(){
	var url = 'url:app/flow/export/reset_print_pwd.jsp';
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

function validPrintPwd(){
	if ($("#newPwd").val() != $("#newPwdPre").val()) {
		$.ligerDialog.warn("<p>两次输入的新密码不一样！<br/>请重新输入！</p>");
		$("#newPwd1").val("");
		$("#newPwd2").val("");
		return false;
	}
	$("#formObj2").submit();
}
</script>
<style type="text/css">
	td,input{font-size:14px;}
	.mdy a{text-decoration:underline;}
	.l-fieldset1 { padding:5px;border:1px solid #a2c8fb;margin:100px 300px 100px 300px;border-radius:5px 5px 5px 5px;}
	.l-legend1 { padding:0px 5px;border:1px solid #a2c8fb;background:#ecf8ff;border-radius:5px 5px 5px 5px; }
	.l-legend1 div { padding:0px; font-weight:bold; }
</style>
</head>
<body>
	<form name="formObj2" id="formObj2" method="post"
		action="employee/basic/validPrintPwd.do" style="margin:2em">
		<input type="hidden" name="request_uri" value="<%=request.getAttribute("origin_uri") %>"/>
		<fieldset class="l-fieldset1">
			<legend class="l-legend1">
				<div>验证打印独立密码</div>
			</legend>
			<table id="tab1" border="0" cellpadding="3" cellspacing="0" width=""
				align="center">
				<tr>
					<td class="l-t-td-left">独立密码：</td>
					<td class="l-t-td-right"><input name="print_pwd"
						id="print_pwd" type="password" ltype="password"
						validate="{required:true,maxlength:16}" /></td>
				</tr>
				<tr>
					<td class="l-t-td-left"></td>
					<td class="l-t-td-right">
						<div class="mdy">
							<a href="javascript:void();" onclick="javascript:doReset();">忘记密码</a>&nbsp;&nbsp;<a href="javascript:void();"
								onclick="javascript:mdyPrintPwd();">修改密码</a>
						</div></td>
				</tr>
				<tr>
					<td class="l-t-td-left">&nbsp;</td>
					<td class="l-t-td-right" align="right"><a class="l-button-warp l-button"
						href="javascript:validPrintPwd();" style="margin-top:2px"><span
							class="l-button-main l-button-hasicon"><span
								class="l-button-text">确定</span><span
								class="l-button-icon l-icon-discuss"></span>
						</span>
					</a></td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>