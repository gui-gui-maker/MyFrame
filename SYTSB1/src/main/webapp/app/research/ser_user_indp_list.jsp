<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@page import="java.util.Date"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.utils.DateUtil"%>
<head pageStatus="${param.status}">
 <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>

    <%@include file="/k/kui-base-list.jsp"%>
<link href="app/research/css/common.css" rel="stylesheet" type="text/css">
<title>人员信息</title>
<script type="text/javascript">
	$(function() {
		
	})
</script>
</head>

<body style="overflow: auto;">


<div class="s_n_bg"></div>

<div class="ser_cont">

	<div class="search_box">
		   <div class="title">
			  <div class="icon_box"><a href="#" target="_blank"><p>报告信息</p></a></div>
		   </div>
		   <div class="Per_info">
				
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="per_tab">
					<tr>
						<th>报告书使用单位</th>
						<th>设备名称</th>
						<th>检验部门</th>
						<th>报告书名称</th>
						<th>参检人员</th>
					</tr>
				 	<c:forEach var="insp" items="${inspList0}">
						   <tr>
							<td>${insp.com_name}</td>
							<td>${insp.device_name}</td>
							<td>${insp.org_name}</td>
							<td>${insp.report_name}</td>
							<td>${insp.check_op_name}</td>
					</tr>
					   
					   </c:forEach>
					
				</table>
		   </div>
		  </div>	
</div>

</body>
</html>