/**
 * 此js用于处理报表录入过程中快捷键问题
 */
var downIndex = 0;
var kj = false;
var preType = null;
var preId = null;
var checkItem = null;
$(function(){
	if(input=="1"){
		//录入的时候
		$("input[ltype='select']").focus(function() {
			$("#inputFocus").val($(this).attr("id"));
			var id = $(this).attr("id");
			bindInputEl = this;
			
			if($(this).attr("type")=="text"){
				if(kj){
					checkSelectFocus($(this).attr("id"));
				}else{
					setValueShow(id,$("#"+id+"_val").val());
				}
			}
			
			
		});
		$("input[type='checkbox']").focus(function() {
			$("#inputFocus").val($(this).attr("id"));
			bindInputEl = this;
			
			
		});
		$("input[type='checkbox']").mouseout(function(event) {
			event.stopPropagation();
			
		});
		$("input[ltype='select']").click(function() {
			$("#inputFocus").val($(this).attr("id"));
			if(paramss.mobile!=undefined&&paramss.mobile=="1"){
				var i = getSIndex($(this).attr("id"));
				var w = $($(".l-box-select")[i]).css("width");
				w = w.substring(0,w.length-2)-0;
				if(w<150){
					$($(".l-box-select")[i]).css("width","150px");
				}
				var h = $($(".l-box-select")[i]).css("height");
				h = h.substring(0,h.length-2)-0;
				if(h<200){
					$($(".l-box-select")[i]).css("height","200px");
					$($(".l-box-select")[i]).find("div").css("height","100%");
					$($(".l-box-select")[i]).find("table").css("height","100%");
				}
			}
			
		});
	/*	$("input[ltype='select']").bind("tap",function(e){
				alert(2)
		})*/
	}
})
//键盘点击事件
window.onkeydown = function(e) {

	var e = e || event; //做IE兼容

	var keyCode = e.keyCode//获取键盘上的数据;
	if(9==keyCode){
		//tab 键
		downIndex = 0;
		kj = true;
		checkItem = null;
	}else if(16==keyCode){
		//shift 键
		
	}else if(17==keyCode){
		//ctrl 键
		
	}else if(38==keyCode){
		//方向上 键
		event.preventDefault();
		checkSelectFocusUp();
	}else if(40==keyCode){
		//方向下 键
		event.preventDefault();
		checkSelectFocusDown();
		
	}else if(32==keyCode){
		//空格 键
		checkOk();
		
	}/*else if(13==keyCode){
		//enter 键
		alert("enter 键")
		
	}*/
}

/**
 * 处理tap键到select选项时
 * @returns
 */
function checkSelectFocus(id){
	
	//var id = $("#inputFocus").val();
	var ltype  = $("#"+id).attr("ltype");
	if(ltype=="select"){
		//下拉要考虑复选问题
		$item = $("#"+id);
		//alert("下拉框")
		$item.trigger("click");
		setValueShow(id,$("#"+id+"_val").val());
		
		return;
		//$item.parents("td").find("div .l-text-wrapper").trigger("click");
		//$($item.parents("td").find("div .l-text-combobox")).trigger("click");
	}
}

/**
 * 处理方向下键到select选项时
 * @returns
 */
