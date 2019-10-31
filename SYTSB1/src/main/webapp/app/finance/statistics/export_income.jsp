<%@page import="net.sf.json.JSONObject"%>
<%@page import="java.math.BigDecimal"%>
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
	List<Map<String,Object>> rows = (List<Map<String,Object>>)map.get("rows");
	List<String> depts = (List<String>)map.get("depts");
	List<String> clsses1 = (List<String>)map.get("clsses");
	String item = (String)request.getSession().getAttribute("items");
	List<String> clsses = new ArrayList<String>();
	JSONObject items = new JSONObject();
	if(item!=null&&!"null".equals(item)){
		System.out.println("{\""+item.replace(",", "\":true,\"")+"\":true}");
		String itemS = "{\""+item.replace(",", "\":true,\"")+"\":true}"; 
		items = JSONObject.fromString(itemS);
		
		for(int i = 0;i<clsses1.size();i++){
			if(items.has(clsses1.get(i).toString())){
				clsses.add(clsses1.get(i).toString());
				
			}else{
				rows.remove(i);
			}
		}
	}else{
		for(int i = 0;i<clsses1.size();i++){
			
			clsses.add(clsses1.get(i).toString());
		}
	}
	String title = "按部门、开票类型统计收入";
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
     	winname.document.execCommand('saveas','','按经济类别财务报账费用统计.xls'); 
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
        	<td colspan="<%=clsses.size()+2%>">
        		<font size="3"><b><%=title %></b></font>
        	</td>
    	</tr>
    	<tr>
    		<td colspan="<%=clsses.size()+2%>" align="center">
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
				for(int i=0;i<clsses.size();i++){
					%>
					<td align="center"><%=clsses.get(i) %></td>
					<%
				}
				%>
				<td align="center">小计</td>
			</tr>
			<%
			BigDecimal d2 = new BigDecimal(Integer.toString(1));
			for(String dept : depts){
			%>
			<tr align="center">
				<td><%=dept%></td>  
				<%
				BigDecimal tt = new BigDecimal(0);
				//float tt = 0;
				for(String clss : clsses){
					boolean flag = true;
					for(Map<String,Object> row : rows){
						if(dept.equals(row.get("DEPT").toString()) 
									&& clss.equals(row.get("CLSS").toString())){
							String money = row.get("MONEY").toString();
							BigDecimal fee =  new BigDecimal(money);
							tt = tt.add(fee);
							/* float moneyf =  Float.parseFloat(money);
							tt += moneyf; */
							%>
							<td><%=fee.divide(d2,2,BigDecimal.ROUND_HALF_UP).toString()%></td>
							<%
							flag = false;
						}
					}
					if(flag){
						%>
						<td>0</td>
						<%
					}
				}
				%>
				<td><%=tt %></td>
			</tr>
			<%
			}
			%>
			<tr>
				<td align="center">合计</td>
				<%
				BigDecimal sum = new BigDecimal(0);
				//float sum = 0;
				for(String clss2 : clsses){
					BigDecimal ttt = new BigDecimal(0);
					//float ttt = 0;
					for(Map<String,Object> r : rows){
						if(clss2.equals(r.get("CLSS").toString())){
							BigDecimal fee =  new BigDecimal(r.get("MONEY").toString());
							ttt = ttt.add(fee);
							sum = sum.add(fee);
							/* float f =  Float.parseFloat(r.get("MONEY").toString());
							ttt += f;
							sum += f; */
						}
					}
					%>
						<td align="center"><%=ttt.divide(d2,2,BigDecimal.ROUND_HALF_UP).toString() %></td>
					<%
				}
				%>
				<td align="center"><%=sum.divide(d2,2,BigDecimal.ROUND_HALF_UP).toString() %></td>
		    </tr>
	</table>
</div>
</body>
</html>