<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" style="overflow:hidden">
<head pageStatus="add">
<title>个人设置</title>
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="pub/explorer/ui-ext/ui-ie-external.js"></script>
<script type="text/javascript">
	$(function(){
		$("#formObj2").initForm({
			toolbar:null
		});
		var autoLogin = getProfile(PROFILE_KEY_AUTO_LOGIN);
		var autorun = getAutoStartup();
		var closeBoxRef = getProfile(PROFILE_KEY_CLOSE_BOX_REF);
		if(autoLogin=="true")
			$("#autologin1").attr("checked",true);
		else
			$("#autologin2").attr("checked",true);
		if(autorun)
			$("#autostartup1").attr("checked",true);
		else
			$("#autostartup2").attr("checked",true);
		
		if(closeBoxRef==PROFILE_VAL_CLOSE_BOX_QUIT)
			$("#main_close_btn1").attr("checked",true);
		else
			$("#main_close_btn2").attr("checked",true);
	});
	//========================================== 登录界面用户选项配置 ==========================================
	//配置用户个性化选项
	function configUserProfile(){
		try{
			saveUserConfig();
			alert("成功保存设置！");
			window.close();
		}
		catch(e){
			alert("发生错误！" + e);
		}
	}
	
	function saveUserConfig(){
		// 自动登录设置
		var autoLogin = getProfile(PROFILE_KEY_AUTO_LOGIN);
		if ($("input[name='autologin']:checked").val()=="1") {
			if(autoLogin != "true") 
				writeProfile(PROFILE_KEY_AUTO_LOGIN, PROFILE_VAL_TRUE);
		} else {
			writeProfile(PROFILE_KEY_AUTO_LOGIN, PROFILE_VAL_FALSE);
		}

		// 自动启动设置
		var autorun = getAutoStartup();
		var autorunProfile = getProfile(PROFILE_KEY_AUTO_RUN);
		if ($("input[name='autostartup']:checked").val()=="1") {
			if(!autorun) 
				setAutoStartup(true);
			if(autorunProfile != "true") 
				writeProfile(PROFILE_KEY_AUTO_RUN, PROFILE_VAL_TRUE);
		} else {
			if(autorunProfile == "true"){
				setAutoStartup(false);
				writeProfile(PROFILE_KEY_AUTO_RUN, PROFILE_VAL_FALSE);
			}
		}
		
		//窗口关闭按钮功能
		writeProfile(PROFILE_KEY_CLOSE_BOX_REF, $("input[name='main_close_btn']:checked").val());
	}
</script>
<style type="text/css">
	table{
		margin-top: 2em;
		width: 100%;
		border: none;
		border-collapse: collapse;
	}
	td{
		font-size: 14px;
		padding: 5px 0;
	}
	.l-t-td-left{
		width: 45%;
		text-align: right;
	}
	.l-t-td-right{
		width: 55%;
		text-align: left;
	}
	.btn_td{
		padding: 2em;
		text-align: center;
	}
	.l-button{
		width: 60px;
		padding: 5px;
		margin-left: 10px;
	}
</style>
</head>
<body>
	<form name="formObj2" id="formObj2" method="post" action="#">
		<table class="l-detail-table">
			<tr class="c_f_t">
				<td class="l-t-td-left">自动登录：</td>
				<td class="l-t-td-right">
					<input type="radio" name="autologin" value="1" id="autologin1" /><label for="autologin1">是</label>&nbsp;
					<input type="radio" name="autologin" value="0" id="autologin2" /><label for="autologin2">否</label>
				</td>
			</tr>
			<tr class="c_f_t">
				<td class="l-t-td-left">开机启动：</td>
				<td class="l-t-td-right">
					<input type="radio" name="autostartup" value="1" id="autostartup1" /><label for="autostartup1">是</label>&nbsp;
					<input type="radio" name="autostartup" value="0" id="autostartup2" /><label for="autostartup2">否</label>
				</td>
			</tr>
			<tr class="c_f_t">
				<td class="l-t-td-left">主窗口关闭按钮功能：</td>
				<td class="l-t-td-right">
					<input type="radio" name="main_close_btn" value="quit" id="main_close_btn1" /><label for="main_close_btn1">退出系统</label>&nbsp;
					<input type="radio" name="main_close_btn" value="hide" id="main_close_btn2" /><label for="main_close_btn2">隐藏界面</label>
				</td>
			</tr>
			<tr>
				<td class="btn_td" colspan="2">
					<input class="l-button" type="button" value="保 存" onclick="configUserProfile()" />
					<input class="l-button" type="button" value="取 消" onclick="window.close()" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>