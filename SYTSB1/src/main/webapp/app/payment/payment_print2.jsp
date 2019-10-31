<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.khnt.utils.DateUtil"%>
<%@page import="java.util.Date"%>
<%@page import="com.scts.payment.bean.InspectionPayInfo"%>
<%@page import="java.util.List"%>
<%@page import="com.scts.payment.bean.InspectionInfoDTO"%>
<%@page import="com.lsts.finance.bean.CwBankDTO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="app/common/js/windowprint.js"></script>
<script type="text/javascript" src="app/common/lodop/LodopFuncs.js"></script>
<style type="text/css">
	html,body,form{
		margin:0;
		padding:0;
		height:100%;
	}
	#printPaymentDiv{
		height:100%;
	} 
	table{
		border:none;
		width:98%;
		margin:1% 1%;
		text-align:center;
		
	}
	table tr td{
		border:1px #ccc solid;
		min-height:30px;
	}
</style>
<script type="text/javascript">
	$(function() {
		$("#form1").initForm({ //参数设置示例
			toolbar : [ {
				text : '打印',
				id : 'print',
				icon : 'print',
				click : print
			}, {
				text : '关闭',
				id : 'close',
				icon : 'cancel',
				click : function(){
					api.close();
				}
			} ],
			getSuccess : function(res) {
			
			}
		});
		$("body").mask("正在加载......");
		$.ajax({
		          url: "payment/payInfo/getPaymentSheet.do",
		          type: "POST",
		          datatype: "json",
		          contentType: "application/json; charset=utf-8",
		          data: $.ligerui.toJSON({id:'${param.id}'}),
		          success: function (data, stats) {
		              $("body").unmask();
		              if (data["success"]) {
		              	if(data.data){
		  					appendData(data.data,data.signInfo);
		  				}
		              }else{
		                  $.ligerDialog.error('提示：' + data.msg);
		              }
		          },
		          error: function (data,stats) {
		              $("body").unmask();
		              $.ligerDialog.error('提示：' + data.msg);
		          }
		      });
	})
	function print(){
		
	}
