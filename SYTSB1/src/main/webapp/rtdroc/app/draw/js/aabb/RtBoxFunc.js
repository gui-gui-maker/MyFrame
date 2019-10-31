			/**
			 * AABB包围盒功能矩形
			 */
			function RtBoxFunc(aabb, start, width, height ) {

				this.aabb=aabb;
				this.primitive=aabb.primitive;
				this.rtLayer = aabb.rtLayer;
				this.type="scale";
				
				
				baseContext.call(this, this.rtLayer);

				this.width = width;
				this.height = height;
				
				this.start=start;
				
				
				this.init();				

			}


			RtBoxFunc.prototype = {
				
				init:function(){

					//包围盒
					this.box={};
						
					this.box.l=this.start.x-this.width/2;
					this.box.r=this.start.x+this.width/2;
					this.box.t=this.start.y-this.height/2;
					this.box.b=this.start.y+this.height/2;
			
					
					//四个顶角点
					
					this.box.nw=new Point(this.box.l,this.box.t);
					this.box.ne=new Point(this.box.r,this.box.t);
					this.box.se=new Point(this.box.r,this.box.b);
					this.box.sw=new Point(this.box.l,this.box.b);

					this.box.center={};


					//用于检测是否选中
					this.box.polygon=[];
					this.box.polygon.push(this.box.nw);
					this.box.polygon.push(this.box.ne);
					this.box.polygon.push(this.box.se);
					this.box.polygon.push(this.box.sw);


				
				},
				redraw : function() {
				
					this.cxt.strokeStyle = 'black';
					this.cxt.fillStyle = 'white';
					this.cxt.lineWidth = 1;
					this.cxt.setLineDash([]);
					this.cxt.beginPath();

					this.cxt.save();
					
					if(this.matrix){						
						this.cxt.transform(this.matrix[0][0],this.matrix[0][1],this.matrix[1][0],this.matrix[1][1],this.matrix[2][0],this.matrix[2][1]);
					}
					
					this.cxt.rect(this.start.x-this.width/2, this.start.y-this.height/2, this.width, this.height);	
					
					this.cxt.stroke();
					this.cxt.fill();
					this.cxt.closePath();
					this.cxt.restore();
				//	this.cxt.fillStyle='black';
				//	this.cxt.fillText(this.start.x+","+this.start.y,this.start.x,this.start.y);
				},					
				hasSelect:function(x,y){
					
				    var flag=false;
					var point=new Point(x,y);
					if(this.matrix){
						
						var polygon=MatrixMultiplyArray(this.box.polygon,this.matrix);
						flag=isInPolygon(point,polygon);
					}else{
						
						flag=isInPolygon(point,this.box.polygon);
						
					}
					
					return flag;				
				},
				refresh:function(r){
					var rotateMatrix=null;
					if(r&&r!=0){
						var cos = Math.cos(r), sin = Math.sin(r);
						var m=this.start.x;
						var n=this.start.y;
						rotateMatrix=[[cos,sin,0],[-sin,cos,0],[m-m*cos+n*sin,n-m*sin-n*cos,1]];
					}
					
					this.matrix=rotateMatrix;
					this.init();
				}
			};