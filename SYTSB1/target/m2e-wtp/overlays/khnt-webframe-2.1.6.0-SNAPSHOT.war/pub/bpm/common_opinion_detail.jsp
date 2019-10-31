<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<%@include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
	$(function() {
		$('#formObj').initForm({
			success : function(responseText, statusText, xhr, $form) {
				if (responseText.success) {
					top.$.notice("保存成功！");
					api.data.callback();
					api.close();
				} else {
					$.ligerDialog.error('保存失败，请稍后重试！')
				}
			}
		});
	});
</script>
</head>
	<form id="formObj"action="bpm/common_opinion/save.do" getAction="bpm/common_opinion/detail.do?id=${param.id}">
		<input name="id" type="hidden" value="${param.id}" />
		<table class="l-detail-table" style="margin-top:1em;">
			<tr>
				<td class="l-t-td-left" style="width:80px;">意见内容:</td>
				<td class="l-t-td-right"><textarea name="content" rows="4" class="l-textarea" validate="{required:true}"></textarea></td>
			</tr>
			<tr>
				<td class="l-t-td-left">排序:</td>
				<td class="l-t-td-right"><input type="text" ltype="spinner" name="sort" validate="{required:true}" ligerui="width:150" /></td>
			</tr>
		</table>
	</form>
</body>
</html>