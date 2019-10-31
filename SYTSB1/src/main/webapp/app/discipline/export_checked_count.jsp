<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.khnt.utils.DateUtil"%>
<%@page import="java.util.Date"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@include file="/k/kui-base-list.jsp" %>
<%
	//response.setHeader("Content-disposition","attachment; filename="+new String("设备信息统计表".getBytes("gb2312"), "ISO8859-1" )+".xls"); 
%>

<script type="text/javascript">
	$(function () {  
        
		exportToExcel();
	});

    //四舍五入num数字，V保留个数
    function decimal(num,v){
    	var vv = Math.pow(10,v);
    	return Math.round(num*vv)/vv;
    	}
	function exportToExcel() { 
		//获取table
        var tab = document.getElementById("table");
		
        $.getJSON(encodeURI("disciplinePlanAction/selectTj.do?startTime=${sessionScope.startTime}&endTime=${sessionScope.endTime}"),function(res){
			for(var i=0; i<res.data.length;i++){
        	var hj=parseInt(res.data[i].FCMY)+parseInt(res.data[i].MY)+parseInt(res.data[i].YB);
            var tr = document.createElement("tr");
            var td1 = document.createElement("td");
            var td2 = document.createElement("td");
            var td3 = document.createElement("td");
            var td4 = document.createElement("td");
            var td5 = document.createElement("td");
            var td6 = document.createElement("td");
            var td7 = document.createElement("td");
            var td8 = document.createElement("td");
            tr.appendChild(td1);
            tr.appendChild(td2);
            tr.appendChild(td3);
            tr.appendChild(td4);
            tr.appendChild(td5);
            tr.appendChild(td6);
            tr.appendChild(td7);
            tr.appendChild(td8);
            tab.appendChild(tr);
            td1.innerHTML = res.data[i].SJ;
            td2.innerHTML = hj;
            td3.innerHTML = res.data[i].FCMY;
            td4.innerHTML = decimal(parseInt(res.data[i].FCMY)/hj*100,2);
            td5.innerHTML = res.data[i].MY;
            td6.innerHTML = decimal(parseInt(res.data[i].MY)/hj*100,2);
            td7.innerHTML = res.data[i].YB;
            td8.innerHTML = decimal(parseInt(res.data[i].YB)/hj*100,2);
            
        	}
        });
		
		
		
		var winname = window.open('', '_blank', 'top=10000'); 
		var strHTML = document.all.tableExcel.innerHTML; 
  		winname.document.open('text/html', 'replace'); 
    	winname.document.writeln(strHTML); 
     	winname.document.execCommand('saveas','','${sessionScope.startTime}至${sessionScope.endTime}工作服务统计表.xls'); 
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
        		<font size="3"><b>${sessionScope.startTime}至${sessionScope.endTime}工作服务统计表</b></font>
        	</td>
    	</tr>
    	<tr>
    		<td colspan="8" align="center">
    			<c:if test="${sessionScope.startDate!=''}">
        			<c:out value="统计时间：${sessionScope.startTime}"></c:out>
        			<c:choose>
	        			<c:when test="${sessionScope.endTime!=''}">
	        				<c:out value="至 ${sessionScope.endTime}"></c:out>
	        			</c:when>
	        			<c:otherwise>
	        				<c:out value="至今"></c:out>
	        			</c:otherwise>
	        		</c:choose>
        		</c:if>
        		<c:if test="${'' eq sessionScope.startTime && '' eq sessionScope.endTime && '' eq sessionScope.minPrice && '' eq sessionScope.maxPrice}">
        			统计截止至<%=DateUtil.getDateTime("yyyy年MM月dd日",new Date())%>
        		</c:if>
    		</td>
    	</tr>
            <tr>
				<td rowspan="2" align="center">月份</td>
				<td rowspan="2" align="center">合计</td>
				<td colspan="2" align="center">非常满意</td>
				<td colspan="2" align="center">满意</td>
				<td colspan="2" align="center">一般</td>
			</tr>
			<tr>
				<td width="95" align="center">个数</td>
				<td width="95" align="center">百分比</td>
				<td width="95" align="center">个数</td>
				<td width="95" align="center">百分比</td>
				<td width="95" align="center">个数</td>
				<td width="95" align="center">百分比</td>
			</tr>
			<%-- <c:forEach items="${sessionScope.data}" var="data"  varStatus="status">
        		<c:set var="hj" value="${data.MY+data.FCMY+data.YB}"/>
	            		<tr align="center">
		                	<td><c:out value="${data.SJ}"></c:out></td>  
		                	<td>${hj}</td>
		                	<td>${data.FCMY}</td>
		                	<td>${data.fcmybfb} </td>
		                	<td>${data.MY}</td>
		                	<td>${data.mybfb}</td>
		                	<td>${data.YB}</td>
		                	<td>${data.ybbfb}</td>
		            	</tr>
	            	
            </c:forEach> --%>
	</table>
	
</div>
</body>
</html>