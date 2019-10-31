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
	List<String> carIdS = (List<String>)map.get("carId");
	List<Map<String,Object>> rows = (List<Map<String,Object>>)map.get("rows");
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
	String title = "车辆费用统计表";
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
        	<td colspan="<%=clsses.size()+2%>">
        		<font size="3"><b><%=title%></b></font>
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
				<td align="center">车牌号</td>
				<%
				for(int i=0;i<clsses.size();i++){
					%>
					<td align="center"><%=clsses.get(i) %></td>
					<%
				}
				%>
				<td align="center">合计</td>
			</tr>
			<%
			BigDecimal d2 = new BigDecimal(Integer.toString(1));
			for(String carId : carIdS){
			%>
			<tr align="center">
				<td><%=carId%></td>  
				<%
				
				//float out_total = 0;
				BigDecimal out_total = new BigDecimal(0);
				for(String clss : clsses){
					boolean flag = true;
					for(Map<String,Object> row : rows){
						if(carId.equals(row.get("CAR_ID").toString()) 
									&& clss.equals(row.get("CLSS").toString())){
							String feeStr = row.get("MONEY").toString();
							BigDecimal fee =  new BigDecimal(feeStr);
							//out_total += fee;
							out_total = out_total.add(fee);
							//float fee =  Float.parseFloat(feeStr);
							//out_total += fee;
							%>
							<td><%=fee.divide(d2,2,BigDecimal.ROUND_HALF_UP).toString() %></td>
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
				<td align="center"><%=out_total.divide(d2,2,BigDecimal.ROUND_HALF_UP).toString()%></td>
			</tr>
			<%
			}
			%>
			<tr>
				<td align="center">合计</td>
				<%
				//BigDecimal d2 = new BigDecimal(Integer.toString(1));
				//float out_total_sum = 0;
				BigDecimal out_total_sum = new BigDecimal(0);
				for(String classOut : clsses){
					//float out_class_total = 0;
					BigDecimal out_class_total = new BigDecimal(0);
					for(Map<String,Object> r : rows){
						if(classOut.equals(r.get("CLSS").toString())){
							/* float f =  Float.parseFloat(r.get("MONEY").toString());
							
							out_class_total += f;
							out_total_sum += f; */
							
							BigDecimal fee =  new BigDecimal(r.get("MONEY").toString());
							//out_total += fee;
							out_class_total = out_class_total.add(fee);
							out_total_sum = out_total_sum.add(fee);
							
						}
					}
					%>
						<td align="center"><%=out_class_total.divide(d2,2,BigDecimal.ROUND_HALF_UP).toString()%></td>
					<%
				}
				
				%>
				<td align="center"><%=out_total_sum.divide(d2,2,BigDecimal.ROUND_HALF_UP).toString()%></td>
		    </tr>
	</table>
</div>
</body>
</html>