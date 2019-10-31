
	var windowPoint=null;
	
	function windowToCanvas(x, y) {
       
	   var bbox = canvas.getBoundingClientRect();
	   windowPoint={x:x,y:y};
	  // console.log("windowToCanvas bbox x:"+(x - bbox.left * (canvas.width  / bbox.width))+", y:"+(y - bbox.top  * (canvas.height / bbox.height)));
	   return new Point((x - bbox.left * (canvas.width  / bbox.width)),(y - bbox.top  * (canvas.height / bbox.height)));
	}



	// canvas event handlers.................................

	canvas.onmousedown = function (e) {
	e = window.e || e;
	   var x = e.x || e.clientX,
		   y = e.y || e.clientY,
		   loc = windowToCanvas(x, y);

	   e.preventDefault(); 

	   mouseDownOrTouchStart(loc);
	}

	canvas.ontouchstart = function (e) { 
		var loc;
	   if (e.touches.length === 1) {
		  e.preventDefault();
		  loc = windowToCanvas(e.changedTouches[0].clientX, e.changedTouches[0].clientY);
		  mouseDownOrTouchStart(loc);
	   }
	}

	canvas.onmousemove = function (e) {
	   var x = e.x || e.clientX,
		   y = e.y || e.clientY,
		 loc = windowToCanvas(x, y);

	   e.preventDefault();
	   mouseMoveOrTouchMove(loc);
	}

	canvas.ontouchmove = function (e) { 
	   var loc;
	   if (e.touches.length === 1) {
		   e.preventDefault();
		  loc = windowToCanvas(e.changedTouches[0].clientX, e.changedTouches[0].clientY);
		  mouseMoveOrTouchMove(loc);
	   }
	}

	canvas.onmouseup = function (e) {
	   var x = e.x || e.clientX,
		   y = e.y || e.clientY,
		 loc = windowToCanvas(x, y);

	   e.preventDefault();
	   mouseUpOrTouchEnd(loc);
	}

    canvas.ontouchend = function (e) { 
	   var loc;	   
	   if (e.changedTouches.length === 1) {
		   e.preventDefault();
		  loc = windowToCanvas(e.changedTouches[0].clientX, e.changedTouches[0].clientY);
		  mouseUpOrTouchEnd(loc);
	   }
	}

	canvas.onmousemout = function (e) {
	   var x = e.x || e.clientX,
		   y = e.y || e.clientY,
		 loc = windowToCanvas(x, y);

	   e.preventDefault();
	   mouseUpOrTouchEnd(loc);
	}

	canvas.ontouchcancel=function(e){
		alert("未处理");
		
	}

	// event handlers.................................

	
	function moveControlPoint(loc) {
	   controlPoint.x = loc.x;
	   controlPoint.y = loc.y;

	}

	function testIsInsideGroup(primitives){				
		var pLength=primitives.length;
		if(pLength<=0){
			return false;
		}else if(pLength==1){
			if(rtDrawer.drawingLayer.insideGroupMode){
				if(!primitives[0].groups||primitives[0].groups.group!=rtDrawer.drawingLayer.insideGroup){
					return false;
				}
				return true;
			}else{
				if(primitives[0].type=="RtGroup"){
					return true;				
				}else{
					return false;
				}
			}
			
		}else{
			for(var i=0;i<pLength;i++){
				if(!primitives[i].groups||primitives[i].groups.group!=rtDrawer.drawingLayer.insideGroup){
					return false;
				}
			}				
			return true;
		}
		
		 
	}

	var startPoint;

	function mouseDownOrTouchStart(loc) {
		
		console.log("mouseDownOrTouchStart ..............");
	   drawDragging = true; 
	   var pointer = rtDrawingLayer;
	  
	   if(selectedFunction=="hand"){
             console.log("drawWriting:"+drawWriting);
			 if(rtDrawer.drawingLayer.visiable){
			  // console.log("touck 11");
			   var primitives=rtDrawer.drawingLayer.selectedPrimitives;
			   if(testIsInsideGroup(primitives)){
				   var primitive;
				   if(rtDrawer.drawingLayer.insideGroupMode){
						primitive=rtDrawer.drawingLayer.insideGroup.hasInSideGroupSelect(loc.x,loc.y);
						GROUP_INSIDE_GROUP=rtDrawer.drawingLayer.insideGroup;
				   }else{
						primitive=primitives[0].hasInSideGroupSelect(loc.x,loc.y);
						GROUP_INSIDE_GROUP=primitives[0];
				   }
				   
				   if(primitive){
					    //rtDrawer.drawingLayer.insideGroupMode=true;//组合内部模式
						//rtDrawer.drawingLayer.insideGroup=primitives[0];//内部模式的组合元素
						//rtDrawer.drawingLayer.select(primitive);
						
						GROUP_INSIDE_PRIMITIVE=primitive;
						GROUP_INSIDE_BEGIN=true;//准备进入组合内部模式，移动后失效
						
						startPoint=loc;

					
						handMoveDrag="drag";

						return;
				   }else{
						//if(rtDrawer.drawingLayer.insideGroupMode){
							//  console.log("touck 33");
							
						//	rtDrawer.drawingLayer.create(rtDrawer.drawingLayer.selectedPrimitives[0]);
						// }					  
				   
				   }				
			   }
		  }
		console.log("22drawWriting:"+drawWriting);
			
			if(drawWriting){
				var textControls=document.getElementById("textControls");
				//var text=document.getElementById("textControl").value;
				//console.log("mouseDownOrTouchStart:"+text);
				drawInstance.draw(loc);
				drawInstance.parent.stopDraw();
				textControls.style.display="none";	
					console.log("textControls.style.display=none");
				document.getElementById("textControl").value="";
				drawWriting=false;
				drawInstance=null;
				rtDrawer.redraw();
			}else{
				startPoint=loc;

				
				var primitive=pointer.hasSelect(loc.x,loc.y);
				if (!primitive) { //鼠标选择
					//未有被选择的图元
					pointer.disabled();
					pointer.canvas.style.cursor = "default";
					//没有选中的图元后，鼠标移动判定是否框选
					 
					drawInstance =new RtBoxSelect(pointer,loc); 
					pointer.addAssist(drawInstance); 
					handMoveDrag="undrag";
					
					//鼠标未选择
					EventHub.emit("RtComponentsList.selectNone");
				} else {
					//if(pointer.visiable){
						pointer.select(primitive);
				  //	}
				  //else{
					//	primitive.select();
					//}
									
					if(!pointer.selectedBoxFunc){
						
						pointer.selectedBoxFunc=primitive.aabb.rangeRect;
						pointer.movingSelectType=pointer.selectedBoxFunc.type;
						pointer.canvas.style.cursor = "move";				 
					}
					
					
					//if(pointer.selectedPrimitives.length>1){
						//pointer.drawingGroupAction=new RtAction("temp",pointer.selectedPrimitives);//加入历史记录
					//}
					handMoveDrag="drag";
					
				}

			}

			rtDrawer.drawingLayer.insideGroupMode=false;//组合内部模式
			rtDrawer.drawingLayer.insideGroup=null;//内部模式的组合元素
			
			
			handMoveType=null;
			//console.log("mouseDownOrTouchStart handMoveType:"+handMoveType);
	   }else{
			if(drawWriting){
				var textControls=document.getElementById("textControls");
				//var text=document.getElementById("textControl").value;
				//console.log("mouseDownOrTouchStart:"+text);
				drawInstance.draw(loc);
				drawInstance.parent.stopDraw();
				textControls.style.display="none";	
				console.log("222textControls.style.display=none");
				document.getElementById("textControl").value="";
				drawWriting=false;
				drawInstance=null;
				rtDrawer.redraw();
			}else{
			
			rtDrawingLayer.disabled(); 
			drawInstance=new selectedFunction(rtLayer, loc, true);
			drawInstance.draw(loc);

			}

		   
			
		   
			/*
		    var textControls=document.getElementById("textControls");
			if(textControls.style.display=="block"){
				var text=document.getElementById("textControl").value;
				console.log("mouseDownOrTouchStart:"+text);
				drawInstance.text=text;
				textControls.style.display="none";	
				drawInstance=null;
			}else{
				rtDrawingLayer.disabled(); 
				drawInstance=new selectedFunction(rtLayer, loc, true);		
			}
		*/
	   }	
 
	}

	function mouseMoveOrTouchMove(loc) {
	//	console.log("mouseMoveOrTouchMove ..............");
		var pointer=rtDrawingLayer;
	    if(selectedFunction=="hand"){
			//console.log("mouseMoveOrTouchMove:"+handMoveDrag);
			if(GROUP_INSIDE_BEGIN){
				if(!(loc.x==startPoint.x&&loc.y==startPoint.y)){
					
					GROUP_INSIDE_GROUP=null;
					GROUP_INSIDE_PRIMITIVE=null;
					GROUP_INSIDE_BEGIN=false;//进入组合内部模式失效
				}
			}
				
			if(handMoveDrag=="undrag"){
				pointer.canvas.style.cursor = "default";
				if(drawDragging&&drawInstance){								
					drawInstance.draw(loc);
					rtDrawer.redraw();
				}else{
					//console.log("mouseMoveOrTouchMove: drawDragging ："+drawDragging);
					
					var p=pointer.hasSelect(loc.x,loc.y);
					//console.log("mouseMoveOrTouchMove: p ："+p);
					if (p) { //鼠标选择
						
						//console.log("选中");

						
							if(pointer.selectedBoxFunc){
								pointer.movingSelectType = pointer.selectedBoxFunc.type;
								//console.log("pointer.movingSelectType :"+pointer.selectedBoxFunc.name);
								if (pointer.movingSelectType == "move") {
									pointer.canvas.style.cursor = "move";
								} else if (pointer.movingSelectType == "rotate") {
									pointer.canvas.style.cursor = "pointer";
								} else if (pointer.movingSelectType =="scale") {
									pointer.canvas.style.cursor = pointer.selectedBoxFunc.cursor;//AABB中已设置鼠标样式
								} else if (pointer.movingSelectType == "select") {
									pointer.canvas.style.cursor = "move";
								} else {
									pointer.canvas.style.cursor = "default";
									pointer.movingSelectType = false;
									//console.log();
								}
							
							}else{
								pointer.canvas.style.cursor = "move";
								pointer.movingSelectType = false;
							}
												
						
						
					}else{
						//鼠标未选择
					}
				}
				/*
				var p=pointer.hasSelect(loc.x,loc.y);
				if (p) { //鼠标选择
					
					//console.log("选中");

					
						if(pointer.selectedBoxFunc){
							pointer.movingSelectType = pointer.selectedBoxFunc.type;
							//console.log("pointer.movingSelectType :"+pointer.selectedBoxFunc.name);
							if (pointer.movingSelectType == "move") {
								pointer.canvas.style.cursor = "move";
							} else if (pointer.movingSelectType == "rotate") {
								pointer.canvas.style.cursor = "pointer";
							} else if (pointer.movingSelectType =="scale") {
								pointer.canvas.style.cursor = pointer.selectedBoxFunc.cursor;//AABB中已设置鼠标样式
							} else if (pointer.movingSelectType == "select") {
								pointer.canvas.style.cursor = "move";
							} else {
								pointer.canvas.style.cursor = "default";
								pointer.movingSelectType = false;
								//console.log();
							}
						
						}else{
							pointer.canvas.style.cursor = "move";
							pointer.movingSelectType = false;
						}
											
					
					
				} else {
					pointer.canvas.style.cursor = "default";
					//console.log("没选中");
					if(drawDragging&&drawInstance){
						drawInstance.draw(loc);
						rtDrawer.redraw();
				   }

					
				}*/
			}else if(handMoveDrag=="drag"){

				  
				   if(loc.x==startPoint.x&&loc.y==startPoint.y){
						return;
				   }
					


					//伸缩
					var primitive=pointer.selectedBoxFunc.primitive;
					if (pointer.movingSelectType == "move") {
							pointer.moving=true;
							var dx=loc.x-startPoint.x;
							var dy=loc.y-startPoint.y;
							var primitive=pointer.selectedBoxFunc.primitive;
							var moveMatrix=primitive.moveTo(dx,dy);
							//console.log(" begin resize all");
							if(pointer.selectedPrimitives.length>1&&moveMatrix){
								for (var i in pointer.selectedPrimitives) {
									var p = pointer.selectedPrimitives[i];
									if(p!=primitive){
										if(pointer.insideGroupMode&&p==pointer.insideGroup){
											continue;
										}
										p.moveTo(dx,dy);
									}
								}

								
							}
							startPoint.x = loc.x;
							startPoint.y = loc.y;
					} else if (pointer.movingSelectType == "rotate") {
						//旋转
						pointer.moving=true;
						var angel=primitive.rotateTo(loc.x,loc.y);
						if(pointer.selectedPrimitives.length>1&&angel){
							for (var i in pointer.selectedPrimitives) {
								var p = pointer.selectedPrimitives[i];
								if(p!=primitive){
									if(this.insideGroupMode&&p==this.insideGroup){
										continue;
									}
									p.rotateTo(loc.x,loc.y,angel);
								}											
							}
							
						}
						

					}  else if (pointer.movingSelectType  == "scale") {	
						pointer.moving=true;
						var cursor=pointer.selectedBoxFunc.cursor;
						var offset=primitive.scaleTo(cursor,loc.x,loc.y);
						if(pointer.selectedPrimitives.length>1&&offset){
							for (var i in pointer.selectedPrimitives) {
								var p = pointer.selectedPrimitives[i];
								if(p!=pointer.selectedBoxFunc.primitive){
									if(this.insideGroupMode&&p==this.insideGroup){
										continue;
									}
									p.scaleTo(cursor,loc.x, loc.y,offset);
								}
							}
							
						}
					} else {
						
						console.log("data selected ,by aabb select error..");
					}
					handMoveType=pointer.movingSelectType;
					//console.log("handMoveType:"+handMoveType);
					 //拖动
					 //1）按下CTRL 进行复制拖动
					 //2）未按下CTRL 进行拖动
					// console.log("move");

				
					pointer.rtDrawer.redraw();
			}

			
			if(!rtDrawingLayer.visiable){
						   
		   }else{

			 
		   }
		   
	   }else{
		   //console.log("move drawInstance ..."+drawInstance);
		   if(drawDragging&&drawInstance){
			    
				drawInstance.draw(loc);
				rtDrawer.redraw();
				if(GUIDEWIRES) {
					 drawGuidewires(loc.x, loc.y);
				}
		   }
			
	   }	
	};

	
	function mouseUpOrTouchEnd(loc) {	 
		
	   drawDragging = false;
	   var pointer=rtDrawingLayer;
	   if(selectedFunction=="hand"){
		  
		   if(!pointer.visiable){
				if(drawInstance){
					pointer.resetAssist();
					rtDrawer.redraw();
					if(pointer.boxSelect(startPoint,loc)){							
						
					}else{
						
					}	
					
				}		   
		   }else{
			 if(handMoveType){	
				// console.log("mouseUpOrTouchEnd handMoveType:"+handMoveType);
			   pointer.stopMove(pointer.movingSelectType );		 
			 }else{
				   
				  //...
				if(GROUP_INSIDE_BEGIN){
					rtDrawer.drawingLayer.insideGroupMode=true;//组合内部模式
					rtDrawer.drawingLayer.insideGroup=GROUP_INSIDE_GROUP;//内部模式的组合元素
					rtDrawer.drawingLayer.select(GROUP_INSIDE_PRIMITIVE);
					console.log("rtDrawer.drawingLayer.insideGroupMode:"+rtDrawer.drawingLayer.insideGroupMode);
					console.log("rtDrawer.drawingLayer.insideGroup:"+rtDrawer.drawingLayer.insideGroup);
					GROUP_INSIDE_MODE=null;
					GROUP_INSIDE_BEGIN=true;//准备进入组合内部模式，移动后失效
				}
			 
			 }		 
		   }
		   handMoveDrag="undrag";
		   handMoveType=null;		   
		   
	   }else{
		   if(drawInstance){
			   if(drawInstance.parent){
				   drawInstance.parent.stopDraw(loc); //自主图形类没有primitive parent
			   }else{
				    drawInstance.stopDraw(loc); //自主图形类
			   }
				
				rtDrawer.redraw();
				
		   }
			
	   }
	  if(selectedFunction.name=="RtText"){
		 cancelDraw();
	   }
	   
	  //drawInstance=null;
	  
	};

// Guidewires.........................................................

function drawHorizontalLine (y) {
   var context=rtDrawer.cxt;
   context.beginPath();
   context.moveTo(0,y+0.5);
   context.lineTo(context.canvas.width,y+0.5);
   context.stroke();
}

function drawVerticalLine (x) {
   var context=rtDrawer.cxt;
   context.beginPath();
   context.moveTo(x+0.5,0);
   context.lineTo(x+0.5,context.canvas.height);
   context.stroke();
}

function drawGuidewires(x, y) {
   var context=rtDrawer.cxt;
   context.save();
   context.strokeStyle = 'rgba(0,0,230,0.4)';
   context.lineWidth = 0.5;
   drawVerticalLine(x);
   drawHorizontalLine(y);
   context.restore();
}