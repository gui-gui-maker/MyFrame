var paramss = GetRequest();
var saveResult = true;
var codeTable = {};
var focus_element;
$(document).ready(  
        function() {  
        		//自定义校验规则

        	
            
        }
       
    );

//自定义校验规则
function addValidateMethod(){
	
	if ($.validator) {
		$.validator.addMethod("reportDate", function(value, element, param) {
			if(value==""||value==null){
				return true;
			}
			 var date = value;
			    var result = date.match(/^(\d{4})(-|\/)(\d{1,2})\2(\d{1,2})$/);//yyyy-MM-dd yyyy/MM/dd
			    var result1 = date.match(/^(\d{4})(-|\/)(\d{1,2})$/);//yyyy-MM  yyyy/MM 
			    var result2 = date.match(/^(\d{4})年(\d{1,2})月(\d{1,2})日$/);
			    var result3 = date.match(/^(\d{4})年(\d{1,2})月$/);
			    if (result == null&&result1==null&&result2==null&&result3==null&&value!="/"&&value!="—"){
			    	return false;	
			    }else if (result != null){
			    	var d = new Date(result[1], result[3] - 1, result[4]);
				    return (d.getFullYear() == result[1] && (d.getMonth() + 1) == result[3] && d.getDate() == result[4]);
			    }else if (result1 != null){
			    	var d = new Date(result1[1], result1[3] - 1);
				    return (d.getFullYear() == result1[1] && (d.getMonth() + 1) == result1[3] );
			    }else{
			    	return true;
			    }
			   
		}, "日期格式不对");
	}
	
}


/**
 * *******************分页报表详情页面相关JS*************************************
 */
var color = "";
$(function() {
	
	
	
});
function textareafocus(obj){
	//alert($(obj).html())
	//alert($(obj).attr("id"))
	
	$("#inputFocus").val($(obj).attr("id"));
	bindInputEl = obj;
	focus_element = obj;
}


/**
 * *******************分页报表详情页面相关JS*************************************
 */



function submitForm() {

	if (!$("#formObj").validate().form()) {
		return false;
	}
	//预览时不用保存
	comm.mask("正在保存，请稍后...");
	var array = new Array();
	var code_ext = $("#code_ext").val();
	// 通过配置参数解决fk_report_id和fk_inspection_info_id

	$("input").each(function() {
		var type = $(this).attr("type");
		var classs = $(this).attr("class");
		var name = $(this).attr("name");
		//var mark  = getMark(this);//$("#"+$(this).attr("id")+"_mark").html();//标注
		//alert(mark)
		
		// checkbox,radio类型的使用隐藏的_val获取值 ZQ EDIT 20170827
		if (type != 'button' && type != "checkbox" && type != "radio"&&name!=undefined ) {


			//var value = getInputVal(name);
			//var value=$(this).val();
			
			//有问题，后修改
			//if(type == "hidden"&&classs!="checkbox"){
			//	value = $(this).next().html();
			

			var color = $(this).css("color");// 颜色
			//color = rgb2hex(color);
			var italic = $(this).css("font-style");// 字体风格
			var bold = $(this).css("font-weight");// 字体粗细
			var family = $(this).css("font-family");// 字体
			var size = $(this).css("font-size");// 字号
			var classs = $(this).attr("class");
			var mark = null;
			if($(this).parents("p.DocDefaults").find("div.bzhu_box").get(0)){
				mark  = getMark(this,input);//$("#"+$(this).attr("id")+"_mark").html();//标注
			}
			var image = null;

			if (classs != undefined && classs.substring(0, classs.length - 1) == "receiptfiles") {
				// 图片特殊处理
				var id = $(this).attr("id");

				type = "image";
				var n = classs.substring(classs.length - 1, classs.length);
				// v2.0 修改

				value = pictures[n - 1]["fileid"];
				var width = pictures[n - 1]["width"];
				var height = pictures[n - 1]["height"];
				var scale = pictures[n - 1]["scale"];
				image = width + "pt," + height + "pt," + scale;
			}else{
				
				try {
					var value = getInputVal(name);
				} catch (e) {
					// TODO: handle exception
				}
				try {
					if(ltype=="select"){
						value = $(this).ligerGetComboBoxManager().getValue();
					}
				} catch (e) {
					// TODO: handle exception
				}
				// 有问题 后修改 _ ZQ EDIT 2017 0827=======================要改10.16
				if (type == "hidden" && $(this).attr('cg')== '1') {
						value = $(this).parent().find('div .input11').html();
				}
				if (type == "hidden" && $(this).attr('class')== 'editDivInput') {
					value = $(this).parent("p").find('div.editDiv').html();
				}
				
				
			}
			// 获取checkbox,radio类型的值 ZQ EDIT 20170827
			if (name.indexOf("_val")==name.length-4) {
				name = name.substring(0,name.length-4);
				try {
					value = $("#"+name).ligerGetComboBoxManager().getValue();
				} catch (e) {
					// TODO: handle exception
				}
			}
			if(name!=undefined&&value!=undefined&&name!=""){
				if(value.indexOf("&nbsp;")>-1){
					value = value.replace(/&nbsp;/g," ");
				}
				if(value.indexOf("<br>")>-1){
					value = value.replace(/<br>/g,"");
				}
				
				var json = {
						name : name,
						value : value,
						type : type,
						color : color,
						bold : bold,
						italic : italic,
						family : family,
						size : size,
						image : image,
						markContent : mark == null? null : JSON.stringify(mark)
					};
				
				array.push(json);
			}
			
		}
		

	});
	
	
	$("textarea").each(function() {
		var name = $(this).attr("name");
		if(name!=undefined){
			var value = $(this).val();
			var type = $(this).attr("type");
			var color = $(this).css("color");
			//color = rgb2hex(color);
			var italic = $(this).css("font-style");// 字体风格
			var bold = $(this).css("font-weight");// 字体粗细
			var family = $(this).css("font-family");// 字体
			var size = $(this).css("font-size");// 字号
			var classs = $(this).attr("class");
			var mark = null;
			if($(this).parents("p.DocDefaults").find("div.bzhu_box").get(0)){
				mark  = getMark(this,input);//$("#"+$(this).attr("id")+"_mark").html();//标注
			}
			var json = {
				name : name,
				value : value,
				type : type,
				color : color,
				bold : bold,
				italic : italic,
				family : family,
				size : size,
				markContent : mark == null? null : JSON.stringify(mark)
			}
			array.push(json);
		}
		
	});


	//分页存值、重要
	var json = {
		name : "fkCodeExt",
		value : code_ext
	}
	array.push(json);
	//业务id保证业务id存在
	var json1 = {
			name : "fk_inspection_info_id",
			value : info_id
		}
	array.push(json1);
	
	//处理复选框
	//getCheckBoxValue(array);
	
	
	//当前页名字
	var pageName="";
	
	try {
		
		for (var i = 0; i < $("a").length; i++) {
			if(pageName!=""){
				break;
			}
			var item = $("a")[i];
			var name = $(item).attr("name");

			if(name!=undefined&&name.indexOf("RTPAGE_")!=-1){
				pageName = name.substring(10,name.length);

			}
		}
		
	} catch (e) {
		// TODO: handle exception
	}
	
	pageName = encodeURI(pageName);
	
	
	saveResult = false;
	var indexN = paramss.pageName;
	//var nowPage = indexN.substring(indexN.indexOf("index")+5,indexN.length-5);
	var nowPage = indexN.substring(indexN.indexOf("index")+5,indexN.length-5);
	$.ajax({
		url : $("#formObj").attr("action")+"?pageName="+pageName+"&page="+nowPage,// 'reportItemValueAction/saveMap.do'
		type : 'post',
		async:false,
		dataType : "json",
		contentType : 'application/json;charset=utf-8', // 设置请求头信息
		data : JSON.stringify(array),
		success : function(response) {
			if (response.success) {
				comm.Content("保存成功！");
				comm.LoadEnd(1);
				saveResult = true;
			} else {
				comm.Content("保存失败！" + response.msg);
				comm.LoadEnd(2);
				saveResult = false;
			}

		}
	});
	return saveResult;
}

