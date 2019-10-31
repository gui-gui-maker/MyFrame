
			/*组合 */

			function RtGroup(rtLayer, primitives) {
				this.name="RtGroup";
				this.cname="组合";
				this.rtLayer = rtLayer; 
				this.cxt=rtLayer.cxt;
				//this.start=start;
				

			
				this.parent = new Primitive(this);
				
				this.primitives = new Array();	

				//添加到组合，区别于导入渲染时先创建RTGROUP再导入元素
				if(primitives){
					
					this.add(primitives);//求出this.zIndex	

					//以组合形式加入图层，按组合中最高ZINDEX元素位置
					//this.addToLayer=true;					
					
					this.rtLayer.primitives.splice(this.primitives[this.primitives.length-1].zIndex+1,0,this.parent);//将组合加入图层
					//this.moving = false;
					//计算组合AABB
					//this.calcAABB();
					
					this.parent.stopDraw();

					//this.rtDrawer.history.add(new RtAction("draw",this));//加入历史记录	drawGroup
					
					/*
					//删除原RTLAYER中组合内元素
					for(var i in primitives){
						var p =primitives[i];
						primitives[i].rtLayer.remove(p);
						p.rtLayer=this.rtLayer;
					}*/
				}		
				
			}


			RtGroup.prototype = {
				//增加图元
				add : function(primitive) {

					if (Array.isArray(primitive)) {
						//for (var i = primitive.length-1;i>=0;i--) {
						for (var i in primitive) {
							var p = primitive[i];
							this.sortPush(p);
							p.buildGroup(this.parent);
							//this.centers.push(p.box.center.clone());
							
						}
					} else {
						this.sortPush(primitive);
						primitive.buildGroup(this.parent);
						
					}

				},
				sortPush:function(primitive){
					//按图层顺序，和图层元素顺序排序
					var layerZIndex=primitive.rtLayer.rtDrawer.layers.indexOf(primitive.rtLayer);
					primitive.rtLayer.zIndex=layerZIndex;
					var primitiveZIndex=primitive.rtLayer.primitives.indexOf(primitive); //由于会splice数组，需要重新获取zIndex
					primitive.zIndex=primitiveZIndex;//用于undo
					if(this.primitives.length>0){
						var i=this.primitives.length-1;
						//console.log(this.primitives[i].rtLayer.zIndex);
						//console.log(primitive.zIndex);
						while(i>=0&&this.primitives[i].rtLayer.zIndex>=primitive.rtLayer.zIndex&&this.primitives[i].zIndex>primitive.zIndex){
							this.primitives[i+1]=this.primitives[i];
							i--;
						}
						this.primitives[i+1]=primitive;
					}else{
						this.primitives.push(primitive);
					}
					var tp=this.primitives[this.primitives.length-1];
					this.rtLayer=tp.rtLayer;
				},
				
				//取消组合
				cancel : function() {
					//从层删除
					for (var i in this.primitives) {
						var p = this.primitives[i];
						p.history={};
						
						p.history.groups=p.groups;
						p.history.rtLayer=p.rtLayer;						
						p.history.zIndex=p.rtLayer.primitives.indexOf(p);//保留原zIndex
											
					}
					for (var i in this.primitives) {
						var p = this.primitives[i];
						p.rtLayer.remove(p);											
					}
					
					
					var zIndex=this.rtLayer.primitives.indexOf(this.parent);
					this.zIndex=zIndex;
					//设置本组合为不可见
					this.setVisiable(false);
					//从新层加入
					for (var i =0;i<this.primitives.length;i++) {
						var p = this.primitives[i];
						this.rtLayer.primitives.splice(this.zIndex+i+1,0,p);
						p.groups=null;	
						p.rtLayer=this.rtLayer;						
						p.zIndex=this.zIndex+i+1;
						p.visiable=true;
					}
					
/*
					this.setVisiable(false);
					this.disable();*/
					return this.primitives;

				},
					/*
				disableCancel:function(){
					this.setVisiable(true);
					//删除后插入的元素
					//this.rtLayer.primitives.splice(this.zIndex+1,this.primitives.length);
				}
				,
				enableCancel:function(){
					return this.cancel();
				}
				,*/
				//重绘
				redraw : function() {
					
					for (var i in this.primitives) {
						var p=this.primitives[i];
						//if(p.groupVisibable){
							this.primitives[i].redraw();
						//}						
					}
					/*
					for (var i in this.centers) {
						//var p=this.primitives[i];
						this.cxt.strokeStyle="blue";
						this.cxt.strokeText("new",this.centers[i].x,this.centers[i].y);					
					}
					*/
				},
				calcAABB : function() {
					//计算上下左右
					baseContextCalcLRTB(this.parent,this.primitives);					

				
					//包围盒
					var offset=2*6;//偏移量
					baseContextInitBox(this.parent,offset);
				},
				//移动
				moveTo : function(dx,dy,moveMatrix) {
					
					for (var i = 0; i < this.primitives.length; i++) {
						var p = this.primitives[i];
						p.moveTo(dx,dy);
					}
				},
				//是否选中
				hasSelect : function(x, y) {
					var selectedBoxFunc;
					/*
					if(this.drawingLayer.insideGroupMode){
						return this.hasInSideGroupSelect(x,y);
						
					}*/
					if(this.aabb){
						if(this.drawingLayer.visiable&&(this.drawingLayer.selectedPrimitives.indexOf(this)>-1||this.drawingLayer.insideGroup==this)){
							//选中的情况下判定
							selectedBoxFunc=this.aabb.hasSelect(x, y);
							if (selectedBoxFunc) {
								this.drawingLayer.selectedBoxFunc=selectedBoxFunc;				
								return selectedBoxFunc;
							}				
						}
						
					}

					return this.hasDataSelect(x,y);

				
				},
			
				hasDataSelect:function(x,y){			
					return baseContextHasSelect(this.parent,x,y);
				},
				hasBoxSelect : function(box) {		
					for (var i in this.primitives) {
						var p=this.primitives[i];
						if (!p.hasBoxSelect(box)) {						
							return false;
						}
					}
					return this;
					
				},
					/*
				//选中
				select : function() {
					
					if(!this.aabb){
						this.aabb = new AABB(this);
					}
					if(this.group){
						this.groupVisiable=true;
					}
					this.drawingLayer.add(this);					
					
				},
				*/
				scaleTo: function(scaleMatrix,scaleMatrixTemp){
					this.tempGroupScaleTo(scaleMatrix,scaleMatrixTemp);//组合内部元素拉伸，如果角度不一致，需要每个单独计算
				
				}
				,
				tempGroupScaleTo:function(scaleMatrix,scaleMatrixTemp){
					for (var i in this.primitives) {
						var p = this.primitives[i];
						var sr=p.finalRotate;
						if(sr&&!equalApproximat(sr,this.finalRotate)){//组合内外元素角度是否相等
							
							if(!p.groups.center){
								p.groups.center=p.box.center.clone();	//非常重要							
							}							
							p.groups.center=MatrixMultiplyPoint(p.groups.center,scaleMatrix);						
							
							//旋转到X平行后 拉伸 再旋转回来
							var c=rotateScale(p.finalCos,p.finalSin,p.box.center,scaleMatrixTemp);//rtutils.js
							//移动回来,不是很优雅的一个解决方案，会发生抖动 待完善 ZQ ADD 20180715
							var b2=[[1,0,0],[0,1,0],[p.groups.center.x-p.box.center.x,p.groups.center.y-p.box.center.y,1]];										
							var scaleMatrix2=MatrixMultiply(c,b2);							 
							p.groupScaleTo(scaleMatrix2,scaleMatrixTemp);
						}else{
							p.groupScaleTo(scaleMatrix);
						}
						
					}
				},
				groupScaleTo :function(scaleMatrix,scaleMatrixTemp){
					this.groupVisable=false;
					this.tempGroupScaleTo(scaleMatrix,scaleMatrixTemp);//组合内部元素拉伸，如果角度不一致，需要每个单独计算

				},
				rotateTo : function(rotateMatrix){
					
					for (var i in this.primitives) {
						var p = this.primitives[i];
						p.groupRotateTo(rotateMatrix);
					}

				},
				groupRotateTo :function(rotateMatrix){
					this.groupVisable=false;		

					for (var i in this.primitives) {
						var p = this.primitives[i];
						p.groupRotateTo(rotateMatrix);
					}
						
				},
				stopMove : function() {
					

					for (var i in this.primitives) {
						var p = this.primitives[i];
						p.stopMove();
					}
					//计算上下左右
					baseContextCalcLRTB(this.parent,this.primitives);	

					

				},
				
				disable:function(){
					this.setVisiable(false);
					for (var i in this.primitives) {
						var p = this.primitives[i];
						p.cancelGroup();
					}
					return this.primitives;
					//this.rtDrawer.redraw();
				}
				,
				enable:function(){
					this.setVisiable(true);
					for (var i in this.primitives) {
						var p = this.primitives[i];
						 p.buildGroup(this.parent);
					}
					//this.calcAABB();
					//this.drawingLayer.create(this);
				},
				setVisiable:function(flag){
					this.parent.setVisiable(flag);
				},
				
			
				copy : function() {					
					var copyGroupData=new RtGroup(this.rtLayer);
					copyGroupData.primitives=new Array();
					for(var i in this.primitives){
						var entity=this.primitives[i];
						var copyData=entity.copy();
						copyGroupData.primitives.push(copyData);
						copyData.buildGroup(copyGroupData.parent);
					}
					
					//copyGroupData.calcAABB();

					return copyGroupData;
				},
				groupCopybak:function(obj,newObj){
					for(var i in obj.primitives){
						var entity=primitives[i];
						if(entity instanceof RtGroup){
							var rtGroup=new RtGroup(this.rtLayer);
							this.groupCopy(entity,rtGroup);

						}else if(entity instanceof Primitive){							
							var copyData=entity.copy();
							newObj.add(copyData);
						}
					
					}
				},
				strokeColor:function(color){					
					for(var i in this.primitives){
						this.primitives[i].strokeColor(color);				
					}					
				} 
				,
			
				fillColor:function(color){					
					for(var i in this.primitives){
						this.primitives[i].fillColor(color);				
					}					
				},
				setLineWidth:function(width){
					for(var i in this.primitives){
						this.primitives[i].setLineWidth(width);				
					}	
				},
				setFontStyle:function(fontStyle){
					for(var i in this.primitives){
						this.primitives[i].setFontStyle(fontStyle);				
					}	
				},
				setLineDashType:function(lineDash){
					for(var i in this.primitives){
						this.primitives[i].setLineDashType(lineDash);				
					}	
				},
				
				leftAlign:function(point,moveMatrix){
					
					for(var i in this.primitives){
						this.primitives[i].leftAlign(point,moveMatrix);				
					}
					
					
				},
				rightAlign:function(point,moveMatrix){
					
					for(var i in this.primitives){
						this.primitives[i].rightAlign(point,moveMatrix);				
					}
				}
				,
				topAlign:function(point,moveMatrix){			
					
					for(var i in this.primitives){
						this.primitives[i].topAlign(point,moveMatrix);				
					}		
				},
				bottomAlign:function(point,moveMatrix){
					
					for(var i in this.primitives){
						this.primitives[i].bottomAlign(point,moveMatrix);				
					}
				},
				horizontallyAlign:function(point,moveMatrix){
					
					for(var i in this.primitives){
						this.primitives[i].horizontallyAlign(point,moveMatrix);				
					}
						
				},
				verticallyAlign:function(point,moveMatrix){
					
					for(var i in this.primitives){
						this.primitives[i].verticallyAlign(point,moveMatrix);				
					}
				},
				centerAlign:function(point,moveMatrix){
					
					for(var i in this.primitives){
						this.primitives[i].centerAlign(point,moveMatrix);				
					}
					
				},
				// ZQ ADD 20190310
				keyMove:function(moveMatrix){
					
					for(var i in this.primitives){
						this.primitives[i].keyMove(moveMatrix);				
					}
					
				},
				save:function(){
					var primitives= new Array();
					for(var i in this.primitives){
						var p=this.primitives[i];
						var primitive=p.save();
						primitives.push(primitive);
						
					}
					return primitives;
				},
				imp:function(primitives){

					for(var i in primitives){
						var  p = new Primitive();
						var primitive=primitives[i];
						p=p.imp(this.rtLayer,primitive);
						p.buildGroup(this.parent);
						this.primitives.push(p);
					}
				}
			};
