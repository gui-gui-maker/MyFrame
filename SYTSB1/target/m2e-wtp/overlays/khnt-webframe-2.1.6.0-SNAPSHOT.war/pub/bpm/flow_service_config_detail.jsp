﻿<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<%@include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">
	function doSelectFlow() {
		top.$.dialog({
			width : 800,
			height : 350,
			lock : false,
			parent : api,
			title : "选择流程",
			content : "url:pub/bpm/flow_definition_select_index.jsp",
			cancel : true,
			ok : function() {
				var datas = this.iframe.contentWindow.getSelect();
				if (datas) {
					$("#flowId").val(datas.code);
					$("#flowName").val(datas.name)
				}
				return true;
			}
		});
	}
</script>
</head>
<body>
	<form name="formObj" id="formObj" method="post" action="bpm/serviceConfig/save.do"
		getAction="bpm/serviceConfig/detail.do?id=${param.id}">
		<input name="id" type="hidden" /> <input name="flowId" type="hidden" id="flowId"
			validate="{required:true,maxlength:32}" /> <input name="orgId" id="org_id" type="hidden"
			value="<sec:authentication property="principal.unit.id" />" />
		<table class="l-detail-table" style="margin-top: 10px;">
			<tr>
				<td class="l-t-td-left">单位：</td>
				<td class="l-t-td-right"><input name="orgName" type="text" readonly="readonly" id="org_name" title="点击此处选择单位"
					ltype='text' validate="{required:true}"
					ligerui="{iconItems:[{icon:'org',click:function(){selectOrgUser({type:0,checkbox:0,code:'org_id',name:'org_name',isAsyn:'1'})}}]}"
					value="<sec:authentication property="principal.unit.orgName" htmlEscape="false" />" /></td>
			</tr>
			<tr>
				<td class="l-t-td-left">业务编码：</td>
				<td class="l-t-td-right"><u:combo name="serviceCode" code="pub_workflow_service_code"
						validate="required:true" tree="true" modify="true" />
			</tr>
			<tr>
				<td class="l-t-td-left">工作流程：</td>
				<td class="l-t-td-right"><input name="flowName" type="text" id="flowName" 
					ligerui="{iconItems:[{icon:'search',click:doSelectFlow}]}"
					title="点击此处选择工作流程" ltype='text' validate="{required:true,maxlength:128}" readonly="readonly" /></td>
			</tr>
			<tr>
				<td class="l-t-td-left">描述：</td>
				<td class="l-t-td-right"><textarea name="remark" rows="2" class="l-textarea" validate="{maxlength:128}"></textarea></td>
			</tr>
		</table>
	</form>
</body>
</html>