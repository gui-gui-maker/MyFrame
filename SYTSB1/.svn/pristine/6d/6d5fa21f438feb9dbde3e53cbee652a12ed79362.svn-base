<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.scts.cspace.fileupload.web.CloudAttachmentAction"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>文件上传</title>
    <link rel="stylesheet" href="plupload/js/jquery.plupload.queue/css/jquery.plupload.queue.css" type="text/css"></link>
    <script type="text/javascript" src="jquery-1.8.0.min.js"></script>	
	<script type="text/javascript" src="plupload/js/plupload.full.min.js"></script>
    <script type="text/javascript" src="plupload/js/plupload.dev.js"></script>
	<script type="text/javascript" src="plupload/js/moxie.js"></script>
    <script type="text/javascript" src="plupload/js/i18n/zh_CN.js"></script>
    <script type="text/javascript" src="plupload/js/jquery.plupload.queue/jquery.plupload.queue.js"></script>
  <body style="padding: 0;margin: 0;">
    <div id="uploader">&nbsp;</div>
<script type="text/javascript">
var files = [];
var errors = [];
var type = 'file';
var chunk = eval('${param.chunk}');
var max_file_size = '3072mb';
var filters = {title : "文档", extensions : "zip,doc,docx,xls,xlsx,ppt,pptx,pdf,jpg,png,gif,rar,txt,bmp,jpeg,tif,dwg,mp3,mp4,wav,avi,ram,rm,mpg,mov,asf,mid,oog,midi,rmvb,flv,3gp"};
$("#uploader").pluploadQueue($.extend({
	runtimes : 'html5,flash,html4',
	url : '/fileupload2/upload.do?pid=${param.pid}&spaceType=${param.spaceType}&spaceId='+sessionStorage.getItem("owner_root")+"&if_down=${param.if_down}",
	max_file_size : max_file_size,
	file_data_name:'file',
	filters : [filters],
	// Flash settings
	flash_swf_url : 'app/cloud_platform/upload/plupload/js/Moxie.swf',
	// Silverlight settings
	silverlight_xap_url : 'app/cloud_platform/upload/plupload/js/Moxie.xap',
	init:{
		FileUploaded:function(uploader,file,response){
			if(response.response){
				var rs = $.parseJSON(response.response);
				if(rs.status){
					//alert(file.size)
					files.push(file.name);
				}else{
					errors.push(file.name);
				}
			}
		},
		
		UploadComplete:function(uploader,fs){
			var e= errors.length ? ",失败"+errors.length+"个("+errors.join("、")+")。" : "。";
			alert("上传完成！共"+fs.length+"个。成功");
			target.window("close");
		}
	}
},(chunk ? {chunk_size:'5mb'} : {})));
//onunload 
window.onbeforeunload= function (){
	//stopup();
	}

</script>
  </body>
</html>
