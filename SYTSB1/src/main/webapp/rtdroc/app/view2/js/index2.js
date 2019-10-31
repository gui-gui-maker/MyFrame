/*
var treeView = tools.$('#treeView');
var fileData = data.files;

treeView.innerHTML = treeHtml(fileData, -1);
var fileItem = tools.$('.treeNode');
var root_icon = tools.$('.icon-control', fileItem[0])[0];
root_icon.className = 'icon icon-control icon-minus';
tools.each(fileItem, function(item) {
	filesHandle(item);
});
*/
var fileData = data.files;

function refreshComponents(){
//	debugger;
	var rtLayers=rtDrawer.layers;
	var data=new Array();
//	data.push({
//		id: 0,
//		pid: -1,
//		title: '组件列表'
//	});
	//debugger;
	//图层			
	for(var i=rtLayers.length-1;i>=0;i--){
		//元素层
		var rtLayer=rtLayers[i];
		if(!rtLayer.visiable){
			continue;
		}
		var rtPrimitives=rtLayer.primitives;
		
	
		for(var j=rtPrimitives.length-1;j>=0;j--){
			var rtPrimitive=rtPrimitives[j];
			if(!rtPrimitive.visiable||rtPrimitive.del){
				continue;
			}
			//rtPrimitive.pid=0;
			var obj={};
			obj.id=rtPrimitive.id;
			obj.pid=-1;
			if(!rtPrimitive.title){
				//rtPrimitive.title=rtPrimitive.data.cname+"_"+rtPrimitive.id;
				var lock="";
				if(rtPrimitive.style.isLocked){
					lock="(锁)";
				}
				obj.title=rtPrimitive.data.cname+"_"+rtPrimitive.id+lock;
			}
			data.push(obj);
			if(rtPrimitive.type=="RtGroup"){
				var primitives=rtPrimitive.data.primitives;
				iterationPrimitives(primitives,rtPrimitive.id,data);
			}
			/*
			var $li =$( " <li id=\"component_"+rtPrimitive.id+"\" ><a href=\"javascript:selectPrimitive('"+rtPrimitive.id+"');refreshClass('"+rtPrimitive.id+"');\">"+rtPrimitive.data.cname+"("+rtPrimitive.id+")</a></li> ");
			$componentsList.append($li);			
			if(rtPrimitive.type=="RtGroup"){
				var primitives=rtPrimitive.data.primitives;
				for(var z=primitives.length-1;z>=0;z--){
					var primitive=rtPrimitive.data.primitives[z];
					var $lis =$( " <li id=\"component_"+primitive.id+"\" ><a href=\"javascript:selectPrimitive('"+primitive.id+"');refreshClass('"+primitive.id+"');\">——"+primitive.data.cname+"("+primitive.id+")</a></li> ");
					$componentsList.append($lis);	
				}
					
			}
			*/
		}
		
	}
//	debugger;
//	createTreeView(fileData);
	createTreeView(data);   //index2.js
//	console.log(data);
}
function iterationPrimitives(rtPrimitives,pid,data){
	for(var j=rtPrimitives.length-1;j>=0;j--){
		var rtPrimitive=rtPrimitives[j];
		//rtPrimitive.pid=0;
		var obj={};
		obj.id=rtPrimitive.id;
		obj.pid=pid;
		if(!rtPrimitive.title){
			//rtPrimitive.title=rtPrimitive.data.cname+"_"+rtPrimitive.id;
			obj.title=rtPrimitive.data.cname+"_"+rtPrimitive.id;
		}
		
		data.push(obj);
		if(rtPrimitive.type=="RtGroup"){
			var primitives=rtPrimitive.data.primitives;
			iterationPrimitives(primitives,rtPrimitive.id,data);
				
		}
	}
}


function createTreeView(fileData){
	var treeView = tools.$('#treeView');
//	var fileData = data.files;

	treeView.innerHTML = treeHtml(fileData, -1);
	var fileItem = tools.$('.treeNode');
	var root_icon = tools.$('.icon-control', fileItem[0])[0];
	if(root_icon){
		root_icon.className = 'icon icon-control icon-minus';	
	}
	
	tools.each(fileItem, function(item) {
		filesHandle(item,fileData);
	});
}
//选中组件
function selectComponent(selectedPrimitives){
	$('.treeNode-cur').each(function(i){
		$(this).removeClass('treeNode-cur');
	})
	
	if(!selectedPrimitives||selectedPrimitives.length<=0){
		return;
	}
	for(var i in selectedPrimitives){
		var obj=$("div[data-file-id='"+selectedPrimitives[i].id+"']");
		obj.addClass('treeNode-cur');
	}	

}

function treeHtml(fileData, fileId) {
	var _html = '';
	var children = getChildById(fileData, fileId);
	var hideChild = fileId > 0 ? 'none' : '';
	_html += '<ul class="' + hideChild + '">';
	children.forEach(function(item, index) {
		var level = getLevelById(fileData, item.id);
		var distance = (level - 1) * 20 + 'px';
		var hasChild = hasChilds(fileData, item.id);
		var className = hasChild ? '' : 'treeNode-empty';
		//var treeRoot_cls = fileId === -1 ? 'treeNode-cur' : '';
		var treeRoot_cls =  '';
		_html+=`
	        <li>
	          <div class="treeNode ${className} ${treeRoot_cls}" style="padding-left: ${distance}" data-file-id="${item.id}">
	            <i class="icon icon-control icon-add"></i>
	            <i class="icon icon-file"></i>
	            <span class="title">${item.title}</span>
	          </div>
	          ${treeHtml(fileData,item.id)}
	        </li>`;
	});
	_html += '</ul>';
	return _html;
};
function filesHandle(item,fileData) {
	tools.addEvent(item, 'click', function() {
		var treeNode_cur = tools.$('.treeNode-cur')[0];
		var fileId = item.dataset.fileId;
		var curElem = document.querySelector('.treeNode[data-file-id="'
				+ fileId + '"]');
		var hasChild = hasChilds(fileData, fileId);
		
		var icon_control = tools.$('.icon-control', item)[0];
		var openStatus = true;
		if(treeNode_cur){
			tools.removeClass(treeNode_cur, 'treeNode-cur');	
		}
		
		tools.addClass(curElem, 'treeNode-cur');
		if (hasChild) {
			openStatus = tools.toggleClass(item.nextElementSibling, 'none');
			if (openStatus) {
				icon_control.className = 'icon icon-control icon-add';
			} else {
				icon_control.className = 'icon icon-control icon-minus';
			}
			
		}
		//组件选中事件
		EventHub.emit("ViewComponentsList.select",fileId);
	});
};
