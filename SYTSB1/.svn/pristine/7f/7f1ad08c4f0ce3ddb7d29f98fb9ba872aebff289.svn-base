//IE禁止选中对话框内容
function unselect(obj){
	if(document.attachEvent) {//ie的事件监听，拖拽div时禁止选中内容，firefox与chrome已在css中设置过-moz-user-select: none; -webkit-user-select: none;
	    obj.attachEvent('onselectstart', function() {
	      return false;
	    });
	}
}
//拖拽、声明需要用到的变量
var ox = 0,oy = 0;      //鼠标x、y轴坐标（相对于left，top）
var activeDraft = {};
document.onmousemove = function(e){
    var e = e || window.event;
    var x = e.pageX;      //移动时鼠标X坐标
    var y = e.pageY;      //移动时鼠标Y坐标
    if(activeDraft.isDraging){        //判断对话框能否拖动
      var moveX = activeDraft.dx + x - ox;      //移动后对话框新的left值
      var moveY = activeDraft.dy + y - oy;      //移动后对话框新的top值
      var left = moveX +'px';
      var top = moveY +'px'; 
      $(activeDraft).parents("div#amc").css({'left':left,'top':top});       //重新设置对话框的left
    };
};



/**
 * 添加标注
 * @param obj 元素（input、div、textarea）
 * @param step 控制是录入阶段还是审核阶段
 * @param labeled 是否已经标注过
 * @param content 标注的内容
 */
function label(obj,step,content){
	//在body加一个div position:absolute
	var allMarkContainer = $("#amc");
	if(allMarkContainer.length==0){
		allMarkContainer = document.createElement("div");
		$(allMarkContainer).attr({"id":"amc"});
		//头部，关闭按钮
		var head = document.createElement('div');
		$(head).addClass("header");
		var save = '<a href="javascript:void(0);" onclick="saveLabel();" class="save" ></a>';
		var undo = '<a href="javascript:void(0);" onclick="delLabel(this);" class="undo" ></a>';
		var close = '<a href="javascript:void(0);" onclick="closeLabel();" class="close" ></a>';
		$(head).append(save);
		if(input!='1'){
			//审核阶段
			$(head).append(undo);
		}
		$(head).append(close);
		
		$(allMarkContainer).append(head);
		//IE禁止选中对话框内容
		unselect(head);
		//鼠标按下,记录拖拽
		head.addEventListener('mousedown',function(e){
		    var e = e || window.event;
		    ox = e.pageX;      //点击时鼠标X坐标
		    oy = e.pageY;      //点击时鼠标Y坐标
		    activeDraft = this;
		    this.dx = $(this).parent("div#amc").get(0).offsetLeft;
		    this.dy = $(this).parent("div#amc").get(0).offsetTop;
		    this.isDraging = true;      //标记对话框可拖动
		});
		//鼠标离开
		head.addEventListener('mouseup',function(){
		    this.isDraging = false;
		    activeDraft={};
		});
		
		document.body.appendChild(allMarkContainer);
	}
	//首先将背景颜色设置为红色
	$(obj).css({'background-color':'#F08080'});
	//上下标已切换成div 2018-2-8
	var obj_id = $(obj).attr("id");
	obj_id = (obj.nodeName=='DIV'?obj_id.substring(0,obj_id.length-4):obj_id);
	//如果已经标注
	var ldiv = $(allMarkContainer).find("#"+obj_id+"_mark").get(0);
	if(ldiv){
		top.$.dialog.notice({
     		content: "请先撤销已有标注！"
    	});
		return;
	}
	//首次标注
	var dv = document.createElement("div");//标注div
	$(dv).addClass("bzhu_box").attr({"id": obj_id+"_mark"}).css({'width':'300px'});
	setMark(dv,obj_id,content,step);
	
	$(allMarkContainer).find(".bzhu_box").removeClass("active");
	$(dv).addClass("active");
	
	$(allMarkContainer).append(dv);
	$(obj).bind("click",function(){
		$("#amc").show();
		$(allMarkContainer).find(".bzhu_box").removeClass("active");
		$(allMarkContainer).find("#"+this.id+"_mark").addClass("active");
	});
}

