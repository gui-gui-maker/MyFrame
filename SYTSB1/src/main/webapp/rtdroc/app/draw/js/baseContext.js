
			/*基类*/
			function baseContext(rtLayer) {
				this.canvas = rtLayer.canvas;
				this.cxt = rtLayer.cxt;
				this.rtDrawer = rtLayer.rtDrawer;
				this.drawingLayer = rtLayer.rtDrawer.drawingLayer;
				this.id = rtid();
				this.visiable=true;//初始状态

				initBaseContext(this.cxt);

				this.style={};
				
		
				this.style.font = this.cxt.font;
				this.style.lineWidth = this.cxt.lineWidth;
				this.style.strokeStyle = this.cxt.strokeStyle;
				this.style.fillStyle =this.cxt.fillStyle;
				this.style.lineDash = this.cxt.getLineDash();
				this.style.textAlign = this.cxt.textAlign;
				this.style.globalAlpha =this.cxt.globalAlpha;
				this.style.globalCompositeOperation = this.cxt.globalCompositeOperation;
				
				this.style.zIndex=rtLayer.primitives.length;

			}
			
			function R4() {
			   return (((1+Math.random())*0x10000)|0).toString(16).substring(1);
			}
			function rtid(){
				
			    return R4()+R4()+R4();
			}
			function initBaseContext(context){
		
				var fillStyle=document.getElementById("fillColor").value;
				if(fillStyle){
					context.fillStyle=fillStyle;
				}
				
				var strokeStyle=document.getElementById("strokeColor").value;
				if(strokeStyle){
					context.strokeStyle=strokeStyle;
				}
				
				
				var width=document.getElementById("strokeWidth").value;
				if(width){
					context.lineWidth=width;
				}

				var size=document.getElementById("fontSize").value;
				var fontSelect=document.getElementById("fontType");
				//var index=fontSelect.selectedIndex;
				//var type=fontSelect.options[index].value;
				var type=fontSelect.value;
				var fontStyle=size+"px "+type;
			
				if(size&&size!=10&&type!="Arial"){
					var fontStyle=size+"px "+type;
					context.font=fontStyle;
				}

				var lineDashValue=document.getElementById("lineDashType").value;
				 
				if(!lineDashValue){
					context.setLineDash([]);
					 
				}else{
					context.setLineDash(lineDashValue.split(",")); 
				}
				
				var textAlignValue=document.getElementById("textAlign").value;
				context.textAlign=textAlignValue;
				
			}
			//请与baseContext属性个数保持一致
			function baseContextRestore(primitive) {
				primitive.cxt.font = primitive.style.font;
				primitive.cxt.lineWidth = primitive.style.lineWidth;
				primitive.cxt.strokeStyle = primitive.style.strokeStyle;
				primitive.cxt.fillStyle = primitive.style.fillStyle;
				//console.log("primitive.cxt.fillStyle:"+primitive.cxt.fillStyle);
				primitive.cxt.textAlign = primitive.style.textAlign;
				primitive.cxt.setLineDash(primitive.style.lineDash);
				primitive.cxt.globalAlpha=primitive.style.globalAlpha;
				primitive.cxt.globalCompositeOperation = primitive.style.globalCompositeOperation;
				 
			}
			//请与baseContext属性个数保持一致
			function baseContextStyleClone(primitive,cloneData) {
				
				for(var i in primitive.style ){
					cloneData.style[i]=primitive.style[i];
				}

			}
			
			function baseContextInitBox(primitive,offset,oldMode){
				var l=primitive.LRTB.l.x;
				var r=primitive.LRTB.r.x;
				var t=primitive.LRTB.t.y;
				var b=primitive.LRTB.b.y;

				if(offset){ //RTGROUP组合的边框会稍微大点
					l-=2*6;//aabb小框的宽度
					r+=2*6;
					t-=2*6;
					b+=2*6;				
				}

				if(!oldMode){
					//this.box中数据会根据变换矩阵及时更新					
					primitive.box.nw=new Point(l,t);
					primitive.box.n =new Point((l+r)/2,t);
					primitive.box.ne=new Point(r,t);
					primitive.box.e =new Point(r,(t+b)/2);
					primitive.box.se=new Point(r,b);
					primitive.box.s =new Point((l+r)/2,b);
					primitive.box.sw=new Point(l,b);
					primitive.box.w =new Point(l,(t+b)/2);
						//用于检测是否选中
					primitive.box.polygon=[];
					primitive.box.polygon.push(primitive.box.nw);
					primitive.box.polygon.push(primitive.box.ne);
					primitive.box.polygon.push(primitive.box.se);
					primitive.box.polygon.push(primitive.box.sw);

					primitive.box.center=new Point(l + (r- l) / 2,t + (b-t) / 2);
					//原始BOX数据，不随变换矩阵更新
					primitive.obox=baseContextBoxClone(primitive.box);
				}else{
					primitive.box.nw.x=l;primitive.box.nw.y=t;
					primitive.box.n.x=(l+r)/2;primitive.box.n.y=t;
					primitive.box.ne.x=r;primitive.box.ne.y=t;
					primitive.box.e.x=r;primitive.box.e.y=(t+b)/2;
					primitive.box.se.x=r;primitive.box.se.y=b;
					primitive.box.s.x=(l+r)/2;primitive.box.s.y=b;
					primitive.box.sw.x=l;primitive.box.sw.y=b;
					primitive.box.w.x=l;primitive.box.w.y=(t+b)/2;

					primitive.box.center.x=l + (r- l) / 2;
					primitive.box.center.y=t + (b-t) / 2;
				}
						

				
			}

			function baseContextBoxClone(box){
				var newBox={};
				for(var i in box){
					var p=box[i];
					if(p instanceof Point){
						newBox[i]=box[i].clone();
					}else{
						newBox[i]=box[i];
					}
				}
				if(box.polygon){
					newBox.polygon=[];
					newBox.polygon.push(box.nw.clone());
					if(box.ne){
						newBox.polygon.push(box.ne.clone());
					}					
					newBox.polygon.push(box.se.clone());
					if(box.sw){
						newBox.polygon.push(box.sw.clone());
					}
					
				}				
				return newBox;
			}


			function baseContextTransform(primitive,matrix){
				//MatrixPrint(matrix);
				if(matrix){
					var idx=0;
					for(var i in primitive.box){
						if(primitive.box[i] instanceof Point){
							
							var p=MatrixMultiplyPoint(primitive.box[i],matrix);
							primitive.box[i].x=p.x;
							primitive.box[i].y=p.y;	
							
						}						
					}
					
				}
				
			
				if(primitive.matrix){
					primitive.matrix=MatrixMultiply(primitive.matrix,matrix);
				}else{
					primitive.matrix=matrix;
				}
				
			}

			
			function baseContextHasSelect(primitive,x,y){
				var point=new Point(x,y);
				var polygon=primitive.box.polygon;
				return isInPolygon(point,polygon);
			
			}

			function baseContextHasBoxSelect(primitive,box){
				for(var i in primitive.box.polygon){
					if(!isInPolygon(primitive.box.polygon[i],box)){
						return false;
					}
				}
				return true;
			
			}


			function baseContextUndoRedo(primitive,matrix){
				//baseContextTransform(primitive,matrix);
				 
				if(matrix){
					for(var i in primitive.box){
						if(primitive.box[i] instanceof Point){
							var p=MatrixMultiplyPoint(primitive.obox[i],matrix);
							primitive.box[i].x=p.x;
							primitive.box[i].y=p.y;					
						}						
					}
					primitive.matrix=matrix;
				}else{
					for(var i in primitive.box){
						if(primitive.box[i] instanceof Point){
							var p=primitive.obox[i];
							primitive.box[i].x=p.x;
							primitive.box[i].y=p.y;					
						}						
					}
					primitive.matrix=null;
				}				
				//RTGROUP 计算边界需求最新的L,R,T.B
				//重新计算上下左右边界点，用于leftAlign等
				if(primitive.type=="RtGroup"){
					baseContextCalcLRTB(primitive,primitive.data.primitives);
				}else{
					baseContextCalcDataLRTB(primitive,primitive.data.points);		
				}
				
			}

			

			function baseContextUndoRedoSort(primitive,zIndex){
				primitive.zIndex=zIndex;
				primitive.rtLayer.primitives[zIndex]=primitive;
			}

			function baseContextUndoRedoGroupSort(primitive,groupZIndex){
				primitive.groups.zIndex=groupZIndex;
				primitive.groups.group.data.primitives[groupZIndex]=primitive;
			}


			//复制数据

			function baseContextImpDatabak(primitive,data){
				for (var i in data.points) {
					var p = data.points[i];
					if(data.matrix){
						p =MatrixMultiplyPoint(p,data.matrix);
					}
					var point = new Point(p.x, p.y);
					primitive.points.push(point);
				}
			}
			

			function baseContextImpData(primitive,data){
				for (var i in data.points) {
					var p = data.points[i];
					/*
					if(data.matrix){
						p =MatrixMultiplyPoint(p,data.matrix);
					}*/
					var point = new Point(p.x, p.y);
					primitive.points.push(point);
				}
			}


			//计算旋转角度界点
		   function baseContextCalcRotate(primitive) {			
				var r=clockwiseRotate(primitive); //util.js
				primitive.finalRotate=r;
				if(r){
					primitive.finalCos=Math.cos(primitive.finalRotate);
					primitive.finalSin=Math.sin(primitive.finalRotate);
				}
				
				return r;				
			}
			
			function baseContextCalcOffset(rect,cross,start,end,offset) {		
				var type=rect.name;
				var Sx=1;
				var Sy=1;
				if(!offset){	
					if(rect.aabb.boxType!="line"){
						/*
						var A=rect.primitive.data.box.n;
						var O=rect.primitive.data.box.center;

						var B=new Point(O.x,O.y-1);
						var r=aobRotate(A,O,B);
						*/
						var r=rect.primitive.finalRotate;
						if(r){
							var cos=rect.primitive.finalCos; var sin=rect.primitive.finalSin;
							var  m=cross.x,n=cross.y;
						

							
							var r1=[[cos,sin,0],[-sin,cos,0],[m-m*cos+n*sin,n-m*sin-n*cos,1]];//旋转到与X平行	
							start=MatrixMultiplyPoint(start,r1);
							cross=MatrixMultiplyPoint(cross,r1);

							end=MatrixMultiplyPoint(end,r1);
						}
					}
					var dx=end.x-cross.x;
					if(start.x!=cross.x){
									
						//控制是否翻转
						///*
						if(start.x>cross.x){
							
							if(dx<2*rect.aabb.functionRectWidth){
								dx=2*rect.aabb.functionRectWidth;
							}
						}else{
							if(dx>-3*rect.aabb.functionRectWidth){
								dx=-2*rect.aabb.functionRectWidth;
							}
						}
						//*/

						Sx=dx/(start.x-cross.x);
						
					}
					var dy=end.y-cross.y;
					if(start.y!=cross.y){		
						
						
						//控制是否翻转
						///*
						if(start.y>cross.y){
							if(dy<2*rect.aabb.functionRectWidth){
								dy=2*rect.aabb.functionRectWidth;
							}
						}else{
							if(dy>-2*rect.aabb.functionRectWidth){
								dy=-2*rect.aabb.functionRectWidth;
							}
						}
						//*/

						Sy=dy/(start.y-cross.y);
						
					}
				 
					//console.log(this.data.name+" calc offset");
				}else{
					//由其他图形拉伸比例决定		
					Sx=offset.Sx;
					Sy=offset.Sy;
					console.log(" get offset:SX:"+offset.Sx+",sY:"+offset.Sy);
				}
				
				switch(type){
					case "n":
						Sx=1;
						break;
					case "s":
						Sx=1;
						break;
					case "w":
						Sy=1;
						break;						
					case "e":
						Sy=1;
						break;
				}


				var Tx=cross.x*(1-Sx);
				var Ty=cross.y*(1-Sy);

				//console.log("offset: Sx:"+Sx+",Sy:"+Sy+",Tx:"+Tx+",Ty:"+Ty);
				return [[Sx,0,0],[0,Sy,0],[Tx,Ty,1]];
			}
		

			
	    //逆时针旋转后计算 再旋转回来 防止斜切发生
		 function baseContextRotateScale(scaleMatrix,center,obj){ 
			if(obj.finalRotate){
				var cos = obj.finalCos, sin = obj.finalSin;
				var  m=center.x,n=center.y;
				//旋转到与X平行
				var r1=[[cos,sin,0],[-sin,cos,0],[m-m*cos+n*sin,n-m*sin-n*cos,1]];
				var a=MatrixMultiply(r1,scaleMatrix);
				//旋转回来			
				var r2=[[cos,-sin,0],[sin,cos,0],[m-m*cos-n*sin,n+m*sin-n*cos,1]];
				scaleMatrix=MatrixMultiply(a,r2);					
			}
			return scaleMatrix;
		 }

