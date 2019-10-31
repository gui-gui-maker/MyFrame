<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
	$(function() {
		$("#layout1").ligerLayout({
			border : false,
			space : 0
		});
		$("form").ligerForm();
		$('#formObj').submit(function() {
			$(this).ajaxSubmit({
				beforeSubmit : function(formData, jqForm, options) {
					$.formValidate(formData, jqForm, options);
				},
				success : function(responseText, statusText, xhr, $form) {
					if (responseText.success) {
						$.ligerDialog.success('保存成功', function() {
							api.close();
						})
						W.submitAction("");//执行列表页面函数
					} else {
						$.ligerDialog.error('保存失败')
					}
				}
			});
			return false;
		});
	});
</script>
</head>
<body style="overflow: hidden; padding: 0px 0px 10px 10px;">
	<div id="layout1">
		<div position="center" style="overflow: auto;">
			<form name="formObj" id="formObj" method="post" action="bpm/flowAction/save.do"
				getAction="bpm/flowAction/detail.do?id=${param.id}">
				<input name="id" type="hidden" /> <input name="flowType" type="hidden" value="${param.flowtype}" />
				<table border="0" cellpadding="3" cellspacing="0" width="" height="" align="" class="l-detail-table"
					style="margin: 8px;">
					<tr>
						<td class="l-t-td-left">动作名称：</td>
						<td class="l-t-td-right"><input name="name" type="text" ltype='text' validate="{required:true}"
							style="width: 100%;" /></td>
					</tr>
					<tr>
						<td class="l-t-td-left">动作事件：</td>
						<td class="l-t-td-right"><input name="actionDo" type="text" ltype='text' validate="{required:true}"
							style="width: 100%;" /></td>
					</tr>
					<tr>
						<td class="l-t-td-left">描述:</td>
						<td class="l-t-td-right"><textarea name="remark" cols="60" rows="4" class="l-textarea" style="width: 100%"
								validate="{required:true}"></textarea></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>