/**
 * 标记颜色
 */
function markColor() {
	var id = $("#inputFocus").val()
	var color = $("#" + id).css("color");
	color = rgb2hex(color);
	if (color == "#000000") {
		var reg = /[\s\S]*_div/;
		if (reg.test(id)) {
			$("#" + id).css("color", "red");
			$("#" + id.substr(0, id.length - 4)).css("color", "red");
		} else {
			$("#" + id).css("color", "red");
		}
	} else {

		$("#" + id).css("color", "black");

	}
	$("#" + id).focus();

}
function tsColor(value) {
	var id = $("#inputFocus").val();
	$("#" + id).css("color", "" + value);
}
function rgb2hex(rgb) {
	 if (!!window.ActiveXObject || "ActiveXObject" in window){
		 return rgb;
	 }else{
		 rgb = rgb.match(/^rgb\((\d+),\s*(\d+),\s*(\d+)\)$/);
			function hex(x) {
			return ("0" + parseInt(x).toString(16)).slice(-2);
			}
			return "#" + hex(rgb[1]) + hex(rgb[2]) + hex(rgb[3]);
			}
	 }
/**
 * 去掉颜色
 */
function unmarkColor() {
	var id = $("#inputFocus").val();
	/*
	 * if (id) { $("#" + id).css("color", "black"); }
	 */
	if (id) {
		var reg = /[\s\S]*_div/;
		if (reg.test(id)) {
			$("#" + id).css("color", "black");
			$("#" + id.substr(0, id.length - 4)).css("color", "black");
		} else {
			$("#" + id).css("color", "black");
		}
	}
}
/**
 * 改变字体大小
 */
function fontSizes(grade) {
	var id = $("#inputFocus").val()
	if (id) {
		$("#" + id).css("font-size", grade + "px");
	}
	$("#" + id).focus();
}
/**
 * 改变字体样式
 */
function fontFamilys(grade) {

	var id = $("#inputFocus").val()
	if (id) {
		$("#" + id).css("font-family", grade);
	}
	$("#" + id).focus();
}
/**
 * 改变字体粗细
 */
function fontBold() {

	var id = $("#inputFocus").val()
	var bold = $("#" + id).css("font-weight");
	if (bold == "400") {

		if (id) {
			$("#" + id).css("font-weight", "bold");
		}
	} else {
		if (id) {
			$("#" + id).css("font-weight", "normal");
		}
	}
	$("#" + id).focus();

}
/**
 * 改变字体倾斜
 */
function fontItalic() {
	var id = $("#inputFocus").val();
	var bold = $("#" + id).css("font-style");
	if (bold == "normal") {
		if (id) {
			$("#" + id).css("font-style", "italic");
		}
	} else {
		if (id) {
			$("#" + id).css("font-style", "normal");
		}
	}
	$("#" + id).focus();
}



