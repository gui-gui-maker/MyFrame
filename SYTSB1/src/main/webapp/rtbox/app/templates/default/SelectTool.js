//tool 在字符串中插入字符串
String.prototype.splice = function(idx, rem, str) {
    return this.slice(0, idx) + str + this.slice(idx + Math.abs(rem));
};
//找出最近的div父元素
function findDivParent(node){
	node = node.parentNode;
	if(node.tagName=="BODY"){
		return "";
	}
	if(node.tagName!="DIV"){
		node = findDivParent(node);
	}
	return node;
}

//接收鼠标选取返回的对象的全局变量
var selObj = {};
//鼠标选取返回的对象函数
function GetSelectedText() {
	//字符串选择事件
        var sel = document.activeElement;
		selObj.activeElement = sel;
        if (sel && 
            (sel.tagName.toLowerCase() == "textarea" || 
             (sel.tagName.toLowerCase() == "input" &&
              sel.getAttribute("type").toLowerCase() == "text"))) 
        {
            var text = sel.value;
			selObj.start =  sel.selectionStart;
			selObj.end = sel.selectionEnd;
            selObj.selText = text.substring(
                sel.selectionStart,
                sel.selectionEnd
            );
         }
} 
//input绑定监听函数
function initSlectTool(){
	 $("input[type='text']").bind("mouseup", function(){
	        GetSelectedText();
	    });
	    $("textarea").bind("mouseup", function(){
	    	GetSelectedText();
	    });
}
/**
 * 
 * @param obj input或TEXTAREA元素
 */
function change(obj){
	if(obj.tagName && (obj.tagName == 'INPUT'||obj.tagName == 'TEXTAREA')){
		var backGroundColor = $(obj).css('background-color');
		var color = $(obj).css('color');
		var fontFamily = $(obj).css("font-family");
		var fontWeight = $(obj).css("font-weight");
		var fontStyle = $(obj).css("font-style");
		var id = $(obj).attr('id');
		var name = $(obj).attr('name');
		var value = $(obj).val();
		value = value.replace("&lt;","<");
		value = value.replace("&gt;",">");	
		//var $td = $(obj).parents("td");
		var height = $(obj).parent().css('height');
		var $p = $(obj).parents("p");//.css({'width':$td.css("width"),'min-height':$td.css("height"),});
		//记录标注
		//var label = $p.find("div.bzhu_box").get(0);
		$p.children().remove();
		//回写标注
		//if(label){
		//	$p.append(label);
		//}
		var editor = document.createElement("div");//input-div
		var hide = document.createElement("input");//切换后的隐藏域
		$(editor).attr({
			'id':id+'_div',
			'name':id,
			'class':'editDiv',
			'onkeydown':'checkEnter(event)',
			'contenteditable':true
		}).css({'background-color':backGroundColor,
			'width':$p.css("width"),
			'min-height':height,
			'_height':height,
			'color':color,
			'text-align':'center',
			'line-height':'13px'
		}).html(value);
		$(hide).attr({'type':'hidden','id':id,'name':name,'class':'editDivInput'}).val(value);
		$p.append(editor);
		$p.append(hide);
		//注册鼠标选择监听函数
		selObjValListener($(editor));
	}
	
};