//关闭标注
function closeLabel(){
	$("#amc").hide();
	return false;
}
function delLabel(){
	var itemId = $("#amc").find("div.active").attr("id").replace("_mark","");
	var id = $("#amc").find("div.active").find("div#"+itemId+"_id").html();
	$.ligerDialog.confirm("确定撤销当前标注？",function(yes){
		if(yes){
			if(id != undefined && id != ''){//id为空
				 $.post('reportItemValueAction/deleteLabel.do',{id:id},function(response){
					 if (response.success) {
						 	var $p = $("#"+itemId).parents("p.DocDefaults");
						 	if($p.find('input')[0]){
						 		$p.find('input').css("background-color", '#CAE1FF');
						 	}
						 	if($p.find('textarea')[0]){
						 		$p.find('textarea').css("background-color", '#CAE1FF');
						 	}
						 	if($p.find('div.editDiv')[0]){
						 		$p.find('div.editDiv').css("background-color", '#CAE1FF');
						 	}
						 	$("#amc").find("div.active").remove();
						 	if($("#amc").find(".bzhu_box").length>0){
						 		$("#amc").find("div.active").removeClass("active");
						 		$("#amc").find(".bzhu_box").aq(0).andClss("active");
						 	}
							top.$.dialog.notice({
			             		content: "撤销成功！"
			            	});
						} else {
							top.$.dialog.notice({
			             		content: "撤销失败！"
			            	});
						}
				 });
					
			}else{//为空，直接不显示
				var $p = $("#"+itemId).parents("p.DocDefaults");
			 	if($p.find('input')[0]){
			 		$p.find('input').css("background-color", '#CAE1FF');
			 	}
			 	if($p.find('textarea')[0]){
			 		$p.find('textarea').css("background-color", '#CAE1FF');
			 	}
			 	if($p.find('div.editDiv')[0]){
			 		$p.find('div.editDiv').css("background-color", '#CAE1FF');
			 	}
				$(obj).remove(); 
				top.$.dialog.notice({
             		content: "删除成功！"
            	});
			}
		}
	});
}

//初始化标注值
function setMark(obj,id,content,input){
	var box = $(obj);
	//标注id
	var tagId = document.createElement('div');
	$(tagId).attr({id:id+"_id",name:id+"_id"}).css({display:"none"});
	box.append(tagId);
	//标注类别
	var typeLabel = document.createElement('label');
	$(typeLabel).css({"text-indent":"10px"}).html("标注类别：");
	var options = markOptions;
	var type = document.createElement('select');
	$(type).attr({id:id+"_markType",name:id+"_markType"})
		.css({width:"200px",height:"24px","margin":"5px 30px",border:"1px solid #ccc",display:'block'});
	$(type).append('<option value="0"></option>');
	for(var k=0;k<options.length;k++){
		$(type).append('<option value="'+options[k]["id"]+'">'+options[k]["text"]+'</option>');
	}
	box.append(typeLabel).append(type);
	//标注状态
	var statusLabel = document.createElement('label');
	$(statusLabel).css({"text-indent":"10px"}).html("标注状态：");
	
	var statusOptions = [{id:'0',text:'未处理'},{id:'1',text:'已处理'}];
	var status = document.createElement('select');
	$(status).attr({id:id+"_status",name:id+"_status"})
		.css({width:"200px",height:"24px","margin":"5px 30px",border:"1px solid #ccc",display:'block'});
	for(var m=0;m<statusOptions.length;m++){
		$(status).append('<option value="'+statusOptions[m]["id"]+'">'+statusOptions[m]["text"]+'</option>');
	}
	box.append(statusLabel).append(status);	
	//标注内容
	var contentLabel = document.createElement('label');
	$(contentLabel).css({"text-indent":"10px"}).css({"text-indent":"10px"}).html("标注内容：");
	var contentDiv = document.createElement('div');
	$(contentDiv).attr({id:id+"_markContent",name:id+"_markContent"})
		.css({width:"200px","min-height":"24px","margin":"5px 30px",border:"1px solid #ccc","word-wrap":"break-word"});
	box.append(contentLabel).append(contentDiv);
	if(content){
		var mark = JSON.parse(content);
		$(tagId).html(mark["id"]);
		$(type).val(mark["markType"]);
		$(status).val(mark["status"]);
		$(contentDiv).html(mark["markContent"]);
	}else{
		$(type).val('0');
		$(status).val('0');
	}
	if(input != '1' ){//审核
		$(contentDiv).attr({"contenteditable":true});
	}else{
		$(type).attr({disabled:true});
		$(contentDiv).attr({"contenteditable":false});
	}
}

