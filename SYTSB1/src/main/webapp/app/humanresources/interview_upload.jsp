<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://bpm.khnt.com" prefix="bpm" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
<title></title>
<%@include file="/k/kui-base-form.jsp"%>
 <link type="text/css" rel="stylesheet" href="app/finance/css/form_detail.css" />
<script type="text/javascript" src="pub/fileupload/js/fileupload.js"></script>
<script type="text/javascript">
$(function() {
           $("#form1").initForm({ //参数设置示例
              	toolbar : [ {
	        	text : '提交情况',
	        	id : 'save',
		        icon : 'save',
		        click : saveAdd
	          }, {
	           	text : '关闭',
		        id : 'close',
		        icon : 'cancel',
		        click : close
	          } ],
	            toolbarPosition : "bottom",
	            getSuccess : function(res) {
	            
	             },
	           afterParse : function(formObj) {//form表单完成渲染后，回调函数

	             }
          });
           
         //上传文件
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
   					$("#testScorePicture").val(files[0].path);
   					$("#image").attr("src","upload/"+files[0].path)
   					$("#image").show();
   					addOneFile(files);
   				}
   			};
   			var oneUploader= new KHFileUploader(oneUploadConfig);
       });
               //添加单文件处理
              function addOneFile(files){
	             if(files){
	               	//判断单个文件是否存在
	               	var uploadFileId = $("#testScorePicture").val();
	               	if(uploadFileId){
			      //删除该id对应的
			      $.getJSON('fileupload/deleteAtt.do?id=' + uploadFileId, function(resp) {
		    	   if(resp.success){
		    	 	$("#" + uploadFileId).remove();
		    		getUploadOneFile();
		    	        }
    		           }) ;
		        }
		          getUploadOneFile();
	              }
                    }
        function close() {
       		api.close();
       	   }
        function saveAdd(){
          if ($("#form1").validate().form()) {
            $("body").mask("正在保存数据，请稍后！");
            $("#form1").submit();
            
         }
        }
        
		
        	

</script>

</head>
<body>

	<form id="form1" action="employeeBaseAction/addInterview.do?id=${param.id}">
		<input type="hidden" id="id" name="id" />
		<input type="hidden" id="testScorePicture" name="testScorePicture" />
	
		<table border="1" cellpadding="3" cellspacing="0" width=""
				class="l-detail-table">
					<tr align="center">
						<td class="l-t-td-right" rowspan="7" ><div ><img id="image" src="" style="display: none;width:150px;height: 200px"></img></div><span id="onefileDIV">
								<a class="l-button" id="onefileBtn"><span
									class="l-button-main"><span class="l-button-text">上传考试图片</span>
								</span>
								</a>
             
					</table>

		</form>
</body>
</html>