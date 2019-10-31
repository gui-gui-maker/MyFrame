/*
*全局变量
*/
var map=null;
var PUB_QX_ZOOM = 10;
var PUB_XZQH_QX=[];//分区县行政区划图层数组【Polygon】
var PUB_SHOW_QX=false;
var PUB_XZQH=[];//单个行政区划图层数组【Polygon】
var PUB_SHOW_XZQH=false;
var PUB_SB=[];//所有设备标记【Marker】
var PUB_SB_DATA=[];//所有设备数组【Array】
var PUB_CHART_DATA=[];//用于统计图的设备数组【Array】
var PUB_ZYSBDW_DATA=[];//用于统计在用设备单位的数组【Array】
var searchInfoWindow=null;
var infoWindow=null;
var PUB_DRAW_LAYERS = [];
var PUB_DRAW_LABEL = [];
var drawingManager=null;
var hasTools = false;
var showStar = true;
var PUB_IMGS=[];
var PUB_CENTER = new BMap.Point(104.059142,30.771587);
var PUB_QHMC_LABEL = [];
var PUB_QHMC_SHOW = true;
var markerClusterer;
var rightM=true;
var naveCtl=null;
var overCtl=null;
var TMP_E=null;
var isChangeColor = true;
var selectDiv = '';
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
    	e.label.setContent("半径："+r+"公里/面积："+labelNum+"平方公里");
    	reCharts = true;
    }
    else if (e.drawingMode=="polyline") {
    	labelNum = Math.round(labelNum/10)/100;
    	e.label.setContent(labelNum+"公里");
    }
    else if (e.drawingMode=="marker"){}
    else {
    	labelNum = Math.round(labelNum/10000)/100;
    	e.label.setContent(labelNum+"平方公里");
    	reCharts = true;
    }
    //e.label.setZIndex(888888888);
    //e.label.setContent(e.label.getContent()+"<a href='javascript:void(0)' onclick='clearCurLayer()' style='font-size:16px;z-index:999999999;cursor:pointer;margin:-5px 2px 0px 5px;'>X</a>");
    e.label.setStyle({'z-index':888888888});
    
    //e.label.setContent(e.label.getContent()+"<img onclick='alert(2);clearCurLayer("+e+")' src='app/gis/img/iw_close.gif' style='cursor:pointer;margin:-5px 1px 0px 5px; z-index:99999999'/>");
    
   	//e.label.addEventListener("click",function(er){TMP_E=e});
    e.label.addEventListener("dblclick",function(){clearCurLayer(e);});
    //map.disableDoubleClickZoom();//禁用双击放大
    if (reCharts)
    {
    	//计算所有绘制图形范围内的数据并更新图表
	    var point,chartData=[];
		for (var i=0;i<PUB_DRAW_LAYERS.length;i++)
		{
			for(var j=0;j<PUB_SB_DATA.length;j++)
			{
				point = new BMap.Point(PUB_SB_DATA[j].JD,PUB_SB_DATA[j].WD);
				if (BMapLib.GeoUtils.isPointInPolygon(point, PUB_DRAW_LAYERS[i]))
					chartData.push(PUB_SB_DATA[j]);
			}
		}
		refreshCharts(chartData);
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
	openSCMap();//打开四川省echart-map
	setRightMHeight();//初始化面板收缩层高度控制
	if(window.innerHeight<700) PUB_QX_ZOOM=9;
	initGLMap();//初始化地图
	parent.closeLoading();//关闭加载等待图层
});

/*
 * 关闭四川省echart-map
 */
function closeSCMap()
{
	$("#beginDiv00").css("display","none");
	$("#beginDiv").css("display","none");
}

/*
 * 打开四川省echart-map
 */
function openSCMap()
{
	$("#beginDiv").css("width",window.innerWidth-200);
	$("#beginDiv00").css("display","block");
	$("#beginDiv").css("display","block");
	$.getJSON('app/gis/js/emap/map_chengdu.json', function (data) {
        echarts.registerMap('chengdu', data);
        var chart = echarts.init(document.getElementById('beginDiv'));
        chart.setOption({
            series: [{
	            type: 'map',
	            map: 'chengdu',
	            //name:'成都',
	            roam:true,
	            silent:false,
	            aspectScale:0.9,
	            zoom:1.1,
	            data:dataSC,
	            itemStyle:{
	            	emphasis:{
	            		areaColor:{
	            			type: 'radial',
						    x: 0.5,
						    y: 0.5,
						    r: 0.6,
						    colorStops: [{
						        offset: 0, color: '#ffffff' // 0% 处的颜色
						    }, {
						        offset: 1, color: '#0690fa' // 100% 处的颜色
						    }],
						    globalCoord: false // 缺省为 false
	            		}
	            	}
	            }
	        }]
    	});
    	chart.on("click",function(params){
			if(params.name=="青白江区")
			{
				closeSCMap();//关闭成都市echart-map
				//openAllQXMap();//打开所有区县图层
				//openQhmcLabel();//打开所有区县名称标签
			}
		});
    });
}

/*
 * 初始化地图
 */
function initGLMap()
{
	map = new BMap.Map("container");          // 创建地图实例
	map.centerAndZoom(PUB_CENTER, PUB_QX_ZOOM); 
	
	//缩放控件
	naveCtl = new BMap.NavigationControl({anchor: BMAP_ANCHOR_TOP_LEFT,type:BMAP_NAVIGATION_CONTROL_ZOOM,enableGeolocation:false});
	naveCtl.setOffset(new BMap.Size(120, 50));
	map.addControl(naveCtl);
	map.enableScrollWheelZoom(true);//允许鼠标滚动缩放
	map.disableDoubleClickZoom();//禁用双击放大
	
	//比例尺控件
	var scaCtl = new BMap.ScaleControl({anchor: BMAP_ANCHOR_BOTTOM_LEFT});
	scaCtl.setOffset(new BMap.Size(110, 20));
	map.addControl(scaCtl);
	//缩略图控件
	overCtl = new BMap.OverviewMapControl({anchor: BMAP_ANCHOR_BOTTOM_RIGHT,isOpen:false});
	overCtl.setOffset(new BMap.Size(300, 0));
	map.addControl(overCtl);
	//路况
	var traCtl = new BMapLib.TrafficControl({showPanel: false});
	traCtl.setAnchor(BMAP_ANCHOR_TOP_RIGHT);
	traCtl.setOffset(new BMap.Size(305, 10));      
	map.addControl(traCtl);
	//地图类型
	var typCtl = new BMap.MapTypeControl({anchor: BMAP_ANCHOR_TOP_RIGHT,mapTypes: [BMAP_NORMAL_MAP,BMAP_SATELLITE_MAP,BMAP_HYBRID_MAP]});
	typCtl.setOffset(new BMap.Size(385, 10));
	map.addControl(typCtl);
	//全景
	var panCtl = new BMap.PanoramaControl();
	panCtl.setAnchor(BMAP_ANCHOR_BOTTOM_RIGHT);
	panCtl.setOffset(new BMap.Size(460, 10));
	map.addControl(panCtl);//添加全景控件
	
	//事件
	map.addEventListener("zoomend", function(e){
		setLayers(e.target.getZoom());//控制省、市轮廓图层显示状态
	});
	map.addEventListener("moveend", function(e){
		setDrawLabelZindex();//设置绘制图层Label的zindex，使其能补双击关闭
		if(!showStar && e.target.getZoom()>PUB_QX_ZOOM)
		{
			setBoundSB();
		}
	});
	
	//地图加载完成后的操作
	setConfigPanelState("cd");//右侧设置面板控制
	initDTStyle();//初始化地图样式
	initTools();//初始化工具
	//openJrq()//打开禁燃区图层
	setSBData();//设置设备数据
	//setZYSBDWData();//设置统计在用设备单位情况的数据
}

