<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>信息导入</title>
<%@include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="k/kui/frame/jquery.form.js"></script>

<script type="text/javascript">
	function submitForm() {
		var filename = document.getElementById('file').value
		if (filename == "") {
			$.ligerDialog.alert('请选择文件',function(){
				formObj.file.click();
			});
			return false;
		}
		var last = filename.match(/^(.*)(\.)(.{1,8})$/)[3]; //检查上传文件格式
		last = last.toUpperCase();
		if (last != "CHART") {
			$.ligerDialog.alert('文件格式错误，请选择chart文件',function(){
				formObj.file.click();
			});
			return false;
		}
		var options = {
			type : 'post',
			success : function(responseText) {
				$('body').unmask();
				top.$.dialog.notice({content:"共导入"+responseText+"个图表！"});
				api.data.window.Qm.refreshGrid();
				api.close();
			},
			beforeSubmit : function(){
				$('body').mask('数据正在导入,请等待...');
			}
			
		};
		$("#formObj").ajaxSubmit(options);
	}
	$(function(){
		$("#formObj").initForm({
			toolbar:[{
				text: "导入",
				icon: "save",
				click: submitForm
			},{
				text: "关闭",
				icon: "cancel",
				click: function(){
					api.close();
				}
			}]
		});
	});
	
	function checkFile(obj){
		if(!(/.*\.(chart|CHART)$/.test(obj.value))){
			$.ligerDialog.alert('文件格式错误，请选择图表导出文件',function(){
				formObj.file.click();
			});
		}
	}
</script>
<style type="text/css">
	p{
		line-height:1.5em;
		font-size: 13px;
		padding: 10px 5px;
	}
	.file{
		border: skyblue 1px solid;
		height: 1.4em;
		width: 100%;
	}
</style>
</head>
<body>
	<form name="formObj" id="formObj" method="post"
		action="chart/chartinfo/import.do" enctype="multipart/form-data">
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>选择要导入的图表文件</div>
			</legend>
			<p><input type="file" id="file" name="file" class="file" onchange="checkFile(this)" /></p>
		</fieldset>
	</form>
</body>
</html>
