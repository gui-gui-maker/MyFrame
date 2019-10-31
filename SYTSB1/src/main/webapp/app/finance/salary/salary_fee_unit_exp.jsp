<%@page import="com.khnt.utils.StringUtil"%>
<%@page import="com.lsts.report.bean.SysOrg"%>
<%@page import="net.sf.json.JSONObject"%>
<%@page import="java.math.BigDecimal"%>
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
	HashMap<String,Object> map = (HashMap<String,Object>)request.getSession().getAttribute("data");
	List<Object[]> rows = (List<Object[]>)map.get("data");
	List<Object[]> sum = (List<Object[]>)map.get("sum");
	List<String> classs = (List<String>)map.get("classs");
	String[] type={"基本工资岗位工资","基本工资薪级工资",
            "基本工资保留补贴","基本工资粮贴","基本工资补发项",
            "基本工资独子","基本工资小计","绩效工资基础绩效每月","绩效工资基础绩效补发",
            "绩效工资季度绩效","绩效工资补贴驻场补贴","绩效工资补贴其他",
            "绩效工资小计","扣款项职业年金每月","扣款项职业年金补扣",
            "扣款项医疗保险每月","扣款项医疗保险补发","扣款项失业每月","扣款项失业补发",
            "扣款项公积金每月","扣款项公积金补发","扣款项工会经费","扣款项所得税","扣款项养老保险每月","扣款项养老保险补发","扣款项小计","应发合计"};
	String item = (String)request.getSession().getAttribute("items");
	List<String> typeo = new ArrayList<String>();
	JSONObject items = new JSONObject();
	if(item!=null&&!"null".equals(item)&&StringUtil.isNotEmpty(item)){
		System.out.println("{\""+item.replace(",", "\":true,\"")+"\":true}");
		String itemS = "{\""+item.replace(",", "\":true,\"")+"\":true}"; 
		items = JSONObject.fromString(itemS);
		
		for(int i = 0;i<type.length;i++){
			if(items.has(type[i].toString())){
					typeo.add(type[i].toString());
				
			}/* else{
				classs.remove(i);
			} */
		}
	}else{
		for(int i = 0;i<type.length;i++){
			
				typeo.add(type[i].toString());
		}
	}
	System.out.println("----------"+typeo);
	String title = "部门工资统计表";
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
     	winname.document.execCommand('saveas','','<%=title%>.xls'); 
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
        	<td colspan="<%=typeo.size()+1%>">
        		<font size="3"><b><%=title%></b></font>
        	</td>
    	</tr>
    	<tr>
    		<td colspan="<%=typeo.size()+1%>" align="center">
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
    	</tr>
            <tr>
				<td align="center">部门</td>
				<%
				for(int i=0;i<typeo.size();i++){
					%>
					<td align="center"><%=typeo.get(i).toString() %></td>
					<%
				}
				%>
			</tr>
		<%
		
		int i = 0;
		 BigDecimal d2 = new BigDecimal(Integer.toString(1));
		for(Object[] row : rows){
		%>
			<tr align="center">
				<%-- <td><%=classs.get(i) %></td> --%>
				<%
					i++;
				BigDecimal out_total = new BigDecimal(0);
						Object [] obj = row;
				for(int j=0;j<obj.length-1;j++){
					if(j==0){
						%>
						<td><%=obj[0].toString()%></td>
						<%
					}else{
						
						BigDecimal fee =  new BigDecimal(obj[j].toString());
							//out_total += fee;
							out_total = out_total.add(fee);
							%>
							<td><%=fee.divide(d2,2,BigDecimal.ROUND_HALF_UP).toString()%></td>
				<%}
				}
				%>
				<%-- <td align="center"><%= out_total.divide(d2,2,BigDecimal.ROUND_HALF_UP).toString()%></td> --%>
			</tr>
			<%
			}
			%>
		<%
		BigDecimal out_total_sum = new BigDecimal(0);

		for(Object[] row : sum){
		%>
			<tr align="center">
				<!-- <td>合计</td> -->
				<%
						Object [] obj = row;
				for(int j=0;j<obj.length;j++){
					if(j==0){
						%>
						<td><%=obj[0].toString()%></td>
						<%
					}else{
						
					BigDecimal fee =  new BigDecimal(obj[j].toString());

							out_total_sum = out_total_sum.add(fee);
							%>
							<td><%=fee.divide(d2,2,BigDecimal.ROUND_HALF_UP).toString() %></td>
				<%}
				}
				%>
				<%-- <td align="center"><%=out_total_sum.divide(d2,2,BigDecimal.ROUND_HALF_UP).toString()%></td> --%>
			</tr>
			<%
			}
			%>	
	</table>
</div>
</body>
</html>