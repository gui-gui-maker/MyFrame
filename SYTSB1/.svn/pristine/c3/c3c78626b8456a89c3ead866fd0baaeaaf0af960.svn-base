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
		  .html("游客&nbsp;"+username.substring(username.length-4,username.length));//+'<br>'+this._element.QUERYTIME)
		  if(this._isNew){
	    	  $(div).find("div.livemap-mark").find('.livemap-hostname').css({"display":"block"});
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
	      }
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
      return div;
    }
    ComplexCustomOverlay.prototype.draw = function(){
      var pixel = this._map.pointToOverlayPixel(this._point);
      this._div.style.left = pixel.x -23 + "px";
      this._div.style.top  = pixel.y -72 + "px";
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
 function queryFlow1(id){
	 $.post("inspectionInfo/basic/detail.do",{"id":id},function(res){
			if(res.success){
				 $.post("department/basic/mapGetFlowStep.do",{"id":id},function(ress){
						if(ress.success){
							showFlow1(ress,res.flow_note_name,res.data);
						}else{
							alert(res.msg);
						}
				 });
			}else{
				alert(res.msg);
			}
	 });
	
 }
 function showFlow(flow,currentFlow,dataString){
	 var dataObject = '';
	 if( typeof dataString == 'string'){
		 dataObject = $.parseJSON(dataString);
	 }else{
		 dataObject = dataString;
	 }
	 //将json字符串转成json对象
	 
	 //统一上面部分
	 $("#tccontent_v4").find("div.pro_head").children().remove();
	 $("#tccontent_v4").find("div.pro_head").css({'height':'155px'}).append("<div class='pro_bod'>"+
					"<ul class='flow'>"+
						"<li class='first'>"+
							"<div class='f-box'>"+
								"<span></span>"+
							"</div>"+
							"<div class='x-tt'>开始</div>"+
						"</li>"+
						"<li>"+
							"<div class='f-box'>"+
								"<span></span>"+
							"</div>"+
							"<div class='x-tt'>任务分配</div>"+
						"</li>"+
						"<li>"+
							"<div class='f-box'>"+
								"<span></span>"+
							"</div>"+
							"<div class='x-tt'>报告录入</div>"+
						"</li>"+
						"<li>"+
							"<div class='f-box'>"+
								"<span></span>"+
							"</div>"+
							"<div class='x-tt'>报告审核</div>"+
						"</li>"+
						"<li>"+
							"<div class='f-box'>"+
								"<span></span>"+
							"</div>"+
							"<div class='x-tt'>报告签发</div>"+
						"</li>"+
						"<li>"+
							"<div class='f-box'>"+
								"<span></span>"+
							"</div>"+
							"<div class='x-tt'>报告领取</div>"+
						"</li>"+
						"<li>"+
							"<div class='f-box'>"+
								"<span></span>"+
							"</div>"+
							"<div class='x-tt'>报告归档</div>"+
						"</li>"+
						"<li class='last'>"+
							"<div class='f-box'>"+
								"<span></span>"+
							"</div>"+
							"<div class='x-tt'>结束</div>"+
						"</li>"+
					"</ul>"+
					"<div id='drCode' class='st1'></div>"+
					"<div id='qrCode' class='st1'></div>"+
					"<div id='enter_op_name' class='st1' style='cursor:pointer;color:#fbe808;'></div>"+
					"<div id='enter_op_tel' class='st1' style='display:none;'></div>"+
				"</div>"+
				"<div class='pro_bg'></div>");
	 //----------------------------------------------------------
	 $("#drCode").html("注册代码："+dataObject.DEVICE_REGISTRATION_CODE);
	 $("#qrCode").html("二维码："+(dataObject.device_qr_code==null||dataObject.DEVICE_QR_CODE==undefined?"":dataObject.DEVICE_QR_CODE));
	 $("#enter_op_tel").html("联系电话："+(dataObject.ENTER_OP_TEL==null||dataObject.ENTER_OP_TEL==undefined?"":dataObject.ENTER_OP_TEL));
	 $("#enter_op_name").html("联系人："
			 +(dataObject.ENTER_OP_NAME==null||dataObject.ENTER_OP_NAME==undefined?"":dataObject.ENTER_OP_NAME))
			 .bind("click",function(){
				var dis = $("#enter_op_tel").css("display");
				if(dis=='none'){
					$("#enter_op_tel").css("display","inline-block");
				}else{
					$("#enter_op_tel").css("display","none");
				}
	 });
	 //添加流程動畫
	 if(currentFlow == '科室报检'){
		 currentFlow = "任务分配"; //处理没有科室报检流程问题
	 }
	 $("ul.flow li i").remove();//先刪除動畫
	 $("ul.flow li div.f-box").removeClass("fish");//移除已走流程
	 var lis = $("ul.flow li").get();
	 for(var i=0;i<lis.length;i++){
		 $(lis[i]).find("div.f-box").addClass("fish");
		 var txt = $(lis[i]).find("div.x-tt").html();
		 if(currentFlow != "报告归档" && currentFlow == txt ){
			 $(lis[i]).find("div.f-box").append("<i><img src='/app/gis/scjy/v1/images/state_actve.gif'></i>");
			 break;
		 }
	 }
	 if(currentFlow == "报告归档"){
		 $(lis[lis.length-1]).find("div.f-box").addClass("fish");
	 }
	//统一清理表格--------------------------------
	 $("div.progress table").children().remove();
	 $("div.progress table").append("<tbody>"+
			 							"<tr class='d_tr'>"+
			 								"<td align='center'>"+
			 									"<div class='st2'>业务流水号：212542</div>"+
			 								"</td>"+
										"</tr>"+
									"</tbody>");
	 //添加下方流程
	 var steps = flow.flowStep;
	 var $td = $("div.progress table tr td");
	 $td.find("div").remove();
	 $td.append("<div class='st2'>业务流水号："+flow.sn+"</div>");
	 if((steps.length)%2==0){
		 for(var i=0;i<steps.length;i++){
			 if(i%2){
				 $td.append("<div class='jt1'>" +
				 				"<img src='/app/gis/scjy/v1/images/jt2.png'>" +
					 		"</div>"+
			 				"<div class='show0'>" +
						 		"<div class='show1'>操作步骤: "+steps[i].op_action+"</div>" +
						 		"<div class='show1'>操作人员: "+steps[i].op_user_name+"</div>" +
						 		"<div class='show1'>操作时间: "+steps[i].op_time+"</div>" +
						 		"<div class='show1'>操作说明: "+steps[i].op_remark+"</div>" +
						 	"</div></div>" );
			 }else{
				 $td.append("<div class='clear'><div class='show0'>" +
						 		"<div class='show1'>操作步骤: "+steps[i].op_action+"</div>" +
						 		"<div class='show1'>操作人员: "+steps[i].op_user_name+"</div>" +
						 		"<div class='show1'>操作时间: "+steps[i].op_time+"</div>" +
						 		"<div class='show1'>操作说明: "+steps[i].op_remark+"</div>" +
						 	"</div>"); 
			 }
			 
		 }
	 }else{
		 for(var i=0;i<steps.length;i++){
			 if(i==(steps.length-1)){
				 $td.append("<div class='clear'><div class='show0'>" +
						 		"<div class='show1'>操作步骤: "+steps[i].op_action+"</div>" +
						 		"<div class='show1'>操作人员: "+steps[i].op_user_name+"</div>" +
						 		"<div class='show1'>操作时间: "+steps[i].op_time+"</div>" +
						 		"<div class='show1'>操作说明: "+steps[i].op_remark+"</div>" +
						 	"</div></div>"); 
			 }else if(i%2){
				 $td.append("<div class='jt1'>" +
				 				"<img src='/app/gis/scjy/v1/images/jt2.png'>" +
					 		"</div>"+
			 				"<div class='show0'>" +
						 		"<div class='show1'>操作步骤: "+steps[i].op_action+"</div>" +
						 		"<div class='show1'>操作人员: "+steps[i].op_user_name+"</div>" +
						 		"<div class='show1'>操作时间: "+steps[i].op_time+"</div>" +
						 		"<div class='show1'>操作说明: "+steps[i].op_remark+"</div>" +
						 	"</div></div>" );
			 }else{
				 $td.append("<div class='clear'><div class='show0'>" +
						 		"<div class='show1'>操作步骤: "+steps[i].op_action+"</div>" +
						 		"<div class='show1'>操作人员: "+steps[i].op_user_name+"</div>" +
						 		"<div class='show1'>操作时间: "+steps[i].op_time+"</div>" +
						 		"<div class='show1'>操作说明: "+steps[i].op_remark+"</div>" +
						 	"</div>"); 
			 }
			 
		 } 
	 }
	
		$(".white_content").addClass("md-show");
		$("#light_v4,#tccontent_v4").show();
		var ligh=$(window).height()*0.8-155;
		$(".tankuang").css({"height":ligh+"px"});
		$("#fade_v4").show();
		var oneExe1=setTimeout(function(){
			$(".div.livemap-mark").addClass("hover");
		},1);
 }
 function showFlow1(flow,currentFlow,dataString){
	 var dataObject = '';
	 if( typeof dataString == 'string'){
		 dataObject = $.parseJSON(dataString);
	 }else{
		 dataObject = dataString;
	 }
	 //将json字符串转成json对象
	 
	 //统一上面部分
	 $("#tccontent_v4").find("div.pro_head").children().remove();
	 $("#tccontent_v4").find("div.pro_head").css({'height':'155px'}).append("<div class='pro_bod'>"+
					"<ul class='flow'>"+
						"<li class='first'>"+
							"<div class='f-box'>"+
								"<span></span>"+
							"</div>"+
							"<div class='x-tt'>开始</div>"+
						"</li>"+
						"<li>"+
							"<div class='f-box'>"+
								"<span></span>"+
							"</div>"+
							"<div class='x-tt'>任务分配</div>"+
						"</li>"+
						"<li>"+
							"<div class='f-box'>"+
								"<span></span>"+
							"</div>"+
							"<div class='x-tt'>报告录入</div>"+
						"</li>"+
						"<li>"+
							"<div class='f-box'>"+
								"<span></span>"+
							"</div>"+
							"<div class='x-tt'>报告审核</div>"+
						"</li>"+
						"<li>"+
							"<div class='f-box'>"+
								"<span></span>"+
							"</div>"+
							"<div class='x-tt'>报告签发</div>"+
						"</li>"+
						"<li>"+
							"<div class='f-box'>"+
								"<span></span>"+
							"</div>"+
							"<div class='x-tt'>报告领取</div>"+
						"</li>"+
						"<li>"+
							"<div class='f-box'>"+
								"<span></span>"+
							"</div>"+
							"<div class='x-tt'>报告归档</div>"+
						"</li>"+
						"<li class='last'>"+
							"<div class='f-box'>"+
								"<span></span>"+
							"</div>"+
							"<div class='x-tt'>结束</div>"+
						"</li>"+
					"</ul>"+
					"<div id='drCode' class='st1'></div>"+
					"<div id='qrCode' class='st1'></div>"+
					"<div id='enter_op_name' class='st1' style='cursor:pointer;color:#fbe808;'></div>"+
					"<div id='enter_op_tel' class='st1' style='display:none;'></div>"+
				"</div>"+
				"<div class='pro_bg'></div>");
	 //----------------------------------------------------------
	 $("#drCode").html("注册代码："+dataObject.device_registration_code);
	 $("#qrCode").html("二维码："+(dataObject.device_qr_code==null||dataObject.device_qr_code==undefined?"":dataObject.device_qr_code));
	 $("#enter_op_tel").html("联系电话："+(dataObject.enter_op_tel==null||dataObject.enter_op_tel==undefined?"":dataObject.enter_op_tel));
	 $("#enter_op_name").html("联系人："
			 +(dataObject.enter_op_name==null||dataObject.enter_op_name==undefined?"":dataObject.enter_op_name))
			 .bind("click",function(){
				var dis = $("#enter_op_tel").css("display");
				if(dis=='none'){
					$("#enter_op_tel").css("display","inline-block");
				}else{
					$("#enter_op_tel").css("display","none");
				}
	 });
	 //添加流程動畫
	 if(currentFlow == '科室报检'){
		 currentFlow = "任务分配"; //处理没有科室报检流程问题
	 }
	 $("ul.flow li i").remove();//先刪除動畫
	 $("ul.flow li div.f-box").removeClass("fish");//移除已走流程
	 var lis = $("ul.flow li").get();
	 for(var i=0;i<lis.length;i++){
		 $(lis[i]).find("div.f-box").addClass("fish");
		 var txt = $(lis[i]).find("div.x-tt").html();
		 if(currentFlow != "报告归档" && currentFlow == txt ){
			 $(lis[i]).find("div.f-box").append("<i><img src='/app/gis/scjy/v1/images/state_actve.gif'></i>");
			 break;
		 }
	 }
	 if(currentFlow == "报告归档"){
		 $(lis[lis.length-1]).find("div.f-box").addClass("fish");
	 }
	//统一清理表格--------------------------------
	 $("div.progress table").children().remove();
	 $("div.progress table").append("<tbody>"+
			 							"<tr class='d_tr'>"+
			 								"<td align='center'>"+
			 									"<div class='st2'>业务流水号：212542</div>"+
			 								"</td>"+
										"</tr>"+
									"</tbody>");
	 //添加下方流程
	 var steps = flow.flowStep;
	 var $td = $("div.progress table tr td");
	 $td.find("div").remove();
	 $td.append("<div class='st2'>业务流水号："+flow.sn+"</div>");
	 if((steps.length)%2==0){
		 for(var i=0;i<steps.length;i++){
			 if(i%2){
				 $td.append("<div class='jt1'>" +
				 				"<img src='/app/gis/scjy/v1/images/jt2.png'>" +
					 		"</div>"+
			 				"<div class='show0'>" +
						 		"<div class='show1'>操作步骤: "+steps[i].op_action+"</div>" +
						 		"<div class='show1'>操作人员: "+steps[i].op_user_name+"</div>" +
						 		"<div class='show1'>操作时间: "+steps[i].op_time+"</div>" +
						 		"<div class='show1'>操作说明: "+steps[i].op_remark+"</div>" +
						 	"</div></div>" );
			 }else{
				 $td.append("<div class='clear'><div class='show0'>" +
						 		"<div class='show1'>操作步骤: "+steps[i].op_action+"</div>" +
						 		"<div class='show1'>操作人员: "+steps[i].op_user_name+"</div>" +
						 		"<div class='show1'>操作时间: "+steps[i].op_time+"</div>" +
						 		"<div class='show1'>操作说明: "+steps[i].op_remark+"</div>" +
						 	"</div>"); 
			 }
			 
		 }
	 }else{
		 for(var i=0;i<steps.length;i++){
			 if(i==(steps.length-1)){
				 $td.append("<div class='clear'><div class='show0'>" +
						 		"<div class='show1'>操作步骤: "+steps[i].op_action+"</div>" +
						 		"<div class='show1'>操作人员: "+steps[i].op_user_name+"</div>" +
						 		"<div class='show1'>操作时间: "+steps[i].op_time+"</div>" +
						 		"<div class='show1'>操作说明: "+steps[i].op_remark+"</div>" +
						 	"</div></div>"); 
			 }else if(i%2){
				 $td.append("<div class='jt1'>" +
				 				"<img src='/app/gis/scjy/v1/images/jt2.png'>" +
					 		"</div>"+
			 				"<div class='show0'>" +
						 		"<div class='show1'>操作步骤: "+steps[i].op_action+"</div>" +
						 		"<div class='show1'>操作人员: "+steps[i].op_user_name+"</div>" +
						 		"<div class='show1'>操作时间: "+steps[i].op_time+"</div>" +
						 		"<div class='show1'>操作说明: "+steps[i].op_remark+"</div>" +
						 	"</div></div>" );
			 }else{
				 $td.append("<div class='clear'><div class='show0'>" +
						 		"<div class='show1'>操作步骤: "+steps[i].op_action+"</div>" +
						 		"<div class='show1'>操作人员: "+steps[i].op_user_name+"</div>" +
						 		"<div class='show1'>操作时间: "+steps[i].op_time+"</div>" +
						 		"<div class='show1'>操作说明: "+steps[i].op_remark+"</div>" +
						 	"</div>"); 
			 }
			 
		 } 
	 }
	
		$(".white_content").addClass("md-show");
		$("#light_v4,#tccontent_v4").show();
		var ligh=$(window).height()*0.8-155;
		$(".tankuang").css({"height":ligh+"px"});
		$("#fade_v4").show();
		var oneExe1=setTimeout(function(){
			$(".div.livemap-mark").addClass("hover");
		},1);
 }
$(function(){
 	$(".livemap-hostname").mouseover(function(){
		$(".white_content").addClass("md-show");
		$("#light_v4,#tccontent_v4").show();
		var ligh=$(window).height()*0.8-155;
		$(".tankuang").css({"height":ligh+"px"});
		$("#fade_v4").show();
		var oneExe1=setTimeout(function(){
			$(".livemap-hostname").addClass("hover");
		},1);
	});  
	$("#fade_v4,#t-close-btn-v4").click(function(){
		alert(111)
		$("#light_v4,#tccontent_v4,#fade_v4").hide();
		$("div.livemap-mark").removeClass("hover");
	});
	$("#light_v4").mouseleave(function(){
		$("#light_v4,#tccontent_v4,#fade_v4").hide();
		$("div.livemap-mark").removeClass("hover");
	}); 
}); 

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
	var $docs = $("#tccontent_v4").find("div.pro_head").children();
	$($docs).remove();
	$("#tccontent_v4").find("div.pro_head").css({'height':'100px'}).append("<div class='data_count' >查到共计"+rows.length+"台设备，详细信息如下表</div>" +
			"<table class='data_head'><thead><tr class='d_tr'>" +
						 		"<th align='center' style='width:20%;'>设备注册代码</th>" +
						 		"<th align='center' style='width:10%;'>二维码</th>" +
						 		"<th align='center' style='width:12%;'>操作步骤</th>" +
						 		"<th align='center' style='width:10%;'>操作人</th>" +
						 		"<th align='center' style='width:18%;'>操作时间</th>" +
						 		"<th align='center' style='width:30%;'>流程说明</th>" +
					 		"</tr></thead></table>");
	 //添加下方流程
	 $("div.progress table").children().remove();
	 $("div.progress table").addClass("data_body").append("<tbody></tbody>");
	 for(var i=0;i<rows.length;i++){
		 $("div.progress table tbody").append("<tr class='d_tr'>" +
			 		"<td align='center' style='width:20%;'>"+rows[i].DEVICE_REGISTRATION_CODE+"</td>" +
			 		"<td align='center' style='width:10%;'>"+(rows[i].DEVICE_QR_CODE!=null?rows[i].DEVICE_QR_CODE:'无')+"</td>" +
			 		"<td align='center' style='width:12%;'>"+rows[i].OP_ACTION+"</td>" +
			 		"<td align='center' style='width:10%;'>"+rows[i].OP_USER_NAME+"</td>" +
			 		"<td align='center' style='width:18%;'>"+rows[i].OP_TIME+"</td>" +
			 		"<td align='center' style='width:30%;'>"+rows[i].OP_REMARK+"</td>" +
		 		"</tr>");
	 }
	 $(".white_content").addClass("md-show");
		$("#light_v4,#tccontent_v4").show();
		var ligh=$(window).height()*0.8-155;
		$(".tankuang").css({"height":ligh+"px"});
		$("#fade_v4").show();
		var oneExe1=setTimeout(function(){
			$(".div.livemap-mark").addClass("hover");
		},1);
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
			}else{
				console.log("地址:"+device.DEVICE_USE_PLACE+",没有解析到结果!");
			}
		}, "四川省");
	}else{
		queryStar2(device,clss)
	}
	
}

function queryStar2(device,clss){
	if(clss.type == 'query'){
		var pt = new BMap.Point(device.LONGITUDE,device.LATITUDE);
		var marker2 = new BMap.Marker(pt);
		marker2.setLabel(new BMap.Label(device.DEVICE_USE_PLACE));
			//添加标注
			//setTimeout(function(){
				var myOverlay = new ComplexCustomOverlay(device,clss);
				map.addOverlay(myOverlay);
				clientQuery.push(myOverlay);
				//用于延时打开设备流程图
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
			 if(clss.box=="rtCacheOverlays"){
				 rtCacheOverlays[clss.cbox].push(myOverlay); 
			 }
			 if(clss.isNew){
				 rtOverlays.push(myOverlay);
			 }
	}
}