/*
 * 设置绘制图层Label的zindex，使其能补双击关闭
 */
function setDrawLabelZindex()
{
	for(var i=0;i<PUB_DRAW_LABEL.length;i++)
	{
		PUB_DRAW_LABEL[i].setStyle({'z-index':888888888});
	}
}

/*
 * 控制省、市轮廓图层显示状态
 */
function setLayers(z)
{
	if(z<PUB_QX_ZOOM)
	{
		closeSBLayer();//关闭设备图层
		closeSingleQH();//清除单个行政区划图层
		closeRoute();//关闭导航路径
		closeQhmcLabel();//关闭所有区县名称标签
		closeAllQXMap();//关闭所有区县行政区划图层
		closeInfoWindows();//关闭所有InfoWindow
		$("#switch-tools").bootstrapSwitch("state",false);//清除所有绘制图层
		openSCMap();//打开四川省echart-map
	}
	else if (z==PUB_QX_ZOOM)
	{
		syncDTStyle();//同步地图样式
		setConfigPanelState("cd");//右侧设置面板控制
		closeSBLayer();//关闭设备图层
		closeSingleQH();//清除单个行政区划图层
		closeRoute();//关闭导航路径
		closeInfoWindows();//关闭所有InfoWindow
		$("#switch-tools").bootstrapSwitch("state",false);//清除所有绘制图层
		openAllQXMap();//打开所有区县图层
		openQhmcLabel();//打开所有区县名称标签
	}
	else
	{
		syncDTStyle();//同步地图样式
		setConfigPanelState("");//右侧设置面板控制
		closeSCMap();//关闭四川省echart-map
		closeQhmcLabel();//关闭所有区县名称标签
		closeAllQXMap();//关闭所有区县行政区划图层
		openSBLayer();//打开设备图层
		//如果未显示单个区划，同步清空下拉选项框内容
		if(!PUB_SHOW_XZQH)
		{
			$("#xzqh").val("");
		}
	}
}

/*
 * 右侧设置面板控制
 */
function setConfigPanelState(val)
{
	if (val=="cd")
	{
		$("#cdSet").css("display","block");
		$("#qxSet").css("display","none");
		//$(".column1").css("height","142px");
		//$(".column1 .m-a-border").css("height","138px");
		$(".column2").css("height","35px");
		$(".column3").css("height","35px");
		//a1h="142px";
		a2h="35px";
		a3h="35px";
		a1=true;
		a2=false;
		a3=false;
		setRightMHeight();
		//cleanSearch();
	}
	else
	{
		$("#cdSet").css("display","none");
		$("#qxSet").css("display","block");
		//$(".column1").css("height","167px");
		$(".column2 .m-a-border").css("height","311px");
		//a1h="167px";
		a2h="315px";
		a3h="178px";
		a1=true;
		a2=false;
		a3=false;
		setRightMHeight();
	}
}

/*
 * 清空筛选条件并执行空查询
 */
function cleanSearch()
{
	$("#chart2 input").val("");
	$("#chart2 select").val("");
	a3=true;
	openResultPanel();
	$("#searchNum").html("");
	$("#chart3").html("");
	setSBData();//重新设置设备数据
	openSBLayer();//重置显示设备图层
}

/*
 * 执行搜索
 */
function doSearch()
{
	var url=encodeURI("app/dt/tsGlSbwz/searchSbList.do?m="+Math.random());
	$.ajax({
		url: url,
		type: "POST",
		async:true,
		datatype: "json",
		data: {
			jcqk:$("#jcqk").val(),
			sbzt:$("#sbzt").val(),
			qymc:$("#qymc").val(),
			rllx:$("#rllx").val(),
			xzqh:$("#ssqh").val(),
			gllx:$("#gllx").val(),
			edclQ:$("#edclQ").val(),
			edclZ:$("#edclZ").val(),
			syrqQN:$("#syrqQN").val(),
			syrqQY:$("#syrqQY").val(),
			syrqQR:$("#syrqQR").val(),
			sryqZN:$("#sryqZN").val(),
			syrqZY:$("#syrqZY").val(),
			syrqZR:$("#syrqZR").val()
		},
		success: function (res) {
			if (res.success) {
				//searchNum,chart3
				PUB_SB_DATA = res.data;//将搜索结果数据覆盖到全局设备数据数组
				setShowData();//设置显示数据
				$("#searchNum").html("共计："+PUB_SB_DATA.length+"条");
				var html = "<ul>";
				refreshCharts(PUB_SB_DATA);//更新图表
				openSBLayer();
				for(var i=0;i<PUB_SB_DATA.length;i++)
				{
					html += "<li><a href='javascript:void(0)' onClick=clickSearchSB(\'"+PUB_SB_DATA[i].ZCDM+"\',\'"+PUB_SB_DATA[i].JD+"\',\'"+PUB_SB_DATA[i].WD+"\') title='"+PUB_SB_DATA[i].JGMC+"'>"+PUB_SB_DATA[i].JGMC+"</a></li>";
				}
				html += "</ul>";
				$("#chart3").html(html);
				//如果查询结果数据不为空且结果面板未打开，打开搜索结果面板
				if(PUB_SB_DATA.length>0 && a3==false){
					openResultPanel();
				}
			}
		},
		error: function (res) {
			alert("数据查询失败...");
			$.ligerDialog.warn("数据查询失败...","提示");
		}
	});
}

/*
 * 关闭设备图层
 */
function closeSBLayer()
{
	$("#starLay").remove();
	cleanSBMarkers();
}

/*
 * 打开设备图层
 */
function openSBLayer()
{
	cleanSBMarkers();//清除所有设备标记
	if(showStar){
		$("#starLay").remove();
		openStarLayer();//设置闪点图
	}
	else{
		$("#starLay").remove();
		setBoundSB();
	}
}

/*
 * 设置可视区域内的设备标记
 */
function setBoundSB()
{
	cleanSBMarkers(); //清除所有设备标记
	var point;
	for(var i=0;i<PUB_SB_DATA.length;i++)
	{
		point = new BMap.Point(PUB_SB_DATA[i].JD,PUB_SB_DATA[i].WD);
		if(map.getBounds().containsPoint(point))
		{
			addMarker(PUB_SB_DATA[i]);
		}
	}
	setClsMarker(true);
	point=null;
}

/*
*清除所有设备标记
*/
function cleanSBMarkers()
{
	for(var i=0;i<PUB_SB.length;i++)
	{
		map.removeOverlay(PUB_SB[i]);
	}
	PUB_SB.length=0;
	setClsMarker(false);
}


/*
 * 设置点聚合显示状态
 */
function setClsMarker(flag)
{
	if(flag)
	{
		if(!showStar)
		{
			try{
				markerClusterer.clearMarkers();
			}
			catch(e){}
			markerClusterer = new BMapLib.MarkerClusterer(map, {markers:PUB_SB,maxZoom:13});
		}
	}
	else
	{
		try{
			markerClusterer.clearMarkers();
		}
		catch(e){}
	}
}

