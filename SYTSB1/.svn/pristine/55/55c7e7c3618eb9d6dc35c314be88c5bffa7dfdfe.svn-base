/**
 * 文件展示页面
 * @param obj 后台获取文件json的url
 */
var _this;
var _callback;
function getFilesPath(url,e,callback) {
	_this = e;
	_callback = callback;
	var width = 700;
	var height = 400;
	var windows = top.$.dialog({
		parent : api,
		width : width,
		height : height,
		lock : true,
		data : {
			window : window
		},
		title : "选择文件",
		content : 'url:pub/fileviewer/FileView.jsp?obj=' + url
	});
}
/**
 * 设置路径
 * @param retvalue
 */
function setPath(retvalue){
	try {
		_this.setValue(retvalue);
	} catch (e) {
		$("input[name='"+_this.id+"']").val(retvalue);
		_this.src=retvalue;
	}
	if(_callback){
		_callback(retvalue);
	}
}
