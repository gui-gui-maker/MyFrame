
/**
 * 绑定拖拽、缩放、文本编辑等功能
 */
function bindingFun(){
	for (var i = 0; i < $("table").length; i++) {
		var table = $("table")[i];
		   var  colgroup = $(table).find("colgroup").html();
		   if(colgroup==""||colgroup==undefined){
			   continue;
		   }
	        colgroup  = colgroup.replace(/col/g,"td border='1'").replace(/>/g,">....</td>");
	       $(table).find("tbody").html("<tr class='tableHead' style='background-color: #36648B;'>"+colgroup+"</tr>"+ $(table).find("tbody").html())

	        $(table).find("colgroup").html("");
	}
	
	//绑定拖拽功能
    $( "span" ).draggable({
        addClasses:false,
        start: function (event, ui) {
            //记录缓存
            recordTable();
        }
    });//addClasses:false 避免添加不必要样式
   
    $( "table" ).draggable(
        {
            addClasses:false,
            start: function (event, ui) {
                //记录缓存
                recordTable();
            }
        }
    );
    //绑定缩放功能
    
    $( "td" ).resizable({
        addClasses:false
        ,start: function (event, ui) {
            //记录缓存
            recordTable();
        }
    });
   
    $( "table" ).resizable({
        addClasses:false
        ,start: function (event, ui) {
            //记录缓存
            recordTable();
        }
    });
  
    //添加编辑功能
    $("span").attr("contenteditable","true");
}

/**
 * 移除绑定功能和对应样式（避免保存不必要的样式到jsp）
 */
function removeBindingFun(){
    //移除拖拽功能
    try{
        $( "span" ).draggable( "destroy" );
    }catch(e){
        $( "span" ).removeClass("ui-draggable-handle");
    }
    try{
        $( "p" ).draggable( "destroy" );
    }catch(e){
        $( "p" ).removeClass("ui-draggable-handle");
    }
    try{
        $( "table" ).draggable( "destroy" );
    }catch(e){
        $( "table" ).removeClass("ui-draggable-handle");
    }
    //移除缩放功能
    try{
        $( "td" ).resizable( "destroy" );
    }catch(e){
        $("div.ui-resizable-handle").remove();
        $( "td" ).removeClass("ui-resizable");
    }
    try{
        $( "p" ).resizable( "destroy");
    }catch(e){
        $("div.ui-resizable-handle").remove();
        $( "p" ).removeClass("ui-resizable");
    }
    try{
        $( ".document" ).resizable( "destroy" );
    }catch(e){
        $("div.ui-resizable-handle").remove();;
        $( ".document" ).removeClass("ui-resizable");
    }
    try{
        $( "table" ).resizable( "destroy" );
    }catch(e){
        $("div.ui-resizable-handle").remove();
        $( "table" ).removeClass("ui-resizable");
    }
    try{
        $( "#layout2" ).resizable( "destroy" );
    }catch(e){
        $("div.ui-resizable-handle").remove();
        $( "#layout2" ).removeClass("ui-resizable");
    }
    //移除编辑功能
    $( "span" ).removeAttr("contenteditable");
}
/**
 * 设置标记的文本样式居左
 */
 function setLeft() {
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
    try {
    	 $(".seled").css("text-align","left");
    	    $(".seled").find("p").css("text-align","left");
    	    if($(".seled").find("span").length>0){
     	    	$(".seled").find("span").css("text-align","left");
     	    }
     	    if($(".seled").find("input").length>0){
    	    	$(".seled").find("input").css("text-align","left");
    	    }
	} catch (e) {
		// TODO: handle exception
	}
   
        if($(".pclick").find("input").length>0){
            $(".pclick").find("input").css("text-align","left");
		}else{
            $(".pclick").css("text-align","left");
		}
        if($(".pclickd").find("input").length>0){
            $(".pclickd").find("input").css("text-align","left");
        }else{
            $(".pclickd").css("text-align","left");
        }
}

/**
 * 设置标记的文本样式居中
 */
function setCenter() {
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
    try {
   	 $(".seled").css("text-align","center");
   	    $(".seled").find("p").css("text-align","center");
   	 if($(".seled").find("span").length>0){
	    	$(".seled").find("span").css("text-align","center");
	    }
	    if($(".seled").find("input").length>0){
	    	$(".seled").find("input").css("text-align","center");
	    }
	} catch (e) {
		// TODO: handle exception
	}
            if($(".pclick").find("input").length>0){
                $(".pclick").find("input").css("text-align","center");
            }else{
                $(".pclick").css("text-align","center");
            }

            if($(".pclickd").find("input").length>0){
                $(".pclickd").find("input").css("text-align","center");
            }else{
                $(".pclickd").css("text-align","center");

		    }


}

/**
 * 设置标记的文本样式居右
 */
