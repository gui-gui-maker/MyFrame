Array.prototype.indexOf = function(val) {
	for (var i = 0; i < this.length; i++) {
		if (this[i] == val)
			return i;
	}
	return -1;
};

Array.prototype.remove = function(val) {
	var index = this.indexOf(val);
	if (index > -1) {
		this.splice(index, 1);
	}
};
var startPoint = new BMap.Point(104.101622,30.658819);//省特检院
var ARRPOIS = new Array();
var countLushu = 0;
var lushuDevices = [];
function addLushu(device){
	// 实例化一个驾车导航用来生成路线
    var drv = new BMap.DrivingRoute(map, {
        onSearchComplete: function(res,device,place) {
        	
            if (drv.getStatus() == BMAP_STATUS_SUCCESS) {
            	countLushu ++;
            	var pt = new BMap.Point(device.LONGITUDE,device.LATITUDE);
        		var place = new BMap.Marker(pt);
        		place.setLabel(new BMap.Label(device.DEVICE_USE_PLACE));
        		map.addOverlay(place);
        	
                var plan = res.getPlan(0);
                var arrPois =[];
                for(var j=0;j<plan.getNumRoutes();j++){
                    var route = plan.getRoute(j);
                    arrPois= arrPois.concat(route.getPath());
                }
                var road = new BMap.Polyline(arrPois, {strokeColor: '#111'});
                ARRPOIS.concat(arrPois);
                map.addOverlay(road);
                map.setViewport(ARRPOIS);
                var myIcon = new BMap.Icon('app/gis/scjy/v1/images/cc4.png', new BMap.Size(180,110),{anchor : new BMap.Size(90, 55)});
                myIcon.setImageSize(new BMap.Size(180,110));
                var lushu = new BMapLib.LuShu(map,arrPois,{
									                        defaultContent:"",
									                        autoView:true,//是否开启自动视野调整，如果开启那么路书在运动过程中会根据视野自动调整
									                        icon  : myIcon,
									                        speed: 4500,
									                        enableRotation:"initDirect",
									                        landmarkPois: []
								                        }); 
                lushu.start(function(marker){
                	map.removeOverlay(road);
                	map.removeOverlay(place);
                	if(marker){
                		map.removeOverlay(marker);
                	}
                	for(var i=0;i<arrPois.length;i++){
                		ARRPOIS.remove(arrPois[i]);
                	}
                	countLushu --;
                	//queryFlow(device.RID,device.FLOW_NOTE_NAME,device);
                });
            }
        }
    });
    drv.search(startPoint,new BMap.Point(device.LONGITUDE,device.LATITUDE));
}



