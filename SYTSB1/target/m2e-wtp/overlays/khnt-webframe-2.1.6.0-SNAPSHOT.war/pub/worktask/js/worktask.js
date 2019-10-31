
/*
config={
	width : 1000,  //任务窗口宽度，可为空，默认 为top宽度和高度的 0.7 
	height : 600,  //任务窗口宽度，可为空，默认 为top宽度和高度的 0.7 
	id : taskId,  //任务id，必须提供
	title : rowData.title,//窗口标题，可为空
	close : function(){
	    //关闭任务窗口的回调函数
	},
	data : {//传入任务页面弹出窗口的js参数，即使 dialog参数
	    "window" : window,
	    "flowname" : rowData.flowname
	}
}*/

/**
 * 打开工作任务
 * 
 * @param id
 */
function openWorktask(config) {
	if (!config.id) {
		$.ligerDialog.alert("参数错误，缺少任务标识！");
		return;
	}
	winOpen({
		//width : config.width?config.width:$(top).width() * 0.7,
		//height : config.height?config.height:$(top).height() * 0.7,
		lock : true,
		parent : parent.api,
		title : "工作任务-" + (config.title ? config.title : ""),
		data : {
			"window" : window
		},
		content : 'url:pub/worktask/doWorktask.do?id=' + config.id,
		close : function() {
			if (config.close)
				config.close();
		}
	}).max();
}