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
     	winname.document.execCommand('saveas','','各部门人员业务统计表（${sessionScope.org_name}）.xls'); 
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
        	<td colspan="9">
        		<font size="3"><b>各部门人员业务统计表（${sessionScope.org_name}）</b></font>
        	</td>
    	</tr>
    	<tr>
    		<td colspan="9" align="center">
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
				<td align="center">人员姓名</td>
				<td align="center">所在部门</td>
				<td align="center">出具报告</td>
				<td align="center">审核报告</td>
				<td align="center">签发报告</td>
				<td align="center">打印报告</td>
				<td align="center">打印合格证</td>
				<td align="center">领取报告</td>
				<td align="center">归档报告</td>
			</tr>
			<c:forEach items="${sessionScope.data}" var="data">
				<c:choose>
	            	<c:when test="${'' != data.user_name && null != data.user_name}">
	            		<tr align="center">
		                	<td><c:out value="${data.user_name}"></c:out></td>
		                	<td>${data.org_name}</td>  
		                	<td>${data.lr_count}</td>
		                	<td>${data.sh_count}</td>
		                	<td>${data.qf_count}</td>
		                	<td>${data.dy_count}</td>
		                	<td>${data.dyhgz_count}</td>
		                	<td>${data.lq_count}</td>
		                	<td>${data.gd_count}</td>
		            	</tr>
	            	</c:when>
	            	<c:otherwise>
	            		<tr align="center">
		                	<td><c:out value="合计"></c:out></td>  
		                	<td></td>
		                	<td>${data.lr_total}</td>
		                	<td>${data.sh_total}</td>
		                	<td>${data.qf_total}</td>
		                	<td>${data.dy_total}</td>
		                	<td>${data.dyhgz_total}</td>
		                	<td>${data.lq_total}</td>
		                	<td>${data.gd_total}</td>
		            	</tr>
	            	</c:otherwise>
	     		</c:choose>
            </c:forEach>
	</table>
</div>
</body>
</html>