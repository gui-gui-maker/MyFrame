        // 百度地图API功能
        var map = null;
        $(function(){
        	map = new BMap.Map("map", {
                enableMapClick: false
            });    // 创建Map实例
            map.centerAndZoom(new BMap.Point(102.687394,30.73658), 7);  // 初始化地图,设置中心点坐标和地图级别
            map.enableScrollWheelZoom(true); // 开启鼠标滚轮缩放

            map.setMapStyle({
                styleJson: [
                    {
                        "featureType": "all",
                        "elementType": "all",
                        "stylers": {
                            "lightness": 61,
                            "saturation": -100
                        }
                    }
                ]
            });
            var bounds = map.getBounds();
            //console.log(bounds);
            var sw_lng = bounds.Le;
            var sw_lat = bounds.Ke;
            var ne_lng = bounds.Ge;
            var ne_lat = bounds.Fe;
            
            
            /*var polygon = new BMap.Polygon([
    			new BMap.Point(sw_lng,sw_lat),
    			new BMap.Point(sw_lng,ne_lat),
    			new BMap.Point(ne_lng,ne_lat),
    			new BMap.Point(ne_lng,sw_lat)
    		], {strokeColor:"#000000",fillColor:"#000000", strokeWeight:1, fillOpacity:0.9});  //创建多边形
    		map.addOverlay(polygon);   //增加多边形 
*/    		
    		function setDsOverLays()
    		{
    			var dataSC = [
    				{name:'成都市',color:'#84bf1c'},
    				{name:'攀枝花市',color:'#c3989f'},
    				{name:'自贡市',color:'#ffe903'},
    				{name:'泸州市',color:'#84bf1c'},
    				{name:'德阳市',color:'#84bf1c'},
    				{name:'绵阳市',color:'#ffe903'},
    				{name:'遂宁市',color:'#c3989f'},
    				{name:'广元市',color:'#c3989f'},
    				{name:'乐山市',color:'#8d9efc'},
    				{name:'内江市',color:'#c3989f'},
    				{name:'眉山市',color:'#84bf1c'},
    				{name:'南充市',color:'#84bf1c'},
    				{name:'雅安市',color:'#c3989f'},
    				{name:'巴中市',color:'#8d9efc'},
    				{name:'宜宾市',color:'#c3989f'},
    				{name:'广安市',color:'#c8c502'},
    				{name:'资阳市',color:'#8d9efc'},
    				{name:'达州市',color:'#c3989f'},
    				{name:'阿坝藏族羌族自治州',color:'#8d9efc'},
    				{name:'凉山彝族自治州',color:'#84bf1c'},
    				{name:'甘孜藏族自治州',color:'#ffe903'}
    			];
    			for(var i=0;i<dataSC.length;i++)
    			{
    				getBoundary(dataSC[i]);
    			}
    		}
    		
    		function getBoundary(data){       
    			var bdary = new BMap.Boundary();
    			bdary.get(data.name, function(rs){       //获取行政区域
    				//map.clearOverlays();        //清除地图覆盖物       
    				var count = rs.boundaries.length; //行政区域的点有多少个
    				if (count === 0) {
    					alert('未能获取当前输入行政区域');
    					return ;
    				}
    	          	var pointArray = [];
    				for (var i = 0; i < count; i++) {
    					var ply = new BMap.Polygon(rs.boundaries[i], {strokeWeight: 1, fillColor: data.color}); //建立多边形覆盖物
    					map.addOverlay(ply);  //添加覆盖物
    					pointArray = pointArray.concat(ply.getPath());
    				}    
    				//map.setViewport(pointArray);    //调整视野  
    				//addlabel();               
    			});   
    		}
    	
    		setDsOverLays();
    		$("#map > div > div").append("<div id='cDiv'></div>");
    		
		    var myCompOverlay = new ComplexCustomOverlay(new BMap.Point(103.510646944444,30.1944980555555));

		    map.addOverlay(myCompOverlay);
		    var pt = new BMap.Point(103.510646944444,30.1944980555555);
			/*var myIcon = new BMap.Icon("http://lbsyun.baidu.com/jsdemo/img/fox.gif", new BMap.Size(300,157));
			var marker2 = new BMap.Marker(pt,{icon:myIcon});  // 创建标注
			map.addOverlay(marker2);*/
			/*var marker = new BMap.Marker(pt);// 创建标注
			map.addOverlay(marker);  */
        });
        	
        
       
