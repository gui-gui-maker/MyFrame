<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
<title>历史记录</title>
<%@include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript" src="pub/bpm/js/util.js"></script>
<script type="text/javascript">
var invoiceGrid;
var pageStatus="${param.pageStatus}";
$("#invoice1").show();
$(function(){
	initGrid();
	queryRecords('${param.id}');
});
function initGrid() {
		invoiceGrid = $("#invoice").ligerGrid({
		columns:[
        	{ display: 'id', name: 'id', hide:true},
			{ display: 'business_id', name: 'business_id',hide:true},
			{ display: 'op_user_id', name: 'op_user_id',hide:true},
			{ display: '操作用户姓名', name: 'op_user_name', width:'15%'},
			{ display: '操作时间', name: 'op_time', width:'20%'},
			{ display: '操作动作', name: 'op_action',width:'20%'},
			{ display: '操作描述', name: 'op_remark',   width:'40%'}
			//{ display: '操作IP地址', name: 'op_ip', width:'15%'}
		],
		isScroll:true,
		height:$(window).height()-320,
		rownumbers: true,
		usePager: false,
		data: {Rows: []}
	});
}

//获取历史记录
function queryRecords(cId){
	$.ajax({
    	url: "cwInvoiceLead/lead/queryRecord.do?cId="+cId,
        type: "POST",
        success: function (resp) {
            if (resp.success) {
            	var list = resp.list;
            	if(list!=null&&list.length>0){
            		invoiceGrid.addRows(list); 
                	/* for(var i in list){
                		var record = {id : list[i].id,
                				business_id : list[i].business_id,
                				op_user_id : list[i].op_user_id,
                				op_user_name: list[i].op_user_name,
                				op_time : list[i].op_time,
                				op_action: list[i].op_action,
                				op_remark: list[i].op_remark,
                				op_ip: list[i].op_ip};
                		try{  
                			invoiceGrid.addRow(record); 
                		  }catch(err){  
                		    console.log(err.stack);  
                		  }  
                	} */
            	}
            }else{
            }
        },
        error: function (data,stats) {
            $.ligerDialog.error('提示：' + data.msg);
        }
    });
}
</script>
</head>
<body>
	<form id="form1" Action="cwInvoiceLead/lead/saveLy.do"  getAction="cwInvoicef/reg/detail.do?id=${param.id}">
	<input type="hidden" id="ids" />
	<input type="hidden" id="lead_id" name="lead_id" />
	<input type="hidden" id="lead_dep_id" name="lead_dep_id"/>
		<fieldset class="l-fieldset" id="pjgl">
					<legend class="l-legend">
						<div>
							交易信息 
						</div>
					</legend>
		<table border="1" cellpadding="3" cellspacing="0" width=""
				class="l-detail-table" >
					<tr>
					<td class="l-t-td-left">交易类型:</td>
						<td class="l-t-td-right"><u:combo name="moneyType" code="PAY_TYPE" /></td>
						<td class="l-t-td-left">发票金额:</td>
						<td class="l-t-td-right"><input type="text" ltype="text"  name="invoiceMoney" id="" /></td>
					</tr>
					<tr>
					<td class="l-t-td-left"> 发票类型</td>
					<td class="l-t-td-right" >
			      	  <u:combo name="invoiceType" ltype="radioGroup" code="TJY2_CW_FP_TYPE"/>
			        </td>
						<td class="l-t-td-left">开票单位:</td>
						<td class="l-t-td-right"><input type="text" ltype="text"  name="invoiceUnit" id="invoiceUnit" /></td>
					</tr>
					<tr>
						<td class="l-t-td-left">开票时间:</td>
						<td class="l-t-td-right"><input type="text" ltype="text"  name="invoiceDate" id="invoiceDate" /></td>
						<td class="l-t-td-left">受检单位:</td>
						<td class="l-t-td-right"><input type="text" ltype="text"  name="checkoutUnit" id="checkoutUnit" /></td>
						<!--<td class="l-t-td-left">检验部门:</td>
						  <td class="l-t-td-right"><input type="text" ltype="text"  name="checroutDep" id="checroutDep"/></td> -->
					</tr>
			</table>
		</fieldset>
		<fieldset class="l-fieldset" id="lygl">
					<legend class="l-legend">
						<div>
							领用信息 
						</div>
					</legend>
		<table border="1" cellpadding="3" cellspacing="0" width=""
				class="l-detail-table">
					<tr>
						<td class="l-t-td-left">领用人:</td>
						<td class="l-t-td-right" colspan="3"><input type="text" ltype="text" name="lead_name" id="lead_name" validate="{required:true,maxlength:50}" /></td>
						<td class="l-t-td-left">领用部门:</td>
						<td class="l-t-td-right" colspan="3"><input type="text" ltype="text" name="lead_dep" id="lead_dep" validate="{required:true}" /></td>
					<tr>
						<td class="l-t-td-left">领用发票号:</td>
						<td class="l-t-td-right" colspan="3"><input type="text" ltype="text" ligerui="{disabled:true}" id="lead_code" name="lead_code" /></td>
						<td class="l-t-td-left">领用总数:</td>
						<td class="l-t-td-right" colspan="3"><input type="text" ltype="text" ligerui="{disabled:true}" id="lead_num" name="lead_num" /></td>
					</tr>
					<tr>
						<td class="l-t-td-left">领用用途:</td>
						<td class="l-t-td-right" colspan="7" ><textarea name="lead_use" id="lead_use" rows="2" cols="25" class="l-textarea" validate="{maxlength:200}"></textarea></td>
					</tr>	
					</table>
		</fieldset>
		<fieldset class="l-fieldset" id="invoice1">
					<legend class="l-legend">
						<div>历史记录 </div>
					</legend>
					<div  id="invoice"></div>
		</fieldset>	
	</form>
</body>
</html>