/**
*rtEntity 可能包括Primitive ,RtGroup,RtLayer等
*opMode用于上一层，下一层，置顶，置底操作（包含组合内）,只需要记录最外层元素排序
*insideGroup 所选中的内部移动操作的组合
*/
function RtAction(type,entity,insideGroup){
	
	this.type=type;	

	this.actionEntitys=new Array();

	//this.sortTypes=["moveUp","moveDown","moveTop","moveBottom"];
	//this.groupSortTypes=["groupMoveUp","groupMoveDown","groupMoveTop","groupMoveBottom"];
	this.opMode=specialType(this.type);//RtHistory.js //查看操作类型
	
	this.initAdd(entity);
/*
	if(this.type=='cancelGroup'){ //||this.type=='drawGroup'
		//只记录组合和组合第一层儿子
		if(Array.isArray(entity)){
			for(var i in entity){
				this.recordGroup(entity[i],this.actionEntitys);
			}			
		}else{
			this.recordGroup(entity,this.actionEntitys);
		}	
		
	}else{
		if(this.opMode){		
			//简单记录
			this.simpleRecord(entity);
		}else{
			//type=="move"||type=="scale"||type=="rotate"
			//递归记录
			this.record(entity,this.actionEntitys);
		}
		
	}
*/
	if(insideGroup){		
		this.simpleRecord(insideGroup);	
		this.opMode="insideGroup";
	}
	
	if(Array.isArray(entity)){
		this.rtDrawer=entity[0].rtDrawer;
	}else{
		this.rtDrawer=entity.rtDrawer;
	}

}

