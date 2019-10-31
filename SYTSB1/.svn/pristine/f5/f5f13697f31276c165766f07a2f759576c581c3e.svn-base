
var focusTarget = null;
var focusTargetBack = null;
var backColor = "#6495ED";
/**
 * 对录入框调整 input textarea div
 * placeholder
 * 
 */

$(function(){
	
	/*
	$("input").attr("onchange","changeInput(this)");
	$("textarea").attr("onchange","changeInput(this)");*/
	
})


function addFocusListener(){
	$("input").focus(function() {
		$("#inputFocus").val($(this).attr("id"));
		bindInputEl = this;
		parent.showProperties(this);
		parent.setPropertiesShow();
	});
	$("textarea").focus(function() {
		$("#inputFocus").val($(this).attr("id"));
		bindInputEl = this;
		parent.showProperties(this);
		parent.setPropertiesShow();
		
	});
	$("div .uploadPhoto").click(function() {
		$("#inputFocus").val($(this).attr("id"));
		console.log($(this).attr("id"))
		bindInputEl = this;
		parent.showProperties(this);
		parent.setPropertiesShow();
		
	});
	$("div .checkboxDiv").focus(function() {
		$("#inputFocus").val($(this).attr("id"));
		console.log($(this).attr("id"))
		bindInputEl = this;
		parent.showProperties(this);
		parent.setPropertiesShow();
		
	});
	
}


/**
 * 显示输入框命名
 * @returns
 */
function showNamed11(){
	addFocusListener();
	var $inputs = $("input");

	for (var i = 0; i < $inputs.length; i++) {
		
		var item = $inputs[i];
		var $item = $(item);
		var name = $item.attr("name");
		var value = $item.val();
		
		var placeholder = "";
		
		if(name!=null){
			
			placeholder = name;
			
		}
		
		/*if(value!=null&&value!=""){
			placeholder = placeholder + " 默认值："+value;
			
		}*/
		//$item.attr("placeholder",placeholder);
		$item.attr("values",value);
		$item.attr("value",placeholder);
		$item.attr("title",placeholder);
		
	}
	for (var i = 0; i < $("textarea").length; i++) {
		var item =$("textarea")[i];
		var $item = $(item);
		var name = $item.attr("name");
		var values = $item.val();
		$item.attr("values",values);
		$item.val(name);
	}
	for (var i = 0; i < $("div .uploadPhoto").length; i++) {
		var item =$("div .uploadPhoto")[i];
		var $item = $(item);
		var name = $item.attr("name");
		
		$item.html(name);
	}
	for (var i = 0; i < $("div .checkboxDiv").length; i++) {
		var item =$("div .checkboxDiv")[i];
		var $item = $(item);
		var name = $item.attr("name");
		
		$item.html(name);
	}
	
}

/**
 * 影藏输入框命名
 * @returns
 */
function hideNamed(){
	$("input").removeAttr("placeholder");
	var $inputs = $("input");

	for (var i = 0; i < $inputs.length; i++) {
		
		var item = $inputs[i];
		var $item = $(item);
		var name = $item.attr("name");
		var value = $item.val();
		var values = $item.attr("values");
		
		if(values!=""&&values!=undefined){
			
			value = values;
			
		}else{
			value = "";
		}
		
		$item.attr("value",value);
		$item.attr("title","");
	}
	//清除名字
	$("div .uploadPhoto").html("");
	$("div .checkboxDiv").html("");
	for (var i = 0; i < $("textarea").length; i++) {
		var item =$("textarea")[i];
		var $item = $(item);
		var value = $item.attr("values");
		$item.val(value);
	}
	//清除选择项
	cleanSelect();
	
}


/**
 * 弹框方式修改命名，用于不能直接改的小框框，
 * 或者关联命名
 * @returns
 */
function modifyName(){
	var id = $("#inputFocus").val();
	if(id==""){
		alert("请选择需要修改的输入框！");
	}
	alert(id);
	
}

