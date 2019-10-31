<%@ page contentType="text/html;charset=UTF-8"%>
<%
	String xRequestedWith = request.getHeader("X-Requested-With");
	if (xRequestedWith != null && xRequestedWith.equals("XMLHttpRequest")) {
		out.print("{\"sessionTimeout\":true}");
	}
	else {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.startsWith("application/json")) {
			response.setContentType("application/json;charset=UTF-8");
			out.print("{\"sessionTimeout\":true}");
		}
		else if (accept != null && accept.startsWith("text/xml")) {
			response.setContentType("text/xml;charset=UTF-8");
			out.print("<server><session-timeout>true</session-timeout></server>}");
		}
		else {
			String cp = request.getContextPath();
			response.sendRedirect((cp.equals("/") ? "/" : (cp + "/")) + "user/login.do");
		}
	}
%>