<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="zh-cn" xml:lang="zh-cn" style="overflow:hidden;">
<head>
<title>系统登录</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>
<script type="text/javascript" src="k/kui/frame/jquery.min.js"></script>
<script type="text/javascript" src="pub/explorer/ui-ext/ui-ie-external.js"></script>
<script type="text/javascript" src="pub/explorer/ui-ext/login.js"></script>
<style type="text/css">
body { margin:0; padding:0; font:12px "宋体"; overflow:hidden; }
form, label { margin:0; padding:0; }
div.layout { width:255px; height:168px; padding:82px 0px 0px 125px; margin:0 auto; background:url(pub/explorer/img/loginbj.png) no-repeat left bottom; overflow-y:hidden; }
div.row { overflow:hidden; padding:5px; }
div.row label { padding-right:2px; line-height:28px; font-weight:bold;}
div.row input { margin:0; padding:0; border:#3c81c4 solid 1px; width:180px; height:26px; line-height:26px; padding:0px 5px; vertical-align:middle; }
div.rowck { overflow:hidden; padding-bottom:25px; padding-top:3px; }
div.rowck span{display:inline-block;vertical-align:middle;line-height:12px;margin:0;padding:0;height:12px;}
div.rowck checkbox { margin:0; padding:0; vertical-align:middle;float:left;}
div.rowbtn {overflow:hidden;text-align:left;}
div#logining { overflow:hidden; text-align:center; padding-right:14px;color:red;display:none;font-weight:bold;}
div.rowbtn input { margin:10px 15px 0 0; padding:0; border:0; width:50px; height:29px; font-weight:bold;}
div.rowbtn input.btnlogin { background:url(pub/explorer/img/btn_login.png) no-repeat center; color:#1c78c3; }
div.rowbtn input.btncanle { background:url(pub/explorer/img/btn_loginc.png) no-repeat center; color:#707070; }
</style>
</head>
<body>
<div class="wrap" style="width:380px; height:251px; left:50%; top:50%; position:absolute; margin:-125px 0 0 -190px;">
	<div class="layout">
		<form id="form1" name="form1" method="post" action="">
			<div class="row">	
				<!--<label for="textfield">帐 号：</label>--><input name="j_username" type="text" value="" class="text1" />
			</div>
			<div class="row">
				<!--<label for="textfield">密 码：</label>--><input name="j_password" type="password" class="text2" value="" maxlength="16" />
			</div>
			<div class="rowck">
				<span title="记住密码可以在下次自动填写用户名和密码！">
					<input class="checkbox" type="checkbox" id="rememberme" value="1" readonly="readonly" /><label for="rememberme">记住密码</label>
				</span>
				<span title="下次自动登录！">
					<input class="checkbox" type="checkbox" id="autoLogin" value="1" onclick="autoLoginCheckedChange()" /><label for="autoLogin">自动登录</label>
				</span>
				<span title="下次开机之后自动启动！">
					<input class="checkbox" type="checkbox" id="autorun" value="1" /><label for="autorun">自动启动</label>
				</span>
			</div>
			<div class="rowbtn" id="logining">正在尝试自动登录...</div>
			<div class="rowbtn" id="login_btn">
				<input type="button" value="登录" class="btnlogin" onclick="doAjaxLogin()" />
				<input type="button" value="取消" class="btncanle" onclick="window.external.Quit()" />
			</div>
		</form>
	</div>
</div>
</body>
</html>