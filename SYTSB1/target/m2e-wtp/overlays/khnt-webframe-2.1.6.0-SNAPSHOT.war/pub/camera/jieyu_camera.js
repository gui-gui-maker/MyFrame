var openSystemCameraImpl = function(uconfig) {
	var _config = {
        businessId: null,
        workItem: null,
        initFileName: null,//拍摄的照片名称，默认为img
        showNameInput: false,//默认为false，不显示输入文件标题的文本框
        fileNum : 0,//为0则不限制上传数量
        folder: null,
        onBeforePhoto: function() {
            return true;
        },
        onAfterPhoto: function() {
            return true;
        },
        onBeforeUpload: function() {
            return true;
        },
        onAfterUpload: function() {
        }
    };
	var config  = {};
    if (uconfig) {
    	config = $.extend(_config,uconfig)
    }
    var c = {
        id: "camera_page",
        width: 700,
        height: 425,
        lock: true,
        data: {window: window, cameraConfig: _config},
        title: "拍摄文件",
        content: 'url:pub/camera/jieyu_camera.jsp',
        close: function() {
            var cameraPage = top.$.dialog.list["camera_page"].content;
            if (cameraPage.isUploaded === false) {
                if (confirm("您拍摄的图片还没有上传，确定要关闭吗？")) {
                    cameraPage.deleteImages();
                    if (cameraPage.captrue && cameraPage.captrue.bStopPlay && cameraPage.captrue.bStopPlay()) {
                        cameraPage.$("body").html("");
                    }
                    return true;
                }
                return false;
            } else {
                if (cameraPage.captrue && cameraPage.captrue.bStopPlay && cameraPage.captrue.bStopPlay()) {
                    cameraPage.$("body").html("");
                }
            }
        }
    };
    if (api) {//设置parent
        c["parent"] = api;
    } else {
        if (parent.api) {//当前页面在iframe内。
            c["parent"] = parent.api;
        }
    }
    top.$.dialog(c).max();
}