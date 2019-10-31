<%@page import="org.apache.poi.util.SystemOutLogger"%>
<%@page import="com.khnt.certificate.util.CertificateUtil"%>
<%@page import="java.security.cert.X509Certificate"%>
<%@page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true"%>
<%
	String phone = (String)session.getAttribute("Phone");
	String account = (String)session.getAttribute("Account");
	System.out.println(phone);
	System.out.println(account);
	boolean isCertError = false;
	Object userName = "";
	if (request.isSecure()){
		X509Certificate cert = CertificateUtil.extractClientCertificate(request);
		try {
			userName = CertificateUtil.extractPrincipal(cert,"CN=(.*?),");
		} catch (Exception e) {
			isCertError = true;
		}
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageKeys="login">
<%@include file="/k/kui-base.jsp"%>
<title>Loading...</title>
<meta HTTP-EQUIV="Page-Exit" CONTENT="blendTrans(Duration=0.5)">
<script type="text/javascript" src="app/js/jQuery.md5.js"></script>
<script type="text/javascript">
	loadCoreLibrary("login");
	var LOGIN_CERT = {
		uname : "<%=userName%>",//用户账号 admin
		isCertErr : <%=isCertError%>//是否证书读取
	};
	
	var username = '<%=account%>';
	$(function(){
		if(username!='null'&&username!=''){
			$.post("j_spring_security_check", {
				j_password : $.md5("password"),
				j_username : username
			}, function(data) {
				if (data["success"]) {
					console.log(username+" is logged....");
					window.location="${pageContext.request.contextPath}/app/weixin/dining/wxdining/dining-main.jsp";
				} else {
					document.write("没有权限，请联系管理员！");
				}
			});
		}else{
			$.post("j_spring_security_check",{j_username:"dengww",j_password:"xxxx"},function(data){
				if (data["success"]) {
					console.log(username+" is logged....");
					window.location="${pageContext.request.contextPath}/app/weixin/dining/wxdining/dining-main.jsp";
				} else {
					alert("11111");
					document.write("没有权限，请联系管理员！");
				}
			}); 
		}
	});
</script>
</head>
<body>
<h1 style="padding:5mm 0 2mm 0;font-family:宋体;font-size:14mm;text-align:center;margin-top: 50%;">正在加载中...</h1></br>
</body>
</html>