function setRight() {
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
    try {
      	 $(".seled").css("text-align","right");
      	    $(".seled").find("p").css("text-align","right");
      	  if($(".seled").find("span").length>0){
     	    	$(".seled").find("span").css("text-align","right");
     	    }
     	    if($(".seled").find("input").length>0){
    	    	$(".seled").find("input").css("text-align","right");
    	    }
   	} catch (e) {
   		// TODO: handle exception
   	}
    if($(".pclick").find("input").length>0){
        $(".pclick").find("input").css("text-align","right");
    }else{
        $(".pclick").css("text-align","right");
    }
    if($(".pclickd").find("input").length>0){
        $(".pclickd").find("input").css("text-align","right");
    }else{
        $(".pclickd").css("text-align","right");
    }


}
/**
 * 设置标记字体大小
 */
function spenfontSizes(size){
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
    try {
      	 $(".seled").css("font-size",size+"pt");
      	    $(".seled").find("p").css("font-size",size+"pt");
      	  if($(".seled").find("span").length>0){
   	    	$(".seled").find("span").css("font-size",size+"pt");
   	    }
   	    if($(".seled").find("input").length>0){
  	    	$(".seled").find("input").css("font-size",size+"pt");
  	    }
   	} catch (e) {
   		// TODO: handle exception
   	}
    if($(".pclick").find("input").length>0){
        $(".pclick").find("input").css("font-size",size+"pt");
    }else{
        $(".pclick").find("span").css("font-size",size+"pt");
    }
    if($(".pclickd").find("input").length>0){
        $(".pclickd").find("input").css("font-size",size+"pt");
    }else{
        $(".pclickd").find("span").css("font-size",size+"pt");
    }
}
/**
 * 设置标记字体
 */
function spenFontFamilys(grade){
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
    
    try {
     	 $(".seled").css("font-family",grade);
     	    $(".seled").find("p").css("font-family",grade);
     	    if($(".seled").find("span").length>0){
     	    	$(".seled").find("span").css("font-family",grade);
     	    }
     	    if($(".seled").find("input").length>0){
    	    	$(".seled").find("input").css("font-family",grade);
    	    }
  	} catch (e) {
  		// TODO: handle exception
  	}
    if($(".pclick").find("input").length>0){
        $(".pclick").find("input").css("font-family",grade);
    }else {
        $(".pclick").find("span").css("font-family",grade);
    }
    if($(".pclickd").find("input").length>0){
        $(".pclickd").find("input").css("font-family",grade);
    }else {
        $(".pclickd").find("span").css("font-family",grade);
    }
}

/**
 * 处理span 设置标记的文本样式两端分散对齐
 */
function justified(){
    if($(".pclick").find("input").length>0
        ||$(".pclickd").find("input").length>0|| $(".pclick").length==0){
        return;
    }
    //分散对齐样式（暂时没想好，存在该样式跳过该步骤）
    $("table").append("<style type=\"text/css\">\n" +
        "    .cent{\n" +
        "        width: 135px;\n" +
        "        display: inline-block;\n" +
        "        text-align: justify;\n" +
        "        text-align-last: justify;\n" +
        "    }\n" +
        "    p:after {\n" +
        "        display: inline-block;\n" +
        "        content: '';\n" +
        "        overflow: hidden;\n" +
        "        width: 100%;\n" +
        "        height: 0;\n" +
        "    }\n" +
        "</style>");
    //移除绑定功能
    removeBindingFun();
    //移除绑定事件
    unbindFun();
    $(".pclick").each(function(index,element){
        var spanTexts = "";
        var spanStyle ="";
        var spanClass ="";
        var spanClassm="";
        var spanStylem ="";
       $(this).find("span").each(function(index,element){
           var text =  $(this).text();
           var $thisStyle = $(this).attr("style");
           var $thisClass = $(this).attr("class");
           var falg = true,falgm = true;
           if(text!=""&&text!=":"&&text!="："&&falg){
               spanStyle = $thisStyle==undefined?"":"style='"+$thisStyle+"' ";
               if($(this).is(".cent")){
                   spanClass = $thisClass==undefined?"":"class='"+$thisClass+"'";
               }else{
                   spanClass = $thisClass==undefined?"":"class='"+$thisClass+" cent'";
               }
               falg = false;
           }
           if(falgm&&(text==":"||text=="：")){
               spanClassm=$thisClass==undefined?"":"class='"+$thisClass+"' ";
               spanStylem=$thisStyle==undefined?"":"style='"+$thisStyle+"' ";
               falgm = false;
           }

           spanTexts +=text;
        });
        if(spanTexts.indexOf(":")>-1||spanTexts.indexOf("：")>-1){
            spanTexts = spanTexts .replace(":","").replace("：","").replace(/\s/g,"")
            spanStylem ==""?spanStyle:spanStylem;
            $(this).html("<span "+spanClass+spanStyle+">"+spanTexts+"</span><span  "+spanClassm+spanStylem+">：</span>")
        }else{
            $(this).html("<span "+spanClass+spanStyle+">"+spanTexts+"</span>")
        }

    });
    removepclick();
    //重新绑定功能
    bindingFun();
    //重新绑定右击事件
    bindingEvent();
}
function mergeSpan() {
    if($(".pclick").find("input").length>0
        ||$(".pclickd").find("input").length>0){
        return;
    }
    var $merSpan = "";
    if($(".pclick").length>0){
        $merSpan = $(".pclick");
    }
    if($(".pclickd").length>0){
        $merSpan = $(".pclickd");
    }
    if($merSpan == ""){
        return;
    }
    //移除绑定功能
    removeBindingFun();
    //移除绑定事件
    unbindFun();
    //记录缓存
    recordTable();
    $merSpan.each(function(index,element){
        var spanTexts = "";
        var spanStyle ="";
        var spanClass ="";
        var spanClassm="";
        var spanStylem ="";
        $(this).find("span").each(function(index,element){
            if($(this).find("span").length==0){
                var text =  $(this).text();
                var $thisStyle = $(this).attr("style");
                var $thisClass = $(this).attr("class");
                var falg = true,falgm = true;
                if(text!=""&&text!=":"&&text!="："&&falg){
                    spanStyle = $thisStyle==undefined?"":"style='"+$thisStyle+"' ";
                    spanClass = $thisClass==undefined?"":"class='"+$thisClass+"'";
                    falg = false;
                }
                if(falgm&&(text==":"||text=="：")){
                    spanClassm=$thisClass==undefined?"":"class='"+$thisClass+"' ";
                    spanStylem=$thisStyle==undefined?"":"style='"+$thisStyle+"' ";
                    falgm = false;
                }
                spanTexts +=text;
            }
        });
        if(spanTexts.indexOf(":")>-1||spanTexts.indexOf("：")>-1){
            spanTexts = spanTexts .replace(":","").replace("：","").replace(/\s/g,"")
            spanStylem ==""?spanStyle:spanStylem;
            $(this).html("<span "+spanClass+spanStyle+">"+spanTexts+"</span><span  "+spanClassm+spanStylem+">：</span>")
        }else{
            $(this).html("<span "+spanClass+spanStyle+">"+spanTexts+"</span>")
        }

    });
    //重新绑定功能
    bindingFun();
    //重新绑定右击事件
    bindingEvent();
}
/**
 * 移除标记样式
 */
