<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<title>权限管理-编辑、查看</title>
<%@include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
	$(function() {
		$("#formObj").initForm({
			success : function(response) {
				if (response.success) {
					top.$.notice("保存成功！");
					api.data.window.loadGridData();
					api.close();
				} else {
					$.ligerDialog.error("保存失败：" + response.msg);
				}
			}
		});
	});
</script>
</head>
<body>
	<form name="formObj" id="formObj" method="post" action="rbac/permission/save.do"
		getAction="rbac/permission/detail.do?id=${param.id}">
		<input name="id" type="hidden" />
		<c:if test="${not empty param.pid}">
		<input name="parent.id" type="hidden" value="${param.pid}" />
		</c:if>
		<div class="l-detail-div">
			<table border="0" cellpadding="3" cellspacing="0" width="" height="" align="" class="l-detail-table">
				<tr>
					<td class="l-t-td-left">权限代码：</td>
					<td class="l-t-td-right"><input name="code" type="text" ltype='text' validate="{required:true,maxlength:64}" /></td>
				</tr>
				<tr>
					<td class="l-t-td-left">权限名称：</td>
					<td class="l-t-td-right"><input name="name" type="text" ltype='text' validate="{required:true,maxlength:64}" /></td>
				</tr>
				<tr>
					<td class="l-t-td-left">排序：</td>
					<td class="l-t-td-right">
						<input name="sort" type="text" ltype='text' validate="{maxlength:20}" />
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">描述：</td>
					<td class="l-t-td-right"><textarea name="remark" cols="60" rows="4" class="l-textarea"
							validate="{maxlength:200}"></textarea></td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>
