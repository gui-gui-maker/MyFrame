<%@page import="com.khnt.base.Factory"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>文件拍摄仪器拍摄</title>
<%@include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
	var height = $(window).height();
	$(function() {
		$("#imgContainer").css("height",height-22+"px")
		try {
			var isStarted = captrue.bStartPlayRotate(270);
			if (!isStarted) {
				alert("启动拍摄设备失败，可能设备未连接或已被其他程序占用！", "错误提示");
				api.close();
			}
			//删除目录
			captrue.bDeleteFile(copyTargetPath);
			//创建目录
			captrue.bCreateDir(copyTargetPath);
			captrue.vSetDelHBFlag(true);//切除黑边
		} catch (e) {
			alert("您可能还没有安装视频设备的插件。\n请到《常用软件》中下载并安装《文件拍摄仪控件.exe》\n安装完成后需要重新启动浏览器。");
			api.close();
		}
		$("#toptoolbar").ligerToolBar({
			items : [ "-", {
				text : '拍摄',
				click : takePhoto,
				icon : "photograph"
			}, "-", {
				text : '上传',
				click : uploadImage,
				icon : "submit"
			} ]
		});
	});
	var tempfilePath = "C:\\JYP";//拍摄仪临时文件夹，如果该文件夹不存在，则需要用管理员权限启动IE
	var nameNumb = 1;
	var copyTargetPath = tempfilePath + "\\imagesOfPai\\";//等待上传的目标文件夹
	var imagedPath = tempfilePath;//拍照保存的文件夹
	var targetImaged = new Array();//等待上传的文件列表
	var businessId = api.data.cameraConfig.businessId ? api.data.cameraConfig.businessId : randomChar(32);
	var workItem = api.data.cameraConfig.workItem || "";
	var fileFolder = api.data.cameraConfig.folder || "";
	var fileNum = api.data.cameraConfig.fileNum;
	var isUploaded = true;//拍摄后是否已上传
	
	function takePhoto() {
		if (fileNum > 0) {
			if (targetImaged.length >= fileNum) {
				$.ligerDialog.alert("您本次最多只能拍摄" + fileNum + "张照片！");
				return;
			}
		}
		var fileName = "img";
		captrue.vSetImageQuality(35);
		captrue.vSetRotate(0);

		var isSaved = captrue.bSaveJPG(imagedPath, fileName);
		if (isSaved) {
			var copyName = (fileName + nameNumb);
			if (fileNum === 1) {
				copyName = fileName;
			}
			copyP = copyTargetPath + copyName + ".JPG";//拍照后拷贝的目标文件全路径
			imageP = imagedPath + fileName + ".JPG";//拍照后生成的目标文件
			var isCopied = captrue.bCopyFile(copyP, imageP);//拷贝文件到一个单独的文件夹
			if (isCopied) {
				copyP = copyP.replace(/\\/g, "/");
				targetImaged.push({
					"isUploaded" : false,
					"path" : copyP
				});
				var b64EnCode =  captrue.sGetBase64();
				var htmlStr = "<div class='file-item'><img src='data:image/jpeg;base64," + b64EnCode + "' title='" + unescape(copyName) + 
						"'/><button class='l-button' type='button' onclick='deletePhto(this,\"" + copyP + "\")'>删除</button></div>"
				$(htmlStr).hover(function(){
					$(this).find("button").show();
				},function(){
					$(this).find("button").hide();
				}).appendTo("#imgContainer");
			} else {
				$.ligerDialog.alert("拍摄失败，请尝试重新打开本页面！");
				return;
			}
		} else {
			$.ligerDialog.alert("拍摄失败，请尝试重新打开本页面！");
			return;
		}
		nameNumb++;
		isUploaded = false;
	}
	function deletePhto(buttonObj, imgP) {
		if (captrue.bDeleteFile(imgP)) {
			$(buttonObj.parentNode).remove();
			for ( var fileObj in targetImaged) {
				if (imgP == targetImaged[fileObj]["path"]) {
					delete targetImaged[fileObj];
					targetImaged.length = targetImaged.length - 1;
				}
			}
		}
	}
	
	function uploadImage() {
		var ctx = "${pageContext.request.contextPath}";
		var url = (ctx == "/" ? "" : ctx) + "/fileupload/upload.do?businessId="	+ businessId;
		if (workItem) {
			url += "&workItem=" + workItem;
		}
		if(fileFolder){
			url += "&folder=" + fileFolder;
		}
		var l = targetImaged.length;
		if (l > 0) {
			var filePathes = "";
			for ( var fileObj in targetImaged) {
				var file = targetImaged[fileObj];
				filePathes += ("|" + file["path"]);
			}
			if (filePathes) {
				filePathes = filePathes.substring(1, filePathes.length);
				var uploadSucess = captrue.bUpLoadImage(filePathes,
						"${pageContext.request.serverName}",
						"<%=Factory.getSysPara().getProperty("server.http.port","80")%>", url);
			}
			afterUpload(function(){
				deleteImages();
				isUploaded = true;
				api.close();
			});
		} else {
			$.ligerDialog.alert("您还未拍照，无法上传！");
		}
	}
	function deleteImages() {
		if (targetImaged.length > 0) {
			for ( var fileObj in targetImaged) {
				flag = captrue.bDeleteFile(targetImaged[fileObj]["path"]);
			}
		}
	}
	function afterUpload(callback) {
		$.getJSON("fileupload/busFiles.do?businessId=" + businessId+"&item="+workItem,function(response){
			if (response.success) {
				var array = [];
				for ( var file in response.data) {
					var f = response.data[file];
					if(f.filePath) f["path"] = f.filePath;
					array.push(f);
				}
				var f = api.data.cameraConfig.onAfterUpload;
				if (typeof (f) === "function") {
					f(response.data);
				}
				
				if(typeof (callback) === "function" ){
					callback();
				}
			}
		});
	}
	/**
	 * 生成随机字符串
	 * @param {type} l 随机串长度
	 * @returns {String}
	 */
	function randomChar(l) {
		var x = "0123456789qwertyuioplkjhgfdsazxcvbnm";
		var tmp = "";
		for (var i = 0; i < l; i++) {
			tmp += x.charAt(Math.ceil(Math.random() * 100000000) % x.length);
		}
		return tmp;
	}
</script>
<style type="text/css">
.file-item{display:inline-block;width:3cm;height:4cm;margin:2mm;position:relative;}
.file-item img{width:3cm;height:4cm;}
.file-item button{position:absolute;bottom:2px;left:0;width:92%;display:none;padding:2px 0;}
</style>
</head>
<body>
	<script type="text/javascript">
		var width = height * 3 / 4;
		document.write('<table border="1" width="100%" height="100%" class="l-table"><tr><td id="objectTd" rowspan="2" height="100%" width="'+ width
				+ '"> <object classid="clsid:454C18E2-8B7D-43C6-8C17-B1825B49D7DE" id="captrue" width="'+ width
				+ '" height="100%"></object></td><td style="height:22px;"><div id="toptoolbar" style="height:22px;"></div><div id="imgContainer" style="overflow:auto"></div></tr></table>');
	</script>
</body>
</html>