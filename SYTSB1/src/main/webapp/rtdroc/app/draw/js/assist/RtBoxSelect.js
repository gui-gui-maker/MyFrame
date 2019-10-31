/**
			 * 矩形 选中助手
			 */
			function RtBoxSelect(rtDrawingLayer,start) {
				this.name="RtBoxSelect";
				this.cxt=rtDrawingLayer.cxt;
				this.rtDrawer=rtDrawingLayer.rtDrawer;
				this.start=start;
				this.box={};
			}


			RtBoxSelect.prototype = {
				draw:function(end){
					this.box.se=end;
					this.box.nw=this.start;
					this.box.ne=new Point(this.box.se.x,this.box.nw.y);
					this.box.sw=new Point(this.box.nw.x,this.box.se.y);

						//用于检测是否选中
					this.box.polygon=[];
					this.box.polygon.push(this.box.nw);
					this.box.polygon.push(this.box.ne);
					this.box.polygon.push(this.box.se);
					this.box.polygon.push(this.box.sw);
				},				
				redraw : function() {	
					//console.log("RtBoxSelect redraw");
					var fillStyle=this.cxt.fillStyle;
					var strokeStyle=this.cxt.strokeStyle;
					var lineWidth=this.cxt.lineWidth;
					var lineDashType=this.cxt.getLineDash();
					this.cxt.fillStyle="#82BBF3";
					this.cxt.strokeStyle="blue";
					this.cxt.setLineDash([]);
					this.cxt.lineWidth=1;
					this.cxt.beginPath();

					for (var i in this.box.polygon) {
						var point = this.box.polygon[i];
						this.cxt.lineTo(point.x, point.y);						
					}

					this.cxt.globalAlpha=0.2;
					this.cxt.closePath();
					this.cxt.stroke();
					//this.cxt.fillStyle="white";
					this.cxt.fill();
					this.cxt.globalAlpha=1;
					
					this.cxt.fillStyle=fillStyle;
					this.cxt.strokeStyle=strokeStyle;
					this.cxt.lineWidth=lineWidth;
					this.cxt.setLineDash(lineDashType);

				}
			};