function removepclick(){
		$(".pclick").removeClass("pclick");
        $(".pclickd").removeClass("pclickd");
        $(".addtrcl").removeClass("addtrcl");
        $(".sheargrid").removeClass("sheargrid");
        $(".copygrid").removeClass("copygrid");
        $(".pclickTable").removeClass("pclickTable");
        $(".pclicktr").removeClass("pclicktr");
        //属性框控制
        parent.window.propertySignleConfig(true);
}
function unbindFun(){
    $("p").unbind();
    $("td").unbind();
}

/**
 * 添加一行
 */
function addtr(){
    //移除绑定功能
    removeBindingFun();
    //移除绑定事件
    unbindFun();
    //记录缓存
    recordTable();
    $($(".addtrcl").parents("tr").clone(true)).insertAfter($(".addtrcl").parents("tr"));
    //重新绑定功能（给新加的也绑定上功能）
    bindingFun();
    //重新绑定右击事件（给新添加的也绑定上右击事件）
    bindingEvent();
    removepclick();
}

/**
 * 记录缓存
 */
function recordTable() {
    var index =  $("#layout1").data("index");
    index  = index==undefined  ? 0 : index + 1 ; //记录次数
    $("#layout1").data("index",index);
    $("#layout1").data("layout"+index,$("#layout1").html()); //缓存拷贝对象
    $("#layout1").data("layoutStyle"+index,$("#layout1").attr("style"));
}
/**
 * 撤回时读取上一步缓存记录
 */
function revoke(){

    var index = $("#layout1").data("index");
    if (index==undefined||index<0) {
        return;
    }
    $("#layout1").attr("style",$("#layout1").data("layoutStyle"+index));
    $("#layout1").html($("#layout1").data("layout"+index)); //重新加载缓存的对象
    $("#layout1").data("layoutStyle"+index,"").data("index",index - 1);
    $("#layout1").data("layout"+index,"").data("index",index - 1); //把已经回退的对象清空，计数减一，
    //移除绑定功能
    removeBindingFun();
    //移除绑定事件
    unbindFun();
    //重新绑定功能
    bindingFun();
    //重新绑定右击事件
    bindingEvent();
    removepclick();
    rmovepP();
    //输入框属性事件
    addFocusListener();
}

/**
 * 移除生成的P标签
 */
function rmovepP(){
    $("table").find("p").each(function(){
        if($(this).find("input").length==0&&
            $(this).find("span").length==0){
            $(this).remove();
        }
    });
}
/**
 * 删除一行
 */
function deltr(){
    //移除绑定功能
    removeBindingFun();
    //移除绑定事件
    unbindFun();
    //记录缓存
    recordTable();
    //删除标记行
    $(".addtrcl").parents("tr").remove();
    //重新绑定功能（给新加的也绑定上功能）
    bindingFun();
    //重新绑定右击事件（给新添加的也绑定上右击事件）
    bindingEvent();
    removepclick();
}
/**
 * 拆分行
 */
