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
     	winname.document.execCommand('saveas','','机电六部（${sessionScope.equipmentVariety_name}）已打印的定检、监检、委检报告统计表.xls'); 
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
    		<c:if test='${sessionScope.equipmentVariety=="4000"}'>
    			<td colspan="5">
        		<font size="3"><b>机电六部（${sessionScope.equipmentVariety_name}）已打印的定检、监检、委检报告统计表</b></font>
        	</td>
    		</c:if>
        	<c:if test='${sessionScope.equipmentVariety == "5000" || sessionScope.equipmentVariety == "6000"}'>
    			<td colspan="4">
        		<font size="3"><b>机电六部（${sessionScope.equipmentVariety_name}）已打印的定检、监检、委检报告统计表</b></font>
        	</td>
    		</c:if>
    		<c:if test='${sessionScope.equipmentVariety=="9000"}'>
    			<td colspan="3">
        		<font size="3"><b>机电六部（${sessionScope.equipmentVariety_name}）已打印的定检、监检、委检报告统计表</b></font>
        	</td>
    		</c:if>
    	</tr>
    	<tr>
    		<c:if test='${sessionScope.equipmentVariety=="4000"}'>
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
    		</c:if>
    		<c:if test='${sessionScope.equipmentVariety == "5000" || sessionScope.equipmentVariety == "6000"}'>
    			<td colspan="4" align="center">
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
    		</c:if>
    		<c:if test='${sessionScope.equipmentVariety=="9000"}'>
    			<td colspan="3" align="center">
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
    		</c:if>
    	</tr>
            <tr>
				<c:if test='${sessionScope.equipmentVariety=="4000"}'>
					<td rowspan="2" align="center" style="width: 30%;">起重机类别</td>
					<td colspan="3" align="center" style="width: 50%;">机电六部</td>
				</c:if>
				<c:if test='${sessionScope.equipmentVariety=="5000"}'>
					<td rowspan="2" align="center" style="width: 40%;">场（厂）内专用机动车辆</td>
					<td colspan="2" align="center" style="width: 40%;">机电六部</td>
				</c:if>
				<c:if test='${sessionScope.equipmentVariety=="6000"}'>
					<td rowspan="2" align="center" style="width: 40%;">大型游乐设施</td>
					<td colspan="2" align="center" style="width: 40%;">机电六部</td>
				</c:if>
				<c:if test='${sessionScope.equipmentVariety=="9000"}'>
					<td rowspan="2" align="center" style="width: 40%;">客运索道</td>
					<td colspan="1" align="center" style="width: 40%;">机电六部</td>
				</c:if>
				<td rowspan="2" align="center" style="width: 20%;">合计</td>
			</tr>
			<tr>
				<c:if test='${sessionScope.equipmentVariety=="4000"}'>
					<td width="95" align="center">定期检验</td>
					<td width="95" align="center">监督检验</td>
					<td width="95" align="center">委托检验</td>
				</c:if>
				<c:if test='${sessionScope.equipmentVariety=="5000"}'>
					<td width="95" align="center">监督检验</td>
					<td width="95" align="center">委托检验</td>
				</c:if>
				<c:if test='${sessionScope.equipmentVariety=="6000"}'>
					<td width="95" align="center">定期检验</td>
					<td width="95" align="center">监督检验</td>
				</c:if>
				<c:if test='${sessionScope.equipmentVariety=="9000"}'>
					<td width="95" align="center">定期检验</td>
				</c:if>
			</tr>
			<c:forEach items="${sessionScope.data}" var="data">
	            <tr align="center">
		                	<td><c:out value="${data.device_name}"></c:out></td>  
		                	
		                	<c:if test='${sessionScope.equipmentVariety=="4000"}'>
								<td>${data.jd6_dj_count}</td>
								<td>${data.jd6_jj_count}</td>
								<td>${data.jd6_wj_count}</td>
							</c:if>
							<c:if test='${sessionScope.equipmentVariety=="5000"}'>
								<td>${data.jd6_jj_count}</td>
								<td>${data.jd6_wj_count}</td>
							</c:if>
							<c:if test='${sessionScope.equipmentVariety=="6000"}'>
								<td>${data.jd6_dj_count}</td>
								<td>${data.jd6_jj_count}</td>
							</c:if>
							<c:if test='${sessionScope.equipmentVariety=="9000"}'>
								<td>${data.jd6_dj_count}</td>
							</c:if>
		                	<td>${data.total}</td>
		    	</tr>	            	
            </c:forEach>
	</table>
</div>
</body>
</html>