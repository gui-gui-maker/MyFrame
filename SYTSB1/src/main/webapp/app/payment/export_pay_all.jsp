<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.khnt.utils.DateUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/k/kui-base-list.jsp" %>
<%  
	response.setHeader("Content-disposition","attachment; filename="+new String("交账明细".getBytes("gb2312"), "ISO8859-1" )+".xls"); 
%>
<style type="text/css">
#table td{text-align:center;}
.xlsText{mso-number-format:"\@";} 
</style>
</head>
<body>
<div id="tableExcel">
	<table width="700" cellpadding="6" cellspacing="0" bgcolor="#cccccc" border="1" id="table">
    	<tr align="center">
        	<td colspan="10">
        		<font size="3"><b>交账明细</b></font>
        	</td>
    	</tr>
        <tr>
			<td rowspan="2">序号</td>
			<td rowspan="2">收费日期</td>
			<td rowspan="2">票号</td>
			<td rowspan="2">发票类型</td>
			<td rowspan="2">开票单位</td>
			<!-- 
			<td rowspan="2">使用单位</td>
			<td rowspan="2">是否打折</td>
			 -->
			<td colspan="5">金额</td>
			<td rowspan="2">科室</td>
		</tr>
		<tr class="trbg">
			<td>现金</td>
			<td>POS</td>
			<td>银行</td>
			<td>上缴财政</td>
			<td>承兑汇票</td>
		</tr>
       	<c:forEach items="${sessionScope.data}" var="data" varStatus="status">
         	<tr  align="center">
            	<td>
	          		<c:out value="${status.index + 1}"></c:out>
              	</td>  
           		<td>${data.pay_date}</td>
               	<td class="xlsText">${data.invoice_no}</td>
               	<td>
               		<c:choose>
	                	<c:when test="${'hand' eq data.invoice_type}">	<!-- hand：一般缴款书(手开) -->
	                		<c:out value="一般缴款书(手开)"></c:out>
	                	</c:when>
	                	<c:when test="${'machine' eq data.invoice_type}">	<!-- machine：一般缴款书(机打) -->
	                		<c:out value="一般缴款书(机打)"></c:out>
	                	</c:when>
	                	<c:when test="${'tax' eq data.invoice_type}">	<!-- tax：税票(专用) -->
		             		<c:out value="税票(专用)"></c:out>
	                	</c:when>
	                	<c:when test="${'ordinary' eq data.invoice_type}">	<!-- ordinary：税票(普通) -->
		             		<c:out value="税票(普通)"></c:out>
	                	</c:when>
	                	<c:when test="${'knot' eq data.invoice_type}">	<!-- knot：往来结算票(手开) -->
		             		<c:out value="往来结算票(手开)"></c:out>
	                	</c:when>
	                	<c:otherwise>
	                		<c:out value=""></c:out>
	                	</c:otherwise>
	           		</c:choose>
               	</td>
              	<td>${data.pay_com_name}</td>
              	<!-- 1：是 -->
              	<!-- 0：否 -->
             	<!-- 
             	<td>${data.com_name}</td>
             	<td>
             		<c:choose>               	
	                	<c:when test="${'1' eq data.isDiscount}">	
		             		<c:out value="是"></c:out>
	                	</c:when>
	               		<c:otherwise>	
	               			<c:out value="否"></c:out>
	             		</c:otherwise>
	           		</c:choose>
             	</td>
             	 -->
          		<c:choose>
                	<c:when test="${'1' eq data.pay_type}">	<!-- 1：现金 -->
	             		<td>${data.pay_received}</td>
	             		<td></td>
	             		<td></td>
	             		<td></td>
	             		<td></td>
                	</c:when>
                	<c:when test="${'2' eq data.pay_type}">	<!-- 2：银行转账 -->
                		<td></td>
                		<td></td>
                		<td>${data.pay_received}</td>
                		<td></td>
                		<td></td>
                	</c:when>
                	<c:when test="${'4' eq data.pay_type}">	<!-- 4：现金及转账 -->
	             		<td>${data.cash_pay}</td>
                		<td></td>
                		<td>${data.remark}</td>
                		<td></td>
                		<td></td>
                	</c:when>
                	<c:when test="${'5' eq data.pay_type}">	<!-- 5：POS机刷卡 -->
	             		<td></td>
	             		<td>${data.pay_received}</td>
	             		<td></td>
	             		<td></td>
	             		<td></td>
                	</c:when>
                	<c:when test="${'6' eq data.pay_type}">	<!-- 6：现金及POS机刷卡 -->
	             		<td>${data.cash_pay}</td>
	             		<td>${data.pos}</td>
	             		<td></td>
	             		<td></td>
	             		<td></td>
                	</c:when>
                	<c:when test="${'7' eq data.pay_type}">	<!-- 7：转账及POS机刷卡 -->
	             		<td></td>
	             		<td>${data.pos}</td>
	             		<td>${data.remark}</td>
	             		<td></td>
	             		<td></td>
                	</c:when>
                	<c:when test="${'8' eq data.pay_type}">	<!-- 8：上缴财政 -->
	             		<td></td>
	             		<td></td>
	             		<td></td>
	             		<td>${data.pay_received}</td>
	             		<td></td>
                	</c:when>
                	<c:when test="${'9' eq data.pay_type}">	<!-- 9：承兑汇票 -->
	             		<td></td>
	             		<td></td>
	             		<td></td>
	             		<td></td>
	             		<td>${data.pay_received}</td>
                	</c:when>
               		<c:otherwise>	<!-- 3：免收费 -->
                		<td></td>
                		<td></td>
                		<td></td>
                		<td></td>
                		<td></td>
             		</c:otherwise>
           		</c:choose>
            	<td>${data.check_department}</td>
        	</tr>
    	</c:forEach>
    	<tr>
			<td>合计</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${cashTotal}</td>
			<td>${posTotal}</td>
			<td>${transferTotal}</td>
			<td>${hand_inTotal}</td>
			<td>${draftTotal}</td>
			<td></td>
		</tr>
	</table>
</div>
</body>
</html>