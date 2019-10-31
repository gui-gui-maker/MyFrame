$(function(){
	queryRtCache();
	//queryWxCache();
	//startShowRtCache(10,5000,unit_id);
	//但画出部门检验区划
	
});
var rtCache = {};//缓存各部门出的报告
var rtCacheInterval = null;

var rtCacheOverlays = {};
var wxCache = {};//缓存各部门出的报告
var wxCacheInterval = null;
var wxCacheOverlays = {};

function queryRtCache(){
	$.ajax({
		url:"gis/device/rtCache2.do",
		type:"post",
		data:{},
		//async:false,
		dataType:"json",
		success:function(data){
			if(data.success){
				rtCache = {};
				$.each(data,function(key,value){
					if(key!="success"){
						rtCache[key]=value;	
						rtCacheOverlays[key]=[];
					}
				});
				
				if(rtCache[unit_id].length>0){
					var devs = rtCache[unit_id];
					//但画出部门检验区划
					closeCdMap();
					for(var i=0;i<CDAREAS.length;i++){
						if(CDAREAS[i].code == devs[0].DEVICE_AREA_CODE){
							drawCd(CDAREAS[i]);
						}
					} 
					stopShowRtCache();
					startShowRtCache(6,5000,unit_id);
				}
			}
		}
	});
}
function queryWxCache(){
	$.ajax({
		url:"gis/device/wxCache.do",
		type:"post",
		data:{},
		dataType:"json",
		success:function(data){
			if(data.success){
				wxCache = data.data;
			}
		}
	});
}
function startShowRtCache(num,speed,department){
	//清除定时器
	if(rtCacheInterval){
		clearInterval(rtCacheInterval);
		rtCacheInterval = null;
	}
	rtCacheInterval = setInterval(function(){
		if(department){
			var value = rtCacheOverlays[department];
			if(value.length>=num){
				var delNum = Math.floor(Math.random()*num+1);
				for(var i=0;i<delNum;i++){
					map.removeOverlay(value[0]);
					value.shift();
				}
			}
			showNotRepeat(rtCache,department,0);
		}else{
			$.each(rtCacheOverlays,function(key,value){
				if(value.length>=num){
					var delNum = Math.floor(Math.random()*num+1);
					for(var i=0;i<delNum;i++){
						map.removeOverlay(value[0]);
						value.shift();
					}
				}
			});
			$.each(rtCache,function(key,value){
				showNotRepeat(rtCache,key,0);
			});
		}
	}, speed);
}
function showNotRepeat(cache,key,num){
		var jn = Math.floor(Math.random()*cache[key].length);
		var flag = true;
		if(rtCacheOverlays[key]){
			for(var i=0;i<rtCacheOverlays[key].length;i++){
				if(cache[key][jn].ENTER_OP_NAME == rtCacheOverlays[key][i]._element.ENTER_OP_NAME){
					flag=false;
					num++;
					if(num < 11){
						showNotRepeat(cache,key,num);
					}
					break;
				}
			}
		}
		if(flag){
			queryStar(rtCache[key][jn],{"type":"report","box":"rtCacheOverlays","cbox":key});
		}
}
function stopShowRtCache(){
	if(rtCacheInterval){
		clearInterval(rtCacheInterval);
		rtCacheInterval = null;
	}
	$.each(rtCacheOverlays,function(key,value){
		for(var i=0;i<value.length;i++){
			map.removeOverlay(value[i]);
		}
		value.length=0;
	});
}
function startShowWxCache(num,speed){
	if(wxCacheInterval){
		clearInterval(wxCacheInterval);
		wxCacheInterval = null;
	}
	wxCacheInterval = setInterval(function(){
		if(wxCacheOverlays.length>=num){
			var delNum = Math.floor(Math.random()*num+1);
			for(var i=0;i<delNum;i++){
				map.removeOverlay(wxCacheOverlays[0]);
				wxCacheOverlays.shift();
			}
		}
		var jn = Math.floor(Math.random()*wxCache.length);
		var flag = true;
		for(var i=0;i<wxCacheOverlays.length;i++){
			if(wxCache[jn].ID == wxCacheOverlays[i]._index){
				flag = false;
				break;
			}else{
				
			}
		}
		if(flag){
			queryStar(wxCache[jn],{"type":"weixin","box":"wxCacheOverlays"});
		}
	}, speed);
}
function stopShowWxCache(){
	if(wxCacheInterval){
		clearInterval(wxCacheInterval);
		wxCacheInterval = null;
	}
	for(var i=0;i<wxCacheOverlays.length;i++){
		map.removeOverlay(wxCacheOverlays[i]);
	}
	wxCacheOverlays.length=0;
}
