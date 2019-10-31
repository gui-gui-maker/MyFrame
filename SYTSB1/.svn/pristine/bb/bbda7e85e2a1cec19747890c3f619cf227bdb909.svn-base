/** 框架的上传插件 */
document
		.write(unescape('%3Cscript type="text/javascript" src="pub/fileupload/js/fileupload.js"%3E%3C/script%3E'));

$.fn.extend({
	mask : function(content) {
		comm.mask(content);
	},
	unmask : function() {
		if (comm.dialog) {
			comm.unmask();
		}
	}
});

function initUpload(itemName, businessId) {
	var uploader = "uploader" + itemName;
	var uploadfilebtn = "uploadfilebtn" + itemName;
	var uploadctr = "uploadctr" + itemName;
	var attList = "attList" + itemName;
	var obj = $("#" + uploadctr);
	if (obj && obj.length > 0) {
		initFileUpload(uploader, uploadfilebtn, uploadctr, attList, businessId,
				itemName);
		$("#" + uploadctr).on("click", "span", function() {
			var fileId = $(this).attr("fileId");
			deleteUploadFile(fileId, "", "", function() {
				$("#" + attList).html("");
				$("#" + uploadfilebtn).show();
			});
		});
		
		$.getJSON("fileupload/busFiles.do?businessId=" + businessId + "&item="
				+ itemName, function(resp) {
			if (resp.success) {
				var files=resp.data;
				for(var i in files){
					showAttachments(files[i], attList, uploadfilebtn, itemName);	
				}
			} else {
				$.ligerDialog.error("获取附件失败！");
			}
		});
	}

}

function initFileUpload(uploader, uploadfilebtn, uploadctr, attList,
		businessId, workItem) {
	window[uploader] = new KHFileUploader({
		buttonId : uploadfilebtn,
		container : uploadctr,
		businessId : businessId,
		workItem : workItem,
		title : "图片文件(PNG,JPG,BMP,GIF)",
		extName : "png,jpg,bpm,gif",
		fileSize : "400kb",
		fileNum : 1,
		fileUploaded : function(file) {
			showAttachments(file, attList, uploadfilebtn, workItem);
		},
		uploadProgress : function(file) {
			if (file.percent < 100)
				$("#" + file.id).text(file.percent + "%");
		}
	});
}

function showAttachments(file, attList, uploadfilebtn, workItem) {
	$("#" + attList).html(
			"<div class='uploading' " + file.id
					+ "'><img src='fileupload/previewImage.do?id=" + file.id
					+ "' id='" + file.id + "'><span fileId='" + file.id
					+ "'>删除</span></div>");
	// myuploader.destroy();
	$("#" + uploadfilebtn).hide();
	$("#" + workItem).val(file.id);
	// window[uploader] = null;
}