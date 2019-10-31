﻿<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>文件上传</title>
<%@include file="/k/kui-base-list.jsp" %>
<script type="text/javascript" src="pub/fileupload/js/fileupload.js"></script>
<script type="text/javascript">
var uploadedFiles = [];
var uploadedFileIds = ""; 
var fileGridManager;
var khFileUploader;
$(function(){
	if(api.data.remember=="1"){
		$("#remenber").show();
	}else{
		$("#remenber").hide();
	}
	$(".layout").ligerLayout({
		bottomHeight : 40,
		topHeight: 290,
		space : 0,
		allowBottomResize : false
	});
	$("#button_div").ligerButton({
		items:[
			{text:"添加文件",id:"pickfiles",icon:"add"},
			{text:"上传文件",id:"uploadFile",icon:"up",disabled:true},
			{text:" 取 消 ",icon:"cancel",click:function(){api.close();} }
		]
	});
	//创建上传实例
	khFileUploader = new KHFileUploader({
		fileSize : api.data.fileSize,//文件大小限制
		businessId : api.data.businessId,//业务ID
		buttonId : "pickfiles",//上传按钮ID
		container : "container",//上传控件容器ID
		fileNum : api.data.fileNum,//上传文件数量限制
		title : api.data.title,//文件选择框提示
		extName : api.data.extName,//文件扩展名限制
		saveDB : api.data.saveDB,//是否往数据库写信息
		attType : api.data.attType,//文件存储类型；1:数据库，0:磁盘，默认为磁盘
		workItem : api.data.workItem,//页面多点上传文件标识符号
		callback : function(uploadedFiles,uploadedFileIds){//回调函数
			if(api.data.callback)
				api.data.callback(uploadedFiles,uploadedFileIds);
			var checked = $("#oversave").attr("checked");
			if(api.data.remember=="1"&&checked=="checked"){
				if(api.data.finishSave){
					api.data.finishSave(uploadedFiles);
				}
			}
			api.close();
		},
		uploadProgress : function(file){//上传进度回调
			fileGridManager.updateCell("percent",file.percent,file);
			fileGridManager.updateCell("name",file.name,file);
		},
		filesAdded : function(files){
			var efiles = fileGridManager.getData();
			if(efiles.length>0){
				$.each(efiles,function(){
					removeAddedFile(this.id);
				});
			}
			fileGridManager.loadData({Rows:files});
		},
		queueChanged:function(files){
			if(files.length>0)
				$("#uploadFile").ligerButton().setEnabled();
			else
				$("#uploadFile").ligerButton().setDisabled();
		},
		startAction : function(fileUploader){
			$("#uploadFile").click(function(){
				if(fileUploader.files.length<1) return;
				fileUploader.start();
				//$("body").mask("上传进行中，请等待...");
			});
		},
		onError : function(code){
			$("body").unmask();
		},
	    uploadError : function(file){
	    	$.ligerDialog.error("上传文件【" + file.name + "】失败！");
	    	removeAddedFile(file.id);
	    	$("body").unmask();
	    }
	});

	//建表格
	createGrid();
});

//建表格
function createGrid(){
	fileGridManager = $("#file_grid").ligerGrid({
		columns: [
			{ display: 'id', name: 'id', hide: true}, 
			{ display: '文件名', name: 'name',width: 500, align:"left",render:function(row){
				return "<div class='upload-process'><div class='upload-process-fn'>" + row.name + "</div><div class='upload-process-bg' style='width:" 
						+ row.percent + "%'></div></div>";
			}},  
			{ display: '大小', name: 'size', width: 100, render:function(row){
				return plupload.formatSize(row.size);
			}},
			{ display: "上传状态", name:"percent",width:86,render:function(row){
				if(row.status==plupload.QUEUED)
					return "未上传";
				else if(row.status==plupload.UPLOADING)
					return "正在上传";
				else if(row.status==plupload.FAILED)
					return "上传失败";
				else if(row.status==plupload.DONE)
					return "上传成功";
				else
					return row.percent + "%";
			}},
			{ display: '操作', name: 'status', width: 50, render: function (row){
				return '<img src="k/kui/images/icons/16/delete.png" style="cursor:pointer;margin-top:3px;" onclick="removeAddedFile(\'' + row.id + '\')" />';
			}}
		],
		data:{Rows:[]},
		usePager : false,
		width : "100%",
		height : "100%"
	});
}

//根据文件ID删除文件对象
function removeAddedFile(id){
	var file = khFileUploader.fileUploaderRuntime.getFile(id);
	khFileUploader.fileUploaderRuntime.removeFile(file);
	fileGridManager.remove(file);
}
</script>
<style type="text/css">
.upload-process{height:100%;width:100%;position:relative;}
.upload-process-bg{position: absolute;left:0;top:0;height:94%;z-index:1;background:#7cbef1;margin:1px 0;}
.upload-process-fn{position: absolute;left:0;top:0;width:100%;height:100%;z-index:2;padding-left:5px;}
</style>
</head>
<body>
<div class="layout">
	<div position="top" id="file_grid"></div>
	<div position="center">
		<span style="margin-top:2px" id="remenber">
			<input type='checkbox' id='oversave'><label for="oversave">&nbsp;上传完成后,是否保存?</label>
		</span>
	</div>
	<div position="bottom" id="container" calss="toolbar-tm">
		<div id="button_div" class="toolbar-tm-bottom" style="text-align:left;"></div>
	</div>
</div>
</body>
</html>
