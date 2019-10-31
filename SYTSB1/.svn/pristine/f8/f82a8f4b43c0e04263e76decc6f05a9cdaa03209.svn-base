//上移一层
function baseContextMoveUp(primitives){
	console.time('上移一层耗时：');
	var array=parseLayerArray(primitives,"increment"); // 递增
	var returnArray=new Array();
	// 每层LAYER 移动相应TEMPARR元素
	for(var i in array){
		var layer=array[i].layer;
		var arr=array[i].arr;// 下标从小到大的有序元素序列
		var pLast=arr[arr.length-1];// 最大 上移
		var pFirst=arr[0];
		var moveFlag=false;
		var lastPrimitive;
		while(arr.length>0){
			var pMode=arr[arr.length-1];// 判断是否为不需要移动的元素
			for(var j =pMode.zIndex+1;j<layer.primitives.length;j++){
				var vp=layer.primitives[j];
				if(vp.visiable&&!vp.del){// 找到最近的可见元素
					if(arr.indexOf(vp)>-1){// 排除参与移动的元素
						break;
					}
					// 仅限于选中的最大的下标元素为图层最大下标元素，去掉此判断可更换为能移动的元素的下一层
					if(lastPrimitive&&(j+1)!=lastPrimitive.zIndex){
						continue;
					}	

					var k = pFirst.zIndex+1;								
			　　　　while ( k <= j) {
						var tempP=layer.primitives[k];
						tempP.historyZIndex=k;  // 确保zIndex historyZIndex正确
						tempP.zIndex=k;
						if(arr.indexOf(tempP)>-1){// 排除参与移动的元素
							k++;
							continue;
						}
						var pos=0;
						for(var h=arr.length-1;h>=0;h--){										
							if(k>arr[h].zIndex){
								pos++;
							}
						}
						layer.primitives[k - pos] = layer.primitives[k];
						// 修正最新zIndex;
						layer.primitives[k - pos].zIndex=k - pos;
						layer.primitives[k - pos].moveType="assit";
						returnArray.push(layer.primitives[k - pos]);// 加入历史记录
			　　　　　  k++;
			　　　　}
					// 最后将选中的元素进行摆放
					var tlen=0;
					while(tlen<arr.length){
						layer.primitives[j-tlen]=arr[arr.length-1-tlen];
						// 修正最新zIndex;
						layer.primitives[j-tlen].zIndex=j-tlen;
						layer.primitives[j-tlen].moveType="main";
						returnArray.push(layer.primitives[j-tlen]);// 加入历史记录
						tlen++;
					}
					moveFlag=true;	
					break;
				}
			}

			// important
			if(moveFlag){
				break;
			}else{
				lastPrimitive=arr.pop();
			}
		
		}
	}
	
	console.timeEnd('上移一层耗时：');
	return returnArray;
}

