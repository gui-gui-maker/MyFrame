/*
*全局变量
*/
var PUB_ZT=[];//所有主体标记【Marker】
var PUB_ZT_DATA=[];//所有主体数组【Array】
var PUB_CHART_DATA=[];//用于统计图的主体数组【Array】
var searchInfoWindow=null;
var gis_qymc;
var sy_flag;
var path;
var jsession = "";
var curFocusName;

$(function (){
	setInterval("getDevIdno()",5000);
	
	$(".mainlevel > a").each(function(){
		if ($(this).text()!=curFocusName)
		{
			$(this).removeClass('active');
		}
	});
	
	setMenuFocus();
});


/**
 * 地图加载完成后执行的方法区
 */
function _ifs_afterMapInit()
{
	//事件
	var sy_flag = parent.sy_flag;
	if(sy_flag==true){
		map.addEventListener("zoomend", function(e){
			if(e.target.getZoom()<=12){
				sy_flag = null;
				parent.api.close();
			}
		});
	}
	//获取设备编号
	getDevIdno();
}

function getDevIdno(){
//	var searchParm = searchData;
//	var url=encodeURI("dm/ydzf/dmYdzfTheme/getDevIdno.do?m="+Math.random());
//	$.ajax({
//		url: url,
//		type: "POST",
//		async:true,
//		traditional: true,  
//		datatype: "json",
//		data: {
//			searchParm:searchParm
//		},
//		success: function (res) {
//			if (res.success) {
//				if(res.data.sbbhList.length==0){
//					$.ligerDialog.warn("该人还没有添加执法设备","提示");
//					return;
//				}
//				var devIdno = null;
//				for ( var iterable_devIdno in res.data.sbbhList) {
//					if(iterable_devIdno=="0"){
//						devIdno = res.data.sbbhList[iterable_devIdno].DEVIDNO;
//					}else{
//						devIdno = devIdno+","+res.data.sbbhList[iterable_devIdno].DEVIDNO;
//					}
//				}
				var devIdno = '80014089,80014082';
				//与北斗视频对接
				//获取回话id
				getJsession();
				//获取设备位置等信息
				getGpsSb(devIdno);
//			}
//		},
//		error: function (res) {
//			alert("数据查询失败...");
//			$.ligerDialog.warn("数据查询失败...","提示");
//		}
//	});
	
}

function setMapCenter(devIdno)
{
	if (PUB_ZT_DATA.length>0)
	{
		for (var i=0;i<PUB_ZT_DATA.length;i++)
		{
			if(PUB_ZT_DATA[i].id==devIdno)
			{
				var p = new BMap.Point(PUB_ZT_DATA[i].mlng,PUB_ZT_DATA[i].mlat);
				map.setCenter(p);
				map.panTo(p);
				map.setZoom(18);
			}
		}
	}
}

/**
 * 通过设备号获取设备位置等信息
 */
function getGpsSb(devIdno){
	 $.ajax({
         type:'POST',
         url: "http://124.129.19.117:8088/StandardApiAction_getDeviceStatus.action?jsession="+jsession+"&devIdno="+devIdno+"&toMap=2",
         cache:false,
         dataType:'JSONP', 
         success: function (data) {
        	 //data = eval('(' + data + ')');
            if(data.result == 0){
				PUB_ZT_DATA = data.status;
				openZTLayer();
            } else {
            	$.ligerDialog.warn("获取设备信息失败","提示");
            }
    },
 });
	 
}

/**
 * 通过用户名密码获取jsession
 */
function getJsession(){
	var account = "cdkh";
	var password = "cdkh";
	 $.ajax({
         type:'POST',
         url: "http://124.129.19.117:8088/StandardApiAction_login.action?account="+account+"&password="+password+"",
         cache:false,
         dataType:'JSONP', 
         success: function (data) {
        	// data = eval('(' + data + ')');
            if(data.result == 0){
            	jsession = data.jsession;
            } else {
            	$.ligerDialog.warn("获取会话ID失败","提示");
            }
    },
 });
	
}


/**
 * 闪点图层控制
 * @param {Object} s
 */
function _ifs_setStarLayer(s)
{
	openZTLayer();
}

/*
 * 打开主体图层
 */
function openZTLayer()
{
	$("#starLay").remove();
	setBoundZT();
}

