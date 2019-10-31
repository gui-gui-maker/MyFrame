	/*层*/
			/**
			 * 层中包含图元
			 * 暂时未时间组合图元数据结构
			 */

			function RtLayer(rtDrawer,noAdd) {
				this.name = "RtLayer";

				this.rtDrawer = rtDrawer;
				this.canvas = this.rtDrawer.canvas;
				this.cxt = this.rtDrawer.cxt;
				if(!noAdd){
					//将图层添加到画板
					this.rtDrawer.add(this);	
				}
				

				this.primitives = new Array();

				//this.zIndex = 1;
				this.visiable = true;
				//this.visiableAABB = false;
			}

			/**
			 * 真层 图层属性方法，添加、删除图元 、查看是否选中摸个图元
			 */
			RtLayer.prototype = {
				add : function(primitive) {

					this.primitives.push(primitive);
					primitive.zIndex = this.primitives.length;

				},
				remove : function(primitive) {
					var zIndex=this.primitives.indexOf(primitive);
					if(zIndex>-1){
						this.primitives.splice(zIndex,1);
					}					

				},
				hasSelect : function(x, y) {

					for (var i = this.primitives.length - 1; i >= 0; i--) {
					//for (var i in this.primitives) {
						var p = this.primitives[i];
						//if(p.visiable&&(!p.group||(p.group&&p.groupVisiable))){
						if(p.visibleTest()){	
							if (p.hasSelect(x, y)) {
								//p.select();
								return p;
							}
						}
					}
					return false;

				},
				hasBoxSelect : function(box) {
					var primitives=new Array();
					for (var i = this.primitives.length - 1; i >= 0; i--) {
					//for (var i in this.primitives) {
						var p = this.primitives[i];
						if(p.visibleTest()&&p.hasBoxSelect(box)){
								//p.select();
							primitives.push(p);							
						}
					}
					if(primitives&&primitives.length>0){
						return primitives;
					}
					return false;

				},
				redraw : function() {
					if (this.visiable) {
						for (var i in this.primitives) {
							//console.log("RtLayer: redraw primitive name,"+this.primitives[i].name+",type:"+this.primitives[i].type+",id:"+this.primitives[i].id);
							//if(this.primitives[i].visiable&&(!this.primitives[i].group||(this.primitives[i].group&&this.primitives[i].group.groupVisiable))){
							if(this.primitives[i].visibleTest()){
								this.primitives[i].redraw();
							}
							
						}
					}

				},
				diabled : function() {
					//失效 取消aabb显示
					this.visiable = false;
					this.rtDrawer.redraw();
				},
				length:function(){
					if(this.primitives){
						return this.primitives.length-1;
					}
					return -1;
				},
				findPrimitive:function(id){
					for (var i in this.primitives) {
						//console.log("RtLayer: redraw primitive name,"+this.primitives[i].name+",type:"+this.primitives[i].type+",id:"+this.primitives[i].id);
						//if(this.primitives[i].visiable&&(!this.primitives[i].group||(this.primitives[i].group&&this.primitives[i].group.groupVisiable))){
						var p=this.primitives[i];
						if(!p.del&&p.id==id){
							return p;
						}						
					}
					return null;
					
				}
			};