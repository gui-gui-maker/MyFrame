<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.lsts.report.bean.ReportDraw" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
$(function () {	
	$("#form1").initForm({
	    success: function (response) {//处理成功
	    		if (response.success) {
	            	top.$.dialog.notice({
	             		content: "保存成功！"
	            	});
	         		api.data.window.refreshGrid();
	            	//api.close();
	    		} else {
	           		$.ligerDialog.error('保存失败！<br/>' + response.msg);
	      		}
		}, getSuccess:function(response){
			if("3" == response.flag){
				document.getElementById("drawRecords").style.display = "block";
				$("#pulldown_op").val(response.reportDraw.pulldown_op);
				$("#pulldown_time").val(response.reportDraw.pulldown_time);
			}else{
				document.getElementById("drawRecords").style.display = "none";
			}
	    }, toolbar: [
				{
					text: "关闭", icon: "cancel", click: function(){
						api.close();
					}
				}
			], toolbarPosition: "bottom"
	});
});

function closewindow(){
	api.close();
}
	</script>
<body>		
	<form id="form1" action="" getAction="report/query/detail.do?id=${param.id}">
		<fieldset class="l-fieldset">
		<legend class="l-legend"><div>业务详情</div></legend>
		<table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">          
			<tr>     
		        <td class="l-t-td-left">检验类型:</td>
		        <td class="l-t-td-right"><u:combo name="inspection.check_type" code="BASE_CHECK_TYPE" validate="required:true"/></td>        
		        <td class="l-t-td-left">业务流水号:</td>
		        <td class="l-t-td-right"><input name="sn" type="text" ltype='text' /> 
		        </td>
       		</tr>
       		<tr> 
         		<td class="l-t-td-left">受检单位:</td>
        		<td class="l-t-td-right"><input name="inspection.com_name" type="text" ltype='text'  /> </td>
		        <td class="l-t-td-left">设备类别:</td>
		        <td class="l-t-td-right">
	        		<input type="text" id="deviceDocument.device_sort_code" name="deviceDocument.device_sort_code" ltype="select" validate="{required:true}"  ligerui="{
						//explain:'sdasdfasdfsadf',
						//valueField:'device_sort_code',
						//value:'3000',
						selectBoxHeight:400,
						initValue:'3000',
						readonly:true,
						tree:{checkbox:false,data: <u:dict sql="select t.id,t.code_table_values_id pid,t.value code,t.name text from PUB_CODE_TABLE_VALUES t,pub_code_table t1 where t.code_table_id=t1.id and   t1.code = 'device_classify' and t.value  like '${param.device_type}%'"/>}
						//disabled:true,
						//data: <u:dict sql="select t.id,t.code_table_values_id pid,t.value code,t.name text from PUB_CODE_TABLE_VALUES t,pub_code_table t1 where t.code_table_id=t1.id and   t1.code = 'device_classify' and t.value  like '${param.device_type}%'"/>,
						//url:'app/demo/zz_select_date.txt',
						//suffix:'后缀很长，很长，不是吗',
						//suffixWidth:'140'
						}"/>
        		</td>
	       </tr>
	       <tr> 
		        <td class="l-t-td-left">设备名称:</td>
		        <td class="l-t-td-right"><input name="deviceDocument.device_name" type="text" ltype='text' id="deviceDocument.device_name" /></td>
		        <td class="l-t-td-left">应收金额:</td>
		        <td class="l-t-td-right"><input name="advance_fees" type="text" ltype='text'  /> 
		        </td>
       		</tr>
       		<tr>
       			<td class="l-t-td-left">检验部门：</td>
        		<td class="l-t-td-right"><input name="check_unit_id" type="text" ltype='text' id="check_unit_id" /></td>
       			<td class="l-t-td-left">检验日期：</td>
        		<td class="l-t-td-right"><input name="advance_time" type="text" ltype='date' ligerui="{initValue:'',format:'yyyy-MM-dd'}" /> 
        		</td>
       		</tr>
       		<tr> 
		        <td class="l-t-td-left">检验联系人:</td>
		        <td class="l-t-td-right"><input name="check_op" type="text" ltype='text' id="check_op" /></td>
		        <td class="l-t-td-left">联系人电话:</td>
		        <td class="l-t-td-right"><input name="check_tel" type="text" ltype='text'  /> 
		        </td>
       		</tr>
       		<tr> 
		        <td class="l-t-td-left">项目负责人:</td>
		        <td class="l-t-td-right"><input name="item_op_name" type="text" ltype='text' id="item_op_name" /></td>
		        <td class="l-t-td-left">参检人员:</td>
		        <td class="l-t-td-right"><input name="check_op_name" type="text" ltype='text'  /> 
		        </td>
       		</tr>
       		<tr>
       			<td class="l-t-td-left">录入人员：</td>
        		<td class="l-t-td-right"><input name="enter_op_name" type="text" ltype='text' id="enter_op_name" /></td>
       			<td class="l-t-td-left">录入日期：</td>
        		<td class="l-t-td-right"><input name="enter_time" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}" /> 
        		</td>
       		</tr>
       		<tr id="drawRecords">
		    	<td class="l-t-td-left">领取人：</td>
		      	<td class="l-t-td-right"><input name="pulldown_op" id="pulldown_op" type="text" ltype='text' value="" /></td>
		   		<td class="l-t-td-left">领取日期：</td>
		     	<td class="l-t-td-right"><input name="pulldown_time" id="pulldown_time" type="text" ltype='date' value="" ligerui="{format:'yyyy-MM-dd'}" /> 
		      	</td>
			</tr>
		</table>
		</fieldset>
	</form>
</body>
</html>
