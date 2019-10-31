document.write('<link rel="stylesheet" href="k/libs/poshytip/tip-violet/tip-violet.css" type="text/css" />'
		+'<link rel="stylesheet" type="text/css" href="pub/fileupload/css/fileupload.css" />'
		+'<script type="text/javascript" src="k/libs/poshytip/jquery.poshytip.min.js"></script>');

//创建一个上传实例
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
	//默认的配置
	this.uploadConfig = {
		fileId : "",//文件ID
		fileSize : "10mb",//文件大小限制
		businessId : "",//业务ID
		busUniqueName : "",//唯一业务名称
		folder: "",//业务指定文件存储目录
		buttonId : "pickfiles",//上传按钮ID
		container : "container",//上传控件容器ID
		fname : "",//自定义的存储文件名称
		title : "*",//文件选择框提示
		extName : "*",//文件扩展名限制
		useThirdDevice: false,//是否允许使用第三方扩展设备扫二维码上传
		thirdUploadTitle: "",
		saveDB : true,//是否往数据库写信息
		attType : "",//文件存储类型；1:数据库，0:磁盘，默认为磁盘
		workItem : "",//页面多点上传文件标识符号
		fileNum : 1,//限制上传文件数目
		remember: "0",//是否记住上传后保存。
		finishSave: function(files){},//保存调用的方法
		callback : function(files){},//上传成功后回调函数
		thirdUploadCallback: function(){},//第三方设备上传回调
		fileUploaded : function(file){},//单文件上传回调
		uploadProgress : function(file){},//上传进度回调，file={id:'',name:'',size:123,percent:33,status:''}
		filesAdded : function(files){},//添加文件事件,如果限制上传一个文件，files为单个文件，否则为选择的文件数组
		queueChanged : null,//上传队列发生变化,
		startAction : function(uploader){},//外部启动上传事件
		onError : function(errCoee){},//错误信息
		uploadError : function(file){}//上传失败
	};
	if(config){
		this.uploadConfig = $.extend(this.uploadConfig,config);
		if(this.uploadConfig.folder && this.uploadConfig.folder.indexOf("/")){
			this.uploadConfig.folder=this.uploadConfig.folder.replace(/\//gi,"_._");
		}
		/*$.each(config,function(k,v){
			if(v) khUploader.uploadConfig[k] = v;
		});*/
	}
	this.fileUploaderRuntime = new plupload.Uploader({
		runtimes : this.uploadRuntime,
		browse_button : this.uploadConfig.buttonId,
		container: this.uploadConfig.container,
		max_file_size : this.uploadConfig.fileSize,
		url : kFrameConfig.base + "fileupload/upload.do",
		flash_swf_url : kFrameConfig.base + 'pub/fileupload/js/plupload.flash.swf?ddd='+Math.random(),
		filters : [
			{title : this.uploadConfig.title, extensions : this.uploadConfig.extName}
		],
		multipart_params: {
			businessId: this.uploadConfig.businessId,
			saveDB: this.uploadConfig.saveDB,
			attType: this.uploadConfig.attType,
			folder: this.uploadConfig.folder,
			busUniqueName: this.uploadConfig.busUniqueName,
			workItem: this.uploadConfig.workItem,
			fileId: this.uploadConfig.fileId,
			fname: this.uploadConfig.fname
		}
	});
	
	this.setParams = function(params){
		var mparams = this.fileUploaderRuntime.settings.multipart_params;
		mparams = $.extend(mparams,params);
		this.fileUploaderRuntime.settings.multipart_params = mparams;
		
		/*if(param.businessId)
			url += "&businessId=" + param.businessId;
		if(param.saveDB)
			url += "&saveDB=" + param.saveDB;
		if(param.folder)
			url += "&folder=" + param.folder;
		if(param.attType)
			url += "&attType=" + param.attType;
		if(param.workItem)
			url += "&workItem=" + param.workItem;
		if(param.busUniqueName)
			url += "&busUniqueName=" + param.busUniqueName;
		if(param.fname)
			url += "&fname=" + param.fname;
		.url = url;*/
	}
	
	this.start = function(){
		khUploader.fileUploaderRuntime.start();
	}
	this.destory = function(){
		khUploader.fileUploaderRuntime.destroy();
	}
	this.destroy = this.destory;
	
	// ----------------------------------以下为绑定事件----------------------------------------
	
	//外部触发上传
	this.uploadConfig.startAction(this.fileUploaderRuntime);
	
	//添加文件事件
	this.fileUploaderRuntime.bind('FilesAdded', function(uploader, files) {
		if(khUploader.uploadConfig.fileNum > 0 && files.length > khUploader.uploadConfig.fileNum){
			$.ligerDialog.alert("选择的文件太多，超过了限制！<br/>提示：您最多可上传【<b style='color:red'>" + khUploader.uploadConfig.fileNum + "</b>】个文件！");
			return false;
		}
		showProcessMsg(khUploader);
		if(khUploader.uploadConfig.filesAdded)
			return khUploader.uploadConfig.filesAdded(files);
	});

	//文件列表发生变化
	this.fileUploaderRuntime.bind('QueueChanged',function(uploader) {//上传队列发生变化
		if(khUploader.uploadConfig.queueChanged)
			khUploader.uploadConfig.queueChanged(uploader.files);
		else uploader.start();
	});
	
	//单个文件上传完成
	this.fileUploaderRuntime.bind('FileUploaded', function(uploader,file,resObj) {
		var json = $.parseJSON(resObj.response);
		if(json.success){//上传成功
			var fobj = json.data;
			fobj.upId = file.id;
			// 将上传的结果id放入某个指定的input元素中
			if(khUploader.uploadConfig["resultIdField"]){
			    var rstField = $("#"+khUploader.uploadConfig["resultIdField"]);
			    rstField.val((rstField.val() + "," + fobj.id).replace(/^,|,$/),"");
			}
			//{"id":json.data.id,"name":file.name,"path":json.data.path,"workItem":json.data.workItem,"upId":file.id};
			khUploader.uploadedFiles.push(fobj);
			khUploader.uploadConfig.fileUploaded(fobj);
		}
		else{//上传失败
			khUploader.fileUploaderRuntime.stop();
			khUploader.uploadConfig.uploadError(file);
			khUploader.fileUploaderRuntime.refresh();
			if(json.msg) $.ligerDialog.error(stringToEntity(json.msg));
			file["extStatus"]=plupload.FAILED;
			
			//将已上传的文件删除
			$.each(khUploader.uploadedFiles,function(){
				$.getJSON('fileupload/deleteAtt.do?id='+this.id);
			});
		}
	});

    //所有文件上传完成
    this.fileUploaderRuntime.bind("UploadComplete",function(uploader,files){
        $("body").unmask();
        khUploader.uploadConfig.callback(khUploader.uploadedFiles,khUploader.uploadConfig);
        khUploader.uploadedFiles = [];
        //上传文成处理事件 调用保存事件
        if(khUploader.uploadConfig.autoSave==1&&$("#issave").attr("checked")=="checked"){
            khUploader.uploadConfig.onComplete(khUploader.uploadedFiles);
        }
    });
    //文件上传进度变化
    window._isAutoSave=false;
    this.fileUploaderRuntime.bind("UploadProgress",function(uploader,file){
        khUploader.uploadConfig.uploadProgress(file);
        if(file["extStatus"] && file["extStatus"]==plupload.FAILED){
            $("body").unmask();
        	return;
        }
        var html = "正在上传【" + stringToEntity(file.name) + "】，完成 <span style='color: red; '>" + file.percent + "%</span>";
        if($("#_uploadProgressDiv").size()==0){
            $("#processDiv").html(html);
        }
        //todo 对原来代码注入性太强
        $("#_global_mask_div").show();
        $("#_global_mask_div_loading").show();
        $("#_uploadProgressDiv").html(html);
    });
    
	//发生错误时
	this.fileUploaderRuntime.bind('Error', function(uploader,error) {
		if(error.code == plupload.FILE_SIZE_ERROR){
			$.ligerDialog.alert("所选的文件【" + stringToEntity(error.file.name) + "】太大，超过了限制(" + uploader.settings.max_file_size/1024 + "KB)！");
		}
		else if(error.code == plupload.FILE_EXTENSION_ERROR){
			$.ligerDialog.alert("文件类型错误！<br/>提示：可以选择的文件类型为:"+uploader.settings.filters[0].extensions);
		}
		else if(error.code == plupload.INIT_ERROR){
			$.ligerDialog.alert("页面加载错误！<br/>您可以刷新本页面尝试解决问题。");
		}
		else{
			$.ligerDialog.alert("发生错误：" + error.code);
		}
		uploader.refresh();
        $("#_uploadProgressDiv").empty();
        $("body").unmask();
	});
	
	this.fileUploaderRuntime.init();

	//允许扫码上传
	if(this.uploadConfig.useThirdDevice){
		var $ubtn = $("#"+this.uploadConfig.buttonId).poshytip({
			alignTo: 'target',
			alignX: 'right',
			alignY: 'center',
			offsetX: 0,
			offsetY: 0,
			content: '<a id="' + this.uploadConfig.container 
				+ '-qrcode-picker-btn" class="third_upload_qrcode_picker"><span class="l-icon l-icon-qrcode" title="文件在手机上？您还可以点这里用手机APP扫码上传"></span></a>'
		});
		$("#" + this.uploadConfig.container + "-qrcode-picker-btn").live("click",function(){
			if($(".third_upload_qrcode_div").length > 0)return;
			$ubtn.poshytip("hide");
			$.post("service/upload/srv/start_third_upload.do?_r="+Math.random(),{
				title: khUploader.uploadConfig.thirdUploadTitle,
				workitem: khUploader.uploadConfig.workItem
			},function(response){
				if(!response.success){
					$.ligerDialog.alert("生成二维码失败！");
				}
				khUploader.openThirdUploadQrcode(response.data.qrcode,response.data.scode);
			},"json");
		});
	}
	
	this.startThirdUploadRollingCall = function(scode){
		window.setTimeout(function(){
			$.getJSON("service/upload/srv/rolling_get_files.do?scode="+scode,function(response){
				if(!response.success){
					$("#scode" + scode + " .qrcode").text("二维码已失效，请重新打开");
					return;
				}
				if(response.status >= 1 && (!response.data || response.data.length==0)){
					$(".qrcode_upload_tips").html('<img class="tips-icon" src="k/kui/images/indicator.gif"/>准备接收文件');
				}
				
				if(response.data && response.data.length > 0){
					var callfile;
					if(khUploader.uploadConfig.fileNum > 0 && khUploader.uploadConfig.fileNum<response.data.length){
						$("#third_upload_qrcode_close").click();
						$.ligerDialog.alert("文件上传过多，系统将会截取前【" + khUploader.uploadConfig.fileNum + "】个文件");
						callfile = [response.data[0]];
					}else{
						callfile = response.data;
					}
					$(".qrcode_upload_tips").html('<img class="tips-icon" src="k/kui/images/icons/16/check.png"/>已接收【' + callfile.length + '】个文件');
					if(khUploader.uploadConfig.thirdUploadCallback)
						khUploader.uploadConfig.thirdUploadCallback(callfile,scode);
							
				}
				khUploader.startThirdUploadRollingCall(scode);
			});
		},3000);
	};
	
	this.openThirdUploadQrcode = function(encrypt,scode){
		var htmlstr = $("<div class='third_upload_qrcode_div' id='scode" + scode 
				+ "'><div class='head'>扫码上传<a class='close' id='third_upload_qrcode_close'>X</a></div><div class='qrcode'><img style='border:none;width:180px;height:180px;' src='data:image/png;base64," 
				+ encrypt + "'/></div><div class='qrcode_upload_tips'>请通过手机、平板电脑的客户端APP扫描上面的二维码上传文件</div></div>");
		htmlstr.find("a.close").click(function(){
			$.getJSON("service/upload/srv/cancel_upload.do?scode="+scode);
			$(".third_upload_qrcode_bg_div").remove();
			$(".third_upload_qrcode_div").remove();
		});
		htmlstr.css({"left":$(window).width()/2-100,"right":$(window).height()/2-150});
		$("body").append(htmlstr);
		khUploader.startThirdUploadRollingCall(scode);
	};
}

