	// 复杂的自定义覆盖物
    function ComplexCustomOverlay(obj,clss){
    	this._element = obj;
		this._point = new BMap.Point(obj.LONGITUDE,obj.LATITUDE);
		this._index = obj.ID;//id
		this._ins = obj.RID;
		this._flow = obj.FLOW_NOTE_NAME;
		this._deviceRegistrationCode = obj.DEVICE_REGISTRATION_CODE;
		this._qrCode = obj.DEVICE_QR_CODE;
		if(obj.FILE_NAME){
			this._imgName = obj.FILE_NAME;
			this._imgPath = obj.FILE_PATH;
		}
		if(typeof clss == 'object'){
			if(clss.type == "weixin"){
				this._color = "1aad06";
				this._text = "微信查询";
			}else if(clss.type == "query"){
				this._color = "ff0000";
				this._text = "设备注册代码查询";
			}else if(clss.type == "report"){
				this._color = "ff7200";
				this._text = "报告领取";
			}else{
				this._color = "ffd500";
				this._text = "";
			}
			this._type = clss.type;
			this._isNew = clss.isNew;
		}
		//名字后面的图片背景颜色、图片名称
		this._deviceImgName = '';
		this._deviceImgBackground='';
		if(obj.DEVICE_SORT.substring(0,1)== '1'){//锅炉
			this._deviceImgName = 'ts04';
			this._deviceImgBackground='#45b293';
		}else if(obj.DEVICE_SORT.substring(0,1)== '2'){//压力容器
			this._deviceImgName = 'ts02';
			this._deviceImgBackground='#f6a800';
		}else if(obj.DEVICE_SORT.substring(0,1)== '3'){//电梯
			this._deviceImgName = 'ts01';
			this._deviceImgBackground='#3EA3E5';
		}else if(obj.DEVICE_SORT.substring(0,1)== '4'){//起重机械
			this._deviceImgName = 'ts07';
			this._deviceImgBackground='#9e28b5';
		}else if(obj.DEVICE_SORT.substring(0,1)== '5'){//场（厂）内专用机动车辆
			this._deviceImgName = 'ts06';
			this._deviceImgBackground='#81bc00';
		}else if(obj.DEVICE_SORT.substring(0,1)== '6'){//大型游乐设施
			this._deviceImgName = 'ts05';
			this._deviceImgBackground='#e06b2f';
		}else if(obj.DEVICE_SORT.substring(0,1)== '7'){//压力管道元件
			this._deviceImgName = '';
			this._deviceImgBackground='';
		}else if(obj.DEVICE_SORT.substring(0,1)== '8'){//压力管道
			this._deviceImgName = 'ts03';
			this._deviceImgBackground='#5b8ab5';
		}else if(obj.DEVICE_SORT.substring(0,1)== '9'){//客运索道
			this._deviceImgName = 'ts08';
			this._deviceImgBackground='#e64461';
		}
		
		
    }
    ComplexCustomOverlay.prototype = new BMap.Overlay();
    ComplexCustomOverlay.prototype.initialize = function(map){
      this._map = map;
      var dataString = JSON.stringify(this._element);
      var bubble = 'bubble_ffd500.png';
      if(this._color){
    	  if(this._color == '1aad06'){
    		  bubble = 'bubble_1aad06.png';
    	  }else if(this._color == 'ff0000'){
    		  bubble = 'bubble_ff0000.gif'; 
    	  }else if(this._color == 'ff7200'){
    		  bubble = 'bubble_ff7200.gif'; 
    	  }
      }
      var div = this._div = document.createElement("div");
      div.style.position = "absolute";
      div.style.zIndex = BMap.Overlay.getZIndex(this._point.lat);
      $(div).css({'width':'42px','height':'72px'}).attr({"id":this._index})
      .append("<div id='"+this._ins+"' name='"+this._flow+"' tt='"+dataString+"' class='livemap-mark anime-jump' style='position: absolute;left: -1px; top: 0px;'>"+
		          "<a class='livemap-hostname' href='javascript:void(0);' style='display:none;'>" +
		          	"<span>" +
		          		"<div class='wz'></div>" +
		          		"<div class='device_cls' style='background:"+this._deviceImgBackground+";'>" +
		          			"<img style='margin:5px 5px;width:20px;height:20px;' src='app/gis/scjy/v2/images/"
		          			+this._deviceImgName+".png' />" +
      					"</div>" +
		          		"<div class='btbg' style='height:40px;'></div>" +
	          		"</span>" +
          		  "</a>"+
		          "<a class='livemap-avatar' href='javascript:void(0);'>"+
		              "<img class='livemap-mark-avatar' src='/app/gis/scjy/v2/images/"+bubble+"'>"+
		          "</a>"+
		      "</div>"+
		      "<div class='map_point' style='position: absolute;left: -9px; top: 36px;user-select: none;'>"+
		          "<div class='dot'></div>"+
		          "<div class='pulse'></div>"+
		          "<div class='pulse1'></div>"+
		      "</div>");
      //如果有检验员的图片则显示头像
      if(this._imgName){
		  $(div).find("img.livemap-mark-avatar").attr("src","/fileupload/downloadByFilePath.do?path="+this._imgPath+"&fileName="+this._imgName);
	  }
      //气球样式
      if(this._color){
    	  $(div).find("div.dot").css({"background-color":"#"+this._color});
    	  $(div).find("div.pulse").css({"border-color":"#"+this._color,"box-shadow": "1px 1px 10px #"+this._color});
    	  $(div).find("div.pulse1").css({"border-color":"#"+this._color,"box-shadow": "1px 1px 10px #"+this._color});
    	  $(div).find("a.livemap-avatar").css({"background-image":"url(app/gis/scjy/v2/images/mark_"+this._color+".png)"});
    	  
      }
      map.getPanes().labelPane.appendChild(div);
      //说明栏加入内容
      if(this._type=="weixin"){
		  var username = this._element.USERNAME;
		  $(div).find("div.livemap-mark").find('.livemap-hostname span').css({"height":"40px"});
		  $(div).find("div.livemap-mark").find('.livemap-hostname span div.wz')
		  .html("游客&nbsp;"+username.substring(username.length-4,username.length));//+'<br>'+this._element.QUERYTIME)
		  if(this._isNew){
	    	  $(div).find("div.livemap-mark").find('.livemap-hostname').css({"display":"block"});
	      }else{
	    	  //如果是缓存数据则说明栏鼠标放上去才显示
	    	  $(div).find("div.livemap-mark").hover(function(){
	    		  $(this).find('.livemap-hostname').css({"display":"block"});
	    	  },function(){
	    		  $(this).find('.livemap-hostname').css({"display":"none"});
	    	  })
	      }
	  }else if(this._type=="report"){
		  $(div).find("div.livemap-mark").find('.livemap-hostname span div.wz').html(this._element.ENTER_OP_NAME);
		  $(div).find("div.livemap-mark").find('.livemap-hostname').css({"display":"block"});
    	 
	  }else if(this._type=="query"){
		  $(div).find("div.livemap-mark").find('.livemap-hostname span div.wz').html(this._element.ENTER_OP_NAME);
		  //显示起草人
		  $(div).find("div.livemap-mark").find('.livemap-hostname').css({"display":"block"});
	  }
    
      return div;
    }
    ComplexCustomOverlay.prototype.draw = function(){
      var pixel = this._map.pointToOverlayPixel(this._point);
      this._div.style.left = pixel.x -23 + "px";
      this._div.style.top  = pixel.y -72 + "px";
    }
 //+n 动画
    function AddAnimateOverlay(point,num,index){
        this._point = point;
        if(index){
        	this._index = index;
        }
        this._num = num;
      }
    AddAnimateOverlay.prototype = new BMap.Overlay();
    AddAnimateOverlay.prototype.initialize = function(map){
        this._map = map;
        var div = this._div = document.createElement("div");
        div.style.position = "absolute";
        div.style.zIndex = BMap.Overlay.getZIndex(this._point.lat);
        $(div).css({'width':'40px','height':'40px'})
        .append("<div class='map_point' style='position: absolute;left:-18px;top:-26px;'>"+
  		          "<div class='addnum' style='position: absolute;left:0;top:0;'><span class='anum0'>+"+this._num+"</span></div>"+
  		      "</div>");
        map.getPanes().labelPane.appendChild(div);
        return div;
      }
    AddAnimateOverlay.prototype.draw = function(){
        var pixel = this._map.pointToOverlayPixel(this._point);
        this._div.style.left = pixel.x + "px";
        this._div.style.top  = pixel.y + "px";
      }