/**
 * 获取标注值
 * $box标注单元jquery对象
 * 
 */
function getMark($box,input){
	var label={};
	//标注所在页面
	var page=0;
	var indexN = paramss.pageName;
	//alert(indexN)
	page = indexN.substring(5,indexN.length-4);
	label.page=page;
	
	//注意命名规则
	var obj_id = $box.attr("id").replace("_mark","");
	label.id = $box.find("#"+obj_id+"_id").html();
	label.markType = $box.find("#"+obj_id+"_markType").val();
	label.status = $box.find("#"+obj_id+"_status").val();
	label.markContent = $box.find("#"+obj_id+"_markContent").html();
	label.originalContent = '';
	if(input!='1'){
		//审核阶段
		label.originalContent = $("#"+obj_id).val();
	}
	//标注字段，必须替换（去除base__），不然取不了
	var name = $("#"+obj_id).attr('name');
	var reg = /^base__/;
	if(reg.test(name)){
		name = name.replace(/^base__/,"");
	}
	label.item = name;
	//标注字段值
	label.itemValue = $("#"+obj_id).val();
	//business_id
	label.fkBusinessId = info_id;
	
	return label;
	
}

/**
 * 保存单个
 */
function saveLabel(){
	comm.mask("正在保存，请稍后...");
	if($("#amc").find("div.active").length==0){
		comm.LoadEnd(1);
		top.$.dialog.notice({
     		content: "未找到保存对象！"
    	});
		return;
	}
	
	var label  = getMark($("#amc").find("div.active"),input);
	$.ajax({
		url : 'reportItemValueAction/saveLabel.do?fk_report_id='+fk_report_id+'&fk_inspection_info_id='+info_id,
		type : 'post',
		async:false,
		dataType : "json",
		contentType : 'application/json;charset=utf-8', // 设置请求头信息
		data : JSON.stringify(label),
		success : function(response) {
			if (response.success) {
				var id = $("#amc").find("div.active").attr("id").replace("_mark","");
				//如果已处理则消除标注
				if(input=='1'&& response.data.status=='1'){
					$("#"+id).css("background-color", '#CAE1FF');
					/*if($(p).find('input')[0]){
						$(p).find('input').css("background-color", '#CAE1FF');
				 	}
				 	if($(p).find('textarea')[0]){
				 		$(p).find('textarea').css("background-color", '#CAE1FF');
				 	}
				 	if($(p).find('div.editDiv')[0]){
				 		$(p).find('div.editDiv').css("background-color", '#CAE1FF');
				 	}*/
					$("#amc").find("div.active").remove();
					if($("#amc").find(".bzhu_box").length>0){
				 		$("#amc").find("div.active").removeClass("active");
				 		$("#amc").find(".bzhu_box").aq(0).andClss("active");
				 	}
				}else{
					//设置id
					var backLabel = response.data;
					/*var idel = ($(p).find("#base__"+backLabel.item+"_id")[0]!=undefined
									?$(p).find("#base__"+backLabel.item+"_id")[0]
									:$(p).find("#base__"+backLabel.item+"_div_id")[0]!=undefined
									?$(p).find("#base__"+backLabel.item+"_div_id")[0]
									:$(p).find("#"+backLabel.item+"_id")[0]);
					$(idel).html(backLabel.id);*/
					$("#amc").find("div.active").find("#"+id+"_id").html(backLabel.id);
				}
				comm.LoadEnd(1);
				top.$.dialog.notice({
		     		content: "标注保存成功！"
		    	});
			} else {
				comm.LoadEnd(2);
				comm.Content("标注保存失败！" + response.msg);
			}

		}
	});
	return false;
}