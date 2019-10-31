
			/**
		   * @description 射线法判断点是否在多边形内部
		   * @param {Object} p 待判断的点，格式：{ x: X坐标, y: Y坐标 }
		   * @param {Array} poly 多边形顶点，数组成员的格式同 p
		   * @return {String} 点 p 和多边形 poly 的几何关系
		   */
		   function isInPolygon(p, poly) {
			var px = p.x,
				py = p.y,
				flag = false;


			for(var i = 0, l = poly.length, j = l - 1; i < l; j = i, i++) {
			  var sx = poly[i].x,
				  sy = poly[i].y,
				  tx = poly[j].x,
				  ty = poly[j].y;
			 



			  // 点与多边形顶点重合
			  if((sx === px && sy === py) || (tx === px && ty === py)) {
				return true;
			  }

			  // 判断线段两端点是否在射线两侧
			  if((sy < py && ty >= py) || (sy >= py && ty < py)) {
				// 线段上与射线 Y 坐标相同的点的 X 坐标
				var x = sx + (py - sy) * (tx - sx) / (ty - sy)

				// 点在多边形的边上
				if(x === px) {
				  return true;
				}

				// 射线穿过多边形的边界
				if(x > px) {
				  flag = !flag
				}
			  }
			}

			// 射线穿过多边形边界的次数为奇数时点在多边形内
			return flag ;
		  }

		   function isInCircle(point, center, r) {
				if (r===0) return false;
				var dx = center.x - point.x;
				var dy = center.y - point.y;
				return dx * dx + dy * dy <= r * r
			}

			function isInLine(point,p1,p2){
				var x=point.x,y=point.y,x1=p1.x,y1=p1.y,x2=p2.x,y2=p2.y;
				//点POINT在P1,P2之间
				if(min(x1 , x2) <= x && x <= max(x1 , x2)      
						 && min(y1 , y2) <= y && y <= max(y1, y2)){
					//求点到线段的距离
					var A=y1-y2,B=x2-x1,C=(y2-y1)*x1-(x2-x1)*y1;
					var d=Math.abs(A*x+B*y+C)/Math.sqrt(A*A+B*B);
					if(d<4){							
					   return true;
					}
				}					
				return false;
			
			}

