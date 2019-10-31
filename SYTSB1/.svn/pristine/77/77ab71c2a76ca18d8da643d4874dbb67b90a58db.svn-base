<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageKeys="login">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Loading...</title>
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>
<script type="text/javascript" src="k/kui/frame/core.js"></script>
<script type="text/javascript" src="k/kui/frame/main.js"></script>
<script type="text/javascript">
loadCoreLibrary("login");
</script>
<script type="text/javascript" src="app/public/k-frame-login.js"></script>
</head>
<body>
<div class="k-login-layer">
	<div class="k-login-layer-panel">
		<div class="k-login-top">
			<div class="k-login-top-logo">
				<div class="k-login-top-logo-btnbox">
					
				</div>
			</div>
		</div>
		<div class="k-login-middle">
			<div class="k-login-layout">
				<div class="k-login-layout-content">
					<div class="k-login-custom_img">
						<div class="k-login-custom_mask"></div>
					</div>

					<div class="k-login-box">
						<form name="loginForm" id="loginForm" action="j_spring_security_check" method="post" onsubmit="return sysLoginDoSubmit(this)">
							<div class="k-login-box-title" id="sysNamePanel">
								<div><span></span></div>
							</div>
							<div class="k-login-inputbox" id="userNamePanel">
								<div  class="k-login-inputname">
									<div><span>用户名</span></div>
								</div>
								<div class="k-login-inputleft">
									<input class="TxtUserNameCssClass" id="TxtUserName" type="text" value="admin" maxLength="20" name="j_username">
								</div>
								<div class="k-login-inputright">
									<div><span>*请输入用户名</span></div>
								</div>
							</div>
							<div class="k-login-inputbox" id="userPassPanel">
								<div  class="k-login-inputname">
									<div><span>密　码</span></div>
								</div>
								<div class="k-login-inputleft">
									<input class="TxtPasswordCssClass" id="TxtPassword" type="password" value="" name="j_password">
								</div>
								<div class="k-login-inputright">
									<div><span>*请输入密码</span></div>
								</div>
							</div>
							<div class="k-login-inputbox" id="userCodePanel">
								<div class="k-login-inputname">
									<div><span>验证码</span></div>
								</div>
								<div class="k-login-inputyz">
									<input name="j_validate_code" type="text" id="validateCode" />
								</div>
								<div class="k-login-inputyzimg"></div>
								<div class="k-login-inputbtn">
									<button type="submit"><div>登&nbsp;录</div></button>
								</div>
							</div>
							<div class="k-login-inputbox-only" id="userButtonPanel">
								<div class="k-login-inputbtn">
									<button type="submit"><div>登&nbsp;录</div></button>
								</div>
								<div class="k-login-inputbtn">
									<button type="reset"><div>清&nbsp;空</div></button>
								</div>
							</div>
						</form>
					</div>
					<div class="k-login-style">
						<%--<div class="k-login-style_btn"></div>--%>
						<%--<div class="k-login-style_btn"></div>--%>
						<%--<div class="k-login-style_btn"></div>--%>
						<%--<div class="k-login-style_btn"></div>--%>
					</div>
				</div>
			</div>
		</div>
		<div class="k-login-foot">
			<div class="k-login-foot-copy" id="loginCopy"><div><span></span></div></div>
			<div class="k-login-foot-tech" id="loginTech"><div><span></span></div></div>
		</div>
	</div>
</div>
</body>
</html>
