<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<title></title>
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
	$(function() {
		$("#formObj").initForm({
			success : function(response) {//处理成功
				if (response.success) {
					top.$.notice('保存成功');
					api.data.window.refreshTree(response.data,
							"${param.status}");
					api.close();
				} else {
					$.ligerDialog.error('保存失败！<br />' + response.msg)
        		}
			}
		});
	});
</script>
</head>
<body>
	<form name="formObj" id="formObj" method="post" action="pub/codetable/save.do"
		getAction="pub/codetable/detail.do?id=${param.id}">
		<input type="hidden" name="id" />
		<table border="0" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
			<tr>
				<td class="l-t-td-left" style="width: 120px">编号：</td>
				<td class="l-t-td-right"><input name="code" type="text" ltype='text' validate="{required:true,maxlength:32}" /></td>
			</tr>
			<tr>
				<td class="l-t-td-left">名称：</td>
				<td class="l-t-td-right"><input name="name" type="text" ltype='text' validate="{required:true,maxlength:64}" /></td>
			</tr>
			<tr>
				<td class="l-t-td-left">排序：</td>
				<td class="l-t-td-right"><input name="sort" type="text" ltype='text' validate="{maxlength:32}" /></td>
			</tr>
			<tr>
				<td class="l-t-td-left">说明：</td>
				<td class="l-t-td-right"><input name="remark" type="text" ltype='text' validate="{maxlength:512}"  /></td>
			</tr>
			<tr>
				<td class="l-t-td-left">系统数据字典：</td>
				<td class="l-t-td-right">
					<sec:authorize access="hasRole('super_administrate')">
						<input name="isSystem"type='radio' ltype="radioGroup" 
						ligerui="{initValue:'false',data:[{id:'true',text:'是'},{id:'false',text:'否'}]}" /></td>
					</sec:authorize>
					<sec:authorize ifNotGranted="super_administrate">
						<input name="isSystem"type='radio' ltype="radioGroup" 
						ligerui="{initValue:'false',data:[{id:'false',text:'否'}]}" /></td>
					</sec:authorize>
				</td>
			</tr>
			<tr>
				<td class="l-t-td-left">表结构：</td>
				<td class="l-t-td-right">
					<input name="type" type='radio' ltype="radioGroup"
						ligerui="{initValue:'2',data:[{id:'1',text:'树状类型'},{id:'2',text:'普通类型'}]}" /></td>
			</tr>
		</table>
	</form>
</body>
</html>