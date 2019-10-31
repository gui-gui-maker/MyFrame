//此功能不用了，不显示
function query7days(){
	$.post("gis/device/queryList.do",{days:7},function(res){
		if(res.success){
			devices = res.data;
			for(var i = 0;i<devices.length;i++){
				//装载今日检验数据
				if(devices[i].ADVANCE_TIME.substring(0,10) == today.substring(0,10)){
					todayDevices[devices[i].ID] = devices[i];
				}
				//有些没坐标
				if(devices[i].LONGITUDE != null && devices[i].LONGITUDE != '' && devices[i].LATITUDE != '' && devices[i].LATITUDE != null)
				{
					pt = new BMap.Point(devices[i].LONGITUDE,devices[i].LATITUDE);
					var marker = new BMap.Marker(pt);
					marker.id = devices[i].ID;
					markers.push(marker);
				}else{
					myGeo.getPoint(devices[i].DEVICE_USE_PLACE, function(point){
						if (point) {
							devices[i].LONGITUDE = point.lng;
							devices[i].LATITUDE = point.lat;
							//反写到base_device_document
							writePointToDocument(devices[i].ID,devices[i].LONGITUDE,devices[i].LATITUDE);
							//点聚合
							var marker = new BMap.Marker(point);
							marker.id = devices[i].ID;
							markers.push(marker);
						}else{
							console.log("地址:"+item.DEVICE_USE_PLACE+",没有解析到结果!");
							return 0;
						}
					}, "成都市");
				}
			}
			markerClusterer = new BMapLib.MarkerClusterer(map, {markers:markers});
		}else{
			alert("loading failure");
		}
	});
}

//不带气球的闪烁点
function StarOverlay(point,index,num){
  this._point = point;
  this._index = index;
  this._num = num;
}
StarOverlay.prototype = new BMap.Overlay();
StarOverlay.prototype.initialize = function(map){
  this._map = map;
  var div = this._div = document.createElement("div");
  div.style.position = "absolute";
  div.style.zIndex = BMap.Overlay.getZIndex(this._point.lat);
  $(div).css({'width':'40px','height':'40px'})
  .append("<div class='map_point' style='position: absolute;user-select: none;'>"+
	          "<div class='dot' style='width:23px;height:23px;font-size:14px;" +
	          "color:red;left:21px;top:21px;line-height:23px;text-indent:8px;" +
	          "opacity:0.7;filter:alpha(opacity=70);'>"+this._num+"</div>"+
	          "<div class='pulse'></div>"+
	          "<div class='pulse1'></div>"+
	      "</div>");
 
  map.getPanes().labelPane.appendChild(div);
  return div;
}
StarOverlay.prototype.draw = function(){
  var pixel = this._map.pointToOverlayPixel(this._point);
  this._div.style.left = pixel.x -23 + "px";
  this._div.style.top  = pixel.y -72 + "px";
}