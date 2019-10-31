<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<title></title>
<%@include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
	$(function() {
		$("#form1").initForm({
			success : function(resp) {
				if(resp.success){
					top.$.notice("job has been added!",2);
					api.close();
				}
				else{
					$.ligerDialog.error("error occured!<br/>" + resp.msg);
				}
			}
		});
	});
</script>
</head>
<body>
	<form id="form1" action="pub/job/addJob.do" getAction="pub/job/detail.do?id=${param.id}">
		<input type="hidden" id="id" name="id" />
		<table class="l-detail-table">
			<tr>
				<td class="l-t-td-left">任务名称：</td>
				<td class="l-t-td-right"><input name="name" type="text" value="" /></td>
			</tr>
			<tr>
				<td class="l-t-td-left">任务执行目标：</td>
				<td class="l-t-td-right"><input name="targetObject" type="text" value="" /></td>
			</tr>
			<tr>
				<td class="l-t-td-left">任务执行参数：</td>
				<td class="l-t-td-right"><input name="parameter" type="text" value="" /></td>
			</tr>
		</table>
	</form>
</body>
</html>