/*
 
  使用jquery plugin的方式实现上传功能。对原有的上传功能进一步封装，实现简单调用。

【使用及参数说明：】
--------------------------------------------------------------------------------------------------------------
使用jquery方法进行初始化，并获取空间对象（myUploader）
var myUploader = $("#element id").khUpload(params);
 	 1、参数element id 为上传区域的容器对象id
     2、参数params：
      {
    		fileSize : "10mb",//文件大小限制，默认10mb
			extName : "jpg,gif,jpeg,png,bmp,doc,pdf,docx,xlsx,xls,ppt,pptx,txt,rar,zip,7z",//文件扩展名限制，默认为*
			title : "*",//文件选择框提示文字
			fileNum : -1,//限制上传文件数目，默认-1
			useThirdDevice: true,//是否使用APP上传，默认为false
			thirdUploadTitle: "",//app上传提示文字
			edit: true, //是否编辑状态，默认使用页面<head>标签的pageStatus属性值
			preview: true,//是否使用小图片预览展示，默认为true
			container: 'xxxx-container-id',//自定义的上传plupload控件容器，默认为随机生成
			buttonId: 'xxxx-btn-id',//自定义的上传按钮id，默认为随机生成
			businessId: '业务ID', //，提供了业务id时，初始化后会自动加载次业务ID之前所有上传的文件，这在修改业务信息时有用
			workItem : "",//业务多点上传文件标识
			busUniqueName : "",//唯一业务名称
			folder: "",//业务指定文件存储目录
			fname : "",//自定义的存储文件名称
			saveDB : true,//是否往数据库写信息
			attType : "0"//文件存储类型；1:数据库，0:磁盘，默认为磁盘，在2.x平台版本之后，已不再写数据库，统一为磁盘
	   }

	所有参数均为可选，但是诸如文件大小、类型、数量这些应该还是有必要进行设定。APP上传也可以尽可能地使用（前提是移动设备可链接到服务器）。	   

【可用的API：】	   
--------------------------------------------------------------------------------------------------------------
   1、【属性】uploadFiles：
		在上传过程中，业务不再需要使用回调函数处理，只需在必要的时候通过 myuploader.uploadFiles属性直接获取上传的文件数组即可。
   2、【方法】getUploadFileIdNames：
   		为了方便业务，还可以直接使用myuploader.getUploadFileIdNames()获取到所有附件的id和name的字符串连接结果（以逗号","分割）。
   3、【方法】loadFiles：
   		大多时候，业务只需要在页面初始化后进行一次初始化，然后通过myuploader.loadFiles(businessId)方法，加载之前已经上传过的文件
 */
