<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<%@include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
	$("#formObj").initForm({
		success : function(response) {
			if (response.success) {
				top.$.notice("操作成功！");
				api.data.window.submitAction();
				api.close();
			} else {
				$.ligerDialog.error("操作失败：" + response.msg);
			}
		}
	});
</script>
</head>
<body>
	<form name="formObj" id="formObj" method="post" action="bpm/flowFunction/save.do"
		getAction="bpm/flowFunction/detail.do?id=${param.id}">
		<input name="id" type="hidden" />
		<input name="flowType" type="hidden" value="${param.flowtype}" />
		<div class="l-detail-div">
			<table border="0" cellpadding="3" cellspacing="0" class="l-detail-table">
				<tr>
					<td class="l-t-td-left">功能编号：</td>
					<td class="l-t-td-right"><input name="code" type="text" ltype='text' validate="{required:true,maxlength:64}" /></td>
				</tr>
				<tr>
					<td class="l-t-td-left">功能名称：</td>
					<td class="l-t-td-right"><input name="name" type="text" ltype='text' validate="{required:true,maxlength:20}" /></td>
				</tr>
				<tr>
					<td class="l-t-td-left">是否默认使用：</td>
					<td class="l-t-td-right">
						<u:combo name="checked" ltype="radioGroup" code="sys_sf" attribute="initValue:'0'"></u:combo>
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">描述：</td>
					<td class="l-t-td-right"><textarea name="remark" style="height:65px" class="l-textarea" validate="{maxlength:255}"></textarea></td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>