function appendData(data,sign){
	var $table = $("<table>");
  	var rows = data["inspectionInfoDTOList"];
	$table.append(
		'<tr>'+
			'<td class="lb">受检单位</td><td>'+rows[0].com_name+'</td>'+
			'<td class="lb">编号</td><td>'+data.pay_no+'</td>'+
		'</tr>'+	
		'<tr>'+
			'<td class="lb">开票单位</td><td>'+data.company_name+'</td>'+
			'<td class="lb">检验部门</td><td>'+rows[0].check_department+'</td>'+
		'</tr>'+	
		'<tr>'+
			'<td>序号</td><td>报告/证书编号</td>'+
			'<td>设备注册代码</td><td>检验费（元）/台</td>'+
		'</tr>'
    );
  
    for(var i=0,len = rows.length;i<len;i++){
	   	$table.append(
			'<tr>'+
				'<td>'+(i+1)+'</td>'+
				'<td>'+rows[i].report_sn+'</td>'+
				'<td>'+rows[i].device_registration_code+'</td>'+
				'<td>'+rows[i].advance_fees+'</td>'+
			'</tr>'	
	   	);
    }
	$table.append(
		'<tr>'+
			'<td>合计:共'+len+'份报告</td>'+
			'<td></td>'+
			'<td></td>'+
			'<td>'+data.pay_received+'</td>'+
		'</tr>'	
   	);
   	$table.append(
		'<tr>'+
			'<td>发票编号</td>'+
			'<td colspan="3">'+data.invoice_no+'</td>'+
		'</tr>'	
   	);
   	if(data.pay_type=='1'){
   		$table.append(
			'<tr>'+
				'<td>现金</td>'+
				'<td colspan="3">'+data.pay_received+'</td>'+
			'</tr>'	
	   	);
   	}else if(data.pay_type=='2'){
   		$table.append(
			'<tr>'+
				'<td>转账</td>'+
				'<td colspan="3">'+data.remark+'</td>'+
			'</tr>'	
	   	);
   	}else if(data.pay_type=='4'){
   		$table.append(
			'<tr>'+
				'<td>现金</td>'+
				'<td colspan="3">'+data.cash_pay+'</td>'+
			'</tr>'+	
			'<tr>'+
				'<td>转账</td>'+
				'<td colspan="3">'+data.remark+'</td>'+
			'</tr>'	
	   	);
   	}else if(data.pay_type=='5'){
   		$table.append(
			'<tr>'+
				'<td>POS</td>'+
				'<td colspan="3">'+data.pay_received+'</td>'+
			'</tr>'
	   	);
   	}else if(data.pay_type=='6'){
   		$table.append(
   			'<tr>'+
				'<td>现金</td>'+
				'<td colspan="3">'+data.cash_pay+'</td>'+
			'</tr>'+
			'<tr>'+
				'<td>POS</td>'+
				'<td colspan="3">'+data.pos+'</td>'+
			'</tr>'
	   	);
   	}else if(data.pay_type=='7'){
   		$table.append(
   			
			'<tr>'+
				'<td>POS</td>'+
				'<td colspan="3">'+data.pos+'</td>'+
			'</tr>'+
			'<tr>'+
				'<td>转账</td>'+
				'<td colspan="3">'+data.remark+'</td>'+
			'</tr>'
	   	);
	   	
   	}
   	/*$table.append(
		'<tr align="right">'+
			'<td colspan="4">'+rows[0].check_department+new Date().getDate()+'</td>'+
		'</tr>'
   	);*/
   	if(sign){
   		if(sign.base64_text==null){
   			$table.append(
   	   				'<tr>'+
   						'<td>部门负责人</td>'+
   						'<td>'+sign.dept_head+'</td>'+
   						'<td>检验人员</td>'+
   						'<td>'+sign.checker+'</td>'+
   					'</tr>'+
   	   				'<tr>'+
   						'<td>经办人</td>'+
   						'<td>'+sign.brokerage+'</td>'+
   						'<td>电话</td>'+
   						'<td>'+sign.brokerage_tel+'</td>'+
   					'</tr>'+
   	   				'<tr>'+
   	   					'<td>签字</td>'+
   	   					'<td colspan="3"><img style=\"width:600px;\" src="'+sign.base64_sign_file+'"></td>'+
   	   				'</tr>'+
   	   				'<tr>'+
   	   					'<td>日期</td>'+
   	   					'<td colspan="3">'+sign.sign_date+'</td>'+
   	   				'</tr>'
   	   		   	);
   		}else{
   			$table.append(
   	   				'<tr>'+
   						'<td>部门负责人</td>'+
   						'<td>'+sign.dept_head+'</td>'+
   						'<td>检验人员</td>'+
   						'<td>'+sign.checker+'</td>'+
   					'</tr>'+
   	   				'<tr>'+
   						'<td>经办人</td>'+
   						'<td>'+sign.brokerage+'</td>'+
   						'<td>电话</td>'+
   						'<td>'+sign.brokerage_tel+'</td>'+
   					'</tr>'+
   	   				'<tr>'+
   	   					'<td>签字</td>'+
   	   					'<td colspan="3"><img style=\"width:600px;\" src="'+sign.base64_text+'"></td>'+
   	   				'</tr>'+
   	   				'<tr>'+
   	   					'<td>日期</td>'+
   	   					'<td colspan="3">'+sign.sign_date+'</td>'+
   	   				'</tr>'
   	   		   	);
   		}
   		
   	}
   	$("#printPaymentDiv").empty().append($table);
}	

</script>
</head>
<body >
<form id="form1">
	<div style="overflow:auto;" id='printPaymentDiv'>
	
	</div>
</form>
</body>
</html>