(function($){
	$.KhUpload = function(elm,options){
		this.$elm = $(elm);
		this.uploader = null;
		this.uploadedFiles = [];
		var $this = this;
		this.opts = $.extend({},options,{
			callback : function(files){
				$this._showFiles(files);
			},
			filesAdded: function(files){
				//检查上传文件的数量限制
				if($this.opts.fileNum>-1){
					if(files.length + $this.uploadedFiles.length > $this.opts.fileNum){
						$.ligerDialog.alert("选择的文件太多，超过了限制！<br/>提示：您最多可上传【" + $this.opts.fileNum + "】个文件！");
						return false;
					}
				}
				return true;
			}
		});
		this.init();
	};
	
	$.KhUpload.prototype = {
		// 删除文件处理，需要从已上传文件集合中移除文件
		_removeUploadFile : function(fid){
			var newFileArr = [];
			$.each(this.uploadedFiles,function(){
				if(this.id != fid){
					newFileArr.push(this);
				}
			});
			this.uploadedFiles = newFileArr;
		},
		// 展示已上传文件，同时更新已上传文件数据集合
		_showFiles : function(fs,delCall){
			// 将新添加的文件信息追加到集合中
			var nfs = [];
			for(var j in fs){
				var isExists = false;
				for(var i in this.uploadedFiles){
					if(this.uploadedFiles[i].id==fs[j].id)
						isExists = true;
				}
				if(!isExists) nfs.push(fs[j]);
			}
			this.uploadedFiles = this.uploadedFiles.concat(nfs);
			var $this = this;
			createFileViewList({
		        files: nfs,
		        print: this.opts.print,
		        edit: this.opts.edit,
		        ctrId: this.opts.container + "-files",
		        preview: this.opts.preview,
		        callback: function(fid){
		        	$this._removeUploadFile(fid);
		        	if(delCall) delCall(fid);
		        }
			});
		},
		// 加载所有已上传文件
		loadFiles : function(param1,param2){
			if($.type(param1) === "string"){
				this.loadFilesWithBusId(param1,param2);
			}else if($.isArray(param1)){
				this.loadFilesWithDataArray(param1);
			}else if($.isPlainObject(param1)){
				this.loadFilesWithDataArray([param1]);
			}
		},
		loadFilesWithBusId: function(busId,item){
			var $this = this;
			getBusinessAttachmentsByItem(busId,item,function(fs){
				if(fs.length < 1) return;
				$this._showFiles(fs);
			});
		},
		loadFilesWithDataArray: function(fs){
			if(fs.length < 1) return;
			$this._showFiles(fs);
		},
		getUploadFileIdNames : function(){
			var returnVal = {id:"",name:""};
			if(this.uploadedFiles.length>0){
				$.each(this.uploadedFiles,function(){
					returnVal.id += "," + this.id;
					returnVal.name += "," + this.name;
				});
				returnVal.id = returnVal.id.substring(1);
				returnVal.name = returnVal.name.substring(1);
			}
			return returnVal;
		},
		init: function(){
			this.$elm.data('khUpload', this);
			$this = this;
			
			// 未指定容器、按钮，动态创建
			if(!this.opts["buttonId"]){
				var random = new Date().getTime();
				this.opts = $.extend({},this.opts,{
					buttonId : "plupload-add-button-" + random,
					container : "plupload-container-" + random
				});
			}
			
			//第三方上传处理
		    if(this.opts["useThirdDevice"]){
				this.opts.thirdUploadCallback = function(fs,scode){
					var newfiles = [];
					$.each(fs,function(){
						if($("#"+this.id).size()==0) newfiles.push(this);
					});
					if(newfiles.length==0) return;
					$this._showFiles(newfiles, function(fid){
						$.post("service/upload/srv/delete_file.do?ids=" + fid + "&scode=" + scode,"json");
					});
				}
		    }
		    
		    // 建立容器
		    var $container = $("#" + this.opts.container);
		    if($container.size()==0) $container = $('<div id="' + this.opts.container + '"></div>').appendTo(this.$elm);
		    if(this.opts.edit){
		    	if($("#" + this.opts.buttonId).size()==0){
					var buttonHtml = this.opts.preview ? 
							'<div class="upload-picker-button-block" id="' + this.opts.buttonId + '">+</div>'
							: '<a class="l-button" id="' + this.opts.buttonId + '" style="z-index:0;float:left;"><span class="l-button-main"><span class="l-button-text">选择文件</span></span></a>';
		    		$container.append(buttonHtml);
		    	} 
		    	// edit为true时进行plupload控件初始化
		    	this.uploader = new KHFileUploader(this.opts);
		    }
		    
		    // 文件列表展示框
		    if($("#" + this.opts.container + "-files").size()==0)
		    	$container.append('<ul class="upload-file-list l-upload-ok-list" id="' + this.opts.container + '-files"></ul>');
		    
		    // 加载已经上传的文件
		    if(this.opts["businessId"]){
				this.loadFiles(this.opts["businessId"],this.opts["workItem"]);
			}
		}
	};
	
	$.fn.khUpload = function(options) {
		if(!options){
			return $(this).data('khUpload');
		}
		
		if (typeof options == 'string') {
			var khUpload = $(this).data('khUpload');
			if (khUpload && khUpload[options])
				return khUpload[options]();
			else return;
		}
		
		var opts = $.extend({},$.fn.khUpload.defaults,options);

		return new $.KhUpload(this, opts);;
	};

	$.fn.khUpload.defaults = {
		fileSize: "10mb",
		extName: "*",
		fileNum : -1,
		useThirdDevice: true,
	    preview: true,
	    edit: $("head").attr("pageStatus")=="add" || $("head").attr("pageStatus")=="modify" || $("head").attr("pageStatus")=="edit"
	};
})(jQuery);