function  splitGridLine() {
    var rowsp =  $(".addtrcl").attr("rowspan");
    if(rowsp == undefined||rowsp == "undefined"||rowsp == 1){
        alert("该表格暂时不能拆分");
        return;
        splitGridCol();
    }else{
        //移除绑定功能
        removeBindingFun();
        //移除绑定事件
        unbindFun();
        //记录缓存
        recordTable();
        var thisStyle = $(".addtrcl").attr("style")==undefined?"":"style = '"+$(".addtrcl").attr("style")+"'  ";
        var thisColsp =$(".addtrcl").attr("colspan")==undefined?"":"colspan = '"+$(".addtrcl").attr("colspan")+"'  ";
        var thisRow =$(".addtrcl").attr("rowspan")==undefined?"":"rowspan = '"+(rowsp-Math.round(Number(rowsp)/2))+"'  ";
        rowsp = Math.round(Number(rowsp)/2);
        $(".addtrcl").attr("rowSpan",rowsp);
        var bj = -1;
       $("tbody").each(function (index,element) {
           if($(this).find(".addtrcl").length>0){
               $(this).find("tr").each(function(index2,element2){
                   if($(this).find(".addtrcl").length>0){
                       bj = index2+rowsp;
                   }
                   if(bj == index2){
                       $(this).prepend("<td "+thisStyle+thisColsp+thisRow+"><p><span style=' white-space: pre-wrap; position: relative;'>    </span></p></td>");
                   }
               });
           }
        });

    }
    removepclick();
    //重新绑定功能（给拆分出的也绑定上功能）
    bindingFun();
    //重新绑定右击事件（给拆分出的也绑定上右击事件）
    bindingEvent();
}
/**
 * 拆分列
 */
function splitGridColumn(){
    var colsp =  $(".addtrcl").attr("colspan");
    if(colsp == undefined||colsp == "undefined"||colsp == 1){
       alert("该表格暂时不能拆分");
       return;
        splitGridCol();
    }else{
        //移除绑定功能
        removeBindingFun();
        //移除绑定事件
        unbindFun();
        //记录缓存
        recordTable();
        var thisStyle = $(".addtrcl").attr("style")==undefined?"":"style = '"+$(".addtrcl").attr("style")+"'  ";
        var thisColsp =$(".addtrcl").attr("colspan")==undefined?"":"colspan = '"+(colsp-Math.round(Number(colsp)/2))+"'  ";
        var thisRow =$(".addtrcl").attr("rowspan")==undefined?"":"rowspan = '"+$(".addtrcl").attr("rowspan")+"'  ";
        $(".addtrcl").attr("colSpan",Math.round(Number(colsp)/2));
        $("<td "+thisStyle+thisColsp+thisRow+"><p><span style=' white-space: pre-wrap; position: relative;'>    </span></p></td>").insertAfter(".addtrcl");
    }
    removepclick();
    //重新绑定功能（给拆分出的也绑定上功能）
    bindingFun();
    //重新绑定右击事件（给拆分出的也绑定上右击事件）
    bindingEvent();
}

/**
 * 处理拆分时colspan为 1 的情况
 */
function splitGridCol(colsp){
    var $thisSibTr= $(".addtrcl").parents("tr");
    var $siblingsSibTr= $(".addtrcl").parents("tr").siblings();
    var jl ;
    var thisStyle = $(".addtrcl").attr("style")==undefined?"":"style = '"+$(".addtrcl").attr("style")+"'  ";
    var thisColsp =$(".addtrcl").attr("colspan")==undefined?"":"colspan = '"+colsp+"'";
    var thisRow =$(".addtrcl").attr("rowspan")==undefined?"":"rowspan = '"+$(".addtrcl").attr("rowspan")+"'  ";
    $thisSibTr.find("td").each(function(index,element){
        if($(this).is(".addtrcl")){
            jl = index;
        }
    });
    $siblingsSibTr.each(function(index1,element){
        $(this).find("td").each(function(index2,element){
         // var  $rowSpen = $(this).attr("rowspan")==undefined?1:$(this).attr("rowspan");
            if(index2 == jl){
                var $thisCol =  $(this).attr("colspan")==undefined?1:$(this).attr("colspan");
                $(this).attr("colSpan",(Number($thisCol)+1));
             //   if($(this).parents("tr.tableHead").length>0){
                    //var tdWidth = $(this).width() ;
                    //$(this).css("width",tdWidth/2)
                    //$("<td border='1' style='width:"+tdWidth/2+"'>...</td>").insertAfter($(this));
              //  }else{

              //  }
            }
        });
    });
    $("<td "+thisStyle+thisColsp+thisRow+"><p><span style=' white-space: pre-wrap; position: relative;'>    </span></p></td>").insertAfter(".addtrcl");
}
/**
 * 复制该行（添加被复制标记）
 */
function copyGrid() {
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
    
    $(".copygrid").removeClass("copygrid");
    $(".sheargrid").removeClass("sheargrid");
    $(".addtrcl").parents("tr").addClass("copygrid");
}
/**
 * 剪切该行（添加被剪切标记）
 */
function shearGrid() {
    $(".sheargrid").removeClass("sheargrid");
    $(".copygrid").removeClass("copygrid");
    $(".addtrcl").parents("tr").addClass("sheargrid");
}

/**
 * 粘贴
 */