// 下移一层
function baseContextMoveDown(primitives){
	console.time('下移一层耗时：');
	var array=parseLayerArray(primitives,"decrement"); // 递减
	var returnArray=new Array();
	// 每层LAYER 移动相应TEMPARR元素
	for(var i in array){
		var layer=array[i].layer;
		var arr=array[i].arr;// 下标从大到小的有序元素序列
		// var pMode=arr[arr.length-1];//最大 上移
		var pFirst=arr[0];
		var moveFlag=false;
		var lastPrimitive;
		while(arr.length>0){
			var pMode=arr[arr.length-1];// 判断是否为不需要移动的元素
			for(var j =pMode.zIndex-1;j>=0;j--){
				var vp=layer.primitives[j];
				if(vp.visiable&&!vp.del){// 找到最近的可见元素
					if(arr.indexOf(vp)>-1){// 排除参与移动的元素
						break;
					}
					// 仅限于选中的最大的下标元素为图层最大下标元素，去掉此判断可更换为能移动的元素的下一层
					if(lastPrimitive&&(j-1)!=lastPrimitive.zIndex){
						continue;
					}	

					var k = pFirst.zIndex-1;								
			　　　　while ( k >= j) {
						var tempP=layer.primitives[k];
						tempP.historyZIndex=k;// 确保zIndex historyZIndex正确
						tempP.zIndex=k;
						if(arr.indexOf(tempP)>-1){// 排除参与移动的元素
							k--;
							continue;
						}
						var pos=0;
						for(var h=0;h<arr.length;h++){										
							if(k<arr[h].zIndex){
								pos++;
							}
						}
						layer.primitives[k + pos] = layer.primitives[k];
						// 修正最新zIndex;
						layer.primitives[k + pos].zIndex=k + pos;
						layer.primitives[k + pos].moveType="assit";
						returnArray.push(layer.primitives[k + pos]);// 加入历史记录
			　　　　　  k--;
			　　　　}
					// 最后将选中的元素进行摆放
					var tlen=0;
					while(tlen<arr.length){
						layer.primitives[j+tlen]=arr[arr.length-1-tlen];
						// 修正最新zIndex;
						layer.primitives[j+tlen].zIndex=j+tlen;
						layer.primitives[j+tlen].moveType="main";
						returnArray.push(layer.primitives[j+tlen]);// 加入历史记录
						tlen++;
					}
					moveFlag=true;	
					break;
				}
			}

			// important
			if(moveFlag){
				break;
			}else{
				lastPrimitive=arr.pop();
			}
		
		}
	}
	
	console.timeEnd('下移一层耗时：');
	return returnArray;
}
// 置顶
function baseContextMoveTop(primitives){
	console.time('置顶耗时：');
	var array=parseLayerArray(primitives,"increment"); // 递增
	var returnArray=new Array();
	// 每层LAYER 移动相应TEMPARR元素
	for(var i in array){
		var layer=array[i].layer;
		var arr=array[i].arr;// 下标从小到大的有序元素序列

		var pFirst=arr[0];
		var moveFlag=false;
		var j=layer.primitives.length-1;
		while(arr.length>0){						
			if(arr.indexOf(layer.primitives[j])>-1){// 排除参与移动的元素
				j--;
				arr.pop();
				continue;
			}								
			var k = pFirst.zIndex+1; // pFirst的ZINDEX已在addPrimitiveToTempBySort确认
	　　　　while ( k <= j) {
				var tempP=layer.primitives[k];
				tempP.historyZIndex=k;// 确保zIndex historyZIndex正确
				tempP.zIndex=k;
				if(arr.indexOf(tempP)>-1){// 排除参与移动的元素
					k++;
					continue;
				}
				var pos=0;
				for(var h=arr.length-1;h>=0;h--){										
					if(k>arr[h].zIndex){
						pos++;
					}
				}
				layer.primitives[k - pos] = layer.primitives[k];
				layer.primitives[k - pos].historyZIndex=k;
				// 修正最新zIndex;
				layer.primitives[k - pos].zIndex=k - pos;
				layer.primitives[k - pos].moveType="assit";
				returnArray.push(layer.primitives[k - pos]);// 加入历史记录
	　　　　　  k++;
	　　　　}
			// 最后将选中的元素进行摆放
			var tlen=0;
			while(tlen<arr.length){
				layer.primitives[j-tlen]=arr[arr.length-1-tlen];
				// 修正最新zIndex;
				layer.primitives[j-tlen].zIndex=j-tlen;
				layer.primitives[j-tlen].moveType="main";
				returnArray.push(layer.primitives[j-tlen]);// 加入历史记录
				tlen++;
			}
			moveFlag=true;	
			break;						
		
		}
	}
	
	console.timeEnd('置顶耗时：');
	return returnArray;
}
// 置底
function baseContextMoveBottom(primitives){
	console.time('置底耗时：');
	var array=parseLayerArray(primitives,"decrement"); // 递减
	var returnArray=new Array();
	// 每层LAYER 移动相应TEMPARR元素
	for(var i in array){
		var layer=array[i].layer;
		var arr=array[i].arr;// 下标从小到大的有序元素序列
		// var pMode=arr[arr.length-1];//最大 上移
		var pFirst=arr[0];
		var moveFlag=false;
		var lastPrimitive;
		var j=0;
		while(arr.length>0){
			if(arr.indexOf(layer.primitives[j])>-1){// 排除参与移动的元素
				j++;
				arr.pop();
				continue;
			}		
			var k = pFirst.zIndex-1;								
	　　　　while ( k >= j) {
				var tempP=layer.primitives[k];
				tempP.historyZIndex=k;// 确保zIndex historyZIndex正确
				tempP.zIndex=k;
				if(arr.indexOf(tempP)>-1){// 排除参与移动的元素
					k--;
					continue;
				}
				var pos=0;
				for(var h=0;h<arr.length;h++){										
					if(k<arr[h].zIndex){
						pos++;
					}
				}
				layer.primitives[k + pos] = layer.primitives[k];
				// 修正最新zIndex;
				layer.primitives[k + pos].zIndex=k + pos;
				layer.primitives[k + pos].moveType="assit";
				returnArray.push(layer.primitives[k + pos]);// 加入历史记录
	　　　　　  k--;
	　　　　}
			// 最后将选中的元素进行摆放
			var tlen=0;
			while(tlen<arr.length){
				layer.primitives[j+tlen]=arr[arr.length-1-tlen];
				// 修正最新zIndex;
				layer.primitives[j+tlen].zIndex=j+tlen;
				layer.primitives[j+tlen].moveType="main";
				returnArray.push(layer.primitives[j+tlen]);// 加入历史记录
				tlen++;
			}
			moveFlag=true;	
			break;							
		
		}
	}
	
	console.timeEnd('置底耗时：');
	return returnArray;
}

