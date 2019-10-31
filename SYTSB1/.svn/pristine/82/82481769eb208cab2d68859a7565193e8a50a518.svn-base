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
	String title = "四川省特种设备检验研究院中层干部公务外出统计";
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
     	winname.document.execCommand('saveas','','四川省特种设备检验研究院中层干部公务外出统计.xls'); 
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
        	<td colspan="8">
        		<font size="3"><b>四川省特种设备检验研究院中层干部公务外出统计</b></font>
        	</td>
    	</tr>
            <tr>
				<td align="center" style="font-weight:bold;">部门名称</td>
				<td align="center" style="font-weight:bold;">姓名</td>
				<td align="center" style="font-weight:bold;">职务</td>
				<td align="center" style="font-weight:bold;">联系电话</td>
				<td align="center" style="font-weight:bold;">外出时间</td>
				<td align="center" style="font-weight:bold;">外出地点</td>
				<td align="center" style="font-weight:bold;">外出理由</td>
				<td align="center" style="font-weight:bold;">备注</td>
			</tr>
			<c:forEach items="${sessionScope.data}" var="data">
				<c:choose>
	            	<c:when test="${'' != data.org_name && null != data.org_name}">
	            		<tr align="center">
		                	<td><c:out value="${data.org_name}"></c:out></td>  
		                	<td>${data.emp_name}</td>
		                	<td>${data.work_title}</td>
		                	<td>${data.emp_phone}</td>
		                	<td>${data.gwwc_date}</td>
		                	<td>${data.gwwc_place}</td>
		                	<td>${data.gwwc_reason}</td>
		                	<td>${data.remark}</td>
		            	</tr>
	            	</c:when>
	            	<%-- <c:otherwise>
	            		
	            	</c:otherwise> --%>
	     		</c:choose>
            </c:forEach>
	</table>
</div>
</body>
</html>