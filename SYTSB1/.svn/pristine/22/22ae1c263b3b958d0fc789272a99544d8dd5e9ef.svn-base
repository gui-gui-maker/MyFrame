	/**
	 * AABB包围盒矩形
	 */
	function RtBoxRange(aabb) {

		this.aabb=aabb;
		this.primitive=aabb.primitive;
		this.rtLayer = aabb.rtLayer;
		this.type="move";//用于移动
		
		
		baseContext.call(this, this.rtLayer);
		this.box=this.primitive.box;
		this.box.polygon=this.primitive.box.polygon;
	}


	RtBoxRange.prototype = {
		
		redraw : function() {
			this.cxt.strokeStyle = '#77CE3C';
			this.cxt.lineWidth = 1;
			this.cxt.setLineDash([2]);
			this.cxt.beginPath();

			for (var i in this.box.polygon) {
				var point = this.box.polygon[i];
				if(!point.hide){
					this.cxt.lineTo(point.x, point.y);
				}
			}					
			this.cxt.closePath();
			this.cxt.stroke();			
			this.cxt.setLineDash([]);
		},					
		hasSelect:function(x,y){
		   if(this.aabb.boxType=="line"){
			   return isInLine(new Point(x,y),this.box.nw,this.box.se);
		   }else{
			   return baseContextHasSelect(this,x,y);				
		   }	   
		}
	};