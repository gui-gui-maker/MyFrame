﻿<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head pageStatus="add">
<title>文件上传</title>
<%@include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="pub/fileupload/js/fileupload.js"></script>
<script type="text/javascript" src="pub/camera/camera.js"></script>
<script type="text/javascript">
var opts = {};
if(api.data.options){
	$.extend(opts,api.data.options,{
		container: "plupload-container",
		buttonId: "plupload-add-button",
		useThirdDevice: false
	});
}
$(function(){
	if(!api.data['camera']){
		$("#do-camera").remove();
	}
	if(!api.data['print']){
		$("#do-print").remove();
	}
	var uploader = $("#plupload-container").khUpload(opts);
	if(!opts.businessId){//无业务ID，无法加载文件，手动加载已上传文件
		if(api.data.upfiles && api.data.upfiles.length > 0){
			uploader.loadFiles(api.data.upfiles);
		}
	}
});

function printFile(){
	var files = $("#plupload-container").khUpload().uploadedFiles;
	if(files.length == 0) return;
	var regex = /\.(jpg|gif|png|bmp)$/gi;
	if(files){
		var imgs = [];
		for(var i in files){
			if(files[i].name.match(regex)!=null){
				imgs.push(files[i]);
			}
		}
		if(imgs.length==0){
			$.ligerDialog.warn("不是图片，无法打印！");
		}else{
			if(files.length > 1){
				alert("操作提示：\n我们将对所有图片逐个打印，请耐心处理！");
			}
			printAttachment(imgs);
		}
	}
}

function scanFile(){
	if(opts.fileNum > 0){
		var fnum = opts.fileNum - $("#plupload-container").khUpload().uploadedFiles.length;
		if(fnum < 1){
			$.ligerDialog.warn("您已上传了" + opts.fileNum + "个文件，不能再上传了！");
			return;
		}
	}
	openSystemCamera({
		businessId: opts.businessId, 
		onAfterUpload : function(files){
			$("#plupload-container").khUpload()._showFiles(files);
		},
		workItem: opts.workItem,
		fileNum: fnum
	});
}

function closeDialog(){ 
	if(api.data.callback) { 
		var fs = $("#plupload-container").khUpload().uploadedFiles;
		api.data.callback(fs); 
	}
	api.close();
}
</script>
<style type="text/css">
table, td {
	border-collapse: collapse;
}
.l-icon-add{
	background: url('pub/fileupload/images/add-16.png') no-repeat center;
}
.l-icon-camera{
	background: url('pub/fileupload/images/camera-16.png') no-repeat center;
}
.l-icon-print{
	background: url('pub/fileupload/images/print-16.png') no-repeat center;
}
.toolbar-tm-bottom{padding-left:6px;}
</style>
</head>
<body>
	<div class="scroll-tm" style="overflow-y: auto;">
		<ul class="upload-file-list" style="display:block;padding:5mm;" id="plupload-container-files"></ul>
	</div>
	<div class="toolbar-tm">
		<div class="toolbar-tm-bottom" id="plupload-container">
			<a class="l-button-warp l-button" id="plupload-add-button" href="javascript:void(0);" style="float:left;">
				<span class="l-button-main l-button-hasicon"><span class="l-button-text">上传文件</span><span class="l-button-icon l-icon-add"></span></span>
			</a>
			<a class="l-button-warp l-button" id="do-camera" href="javascript:scanFile();" style="float:left;">
				<span class="l-button-main l-button-hasicon"><span class="l-button-text">拍摄文件</span><span class="l-button-icon l-icon-camera"></span></span>
			</a>
			<a class="l-button-warp l-button" id="do-print" href="javascript:printFile();" style="float:left;">
				<span class="l-button-main l-button-hasicon"><span class="l-button-text">打印</span><span class="l-button-icon l-icon-print"></span></span>
			</a>
			<a class="l-button-warp l-button" href="javascript:closeDialog();">
				<span class="l-button-main l-button-hasicon"><span class="l-button-text">确定</span><span class="l-button-icon l-icon-save"></span></span>
			</a>
			<a class="l-button-warp l-button" href="javascript:api.close();">
				<span class="l-button-main l-button-hasicon"><span class="l-button-text">取消</span><span class="l-button-icon l-icon-cancel"></span></span>
			</a>
		</div>
	</div>
</body>
</html>
