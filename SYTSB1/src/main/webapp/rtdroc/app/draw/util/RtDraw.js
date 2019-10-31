
function RtDroc(fileId,imageId,callback){		
	top.$.dialog({			
		lock : true,
		parent : api,
//		id : "RtDroc"+fileId,
		title : "绘制",
		content : "url:rtdroc/app/view2/index.jsp?fileId="+fileId+"&imageId="+imageId,
		cancel: true,
		ok : function() {
			var result = this.iframe.contentWindow.getSelectResult();			
			//alert(datas);
			if(callback){
				callback(result);
			}
			else 
				return false;
		}
	}).max();
}


function RtDrocDataOnly(fileId,imageId,callback){		
	top.$.dialog({			
		lock : true,
		parent : api,
//		id : "RtDroc"+fileId,
		title : "绘制",
		content : "url:rtdroc/app/view2/index.jsp?fileId="+fileId+"&imageId="+imageId,
		cancel: true,
		ok : function() {
			var result = this.iframe.contentWindow.saveDataOnly();			
			//alert(datas);
			if(callback){
				callback(result);
			}
			else 
				return false;
		}
	}).max();
}