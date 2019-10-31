	/*圆*/
	function RtCircle(rtLayer, start,addToLayer) {
		this.name = "RtCircle";
		this.cname = "圆";
		this.rtLayer = rtLayer; 
		this.cxt=rtLayer.cxt;
		this.start=new Point(start.x,start.y);
		this.parent = new Primitive(this,addToLayer);

		this.points = new Array();
		
		
	}

	/**
	 * 
	 */
	RtCircle.prototype = {
		add : function(point) {
			this.points.push(point);
		},
		draw : function(loc) {
			this.parent.end.x=loc.x;
			this.parent.y=loc.y;
			
			this.points = new Array();
			var dx=(this.parent.end.x-this.parent.center.x);
			var dy=(this.parent.end.y-this.parent.center.y);
			var r=Math.sqrt(dx*dx+dy*dy);
			
			var step = 1 / r; //更加平滑可以参考1/(r/2)
			 
			 for (var i = 0; i < 2 * Math.PI; i += step)  
			{  
			//参数方程为x = a * cos(i), y = b * sin(i)，  
			//参数为i，表示度数（弧度）  
				this.add(new Point(this.parent.center.x + r * Math.cos(i), this.parent.center.y + r * Math.sin(i)));  
			}
			 
		},
		redrawbak : function() {
			//console.log("RtCircle:redraw..");

			baseContextRestore(this);
			this.cxt.beginPath();
			
			for (var i in this.points) {
				var point = this.points[i];
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
		
		copy : function() {
			var start=this.start.clone();
			var copyData = new RtCircle(this.rtLayer,start);
			 
			for (var i in this.points) {
				var p = this.points[i];
				/*
				if(this.matrix){
					p =MatrixMultiplyPoint(p,this.matrix);
				}*/
				var point = new Point(p.x, p.y);
				copyData.add(point);
			}		
			
			return copyData;
		} ,
		
		imp:function(data){
			baseContextImpData(this,data);				
		}
	
	};