//核心函数，操作div，将div中的文字加上去掉上下标
function addSS(tag){
	//标签
	var st = '<'+tag+'>';
	var nd = '</'+tag+'>';
	if(selObj.activeElement){
		if(selObj.activeElement.tagName=='TEXTAREA'){
			var txt =  $(selObj.activeElement).val();
			if(txt!=null&&''!=txt&&selObj.end!=selObj.start){
				txt=txt.splice(selObj.end,0,nd);
				txt=txt.splice(selObj.start,0,st);
				$(selObj.activeElement).val(txt);
				change(selObj.activeElement);
			}
		}else if(selObj.activeElement.tagName=='INPUT'){
			var txt =  $(selObj.activeElement).val();
			if(txt!=null&&''!=txt&&selObj.end!=selObj.start){
				txt=txt.splice(selObj.end,0,nd);
				txt=txt.splice(selObj.start,0,st);
				$(selObj.activeElement).val(txt);
				change(selObj.activeElement);
			}
		}else if(selObj.activeElement==sel.el){
			//操作的对象
			var div = sel.el;
			//原始数据
			var oldHtml = sel.htm;
			//var newHtml = '';
			var tempHtml='';
			//正则表达式
			//0匹配标记部分
			var reg0 = /<mark>[\s\S]*<\/mark>/;
			var markHtml = reg0.exec(oldHtml)[0];
			//1.匹配所有开始或结束标签
			var reg1 = /<sup>|<\/sup>|<sub>|<\/sub>/g;
			//2.mark开始标签到第一个reg1
			//var reg2 = /<mark>[\s\S]*?(<sup>|<sub>|<\/sub>|<\/sup>)/;
			//3.mark结束标签到第一个reg1
			var reg3 = /<\/mark>[\s\S]*?(<sup>|<sub>|<\/sub>|<\/sup>)/;
			var endMarkToSbp = reg3.exec(oldHtml);
			//4.标记部分内容是否以<sub>开头
			var reg4 = /^(<sub>|<sup>)[\s\s]*|[\s\S]*(<\/sub>|<\/sup>)$/;
			//5.匹配一堆标签正好是开头和结尾
			var reg5 = /^(<sup>)[\S\s]*(<\/sup>)$|^(<sub>)[\S\s]*(<\/sub>)$/;
			//6.匹配最小单元的成对标签
			var reg6 = new RegExp(st+"[\\s\\S]*?"+nd,'g');
			//7.匹配去reg6后的内容是否符合要求
			var reg7 = new RegExp("[\\s\\S]*"+nd+st+"[\\s\\S]*|[\\s\\S]*("+nd+")$|^("+st+")[\\s\\S]*");
			//分情况
			//condition
			var cd = markHtml.match(reg1);
			//情况1，标记部分中没有上下标开始或结束标签（纯文本/一个标签内部）
			if(cd==null){
				if(endMarkToSbp==null||endMarkToSbp[0].indexOf('<sub>')!=-1||endMarkToSbp[0].indexOf('<sup>')!=-1){
					//文本,只在mark后面加结束标签，在mark前面加开始标签
					tempHtml = '';
					tempHtml = oldHtml.splice(oldHtml.indexOf('</mark>')+7,0,nd);
					tempHtml = tempHtml.splice(tempHtml.indexOf('<mark>'),0,st);
				}else if(endMarkToSbp[0].indexOf('</sub>')!=-1){
					//标签内部
					if(nd=='</sub>'){
						tempHtml = '';
						tempHtml = oldHtml.splice(oldHtml.indexOf('</mark>')+7,0,st);
						tempHtml = tempHtml.splice(tempHtml.indexOf('<mark>'),0,nd);
					}else{
						tempHtml = '';
						tempHtml = oldHtml.splice(oldHtml.indexOf('</mark>')+7,0,nd+'<sub>');
						tempHtml = tempHtml.splice(tempHtml.indexOf('<mark>'),0,'</sub>'+st);
					}
				}else if(endMarkToSbp[0].indexOf('</sup>')!=-1){
					//标签内部
					if(nd=='</sup>'){
						tempHtml = '';
						tempHtml = oldHtml.splice(oldHtml.indexOf('</mark>')+7,0,st);
						tempHtml = tempHtml.splice(tempHtml.indexOf('<mark>'),0,nd);
					}else{
						tempHtml = '';
						tempHtml = oldHtml.splice(oldHtml.indexOf('</mark>')+7,0,nd+'<sup>');
						tempHtml = tempHtml.splice(tempHtml.indexOf('<mark>'),0,'</sup>'+st);
					}
				}
				
			}else if(cd.length==1){
				//标记部分内容是否以<sub>开头
				var stOrNd = reg4.exec(markHtml.substring(6,markHtml.length-7));
				if(stOrNd!=null){
					//只有一个标签
					if(/sup|sub/.exec(cd[0])==tag){
						if(cd[0].replace(/sup|sub/,'')=='<>'){
							tempHtml = '';
							tempHtml = oldHtml.splice(oldHtml.indexOf('</mark>')+7,0,cd[0]);
							
						}else{
							tempHtml = '';
							tempHtml = oldHtml.splice(oldHtml.indexOf('<mark>'),0,cd[0]);
						}
					}else{//这里可以合并到下面，只是为了思路清晰，保留
						if(cd[0].replace(/sup|sub/,'')=='<>'){
							tempHtml = '';
							tempHtml = oldHtml.splice(oldHtml.indexOf('</mark>')+7,0,nd+cd[0]);
							tempHtml = tempHtml.splice(tempHtml.indexOf('<mark>'),0,st);
						}else{
							tempHtml = '';
							tempHtml = oldHtml.splice(oldHtml.indexOf('</mark>')+7,0,nd);
							tempHtml = tempHtml.splice(tempHtml.indexOf('<mark>'),0,cd[0]+st);
						}
					}
				}else{
					//文本加标签
					if(cd[0].replace(/sup|sub/,'')=='<>'){
						tempHtml = '';
						tempHtml = oldHtml.splice(oldHtml.indexOf('</mark>')+7,0,nd+cd[0]);
						tempHtml = tempHtml.splice(tempHtml.indexOf('<mark>'),0,st);
					}else{
						tempHtml = '';
						tempHtml = oldHtml.splice(oldHtml.indexOf('</mark>')+7,0,nd);
						tempHtml = tempHtml.splice(tempHtml.indexOf('<mark>'),0,cd[0]+st);
					}
				}
			}else if(cd.length==2){
				//两个反向标签,且是否标签名一样
				//两个配对标签
				//两个配对且一个开头一个结束
				var stAndNd = reg5.exec(markHtml.substring(6,markHtml.length-7));
				if(stAndNd!=null){
					if(/sup|sub/.exec(cd[0])==tag){
						tempHtml = '';
						tempHtml = oldHtml;
						
					}else{
						tempHtml = '';
						tempHtml = oldHtml.splice(oldHtml.indexOf('</mark>')+7,0,nd);
						tempHtml = tempHtml.splice(tempHtml.indexOf('<mark>'),0,st);
					}
				}else if(/<sub>|<sup>/.test(cd[0])){
					tempHtml = '';
					tempHtml = oldHtml.splice(oldHtml.indexOf('</mark>')+7,0,nd);
					tempHtml = tempHtml.splice(tempHtml.indexOf('<mark>'),0,st);
				}else if(!/<sub>|<sup>/.test(cd[0])){
					if(new RegExp(nd+st).test(markHtml)){
						tempHtml = '';
						tempHtml = oldHtml.splice(oldHtml.indexOf('</mark>')+7,0,st);
						tempHtml = tempHtml.splice(tempHtml.indexOf('<mark>'),0,nd);
					}else{
						tempHtml = '';
						tempHtml = oldHtml.splice(oldHtml.indexOf('</mark>')+7,0,nd+cd[1]);
						tempHtml = tempHtml.splice(tempHtml.indexOf('<mark>'),0,cd[0]+st);
					}
				}
			}else{
				//3个标签及以上
				
				//不含文本，相同标签,且与事件相同
				var isSame = isOneType(cd);
				var repString = markHtml.replace(reg6,'');
				if(isSame&&(reg7.test(repString.substring(6,repString.length-7))||repString.substring(6,repString.length-7)=="")){
					tempHtml = '';
					tempHtml = oldHtml.splice(oldHtml.indexOf('</mark>')+7,0,(cd[cd.length-1].replace(/sup|sub/,'')=='</>'?'':cd[cd.length-1]));
					tempHtml = tempHtml.splice(tempHtml.indexOf('<mark>'),0,(cd[0].replace(/sup|sub/,'')=='<>'?'':cd[0]));
				}else{
					//有不同不标签或有文本或与事件标签相反
					
					tempHtml = '';
					tempHtml = oldHtml.splice(oldHtml.indexOf('</mark>')+7,0,nd+(cd[cd.length-1].replace(/sup|sub/,'')=='</>'?'':cd[cd.length-1]));
					tempHtml = tempHtml.splice(tempHtml.indexOf('<mark>'),0,(cd[0].replace(/sup|sub/,'')=='<>'?'':cd[0])+st);
				}
			}
			
			
				//将mark中的标签去掉
				var str = markHtml.replace(reg1,'');
				tempHtml = tempHtml.replace(reg0,str);
				//去空标签
				tempHtml = tempHtml.replace(/<sub><\/sub>|<sup><\/sup>/g,'');
				//去mark,赋值
				div.innerHTML=tempHtml.replace(/<mark>|<\/mark>/g,'');
				$(div).parent().find('input').val(div.innerHTML);
				//保存标记后的含mark标记的字符串
				tempHtml = tempHtml.replace(st+'<mark>','<mark>'+st);
				tempHtml = tempHtml.replace('</mark>'+nd,nd+'</mark>');
				sel.htm = tempHtml;
		}
	}
}
//判断传入标签数组是否是同一类标签，addSS函数专用
function isOneType(arr){
	var flag = true;
	var mid = /sup|sub/.exec(arr[0])[0];
	for(var i=0;i<arr.length;i++){
		if(/sup|sub/.exec(arr[i])[0]!= mid){
			flag = false;
			break;
		}
	}
	return flag;
}
//
function sub(){
	addSS('sub');	
}
function sup(){
	addSS('sup');	
}
//div绑定监听函数
var sel = {};
function selObjValListener(obj){
    obj.bind("mouseup", function(){
    	sel = getSelect();
    	selObj.activeElement=sel.el;
    	focus_element = this;
    	//alert(focus_element);
    });
    obj.bind("keyup", function(){
    	var str = this.innerHTML;
		var reg = /<span[\s\S]*?>|<\/span>/g;
		if(reg.test(str)){
			correctContent(reg,obj);
		}
    });
    obj.bind('input propertychange', function() {
    	var str = obj.html();
		obj.parents("p.DocDefaults").find("input").val(str);
	});
    obj.focus(function() {
		$("#inputFocus").val($(this).attr("id"));
	});
}
//监听删除重新输入，重新初始化div
/*document.onkeyup=function(event){
			var e = event || window.event || arguments.callee.caller.arguments[0];
			var el = $("div :focus");
			var div = el.context.activeElement;
			if(isInCollection(div,$('div.sup_sub_div').get())){
				var str = div.innerHTML;
				var reg = /<span[\s\S]*?>|<\/span>/g;
				if(reg.test(str)){
					correctContent(reg,div);
				}
			}
			return true;
};
function isInCollection(obj,arr){
	var flag=false;
	for(var i=0;i<arr.length;i++){
		if(obj==arr[i]){
			flag=true;
			break;
		}
	}
	return flag;
}*/ 

