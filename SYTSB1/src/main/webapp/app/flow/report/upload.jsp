<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>信息导入</title>
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>
<script src="k/kui/frame/jquery.min.js" type="text/javascript"></script>
<script src="k/app/k-frame-config.jsp" type="text/javascript"></script>
<script src="k/kui/frame/jquery.form.js" type="text/javascript"></script>
<link href="k/kui/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /><!-- ligerui框架css -->
<link href="k/app/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /><!-- kui框架css -->
<link href="k/kui/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
<script src="k/kui/frame/ligerui.all.js" type="text/javascript"></script>
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
		if (last != "JPG" && last !="JPEG" /* && last !="PNG" && last !="BPM" && last !="GIF" */) {
			alert("文件格式错误，请选择JPG、JPEG格式的图片文件！");
			return;
		}
		$('#upload').attr('disabled',true);
		var options = {
			type : 'post',
			dataType:'json',
			success : function(responseText) {
				//alert(responseText.success);
				if(responseText.success)
				{
					$('#upload').attr('disabled',false);
					if(responseText.data.filesize/1024>1024)
					{
						alert("大小不能超过1024KB，请重新上传！");
						return;
					}else
					{
						parent.window.returnValue = responseText.data.id;
						parent.window.close();
					}
				}
				else
				{
					alert("出错了！");	
				}
			}
		};
		$("#formObj").ajaxSubmit(options);
	}
	function checkFile(obj){
		if(!(/.*\.(jpg|jpeg|bpm|gif|png)$/.test(obj.value.toLowerCase()))){
			$.ligerDialog.alert('文件格式错误，请选择图片文件',function(){
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
		action="${pageContext.request.contextPath}/reportpic/fileUp.do?ins_info_id=${param.ins_info_id}&item_name=${param.item_name}&pic_type=${param.pic_type}&type=B" enctype="multipart/form-data">
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>选择要上传的图片</div>
			</legend>
			<p><input type="file" id="file" name="file" class="file" onchange="checkFile(this)" />
			</p>
			<p style="text-align: right;"><button type="button" id="upload" name="upload" onclick="submitForm()">上传图片</button></p>
		</fieldset>
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>图片说明</div>
			</legend>
			<p>
			<!-- 图片说明：<br/> -->
			1、为了不影响报告里图片的清晰度，请上传尺寸不大于<c:out value="${param.width}"></c:out>×<c:out value="${param.height}"></c:out>的图片。<br/>
			2、图片的格式为jpg、jpeg。<br/><!-- png、gif、bmp -->
			3、图片大小不超过1024KB，图片需清晰。<br/>
			</p>
		</fieldset>
	</form>
</body>
</html>
