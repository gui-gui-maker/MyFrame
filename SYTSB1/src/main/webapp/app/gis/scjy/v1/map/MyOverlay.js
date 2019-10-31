//判断字符串是否为空
function isEmpty(words){
	if(words){
		if(words==""||words=='null'||words=='undefined'){
			return true;
		}else{
			return false;
		}
	}else{
		return true;
	}
	
}	
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
		          			"<img style='margin:5px 5px;width:20px;height:20px;' src='app/gis/scjy/v1/images/"
		          			+this._deviceImgName+".png' />" +
      					"</div>" +
		          		"<div class='btbg' style='height:40px;'></div>" +
	          		"</span>" +
          		  "</a>"+
		          "<a class='livemap-avatar' href='javascript:void(0);'>"+
		              "<img class='livemap-mark-avatar' src='/app/gis/scjy/v1/images/"+bubble+"'>"+
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
    	  $(div).find("a.livemap-avatar").css({"background-image":"url(app/gis/scjy/v1/images/mark_"+this._color+".png)"});
    	  
      }
      map.getPanes().labelPane.appendChild(div);
      //说明栏加入内容
      if(this._type=="weixin"){
		  var username = this._element.USERNAME;
		  $(div).find("div.livemap-mark").find('.livemap-hostname span').css({"height":"40px"});
		  $(div).find("div.livemap-mark").find('.livemap-hostname span div.wz')
		  .html(this._element.QUERYCONTENT/*"游客&nbsp;"+username.substring(username.length-4,username.length)*/);//+'<br>'+this._element.QUERYTIME)
		 // if(this._isNew){
	    	  $(div).find("div.livemap-mark").find('.livemap-hostname').css({"display":"block"});
	    	  $(div).find("div.livemap-mark").hover(function(){
	    		  if(this.id!='undefined'){
	    			  queryFlow(this.id,$(this).attr("name"),$(this).attr("tt"));
	    		  }
	    	  },function(){
	    		  
	    	  })
	     /* }else{//如果是缓存数据则说明栏鼠标放上去才显示
	    	  $(div).find("div.livemap-mark").hover(function(){
	    		  $(this).find('.livemap-hostname').css({"display":"block"});
	    		  if(this.id!='undefined'){
	    			  queryFlow(this.id,$(this).attr("name"),$(this).attr("tt"));
	    		  }
	    	  },function(){
	    		  $(this).find('.livemap-hostname').css({"display":"none"});
	    	  })
	      }*/
	  }else if(this._type=="report"){
		  $(div).find("div.livemap-mark").find('.livemap-hostname span div.wz').html(this._element.ENTER_OP_NAME);
		  $(div).find("div.livemap-mark").find('.livemap-hostname').css({"display":"block"});
    	  //显示起草人
    	  $(div).find("div.livemap-mark").hover(function(){
    		  if(this.id!='undefined'){
    			  queryFlow(this.id,$(this).attr("name"),$(this).attr("tt"));
    		  }
    	  },function(){
    		  
    	  })
	  }else if(this._type=="query"){
		  $(div).find("div.livemap-mark").find('.livemap-hostname span div.wz').html(this._element.ENTER_OP_NAME);
		  //显示起草人
		  $(div).find("div.livemap-mark").find('.livemap-hostname').css({"display":"block"});
    	  $(div).find("div.livemap-mark").hover(function(){
    		  if(this.id!='undefined'){
    			  queryFlow(this.id,$(this).attr("name"),$(this).attr("tt"));
    		  }
    	  },function(){
    		  
    	  })
	  }
      
      //如果是新数据则说明栏直接显示
      /* if(this._isNew){
    	 
    	  $(div).find("div.livemap-mark").find('.livemap-hostname').css({"display":"block"});
    	  //html公司名称
    	  $(div).find("div.livemap-mark").hover(function(){
    		  if(this.id!='undefined'){
    			  queryFlow(this.id,$(this).attr("name"),$(this).attr("tt"));
    		  }
    	  },function(){
    		  
    	  })
      }else{//如果是缓存数据则说明栏鼠标放上去才显示
    	  $(div).find("div.livemap-mark").hover(function(){
    		  $(this).find('.livemap-hostname').css({"display":"block"});
    		  if(this.id!='undefined'){
    			  queryFlow(this.id,$(this).attr("name"),$(this).attr("tt"));
    		  }
    	  },function(){
    		  $(this).find('.livemap-hostname').css({"display":"none"});
    	  })
      }*/
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
 /**
  * 查询设备，返回设备信息及检验流程数据
  * @param id 设备id
  * @param name 当前流程
  * @param code 数组，0设备注册代码1二维码
  */
 function queryFlow(id,name,dataString){
	 $.post("department/basic/mapGetFlowStep.do",{"id":id},function(res){
			if(res.success){
				showFlow(res,name,dataString);
			}else{
				alert(res.msg);
			}
	 });
 }
 
 /**
  * 根据单位批量查询
  */
 function MultiOverlay(obj){
     this._point = obj.point;
     if(obj.id){
     	this._index = obj.id;
     }
     if(obj.number){
     	 if(obj.number<=9){
          	this._num = "&nbsp;"+obj.number+"&nbsp;";
          }else{
          	this._num = obj.number;
          }
     }
     if(obj.data){
 	   this._data = obj.data;
     }
   }
 MultiOverlay.prototype = new BMap.Overlay();
 MultiOverlay.prototype.initialize = function(map){
     this._map = map;
     var div = this._div = document.createElement("div");
     div.style.position = "absolute";
     div.style.zIndex = BMap.Overlay.getZIndex(this._point.lat);
     $(div).css({'width':'40px','height':'40px'})
     .append("<div id='"+this._index+"' class='map_point' style='position: absolute;left:-18px;top:-26px;'>"+
		          "<div class='addnum' style='position: absolute;left:0;top:0;background-color:#23C944;border-color:#78E78E;'>" +
		          "<span class='anum0'>"+this._num+"</span></div>"+
		      "</div>");
     map.getPanes().labelPane.appendChild(div);
     $(div).find("div.map_point").hover(function(){
	  		  if(this.id != '' && this.id == companyQueryData.id){
	  			 fillWindow(companyQueryData.data);
	  		  }
	  	  },function(){
	  		  
	  	  })
     return div;
   }
 MultiOverlay.prototype.draw = function(){
     var pixel = this._map.pointToOverlayPixel(this._point);
     this._div.style.left = pixel.x + "px";
     this._div.style.top  = pixel.y + "px";
 }


