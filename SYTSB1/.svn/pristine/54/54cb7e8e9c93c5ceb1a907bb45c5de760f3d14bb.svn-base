<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/k/kui-base-form.jsp"%>
<title>复制流程</title>
<script type="text/javascript">
	$(function() {
		$("#formObj").initForm({
			success: function(resp){
				if (resp.success) {
					top.$.notice("复制成功！",3);
					api.data.window.Qm.refreshGrid();
					api.close();
				}else{
					$.ligerDialog.error("复制失败！");
				}
			}
		});
		//构建分类树
		$("#typeTree").ligerTree({
			checkbox: false,
			selectCancel: false,
			idFieldName: "id",
			parentIDFieldName: "parentId",
			textFieldName: "typeName",
			url: 'bpm/flowType/viewTree.do',
			onSelect: function(node){
				$("#ftype").val(node.data.id);
			}
		});
	});
</script>
<style type="text/css">
.border-tbl,.border-tbl .l-t-td-left,.border-tbl .l-t-td-right{
	border: 1px solid #BED5F3;
	border-collapse: collapse;
}
</style>
</head>
<body>
	<form action="bpm/definition/copyFlow.do" method="post" id="formObj">
		<input name="id" type="hidden" value="${param.id}" />
		<input name="type" id="ftype" type="hidden" />
		<table class="l-detail-table border-tbl" style="padding:0;">
			<tr>
				<td class="l-t-td-left" style="height:30px;">复制份数：</td>
				<td class="l-t-td-right" style="padding-left:5px;"><input type="text" name="copies" ltype="spinner" validate="{required:true,min:1}"
						value="1" ligerui="{type:'int',width:100,suffix:'份',suffixWidth:200}" /></td>
			</tr>
			<tr>
				<td class="l-t-td-left">流程分类：</td>
				<td class="l-t-td-right" style="padding:0;">
					<div style="overflow:auto;height:174px;">
						<ul id="typeTree"></ul>
					</div>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>