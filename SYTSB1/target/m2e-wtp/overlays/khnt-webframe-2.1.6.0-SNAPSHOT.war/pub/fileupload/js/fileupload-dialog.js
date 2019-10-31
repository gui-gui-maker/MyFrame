//删除上传文件
function deleteUploadFile(id, path,obj, callback) {
    $.ligerDialog.confirm("确定删除文件？<p style='color:red'>注意：删除后不可恢复！</p>", function(flag) {
		if(flag){
		    $.getJSON('fileupload/deleteAtt.do',{id:id,path:path}, function(resp) {
	    		if(obj){
	    			$(obj).parent().remove();
	    		}
		    	if(resp.success){
		    		if(callback) callback(id,path);
		    	}
		    	else{
		    		alert("删除失败！");
		    	}
		    });
		}
    });
}

/**
 * json方式配置上传参数
 */
function KHDialogFileUploader(config){
	//新窗口上传文件参数
	this.pluploadParam = {
	    window : window,//新窗口回调使用的当前window
	    extName : "*",//过滤文件类型
	    title : "*",//过滤文件类型说明，选择文件时用于窗口提示
	    sid : "", //业务id
	    maxSize : "10mb",//文件大小限制
	    attType : "",//文件存储类型：1:数据库，0:磁盘，默认为磁盘
	    saveDB : true,//是否存储附件信息到数据库
	    fileNum : -1,//文件数量，-1为不限
	    workItem : "",//多点上传标识
    	remember:"0",//是否记住上传后保存。
    	finishSave:function(files){}//保存调用的方法
	};
	var uploader = this;
	$.each(config,function(k,v){
		if(v) uploader.pluploadParam[k] = v;
	});
	
	//上传成功
	this.pluploadParam.callback = function(uploadedFiles,uploadedFileIds){
		//指定页面文件显示容器时，把文件列出
		if(config.fileContainer && uploadedFiles.length > 0){
			var htmlStr = '<div class="l-upload-ok-list"><ul class="file_list">';
			$.each(uploadedFiles,function(i){
				htmlStr += '<li><div><a href="fileupload/downloadByFilePath.do?path='+ uploadedFiles.path 
					+ '&fileName=' + uploadedFiles.name + '">' + uploadedFiles[i].name 
					+ '</a></div><div class="l-icon-close progress" onclick="deleteUploadFile(\''
					+ uploadedFiles[i].id + '\',\'' + uploadedFiles[i].path + '\',this)">&nbsp;</div></li>';
			});
			htmlStr += "</ul></div>";
			$("#" + config.fileContainer).append(htmlStr);
		}
		
		if(config.callback)config.callback(uploadedFiles,uploadedFileIds);
	}
	
	//打开上传窗口
	top.$.dialog({
		width : 750,
		height : 350,
		lock : true,
		parent : api,
		max: false,
	    min: false,
		title : "上传文件",
		content : "url:pub/fileupload/kui-fileupload.jsp",
		data : this.pluploadParam
    });
}
