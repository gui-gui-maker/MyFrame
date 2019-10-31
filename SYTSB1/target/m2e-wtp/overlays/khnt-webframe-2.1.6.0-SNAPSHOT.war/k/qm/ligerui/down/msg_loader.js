$(function(){//jQuery页面载入事件
	msg_loader_qm();
});

function msg_loader_qm() {
	$.ajaxSetup({
		cache: false
	});
	var poi_asyn = kui["QM2EXCEL_TYPE"];
	if(poi_asyn=="poi_asyn"){
		startInterval_qm();
	}
}
//消息和任务提醒
function startInterval_qm(){
	var poi_asyn = kui["QM2EXCEL_TYPE"];
	if(poi_asyn!="poi_asyn"){
		return;
	}
	$.getJSON("qm?__method=getDownLoadInfoNum",{pageid:'QM999999999'},function(resp){
		var oneExe1=setTimeout(function(){
			startInterval_qm();
		},kui["qmMsgIntervalMS"] || 10000);
    	if(!resp.success || resp.num==0){
    		return;
    	}
        if(resp.num > 0){
        	top.$.dialog({
        	    id: 'msg',
        	    title: '数据导出列表',
        	    content: 'url:k/qm/ligerui/down/message.jsp',
        	    width: 300,
        	    height: 120,
        	    left: '50%',
        	    top: '0%',
        	    data:{
        	    	window:window,
        	    	data:resp.data
        	    },
        	    max: false,
        	    min: false,
        	    lock:false,
        	    fixed: true,
        	    drag: false,
        	    resize: false
        	});
        }
    });
}