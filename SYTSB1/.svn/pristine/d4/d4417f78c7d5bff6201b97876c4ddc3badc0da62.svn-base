
var sortTypes=["moveUp","moveDown","moveTop","moveBottom"]; //历史记录中可跳过的
var groupSortTypes=["groupMoveUp","groupMoveDown","groupMoveTop","groupMoveBottom"];//历史中记录中可跳过的
//特殊操作类型
function specialType(type){
	for(var i in sortTypes){ //rtParam
		if(type==sortTypes[i]){
			return "sortTypes";
		}
	}

	for(var i in groupSortTypes){
		if(type==groupSortTypes[i]){
			return "groupSortTypes";
		}
	}
	return false;
}

function RtHistory(rtDrawer){
	this.rtDrawer=rtDrawer;
	 
	this.undoActions=new Array();
	this.redoActions;

	this.jumps=["drawingGroupAction"];//临时多选图形
}


RtHistory.prototype={
	add:function (action){				
		this.undoActions.push(action);
		this.redoActions=null;

		if(this.refreshType(action)){
			//刷新组件列表
			EventHub.emit("RtComponentsList.refresh");
		}
		
	},
	refreshType:function(action){
		//执行刷新组件机制
		if(action.type=='draw'||action.type=='delete'||action.type=='cancelGroup'||action.type=='copy'||action.type=='imp'||action.type=='locked'||action.type=='unlocked'||specialType(action.type)){
			return true;
		}
		return false;	
	},	
    currentType:function(action){
		//执行当前action.undo redo
		if(action.type=='draw'||action.type=='delete'||action.type=='cancelGroup'||action.type=='copy'||action.type=='imp'||specialType(action.type)){
			return true;
		}
		return false;	
	},
	createTemporary:function (action){				
		this.temporary=new Array();
		this.temporary.push(action);
	},
	addTemporary:function (action){				
		this.undoActions.push(this.temporary.pop());
		this.redoActions=null;
	}
	,
	undo:function(){
		var length=this.undoActions.length;
		if(length>0){
			var action=this.undoActions.pop();
						
			if(!this.redoActions){
				this.redoActions=new Array();
			}
			this.redoActions.push(action);
			
			this.checkTypeUndo(action);

			if(this.refreshType(action)){
				//刷新组件列表
				EventHub.emit("RtComponentsList.refresh");
			}
		}else{
			console.log("没有上一步了。。。");
		}
	},
	checkTypeUndo:function(action){
		if(this.currentType(action)){
			action.undo(action.type);
			

		}else if(this.isJump(action.type)){			
			action=this.undoActions.pop();
			if(action){
				this.redoActions.push(action);
				this.checkTypeUndo(action);
			}
			
		}else{
			var length=this.undoActions.length;
			if(length>0){
				this.undoActions[length-1].undo(action.type);
			}else{
				alert("null undoActions");
			}
		}
	
	},
	redo:function(){
		if(this.redoActions&&this.redoActions.length>0){
			var action=this.redoActions.pop();

			

			if(this.isJump(action.type)){
				
				this.undoActions.push(action);		
				if(this.redoActions.length>0){
					action=this.redoActions.pop();					
				}else{
					action=null;
				}
			}
			if(action){
				action.redo();
				this.undoActions.push(action);
			}
			
			if(this.refreshType(action)){
				//刷新组件列表
				EventHub.emit("RtComponentsList.refresh");
			}
	
	
		}else{
			console.log("没有下一步了。。。");
		}
	},
	isJump:function(type){		
		for(var i in this.jumps){
			if(this.jumps[i]==type){
				return true;
			}
		}
		return false;
	}
	
	
}




