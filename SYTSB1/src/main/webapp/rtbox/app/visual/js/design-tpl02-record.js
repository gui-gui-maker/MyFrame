var paramss = GetRequest();
var saveResult = true;
var codeTable = {};

$(document).ready(  
        function() {  
	        	
	        
        	if(input==""){
        		//$("form").css("height","900px");
        		//$("form").css("overflow","auto");
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
        }
       
    );


/**
 * *******************分页报表详情页面相关JS*************************************
 */
var color = "";

$(function() {
	/*$("span").focus(function() {
		$(focusTarget).css("background",focusTargetBack);
		focusTarget = this;
		focusTargetBack = $(this).css("background");
		$(this).css("background",backColor);
		parent.showProperties(this);
		parent.setPropertiesShow();
	});
	$("td").bind('mousedown',function(e) {
		
		$(focusTarget).css("background",focusTargetBack);
		focusTarget = this;
		focusTargetBack = $(this).css("background");
		$(this).css("background",backColor);
		parent.showProperties(this);
		parent.setPropertiesShow();
	});
	$("tr").bind('mousedown',function(e) {
		
		$(focusTarget).css("background",focusTargetBack);
		focusTarget = this;
		focusTargetBack = $(this).css("background");
		$(this).css("background",backColor);
		parent.showProperties(this);
		parent.setPropertiesShow();
	});*/


	
	if (document.getElementById('tsColor') != undefined) {
		document.getElementById('tsColor').onchange = function() {
			var id = $("#inputFocus").val();
			$("#" + id).css("color", "" + this.value);
		};
	}
});
function textareafocus(obj){
	//alert($(obj).html())
	//alert($(obj).attr("id"))
	
	$("#inputFocus").val($(obj).attr("id"));
	bindInputEl = obj;
}


/**
 * *******************分页报表详情页面相关JS*************************************
 */


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
	try {
	
		var ll = $("form").find("table").length;
		
		
		var classs = $($("#layout2").find("table")[ll-1]).attr("class");
		$($("#layout2").find("table")[ll-1]).attr("class",classs+" two");
		
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
	//$(".l-textarea").css("background-color",'#F0F8FF');
	//$("textarea").css("background-color",'#F0F8FF');
	//#F8F8FF     CAE1FF
	//添加换行事件，禁止手动换行,现在需要换行，后台导出时处理过换行，所以就没有限制了pingZhou
	//$("textarea").attr("onkeydown",'checkEnter(event)');
	//$("input").css("background-color",'#F0F8FF');
	/*var ll = $("form").find("table").length;
	$($("#layout2").find("table")[ll-1]).attr("class","two");*/
	
	
	var width = $("#layout2").width();
	var maxheight = 900;
	
	//内容窗口自适应
	/*for (var i = 0; i < $("form").find("table").length; i++) {
		var tb = $("form").find("table")[i];
		var tw = $(tb).width();
		var th = $(tb).height();
		if((th+100)>maxheight){
			maxheight = (th+100);
		}
		//alert(width*0.8>tw)
		if(width*0.8>tw){
			$(".l-layout-center").css("width","98%")
			$(tb).css("width","94%")
		}
	}
	$(".SY").css("width","80%");*/
	} catch (e) {
		// TODO: handle exception
	}
	
	if(paramss.mobile!=undefined&&paramss.mobile=="1"){
		//移动端查看，不能删
		return;
	}
	
	setTimeout(function(){
		try {
			var formObjh = $("#formObj").outerHeight(true)+50
			var landFlag = $("#landFlag").val();
			
			if(formObjh<950&&landFlag==undefined){
				formObjh = 950;
			}else if(landFlag!=undefined){
				formObjh = 800;
			}
			$("#formObj").parent("div").css("height",formObjh);
			$(".layout2").css("height",formObjh+50);
			
			var maxheight=$("#formObj").parent("div").outerHeight(true)+50;
			if(maxheight<900){
				maxheight=900;
			}
		//$("body").outerHeight(true)+50;alert(maxheight)
		if(parent.setH){
			parent.setH(maxheight);
		}
			
		
		
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
					var ligerui = [];
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

//检验项目的检验结果和检验结论联动事件
function changeRecord(input,val){
	
	var id = $(input).attr("id");
	if(val==null||val==""){
		return;
	}
	/*var ss = false;
	if("S"==id.substring(id.length-1, id.length)){
		ss = true;
	}*/
	
	var concl_num = "";
	
	//var num = id.substring(8, 11);
	var num = $(input).attr("class").split(" ")[0];
	var flag = true;
	var wcx = 0;
	for (var i = 0; i < $("."+num).length; i++) {
		var obj = $("."+num)[i];
		if(i==0){
			concl_num = $($("."+num)[0]).attr("id").replace("record__","");
			//alert(concl_num)
		}
		//var record = $(obj).ligerGetComboBoxManager().getValue();
		var nid = $(obj).attr("id");
		var record = $("#"+nid+"_val").val();
		//alert(record)
		if(record=="无此项"){
			wcx = wcx +1;
		}
		if(record!="符合"&&record!="资料确认符合"&&record!="无此项"){
			flag = false;
				try {
					$("#conclusion__"+concl_num).ligerGetComboBoxManager().setValue("不合格");
				} catch (e) {
					// TODO: handle exception
				}
			
			
			return;
		}
	}
	if($("."+num).length>0&&flag){
		var jl = "合格";
		if(wcx==$("."+num).length){
			jl = "无此项";
		}
		
		
			try {
				$("#conclusion__"+concl_num).ligerGetComboBoxManager().setValue(jl);
			} catch (e) {
				// TODO: handle exception
			}
		
	}
	
}
var i = 0;
//c重写标签赋值
function setInputVal(name, val,type) {
	try {


		if(name.indexOf("_t")>0){
			names=name.substring(0,name.indexOf("_t"));
			$("#"+names).parent().css("height",'40px');
	    	$("#"+names).hide();
	    	$("#"+names).after("<div class='sup_sub_div' onfocus='textareafocus(this)' id='"+names+"_t' contenteditable='true' name='"+names+"_t' rows='2' cols='43' style='background-color:#CAE1FF'  >s<sup>ss</sup>s</div>");
		
	    	try {
	    		
	    		$("#"+names).parents("tr").show();
			} catch (e) {
				console.log(e);
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
				console.log(e);
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
	
	if(name.indexOf('_time')>0||name.indexOf('_date')>0){
		if(val.length>10){
			val = val.substring(0,10);
		}
	}
	
	var page=0;
	var action = $('form#formObj').attr('action');
	var reg = /page=[0-9]*/;
	action.replace(reg, function() { 
		//console.log(arguments.length); //5 
		//console.log(arguments);//test 
		page = arguments[0].replace('page=','');
	});
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
	console.log(JSON.stringify(hasNamed))
	console.log(JSON.stringify(sameNamed))
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
	console.log(JSON.stringify(hasNamed))
	console.log(JSON.stringify(baseNamed))
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
	alert(show)
	
}

function setSginPicture(signMap){
	
	var signInput = $(".signPic");
	for (var i = 0; i < signInput.length; i++) {
		var item = signInput[i];
		var id = $(item).attr("id");
		var name = $(item).attr("name");
		if(signMap[name+"_signp"]!=null&&signMap[name+"_signp"]!=""
			&&name!="base__inspect_op"&&name!="base__enter_op"
				&&name!="base__confirm_op"&&name!="base__audit_op"
					&&name!="base__sign_op"){
			//取录入时选择的人员的签名
			var signId = signMap[name+"_signp"];
			var signIds = signId.split(",");
			var parent = $(item).parents("td");
			$(item).parents("td").html("");
			//alert(name+"---"+signId)
			for (var j = 0; j < signIds.length; j++) {
				
				
				$(parent).append('<img style="padding:1px;height:40px;" src="fileupload/downloadByObjId.do?id='+signIds[j]+'"/>');
			}
		}else{
			//取流程中人员的签名
			var flowOp = null;
			if(name.indexOf("base__inspect_op")!=-1){
				flowOp = "base__inspect_op";
			}else if(name.indexOf("base__enter_op")!=-1){
				flowOp = "base__enter_op";
			}else if(name.indexOf("base__confirm_op")!=-1){
				flowOp = "base__confirm_op";
			}else if(name.indexOf("base__audit_op")!=-1){
				flowOp = "base__audit_op";
			}else if(name.indexOf("base__sign_op")!=-1){
				flowOp = "base__sign_op";
			}
			if(flowOp != null){
				if(signMap[flowOp+"_signp"]!=null&&signMap[flowOp+"_signp"]!=""){
					var signId = signMap[flowOp+"_signp"];
					//alert(name+"---"+signId)
					var signIds = signId.split(",");
					var parent = $(item).parents("td");
					$(item).parents("td").html("");
					for (var j = 0; j < signIds.length; j++) {
						$(parent).append('<img style="padding:1px;height:40px;" src="fileupload/downloadByObjId.do?id='+signIds[j]+'"/>');
					}
				}
			}
			 
		}
	}
	
}

function setSignPictrue(name,val){
	name = name.substring(0,name.length-5);
	var page=0;
	var action = $('form#formObj').attr('action');
	var reg = /page=[0-9]*/;
	action.replace(reg, function() { 
		//console.log(arguments.length); //5 
		//console.log(arguments);//test 
		page = arguments[0].replace('page=','');
	});
	var check_ops = val.split(",");
	var value = "";
	for (var i = 0; i < check_ops.length; i++) {
		if(value == ""){
			value = check_ops[i];	
		}else{
			value = value+";"+check_ops[i];	
		}
	}

	$("#"+name).hide();
	
	$("#"+name).parents("td").append('<img style="padding: 1px;height:40px;" src="fileupload/downloadByObjId.do?id='+val+'"/>');
	
		try {
			
			$("#"+name).ligerGetComboBoxManager().setValue(value);

		} catch (e) {
			try {
				var prev = $("#"+name+page).ligerGetComboBoxManager().getValue();
				if(prev==""||prev==null){
					$("#"+name+page).parents("div").hide();
					$("#"+name+page).parents("td").append('<img  style="padding: 1px;height:40px;" src="fileupload/downloadByObjId.do?id='+val+'"/>');
				}

			} catch (e) {
				//console.error(e)
				// TODO: handle exception

			}// TODO: handle exception

		}
		
		
}


/**

 * 文本框根据输入内容自适应高度

* @param                {HTMLElement}        输入框元素

* @param                {Number}                设置光标与输入框保持的距离(默认0)

 * @param                {Number}                设置最大高度(可选)

 */

var autoTextarea = function (elem, extra, maxHeight) {
		
        extra = extra || 0;

        var isFirefox = !!document.getBoxObjectFor || 'mozInnerScreenX' in window,

        isOpera = !!window.opera && !!window.opera.toString().indexOf('Opera'),

                addEvent = function (type, callback) {

                        elem.addEventListener ?

                                elem.addEventListener(type, callback, false) :

                                elem.attachEvent('on' + type, callback);

                },

                getStyle = elem.currentStyle ? function (name) {

                        var val = elem.currentStyle[name];

 

                        if (name === 'height' && val.search(/px/i) !== 1) {

                                var rect = elem.getBoundingClientRect();

                                return rect.bottom - rect.top -

                                        parseFloat(getStyle('paddingTop')) -

                                        parseFloat(getStyle('paddingBottom')) + 'px';        

                        };

 

                        return val;

                } : function (name) {

                                return getComputedStyle(elem, null)[name];

                },

                minHeight = parseFloat(getStyle('height'));

 

        elem.style.resize = 'none';

 

        var change = function () {

                var scrollTop, height,

                        padding = 0,

                        style = elem.style;

 

                if (elem._length === elem.value.length) return;

                elem._length = elem.value.length;

 

                if (!isFirefox && !isOpera) {

                        padding = parseInt(getStyle('paddingTop')) + parseInt(getStyle('paddingBottom'));

                };

                scrollTop = document.body.scrollTop || document.documentElement.scrollTop;

 

                elem.style.height = minHeight + 'px';

                if (elem.scrollHeight > minHeight) {

                        if (maxHeight && elem.scrollHeight > maxHeight) {

                                height = maxHeight - padding;

                                style.overflowY = 'auto';

                        } else {

                                height = elem.scrollHeight - padding;

                                style.overflowY = 'hidden';

                        };

                        style.height = height + extra + 'px';

                        scrollTop += parseInt(style.height) - elem.currHeight;

                        document.body.scrollTop = scrollTop;

                        document.documentElement.scrollTop = scrollTop;

                        elem.currHeight = parseInt(style.height);

                };

        };

 

        addEvent('propertychange', change);

        addEvent('input', change);

        addEvent('focus', change);

        change();

};

//自定义checkbox渲染方式
function initCheckBoxDIV(dataD){
	var dataa = dataD.data;
	var name = dataD.name;
	var xm = "";
	for (var i = 0; i < dataa.length; i++) {
		xm = xm+'<div style="display:inline;width:100%;"><input type="checkbox" style="width:20px;" name="'+name
		+'" value="'+dataa[i].id+'" id="'+name+'-'+i+'" class="checkBox-'+name+'" onchange="changeCheck(this)"><label for="'+name+'-'+i+'">'+dataa[i].text+'</label></div>'
	}
	var html  = '<div class="l-checkboxlist-inner" style="width:100%;">'
		+'<table cellpadding="0" cellspacing="0" border="0" class="l-checkboxlist-table" style="width:100%;"><tbody><tr><td>'
		+xm
		+'</td></tr></tbody></table>'
		+' </div><input type="hidden" name="'+name+'_val" id="'+name+'_val" data-ligerid="'+name+'" value="">';
	$("#"+name).attr("class","l-checkboxlist");
	$("#"+name).css("width","100%");
	$("#"+name).attr("ligeruiid",name);
	$("#"+name).append(html);
	
	
	
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
	var checkName = $(".checkBox-"+name);
	for (var i = 0; i < checkName.length; i++) {
		var item = checkName[i];
		var iv = $(item).val()
		if(val.indexOf(";"+iv+";")!=-1){
			$(item).attr("checked","checked");
		}
	}
}

//下拉框改变事件
function onSelectValueChange(inp,inpN){
	var text = $("#"+inp.id).val();
	
	$("#"+inpN).val(text);
}
