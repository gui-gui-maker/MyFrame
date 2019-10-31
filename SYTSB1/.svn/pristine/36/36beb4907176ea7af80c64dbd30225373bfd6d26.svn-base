<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<title>码表值</title>
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
	$(function() {
		$("#formObj").initForm({
			success : function(res) {
				if (res.success) {
					top.$.notice("保存成功！",3);
					api.data.window.reloadDataGrid();
					api.close();
				} else {
					$.ligerDialog.error("保存失败！<br />错误信息：" + res.msg);
				}
			}
		});
	});
</script>
</head>
<body>
	<form name="formObj" id="formObj" method="post" action="pub/codetablevalue/save.do"
		getAction="pub/codetablevalue/detail.do?id=${param.id}">
		<input name="id" type="hidden" />
		<input name="status" type="hidden" />
		<c:choose>
			<c:when test="${param.status=='add'}">
				<input type="hidden" name="codeTable.id" value='${param.id}' />
				<input type="hidden" name="codeTableValue.id" value='${param.code_table_value_id}' />
			</c:when>
			<c:otherwise>
				<input type="hidden" name="codeTable.id" />
				<input type="hidden" name="codeTableValue.id" />
			</c:otherwise>
		</c:choose>
		<table border="0" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
			<tr>
				<td class="l-t-td-left">值：</td>
				<td class="l-t-td-right"><input name="value" type="text" ltype='text' validate="{required:true,maxlength:64}" /></td>
			</tr>
			<tr>
				<td class="l-t-td-left">名称：</td>
				<td class="l-t-td-right"><input name="name" type="text" ltype='text' validate="{required:true,maxlength:128}" /></td>
			</tr>
			<tr>
				<td class="l-t-td-left">排序：</td>
				<td class="l-t-td-right"><input name="sort" type="text" ltype='text' validate="{maxlength:30}" /></td>
			</tr>
			<tr>
				<td class="l-t-td-left">说明：</td>
				<td class="l-t-td-right"><input name="remark" type="text" ltype='text' validate="{maxlength:1024}" /></td>
			</tr>
		</table>
	</form>
</body>
</html>