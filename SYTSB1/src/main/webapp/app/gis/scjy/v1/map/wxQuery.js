//微信查询设备
var wxShowNew = false;
var wxNewOverlays = [];//缓存微信图表的集合
var wxNewCache = [];//缓存微信查询
var wxNewInterval = null;//展示微信查询的定时器
var wxNewSequence = 0;//用于顺序显示新增微信查询
function clearWeixin(){
	for(var i=0;i<wxNewOverlays.length;i++){
		map.removeOverlay(wxNewOverlays[i]);
	}
	wxNewOverlays.length = 0;
}
function xwQuery(){
	  $.ajax({
			url:"gis/device/xwQuery.do",
			type:"post",
			data:{},
			dataType:"json",
			success:function(data){
				if(data.success&&data.data.length>0&&wxShowNew ==false){
					//刷新统计
					countWeixin();
					wxShowNew = true;
					//顺序显示微信查询
					wxNewCache = data.data;
					//清空
					clearWeixin();
					//清空微信缓存
					stopShowWxCache();
					//更新通知栏
					for(var i=0;i<wxNewCache.length;i++){
						notice(wxNewCache[i],{type:'weixin',isNew:true});
						changeScrollContent("成功返回关键字"+wxNewCache[i].QUERYCONTENT+"的微信查询请求");
					}
					wxNewSequence = 0;
					wxNewInterval = setInterval(function(){
							if(wxNewOverlays.length >= 8){
								map.removeOverlay(wxNewOverlays[0]);
								wxNewOverlays.shift();
							}
							var flag = true;
							for(var i=0;i<wxNewOverlays.length;i++){
								if(wxNewCache[rtSequence].ID == wxNewOverlays[i]._index)
									flag = false;
								break;
							}
							if(flag){
								queryStar(wxNewCache[i],{"type":"weixin","isNew":true});
							}
							//当最后一个显示完时清除定时器
							wxNewSequence++;
							if(wxNewSequence==wxNewCache.length){
								clearInterval(wxNewInterval);
							}
					},3000);
				}else if(data.success&&data.data.length==0&&!isClientQuery){
					if(wxShowNew){
						clearWeixin();
						queryWxCache();
						startShowWxCache(10,15000);
					}
					wxShowNew = false;
				}
			}
	});
}