/**
 * 修改默认值，
 * 提供弹框方式批量修改
 * @returns
 */
function modifyInitValue(){
	var id = $("#inputFocus").val();
	if(id==""){
		alert("请选择需要修改的输入框！");
	}
	var valOld = $("#"+id).attr("values");
	alert(valOld);
}


/**
 * 使用值改变事件来快速修改命名(已取消，会影响模板原有事件)
 * 此处用于设计，输入录入没得其他作用
 * @param e
 * @returns
 */
function changeInput(e){
	  //移除绑定功能
    removeBindingFun();
    //移除绑定事件
    unbindFun();
    //记录缓存
    recordTable();
    //重新绑定功能
    bindingFun();
    //重新绑定右击事件
    bindingEvent();
    
	//验证下修改后的命名
	var nameNew = e.value;
	if(nameNew.indexOf("base__")!=0
			&&nameNew.indexOf("TBL")!=0
			&&nameNew.indexOf("fk__")!=0
			&&nameNew.indexOf("record__")!=0
			&&nameNew.indexOf("conclusion__")!=0){
		//不符合要求的命名
		alert("命名必须由'base__'、'TBL'、'fk'、'record__'、'conclusion__'开头，请检查！");
		e.value = e.id;
		$(e).focus();
	}else{
		e.id = e.value;
		e.name = e.value;	
	}
	
}

/**
 * 修改输入框命名
 * @param name
 * @returns
 */
function changeInputName(name){
	 //移除绑定功能
    removeBindingFun();
    //移除绑定事件
    unbindFun();
    //记录缓存
    recordTable();
    //重新绑定功能
    bindingFun();
    //重新绑定右击事件
    bindingEvent();
	//验证下修改后的命名
	var inputFocus = $("#inputFocus").val();
	$inputFocus = $("#"+inputFocus);
	var nameNew = name;
	if(nameNew.indexOf("base__")!=0
			&&nameNew.indexOf("TBL")!=0
			&&nameNew.indexOf("fk__")!=0
			&&nameNew.indexOf("record__")!=0
			&&nameNew.indexOf("conclusion__")!=0){
		//不符合要求的命名
		alert("命名必须由'base__'、'TBL'、'fk'、'record__'、'conclusion__'开头，请检查！");
		
		$inputFocus.focus();
	}else{
		alert(inputFocus)
		$inputFocus.attr("id",nameNew);
		$inputFocus.attr("name",nameNew);
		$inputFocus.attr("value",nameNew);
		$("#inputFocus").val(nameNew)
	}
	
}
/**
 * 获取选择输入框
 * @returns
 */
function getinputFocusName(){
	var id = $("#inputFocus").val();
	if(id==""){
		alert("没有选择修改的输入框！");
	}
	return;
}

/**
 * 设置选择输入框（根据配置信息命名）
 * @param val
 * @returns
 */
function setinputFocusName(val){
	
	 //移除绑定功能
    removeBindingFun();
    //移除绑定事件
    unbindFun();
    //记录缓存
    recordTable();
    //重新绑定功能
    bindingFun();
    //重新绑定右击事件
    bindingEvent();
	var inputFocus = $("#inputFocus").val();
	
	$("#"+inputFocus).attr("value","base__"+val);
	$("#"+inputFocus).attr("title","base__"+val);
	$("#"+inputFocus).attr("name","base__"+val);
	$("#"+inputFocus).attr("id","base__"+val);
	$("#inputFocus").val("base__"+val);
	
}
/**
 * 修改控件文本类型
 * @returns
 */
function changeTagType(type){
	var inputFocus = $("#inputFocus").val();
	var tag = document.getElementById(inputFocus);
	
	if(tag!=input){
		alert("此选项只支持单行输入框！");
		return;
	}
	$("#"+inputFocus).attr("ltype",inputFocus);
	
	
}


/**
 * 修改默认值
 * @param val
 * @returns
 */
