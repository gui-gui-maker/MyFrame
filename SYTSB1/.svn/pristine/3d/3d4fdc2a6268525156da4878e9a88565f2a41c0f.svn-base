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
	String title = "财务报账费用统计（部门）("+org_name+")";
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
     	winname.document.execCommand('saveas','','财务报账费用统计（部门）（${sessionScope.org_name}）.xls'); 
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
        		<font size="3"><b>财务报账费用统计（${sessionScope.org_name}）</b></font>
        	</td>
    	</tr>
    	<tr>
    		<td colspan="8" align="center">
    			<c:if test="${sessionScope.startDate!=''}">
        			<c:out value="统计时间：${sessionScope.startDate}"></c:out>
        			<c:choose>
	        			<c:when test="${sessionScope.endDate!=''}">
	        				<c:out value="至 ${sessionScope.endDate}"></c:out>
	        			</c:when>
	        			<c:otherwise>
	        				<c:out value="至今"></c:out>
	        			</c:otherwise>
	        		</c:choose>
        		</c:if>
        		<c:if test="${'' eq sessionScope.startDate && '' eq sessionScope.endDate && '' eq sessionScope.minPrice && '' eq sessionScope.maxPrice}">
        			统计截止至<%=DateUtil.getDateTime("yyyy年MM月dd日",new Date())%>
        		</c:if>
    		</td>
    	</tr>
            <tr>
				<td align="center">单位</td>
				<td align="center">部门</td>
				<td align="center">差旅补助</td>
				<td align="center">差旅费</td>
				<td align="center">培训费</td>
				<td align="center">费用报销</td>
				<td align="center">领款</td>
				<td align="center">小计</td>
			</tr>
			<c:forEach items="${sessionScope.data}" var="data">
				<c:choose>
	            	<c:when test="${'' != data.depName && null != data.depName}">
	            		<tr align="center">
		                	<td><c:out value="${data.unitName}"></c:out></td>  
		                	<td>${data.depName}</td>
		                	<td>${data.clBz}</td>
		                	<td>${data.clf}</td>
		                	<td>${data.pxfy}</td>
		                	<td>${data.fybx}</td>
		                	<td>${data.draw}</td>
		                	<td>${data.taotal}</td>
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