<%@page import="org.apache.commons.logging.LogFactory"%>
<%@page import="org.apache.commons.logging.Log"%>
<%@page import="com.khnt.utils.LogUtil"%>
<%@ page import="com.khnt.core.exception.KhntException"%>
<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" %>
<%
    Log loger = LogFactory.getLog("REQUEST_ERROR");
	Exception e = (Exception) request.getAttribute("exception");
	String msg = "操作失败，请稍后重试！";
    if(e != null){
    	if (e instanceof KhntException)
    		msg = ((KhntException) e).getMessage();
        LogUtil.logError(loger, e);
    }
	String xRequestedWith = request.getHeader("X-Requested-With");
	if (xRequestedWith != null && xRequestedWith.equals("XMLHttpRequest")) {
		out.print("{\"success\":false,\"msg\":\"" + msg + "\"}");
	}
	else {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.startsWith("application/json")) {
			response.setContentType("application/json;charset=UTF-8");
			out.print("{\"success\":false,\"msg\":\"" + msg + "\"}");
		}
		else if (accept != null && accept.startsWith("text/xml")) {
			response.setContentType("text/xml;charset=UTF-8");
			out.print("<request><success>false</success><msg>" + msg + "</msg></request>");
		}
		else {
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>错误提醒</title>
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>
</head>
<style>
ul, li, a, p, div {font-size:4mm;font-family: "微软雅黑", "宋体";}
p{text-indent: 2em}
h1 { font-family: "微软雅黑", "宋体"; font-size:18px; font-weight:normal; background:#2e92ec; padding:8px 0px; text-align:center; color:#fff; }
.errorpage { position:absolute; width:100%; top:20px; margin-left:auto;margin-right:auto; border:#e1e1e1 solid 1px; position:relative; padding:0px; font:12px "宋体"; zoom:1; }
.errorpage .erricon { background:url(k/kui/images/icons/dialog/error.png) no-repeat center; position:absolute; left:0; top:0px; width:80px; height:80px; }
.errorpage .wrnicon { background:url(k/kui/images/icons/dialog/alert.png) no-repeat center; position:absolute; left:0; top:0px; width:80px; height:80px; }
.errorpage .sucicon { background:url(k/kui/images/icons/dialog/success.gif) no-repeat center; position:absolute; left:0; top:0px; width:80px; height:80px; }
.errorpage .first { font-size:5mm; border-bottom:#ccc dashed 1px; color:#f00; padding:30px 20px 30px 80px;}
.errorpage ul {margin-left:0; margin-right:1em;}
.errorpage li { margin:10px 0px 10px 0px; line-height:18px; list-style-position:inside; }
.errorpage li a { color:#f00; margin:0px 5px;}
</style>
<body>
<h1>错误提示</h1>
<div class="errorpage"> <span class="erricon"></span> 
	<div class="first"><%=msg%></div>
	<ul>
		<li>如果这是一个弹出窗口，您可以关闭此窗口！</li>
		<li>或者您可以点击<a href="javascript:history.go(-1)">返回</a>，尝试回到之前的页面！</li>
	</ul>
	<p>关于技术支持，您可以：</p>
	<ul>
		<li><!--点击 <a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=649023372&site=qq&menu=yes"><img 
				src="http://wpa.qq.com/pa?p=2:498747434:41" /></a> -->联系技术支持，获得帮助！</li>
		<li><!--点击<a target="_blank" href="http://mail.qq.com/cgi-bin/qm_share?t=qm_mailme&email=jffl-c3m5eP5o_7i4A" 
					style="text-decoration:none;"><img 
				src="http://rescdn.qqmail.com/zh_CN/htmledition/images/function/qm_open/ico_mailme_01.png"/></a>-->给技术支持人员发送邮件！</li>
	</ul>
</div>
</body>
</html>
<%}}%>