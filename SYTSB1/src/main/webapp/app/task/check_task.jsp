<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="add">
<title>考核任务</title>
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="pub/fileupload/js/fileupload.js"></script>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">
$(function(){
	$("#createform").initForm({
		success: function(resp) {
			if (resp.success) {
				top.$.dialog.notice('考核成功!',3);
				if(api.data.callback)
					api.data.callback();
				api.close();
			} else {
				$.ligerDialog.error('考核失败!<br/>' + resp.msg);
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
	<form id="createform" action="oa/task/check.do" method="post">
		<input type="hidden" name="taskId" value="${param.id}" />
		<table class="l-detail-table">
			<tr>
				<td class="l-t-td-left" style="width:80px;">考核评分：</td>
				<td class="l-t-td-right" colspan="3">
					<input type="text" name="score" ltype="spinner" validate="{required:true}" 
						ligerui="{type:'int',suffix:'分'}" />
				</td>
			</tr>
			<tr>
				<td class="l-t-td-left">考核说明：</td>
				<td class="l-t-td-right" colspan="3">
					<textarea name="remark" rows="7" ltype="textarea" validate="{maxLenth:2000}"></textarea>
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