//修正删除后自动加span标签
function correctContent(reg,$div){
	 //var txt  = $(div).find('span').html();
	 var txt  = $div.html();
	 txt=txt.replace(reg,"");
	 $div.empty().html(txt);
	 set_focus($div);
	 /*  var selection = null;
	 var range =null;
	try{
		 selection = window.getSelection ? window.getSelection() : document.selection;
		 range = selection.createRange ? selection.createRange() : selection.getRangeAt(0);
		 selection.removeAllRanges();
		// var clds = $div.get(0).childNodes;
		// var lst = clds[clds.length-1];
	    // 把光标移动到内容之后
		 //range.setStart(lst, lst.nodeValue.length);
		 range.setStart(range.startContainer, range.startContainer.innerHTML.length);//selection.focusNode.innerHTML.length
		// range.setEnd(lst, lst.nodeValue.length);	
		 range.setEnd(range.endContainer, range.startContainer.innerHTML.length);	
		 
		 selection.addRange(range);
	 }catch(err){
		 console.log(err.description);
	 }*/

}
//光标定位
function set_focus(el){  
		el=el[0];  // jquery 对象转dom对象  
		el.focus();  
		if($.browser.msie){  
			var rng;  
			el.focus();  
			rng = document.selection.createRange();  
			rng.moveStart('character', -el.innerText.length);  
			var text = rng.text;  
			for (var i = 0; i < el.innerText.length; i++) {  
				if (el.innerText.substring(0, i + 1) == text.substring(text.length - i - 1, text.length)) {  
					result = i + 1;  
				}  
			}  
		}else{  
			var range = document.createRange();  
			range.selectNodeContents(el);  
			range.collapse(false);  
			var sel = window.getSelection();  
			sel.removeAllRanges();  
			sel.addRange(range);  
		}  
}     
//初始化选择对象
function getSelect(){
	var sel={};
	var selection = window.getSelection ? window.getSelection() : document.selection;
	//选择的字符串
	var ss_div = findDivParent(selection.anchorNode);
	
	sel.el=ss_div;
	
	var html = '';
	var allNodes = ss_div.childNodes;
	var anchorNode = {},focusNode = {};
	if(selection.anchorNode.parentNode.tagName=="SUP"||selection.anchorNode.parentNode.tagName=="SUB"){
		anchorNode = selection.anchorNode.parentNode;
	}else{
		anchorNode = selection.anchorNode;
	}
	if(selection.focusNode.parentNode.tagName=="SUP"||selection.focusNode.parentNode.tagName=="SUB"){
		focusNode = selection.focusNode.parentNode;
	}else{
		focusNode = selection.focusNode;
	}
	var anchorIndex =  $(allNodes).index(anchorNode);
	var focusIndex =  $(allNodes).index(focusNode);
	var startNode={},endNode={};
	if(anchorIndex<focusIndex){
		startNode = anchorNode;
		startNode.offset = selection.anchorOffset;
		endNode = focusNode;
		endNode.offset = selection.focusOffset;
	}else if(anchorIndex>focusIndex){
		startNode = focusNode;
		startNode.offset = selection.focusOffset;
		endNode = anchorNode;
		endNode.offset = selection.anchorOffset;
	}else{
		//==
		startNode = focusNode;
		startNode.soffset = selection.anchorOffset<selection.focusOffset?selection.anchorOffset:selection.focusOffset;
		startNode.eoffset = selection.anchorOffset>selection.focusOffset?selection.anchorOffset:selection.focusOffset;
	}
	if(anchorIndex==focusIndex){
		$.each(allNodes,function(index,node){
			if(node == startNode){
				var str='';
				if(startNode.tagName=="SUP"||startNode.tagName=="SUB"){
					if(startNode.innerHTML.length==startNode.eoffset){
						str= startNode.outerHTML.splice(startNode.eoffset+11,0,'</mark>');
					}else{
						str = startNode.outerHTML.splice(startNode.eoffset+5,0,'</mark>');
					}
					if(startNode.soffset==0){
						html += str.splice(startNode.soffset,0,'<mark>');
					}else{
						html += str.splice(startNode.soffset+5,0,'<mark>');
					}
					
				}else{
					str = startNode.nodeValue.splice(startNode.eoffset,0,'</mark>');
					html += str.splice(startNode.soffset,0,'<mark>');
				}
			}else{
				if(node.nodeType==3){
					html += node.textContent;
				}else{
					html += node.outerHTML;
				}
			}
		});
	}else{
		$.each(allNodes,function(index,node){
			if(node == startNode){
				if((startNode.tagName=="SUP"||startNode.tagName=="SUB")&&startNode.innerHTML.length==startNode.offset){
					html += startNode.outerHTML+'<mark>'; 
				}else if((startNode.tagName=="SUP"||startNode.tagName=="SUB")&&startNode.offset==0){
					html += '<mark>'+startNode.outerHTML;
				}else if((startNode.tagName=="SUP"||startNode.tagName=="SUB")&&startNode.innerHTML.length!=startNode.offset&&startNode.offset!=0){
					html += startNode.outerHTML.splice(startNode.offset+5,0,'<mark>');
				}else{
					html += startNode.nodeValue.splice(startNode.offset,0,'<mark>');
				}
			}else if(node == endNode){
				if((endNode.tagName=="SUP"||endNode.tagName=="SUB")&&endNode.innerHTML.length==endNode.offset){
					html += endNode.outerHTML+'</mark>'; 
				}else if((endNode.tagName=="SUP"||endNode.tagName=="SUB")&&endNode.offset==0){
					html += '</mark>'+endNode.outerHTML;
				}else if((endNode.tagName=="SUP"||endNode.tagName=="SUB")&&endNode.innerHTML.length!=endNode.offset&&endNode.offset!=0){
					html += endNode.outerHTML.splice(endNode.offset+5,0,'</mark>');
				}else{
					html += endNode.nodeValue.splice(endNode.offset,0,'</mark>');
				}
			}else{
				if(node.nodeType==3){
					html += node.textContent;
				}else{
					html += node.outerHTML;
				}
			}
		});
	}
	sel.htm = html;
	return sel;
}

var focus_element="";
$(function(){
    $("input").focus(function(){
    	focus_element = this;
    });
    $("textarea").focus(function() {
		focus_element = this;
	});
});
/*
//获取光标位子
function getRange(){
	 var obj = {};
	 var selection = null;
	 var range = null;
	 try{
		 selection = window.getSelection ? window.getSelection() : document.selection;
		 obj.selection = selection;
		 range = selection.createRange ? selection.createRange() : selection.getRangeAt(0);
		 obj.range = range;
		// selection.removeAllRanges();
	    // 把光标移动到内容之后
		// range.setStart(div, txt.length);
		// range.setEnd(div, txt.length);		 
		// selection.addRange(range);
		
	 }catch(e){
		 Console.log("range 对象获取失败！");
		 return '';
	 }
	 return obj;
}

*/

