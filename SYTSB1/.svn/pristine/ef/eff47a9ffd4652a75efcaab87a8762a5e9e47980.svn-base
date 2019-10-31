			/*AABB包围盒 */
			function AABB(primitive,addToLayer) {
				//function AABB(rtDrawingLayer, primitive, addToLayer) {
				this.name = "AABB";

				this.primitive = primitive;
				this.box=this.primitive.box;
				this.center=this.primitive.center;
								

				this.rtLayer=this.primitive.rtLayer;				
				

				
				this.addToLayer = addToLayer;
				//是否添加到层
				baseContext.call(this, this.rtLayer);

				
				this.lineWidth = 1;
				this.functionRectWidth = 6;//伸缩矩形宽度
				this.rotateRectWidth = 20;//旋转矩形宽度
				this.rotateRectHeight = 18;//旋转矩形高度

				//大矩形
				this.rangeRect = null;
				//八个小矩形				
				this.functionRects = null;
				this.cursorFunctionRects={};
				//旋转功能
				this.rotateRect = null;
				
				
				
				//是否移动中
				//this.moving = false;
				this.isMoved=false;//是否移动过

				this.init(); //初始化

			

			}


			AABB.prototype = {
				init : function() {
					
					//初始化包围盒类型
					this.initBoxType();
					
					//初始化大矩形
					this.initRangeRect();	

					//初始化伸缩小矩形
					this.initFunctionRects();	

					if(this.boxType!="line"){							
						
						//初始化旋转图形
						this.initRotateRect();
					}
							
					
				},
				initBoxType:function(){
					//if(this.primitive.type=="RtLine"){//||this.primitive.type=="RtArrow"
					if(baseContextIsLinePrimitive(this.primitive.type)){
						this.boxType="line";
					}
				},
				//外面那个大矩形
				initRangeRect : function() {

					
					var rtRectAABB = new RtBoxRange(this);	
					this.rangeRect = rtRectAABB;
					
					//还原直线
				},
				//大矩形周围八个小功能方形
				initFunctionRects : function() {
					this.functionRects = new Array();				

					var rectArray = [{
						cursor : 'nw-resize',
						name:"nw",
						start: this.box.nw, 
						cross: this.box.se,
						boxType:"line"
					}, {
						cursor : 'n-resize',
						name:"n",
						start: this.box.n,
						cross:this.box.s

					}, {
						cursor : 'ne-resize',
						name:"ne",
						start: this.box.ne,
						cross:this.box.sw
					}, {
						cursor : 'e-resize',
						name:"e",
						start: this.box.e,
						cross:this.box.w
					}, {
						name:"se",
						cursor : 'se-resize',
						start: this.box.se,
						cross:this.box.nw,
						boxType:"line"
					}, {
						cursor : 's-resize',
						name:"s",
						start: this.box.s,
						cross:this.box.n
					}, {
						cursor : 'sw-resize',
						name:"sw",
						start: this.box.sw,
						cross:this.box.ne
					}, {
						cursor : 'w-resize',
						name:"w",
						start: this.box.w,
						cross:this.box.e
					}];
					 
				
					//画八个伸缩小矩形
					for (var i in rectArray) {
						var rectData = rectArray[i];
						if(this.boxType=="line"&&rectData.boxType!="line"){
							continue;
						}
						
						var functionRect = new RtBoxFunc(this,rectData.start, this.functionRectWidth, this.functionRectWidth,"scale");
						

						for(var j in rectData){
							functionRect[j]=rectData[j];
						}						
						
						functionRect.drawType = "fillAndStroke";
						this.cursorFunctionRects[functionRect.cursor]=functionRect;
						//ZQ EDIT 20180920 用于导入
//						if(this.primitive.finalRotate){
//							functionRect.refresh(this.primitive.finalRotate);
//						}
						this.functionRects.push(functionRect);
						
					}

					this.cxt.strokeStyle = this.strokeStyle;
					this.cxt.fillStyle = this.fillStyle;
				 
				},
				initRotateRect : function() {				

					var rotateRect = new RtBoxRotate(this, this.box.n, this.rotateRectWidth, this.rotateRectHeight,"rotate");

					rotateRect.rotateCenter=this.primitive.center;
					
					rotateRect.cursor = "pointer";
					//ZQ EDIT 20180920 用于导入
//					if(this.primitive.finalRotate){
//						rotateRect.refresh(this.primitive.finalRotate);
//					}
					this.rotateRect = rotateRect;
				},
				
				redraw : function() {
					
					//console.log("box aabb redraw..");
					//if(this.primitive.visiable&&(!this.primitive.group||(this.primitive.group&&this.primitive.group))){
					//if(this.primitive.visiable){	
					if(this.primitive.statusTest())	{
					
						for (var i in this.functionRects) {
							//this.functionRects[i].redraw();
							var r=this.functionRects[i];						
							r.redraw();
						}
						this.rangeRect.redraw();

						if (this.rotateRect) {
							//console.log("aabb redraw rotate redraw");
							this.rotateRect.redraw();
						}
					}
					

				},
				stopMove : function() {
					//this.moving = false;
					//console.log("box aabb stopMove .. ");
					if(this.rotateRect){
						this.rotateRect.enable();
					    //if(!this.primitive.group||(this.primitive.group&&this.primitive.groupVisiable)){
						//if(this.primitive.visiable&&(!this.primitive.group||(this.primitive.group&&this.primitive.group))){	
						if(this.primitive.statusTest()){	
							console.log("stopMove rotate redraw");
							this.rotateRect.redraw();
						}		
					}
					
					
				},
				hasSelect:function(x,y){
					if(this.hasSelectFunctionRect(x,y)){
						return this.selectedBoxFunc;
					}else if(this.hasSelectRotateRect(x,y)){
						return this.selectedBoxFunc;
					}else if(this.hasSelectRangeRect(x,y)){
						return this.selectedBoxFunc;
					}

					return false;
				}
				,
				hasSelectRangeRect:function(x,y){
					if(this.rangeRect.hasSelect(x,y)){
						this.selectedBoxFunc=this.rangeRect;
						return true;	
					}	
					return false;
				},
				hasSelectFunctionRect : function(x, y) {
					for (var i in this.functionRects) {
						var rect = this.functionRects[i];
                       	if(rect.hasSelect(x,y)){
							//重置光标							
												
							this.selectedBoxFunc = rect;
							return true;	
						}
					}

					return false;
				},
				hasSelectRotateRect : function(x, y) {
					if(this.rotateRect&&this.rotateRect.hasSelect(x,y)){
					   this.selectedBoxFunc = this.rotateRect;
					   return true;	
					}
					return false;
				},
				resetCursor:function (r){				
					
					if(r!=0){
						var angle=r*180/Math.PI;
						// console.log("angle:"+(angle));
						if(angle<0){
							angle=angle+360;
						}
						var angleStandard=[{min:345,max:15,p:"n-resize"},{min:15,max:75,p:"ne-resize"},{min:75,max:105,p:"e-resize"},{min:105,max:165,p:"se-resize"},{min:165,max:195,p:"s-resize"},{min:195,max:255,p:"sw-resize"},{min:255,max:285,p:"w-resize"},{min:185,max:345,p:"nw-resize"},
						{min:345,max:15,p:"n-resize"},{min:15,max:75,p:"ne-resize"},{min:75,max:105,p:"e-resize"},{min:105,max:165,p:"se-resize"},{min:165,max:195,p:"s-resize"},{min:195,max:255,p:"sw-resize"},{min:255,max:285,p:"w-resize"},{min:185,max:345,p:"nw-resize"}];
						var cursorFunctionRects={};
						for(var j=0;j<angleStandard.length;j++){							
							var a=angleStandard[j];							
							if((angle<a.max&&angle>=a.min)||((a.min>a.max)&&(angle<=360&&angle>=a.min||angle<a.max&&angle>=0))){								
							   for(var k=0;k<this.functionRects.length;k++){
									 if(j==0){
										j=angleStandard.length/2;
									 }								  
									this.functionRects[k].cursor=angleStandard[j+k-1].p;
									this.cursorFunctionRects[this.functionRects[k].cursor]=this.functionRects[k];
							   }
							   break;
							}
						}								
					}
				
				},
				getRotate:function(){
					
					var O=this.primitive.box.center;
					//console.log("rotate center:"+this.center.x+","+this.center.y);
					var B=this.primitive.box.n;					
					var A=new Point(O.x,O.y-1);
					var r=aobRotate(A,O,B);
					
					return r;	
				},
				refresh:function(){	
					if(this.rotateRect&&this.primitive.moving){
						this.rotateRect.disable();
					}

					if(this.boxType!="line"){
						
						var rotate=this.getRotate();					
						for (var i in this.functionRects) {
							 this.functionRects[i].refresh(rotate);
						}
						this.rotateRect.refresh(rotate);
						
						this.resetCursor(rotate);
					}else{
						for (var i in this.functionRects) {
							this.functionRects[i].refresh();
						}
					}
				},
				transform:function(matrix){
				
					//this.rangeRect.transform(matrix);
					
					for (var i in this.functionRects) {
						 this.functionRects[i].transform(matrix);
					}
					if(this.rotateRect){
						this.rotateRect.transform(matrix);
					}
					
				},
				moveTo : function(moveMatrix) {
					
					this.refresh();
					
				},
				//鼠标拖动伸缩变形
				scaleTo : function(scaleMatrix,start,point) {						
					this.refresh();	
										
				},
				rotateTo:function(){
					

					this.refresh();
				}
			};