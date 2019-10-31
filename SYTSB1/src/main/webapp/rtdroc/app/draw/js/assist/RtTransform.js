/*图元 */

	function RtTransform(rtDrawingLayer,start) {
			this.name="RtTransform";
			this.cxt=rtDrawingLayer.cxt;
			this.rtDrawer=rtDrawingLayer.rtDrawer;
			this.start=start;
			this.box={};
	}


	RtTransform.prototype = {
		draw:function(end){
			this.box.se=end;
			this.box.nw=this.start;
			this.box.ne=new Point(this.box.se.x,this.box.nw.y);
			this.box.sw=new Point(this.box.nw.x,this.box.se.y);

				//用于检测是否选中
			this.box.polygon=[];
			this.box.polygon.push(this.box.nw);
			this.box.polygon.push(this.box.ne);
			this.box.polygon.push(this.box.se);
			this.box.polygon.push(this.box.sw);
		},
			//重绘
		redraw : function() {
			baseContextRestore(this);
			var globalAlpha=this.data.globalAlpha;
			this.data.globalAlpha=0.2;
			this.data.redraw();
			this.data.globalAlpha=globalAlpha;
		}
	};