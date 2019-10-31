<%@page import="com.khnt.utils.FileUtil"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.File"%>
<%@page import="net.glxn.qrgen.image.ImageType"%>
<%@page import="net.glxn.qrgen.QRCode"%>
<%@page import="java.io.ByteArrayOutputStream"%>
<%@ page contentType="image/png;charset=UTF-8"%>
<%
	String fname = request.getParameter("fname");
	if ("n".equals(request.getParameter("type")))
		fname = "/fileupload/downloadByObjId.do?id=" + fname;
	else
		fname = "/pub/tools/dowload_soft.jsp?fpath=" + fname;

	fname = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
	        + request.getContextPath() + fname;

	ByteArrayOutputStream bout = QRCode.from(fname).to(ImageType.PNG).stream();
	response.getOutputStream().write(bout.toByteArray());

	File qrimg = File.createTempFile("tmpimg", ".png");
	FileOutputStream fout = new FileOutputStream(qrimg);
	fout.write(bout.toByteArray());
	fout.flush();
	fout.close();
	FileUtil.download(response, qrimg, "qrcode.png", "image/png");
	qrimg.delete();
	out = pageContext.pushBody();
%>