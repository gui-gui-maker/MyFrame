/*图元 */

	function Primitive(data, addToLayer) {
		this.name = "Primitive";		
		if(data){
			this.data = data; 
			this.type = data.name; //RTRECT RTPEN etc.

			this.rtLayer = this.data.rtLayer;
			baseContext.call(this, this.rtLayer); 

			if(data.start){
				this.center=new Point(data.start.x,data.start.y);
				this.start=new Point(data.start.x,data.start.y);
				this.end=new Point(data.start.x,data.start.y);			
			}
		}		

		this.LRTB={};
		this.box={};//包围盒
		this.matrix;//变幻矩阵

		//是否可见
		this.visiable = true;
		//层级 
		//this.zIndex;
		//是否移动中
		this.moving = false;
		//是否锁定
//		this.style.isLocked=false;

		//加入图层
		if (addToLayer) {
			this.rtLayer.add(this);
		}			
		
	}


	Primitive.prototype = {
		//重绘
		redraw : function() {
			baseContextRestore(this);
			if(this.data.redraw){
				this.data.redraw();
				return;
			}
			this.cxt.beginPath();
			for (var i in this.data.points) {
				var point = this.data.points[i];
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
				

				if(this.data.calcAABB){
					this.data.calcAABB();
					return ;
				}
				//上下左右
				baseContextCalcDataLRTB(this,this.data.points);		
				//包围盒
				baseContextInitBox(this);
			
				

		},
		transform:function(matrix){		
			baseContextTransform(this,matrix);
			if(baseContextIsLinePrimitive(this.type)){
				this.data.transform();
			}
		},

		//移动
		moveTo : function(dx,dy,moveMatrix) {
			
			//baseContextRestore(this);
			if(!moveMatrix){
				moveMatrix=[[1,0,0],[0,1,0],[dx,dy,1]];//平移矩阵
			}
						
			//图形平移
			this.transform(moveMatrix);
	
			
			//包围盒平移
			if(this.aabb){
				this.aabb.moveTo(moveMatrix);
			}	
			
			//组合有单独的moveTo
			if(this.data.moveTo){
				this.data.moveTo(dx,dy,moveMatrix);				
			}
			
			this.moving = true;//控制旋转按钮是否出现
			this.changeType="move";
			return moveMatrix;
			
			if(!moveMatrix){
				moveMatrix=[[1,0,0],[0,1,0],[dx,dy,1]];//平移矩阵
			}			
			 
		},
		  	//鼠标拖动伸缩变形
		scaleTo : function(type,x, y,offset) {
			
			//console.log("scaleTo x:"+x+",y:"+y);
			if(!this.aabb){
				alert("Primitive scale error ,not find binding box...");
				return ;
			}			
			
			
			var functionRect=this.aabb.cursorFunctionRects[type];
			 	
			
			if(baseContextIsLinePrimitive(this.type)&&!offset){				
				this.data.scaleTo(functionRect.start,new Point(x,y));
				this.aabb.scaleTo();	
			}else{
				if(baseContextIsLinePrimitive(this.type)){
					//直线被其他非组合元素联动 按其他元素变换矩阵做变换
					functionRect=this.drawingLayer.selectedBoxFunc.aabb.cursorFunctionRects[type];  
				} 

				
				var start=functionRect.start;	
			
				var cross=functionRect.cross;
				var end=new Point(x,y);
			
				//比例矩阵
				var scaleMatrix=baseContextCalcOffset(functionRect,cross,start,end,offset);//baseContext.js
				var scaleMatrixTemp=MatrixCopy(scaleMatrix);
				if(this.finalRotate&&this.finalRotate!=0&&functionRect.aabb.boxType!="line"){						
					scaleMatrix=baseContextRotateScale(scaleMatrix,functionRect.cross,this);//baseContext.js
				}	
				/*
				*/	

				//图形比例伸缩
				this.transform(scaleMatrix);
				this.aabb.scaleTo();				
				
				if(!baseContextIsLinePrimitive(this.type)){	//直线不联动其他图形
					this.offset={Sx:scaleMatrix[0][0],Sy:scaleMatrix[1][1]};
				}else{
					this.offset=null;
				}
				
				
			
			}

			//组合有单独的moveTo
			if(this.data.scaleTo){
				this.data.scaleTo(scaleMatrix,scaleMatrixTemp);
			}


			this.moving = true;
			this.changeType="scale";
			

			return this.offset;
		},
		groupScaleTo :function(scaleMatrix,scaleMatrixTemp){
			this.groupVisable=false;		

			//图形比例伸缩
			this.transform(scaleMatrix);
			
			if(this.aabb){
				this.aabb.scaleTo();
			}
			
			if(this.data.groupScaleTo){
				this.data.groupScaleTo(scaleMatrix,scaleMatrixTemp);
			}

			this.moving = true;
			this.changeType="scale";	
		}
		
		,
		//鼠标拖动旋转弧度r
		rotateTo : function(x,y,rotate) {
			if(!this.aabb){
				alert("Primitive rotate error ,not find binding box...");
				return ;
			}
			
			//计算旋转角度
			var A=this.box.n;
			var O=this.box.center;
            if(rotate){
				this.rotate=rotate;
			}else{
				var B=new Point(x,y);
				this.rotate=aobRotate(A,O,B);
			}

			//console.log("this.rotate:"+this.rotate*180/Math.PI);
			var cos = Math.cos(this.rotate), sin = Math.sin(this.rotate);
			var m=O.x;
			var n=O.y;
			var rotateMatrix=[[cos,sin,0],[-sin,cos,0],[m-m*cos+n*sin,n-m*sin-n*cos,1]];
			
				
			//图形旋转
			this.transform(rotateMatrix);				
			
			//包围盒旋转
			this.aabb.rotateTo(rotateMatrix);

			if(this.data.rotateTo){
				this.data.rotateTo(rotateMatrix);
			}

			this.changeType="rotate";
			return this.rotate;
		},
		groupRotateTo :function(rotateMatrix){
			this.groupVisable=false;		

			//图形比例伸缩
			this.transform(rotateMatrix);

			//包围盒旋转
			if(this.aabb){
				this.aabb.rotateTo(rotateMatrix);
			}
			
			if(this.data.groupRotateTo){
				this.data.groupRotateTo(rotateMatrix);
			}
			
			//this.aabb.rotateTo();
			this.moving = true;
			this.changeType="scale";	
		}
		,
		//停止移动
		stopMove : function() {
			
			this.moving = false;
			if(this.aabb){
				this.aabb.stopMove();
			}
			
		
			this.data.matrix=this.matrix;
			if(this.data.points){
				baseContextCalcDataLRTB(this,this.data.points);		
			}

			if(this.data.stopMove){
				this.data.stopMove();
			}
		   
			if(this.groups&&this.groups.center){//参见RtGroup.js ScaleTo方法
				this.groups.center=null;
			}		

			baseContextCalcRotate(this);
			//console.log("primitive stopMove this.rotate:"+this.finalRotate);
		},			
		stopDraw:function(loc){
			if(loc){
				this.end.x=loc.x;
				this.end.y=loc.y;
			}
			
			//如果只是误点 则不做记录,自定义和文本除外	
			if(loc&&this.type!="RtText"&&Math.abs(this.start.x-this.end.x)<3&&Math.abs(this.start.y-this.end.y)<3){
				this.rtLayer.remove(this);
				return ;
			}
			this.calcAABB();
			
			this.rtDrawer.history.add(new RtAction("draw",this));//加入历史记录			
		},
		//是否选中
		hasSelect : function(x, y) {
			if(this.style.isLocked){
				return false;
			}
			var selectedBoxFunc;
			if(this.aabb){
				if(this.drawingLayer.visiable&&this.drawingLayer.selectedPrimitives.indexOf(this)>-1){
					//选中的情况下判定
					selectedBoxFunc=this.aabb.hasSelect(x, y);
					if (selectedBoxFunc) {
						this.drawingLayer.selectedBoxFunc=selectedBoxFunc;	
						return selectedBoxFunc;
					}				
				}
				
			}
			//未选中的情况下判定
			if(this.data.hasSelect){
				return this.data.hasSelect(x,y);
			}
			return baseContextHasSelect(this,x,y);
			
			
			//return false;
								
		},
		//是否选中
		hasBoxSelect : function(box) {
			if(this.style.isLocked){
				return false;
			}
			
			if(this.data.hasBoxSelect){
				if( this.data.hasBoxSelect(box)){
					return this;
				}
				return false;
			}

			if( baseContextHasBoxSelect(this,box)){
				return this;
			}		
			return false;
								
		},
			
		//是否组合内选中
		hasInSideGroupSelect : function(x, y) { //event.js testIsInsideGroup

			for (var i = this.data.primitives.length-1; i >=0;i--) {
				var p = this.data.primitives[i];
				if(p.hasSelect(x,y)){
					return p;
				}
			}
			return false;

		
		},
		//备份数据，为变形存储数据
		backup : function() {
			this.data.backup();

		},
		moveUp:function(){
			return baseContextMoveUp(this);		
		},
		moveDown:function(){
			return baseContextMoveDown(this);
		},
		moveTop:function(){
			return baseContextMoveTop(this);
		},
		moveBottom:function(){
			return baseContextMoveBottom(this);
		},

		delete:function(){
			//this.visiable=false;
			this.del=true;
		},
		visible:function(){
			if(this.groups){
				this.groups.visiable=true;
			}else{
				this.visiable=true;
			}					
		},
		visibleTest:function(){
			if(!this.del&&this.visiable){
				return true;
			}				
			return false;
		},
		statusTest:function(){
			//（没有被组合必须可见，或者在组合中可见），未被删除
			//if(this.visiable&&!this.del&&(!this.group||(this.group&&this.groupVisiable))){
			//if(this.visiable&&!this.del){
			if(!this.drawingLayer.insideGroupMode&&this.groups){
				this.groups.visiable=false;
			}
			if(((!this.groups&&this.visiable)||(this.groups&&this.groups.visiable))&&!this.del){
				return true;
			}
			return false;
		},
		undo:function(matrix,historyPoint){
			this.matrix=matrix;
			if(this.data.undo){
				this.data.undo(matrix,historyPoint);
				return;
			}
			//this.data.undo(matrix,historyPoint); 未完成
			baseContextUndoRedo(this,matrix);
			if(this.aabb){
				this.aabb.refresh();
			}
			
			//this.drawingLayer.create(this);
		},
		redo:function(matrix,historyPoint){
			this.matrix=matrix;
			if(this.data.redo){
				this.data.redo(matrix,historyPoint);
				return;
			}
			//this.data.redo(matrix,historyPoint); 未完成
			baseContextUndoRedo(this,matrix);
			if(this.aabb){
				this.aabb.refresh();
			}
			//this.drawingLayer.create(this);
		},
		cancel:function(){
			//取消组合
			return this.data.cancel();
		}
		,
		buildGroup:function(group){
			this.groups={};
			this.groups.group=group;
			this.groups.visiable=false;
           
			this.setVisiable(false);

		},
		cancelGroup:function(){
			this.groups=null;
			 
			this.setVisiable(true);

		},
		reset:function(){
			var r=this.finalRotate;
			var r1;
			var r2;
			if(r){
				var cos = Math.cos(r), sin = Math.sin(r);
				var  m=this.box.center.x,n=this.box.center.y;
				//旋转到与X平行
				r1=[[cos,sin,0],[-sin,cos,0],[m-m*cos+n*sin,n-m*sin-n*cos,1]];
				r2=[[cos,-sin,0],[sin,cos,0],[m-m*cos-n*sin,n+m*sin-n*cos,1]];
			}
			

			baseContextCalcLRTB(this,this.data.primitives,r1);
			
		
			//包围盒
			var offset=2*6;//偏移量
			baseContextInitBox(this,offset,"old");	
			this.obox=baseContextBoxClone(this.box);
			this.matrix=null;
			if(r2){
				baseContextTransform(this,r2);
			}
			
	
		},
		disable:function(){
			this.visiable=false;
			if(this.data.disable){
				return this.data.disable();
			}
			
			//this.rtDrawer.redraw();
		}
		,
		enable:function(){
			this.visiable=true;
			if(this.data.enable){
				return this.data.enable();
			}
			
			//this.drawingLayer.create(this);
		},
		setVisiable:function(flag){
			this.visiable=flag;
		},
		visible:function(){
			if(this.groups){
				this.groups.visiable=true;
			}else{
				this.visiable=true;
			}					
		},
		strokeColor:function(color){					
			this.style.strokeStyle=color;
			if(this.data.strokeColor){
				this.data.strokeColor(color);
			}
		}, 
		fillColor:function(color){		
			this.style.fillStyle=color;
			if(this.data.fillColor){
				this.data.fillColor(color);
			}
		},
		setLineWidth:function(width){
			this.style.lineWidth=width;
			if(this.data.setLineWidth){
				this.data.setLineWidth(width);
			}
		},
		setFontStyle:function(fontStyle){
			this.style.font=fontStyle;
			if(this.data.setFontStyle){
				this.data.setFontStyle(fontStyle);
			}
		},
		setLineDashType:function(lineDash){
			this.style.lineDash=lineDash;
			if(this.data.setLineDashType){
				this.data.setLineDashType(lineDash);
			}
		},
		copy:function(){
			var copyData=this.data.copy();
			var p=copyData.parent;
			baseContextPrimitiveClone(p,this);
			
			var moveMatrix=[[1,0,0],[0,1,0],[5,5,1]];//平移矩阵 5
			baseContextMoveAlign(p,moveMatrix);	
			
			//baseContextStyleClone(this,p);
			//p.calcAABB();

			//data.parent.init(); //未完成
			return copyData.parent;
		},
		
		leftAlign:function(point,moveMatrix){
			if(!moveMatrix){
				var dx=point.x-this.LRTB.l.x;
				moveMatrix=[[1,0,0],[0,1,0],[dx,0,1]];//平移矩阵
			}
			if(this.data.leftAlign){
				this.data.leftAlign(point,moveMatrix);
			}
			baseContextMoveAlign(this,moveMatrix);			
		},
		rightAlign:function(point,moveMatrix){
			if(!moveMatrix){
				var dx=point.x-this.LRTB.r.x;
				moveMatrix=[[1,0,0],[0,1,0],[dx,0,1]];//平移矩阵
			}
			if(this.data.rightAlign){
				this.data.rightAlign(point,moveMatrix);
			}
			
			baseContextMoveAlign(this,moveMatrix);	
		}
		,
		topAlign:function(point,moveMatrix){			
			if(!moveMatrix){
				var dy=point.y-this.LRTB.t.y;
				moveMatrix=[[1,0,0],[0,1,0],[0,dy,1]];//平移矩阵
			}
			if(this.data.topAlign){
				this.data.topAlign(point,moveMatrix);
			}
			
			baseContextMoveAlign(this,moveMatrix);				
		},
		bottomAlign:function(point,moveMatrix){
			if(!moveMatrix){
				var dy=point.y-this.LRTB.b.y;
				moveMatrix=[[1,0,0],[0,1,0],[0,dy,1]];//平移矩阵
			}
			if(this.data.bottomAlign){
				this.data.bottomAlign(point,moveMatrix);
			}
			
			baseContextMoveAlign(this,moveMatrix);	
		},
		horizontallyAlign:function(point,moveMatrix){
			if(!moveMatrix){
				var dx=point.x-this.box.center.x;
				moveMatrix=[[1,0,0],[0,1,0],[dx,0,1]];//平移矩阵
			}
			if(this.data.horizontallyAlign){
				this.data.horizontallyAlign(point,moveMatrix);
			}
			
			baseContextMoveAlign(this,moveMatrix);	
		},
		verticallyAlign:function(point,moveMatrix){
			if(!moveMatrix){
				var dy=point.y-this.box.center.y;
				moveMatrix=[[1,0,0],[0,1,0],[0,dy,1]];//平移矩阵
			}
			if(this.data.verticallyAlign){
				this.data.verticallyAlign(point,moveMatrix);
			}
			
			baseContextMoveAlign(this,moveMatrix);	
		},
		centerAlign:function(point,moveMatrix){
			if(!moveMatrix){
				var dx=point.x-this.box.center.x;
				var dy=point.y-this.box.center.y;
				moveMatrix=[[1,0,0],[0,1,0],[dx,dy,1]];//平移矩阵
			}
			if(this.data.centerAlign){
				this.data.centerAlign(point,moveMatrix);
			}
			
			baseContextMoveAlign(this,moveMatrix);	
		},
		// ZQ ADD 20190310
		keyMove:function(moveMatrix){
			if(moveMatrix){
				if(this.data.keyMove){
					this.data.keyMove(moveMatrix);
				}
				baseContextMoveAlign(this,moveMatrix);	
			}else{
				console.log("keyMove error null moveMatrix...");	
			}
		},
		// ZQ ADD 20190310
		locked:function(){
			this.style.isLocked=true;
		},
		unlocked:function(){
			this.style.isLocked=false;
		},

		save:function(){
			var rtData=this.data;
			var p={},data={};				
			p.id=this.id;
			p.name=this.name;
			p.type=this.type;
			p.style=this.style;
			p.box=this.box;
			p.obox=this.obox;
			p.LRTB=this.LRTB;
			p.matrix=this.matrix;
			if(this.groupType){
				p.groupType=this.groupType;
			}
			
			p.finalRotate=this.finalRotate;
			if(p.finalRotate){
				p.finalCos=this.finalCos;
				p.finalSin=this.finalSin;
			}
			
			p.data=data;
			
			data.name=rtData.name;
			data.start=rtData.start;
			data.end=rtData.end;
			data.center=rtData.center;
			data.points=rtData.points;				
			data.matrix=rtData.matrix;
			if(rtData.text){
				data.text=rtData.text;
				data.lineHeight=rtData.lineHeight;
			}
			if(rtData.groupType){
				data.groupType=rtData.groupType;
			}
			
			if(rtData.img&&rtData.img.src){
				data.img={};
				data.img.src=rtData.img.src;
				console.log("data.img.src:"+data.img.src);
			}

			if(this.type=="RtGroup"){
				data.primitives=this.data.save();
			}
			return p;
		},
		imp:function(cRtLayer,primitive){
			//this.rtLayer=cRtLayer;
			
			if(primitive.type=="RtGroup"){
				var cRrGroup=new RtGroup(cRtLayer);
				cRrGroup.imp(primitive.data.primitives);
				var p=cRrGroup.parent;
				cRrGroup.rtLayer.add(p);
				
				baseContextPrimitiveClone(p,primitive);
//				p.calcAABB();
				return p;
			}else{
				var data=primitive.data;//真实图形
				var fun=eval(data.name);
				if(!data.start){
					console.log("imp shape ["+data.name+"] data  error  : null start point  ---> "+data.id);
					return false;
				}
				var rtData=new fun(cRtLayer,data.start,true);
				rtData.imp(data);
				var p=rtData.parent;
				
				

				baseContextPrimitiveClone(p,primitive);
//				p.calcAABB();
				return p;
			}
			return false;
			
		}


	};
	
	