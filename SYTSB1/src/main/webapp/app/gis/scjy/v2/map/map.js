//将地址解析结果显示在地图上,并调整地图视野
var myGeo = new BMap.Geocoder();
//视图
var currentView = 'sc';
//城市名
var cityLabels = [];
//区县名称
var areaLabels = [];
//统计总数
var COUNT = {"code":"510","name":"成都市","annual":0,"yesterday":0,"today":0};
//大地图路书目标设备 
var lushuDevice = null;
// 百度地图API功能
        var map = null;
        $(function(){
        	openLoading();
        	map = new BMap.Map("map", {enableMapClick: false});    // 创建Map实例
            map.centerAndZoom(new BMap.Point(104.049914,30.668745), 12);  // 初始化地图,设置中心点坐标和地图级别10
            map.enableScrollWheelZoom(true); // 开启鼠标滚轮缩放
            map.setMapStyle({
                styleJson: [{
		                        "featureType": "all",
		                        "elementType": "all",
		                        "stylers": {"lightness": 61,"saturation": -100}
		                    }]
            });
            
          /*  var pt1 = new BMap.Point(104.101463,30.658724);
        	var poverlay1 = new POverlay(pt1,"app/gis/scjy/v2/images/LOGO32.png","省特检院");  // 创建标注
        	map.addOverlay(poverlay1);
        	var pt2 = new BMap.Point(104.070002,30.595457);
        	var poverlay2 = new POverlay(pt2,"app/gis/scjy/v2/images/LOGO32.png","高新工作站");  // 创建标注
        	map.addOverlay(poverlay2);
        	var pt3 = new BMap.Point(104.012219,30.723908);
        	var poverlay3 = new POverlay(pt3,"app/gis/scjy/v2/images/LOGO32.png","金牛工作站");  // 创建标注
        	map.addOverlay(poverlay3);
        	var pt4 = new BMap.Point(104.076243,30.523343);
        	var poverlay4 = new POverlay(pt4,"app/gis/scjy/v2/images/LOGO32.png","天府新区工作站");  // 创建标注
        	map.addOverlay(poverlay4);
        	var pt5 = new BMap.Point(104.010132,30.576831);
        	var poverlay5 = new POverlay(pt5,"app/gis/scjy/v2/images/LOGO32.png","双流工作站");  // 创建标注
        	map.addOverlay(poverlay5);*/
        	
        	//画出四川地图
            drawSc();
            map.setMinZoom(8);
            for(var i=0;i<CDAREAS.length;i++){
				drawCd(CDAREAS[i]);
			}
            //添加背景
            addBackground();
            setTimeout(function(){
            	closeLoading();
            }, 10000);
           
            //统计微信
            countWeixin();
            //初始化随机显示（出报告）
            $.ajax({
        		url:"gis/device/static/rtCache.do",
        		type:"post",
        		data:{},
        		dataType:"json",
        		success:function(data){
        			if(data.success){
        				rtCache = {};
        				for(var i=1;i<=16;i++){
        					if(data['d'+i].length>0){
        						rtCache['d'+i]=data['d'+i];	
        						lushuDevices = lushuDevices.concat(data['d'+i]);
        					}
        				}
        				setInterval(function(){
        					var randomNum = Math.floor(Math.random()*lushuDevices.length);
        					var device = lushuDevices[randomNum];
        					if(device.LONGITUDE){
        						run(device);
        					}
        				}, 10000);
        				//启动显示缓存
        				startShowRtCache(4,12000);
        			}
        		}
        	});
            //初始化微信缓存
            $.ajax({
        		url:"gis/device/static/wxCache.do",
        		type:"post",
        		data:{},
        		dataType:"json",
        		success:function(data){
        			if(data.success){
        				wxCache = data.data;
        				startShowWxCache(5,15000);
        			}
        		}
        	});
    		//循环刷新
    		act();
        });
        
//显示在地图上的所有设备对象
var devices = null;
//初始化所有坐标
var points = [];
//坐标
var pt = null;
//初始化所有覆盖物（自定义覆盖物）
var markers = [];
//点聚合对象
var markerClusterer = null;