function pasteGrid(){
    var $copTr = "";
    if($(".copygrid").length>0){
        $copTr = $(".copygrid");
    }
    if($(".sheargrid").length>0){
        $copTr = $(".sheargrid");
    }
    if($copTr!=""){
        //移除绑定功能
        removeBindingFun();
        //移除绑定事件
        unbindFun();
        //记录缓存
        recordTable();
        var $prentsTrStyle = $copTr.attr("style");
        var $thisTrStyle = $prentsTrStyle==undefined?"":"style = '"+$prentsTrStyle+"'";
        $("<tr  "+$thisTrStyle+">"+$copTr.html()+"</tr>").insertAfter($(".addtrcl").parents("tr"));
        if($(".sheargrid").length>0){
            $(".sheargrid").remove();
        }
        removepclick();
        //重新绑定功能（给新加的也绑定上功能）
        bindingFun();
        //重新绑定右击事件（给新添加的也绑定上右击事件）
        bindingEvent();
    }

}
/**
 * 合并标记表格
 */
function  mergeGrid(){
    var colsps = 0,rowsps = 0,text="";
    var $tds = $(".pclick").parents("td");

    var falg = true;
    var num = -1;
    for (var i = 0;i<$tds.length-1;i++){
        num = $($tds[i]).siblings($($tds[i+1])).length;
        if(num>2){
            falg = true;
        }else{
            falg = false;
        }

    }
    //移除绑定功能
    removeBindingFun();
    //移除绑定事件
    unbindFun();
    //记录缓存
    recordTable();
    $(".pclick").each(function(index,element){
        var $thisTdParent = $(this).parents("td");
        var thisColsp = $thisTdParent.attr("colspan")==undefined?1:$thisTdParent.attr("colspan");
        var thisRowsp = $thisTdParent.attr("rowspan")==undefined?1:$thisTdParent.attr("rowspan");
        if(falg){
            colsps =colsps + Number(thisColsp);
        }else{
            rowsps = rowsps + Number(thisRowsp);
        }

        text += $thisTdParent.text()==undefined?"":$thisTdParent.text();
        if(index!=0){
            $thisTdParent.remove();
        }
    });
    if(falg){
        $(".pclick").parents("td").attr("colSpan",colsps);
    }else{
        $(".pclick").parents("td").attr("rowSpan",rowsps);
    }
    //$(".pclick").parents("td").find("span")[0].text(text.replace(/\s/g,""));
    //重新绑定功能（给拆分出的也绑定上功能）
    bindingFun();
    //重新绑定右击事件（给拆分出的也绑定上右击事件）
    bindingEvent();
    removepclick();
}

function  mergeGridTd(){
    var colsps = 0,rowsps = 0,text=""; var html = "";
    var $tds = $(".seled");
    var falg = true;
    var num = -1;
    if($tds.length<2){
    	return;
    }
    var falg = true;
    var num = -1;
   var tr1 = $($tds[0]).parent("tr").html();
   var tr2 = $($tds[1]).parent("tr").html();
    if(tr1==tr2){
    	 falg = true;
    }else{
    	 falg = false;
    }
    //移除绑定功能
    removeBindingFun();
    //移除绑定事件
    unbindFun();
    //记录缓存
    recordTable();
    for (var index = 0; index < $tds.length; index++) {
        var $thisTdParent = $($tds[index]);
        var thisColsp = $thisTdParent.attr("colspan")==undefined?1:$thisTdParent.attr("colspan");
        var thisRowsp = $thisTdParent.attr("rowspan")==undefined?1:$thisTdParent.attr("rowspan");
        if(falg){
            colsps =colsps + Number(thisColsp);
        }else{
            rowsps = rowsps + Number(thisRowsp);
        }
       
        text += $thisTdParent.text()==undefined?"":$thisTdParent.text();
       
        if(index!=0){
        	 html += $thisTdParent.find("p").html()==undefined?"":$thisTdParent.find("p").html();
            $thisTdParent.remove();
        }
	}
    if(falg){
    	$($tds[0]).attr("colSpan",colsps);
    }else{
    	$($tds[0]).attr("rowSpan",rowsps);
    }
    $($tds[0]).append(html);
    //$(".pclick").parents("td").find("span")[0].text(text.replace(/\s/g,""));
    //重新绑定功能（给拆分出的也绑定上功能）
    bindingFun();
    //重新绑定右击事件（给拆分出的也绑定上右击事件）
    bindingEvent();
    removepclick();
}
/**
 * 绑定事件
 */
