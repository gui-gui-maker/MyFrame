<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd"); 
	String nowTime=""; 
	nowTime = dateFormat.format(new Date());%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
<title></title>
<%@include file="/k/kui-base-form.jsp"%>
 <link type="text/css" rel="stylesheet" href="app/finance/css/form_detail.css" />
</head>
<body>
     	<form id="formObj" action="removeAction/addRemove.do?status=${param.status}" getAction="">
		<input type="hidden" id="id" name="id" />
		<input type="hidden" id="fkEmployeeBaseId" name="fkEmployeeBaseId" value="${param.id}" />
		<table border="1" cellpadding="3" cellspacing="0" width=""
				class="l-detail-table">
				<tr>
				     <td class="l-t-td-left">执行时间</td>
					 <td class="l-t-td-right"><input type="text" ltype="date"  name="operateDate" value="<%=nowTime %>" /></td>
				</tr>
				<tr>
				     <td class="l-t-td-left">理由</td>
					 <td><textarea name="reason" rows="4" cols="25" class="l-textarea"></textarea></td>
				</tr>
		</table>
		</form>
</body>
</html>