function fillWindow(rows){
	var $docs = $("#tccontent").find("div.pro_head").children();
	$($docs).remove();
	$("#tccontent").find("div.pro_head").css({'height':'100px'}).append("<div class='data_count' >查到共计"+rows.length+"台设备，费用合计：<span id='total_fee'>0</span>元</div>" +
			"<table class='data_head'><thead><tr class='d_tr'>" +
								/*"<th align='center' style='width:5%;line-height:100%;'>选择&nbsp;<input id='selectAll' type='checkbox'></th>" +*/
						 		"<th align='center' style='width:20%;'>设备注册代码</th>" +
						 		"<th align='center' style='width:15%;'>金属二维码</th>" +
						 		"<th align='center' style='width:15%;'>操作步骤</th>" +
						 		"<th align='center' style='width:8%;'>操作人</th>" +
						 		"<th align='center' style='width:18%;'>报告编号</th>" +
						 		"<th align='center' style='width:24%;'>流程说明</th>" +
					 		"</tr></thead></table>");
	 //添加下方流程
	 $("div.progress table").children().remove();
	 $("div.progress table").addClass("data_body").append("<tbody></tbody>");
	 //排序
	 //将没有报告编号的取出来
	 var arra=[];
	 var arrb=[];
	 for(var j=0;j<rows.length;j++){
		 try{
			 var str = rows[j].REPORT_SN;
			 parseInt(str.substring(str.length-9));
			 arra.push(rows[j]);
 		 }catch(e){
 			 arrb.push(rows[j]);
 		 }
	 }
	 arra = arra.sort(function(a,b){
		 		  try{
		 			 var x = parseInt(a.REPORT_SN.substring(a.REPORT_SN.length-9));
			 		 var y = parseInt(b.REPORT_SN.substring(a.REPORT_SN.length-9)); 
			 		 if (x>y) {
			              return -1;
			         }else if(x<y){
			              return 1
			         }else{
			              return 0;
			         }    
		 		  }catch(e){
		 			  return 1;
		 		  }
		    });
	 for(var k=0;k<arrb.length;k++){
		 arra.push(arrb[k]);
	 }

	 rows = arra;
	 var fees = 0;
	 for(var i=0;i<rows.length;i++){
		 var opMark = "";
		 if("打印报告"==rows[i].FLOW_NOTE_NAME||"报告领取"==rows[i].FLOW_NOTE_NAME||"报告归档"==rows[i].FLOW_NOTE_NAME){
			 opMark="报告书流转完成，可领取";
		 }else{
			 opMark="报告书流转中，请等待";
		 }
		 
		 try{
			var fee = parseFloat(rows[i].ADVANCE_FEES);
			if(typeof fee == 'number'){
				fees += (isNaN(fee)?0:fee);
			}
		 }catch(e){
				
		 }
		 
		 $("div.progress table tbody").append("<tr class='d_tr'>" +
				 	/*"<td align='center' style='width:5%;'><input class='selectOne' id='"+rows[i].ID+"' value='"+rows[i].ADVANCE_FEES+"' type='checkbox'></td>" +*/
			 		"<td align='center' style='width:20%;'>"+rows[i].DEVICE_REGISTRATION_CODE+"</td>" +
			 		"<td align='center' style='width:15%;'>"+(rows[i].DEVICE_QR_CODE!=null?rows[i].DEVICE_QR_CODE:'')+"</td>" +
			 		"<td align='center' style='width:12%;'>"+rows[i].OP_ACTION+"</td>" +
			 		"<td align='center' style='width:8%;'>"+rows[i].OP_USER_NAME+"</td>" +
			 		"<td align='center' style='width:18%;'>"+(rows[i].REPORT_SN!=null?rows[i].REPORT_SN:'')+"</td>" +
			 		"<td align='center' style='width:24%;'>"+opMark+"</td>" +
		 		"</tr>");
	 }
	 $("#total_fee").html(fees);
	 $(".white_content").addClass("md-show");
		$("#light,#tccontent").show();
		var ligh=$(window).height()*0.8-155;
		$(".tankuang").css({"height":ligh+"px"});
		$("#fade").show();
		var oneExe1=setTimeout(function(){
			$(".div.livemap-mark").addClass("hover");
		},1);
		
	$("#selectAll").click(function(){
		if($(this).attr("checked")){
			$("input.selectOne").attr("checked",true);
			if($("input.selectOne:checked").length>0){
				var fees = 0;
				var objs = $("input.selectOne:checked").get();
				for(var i in objs){
					try{
						var fee = parseFloat($(objs[i]).val());
						if(typeof fee == 'number'){
							fees += (isNaN(fee)?0:fee);
						}
					}catch(e){
						
					}
				}
				$("#total_fee").html(fees);
			}else{
				$("#total_fee").html(0);
			}
			
			//当前为选中状态
		}else{
			//当前为不选中状态
			$("input.selectOne").attr("checked",false);
			$("#total_fee").html(0);
		}
	});
	$(".selectOne").click(function(){
		if($("input.selectOne:checked").length>0){
			var fees = 0;
			var objs = $("input.selectOne:checked").get();
			for(var i in objs){
				try{
					var fee = parseFloat($(objs[i]).val());
					if(typeof fee == 'number'){
						fees += (isNaN(fee)?0:fee);
					}
				}catch(e){
					
				}
			}
			$("#total_fee").html(fees);
		}else{
			$("#total_fee").html(0);
		}
	});

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
				//console.log("地址:"+device.DEVICE_USE_PLACE+",没有解析到结果!");
			}
		}, "成都市");
	}else{
		queryStar2(device,clss)
	}
	
}

