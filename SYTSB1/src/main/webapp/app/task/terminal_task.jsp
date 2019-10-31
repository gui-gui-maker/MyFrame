<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="add">
<title>终止任务</title>
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="pub/fileupload/js/fileupload.js"></script>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">
$(function(){
	$("#createform").initForm({
		success: function(resp) {
			if (resp.success) {
				top.$.dialog.notice('操作成功!',3);
				if(api.data.callback)
					api.data.callback();
				api.close();
			} else {
				$.ligerDialog.error('操作失败!<br/>' + resp.msg);
			}
		}
	});

	var manyuloadConfig = {
		fileSize : "10mb",
		fileContainer : "uploader_container",
		title : "rar,doc,docx,xls,xlsx,ppt,pptx,pdf,zip,7z,jpg,png,gif,txt,epub",
		extName : "rar,doc,docx,xls,xlsx,ppt,pptx,pdf,zip,7z,jpg,png,gif,txt,epub",
		fileNum : -1,
		callback : function(files){
			$.each(files,function(i){
				$("#createform").append("<input type='hidden' name='files' id='"+ this.id +"' value='" + this.id + "|" + this.name + "' />");
			});
		},
		delCallback:function(file){
			$("#" + file.id).remove();
		}
	};
	new KHKuiFileuploader(manyuloadConfig);
});
</script>
<body>
	<form id="createform" action="oa/task/terminal.do" method="post" style="padding-top:1em">
		<input type="hidden" name="taskId" value="${param.id}" />
		<table class="l-detail-table">
			<tr>
				<td class="l-t-td-left">终止原因：</td>
				<td class="l-t-td-right" colspan="3">
					<textarea name="remark" rows="6" ltype="textarea" validate="{required:true,maxLenth:2000}"></textarea>
				</td>
			</tr>
			<tr>
				<td class="l-t-td-left">附件：</td>
				<td class="l-t-td-right" colspan="3">
					<div id="uploader_container"></div>
					<div id="uploaded_list"></div>
				</td>
			</tr>
		</table>
	</form>
</body>