//����һ���ϴ�ʵ��
function KHFileUploader(config){
	if(!window.plupload){
		$("head").append('<script type="text/javascript" src="pub/fileupload/js/plupload.js"></script>' +
			'<script type="text/javascript" src="pub/fileupload/js/plupload.flash.js"></script>' +
			'<script type="text/javascript" src="pub/fileupload/js/plupload.html5.js"></script>');
	}
	
	this.fileUploaderRuntime = null;
	this.uploadRuntime = "html5,flash";
	this.uploadedFiles = [];
	var khUploader = this;
	
	//Ĭ�ϵ�����
	this.uploadConfig = {
		fileId : "",//�ļ�ID
		fileSize : "10mb",//�ļ���С����
		businessId : "",//ҵ��ID
		busUniqueName : "",//Ψһҵ������
		buttonId : "pickfiles",//�ϴ���ťID
		container : "container",//�ϴ��ؼ�����ID
		title : "*",//�ļ�ѡ�����ʾ
		extName : "*",//�ļ���չ������
		saveDB : true,//�Ƿ������ݿ�д��Ϣ
		attType : "",//�ļ��洢���ͣ�1:���ݿ⣬0:���̣�Ĭ��Ϊ����
		workItem : "",//ҳ�����ϴ��ļ���ʶ����
		fileNum : 1,//�����ϴ��ļ���Ŀ
		callback : function(files){},//�ϴ��ɹ���ص�����
		fileUploaded : function(file){},//���ļ��ϴ��ص�
		uploadProgress : function(file){},//�ϴ����Ȼص���file={id:'',name:'',size:123,percent:33,status:''}
		filesAdded : function(files){},//����ļ��¼�,��������ϴ�һ���ļ���filesΪ�����ļ�������Ϊѡ����ļ�����
		queueChanged : null,//�ϴ����з����仯,
		startAction : function(uploader){},//�ⲿ�����ϴ��¼�
		onError : function(errCoee){},//������Ϣ
		uploadError : function(file){}//�ϴ�ʧ��
	};
	if(config){
		$.each(config,function(k,v){
			if(v) khUploader.uploadConfig[k] = v;
		});
	}
	this.fileUploaderRuntime = new plupload.Uploader({
		runtimes : this.uploadRuntime,
		browse_button : this.uploadConfig.buttonId,
		container: this.uploadConfig.container,
		max_file_size : this.uploadConfig.fileSize,
		url : kFrameConfig.base + "fileupload/fileUp.do?businessId=" + this.uploadConfig.businessId 
				+ "&saveDB=" + this.uploadConfig.saveDB 
				+ "&attType=" + this.uploadConfig.attType 
				+ "&busUniqueName=" + this.uploadConfig.busUniqueName
				+ "&workItem=" + this.uploadConfig.workItem
				+ "&fileId=" + this.uploadConfig.fileId,
		flash_swf_url : kFrameConfig.base + 'pub/fileupload/js/plupload.flash.swf?ddd='+Math.random(),
		filters : [
			{title : this.uploadConfig.title, extensions : this.uploadConfig.extName}
		]
	});
	
	this.setParams = function(param){
		var url = kFrameConfig.base + "fileupload/fileUp.do?_r=0";
		if(param.businessId)
			url += "&businessId=" + param.businessId;
		if(param.saveDB)
			url += "&saveDB=" + param.saveDB;
		if(param.attType)
			url += "&attType=" + param.attType;
		if(param.workItem)
			url += "&workItem=" + param.workItem;
		if(param.busUniqueName)
			url += "&busUniqueName=" + param.busUniqueName;
		this.fileUploaderRuntime.settings.url = url;
	}
	
	// ----------------------------------����Ϊ���¼�----------------------------------------
	
	//�ⲿ�����ϴ�
	this.uploadConfig.startAction(this.fileUploaderRuntime);
	
	//����ļ��¼�
	this.fileUploaderRuntime.bind('FilesAdded', function(uploader, files) {
		if(khUploader.uploadConfig.fileNum > 0 && files.length > khUploader.uploadConfig.fileNum){
			$.ligerDialog.alert("ѡ����ļ�̫�࣬���������ƣ�<br/>��ʾ���������ϴ���<b style='color:red'>" + khUploader.uploadConfig.fileNum + "</b>�����ļ���");
			return false;
		}
	});

	//�ļ��б����仯
	this.fileUploaderRuntime.bind('QueueChanged',function(uploader) {//�ϴ����з����仯
		khUploader.uploadConfig.filesAdded(uploader.files);
		if(khUploader.uploadConfig.queueChanged)
			khUploader.uploadConfig.queueChanged(uploader.files);
		else uploader.start();
	});
	
	//�����ļ��ϴ����
	this.fileUploaderRuntime.bind('FileUploaded', function(uploader,file,resObj) {
		var json = $.parseJSON(resObj.response);
		if(json.success){//�ϴ��ɹ�
			var fobj = json.data;
			fobj.upId = file.id;
			//{"id":json.data.id,"name":file.name,"path":json.data.path,"workItem":json.data.workItem,"upId":file.id};
			khUploader.uploadedFiles.push(fobj);
			khUploader.uploadConfig.fileUploaded(fobj);
		}
		else{//�ϴ�ʧ��
			khUploader.fileUploaderRuntime.stop();
			khUploader.uploadConfig.uploadError(file);
			khUploader.fileUploaderRuntime.refresh();
		}
	});
	
	//�����ļ��ϴ����
	this.fileUploaderRuntime.bind("UploadComplete",function(uploader,files){
		khUploader.uploadConfig.callback(khUploader.uploadedFiles);
		khUploader.uploadedFiles = [];
	});

	//�ļ��ϴ����ȱ仯
	this.fileUploaderRuntime.bind("UploadProgress",function(uploader,file){
		khUploader.uploadConfig.uploadProgress(file);
	});
	
	//��������ʱ
	this.fileUploaderRuntime.bind('Error', function(uploader,error) {
		if(error.code == plupload.FILE_SIZE_ERROR){
			$.ligerDialog.alert("��ѡ���ļ���" + error.file.name + "��̫�󣬳���������(" + uploader.settings.max_file_size/1024 + "KB)��");
		}
		else if(error.code == plupload.FILE_EXTENSION_ERROR){
			$.ligerDialog.alert("�ļ����ʹ���<br/>��ʾ������ѡ����ļ�����Ϊ<" + uploader.settings.filters[0].extensions + ">");
		}
		else if(error.code == plupload.INIT_ERROR){
			$.ligerDialog.alert("ҳ����ش���<br/>������ˢ�±�ҳ�波�Խ�����⡣");
		}
		else{
			$.ligerDialog.alert("��������" + error.code);
		}
		uploader.refresh();
	});

	this.fileUploaderRuntime.init();
}

