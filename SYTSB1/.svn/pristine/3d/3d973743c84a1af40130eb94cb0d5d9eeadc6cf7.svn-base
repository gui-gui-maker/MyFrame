/**
			 * 直线
			 */
			function RtLine(rtLayer, start, addToLayer) {
				this.name="RtLine";
				this.cname = "直线";
				this.rtLayer = rtLayer; 
				this.cxt=rtLayer.cxt;
				this.start=new Point(start.x,start.y);
				this.parent = new Primitive(this, addToLayer);

				this.points = new Array();
				

				//this.box.nw=this.start;
				//this.box.se=new Point(this.start.x,this.start.y);
				

			}


			RtLine.prototype = {
				draw:function(end){
					this.end=this.parent.end;			
					this.end.x=end.x;
					this.end.y=end.y;

					this.points=new Array();	
					this.points.push(this.start.clone());
					this.points.push(this.end.clone());
				
				},
				dragDraw:function(center,direction){
					var dx=6;//边长10
					var A,B;
					if(direction=="sw"){
						A=new Point(center.x-dx/2,center.y-dx/2);
						B=new Point(center.x+dx/2,center.y+dx/2);
					}else if(direction=="se"){
						A=new Point(center.x+dx/2,center.y-dx/2);
						B=new Point(center.x-dx/2,center.y+dx/2);
					}
					this.parent.start=A.clone();
					this.parent.end=B.clone();
					this.start=this.parent.start;
					this.end=this.parent.end;

					this.points = new Array();
					this.points.push(A);	
					this.points.push(B);

				},
				
				redraw : function() {	
					
					this.cxt.beginPath();

					this.cxt.moveTo(this.points[0].x,this.points[0].y);
					//console.log("start:"+this.start.x+",y:"+this.start.y);
					this.cxt.lineTo(this.points[1].x,this.points[1].y);
					//console.log("this.points redraw:"+this.points[0].x);
					//console.log("end:"+this.end.x+",y:"+this.end.y);
					/*
					this.cxt.moveTo(this.box.nw.x,this.box.nw.y);
					this.cxt.lineTo(this.box.se.x,this.box.se.y);
*/
					//console.log("redraw se:"+this.box.se.x+","+this.box.se.y) 	
					//console.log("redraw nw:"+this.box.nw.x+","+this.box.nw.y) 

					this.cxt.stroke();
					this.cxt.closePath();
					
					 
					/*
					if(this.box){
						this.cxt.strokeText("nwp",this.box.nw.x,this.box.nw.y);
						this.cxt.strokeText("sep",this.box.se.x,this.box.se.y);
					}
					if(this.parent.box&&this.parent.box.nw){
						this.cxt.strokeStyle="red";
						this.cxt.strokeText("nw",this.parent.box.nw.x,this.parent.box.nw.y);
						this.cxt.strokeText("se",this.parent.box.se.x,this.parent.box.se.y);
					}
				
					
					this.LRTB=this.parent.LRTB;
					if(this.LRTB.l){
						this.cxt.strokeText("l",this.LRTB.l.x,this.LRTB.l.y);
						this.cxt.strokeText("r",this.LRTB.r.x,this.LRTB.r.y);
						this.cxt.strokeText("t",this.LRTB.t.x,this.LRTB.t.y);
						this.cxt.strokeText("b",this.LRTB.b.x,this.LRTB.b.y);
					}	*/
			
				},
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
					
					var copyData = new RtLine(this.rtLayer,start);
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
					this.points.push(this.end);
					this.points.push(this.start);

					this.ostart=this.start.clone();
					this.oend=this.end.clone();
					
					//baseContextStyleClone(data,this.parent);				
					//this.calcAABB();
					//baseContextImpData(this,data);				
					
				}
			};
