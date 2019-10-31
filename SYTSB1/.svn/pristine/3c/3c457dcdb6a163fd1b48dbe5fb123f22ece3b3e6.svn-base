	// 复杂的自定义覆盖物
    function ComplexCustomOverlay(point, text, mouseoverText){
      this._point = point;
      this._text = text;
      this._overText = mouseoverText;
    }
    ComplexCustomOverlay.prototype = new BMap.Overlay();
    ComplexCustomOverlay.prototype.initialize = function(map){
      this._map = map;
      var div = this._div = document.createElement("div");
      div.style.position = "absolute";
      div.style.zIndex = BMap.Overlay.getZIndex(this._point.lat);
      $(div).css({'width':'42px','height':'72px'})
      .append("<div class='livemap-mark anime-jump' style='position: absolute;left: -1px; top: 0px;'>"+
		          "<a class='livemap-hostname' href='#' target='_blank'><span>特种设备核准 (审批)</span></a>"+
		          "<a class='livemap-avatar' href='#' target='_blank'>"+
		              "<img class='livemap-mark-avatar' src='/app/gis/view/static_show_devices/images/2.gif'>"+
		          "</a>"+
		      "</div>"+
		      "<div class='map_point' style='position: absolute;left: -9px; top: 36px;user-select: none;'>"+
		          "<div class='dot'></div>"+
		          "<div class='pulse'></div>"+
		          "<div class='pulse1'></div>"+
		      "</div>");
      map.getPanes().labelPane.appendChild(div);
      return div;
    }
    ComplexCustomOverlay.prototype.draw = function(){
      var pixel = this._map.pointToOverlayPixel(this._point);
      this._div.style.left = pixel.x -23 + "px";
      this._div.style.top  = pixel.y -72 + "px";
    }
        
 