function setinputFocusInitValue(val){
	 //移除绑定功能
    removeBindingFun();
    //移除绑定事件
    unbindFun();
    //记录缓存
    recordTable();
    //重新绑定功能
    bindingFun();
    //重新绑定右击事件
    bindingEvent();
	var inputFocus = $("#inputFocus").val();
	$("#"+inputFocus).attr("values",val);
	
	var $inputFocus = $("#"+inputFocus);
	
	var ligerUI = $inputFocus.attr("ligerui");

	var ligerui = [];
	if(ligerui!=undefined){
		try {
			ligerui = eval('(' + ligerUI + ')');
			
		} catch (e) {
			//alert(ligerui1)
		}
	}
	try {
		ligerui["initValue"]=val;
		
	} catch (e) {
		//alert(ligerui1)
	}
	$inputFocus.attr("ligerui",JSON.stringify(ligerui));
	
	var pclicks = $(".seled");
	if(pclicks.length==1){
		return;
	}
	for (var i = 0; i < pclicks.length; i++) {
		var pclick = pclicks[i];
		/*var pclick = $(pclicks[i]).find("input");
		if(pclick==undefined||pclick==""){
			pclick = $(pclicks[i]).find("TEXTAREA");
		}*/
		if(pclick!=undefined){
			var $pclick = $(pclick);
			$pclick.attr("values",val);
			var ligerUIP = $pclick.attr("ligerui");

			var ligeruiP = [];
			if(ligeruiP!=undefined){
				try {
					ligeruiP = eval('(' + ligerUIP + ')');
					
				} catch (e) {
					//alert(ligerui1)
				}
			}
			try {
				ligeruiP["initValue"]=val;
				
			} catch (e) {
				//alert(ligerui1)
			}
			
			$pclick.attr("ligerui",JSON.stringify(ligeruiP));
		}
	}
}

/**
 * 修改文本框类型
 * @param val
 * @returns
 */
