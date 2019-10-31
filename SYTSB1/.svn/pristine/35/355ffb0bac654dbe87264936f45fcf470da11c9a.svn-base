<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<title>资源编辑</title>
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
	$(function() {
		$("#formObj").initForm({
			success : function(response) {//处理成功
				if (response.success) {
					top.$.notice('保存成功', 2);
					api.data.window.reloadDataGrid();
					api.close();
				} else {
					$.ligerDialog.error('保存失败' + response.msg != null ? response.msg : response)
				}
			}
		});
	});
</script>
</head>
<body>
	<form name="formObj" id="formObj" method="post" action="chart/bparam/save.do"
		getAction="chart/bparam/detail.do?id=${param.id}">
		<c:choose>
			<c:when test="${param.status=='add'}">
				<input type="hidden" name="chartType.id" value='${param.typeId}'>
			</c:when>
			<c:otherwise>
				<input type="hidden" name="chartType.id">
				<input name="id" type="hidden"  />
			</c:otherwise>
		</c:choose>
		<input name="status" type="hidden" value='0' />
		<table cellpadding="3" cellspacing="0" class="l-detail-table">
			<tr>
				<td class="l-t-td-left" style="width:100px;">参数大类名称：</td>
				<td class="l-t-td-right">
					<input name="name" type="text" ltype='text' validate="{required:true,maxlength:32}" />
				</td>
				<td class="l-t-td-left">编码：</td>
				<td class="l-t-td-right">
					<input name="code" type="text" ltype='text' validate="{maxlength:32}" />
				</td>
			</tr>
			<tr>
				<td class="l-t-td-left" style="width:100px;">是否数据属性：</td>
				<td class="l-t-td-right">
					<u:combo name="dataParam" code="sys_sf" attribute="initValue:'0',required:true"></u:combo>
				</td>
				<td class="l-t-td-left">排序：</td>
				<td class="l-t-td-right">
					<input name="sortOrder" type="text" ltype='text' validate="{maxlength:32}" />
				</td>
			</tr>
			<tr>
				<td class="l-t-td-left">描述：</td>
				<td class="l-t-td-right" colspan="3">
					<textarea rows="5" cols="20" name="remark" validate="{maxlength:256}"></textarea>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
