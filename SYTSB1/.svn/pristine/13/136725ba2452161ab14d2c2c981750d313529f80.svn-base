/*
*全局变量
*/
var map=null;
var rightM=true;
var PUB_CENTER = new BMap.Point(104.101431,30.658739);
var PUB_ZOOM = 15;
var hasTools = false;
var PUB_DRAW_LAYERS = [];
var PUB_DRAW_LABEL = [];
var _pub_drawingManager=null;
var PUB_XZQH=null;//单个行政区划图层数组【Polygon】
/*
 * 绘制图层工具定义
 */
var overlaycomplete = function(e){
    PUB_DRAW_LAYERS.push(e.overlay);
    PUB_DRAW_LABEL.push(e.label);
    var labelNum = e.calculate;
    var reCharts = false;
    if (e.drawingMode=="circle") {
    	var r=e.overlay.getRadius();
    	labelNum = Math.round(labelNum/10000)/100;
    	r = Math.round(r/10)/100;
    	e.label.setContent("半径："+r+"公里/面积："+labelNum+"平方公里  关闭 X");
    	//e.label.setContent(" X 双击关闭");
    	reCharts = true;
    }
    else if (e.drawingMode=="polyline") {
    	labelNum = Math.round(labelNum/10)/100;
    	e.label.setContent(labelNum+"公里  关闭 X");
    	//e.label.setContent(" X 双击关闭");
    	reCharts = true;
    }
    else if (e.drawingMode=="marker"){}
    else {
    	labelNum = Math.round(labelNum/10000)/100;
    	e.label.setContent(labelNum+"平方公里  关闭 X");
    	//e.label.setContent(" X 双击关闭");
    	reCharts = true;
    }
    
    //e.label.setContent(e.label.getContent()+"X 双击关闭 X 双击关闭");
    /*e.label.setStyle({'z-index':888888888});*/
    
    //e.label.setContent(e.label.getContent()+"<img onclick='alert(2);_pub_clearCurLayer("+e+")' src='app/gis/img/iw_close.gif' style='cursor:pointer;margin:-5px 1px 0px 5px; z-index:99999999'/>");
    
   	//e.label.addEventListener("click",function(er){TMP_E=e});
   	
   	e.label.setZIndex(999);
    map.setViewport(e.overlay.getPath());//调整视野，同时触发修复移动地图才能双击关闭的bug
    e.label.addEventListener("click",function(){
    	_pub_clearCurLayer(e);
    });
    //map.disableDoubleClickZoom();//禁用双击放大
    if (reCharts)
    {
    	_ifs_afterDraw(PUB_DRAW_LAYERS);
    }
};
var styleOptions = {
    strokeColor:"#0ff",    //边线颜色。
    fillColor:"#0ff",      //填充颜色。当参数为空时，圆形将没有填充效果。
    strokeWeight: 1,       //边线的宽度，以像素为单位。
    strokeOpacity: 0.5,	   //边线透明度，取值范围0 - 1。
    fillOpacity: 0.5,      //填充的透明度，取值范围0 - 1。
    strokeStyle: 'solid' //边线的样式，solid或dashed。
}
var styleLineOptions = {
    strokeColor:"#0ff",    //边线颜色。
    fillColor:"#0ff",      //填充颜色。当参数为空时，圆形将没有填充效果。
    strokeWeight: 2,       //边线的宽度，以像素为单位。
    strokeOpacity: 0.8,	   //边线透明度，取值范围0 - 1。
    fillOpacity: 0.8,      //填充的透明度，取值范围0 - 1。
    strokeStyle: 'solid' //边线的样式，solid或dashed。
}

//初始化
$(function(){
	if(window.innerHeight<700) PUB_ZOOM=12;
	//_pub_setRightMHeight();//初始化面板收缩层高度控制
	try {
		parent.closeLoading();//关闭加载等待图层
	} catch (e) {
		// TODO: handle exception
	}
	
	_pub_initGLMap();//初始化地图
	//_pub_openDrawManager();//打开工具条
});

/*
 * 设置面板收缩层高度控制
 */
function _pub_setRightMHeight()
{
	var h = $(".side_right").height()-5;
	var wh = window.innerHeight;
	h = h>wh?wh:h;
	if(!rightM)
	{
		h=wh-15;
	}
	$(".shrinkRight").css("height",h);
	$(".shrinkRight a").css("height",h);
}

/*
 * 设置右侧控制面板
 */
function _pub_setShrinkRight()
{
	if(rightM)
	{
		$(".side_right").css("display","none");
		$(".shrinkRight").css("right","0");
		$(".shrinkRight a").css("background","url(app/gis/img/sbg.png) no-repeat -22px center");
		$(".pano_close").css("right","0px!important;");
		rightM=false;
		parent.rightWidth = 0;
	}
	else
	{
		$(".side_right").css("display","block");
		$(".shrinkRight").css("right","300px");
		$(".shrinkRight a").css("background","url(app/gis/img/sbg.png) no-repeat -33px center");
		$(".pano_close").css("right","310px!important;");
		rightM=true;
		parent.rightWidth = 300;
	}
	_pub_setRightMHeight();
	_pub_shrinkRightCtl(rightM);
}