function initInput(){
//$("#layout2").find("div").attr("class","wrapper");
	var ll = $("form").find("table").length;
	$("input[type='text']").focus(function() {
		$("#inputFocus").val($(this).attr("id"));
		bindInputEl = this;
		focus_element = this;
	});
	
	
	if(input=="1"){
    	$('input').bind('keydown',function(event){
    	    if(event.keyCode == "13") {
    	    	var id = $("#inputFocus").val();
    	    	
    	    		change($("#"+id)[0]);
    	    	}
    	   
    	}); 
    	addValidateMethod();
    	
    	$("#formObj").validate();
    	$.metadata.setType("attr", "validate");
    	 
	 }
	
	if(input==""){
 		//非录入状态
 		$("body").keydown(function(e){
 			var ev = window.event || e;
 			var code = ev.keyCode || ev.which;		
 			try{
     			// 捕获ctrl事件
     			if (code==17) {
     				parent.window.f_batch_s();
     			}
     			// 捕获shift事件
     			if (code==16) {
     				parent.window.f_batch_e();
     			}
 			} catch (e) {
 				// TODO: handle exception
 			}
 		});
 	}
	
	
	if (document.getElementById('tsColor') != undefined) {
		document.getElementById('tsColor').onchange = function() {
			var id = $("#inputFocus").val();
			$("#" + id).css("color", "" + this.value);
		};
	}
	
	var classs = $($("#layout2").find("table")[ll-1]).attr("class");
	
	$($("#layout2").find("table")[ll-1]).attr("class",classs+" two");
	
	$("input[type=text]").css("width",'95%');
	$("input").parents("td").css("padding","1px");
	$("textarea").parents("td").css("padding","1px");
	$("textarea").css("overflow","hidden");
	
	if (pageStatus != undefined &&pageStatus != ""&& pageStatus == "detail") {
		$(".l-trigger").remove();
		$(".l-trigger-icon").remove();
	}else{
		var inp = "<style>#layout2 table input:focus { outline: 0 none; background: rgba(0,0,0,.02); border: 1px solid #ccc; }"
			+"#layout2 table textarea:focus { outline: 0 none; background: rgba(0,0,0,.02); border: 1px solid #ccc; }</style>";
		$("head").append(inp);
		$("input").css("height","5mm");
		$("input").parents("p").css("padding","1px");
		
	}
	if(paramss.mobile!=undefined&&paramss.mobile=="1"){
		$("tr").css("min-height","12mm");
		$("input").css("height","10mm");
		$("input").css("padding","1px");
	}
	//$(".two").attr("height",screen.height);
	/*$(".l-text").css("display",'inline-block');
	$(".l-text").css("width",'96%');
	$("input").css("width",'99%');
	$(".l-textarea").css("width",'99%');
	$("textarea").css("width",'99%');
	$(".l-text").css("border-color",'#FFFFFF');
	$(".l-text").css("border-color-bottom",'black');
	$(".l-text").css("background-color",'#F0F8FF');
	$(".l-textarea").css("border-color",'#FFFFFF');
	$(".l-textarea").css("background-color",'#F0F8FF');
	$("textarea").css("background-color",'#F0F8FF');
	$("input").css("background-color",'#F0F8FF');
	//#F8F8FF     CAE1FF
	//添加换行事件，禁止手动换行,现在需要换行，后台导出时处理过换行，所以就没有限制了pingZhou
	//$("textarea").attr("onkeydown",'checkEnter(event)');

$("input").css("background-color",'#F0F8FF');*/
	
	/*if(paramss.mobile!=undefined&&paramss.mobile=="1"){
		//移动端查看，不能删
		return;
	}*/
	$(".SY").css("width","70%");
	setTimeout(function(){
		try {
		var formObjh = $("#formObj").outerHeight(true)+100
		
		var maxheight=formObjh+50;
		if(maxheight<900){
			maxheight=900;
		}
		//$("body").outerHeight(true)+50;alert(maxheight)
			parent.setH(maxheight);
		
		
		} catch (e) {
			// TODO: handle exception
		}
	},100);
}

/**
 * 获取url参数
 * @returns
 */
function GetRequest() {  
	   var url = location.search; //获取url中"?"符后的字串  
	   var theRequest = new Object();  
	   if (url.indexOf("?") != -1) {  
	      var str = url.substr(1);  
	      strs = str.split("&");  
	      for(var i = 0; i < strs.length; i ++) {  
	         theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);  
	      }  
	   }  
	   return theRequest;  
	} 



//textarea禁止手动换行
function checkEnter(e)
{
	var et=e||window.event;
	var keycode=et.charCode||et.keyCode;
	if(keycode==13)
	{
		//alert(keycode+"--"+window.event)
	if(window.event!=undefined){
		//alert(window.event+"---"+JSON.stringify(window.event))
		window.event.returnValue = false;
	
		
	}
	/*else*/
	e.preventDefault();//for firefox
	}
}

//复制内容到同行

function getItemByLtype(ltype){
	var items=[];
	for ( var item in $("input")) {
		if($(item).attr("ltype")==ltype){
			
			items[items.length]=item;
			
		}
	}
	return items;
}

function initItemByLtype(ltype){
	
	for (var i = 0; i < $("input").length; i++) { 
		var item = $("input")[i];
		var id = $(item).attr("id");
				var ligerui1 = $(item).attr("ligerui");
				
				if(ligerui1!=undefined&&ligerui1!=""){
					var ligerui = {};
					try {
						ligerui = eval('(' + ligerui1 + ')');
					} catch (e) {
						//alert(ligerui1)
					}
					if(ligerui.data==undefined||ligerui.data==null||ligerui.data==""){
						if(ligerui.code!=undefined&&ligerui.code!=""){
							var code = ligerui.code;
							
							//动态取码表
							if(codeTable[code]!=undefined){
								ligerui["data"] = codeTable[code];
								$("#"+id).ligerGetComboBoxManager().setData(codeTable[code]);
								var initValue = ligerui.initValue;
								var isTextBoxMode = ligerui.isTextBoxMode;
								if(initValue!=undefined&&initValue!=null&&initValue!=""){
									
									$("#"+id).ligerGetComboBoxManager().setValue(initValue);
								}
								//$("#"+id).ligerGetComboBoxManager().setDatas(codeTable[code]);
							}else{
								$.ajax({url:"report/item/record/queryCodeTable.do?code="+code,
									async:false,
									success:function(res){
										if(res.success){
											var codetable = res.codeTable;
											if(codetable!=undefined){
												var datacode = [];
												for (var j = 0; j < codetable.length;j++) {
													
													var datan = {};
													datan["id"] = codetable[j].value;
													datan["text"] = codetable[j].name;
													datacode[datacode.length] = datan;  
												}
												//放入码表
												codeTable[code] = datacode;
												$("#"+id).ligerGetComboBoxManager().setData(codeTable[code]);
												//alert(id)
												//$("#"+id).attr("ligerui",JSON.stringify(ligerui));
												//alert($("#"+id).attr("ligerui"))
												//$("#"+id).ligerGetComboBoxManager().setData(codeTable[code]);
											}
											if(pageStatus==null||pageStatus!="detail"){
												var initValue = ligerui.initValue;
												var isTextBoxMode = ligerui.isTextBoxMode;
												
												if(initValue!=undefined&&initValue!=null&&initValue!=""){
													$("#"+id).ligerGetComboBoxManager().setValue(initValue);
												}
											}
											
										}
									},
									error: function (data,stats) {
										isContinue=false;
						               // $.ligerDialog.error('提示：' + data.msg);
						            }
							});
							
							}
						}
					}else{
						var initValue = ligerui.initValue;
						
						var isTextBoxMode = ligerui.isTextBoxMode;
						
						if(initValue!=undefined&&initValue!=null&&initValue!=""){
						//	alert(id+"---"+initValue)
							$("#"+id).ligerGetComboBoxManager().setValue(initValue);
						}
					}
					/*var ltype = $(item).attr("ltype");
					if(ligerui.data!=undefined){
						
						ligerui["disabled"]=true;
						
					}*/
					
					
				}
				
		
		
	}
	
	
}

