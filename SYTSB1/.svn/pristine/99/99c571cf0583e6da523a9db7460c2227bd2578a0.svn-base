<%@page import="java.util.Set"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
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

	String[] type={"基本工资岗位工资","基本工资薪级工资",
	               "基本工资保留补贴","基本工资粮贴","基本工资补发项",
	               "基本工资独子","基本工资小计","绩效工资基础绩效每月","绩效工资基础绩效补发",
	               "绩效工资季度绩效","绩效工资补贴驻场补贴","绩效工资补贴其他",
	               "绩效工资小计","扣款项职业年金每月","扣款项职业年金补扣",
	               "扣款项医疗保险每月","扣款项医疗保险补发","扣款项失业每月","扣款项失业补发",
	               "扣款项公积金每月","扣款项公积金补发","扣款项工会经费","扣款项所得税","扣款项养老保险每月","扣款项养老保险补发","扣款项小计","应发合计"};
	 		
	String title = "车辆费用统计表";
	response.setHeader("Content-disposition","attachment; filename="+new String(title.getBytes("gb2312"), "ISO8859-1" )+".xls"); 
%>
<script type="text/javascript">
	
	function init(){
		
    	
	}

	$(function () {
		var data = api.data.exportData;
		/* alert(data)
		for (var i = 0; i < data.length; i++) {
			var dataa = data[i];
			var ttr = "<tr>"
			for ( var key in dataa) {
				ttr  = ttr + "<td>"+key+":"+dataa[key]+"</td>";
			}
			ttr  = ttr + "</tr>";
			$("#table").append(ttr);
		} */
		var winname = window.open('', '_blank', 'top=10000'); 
		//var strHTML = $("#grid").html(); 
  		winname.document.open('text/html', 'replace'); 
  		var table='<table  width="700" cellpadding="6" cellspacing="0" bgcolor="#cccccc" border="1" id="table">';
  		var type=['基本工资岗位工资','基本工资薪级工资','基本工资保留补贴','基本工资粮贴','基本工资补发项','基本工资独子','基本工资小计','绩效工资基础绩效每月','绩效工资基础绩效补发','绩效工资季度绩效','绩效工资补贴驻场补贴','绩效工资补贴其他','绩效工资小计','扣款项职业年金每月','扣款项职业年金补扣','扣款项医疗保险每月','扣款项医疗保险补发','扣款项失业每月','扣款项失业补发','扣款项公积金每月','扣款项公积金补发','扣款项工会经费','扣款项所得税','扣款项养老保险每月','扣款项养老保险补发','扣款项小计','应发合计']
 		
  		var thead = '<tr><td align="center">部门</td>';
  		for(var i=0;i<type.length;i++){
  			thead = thead + '<td align="center">'+type[i]+'</td>';
  		}
  		thead = thead + '</tr>';
		
  		table = table + thead;
  		//winname.document.writeln(strHTML); 
    	for (var i = 0; i < data.length; i++) {
			var dataa = data[i];
			var ttr = "<tr>"
			for ( var key in dataa) {
				if("nochanged"!=dataa[key]){
					ttr  = ttr + "<td>"+ dataa[key]+"</td>";
				}
				
			}
			ttr  = ttr + "</tr>";
			table = table + ttr;
			//$("#table").append(ttr);
		}
    	table = table + '</table>';
    	//winname.document.writeln(table); 
     	//winname.document.execCommand('saveas','','salary.xls'); 
		exportToExcel();
	});
	function exportToExcel(table) { 
		var winname = window.open('', '_blank', 'top=10000'); 
		//var strHTML = document.all.tableExcel.innerHTML; 
  		winname.document.open('text/html', 'replace'); 
    	winname.document.writeln(table); 
     	winname.document.execCommand('saveas','','<%=title%>.xls'); 
     	api.close(); 
  		//closeCurWin();
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
<body onload="init()">
<div id="tableExcel"> 
	<table width="700" cellpadding="6" cellspacing="0" bgcolor="#cccccc" border="1" id="table">
    	<tr align="center">
        	<td colspan="<%=type.length+2%>">
        		<font size="3"><b><%=title%></b></font>
        	</td>
    	</tr>
    	<%-- <tr>
    		<td colspan="<%=type.length+2%>" align="center">
    			<c:if test="${sessionScope.start!=''}">
        			<c:out value="统计时间：${sessionScope.start}"></c:out>
        			<c:choose>
	        			<c:when test="${sessionScope.end!=''}">
	        				<c:out value="至 ${sessionScope.end}"></c:out>
	        			</c:when>
	        			<c:otherwise>
	        				<c:out value="至今"></c:out>
	        			</c:otherwise>
	        		</c:choose>
        		</c:if>
        		<c:if test="${'' eq sessionScope.start && '' eq sessionScope.end}">
        			统计截止至<%=DateUtil.getDateTime("yyyy年MM月dd日",new Date())%>
        		</c:if>
    		</td>
    	</tr> --%>
            <tr>
				<td align="center">部门</td>
				<%
				for(int i=0;i<type.length;i++){
					%>
					<td align="center"><%=type[i] %></td>
					<%
				}
				%>
				<td align="center">合计</td>
			</tr>
			
	</table>
</div>
</body>
</html>