function KHKuiFileuploader(config){
	if(!config.fileContainer){
		$.ligerDialog.alert("��ָ�������������������ƣ�fileContainer");
		return;
	}
	var myConfig = {
		fileContainer : config.fileContainer,
		fileSize : config.fileSize?config.fileSize:"100mb",
		businessId : config.businessId?config.businessId:"",
		title : config.title?config.title:"ͼƬ",
		extName :  config.extName?config.extName:"",
		saveDB : config.saveDB?config.saveDB:false,
		attType : config.attType?config.attType:"",
		workItem : config.workItem?config.workItem:"",
		fileNum : config.fileNum?config.fileNum:-1,
		callback : config.callback?config.callback:function(file){}
	};
	
	myConfig.fileUploaded = function(file){//�����ص�����
		if(file){
			$("#" + file.upId).find(".progress").addClass("l-icon-close").click(function(){
				deleteUploadFile(file.id,file.path,this,function(){
					if(config.delCallback){
						config.delCallback(file);
					}
				});
			});
			/*$("#" + file.upId).find("a").attr("href","fileupload/downloadByFilePath.do?path="+ file.path + "&fileName=" + file.name);*/
			$("#" + file.upId).find("a").attr("href","fileupload/download.do?id="+ file.id + "&fileName=" + file.name);
		}
	};
	
	myConfig.uploadProgress = function(file){//�ϴ����Ȼص�
		if(file.percent==100)
			$("#" + file.id).find(".progress").html("&nbsp;");
		else
			$("#" + file.id).find(".progress").html("<span>"+ file.percent + "%<span>");
	};
	
	myConfig.filesAdded = function(file){//����ļ����ϴ������¼�
		var htmlStr = "";
		if($.isArray(file)){
			$.each(file,function(i){
				htmlStr += '<li id="' + file[i].id + '"><div><a href="javascript:void();">' + file[i].name 
					+ '</a></div><div class="progress">&nbsp;</div></li>';
			});
		}
		else{
			htmlStr = '<li id="' + file.id + '"><div><a href="javascript:void();">' + file.name 
				+ '</a></div><div class="progress">&nbsp;</div></li>';
		}
		$("#" + myConfig.fileContainer).find(".file_list").append(htmlStr);
	};
	
	myConfig.container = myConfig.fileContainer + "_container";
	myConfig.buttonId = myConfig.fileContainer + "_pickfiles";
	
	//�����ϴ�����
	$("#" + myConfig.fileContainer).append('<p id="' + myConfig.container + '"><a class="l-button" id="' + myConfig.buttonId 
			+ '"><span class="l-button-main"><span class="l-button-text">ѡ���ļ�</span></span></a></p>'
			+ '<div class="l-upload-ok-list"><ul class="file_list"></ul></div>');
	return new KHFileUploader(myConfig);
}

//ɾ���������ļ�
function deleteUploadFile(id, path,obj, callback) {
    $.ligerDialog.confirm("ȷ��ɾ���ļ���<p style='color:red'>ע�⣺ɾ���󲻿ɻָ���</p>", function(flag) {
		if(flag){
			$("body").mask("����ɾ�������Ժ󡣡���");
		    $.getJSON('fileupload/deleteAtt.do',{"id":id,"path":path}, function(resp) {
		    	if(resp.success){
		    		if(obj){
		    			$(obj).parent().remove();
		    		}
		    		if(callback){
		    			callback(id,path);
		    		}
					$("body").unmask();
		    	}
		    	else{
					$("body").unmask();
		    		alert("ɾ��ʧ�ܣ�");
		    	}
		    });
		}
    });
}
