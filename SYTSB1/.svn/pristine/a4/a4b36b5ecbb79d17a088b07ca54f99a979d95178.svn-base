	function expDrawer(rtDrawer){		
		var drawer={};
		var layers=new Array();
		drawer.layers=layers;
		var rtLayers=rtDrawer.layers;
		//debugger;
		//图层
		for(var i in rtLayers){
			//元素层
			var rtLayer=rtLayers[i];
			if(!rtLayer.visiable){
				continue;
			}
			var rtPrimitives=rtLayer.primitives;
			var layer={};
			layers.push(layer);
			
			layer.name=rtLayer.name;
			layer.primitives=new Array();
			for(var j in rtPrimitives){
				var rtPrimitive=rtPrimitives[j];
				if(!rtPrimitive.visiable||rtPrimitive.del){
					continue;
				}
				layer.primitives.push(rtPrimitive.save());
			}

					
				
		}

		var value=JSON.stringify(drawer);
		return value;
	}

	function groupPrimitive(rtGroups,newGroup,groupFlag){
		for(var i in rtGroups){
			var rtPrimitive=rtGroups[i];
			if(!groupFlag&&(!rtPrimitive.visiable||rtPrimitive.del)){
				continue;
			}
			if(rtPrimitive.type=="RtGroup"){
				var primitive={};
				primitive.id=rtPrimitive.id;
				primitive.name=rtPrimitive.name;
				primitive.primitives=new Array();
				primitive.matrix=rtPrimitive.matrix;
				primitive.type=rtPrimitive.type;
				newGroup.push(primitive);
				groupPrimitive(rtPrimitive.primitives,primitive.primitives,true);
			}else {
				var rtData=rtPrimitive.data;
				var p={},data={};				
				p.id=rtPrimitive.id;
				p.name=rtPrimitive.name;
				p.data=data;
				
				data.name=rtData.name;
				data.start=rtData.start;
				data.end=rtData.end;
				data.points=rtData.points;
				data.style=rtPrimitive.style;
				
				data.matrix=rtData.matrix;
				data.text=rtData.text;
				newGroup.push(p);
			}
		}

	
	}


function impDrawer(value,rtDrawer,rtLayer){	
	var jsonObj= JSON.parse(value);
	var layersObj=jsonObj.layers;
	var layers=new Array();
	var primitives=new Array();	
	for(var i in layersObj){
		var entity=layersObj[i];
		if(entity.name=="RtLayer"){
			var cRtLayer = new RtLayer(rtDrawer,true);
			layers.push(cRtLayer);
			
			for(var j in entity.primitives){
				var primitive=entity.primitives[j];
				if(!primitive){
					continue;
				}
				var p=new Primitive();
				p=p.imp(cRtLayer,primitive);
				primitives.push(p);
				/*
				if(primitive.type=="RtGroup"){
					var cRrGroup=new RtGroup(cRtLayer);
					cRrGroup.imp(primitive.data.primitives);
					//cRrGroup.calcAABB();
					//cRrGroup.rtLayer.add(cRrGroup);
					
				}else{
					var data=primitive.data;//真实图形
					var fun=eval(data.name);
					if(!data.start){
						console.log("imp shape ["+data.name+"] data  error  : null start point  ---> "+data.id);
						continue;
					}
					var p=new fun(cRtLayer,data.start,true);
					p.imp(data);
					
				} */
			}
			
		}
	}
	
	//debugger;
	if(layers.length>0){
		
		if(!rtLayer){
			
			rtDrawer.layers=layers;
		}else{
			var action=new RtAction("imp",primitives);//加入历史记录
			rtDrawer.history.add(action);	
			rtLayer.primitives=rtLayer.primitives.concat(layers[0].primitives);
		}				
		rtDrawer.redraw();
	}
	return true;

}