/*
 * 添加设备标记
 */
function addMarker(json)
{
	var point = new BMap.Point(json.JD,json.WD);
	var myIcon = new BMap.Icon("app/gis/img/"+json.RL+"-"+json.YJBZ+".png", new BMap.Size(28,40));
	myIcon.setAnchor(new BMap.Size(14,40));
	var marker = new BMap.Marker(point,{icon:myIcon});  // 创建标注
	//var marker = new BMap.Marker(point);  // 创建标注
	//map.addOverlay(marker);       // 将标注添加到地图中
	PUB_SB = PUB_SB.concat(marker); 
	marker.addEventListener("click", function(e){
		setInfoWindow(json.ZCDM,point);
	});
}

/*
 * 设置设备状态中文
 */
function setSBZT(zt)
{
	var st = "未知";
	var op = $("#sbzt option");
	//var op = [["0","未注册"],["1","未注册"],["2","在用"],["4","拆除"],["5","停用"],["6","报废"]];
	for (var i=0;i<op.length;i++) {
		if (zt==op[i].value)
		{
			st = op[i].text;
		}
	}
	return st;
}
function setJCZT(zt)
{
	var st = "";
	if(zt=="Y")
		st = "<span style='color:#ff0000;'>超期</span>";
	else if(zt=="W")
		st = "<span style='color:#ffc000;'>预警</span>";
	else
		st = "<span style='color:#4db116;'>正常</span>";
	return st;
}

function setInfoWindow(zcdm,point)
{
	var content = "";
	var url=encodeURI("app/dt/tsGlSbwz/getSbInfo.do?m="+Math.random());
	$.ajax({
		url: url,
		type: "POST",
		async:false,
		datatype: "json",
		data: {
			zcdm:zcdm
		},
		success: function (res) {
			if (res.success && res.data.length>0) {
				content = '<div style="margin:0;line-height:20px;padding:2px;font-size:14px;line-height:24px;">' +
				'<div id="infoW_imgs" style="float:right;zoom:1;overflow:hidden;width:100px;height:100px;margin-left:2px;cursor:pointer;">' +
                //'<img src="app/gis/img/timg.jpg" alt="" style="width:100px;height:100px;margin-bottom:2px;"/>' +
                //'<img onClick="openImgViewer(this)" src="app/gis/img/timg.jpg" alt="" style="width:100px;height:100px;margin-bottom:2px;"/>' +
                '<img onClick="openImgViewer(this)" src="app/gis/img/timg.jpg" alt="" style="width:100px;height:100px;"/>' +
                '</div>' +
                //'注册代码：'+json.ZCDM+'<br/>' +
                '登记证号：'+res.data[0].SYDJZ+'<br/>' +
                '检测状态：'+setJCZT(res.data[0].YJBZ)+'<br/>' +
                '设备状态：'+setSBZT(res.data[0].SBZT)+'<br/>' +
                '淘汰计划：-<br/>' +
                '锅炉品种：'+res.data[0].GLLX+'<br/>' +
                '燃料类型：'+res.data[0].RLLX+'<br/>' +
                '额定出力：'+res.data[0].EDCL+' 吨/时<br/>' +
                '额定压力：'+res.data[0].EDYL+' MPa<br/>' +
                //'出口温度：'+res.data[0].CKWD+' &deg;C<br/>' +
                '制造日期：'+res.data[0].ZZRQ+'<br/>' +
                //'制造单位：'+res.data[0].ZZDW+'<br/>' +
                '安装日期：'+res.data[0].AZWCRQ+'<br/>' +
                //'安装单位：'+res.data[0].AZDW+'<br/>' +
                //'使用日期：'+res.data[0].SYRQ+'<br/>' +
                //'所属区划：'+res.data[0].QHMC+'('+res.data[0].QHDM+')<br/>' +
                '所属区划：'+res.data[0].QHMC+'<br/>' +
                //'锅炉地址：'+res.data[0].GLDZ+'<br/>' +
                '安全人员：'+res.data[0].AQGLY+' / '+res.data[0].AQGLYDH+'<br/>' +
                //'主体信息：'+res.data[0].JGDM+' / '+res.data[0].JGMC+'<br/>' +
                '使用单位：'+res.data[0].JGDM+'<br/>'+res.data[0].JGMC+'<br/>' +
                //'主体地址：'+res.data[0].QYDZ+'<br/>' +
                '<a style="font-size:16px; line-height:25px;" href="javascript:void(0)" onclick=gotoDetail(\''+res.data[0].SBID+'\')>查看详细信息>></a><br/>' +
              '</div>';
			}
		},
		error: function (res) {
			content = '<div style="margin:0;line-height:20px;padding:2px;font-size:14px;">' +
	        	'注册代码：'+json.ZCDM+'<br/>锅炉信息加载失败...' +
	      		'</div>';
		}
	});
	
	if (hasTools)
	{
		//创建检索信息窗口对象
		if (showStar){
			searchInfoWindow = new BMapLib.SearchInfoWindow(map, content, {
				title  : "锅炉信息",      //标题
				width  : 320,             //宽度
				//height : 105,              //高度
				panel  : "spanel",         //检索结果面板
				enableAutoPan : true,     //自动平移
				enableSendToPhone:false,
				offset:new BMap.Size(0,10),
				searchTypes   :[
					//BMAPLIB_TAB_SEARCH,   //周边检索
					BMAPLIB_TAB_TO_HERE,  //到这里去
					BMAPLIB_TAB_FROM_HERE //从这里出发
				]
			});
			searchInfoWindow.open(point);
		}
		else{
			searchInfoWindow = new BMapLib.SearchInfoWindow(map, content, {
				title  : "锅炉信息",      //标题
				width  : 320,             //宽度
				//height : 105,              //高度
				panel  : "spanel",         //检索结果面板
				enableAutoPan : true,     //自动平移
				enableSendToPhone:false,
				offset:new BMap.Size(0,40),
				searchTypes   :[
					//BMAPLIB_TAB_SEARCH,   //周边检索
					BMAPLIB_TAB_TO_HERE,  //到这里去
					BMAPLIB_TAB_FROM_HERE //从这里出发
				]
			});
			searchInfoWindow.open(point);
		}
		
	}
	else
	{
		if(searchInfoWindow!=null) searchInfoWindow.close();
		var opts;
		if (showStar){
			opts = {
				offset:new BMap.Size(0,-10),
				width : 320,     // 信息窗口宽度
				//height: 80,     // 信息窗口高度
				title : "锅炉信息"  // 信息窗口标题
			};
		}
		else{
			opts = {
				offset:new BMap.Size(0,-40),
				width : 320,     // 信息窗口宽度
				//height: 80,     // 信息窗口高度
				title : "锅炉信息"  // 信息窗口标题
			};
		}
		infoWindow = new BMap.InfoWindow(content,opts);  // 创建信息窗口对象
		infoWindow.disableCloseOnClick();//关闭点击地图时关闭窗口，使两类窗口关闭操作统一
		map.openInfoWindow(infoWindow,point);
	}
}

/*
 * 关闭所有InfoWindow
 */
function closeInfoWindows()
{
	if(searchInfoWindow!=null) searchInfoWindow.close();
	map.closeInfoWindow();
}

