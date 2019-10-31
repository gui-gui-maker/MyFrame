/**
			 * 矩形
			 */
			function RtRect(rtLayer, start, addToLayer) {
				this.name="RtRect";
				this.cname = "矩形";
				this.rtLayer = rtLayer; 
				this.cxt=rtLayer.cxt;
				this.start=new Point(start.x,start.y);
				this.parent = new Primitive(this, addToLayer);

				this.points = new Array();
			}


			RtRect.prototype = {
				draw:function(end){
						this.parent.end=end;
						var dx=this.parent.end.x-this.parent.start.x;
						var dy=this.parent.end.y-this.parent.start.y;
					 

						this.points = new Array();
						this.points.push(new Point(this.parent.start.x,this.parent.start.y));
						this.points.push(new Point(this.parent.start.x,this.parent.end.y));						
						this.points.push(new Point(this.parent.end.x,this.parent.end.y));
						this.points.push(new Point(this.parent.end.x,this.parent.start.y));

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

					baseContextImpData(this,data);	//请确认有this.points			
					
				}
			};