/*
 * 地图缩略图控件随动
 */
function _pub_shrinkRightCtl(flag)
{
	map.removeControl(overCtl);
	if(!flag)
	{
		//缩略图控件
		overCtl = new BMap.OverviewMapControl({anchor: BMAP_ANCHOR_BOTTOM_RIGHT,isOpen:false});
		//overCtl.setOffset(new BMap.Size(300, 0));
		map.addControl(overCtl);
	}
	else{
		//缩略图控件
		overCtl = new BMap.OverviewMapControl({anchor: BMAP_ANCHOR_BOTTOM_RIGHT,isOpen:false});
		overCtl.setOffset(new BMap.Size(300, 0));
		map.addControl(overCtl);
	}
}

/*
 * 地图缩放控件随动
 */
function _pub_shrinkNaveCtl(flag)
{
	map.removeControl(naveCtl);
	if(!flag)
	{
		//缩放控件
		naveCtl = new BMap.NavigationControl({anchor: BMAP_ANCHOR_TOP_LEFT,type:BMAP_NAVIGATION_CONTROL_ZOOM,enableGeolocation:false});
		naveCtl.setOffset(new BMap.Size(20, 50));
		map.addControl(naveCtl);
	}
	else{
		//缩放控件
		naveCtl = new BMap.NavigationControl({anchor: BMAP_ANCHOR_TOP_LEFT,type:BMAP_NAVIGATION_CONTROL_ZOOM,enableGeolocation:false});
		naveCtl.setOffset(new BMap.Size(120, 50));
		map.addControl(naveCtl);
	}
}

/**
 * 设置绘图工具
 * @param {Object} s
 */
function _pub_setDrawManager(s)
{
	if(s)
	{
		_pub_openDrawManager();
	}
	else
	{
		_pub_closeDrawManager();
		_pub_closeRoute();
	}
}

/*
 * 打开绘图工具
 */
function _pub_openDrawManager()
{
	hasTools = true;
	//map.disableDoubleClickZoom();//禁用双击放大
//	_pub_drawingManager.close();
	$(".BMapLib_Drawing_panel").show();
}
/*
 * 关闭绘图工具
 */
function _pub_closeDrawManager()
{
	hasTools = false;
	//map.enableDoubleClickZoom();
	_pub_drawingManager.close();
	_pub_clearAllDrawLayer();
	$(".BMapLib_Drawing_panel").hide();
	_ifs_afterCloseDrawManager();
}
/*
 * 清除所有绘制图层
 */
function _pub_clearAllDrawLayer() {
	for(var i = 0; i < PUB_DRAW_LAYERS.length; i++){
        map.removeOverlay(PUB_DRAW_LAYERS[i]);
    }
    PUB_DRAW_LAYERS.length = 0;
    for(var i = 0; i < PUB_DRAW_LABEL.length; i++){
        map.removeOverlay(PUB_DRAW_LABEL[i]);
    }
    PUB_DRAW_LABEL.length = 0;
}
/*
 * 双击标签，清除当前绘制的图层
 */
function _pub_clearCurLayer(e)
{
	var newLayers = [];
	for(var i=0;i<PUB_DRAW_LAYERS.length;i++)
	{
		if (PUB_DRAW_LAYERS[i]!=e.overlay)
		{
			newLayers.push(PUB_DRAW_LAYERS[i]);
		}
	}
	PUB_DRAW_LAYERS = newLayers;//更新剩余图层数组
	map.removeOverlay(e.overlay);
	map.removeOverlay(e.label);
	_ifs_afterDraw(PUB_DRAW_LAYERS);
}

/*
 * 初始化工具
 */
function _pub_initTools()
{
	//实例化鼠标绘制工具
	_pub_drawingManager = new BMapLib.DrawingManager(map, {
	    isOpen: false, //是否开启绘制模式
	    enableDrawingTool: true, //是否显示工具栏
	    enableCalculate:true,//关闭面积计算
	    drawingToolOptions: {
	        anchor: BMAP_ANCHOR_TOP_RIGHT, //位置
	        offset: new BMap.Size(360, 10), //偏离值
	        scale:0.45,
	        drawingTypes : [
            BMAP_DRAWING_MARKER,
            BMAP_DRAWING_CIRCLE,
            BMAP_DRAWING_POLYLINE,
            BMAP_DRAWING_POLYGON,
            BMAP_DRAWING_RECTANGLE 
         ]
	    },
	    circleOptions: styleOptions, //圆的样式
	    polylineOptions: styleLineOptions, //线的样式
	    polygonOptions: styleOptions, //多边形的样式
	    rectangleOptions: styleOptions //矩形的样式
	});  
	 //添加鼠标绘制工具监听事件，用于获取绘制结果
	_pub_drawingManager.addEventListener('overlaycomplete', overlaycomplete);
	$(".BMapLib_Drawing_panel").hide();
	
	if (parent.P_TOOLS)
	{
		_pub_openDrawManager();
	}
}

/**
 * 设置闪点图层
 * @param {Object} s
 */
