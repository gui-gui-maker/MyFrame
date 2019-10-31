/**
			 * 箭头
			 */
			function RtArrow(rtLayer, start, addToLayer) {
				this.name="RtArrow";
				this.cname = "单向箭头";
				this.rtLayer = rtLayer; 
				this.cxt=rtLayer.cxt;
				this.start=new Point(start.x,start.y);
				this.parent = new Primitive(this, addToLayer);

				this.points = new Array();
				

				//this.box.nw=this.start;
				//this.box.se=new Point(this.start.x,this.start.y);
				this.theta=15;  //三角斜边一直线夹角
				this.headlen=20;//三角斜边长度 

			}


			RtArrow.prototype = {
				draw:function(end){
					this.end=this.parent.end;			
					this.end.x=end.x;
					this.end.y=end.y;

					this.points=new Array();	
					this.points.push(this.start.clone());
					this.points.push(this.end.clone());
				
				},
				
				redraw : function() {	
					
					
					this.assistDraw(this.cxt, this.points[0].x, this.points[0].y, this.points[1].x,this.points[1].y);
					 
					
			
				}
				,
				assistDraw: function(ctx, fromX, fromY, toX, toY) { 
					
					var angle = Math.atan2(fromY - toY, fromX - toX) * 180 / Math.PI, 
						angle1 = (angle + this.theta) * Math.PI / 180, 
						angle2 = (angle - this.theta) * Math.PI / 180, 
						topX = this.headlen * Math.cos(angle1), 
						topY = this.headlen * Math.sin(angle1), 
						botX = this.headlen * Math.cos(angle2), 
						botY = this.headlen * Math.sin(angle2); 
			
					ctx.beginPath(); 
					var arrowX = fromX - topX, arrowY = fromY - topY; 
					//ctx.moveTo(arrowX, arrowY); 

					ctx.moveTo(fromX, fromY); 					
					ctx.lineTo(toX, toY); 
					arrowX = toX + topX;
					arrowY = toY + topY;										 
					ctx.moveTo(arrowX, arrowY); 
					ctx.lineTo(toX, toY); 
					var barrowX = toX + botX; 
				    var barrowY = toY + botY;
					ctx.lineTo(barrowX, barrowY); 
					ctx.closePath();
					ctx.stroke();	
					
									
					ctx.fill();
				}
				,
				
				calcAABB : function() {
						//上下左右
						baseContextCalcDataLRTB(this.parent,this.points);		
						//包围盒			
						baseContextInitLineBox(this.parent);

						this.box=this.parent.box;
				},
				hasSelect:function(x,y){ 
					this.box=this.parent.box;
					return isInLine(new Point(x,y),this.box.nw,this.box.se);				 
					 
				},
				hasBoxSelect:function(box){
					this.box=this.parent.box;
					//判断线在矩形中
					if(isInPolygon(this.box.nw,box)&&isInPolygon(this.box.se,box)){
						return true;
					}
					return false;				 
					 
				},
				scaleTo:function(functionPoint,point){
					this.box=this.parent.box;
					if(point){
						//this.transformType="point";//按点拖动
						functionPoint.x=point.x;
						functionPoint.y=point.y;
					}
					//修复center  ,important 
					this.box.center.x=this.box.se.x+(this.box.nw.x-this.box.se.x)/2;
					this.box.center.y=this.box.se.y+(this.box.nw.y-this.box.se.y)/2;			

					this.transform();
				},

				transform:function(matrix){					
					
					this.points=new Array();					
					this.points.push(this.parent.box.nw.clone());
					this.points.push(this.parent.box.se.clone());	
					//console.log("RtLine transform this.box.nw:  x:"+this.box.nw.x+",y:"+this.box.nw.y);
				},
				clone : function() {
					
					var cloneData=new RtLine(this.rtLayer,this.start,false);
					cloneData.box=baseContextBoxClone(this.box);
					cloneData.obox=baseContextBoxClone(this.obox);
					baseContextClone(this,cloneData);

					return cloneData;
				},
				undo:function(matrix,points){
					
					baseContextLineUndoRedo(this,points);
					if(this.parent.aabb){
						this.parent.aabb.refresh();
					}
				},
				redo:function(matrix,points){
					baseContextLineUndoRedo(this,points);	
					if(this.parent.aabb){
						this.parent.aabb.refresh();
					}
				},
				copy : function() {
					
					var start=this.points[0].clone();
					var end=this.points[1].clone();
					
					var copyData = new RtArrow(this.rtLayer,start);
					copyData.end = end;
					
					copyData.points=new Array(); 
					copyData.points.push(start);
					copyData.points.push(end);

					copyData.ostart=start.clone();
					copyData.oend=end.clone();

					
					return copyData;
				} ,
				imp:function(data){				
					
					this.start=new Point(data.points[0].x,data.points[0].y);
					this.end=new Point(data.points[1].x,data.points[1].y);
					this.parent.start=this.start;
					this.parent.end=this.parent.end;

					this.points=new Array();
					this.points.push(this.start);
					this.points.push(this.end);
					

					this.ostart=this.start.clone();
					this.oend=this.end.clone();		
					
				}
			};