function setligerCheckBoxListInitValue(){
	//处理复选框默认值
	var items = $(".checkboxDiv");
	for (var i = 0; i < items.length; i++) { 
		var item = items[i];
		var id = $(item).attr("id");
		var ligerui1 = $(item).attr("ligerui");
				
		if(ligerui1!=undefined&&ligerui1!=""){
			var ligerui = {};
			try {
			ligerui = eval('(' + ligerui1 + ')');
			
			} catch (e) {
				//alert(ligerui1)
			}
			if(ligerui.initValue!=undefined&&ligerui.initValue!=""){
				$(item).ligerGetCheckBoxManager().setValue(ligerui.initValue);
			}
		}
	}
}

function copyToRow(obj){
	var value = $(obj).val();
	var tr = $(obj).parents("tr").get(0);
	try {
		
		$(tr).find('input').val(value);
	} catch (e) {
		
	}
	try {
		$(tr).find('input').ligerGetComboBoxManager.setValue(value);
	} catch (e) {
		
	}
	
}

//自动生成序号
function sequence(obj,initValue,prefix,suffix){
	//var tr = $(obj).parent().parent().parent().parent().get(0);
	var tr = $(obj).parents("tr");
	var otds = $(tr).find("td");
	obj.index="";
	for(var i=0;i<otds.length;i++){
		if($(otds[i]).find("input")[0]==obj){
			obj.index = i;
			break;
		}
	}
	var nextTrs = $(tr).nextAll();   
	var inputs = [];
	inputs.push(obj);
	for(var j=0;j<nextTrs.length;j++){
		if(nextTrs[j].tagName=="TR"){
			var tds = $(nextTrs[j]).find("td");
			if(tds.length==otds.length){
				if($(tds[obj.index]).find("input")[0]!=null){
					inputs.push($(tds[obj.index]).find("input")[0]);
				}
			}
		}
		
	}
	var prefix = (prefix==undefined ? '' : prefix);
	var suffix = (suffix==undefined ? '' : suffix);
	if( initValue != undefined){
		$.each(inputs,function(id,item){
			$(item).val(prefix+initValue+suffix);
			initValue ++;
		});
	}else{
		initValue = 1;
		$.each(inputs,function(id,item){
			$(item).val(prefix+initValue+suffix);
			initValue ++;
		});
	}
	/*var value = $(obj).val();
	if(value != null && value != '' && !isNaN(parseInt(value))){
		var sn = parseInt(value);
		$(obj).val(sn);
		$.each(inputs,function(id,item){
			$(item).val(++sn);
		});
	}else{
		//
		var sn = 1;
		$(obj).val(sn);
		$.each(inputs,function(id,item){
			$(item).val(++sn);
		});
	}*/
}

