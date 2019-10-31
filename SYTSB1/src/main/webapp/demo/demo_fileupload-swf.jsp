<%@page import="com.khnt.pub.fileupload.service.AttachmentManager"%>
<%@page import="com.khnt.base.Factory"%>
<%@ page contentType="text/html;charset=UTF-8"
	trimDirectiveWhitespaces="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>上传文件实例</title>
<%@include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="pub/fileupload/swfupload/upload.js"></script>
<script type="text/javascript">
	$(function() {
		var myUploadConfig = {
			fileSize: "10mb",
			businessId: "",
			buttonId: "pickfile",
			title: "*",
			extName: "jpg,gif.png",
			saveDB: true,
			fileNum: 2,
			callback: function (files) {//上传成功后回调函数,实现页面文件显示，交与业务自行后续处理
				if (files) {
					$.each(files, function (i) {
						$("#upfilelist").append('<li><div><a href="fileupload/downloadByFilePath.do?path='
							+ files[i].path + '&fileName=' + files[i].name + '">' + files[i].name
							+ '</a></div><div class="l-icon-close progress" onclick="deleteUploadFile(\''
							+ files[i].id + '\',\'' + files[i].path + '\',this)">&nbsp;</div></li>');
					})
				}
			}
		};
		var uploader3 = new KHFileUploader(myUploadConfig);
		$("#dst").click(function(){
			uploader3.destroy();
		});
	});
</script>
</head>
<body>
	<fieldset class="l-fieldset">
		<legend class="l-legend">
			<div>自定义上传按钮，自定义图片显示</div>
		</legend>
		<a class="l-button"><span id="pickfile">选择文件</span></a>
		<a class="l-button" id="dst"><span>销毁</span></a>
		<div class="l-upload-ok-list">
			<ul id="upfilelist"></ul>
		</div>
	</fieldset>
</body>
</html>