function _pub_setStarLayer(s)
{
	_ifs_setStarLayer(s);
}

/*
 * 设置地图样式（按选择框）
 */
function _pub_setDTStyle(val)
{
	map.setMapStyle({style:val});
}

/*
*初始化地图样式
*/
function _pub_initDTStyle()
{
	var ds=parent.P_MAPSTYLE;
	if(ds==null) ds = "normal";
	map.setMapStyle({style:ds});
}

/*
 * 初始化地图
 */
function _pub_initGLMap()
{
	map = new BMap.Map("mapDiv");          // 创建地图实例
	map.centerAndZoom(PUB_CENTER, PUB_ZOOM); 
	//缩放控件
	naveCtl = new BMap.NavigationControl({anchor: BMAP_ANCHOR_TOP_LEFT,type:BMAP_NAVIGATION_CONTROL_ZOOM,enableGeolocation:false});
	//naveCtl.setOffset(new BMap.Size(120, 50));
	map.addControl(naveCtl);
	map.enableScrollWheelZoom(true);//允许鼠标滚动缩放
	map.disableDoubleClickZoom();//禁用双击放大
	
	//比例尺控件
	var scaCtl = new BMap.ScaleControl({anchor: BMAP_ANCHOR_BOTTOM_LEFT});
	scaCtl.setOffset(new BMap.Size(110, 20));
	map.addControl(scaCtl);
	//缩略图控件
	overCtl = new BMap.OverviewMapControl({anchor: BMAP_ANCHOR_BOTTOM_RIGHT,isOpen:true});
	//overCtl.setOffset(new BMap.Size(300, 0));
	map.addControl(overCtl);
	//地图类型
	var typCtl = new BMap.MapTypeControl({anchor: BMAP_ANCHOR_TOP_RIGHT,mapTypes: [BMAP_NORMAL_MAP,BMAP_SATELLITE_MAP,BMAP_HYBRID_MAP]});
	typCtl.setOffset(new BMap.Size(120, 10));
	map.addControl(typCtl);
	//全景
	var panCtl = new BMap.PanoramaControl();
	panCtl.setAnchor(BMAP_ANCHOR_BOTTOM_RIGHT);
	panCtl.setOffset(new BMap.Size(160, 10));
	map.addControl(panCtl);//添加全景控件
	//初始化地图样式
	_pub_initDTStyle();
	//地图加载完成后的操作
	_ifs_afterMapInit();
}

/**
 * 启用路况信息
 */
function _pub_initTraffic()
{
	//路况
	var traCtl = new BMapLib.TrafficControl({showPanel: false});
	traCtl.setAnchor(BMAP_ANCHOR_TOP_RIGHT);
	traCtl.setOffset(new BMap.Size(305, 10));      
	map.addControl(traCtl);
}

/**
 * 改变视野
 * @param {Object} dm
 */
function _pub_changeView(dm)
{
	map.removeOverlay(PUB_XZQH);
	if (dm=="0")
	{
		_ifs_afterChangeView(null);
	}
	else
	{
		var pointArray = [];
		for(var i=0;i<PUB_QH.length;i++)
		{
			if(PUB_QH[i].qhdm==dm)
			{
				var PA=[];
				var pa=PUB_QH[i].data;
				for(var i=0;i<pa.length;i++)
				{
					PA.push(new BMap.Point(pa[i][0],pa[i][1]));	
				}
				pa=null;
				var ply = new BMap.Polygon(PA, {strokeWeight: 1, strokeColor: '#ff0000',fillColor:'#00ff00',strokeOpacity:0.6,fillOpacity:0.3});  //创建多边形
				PA.length=0;
				PUB_XZQH = ply;
				pointArray = pointArray.concat(ply.getPath());
				map.addOverlay(ply);   //增加多边形
				map.setViewport(pointArray);    //调整视野  
			}
		}
		pointArray.length=0;
		_ifs_afterChangeView(PUB_XZQH);
	}
}

/*
 * 清除路径规划图层
 */
function _pub_closeRoute()
{
	var layers = map.getOverlays();
	try{
		for (var i=0;i<layers.length;i++) {
			var otype = layers[i].toString();
			//alert(otype);
			if (otype.indexOf("Marker")>0) {
				var mTitle = layers[i].getTitle();
				if (mTitle.length>0) {
					map.removeOverlay(layers[i]);
				}
				/*var imgUrl = layers[i].getIcon().imageUrl;
				alert(imgUrl);
				if ((imgUrl.indexOf("spotmkrs")>0) || (imgUrl.indexOf("dest_markers")>0) || (imgUrl.indexOf("trans_icons"))) {
					alert(layers[i].toString());
					
					map.removeOverlay(layers[i]);
				}*/
				//spotmkrs.png,dest_markers.png,trans_icons.png
			}
			if (otype.indexOf("Polyline")>0){
 				map.removeOverlay(layers[i]);
			}
			if (otype.indexOf("Label")>0){
 				map.removeOverlay(layers[i]);
			}
		}
	}
	catch(e)
	{
		//alert(e);
	}
}
