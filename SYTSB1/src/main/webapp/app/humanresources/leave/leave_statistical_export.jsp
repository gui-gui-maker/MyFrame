<%@page import="com.khnt.utils.StringUtil"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.khnt.utils.DateUtil"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/k/kui-base-list.jsp" %>
<%  
	String org_name = request.getSession().getAttribute("org_name").toString();
	String userName = request.getSession().getAttribute("userName").toString();
	String startDate = request.getSession().getAttribute("startDate").toString();
	String lastDate = request.getSession().getAttribute("lastDate").toString();
	String title = "请休假统计("+startDate+"至"+lastDate+")";
	response.setHeader("Content-disposition","attachment; filename="+new String(title.getBytes("gb2312"), "ISO8859-1" )+".xls"); 
%>
<script type="text/javascript">
	$(function () {  	
		exportToExcel();
	});
	
	function exportToExcel() { 
		var winname = window.open('', '_blank', 'top=10000'); 
		var strHTML = document.all.tableExcel.innerHTML; 
  		winname.document.open('text/html', 'replace'); 
    	winname.document.writeln(strHTML); 
     	winname.document.execCommand('saveas','','请休假统计（${sessionScope.startDate}至${sessionScope.lastDate}）.xls'); 
     	winname.close(); 
  		closeCurWin();
	}
	
	function closeCurWin() {
	    window.opener=null;
	    window.open('','_self');
	    window.close();
	}
</script>
<style type="text/css">
#table td{text-align:center;}
</style>
</head>
<body>
<div id="tableExcel"> 
	<table width="700" cellpadding="6" cellspacing="0" bgcolor="#cccccc" border="1" id="table">
    	<tr align="center">
        	<td colspan="11">
        		<font size="3"><b>请休假统计（${sessionScope.startDate}至${sessionScope.lastDate}）</b></font>
        	</td>
    	</tr>
            <tr>
				<td align="center">部门</td>
				<td align="center">姓名</td>
				<td align="center">进院时间</td>
				<td align="center">工龄（年）</td>
				<td align="center">应休假天数</td>
				<td align="center">已休年假</td>
				<td align="center">休假时间</td>
				<td align="center">剩余年假</td>
				<td align="center">其他已休</td>
				<td align="center">休假时间</td>
				<td align="center">备注</td>
			</tr>
			<c:forEach items="${sessionScope.data}" var="data">
				<c:choose>
	            	<c:when test="${'' != data.org_name && null != data.org_name}">
	            		<tr align="center">
		                	<td><c:out value="${data.org_name}"></c:out></td>  
		                	<td>${data.emp_name}</td>
		                	<td>${data.into_work_date}</td>
		                	<td>${data.work_age}</td>
		                	<td>${data.total_days}</td>
		                	<td>${data.nj_days_used}</td>
		                	<td>${data.nj_days_used_date}</td>
		                	<td>${data.nj_days_left}</td>
		                	<td>${data.other_days_used}</td>
		                	<td>${data.other_days_used_date}</td>
		                	<td>${data.remark}</td>
		            	</tr>
	            	</c:when>
	            	<c:otherwise>
	            		
	            	</c:otherwise>
	     		</c:choose>
            </c:forEach>
	</table>
</div>
</body>
</html>