// 组合内部上移一层
function baseContextGroupMoveUp(primitive,primitives){	
	var group=primitive.data;
	var returnArray=new Array();
	var arr=sortArray(group,primitives,"increment");// 计算组合中每个元素zIndex
	
	// 每层LAYER 移动相应TEMPARR元素
	// for(var i in array){
		// var layer=array[i].layer;
		 
	 
		var pLast=arr[arr.length-1];// 最大 上移
		var pFirst=arr[0];
		var moveFlag=false;
		var lastPrimitive;
		while(arr.length>0){
			var pMode=arr[arr.length-1];// 判断是否为不需要移动的元素
			for(var j =pMode.groups.zIndex+1;j<group.primitives.length;j++){
				var vp=group.primitives[j];
				
				if(arr.indexOf(vp)>-1){// 排除参与移动的元素
					break;
				}
				// 仅限于选中的最大的下标元素为图层最大下标元素，去掉此判断可更换为能移动的元素的下一层
				if(lastPrimitive&&(j+1)!=lastPrimitive.groups.zIndex){
					continue;
				}	

				var k = pFirst.groups.zIndex+1;								
		　　　　while ( k <= j) {
					var tempP=group.primitives[k];
					tempP.groups.historyZIndex=k;  // 确保zIndex historyZIndex正确
					tempP.groups.zIndex=k;
					if(arr.indexOf(tempP)>-1){// 排除参与移动的元素
						k++;
						continue;
					}
					var pos=0;
					for(var h=arr.length-1;h>=0;h--){										
						if(k>arr[h].groups.zIndex){
							pos++;
						}
					}
					group.primitives[k - pos] = group.primitives[k];
					// 修正最新zIndex;
					group.primitives[k - pos].groups.zIndex=k - pos;
					group.primitives[k - pos].moveType="assit";
					returnArray.push(group.primitives[k - pos]);// 加入历史记录
		　　　　　  k++;
		　　　　}
				// 最后将选中的元素进行摆放
				var tlen=0;
				while(tlen<arr.length){
					group.primitives[j-tlen]=arr[arr.length-1-tlen];
					// 修正最新zIndex;
					group.primitives[j-tlen].groups.zIndex=j-tlen;
					group.primitives[j-tlen].moveType="main";
					returnArray.push(group.primitives[j-tlen]);// 加入历史记录
					tlen++;
				}
				moveFlag=true;	
				break;
				
			}

			// important
			if(moveFlag){
				break;
			}else{
				lastPrimitive=arr.pop();
			}
		
		}
	// }
	
	
	return returnArray;
}


// 组合内下移一层
function baseContextGroupMoveDown(primitive,primitives){
	var group=primitive.data;
	var returnArray=new Array();
	var arr=sortArray(group,primitives,"decrement");// 计算组合中每个元素zIndex

	// 每层LAYER 移动相应TEMPARR元素
	// for(var i in array){
	// var layer=array[i].layer;
	// var arr=array[i].arr;//下标从小到大的有序元素序列
		// var pMode=arr[arr.length-1];//最大 上移
		
		var pFirst=arr[0];
		var moveFlag=false;
		var lastPrimitive;
		while(arr.length>0){
			var pMode=arr[arr.length-1];// 判断是否为不需要移动的元素
			for(var j =pMode.groups.zIndex-1;j>=0;j--){
				var vp=group.primitives[j];
				if(arr.indexOf(vp)>-1){// 排除参与移动的元素
					break;
				}
				// 仅限于选中的最大的下标元素为图层最大下标元素，去掉此判断可更换为能移动的元素的下一层
				if(lastPrimitive&&(j-1)!=lastPrimitive.groups.zIndex){
					continue;
				}	

				var k = pFirst.groups.zIndex-1;								
		　　　　while ( k >= j) {
					var tempP=group.primitives[k];
					tempP.groups.historyZIndex=k;// 确保zIndex historyZIndex正确
					tempP.groups.zIndex=k;
					if(arr.indexOf(tempP)>-1){// 排除参与移动的元素
						k--;
						continue;
					}
					var pos=0;
					for(var h=0;h<arr.length;h++){										
						if(k<arr[h].groups.zIndex){
							pos++;
						}
					}
					group.primitives[k + pos] = group.primitives[k];
					// 修正最新zIndex;
					group.primitives[k + pos].groups.zIndex=k + pos;
					group.primitives[k + pos].moveType="assit";
					returnArray.push(group.primitives[k + pos]);// 加入历史记录
		　　　　　  k--;
		　　　　}
				// 最后将选中的元素进行摆放
				var tlen=0;
				while(tlen<arr.length){
					group.primitives[j+tlen]=arr[arr.length-1-tlen];
					// 修正最新zIndex;
					group.primitives[j+tlen].groups.zIndex=j+tlen;
					group.primitives[j+tlen].moveType="main";
					returnArray.push(group.primitives[j+tlen]);// 加入历史记录
					tlen++;
				}
				moveFlag=true;	
				break;
			}

			// important
			if(moveFlag){
				break;
			}else{
				lastPrimitive=arr.pop();
			}
		
		}
	// }
	
	
	return returnArray;
}

