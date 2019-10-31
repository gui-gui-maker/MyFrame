/**
			 * 五角形 未完成 
			 */
			function RtPentangle(rtLayer, start, addToLayer) {
				this.name="RtPentangle";
				this.cname = "五角形";
				this.rtLayer = rtLayer;
				//this.cxt = this.rtLayer.cxt;
				this.addToLayer = addToLayer;
				 
				baseContext.call(this, this.rtLayer);

				//this.start=new Point(x,y);
				//this.width = width;
				//this.height = height;
		
//				console.log("RtPentangle minX:"+this.minX+",minY:"+this.minY);
				this.start=new Point(start.x,start.y);
				
				
				//包围盒
				this.box={};
				this.box.l=start.x;
				this.box.r=start.x;
				this.box.t=start.y;
				this.box.b=start.y;

				
				//中心点
				this.center={};

				baseContextInitBox(this);

				this.parent = new Primitive(this, 'RtPentangle', addToLayer);
				

			}


			RtPentangle.prototype = {
				draw:function(end){
						var dx=end.x-this.start.x;
						var dy=end.y-this.start.y;
					 
						this.box.ne.x=this.box.nw.x+dx;
						this.box.ne.y=this.box.nw.y;
						this.box.se.x=this.box.nw.x+dx;
						this.box.se.y=this.box.nw.y+dy;
						this.box.sw.x=this.box.nw.x;
						this.box.sw.y=this.box.nw.y+dy;
						
						this.points = new Array();
						for(var i = 0; i < 5; i ++){
							cxt.lineTo( Math.cos( (18 + i*72 )/180 * Math.PI) * R + x,
										-Math.sin( (18 + i*72 )/180 * Math.PI) * R + y)

							cxt.lineTo( Math.cos( (54 + i*72 )/180 * Math.PI) * r + x,
										-Math.sin( (54 + i*72 )/180 * Math.PI) * r + y)
						}

						
						this.points.push(new Point(this.box.nw.x+dx/2,this.box.nw.y));
						this.points.push(new Point(this.box.se.x,this.box.se.y));
						this.points.push(new Point(this.box.sw.x,this.box.sw.y));

				},
				redraw : function() {	
					//console.log("recet fillStyle:"+this.fillStyle);
					//baseContextRestore(this);
					/*
					this.cxt.beginPath();

					for (var i in this.box.polygon) {
						var point = this.box.polygon[i];
						this.cxt.lineTo(point.x, point.y);						
					}

					this.cxt.closePath();
					this.cxt.stroke();

					this.cxt.fill();
					*/
					baseContextRestore(this);
					this.cxt.beginPath();
					for (var i in this.points) {
						var point = this.points[i];
						if(this.matrix){
							var c=MatrixMultiplyPoint(point,this.matrix);
							this.cxt.lineTo(c.x, c.y);
						}else{
							this.cxt.lineTo(point.x, point.y);
						}
						
					}
					this.cxt.closePath();
					this.cxt.stroke();					
					this.cxt.fill();					

				},
				calcAABB : function() {
						//包围盒
				
						for (var i = 0; i < this.box.polygon.length; i++) {
							var point = this.box.polygon[i];
							if (i == 0) {
								//left
								this.box.l = point.x;
								//right
								this.box.r= point.x;
								//top
								this.box.t= point.y;
								//bottom
								this.box.b= point.y;

							} else {
								if (point.x < this.box.l) {
									this.box.l = point.x;
								}

								if (point.x > this.box.r) {
									this.box.r = point.x;
								}

								if (point.y < this.box.t) {
									this.box.t = point.y;
								}

								if (point.y > this.box.b) {
									this.box.b = point.y;
								}
							}
						}
							
						
						 

						this.center={};
						this.center.x = this.box.l + (this.box.r- this.box.l) / 2;
						this.center.y = this.box.t + (this.box.b-this.box.t) / 2;

						baseContextInitBox(this);

				},
				hasSelect:function(x,y){			
					return baseContextHasSelect(this,x,y);
				},
				hasBoxSelect:function(box){			
					return baseContextHasBoxSelect(this,box);
				},
				transform:function(matrix){
					baseContextTransform(this,matrix);
					
					//console.log("RtLine transform this.box.nw:  x:"+this.box.nw.x+",y:"+this.box.nw.y);
				},
				copy : function() {
					
					var copyData=new RtPentangle(this.rtLayer,this.start,this.addToLayer);
					return copyData;
				} ,
				stopMove :function(){
					
					
				},
				undo:function(matrix){
				
					baseContextUndoRedo(this,matrix);
				},
				redo:function(matrix){
				
					baseContextUndoRedo(this,matrix);
				}
			};