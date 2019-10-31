<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="pub/fileupload/js/fileupload.js"></script>
<script type=text/javascript src="ueditor/ueditor.config.js"></SCRIPT>  
<script type=text/javascript src="ueditor/ueditor.all.min.js"></SCRIPT>
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
				if (response.attachs != null && response.attachs != undefined)
					showAttachFile(response.attachs);
			}
		});
		<c:if test="${param.status!='detail'}">
		    var editor1 = UE.getEditor("content",{initialFrameWidth:$("body").width()-140});
		    editor1.ready(function(){
				window.setTimeout(function(){
					var con = document.getElementById("content").value;
		    		editor1.setContent(con);
		    		document.getElementById("topLevel-txt").focus();
				},100);
		    });
	    </c:if>
	});
	//附件上传配置
	$(function() {
    	var receiptUploadConfig = {
    			fileSize : "10mb",//文件大小限制
    			businessId : "",//业务ID
    			buttonId : "procufilesBtn",//上传按钮ID
    			container : "procufilesDIV",//上传控件容器ID
    			title : "*",//文件选择框提示
    			extName : "*",//文件扩展名限制
    			workItem : "",//页面多点上传文件标识符号
    			fileNum : -1,//限制上传文件数目
    			callback : function(files){//上传成功后回调函数,实现页面文件显示，交与业务自行后续处理
    				addAttachFile(files);
    			}
    		};
		var receiptUploader= new KHFileUploader(receiptUploadConfig);
	});
	
	//添加附件
	function addAttachFile(files){
		if("${param.status}"=="detail"){
			$("#procufilesBtn").hide();
		}
		if(files){
			var attContainerId="procufiles";
			$.each(files,function(i){
				var file=files[i];
				$("#"+attContainerId).append("<li id='"+file.id+"'>"+
						"<div><a href='fileupload/downloadByFilePath.do?path="+file.path+"&fileName="+file.name+"'>"+file.name+"</a></div>"+
						"<div class='l-icon-close progress' onclick='deleteUploadFile(\""+file.id+"\",\""+file.path+"\",this,getUploadFile)'>&nbsp;</div>"+
						"</li>");
			});
			getUploadFile();
		}
	}

	  //显示附件文件
    function showAttachFile(files){
    	if("${param.status}"=="detail"){
			$("#procufilesBtn").hide();
		}
		if(files){
			//详情
			var attContainerId="procufiles";
			if("${param.status}"=="detail"){
				$.each(files,function(i){
					var file=files[i];
					 //显示采购单附件
					$("#"+attContainerId).append("<li id='"+file.id+"'>"+
											"<div><a href='fileupload/downloadByFilePath.do?path="+file.filePath+"&fileName="+file.fileName+"'>"+file.fileName+"</a></div>"+
											"</li>");
				});
			}
			//修改
			else if("${param.status}"=="modify"){
				$.each(files,function(i){
					var file=files[i];
					$("#"+attContainerId).append("<li id='"+file.id+"'>"+
							"<div><a href='fileupload/downloadByFilePath.do?path="+file.filePath+"&fileName="+file.fileName+"'>"+file.fileName+"</a></div>"+
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
		$("#procufiles li").each(function(){
			attachId+=(i==0?"":",")+this.id;
			i=i+1;
		});
		if(attachId!=""){
			attachId=attachId.substring(0,attachId.length);
		}
		$("#uploadFiles").val(attachId);
	}
</script>
</head>
<body>
	<form name="formObj" id="formObj" method="post"
		action="app/zp/notice/saveNotice.do"
		getAction="app/zp/notice/detail.do?id=${param.id}">
		<input name="id" type="hidden" id="id" ltype='hidden' />
		
		<table border="1" cellpadding="3" cellspacing="0"
			class="l-detail-table">
			<tr>
				<td class="l-t-td-left">标题：</td>
				<td class="l-t-td-right" colspan="3"><input name="title" type="text" ltype='text' validate="{required:true,maxlength:255}" /></td>
			</tr>
			<tr>
				<td class="l-t-td-left">是否显示：</td>
				<td class="l-t-td-right" colspan="3">
					<u:combo name="sign" code="isOrNot" attribute="initValue:1"></u:combo>
				</td>
			</tr>
			<tr>
				<td class="l-t-td-left">附件：</td>
				<td class="l-t-td-right">
				<input name="uploadFiles" type="hidden" ltype='text' id="uploadFiles" validate="{maxlength:1000}" />
				<p id="procufilesDIV"><a class="l-button" id="procufilesBtn"><span class="l-button-main"><span class="l-button-text">选择文件</span></span></a></p>
		    	<div class="l-upload-ok-list">
					<ul id="procufiles"></ul>
				</div>
				</td>
			</tr>
			<tr>
				<td class="l-t-td-left">内容：</td>
				<td class="l-t-td-right" colspan="3">
					<textarea name="content" id="content" class="l-textarea" ltype="textarea" style="height:300px;width:100%"></textarea> 
				</td>
			</tr>
		</table>
	</form>
</body>
</html>