/*
 * 清除路径规划图层
 */
function closeRoute()
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

/*
 * 初始化工具
 */
function initTools()
{
	//实例化鼠标绘制工具
	drawingManager = new BMapLib.DrawingManager(map, {
	    isOpen: false, //是否开启绘制模式
	    enableDrawingTool: true, //是否显示工具栏
	    enableCalculate:true,
	    drawingToolOptions: {
	        anchor: BMAP_ANCHOR_TOP_RIGHT, //位置
	        offset: new BMap.Size(205, 50), //偏离值
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
	drawingManager.addEventListener('overlaycomplete', overlaycomplete);
	$(".BMapLib_Drawing_panel").hide();
}

/*
*初始化地图样式
*/
function initDTStyle()
{
	var ds=getCookie("cdzj_Dt_Style");
	if(ds==null) ds = "normal";
	map.setMapStyle({style:ds});
	$("#dtys").val(ds);
	$("#dtys0").val(ds);
}

/*
 * 同步二级、三级页面两个地图样式制件的值
 */
function syncDTStyle()
{
	var ds=getCookie("cdzj_Dt_Style");
	if(ds==null) ds = "normal";
	//map.setMapStyle({style:ds});
	$("#dtys").val(ds);
	$("#dtys0").val(ds);
}

/*
 * 设置地图样式（按选择框）
 */
function setDTStyle(val)
{
	map.setMapStyle({style:val});
	var exp = new Date();  
    exp.setTime(exp.getTime() + 30 * 24 * 60 * 60 * 1000);//过期时间 30天
	document.cookie="cdzj_Dt_Style="+escape(val)+";expires="+exp.toGMTString();
	syncDTStyle();
}
/*
 * 取地图样式cookie
 */
function getCookie(name)
{
	var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
	if(arr=document.cookie.match(reg))
		return unescape(arr[2]);
	else
		return null;
}

/*
 * 打开绘图工具
 */
function openDrawManager()
{
	hasTools = true;
	//map.disableDoubleClickZoom();//禁用双击放大
	drawingManager.close();
	$(".BMapLib_Drawing_panel").show();
}
/*
 * 关闭绘图工具
 */
function closeDrawManager()
{
	hasTools = false;
	//map.enableDoubleClickZoom();
	drawingManager.close();
	clearAllDrawLayer();
	$(".BMapLib_Drawing_panel").hide();
	//将图表数据重置为全局锅炉数据,并更新图表
	refreshCharts(PUB_SB_DATA);
}
/*
 * 清除所有绘制图层
 */
function clearAllDrawLayer() {
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
function clearCurLayer(e)
{
	//var e = TMP_E;
	//alert('in...');
	var newLayers = [];
	for(var i=0;i<PUB_DRAW_LAYERS.length;i++)
	{
		if (PUB_DRAW_LAYERS[i]!=e.overlay)
		{
			newLayers.push(PUB_DRAW_LAYERS[i]);
		}
	}
	PUB_DRAW_LAYERS = newLayers;//更新剩余图层数组
	//计算所有绘制图形范围内的数据并更新图表
    var point,chartData=[];
	for (var i=0;i<PUB_DRAW_LAYERS.length;i++)
	{
		for(var j=0;j<PUB_SB_DATA.length;j++)
		{
			point = new BMap.Point(PUB_SB_DATA[j].JD,PUB_SB_DATA[j].WD);
			if (BMapLib.GeoUtils.isPointInPolygon(point, PUB_DRAW_LAYERS[i]))
				chartData.push(PUB_SB_DATA[j]);
		}
	}
	if(chartData.length>0)
	{
		refreshCharts(chartData);
	}
	else
	{
		refreshCharts(PUB_SB_DATA);//将最新数据设置给图表，更新图表
	}
	map.removeOverlay(e.overlay);
	map.removeOverlay(e.label);
}

/*
 * 读取锅炉数据
 */
function setSBData()
{
	var url=encodeURI("app/dt/tsGlSbwz/getSbList.do?m="+Math.random());
	$.ajax({
		url: url,
		type: "POST",
		async:false,
		datatype: "json",
		success: function (res) {
			if (res.success) {
				PUB_SB_DATA = res.data;
			}
		},
		error: function (res) {
			PUB_SB_DATA.length = 0;
		}
	});
	refreshCharts(PUB_SB_DATA);//将最新数据设置给图表，更新图表
}

/*
 * 读取在用锅炉数据（以单位去重，按区划统计单位情况），后台去重时使用，已改成前端过滤
 */
function setZYSBDWData()
{
	var url=encodeURI("app/dt/tsGlSbwz/getZYSbDWList.do?m="+Math.random());
	$.ajax({
		url: url,
		type: "POST",
		async:false,
		datatype: "json",
		success: function (res) {
			if (res.success) {
				PUB_ZYSBDW_DATA = res.data;
			}
		},
		error: function (res) {
			PUB_ZYSBDW_DATA.length = 0;
		}
	});
	refreshZYSBDWCharts(PUB_ZYSBDW_DATA);//将最新数据设置给图表，更新图表
}

/*
 * 设置数据显示范围
 * all:全部
 * inner:仅显示禁燃区内设备
 * outer:仅显示禁燃区外设备
 */
function setShowData()
{
	var jr = $("#sjxsfw").val();
	var point,tempArr=[];
	if (jr=="in")
	{
		for (var i=0;i<PUB_JRQY.length;i++)
		{
			for(var j=0;j<PUB_SB_DATA.length;j++)
			{
				point = new BMap.Point(PUB_SB_DATA[j].JD,PUB_SB_DATA[j].WD);
				if (BMapLib.GeoUtils.isPointInPolygon(point, PUB_JRQY[i]))
					tempArr.push(PUB_SB_DATA[j]);
			}
		}
		PUB_SB_DATA = tempArr;
	}
	else if (jr=="out")
	{
		var isOuter = true,temp=[];
		for(var j=0;j<PUB_SB_DATA.length;j++)
		{
			isOuter = true;
			for (var i=0;i<PUB_JRQY.length;i++)
			{
				point = new BMap.Point(PUB_SB_DATA[j].JD,PUB_SB_DATA[j].WD);
				if (BMapLib.GeoUtils.isPointInPolygon(point, PUB_JRQY[i]))
					isOuter = false;
				else
					temp = PUB_SB_DATA[j];
					
			}
			if(isOuter)
			{
				tempArr.push(temp);
			}
		}
		PUB_SB_DATA = tempArr;
	}
}

/*
 * 关闭所有区县行政区划图层
 */
function closeAllQXMap()
{
	for(var i=0;i<PUB_XZQH_QX.length;i++)
	{
		map.removeOverlay(PUB_XZQH_QX[i]);
	}
	//PUB_XZQH_QX.length=0;
	PUB_SHOW_QX=false;
}

/*
 * 打开所有区县图层
 */
function openAllQXMap()
{
	refreshCharts(PUB_SB_DATA);//更新图表
	if(!PUB_SHOW_QX)
	{
		if(PUB_XZQH_QX.length>=24)//21个区县+3个高新区
		{
			for(var i=0;i<PUB_XZQH_QX.length;i++)
			{
				map.addOverlay(PUB_XZQH_QX[i]);
			}
			PUB_SHOW_QX=true;
			map.setZoom(PUB_QX_ZOOM);
			map.setCenter(PUB_CENTER);
		}
		else
		{
			setQH("成都市");
		}
	} 
}

/*
 * 关闭所有区县名称标签
 */
function closeQhmcLabel()
{
	for (var i=0;i<PUB_QHMC_LABEL.length;i++) {
		map.removeOverlay(PUB_QHMC_LABEL[i]);
	}
	//PUB_QHMC_LABEL.length=0;
	//PUB_QHMC_SHOW=false;
}

/*
 * 打开所有区县名称标签
 */
function openQhmcLabel()
{
	if (PUB_QHMC_SHOW)
	{
		if(PUB_QHMC_LABEL.length>=24)
		{
			for(var i=0;i<PUB_QHMC_LABEL.length;i++)
			{
				map.addOverlay(PUB_QHMC_LABEL[i]);
			}
		}
		else
		{
			var point;
			for (var i=0;i<PUB_QH.length;i++)
			{
				point = new BMap.Point(PUB_QH[i].center[0],PUB_QH[i].center[1]);
				setLabel(PUB_QH[i].qhmc,point);
			}
			point=null;
		}
		
	}
}

/*
 * 添加单个标签
 */
function setLabel(mc,point)
{
	var opts = {
	  position : point,    // 指定文本标注所在的地理位置
	  offset   : new BMap.Size(-15, 0)    //设置文本偏移量
	}
	var label = new BMap.Label(mc,opts);
	label.setStyle({'background-color':'rgba(255, 255, 255,0)','border':'0px'});
	PUB_QHMC_LABEL.push(label);
	map.addOverlay(label);
}

/*
 * 清除单选行政区划图层
 */
function closeSingleQH()
{
	for(var i=0;i<PUB_XZQH.length;i++)
	{
		map.removeOverlay(PUB_XZQH[i]);
	}
	PUB_XZQH.length=0;
	PUB_SHOW_XZQH=false;
}

function chooseQH(val)
{
	if (val=="成都市")
	{
		openAllQXMap();
	}
	else
	{
		setQH(val);
	}
}

/*
*添加区县行政区划图层
*val:行政区划中文名称
*/
function setQH(val){
	$("#xzqh").val(val);
	if (val=="") 
	{
		closeAllQXMap();//关闭所有区县行政区划图层
		closeQhmcLabel();//关闭所有区县名称标签
		closeSingleQH();//清除单个行政区划图层
		refreshCharts(PUB_SB_DATA);//更新图表
		return;
	}
	else if (val=="成都市")
	{
		map.setZoom(PUB_QX_ZOOM);
		map.setCenter(PUB_CENTER);
		if (!PUB_SHOW_QX)
		{
			PUB_SHOW_QX=true;
			PUB_XZQH_QX.length=0;
			for (var i=0;i<PUB_QH.length;i++) {
				addQH(PUB_QH[i],"top");
			}
			openQhmcLabel();
			refreshCharts(PUB_SB_DATA);//更新图表
		}
	}
	else
	{
		closeSingleQH();//清除单选行政区划图层
		PUB_SHOW_XZQH=true;
		PUB_XZQH.length=0;
		for (var i=0;i<PUB_QH.length;i++) {
			if (val.indexOf("高新")>=0)
			{
				if (PUB_QH[i].qhdm=="510109")
				{
					addQH(PUB_QH[i],"single");
				}
			}
			else
			{
				if (PUB_QH[i].qhmc==val)
				{
					addQH(PUB_QH[i],"single");
				}
			}
		}
		if (val.indexOf("高新")>=0)
		{
			$("#xzqh").val("高新区");
			//高新有三个区域，特殊处理,改变地图中心和缩放级别让三个区域都能看见
			window.setTimeout(function(){
				map.setZoom(PUB_QX_ZOOM+1);
				map.setCenter(new BMap.Point(104.091967,30.559262));
			},800);
		}
		
		var point,chartData=[];
		for (var i=0;i<PUB_XZQH.length;i++)
		{
			for(var j=0;j<PUB_SB_DATA.length;j++)
			{
				point = new BMap.Point(PUB_SB_DATA[j].JD,PUB_SB_DATA[j].WD);
				if (BMapLib.GeoUtils.isPointInPolygon(point, PUB_XZQH[i]))
					chartData.push(PUB_SB_DATA[j]);
			}
		}
		refreshCharts(chartData);
	}
}

/*
 * 添加区划图层
 */
function addQH(json,z)
{
	if(z=="top")
	{
		if(json.jdtype=="dary")
		{
			var dary = new BMap.Boundary();
			dary.get(json.qhmc, function(rs){       //获取行政区域
				for (var i = 0; i < rs.boundaries.length; i++) {
					var ply = new BMap.Polygon(rs.boundaries[i], {strokeWeight: 1, strokeColor: '#000000',fillColor:json.color,strokeOpacity:0.6,fillOpacity:0.9}); //建立多边形覆盖物
					ply.addEventListener("click", function(e){
						closeSingleQH();//清除单选行政区划图层
						closeAllQXMap();//关闭所有区县行政区划图层
						setQH(json.qhmc);
					});
					ply.addEventListener("mouseover", function(e){
						this.setFillOpacity(0.7);
						this.setStrokeColor('#ff0000');
						this.setFillColor('#ff0000');
					});
					ply.addEventListener("mouseout", function(e){
						this.setFillOpacity(0.9);
						this.setStrokeColor('#000000');
						this.setFillColor(json.color);
					});
					PUB_XZQH_QX = PUB_XZQH_QX.concat(ply);
					map.addOverlay(ply);  //添加覆盖物
				}
			});
		}
		else if(json.jdtype=="jdata")
		{
			var PA=[];
			var pa=json.jdata;
			for(var i=0;i<pa.length;i++)
			{
				PA.push(new BMap.Point(pa[i][0],pa[i][1]));	
			}
			pa=null;
			var ply = new BMap.Polygon(PA, {strokeWeight: 1, strokeColor: '#000000',fillColor:json.color,strokeOpacity:0.6,fillOpacity:0.9});  //创建多边形
			PA.length=0;
			ply.addEventListener("click", function(e){
				closeSingleQH();//清除单选行政区划图层
				closeAllQXMap();//关闭所有区县行政区划图层
				setQH(json.qhmc);
			});
			ply.addEventListener("mouseover", function(e){
				this.setFillOpacity(0.7);
				this.setStrokeColor('#ff0000');
				this.setFillColor('#ff0000');
			});
			ply.addEventListener("mouseout", function(e){
				this.setFillOpacity(0.9);
				this.setStrokeColor('#000000');
				this.setFillColor(json.color);
			});
			PUB_XZQH_QX = PUB_XZQH_QX.concat(ply);
			map.addOverlay(ply);   //增加多边形
		}
	}
	else //单个区县
	{
		var pointArray = [];
		if(json.dtype=="dary")
		{
			var dary = new BMap.Boundary();
			dary.get(json.qhmc, function(rs){       //获取行政区域
				//var count = rs.boundaries.length; //行政区域的点有多少个
				for (var i = 0; i < rs.boundaries.length; i++) {
					var ply = new BMap.Polygon(rs.boundaries[i], {strokeWeight: 1, strokeColor: '#ff0000',fillColor:'#00ff00',strokeOpacity:0.6,fillOpacity:0.3}); //建立多边形覆盖物
					PUB_XZQH = PUB_XZQH.concat(ply);
					pointArray = pointArray.concat(ply.getPath());
					map.addOverlay(ply);  //添加覆盖物
				}
				map.setViewport(pointArray);    //调整视野  
			});
		}
		else if(json.dtype=="jdata")
		{
			var PA=[];
			var pa=json.jdata;
			for(var i=0;i<pa.length;i++)
			{
				PA.push(new BMap.Point(pa[i][0],pa[i][1]));	
			}
			pa=null;
			var ply = new BMap.Polygon(PA, {strokeWeight: 1, strokeColor: '#ff0000',fillColor:'#00ff00',strokeOpacity:0.6,fillOpacity:0.3});  //创建多边形
			PA.length=0;
			PUB_XZQH = PUB_XZQH.concat(ply);
			pointArray = pointArray.concat(ply.getPath());
			map.addOverlay(ply);   //增加多边形
			map.setViewport(pointArray);    //调整视野  
		}
		else if(json.dtype=="data")
		{
			var PA=[];
			var pa=json.data;
			for(var i=0;i<pa.length;i++)
			{
				PA.push(new BMap.Point(pa[i][0],pa[i][1]));	
			}
			pa=null;
			var ply = new BMap.Polygon(PA, {strokeWeight: 1, strokeColor: '#ff0000',fillColor:'#00ff00',strokeOpacity:0.6,fillOpacity:0.3});  //创建多边形
			PA.length=0;
			PUB_XZQH = PUB_XZQH.concat(ply);
			pointArray = pointArray.concat(ply.getPath());
			map.addOverlay(ply);   //增加多边形
			map.setViewport(pointArray);    //调整视野  
		}
		pointArray.length=0;
	}
}

/*
 * 刷新图表
 */
function refreshCharts(data)
{
	PUB_CHART_DATA = data;
	//更新图表
	setRLChart("","");
	setQHChart("","");
}

//去重数据，统计在用设备单位信息时用,前端过滤
function getQCDataByJGMC(){
	var res = [];
	for(var i=1;i<PUB_CHART_DATA.length;i++){
		if(PUB_CHART_DATA[i].SBZT == "Z"){
			if(res.length == 0){
				res.push(PUB_CHART_DATA[0])
			}
			var repeat = false;
			for(var j=0;j<res.length;j++){
				if (PUB_CHART_DATA[i].JGMC == res[j].JGMC) {
					repeat = true;
					break;
				}
			}
			if (!repeat) {
				res.push(PUB_CHART_DATA[i]);
			}
		}
	}
	PUB_ZYSBDW_DATA = res;
}


//本方法是后台SQL过滤时使用，已改成前端过滤
function refreshZYSBDWCharts(data){
	//按区划统计在用单位
	//setZYDWQHChart("","");
}

/*
 * 设置燃料占比图表
 * tag：目标div的id
 * s：放大图（'big'）/小图（''）
 */
function setRLChart(tag,s)
{
	if (tag=="") tag="echart4";
	var seriesData=[];
	var dM=0,dD=0,dY=0,dQ=0,dS=0,dO=0;
	for (var i=0;i<PUB_CHART_DATA.length;i++)
	{
		dM += PUB_CHART_DATA[i].RL=="M"?1:0;
		dD += PUB_CHART_DATA[i].RL=="D"?1:0;
		dY += PUB_CHART_DATA[i].RL=="Y"?1:0;
		dQ += PUB_CHART_DATA[i].RL=="Q"?1:0;
		dS += PUB_CHART_DATA[i].RL=="S"?1:0;
		dO += PUB_CHART_DATA[i].RL=="O"?1:0;
	}
	seriesData.push($.parseJSON("{name:'煤',value:"+dM+",itemStyle:{normal:{color:'rgba(147,156,163,0.8)'}}}"));
	seriesData.push($.parseJSON("{name:'油',value:"+dY+",itemStyle:{normal:{color:'rgba(145,96,49,0.8)'}}}"));
	seriesData.push($.parseJSON("{name:'气',value:"+dQ+",itemStyle:{normal:{color:'rgba(252,190,1,0.8)'}}}"));
	seriesData.push($.parseJSON("{name:'电',value:"+dD+",itemStyle:{normal:{color:'rgba(0,139,255,0.8)'}}}"));
	seriesData.push($.parseJSON("{name:'生物质',value:"+dS+",itemStyle:{normal:{color:'rgba(81,162,18,0.8)'}}}"));
	seriesData.push($.parseJSON("{name:'其它',value:"+dO+",itemStyle:{normal:{color:'rgba(173,18,255,0.8)'}}}"));
	
	var myChart = echarts.init(document.getElementById(tag)); 
	var option = {};
	if (s=="big")
	{
	   	option = {
	   		title:{
	   			text:'燃料类型占比图',
	   			textStyle:{
			        color:'#fff'
			    },
			    left:'8%',
			    top:'2%'
	   		},
		    textStyle:{
		        color:'#fff'
		    },
		    legend: {
		        orient: 'vertical',
		        show:true,
		        x: 'left',
		        left:'8%',
		        top:'10%',
		        data:['煤','油','气','电','生物质','其它'],
		        textStyle:{
		    		color:'#fff',
		    		fontSize:16
		    	}
		   },
		    series: [
		        {
		            name:'',
		            type:'pie',
		            radius: ['60%', '85%'],
		            avoidLabelOverlap: false,
		            label: {
		                normal: {
		                    show: false,
		                    position: 'center',
		                    formatter:'{b}\n\n{c}\n\n{d}%'
		                },
		                emphasis: {
		                    show: true,
		                    textStyle: {
		                        fontSize: '32',
		                        fontWeight: 'lighter'
		                    }
		                }
		            },
		            labelLine: {
		                normal: {
		                    show: false
		                }
		            },
		            data:seriesData
		        }
		    ]
		};
	}
	else{
		option = {
			title:{
				text:'共'+PUB_CHART_DATA.length+'台',
				textStyle:{
			        color:'#fff'
			    },
			    left:'2%',
			    bottom:'0%'
			},
		    textStyle:{
		        color:'#fff'
		    },
		    legend: {
		    	orient: 'vertical',
		        show:true,
		        x: 'left',
		        left:'2%',
		        top:'0%',
		        data:['煤','油','气','电','生物质','其它'],
		        textStyle:{
		    		color:'#fff',
		    		fontSize:12
		    	},
		    	itemGap:5
		    },
		    series: [
		        {
		            name:'',
		            type:'pie',
		            radius: ['60%', '85%'],
		            center:['65%', '50%'],
		            avoidLabelOverlap: false,
		            label: {
		                normal: {
		                    show: false,
		                    position: 'center',
		                    formatter:'{b}\n{c}\n{d}%'
		                },
		                emphasis: {
		                    show: true,
		                    textStyle: {
		                        fontSize: '16',
		                        fontWeight: 'lighter'
		                    }
		                }
		            },
		            labelLine: {
		                normal: {
		                    show: false
		                }
		            },
		            data:seriesData
		        }
		    ]
		};
	}
	// 为echarts对象加载数据 
	myChart.setOption(option); 
	myChart.on("click",function(params){
		if($("#chartView").css("display")=="none")
		{
			//openChartViewer('rl');
			changeLabel('rl');
		}
		gotoSubChart(params.name);
	});
}

/*
 * 设置燃料子类图表
 */
function gotoSubChart(rlp)
{
	var dm = [["M","煤"],["D","电"],["Y","油"],["Q","气"],["S","生物质"],["O","其它"]];
	var rl = "";
	for (var i=0;i<dm.length;i++) {
		if(rlp==dm[i][1]) rl=dm[i][0];
	}
	var ser = [];
	var serData = [];
	var num=0;
	for (var i=0;i<PUB_CHART_DATA.length;i++) {
		if(PUB_CHART_DATA[i].RL==rl)
		{
			if(ser.indexOf(PUB_CHART_DATA[i].RLLX)<0)
				ser.push(PUB_CHART_DATA[i].RLLX);
		}
	}
	for (var i=0;i<ser.length;i++) {
		num=0;
		for (var j=0;j<PUB_CHART_DATA.length;j++) {
			if(PUB_CHART_DATA[j].RLLX==ser[i])
			{
				num++;
			}
		}
		
		try{
			serData.push($.parseJSON("{name:'"+ser[i]+"',value:"+num+"}"));
		}
		catch(e){}
		
	}
	var subChart = echarts.init(document.getElementById("echartV2")); 
	var option = {
		title:{
   			text:rlp+'-分类占比图',
   			textStyle:{
		        color:'#fff'
		    },
		    left:'8%',
		    top:'2%'
   		},
	    textStyle:{
	        color:'#fff'
	    },
	    legend: {
	        orient: 'horizontal',
	        show:true,
	        x: 'left',
	        left:'8%',
	        top:'10%',
	        data:ser,
	        textStyle:{
	    		color:'#fff',
	    		fontSize:16
	    	}
	    },
	    series: [
	        {
	            name:'',
	            type:'pie',
	            radius: ['60%', '85%'],
	            center: ['50%', '60%'],
	            avoidLabelOverlap: false,
	            label: {
	                normal: {
	                    show: false,
	                    position: 'center',
	                    formatter:'{b}\n\n{c}\n\n{d}%'
	                },
	                emphasis: {
	                    show: true,
	                    textStyle: {
	                        fontSize: '32',
	                        fontWeight: 'lighter'
	                    }
	                }
	            },
	            labelLine: {
	                normal: {
	                    show: false
	                }
	            },
	            data:serData
	        }
	    ],
	    color:['#c23531', '#d48265', '#91c7ae','#749f83', '#ca8622', '#bda29a','#6e7074', '#546570', '#c4ccd3']
	};
	// 为echarts对象加载数据 
	subChart.setOption(option); 
}

/*
 * 设置行政区划图表
 * tag：目标div的id
 * s：放大图（'big'）/小图（''）
 */
function setQHChart(tag,s)
{
	if (tag=="") tag="echart5";
	var ops = $("#ssqh option");
	var qhM=[],jrqN=[],jrqW=[],qhZ=[];
	var jn=0,gln=0,point;
	for (var i=0;i<ops.length;i++)
	{
		gln=0;
		jn=0;
		if (ops[i].value.length>4)
		{
			qhM.push(ops[i].text);
			for (var j=0;j<PUB_CHART_DATA.length;j++)
			{
				if(PUB_CHART_DATA[j].QHDM==ops[i].value)
				{
					gln += 1;
					point = new BMap.Point(PUB_CHART_DATA[j].JD,PUB_CHART_DATA[j].WD);
					for (var k=0;k<PUB_JRQY.length;k++)
					{
						if (BMapLib.GeoUtils.isPointInPolygon(point, PUB_JRQY[k]))
							jn += 1;
					}
				}
			}
			jrqN.push(jn);
			jrqW.push(gln-jn);
			qhZ.push(gln);
		}
	}
	
	var myChart = echarts.init(document.getElementById(tag)); 
	var option = {};
	if(s=="big")
	{
	   option = {
	   		title:{
	   			text:'行政区划分布图',
	   			textStyle:{
			        color:'#fff'
			    },
			    left:'8%',
			    top:'2%'
	   		},
		    tooltip : {
		        trigger: 'axis',
		        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
		            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
		        }
		    },
		    textStyle:{
		        color:'#fff'
		    },
		    legend: {
		    	show:true,
		    	top:'3%',
		    	textStyle:{
		    		color:'#fff',
		    		fontSize:14
		    	},
		        data:['总数','禁燃区内','禁燃区外']
		    },
		    grid: {
		        left: '5%',
		        top:'10%',
		        right: '5%',
		        bottom: '10%',
		        containLabel: true
		    },
		    xAxis : [
		        {
		            type : 'category',
		            data : qhM,
		            axisLine:{
		            	lineStyle:{
		            		color:'#fff'
		            	}
		            },
		            axisLabel:{
		            	//rotate:270,
		            	interval:0
		            }
		        }
		    ],
		    yAxis : [
		        {
		            type : 'value',
		            axisLine:{
		            	lineStyle:{
		            		color:'#fff'
		            	}
		            }
		        }
		    ],
		    series : [
		    	{
		            name:'总数',
		            type:'line',
		            data:qhZ,
		            itemStyle:{
		            	normal:{
		            		color:'rgba(252,190,1,0.8)'
		            	}
		            },
		            label:{
		            	normal:{
		            		show:true/*,
		            		position:'top'*/
		            	}
		            }
		        },
		        {
		            name:'禁燃区内',
		            type:'bar',
		            data:jrqN,
		            itemStyle:{
		            	normal:{
		            		color:'rgba(255,90,90,0.8)'
		            	}
		            }
		        },
		        {
		            name:'禁燃区外',
		            type:'bar',
		            data:jrqW,
		            itemStyle:{
		            	normal:{
		            		color:'rgba(90,255,125,0.8)'
		            	}
		            }
		        }
		    ]
		};
	}
	else{
		option = {
		    tooltip : {
		        trigger: 'axis',
		        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
		            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
		        }
		    },
		    textStyle:{
		        color:'#fff'
		    },
		    legend: {},
		    grid: {
		        left: '3%',
		        top:'3%',
		        right: '3%',
		        bottom: '3%',
		        containLabel: true
		    },
		    xAxis : [
		        {
		            type : 'category',
		            data : qhM,
		            axisLine:{
		            	lineStyle:{
		            		color:'#fff'
		            	}
		            }/*,
		            axisLabel:{
		            	rotate:270,
		            	interval:0
		            }*/
		        }
		    ],
		    yAxis : [
		        {
		            type : 'value',
		            axisLine:{
		            	lineStyle:{
		            		color:'#fff'
		            	}
		            }
		        }
		    ],
		    series : [
		        {
		            name:'禁燃区内',
		            type:'bar',
		            data:jrqN,
		            itemStyle:{
		            	normal:{
		            		color:'rgba(255,90,90,0.8)'
		            	}
		            }
		        },
		        {
		            name:'禁燃区外',
		            type:'bar',
		            data:jrqW,
		            itemStyle:{
		            	normal:{
		            		color:'rgba(90,255,125,0.8)'
		            	}
		            }
		        }
		    ]
		};
	}
	// 为echarts对象加载数据 
	myChart.setOption(option); 
}


/*
 * 关闭图片预览
 */
function closeImgViewer()
{
	$("#picView").css("display","none");
	$("#imgView").attr("src","");
}

/*
 * 关闭图表（放大）窗口
 */
function closeChartViewer()
{
	$("#chartView").css("display","none");
	$("#echartV2").html("");//清空小图内容
}
/*
 * 打开图表（放大）窗口
 */
function openChartViewer(t)
{
	$("#chartView").css("display","block");
	if(t=="qh")
	{
		$("#echartV").css("width","100%");
		$("#echartV2").css("width","0%");
		$("#echartV2").html("");//清空小图内容
		setQHChart("echartV","big");
	}
	else if (t=="rl")
	{
		$("#echartV").css("width","60%");
		$("#echartV2").css("width","38%");
		setRLChart("echartV","big");
	}else if(t=="rc"){
		$("#echartV").css("width","100%");
		$("#echartV2").css("width","0");
		$("#echartV2").html("");//清空小图内容
	}else if(t=="zydw"){
		$("#echartV").css("width","100%");
		$("#echartV2").css("width","0");
		$("#echartV2").html("");//清空小图内容
		setZYDWQHChart("echartV","big");
	}
	else if(t=="sbzx"){
		$("#echartV").css("width","100%");
		$("#echartV2").css("width","0");
		$("#echartV2").html("");//清空小图内容
		setSBZXChart("echartV","big");
	}
}

/*
 * wwq
 * 切换echartLabel标签js
 */
function changeLabel(tag){
	var chartLabel = $(".chartLabel");
	for(var i=0;i<chartLabel.length;i++){
		if(chartLabel[i].id == tag){
			chartLabel[i].style.backgroundColor = "#0080ff";
			chartLabel[i].style.color = '#fff';
			selectDiv = tag;
			openChartViewer(tag);
		}else {
			chartLabel[i].style.backgroundColor = "#fff";
			chartLabel[i].style.color = '#000';
		}
	}
}

/*
 * wwq
 * 鼠标移入事件，改变颜色
 */
function over(obj){
	obj.style.backgroundColor = '#0080ff';
	obj.style.color = '#fff';
	
}

/*
 * wwq
 * 鼠标移除事件，改变颜色
 */
function out(obj){
	if(obj.id == selectDiv){
		
	}else {
		obj.style.backgroundColor = "#fff";
		obj.style.color = '#000';
	}
}


/*
 * 设置右侧控制面板
 */
function setShrinkRight()
{
	if(rightM)
	{
		$(".side_right").css("display","none");
		$(".shrinkRight").css("right","0");
		$(".shrinkRight a").css("background","url(app/gis/img/sbg.png) no-repeat -22px center");
		$(".pano_close").css("right","0px!important;");
		rightM=false;
	}
	else
	{
		$(".side_right").css("display","block");
		$(".shrinkRight").css("right","300px");
		$(".shrinkRight a").css("background","url(app/gis/img/sbg.png) no-repeat -33px center");
		$(".pano_close").css("right","310px!important;");
		rightM=true;
	}
	setRightMHeight();
	shrinkRightCtl(rightM);
}
/*
 * 设置面板收缩层高度控制
 */
function setRightMHeight()
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
 * 缩放控件随动
 */
function shrinkNaveCtl(flag)
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
/*
 * 缩略图控件随动
 */
function shrinkRightCtl(flag)
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
 * 打开/关闭查询结果面板
 */
function openResultPanel()
{
	if(a3){
		$(".column3").css("height","35px");
		a3=false;
	} else {
		$(".column3").css("height",a3h);
		a3=true;
	}
}

/*
 * 查询结果面板内的点击事件，显示设备信息
 */
function clickSearchSB(zcdm,jd,wd)
{
	var point = new BMap.Point(jd,wd);
	map.centerAndZoom(point, 14); 
	setInfoWindow(zcdm,point);
}

/*
 * 跳转到设备详情页面
 */
function gotoDetail(gid)
{
	top.$.dialog({
		width : 800,
		height : 680,
		lock : true,
		title : "设备信息",
		content : 'url:app/ts/device/device_detail.jsp?status=detail&id='+ gid + "&bigClassify=1000",
		data : {"window" : window}
	});
}

/*
 * 打开图片预览
 */
function openImgViewer(e)
{
	$("#picView").css("width","60%");
	$("#picView").css("height","80%");
	$("#imgView").attr("src",e.src).load(function(){
		var w = this.width;
		var h = this.height;
		$("#picView").css("width",w+2+"px");
		$("#picView").css("height",h+2+"px");
		$("#picView").css("top",window.innerHeight/2-h/2);
		$("#picView").css("left",window.innerWidth/2-w/2);
	});
	$("#picView").css("display","block");
}
/*
 * 关闭图片预览
 */
function closeImgViewer()
{
	$("#picView").css("display","none");
	$("#imgView").attr("src","");
}

/*
*禁燃区坐标转换
*/
function setBaiDuGPS()
{
	//alert("in...");
	//return;
	//禁燃区转换
	var url=encodeURI("app/dt/ts/jrqy/setJrqyBaiDuGPS.do?m="+Math.random());
	$.getJSON(url, function(res) {
		if (res.success)
		{
			$.ligerDialog.alert(res.msg,"禁燃区：坐标转换信息（成功/总数）");
		}
		else
		{
			$.ligerDialog.error("坐标转换失败！","禁燃区：坐标转换信息");
		}
	});
	//设备坐标转换
	url=encodeURI("app/dt/tsGlSbwz/setSbBaiDuGPS.do?m="+Math.random());
	$.getJSON(url, function(res) {
		if (res.success)
		{
			$.ligerDialog.alert(res.msg,"锅炉：坐标转换信息（成功/总数）");
		}
		else
		{
			$.ligerDialog.error("坐标转换失败！","锅炉：坐标转换信息");
		}
	});
}

//bootstrap-switch控制
var $createDestroy = $('#switch-create-destroy');
$(function () {
  $('a[href*=\'#\']').on('click', function (event) {
    event.preventDefault()
    var $target = $($(this).attr('href').slice('#'))
    if ($target.length) {
      $window.scrollTop($target.offset().top - sectionTop)
    }
  })
  $('input[type="checkbox"], input[type="radio"]')
    .not('[data-switch-no-init]')
    .bootstrapSwitch()

  $('[data-switch-get]').on('click', function () {
    var type = $(this).data('switch-get')
    window.alert($('#switch-' + type).bootstrapSwitch(type))
  })
  $('[data-switch-set]').on('click', function () {
    var type
    type = $(this).data('switch-set')
    $('#switch-' + type).bootstrapSwitch(type, $(this).data('switch-value'))
  })
  $('[data-switch-toggle]').on('click', function () {
    var type = $(this).data('switch-toggle')
    $('#switch-' + type).bootstrapSwitch('toggle' + capitalize(type))
  })
  $('[data-switch-set-value]').on('input', function (event) {
    var type, value
    event.preventDefault()
    type = $(this).data('switch-set-value')
    value = $.trim($(this).val())
    if ($(this).data('value') === value) {
      return
    }
    $('#switch-' + type).bootstrapSwitch(type, value)
  })
  $('[data-switch-create-destroy]').on('click', function () {
    var isSwitch
    isSwitch = $createDestroy.data('bootstrap-switch')
    $createDestroy.bootstrapSwitch((isSwitch ? 'destroy' : null))
    $(this).button((isSwitch ? 'reset' : 'destroy'))
  })
  $('#confirm').bootstrapSwitch({
    size: 'large',
    onSwitchChange: function (event, state) {
      event.preventDefault()
      return console.log(state, event.isDefaultPrevented())
    }
  })
});