/**
			 * 文字
			 */
			function RtText(rtLayer, start, addToLayer) {
				this.name="RtText";
				this.cname = "文本";
				this.rtLayer = rtLayer; 
				this.cxt=rtLayer.cxt;
				this.start=new Point(start.x,start.y);
				this.parent = new Primitive(this, addToLayer);
			
				this.points = new Array();
				
			}


			RtText.prototype = {
				draw:function(end){
						drawWriting=true;
						//this.end=end;

						var textControl=document.getElementById("textControl");
						var enterControl=autoEnter(textControl);
						this.text=enterControl.text;//文字输入框 
						
						this.lineHeight=enterControl.lineHeight;//文字高度
						//console.log("----------this.text:"+this.text);
						
						var  ruler=this.cxt.measureText(this.text);
						
						
						var fontStyle=this.cxt.font;
						var fontSize=fontStyle.replace(/[^0-9]/ig, "");
						
						var dx=ruler.width+8;
						var dy=parseInt(fontSize);
						

						this.end=new Point(this.start.x+dx,this.start.y+dy);
						this.parent.end=this.end;
					/*
						this.points = new Array();
						this.points.push(new Point(this.start.x-5,this.start.y-5));
						this.points.push(new Point(this.start.x-5,this.end.y));						
						this.points.push(new Point(this.end.x,this.end.y));
						this.points.push(new Point(this.end.x,this.start.y-5));
					*/

						var textControls=document.getElementById("textControls");
						var bbox = canvas.getBoundingClientRect();
						var windowX=this.start.x+bbox.left * (canvas.width  / bbox.width);
						var windowY=this.start.y+bbox.top * (canvas.height / bbox.height);		
						
						//INPUT框高度
						windowY=windowY-10;
						windowX=windowX-3;

						//console.log("RtText:windowPoint.x:"+windowX);
						//console.log("RtText:windowPoint.y:"+windowY);
						
						var size= document.getElementById("fontSize").value;

						textControls.style.top=windowY+"px";
						textControls.style.left=windowX+"px";
						textControls.style.width="100px";
						//textControls.style.height="100px";

						textControls.style.display="block";		
						textControl.style.fontSize = size+"px";
	
						var top=this.start.y-this.lineHeight;
						var left=this.start.x-15;
						var w=textControl.offsetWidth+15;
						var h=textControl.offsetHeight+this.lineHeight;
						
						this.points = new Array();
						this.points.push(new Point(left,top));
						this.points.push(new Point(left,top+h));						
						this.points.push(new Point(left+w,top));
						this.points.push(new Point(left+w,top+h));
							
						textControl.focus();
						
				},
				dragDraw:function(center,text){
					this.start=center;
					this.text=text;//文字输入框 
					var  ruler=this.cxt.measureText(this.text);
					//console.log("this.text:"+this.text);
					
					var fontStyle=this.cxt.font;
					var fontSize=fontStyle.replace(/[^0-9]/ig, "");
					//console.log("fontSize:"+fontSize);
					var dx=ruler.width+8;
					var dy=parseInt(fontSize);

//					console.log("ruler width:"+ruler.width+",dy:"+dy);
					

					this.end=new Point(this.start.x+dx,this.start.y+dy);
					this.parent.end=this.end;

					this.points = new Array();
					this.points.push(new Point(this.start.x-5,this.start.y-5));
					this.points.push(new Point(this.start.x-5,this.end.y));						
					this.points.push(new Point(this.end.x,this.end.y));
					this.points.push(new Point(this.end.x,this.start.y-5));

			    },
			
				redraw : function() {	
					this.obox=this.parent.obox;
					this.matrix=this.parent.matrix;
					this.cxt.save();
					if(this.matrix){
						this.cxt.transform(this.matrix[0][0],this.matrix[0][1],this.matrix[1][0],this.matrix[1][1],this.matrix[2][0],this.matrix[2][1]);
					}
					
					/*
					for (var i in this.box.polygon) {
						var point = this.box.polygon[i];
						this.cxt.lineTo(point.x, point.y);						
					}
					*/
					//console.log(this.cxt.font);
					//this.cxt.strokeText("width:" + this.cxt.measureText(this.text).width,10,50)
					//if(this.obox){
					
						//var text =document.getElementById("textControl").value;//获取id为ta的textarea的全部内容
						
						var arry = this.text.split('\n');//以换行符为分隔符将内容分割成数组
						var x=this.start.x;
						var y=this.start.y;
						
						for(var i in arry){							
							this.cxt.fillText(arry[i],x,y);
							this.cxt.strokeText(arry[i],x,y);
							y+=this.lineHeight;
						}
						
						
					//}
					
					
					this.cxt.restore();
					
					

				},
				
				copy : function() {
					var start=this.start.clone();
					var copyData = new RtText(this.rtLayer,start);

					copyData.text=this.text;

					for (var i in this.points) {
						var p = this.points[i];
						var point = new Point(p.x, p.y);
						copyData.points.push(point);
					}


					//console.log("copyData style font:"+copyData.style.font);
					
					return copyData;
				},
			
				imp:function(data){
					//console.log("data:"+data.style.font);
					baseContextImpData(this,data);	
					this.text=data.text;
					this.lineHeight=data.lineHeight;
					
					//this.matrix=data.matrix;
					
				}
			};