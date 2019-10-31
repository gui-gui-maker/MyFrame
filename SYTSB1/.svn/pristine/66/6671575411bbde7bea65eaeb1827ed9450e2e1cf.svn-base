<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="add">
<title>任务反馈</title>
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="pub/fileupload/js/fileupload.js"></script>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">
$(function(){
	$("#formObj").initForm({
		success: function(resp) {
			if (resp.success) {
				top.$.dialog.notice('反馈成功!',3);
				if(api.data.callback)
					api.data.callback();
				api.close();
			} else {
				$.ligerDialog.error('反馈失败!<br/>' + resp.msg);
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
				$("#formObj").append("<input name='files' type='hidden' id='"+ this.id +"' value='" + this.id + "|" + this.name + "' />");
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
	<form id="formObj" action="oa/task/feecback.do">
		<input type="hidden" name="taskId" value="${param.id}" />
		<table class="l-detail-table">
			<tr>
				<td class="l-t-td-left">进度：</td>
				<td class="l-t-td-right" colspan="3">
					<input type="text" name="progress" ltype="spinner" validate="{required:true}" 
						ligerui="{type:'int',suffix:'%',initValue:'${param.progress}'}" value="${param.progress}" />
				</td>
			</tr>
			<tr>
				<td class="l-t-td-left">进度说明：</td>
				<td class="l-t-td-right" colspan="3">
					<textarea name="content" rows="5" ltype="textarea" validate="{maxLenth:2000}"></textarea>
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