function initForm() {
	//加载完成后才允许翻页保存
	try{
		parent.saveFlag = false;
	} catch (e) {
		// TODO: handle exception
	}
	
	try {
		initInput();
	} catch (e) {
		// TODO: handle exception
	}
	//预览时没有参数
	if (pageStatus == "") {
		initItemByLtype("select")
		//alert("pageStatus is null");
		return;
	}
	try {
		initItemByLtype("select")
	} catch (e) {
		//console.log(e)
		// TODO: handle exception
	}
	if (input == "1"||pageStatus == "detail") {
	//录入时才需要加载默认值和默认的参检人员信息
		
			
			//alert(check_op)
		if(check_op!=''){
			setSignData("base__insepct_op",check_op)
		
		}
	}	
	
	
	comm.mask("正在获取数据，请稍后...");
	var formParam = {
		fk_report_id : fk_report_id
	};
	if (input != "") {
		formParam = {
			fk_report_id : fk_report_id,
			input : input
		};
	}
	//$("#formObj").attr("getAction") + '&input=' + input + '&' + relColumn+'&id='+info_id
	// relColumn为rtpage中配置的关联字段
	var indexN = paramss.pageName;
	$.ajax({
		url : $("#formObj").attr("getAction") + '?input=' + input + '&pageCode=' + pageCode+"&infoId="+info_id+"&code_ext="+code_ext+"&now="+indexN,// reportItemValueAction/detailMap.do
		type : 'post',
		dataType : "json",
		// data : formParam,
		success : function(response) {
			if (response.success) {
				var data = response.dataList;
				comm.LoadEnd();
				if (data != null) {
					for ( var row=0;row<data.length;row++) {
						var name = data[row]["itemName"];
                        var value = data[row]["itemValue"];
                       // //console.log(name+"---"+value);
                        /*if(name=='base_inspect_op'){
                            //console.log(name+"---"+value);
                            //console.log(JSON.stringify(data[row]));
                        }*/

						var color = data[row]["color"];
						var bold = data[row]["bold"];
						var italic = data[row]["italic"];
						var size = data[row]["size"];
						var family = data[row]["family"];
						var image = data[row]["image"];
						var markContent = data[row]["markContent"];
						
						if (code_ext) {
							
							if (name.indexOf(code_ext) > 0) {
								name = name.replace("__"+code_ext, "");
								//name = name.replace("__", "");
							} 
							if((name.indexOf('op')>0||name.indexOf('_time')>0||name.indexOf('_date')>0)&&value!=null&&value!=""){
								setSignData(name,value);
								
							}else if(value!=null&&value!=""){
								setInputVal(name, value);
								try {
									setCheckBoxValue(name, value);
								} catch (e) {
									// TODO: handle exception
								}
								try {
									$("#"+name).ligerGetComboBoxManager().setValue(value);
								} catch (e) {
									// TODO: handle exception
								}
							}
							
							
							if (color) {
								$("#" + name).css("color", "" + color);
							}
							if (bold) {
								$("#" + name).css("font-weight", "bold");
							}
							if (italic) {
								$("#" + name).css("font-style", "italic");
							}
							if (size) {
								$("#" + name).css("font-size", size);
							}
							if (family) {
								$("#" + name).css("font-family", family);
							}
							if (markContent) {
								//必须判断，有可能当页没有该name的input
								//alert(markContent)
								if($("#" + name)[0]){
									var mark = JSON.parse(markContent);
									if(mark.status == '0' || mark.status == '' || input ==''){
										//change($("#" + name)[0],'1',markContent,input);
										label($("#" + name)[0],input,markContent)
									}
								}
							}
							if (image != null) {
								// 图片处理
								var classs = $("input[name='" + name + "']:eq(0)").attr("class");
								// 没有找到图片控件则不显示
								if (classs != undefined) {

									// 图片的高宽样式没有设置，因为目前初始化的时候设置了
									var n = classs.substring(classs.length - 1, classs.length);

									var wh = image.split("||")[0];
									var w = wh.split(";")[0];
									var h = wh.split(";")[1];
									var w1 = w.substring(0, w.length - 2).split(":")[1];
									var h1 = h.substring(0, h.length - 2).split(":")[1];

									pictures[n - 1]["width"] = w1;
									pictures[n - 1]["height"] = h1;
									pictures[n - 1]["fileid"] = value;
									// alert("w1---"+w1+"---h1--"+h1)
									if (image.split("||").length > 1) {
										var scale = image.split("||")[1];
										$("#canvas" + n + "-range").val(scale);
										pictures[n - 1]["scale"] = scale;
									}
									$("#receiptfiles" + n + 'AttchNames').val(value);

									// v2.0 修改了图片控件
									// changeCanvasInit(n,value,0,0);
									// $("#"+classs+"P").attr("src","fileupload/downloadByObjId.do?id="+value);

									// alert(classs)

								}
							}
						} else {
							
							if(name!=undefined&&(name.indexOf('op')>0||name.indexOf('_time')>0||name.indexOf('_date')>0)&&value!=null&&value!=""){
								setSignData(name,value);
								
							}else
							if(value!=null&&value!=""){
								
								setInputVal(name, value);
								try {
									setCheckBoxValue(name, value);
								} catch (e) {
									// TODO: handle exception
								}
								try {
									
									$("#"+name).ligerGetComboBoxManager().setValue(value);
								} catch (e) {
									
									if(name=="base__sycs"){
										//console.log(e)
									}
									// TODO: handle exception
								}
							}
							
							
							//setInputVal(name, value);
							if (color) {
								$("#" + name).css("color", "" + color);
							}
							if (bold) {
								$("#" + name).css("font-weight", "bold");
							}
							if (italic) {
								$("#" + name).css("font-style", "italic");
							}
							if (size) {
								$("#" + name).css("font-size", size);
							}
							if (family) {
								$("#" + name).css("font-family", family);
							}
							if (markContent) {
								//必须判断，有可能当页没有该name的input
								//alert(markContent)
								if($("#" + name)[0]){
									var mark = JSON.parse(markContent);
									if(mark.status == '0' || mark.status == '' || input ==''){
										//change($("#" + name)[0],'1',markContent,input);
										label($("#" + name)[0],input,markContent)
									}
								}
							}
							if (image != null) {
								// 图片处理
								var classs = $("input[name='" + name + "']:eq(0)").attr("class");
								// 没有找到图片控件则不显示
								if (classs != undefined) {

									// 图片的高宽样式没有设置，因为目前初始化的时候设置了
									var n = classs.substring(classs.length - 1, classs.length);

									var wh = image.split("||")[0];
									var w = wh.split(";")[0];
									var h = wh.split(";")[1];
									var w1 = w.substring(0, w.length - 2).split(":")[1];
									var h1 = h.substring(0, h.length - 2).split(":")[1];

									pictures[n - 1]["width"] = w1;
									pictures[n - 1]["height"] = h1;
									pictures[n - 1]["fileid"] = value;
									// alert("w1---"+w1+"---h1--"+h1)
									if (image.split("||").length > 1) {
										var scale = image.split("||")[1];
										$("#canvas" + n + "-range").val(scale);
										pictures[n - 1]["scale"] = scale;
									}
									$("#receiptfiles" + n + 'AttchNames').val(value);

									// v2.0 修改了图片控件
									// changeCanvasInit(n,value,0,0);
									// $("#"+classs+"P").attr("src","fileupload/downloadByObjId.do?id="+value);

									// alert(classs)

								}
							}
						}

					}
					
					//加载完成后才允许翻页保存
					try{
						parent.saveFlag = true;
					} catch (e) {
						// TODO: handle exception
					}
					
					
				}

				
				if (pageStatus != undefined &&pageStatus != ""&& pageStatus == "detail") {
					$(".l-trigger").remove();
					$(".l-trigger-icon").remove();
				}
				var objst = $("textarea").get();
				for (var i = 0, l = objst.length; i < l; i++) {
					if (pageStatus != undefined &&pageStatus != ""&& pageStatus == "detail") {
						$(objst[i]).attr("readonly", "readonly");
						//$(objst[i]).attr("disabled","disabled");
						//$(".l-trigger-hover").hide();
					}
				}
				
				// 处理上下标
				var objs = $("input").get();
				for (var i = 0, l = objs.length; i < l; i++) {
					if (pageStatus != undefined &&pageStatus != ""&& pageStatus == "detail") {
						$(objs[i]).attr("readonly", "readonly");
						$(objs[i]).attr("disabled","disabled")
						$(objs[i]).css("background","white")
						$(objs[i]).css("color","black")
						//处理下日期格式
						if($(objs[i]).attr("ltype")=="date"){
							formatDateIuput(objs[i]);
						}
						
						//$(".l-trigger-hover").hide();
					}
					var val = $(objs[i]).val();
					var reg = /<sub>[\s\S]*<\/sub>|<sup>[\s\S]*<\/sup>|<SUB>[\s\S]*<\/SUB>|<SUP>[\s\S]*<\/SUP>/;
					var result = reg.test(val);
					if (result) {
						change(objs[i]);
					}
				}
				var textareas = $("textarea").get();
				for (var j = 0, m = textareas.length; j < m; j++) {
					if (pageStatus != undefined &&pageStatus != ""&& pageStatus == "detail") {
						$(textareas[j]).attr("readonly", "readonly");
						$(textareas[i]).attr("disabled","disabled")
					}
					var val = $(textareas[j]).val();
					var reg = /<sub>[\s\S]*<\/sub>|<sup>[\s\S]*<\/sup>|<SUB>[\s\S]*<\/SUB>|<SUP>[\s\S]*<\/SUP>/;
					var result = reg.test(val);
					if (result) {
						change(textareas[j]);
					}
				}
				
				
				if (pictures != undefined) {
					if (pictures.length > 0) {
						loadImage(0);
					}
				}

				if (pageStatus != undefined &&pageStatus != ""&& pageStatus == "detail") {
						
						//去掉详情时还能下拉
						var items = $("div .l-text-combobox");
						for (var k = 0; k < items.length; k++) {
							var item = items[k];
							$(item).attr("class",$(item).attr("class").replace("l-text-combobox",""))
						}
						
					}
				//ZQ ADD 非IE浏览器判断图片加载完成
				var isIE = (navigator.userAgent.indexOf('MSIE')>=0) || (navigator.userAgent.indexOf('Trident')>=0);
				if(!isIE){
					var imgs=document.getElementsByTagName("img");
					//debugger;
					var imgCount=imgs.length;
					if(imgCount>0){
						 
						var waitFor = setInterval(function(){
							var flag=0;
							for(var idx=0;idx<imgs.length;idx++){
								var img=imgs[idx];
								if(!img.complete){
									//console.log("img no complete:"+img.src);
									break;
								}
								flag++;
							}
							
							if(flag==imgCount){
								$("form").append("<input type='hidden' name='initReadyFlag' id='initReadyFlag' value='0'/>")
								clearInterval(waitFor);
							}
							
						}, 250);
					}else{
						$("form").append("<input type='hidden' name='initReadyFlag' id='initReadyFlag' value='0'/>")
					}
				}else{
					$("form").append("<input type='hidden' name='initReadyFlag' id='initReadyFlag' value='0'/>")
				}
				
				 //$("#base__inspect_op").ligerGetComboBoxManager().setValue("402880044a800274014a802d3af9000d");

			} else {
				comm.Content("获取失败！" + response.msg);
				comm.LoadEnd(2);
			}

		}
	});
	return false;
}