function checkSelectFocusDown(){
	var id = $("#inputFocus").val();
	if(preId!=null&&id==preType){
	
		var isMultiSelect = preType;
		if(isMultiSelect==undefined||isMultiSelect=='false'||isMultiSelect==false){
			downIndex++;
			var i = getSIndex(id);
			
			$($($(".l-box-select")[i]).find("tr")[downIndex]).find("td").trigger("click");
			return;
		}else{
			var i = getSIndex(id);
			
			try {
				if($($($(".l-box-select")[i]).find("tr")[downIndex-1]).find("a").css("background-position")=="-13px -13px"){
					$($($(".l-box-select")[i]).find("tr")[downIndex-1]).find("a").css("background-position","0px -13px");
				}else if($($($(".l-box-select")[i]).find("tr")[downIndex-1]).find("a").css("background-position")!="0px -13px"){
					$($($(".l-box-select")[i]).find("tr")[downIndex-1]).find("a").css("background-position","0px 0px");
				}
				
			} catch (e) {
				// TODO: handle exception
			}
			if($($($(".l-box-select")[i]).find("tr")[downIndex]).find("a").css("background-position")!="0px -13px"){
				$($($(".l-box-select")[i]).find("tr")[downIndex]).find("a").css("background-position","-13px 0px");
			}else{
				$($($(".l-box-select")[i]).find("tr")[downIndex]).find("a").css("background-position","-13px -13px");
			}
			checkItem = $($($(".l-box-select")[i]).find("tr")[downIndex]).find("input");
			downIndex++;
			
			return;
		
			/*var i = getSIndex(id);
			$($($(".l-box-select")[i]).find("tr")[downIndex]).find("td").trigger("click");
			return;*/
		}
	}else{
		var ltype  = $("#"+id).attr("ltype");
		if(ltype=="select"){
			//下拉要考虑复选问题
			//alert("下拉框")
			
			var ligerui1 = $("#"+id).attr("ligerui");
			
			if(ligerui1!=undefined&&ligerui1!=""){
				var ligerui = [];
				try {
						ligerui = eval('(' + ligerui1 + ')');
				} catch (e) {
						//alert(ligerui1)
				}
				var isMultiSelect = ligerui.isMultiSelect;
				preType = isMultiSelect;
				if(isMultiSelect==undefined||isMultiSelect=='false'||isMultiSelect==false){
					downIndex++;
					var i = getSIndex(id);
					$($($(".l-box-select")[i]).find("tr")[downIndex]).find("td").trigger("click");
					return;
				}else{
					
					var i = getSIndex(id);
					try {
						if($($($(".l-box-select")[i]).find("tr")[downIndex-1]).find("a").css("background-position")=="-13px -13px"){
							$($($(".l-box-select")[i]).find("tr")[downIndex-1]).find("a").css("background-position","0px -13px");
						}else if($($($(".l-box-select")[i]).find("tr")[downIndex-1]).find("a").css("background-position")!="0px -13px"){
							$($($(".l-box-select")[i]).find("tr")[downIndex-1]).find("a").css("background-position","0px 0px");
						}
						
					} catch (e) {
						// TODO: handle exception
					}
					if($($($(".l-box-select")[i]).find("tr")[downIndex]).find("a").css("background-position")!="0px -13px"){
						$($($(".l-box-select")[i]).find("tr")[downIndex]).find("a").css("background-position","-13px 0px");
					}else{
						$($($(".l-box-select")[i]).find("tr")[downIndex]).find("a").css("background-position","-13px -13px");
					}
					$($($(".l-box-select")[i]).find("tr")[downIndex]).find("input").trigger("mouseover");
					checkItem = $($($(".l-box-select")[i]).find("tr")[downIndex]).find("input");
					downIndex++;
					return;
				
					/*var i = getSIndex(id);
					$($($(".l-box-select")[i]).find("tr")[downIndex]).find("td").trigger("click");
					return;*/
				}
			}else{
				var i = getSIndex(id);
				$($($(".l-box-select")[i]).find("tr")[downIndex]).find("td").trigger("click");
				return;
			}
			//$item.parents("td").find("div .l-text-wrapper").trigger("click");
			//$($item.parents("td").find("div .l-text-combobox")).trigger("click");
		}
	}
	
	
}

/**
 * 处理方向上键到select选项时
 * @returns
 */
