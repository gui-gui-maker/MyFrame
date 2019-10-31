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
	String tmo = request.getSession().getAttribute("tmo").toString();
 String year = request.getSession().getAttribute("year").toString();
	String title = "员工"+year+"年"+tmo+"月工资";
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
     	winname.document.execCommand('saveas','','（'+<%=title%>+'）.xls'); 
    // 	winname.close(); 
  		closeCurWin();
	}
	
	function closeCurWin() {
	    window.opener=null;
	    window.open('','_self');
	//    window.close();
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
        	<td colspan="32">
        		<font size="3"><b><%=title%></b></font>
        	</td>
    	</tr>
    	<tr>
    	<td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
    	<td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td colspan="2">单位：元 </td>
    	</tr>
            <tr>
				<td rowspan="3" Style="color: red;" align="center">序号</td>
				<td rowspan="3" align="center">姓名</td>
				<td rowspan="3" align="center">手机号</td>
				<td align="center" colspan="7" >基本工资</td>
				
				<td align="center" colspan="7">绩效工资及其他</td>
				
				<td align="center" colspan="14">扣款项</td>
				
				<td rowspan="3" align="center">应发合计</td>
			</tr>
			 <tr>
				<td rowspan="2" align="center">岗位<br>工资</td>
				<td rowspan="2" align="center">薪级<br />工资</td>
				<td rowspan="2" align="center">保留<br />补贴</td>
				<td rowspan="2" align="center">独子</td>
				<td rowspan="2" align="center">粮贴</td>
				<td rowspan="2" align="center">补发<br />项</td>
				<td rowspan="2" align="center">小计</td>
				<td colspan="2" align="center">基础绩效</td>
				<td rowspan="2" align="center">季度<br />绩效</td>
				<td colspan="2" align="center">补贴</td>
				<td rowspan="2" align="center">备用</td>
				<td  rowspan="2"align="center">小计</td>
				<td colspan="2" align="center">职业年金</td>
				<td colspan="2"  align="center">医疗保险</td>
				<td colspan="2"  align="center">养老保险</td>
				<td colspan="2" align="center">失业</td>
				<td colspan="2" align="center">公积金</td>
				<td rowspan="2" align="center">工会经费</td>
				<td rowspan="2" align="center">所得税</td>
				<td rowspan="2" align="center">备用</td>
				<td rowspan="2"rowspan="2" align="center">小计</td>
			</tr>
			 <tr>
				<td align="center">每月</td>
				<td align="center">补发</td>
				<td align="center">驻场补贴</td>
				<td align="center">其他</td>
				<td align="center">每月</td>
				<td align="center">补扣</td>
				<td align="center">每月</td>
				<td align="center">补扣</td>
				<td align="center">每月</td>
				<td align="center">补扣</td>
				<td align="center">每月</td>
				<td align="center">补发</td>
				<td align="center">每扣</td>
				<td align="center">补扣</td>
			</tr>
			<% int add=1;
			%>
			<c:forEach items="${sessionScope.data}" var="data">
				<c:choose>
	            	<c:when test="${'' != data.name && null != data.name}">
	            		<tr align="center">
		                	<td><c:out value="<%=add %>"></c:out></td>  
		                	<%add=add+1; %>
		                	<td>${data.name}</td>
		                	<td>${data.telePhone}</td>
		                	<td>${data.jbgzGwgz}</td>
		                	<td>${data.jbgzXjgz}</td>
		                	<td>${data.jbgzBlbt}</td>
		                	<td>${data.jbgzDz}</td>
		                	<td>${data.jbgzLt}</td>
		                	<td>${data.jbgzBfx}</td>
		                	<td>${data.jbgzXj}</td>
		                	<td>${data.jxgzJcjxMy}</td>
		                	<td>${data.jxgzJcjxBf}</td>
		                	<td>${data.jxgzJdjx}</td>
		                	<td>${data.jxgzBtZcbt}</td>
		                	<td>${data.jxgzBtQt}</td>
		                	<td>${data.jxgzBy}</td>
		                	<td>${data.jxgzXj}</td>
		                	<td>${data.kkxZynjMy}</td>
		                	<td>${data.kkxZynjBk}</td>
		                	<td>${data.kkxYlbxMy}</td>
		                	<td>${data.kkxYlbxBf}</td>
		                	<td>${data.kkxOldbxMy}</td>
		                	<td>${data.kkxOldbxBf}</td>
		                	<td>${data.kkxSyMy}</td>
		                	<td>${data.kkxSyBf}</td>
		                	<td>${data.kkxGjjMy}</td>
		                	<td>${data.kkxGjjBf}</td>
		                	<td>${data.kkxGhjf}</td>
		                	<td>${data.kkxSds}</td>
		                	<td>${data.kkxBy}</td>
		                	<td>${data.kkxXj}</td>
		                	<td>${data.fsalary}</td>
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