//添加跳动闪点
function queryStar(device,clss){
	if(!device){
		return;
	}
	//有些没坐标
	if(!device.LONGITUDE || device.LONGITUDE == '' || device.LONGITUDE == null 
			||!device.LATITUDE || device.LATITUDE == '' || device.LATITUDE == null)
	{
		myGeo.getPoint(device.DEVICE_USE_PLACE, function(point){
			if (point) {
				device.LONGITUDE = point.lng;
				device.LATITUDE = point.lat;
				queryStar2(device,clss);
				//反写到base_device_document
				writePointToDocument(device.ID,device.LONGITUDE,device.LATITUDE);
			}else{
				console.log("地址:"+device.DEVICE_USE_PLACE+",没有解析到结果!");
			}
		}, "成都市");
	}else{
		queryStar2(device,clss)
	}
	
}

function queryStar2(device,clss){
	if(clss.type == 'query'){
		
			//添加标注
			//setTimeout(function(){
		var myOverlay = new ComplexCustomOverlay(device,clss);
		map.addOverlay(myOverlay);
		clientQuery.push(myOverlay);
		//用于延时打开设备流程图
		lushuDevice = device;
				//聚焦到查询的点
				//var pt = new BMap.Point(device.LONGITUDE,device.LATITUDE);
				//map.setCenter(pt);
				//打开新流程窗口
				//queryFlow(device.RID,device.FLOW_NOTE_NAME,device);
			//}, 3000);
			
			
	}else if(clss.type == 'weixin'){
		 var myOverlay = new ComplexCustomOverlay(device,clss);
		 map.addOverlay(myOverlay);
		 if(clss.box=="wxCacheOverlays"){
			  wxCacheOverlays.push(myOverlay); 
		 }
		 if(clss.isNew){
			 wxNewOverlays.push(myOverlay);
		 }
		 
	}else if(clss.type == 'report'){
		
			 var myOverlay = new ComplexCustomOverlay(device,clss);
			 map.addOverlay(myOverlay);
			 changeScrollContent("抓取"+device.COMPANY_NAME+"的"+device.DEVICE_NAME+"检验信息");
			 if(clss.box=="rtCacheOverlays"){
				 rtCacheOverlays[clss.cbox].push(myOverlay); 
			 }
			 if(clss.isNew){
				 rtOverlays.push(myOverlay);
			 }
	}
}

