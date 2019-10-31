<%@page import="com.khnt.utils.FileUtil"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.File"%>
<%@page import="net.glxn.qrgen.image.ImageType"%>
<%@page import="net.glxn.qrgen.QRCode"%>
<%@page import="java.io.ByteArrayOutputStream"%>
<%@ page contentType="image/png;charset=UTF-8"%>
<%
	String codeText = request.getParameter("code_text");
	System.out.print(codeText);
	ByteArrayOutputStream bout = QRCode.from(codeText).to(ImageType.PNG).stream();
	response.getOutputStream().write(bout.toByteArray());
	File qrimg = File.createTempFile("tmpimg", ".png");
	FileOutputStream fout = new FileOutputStream(qrimg);
	fout.write(bout.toByteArray());
	fout.flush();
	fout.close();
	FileUtil.download(response, qrimg, "qrcode.png", "image/png");
	qrimg.delete();
	out.clear();
	out = pageContext.pushBody();
%>