
			/*画板*/
			function RtDrawer(canvas) {
				if (!canvas) {
					alert("canvans不能为空");
					return null;
				}
				this.layers = new Array();
				this.drawingLayer = null;
				//会在DRAWINGLAYER中初始化
				this.canvas = canvas;
				this.cxt = this.canvas.getContext('2d');

				
				//历史动作列表
				this.actions=new Array();
               
			    
				//this.historyPointer;//操作指针
				this.history=new RtHistory(this);//历史操作列表

			}

			/**
			 *
			 *画板属性方法、主要用于添加删除层，重绘CANVAS
			 *
			 */
			RtDrawer.prototype = {
				add : function(layer) {
					var length = (layer.primitives == null ? 0 : layer.primitives.length);
					this.layers.push(layer);
					layer.zIndex = this.layers.length;
					if (length > 0) {
						this.redraw();
					}
				},
				remove : function(layer) {
					var length = (layer.primitives == null ? 0 : layer.primitives.length);
					this.layers.pop(layer);
					if (length > 0) {
						this.redraw();
					}

				},
				/*重绘*/
				redraw : function() {
					//console.log("rtDrawer redraw..");
					this.cxt.clearRect(0, 0, this.canvas.width, this.canvas.height);
					drawBackground();
					//暂定全部重绘
					//debugger;
					var layerLength = this.layers.length;
					for (var i = 0; i < layerLength; i++) {
						this.layers[i].redraw();
					}

					if(this.drawingLayer){
						this.drawingLayer.redraw();
					}
					

				},
				/*删除*/
				delete : function() {
					this.drawingLayer.delete();
					
					this.redraw();

				},
				undo:function(){
					this.history.undo();
				},
				redo:function(){
				    this.history.redo();
				},
				fillColor:function(color){
					this.drawingLayer.fillColor(color);
				},
				strokeColor:function(color){
					this.drawingLayer.strokeColor(color);
				},
				setLineWidth:function(width){
					this.drawingLayer.setLineWidth(width);
				},
				setFontStyle:function(fontStyle){
					this.drawingLayer.setFontStyle(fontStyle);
				}				
				,
				setLineDashType:function(segments){
					this.drawingLayer.setLineDashType(segments);
				},
				moveAlign:function(method){
					this.drawingLayer.moveAlign(method);
				},	
				moveCenterAlign:function(method){
					this.drawingLayer.moveCenterAlign(method);
				},
				horizontallyAvg:function(){
					this.drawingLayer.horizontallyAvg();
				},
				verticallyAvg:function(){
					this.drawingLayer.verticallyAvg();
				},
				keyMove:function(method){
					this.drawingLayer.keyMove(method);
				},
				copy:function(){	
					this.pasteFlag=false;
					this.copyData=this.drawingLayer.copy();
				},
				paste:function(){
					if(this.copyData){
						if(this.pasteFlag){
							this.copyData=this.drawingLayer.copy();
						}
						var idx=this.layers.length-1;
						var rtLayer=this.layers[idx];
						
						for(var i in this.copyData){
							var p=this.copyData[i];
							p.addToLayer=true;
							rtLayer.add(p);

						}
						
						this.drawingLayer.create(this.copyData);
						
						var drawingGroupAction=new RtAction("copy",this.copyData);
						this.history.add(drawingGroupAction);
						this.pasteFlag=true;
					}
					
					
				},
				locked:function(){
					this.drawingLayer.locked();
				},
				unlocked:function(){
					this.drawingLayer.unlocked();
				},
				exp:function(){
					//ZQ EDIT 20180715
					var value=expDrawer(this);
					/*
					var cache = [];
					var value=JSON.stringify(this, function(key, value) {
						if (typeof value === 'object' && value !== null) {
							if (cache.indexOf(value) !== -1) {
								// Circular reference found, discard key
								return;
							}
							// Store value in our collection
							cache.push(value);
						}
						return value;
					});*/
					//cache = null; // Enable garbage collection
					//console.log("{\"layers\":"+value+"}");
					//var jsonObj = JSON.parse(value);
				
					//console.log(typeof(jsonObj)); // obj
					console.log(value);
					return value;
					
				},
				imp:function(value){
					console.log("rtdrawer:imp"); 
					var impFlag=impDrawer(value,this);
					if(impFlag){
						var idx=this.layers.length-1;
						rtLayer=this.layers[idx];
					}
					
				},
				importComponent:function(value){
					console.log("rtdrawer:importComponent");
					var impFlag=impDrawer(value,this,rtLayer);
//					if()
				},
				selectPrimitive:function(id){
					for (var i = 0; i < this.layers.length; i++) {
						var p=this.layers[i].findPrimitive(id);
						if(p){
							this.drawingLayer.selectPrimitive(p);
							return;
						}
					}					
				},
				newPage:function(id){
					this.layers=new Array();
					this.redraw();
				}
			};
