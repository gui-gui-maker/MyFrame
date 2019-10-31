<%@page import="org.apache.poi.util.SystemOutLogger"%>
<%@page import="com.khnt.certificate.util.CertificateUtil"%>
<%@page import="java.security.cert.X509Certificate"%>
<%@page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	
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
<%-- <%@include file="/k/kui-base.jsp"%> --%>
<base href="<%=basePath%>">
<title>Loading...</title>
<meta HTTP-EQUIV="Page-Exit" CONTENT="blendTrans(Duration=0.5)">
<link rel="stylesheet" href="app/gis/scjy/v3/frame-main.css"/>
<style type="text/css">
	body, html, #frameDiv, #loader {
		width: 100%;
		height: 100%;
		overflow: hidden;
		margin: 0;
		font-size: 14px;
		font-family: "Microsoft Yahei", "黑体", "宋体";
	}
</style>

<link rel="stylesheet" type="text/css" href="app/gis/css/navigation-bar.css" media="all" />
<script type="text/javascript" src="app/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="app/js/jQuery.md5.js"></script>
<script type="text/javascript" src="app/gis/scjy/v3/frame-main.js"></script>
<script type="text/javascript">
	//loadCoreLibrary("login");
	var LOGIN_CERT = {
		uname : "<%=userName%>",//用户账号 admin
		isCertErr : <%=isCertError%>//是否证书读取
	};
	
	$(function(){
		openLoading();
		$.post("j_spring_security_check",{j_username:"admin",j_password:"pxT17,Oy"},function(data){
			if (data["success"]) {
				//alert("scc");
				//mBigData("app/gis/scjy/v3/main.jsp");
				window.location.href="${pageContext.request.contextPath}/app/gis/scjy/v3/main.jsp?t="+Math.random();
			} else {
				document.write("admin用户登录失败！");
				closeLoading();
			}
		}); 
	});
	
	/*
	 * 打开加载等待图层
	 */
	function openLoading()
	{
		$("#loader").css("display","block");
	}

	/*
	 * 关闭加载等待图层
	 */
	function closeLoading()
	{
		$("#loader").css("display","none");
	}
</script>
</head>
<body>
<div id="loader" class="loader">
	<div class="load-center">
		<div class="ball-spin-fade-loader">
			<div></div>
			<div></div>
			<div></div>
			<div></div>
			<div></div>
			<div></div>
			<div></div>
			<div></div>
		</div>
		<div class="load-txt">数据加载中，请稍候...</div>
	</div>
</div>
</body>
</html>