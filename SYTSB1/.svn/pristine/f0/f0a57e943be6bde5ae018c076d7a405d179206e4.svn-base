/**
			 * 三角形
			 */
			function RtTriangle(rtLayer, start, addToLayer) {				
				this.name="RtTriangle";
				this.cname = "三角形";
				this.rtLayer = rtLayer; 
				this.cxt=rtLayer.cxt;
				this.start=new Point(start.x,start.y);
				this.parent = new Primitive(this, addToLayer);

				this.points = new Array();

			}


			RtTriangle.prototype = {
				draw:function(end){
						this.end=this.parent.end;
						this.end.x=end.x;
						this.end.y=end.y;
						var dx=this.end.x-this.start.x;
						var dy=this.end.y-this.start.y;
					 

						this.points = new Array();
						this.points.push(new Point(this.start.x+dx/2,this.start.y));	
						this.points.push(new Point(this.start.x,this.end.y));
						this.points.push(new Point(this.end.x,this.end.y));

				},
				dragDraw:function(center){
						
						var dx=20;//边长20
						var diagonal=Math.sqrt(dx*dx-(dx/2)*(dx/2));
						var A=new Point(0+center.x,-diagonal*2/3+center.y);
						var B=new Point(-dx/2+center.x,diagonal*1/3+center.y);
						var C=new Point(dx/2+center.x,diagonal*1/3+center.y);


						this.points = new Array();
						this.points.push(A);	
						this.points.push(B);
						this.points.push(C);

				},
				
				copy : function() {
					
					var start=this.start.clone();
					var copyData = new RtRect(this.rtLayer,start);
					 
					for (var i in this.points) {
						var p = this.points[i];
						var point = new Point(p.x, p.y);
						copyData.points.push(point);
					}
					
					return copyData;
				} ,
				imp:function(data){

					baseContextImpData(this,data);			
					
				}
			};