function stringToEntity(str,radix){
  let arr=[]
  // 返回的字符实体默认10进制，也可以选择16进制
  radix=radix||0
  for(let i=0;i<str.length;i++){                               
    arr.push((!radix?'&#'+str.charCodeAt(i):'&#x'+str.charCodeAt(i).toString(16))+';')
  }
  let tmp=arr.join('')
  return tmp
}

function KHKuiFileuploader(myConfig){
	if(!myConfig || !myConfig.fileContainer){
		$.ligerDialog.alert("请指定附件容器！参数名称：fileContainer");
		return;
	}
	
	myConfig.fileUploaded = function(file){//单个回调函数
		if(file){
			$("#" + file.upId).find(".progress").addClass("l-icon-close").click(function(){
				deleteUploadFile(file.id,file.path,this,function(){
					if(myConfig.delCallback){
						myConfig.delCallback(file);
					}
				});
			});
			$("#" + file.upId).find("a").attr("href",kFrameConfig.base + "fileupload/download.do?id="+ file.id);
		}
	};
	
	myConfig.uploadProgress = function(file){//上传进度回调
		if(file.percent==100)
			$("#" + file.id).find(".progress").html("&nbsp;");
		else
			$("#" + file.id).find(".progress").html("<span>"+ file.percent + "%<span>");
	};
	
	myConfig.filesAdded = function(file){//添加文件到上传队列事件
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
	
	//创建上传区域
	$("#" + myConfig.fileContainer).append('<p id="' + myConfig.container + '"><a class="l-button" id="' + myConfig.buttonId 
			+ '"><span class="l-button-main"><span class="l-button-text">选择文件</span></span></a></p>'
			+ '<div class="l-upload-ok-list"><ul class="file_list"></ul></div>');
	return new KHFileUploader(myConfig);
}

//删除服务器文件
function deleteUploadFile(id, path, obj, callback) {
    $.ligerDialog.confirm("确定删除文件？<p style='color:red'>注意：删除后不可恢复！</p>", function(flag) {
		if(flag){
			$("body").mask("正在删除，请稍后……");
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
		    		alert("删除失败！");
		    	}
		    });
		}
    });
}

function viewFileList(files,first){
	if(!files || files.length==0)return;
	top.winOpen({
		title: "文件查看",
		parent: window["api"]?window["api"]:null,
		resize: false,
		max: true,
		min: false, 
		lock: true,
		skin: "win-flat noborder close4 min-hide max-hide",
		content: "url:pub/fileupload/file_view.jsp",
		data: {
			files: files,
			first: first
		}
	}).max();
}

function viewAttachment2(fs){
	viewFileList(fs,0);
}

function viewAttachment(id,name,container){
	var allFile = container?container.children():[];
	var first = 0,files = [];
	$.each(allFile, function(i) {
		var $id = $(this).attr("id");
		var $fname = $(this).attr("fname");
		if ($id == id) {
			first = (files.length == 0 ? 0 : files.length);
		}
		files.push({
			id : $id,
			name : $fname
		});
	});
	viewFileList(files,first);
}

function printAttachment(data){
	if(!window["getLodop"]){
		$("head").append('<script type="text/javascript" src="pub/fileupload/js/lodop_funcs.js"></script>');
	}
	if($("#LODOP_OB").length==0){
		$("body").append('<object style="height:1px;" id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0>\
				<embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0></embed></object>');
	}
	$("body").mask("正在打印！请稍候...");
	var arr;
	if($.isArray(data)) arr = data;// file array
	else if($.isPlainObject(data)) arr = [data]; // file object
	else if($.type(data)=='string') arr = [{id:data}]; // file id
	else {
		$.ligerDialog.warn("参数错误，无法打印！");
		$("body").unmask();
		return;
	}
	_printAttachment(arr,0);
}

function _printAttachment(images,idx){
	if(images.length <= idx) return;
	var href = $("base").attr("href") + "fileupload/download.do?id=" + images[idx].id;
	var imgObj = new Image();
	imgObj.onload = function(){
	    $("body").unmask();
		var LODOP = getLodop(document.getElementById('LODOP_OB'), document.getElementById('LODOP_EM'));
        LODOP.PRINT_INIT("打印文件" + images[idx].id);
    	var w=0,h=0;
	    //横向
    	if(imgObj.width > imgObj.height){
        	LODOP.SET_PRINT_PAGESIZE(2, 2100, 2970, "CreateCustomPage");
	    	h = imgObj.height <= 755?imgObj.height:755;
	    	w = imgObj.height <= 755?imgObj.width:(imgObj.width*(h/imgObj.height));
    	}else{
        	LODOP.SET_PRINT_PAGESIZE(1, 2100, 2970, "CreateCustomPage");
        	w = imgObj.width <= 755?imgObj.width:755
    		h = imgObj.width <= 755?imgObj.height:(imgObj.height*(w/imgObj.width));
    	}
	    LODOP.ADD_PRINT_IMAGE('5mm', '5mm', (w/3.7)+"mm", (h/3.7)+"mm", "<img src='"+href+"' style='width:"+w+"px;height:"+h+"px' />");
	    LODOP.PREVIEW();
	    if(images.length > idx)
	    	_printAttachment(images,idx+1);
	};
	imgObj.src = href;
}

