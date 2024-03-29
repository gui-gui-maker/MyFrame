<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head pageStatus="${param.status}">
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
var pageStatus="${param.status}"; 
var bigClassify="${param.bigClassify}";
var little_type = "${param.device_sort}"
	$(function () {
	    $("#formObj").initForm({    //参数设置示例
	       toolbar:[
	            { text:'保存', id:'save',icon:'save', click:saveClose },
	          
	            { text:'关闭', id:'close',icon:'cancel', click:close }
	        ],
	        getSuccess:function(res){
	        }
	        
	    });
	  
		 
	}); 

</script>
</head>
<body>
	
		<form name="formObj" id="formObj" method="post" Action="baseConfigAction/save.do"
				getAction="baseConfigAction/detail.do?id=${param.id}">
			<table cellpadding="3" cellspacing="0" class="l-detail-table">
		<tr>
			<td class="l-t-td-left">
				<input id="id" name="id" type="hidden" />
			</td>
			<td class="l-t-td-right"></td>
		</tr>
		<tr>
			<td class="l-t-td-left">系统平台：</td>
			<td class="l-t-td-right" >
				<input name="platform"
					validate="{required:false,maxlength:100}"  ltype='text' id="platform" /></td>
			</td>
			<td class="l-t-td-left">基础信息名字：</td>
			<td class="l-t-td-right" >
				<input type="text" id="name" name="name" ltype="text" validate="{required:true,maxlength:100}" />
			</td>
		</tr>
		<tr>
			<td class="l-t-td-left">设备类型：</td>
			<td class="l-t-td-right">
			<input name="deviceType" id="deviceType" type="text" ltype="select" validate="{required:false}" ligerUi="{data:<u:dict code='device_type'/>}" /></td>
			<td class="l-t-td-left">报表类型：</td>
			<td class="l-t-td-right">
			<input name="reportType" id="reportType" type="text" ltype="select" validate="{required:false}" ligerUi="{data:<u:dict code='tzsb_report_type'/>}"/></td>
		</tr>
		<tr>
			<td class="l-t-td-left">字段名：</td>
			<td class="l-t-td-right" ><input name="code"
				validate="{required:true,maxlength:100}"  ltype='text' id="code" /></td>
			
			</td>
			<td class="l-t-td-left">类型：</td>
			<td class="l-t-td-right" >
				<input type="text" id="type" name="type" ltype="select" validate="{required:true}" 
					ligerui="{
					readonly:true,
					initValue:'text',
					data:[{id:'text',text:'text'},{id:'date',text:'date'},{id:'select',text:'select'},
					{id:'redio',text:'redio'},{id:'checkBox',text:'checkBox'},{id:'print',text:'print'}]}" />
			</td>
		</tr>
		<tr>
			<td class="l-t-td-left">默认值：</td>
			<td class="l-t-td-right" ><input id="defaultValue" name="defaultValue" ltype='text' 
				validate="{maxlength:100}" /></td>
			</td>
		</tr>
		<tr>
			<td class="l-t-td-left" >绑定类型：</td>
			<td class="l-t-td-right">
				<input type="text" id="bindType" name="bindType" 
					ltype="select" validate="{required:false}" 
					ligerUi="{data:[{id:'code',text:'code'},{id:'sql',text:'sql'},{id:'string',text:'string'}]}"/>
			</td>
			<td class="l-t-td-left" >字典值：</td>
			<td class="l-t-td-right">
				<input type="text" id="dataCode" name="dataCode" ltype="text" validate="{required:false}" />
			</td>
		</tr>
		<tr>
			<td class="l-t-td-left" >SQL：</td>
			<td class="l-t-td-right" colspan="3">
				<input type="text" id="dataSql" name="dataSql" ltype="text" validate="{required:false}" />
			</td>
		</tr>
		<tr>
			<td class="l-t-td-left" >字符串：</td>
			<td class="l-t-td-right" colspan="3">
				<input type="text" id="dataString" name="dataString" ltype="text" validate="{required:false}" />
			</td>
		</tr>
		<tr>
			<td class="l-t-td-left">备注：</td>
			<td class="l-t-td-right" colspan="3">
				<textarea rows="3" cols=""  name="remark" id="remark" validate="{required:false,maxlength:500}"  ltype='text' ></textarea>
			
			</td>
		</tr>
		</table>
		</form>
	
</body>
	
</html>
