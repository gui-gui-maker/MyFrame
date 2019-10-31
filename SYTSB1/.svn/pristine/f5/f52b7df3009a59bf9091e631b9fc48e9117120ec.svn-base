<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://bpm.khnt.com" prefix="bpm" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
<%@include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
$(function(){
	$("#form1").initForm({
		toolbar: [{text: "保存", icon: "save", click: saveData},
				  {text: "关闭", icon: "cancel", click: function(){
					api.close();
				}
			}
		], toolbarPosition: "bottom"
	});	
}); 
function saveData(){
	 var formData = $("#form1").getValues();
	if ($("#form1").validate().form()) {
		$("body").mask("正在保存...");
		$.ajax({
    		url : 'cwInvoicef/reg/saveF.do',
    		type : "POST",
    		dataType : "json",
    		contentType : "application/json; charset=utf-8",
    		data: $.ligerui.toJSON(formData),
    		success : function(data, stats) {
    			$("body").unmask();
    			if(data.success){
    				top.$.dialog.notice('保存成功！');
    				api.data.window.Qm.refreshGrid();
    				api.close();
    			} else {
    				$.ligerDialog.error('保存失败！');
    			}
    		},
    		error : function(data) {
    			$("body").unmask();
    			$.ligerDialog.error('保存失败！');
    		}
    	});
	}else{
		 return;
	}
}
</script>
</head>
<body>
	<form id="form1" Action="cwInvoicef/reg/saveF.do"  getAction="cwInvoicef/reg/detail.do?id=${param.id}">
	<input type="hidden" id="id" name="id" /><!-- 主键 -->
	<input type="hidden" id="pkInvoiceId" name="pkInvoiceId" /><!-- 发票入库登记表ID -->
	<input type="hidden" id="checkoutDep" name="checkoutDep"/><!-- 检验部门 -->
	<input type="hidden" id="pk_lead_id" name="pk_lead_id"/><!-- 领用记录ID -->
	<input type="hidden" id="fkBusinessId" name="fkBusinessId"/><!-- 开票业务ID -->
	<input type="hidden" id="invoice_id" name="invoice_id"/><!-- 开票人ID -->
	<input type="hidden" id="invoice_name" name="invoice_name"/><!-- 开票人 -->
	<input type="hidden" id="lead_id" name="lead_id"/><!-- 领用人ID -->
	<input type="hidden" id="lead_name" name="lead_name"/><!-- 领用人 -->
	<input type="hidden" id="lead_date" name="lead_date"/><!-- 领用时间 -->
		<fieldset class="l-fieldset" id="pjgl">
					<legend class="l-legend">
						<div>
							交易信息 
						</div>
					</legend>
		<table border="1" cellpadding="3" cellspacing="0" width=""
				class="l-detail-table" >
					<tr>
				        <td class="l-t-td-left"> 发票编号:</td>
				        <td class="l-t-td-right"> 
				        <input name="invoiceCode" type="text" ltype='text' ligerui="{disabled:true}" validate="{required:true,maxlength:100}"/>
				        </td>
				        <td class="l-t-td-left"> 发票类型</td>
						<td class="l-t-td-right" ><u:combo name="invoiceType" code="TJY2_CW_FP_TYPE" attribute="disabled:'true'"/>
			        	</td>
					</tr>
					<tr>
					<td class="l-t-td-left">发票金额:</td>
						<td class="l-t-td-right"><input type="text" ltype="text"  name="invoiceMoney" id="" /></td>
					<td class="l-t-td-left">交易类型:</td>
						<td class="l-t-td-right"><u:combo name="moneyType" code="PAY_TYPE" /></td>
						
					</tr>
					<tr>
						<td class="l-t-td-left">开票单位:</td>
						<td class="l-t-td-right"><input type="text" ltype="text"  name="invoiceUnit" id="invoiceUnit" /></td>
						<td class="l-t-td-left">开票时间:</td>
						<td class="l-t-td-right"><input type="text" ltype="date"  name="invoiceDate" id="invoiceDate" ligerui="{format:'yyyy-MM-dd'}"/></td>
					</tr>
					<tr>
						<td class="l-t-td-left">受检单位:</td>
						<td class="l-t-td-right"><input type="text" ltype="text"  name="checkoutUnit" id="checkoutUnit" /></td>
						<td class="l-t-td-left">状态:</td>
						  <td class="l-t-td-right"><u:combo name="status" code="TJY2_CW_FP_STATUS" /></td>
					</tr>
					<tr>
						<td class="l-t-td-left">备注</td>
						<td class="l-t-td-right" colspan="3">
							<textarea name="remark" rows="3" cols="25" class="l-textarea" validate="{maxlength:1000}"></textarea>
						</td>	
					</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>