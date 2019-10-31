<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
<title></title>
<%@include file="/k/kui-base-form.jsp" %>
<script type="text/javascript" src="pub/fileupload/js/fileupload-dialog.js"></script>
<script type="text/javascript">
	$(function () {
		var uploadConfig = {
			fileSize: "10mb",//文件大小限制
			businessId: "",//业务ID
			title: "请选择图片",//文件选择框提示【可选，默认为'*'】
			extName: "jpg,gif,png,bmp",//文件扩展名限制【可选，默认为'*'】
			saveDB: false,//是否往数据库写信息【可选，默认为'true'】
			attType: "",//文件存储类型；1:数据库，0:磁盘，默认为磁盘【可选，默认为''】
			workItem: "",
			fileNum: 1,
			callback: function (files, ids) {//回调函数默认为磁盘【应该提供】
				$.each(files, function (i) {
					var file = files[i];
					var ctn = $("#item1f");
					if (file.workItem == "item2") ctn = $("#item2f");
					ctn.append('<li><div><a href="fileupload/downloadByFilePath.do?path='
						+ file.path + '&fileName=' + file.name + '">' + file.name
						+ '</a></div><div class="l-icon-close progress" onclick="deleteUploadFile(\''
						+ file.id + '\',\'' + file.path + '\',this)">&nbsp;</div></li>');
				});
			}
		};

		//多点上传绑定workItem
		$("#pickfiles1").click(function () {
			uploadConfig.workItem = "item1";
			uploadConfig.fileContainer = "ct1";
			new KHDialogFileUploader(uploadConfig);
		});

		$("#pickfiles2").click(function () {
			uploadConfig.workItem = "item2";
			uploadConfig.fileNum = -1;
			uploadConfig.fileContainer = null;
			new KHDialogFileUploader(uploadConfig);
		});
	});
</script>
</head>
<body>
	<fieldset class="l-fieldset" id="ct1">
		<legend class="l-legend">
       		<div>只上传一个文件</div>
    	</legend>
		<p><a class="l-button-warp l-button" id="pickfiles1"><span class="l-button-main"><span class="l-button-text">选择文件</span></span></a></p>
	</fieldset>
	<fieldset class="l-fieldset">
	    <legend class="l-legend">
	        <div>上传多个文件</div>
	    </legend>
		<p><a class="l-button-warp l-button" id="pickfiles2"><span class="l-button-main"><span class="l-button-text">选择文件</span></span></a></p>
		<div class="l-upload-ok-list">
		<ul id="item2f">
		</ul>
		</div>
	</fieldset>
</body>
</html>
