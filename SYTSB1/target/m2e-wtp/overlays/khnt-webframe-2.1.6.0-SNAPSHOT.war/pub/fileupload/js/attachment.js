function deleteFile(id, callback) {
    $.ligerDialog.confirm("确定删除文件？<p style='color:red'>注意：删除后不可恢复！</p>", function(flag) {
		if(flag){
		    $.getJSON('fileupload/deleteAtt.do?id=' + id, function(resp) {
		    	if(resp.success){
			    	if (callback)
			    		callback(id);
		    	}
		    	else{
		    		alert("删除失败！");
		    	}
		    });
		}
    });
}

function openUploader(data) {
    top.$.dialog({
		width : 750,
		height : 350,
		lock : true,
		parent : api,
		max: false,
	    min: false,
		title : "上传文件",
		content : "url:pub/fileupload/kui-fileupload.jsp",
		data : data
    });
}