// 组合内置顶
function baseContextGroupMoveTop(primitive,primitives){	
	var group=primitive.data;
	var returnArray=new Array();
	var arr=sortArray(group,primitives,"increment");// 计算组合中每个元素zIndex
	// 每层LAYER 移动相应TEMPARR元素
	// for(var i in array){
	// var layer=array[i].layer;
	// var arr=array[i].arr;//下标从小到大的有序元素序列

		var pFirst=arr[0];
		var moveFlag=false;
		var j=group.primitives.length-1;
		while(arr.length>0){						
			if(arr.indexOf(group.primitives[j])>-1){// 排除参与移动的元素
				j--;
				arr.pop();
				continue;
			}								
			var k = pFirst.groups.zIndex+1; // pFirst的ZINDEX已在addPrimitiveToTempBySort确认
	　　　　while ( k <= j) {
				var tempP=group.primitives[k];
				tempP.groups.historyZIndex=k;// 确保zIndex historyZIndex正确
				tempP.groups.zIndex=k;
				if(arr.indexOf(tempP)>-1){// 排除参与移动的元素
					k++;
					continue;
				}
				var pos=0;
				for(var h=arr.length-1;h>=0;h--){										
					if(k>arr[h].groups.zIndex){
						pos++;
					}
				}
				group.primitives[k - pos] = group.primitives[k];
				group.primitives[k - pos].historyZIndex=k;
				// 修正最新zIndex;
				group.primitives[k - pos].groups.zIndex=k - pos;
				group.primitives[k - pos].moveType="assit";
				returnArray.push(group.primitives[k - pos]);// 加入历史记录
	　　　　　  k++;
	　　　　}
			// 最后将选中的元素进行摆放
			var tlen=0;
			while(tlen<arr.length){
				group.primitives[j-tlen]=arr[arr.length-1-tlen];
				// 修正最新zIndex;
				group.primitives[j-tlen].groups.zIndex=j-tlen;
				group.primitives[j-tlen].moveType="main";
				returnArray.push(group.primitives[j-tlen]);// 加入历史记录
				tlen++;
			}
			moveFlag=true;	
			break;						
		
		}
	// }
	return returnArray;
}

// 组合内置底
function baseContextGroupMoveBottom(primitive,primitives){
	var group=primitive.data;
	var returnArray=new Array();
	var arr=sortArray(group,primitives,"decrement");// 计算组合中每个元素zIndex
	// 每层LAYER 移动相应TEMPARR元素
	// for(var i in array){
	// var layer=array[i].layer;
	// var arr=array[i].arr;//下标从小到大的有序元素序列
		// var pMode=arr[arr.length-1];//最大 上移
		var pFirst=arr[0];
		var moveFlag=false;
		var lastPrimitive;
		var j=0;
		while(arr.length>0){
			if(arr.indexOf(group.primitives[j])>-1){// 排除参与移动的元素
				j++;
				arr.pop();
				continue;
			}		
			var k = pFirst.groups.zIndex-1;								
	　　　　while ( k >= j) {
				var tempP=group.primitives[k];
				tempP.groups.historyZIndex=k;// 确保zIndex historyZIndex正确
				tempP.groups.zIndex=k;
				if(arr.indexOf(tempP)>-1){// 排除参与移动的元素
					k--;
					continue;
				}
				var pos=0;
				for(var h=0;h<arr.length;h++){										
					if(k<arr[h].groups.zIndex){
						pos++;
					}
				}
				group.primitives[k + pos] = group.primitives[k];
				// 修正最新zIndex;
				group.primitives[k + pos].groups.zIndex=k + pos;
				group.primitives[k + pos].moveType="assit";
				returnArray.push(group.primitives[k + pos]);// 加入历史记录
	　　　　　  k--;
	　　　　}
			// 最后将选中的元素进行摆放
			var tlen=0;
			while(tlen<arr.length){
				group.primitives[j+tlen]=arr[arr.length-1-tlen];
				// 修正最新zIndex;
				group.primitives[j+tlen].groups.zIndex=j+tlen;
				group.primitives[j+tlen].moveType="main";
				returnArray.push(group.primitives[j+tlen]);// 加入历史记录
				tlen++;
			}
			moveFlag=true;	
			break;							
		
		}
	// }
	
	return returnArray;
}

