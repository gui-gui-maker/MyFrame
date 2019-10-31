/**
 * 使用拍摄工具进行文件实时拍摄
 */
function openSystemCamera(cfg){
	var cameraSource = kFrameConfig["sys_camera_source"];
	if(cameraSource){
		$("head").append("<script type='text/javascript' src='" + cameraSource + "'></script>");
		openSystemCameraImpl(cfg);
	}
}