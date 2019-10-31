

//矩阵乘法
function MatrixMultiply(a,b){
	if(!a||!b||!a[0]||!b[0]){
		return null;
	}
	if(a[0].length!=b.length){ //非法矩阵乘
		console.log( "非法矩阵乘");
		return null;
	}
 
	var c=new Array(a.length);
	for(var t=0;t<c.length;t++){
		c[t]=new Array(b[0].length);
	}
	for(var i=0; i!=c.length; ++i){
		 for(var j=0; j!=c[0].length; ++j){
			   for(var k=0; k!=a[0].length; ++k){
				    if(!c[i][j]){
						c[i][j]=0;
					}
				    c[i][j]+=a[i][k]*b[k][j];
			   }
         }  
	}  
	return c;
	 
}

//点乘矩阵 转换为齐次坐标 结果为点
function MatrixMultiplyPoint(point,b){	
	if(!point||!b){
		return null;
	}
	var a=[[point.x,point.y,1]];

	var c=MatrixMultiply(a,b);
	var p=new Point(c[0][0],c[0][1]);
	return p;	 
}

//点的数组乘矩阵 转换为点乘 结果为点的数据
function MatrixMultiplyArray(polygon,b){	
	if(!polygon){
		return null;
	}
	if(!b){
		return polygon;
	}
	var c=[];
	for(var i in polygon){
		var p=MatrixMultiplyPoint(polygon[i],b);
		c.push(p);
	}
	return c;	 
}

//矩阵加法
function MatrixAdd(a,b){
	if(!a||!b||!a[0]||!b[0]){
		console.log("MatrixAdd error null ");
		return null;
	}
	if(a[0].length!=b.length){ //非法矩阵加 
		console.log("MatrixAdd error");
		return null;
	}
 
	var c=new Array(a.length);
	for(var t=0;t<c.length;t++){
		c[t]=new Array(b[0].length);
	}
	for(var i=0; i<c.length; i++){
		 for(var j=0; j<c[0].length; j++){ 
			 if(!c[i][j]){
				c[i][j]=0;
			 }
			 c[i][j]=a[i][j]+b[i][j];			  
         }  
	}  
	return c;
	 
}

//矩阵复制 用于记录历史动作
function MatrixCopy(a){
	if(!a||!a[0]){
		console.log("MatrixCopy error null ");
		return null;
	}
	var b =new Array();
	
	for(var i in a){
		b[i]=new Array(a[i].length);
		for(var j in a[i] ){
			b[i][j]=a[i][j];
		}
	}
	//MatrixPrint(b);
	return b;
	 
}

//打印矩阵 
function MatrixPrint(a){
	for(var i in a){
		var log="[";
		for(var j in a[i]){
			if(a[i][j].toFixed(2)>=0){
			  log+=" ";
			}
			log+=a[i][j].toFixed(2)+" ";
		}
		log+="]";
		console.log(log)
	}
}