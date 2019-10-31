<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="k/kui/frame/jquery.form.js"></script>
<script type="text/javascript">
$(function(){
	$("#impform").initForm({
		toolbar : [{
			text : "导入",
			icon : "save",
			click : submitForm
		},{
			text : "关闭",
			icon : "close",
			click : function() {
				api.close();
			}
		}]
	});
});

function submitForm() {
	if ($("#f_data_file").val() == "") {
		top.$.dialog.alert('请选择要导入的文件',null,api);
		return;
	}
	
	var suffix = $("#f_data_file").val().match(/^(.*)(\.)(.{1,8})$/)[3];
	suffix = suffix.toUpperCase();
	if (suffix != "ZIP") {
		top.$.dialog.alert('请选择正确的流程定义备份文件！',null,api);
		return false;
	}
	$("body").mask("正在导入，请稍后...");
	$("#impform").ajaxSubmit({
		type: 'post',
		dataType: "json",
		success: function(response) {
			$("body").unmask();
			if(response.success){
				top.$.notice("导入成功！");
				api.data.callback();
				api.close();
			}
			else{
				top.$.dialog.alert('导入失败！',null,api);
			}
		}
	});
}
</script>
<style type="text/css">
body {margin: 0;padding: 0;}
.caption {padding-top:30px;padding-left: 20px;}
</style>
</head>
<body>
	<form id="impform" action="bpm/definition/import.do" method="post" enctype="multipart/form-data">
		<input type="hidden" name="typeId" value="${param.typeId}" />
		<div class="caption">
			<input type="file" name="upload_excel" id="f_data_file" style="width: 60%;" />
		</div>
	</form>
</body>
</html>