//检验项目的检验结果和检验结论联动事件
function changeRecord(inp,val){
	if(input !='1'){
		return ;
	}
	var id = $(inp).attr("id");
	if(val==null||val==""){
		return;
	}
	/*var ss = false;
	if("S"==id.substring(id.length-1, id.length)){
		ss = true;
	}*/
	
	var concl_num = "";
	
	//var num = id.substring(8, 11);
	var num = $(inp).attr("class").split(" ")[0];
	var flag = true;
	var wcx = 0;
	for (var i = 0; i < $("."+num).length; i++) {
		var obj = $("."+num)[i];
		if(i==0){
			concl_num = $($("."+num)[0]).attr("id").replace("record__","");
			//alert(concl_num)
		}
		var nid = $(obj).attr("id");
		var record = $("#"+nid+"_val").val();
			//$(obj).ligerGetComboBoxManager().getValue();
		if(record=="无此项"||record=="∕"){
			wcx = wcx +1;
		}
		if(record==undefined){
			return;
		}
		//record!="符合"&&record!="资料确认符合"&&record!="无此项"
		if(record.indexOf("不符合")!=-1){
			try {
				
				$("#conclusion__"+concl_num).ligerGetComboBoxManager().setValue("×");
				flag = false;
				//$("#conclusion__"+concl_num).ligerGetComboBoxManager().setValue("不合格");
			} catch (e) {
				//console.log(e)
				// TODO: handle exception
			}
		
			
			return;
		}else if(record!="√"&&record!="∕"&&record!="O"&&record!=""&&record.indexOf("符合")==-1){
			
				try {
					
					$("#conclusion__"+concl_num).ligerGetComboBoxManager().setValue("×");
					flag = false;
					//$("#conclusion__"+concl_num).ligerGetComboBoxManager().setValue("不合格");
				} catch (e) {
					//console.log(e)
					// TODO: handle exception
				}
			
			
			return;
		}
	}
	if($("."+num).length>0&&flag){
		//var jl = "合格";
		var jl = "√";
		
		if(wcx==$("."+num).length){
			
			//jl = "无此项";
			jl = "∕";
		}
		
		
			try {
				$("#conclusion__"+concl_num).ligerGetComboBoxManager().setValue(jl);
			} catch (e) {
				jl
				// TODO: handle exception
			}
		
	}
	
}
var i = 0;
//c重写标签赋值
function setInputVal(name, val,type) {
	try {
		if(name.indexOf("_t")==(name.length-2)){
			names=name.substring(0,name.indexOf("_t"));
			$("#"+names).parent().css("height",'40px');
	    	$("#"+names).hide();
	    	$("#"+names).after("<div class='sup_sub_div' onfocus='textareafocus(this)' id='"+names+"_t' contenteditable='true' name='"+names+"_t' rows='2' cols='43' >s<sup>ss</sup>s</div>");
		
	    	try {
	    		
	    		$("#"+names).parents("tr").show();
			} catch (e) {
				//console.log(e);
				// TODO: handle exception
			}
		}else{
			$('#' + name).val(val);
			try {
				/*if(i<1&&$("#"+name).parents("tr").html()!=undefined){
					alert($("#"+name).parents("tr").html())
					i++;
				}*/
				
				$("#"+name).parents("tr").show();
			} catch (e) {
				//console.log(e);
				// TODO: handle exception
			}
		}
		
	} catch (e) {
		try {
			$('#' + name).ligerGetComboBoxManager.setValue(val);
		} catch (e) {
			try {
				$('#' + name).ligerGetDateEditorManager.setValue(val);
			} catch (e) {
				try {
					$('#' + name).ligerGetRadioGroupManager().setValue(val);
				} catch (e) {
					//document.getElementById(name).value = val;
				}
			}
		}
	}
}