function bindingEvent(){
        $("span").click(function(e){
            menu.hide();
            //menu2.hide();
            // 阻止冒泡事件，上级的单击事件不会被调用
            e.stopPropagation();
            $(".pclickTable").removeClass("pclickTable");
            $(".pclicktr").removeClass("pclicktr");
            $(".pclicktd").removeClass("pclicktd");
            $(".pclickd").removeClass("pclickd");
        });
        $("p").click(function(e){
            menu.hide();
            //menu2.hide();
           // 阻止冒泡事件，上级的单击事件不会被调用
            e.stopPropagation();
            if($(this).is(".pclick")){
                return;
            }
            $(".pclickTable").removeClass("pclickTable");
            $(".pclicktr").removeClass("pclicktr");
            $(".pclicktd").removeClass("pclicktd");
            $(".pclickd").removeClass("pclickd");
            $(this).addClass("pclickd");
        });
        $("tr").click(function(e){
            menu.hide();
            //menu2.hide();
            // 阻止冒泡事件，上级的单击事件不会被调用
            e.stopPropagation();
            $(".pclickTable").removeClass("pclickTable");
            $(".pclicktr").removeClass("pclicktr");
            $(".pclicktd").removeClass("pclicktd");
            $(".pclickd").removeClass("pclickd");
            $(this).addClass("pclicktr");
        });
        $("td").click(function(e){
            menu.hide();
            //menu2.hide();
            // 阻止冒泡事件，上级的单击事件不会被调用
            e.stopPropagation();
            $(".pclickTable").removeClass("pclickTable");
            $(".pclicktr").removeClass("pclicktr");
            $(".pclicktd").removeClass("pclicktd");
            $(".pclickd").removeClass("pclickd");
            $(this).addClass("pclicktd");
        });

        $("table").click(function(e){
            menu.hide();
            //menu2.hide();
            $(".pclickTable").removeClass("pclickTable");
            $(this).addClass("pclickTable");
        });
        $("p").keyup(function(e){
            $(".pclickd").removeClass("pclickd");
            if(e.which==18){
                $(this).addClass("pclick");
            }
            if(e.which==16){
                var falg =false;
                var sorp = true;
                $(this).addClass("pclick");
                var num = $(".pclick").length;
                var bj = 0;
                if(num==1){
                    alert("请先添加开始标记！");
                    $(".pclick").removeClass("pclick");
                    return;
                }
                $("p").each(function(index,element){
                    if($(this).is(".pclick")){
                        bj++;
                        if(num-bj==1){
                            sorp= $(this).find("input").length==0;
                            falg = true;
                        }
                        if(num-bj==0){
                            falg = false;
                        }
                    }
                    if(falg){
                        if(sorp){
                            if($(this).find("input").length==0){
                                $(this).addClass("pclick");
                            }
                        }else{
                            if($(this).find("input").length>0){
                                $(this).addClass("pclick");
                            }
                        }

                    }
                });
                parent.window.propertySignleConfig(false);
            }

        });
        $("td").keyup(function(e){
            $(".addtrcl").removeClass("addtrcl");
            $(this).addClass("addtrcl");
            //ctrl+c 复制一行
            if(e.which==67 && e.ctrlKey){
                copyGrid();
            }
            //ctrl+x 剪切一行
            if(e.which==88 && e.ctrlKey){
                shearGrid();
            }
            //ctrl+v 粘贴
            if(e.which==86 && e.ctrlKey){
                pasteGrid();
            }
            //ctrl+b 添加一行
            if(e.which==66 && e.ctrlKey){
                addtr();
            }
            //delete 删除一行
            if(e&&e.which==46){
                deltr();
            }
        });
        //绑定右键点击
        $("td").bind("contextmenu", function (e)
        {
            //menu2.hide();
            $(".addtrcl").removeClass("addtrcl");
            $(this).addClass("addtrcl");
            var formObj = document.getElementById("formObj");
            var selDat = getIE(formObj);
           var fr =  formObj.offsetWidth + selDat.left;
           var fb =  formObj.offsetHeight + selDat.top;

            if(e.pageX+170>fr){
            	
            	if(e.pageY+270>fb){
            		//注意右边界
            		menu.show({ top: fb-270, left: fr-170 });
            	}else{
            		//注意右边界
               	 	menu.show({ top: e.pageY, left: fr-170 });
            	}
            	
            	
            	 
            	 
            }else{
            	if(e.pageY+270>fb){
            		//注意右边界
            		menu.show({ top: fb-270, left: e.pageX });
            	}else{
            		menu.show({ top: e.pageY, left: e.pageX });
            	}
            }
           
            return false;
        });
        
       $("span").click(function(e){
    	   SelectText(e.target);
       })
        //绑定右键点击
       
}

/**
 * 上边框
 */
function topLine(){
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
    
	if($(".seled").length==0){
		return;
	}
	var line =  $($(".seled")[0]).css("border-top-style");
	if(line!=undefined&&line!="none"){
		$(".seled").css("border-top-style","none");
	}else{
		$(".seled").css("border-top-style","solid");
	}
    
    //cleanSelect();
}
/**
 * 下边框
 */
function bottomLine(){
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
    
	if($(".seled").length==0){
		return;
	}
	var line =  $($(".seled")[0]).css("border-bottom-style");
	if(line!=undefined&&line!="none"){
		$(".seled").css("border-bottom-style","none");
	}else{
		$(".seled").css("border-bottom-style","solid");
	}
    
    //cleanSelect();
}
/**
 * 左边框
 */
function leftLine(){
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
    
	if($(".seled").length==0){
		return;
	}
	var line =  $($(".seled")[0]).css("border-left-style");
	if(line!=undefined&&line!="none"){
		$(".seled").css("border-left-style","none");
	}else{
		$(".seled").css("border-left-style","solid");
	}
    
   // cleanSelect();
}
/**
 * 右边框
 */