function queryStar2(device,clss){
	if(clss.type == 'query'){
		//var pt = new BMap.Point(device.LONGITUDE,device.LATITUDE);
		//var marker2 = new BMap.Marker(pt);
		//marker2.setLabel(new BMap.Label(device.DEVICE_USE_PLACE));
		//smallMap.addOverlay(marker2);
			//drv.search(startPoint, pt);
			//drive.search(startPoint, pt);
			//openSmallMap();
			//添加标注
			//setTimeout(function(){
				var myOverlay = new ComplexCustomOverlay(device,clss);
				map.addOverlay(myOverlay);
				clientQuery.push(myOverlay);
				//用于延时打开设备流程图
				//lushuDevice = device;
				//聚焦到查询的点
				//var pt = new BMap.Point(device.LONGITUDE,device.LATITUDE);
				//map.setCenter(pt);
				//打开新流程窗口
				queryFlow(device.RID,device.FLOW_NOTE_NAME,device);
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
//显示鼠标指定的设备检验的流程
function showFlow(flow,currentFlow,dataString){
	 var dataObject = '';
	 if( typeof dataString == 'string'){
		 dataObject = $.parseJSON(dataString);
	 }else{
		 dataObject = dataString;
	 }
	 //添加流程動畫
	 if(currentFlow == '科室报检'){
		 currentFlow = "设备报检"; //处理没有科室报检流程问题
	 }
	 //设备二维码
	 var qrcode = "";
	 if(!isEmpty(dataObject.REPORT_SN)){
		 $("#current_device_qrcode").show();
		 $("#qrcode_box").css({"background":"#fff"});
		 //报告书编号
		 $("#report_sn").html(dataObject.REPORT_SN);
		  $("#report_sn_box").show();
		 qrcode = "http://m.scsei.org.cn/?action=queryreport&searchvalue="+dataObject.REPORT_SN;
		 if(current_device_qrcode){
			 current_device_qrcode.clear(); // 清除代码
			 current_device_qrcode.makeCode(qrcode);
		 }else{
			 current_device_qrcode =  
				new QRCode(document.getElementById("current_device_qrcode"),{
				    text: qrcode,
				    width: 104,
				    height: 104,
				    colorDark : "#000000",
				    colorLight : "#ffffff",
				    correctLevel : QRCode.CorrectLevel.H
				});
		 }
		 
	 }/*else if(!isEmpty(dataObject.DEVICE_REGISTRATION_CODE)){
		 $("#current_device_qrcode").show();
		 qrcode = "http://m.scsei.org.cn/?action=queryreport&searchvalue="+dataObject.DEVICE_REGISTRATION_CODE;
		 if(current_device_qrcode){
			 current_device_qrcode.clear(); // 清除代码
			 current_device_qrcode.makeCode(qrcode);
		 }else{
			 current_device_qrcode =  
				new QRCode(document.getElementById("current_device_qrcode"),{
				    text: qrcode,
				    width: 104,
				    height: 104,
				    colorDark : "#000000",
				    colorLight : "#ffffff",
				    correctLevel : QRCode.CorrectLevel.H
				});
		 }
	 }*/else{
		 $("#current_device_qrcode").hide();
		 $("#qrcode_box").css({"background":"none"});
		 //报告书编号
		 $("#report_sn").html("");
		 $("#report_sn_box").hide();
	 }
	 
	 //使用单位
	 $("#current_company_name").html(dataObject.COMPANY_NAME);
	 
	 
	 
	 //添加流程動畫
	 $("#report_flow_info li").removeClass("active");
	 $("#report_flow_info li span").remove();
	 
	 var lines = $("#report_flow_info li").get();
	 for(var i=0;i<lines.length;i++){
		 $(lines[i]).addClass("active");
		 var txt = $(lines[i]).attr("name");
		 if(currentFlow != "报告归档" && currentFlow == txt ){
			 $(lines[i]).append("<span class=\"arrjt\"><img src=\"app/gis/scjy/v1/images/lc_jt.png\"></span>");
			 break;
		 }
	 }
	 
	 $("#stepInfo div.stepIco i").removeClass("active");
	 
	 var flows = $("#stepInfo div.stepIco").get();
	 for(var i=0;i<flows.length;i++){
		 $(flows[i]).find("i").addClass("active");
		 var txt = $(flows[i]).find("div.stepText").html();
		 if(currentFlow != "报告归档" && currentFlow == txt ){
			 break;
		 }
	 }
	
}
