<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html  xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">

<title></title>
<%@include file="/k/kui-base-form.jsp"%>
</head>
<body>
	<form id="form1"  getAction="cwInvoiceLead/lead/detail.do?id=${param.id}">
	
	  <fieldset class="l-fieldset" >
					<legend class="l-legend">
						<div>
							发票管理 
						</div>
					</legend>
		<table border="1" cellpadding="3" cellspacing="0" width=""
				class="l-detail-table">
					<tr>
					<td class="l-t-td-left">交易类型:</td>
						<td class="l-t-td-right"><input type="text" ltype="text"  name="moneyType" /></td>
						<td class="l-t-td-left">发票金额:</td>
						<td class="l-t-td-right"><input type="text" ltype="text"  name="invoiceMoney" /></td>
					</tr>
					<tr>
						<td class="l-t-td-left">开票类型:</td>
						<td class="l-t-td-right"><input type="text" ltype="text"  name="invoiceType" /></td>
						<td class="l-t-td-left">开票单位:</td>
						<td class="l-t-td-right"><input type="text" ltype="text"  name="invoiceUnit" /></td>
					</tr>
					<tr>
						<td class="l-t-td-left">开票时间:</td>
						<td class="l-t-td-right"><input type="text" ltype="text"  name="invoiceDate" /></td>
						<td class="l-t-td-left">检验部门:</td>
						<td class="l-t-td-right"><input type="text" ltype="text"  name="checroutDep" /></td>
					</tr>
					<tr>
						<td class="l-t-td-left">受检单位:</td>
						<td class="l-t-td-right"><input type="text" ltype="text"  name="checroutUnit" /></td>
						
					</tr>
					</table>
		</fieldset>
	</form>
</body>
</html>