//画出成都市区划地图
var CDOverlay = [];
function drawCd(obj){       
	var bdary = new BMap.Boundary();
	bdary.get(obj.name, function(rs){       //获取行政区域
		var count = rs.boundaries.length; //行政区域的点有多少个
		if (count === 0) {
			try{
				var PA=[];
				var pa=obj.data;
				for(var i=0;i<pa.length;i++)
				{
					PA.push(new BMap.Point(pa[i][0],pa[i][1]));	
				}
				pa=null;
				var ply = new BMap.Polygon(PA, {strokeWeight: 1, strokeColor: '#00e4ff',fillColor:obj.color,strokeOpacity:0.5,fillOpacity:obj.opacity}); //创建多边形
				PA.length=0;
				ply.addEventListener("click", function(e){
					alert(obj.name);
				});
				ply.name = obj.name;
				map.addOverlay(ply);   //增加多边形
				CDOverlay.push(ply);
				drawLabel(new BMap.Point(obj.center[0],obj.center[1]),obj.name,areaLabels);
			}catch(e){
				alert(e);
			}
		}
		for (var i = 0; i < count; i++) {
			var ply = new BMap.Polygon(rs.boundaries[i], 
					{strokeWeight: 1, strokeColor: '#00e4ff',fillColor:obj.color,strokeOpacity:0.5,fillOpacity:obj.opacity}); //建立多边形覆盖物
			ply.addEventListener("click", function(e){
				//alert(obj.name);
			});
			ply.name = obj.name;
			map.addOverlay(ply);  //添加覆盖物
			CDOverlay.push(ply);
			drawLabel(new BMap.Point(obj.center[0],obj.center[1]),obj.name,areaLabels);
		}    
	});   
}
//关闭成都地图
function closeCdMap()
{
	for(var i=0;i<CDOverlay.length;i++)
	{
		map.removeOverlay(CDOverlay[i]);
	}
}

//画出四川区划地图
var SCOverlay = [];
function drawSc()
{
	var dataSC = [
		{name:'成都市',color:'#0f3085',opacity:.8,center:new BMap.Point(104.049914,30.668745)},
		{name:'攀枝花市',color:'#0f3085',opacity:.5,center:new BMap.Point(101.711743,26.607674)},
		{name:'自贡市',color:'#0f3085',opacity:.5,center:new BMap.Point(104.740755,29.370769)},
		{name:'泸州市',color:'#0f3085',opacity:.2,center:new BMap.Point(105.465197,28.882729)},
		{name:'德阳市',color:'#08389a',opacity:.7,center:new BMap.Point(104.395795,31.128169)},
		{name:'绵阳市',color:'#08389a',opacity:.6,center:new BMap.Point(104.70051,31.482532)},
		{name:'遂宁市',color:'#104ecd',opacity:.8,center:new BMap.Point(105.602871,30.530637)},
		{name:'广元市',color:'#0f3085',opacity:.3,center:new BMap.Point(105.850423,32.452341)},
		{name:'乐山市',color:'#104ecd',opacity:.8,center:new BMap.Point(103.675952,29.546882)},
		{name:'内江市',color:'#104ecd',opacity:.8,center:new BMap.Point(105.04849,29.580862)},
		{name:'眉山市',color:'#08389a',opacity:.7,center:new BMap.Point(103.848514,30.096526)},
		{name:'南充市',color:'#1951b1',opacity:.9,center:new BMap.Point(106.111754,30.832868)},
		{name:'雅安市',color:'#0f3085',opacity:.5,center:new BMap.Point(102.837974,30.068816)},
		{name:'巴中市',color:'#104ecd',opacity:.6,center:new BMap.Point(106.759634,31.867983)},
		{name:'宜宾市',color:'#104ecd',opacity:.8,center:new BMap.Point(104.641355,28.770166)},
		{name:'广安市',color:'#104ecd',opacity:.8,center:new BMap.Point(106.636104,30.457761)},
		{name:'资阳市',color:'#08389a',opacity:.7,center:new BMap.Point(104.656282,30.138455)},
		{name:'达州市',color:'#08389a',opacity:.4,center:new BMap.Point(107.526336,31.215296)},
		{name:'阿坝藏族羌族自治州',color:'#104ecd',opcity:.7,center:new BMap.Point(102.252687,31.908945)},
		{name:'凉山彝族自治州',color:'#104ecd',opcity:.8,center:new BMap.Point(102.290176,27.89899)},
		{name:'甘孜藏族自治州',color:'#1f63ec',opcity:.2,center:new BMap.Point(100.844012,30.591963)}
	];
	for(var i=0;i<dataSC.length;i++)
	{
		getBoundary(dataSC[i]);
		drawLabel(dataSC[i].center,dataSC[i].name,cityLabels);
	}
	map.setViewport(SCOverlay);    //调整视野  
}
//画出单个区域
function getBoundary(data){       
	var bdary = new BMap.Boundary();
	bdary.get(data.name, function(rs){       //获取行政区域  
		var count = rs.boundaries.length; //行政区域的点有多少个
		if (count === 0) {
			alert(data.name+'地图加载失败！！');
			return ;
		}
		for (var i = 0; i < count; i++) {
			var ply = new BMap.Polygon(rs.boundaries[i], {
				strokeWeight: 1, strokeColor: '#00e4ff',fillColor:data.color,strokeOpacity:0.5,fillOpacity:data.opacity
			}); //建立多边形覆盖物
			map.addOverlay(ply);  //添加覆盖物
		}    
	});   
}
//关闭四川地图
function closeScMap()
{
	for(var i=0;i<SCOverlay.length;i++)
	{
		map.removeOverlay(SCOverlay[i]);
	}
}
function addBackground(){
	$("#map > div > div").append("<div id='miBackground'></div>");
}
function romoveBackground(){
	$("#miBackground").remove();
}