//		 ========================================直线类图元专属==========================================================
		//初始化直线类图元
		 function baseContextInitLineBox(primitive){

				primitive.box.nw=primitive.data.start.clone();	
				primitive.box.se=primitive.data.end.clone();
				//primitive.box.nw=primitive.start;	
				//primitive.box.se=primitive.end;
				
				
					//用于检测是否选中
				primitive.box.polygon=[];
				primitive.box.polygon.push(primitive.box.nw);
				primitive.box.polygon.push(primitive.box.se);
				

				primitive.box.center=new Point(primitive.LRTB.l.x + (primitive.LRTB.r.x- primitive.LRTB.l.x) / 2,primitive.LRTB.t.y + (primitive.LRTB.b.y-primitive.LRTB.t.y) / 2);				

				//原始BOX数据，不随变换矩阵更新
				primitive.obox=baseContextBoxClone(primitive.box);	
				
			}
			//直线类图元 撤销 /取消撤销
			function baseContextLineUndoRedo(primitive,points){
				primitive.parent.box.nw.x=points[0].x;
				primitive.parent.box.nw.y=points[0].y;
				primitive.parent.box.se.x=points[1].x;
				primitive.parent.box.se.y=points[1].y;
				primitive.points[0].x=points[0].x;
				primitive.points[0].y=points[0].y;
				primitive.points[1].x=points[1].x;
				primitive.points[1].y=points[1].y;
			
				//重新计算上下左右边界点，用于leftAlign等
				baseContextCalcDataLRTB(primitive.parent,primitive.points);		
			}

		/*判断是否直线型图元*/
		 function baseContextIsLinePrimitive(type){
			if(type=="RtLine"||type=="RtArrow"||type=="RtArrowDouble"){
				return true;
			}
		 }
		 
		 /*判断是否直线型图元*/
		 function baseContextIsTextPrimitive(primitive){
			var type=primitive.type;
			if(type=="RtText"||(type=="RtGroup"&&primitive.groupType=="textGroup")){
				return true;
			}
		 }
		 
		 
		 /*用于复制、导入图元后，复制图元的STYLE、BOX、OBOX、LRTB,MATRIX,旋转角度等信息*/
		 function baseContextPrimitiveClone(p,primitive){
			//STYLE	
			baseContextStyleClone(primitive,p);	
			
			//MATRIX
			p.matrix=primitive.matrix;
			
			//ROTATE
			p.finalRotate=primitive.finalRotate;
			if(p.finalRotate){
				p.finalCos=primitive.finalCos;
				p.finalSin=primitive.finalSin;
			}
			
			//LRTB
			for(var i in primitive.LRTB){
				p.LRTB[i]=new Point(primitive.LRTB[i].x,primitive.LRTB[i].y);
			}
			
			//BOX
			for(var i in primitive.box){
				var obj=primitive.box[i];
				if(!Array.isArray(obj)){
					p.box[i]=new Point(obj.x,obj.y);
				}					
			}
			p.box.polygon=[];
			
			
			p.box.polygon.push(p.box.nw);
			if(p.box.ne){
				p.box.polygon.push(p.box.ne);
			}					
			p.box.polygon.push(p.box.se);
			if(p.box.sw){
				p.box.polygon.push(p.box.sw);
			}
			
			//OBOX
			p.obox={};
			for(var i in primitive.obox){
				var obj=primitive.obox[i];
				if(!Array.isArray(obj)){
					p.obox[i]=new Point(obj.x,obj.y);
				}					
			}
			p.obox.polygon=[];
			
			
			p.obox.polygon.push(p.obox.nw);
			if(p.obox.ne){
				p.obox.polygon.push(p.obox.ne);
			}					
			p.obox.polygon.push(p.obox.se);
			if(p.obox.sw){
				p.obox.polygon.push(p.obox.sw);
			}
		}