//打开office文档
function openOfficeDocument(fid,fname,status){
    top.$.dialog({
        width: $(top).width(),
        height: $(top).height(),
        title: fname,
        lock: false,
        reSize: false,
        content: "url:pub/fileupload/ntko_editor.jsp",
        data: {fid:fid,status:status}
    });
}

// 在线播放视频
function playVideoOnline(fid,fname){
	top.$.dialog({
        width: $(top).width(),
        height: $(top).height(),
        title: fname,
        lock: false,
        reSize: false,
        data: {fid:fid, fname:fname},
        content: "url:pub/fileupload/video_player.jsp?fid=" + fid
    });
}

function viewPdfOnline(fid,fname){
	if($.browser.msie && $.browser.version < 9.0){
		window.open($("base").attr("href") + "fileupload/download.do?id=" + fid);
	}else{
		winOpen({
			width : 1000,
			height : 5000,
			title : fname,
			lock : false,
			skin : "win-flat",
			reSize : false,
			max : false,
			min : false,
			parent : api,
			content : "url:pub/pdfjs/viewer.jsp?fid=" + fid
		}).max();
	}
}

/**
 * 生成文件列表。
 * 这里要求文件数组的元素格式必须为{id:"",name:""}
 * @param fs 文件数组
 * @param edit 是否可编辑
 * @param ctrId 容器ID
 * @param preview 是否以预览图展示
 */
function createFilesView(fs,edit,ctrId,preview,callback){
    createFileViewList({
        files:fs,edit:edit,ctrId:ctrId,preview:preview,callback:callback
    })
}

/**
 * 创建一组文件列表视图，使用json格式参数
 * @param options
 */
function createFileViewList(options){
	$.each(options.files,function(){
	    var $ops = $.extend({fid:this.id,fname:this.name},options);
	    createFileViewItem($ops);
	});
}

/**
 * 生成单个文件视图
 * 
 * @param fid 文件id
 * @param fname 文件名
 * @param edit 是否可编辑
 * @param ctrId 容器ID
 * @param preview 是否以预览图展示
 */
function createFileView(fid,fname,edit,ctrId,preview,callback){
    createFileViewItem({
        fid:fid,fname:fname,edit:edit,ctrId:ctrId,preview:preview,callback:callback
    });
}

/**
 * 生成单个文件视图,采用json参数
 * 
 * @param options
 */
function createFileViewItem($ops){
    var options = $.extend({edit:false,print:false,preview:true},$ops);
    var ctr = $(".l-upload-ok-list");
    if(options.ctrId) ctr = $("#" + options.ctrId);
	ctr.addClass("upload-file-list l-upload-ok-list");
    var fitem = $("<li id='" + options.fid + "' class='"+(options.preview?"preview":"not-preview") + "'></li>").appendTo(ctr);
    var ofpreview = options.fname.match(/\.(doc|docx|xls|xlsx|ppt|pptx|wps|et|dps)$/i) && kFrameConfig.previewOfficeDocument;
    var isVedio = options.fname.match(/\.(avi|mp4|wmv|mkv|rmvb|mp3|mav|ogg)$/i);
    var isImage = options.fname.match(/\.(jpg|png|gif|bmp|jpeg)$/i);
    var isPdf = options.fname.match(/\.pdf$/i);
    var fcontainer = $("<a class='f-link'></a>").appendTo(fitem);
    fitem.attr("fname", options.fname);
    if(!ofpreview){
    	fcontainer.click(function(){
    		viewAttachment(options.fid,options.fname,ctr);
    	});
    }
    if(options.preview){
    	var previewImgAddr;
    	if(options.previewImg) previewImgAddr = options.previewImg;
    	else previewImgAddr = getPreviewImageAddr(options.fid,options.fname,true);
        fcontainer.append("<img width='48' height='48' src='" + previewImgAddr + "' />");
    }
    else{
        fcontainer.append("<img width='16' height='16' class='fname-icon' src='" + getPreviewImageAddr(options.fid,options.fname,false) + "' />" + stringToEntity(options.fname));
    }
    if(options.edit){
        var d = $('<div class="l-icon-close progress">&nbsp;</div>');
        d.click(function(){
            deleteUploadFile(options.fid,"","",function(){
                $("#" + options.fid).remove();
                if(options.callback) options.callback(options.fid);
            });
        });
        d.appendTo(fitem)
    }
    if(options.preview || ofpreview || isVedio || isPdf){
    	var content;
        if(ofpreview || isVedio || isImage || isPdf){
        	 content = '<div class="tip-file-name">' + stringToEntity(options.fname) + '</div><div class="tip-btn-wrap">';
        	 if(options.modify && ofpreview){
                 content += '<div class="tip-btn edit" onclick="openOfficeDocument(\''+options.fid+'\',\''+stringToEntity(options.fname) + 
                 			'\',\'edit\')"><span class="l-icon l-icon-edit"></span><span class="text">编辑</span></div>';
             }
        	 var viewEvent;
        	 if(ofpreview)
        		 viewEvent = 'openOfficeDocument(\''+options.fid+'\',\''+stringToEntity(options.fname) + '\',\'detail\')';
         	 //else if(isVedio)
         	 //	viewEvent = 'playVideoOnline(\''+options.fid+'\',\''+stringToEntity(options.fname) + '\')';
         	 else if(isPdf)
         		viewEvent = 'viewPdfOnline(\''+options.fid+'\',\''+stringToEntity(options.fname) + '\')';
         	 else
         		viewEvent = 'viewAttachment(\''+options.fid+'\',\''+stringToEntity(options.fname) + '\',$(\'#' +options.ctrId + '\'))';
        	 content += '<div class="tip-btn view" onclick="' + viewEvent + '"><span class="l-icon l-icon-detail"></span><span class="text">查看</span></div>' +
        	 			//'<div class="tip-btn print" onclick="printAttachment({id:\'' + options.fid + '\',name:\'' + stringToEntity(options.fname) + '\'})"><span class="l-icon l-icon-print"></span><span class="text">打印</span></div>' +
        	 			'<div class="tip-btn down" onclick="window.open($(\'base\').attr(\'href\')+\'fileupload/download.do?id=' + options.fid + 
        	 			'\')"><span class="l-icon l-icon-save"></span><span class="text">下载</span></div></div>';
        }else{
        	content = stringToEntity(options.fname);
        }
    	fitem.poshytip({
    		className: 'tip-violet',
    		allowTipHover: true,
    		content: content,
    		hideTimeout: 1000
    	});
    }
}

