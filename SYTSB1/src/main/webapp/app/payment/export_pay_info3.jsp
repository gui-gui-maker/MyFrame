<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/k/kui-base-list.jsp" %>
<%  
	String check_dep_name = request.getSession().getAttribute("check_dep_name").toString();
	String borrow_type = request.getSession().getAttribute("borrow_type").toString();
	String borrow_title = "1".equals(borrow_type)?"借发票":"借报告和发票";
	String title = check_dep_name + borrow_title;
	response.setHeader("Content-disposition","attachment; filename="+new String(title.getBytes("gb2312"), "ISO8859-1" )+".xls"); 
%>
<style type="text/css">
#table td{text-align:center;}
</style>
</head>
<body>
<div id="tableExcel"> 
	<table width="700" cellpadding="6" cellspacing="0" bgcolor="#cccccc" border="1" id="table">
    	<tr align="center">
        	<td colspan="8">
        		<font size="3"><b>${sessionScope.check_dep_name}<%=borrow_title %></b></font>
        	</td>
    	</tr>
        <tr>
			<td >序号</td>
			<td >发票日期</td>
			<td >发票号</td>
			<!-- <td >开票单位</td>-->
			<td >借票单位</td>
			<td >金额</td>
			<!-- <td >科室</td> -->
			<td >欠款人</td>
			<td >欠款人联系方式</td>
			<td >签字主任</td>
		</tr>
       	<c:forEach items="${sessionScope.data}" var="data" varStatus="status">
         	<tr  align="center">
            	<td>
	          		<c:out value="${status.index + 1}"></c:out>
              	</td>  
           		<td>${data.borrow_date}</td>
               	<td>${data.invoice_no}</td>
             	<td>${data.borrow_com_name}</td>
          		<td>${data.unpay_amount}</td>
          		<td>${data.borrow_name}</td>
          		<td>${data.contack_number}</td>
          		<td>${data.issue_name}</td>
        	</tr>
    	</c:forEach>
    	<tr>
			<td>合计</td>
			<td></td>
			<td></td>
			<td></td>
			<td>${total}</td>
			<td></td>
			<td></td>
			<td></td>
		</tr>
	</table>
</div>
</body>
</html>