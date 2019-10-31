<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
    <title></title>
    <%@include file="/k/kui-base-form.jsp" %>
    <script type="text/javascript" src="pub/fileupload/js/fileupload.js"></script>
    <script type="text/javascript">
        $(function () {
			$("#form1").initForm({    //参数设置示例
				success: function (response) {
					if(response.success){
						top.$.notice("保存成功！",3);
						api.data.window.Qm.refreshGrid();
						api.close();
					}
					else{
						$.ligerDialog.error("操作失败！<br/>" + response.data);
					}
				},
			getSuccess : function(response) {
					if (response.onefiles != null && response.onefiles != undefined){
						showAttachFile(response.onefiles);
					}
				}
			});
		});
        $(function(){
        	 var oneUploadConfig = {
         			fileSize : "10mb",//文件大小限制
         			businessId : "",//业务ID
         			folder:"food/pic",
         			buttonId : "receiptfilesBtn",//上传按钮ID
         			container : "receiptfilesDIV",//上传控件容器ID
         			attType : '',//文件存储类型；1:数据库，0:磁盘，默认为磁盘
         			title : "图片选择",//文件选择框提示
         			extName : "jpg,jpeg,gif,bmp,png",//文件扩展名限制
         			fileNum : 1,//限制上传文件选择数目 弹出选择框 可以选择文件数量限制
         			workItem : "one",//页面多点上传文件标识符号
         			callback : function(files){//上传成功后回调函数,实现页面文件显示，交与业务自行后续处理
         				addOneFile(files);
         			}
         		};
     		var oneUploader= new KHFileUploader(oneUploadConfig);
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
				var attContainerId="receiptfiles";
				$.each(files,function(i){
					var data=files[i];
							$("#receiptfilesBtn").hide();
     						createFileView(data.id,data.name,$("head").attr("pageStatus")=="detail"?false:true,"receiptfiles",true,function(fid){
     							deleteFileUp();
     						})
				});
				getUploadOneFile();
			}
		}
		//将上传的所有文件id写入隐藏框中
		function getUploadOneFile(){
			var attachId="";
			var i=0;
			$("#receiptfiles li").each(function(){
				attachId+=(i==0?"":",")+this.id;
				i=i+1;
			});
			if(attachId!=""){
				attachId=attachId.substring(0,attachId.length);
			}
			$("#uploadFile").val(attachId);
		}
		
	    function showAttachFile(files){
	    	if("${param.status}"=="detail"){
				$("#receiptfilesBtn").hide();
			}
			if(files!=null&&files!=""){
				$.each(files,function(i){
					var data=files[i];
					$("#receiptfilesBtn").hide();
					createFileView(data.id,data.fileName,$("head").attr("pageStatus")=="detail"?false:true,"receiptfiles",true,function(fid){
						deleteFileUp();
					})
				})
			}
	    }
	    function deleteFileUp(){
			$("#receiptfilesBtn").show();
			$("#procufilesBtn").show();
			getUploadOneFile();
		}
    </script>
</head>
<body>
<form id="form1" action="dining/food/saveFood.do" getAction="dining/food/detailFood.do?id=${param.id}">
    <input type="hidden" id="id" name="id">
    <input name="uploadFile" type="hidden" id="uploadFile" />
    <table cellpadding="3" class="l-detail-table">
        <tr>
            <td class="l-t-td-left">菜品名称：</td>
            <td class="l-t-td-right" colspan="3"><input name="name" type="text" ltype='text' validate="{required:true,maxlength:32}"/></td>
        </tr>
        <tr>
            <td class="l-t-td-left">菜品类型：</td>
            <td class="l-t-td-right" colspan="3"><u:combo name="ftype" code="fCategory" validate="{required:true}"></u:combo></td>
        </tr>
        <tr>
            <td class="l-t-td-left">菜品描述：</td>
            <td class="l-t-td-right" colspan="3"><textarea name="fdesc" rows="5" validate="{required:true,maxlength:512}"></textarea></td>
        </tr>
        <tr>
				<!-- <td class="l-t-td-left">图片：</td>
				<td class="l-t-td-right">
					<p id="onefileDIV">
						<a class="l-button" id="receiptfilesBtn"><span
							class="l-button-main"><span class="l-button-text">选择文件</span>
						</span>
						</a>
					</p>
					<div class="l-upload-ok-list">
							<ul id="onefile"></ul>
					</div>
				</td> -->
				<td class="l-t-td-left">图片：</td>
				<td  class="l-t-td-right" colspan="3" id="receiptfilesDIV">
						<input name="attchNames" id="attchNames" type="hidden"/>
						<a class="l-button3"  id="receiptfilesBtn" >+</a>
					<div class="l-upload-ok-list"><ul id="receiptfiles"></ul></div>
				</td>
		</tr>
    </table>
</form>
</body>
</html>
