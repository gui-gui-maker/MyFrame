<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/k/kui-base-form.jsp"%>
<title>login</title>
<script type="text/javascript" src="k/libs/jsencrypt.min.js"></script>
<script type="text/javascript">
	api.title("");
	api.max = false;
	api.min = false;
	$(function() {
		pageTitle({
			to : "#title",
			text : "<span style='font-size:18px'>您已离线，请重新登陆！</span>",
			icon : "k/kui/images/icons/32/places.png"
		});
		$("#loginForm").initForm({
			toolbar: [{
				icon: "key",
				text: "登录",
				click: function(){
					$("#loginForm").submit();
				}
			},{
                icon: "logout",
                text: "退出",
                click: function(){top.location.reload();}
            }]
		});
		if(!kui["loginYZM"]){
			$("#yzmtr").remove();
			$("#loginForm").css("marginTop","2em");
		}
		$("#loginForm").validate({submitHandler:doReLogin});
	});
	var _j_login_rsa_pubkey = "${j_login_public_kay}";
	function _getLoginFormData(){
		var returnVal = {
			_spring_security_remember_me : false
		};
		var username = top._current_user_account;
		var password = $("#j_password").val();
		var vcode = kui["loginYZM"]?$("#j_validate_code").val():"";
		var encrypt = new JSEncrypt();
	    encrypt.setPublicKey(_j_login_rsa_pubkey);
		returnVal['j_username'] = encrypt.encrypt(username);//用户名密文
		returnVal['j_password'] = encrypt.encrypt(password);//密码密文
		if(vcode) returnVal['j_validate_code'] = encrypt.encrypt(vcode);//验证码密文
		return returnVal;
	}
	function doReLogin(){
		$("body").mask("正在登录...");
        $.post($("#loginForm").attr("action"),_getLoginFormData(), function(resp) {
            if (resp.success) {
                top.$.notice("登录成功！", 3);
                if(api.data["callback"]) api.data["callback"].call();
                api.close();
            } else {
            	top.$.notice(resp.msg || "登录失败！", 3,"k/kui/images/icons/dialog/32X32/hits.png");
            }
            $("body").unmask();
        }, "json");
	}
</script>
</head>
<body>
	<div class="title-tm">
		<div id="title"></div>
	</div>
	<form name="loginForm" id="loginForm" action="j_spring_security_check" method="post" style="padding:8px">
		<table class="l-detail-table">
			<tr>
				<td class="l-t-td-left" style="width: 80px;">您的密码：</td>
				<td class="l-t-td-right" colspan="2"><input id="j_password" type="password" value="" name="j_password"
				     validate="{required:true,maxLength:16}"/></td>
			</tr>
			<tr id="yzmtr">
                <td class="l-t-td-left" style="width: 80px;">验证码：</td>
                <td class="l-t-td-right"><input name="j_validate_code" type="text" id="j_validate_code"
                    validate="{required:true,maxLength:4}" /></td>
                <td class="l-t-td-right" style="width: 80px;"><img src="security/web/validateCodeImg.do?isNew=true" width="70" height="26" /></td>
            </tr>
		</table>
	</form>
</body>
</html>