function rightLine(){
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
    
	if($(".seled").length==0){
		return;
	}
	var line =  $($(".seled")[0]).css("border-right-style");
	if(line!=undefined&&line!="none"){
		$(".seled").css("border-right-style","none");
	}else{
		$(".seled").css("border-right-style","solid");
	}
    
    //cleanSelect();
}
/**
 * 无边框
 */
function noneLine(){
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
    
    $(".seled").css("border-top-style","none");
    $(".seled").css("border-bottom-style","none");
    $(".seled").css("border-left-style","none");
    $(".seled").css("border-right-style","none");
    $(".seled").css("border-style","none");
}

/**
 * 全边框
 */
function allLine(){
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
    
    $(".seled").css("border-top-style","solid");
    $(".seled").css("border-bottom-style","solid");
    $(".seled").css("border-left-style","solid");
    $(".seled").css("border-right-style","solid");
    $(".seled").css("border-style","solid");
}



    
/**
 * 删除表格头部编辑栏
 * @returns
 */
    function removeTableHead(){
    	try{
	        $( "td" ).resizable( "destroy" );
	    }catch(e){
	        $("div.ui-resizable-handle").remove();
	        $( "td" ).removeClass("ui-resizable");
	    }
    	for (var i = 0; i < $(".tableHead").length; i++) {
			var tableHead = $(".tableHead")[i];
			var table = $(tableHead).parent("tbody").parent("table");
			 var  colgroup = table.find("colgroup").html();
			   if(colgroup==undefined){
				   continue;
			   }
			var td = $(tableHead).html();
			var cal = td.replace(/td/g,'col').replace(/\.\.\.\./g,'');
			table.find("colgroup").html(cal);
		}
    	$(".tableHead").remove();
    }
    
    
/**
 * 设置标记的文本样式水平居中
*/
function setVerticalMiddle() {
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
        if($(".seled")[0].tagName=="TD"){
        	 $(".seled").css("vertical-align","middle");
        }
        if($(".pclick").parents("td").length>0){
            $(".pclick").parents("td").css("vertical-align","middle");
        }
        if($(".pclickd").parents("td").length>0){
              $(".pclickd").parents("td").css("vertical-align","middle");
        }


}
    
/**
 * 设置标记的文本样式水平居上
*/
function setVerticalTop() {
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
        if($(".seled")[0].tagName=="TD"){
       	 $(".seled").css("vertical-align","top");
       }
        if($(".pclick").parents("td").length>0){
            $(".pclick").parents("td").css("vertical-align","top");
        }
        if($(".pclickd").parents("td").length>0){
              $(".pclickd").parents("td").css("vertical-align","top");
        }
        if($(".seled").length>0){
            $(".seled").css("vertical-align","top");
      }


}
    
/**
 * 设置标记的文本样式水平居下
*/
function setVerticalBottom() {
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
        if($(".seled")[0].tagName=="TD"){
       	 $(".seled").css("vertical-align","bottom");
        }
        if($(".pclick").parents("td").length>0){
            $(".pclick").parents("td").css("vertical-align","bottom");
        }
        if($(".pclickd").parents("td").length>0){
              $(".pclickd").parents("td").css("vertical-align","bottom");
        }


}


function addParamTag(e){
	 var rangeData = {text: "", start: 0, end: 0 };  
	//alert(rangeData.start+"-----------"+rangeData.end+"---"+rangeData.text)
	var preVal = $(e).text();
	rangeData.end = rangeData.start+preVal.length;
	setCursorPosition(e,rangeData);
}

function getCursorPosition(textarea) {  
    var rangeData = {text: "", start: 0, end: 0 };  
        textarea.focus();  
    if (textarea.setSelectionRange) { // W3C  
        rangeData.start= textarea.selectionStart;  
        rangeData.end = textarea.selectionEnd;  
        rangeData.text = (rangeData.start != rangeData.end) ? textarea.value.substring(rangeData.start, rangeData.end): "";  
    } else if (document.selection) { // IE  
        var i,  
            oS = document.selection.createRange(),  
            // Don't: oR = textarea.createTextRange()  
            oR = document.body.createTextRange();  
        oR.moveToElementText(textarea);  
        rangeData.text = oS.text;  
        rangeData.bookmark = oS.getBookmark();  
        // object.moveStart(sUnit [, iCount])  
        // Return Value: Integer that returns the number of units moved.  
        for (i = 0; oR.compareEndPoints('StartToStart', oS) < 0 && oS.moveStart("character", -1) !== 0; i ++) {  
            // Why? You can alert(textarea.value.length)  
            if (textarea.value.charAt(i) == '/n') {  
                i ++;  
            }  
        }  
        rangeData.start = i;  
        rangeData.end = rangeData.text.length + rangeData.start;  
    }  
    return rangeData;  
}  

function setCursorPosition(textarea, rangeData) {  
    if(!rangeData) {  
        alert("You must get cursor position first.")  
    }  
    if (textarea.setSelectionRange) { // W3C  
        textarea.focus();  
        textarea.setSelectionRange(rangeData.start, rangeData.end);  
    } else if (textarea.createTextRange) { // IE  
        var oR = textarea.createTextRange();  
        // Fixbug :  
        // In IE, if cursor position at the end of textarea, the setCursorPosition function don't work  
        if(textarea.value.length === rangeData.start) {  
            oR.collapse(false)  
            oR.select();  
        } else {  
            oR.moveToBookmark(rangeData.bookmark);  
            oR.select();  
        }  
    }  
}


