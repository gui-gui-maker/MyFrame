	function RtDrawingLayer(rtDrawer) {
				this.name = "RtDrawingLayer";

				this.rtDrawer = rtDrawer;
				this.rtDrawer.drawingLayer = this;
				this.canvas = this.rtDrawer.canvas;
				this.cxt = this.rtDrawer.cxt;
				//将图层添加到画板

				//被选中的 类型为RtSelectedPrimitive
				//this.aabbPrimitives=new Array();
				this.selectedPrimitives = null;

				this.assists=null;//用来装辅助的

				
				//当前被选中的包围盒的功能图形
				this.selectedBoxFunc=null;

				this.zIndex = 1;
				this.visiable = false;

				//this.drawingGroupTemp=false;//用于标记多选是否被记录
				//this.drawingGroupAction=null;//用于存储多选操作
				this.box={};

				this.insideGroupMode=false;//组合内部模式
				this.insideGroup=null;//内部模式的组合元素
				
				this.moving = false;

			}

			//RtDrawingLayer.prototype = new RtLayer();
			//RtDrawingLayer.prototype.selected=drawFunction;
			//绘制层
			RtDrawingLayer.prototype = {
				cleanTempGroupAction:function(){
					//this.drawingGroupTemp=false;
					this.drawingGroupAction=null;
				},
				initTempGroupAction:function(){
					if(this.selectedPrimitives.length>0){
						this.drawingGroupAction=new RtAction("drawingGroupAction",this.selectedPrimitives,this.insideGroup);//创建临时历史记录
						this.rtDrawer.history.createTemporary(this.drawingGroupAction);	
					}
				},
				add : function(primitive) {
					this.visiable = true;
					primitive.visible();
					if (window.RtCtrlKey) {
						var removeFlag=false;
						console.log("zindex:"+primitive.zIndex);
						if (!this.selectedPrimitives || !this.visiable) {
							this.selectedPrimitives = new Array();
						}else{
							
							//判断是否存在，存在则移出
							for(var i in this.selectedPrimitives){
								if(this.selectedPrimitives[i]==primitive){	
									removeFlag=true;
									this.selectedPrimitives.splice(i,1);
									break;
								}
							}
						}
						if(!removeFlag){
							this.selectedPrimitives.push(primitive);
						} 
					
					} else {
						this.selectedPrimitives = new Array();
						this.selectedPrimitives.push(primitive);
						
					}
					//this.redraw();
					//this.cleanTempGroupAction();//配合MOURSEMOVE记录历史操作
					
					this.initTempGroupAction();
					/*
					if(primitive.group&&this.selectedPrimitives[0]!=primitive.group){
						this.selectedPrimitives.splice(0,0,primitive.group);
					}
					*/
					/*
					if(this.insideGroupMode&&this.selectedPrimitives[0]!=this.insideGroup){
						this.selectedPrimitives.splice(0,0,this.insideGroup);
					}
					 */

					this.rtDrawer.redraw();

					//primitive.zIndex = this.selectedPrimitives.length;
				},
				create:function(primitive){
					if (Array.isArray(primitive)) {
						this.visiable = true;
						this.selectedPrimitives = new Array();
						for (var i in primitive) {
							var p = primitive[i];							
							p.visible();
							if(!p.aabb){
								p.aabb = new AABB(p);
							}							
							this.selectedPrimitives.push(p);
						}
					} else {
						primitive.visible();
						if(!primitive.aabb){
							primitive.aabb = new AABB(primitive);
						}
						this.visiable = true;
						this.selectedPrimitives = new Array();
						this.selectedPrimitives.push(primitive);
					}
					//this.cleanTempGroupAction();//配合MOURSEMOVE记录历史操作
					this.initTempGroupAction();

					this.rtDrawer.redraw();			
				},
				
				draw : function() {
					for (var i in this.selectedPrimitives) {
						this.selectedPrimitives[i].aabb.redraw();
					}
				},
				redraw : function() {
					if (this.visiable) {
						for (var i in this.selectedPrimitives) {
							
							if(this.selectedPrimitives[i].statusTest()){
								this.selectedPrimitives[i].aabb.redraw();
								
							}

						}
						
						if(this.insideGroupMode&&!this.moving){
							this.insideGroup.aabb.redraw();
						}
						/*
						if(this.box.l){
						this.cxt.strokeText("l",this.box.l.x,this.box.l.y);
						this.cxt.strokeText("r",this.box.r.x,this.box.r.y);
						this.cxt.strokeText("t",this.box.t.x,this.box.t.y);
						this.cxt.strokeText("b",this.box.b.x,this.box.b.y);
						}*/
						
					}

					if(this.assists){
						for (var i in this.assists) {	
							//console.log("RtDrawing assists .."+this.assists[i].name);
							this.assists[i].redraw();
						}
					}


					this.testDraw();	

				},
				testDraw:function(){
					if(this.testDrawPoint){
						this.cxt.fillStyle="blue";
						this.cxt.fillText('新',this.testDrawPoint.x,this.testDrawPoint.y);
					}
						
				},
				addAssist:function(assist){
					if(!this.assists){
						this.assists=new Array();
					}
					this.assists.push(assist);
				}
				,
				resetAssist:function(){
					this.assists=null;
				},
				delete:function(){
					for (var i in this.selectedPrimitives) {
						this.selectedPrimitives[i].delete();
					}		

					this.rtDrawer.history.add(new RtAction("delete",this.selectedPrimitives));//加入历史记录
					this.selectedPrimitives=new Array();
				},
				group:function(){
					if(!this.visiable){
						console.log("没有选中的图形，不用组合");
						return false;
					}
					if(this.selectedPrimitives.length<=1){
						console.log("只有一个图形，不用组合");
						return false;											
					}
					if(this.insideGroupMode){
						console.log("组合内部模式，不能组合");
						return false;											
					}
					var rtLayer=this.selectedPrimitives[0].rtLayer;
					
					//组合
					var rtGroup=new RtGroup(rtLayer,this.selectedPrimitives);	
					
					//创建新的绘制图层
					this.create(rtGroup.parent);
					
				
				},
				groupCancel:function(){
					if(this.insideGroupMode){
						console.log("组合内部模式，不能组合");
						return false;											
					}
					var cancelFlag=false;
					var primitives=new Array();
					var groupPrimitives=new Array();
					for (var i in this.selectedPrimitives) {
						var primitive=this.selectedPrimitives[i];
						if(primitive.type=="RtGroup" ){
							var groups=primitive.cancel();
							for(var j in groups){
								primitives.push(groups[j]);						
							}
							groupPrimitives.push(primitive);
							cancelFlag=true;
						}else{
							primitives.push(primitive);
							console.log(primitive.name+"id:"+primitive.id+",非组合图形，不用取消组合");
						}

					}
					
					if(cancelFlag){
						console.log("groupCancel:"+primitives.length);
						var action=new RtAction("cancelGroup",groupPrimitives);//加入历史记录
						this.rtDrawer.history.add(action);	
						this.rtDrawer.drawingLayer.create(primitives);
					}
					
					
					
				},
				disabled : function() {
					//失效 取消aabb显示
					this.visiable = false;

					this.insideGroupMode=false;//组合内部模式
					this.insideGroup=null;//内部模式的组合元素
					//this.selectedBoxFunc=null;
					//this.selectedPrimitives=new Array();
					//选中的全部置为FALSE；
					this.rtDrawer.redraw();
				},
				
				contains:function(primitive){
					for (var i in this.selectedPrimitives) {
						var p = this.selectedPrimitives[i];
						if(p==primitive){
							return true;
						}
					}
					return false;
				},
				hasSelect : function(x, y) {
					
					if(this.visiable){
						for (var i in this.selectedPrimitives) {
							var p = this.selectedPrimitives[i];
							if (p.hasSelect(x, y)) {
								return p;
							}
					 	}					
					
					
					}
					
					this.selectedBoxFunc=null;		

					for (var i in this.rtDrawer.layers) {
						var layer = this.rtDrawer.layers[i];
						
						var p=layer.hasSelect(x, y);
						if (p) {
							return p;
						}
					}

					//this.selectedBoxFunc=null;			

					return false;

				},
				boxSelect:function(start,end){
					var nw=new Point(start.x,start.y);
					var ne=new Point(end.x,start.y);
					var se=new Point(end.x,end.y);
					var sw=new Point(start.x,end.y);
					var box=[nw,ne,se,sw];
					var primitives=this.hasBoxSelect(box);
					
					if(primitives){
						this.create(primitives);
						console.log("boxSelect true");
						//组件选中事件
						EventHub.emit("RtComponentsList.select");
						return true;
					}

					console.log("boxSelect false");
					return false;
				},
				hasBoxSelect : function(box) {
					/*
					for (var i in this.selectedPrimitives) {
						var p = this.selectedPrimitives[i];
						//if (p.visiable&&(!p.group||(p.group&&p.groupVisiable))&&p.hasSelect(x, y)) {
						if (p.hasBoxSelect(box)) {
							primitives.push(p);
						}
					}*/

					for (var i in this.rtDrawer.layers) {
						var layer = this.rtDrawer.layers[i];
						var p=layer.hasBoxSelect(box);
						if (p) {
							return p;
						}
					}
					
					return false;

				},
				stopMove : function(type) {
					this.moving = false;
					if(!this.selectedPrimitives){
						return;
					}	
					
					if(this.drawingGroupAction){
						this.rtDrawer.history.addTemporary();
						this.cleanTempGroupAction();
					}
					
					var action;
					if(this.selectedPrimitives.length>1){
						

						if(this.selectedBoxFunc&&baseContextIsLinePrimitive(this.selectedBoxFunc.primitive.type)&&type=="scale"){ //直线未对其他图形做联动
							action=new RtAction(type,this.selectedBoxFunc.primitive);//加入历史记录
							this.rtDrawer.history.add(action);	
						}else{
							action=new RtAction(type,this.selectedPrimitives);
							this.rtDrawer.history.add(action);
						}
						
					}else{
						//var primitive=this.selectedBoxFunc.primitive;
						//action=new RtAction(type,this.selectedBoxFunc.primitive);//加入历史记录
						action=new RtAction(type,this.selectedPrimitives[0]);//加入历史记录
						this.rtDrawer.history.add(action);					
					}

					for (var i in this.selectedPrimitives) {
						var p = this.selectedPrimitives[i];						
						p.stopMove();
					}
				
							
					if(this.insideGroupMode){
						this.insideGroup.reset(); 
						this.insideGroup.aabb.moveTo(this.insideGroup.rotate);					
					
						action.add(this.insideGroup,"insideGroup"); //添加
						this.rtDrawer.redraw();
					}

					
				},
				stopEdit : function(type) {
					/*在修改前保存drawingGroupAction,保障undo redo功能*/
					if(!this.selectedPrimitives){
						return;
					}				 
					
					if(this.selectedPrimitives.length>0){
						if(this.drawingGroupAction){
							this.rtDrawer.history.addTemporary();
							this.cleanTempGroupAction();
						}
						var action=new RtAction(type,this.selectedPrimitives);
						this.rtDrawer.history.add(action);
					}
					/*
					else{
						//var primitive=this.selectedBoxFunc.primitive;
						var action=new RtAction(type,this.selectedPrimitives[0]);//加入历史记录
						this.rtDrawer.history.add(action);					
					}
					*/
				},
				select:function(primitive){
					/*
					for(var i in this.selectedPrimitives){
						if(this.selectedPrimitives[i]==primitive){

							return;
						}
					}
					primitive.select();
					*/

					if (!window.RtCtrlKey) {
						if(this.visiable){
							for(var i in this.selectedPrimitives){
								if(this.selectedPrimitives[i]==primitive){
									return;
								}
							}
						}	
					
					}  

					if(!primitive.aabb){
			
						primitive.aabb = new AABB(primitive);
					}
					
					this.add(primitive);	
					
					//组件选中事件
					EventHub.emit("RtComponentsList.select");
				}	

				
				,
				//选择图元
				selectedbak : function(x, y) {
					
					var selectedFlag = false;
					var opFlag=false;
					/*
					if (this.visiable) {
						var primitive=this.hasSelect(x, y);
						if (primitive) {							
							opFlag = true;
							selectedFlag = true;
						} else {
							opFlag = false;
						}
					}*/

					//if (!opFlag) {
						for (var i in this.rtDrawer.layers) {
							var layer = this.rtDrawer.layers[i];
							var p=layer.hasSelect(x, y);
							if (p) {
								p.select();
								return p;
							}
						}
					//}

					return selectedFlag;

				},
				moveUp:function(){
					if(this.visiable){
						if(this.insideGroupMode){
							var historyArray=baseContextGroupMoveUp(this.insideGroup,this.selectedPrimitives);
							if(historyArray&&historyArray.length>0){
								var action=new RtAction("groupMoveUp",historyArray);
								this.rtDrawer.history.add(action);
							}
						}else{
							var historyArray=baseContextMoveUp(this.selectedPrimitives);
							if(historyArray&&historyArray.length>0){
								var action=new RtAction("moveUp",historyArray);
								this.rtDrawer.history.add(action);
							}
						}
						
						console.log(this.selectedPrimitives.length);
						this.rtDrawer.redraw();	
					}
				},
				moveDown:function(){
					if(this.visiable){
						if(this.insideGroupMode){
							var historyArray=baseContextGroupMoveDown(this.insideGroup,this.selectedPrimitives);
							if(historyArray&&historyArray.length>0){
								var action=new RtAction("groupMoveDown",historyArray);
								this.rtDrawer.history.add(action);
							}
						}else{
							var historyArray=baseContextMoveDown(this.selectedPrimitives);
							if(historyArray&&historyArray.length>0){
								var action=new RtAction("moveDown",historyArray);
								this.rtDrawer.history.add(action);
							}
						}
						
						this.rtDrawer.redraw();	
					}
				},
				moveTop:function(){
					if(this.visiable){
						if(this.insideGroupMode){
							var historyArray=baseContextGroupMoveTop(this.insideGroup,this.selectedPrimitives);
							if(historyArray&&historyArray.length>0){
								var action=new RtAction("groupMoveTop",historyArray);
								this.rtDrawer.history.add(action);
							}
						}else{
							var historyArray=baseContextMoveTop(this.selectedPrimitives);
							if(historyArray&&historyArray.length>0){
								var action=new RtAction("moveTop",historyArray);
								this.rtDrawer.history.add(action);
							}
						}
						this.rtDrawer.redraw();	
					}
				},
				moveBottom:function(){
					if(this.visiable){
						if(this.insideGroupMode){
							var historyArray=baseContextGroupMoveBottom(this.insideGroup,this.selectedPrimitives);
							if(historyArray&&historyArray.length>0){
								var action=new RtAction("groupMoveBottom",historyArray);
								this.rtDrawer.history.add(action);
							}
						}else{
							var historyArray=baseContextMoveBottom(this.selectedPrimitives);
							if(historyArray&&historyArray.length>0){
								var action=new RtAction("moveBottom",historyArray);
								this.rtDrawer.history.add(action);
							}
						}
						this.rtDrawer.redraw();	
					}
				},
				fillColor:function(color){
					
					if(this.visiable){
						for(var i in this.selectedPrimitives){
							var p=this.selectedPrimitives[i];
							if(p.style.fillStyle!=color){
								p.fillColor(color);								
							}							
						}

						//添加历史记录
						
						this.stopEdit("fillColor");
						this.rtDrawer.redraw();
					}			
					
				}
				,
				strokeColor:function(color){					
					if(this.visiable){
						for(var i in this.selectedPrimitives){
							var p=this.selectedPrimitives[i];
							if(p.style.strokeStyle!=color){
								p.strokeColor(color);								
							}
						}
						//添加历史记录
						this.stopEdit("strokeColor");
						this.rtDrawer.redraw();
					}			
					
				} 
				,
				setLineWidth:function(width){					
					if(this.visiable){
						for(var i in this.selectedPrimitives){
							var p=this.selectedPrimitives[i];
							if(p.style.lineWidth!=width){								
								p.setLineWidth(width);													
							}
						}
						//添加历史记录
						this.stopEdit("setLineWidth");		
						this.rtDrawer.redraw();
					}			
					
				},
				setFontStyle:function(fontStyle){					
					if(this.visiable){
						for(var i in this.selectedPrimitives){
							var p=this.selectedPrimitives[i];
							if(p.style.fontStyle!=fontStyle){
								p.setFontStyle(fontStyle);																
							}
						}
						//添加历史记录
						this.stopEdit("setFontStyle");		
						this.rtDrawer.redraw();
					}			
					
				},
				
				setLineDashType:function(segments){
					if(this.visiable){
						for(var i in this.selectedPrimitives){
							var p=this.selectedPrimitives[i];
							if(p.style.lineDash!=segments){								
								p.setLineDashType(segments);														
							}
						}
						//添加历史记录
						this.stopEdit("setLineDashType");		
						this.rtDrawer.redraw();
					}			
				},
				moveAlign:function(method){									
					if(this.visiable&&this.selectedPrimitives&&this.selectedPrimitives.length>1){	
						var param='LRTB',lrtb,type;
						if(method=='leftAlign'){
							lrtb='l';type='x';
						}else if(method=='rightAlign'){
							lrtb='r';type='x';
						}else if(method=='topAlign'){
							lrtb='t';type='y';
						}else if(method=='bottomAlign'){
							lrtb='b';type='y';
						}
						baseContextCalcLRTB(this,this.selectedPrimitives);
						for(var i in this.selectedPrimitives){
							 var p =this.selectedPrimitives[i];
							 if(p[param][lrtb][type]==this[param][lrtb][type]){
									continue;
							 }							 
							 p[method](this[param][lrtb]);
						}
						//添加历史记录
						this.stopMove(method);

						this.rtDrawer.redraw();

					}

					
				},
				moveCenterAlign:function(method){				
				
					if(this.visiable&&this.selectedPrimitives&&this.selectedPrimitives.length>1){	
						baseContextCalcLRTB(this,this.selectedPrimitives);

						for(var i in this.selectedPrimitives){
							 var p =this.selectedPrimitives[i];
						     							 
							 p[method](this.LRTB.center);
						}
						//添加历史记录
						this.stopMove(method);

						this.rtDrawer.redraw();
						
					}
				},
				horizontallyAvg:function(){
					if(this.visiable&&this.selectedPrimitives&&this.selectedPrimitives.length>2){	
						baseContextSortDataLRTB(this.selectedPrimitives,"x");
						
						var step=0;
						for(var i=0,l=this.selectedPrimitives.length;i<l;i++){
							 var p =this.selectedPrimitives[i];
						     if(i!=l-1){
								step+=this.selectedPrimitives[i+1].LRTB.l.x-p.LRTB.r.x;
							 }
						}
						step=(step/(this.selectedPrimitives.length-1)).toFixed(4);
						step=parseFloat(step);

						for(var i=0,l=this.selectedPrimitives.length;i<l;i++){
							 var p =this.selectedPrimitives[i];
						     if(i!=l-1){
								this.selectedPrimitives[i+1].leftAlign(new Point(p.LRTB.r.x+step,p.LRTB.l.y));
							 }
						}
						//添加历史记录
						this.stopMove("horizontallyAvg");
						this.rtDrawer.redraw();
						
					}
				},
				verticallyAvg:function(){
					if(this.visiable&&this.selectedPrimitives&&this.selectedPrimitives.length>2){	
						baseContextSortDataLRTB(this.selectedPrimitives,"y");
						
						var step=0;
						for(var i=0,l=this.selectedPrimitives.length;i<l;i++){
							 var p =this.selectedPrimitives[i];
						     if(i!=l-1){
								step+=this.selectedPrimitives[i+1].LRTB.t.y-p.LRTB.b.y;
							 }
						}
						step=(step/(this.selectedPrimitives.length-1)).toFixed(4);
						step=parseFloat(step);

						for(var i=0,l=this.selectedPrimitives.length;i<l;i++){
							 var p =this.selectedPrimitives[i];
						     if(i!=l-1){
								this.selectedPrimitives[i+1].topAlign(new Point(p.LRTB.b.x,p.LRTB.b.y+step));
							 }
						}
						//添加历史记录
						this.stopMove("verticallyAvg");
						this.rtDrawer.redraw();
						
					}
				},	
				keyMove:function(method){									
					if(this.visiable&&this.selectedPrimitives&&this.selectedPrimitives.length>0){	
						var dx,dy;
						if(method=='left'){
							dx=-1;
							dy=0;							
						}else if(method=='right'){
							dx=1;
							dy=0;
						}else if(method=='up'){
							dx=0;
							dy=-1;
						}else if(method=='down'){
							dx=0;
							dy=1;
						}
						var moveMatrix=[[1,0,0],[0,1,0],[dx,dy,1]];
						 
						for(var i in this.selectedPrimitives){
							var p =this.selectedPrimitives[i];
							 //baseContextMoveAlign(p,moveMatrix); 
							 //ZQ EDIT 20190310
							 p.keyMove(moveMatrix);
						}
						//添加历史记录
						this.stopMove("move");

						this.rtDrawer.redraw();

					}

					
				},
				copy:function(){
					if(this.visiable){
						var primitives=new Array();					

						for(var i = this.selectedPrimitives.length-1;i>=0;i--){
							var p=this.selectedPrimitives[i];
							primitives.push(p.copy());							
						}	
						return primitives;
					}		
				
				},
				locked:function(){
					if(this.visiable){
						for(var i in this.selectedPrimitives){
							var p=this.selectedPrimitives[i];
							p.locked();
						}
						//添加历史记录
						this.stopEdit("locked");		
						this.rtDrawer.redraw();
					}	
				},
				unlocked:function(){
					if(this.visiable){
						for(var i in this.selectedPrimitives){
							var p=this.selectedPrimitives[i];
							p.unlocked();
						}
						//添加历史记录
						this.stopEdit("unlocked");		
						this.rtDrawer.redraw();
					}	
				},
				selectPrimitive:function(primitive){
					if(!primitive.groups||!primitive.groups.group){						
						this.create(primitive);
					}else{
						this.insideGroupMode=true;//组合内部模式
						this.insideGroup=primitive.groups.group;//内部模式的组合元素
						this.create(primitive);
					}
										
				}
			};