function createThirdUploadFileViewItem(options,scode){
	if($("#"+options.fid).size()>0) return;
	var myops = {};
	$.extend(myops,options,{callback:function(fid){
		$.post("service/upload/srv/delete_file.do?ids=" + fid + "&scode=" + scode,function(resp){
			if(options.callback) options.callback(fid);
		},"json");
	}});
	createFileViewItem(myops);
}

//获取与文件后缀名匹配的小图标地址
function getPreviewImageAddr(fid,fname,preview){
	if(preview && fname.match(/\.(jpg|gif|png|bmp)$/gi) != null){
		return kFrameConfig.base + "fileupload/previewImage.do?id=" + fid;
	}else{
		var suffixIdx = fname.lastIndexOf(".");
		var suffix = suffixIdx==-1?"file":fname.substring(suffixIdx).toLowerCase().substring(1);
		// 从系统参数【文件后缀图标集合】中查找
		if(suffixIdx > -1 && window["kFrameConfig"] && kFrameConfig["suffixIcon"] && kFrameConfig.suffixIcon.indexOf(suffix)==-1)
	            suffix = "file";
		return "k/kui/images/file-type/"+(preview?48:16)+"/" + suffix + ".png";
	}
}

/**
 * 获取业务附件
 * @param _bussid
 * @param _callback
 */
function getBusinessAttachments(_bussid,_callback){
	$.getJSON("fileupload/busFiles.do?businessId=" + _bussid,function(resp){
		if(resp.success){
			_callback(resp.data);
		}else{
			$.ligerDialog.error("获取附件失败！");
		}
	});
}
function getBusinessAttachmentsByItem(_bussid,_item,_callback){
	var item = _item||"";
	$("body").mask("正在获取数据，请稍候...");
	$.getJSON("fileupload/busFiles.do?businessId=" + _bussid+"&item="+item,function(resp){
		if(resp.success){
			_callback(resp.data);
		}else{
			$.ligerDialog.error("获取附件失败！");
		}
		$("body").unmask();
	});
}
function showProcessMsg(khUploader){
	var html = "<div id='processDiv'></div>";
	if($("#_uploadProgressDiv").size()==0){
        if (khUploader.uploadConfig.autoSave == 1) {
        	html+="<br><br><div id='_isSaveDiv' style='z-index:200'><input type='checkbox' id='issave'><label for='issave' style='margin-left:2px'>上传完成后，是否保存？</label></div>";
        }
    }
	$("#_global_mask_div_loading").html(html);
}