// 排序
function baseContextSortDataLRTB(primitives,type) {
	for(i=0;i<primitives.length-1;i++){
		for(j=0;j<primitives.length-1-i;j++){
			if(primitives[j].LRTB.l[type]>primitives[j+1].LRTB.l[type]){
				var temp=primitives[j];
				primitives[j]=primitives[j+1];
				primitives[j+1]=temp;
			}
		}
	}				
}

// ========用于 移动、组合 开始 ============

function groupsZIndex(group){

	for(var i=0;i<group.primitives.length;i++){
		group.primitives[i].groups.zIndex=i;
		group.primitives[i].groups.historyZIndex=i;
	}
}

function sortArray(group,primitives,type){
	groupsZIndex(group);
	var array=new Array();				
	for(var i in primitives){
		var p=primitives[i];
		array=sortPrimitive(array,p,type);
	}
	return array;
}
function sortPrimitive(array,p,type){
	if(array.length==0){
		array.push(p);
	}else{
		
		array=sortGroupSwapArray(array,p,type);
	}
	return array;
}

function sortGroupSwapArray(array,p,type){
	var swap=new Array();
	var insertFlag=false;
	for(var i =0;i<array.length;i++){
		var primitive = array[i];	
		if(type=="decrement"){// 递减
			if( primitive.groups.zIndex < p.groups.zIndex&&!insertFlag) {
				swap.push(p);
				insertFlag=true;
	　　　　}
		}else{// 递增
			if( primitive.groups.zIndex > p.groups.zIndex&&!insertFlag) {
				swap.push(p);
				insertFlag=true;
	　　　　}
		}
　　　　
		swap.push(primitive);
	}
	if(!insertFlag){
		swap.push(p);
	}
	return swap;
}

function parseLayerArray(primitives,type){
	var array=new Array();				
	for(var i in primitives){
		var p=primitives[i];
		var json=findLayer(array,p.rtLayer);
		addPrimitiveToTempBySort(json,p,type);
	}
	return array;
}
function addPrimitiveToTempBySort(json,p,type){
	var zIndex=json.layer.primitives.indexOf(p);
	var temp=json.arr;
	p.zIndex=zIndex;// 确保zIndex historyZIndex正确
	p.historyZIndex=zIndex;
	if(temp.length==0){
		temp.push(p);
	}else{
		
		var swap=sortSwapArray(temp,p,type);
		json.arr=swap;
	
	}

}
function sortSwapArray(array,p,type){
	var swap=new Array();
	var insertFlag=false;
	for(var i =0;i<array.length;i++){
		var primitive = array[i];	
		if(type=="decrement"){// 递减
			if( primitive.zIndex < p.zIndex&&!insertFlag) {
				swap.push(p);
				insertFlag=true;
	　　　　}
		}else{// 递增
			if( primitive.zIndex > p.zIndex&&!insertFlag) {
				swap.push(p);
				insertFlag=true;
	　　　　}
		}
　　　　
		swap.push(primitive);
	}
	if(!insertFlag){
		swap.push(p);
	}
	return swap;
}
// 存在则返回，不存在则增加LAYER
function findLayer(arr,layer){
	var zIndex=layer.rtDrawer.layers.indexOf(layer);
	layer.zIndex=zIndex;
	for(var idx in arr){
		if(arr[idx]["layer"]==layer){
			return arr[idx];
		}
	}
	var json={"layer":layer,"arr":new Array()};
	// if(arr.length>0){
	// var i=arr.length-1;
	// while(i>=0&&arr[i].layer.zIndex>layer.zIndex){
	// arr[i+1]=arr[i];
	// i--;
	// }
	// arr[i+1]=json;
	// }else{
		arr.push(json);
	// }
	
	
	return json;
}


// =========用于 移动、组合 结束===========