function changeTag(type){
	 //移除绑定功能
    removeBindingFun();
    //移除绑定事件
    unbindFun();
    //记录缓存
    recordTable();
    //重新绑定功能
    bindingFun();
    //重新绑定右击事件
    bindingEvent();
	var inputFocus = $("#inputFocus").val();
	var $inputFocus = $("#"+inputFocus);

	var name = $inputFocus.attr("name");
	var value = $inputFocus.attr("values");
	if(value==undefined){
		value = $inputFocus.text();
	}
	
	var onchange = $inputFocus.attr("onchange");
	var ligerUI = $inputFocus.attr("ligerui");
	var classs = $inputFocus.attr("class");
	var style = $inputFocus.attr("style");
	
	var ligerui = {};
	try {
		ligerui = JSON.parse(ligerUI);
		//ligerui = eval('(' + ligerUI + ')');
	} catch (e) {
		//alert(ligerui1)
	}
	var tag = "input"; 
	var html = "";
	if("INPUT"==type){
		
		tag = "input";
		html = '<'+tag+ ' id="'+inputFocus+'" name="'+name+'" value="'+name+'" values="'+value+'"'
		+' type="text" ltype="text" ';
		if(onchange!=undefined){
			html = html + ' onchange = "'+onchange+'"';
		}
		if(style!=undefined){
			html = html + ' style="'+style+'" ';
		}
		if(ligerUI!=undefined){
			html = html + ' ligerui="'+ligerUI+'" ';
		}
		html = html + ' />';
		
	}else if("TEXTAREA"==type){
		tag = "textarea";
		 html = '<'+tag+ ' id="'+inputFocus+'" name="'+name+'" value="'+name+'" values="'+value+'" '
		+' type="text" ltype="text" ';
		 if(onchange!=undefined){
				html = html + ' onchange = "'+onchange+'"';
		}
		 if(style!=undefined){
				html = html + ' style="'+style+'" ';
		 }
		 if(ligerUI!=undefined){
			html = html + ' ligerui="'+ligerUI+'" ';
		}
		html = html + ' >'+name+'</textarea> ';
	}else if("SELECT"==type){
		tag = "input";
		 html = '<'+tag+ ' id="'+inputFocus+'" name="'+name+'" value="'+name+'" values="'+value+'" '
		+' type="text" ltype="select" ';
		 if(onchange!=undefined){
				html = html + ' onchange = "'+onchange+'"';
		}
		  if(style!=undefined){
				html = html + ' style="'+style+'" ';
		 }
		 if(ligerUI!=undefined){
			 if(ligerui.isMultiSelect!=undefined){
				 delete ligerui.isMultiSelect;
			 }
			html = html + ' ligerui="'+JSON.stringify(ligerui).replace(/"/g,"'")+'"';
		}
		html = html + ' />';
	}else if("SELECT2"==type){
		tag = "input";
		tag = "input";
		 html = '<'+tag+ ' id="'+inputFocus+'" name="'+name+'" value="'+name+'" values="'+value+'" '
		+' type="text" ltype="select" ';
		 if(onchange!=undefined){
				html = html + ' onchange = "'+onchange+'"';
		}
		 if(style!=undefined){
				html = html + ' style="'+style+'" ';
		 }
		 if(ligerUI!=undefined){
			ligerui["isMultiSelect"]=true;
			html = html + ' ligerui="'+JSON.stringify(ligerui).replace(/"/g,"'")+'" ';
		}else{
			ligerui["isMultiSelect"]=true;
			html = html + " ligerui='"+JSON.stringify(ligerui).replace(/"/g,"'")+"' ";
		}
		html = html + ' />';
	}else if("CHECKBOX"==type){
		tag = "input";
		 html = '<'+tag+ ' id="'+inputFocus+'" name="'+name+'" value="'+name+'" values="'+value+'" '
			+' type="checkbox" ltype="checkBox" ';
		 if(onchange!=undefined){
				html = html + ' onchange = "'+onchange+'"';
		}
		  if(style!=undefined){
				html = html + ' style="'+style+'" ';
		 }
		 if(ligerUI!=undefined){
				html = html + ' ligerui="'+ligerUI+'" ';
			}
			html = html + ' />';
	}else if("RADIO"==type){
		tag = "input";
		 html = '<'+tag+ ' id="'+inputFocus+'" name="'+name+'" value="'+name+'" values="'+value+'" '
			+' type="radio" ltype="select" ';
		 if(onchange!=undefined){
				html = html + ' onchange = "'+onchange+'"';
		}
		 if(style!=undefined){
				html = html + ' style="'+style+'" ';
		 }
		 if(ligerUI!=undefined){
				html = html + ' ligerui="'+ligerUI+'" ';
			}
			html = html + ' />';
	}else if("DATE"==type){
		tag = "input";
		 html = '<'+tag+ ' id="'+inputFocus+'" name="'+name+'" value="'+name+'" values="'+value+'" '
			+' type="text" ltype="date" ';
		 if(onchange!=undefined){
				html = html + ' onchange = "'+onchange+'"';
		}
		  if(style!=undefined){
				html = html + ' style="'+style+'" ';
		 }
		 if(ligerUI!=undefined){
				html = html + ' ligerui="'+ligerUI+'" ';
			}
			html = html + ' />';
	}else if("PICTRUE"==type){
		var td = $inputFocus.parents("td")[0];
		var width = td.offsetWidth;
		var height = td.offsetHeight;
		/*var tdDat = getIE(td);
		alert(tdDat.top+"-----"+tdDat.left)
		alert(width+"-----"+height)*/
		//图片
		html = "<div id='"+name+"' name='"+name+"' class='uploadPhoto' "
		+"style='width: "+width+"px; height:"+height+"px' "
		+"align='center'>"+name+"</div>"
	}else if("CHECKBOXDIV"==type){
		//复选框
		html = "<div id='"+name+"' name='"+name+"' class='checkboxDiv'  type='text' ltype='checkBox' "
		+"align='center'"
		 if(onchange!=undefined){
				html = html + ' onchange = "'+onchange+'"';
		}
		 if(style!=undefined){
			 if(style.indexOf("height")!=-1){
				 html = html + ' style="'+style+'" ';
			 }else{
				 html = html + ' style="'+style+';height: 6mm;" ';
			 }
				
		 }else{
			 html = html +"style='height: 6mm;' "
		 }
		 if(ligerUI!=undefined){
			html = html + ' ligerui="'+JSON.stringify(ligerui).replace(/"/g,"'")+'" ';
		}
		 html = html + ' value="'+name+'">'+name+'</div>';
	}
	$inputFocus.after(html);
	$inputFocus.remove();

	$("#"+inputFocus).focus(function(){
		$("#inputFocus").val($(this).attr("id"));
		bindInputEl = this;
		parent.showProperties(this);
		parent.setPropertiesShow();
	})
	
	
	
	
	var pclicks = $(".seled");
	if(pclicks.length==1){
		return;
	}
	for (var i = 0; i < pclicks.length; i++) {
		var pclick = pclicks[i];
		/*var pclick = $(pclicks[i]).find("input");
		if(pclick==undefined||pclick==""){
			pclick = $(pclicks[i]).find("TEXTAREA");
		}*/
		if(pclick!=undefined){


			var $pclick = $(pclick);
			var typep = type;
			var namep = $pclick.attr("name");
			var valuep = $pclick.attr("values");
			if(valuep==undefined){
				valuep = $pclick.text();
			}
			
			var onchangep = $pclick.attr("onchange");
			var ligerUIp = $pclick.attr("ligerui");
			var classsp = $pclick.attr("class");
			var stylep = $pclick.attr("style");
			
			var ligeruip = {};
			try {
				ligeruip = JSON.parse(ligerUIp);
				//ligeruip = eval('(' + ligerUIp + ')');
			} catch (e) {
				//alert(ligerui1)
			}
			var tagp = "input"; 
			var htmlp = "";
			if("INPUT"==typep){
				
				tagp = "input";
				htmlp = '<'+tagp+ ' id="'+namep+'" name="'+namep+'" value="'+namep+'" values="'+valuep+'"'
				+' type="text" ltype="text" ';
				if(onchangep!=undefined){
					htmlp = htmlp + ' onchange = "'+onchangep+'"';
				}
				if(stylep!=undefined){
					htmlp = htmlp + ' style="'+stylep+'" ';
				}
				if(ligerUIp!=undefined){
					htmlp = htmlp + ' ligerui="'+ligerUIp+'" ';
				}
				htmlp = htmlp + ' />';
				
			}else if("TEXTAREA"==typep){
				tagp = "textarea";
				 htmlp = '<'+tagp+ ' id="'+namep+'" name="'+namep+'" value="'+namep+'" values="'+valuep+'" '
				+' type="text" ltype="text" ';
				 if(onchangep!=undefined){
						htmlp = htmlp + ' onchange = "'+onchangep+'"';
				}
				 if(stylep!=undefined){
						htmlp = htmlp + ' style="'+stylep+'" ';
				 }
				 if(ligerUIp!=undefined){
					htmlp = htmlp + ' ligerui="'+ligerUIp+'" ';
				}
				htmlp = htmlp + ' >'+namep+'</textarea> ';
			}else if("SELECT"==typep){
				tagp = "input";
				 htmlp = '<'+tagp+ ' id="'+namep+'" name="'+namep+'" value="'+namep+'" values="'+valuep+'" '
				+' type="text" ltype="select" ';
				 if(onchangep!=undefined){
						htmlp = htmlp + ' onchange = "'+onchangep+'"';
				}
				  if(stylep!=undefined){
						htmlp = htmlp + ' style="'+stylep+'" ';
				 }
				  if(ligerUIp!=undefined){
						 if(ligeruip.isMultiSelect!=undefined){
							 delete ligeruip.isMultiSelect;
						 }
						htmlp = htmlp + ' ligerui="'+JSON.stringify(ligeruip).replace(/"/g,"'")+'"';
					}
				htmlp = htmlp + ' />';
			}else if("SELECT2"==typep){
				tagp = "input";
				tagp = "input";
				 htmlp = '<'+tagp+ ' id="'+namep+'" name="'+namep+'" value="'+namep+'" values="'+valuep+'" '
				+' type="text" ltype="select" ';
				 if(onchangep!=undefined){
						htmlp = htmlp + ' onchange = "'+onchangep+'"';
				}
				 if(stylep!=undefined){
						htmlp = htmlp + ' style="'+stylep+'" ';
				 }
				 if(ligerUIp!=undefined){
					ligeruip["isMultiSelect"]=true;
					alert(ligeruip["data"])
					htmlp = htmlp + ' ligerui="'+JSON.stringify(ligeruip).replace(/"/g,"'")+'" ';
				}else{
					ligeruip["isMultiSelect"]=true;
					htmlp = htmlp + ' ligerui="'+JSON.stringify(ligeruip).replace(/"/g,"'")+'" ';
				}
				htmlp = htmlp + ' />';
			}else if("CHECKBOX"==typep){
				tagp = "input";
				 htmlp = '<'+tagp+ ' id="'+namep+'" name="'+namep+'" value="'+namep+'" values="'+valuep+'" '
					+' type="checkbox" ltype="checkbox" ';
				 if(onchangep!=undefined){
						htmlp = htmlp + ' onchange = "'+onchangep+'"';
				}
				  if(stylep!=undefined){
						htmlp = htmlp + ' style="'+stylep+'" ';
				 }
				 if(ligerUIp!=undefined){
						htmlp = htmlp + ' ligerui="'+ligerUIp+'" ';
					}
					htmlp = htmlp + ' />';
			}else if("RADIO"==typep){
				tagp = "input";
				 htmlp = '<'+tagp+ ' id="'+namep+'" name="'+namep+'" value="'+namep+'" values="'+valuep+'" '
					+' type="radio" ltype="select" ';
				 if(onchangep!=undefined){
						htmlp = htmlp + ' onchange = "'+onchangep+'"';
				}
				 if(stylep!=undefined){
						htmlp = htmlp + ' style="'+stylep+'" ';
				 }
				 if(ligerUIp!=undefined){
						htmlp = htmlp + ' ligerui="'+ligerUIp+'" ';
					}
					htmlp = htmlp + ' />';
			}else if("DATE"==typep){
				tagp = "input";
				 htmlp = '<'+tagp+ ' id="'+namep+'" name="'+namep+'" value="'+namep+'" values="'+valuep+'" '
					+' type="text" ltype="date" ';
				 if(onchangep!=undefined){
						htmlp = htmlp + ' onchange = "'+onchangep+'"';
				}
				  if(stylep!=undefined){
						htmlp = htmlp + ' style="'+stylep+'" ';
				 }
				 if(ligerUIp!=undefined){
						htmlp = htmlp + ' ligerui="'+ligerUIp+'" ';
					}
					htmlp = htmlp + ' />';
			}else if("PICTRUE"==typep){
				//图片
				var tdp = $pclick.parents("td")[0];
				var widthp = tdp.offsetWidth;
				var heightp = tdp.offsetHeight;
				/*var tdDat = getIE(td);
				alert(tdDat.top+"-----"+tdDat.left)
				alert(width+"-----"+height)*/
				//图片
				htmlp = "<div id='"+namep+"' name='"+namep+"' class='uploadPhoto' "
				+"style='width: "+widthp+"px; height:"+heightp+"px' "
				+"align='center'>"+namep+"</div>"
			}else if("CHECKBOXDIV"==typep){
				//复选框
				htmlp = "<div id='"+namep+"' name='"+namep+"' class='checkboxDiv'  type='text' ltype='checkBox' "
				+"align='center'"
				 if(onchangep!=undefined){
						htmlp = htmlp + ' onchange = "'+onchangep+'"';
				}
				 if(stylep!=undefined){
					 if(stylep.indexOf("height")!=-1){
						 htmlp = htmlp + ' style="'+stylep+'" ';
					 }else{
						 htmlp = htmlp + ' style="'+stylep+';height: 6mm;" ';
					 }
						
				 }else{
					 htmlp = htmlp +"style='height: 6mm;' "
				 }
				 if(ligerUIp!=undefined){
					htmlp = htmlp + ' ligerui="'+JSON.stringify(ligeruip).replace(/"/g,"'")+'" ';
				}
				 htmlp = htmlp + ' value="'+namep+'">'+namep+'</div>';
			}
			$pclick.after(htmlp);
			$pclick.remove();

			$("#"+namep).focus(function(){
				$("#inputFocus").val($(this).attr("id"));
				bindInputEl = this;
				parent.showProperties(this);
				parent.setPropertiesShow();
			})
			
			
		}
	}
	
	
}




/**
 * 下拉选项自定义
 * @returns
 */
function changeInputDataConfig(data){
	 //移除绑定功能
    removeBindingFun();
    //移除绑定事件
    unbindFun();
    //记录缓存
    recordTable();
    //重新绑定功能
    bindingFun();
    //重新绑定右击事件
    bindingEvent();
	if(!isJson(data)){
		alert("自定义选择格式不符合要求，请检查！,格式如“[{\"id\":\"xx\",\"text\":\"xx\"},{\"id\":\"yy\",\"text\":\"yy\"}]”");
		return;
	}
	var inputFocus = $("#inputFocus").val();
	var $inputFocus = $("#"+inputFocus);
	var ligerUI = $inputFocus.attr("ligerui");

	var ligerui = {};
	
	if(ligerUI!=undefined){
		try {
			ligerui = eval('(' + ligerUI + ')');
		} catch (e) {
			//alert(ligerui1)
		}
	}
	var datan =  eval('(' + data + ')')

	ligerui["data"] = datan;
	console.log(JSON.stringify(ligerui));
	$inputFocus.attr("ligerui",JSON.stringify(ligerui).replace(/"/g,"'"));
	
}



/**
 * 下拉选项码表设置
 * @returns
 */
function changeInputCodeConfig(code){
	 //移除绑定功能
    removeBindingFun();
    //移除绑定事件
    unbindFun();
    //记录缓存
    recordTable();
    //重新绑定功能
    bindingFun();
    //重新绑定右击事件
    bindingEvent();
	var inputFocus = $("#inputFocus").val();
	var $inputFocus = $("#"+inputFocus);
	var ligerUI = $inputFocus.attr("ligerui");

	var ligerui = {};
	
	if(ligerUI!=undefined){
		try {
			ligerui = eval('(' + ligerUI + ')');
		} catch (e) {
			//alert(ligerui1)
		}
	}
	if(code==null){
		if(ligerui.code!=undefined){
			delete ligerui.code;
		}
	}else{
		ligerui["code"] = code;
	}
	
	$inputFocus.attr("ligerui",JSON.stringify(ligerui).replace(/"/g,"'"));
	
}

/**
 * 校验字符串是否合适json格式
 * @param str
 * @returns
 */
function isJson(str) {
    if (typeof str == 'string') {
        try {
            var obj=JSON.parse(str);
            if(typeof obj == 'object' && obj ){
                return true;
            }else{
                return false;
            }
        } catch(e) {
            console.log('error：'+str+'!!!'+e);
            return false;
        }
    }
    console.log('It is not a string!')
}



/**
 * 添加文本框
 * @returns
 */
function addText(){
	  //移除绑定功能
    removeBindingFun();
    //移除绑定事件
    unbindFun();
    //记录缓存
    recordTable();
    //重新绑定功能
    bindingFun();
    //重新绑定右击事件
    bindingEvent();
    
	var span = '<span class="a0 ui-draggable-handle" style="position: relative; left: -1px; top: 0px;" contenteditable="true">输入文字</span>';
	if($(".pclicktd").length>0){
		if($(".pclicktd").find("p").length>0){
			if($(".pclicktd").find("p").find("span").length>0){
				console.log("p---span")
				$(".pclicktd").find("p").find("span").html("输入文字");
			}else{
				var html = $(".pclicktd").find("p").html();
				$(".pclicktd").find("p").html(span+html);
			}
		}else{
			var html = $(".pclicktd").find("p").html();
			$(".pclicktd").find("p").html(span+html);
		}
		
	}else if($(".pclickd").length>0){
		if($(".pclickd").find("span").length>0){
			$(".pclickd").find("span").html("输入文字");
		}else{
			var html = $(".pclickd").html();
			$(".pclickd").html(span+html);
		}
		
	}
}
/**
 * 添加输入框
 * @returns
 */
function addInput(){
	  //移除绑定功能
    removeBindingFun();
    //移除绑定事件
    unbindFun();
    //记录缓存
    recordTable();
    //重新绑定功能
    bindingFun();
    //重新绑定右击事件
    bindingEvent();
    
	var input = '<input class="a0 ui-draggable-handle" type="text" ltype="text" id="newInput__1"/>';
	if($(".pclicktd").length>0){
		if($(".pclicktd").find("p").length>0){
			if($(".pclicktd").find("p").find("span")){
				$(".pclicktd").find("p").find("span").remove();
				$(".pclicktd").find("p").append(input);
			}else{
				$(".pclicktd").find("p").append(input);
			}
		}else{
			$(".pclicktd").find("p").append(input);
		}
		
	}else if($(".pclickd").length>0){
		if($(".pclickd").find("span").length>0){
			$(".pclickd").find("span").remove();
			$(".pclickd").append(input);
		}else{
			$(".pclickd").append(input);
		}
		
	}
	$("#newInput__1").focus(function(){
		$("#inputFocus").val($(this).attr("id"));
		bindInputEl = this;
		parent.showProperties(this);
		parent.setPropertiesShow();
	})
	$("input").css("width",'99%');
}

/**
 * 删除选中控件（清空选中单元格）
 * @returns
 */
function deleteControler(){
	  //移除绑定功能
    removeBindingFun();
    //移除绑定事件
    unbindFun();
    //记录缓存
    recordTable();
    //重新绑定功能
    bindingFun();
    //重新绑定右击事件
    bindingEvent();
    
	if($(".pclicktd").length>0){
		if($(".pclicktd").find("p").length>0){
			if($(".pclicktd").find("p").find("span").length>0){
				$(".pclicktd").find("p").find("span").html("");
			}else if($(".pclicktd").find("p").find("input").length>0){
				$(".pclicktd").find("p").find("input").remove();
			}else if($(".pclicktd").find("p").find("textarea").length>0){
				$(".pclicktd").find("p").find("textarea").remove();
			}
		}
		
	}else if($(".pclickd").length>0){
		if($(".pclicktd").find("p").length>0){
			if($(".pclicktd").find("p").find("span").length>0){
				$(".pclicktd").find("p").find("span").html("");
			}else if($(".pclicktd").find("p").find("input").length>0){
				$(".pclicktd").find("p").find("input").remove();
			}else if($(".pclicktd").find("p").find("textarea").length>0){
				$(".pclicktd").find("p").find("textarea").remove();
			}
		}else{
			if($(".pclickd").find("span").length>0){
				$(".pclickd").find("span").html("");
			}else if($(".pclickd").find("input").length>0){
				$(".pclickd").find("input").remove();
			}else if($(".pclickd").find("textarea").length>0){
				$(".pclickd").find("textarea").remove();
			}
		}
		
	}
	
}

/**
 * 输入框命名显示控制
 * @returns
 */
function managerShowName(){
	var value = parent.window.managerShowName;
	if(value=="0"){
		showNamed11();
	}else{
		hideNamed();
	}
}
