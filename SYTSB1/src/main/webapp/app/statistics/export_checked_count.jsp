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
     	winname.document.execCommand('saveas','','各检验部门检验业务统计表（${sessionScope.device_name}）.xls'); 
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
        	<td colspan="7">
        		<font size="3"><b>各检验部门检验业务统计表（${sessionScope.device_name}）</b></font>
        	</td>
    	</tr>
    	<tr>
    		<td colspan="7" align="center">
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
				<td rowspan="2" align="center">检验部门</td>
				<td colspan="3" align="center">检验类别</td>
				<td rowspan="2" align="center">合计</td>
				<td rowspan="2" width="100" align="center">去年同期合计</td>
				<td rowspan="2" width="100" align="center">与去年同期比较</td>
			</tr>
			<tr>
				<td width="95" align="center">定期检验</td>
				<td width="95" align="center">监督检验</td>
				<td width="95" align="center">委托检验</td>
			</tr>
			<c:forEach items="${sessionScope.data}" var="data">
				<c:choose>
	            	<c:when test="${'' != data.check_unit && null != data.check_unit}">
	            		<tr align="center">
		                	<td><c:out value="${data.check_unit}"></c:out></td>  
		                	<td>${data.cur_dj_count}</td>
		                	<td>${data.cur_jj_count}</td>
		                	<td>${data.cur_wj_count}</td>
		                	<td>${data.cur_jy_total}</td>
		                	<td>${data.last_jy_total}</td>
		                	<td>${data.compare_count}</td>
		            	</tr>
	            	</c:when>
	            	<c:otherwise>
	            		<tr align="center">
		                	<td><c:out value="合计"></c:out></td>  
		                	<td>${data.dj_total}</td>
		                	<td>${data.jj_total}</td>
		                	<td>${data.wj_total}</td>
		                	<td>${data.total}</td>
		                	<td>${data.last_total}</td>
		                	<td>${data.compare_total}</td>
		            	</tr>
	            	</c:otherwise>
	     		</c:choose>
            </c:forEach>
	</table>
</div>
</body>
</html>