//循环刷新方法
function act(){
	//查询10分钟新起草的报告
	setInterval(rtQuery, 15000);
	//微信查询设备
	setInterval(xwQuery, 20000);
	//查询10分钟新起草的报告+n显示
	setInterval(queryTodayList, 600000);
}

function drawLabel(point,text,collection){
	var opts = {
			  position : point,    // 指定文本标注所在的地理位置
			  offset   : new BMap.Size(0, 0)    //设置文本偏移量
			}
	var label = new BMap.Label(text, opts);  // 创建文本标注对象
		label.setStyle({
			 backgroundColor:'transparent',
			 border:0,
			 color : "#999",
			 fontSize : "12px",
			 height : "20px",
			 lineHeight : "20px",
			 fontFamily:"微软雅黑"
		 });
	map.addOverlay(label);  
	collection.push(label);
}
function closeLabel(arr){
	for(var i=0;i<arr.length;i++){
		map.removeOverlay(arr[i]);
	}
}
function selectsArea(area){
	if(area.name == '高新区'){
		map.centerAndZoom(new BMap.Point(area.center[0],area.center[1]),11);
		for(var i=0;i<areaLabels.length;i++){
			var label = areaLabels[i];
			if(label.content.indexOf('高新')!=-1){
				label.setStyle({color:'red'});
			}else{
				label.setStyle({color:'#999'});
			}
		}
	}else{
		map.centerAndZoom(new BMap.Point(area.center[0],area.center[1]),12);
		for(var i=0;i<areaLabels.length;i++){
			var label = areaLabels[i];
			if(label.content==area.name){
				label.setStyle({color:'red'});
			}else{
				label.setStyle({color:'#999'});
			}
		}
	}
}
//刷新地图展示新增数据
function mapAddDevices(items){
	//在地图区划中心显示加N台设备
	//刷新统计
	initCount();
	//记录各区域增加了几台设备
	var ccc = {};
	for(var i=0;i<items.length;i++){
		var area = items[i].DEVICE_AREA_CODE ;
		//判断成都市是否包含这些区划
		if(!areaCodes[area]){
			continue;
		}
		if(ccc[area]){
			ccc[area] += 1;
		}else{
			ccc[area] = 1;
		}
	}
	$.each(ccc,function(key,value){
		//区域中心显示动画
		var pt = new BMap.Point(areaCodes[key].center[0],areaCodes[key].center[1]);
		addAnimate(pt,value);
	});
	window.setTimeout(romoveAnimate,30000);
	
};
//动画
var animates = [];
function addAnimate(point,index,num){
	var animate = new AddAnimateOverlay(point,index,num);
	map.addOverlay(animate);
	animates.push(animate);
}
function romoveAnimate(){
	for(var i=0;i<animates.length;i++){
		map.removeOverlay(animates[i]);
	}
	animates.length=0;
}

