<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
	String nowTime=""; 
	nowTime = dateFormat.format(new Date());%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<title></title>
<%@include file="/k/kui-base-form.jsp"%>
<link type="text/css" rel="stylesheet" href="app/qualitymanage/css/form_detail.css" />
<script type="text/javascript">
	function number(){
		var money = document.getElementById("compact_money");
		var phone = document.getElementById("user_phone");
		var reg = /^\d*$/;
		if(!reg.test(money,phone)){
			alert("亲,只能输入,数字哟");
		}
	}
</script>

</head>
<body>

	<form id="form1" action="taskConvey/convey/save.do" getAction="taskConvey/convey/detail.do?id=${param.id}">
		<input type="hidden" id="id" />
		<h1 class="l-label" style="padding:5mm 0 2mm 0;font-family:微软雅黑;font-size:6mm;">检&nbsp;验&nbsp;任&nbsp;务&nbsp;书 </h1><div style="height:2px">&nbsp;</div>
	<fieldset class="l-fieldset" >
					<legend class="l-legend">
						<div>
							检验任务书
						</div>
					</legend>
		<table border="1" cellpadding="3" cellspacing="0" width=""
				class="l-detail-table">
					<tr>
						<td class="l-t-td-left">检验性质:</td>
						<td class="l-t-td-right"><input type="radio" ltype="radioGroup" name="ck_nature" 
									ligerui="{initValue:2,data:[{text:'法定',id:'法定'},{text:'委托',id:'委托'}]} " /></td>
						<td class="l-t-td-left">检验编号:</td>
						<td class="l-t-td-right"><input type="text" ltype="text"  name="ck_sn" /></td>
					</tr>
					</table>
				</fieldset>
				<fieldset class="l-fieldset" >
					<legend class="l-legend">
						<div>
							报告详情
						</div>
					</legend>
					<table border="1" cellpadding="3" cellspacing="0" width=""
						class="l-detail-table">
					<tr>
						<td class="l-t-td-left">报告编号:</td>
						<td class="l-t-td-right"><input type="text" ltype="text"  name="report_sn" /></td>
						<td class="l-t-td-left">用户名称:</td>
						<td class="l-t-td-right"><input type="text" ltype="text"  name="user_name" /></td>
					</tr>
					<tr>
						<td class="l-t-td-left">设备名称:</td>
						<td class="l-t-td-right"><input type="text" ltype="text"  name="device_name" /></td>
						<td class="l-t-td-left">设备编号:</td>
						<td class="l-t-td-right"><input type="text" ltype="text"  name="device_sn" /></td>
					</tr>
					<tr style="width: 788px; ">
						<td class="l-t-td-left">设备所在地址:</td>
						<td class="l-t-td-right" colspan="3" ><input type="text" ltype="text"  name="device_place" /></td>
					</tr>
					<tr>
						<td class="l-t-td-left">检验/委托内容:</td>
						<td class="l-t-td-right" colspan="3"><input type="text" ltype="text"  name="ck_content" /></td>
					</tr>
					<tr>
						<td class="l-t-td-left">资料份数:</td>
						<td class="l-t-td-right"><input type="text" ltype="text" name="material_part" /></td>
						<td class="l-t-td-left">报检/委托时间:</td>
						
        				<td class="l-t-td-right">
        				<input name="report_date" type="text" ltype="date" validate="{required:false}" 
        					ligerui="{initValue:'',format:'yyyy-MM-dd'}" id="report_date"  
        					 readonly="readonly" value="<%=nowTime%>" />
        			</td>
					</tr>
					<tr>
						<td class="l-t-td-left">联系电话:</td>
						<td class="l-t-td-right"><input type="text" ltype="text" name="user_phone" id="user_phone" onblur="number();" /></td>
						<td class="l-t-td-left">联系人:</td>
						<td class="l-t-td-right"><input type="text" ltype="text" name="user_man"/></td>
					</tr>
					<tr>
						<td class="l-t-td-left">承接部门</td>
						<td class="l-t-td-right" colspan="3"><input type="text" ltype="text" name="receive_dep" /></td>
					</tr>
					</table>
				
		
				
			<table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
					<tr>
						<td class="l-t-td-left">合同编号:</td>
						<td class="l-t-td-right"><input type="text" ltype="text" name="compact_sn" /></td>
						<td class="l-t-td-left">合同金额:</td>
						<td class="l-t-td-right"><input type="text" ltype="text" name="compact_money" id="compact_money" onblur="number();" /></td>
						<td class="l-t-td-left">签订时间:</td>
        				<td class="l-t-td-right">
        				<input name="compact_date" type="text" ltype="date" validate="{required:false}" ligerui="{initValue:'',format:'yyyy-MM-dd'}" id="compact_date" readonly="readonly" value="<%=nowTime%>" />
        			</td>
        			</tr>
        		</table>
        			
        	<table border="1" cellpadding="3" cellspacing="0" width=""
						class="l-detail-table">
        			<tr>
        				<td class="l-t-td-left">备注:</td>
        				<td class="l-t-td-right" colspan="5"><textarea name="remark" id="remark" rows="2" cols="25" class="l-textarea" validate="{maxlength:200}"></textarea></td>
        			</tr>
					<tr>
						<td class="l-t-td-left">经办人:</td>
						<td class="l-t-td-right"><input type="text" ltype="text" name="agent_name"/></td>
						<td class="l-t-td-left">经办时间:</td>
						<td class="l-t-td-right"><input name="agent_date" type="text" ltype="date" 
								validate="{required:false}" ligerui="{initValue:'',format:'yyyy-MM-dd'}" 
								id="agent_date" readonly="readonly" value="<%=nowTime%>" /></td>
					</tr>
			</table>
			</fieldset>
		</form>
</body>
</html>