<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="add">
<title>任务转派</title>
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">
$(function(){
	$("#formObj").initForm({
		success: function(resp) {
			if (resp.success) {
				top.$.dialog.notice('转派成功!',3);
				if(api.data.callback)
					api.data.callback();
				api.close();
			} else {
				$.ligerDialog.error('转派失败!<br/>' + resp.msg);
			}
		},
		toolbar:[{
			id:"save",icon:"save",text:"确定",click:function(){
				if($("#executorId").val()=="")
					$.ligerDialog.warn("请选择要转派的目标责任人！");
				else
					$("#formObj").submit();
			}	
		},{
			id:"close",icon:"close",text:"取消",click:function(){api.close();}	
		}]
	});
});
function selectExecutor(){
	selectUnitOrUser(1, 0, 'executorId', 'executor', function(data){
		$("#executorText").text(data.name);
	});
}
</script>
<style type="text/css">
.label {
  display: inline-block;
  padding: .2em 5px .3em;
  margin: 2px;
  font-weight: bold;
  color: #fff;
  text-align: center;
  white-space: nowrap;
  vertical-align: middle;
  border-radius: .25em;
	cursor: pointer;
}
.label-exe{
  background-color: #428bca;
}
.label .text,.label .btn{
	display: inline-block;
	margin-top: 2px;
	height: 16px;
}
.label .btn{
	width: 16px;
	margin-left: .5em;
}
.label .edit{
	background:url('k/kui/images/icons/16/edit.png') no-repeat center;
}
</style>
<body>
	<form id="formObj" action="oa/task/transfer.do" style="padding-top:1em;">
		<input type="hidden" name="taskId" value="${param.id}" />
		<input type="hidden" name="executor" id="executor" value="" />
		<input type="hidden" name="executorId" id="executorId" value="" />
		<table class="l-detail-table">
			<tr>
				<td class="l-t-td-left">转派给：</td>
				<td class="l-t-td-right">
					<span class="label label-exe" onclick="selectExecutor();">
						<span class="text" id="executorText">请选择</span>
						<span class="edit btn btn-lm" title="点击选择责任人">&nbsp;</span>
					</span>
				</td>
			</tr>
			<tr>
				<td class="l-t-td-left">备注：</td>
				<td class="l-t-td-right">
					<textarea name="remark" rows="5" ltype="textarea" validate="{maxLenth:2000}"></textarea>
				</td>
			</tr>
		</table>
	</form>
</body>