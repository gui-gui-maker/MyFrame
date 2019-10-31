
/**
 * 获取页面内容
 * @param rtPageId rtpage的id
 * @param pageCode 页面code
 * @param callback 回调
 * @returns 页面内容
 */
function getPageData(rtboxId,pageCode,callback){
	if(info_id==null||info_id==""){
		//模板自身预览
		$.post("com/rt/page/getPageContent.do?rtPageId="+rtboxId+"&pageCode="+pageCode+"&rtCode="+rtboxCode,function(res){
			if(res.success){
				if(callback){
					callback.call(this,res.data);
				}
			}else{
				alert("查询模板内容失败！")
			}
		})
	}else{
		//通过模板id显示
		getPageDataByTemple(pageCode,callback);
	}
	
}


function getPageDataByTemple(pageCode,callback){
	//alert(info_id+"---"+pageCode)
		$.post("inspectionInfo/basic/getPageContent.do",{"infoId":info_id,"pageCode":pageCode,"modelType":modelType},function(res){
			if(res.success){
				if(callback){
					callback.call(this,res.data);
				}
			}else{
				alert("查询模板内容失败！")
			}
		})
	
}