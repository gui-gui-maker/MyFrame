var smap = [];

$(function(){
	//初始化地图
	smap[0] = initSMap("smap1");
	smap[1] = initSMap("smap2");
	smap[2] = initSMap("smap3");
	smap[3] = initSMap("smap4");
	smap[4] = initSMap("smap5");
	smap[5] = initSMap("smap6");
	smap[0].isRunning = false;
	smap[1].isRunning = false;
	smap[2].isRunning = false;
	smap[3].isRunning = false;
	smap[4].isRunning = false;
	smap[5].isRunning = false;
	//初始化地图开关
	$("#mapv1 .small_map_close").click(function(){
		closeMap($("#mapv1"));
	});
	$("#mapv2 .small_map_close").click(function(){
		closeMap($("#mapv2"));
	});
	$("#mapv3 .small_map_close").click(function(){
		closeMap($("#mapv3"));
	});
	$("#mapv4 .small_map_close").click(function(){
		closeMap($("#mapv4"));
	}); 
	$("#mapv5 .small_map_close").click(function(){
		closeMap($("#mapv5"));
	}); 
	$("#mapv6 .small_map_close").click(function(){
		closeMap($("#mapv6"));
	}); 
	
	//closeMap($("#mapv2"));
});

function run(device,cls){
	
	var ok = false;
	var pt = new BMap.Point(device.LONGITUDE,device.LATITUDE);
	var place = new BMap.Marker(pt);
	place.setLabel(new BMap.Label(device.DEVICE_USE_PLACE));
	for(var i=0;i<6;i++){
		if(!smap[i].isRunning){
			smap[i].addOverlay(place);
			smap[i].drive.search(startPoint, pt);
			var desc = (!cls?"":cls+":")+device.CHECK_OP_NAME+"前往"+(device.DEVICE_USE_PLACE==null?"目的地":device.DEVICE_USE_PLACE)+"检验"+(device.DEVICE_NAME==null?"设备":device.DEVICE_NAME);
			$("#mapv"+(i+1)).find("div.small_map_desc").html(desc);
			ok = true;
			break;
		}
	}
	return ok;
}

/**
 * 延时关闭map
 * @param $mapContainer
 */
function dCloseMap($mapContainer){
	setTimeout(
			function(){
				closeMap($mapContainer);
			}, 10000
			);
}
function closeMap($mapContainer){
	$mapContainer.hide();
}
function openMap($mapContainer){
	$mapContainer.show();
}
/**
 * 实例化 map
 * @param map 
 * @param container 字符串map容器
 */
function initSMap(container){
	
	var smap = new BMap.Map(container, {enableMapClick: false});    // 创建Map实例
	smap.centerAndZoom(startPoint, 15);  // 初始化地图,设置中心点坐标和地图级别10
	var marker = new BMap.Marker(startPoint);
	marker.setLabel(new BMap.Label("四川省特种设备检验院"));
	smap.addOverlay(marker);
	smap.enableScrollWheelZoom(true); // 开启鼠标滚轮缩放
	smap.setMinZoom(12);
	
	//将驾车导航线路、路书、线路覆盖物集合 增加为map对象的属性
	smap.drive = new BMap.DrivingRoute(smap, {
        onSearchComplete: function(res) {
        	//清除线路
        	clearLushu(smap)
            if (smap.drive.getStatus() == BMAP_STATUS_SUCCESS) {
            	smap.isRunning = true;
                var plan = res.getPlan(0);
                var arrPois =[];
                for(var j=0;j<plan.getNumRoutes();j++){
                    var route = plan.getRoute(j);
                    arrPois= arrPois.concat(route.getPath());
                }
                var road = new BMap.Polyline(arrPois, {strokeColor: '#111'});
                smap.roads = [];
                smap.roads.push(road);
                smap.addOverlay(road);
                smap.setCenter(startPoint);
                var myIcon = new BMap.Icon('app/gis/scjy/v1/images/cc4.png', new BMap.Size(90,55),{anchor : new BMap.Size(45, 23)});
                myIcon.setImageSize(new BMap.Size(90,55));
                smap.lushu = new BMapLib.LuShu(smap,arrPois,{
								                        defaultContent:"",
								                        autoView:true,//是否开启自动视野调整，如果开启那么路书在运动过程中会根据视野自动调整
								                        icon  : myIcon,
								                        speed: 4500,
								                        enableRotation:"initDirect",
								                        landmarkPois: []
								                        }); 
                smap.lushu.start(function(){
                	smap.isRunning = false;
                });
            }
        }
    });
	return smap;
}


/**
 * 去除路书和线路
 * @param lushu
 * @param marker
 * @param roads
 * @param map
 */
function clearLushu(smap,marker){
	if(smap.roads){
		for(var i =0;i<smap.roads.length;i++){
			smap.removeOverlay(smap.roads[i]);
		}
		//清楚后将容器设置为空
		smap.roads = [];
	}
	if(marker){
		smap.removeOverlay(marker);
	}else{
		var overlays = smap.getOverlays();
		for(var i =0;i<overlays.length;i++){
			if(overlays[i].ba 
					&& smap.lushu 
					&& smap.lushu._marker 
					&& overlays[i].ba == smap.lushu._marker.ba){
				smap.removeOverlay(overlays[i]);
			}
		}
	}
}