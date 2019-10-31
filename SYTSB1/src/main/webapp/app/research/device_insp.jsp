<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@page import="java.util.Date"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.utils.DateUtil"%>
<head pageStatus="${param.status}">
 <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>

    <%@include file="/k/kui-base-list.jsp"%>
<link href="app/research/css/common.css" rel="stylesheet" type="text/css">
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
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
			  <div class="icon_box"><a href="#" target="_blank"><p>设备检验报告信息</p></a></div>
		   </div>
		   <div class="Per_info">
				
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="per_tab">
					<tr>
						<th>报告书使用单位</th>
						<th>报告书编号</th>
						<th>检验时间</th>
						<th>报告书名称</th>
						<th>检验机构</th>
						<th>参检人员</th>
						<th>检验结论</th>
					</tr>
				 	<c:forEach var="insp" items="${inspList}">
						   <tr>
							<td>${insp.com_name}</td>
							<td>${insp.report_sn}</td>
							<td>${fn:substring(insp.advance_time, 0, 10)}</td>
							<td>${insp.report_name}</td>
							<td>${insp.org_name}</td>
							<td>${insp.check_op_name}</td>
							<td>${insp.inspection_conclusion}</td>
					</tr>
					   
					   </c:forEach>
					
				</table>
		   </div>
		  </div>	
</div>

</body>
</html>