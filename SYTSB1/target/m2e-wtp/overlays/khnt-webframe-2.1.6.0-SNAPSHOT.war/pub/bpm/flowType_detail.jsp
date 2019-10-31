<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<%@include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
	$(function() {
		$("#formObj").initForm({
			success : function(response) {
				if (response.success) {
					top.$.notice('保存成功', 2);
					api.data.window.detailBack(response.data, "${param.status}");
					api.close();
				} else {
					$.ligerDialog.error('保存失败!</br>' + response.msg);
				}
			}
		});
	});
</script>
</head>
<body>
	<form name="formObj" id="formObj" method="post"
		action="bpm/flowType/save.do"
		getAction="bpm/flowType/detail.do?id=${param.id}">
		<input name="id" type="hidden" />
		<c:choose>
			<c:when test="${param.status=='add'}">
				<input type="hidden" name="flowType.id" value="${param.pid}">
			</c:when>
			<c:otherwise>
				<input type="hidden" name="flowType.id" value="">
			</c:otherwise>
		</c:choose>
		<table cellpadding="3" cellspacing="0" class="l-detail-table">
			<tr>
				<td class="l-t-td-left">分类编号：${FlowType.flowType.id}</td>
				<td class="l-t-td-right"><input name="typeCode" type="text"
					ltype='text' validate="{required:true,maxlength:32}"
					value="${entity.typeCode}" /></td>
			</tr>
			<tr>
				<td class="l-t-td-left">分类名称：</td>
				<td class="l-t-td-right"><input name="typeName" type="text"
					ltype='text' validate="{required:true,maxlength:40}" /></td>
			</tr>
			<tr>
				<td class="l-t-td-left">排序：</td>
				<td class="l-t-td-right"><input name="sort" type="text"
					ltype='text' validate="{maxlength:32}" /></td>
			</tr>
			<tr>
				<td class="l-t-td-left">描述：</td>
				<td class="l-t-td-right"><textarea name="remark"  style="height:65px"
						class="l-textarea" validate="{maxlength:120}"></textarea></td>
			</tr>
		</table>
	</form>
</body>
</html>
