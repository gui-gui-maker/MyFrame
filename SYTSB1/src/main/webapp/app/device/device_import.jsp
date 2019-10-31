<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<title>设备信息导入</title>
<%@include file="/k/kui-base-form.jsp" %>
<script type="text/javascript" src="pub/fileupload/js/fileupload.js"></script>
<script type="text/javascript">
	$(function () {
		//pageTitle({to: "#uploadDevice", text: "设备信息导入", style: ""});
		var uploadConfig = {
			fileSize: "100mb",	//文件大小限制
			businessId: "",		//业务ID
			title: "Excel",		//文件选择框提示
			extName: "xls,xlsx",	//文件扩展名限制
			saveDB: false,	//是否往数据库写信息
			attType: "",	//文件存储类型；1:数据库，0:磁盘，默认为磁盘
			workItem: "",	//页面多点上传文件标识符号
			fileNum: 1,		//只上传一个文件
			callback: function (file) {	//回调函数
				//$.ligerDialog.alert("上传成功的文件名：" + file[0].name + "；文件路径：" + file[0].path);
				$.post("device/import/importData.do", {filename:file[0].path}, function(resp) {
					if (resp.success) {
						top.$.dialog.notice({
		             		content: resp.data
		            	});
						//$.ligerDialog.success(resp.data);
						api.data.window.refreshGrid();
						api.close();
					}else{
						$.ligerDialog.error(resp.data);
					}
				});
			}
		};

		//以下上传实例是指定一个页面容器，自动生成按钮和上传文件显示
		uploadConfig.fileContainer = "filecontainer1";
		var uploader1 = new KHKuiFileuploader(uploadConfig);

	});
</script>
</head>
<body>
	<div class="item-tm" id="uploadDevice"></div>
	<fieldset class="l-fieldset" id="filecontainer1">
		<legend class="l-legend">
       		<div>设备信息导入</div>
    	</legend>
	</fieldset>
</body>
</html>