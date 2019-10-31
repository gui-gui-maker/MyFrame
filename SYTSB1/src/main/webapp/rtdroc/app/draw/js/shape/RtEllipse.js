	/*椭圆*/
	function RtEllipse(rtLayer, start,addToLayer) {
		this.name = "RtEllipse";
		this.cname = "椭圆";
		this.rtLayer = rtLayer; 
		this.cxt=rtLayer.cxt;
		this.start=new Point(start.x,start.y);
		this.parent = new Primitive(this,addToLayer);

		this.points = new Array();

		
	}

	/**
	 * 
	 */
	RtEllipse.prototype = {
		add : function(point) {
			this.points.push(point);
		},
		draw : function(loc) {
			this.parent.end.x=loc.x;
			this.parent.end.y=loc.y;
			
			this.points = new Array();
			var a=(this.parent.end.x-this.parent.start.x)/2;
			var b=(this.parent.end.y-this.parent.start.y)/2;
			this.parent.center=new Point(this.parent.start.x+a,this.parent.start.y+b);
			var step = (a > b) ? 1 / a : 1 / b; 
			 if(step<0){
				for (var i = 2 * Math.PI; i >0 ; i += step)  
				{  
				//参数方程为x = a * cos(i), y = b * sin(i)，  
				//参数为i，表示度数（弧度）  
					this.add(new Point(this.parent.center.x + a * Math.cos(i), this.parent.center.y + b * Math.sin(i)));  
				} 
			 }else{
			  for (var i = 0; i < 2 * Math.PI; i += step)  
				{  
				//参数方程为x = a * cos(i), y = b * sin(i)，  
				//参数为i，表示度数（弧度）  
					this.add(new Point(this.parent.center.x + a * Math.cos(i), this.parent.center.y + b * Math.sin(i)));  
				} 
			 
			 }
		},
		copy : function() {
			var start=this.start.clone();
			var copyData = new RtCircle(this.rtLayer,start);
			 
			for (var i in this.points) {
				var p = this.points[i];
				var point = new Point(p.x, p.y);
				copyData.add(point);
			}
			
			return copyData;
		},
		imp:function(data){
			baseContextImpData(this,data);
		}
	
	};
