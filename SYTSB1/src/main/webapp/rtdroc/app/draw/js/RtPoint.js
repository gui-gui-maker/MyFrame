   
	/*自主画线类的点*/
	function Point(x, y) {
		this.x = x;
		this.y = y;
	}

	Point.prototype={
		clone:function(){
			return new Point(this.x,this.y);
		}
	}