function checkSelectFocusUp(){
	var id = $("#inputFocus").val();
	var ltype  = $("#"+id).attr("ltype");
	if(preId!=null&&id==preType){
		
		var isMultiSelect = preType;
		if(isMultiSelect==undefined||isMultiSelect=='false'||isMultiSelect==false){
			if(downIndex>0){
				downIndex--;
			}
			
			var i = getSIndex(id);
			$($($(".l-box-select")[i]).find("tr")[downIndex]).find("td").trigger("click");
			return;
		}else{
			var i = getSIndex(id);
			if(downIndex>0){
				downIndex--;
			}
			if($($($(".l-box-select")[i]).find("tr")[downIndex]).find("a").css("background-position")=="-13px -13px"){
				$($($(".l-box-select")[i]).find("tr")[downIndex]).find("a").css("background-position","0px -13px");
			}else if($($($(".l-box-select")[i]).find("tr")[downIndex]).find("a").css("background-position")!="0px -13px"){
				$($($(".l-box-select")[i]).find("tr")[downIndex]).find("a").css("background-position","0px 0px");
			}
			if($($($(".l-box-select")[i]).find("tr")[downIndex-1]).find("a").css("background-position")!="0px -13px"){
				$($($(".l-box-select")[i]).find("tr")[downIndex-1]).find("a").css("background-position","-13px 0px");
			}else{
				$($($(".l-box-select")[i]).find("tr")[downIndex-1]).find("a").css("background-position","-13px -13px");
			}
			$($($(".l-box-select")[i]).find("tr")[downIndex-1]).find("input").trigger("mouseover");
			checkItem = $($($(".l-box-select")[i]).find("tr")[downIndex-1]).find("input");
			
			
			return;
		
			/*var i = getSIndex(id);
			$($($(".l-box-select")[i]).find("tr")[downIndex]).find("td").trigger("click");
			return;*/
		}
	}else{
		var ltype  = $("#"+id).attr("ltype");
		if(ltype=="select"){
			//下拉要考虑复选问题
			//alert("下拉框")
			
			var ligerui1 = $("#"+id).attr("ligerui");
			
			if(ligerui1!=undefined&&ligerui1!=""){
				var ligerui = [];
				try {
						ligerui = eval('(' + ligerui1 + ')');
				} catch (e) {
						//alert(ligerui1)
				}
				var isMultiSelect = ligerui.isMultiSelect;
				preType = isMultiSelect;
				if(isMultiSelect==undefined||isMultiSelect=='false'||isMultiSelect==false){
					if(downIndex>0){
						downIndex--;
					}
					
					var i = getSIndex(id);
					$($($(".l-box-select")[i]).find("tr")[downIndex]).find("td").trigger("click");
					return;
				}else{
					if(downIndex>0){
						downIndex--;
					}
					var i = getSIndex(id);
					if($($($(".l-box-select")[i]).find("tr")[downIndex]).find("a").css("background-position")=="-13px -13px"){
						$($($(".l-box-select")[i]).find("tr")[downIndex]).find("a").css("background-position","0px -13px");
					}else if($($($(".l-box-select")[i]).find("tr")[downIndex]).find("a").css("background-position")!="0px -13px"){
						$($($(".l-box-select")[i]).find("tr")[downIndex]).find("a").css("background-position","0px 0px");
					}
					if($($($(".l-box-select")[i]).find("tr")[downIndex-1]).find("a").css("background-position")!="0px -13px"){
						$($($(".l-box-select")[i]).find("tr")[downIndex-1]).find("a").css("background-position","-13px 0px");
					}else{
						$($($(".l-box-select")[i]).find("tr")[downIndex-1]).find("a").css("background-position","-13px -13px");
					}
					
					$($($(".l-box-select")[i]).find("tr")[downIndex-1]).find("input").trigger("mouseover");
					checkItem = $($($(".l-box-select")[i]).find("tr")[downIndex-1]).find("input");
					
					return;
				
					/*var i = getSIndex(id);
					$($($(".l-box-select")[i]).find("tr")[downIndex]).find("td").trigger("click");
					return;*/
				}
			}else{
				var i = getSIndex(id);
				$($($(".l-box-select")[i]).find("tr")[downIndex]).find("td").trigger("click");
				return;
			}
			//$item.parents("td").find("div .l-text-wrapper").trigger("click");
			//$($item.parents("td").find("div .l-text-combobox")).trigger("click");
		}
	}
	
	
}

function getSIndex(id){
	for (var i = 0; i < $("input[ltype='select']").length; i++) {
		var item = $("input[ltype='select']")[i];
		if(item.id ==id){
			return i;
		}
	}
	return 0;
}


function checkOk(){
	
	console.error("checkItem-----"+checkItem)
	if(checkItem!=null){
		var classs = checkItem.prev("a").attr("class");
		console.log(classs)
		if(classs.indexOf("l-checkbox-checked")!=-1){
			checkItem.prev("a").attr("class",classs.replace(" l-checkbox-checked",""));
			checkItem.prev("a").css("background-position","0px 0px");
		}else{
			checkItem.prev("a").attr("class",classs+" l-checkbox-checked");
			checkItem.prev("a").css("background-position","0px -13px");
		}
			
		/*if(checkItem.attr("checked")==undefined){
			checkItem.attr("checked","checked");
		}else{
			checkItem.remove("checked");
		}*/
		checkItem.trigger("click");
	}
}

function setValueShow(id,val){
	var i = getSIndex(id);
	var values = val.split(",");
	if(values.length==1){
		values =  val.split(";");
	}
	var trs = $($(".l-box-select")[i]).find("tr");
	
	for(var i = 0; i < values.length; i++) {
		for(var j = 0; j < trs.length; j++) {
			var tr = trs[j];
			if($(tr).attr("value")==values[i]){
				
				$(tr).find("a").attr("class",$(tr).find("a").attr("class")+" l-checkbox-checked");
				
				$(tr).find("a").css("background-position","0px -13px");;
			}
		}
	}
	
	
}