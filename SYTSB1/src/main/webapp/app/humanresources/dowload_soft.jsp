<%@page import="com.khnt.base.Factory"%>
<%@page import="com.khnt.utils.Base64Utils"%>
<%@page import="java.io.File"%>
<%@ page import="com.khnt.utils.FileUtil"%>
<%@ page import="com.khnt.utils.StringUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	String filePath = new String(Base64Utils.decode(request.getParameter("fpath").replaceAll(" ", "+")));
	String fileName = filePath;
	String softPath = Factory.getSysPara().getProperty("pub.soft.path");
	if (!StringUtil.isEmpty(filePath)) {
		filePath = softPath + File.separator + filePath;
        if(new File(filePath).exists()){
        	 FileUtil.download(response, filePath, fileName, null);
        	 out.clear();
        	 out = pageContext.pushBody();
        }
        else
            out.print("<script>alert('非法操作！')</script>");
	}
%>