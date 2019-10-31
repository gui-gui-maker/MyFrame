<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="pub/fileupload/js/fileupload.js"></script>
<!-- fileupload-dialog.js弹窗附件 -->
<script type="text/javascript" src="pub/fileupload/js/fileupload-dialog.js"></script>

<script type="text/javascript">
	$(function() {
		$("#formObj").initForm({
			success : function(responseText) {//处理成功
				if (responseText.success) {
					top.$.notice("保存成功！");
					api.data.window.Qm.refreshGrid();
					api.close();
				} else {
					$.ligerDialog.error('保存失败' + responseText)
				}
			},
			getSuccess : function(response) {
				if (response.onefiles != null && response.onefiles != undefined)
					showAttachOneFile(response.onefiles);
				if (response.manyfiles != null && response.manyfiles != undefined)
					showAttachFile(response.manyfiles);
			}
		});
		if("${param.status}"=="detail"){
            $("#onefileFolderBtn").hide();
        }
	});
	//附件上传配置
	$(function() {
    	var oneUploadConfig = {
    			fileSize : "10mb",//文件大小限制
    			businessId : "",//业务ID
    			buttonId : "onefileBtn",//上传按钮ID
    			container : "onefileDIV",//上传控件容器ID
    			attType : "",//文件存储类型；1:数据库，0:磁盘，默认为磁盘
    			title : "图片选择",//文件选择框提示
    			extName : "jpg,jpeg,gif,bmp,png",//文件扩展名限制
    			fileNum : 1,//限制上传文件选择数目 弹出选择框 可以选择文件数量限制
    			workItem : "one",//页面多点上传文件标识符号
    			callback : function(files){//上传成功后回调函数,实现页面文件显示，交与业务自行后续处理
    				addOneFile(files);
    			}
    		};
		var oneUploader= new KHFileUploader(oneUploadConfig);
		
		var oneFolderUploadConfig = {
            fileSize : "10mb",//文件大小限制
            businessId : "",//业务ID
            folder: "busfolder\\alibaba",//业务定义的文件存储文件夹名称，此文件夹位于系统附件基目录下,多层目录用\\分割
            buttonId : "onefileFolderBtn",//上传按钮ID
            container : "onefileFolderDiv",//上传控件容器ID
            attType : "",//文件存储类型；1:数据库，0:磁盘，默认为磁盘
            title : "图片选择",//文件选择框提示
            extName : "jpg,jpeg,gif,bmp,png",//文件扩展名限制
            fileNum : 1,//限制上传文件选择数目 弹出选择框 可以选择文件数量限制
            workItem : "folder",//页面多点上传文件标识符号
            callback : function(file){//上传成功后回调函数,实现页面文件显示，交与业务自行后续处理
            	$("#fileFolder").append("<li id='"+file[0].id+"'>"+
                        "<div><a href='fileupload/downloadByObjId.do?id="+file[0].id+"'>"+file[0].name+"</a></div>"+
                        "<div class='l-icon-close progress' onclick='deleteUploadFile(\""+file[0].id+"\",\""+file[0].path+"\",this)'>&nbsp;</div>"+
                        "</li>");
            }
        };
		
        var oneFolderUpload= new KHFileUploader(oneFolderUploadConfig);
		
		var manyuloadConfig = {
    			fileSize : "500mb",//文件大小限制
    			businessId : "",//业务ID
    			buttonId : "manyfileBtn",//上传按钮ID
    			container : "manyfilesDIV",//上传控件容器ID
    			attType : "",//文件存储类型；1:数据库，0:磁盘，默认为磁盘
    			title : "*",//文件选择框提示
    			extName : "*",//文件扩展名限制
    			fileNum : -1,//限制上传文件数目 弹出选择框 可以选择文件数量限制-1无限制
    			workItem : "many",//页面多点上传文件标识符号
    			autoSave:"1",//是否记住上传后保存。0否 1是
    			onComplete:function(files){
    				$("#formObj").submit();
    			},//保存调用的方法
    			callback : function(files){//上传成功后回调函数,实现页面文件显示，交与业务自行后续处理
    				addAttachFile(files);
    			}
    		};
		var manyuploader= new KHFileUploader(manyuloadConfig);
		
		
		
		var dialogConfig = {
				fileSize: "700mb",//文件大小限制
				businessId: "",//业务ID
				title: "请选择图片",//文件选择框提示【可选，默认为'*'】
				extName: "*",//文件扩展名限制【可选，默认为'*'】
				saveDB: false,//是否往数据库写信息【可选，默认为'true'】
				attType: "",//文件存储类型；1:数据库，0:磁盘，默认为磁盘【可选，默认为''】
				workItem: "",
				fileNum: 1,
				autoSave:"1",//是否记住上传后保存。
				onComplete:function(files){
					$("#formObj").submit();
				},//保存调用的方法
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
			$("#pickfiles2").click(function () {
				dialogConfig.workItem = "item2";
				dialogConfig.fileNum = -1;
				dialogConfig.fileContainer = null;
				new KHDialogFileUploader(dialogConfig);
			});
		
		
		
	});
	
	//添加单文件处理
	function addOneFile(files){
		if(files){
			//判断单个文件是否存在
			var uploadFileId = $("#uploadFile").val();
			if(uploadFileId){
				//删除该id对应的
				$.getJSON('fileupload/deleteAtt.do?id=' + uploadFileId, function(resp) {
			    	if(resp.success){
			    		$("#" + uploadFileId).remove();
			    		getUploadOneFile();
			    	}
	    		});
			}
			var attContainerId="onefile";
			$.each(files,function(i){
				var file=files[i];
				$("#"+attContainerId).append("<li id='"+file.id+"'>"+
						"<div><a href='fileupload/downloadByObjId.do?id="+file.id+"'>"+file.name+"</a></div>"+
						"<div class='l-icon-close progress' onclick='deleteUploadFile(\""+file.id+"\",\""+file.path+"\",this,getUploadOneFile)'>&nbsp;</div>"+
						"</li>");
			});
			getUploadOneFile();
		}
	}
	
	
	//添加多个附件
	function addAttachFile(files){
		if("${param.status}"=="detail"){
			$("#manyfileBtn").hide();
		}
		if(files){
			var attContainerId="manyfiles";
			$.each(files,function(i){
				var file=files[i];
				$("#"+attContainerId).append("<li id='"+file.id+"'>"+
						"<div><a href='fileupload/downloadByObjId.do?id="+file.id+"'>"+file.name+"</a></div>"+
						"<div class='l-icon-close progress' onclick='deleteUploadFile(\""+file.id+"\",\""+file.path+"\",this,getUploadFile)'>&nbsp;</div>"+
						"</li>");
			});
			getUploadFile();
		}
	}
	
	//显示单个文件
    function showAttachOneFile(files){
    	if("${param.status}"=="detail"){
			$("#onefileBtn").hide();
		}
		if(files){
			//详情
			var attContainerId="onefile";
			if("${param.status}"=="detail"){
				$.each(files,function(i){
					var file=files[i];
					$("#"+attContainerId).append("<li id='"+file.id+"'>"+
											"<div><a href='fileupload/downloadByObjId.do?id="+file.id+"'>"+file.fileName+"</a></div>"+
											"</li>");
				});
			}
			//修改
			else if("${param.status}"=="modify"){
				$.each(files,function(i){
					var file=files[i];
					$("#"+attContainerId).append("<li id='"+file.id+"'>"+
							"<div><a href='fileupload/downloadByObjId.do?id="+file.id+"'>"+file.fileName+"</a></div>"+
							"<div class='l-icon-close progress' onclick='deleteUploadFile(\""+file.id+"\",\""+file.filePath+"\",this,getUploadOneFile)'>&nbsp;</div>"+
							"</li>");
				});
				getUploadOneFile();
			}
		}
    }
	//显示多个附件文件
    function showAttachFile(files){
    	if("${param.status}"=="detail"){
			$("#manyfileBtn").hide();
		}
		if(files){
			//详情
			var attContainerId="manyfiles";
			if("${param.status}"=="detail"){
				$.each(files,function(i){
					var file=files[i];
					$("#"+attContainerId).append("<li id='"+file.id+"'>"+
											"<div><a href='fileupload/downloadByObjId.do?id="+file.id+"'>"+file.fileName+"</a></div>"+
											"</li>");
				});
			}
			//修改
			else if("${param.status}"=="modify"){
				$.each(files,function(i){
					var file=files[i];
					$("#"+attContainerId).append("<li id='"+file.id+"'>"+
							"<div><a href='fileupload/downloadByObjId.do?id="+file.id+"'>"+file.fileName+"</a></div>"+
							"<div class='l-icon-close progress' onclick='deleteUploadFile(\""+file.id+"\",\""+file.filePath+"\",this,getUploadFile)'>&nbsp;</div>"+
							"</li>");
				});
				getUploadFile();
			}
		}
    }
  	//将上传的所有文件id写入隐藏框中
	function getUploadFile(){
		var attachId="";
		var i=0;
		$("#manyfiles li").each(function(){
			attachId+=(i==0?"":",")+this.id;
			i=i+1;
		});
		if(attachId!=""){
			attachId=attachId.substring(0,attachId.length);
		}
		$("#uploadFiles").val(attachId);
	}
	//将上传的所有文件id写入隐藏框中
	function getUploadOneFile(){
		var attachId="";
		var i=0;
		$("#onefile li").each(function(){
			attachId+=(i==0?"":",")+this.id;
			i=i+1;
		});
		if(attachId!=""){
			attachId=attachId.substring(0,attachId.length);
		}
		$("#uploadFile").val(attachId);
	}
</script>
</head>
<body>
	<form name="formObj" id="formObj" method="post"
		action="demo/upload/savefiles.do"
		getAction="demo/upload/detail.do?id=${param.id}">
		<input name="id" type="hidden" id="id" ltype='hidden' />
		<input name="uploadFiles" type="hidden" id="uploadFiles" />
		<input name="uploadFile" type="hidden" id="uploadFile" />
		<table border="1" cellpadding="3" cellspacing="0"
			class="l-detail-table">
			<tr>
				<td class="l-t-td-left" style="width:200px;">标题：</td>
				<td class="l-t-td-right"><input name="title" type="text" ltype='text' validate="{required:true,maxlength:256}" /></td>
			</tr>
			<tr>
				<td class="l-t-td-left">附件（一个文件）：</td>
				<td class="l-t-td-right">
					<p id="onefileDIV">
						<a class="l-button" id="onefileBtn"><span
							class="l-button-main"><span class="l-button-text">选择文件</span>
						</span>
						</a>
					</p>
					<div class="l-upload-ok-list">
						<ul id="onefile"></ul>
					</div></td>
			</tr>
			<tr>
                <td class="l-t-td-left">一个文件，指定文件存储文件夹：</td>
                <td class="l-t-td-right">
                    <p id="onefileFolderDiv">
                        <a class="l-button" id="onefileFolderBtn"><span
                            class="l-button-main"><span class="l-button-text">选择文件</span>
                        </span>
                        </a>
                    </p>
                    <div class="l-upload-ok-list">
                        <ul id="fileFolder"></ul>
                    </div></td>
            </tr>
			<tr>
				<td class="l-t-td-left">附件（多个文件）：</td>
				<td class="l-t-td-right">
					<p id="manyfilesDIV">
						<a class="l-button" id="manyfileBtn"><span
							class="l-button-main"><span class="l-button-text">选择文件</span>
						</span>
						</a>
					</p>
					<div class="l-upload-ok-list">
						<ul id="manyfiles"></ul>
					</div></td>
			</tr>
			
			<tr>
			
			<td class="l-t-td-left">弹窗附件：</td>
			<td class="l-t-td-right">
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
			</td>
			</tr>
		</table>
	</form>
</body>
</html>