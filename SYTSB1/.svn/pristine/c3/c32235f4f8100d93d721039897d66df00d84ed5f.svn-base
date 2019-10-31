	/*圆*/
	function RtArc(rtLayer, start,addToLayer) {
		this.name = "RtArc";
		
		this.cname = "弧线";
		this.rtLayer = rtLayer;
		this.cxt = this.rtLayer.cxt;
		this.addToLayer = addToLayer;
		baseContext.call(this, this.rtLayer);
		

		//console.log("RtLine:this.cxt....."+this.cxt);
		
		this.visiable=true;//初始状态
		this.start=new Point(start.x,start.y);
		this.end=new Point(start.x,start.y);
		//画线的初始时候为不被选中
		this.points = new Array();

		this.box={};//包围盒
		this.center={};//中心点
		
		

		this.parent = new Primitive(this, this.name, addToLayer);

		
	}

	/**
	 * 
	 */
	RtArc.prototype = {
		add : function(point) {
			this.points.push(point);
		},
		draw : function(loc) {
			this.end=this.parent.end;
			this.start=this.parent.start;
			this.center=this.parent.center;
			this.end.x=loc.x;
			this.end.y=loc.y;
			/*
			this.points = new Array();
			var a=this.end.x-this.start.x;
			var b=this.end.y-this.start.y;
			this.center=new Point(this.start.x+a/2,this.start.y+b/2);
			var step = (a > b) ? 1 / a : 1 / b; 
			 for (var i = 0; i < 2 * Math.PI; i += step)  
			{  
			//参数方程为x = a * cos(i), y = b * sin(i)，  
			//参数为i，表示度数（弧度）  
				this.add(this.center.x + a * Math.cos(i), this.center.y + b * Math.sin(i));  
			} 
			*/
			this.points = new Array();

		
			var r=(this.end.x-this.start.x)/2;			
			this.center=new Point(this.start.x+r,this.start.y+r);
			var step = 1 / (2*r);
			var rotate=aobRotate(this.start,this.center,this.end)+Math.PI/4;
			if(this.end.x>=this.start.x&&this.end.y>=this.start.y){
				  
				 for (var i = 0; i <=rotate; i += step)  
				{	
					
					this.add( new Point(this.center.x - r * Math.cos(i), this.center.y - r * Math.sin(i)));  
				}
			}else if(this.end.x>=this.start.x&&this.end.y<=this.start.y){
				
				 for (var i = 0; i <=rotate; i += step)  
				{	
					this.add( new Point(this.center.x +r * Math.cos(i), this.center.y + r * Math.sin(i)));  
				}
			}else if(this.end.x<=this.start.x&&this.end.y>=this.start.y){
				

				
				 for (var i = rotate; i >=0; i += step)  
				{	
					// console.log("i:"+i);
				 
					this.add( new Point(this.center.x -r * Math.cos(i), this.center.y - r * Math.sin(i)));  
				}
			}
			else if(this.end.x<=this.start.x&&this.end.y<=this.start.y){
				 for (var i = rotate; i >=0; i += step)  
				{	
					// console.log("i:"+i);
				 
					this.add( new Point(this.center.x +r * Math.cos(i), this.center.y + r * Math.sin(i)));  
				}			
			}
			
			
		},
		redraw : function() {
			
			this.cxt.beginPath();
			//console.log("redraw this.box.nw:  x:"+this.box.nw.x+",y:"+this.box.nw.y);
			//console.log("RtLine redraw:"+this.matrix);
			this.matrix=this.parent.matrix;
			for (var i in this.points) {
				var point = this.points[i];
				if(this.matrix){
					var c=MatrixMultiplyPoint(point,this.matrix);
					this.cxt.lineTo(c.x, c.y);
					//this.cxt.lineTo(point.x, point.y);
				}else{
					this.cxt.lineTo(point.x, point.y);
				}
				
			}
			this.cxt.stroke();
			this.cxt.closePath();
			
			//console.log("RtLine:this.cxt.closePath..");
		},
		copy : function() {
			var start=this.start.clone();			
			var copyData = new RtArc(this.rtLayer,start);
			 
			for (var i in this.points) {
				var p = this.points[i];
				var point = new Point(p.x, p.y);
				copyData.add(point);
			}
			
			return copyData;
		} ,
		imp:function(data){
			baseContextImpData(this,data);
		}
	
	};
