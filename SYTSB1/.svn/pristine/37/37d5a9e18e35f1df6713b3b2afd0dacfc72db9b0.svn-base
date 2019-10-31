	/*自主画线类*/
	function RtPen(rtLayer,start, addToLayer) {
		this.name = "RtPen";
		this.cname = "笔";
		this.rtLayer = rtLayer; 
		this.cxt=rtLayer.cxt;
		this.start=new Point(start.x,start.y);
		this.parent = new Primitive(this,addToLayer);

		this.points = new Array();
	}

	/**
	 * 自主画线类 ，添加每一点，计算AABB
	 */
	RtPen.prototype = {
		add : function(point) {
			this.points.push(point);
		},
		draw:function(end){
			//console.log("pen add x:"+end.x+",y:"+end.y);
			this.add(end);
		},
		redraw: function() {
			
			this.cxt.beginPath();
			var start=this.start.clone();
			var matrix=this.parent.matrix;
			if(matrix){
				start=MatrixMultiplyPoint(start,matrix);
			}
			this.cxt.moveTo(start.x,start.y);	

			for (var i in this.points) {
				var point = this.points[i];
				if(matrix){
					var c=MatrixMultiplyPoint(point,matrix);
					this.cxt.lineTo(c.x, c.y);
				}else{
					this.cxt.lineTo(point.x, point.y);
				}
				
			}
			this.cxt.stroke();			
		
			this.cxt.closePath();
		},
		copy : function() {
			var start=this.start.clone();
			
			var copyData = new RtPen(this.rtLayer,start);
			 
			for (var i in this.points) {
				var p = this.points[i];
				var point = new Point(p.x, p.y);
				copyData.add(point);
			}
			return copyData;
		}, 
		imp:function(data){
			//ZQ EDIT 20181102 解决修改表现异常
			//if(data.matrix){
			//	this.start =MatrixMultiplyPoint(this.start,data.matrix);
			//}
			baseContextImpData(this,data);				
		}
	
	};
