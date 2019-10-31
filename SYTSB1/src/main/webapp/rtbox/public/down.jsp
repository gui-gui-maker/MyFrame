<%@page import="com.khnt.rtbox.template.constant.RtPath"%>
<%@page import="com.khnt.rtbox.tools.Utils"%>
<%@page import="com.khnt.rtbox.tools.DbUtil"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>
<%@page import="com.khnt.utils.StringUtil"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>

<%@page language="java" contentType="application/force-download" pageEncoding="UTF-8"%>

<%
	String filedownload =request.getParameter("path");
	String fileName =request.getParameter("name");

	response.reset();
	String contentType = request.getParameter("contentType");
	if (StringUtil.isNotEmpty(contentType)) {
		response.setContentType(contentType);
	} else {
		response.setContentType("application/force-download");
	}
	response.setHeader("Content-Disposition", "attachment; filename=" + new String(fileName.getBytes(), "ISO-8859-1"));
	java.io.OutputStream outp = null;
	FileInputStream in = null;
	try {
		outp = response.getOutputStream();
		in = new FileInputStream(RtPath.basePath+filedownload);
		byte[] b = new byte[1024];
		int i = 0;
		while ((i = in.read(b)) > 0) {
			outp.write(b, 0, i);
		}
		outp.flush();

		System.out.println("Downloaded file filename:" + fileName);
	} catch (Exception e) {
		System.out.println("Download file filename:" + fileName + ", failed:");
		e.printStackTrace();
	} finally {
		if (in != null) {
			in.close();
			in = null;
		}
	}

	out.clear();
	out = pageContext.pushBody();
%>
