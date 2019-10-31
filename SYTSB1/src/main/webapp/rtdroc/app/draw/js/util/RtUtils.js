	function aobRotate(A,O,B){		
		var a=Math.atan2(A.x-O.x,-(A.y-O.y));
		var b=Math.atan2(B.x-O.x,-(B.y-O.y));
		var r=b-a;
		//console.log("弧度："+r);
		//console.log("角度："+(r*180/Math.PI));
		return r;
	}

	function abRotate(A,B){		
		var dx=B.x-A.x;
		var dy=B.y-A.y;
		var r=Math.atan2(dy,dx);
		console.log("abRotate弧度："+r);
		//console.log("角度："+(r*180/Math.PI));
		return r;
	}
	
	function min(a,b){
		if(a>b){
			a=b;
		}
		return a;
	}
	function max(a,b){
		if(a<b){
			a=b;
		}
		return a;
	}

	function minPoint(a,b,type){
		if(a[type]>b[type]){
			return b;
		}
		return a;
	}
	function maxPoint(a,b,type){
		if(a[type]<b[type]){
			return b;
		}
		return a;
	}

	function minPointInArray(points,type){
		var t;
		for(var i =0;i<points.length;i++){
			var point=points[i];
			if(i==0){
				t=point;
			}else{
				t=minPoint(t,point,type);
			}
		}
		return t;
	}

	function maxPointInArray(points,type){
		var t;
		for(var i =0;i<points.length;i++){
			var point=points[i];
			if(i==0){
				t=point;
			}else{
				t=maxPoint(t,point,type);
			}
		}
		return t;
	}

	
	//查看两弧度大致相等，以1/3度为准，1度(°)=0.0174533弧度(rad) 1/3=0.0058177666666667
	function equalApproximat(param1,param2){
		var min=0.0058177666666667;
		if(Math.abs(param1-param2)<min){
			return true;
		}
		return false;
	}

	
 function rotateScale(cos,sin,center,scaleMatrix){ 
	//var  cos = Math.cos(r), sin = Math.sin(r);
	var  m=center.x,n=center.y;
	//旋转到与X平行
	var r1=[[cos,sin,0],[-sin,cos,0],[m-m*cos+n*sin,n-m*sin-n*cos,1]];							
	var b=MatrixMultiply(r1,scaleMatrix);								
	//旋转回来			
	var r2=[[cos,-sin,0],[sin,cos,0],[m-m*cos-n*sin,n+m*sin-n*cos,1]];	
	var c=MatrixMultiply(b,r2);
	return c;
 }

//计算顺时针旋转的角度
function clockwiseRotate(primitive) {	
	/*
	var O=primitive.box.center;
	//console.log("rotate center:"+this.center.x+","+this.center.y);
	var B=primitive.box.n;					
	var A=new Point(O.x,O.y-1);
	var r=aobRotate(A,O,B); //util.js
	*/
	if(!primitive.box.n){
		return null;
	}
	var A=primitive.box.n;
	var O=primitive.box.center;

	var B=new Point(O.x,O.y-1);
	var r=aobRotate(A,O,B);
	return r;				
}



function calcDataLRTB(points,start) {
	var obj={};

	for (var i = 0; i < points.length; i++) {
		var point = points[i];
		 if(point.x==start.x&&point.y==start.y){
			continue; 
		}
		if (i == 0) {
			//left
			obj.l = point.x;
			//right
			obj.r= point.x;
			//top
			obj.t= point.y;
			//bottom
			obj.b= point.y;

		} else {
			if (point.x < obj.l) {
				obj.l = point.x;
			}

			if (point.x > obj.r) {
				obj.r = point.x;
			}

			if (point.y < obj.t) {
				obj.t = point.y;
			}

			if (point.y > obj.b) {
				obj.b = point.y;
			}
		}
	}
	return obj;
	
}

	//=======================
	function drawGrid(cxt, color, stepx, stepy) {
	   cxt.save()

	   cxt.strokeStyle = color;
	   cxt.fillStyle = '#ffffff';
	   cxt.lineWidth = 0.5;
	   cxt.fillRect(0, 0, cxt.canvas.width, cxt.canvas.height);
	   cxt.globalAlpha = 0.1;

	   cxt.beginPath();
	   for (var i = stepx + 0.5; i < cxt.canvas.width; i += stepx) {
		 cxt.moveTo(i, 0);
		 cxt.lineTo(i, cxt.canvas.height);
	   }
	   cxt.stroke();

	   cxt.beginPath();
	   for (var i = stepy + 0.5; i < cxt.canvas.height; i += stepy) {
		 cxt.moveTo(0, i);
		 cxt.lineTo(cxt.canvas.width, i);
	   }
	   cxt.stroke();

	   cxt.restore();
	}

	