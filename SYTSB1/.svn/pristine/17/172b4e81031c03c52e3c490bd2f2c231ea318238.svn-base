var smallMap = null;
var drive = null;
var myLushu = null;
var myRoads = [];
$(function(){
	/**
	 * 实现map容器可拖拽
	 */
	var small_map_div = document.getElementById("tccontent2");
	small_map_div.onmousedown = function(ev){
			var oevent = ev || event;
			
			var distanceX = oevent.clientX - small_map_div.offsetLeft;
			var distanceY = oevent.clientY - small_map_div.offsetTop;
			
			document.onmousemove = function(ev){
				var oevent = ev || event;
				small_map_div.style.left = oevent.clientX - distanceX + 'px';
				small_map_div.style.top = oevent.clientY - distanceY + 'px'; 
			};
			
			document.onmouseup = function(){
				document.onmousemove = null;
				document.onmouseup = null;
			};
	}
	$("#tccontent2 .small_map_close").click(function(){
		closeSmallMap();
	});
	/**
	 * 实例化map
	 */
	smallMap = new BMap.Map("small_map", {
        enableMapClick: false
    });    // 创建Map实例
	smallMap.centerAndZoom(startPoint, 15);  // 初始化地图,设置中心点坐标和地图级别10
	var marker1 = new BMap.Marker(startPoint);
	marker1.setLabel(new BMap.Label("四川省特种设备检验院"));
	smallMap.addOverlay(marker1);
	//
	smallMap.enableScrollWheelZoom(true); // 开启鼠标滚轮缩放
	//smallMap.disableDragging();     //禁止拖拽
	// 添加带有定位的导航控件
	var navigationControl = new BMap.NavigationControl({
		// 靠左上角位置
		anchor: BMAP_ANCHOR_TOP_LEFT,
		// LARGE类型
		type: BMAP_NAVIGATION_CONTROL_LARGE,
		// 启用显示定位
		enableGeolocation: true
	});
	smallMap.addControl(navigationControl);

	// 实例化一个驾车导航用来生成路线
    drive = new BMap.DrivingRoute(map, {
    	//renderOptions:{map: map, autoViewport: true},
        onSearchComplete: function(res) {
        	//清除线路
        	clearLushu();
            if (drive.getStatus() == BMAP_STATUS_SUCCESS) {
                var plan = res.getPlan(0);
                var arrPois =[];
                for(var j=0;j<plan.getNumRoutes();j++){
                    var route = plan.getRoute(j);
                    arrPois= arrPois.concat(route.getPath());
                }
                var road = new BMap.Polyline(arrPois, {strokeColor: '#111'});
                myRoads.push(road);
                smallMap.addOverlay(road);
                //smallMap.setViewport(arrPois);
                smallMap.setCenter(startPoint);
                var myIcon = new BMap.Icon('app/gis/scjy/v1/images/cc4.png', new BMap.Size(90,55),{anchor : new BMap.Size(45, 23)});
                myIcon.setImageSize(new BMap.Size(90,55));
                myLushu = new BMapLib.LuShu(smallMap,arrPois,{
								                        defaultContent:"",
								                        autoView:true,//是否开启自动视野调整，如果开启那么路书在运动过程中会根据视野自动调整
								                        icon  : myIcon,
								                        speed: 4500,
								                        enableRotation:true,
								                        landmarkPois: []
								                        }); 
                myLushu.start(dCloseMap);
            }
        }
    });
    smallMap.setMinZoom(12);
    closeSmallMap();
});

function clearLushu(marker){
	for(var i =0;i<myRoads.length;i++){
		smallMap.removeOverlay(myRoads[i]);
	}
	if(marker){
		smallMap.removeOverlay(marker);
	}else{
		var smallMapOverlays = smallMap.getOverlays();
		for(var i =0;i<smallMapOverlays.length;i++){
			if(smallMapOverlays[i].ba 
					&& myLushu 
					&& myLushu._marker 
					&& smallMapOverlays[i].ba == myLushu._marker.ba){
				smallMap.removeOverlay(smallMapOverlays[i]);
			}
		}
	}
	
}
function dCloseMap(){
	setTimeout(closeSmallMap, 10000);
}
function closeSmallMap(){
	$("#tccontent2").hide();
}
function openSmallMap(){
	$("#tccontent2").show();
}

