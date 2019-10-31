
//计算SHAPE左右上下边界点
function baseContextCalcDataLRTB(obj,primitives) {
	
	for (var i = 0; i < primitives.length; i++) {
		var point = primitives[i];
		if(obj.matrix&&!baseContextIsLinePrimitive(obj.type)){// EDIT 20190310解决直线movenALIGN错误问题
			//debugger;
			point=MatrixMultiplyPoint(point,obj.matrix);
		}
		if (i == 0) {
			//left
			obj.LRTB.l = point.clone();
			//right
			obj.LRTB.r= point.clone();
			//top
			obj.LRTB.t= point.clone();
			//bottom
			obj.LRTB.b= point.clone();

		} else {
			if (point.x < obj.LRTB.l.x) {
				obj.LRTB.l = point.clone();
			}

			if (point.x > obj.LRTB.r.x) {
				obj.LRTB.r = point.clone();
			}

			if (point.y < obj.LRTB.t.y) {
				obj.LRTB.t = point.clone();
			}

			if (point.y > obj.LRTB.b.y) {
				obj.LRTB.b = point.clone();
			}
		}
	}
	
	
}

function baseContextCalcLRTB(obj,primitives,rotateMatrix) {
   if(!obj.LRTB){
	  obj.LRTB={};//rtdrawingLayer
   }
	for (var i = 0; i < primitives.length; i++) {
		var p = primitives[i];	
		//baseContextCalcDataLRTB(p,p.data.points);
		if(rotateMatrix){
			var temp={};
			temp.LRTB={};
			for(var j in p.LRTB ){
				temp.LRTB[j]=MatrixMultiplyPoint(p.LRTB[j],rotateMatrix);
				
			}
			p=temp;
		}
		if (i == 0) {
			//left
			obj.LRTB.l =p.LRTB.l.clone();
			//right
			obj.LRTB.r= p.LRTB.r.clone();
			//top
			obj.LRTB.t= p.LRTB.t.clone();
			//bottom
			obj.LRTB.b= p.LRTB.b.clone();

		} else {
			if (p.LRTB.l.x < obj.LRTB.l.x) {
				obj.LRTB.l = p.LRTB.l.clone();
			}

			if (p.LRTB.r.x > obj.LRTB.r.x) {
				obj.LRTB.r = p.LRTB.r.clone();
			}

			if (p.LRTB.t.y < obj.LRTB.t.y) {
				obj.LRTB.t = p.LRTB.t.clone();
			}

			if (p.LRTB.b.y > obj.LRTB.b.y) {
				obj.LRTB.b = p.LRTB.b.clone();
			}
		}
	} 
	obj.LRTB.center=new Point(obj.LRTB.l.x + (obj.LRTB.r.x- obj.LRTB.l.x) / 2,obj.LRTB.t.y + (obj.LRTB.b.y-obj.LRTB.t.y) / 2);	
	
}


//中心对齐
function baseContextCenterAlign(primitive,point){
	var dx=point.x-primitive.box.center.x;
	var dy=point.y-primitive.box.center.y;
	var moveMatrix=[[1,0,0],[0,1,0],[dx,dy,1]];//平移矩阵
	baseContextMoveAlign(primitive,moveMatrix);					
}

//上下左右对齐
function baseContextMoveAlign(primitive,moveMatrix){
	//图形平移
	primitive.transform(moveMatrix);
	if(primitive.type=="RtGroup"){
		if(primitive.aabb){
			primitive.aabb.moveTo();	
		}

		baseContextCalcLRTB(primitive,primitive.data.primitives);
	}else{
		//primitive.parent.matrix=data.matrix;
		//包围盒平移
		if(primitive.aabb){
			primitive.aabb.moveTo();
		}

		baseContextCalcDataLRTB(primitive,primitive.data.points);
	}
}

