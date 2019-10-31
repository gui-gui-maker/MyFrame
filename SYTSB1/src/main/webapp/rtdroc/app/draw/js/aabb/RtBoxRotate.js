			/**
			 * AABB包围盒功能矩形
			 */
			function RtBoxRotate(aabb,start,width,height) {

				this.aabb=aabb;
				this.primitive=aabb.primitive;
				this.rtLayer = aabb.rtLayer;
				this.type="rotate";
				baseContext.call(this, this.rtLayer);

				this.start=start;
				this.width=width;
				this.height=height;
				this.visiable=true;

				this.imgUrl="rtdroc/app/draw/images/aabb/box_rotate.png";
				//this.imgUrl="images/aabb/box_rotate.png";
				this.imgWidth=width;
				this.imgHeight=height;
				this.onload=false;
				
				
				
				this.img = new Image();
				this.img.src =this.imgUrl;
				//this.img.setAttribute("crossOrigin",'Anonymous');

				var boxRotate=this;
				this.img.onload=function(){
					boxRotate.onload=true;
					boxRotate.cxt.drawImage(boxRotate.img,(boxRotate.start.x-boxRotate.width/2),(boxRotate.start.y-boxRotate.height*2),boxRotate.imgWidth,boxRotate.imgHeight);
				};

				this.init();				

			}


			RtBoxRotate.prototype = {
				
				init:function(){
					//包围盒
					this.box={};
					
					this.box.l=this.start.x-this.width/2;
					this.box.r=this.start.x+this.width/2;
					this.box.t=this.start.y-this.height*2;
					this.box.b=this.start.y-this.height;
			
					
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
					//console.log("box ratote redraw..");
					if(!this.visiable){
						//console.log("box ratote no visiable..");
						return;					
					}
					this.cxt.beginPath();


					this.cxt.save();
					if(this.matrix){						
						this.cxt.transform(this.matrix[0][0],this.matrix[0][1],this.matrix[1][0],this.matrix[1][1],this.matrix[2][0],this.matrix[2][1]);
					}
					
					if(this.onload){
					
						this.cxt.drawImage(this.img,(this.start.x-this.width/2),(this.start.y-this.height*2),this.imgWidth,this.imgHeight);
					}

					
					this.cxt.closePath();
					this.cxt.restore();
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
					if(r!=0){
						var cos = Math.cos(r), sin = Math.sin(r);
						var m=this.start.x;
						var n=this.start.y;
						rotateMatrix=[[cos,sin,0],[-sin,cos,0],[m-m*cos+n*sin,n-m*sin-n*cos,1]];
					}
					
					this.matrix=rotateMatrix;
					this.init();	
				},
				disable:function(){
					this.visiable=false;
				},
				enable:function(){
					this.visiable=true;
				}
			};