/*
 * 设置可视区域内的设备标记
 */
function setBoundZT()
{
	cleanZTMarkers(); //清除所有设备标记
	var point;
	for(var i=0;i<PUB_ZT_DATA.length;i++)
	{
		point = new BMap.Point(PUB_ZT_DATA[i].mlng,PUB_ZT_DATA[i].mlat);
//		if(map.getBounds().containsPoint(point))
//		{
			addMarker(PUB_ZT_DATA[i]);
//		}
	}
	setClsMarker(true);
	point=null;
}

/*
*清除所有设备标记
*/
function cleanZTMarkers()
{
	for(var i=0;i<PUB_ZT.length;i++)
	{
		map.removeOverlay(PUB_ZT[i]);
	}
	PUB_ZT.length=0;
	setClsMarker(false);
}

/*
 * 设置点聚合显示状态
 */
function setClsMarker(flag)
{
	if(flag)
	{
			try{
				markerClusterer.clearMarkers();
			}
			catch(e){}
			markerClusterer = new BMapLib.MarkerClusterer(map, {markers:PUB_ZT,maxZoom:17});
	}
	else
	{
		try{
			markerClusterer.clearMarkers();
		}
		catch(e){}
	}
}




/**
 * 设置信息窗口
 * @param {Object} ptsn
 * @param {Object} point
 */
function setInfoWindow(devIdno,jsession)
{
	var url = "url:app/gisydzf/gs_ydzf/sbxx_video.jsp?devIdno="+devIdno+"&jsession="+jsession+"";
	winOpen({
		width: 1300,
		height: 650,
		lock: true,
        max: false,
        min: false,
		data: {},
		title: "",
		content: url
	});
}




/*
 * 添加标记
 */
function addMarker(json) 
{
	var point = new BMap.Point(json.mlng,json.mlat);
	var myIcon = null;
	if(json.ol==0){
		 myIcon = new BMap.Icon("app/gisydzf/img/ico_00.png", new BMap.Size(90,100));
	}else{
		 myIcon = new BMap.Icon("app/gisydzf/img/ico_01.png", new BMap.Size(90,100));
	}
	
	var marker = new BMap.Marker(point,{icon:myIcon});  // 创建标注
	PUB_ZT = PUB_ZT.concat(marker); 
	
	marker.setTitle(json.id);
	
	
	
	marker.addEventListener("click", function(e){
		if(json.ol==0){
			$.ligerDialog.warn("设备不在线，请选择在线设备操作","提示");
			return;
		}
		setInfoWindow(json.id,jsession);
	});
}

function setMenuFocus(){
	$(".mainlevel a").click(function(){
		$(this).parents(".mainlevel").find(".xxx").addClass("active");
		curFocusName = $(this).text();
		
		$(".mainlevel > a").each(function(){
			if ($(this).text()!=curFocusName)
			{
				$(this).removeClass('active');
			}
		});
	});
}

/*
 * 打开视频
 */
function openVideoViewer() {
	var left_devIdno = "80014082";
	var right_devIdno = "80014089";

	getJsession();
	var left_url = "app/gisydzf/gs_ydzf/sbxx_video2.jsp?devIdno="
			+ left_devIdno + "&jsession=" + jsession+"&videoOpenType=3";
	var right_url = "app/gisydzf/gs_ydzf/sbxx_video2.jsp?devIdno="
			+ right_devIdno + "&jsession=" + jsession+"&videoOpenType=3";
	$("#leftVideo").attr("src", left_url);
	$("#rightVideo").attr("src", right_url);

	$("#picView").css("width", "96%");
	$("#picView").css("height", "96%");
	$("#picView").css("top", "2%");
	$("#picView").css("left", "2%");
	/*
	 * $("#imgView").attr("src","http://www.baidu.com").load(function(){ var w =
	 * this.width; var h = this.height; $("#picView").css("width",w+2+"px");
	 * $("#picView").css("height",h+2+"px");
	 * $("#picView").css("top",window.innerHeight/2-h/2);
	 * $("#picView").css("left",window.innerWidth/2-w/2); });
	 */
	$("#picView").css("display", "block");
}

/*
 * 关闭视频
 */
function closeVideoViewer() {
	$("#picView").css("display", "none");
	//$("#imgView").attr("src", "");
}