(function($){
	$.KhUploadGrid = function(elm,options){
		this.$elm = $(elm);
		this.opts = options;
		this.elmid = null;
		this.uploadImpl = null;
		this.init();
	};
	
	$.KhUploadGrid.prototype = {
		init: function(){
			this.$elm.data('khUploadGrid', this);
			var $this = this;
			if(!this.opts.columns){
				$.ligerDialog.error("未配置表格列信息，无法创建上传表");
				return;
			}
			var elid = this.$elm.attr("id");
			if(!elid){
				elid = "kh_up_grid_" + Math.random();
				this.$elm.attr("id",elid);
			}
			this.elmid = elid;
			if((this.opts.edit==true && this.opts.editType == "block") || (this.opts.edit==false && this.opts.viewType == "block"))
				this.uploadImpl = this.$elm.khUploadBlockArea(this.opts);
			else
				this.uploadImpl = this.$elm.khUploadLigerGrid(this.opts);
		},
		loadData: function(dataRows){
			this.uploadImpl.loadData(dataRows);
		},
		getData: function(){
			return this.uploadImpl.getData();
		},
		addRow: function(fr){
	    	this.uploadImpl.addRow(fr);
		},
		getFile: function(rowidx){
	    	this.uploadImpl.getFile(rowidx);
		},
		clearFile: function(rowidx){
	    	this.uploadImpl.clearFile(rowidx);
		},
		getGrid:function(){
			return this.uploadImpl.getGrid();
		}
	};
	
	$.khUploadGrid = {
		impl : {
			LigerGrid: function($elm,options){
				this.fileGrid = null;
				this.$elm = $elm;
				this.opts = options;
				this.init();
			},
			BlockArea: function($elm,options){
				this.$elm = $elm;
				this.opts = options;
				this.data = [];
				this.init();
			}
		}
	}
	
	$.khUploadGrid.impl.LigerGrid.prototype = {
		init: function(){
			var $this = this;
			this.$elm.data('khUploadLigerGrid', this);
			var gcls = this.opts.columns;
			var oprDisplay = "操作";
			var elmid = $this.$elm.attr("id");
			if($this.opts.edit)
				oprDisplay = "<a class='l-a l-icon-add' href='javascript:$(\"#" + $this.$elm.attr("id") +"\").khUploadLigerGrid().addRow();' title='添加'><span>添加</span></a>"
			gcls.push({
				isSort: false,
				width: 100,
				display: "已上传/限制数",
				render: function (rowdata, rowindex, value) {
					return rowdata.files.length + "/" + (rowdata.fnum>0?rowdata.fnum:'不限');
				}
			},{
				isSort: false,
				width: $this.opts.nameColumnWidth,
				display: "文件名称(点击查看)",
				render: function (rowdata, rowindex, value) {
					var fnames = "";
					if(rowdata.files && rowdata.files.length > 0){
						for(var i in rowdata.files){
							fnames += "、" + rowdata.files[i].name;
						}
						return "<div style='text-overflow:ellipsis;overflow:hidden;' title='点击查看所有文件【" + fnames.substring(1) + "】'><a href='javascript:$(\"#" + elmid 
								+ "\").khUploadLigerGrid().viewFile(" + rowindex + ");'>" + fnames.substring(1)  + "<a></div>";
					}else{
						return fnames;
					}
				}
			},{
				isSort: false, 
				width: 120,
				display: oprDisplay,
				render: function (rowdata, rowindex, value) {
					var shows = "",hides = "";
					if($this.opts.edit){
						shows += "<a class='upload-grid-icon upload-grid-icon-upload' href='javascript:$(\"#"
								+ elmid +"\").khUploadLigerGrid().uploadFile(" + rowindex + ")' title='管理文件'></a>";
						if(rowdata.camera)
							shows += "<a class='upload-grid-icon upload-grid-icon-camera' href='javascript:$(\"#" + 
									elmid +"\").khUploadLigerGrid().scanFile(" + rowindex + ",false);' title='文件扫描'></a>"; 
					}
					/*shows += "<a class='upload-grid-icon upload-grid-icon-preview' href='javascript:$(\"#"  
						+ elmid +"\").khUploadLigerGrid().viewFile(" + rowindex + ");' title='查看'></a>";*/
					if($this.opts.print){
						shows += "<a class='upload-grid-icon upload-grid-icon-print' href='javascript:$(\"#" 
								+ elmid +"\").khUploadLigerGrid().printFile(" + rowindex + ");' title='打印'></a>";
					}
					if($this.opts.edit){
						shows += "<a class='upload-grid-icon upload-grid-icon-clear' href='javascript:$(\"#" 
							+ elmid +"\").khUploadLigerGrid().clearFile(" + rowindex + ");' title='清空文件'></a>"
							+ "<a class='upload-grid-icon upload-grid-icon-delete' href='javascript:$(\"#" 
							+ elmid +"\").khUploadLigerGrid().deleteRow(" + rowindex + ");' title='删除此行'></a>";
					};
					/*if(hides!=""){
						shows += "<a class='upload-grid-icon upload-grid-icon-more' onmouseover='$(\"#"  
							+ elmid +"\").khUploadLigerGrid()._showMoreOper(this);' onmouseout='$(\"#"  
							+ elmid +"\").khUploadLigerGrid()._hideMoreOper(this);' title='更多'></a>";
					}*/
					/*return "<div class='upload-grid-icon-wrap'><div class='upload-grid-icon-wrap-show'>" 
							+ shows + "</div><div class='upload-grid-icon-wrap-hide'>" + hides + "</div></div>";*/
					return "<div class='upload-grid-icon-wrap'><div class='upload-grid-icon-wrap-show'>" + shows + "</div></div>";
				}
			});
			this.$elm.addClass("upload-grid-wrap");
			this.fileGrid = this.$elm.ligerGrid({
				height: this.opts.height,
				frozen: false,
				columns: gcls,
				usePager: false,
				rowHeight: 32,
				allowHideColumn:this.opts.allowHideColumn,
				enabledEdit: this.opts.edit,
				data: {Rows:[]}
			});
			this.loadData(this.opts.data);
		},
		clearFile: function(rowidx){
			var $this = this;
			var rowData = this.fileGrid.getData()[rowidx];
			if(rowData.files.length == 0) return;
			$.ligerDialog.confirm("清空后无法恢复，确定清空？",function(rst){
				if(!rst) return;
				var fids = "";
				for(var i in rowData.files){
					fids += "," + rowData.files[i].id;
				}
				$("body").mask("正在处理，请稍候...");
				$.post("fileupload/delete.do",{id:fids.substring(1)},function(response){
					if(response.success){
						var rd = $.extend({},rowData);
						rowData.files = [];
						$this.fileGrid.update(rowidx,rowData);
						if(typeof $this.opts["onClearFile"]=="function"){
							$this.opts["onClearFile"].call(window,rd);
						}
					}
					$("body").unmask();
				},"json");
			});
		},
		_showMoreOper: function(elm){
			var hides = $(elm).parent().next();
			var loc = $(elm).offset();
			var tips = $("#upload-grid-more-ops-warp");
			if(tips.length == 0){
				tips = $("<div id='upload-grid-more-ops-warp'></div>").hover(function(){
					tips.data("_is_hide","0");
				},function(){
					tips.data("_is_hide","1");
					window.setTimeout(function(){
						if(tips.data("_is_hide")=="1"){
							tips.empty().hide();
						}
					},300);
				}).appendTo("body");
			}
			tips.data("_is_hide","0").show().html(hides.html()).offset({
				left: loc.left + 22,
				top: loc.top
			});
		},
		_hideMoreOper: function(){
			var tips = $("#upload-grid-more-ops-warp");
			tips.data("_is_hide","1");
			window.setTimeout(function(){
				if(tips.data("_is_hide")=="1"){
					tips.empty().hide();
				}
			},300);
		},
		deleteRow: function(idx){
			var $this = this;
			var rowData = this.fileGrid.getData()[idx];
			$.ligerDialog.confirm("删除后无法恢复，确定删除？",function(rst){
				if(!rst) return;
				$this.fileGrid.deleteRow(idx);
				//$this._hideMoreOper();
				if(rowData.files.length > 0){
					var fids = "";
					for(var i in rowData.files){
						fids += "," + rowData.files[i].id;
					}
					$("body").mask("正在处理，请稍候...");
					$.post("fileupload/delete.do",{id:fids.substring(1)},function(response){
						if(response.success){
							if(typeof $this.opts["onDeleteRow"]=="function"){
								$this.opts["onDeleteRow"].call(window,rowData);
							}
						}
						$("body").unmask();
					},"json");
				}
			});
		},
		viewFile: function(rowIdx){
			var rdata = this.fileGrid.getData()[rowIdx];
			if(rdata.files.length > 0){
				viewFileList(rdata.files,0);
			}else{
				top.$.notice("没有文件可查看！",3,"k/kui/images/icons/dialog/32X32/hits.png");
			}
		},
		printFile: function(rowIdx){
			var rdata = this.fileGrid.getData()[rowIdx];
			if(rdata.files.length == 0) return;
			var regex = /\.(jpg|gif|png|bmp)$/gi;
			if(rdata.files){
				var imgs = [];
				for(var i in rdata.files){
					if(rdata.files[i].name.match(regex)!=null){
						imgs.push(rdata.files[i]);
					}
				}
				if(imgs.length==0){
					$.ligerDialog.warn("不是图片，无法打印！");
				}else{
					printAttachment(imgs);
				}
			}
		},
		addRow: function(dataRow){
			var idx = this.fileGrid.getData().length;
			if(!dataRow) {
				dataRow = {businessId:"",workitem:"",files:[],fnum:-1};
			}
			if(!dataRow.files){
				dataRow.files =[];
			}
			if($.kh.isNull(dataRow.camera))
				dataRow.camera = this.opts.camera;
			if($.kh.isNull(dataRow.folder))
				dataRow.folder = this.opts.folder;
			
			this.fileGrid.addRow(dataRow);
			var $this = this;
			if(dataRow.businessId){
				getBusinessAttachmentsByItem(dataRow.businessId,dataRow.workitem,function(fs){
					if(fs.length == 0) return;
					for(var j in fs){
						dataRow.files.push({id:fs[j].id,name:fs[j].name});
					}
					$this.fileGrid.update(idx,dataRow);
				});
			}
		},
		loadData: function(dataRows){
			var $this = this;
			this.fileGrid.loadData({Rows:[]});
			if(dataRows.length > 0){
				for(var i = 0; i < dataRows.length; i++){
					if($.kh.isNull(dataRows[i].fnum)) 
						dataRows[i].fnum=-1;
					$this.addRow(dataRows[i]);
				}
			}
		},
		getData: function(){
			return this.fileGrid.getData();
		},
		getFile: function(rowidx){
			return this.fileGrid.getData()[rowidx];
		},
		scanFile: function(rowidx){
			this.fileGrid.endEdit();
			var rowData = this.fileGrid.getData()[rowidx];
			if(rowData.fnum > 0 && rowData.files.length>=rowData.fnum){
				$.ligerDialog.warn("您已上传了" + rowData.fnum + "个文件，不能再上传了！");
				return;
			}
			var $this = this;
			if(!window["openSystemCamera"]){
				$("head").append('<script type="text/javascript" src="pub/camera/camera.js"></script>');
			}
			openSystemCamera({
		    	businessId: rowData.businessId,
		        onAfterUpload : function(files){
		        	$this._addRowFile(rowidx,files);
		        },
		        workItem: rowData.workitem,
		        folder: rowData.folder,
		        fileNum: rowData.fnum - rowData.files.length
		    });
		},
		_addRowFile: function(rowidx,files){
			var rowData = this.fileGrid.getData()[rowidx];
			var $this = this;
			if(files.length > 0){
		    	for(var i in files){
		    		var repeat = false;
		    		for(var j in rowData.files){
		    			if(rowData.files[j].id==files[i].id){
		    				repeat = true;
		    				break;
		    			}
		    		}
		    		if(!repeat) rowData.files.push(files[i]);
		    	}
		    	$this.fileGrid.update(rowidx,rowData);
	    	}
		},
		uploadFile: function(rowidx){
			this.fileGrid.endEdit();
			var rowData = this.fileGrid.getData()[rowidx];
			var $this = this;
			top.$.dialog({
				width : 500,
				height : 300,
				lock : true,
				parent : api,
				max: false,
			    min: false,
				title : "上传文件",
				content : "url:pub/fileupload/upload_dialog.jsp",
				data : {
					options:{
						businessId : rowData.businessId,
						title : rowData.fileType||"*",
					    extName : rowData.fileType||"*",
					    fileSize : rowData.fileSize || "10mb",
					    fileNum : rowData.fnum,
					    folder: rowData.folder||"",
					    workItem : rowData.workitem
					},
					upfiles: rowData.files,
					camera: rowData.camera,
					print: $this.opts.print,
				    callback: function(files){
				    	$this._addRowFile(rowidx,files);
				    	if(typeof $this.opts["onUploadFile"]=="function"){
				    		$this.opts["onUploadFile"].call(window,rowData,files);
				    	}
				    }
				}
		    });
		},
		getGrid:function(){
			return this.fileGrid;
		}
	};
	
	$.khUploadGrid.impl.BlockArea.prototype = {
		init: function(){
			this.loadData(this.opts.data);
		},
		addRow: function(nr){
			this.data.push(nr);
			var idx = this.$elm.find(".block-upload-area").length;
			var cid = this.$elm.attr("id") + "_fs_" + idx;
			this.$elm.append("<div class='block-upload-area'>" 
					+ ($.kh.isNull(nr.title)?"":"<div class='block-upload-area-head'><span>" + nr.title + "</span></div>")
					+ "<div class='block-upload-area-main' style='width:" + ($.kh.isNull(this.opts.width)?200:this.opts.width)
					+ "px;' id='" + cid + "'></div></div>");
			var uploader = $("#" + cid).khUpload({
				businessId: nr.businessId,
				workItem: nr.workitem,
				useThirdDevice: this.opts.useThirdDevice,
				fileNum: nr.fnum,
				edit: this.opts.edit,
				folder: nr.folder,
				print: this.opts.print,
				extName: nr.fileType
			});
		},
		loadData: function(dataRows){
			var $this = this;
			this.$elm.find(".block-upload-area").remove();
			$.each(dataRows,function(){
				$this.addRow(this);
			});
		},
		getData: function(){
			var $this = this;
			$.each(this.data,function(idx){
				var cid = $this.$elm.attr("id") + "_fs_" + idx;
				var files = $("#" + cid).khUpload("getUploadFileIdNames");
				this.fid = files.id;
				this.fname = files.name;
			});
			return this.data;
		},
		getGrid:function(){
			
		}
	};
	$.fn.khUploadLigerGrid = function(options) {
		if(!options){
			return $(this).data('khUploadLigerGrid');
		}
		var opts = $.extend({height:200},options);
		return new $.khUploadGrid.impl.LigerGrid(this, opts);
	};
	
	$.fn.khUploadBlockArea = function(options) {
		if(!options){
			return $(this).data('khUploadBlockArea');
		}
		var opts = $.extend({width:300,useThirdDevice:true},options);
		return new $.khUploadGrid.impl.BlockArea(this, opts);
	};
	
	$.fn.khUploadGrid = function(options) {
		if(!options){
			return $(this).data('khUploadGrid');
		}
		var opts = $.extend($.fn.khUploadGrid.defaults,options);
		return new $.KhUploadGrid(this, opts);;
	};
	
	var pageStatus = $("head").attr("pageStatus");
	var editable = false;
	if(pageStatus && (pageStatus=='add' || pageStatus=='edit' || pageStatus=='modify')){
		editable = true;
	}
	$.fn.khUploadGrid.defaults = {
		edit: editable,
		print: false,
		nameColumnWidth: 255,
		editType: "grid",
		viewType: "block"
	};
})(jQuery);