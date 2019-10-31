
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
String title = "按经济类别财务报账费用统计明细";
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
     	winname.document.execCommand('saveas','','按经济类别财务报账费用统计明细.xls'); 
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
        	<td colspan="9">
        		<font size="3"><b>按经济类别财务报账费用统计明细</b></font>
        	</td>
    	</tr>
    	<tr>
    		<td colspan="9" align="center">
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
    		<td>编号</td><td>费用类型</td><td>报销人</td><td>报销日期</td><td>单位</td><td>部门</td>
    		<td>金额</td><td>处理人</td><td>处理时间</td>
    	</tr>
    	<%
		for(Map<String,Object> row : rows){
    	%>
    	<tr>
    	<td><%=row.get("IDENTIFIER") %></td>
    	<td><%=row.get("CLSS") %></td>
    	<td width="200px"><%=row.get("PEOPLECONCERNDE") %></td>
    	<td><%=row.get("BSDATE") %></td>
    	<td><%=row.get("UNIT") %></td>
    	<td><%=row.get("DEPARTMENT") %></td>
    	<td><%=row.get("MONEY") %></td>
    	<td><%=row.get("HANDLE_NAME") %></td>
    	<td><%=row.get("HANDLE_TIME") %></td>
    	</tr>
    	
    	<%} %>
    	
    	</table>
	</body>
</html>