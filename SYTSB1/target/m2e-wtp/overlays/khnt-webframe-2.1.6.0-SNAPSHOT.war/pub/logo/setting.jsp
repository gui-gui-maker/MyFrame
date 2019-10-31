<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <%@include file="/k/kui-base-form.jsp" %>
    <script src="k/kui/frame/jquery.form.js" type="text/javascript"></script>
    <script type="text/javascript" src="pub/fileupload/js/fileupload.js"></script>
    <script type="text/javascript" src="pub/fileviewer/fileviewer.js"></script>
    <script test="text/javascript">
   	    var toolbar;
        $(function () {//jQuery页面载入事件
        	//设置logo路劲 
        	toolbar=$("#toolbar").ligerToolBar({
                items: [
                    "->",
                    "-",
                    {text: "生成LOGO", img: "k/kui/images/icons/16/table_save.png",click:function(){
                    	$.ligerDialog.confirm('确定生成LOGO？', function (yes) { if(yes){
                    		var options = {
                       				type : 'post',
                       				success : function(res) {
                       					$("body").unmask();
                       					if(res.success){
                       						alert("图片生成成功")
                       					}else{
                       						alert("生成失败！")
                       					}
                       				},
                       				beforeSubmit : function(){
                       					$("body").mask("正在生成。。。");
                       				}
                       			};
                        		$("#formObj").ajaxSubmit(options);
                    	} });
                    }}
                    ,"-",
                    {
                    	text:'预览LOGO',icon:'detail',click:function(){
                    		top.$.dialog({
                    			width:'800px',
                    			height:'600px',
                    			lock:true,
                    			parent:api,
                    			title:"预览",
                    			content: 'url:pub/logo/priview.jsp?path='+$("input[name='logoPath']").val(),
                    			data:{window:window}
                    		}).max();
                    	}
                    }
                ]
            });
        
        	//附件上传
        	var uploadConfig = {
        			fileSize : "1mb",//文件大小限制
        			businessId : "",//业务ID
        			buttonId : "uploadBtn",//上传按钮ID
        			container : "uploadfilesDIV",//上传控件容器ID
        			title : "请选择背景图片",//文件选择框提示
        			folder: "logo",//存储路劲
        			extName : "jpg,gif,jpeg,png,bmp",//文件扩展名限制
        			saveDB : true,//是否往数据库写信息
        			attType : "",//文件存储类型；1:数据库，0:磁盘，默认为磁盘
        			fileNum : 1,//限制上传文件数目
        			callback : function(files){//上传成功后回调函数,实现页面文件显示，交与业务自行后续处理
        				$("#uploadBtn").hide();
        				$("#uploadfiles").empty();
        				showAttachFile(files);
        			}
        		};
    		var uploader= new KHFileUploader(uploadConfig);
    		//附件上传
        	var uploadConfig1 = {
        			fileSize : "1mb",//文件大小限制
        			businessId : "",//业务ID
        			buttonId : "logoBtn",//上传按钮ID
        			container : "logofilesDIV",//上传控件容器ID
        			title : "请选择LOGO图标",//文件选择框提示
        			folder: "logo",//存储路劲
        			extName : "jpg,gif,jpeg,png,bmp",//文件扩展名限制
        			saveDB : true,//是否往数据库写信息
        			attType : "",//文件存储类型；1:数据库，0:磁盘，默认为磁盘
        			fileNum : 1,//限制上传文件数目
        			callback : function(files){//上传成功后回调函数,实现页面文件显示，交与业务自行后续处理
        				$("#logoBtn").hide();
        				$("#logofiles").empty();
        				showLogoAttachFile(files);
        			}
        		};
    		var uploader1= new KHFileUploader(uploadConfig1);
    		
    		$("#selectBtn").click(function(){
    			getFilesPath('logo/getFiles.do',$("#uploadFile"),function(obj){
    				$("#uploadfiles").empty();
    				var ctr = $("#uploadfiles")
        			var imgHtml = "<li class='not-preview' >";
    				imgHtml += "<img width='48' height='48' src='"+obj+"' /></li>";
    				ctr.append(imgHtml);
    				$("#uploadFile").val(obj);
    				$("#showImg").attr("src",obj);
    			});
    			$("#uploadBtn").show();
    		});
    		$("#selectBtn1").click(function(){
    			getFilesPath('logo/getFiles.do',$("#logoFile"),function(obj){
    				$("#logofiles").empty();
    				var ctr = $("#logofiles")
        			var imgHtml = "<li class='not-preview' >";
    				imgHtml += "<img width='48' height='48' src='"+obj+"' /></li>";
    				ctr.append(imgHtml);
    				$("#logoFile").val(obj);
    				//$("#showImg").attr("src",obj);
    			});
    			$("#logoBtn").show();
    		});
    		
    		
    		//字体文件
    		var oneUploadConfig = {
        			fileSize : "10mb",//文件大小限制
        			businessId : "",//业务ID
        			buttonId : "onefileBtn",//上传按钮ID
        			container : "onefileDIV",//上传控件容器ID
        			attType : "",//文件存储类型；1:数据库，0:磁盘，默认为磁盘
        			title : "字体文件选择",//文件选择框提示
        			extName : "ttf",//文件扩展名限制
        			fileNum : 1,//限制上传文件选择数目 弹出选择框 可以选择文件数量限制
        			workItem : "one",//页面多点上传文件标识符号
        			callback : function(files){//上传成功后回调函数,实现页面文件显示，交与业务自行后续处理
        				addOneFile(files);
	        			$("input[name='ttf']").val(files[0].path)
        			}
        		};
    		var oneUploader= new KHFileUploader(oneUploadConfig);
        });
        
        function showAttachFile(files){
    		if(files!=null&&files!=""){
    			$.each(files,function(i){
    				var data=files[i];
    				$("#uploadBtn").hide();
    				var fileName = data.name||data.fileName;
    				//设置显示图片
    				$("#uploadFile").val(data.path);
    				$("#showImg").attr("src","fileupload/download.do?id="+data.id);
    				createFileView(data.id,fileName,$("head").attr("pageStatus")=="detail"?false:true,"uploadfiles",true,function(fid){
    					$("#uploadBtn").show();
    					$("#showImg").attr("src","");
    					$("#uploadFile").val("");
    				})
    			})
    		}
        }
        function showLogoAttachFile(files){
    		if(files!=null&&files!=""){
    			$.each(files,function(i){
    				var data=files[i];
    				$("#logoBtn").hide();
    				var fileName = data.name||data.fileName;
    				$("#logoFile").val(data.path);
    				//设置显示图片
    				createFileView(data.id,fileName,$("head").attr("pageStatus")=="detail"?false:true,"logofiles",true,function(fid){
    					$("#logoBtn").show();
    					$("#logoFile").val("");
    				})
    			})
    		}
        }
        var _this;
        function showFiles(val,e,srcObj){
    		_this = e;
    		top.$.dialog({
    			width:'340px',
    			height:'190px',
    			lock:true,
    			parent:api,
    			title:"颜色选择",
    			content: 'url:pub/logo/color_detail.jsp',
    			data:{window:window}
    		});
    	}
        function setColorValue(retvalue){
    		try {
    			_this.setValue(retvalue);
    		} catch (e) {
    			$("input[name='textColor']").val(retvalue);
    			_this.src=retvalue;
    		}
    	}
      //添加单文件处理
    	function addOneFile(files){
    		if(files){
    			//判断单个文件是否存在
    			var uploadFileId = $("#ttfId").val();
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
    	//将上传的所有文件id写入隐藏框中
    	function getUploadOneFile(){
    		$("input[name='ttf']").val("");
    		var attachId="";
    		var i=0;
    		$("#onefile li").each(function(){
    			attachId+=(i==0?"":",")+this.id;
    			i=i+1;
    		});
    		if(attachId!=""){
    			attachId=attachId.substring(0,attachId.length);
    		}
    		$("#ttfId").val(attachId);
    	}
    	function logoType(obj){
    		if(obj =="login_logo"){
    			$("input[name='logoPath']").val(kFrameConfig.loginLogo.src);
    		}else if(obj == "logo"){
    			$("input[name='logoPath']").val(kFrameConfig.mainLogo.src);
    		}else{
    			$("input[name='logoPath']").val("upload/logo/default.png");
    		}
    		
    	}
    </script>
</head>
<body>

<div class="item-tm" id="titleElement">
    <div class="l-page-title has-icon has-note">
		<div class="l-page-title-div"></div>
		<div class="l-page-title-text"><h1>系统LOGO图标生成</h1></div>
		<div class="l-page-title-note">说明：系统LOGO图标生成主要是生成系统登录LOGO和系统标题LOGO，可以在默认图标上重新生成LOGO，也可以自定义上传图片重新生成LOGO。</div>
		<div class="l-page-title-icon"><img src="k/kui/images/icons/32/setting_tools.png" border="0"></div>
	</div>
</div>

<div class="item-tm">
    <div id="toolbar"></div>
</div>

<div class="scroll-tm">
	<form id="formObj" name="formObj" action="logo/createLogo.do">
		<fieldset  class="l-fieldset">
			<legend class="l-legend">背景图片</legend>
			<table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
		        <tr>
					<td class="l-t-td-left" style="height:200px">背景图片：</td>
					<td class="l-t-td-right" id="uploadfilesDIV" style="width:200px">
						<input name="uploadFile" id="uploadFile" type="hidden"/>
						<a class="l-button3" id="uploadBtn" title="上传文件">+</a>
						<a class="l-button3" id="selectBtn" title="选择文件">S</a>
						<div class="l-upload-ok-list"><ul id="uploadfiles"></ul></div>
					</td>
					<td class="l-t-td-left" style="width:1px"></td>
					<td class="l-t-td-right">
						<img id="showImg" style="width: 600px;height:200px"   style="cursor:pointer"/>
					</td>
				</tr>
	     	</table>
		</fieldset>
		<fieldset  class="l-fieldset">
			<legend class="l-legend">文字</legend>
			<table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
		        <tr>
		        	<td class="l-t-td-left">文字位置X坐标：</td>
					<td class="l-t-td-right">
						<input type="text" ltype="spinner" name="textX" ligerui="{type:'int'}" />
					</td>
					<td class="l-t-td-left">文字位置Y坐标：</td>
					<td class="l-t-td-right">
						<input type="text" ltype="spinner" name="textY" ligerui="{type:'int'}" />
					</td>
					<td class="l-t-td-left">LOGO文字：</td>
					<td class="l-t-td-right">
						<input type="text" ltype="text" name="logoText" />
					</td>
				</tr>
				 <tr>
					<td class="l-t-td-left">文字字体文件：</td>
					<td class="l-t-td-right">
						<p id="onefileDIV">
						<a class="l-button" id="onefileBtn"><span
							class="l-button-main"><span class="l-button-text">选择字体文件</span>
						</span>
						</a>
						</p>
						<div class="l-upload-ok-list">
							<ul id="onefile"></ul>
						</div>
						<input type="hidden" name="ttf"/>
						<input type="hidden" id="ttfId" name="ttfId"/>
					</td>
					<td class="l-t-td-left">文字字体：</td>
					<td class="l-t-td-right">
						<input type="text" ltype="text"  name="textFontFamily"  /> 
					</td>
					<td class="l-t-td-left">文字字体样式：</td>
					<td class="l-t-td-right">
						<input type="text" ltype="select" name="textFontStyle" ligerui="{data:[{id:'Font.BOLD',text:'粗体'},{id:'Font.ITALIC',text:'斜体'},{id:'Font.PLAIN',text:'普通样式'}]}" />
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">文字字体大小：</td>
					<td class="l-t-td-right">
						<input type="text" ltype="spinner" name="textFontSize" ligerui="{type:'int'}"/>
					</td>
					<td class="l-t-td-left">文字颜色：</td>
					<td class="l-t-td-right">
						<input type="text" ltype="text" name="textColor"  ligerui="{iconItems:[{icon:'edit',click:function(val,e,srcObj){showFiles(val,e,srcObj)}}]}"/>
					</td>
					<td class="l-t-td-left">文字透明度：</td>
					<td class="l-t-td-right">
						<input type="text" ltype="spinner" name="textAlpha" ligerui="{type:'float',maxValue:'1.00',minValue:'0.00'}" />
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">文字阴影宽度：</td>
					<td class="l-t-td-right">
						<input type="text" ltype="spinner" name="shadowWidth"  ligerui="{type:'int',minValue:'1'}"/>
					</td>
					<td class="l-t-td-left">文字阴影颜色：</td>
					<td class="l-t-td-right">
						<input type="text" ltype="text" name="shadowColor"  ligerui="{iconItems:[{icon:'edit',click:function(val,e,srcObj){showFiles(val,e,srcObj)}}]}"/>
					</td>
				</tr>
	     	</table>
		</fieldset>
		<fieldset  class="l-fieldset">
			<legend class="l-legend">其他图片</legend>
			<table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
		        <tr>
		        	<td class="l-t-td-left">图片位置X坐标：</td>
					<td class="l-t-td-right">
						<input type="text" ltype="spinner" name="logoX" ligerui="{type:'int'}" />
					</td>
					<td class="l-t-td-left">图片位置Y坐标：</td>
					<td class="l-t-td-right">
						<input type="text" ltype="spinner" name="logoY" ligerui="{type:'int'}" />
					</td>
					<td class="l-t-td-left">图标：</td>
					<td class="l-t-td-right" id="logofilesDIV" style="width:200px">
						<input name="logoFile" id="logoFile" type="hidden"/>
						<a class="l-button3" id="logoBtn" title="上传文件">+</a>
						<a class="l-button3" id="selectBtn1" title="选择文件">S</a>
						<div class="l-upload-ok-list"><ul id="logofiles"></ul></div>
					</td>
				</tr>
	     	</table>
		</fieldset>
		<fieldset  class="l-fieldset">
			<legend class="l-legend">LOGO</legend>
			<table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
		        <tr>
		        	<td class="l-t-td-left">LOGO类型：</td>
					<td class="l-t-td-right">
						<input type="radio" ltype="radioGroup" name="logoType" ligerui="{data:[{id:'login_logo',text:'登录LOGO'},{id:'logo',text:'标题LOGO'}],onChange:logoType}" />
					</td>
					<td class="l-t-td-left">LOGO路径：</td>
					<td class="l-t-td-right">
						<input type="text" ltype="type" name="logoPath" value="upload/logo/default.png" />
					</td>
				</tr>
	     	</table>
		</fieldset>
	</form>
</div>
</body>
</html>