function setSignData(name,val){
	var indexN = paramss.pageName;
	var page = indexN.substring(indexN.indexOf("index")+5,indexN.length-5);
	var check_ops = val.split(",");
	var value = "";
	for (var i = 0; i < check_ops.length; i++) {
		if(value == ""){
			value = check_ops[i];	
		}else{
			value = value+";"+check_ops[i];	
		}
	}

	$("#"+name).val(value)
	
		try {
			
			$("#"+name).ligerGetComboBoxManager().setValue(value);

		} catch (e) {
			// TODO: handle exception

		}
		try {
			var prev = $("#"+name+page).ligerGetComboBoxManager().getValue();
			if(prev==""||prev==null){
				$("#"+name+page).ligerGetComboBoxManager().setValue(value);
			}

		} catch (e) {
			//console.error(e)
			// TODO: handle exception

		}
		
}


/**
 * 单元格画斜线
 * @param header 需要画斜线的td的id
 * @param line_width 线宽度 一般 值1
 * @param line_color 线颜色
 * @param line_number 线数量
 * @returns {Number}
 */
function line(header,line_width,line_color,line_number){  
	$("body").append('<canvas id="line_'+header+'" style="display:none;"></canvas>');
	    var table = document.getElementById(header);   
	    var xpos = table.clientWidth;  
	    var ypos = table.clientHeight;  
	    var canvas = document.getElementById("line_"+header);  
	    if(canvas.getContext){  
	        var ctx = canvas.getContext('2d');  
	        ctx.clearRect(0,0,xpos,ypos); //清空画布，多个表格时使用  
	        ctx.fill();  
	        ctx.lineWidth = line_width;  
	        ctx.strokeStyle = line_color;  
	        ctx.beginPath();  
	        switch(line_number){  
	            case 1:  
	                ctx.moveTo(0,0);  
	                ctx.lineTo(xpos,ypos);  
	                break;  
	            case 2:  
	                ctx.moveTo(0,0);  
	                ctx.lineTo(xpos/2,ypos);  
	                ctx.moveTo(0,0);  
	                ctx.lineTo(xpos,ypos/2);  
	                break;  
	            case 3:  
	                ctx.moveTo(0,0);  
	                ctx.lineTo(xpos,ypos);  
	                ctx.moveTo(0,0);  
	                ctx.lineTo(xpos/2,ypos);  
	                ctx.moveTo(0,0);  
	                ctx.lineTo(xpos,ypos/2);  
	                break;  
	            default:  
	            return 0;     
	        }  
	                  
	        ctx.stroke();  
	        ctx.closePath();  
	        document.getElementById(header).style.backgroundImage = 'url("' + ctx.canvas.toDataURL() + '")';  
	        document.getElementById(header).style.background-attachment >= 'fixed';  
	    }  
	} 


function checkSameName(){
	//alert(1)
	var hasNamed = {};
	var sameNamed = {};
	var inputs = $("input");
	for(var i=0;i<inputs.length;i++){
		var item = inputs[i];
		var name = $(item).attr("name");
		if(name!=undefined&&name!=""){
			var pretd = $(item).parents("td").prev("td");
			var text = pretd.text().replace(/\t/g,"").replace(/\n/g,"");
			//alert(text)
			if(hasNamed[name]!=null){
				sameNamed[name+"___"+i] = hasNamed[name];	
				sameNamed[name] = text;	
			}else{
				hasNamed[name] = text;	
			}
			
		}
	}
	//console.log(JSON.stringify(hasNamed))
	//console.log(JSON.stringify(sameNamed))
	//alert(JSON.stringify(hasNamed))
	var show = "";
	for(var key in sameNamed ){
		var keyn = key;
		if(keyn.indexOf("___")>0){
			keyn = key.split("___")[0];
		}
		if(show==""){
			show = sameNamed[key]+":"+keyn
		}else{
			show = show + "\n"+sameNamed[key]+":"+keyn
		}
	}
	alert(show)
}

