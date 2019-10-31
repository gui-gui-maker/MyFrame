// 复杂的自定义覆盖物
function POverlay(point,img_path,place) {
	this._point = point;
	this._imgPath = img_path;
	this._place = place;
}
POverlay.prototype = new BMap.Overlay();
POverlay.prototype.initialize = function(map) {
	this._map = map;
	var div = this._div = document.createElement("div");
	div.style.position = "absolute";
	div.style.zIndex = BMap.Overlay.getZIndex(this._point.lat);
	$(div)
			.css({
				'width' : '42px',
				'height' : '72px'
			})
			.attr({
				"id" : this._index
			})
			.append(
					"<div class='livemap-mark anime-jump' style='position: absolute;left: -1px; top: 0px;'>"
							+ "<a class='livemap-hostname' href='javascript:void(0);' style='display:none;'>"
							+ "<span>"
							+ "<div class='wz'></div>"
							+ "<div class='btbg' style='height:40px;'></div>"
							+ "</span>"
							+ "</a>"
							+ "<a class='livemap-avatar' href='javascript:void(0);'>"
							+ "<img class='livemap-mark-avatar' src='"
							+ this._imgPath
							+ "'>"
							+ "</a>"
							+ "</div>"
							+ "<div class='map_point' style='position: absolute;left: -9px; top: 36px;user-select: none;'>"
							+ "<div class='dot'></div>"
							+ "<div class='pulse'></div>"
							+ "<div class='pulse1'></div>" + "</div>");
	//气球样式
	if (this._color) {
		$(div).find("div.dot").css({
			"background-color" : "#ffd500"
		});
		$(div).find("div.pulse").css({
			"border-color" : "#ffd500",
			"box-shadow" : "1px 1px 10px #ffd500"
		});
		$(div).find("div.pulse1").css({
			"border-color" : "#ffd500",
			"box-shadow" : "1px 1px 10px #ffd500"
		});
		$(div).find("a.livemap-avatar").css({
			"background-image" : "url(app/gis/scjy/v2/images/mark_ffd500.png)"
		});
	}
	map.getPanes().labelPane.appendChild(div);
	$(div).find("div.livemap-mark").find('.livemap-hostname span div.wz').html(this._place);
	$(div).find("div.livemap-mark").hover(function() {
		$(this).find('.livemap-hostname').css({
			"display" : "block"
		});
	}, function() {
		$(this).find('.livemap-hostname').css({
			"display" : "none"
		});
	})
	return div;
}
POverlay.prototype.draw = function() {
	var pixel = this._map.pointToOverlayPixel(this._point);
	this._div.style.left = pixel.x - 23 + "px";
	this._div.style.top = pixel.y - 72 + "px";
}

