<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<title>新增合同</title>
<!-- 每个页面引入，页面编码、引入js，页面标题 -->
<%@include file="/k/kui-base-form.jsp"%>
<!-- <script type="text/javascript"
	src="app/tzsb/data/device/shortcutIns/report_insp.js"></script> -->


<script type="text/javascript" src="pub/fileupload/js/fileupload.js"></script>
<script type="text/javascript">
	var pageStatus = "${param.status}";
	$(function() {
	    $("#form1").initForm({
			success: function (response) {//处理成功
	    		if (response.success) {
	    			
	            	top.$.dialog.notice({
	             		content: "保存成功！"
	            	});
	            	api.close();
	         		api.data.window.submitAction();
	    		} else {
	           		$.ligerDialog.error('保存失败！<br/>' + response.msg);
	      		}
			}, getSuccess: function (response){
				
				if (response.attachs != null && response.attachs != undefined)
					showAttachFile(response.attachs);
			}, toolbar: [
			{text: "保存", icon: "save", click: function(){
				
				if ($("#form1").validate().form()) {
		    		//if(checkBasic()){
		    			//if(confirm("确定保存？")){
		    				//表单提交
		    				$("#form1").submit();
			    		//}
		    		//}				    		
		    	}
	      			}
	      		},
				{text: "关闭", icon: "cancel", click: function(){api.close();}}
			], toolbarPosition: "bottom"
			
		});		
		
		var receiptUploadConfig = {
				fileSize : "100mb",	//文件大小限制
				businessId : "",	//业务ID
				buttonId : "procufilesBtn",		//上传按钮ID
				container : "procufilesDIV",	//上传控件容器ID
				title : "附件",	//文件选择框提示
				extName : "doc,docx,dot,dotx,pdf",	//文件扩展名限制
				workItem : "",	//页面多点上传文件标识符号
				fileNum : 2,	//限制上传文件数目
				callback : function(files){	//上传成功后回调函数,实现页面文件显示，交与业务自行后续处理
					addAttachFile(files);
				}
		};
		var receiptUploader= new KHFileUploader(receiptUploadConfig);
	
	});
	        function directChange(){ 
	        	var obj=$("#form1").validate().form();
	    	 if(obj){
	    		 
	    		 //$("#form1").submit();
	    	 }else{
	    		 return;
	    	}} 
	        
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
//			alert(files);
			if("${param.status}"=="detail"){
				$("#procufilesBtn").hide();
			}
			if(files){
				var attContainerId="procufiles";	
				$.each(files,function(i){
					var file=files[i];
					$("#"+attContainerId).append("<li id='"+file.id+"'>"+
							"<div><a href='fileupload/download.do?id="+file.id+"&fileName="+file.name+"'>"+file.name+"</a></div>"+
							"<div class='l-icon-close progress' onclick='deleteUploadFile(\""+file.id+"\",\""+file.path+"\",this,getUploadFile)'>&nbsp;</div>"+
							"</li>");
					//setinput(file.name);
				});
				getUploadFile();
			}
		}
		// 显示附件文件
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
						 //显示附件
						$("#"+attContainerId).append("<li id='"+file.id+"'>"+
												"<div><a href='fileupload/download.do?id="+file.id+"&fileName="+file.fileName+"'>"+file.fileName+"</a></div>"+
												"</li>");
					});
				}
				//修改
				else if("${param.status}"=="modify"){
					$.each(files,function(i){
						var file=files[i];
						$("#"+attContainerId).append("<li id='"+file.id+"'>"+
								"<div><a href='fileupload/download.do?id="+file.id+"&fileName="+file.fileName+"'>"+file.fileName+"</a></div>"+
								"<div class='l-icon-close progress' onclick='deleteUploadFile(\""+file.id+"\",\""+file.filePath+"\",this,getUploadFile)'>&nbsp;</div>"+
								"</li>");
						//setinput(file.name);
					});
					getUploadFile();
				}
			}
	    }
	
	

</script>
</head>
<body>
	<form id="form1" action="pact/action/saveRule.do"
		getAction="pact/action/getRule.do?id=${param.id}">
	
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>文件信息</div>
			</legend>
			<table border="1" cellpadding="3" cellspacing="0" width=""
				class="l-detail-table">
				<tr>
				<input type="hidden" id="id" name="id"  />
			
					
					<td class="l-t-td-left">文件编号：</td>
					<td class="l-t-td-right"><input name="file_num" type="text"
					ltype='text' validate="{required:true,maxlength:30}" /></td>
					<td class="l-t-td-left">文件名称：</td>
					<td class="l-t-td-right"><input name="file_name" type="text"
					ltype='text' validate="{required:true,maxlength:300}" /></td>
					
				</tr>
			
				
				
			</table>
		</fieldset>
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>上传文件</div>
			</legend>
			<table cellpadding="3" cellspacing="0" class="l-detail-table">
				<tr>
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