//反写坐标到设备表
function writePointToDocument(id,longitude,latitude){
	$.ajax({
		url:"gis/device/static/writePointToDocument.do",
		type:"post",
		data:{
			"id":id,
			"longitude":longitude,
			"latitude":latitude
		},
		dataType:"json",
		success:function(data){
			if(data.success){
				console.log("反写坐标成功！");
			}else{
				console.log("反写坐标失败！");
			}
		},
		error:function(data){
			console.log(data.msg);
		}
	});
}
/*function notice(item,option){
	var lis = $("#notice_col1 div").get();
	if(option.type=="weixin"){
		var flag = true;
		var str = '';
		for(var i=0;i<lis.length;i++){
			str = $(lis[i]).html();
			if(str.indexOf(item.USERNAME.substring(item.USERNAME.length-4,item.USERNAME.length))!=-1
					&&str.indexOf(item.QUERYTIME.substring(0,10))!=-1){
				flag = false;
				break;
			}
		}
		if(flag){
			var notice_content = "游客" +item.USERNAME.substring(item.USERNAME.length-4,item.USERNAME.length)+"于"+item.QUERYTIME+"通过微信平台查询了报告信息。" ;
			if(option.isNew){
				changeNoticeContent(notice_content,{'color':'yellowgreen'});
			}else{
				changeNoticeContent(notice_content,{'color':'white'});
			}
		}
	}else if(option.type=="report"){
		var flag = true;
		var str = '';
		for(var i=0;i<lis.length;i++){
			str = $(lis[i]).html();
			if(str.indexOf(item.ENTER_OP_NAME)!=-1
					&&str.indexOf(item.DEVICE_USE_PLACE)!=-1){
				flag = false;
				break;
			}
		}
		if(flag){
			if(item.ENTER_OP_NAME == null){
				return;
			}
			var notice_content = item.ENTER_OP_NAME+"于"+item.ENTER_TIME2+"起草了报告"+(item.DEVICE_USE_PLACE==null?"":(",设备地点："+item.DEVICE_USE_PLACE));
			if(option.isNew){
				changeNoticeContent(notice_content,{'color':'yellowgreen'});
			}else{
				changeNoticeContent(notice_content,{'color':'white'});
			}
		}
	}
}*/

function addNewMarker(item){
	if(item.LONGITUDE != null && item.LONGITUDE != '' && item.LATITUDE != '' && item.LATITUDE != null)
	{
		//添加动画
		var pt = new BMap.Point(item.LONGITUDE,item.LATITUDE);
		//点聚合
		var marker = new BMap.Marker(pt);
		marker.id = item.ID;
		markers.push(marker);
		markerClusterer = new BMapLib.MarkerClusterer(map, {markers:markers});
	}else{
		myGeo.getPoint(item.DEVICE_USE_PLACE, function(point){
			if (point) {
				item.LONGITUDE = point.lng;
				item.LATITUDE = point.lat;
				//反写到base_device_document
				writePointToDocument(item.ID,item.LONGITUDE,item.LATITUDE);
				//点聚合
				var marker = new BMap.Marker(point);
				marker.id = item.ID;
				markers.push(marker);
				markerClusterer = new BMapLib.MarkerClusterer(map, {markers:markers});
			}else{
				console.log("地址:"+item.DEVICE_USE_PLACE+",没有解析到结果!");
				return 0;
			}
		}, "成都市");
	}
}

