
//将地址解析结果显示在地图上,并调整地图视野
var myGeo = new BMap.Geocoder();
// 百度地图API功能
        var map = null;
        $(function(){
        	// 创建Map实例
        	map = new BMap.Map("map", {enableMapClick: false});   
        	// 初始化地图,设置中心点坐标和地图级别10
            map.centerAndZoom(new BMap.Point(104.049914,30.668745), 8);
            //鼠标滚轮缩放
            map.enableScrollWheelZoom(false); 
            //禁止拖拽
           /* map.disableDragging(); */   

            map.setMapStyle({
                styleJson: [{
		                        "featureType": "all",
		                        "elementType": "all",
		                        "stylers": {"lightness": 61,"saturation": -100}
		                    }]
            });
        	//画出四川地图
            drawSc();
            //添加背景
            addBackground();
            //事件
            map.addEventListener("zoomend", function(e){
            	//如果zoom>7 显示市区地图
            	var z = e.target.getZoom();
            	//alert(z);
            	
            });
            map.addEventListener("dragend", function showInfo(){ 
            	//alert("当前地图中心点：" + map.getCenter().lng + "," + map.getCenter().lat);
        	});
        });
        
function addBackground(){
     $("#map > div > div").append("<div id='miBackground'></div>");
}  

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
		drawLabel(dataSC[i].center,dataSC[i].name);
	}
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


//区县名称
var areaLabels = [];
//画出成都市区划地图
var CDOverlay = [];
function drawCd(obj){       
	var bdary = new BMap.Boundary();
	bdary.get(obj.name, function(rs){       //获取行政区域
		var count = rs.boundaries.length; //行政区域的点有多少个
		var pointArray = [];
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
				pointArray = pointArray.concat(ply.getPath());
				CDOverlay.push(ply);
				drawLabel(new BMap.Point(obj.center[0],obj.center[1]),obj.name);
			}catch(e){
				alert(e);
			}
		}
      	
		for (var i = 0; i < count; i++) {
			var ply = new BMap.Polygon(rs.boundaries[i], 
					{strokeWeight: 1, strokeColor: '#00e4ff',fillColor:obj.color,strokeOpacity:0.5,fillOpacity:obj.opacity}); //建立多边形覆盖物
			ply.addEventListener("click", function(e){
				alert(obj.name);
			});
			ply.name = obj.name;
			map.addOverlay(ply);  //添加覆盖物
			pointArray = pointArray.concat(ply.getPath());
			CDOverlay.push(ply);
			drawLabel(new BMap.Point(obj.center[0],obj.center[1]),obj.name);
		}  
		if(obj.name.indexOf("高新")!=-1){
			map.centerAndZoom(new BMap.Point(104.053578,30.682605), 12); 
		}else{
			
			map.setViewport(pointArray);    //调整视野  
		}
		//addlabel();               
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

function drawLabel(point,text){
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
}
function closeLabel(arr){
	for(var i=0;i<arr.length;i++){
		map.removeOverlay(arr[i]);
	}
}