RtAction.prototype={
	initAdd : function (entity){	
		if(this.type=='cancelGroup'){ //||this.type=='drawGroup'
			//只记录组合和组合第一层儿子
			if(Array.isArray(entity)){
				for(var i in entity){
					this.recordGroup(entity[i],this.actionEntitys);
				}			
			}else{
				this.recordGroup(entity,this.actionEntitys);
			}	
			
		}else{
			if(this.opMode){		
				//简单记录
				this.simpleRecord(entity);
			}else{
				//type=="move"||type=="scale"||type=="rotate"
				//递归记录
				this.record(entity,this.actionEntitys);
			}
			
		}
	},
	add :function (entity,opMode){
		if(opMode){		
			this.simpleRecord(entity);	
			this.opMode=opMode;//insideGroup --> RtdrawingLayer stopMove
		}else{
			this.initAdd(entity);
		}
		
	},	
	simpleRecord:function(entity){
		if(Array.isArray(entity)){
			for(var i in entity){
				var actionEntity=new RtActionEntity(entity[i],this.type,this.opMode);
				this.actionEntitys.push(actionEntity);
			}			
		}else{
			var actionEntity=new RtActionEntity(entity,this.type,this.opMode);
			this.actionEntitys.push(actionEntity);
		}	   
	},
	record:function(entity,obj){
		//if(entity instanceof RtGroup){
		if(Array.isArray(entity)){
			for(var i in entity){	
				this.record(entity[i],obj);
			}			
		}else if(entity.type=="RtGroup"){
			var actionEntity=new RtActionEntity(entity,this.type);
			obj.push(actionEntity);			
			for(var i in entity.data.primitives){
				var p =entity.data.primitives[i];
				if(!actionEntity.children){
					actionEntity.children=new Array();
				}
				this.record(p,actionEntity.children);
			}

		}else {
			
			var actionEntity=new RtActionEntity(entity,this.type);
			obj.push(actionEntity);
		}
	},
	recordGroup:function(entity,obj){
		if(entity.type=="RtGroup"){
			var actionEntity=new RtActionEntity(entity,this.type);
			obj.push(actionEntity);	
			actionEntity.group=true;
			for(var i in entity.data.primitives){
				var p =entity.data.primitives[i];
				var actionEntity2=new RtActionEntity(p,this.type);
				actionEntity2.group=false;
				/*
				if(!actionEntity2.groups){
					console.log("自动修复hisitory groups");
					actionEntity2.hisitory=entity.history;
					actionEntity2.groups.group=entity;
				}*/
				obj.push(actionEntity2);
			}

		}
	},
	undo:function(type){		
		
		switch(type){
			case 'draw':
				var primitives;
				for(var i in this.actionEntitys){			
					var p=this.actionEntitys[i].entity;
					primitives=p.disable();					
				}
				if(primitives){
					this.rtDrawer.drawingLayer.create(primitives);		
				}else{
					this.rtDrawer.redraw();
				}
				
				
			    break;
			case 'delete':
				var primitives=new Array();
				for(var i in this.actionEntitys){			
					var p=this.actionEntitys[i].entity;
					p.del=false;
					primitives.push(p);
				}
				this.rtDrawer.drawingLayer.create(primitives);
				//this.rtEntity.enable();
				break;
			case 'drawGroup':
				var primitives=new Array();
				for(var i in this.actionEntitys){	
					var actionEntity=this.actionEntitys[i];
					if(actionEntity.group){
						//组合设置为不可见
						actionEntity.entity.setVisiable(false);						
					}else{
						//组合内元素 还原回rtLayer.primitives
						actionEntity.entity.groups=null;
						actionEntity.entity.zIndex=actionEntity.zIndex;
						actionEntity.entity.rtLayer=actionEntity.rtLayer;
						actionEntity.entity.rtLayer.primitives.splice(actionEntity.zIndex,0,actionEntity.entity);
						primitives.push(actionEntity.entity);
					}
				}
				this.rtDrawer.drawingLayer.create(primitives);
				//this.rtEntity.enable();
				break;
			case 'cancelGroup':
				var primitives=new Array();
				for(var i in this.actionEntitys){	
					var actionEntity=this.actionEntitys[i];
					if(actionEntity.group){
						//组合设置为可见
						actionEntity.entity.setVisiable(true);
						primitives.push(actionEntity.entity);
					}else{
						//组合内元素修复groups
						actionEntity.entity.groups=actionEntity.history.groups;
						actionEntity.entity.zIndex=actionEntity.history.zIndex;
						actionEntity.entity.setVisiable(false);
						//修复原层
						actionEntity.entity.rtLayer=actionEntity.history.rtLayer;
						//从组合层删除，删除后再恢复为正确顺序，且新zIndex永久大于等于原zIndex，不会影响恢复						
						actionEntity.entity.rtLayer.remove(actionEntity.entity);
						//从原层恢复
						actionEntity.entity.history.rtLayer.primitives.splice(actionEntity.history.zIndex,0,actionEntity.entity);
					}
									
					
				}
				this.rtDrawer.drawingLayer.create(primitives);
				//this.rtEntity.enable();
				break;
			case 'imp':
				var primitives;
				for(var i in this.actionEntitys){			
					var p=this.actionEntitys[i].entity;
					p.del=true;					
				}
				this.rtDrawer.drawingLayer.selectedPrimitives=new Array();
				this.rtDrawer.redraw();
				
			    break;
			case 'copy':
				var primitives;
				for(var i in this.actionEntitys){			
					var p=this.actionEntitys[i].entity;
					p.del=true;					
				}
				this.rtDrawer.drawingLayer.selectedPrimitives=new Array();
				this.rtDrawer.redraw();
				
			    break;
			default:
				//if(action){
					var primitives=new Array();
					//console.log(type+":"+this.actionEntitys);
					for(var i in this.actionEntitys){			
						var actionEntity=this.actionEntitys[i];
						actionEntity.undo();
						if(this.opMode=="sortTypes"||this.opMode=="groupSortTypes"){
							if(actionEntity.moveType=="main"){
								primitives.push(actionEntity.entity);
							}else{
								//辅助移动的不做处理
							}
						}else if(this.opMode=="insideGroup"){
							//组合内变形
							if(actionEntity.groups){
								primitives.push(actionEntity.entity);
							}else{
								//辅助移动的insertGroup
								this.rtDrawer.drawingLayer.insideGroupMode=true;//组合内部模式
								this.rtDrawer.drawingLayer.insideGroup=actionEntity.entity;//内部模式的组合元素
							}
						}else{
							primitives.push(actionEntity.entity);
						}
						
					}
					this.rtDrawer.drawingLayer.create(primitives);
					
				//}
				break;
		}
		
	},
	redo:function(){
		switch(this.type){
			case 'draw':
				var primitives=new Array();
				for(var i in this.actionEntitys){			
					var p=this.actionEntitys[i].entity;
					p.enable();
					primitives.push(p);
				}
				this.rtDrawer.drawingLayer.create(primitives);
			    break;
			case 'delete':			
				
				for(var i in this.actionEntitys){			
					var p=this.actionEntitys[i].entity;
					p.del=true;
					
				}
				this.rtDrawer.redraw();
				break;
			case 'drawGroup':
				var primitives=new Array();
				for(var i in this.actionEntitys){	
					var actionEntity=this.actionEntitys[i];
					if(actionEntity.group){
						//组合设置为可见
						actionEntity.entity.setVisiable(true);
						primitives.push(actionEntity.entity);
					}else{
						//组合内元素修复groups
						actionEntity.entity.groups=actionEntity.groups;
						actionEntity.entity.zIndex=actionEntity.zIndex;
						//从组合层删除
						actionEntity.entity.rtLayer.remove(actionEntity.entity);
						actionEntity.entity.rtLayer=actionEntity.groups.group.rtLayer;
						
					}
				}
				this.rtDrawer.drawingLayer.create(primitives);
				//this.rtEntity.enable();
				break;
			case 'cancelGroup':
				var primitives=new Array();
				for(var i in this.actionEntitys){	
					var actionEntity=this.actionEntitys[i];
					if(actionEntity.group){
						//组合设置为不可见
						actionEntity.entity.setVisiable(false);
						primitives=primitives.concat(actionEntity.entity.cancel());
					}else{
						/*
						//组合内元素删除groups
						actionEntity.entity.groups=null;
						actionEntity.entity.zIndex=actionEntity.zIndex;
						//修复原层
						actionEntity.entity.rtLayer=actionEntity.rtLayer;
						//

						//在组合层RTLAYER插入各组合内元素
						actionEntity.entity.rtLayer.primitives.splice(actionEntity.zIndex,0,actionEntity.entity);
						actionEntity.entity.groups=null;						
						*/
					}
				}
				this.rtDrawer.drawingLayer.create(primitives);
				//this.rtEntity.enable();
				break;
			case 'imp':
				var primitives;
				for(var i in this.actionEntitys){			
					var p=this.actionEntitys[i].entity;
					p.del=false;					
				}
				this.rtDrawer.redraw();
				
			    break;
			case 'copy':
				var primitives=new Array();
				for(var i in this.actionEntitys){			
					var p=this.actionEntitys[i].entity;
					p.del=false;		
					primitives.push(p);
				}
				this.rtDrawer.drawingLayer.create(primitives);
			    break;
			default:
				var primitives=new Array();
				for(var i in this.actionEntitys){			
					var actionEntity=this.actionEntitys[i];
					actionEntity.redo();
					if(this.opMode=="sortTypes"||this.opMode=="groupSortTypes"){
						if(actionEntity.moveType=="main"){
							primitives.push(actionEntity.entity);
						}else{
							//辅助移动的不做处理
						}
					}else if(this.opMode=="insideGroup"){
							//组合内变形
							if(actionEntity.groups){
								primitives.push(actionEntity.entity);
							}else{
								//辅助移动的insertGroup
								this.rtDrawer.drawingLayer.insideGroupMode=true;//组合内部模式
								this.rtDrawer.drawingLayer.insideGroup=actionEntity.entity;//内部模式的组合元素
							}
					}else{
						primitives.push(actionEntity.entity);
					}
				}
				this.rtDrawer.drawingLayer.create(primitives);
				break;
		}
	}
}

