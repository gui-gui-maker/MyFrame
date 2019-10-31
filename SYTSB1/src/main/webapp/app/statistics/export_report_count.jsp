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
	//response.setHeader("Content-disposition","attachment; filename="+new String("设备信息统计表".getBytes("gb2312"), "ISO8859-1" )+".xls"); 
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
     	winname.document.execCommand('saveas','','检验资料综合统计数据表.xls'); 
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
        	<td colspan="5">
        		<font size="3"><b>检验资料综合统计数据表</b></font>
        	</td>
    	</tr>
    	<tr>
    		<td colspan="5" align="center">
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
				<td rowspan="2" align="center"><b>类别</b></td>
				<td colspan="2" align="center"><b>打印检验报告</b></td>
				<td colspan="2" align="center"><b>发放检验报告</b></td>
			</tr>
			<tr>
				<td width="220" align="center"><b>台数</b></td>
				<td width="220" align="center"><b>份数</b></td>
				<td width="100" align="center"><b>台数</b></td>
				<td width="100" align="center"><b>份数</b></td>
			</tr>
			<c:forEach items="${sessionScope.data}" var="data">
	            <tr align="center">
		                	<td>${data.category}</td>  
		                	<td>${data.dev_p_count}</td>
		                	<td>${data.rep_p_count}</td>
		                	<td>${data.dev_lq_count}</td>
		                	<td>${data.rep_lq_count}</td>
		    	</tr>	            	
            </c:forEach>
	</table>
</div>
</body>
</html>