function SelectText(element) {
    var text = element;
    if ($.browser.msie) {
        var range = document.body.createTextRange();
        range.moveToElementText(text);
        range.select();
    } else if ($.browser.mozilla || $.browser.opera) {
        var selection = window.getSelection();
        var range = document.createRange();
        range.selectNodeContents(text);
        selection.removeAllRanges();
        selection.addRange(range);
    } else if ($.browser.safari) {
        var selection = window.getSelection();
        selection.setBaseAndExtent(text, 0, text, 1);
    }
}

/**
 * 设置A4页面方向
 * @param flag ：true为横页
 * @returns
 */
function setHorizontal(flag){
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
    
	var html = '<input type="hidden" name="landFlag" id="landFlag" value="1"/>';
	var $landFlag = $("#landFlag");
	if(flag){
		//横页
		if($landFlag.length==0){
			$("form").append(html);
		}
	}else{
		//竖页
		if($landFlag.length>0){
			$landFlag.remove();
		}
	}
}

/**
 * 改变字体粗细
 */
function fontBold() {
	
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
    try {
   	 $(".seled").css("font-weight", "bold");
   	    $(".seled").find("p").css("font-weight", "bold");
   	 if($(".seled").find("span").length>0){
	    	$(".seled").find("span").css("font-weight", "bold");
	    }
	    if($(".seled").find("input").length>0){
	    	$(".seled").find("input").css("font-weight", "bold");
	    }
	} catch (e) {
		// TODO: handle exception
	}
            if($(".pclick").find("input").length>0){
                $(".pclick").find("input").css("font-weight", "bold");
            }else{
                $(".pclick").css("font-weight", "bold");
            }

            if($(".pclickd").find("input").length>0){
                $(".pclickd").find("input").css("font-weight", "bold");
            }else{
                $(".pclickd").css("font-weight", "bold");

		    }

}
/**
 * 改变字体倾斜
 */
function fontItalic() {
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
    try {
   	 $(".seled").css("font-style", "italic");
   	    $(".seled").find("p").css("font-style", "italic");
   	 if($(".seled").find("span").length>0){
	    	$(".seled").find("span").css("font-style", "italic");
	    }
	    if($(".seled").find("input").length>0){
	    	$(".seled").find("input").css("font-style", "italic");
	    }
	} catch (e) {
		// TODO: handle exception
	}
            if($(".pclick").find("input").length>0){
                $(".pclick").find("input").css("font-style", "italic");
            }else{
                $(".pclick").css("font-style", "italic");
            }

            if($(".pclickd").find("input").length>0){
                $(".pclickd").find("input").css("font-style", "italic");
            }else{
                $(".pclickd").css("font-style", "italic");

		    }


}

/**
 * 设置行高
 * @param height
 * @returns
 */
function setTrHeight(height) {
	
	var $seled = $(".seled");
	
	if($seled.length==0){
		return;
	}
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
  
   var tagName = $seled[0].tagName;
   if(tagName=="TD"){
	  for (var i = 0; i < $seled.length; i++) {
		var item = $seled[i];
		$(item).parent("tr").css("height",height);
	  }
   }else{
	   for (var i = 0; i < $seled.length; i++) {
			var item = $seled[i];
			$(item).parents("p").parent("td").parent("tr").css("height",height);
		 }
   }
}


/**
 * 设置上边距
 * @returns
 */
function setTopMargin(){
	
	var $seled = $(".seled");
	
	if($seled.length==0){
		return;
	}
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
  
   var tagName = $seled[0].tagName;
   var padding = $($seled[0]).css("padding-top")==undefined
   						?$($seled[0]).css("padding")
   						:$($seled[0]).css("padding-top");
   	if(padding==undefined||padding=="0px"){
   		padding=="1px";
   	}else{
   		padding=="0px";
   	}
   if(tagName=="TD"){
	  for (var i = 0; i < $seled.length; i++) {
		var item = $seled[i];
		$(item).find("p").css("padding-top",padding);
	  }
   }else{
	   for (var i = 0; i < $seled.length; i++) {
			var item = $seled[i];
			$(item).parents("p").css("padding-top",padding);
		 }
   }
}

/**
 * 设置下边距
 * @returns
 */
function setBottomMargin(){
	
	var $seled = $(".seled");
	
	if($seled.length==0){
		return;
	}
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
  
   var tagName = $seled[0].tagName;
   var padding = $($seled[0]).css("padding-bottom")==undefined
   						?$($seled[0]).css("padding")
   						:$($seled[0]).css("padding-bottom");
   	if(padding==undefined||padding=="0px"){
   		padding=="1px";
   	}else{
   		padding=="0px";
   	}
   if(tagName=="TD"){
	  for (var i = 0; i < $seled.length; i++) {
		var item = $seled[i];
		$(item).find("p").css("padding-bottom",padding);
	  }
   }else{
	   for (var i = 0; i < $seled.length; i++) {
			var item = $seled[i];
			$(item).parents("p").css("padding-bottom",padding);
		 }
   }
}