/**
*RtActionEntity 可能包括Primitive ,RtGroup,临时GROUP
*
*/
function RtActionEntity(entity,type,opMode){
	this.type=type;

	if(this.type!="draw"){
		this.modified=true;
	}

	this.entity=entity;
	this.style={};
	for(i in entity.style){
		this.style[i]=entity.style[i];
	}
	this.groups={};
	if(entity.groups){
		for(i in entity.groups){
			this.groups[i]=entity.groups[i];
		}	
	}
	
	this.rtLayer=entity.rtLayer;
	this.obox=entity.obox;
	this.opMode=opMode;
	//if(this.opMode){
		this.historyZIndex=entity.historyZIndex;
		this.zIndex=entity.zIndex;
		this.moveType=entity.moveType;
	//}


//	if(Array.isArray(entity.historyMatrix)){
	
//		this.matrix=MatrixCopy(entity.historyMatrix);
//	}
	if(Array.isArray(entity.matrix)){
		//this.matrix=MatrixCopy(entity.matrix);
		this.matrix=MatrixCopy(entity.matrix);
	}
	if(baseContextIsLinePrimitive(entity.type)){//="RtLine" RtArrow
		/*
		if(type=="last"){
			entity.data.historyPoint.start=entity.data.start.clone();
			entity.data.historyPoint.end=entity.data.end.clone();
		}*/		
	
		if(entity.data.points){
			//console.log("add history:"+entity.data.points[0].x);
			this.points={};
			for(i in entity.data.points){
				this.points[i]=entity.data.points[i].clone();
			}
		}
		
	}else if("cancelGroup"){		
		this.history=entity.history;
	}

	this.children=null;

}

RtActionEntity.prototype={
	
	undo:function(){
		 if(this.children){
				for(var i in this.children){
					var actionEntity=this.children[i];
					actionEntity.undo();
				}
		 }
		 
		 for(var i in this.style){
			// if(i=="fillStyle"){
				//console.log("i:"+i+",style i :"+this.style[i]);
			// }
			 this.entity.style[i]=this.style[i];								
		 }
		 this.entity.style=this.entity.style;
		 this.entity.obox=this.obox;
		 
		 if(this.opMode=="sortTypes"){
			 baseContextUndoRedoSort(this.entity,this.historyZIndex);
		 }
		 if(this.opMode=="groupSortTypes"){
			 baseContextUndoRedoGroupSort(this.entity,this.groups.historyZIndex);
		 }
		 if(this.points){
			 var p=this.points[0];
			 console.log("undo history:"+p.x);
		 }
		 
		 this.entity.undo(this.matrix,this.points);
		// console.log("RtActionEntity undo matrix:"+MatrixPrint(this.matrix));

		
	},
	redo:function(){
		if(this.children){
			for(var i in this.children){
				var actionEntity=this.children[i];
				actionEntity.undo();
			}
		 }
		
		 for(var i in this.style){
		 	 this.entity.style[i]=this.style[i];								
		 }
		 this.entity.style=this.entity.style;
		 this.entity.obox=this.obox;

		 if(this.opMode=="sortTypes"){
			 baseContextUndoRedoSort(this.entity,this.zIndex); 
		 }
		 if(this.opMode=="groupSortTypes"){
			 baseContextUndoRedoGroupSort(this.entity,this.groups.zIndex); 
		 }
		 if(this.points){
			 console.log("redo history:"+this.points[0].x);
		 }
		 
		 this.entity.redo(this.matrix,this.points);
		//	 console.log("RtActionEntity redo matrix:"+MatrixPrint(this.matrix));
	}
}

