<%@page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.*" %> 
<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
    <title></title>
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="pub/fileupload/js/fileupload.js"></script>
<script type="text/javascript" src="app/archives/ss/abc.js"></script>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">
$(function() {
    $("#form1").initForm({
		success: function (response) {//处理成功
    		if (response.success) {
            	top.$.dialog.notice({
             		content: "保存成功！"
            	});
            	api.close();
         		//api.data.window.refreshGrid();
    		} else {
           		$.ligerDialog.error('保存失败！<br/>' + response.msg);
      		}
		}, getSuccess: function (response){
	//		alert(response);
			if (response.attachs != null && response.attachs != undefined)
				showAttachFile(response.attachs);
		}, toolbar: [
      		{
      			text: "保存", icon: "save", click: function(){
      				//表单验证
			    	if ($("#form1").validate().form()) {
					    //表单提交
					    $("#form1").submit();
					    	
			    	}
      			}
      		},
			{
				text: "关闭", icon: "cancel", click: function(){
					api.close();
				}
			}
		], toolbarPosition: "bottom"
	});		
	
	var receiptUploadConfig = {
			fileSize : "10mb",	//文件大小限制
			businessId : "",	//业务ID
			buttonId : "procufilesBtn",		//上传按钮ID
			container : "procufilesDIV",	//上传控件容器ID
			title : "附件",	//文件选择框提示
			extName : "",	//文件扩展名限制
			workItem : "",	//页面多点上传文件标识符号
			fileNum : 2,	//限制上传文件数目
			callback : function(files){	//上传成功后回调函数,实现页面文件显示，交与业务自行后续处理
				addAttachFile(files);
			}
	};
	var receiptUploader= new KHFileUploader(receiptUploadConfig);
	});
        
   	// 将上传的所有文件id写入隐藏框中
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
	
	//添加附件
	function addAttachFile(files){
		if("${param.pageStatus}"=="detail"){
			$("#procufilesBtn").hide();
		}
		if(files){
			var attContainerId="procufiles";	
			$.each(files,function(i){
				var file=files[i];
				$("#"+attContainerId).append("<li id='"+file.id+"'>"+
						/* "<div><a href='fileupload/downloadByFilePath.do?path="+file.path+"&fileName="+file.name+"'>"+file.name+"</a></div>"+ */
						"<div><a href='fileupload/download.do?id="+file.id+"&fileName="+file.name+"'>"+file.name+"</a></div>"+
						"<div class='l-icon-close progress' onclick='deleteUploadFile(\""+file.id+"\",\"\",this,getUploadFile)'>&nbsp;</div>"+
						"</li>");
			});
			getUploadFile();
		}
	}
	// 显示附件文件
    function showAttachFile(files){
    	if("${param.pageStatus}"=="detail"){
			$("#procufilesBtn").hide();
		}
		if(files){
//			alert(files);
			//详情
			var attContainerId="procufiles";
			if("${param.pageStatus}"=="detail"){
				$.each(files,function(i){
					var file=files[i];
					 //显示附件
					/* $("#"+attContainerId).append("<li id='"+file.id+"'>"+
											"<div><a href='fileupload/downloadByFilePath.do?path="+file.filePath+"&fileName="+file.fileName+"'>"+file.fileName+"</a></div>"+
											"</li>"); */
					$("#"+attContainerId).append("<li id='"+file.id+"'>"+
							"<div><a href='fileupload/download.do?id="+file.id+"&fileName="+file.fileName+"'>"+file.fileName+"</a></div>"+
							"</li>");
				});
			}
			//修改
			else if("${param.pageStatus}"=="modify"){
				$.each(files,function(i){
					var file=files[i];
					$("#"+attContainerId).append("<li id='"+file.id+"'>"+
							/* "<div><a href='fileupload/downloadByFilePath.do?path="+file.filePath+"&fileName="+file.fileName+"'>"+file.fileName+"</a></div>"+ */
							"<div><a href='fileupload/download.do?id="+file.id+"&fileName="+file.fileName+"'>"+file.fileName+"</a></div>"+
							"<div class='l-icon-close progress' onclick='deleteUploadFile(\""+file.id+"\",\"\",this,getUploadFile)'>&nbsp;</div>"+
							"</li>");
				});
				getUploadFile();
			}
		}
    }
    </script>
</head>

<body>
	<form id="form1" action="archives/file/save1.do" getAction="archives/file/detail1.do?id=${param.id}">
		<input type="hidden" id="id" name="id"/>
		<input type="hidden" id="status" name="status"/>
			<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>档案正文和附件上传</div>
			</legend>
			<table cellpadding="3" cellspacing="0" class="l-detail-table">
				<tr>
					<td class="l-t-td-left">选择上传的类型</td>
					<td class="l-t-td-right"><u:combo  name="uploadType"  code="TJY2_DA_UPLOAD" validate="required:true"></u:combo></td>
					<td class="l-t-td-left">文件：</td>
					<td class="l-t-td-right">
						<input name="uploadFiles" type="hidden" id="uploadFiles" validate="{maxlength:1000}" />
						<p id="procufilesDIV">
							<a class="l-button" id="procufilesBtn">
								<span class="l-button-main"><span class="l-button-text">选择文件</span></span>
							</a>
						</p>
				    	<div class="l-upload-ok-list">
							<ul id="procufiles"></ul>
						</div>
					</td>	
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>