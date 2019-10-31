
<%@page import="org.apache.velocity.runtime.directive.Foreach"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.khnt.utils.DateUtil"%>
<%@page import="java.util.Date"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
	<head>
<%@include file="/k/kui-base-list.jsp" %>
<%-- 	<%@include file="/k/kui-base-list.jsp"%> --%>
<%
String title = "按部门、开票类型统计收入明细";
response.setHeader("Content-disposition","attachment; filename="+new String(title.getBytes("gb2312"), "ISO8859-1" )+".xls"); 
HashMap<String,Object> map = (HashMap<String,Object>)request.getSession().getAttribute("data");
List<Map<String,Object>> rows = (List<Map<String,Object>>)map.get("rows");
%>
	<script type="text/javascript" src="app/payment/change_money_info.js"></script>
<script type="text/javascript">
	$(function () {
		exportToExcel();
	});	
	function exportToExcel() {
		var winname = window.open('', '_blank', 'top=10000'); 
		var strHTML = document.all.tableExcel.innerHTML; 
  		winname.document.open('text/html', 'replace'); 
    	winname.document.writeln(strHTML); 
     	winname.document.execCommand('saveas','','按部门、开票类型统计收入明细.xls'); 
     	winname.close(); 
  		closeCurWin();
	}
	
	function closeCurWin() {
	    window.opener=null;
	    window.open('','_self');
	    window.close();
	}
</script>
</head>
	<body >
	<div id="tableExcel"> 
	<table width="" cellpadding="6" cellspacing="0" bgcolor="#cccccc" border="1" id="table">
    	<tr align="center">
        	<td colspan="13">
        		<font size="3"><b>按部门、开票类型统计收入</b></font>
        	</td>
    	</tr>
    	<tr>
    		<td colspan="13" align="center">
    			<c:if test="${param.startDate!=''}">
        			<c:out value="统计时间：${param.startDate}"></c:out>
        			<c:choose>
	        			<c:when test="${param.endDate!=''}">
	        				<c:out value="至 ${param.endDate}"></c:out>
	        			</c:when>
	        			<c:otherwise>
	        				<c:out value="至今"></c:out>
	        			</c:otherwise>
	        		</c:choose>
        		</c:if>
        		<c:if test="${'' eq param.startDate && '' eq param.endDate}">
        			统计截止至<%=DateUtil.getDateTime("yyyy年MM月dd日",new Date())%>
        		</c:if>
    		</td>
    	</tr>
    	<tr>
    		<td>票号</td><td>发票类型</td><td>开票名称</td><td>检验部门</td><td>合同号/编号</td><td>总金额</td>
    		<td>收费方式</td><td>现金</td><td>转账</td><td>POS</td><td>上缴财政</td><td>开票人</td><td>开票日期</td>
    		
    	</tr>
    	<%
		for(Map<String,Object> row : rows){
    	%>
    	<tr>
    	<td><%=row.get("INVOICE_NO") %></td>
    	<td><%=row.get("CLSS") %></td>
    	<td width="200px"><%=row.get("COMPANY_NAME") %></td>
    	<td><%=row.get("DEPT") %></td>
    	<td><%=row.get("PAY_NO") %></td>
    	<td><%=row.get("PAY_RECEIVED") %></td>
    	<td><%=row.get("PAY_TYPE") %></td>
    	<td><%=row.get("CASH_PAY") %></td>
    	<td><%=row.get("REMARK") %></td>
    	<td><%=row.get("POS") %></td>
    	<td><%=row.get("HAND_IN") %></td>
    	<td><%=row.get("RECEIVE_MAN_NAME") %></td>
    	<td><%=row.get("PAY_DATE") %></td>
    	
    	</tr>
    	
    	<%} %>
    	
    	</table>
	</body>
</html>