
//========================================== 系统登录过程 ==========================================
// 验证登录表单、执行登录
function doAjaxLogin() {
	if ($("input[name='j_username']").val() == "" || $("input[name='j_password']").val() == "") {
		alert("“用户名”和“密码”不能为空!");
		return;
	}
	$("#login_btn").hide();
	$("#logining").show();
	var loginAction = "j_spring_security_check";
	// var loginAction = getProfile(PROFILE_KEY_SYS_LOGIN_ACTION);
	if (loginAction) {
		var formData = {
			j_username : $("input[name='j_username']").val(),
			j_password : $("input[name='j_password']").val()
		};
		// login submit by ajax jsonp
		$.post(loginAction,formData,loginCallback,"json");
	} else {
		alert("参数配置错误：未设置登录目标URL!\n\n参数KEY：LOGIN_ACTION");
	}
}

// JSON回调方法
function loginCallback(resp) {
	var sysIndex = "k/main.jsp";
	// var sysIndex = getProfile(PROFILE_KEY_SYS_INDEX);
	if (resp.success) {
		if (sysIndex) {
			configUserProfile();
			window.location = $("base").attr("href")+sysIndex;
			extMaxMinWindow(2);
		} else{
			$("#login_btn").show();
			$("#logining").hide();
			alert("参数配置错误：未设置系统主页！");
		}
	} else {
		alert("错误的用户名或密码！");
		$("#login_btn").show();
		$("#logining").hide();
	}
}

//========================================== 登录界面用户选项配置 ==========================================
//配置用户个性化选项
function configUserProfile(){
	configRememberme();
	configAutoLogin();
	configAutorun();
}

// 记住密码设置
function configRememberme() {
	var rememberme = getProfile(PROFILE_KEY_REMEMBER_ME);
	if ($("#rememberme").attr("checked")) {
		writeProfile(PROFILE_KEY_REMEMBER_ME, PROFILE_VAL_TRUE);
		writeProfile(PROFILE_KEY_USER_ACCOUNT, $("input[name='j_username']").val());
		writeProfile(PROFILE_KEY_USER_PWD, $("input[name='j_password']").val());
	} else {
		writeProfile(PROFILE_KEY_REMEMBER_ME, PROFILE_VAL_FALSE);
		writeProfile(PROFILE_KEY_USER_ACCOUNT, "");
		writeProfile(PROFILE_KEY_USER_PWD, "");
	}
}

// 自动登录设置
function configAutoLogin() {
	var autoLogin = getProfile(PROFILE_KEY_AUTO_LOGIN);
	if ($("#autoLogin").attr("checked")) {
		writeProfile(PROFILE_KEY_AUTO_LOGIN, PROFILE_VAL_TRUE);
	} else {
		writeProfile(PROFILE_KEY_AUTO_LOGIN, PROFILE_VAL_FALSE);
	}
}

// 自动启动设置
function configAutorun() {
	var autorun = getAutoStartup();
	if ($("#autorun").attr("checked")) {
		if(autorun) return;
		else setAutoStartup(true);
	} else if(autorun) setAutoStartup(false);
}

//检查自动登录选项变化，控制记住密码选项
function autoLoginCheckedChange() {
	if ($("#autoLogin").attr("checked")) {
		$("#rememberme").attr("checked", true).attr("readonly", "readonly");
	} else {
		$("#rememberme").attr("readonly", "");
	}
}

//========================================== 自动登录、记住密码、开机启动等初始化  ==========================================
// 启动后首先检测是否自动登录
$(function() {
	try {
		var rememberme = getProfile(PROFILE_KEY_REMEMBER_ME);
		var autoLogin = getProfile(PROFILE_KEY_AUTO_LOGIN);
		var autorun = getAutoStartup();
		
		//获取用户名、密码
		var u_name = getProfile(PROFILE_KEY_USER_ACCOUNT);
		var u_pwd = getProfile(PROFILE_KEY_USER_PWD);
		if (rememberme=="true") {
			if (u_name && u_pwd) {
				$("input[name='j_username']").val(u_name);
				$("input[name='j_password']").val(u_pwd);
				$("#rememberme").attr("checked",true);
			}
		}
		if (autorun) {
			$("#autorun").attr("checked",true);
		}
		if (autoLogin=="true") {
			if (u_name && u_pwd) {
				$("#rememberme").attr("readonly","readonly");
				$("#autoLogin").attr("checked",true);
				$("#login_btn").hide();
				$("#logining").show();
				window.setTimeout('doAjaxLogin()',1500)
			}
		}
	} catch (e) {
		alert("发生错误：\n\n" + e);
	}
});

function hideFrameWindow(){
	window.external.Quit();
}