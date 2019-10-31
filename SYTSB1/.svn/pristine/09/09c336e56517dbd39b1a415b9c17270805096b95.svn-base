/**
			 * 图形
			 */
			function RtImage(rtLayer, start, addToLayer) {
				this.name="RtImage";
				this.cname = "图像";
				this.rtLayer = rtLayer; 
				this.cxt=rtLayer.cxt;
				if(!start){
					start=new Point();
				}
				this.start=new Point(start.x,start.y);
				this.parent = new Primitive(this, addToLayer);

				this.points = new Array();
			}


			RtImage.prototype = {
				//重绘
				redraw : function() {
					
					this.matrix=this.parent.matrix;
					this.cxt.save();
					if(this.matrix){
						this.cxt.transform(this.matrix[0][0],this.matrix[0][1],this.matrix[1][0],this.matrix[1][1],this.matrix[2][0],this.matrix[2][1]);
					}
					
					this.cxt.drawImage(this.img,this.start.x,this.start.y);
					
					this.cxt.restore();
					
				},
				draw:function(src){
                        var rtImage=this;
						rtImage.img=new Image();
						rtImage.img.src=src;						
						var end=new Point(0,0);
					    rtImage.img.onload=function(){
						   rtImage.cxt.drawImage(this,0,0);
						   //图片绘制和初始化
						   end.x=this.naturalWidth;
						   end.y=this.naturalHeight;
						   console.log("this.img.width,this.img.height:"+this.naturalWidth+","+this.naturalHeight);

						    //var end=new Point(this.img.naturalWidth,this.img.naturalHeight);					  
							rtImage.parent.end=end;
							var dx=rtImage.parent.end.x-rtImage.parent.start.x;
							var dy=rtImage.parent.end.y-rtImage.parent.start.y;
						 

							rtImage.points = new Array();
							rtImage.points.push(new Point(rtImage.parent.start.x,rtImage.parent.start.y));
							rtImage.points.push(new Point(rtImage.parent.start.x,rtImage.parent.end.y));						
							rtImage.points.push(new Point(rtImage.parent.end.x,rtImage.parent.end.y));
							rtImage.points.push(new Point(rtImage.parent.end.x,rtImage.parent.start.y));

							rtImage.parent.stopDraw();
							console.log("rtImage:"+rtImage.parent.box.polygon);
					    } 
						
					   

				},
				 
				copy : function() {
					var start=this.start.clone();
					var copyData = new RtImage(this.rtLayer,start);

					copyData.img=new Image();
					copyData.img.src=this.img.src;	

					for (var i in this.points) {
						var p = this.points[i];
						var point = new Point(p.x, p.y);
						copyData.points.push(point);
					}

					
					return copyData;
				} ,
				imp:function(data){
					this.img=new Image();
					this.img.src=data.img.src;	
					baseContextImpData(this,data);	//请确认有this.points	
					var rtImage=this;
					rtImage.img.onload=function(){
						rtImage.rtLayer.redraw();
					} 
						
					 
				}
			};