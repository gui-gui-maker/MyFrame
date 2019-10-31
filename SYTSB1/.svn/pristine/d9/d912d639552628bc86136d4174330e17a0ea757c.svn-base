<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<title>设备出库</title>
<%@ include file="/k/kui-base-form.jsp"%>
<%
	String status = request.getParameter("status");
%>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript" src="app/common/js/render.js"></script>
<script type="text/javascript" src="app/equipment/js/order.js"></script>
<script type="text/javascript">
	var pageStatus="${param.status}";
	
</script>
</head>
<body>
	<div title="出库信息" tabId="basicTab" style="height: 400px">
	<form name="formObj" id="formObj" method="post" getAction="equipOutstockRecordAction/detail.do?id=${param.id}">
	
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>记录详情</div>
			</legend>
			<table cellpadding="3" cellspacing="0" class="l-detail-table">
				<tr>
					<td class="l-t-td-left">出库类型</td>
					<td class="l-t-td-right"><u:combo name="outstock_type" code="TJY2_EQUIP_OUTSTOCK_TYPE" 
					validate="required:true" /></td>
					<td class="l-t-td-left">出库登记人</td>
					<td class="l-t-td-right">
						<input name="outstock_by" id="outstock_by" type="text" ltype='text' validate="{disabled:true,maxlength:32}" 
						validate="{required:false,maxlength:64}" />
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">借用/领用人</td>
					<td class="l-t-td-right">
						<input  ltype='text' id="borrow_draw_by" name="borrow_draw_by" type="text" id="Reviewers" validate="required:true" /></td>
						<!-- <input name="borrow_draw_by" id="borrow_draw_by" type="text" ltype='text' validate="{required:false,maxlength:64}"/> -->
					</td> 	
					<td class="l-t-td-left">借用/领用/配置时间</td>
					<td class="l-t-td-right">
						<input name="borrow_draw_date" id="borrow_draw_date" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}" /></td>
				</tr>
				<tr>	
					<td class="l-t-td-left">出库部门</td>
					<td class="l-t-td-right">
						<input  validate="{maxlength:50,required:true}" ltype="text"  name="outstock_position" id="outstock_position" type="text" /></td>
						<!-- <input name="outstock_position" id="outstock_position" type="text" ltype='text' validate="{required:false,maxlength:64}"/> -->
					</td> 	
					<td class="l-t-td-left">出库日期</td>
					<td class="l-t-td-right">
						<input name="outstock_date" id="outstock_date" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}" /></td>
				</tr>	
				<tr>
					<td class="l-t-td-left">归还日期</td>
					<td class="l-t-td-right">
						<input name="return_date" id="return_date" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}" /></td>
				</tr>
			</table>
		</fieldset>
		 <fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>出库设备信息</div>
			</legend>
			<div style="height:150px;" id="device">
				<qm:qm pageid="TJY2_EQ_INSTOCK_LIST" singleSelect="false"></qm:qm>
			</div>
		</fieldset> 
	</form>
	</div>
</body>
</html>
