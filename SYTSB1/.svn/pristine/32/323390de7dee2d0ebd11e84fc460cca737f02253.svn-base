<%@page import="com.khnt.pub.fileupload.service.AttachmentManager" %>
<%@page import="com.khnt.base.Factory" %>
<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>上传文件实例</title>
<%@include file="/k/kui-base-form.jsp" %>
<script type="text/javascript" src="pub/fileupload/js/fileupload.js"></script>
<script type="text/javascript">
	$(function () {
		pageTitle({to: "#asdfasdf", text: "", style: ""});
		var uploadConfig = {
			fileSize: "100mb",//文件大小限制
			businessId: "",//业务ID
			title: "图片",//文件选择框提示
			extName: "jpg,gif,bpm,png",//文件扩展名限制
			saveDB: true,//是否往数据库写信息
			attType: "",//文件存储类型；1:数据库，0:磁盘，默认为磁盘
			workItem: "",//页面多点上传文件标识符号
			fileNum: 1,//只上传一个文件
			callback: function (file) {//回调函数
				$.ligerDialog.alert("上传成功的文件名：" + file[0].name + "；文件路径：" + file[0].path);
			}
		};

		//以下两个上传实例是指定一个页面容器，自动生成按钮和上传文件显示
		uploadConfig.fileContainer = "filecontainer1";
		var uploader1 = new KHKuiFileuploader(uploadConfig);
		uploadConfig.fileContainer = "filecontainer2";
		uploadConfig.fileNum = 4;
		var uploader2 = new KHKuiFileuploader(uploadConfig);


		//下面是自定义上传按钮和自己实现回调函数显示文件

		//更多的实现请参考
		var myUploadConfig = {
			fileSize: "10mb",//文件大小限制
			businessId: "",//业务ID
			buttonId: "pickfiles3",//上传按钮ID
			container: "filecontainer3",//上传控件容器ID
			title: "*",//文件选择框提示
			extName: "*",//文件扩展名限制
			saveDB: true,//是否往数据库写信息
			attType: "",//文件存储类型；1:数据库，0:磁盘，默认为磁盘
			workItem: "",//页面多点上传文件标识符号
			fileNum: -1,//限制上传文件数目
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

	});
</script>
</head>
<body>
	<div class="item-tm" id="asdfasdf"></div>
	<fieldset class="l-fieldset" id="filecontainer1">
		<legend class="l-legend">
       		<div>只上传一个文件</div>
    	</legend>
	</fieldset>
	<fieldset class="l-fieldset" id="filecontainer2">
		<legend class="l-legend">
       		<div>上传多个文件</div>
    	</legend>
	</fieldset>
	<fieldset class="l-fieldset">
		<legend class="l-legend">
       		<div>自定义上传按钮，自定义图片显示</div>
    	</legend>
    	<p id="filecontainer3"><a class="l-button" id="pickfiles3"><span class="l-button-main"><span class="l-button-text">选择文件</span></span></a></p>
    	<div class="l-upload-ok-list">
			<ul id="upfilelist"></ul>
		</div>
	</fieldset>
</body>
</html>
