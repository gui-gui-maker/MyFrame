<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
    <title>经纬度设置区划地址 </title>
    <%@ include file="/k/kui-base-form.jsp" %>
     <script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=qRrUl0Vy6HMQb7erHbxGU90F"></script>
    <script type="text/javascript">
    	var map;
    	var localSearch;
        $(function () {
        	$("#formObj").initForm({
        		afterParse:function(){
        			map = new BMap.Map("container");
        	        map.centerAndZoom(api.data.fullName, 12);
        	        map.enableScrollWheelZoom();    //启用滚轮放大缩小，默认禁用
        	        map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用
        	        map.addControl(new BMap.NavigationControl());  //添加默认缩放平移控件
        	        map.addControl(new BMap.OverviewMapControl()); //添加默认缩略地图控件
        	        map.addControl(new BMap.OverviewMapControl({ isOpen: true, anchor: BMAP_ANCHOR_BOTTOM_RIGHT }));   //右下角，打开
        	        localSearch = new BMap.LocalSearch(map);
        	        localSearch.enableAutoViewport(); //允许自动调节窗体大小
        	        searchByStationName(api.data.fullName);
        		}
        	})
        });
        function searchByStationName(fullname) {
	        map.clearOverlays();//清空原来的标注
	        localSearch.setSearchCompleteCallback(function (searchResult) {
	            var poi = searchResult.getPoi(0);
	            var geocode = "{\"lng\":\""+poi.point.lng +"\"," +"\"lat\":\""+poi.point.lat+"\"}";
	           	$("#geocode").val(geocode);
	            map.centerAndZoom(poi.point, 13);
	            var marker = new BMap.Marker(new BMap.Point(poi.point.lng, poi.point.lat));  // 创建标注，为要查询的地方对应的经纬度
	            map.addOverlay(marker);
	            var content = fullname + "<br/><br/>经度：" + poi.point.lng + "<br/>纬度：" + poi.point.lat;
	            var infoWindow = new BMap.InfoWindow("<p style='font-size:14px;'>" + content + "</p>");
	            marker.addEventListener("click", function () { this.openInfoWindow(infoWindow); });
	            marker.openInfoWindow(infoWindow);
	            //marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
	        });
	        localSearch.search(fullname);
    	} 
    </script>
</head>
<body>
<form name="formObj" id="formObj" method="post"
      action="rbac/area/setGeoCode.do">
      <table cellpadding="3" cellspacing="0" class="l-detail-table">
      	<tr>
      		<td >
      			<input type="hidden" name="id" value="${param.id}">
      			<input type="hidden" name="geocode" id="geocode">
      			<div id="container" 
		            style="position: absolute;
		                width: 100%; 
		                height: 93%; 
		                border: 1px solid gray;
		                overflow:hidden;">
		        </div>
      		</td>
      	</tr>
      </table>
</form>
</body>
</html>
