<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'cxbg.jsp' starting page</title>
    

<link rel="stylesheet" type="text/css"
	href="/app/webmanage/CSS/style.css" />
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body >
   
				<table border="0" cellspacing="0" cellpadding="0"  width="800" align="center" class="">
						<tr>
							<td>单位名称</td>
							<td>报告书编号</td>
							<td>检验日期</td>
							<td>报告类型</td>
							<td>设备类别</td>
							<td>报告情况</td>
						</tr>
						<c:forEach items="${bjxxcxData}" var="bjxxcxDatas">
							<tr>
								
								<td>${bjxxcxDatas.reportInfo}</td>
							</tr>
						</c:forEach>
				</table>
				
				
				
				
		
  </body>
</html>