function checkAllBaseName(){
	var hasNamed = {};
	var baseNamed = {};
	var inputs = $("input");
	for(var i=0;i<inputs.length;i++){
		var item = inputs[i];
		var name = $(item).attr("name");
		if(name!=undefined&&name!=""){
			var pretd = $(item).parents("td").prev("td");
			var text = pretd.text().replace(/\t/g,"").replace(/\n/g,"");
			if(name.indexOf("base__")==0){
				baseNamed[name] = text;	
			}
			if(name.indexOf("base__")==-1&&name.indexOf("TBL")==-1&&name.indexOf("FK")==-1&&name.indexOf("fk")==-1){
				baseNamed[name] = text;	
			}
			//alert(text)
			if(hasNamed[name]!=null){
				baseNamed[name+"___"+i] = hasNamed[name];	
				baseNamed[name] = text;	
			}else{
				hasNamed[name] = text;	
			}
			
		}
	}
	//console.log(JSON.stringify(hasNamed))
	//console.log(JSON.stringify(baseNamed))
	var show = "";
	for(var key in baseNamed ){
		var keyn = key;
		if(keyn.indexOf("___")>0){
			keyn = key.split("___")[0];
		}
		
		if(show==""){
			show = baseNamed[key]+":"+keyn
		}else{
			show = show + "\n"+baseNamed[key]+":"+keyn
		}
	}
	//alert(JSON.stringify(hasNamed))
	//alert(JSON.stringify(baseNamed))
	//alert(show)
	
}


//自定义checkbox渲染方式
function initCheckBoxDIV(dataD){
	var dataa = dataD.data;
	var name = dataD.name;
    var ligerui = "{}";
    if($("#"+name).attr("ligerui")){
    	ligerui=$("#"+name).attr("ligerui");
	}

    var itemj = eval("(" + ligerui.toString() + ")");
    var initValue = itemj.initValue==undefined?"":itemj.initValue;
    var selectall = itemj.selectall==undefined?false:itemj.selectall;
    var initValues = initValue.replace(",");
	var xm = "";
	for (var i = 0; i < dataa.length; i++) {
        var initv = "";
        if(initValues.indexOf(dataa[i].id)>-1){
            initv = 'checked="checked"';
        }else if(selectall){
            initv = 'checked="checked"';
        }
		xm = xm+'<div style="display:inline;width:100%;"><input type="checkbox" style="width:20px;" name="'+name
		+'" value="'+dataa[i].id+'" id="'+name+'-'+i+'" class="checkBox-'+name+'" onchange="changeCheck(this)"'+initv+'><label for="'+name+'-'+i+'">'+dataa[i].text+'</label></div>'
	}
	var html  = '<div class="l-checkboxlist-inner" style="width:100%;">'
		+'<table cellpadding="0" cellspacing="0" border="0" class="l-checkboxlist-table" style="width:100%;"><tbody><tr><td>'
		+xm
		+'</td></tr></tbody></table>'
		+' </div><input type="hidden" name="'+name+'_val" id="'+name+'_val" data-ligerid="'+name+'" value="">';
	
	$("#"+name).attr("class","l-checkboxlist");
	$("#"+name).css("width","100%");
	$("#"+name).attr("ligeruiid",name);
	$("#"+name).html(html);
	
	
	
}

//自定义checkbox渲染方式
function changeCheck(inp){
	var name = inp.name;
	var checkedi = $(inp).attr("checked");
	if(checkedi==undefined){
		 $(inp).attr("checked","checked");
	}else{
		 $(inp).removeAttr("checked");
	}
	var classs = $(inp).attr("class");
	var inpClass = $("."+classs);
	var val = "";
	for (var i = 0; i < inpClass.length; i++) {
		var checked = $(inpClass[i]).attr("checked");
		if(checked!=undefined){
			var value =  $(inpClass[i]).val();
			if(val==""){
				val = value;
			}else{
				val = val+";"+value;
			}
		}
		
	}
	$("#"+name+"_val").val(val);
}
//自定义checkbox赋值方式
function setCheckBoxValue(name,value){
	$("#"+name+"_val").val(value);
	var val =  ";"+value+";";
	if(name=="TBL00069"){
        //console.log(name+"---"+value);
	}

	var checkName = $(".checkBox-"+name);
	for (var i = 0; i < checkName.length; i++) {
		var item = checkName[i];
		var iv = $(item).val()
		if(val.indexOf(";"+iv+";")!=-1){
			$(item).attr("checked","checked");
		}else {
            $(item).removeAttr("checked");
		}
	}
}

//下拉框改变事件
function onSelectValueChange(inp,inpN){
	var text = $("#"+inp.id).val();
	
	$("#"+inpN).val(text);
}



/**
 * 日期格式化
 * @param value
 * @param format
 * @returns
 */
function formatDate(value,format){
	if(value==null||value==""){
		return "";
	}else{
		return new Date(value).format(format);
	}
	
}

/**
 * 格式化日期控件
 * @param item
 * @returns
 */
function formatDateIuput(item){
	try {
		var $item = $(item);
		if($item.val()==""){
			return;
		}
		var ligerui1 = $item.attr("ligerui");
		if(ligerui1!=undefined&&ligerui1!=""){
			var ligerui = {};
			try {
				ligerui =JSON.parse(ligerui1);
			} catch (e) {
			}
			if(ligerui.format!=undefined){
				var valuen = getFormatDate(new Date($item.val()),ligerui.format);//formatDate($item.val(),ligerui.format);
				$item.val(valuen);
			}else{
				var valuen = getFormatDate(new Date($item.val()),"yyyy年MM月dd日");//formatDate($item.val(),"yyyy年MM月dd日");
				$item.val(valuen);
			}
		}
	} catch (e) {
		
		// TODO: handle exception
	}
	
}

function getFormatDate(date, pattern) {
	 if (date == undefined) {
	 date = new Date(); 
	 }
	 var Year= date.getFullYear();
	 var Month= date.getMonth()+1; 
	 var Day = date.getDate();
	 var Hour=date.getHours();
	 var Minute=date.getMinutes();
	 var Second=date.getSeconds();
	 if (pattern == undefined) {
	 pattern = "yyyy-MM-dd hh:mm:ss";
	 }
	 return pattern.replace("yyyy",Year).replace("MM",Month).replace("DD", Day).replace("dd", Day).replace("HH",Hour).replace("mm",Minute).replace("ss", Second);
	}