var copmonentsMap=new RtMap();//组件列表
/**
 * 加载组件
 */
function loadComponents() {
	$.post("com/rtd/components/getComponents.do", {
//		len : maxPendingCount
	}, function(response) {
		var $pendingList = $("#custom_components");
		if (response.success) {
			var data = response.data;
			if (data && data.length > 0) {
				$pendingList.html('');
				copmonentsMap=new RtMap();//重置组件列表
				$(data).each(
						function() {
							var componentName = this.componentName;
							var imgUrl=kFrameConfig.base + "fileupload/previewImage.do?id=" + this.fkAttIdImg;
							copmonentsMap.put(this.id,this.drawData);
							$pendingList.append("<li id=\""+this.id+"\" onclick=\"hitComponets('"+this.id+"')\"><img src=\""+imgUrl+"\" width=\"32\" height=\"29\"><p>"+componentName+"</p></li>");
						});
				$pendingList.append("<div class=\"cl\"></div>");

			} else {
				$pendingList.html('<li>暂无</li>');
			}
		} else {
			$pendingList.html('<li>加载失败</li>');
		}
	}, "json");
}
/* 绘制组件*/
function hitComponets(id){
	var data=copmonentsMap.get(id);
	importComponent(data); //rtDroc.js	
}

function hitRule(type){
	top.$.dialog({			
		width:500,
		height:200,
		parent : api,
		id : "RtDroc_Rule",
		title : "设置编号参数",
		content : "url:rtdroc/app/view/func/func_rule.jsp",
		cancel: true,
		ok : function() {
			var result = this.iframe.contentWindow.getSelectResult();	
//			alert(result);
			if(result){
				$("#ruleStart").val(result.ruleStart);
				$("#ruleShape").val(type);
				$("#rulePrefix").val(result.rulePrefix);
				initWindow();
				drawRule();
				
			}
			else 
				return false;
		}
	});
	
}

function openFile(){
	top.$.dialog({			
		width:900,
		height:600,
		parent : api,
		id : "RtDroc_Rule",
		title : "打开文件",
		content : "url:rtdroc/app/view/func/func_file.jsp",
		cancel: true,
		ok : function() {
			var result = this.iframe.contentWindow.getSelectResult();	
//			alert(result);
			if(result){
				
				initWindow();
				//drawRule();
				var id=result.id;
				if(id){					
					var drawData=getFileDrawData(id);
					if(drawData){
						importPage(drawData);
					}
				}
				
			}
			else 
				return false;
		}
	});
	
}

function getFileDrawData(id){
	var drawData;
	$.ajax({
		url : "com/rtd/files/getDrawDataById.do",
		type : 'post',
		async:false,
		dataType : "json",
		data : {id:id},
		success : function(response) {
			if (response.success) {
				drawData=response.data;
			}
		}
	});
	return drawData;
	
}

function getFileDrawDataByAttIdbak(attId){
	var drawData;
	$.ajax({
		url : "com/rtd/files/getDrawDataByAttId.do",
		type : 'post',
		async:false,
		dataType : "json",
		data : {attId:attId},
		success : function(response) {
			if (response.success) {
				drawData=response.data;
			}
		}
	});
	return drawData;
}

function getDrawDataByAttId(attId){
	var drawData;
	$.ajax({
		url : "com/rtd/files/getDrawDataByAttId.do",
		type : 'post',
		async:false,
		dataType : "json",
		data : {attId:attId},
		success : function(response) {
			if (response.success) {
				drawData=response.data;
			}
		}
	});
	return drawData;
	
}

function getDrawDataByImgAttId(attId){
	var drawData;
	$.ajax({
		url : "com/rtd/files/getDrawDataByImgAttId.do",
		type : 'post',
		async:false,
		dataType : "json",
		data : {attId:attId},
		success : function(response) {
			if (response.success) {
				drawData=response.data;
			}
		}
	});
	return drawData;
}


//用于反写
function getSelectResult(){
	return save();
}