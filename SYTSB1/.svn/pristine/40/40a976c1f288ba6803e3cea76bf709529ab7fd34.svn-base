function RtRule(rtLayer, start){	
	this.rtLayer=rtLayer;
	this.rtDrawer=rtLayer.rtDrawer;
	this.primitives=new Array();
	
	this.ruleShape=document.getElementById("ruleShape").value;   //形状
//	alert(this.ruleShape);
	this.ruleStart=document.getElementById("ruleStart").value;   //开始编号
	this.rulePrefix=document.getElementById("rulePrefix").value; //前缀
	
//	if(this.ruleStart=="calc"){
//		this.calcStart();
//	}
	
	
	this.offset=20;
	
	if(this.ruleShape=="三角形"){
		this.trianglePrimitve=new RtTriangle(rtLayer,start,true);
		this.primitives.push(this.trianglePrimitve.parent);
		
	}else if(this.ruleShape=="x"){
		this.rtLine1=new RtLine(rtLayer,start,true);
		this.primitives.push(this.rtLine1.parent);
		this.rtLine2=new RtLine(rtLayer,start,true);
		this.primitives.push(this.rtLine2.parent);
		this.offset=6;
	}
		
	this.textPrimitve=new RtText(rtLayer,new Point(start.x,start.y-this.offset),true);
	this.primitives.push(this.textPrimitve.parent);
	
}
RtRule.prototype = {
	draw:function(end){
		if(this.ruleShape=="三角形"){
			this.trianglePrimitve.dragDraw(end,this.rulePrefix+this.ruleStart);
			this.trianglePrimitve.parent.stopDraw();
		}else if(this.ruleShape=="x"){
			this.rtLine1.dragDraw(end,"sw");
			this.rtLine1.parent.stopDraw();
			this.rtLine2.dragDraw(end,"se");
			this.rtLine2.parent.stopDraw();
		}
		this.textPrimitve.dragDraw(new Point(end.x,end.y-this.offset),this.rulePrefix+this.ruleStart);
		this.textPrimitve.parent.stopDraw();
	},
	stopDraw:function (){
		if(this.primitives.length>1){		 
			var rtGroup=new RtGroup(this.rtLayer,this.primitives);
			rtGroup.ruleType=this.rulePrefix+this.ruleShape;	
			//debugger;
			rtGroup.parent.stopDraw();
		}
		
//		document.getElementById("ruleStart").value="calc";
		document.getElementById("ruleStart").value=parseInt(this.ruleStart)+1;
	},
	calcStart:function(){
		var rtLayers=this.rtDrawer.layers;
		
//		var start=1;
		//图层
//		for(var i in rtLayers){
//			//元素层
//			var rtLayer=rtLayers[i];
//			if(!rtLayer.visiable){
//				continue;
//			}
//			var rtPrimitives=rtLayer.primitives;
//			
//			 
//			for(var j in rtPrimitives){
//				var rtPrimitive=rtPrimitives[j];
//				
//				if(rtPrimitive.del){
//					continue;
//				}
//				if(!rtPrimitive.type=="RtGroup"){
//					continue;
//				}
//				console.log(rtPrimitive.type+",rtPrimitiveid:"+rtPrimitive.id);
//				console.log("rtPrimitive.data.ruleType:"+rtPrimitive.data.ruleType+",start:"+start);
//				if(this.rulePrefix&&rtPrimitive.data.ruleType==(this.rulePrefix+this.ruleShape)){
//					start++;
//				}				 
//			}				
//		}
//		this.ruleStart=start;
//		this.ruleStart++;

	}
}