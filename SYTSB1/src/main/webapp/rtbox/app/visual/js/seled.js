var seledType = "input";

(function() { 
	seledType = parent.seledType==undefined?"input":parent.seledType;
	//console.log(seledType)
	changeSelectSeledType(seledType);
	
  document.onmousedown = function(e) { 
	 var item = e.target;
	 try {
		 parent.window.hidelinkBtnSelect(); 
		 $("#inputFocus").val($(item).attr("id"));
		 bindInputEl = item;
		 parent.window.showProperties(item);
	} catch (e) {
		// TODO: handle exception
	}
	 
	 /*  if("input"==seledType){
		  if($(item).parents("table").length==0){
			  //处理表格鼠标离开事件
			  removeTableHead();
		  }
	  }
	 */
	  
	  parent.window.showProperties(null);
	  /**
	   * 清除之前选中
	   */
	  cleanSelect();
    var selList = []; 
    if("input"==seledType){
    	var fileNodes = document.getElementsByTagName("input"); 
        for ( var i = 0; i < fileNodes.length; i++) { 
        	
        	if($(fileNodes[i]).attr("type")!="hidden"){
        		// fileNodes[i].className = $(fileNodes[i]).attr("class"); 

        	        selList.push(fileNodes[i]); 
        	}

        } 
        var fileNodesT = document.getElementsByTagName("textarea"); 
        for ( var i = 0; i < fileNodesT.length; i++) { 
        	
        	//fileNodesT[i].className = $(fileNodesT[i]).attr("class"); 

        	  selList.push(fileNodesT[i]); 
        	

        } 
        //图片
        for ( var i = 0; i < $("div").length; i++) { 
        	var item  = $("div")[i];
        	var classs = $(item).attr("class"); 
        	var name = $(item).attr("name"); 
        	if(classs!=undefined){
        		if(classs.indexOf("uploadPhoto")!=-1){
        			//图片
        			 selList.push(item); 
        		}else if(classs.indexOf("checkboxDiv")!=-1){
        			 //复选框
        			 selList.push(item); 
        		}

        	}
        	
        } 
    }else if("span"==seledType){
    	//文本筛选
    	
    	 for ( var i = 0; i < $("p").length; i++) { 
         	var item  = $("p")[i];
         	
         	if($(item).find("span").length>0){
         		 selList.push(item); 

         	}
         	
         } 
    }else if("td"==seledType){
    	//文本筛选
    	
   	 for ( var i = 0; i < $("td").length; i++) { 
        	var item  = $("td")[i];
        	selList.push(item); 
        	
        } 
   }
    
  
    var isSelect = true; 

    var evt = window.event || arguments[0]; 

    var startX = (evt.x || evt.clientX); 

    var startY = (evt.y || evt.clientY); 

    var selDiv = document.createElement("div"); 

    selDiv.style.cssText = "position:absolute;width:0px;height:0px;font-size:0px;margin:0px;padding:0px;border:1px dashed #0099FF;background-color:#C3D5ED;z-index:1000;filter:alpha(opacity:60);opacity:0.6;display:none;"; 

    selDiv.id = "selectDiv"; 

    document.body.appendChild(selDiv); 

    selDiv.style.left = startX + "px"; 

    selDiv.style.top = startY + "px"; 

    var _x = null; 

    var _y = null; 

    clearEventBubble(evt); 

    document.onmousemove = function() { 

      evt = window.event || arguments[0]; 

      if (isSelect) { 

        if (selDiv.style.display == "none") { 

          selDiv.style.display = ""; 

        } 

        _x = (evt.x || evt.clientX); 

        _y = (evt.y || evt.clientY); 

        selDiv.style.left = Math.min(_x, startX) + "px"; 

        selDiv.style.top = Math.min(_y, startY) + "px"; 

        selDiv.style.width = Math.abs(_x - startX) + "px"; 

        selDiv.style.height = Math.abs(_y - startY) + "px"; 

        // ---------------- 关键算法 ---------------------  

        var _l = Math.min(_x, startX), _t = Math.min(_y, startY); 

        var _w = selDiv.offsetWidth, _h = selDiv.offsetHeight; 
        for ( var i = 0; i < selList.length; i++) { 
        	var selDat = getIE(selList[i]);
          var sl = selList[i].offsetWidth + selDat.left; 

          var st = selList[i].offsetHeight + selDat.top; 
          

          if (sl > _l && st > _t && selDat.left < _l + _w && selDat.top < _t + _h) { 

            if (selList[i].className.indexOf("seled") == -1) { 

              selList[i].className = selList[i].className + " seled pclickd"; 

            } 

          } else { 

            if (selList[i].className.indexOf("seled") != -1) { 
            	 selList[i].className = selList[i].className.replace("seled","") ;
            	 selList[i].className = selList[i].className.replace("pclickd","") ; 

            } 

          } 

        } 

      } 

      clearEventBubble(evt); 

    } 

    document.onmouseup = function() { 

      isSelect = false; 

      if (selDiv) { 

        document.body.removeChild(selDiv); 

       // showSelDiv(selList); 
        var count = 0; 
        var seled = null;
        var idss = "";
        for ( var i = 0; i < selList.length; i++) { 

          if (selList[i].className.indexOf("seled") != -1) { 
        	  seled = selList[i];
            count++; 
            var seledId = $(seled).attr("name");
            if(idss==""){
            	idss = seledId;
            }else{
            	idss = idss+","+seledId;
            }
          } 

        }
        
        parent.setSeledIds(idss);
        
        //console.log("select----------"+count)
      //属性框控制
        if(count>1){
        	parent.window.propertySignleConfig(false);
        	parent.window.showProperties(null);
        }else if(count==1){
        	if(seledType=="input"){
        		 $("#inputFocus").val(seled.id);
             	parent.window.showProperties(seled);
        	}
        	
        }
        selList = null, _x = null, _y = null, selDiv = null, startX = null, startY = null, evt = null; 
      } 

     

    } 

  } 

})(); 

/**
 * 获取控件位置
 * @param e
 * @returns
 */
function getIE(e) {
	var t = e.offsetTop;
	var l = e.offsetLeft;
	var width = e.offsetWidth;
	while (e = e.offsetParent) {
		t += e.offsetTop;
		l += e.offsetLeft;
	}
	
	var data = {};
	data["top"] = t;
	data["left"] = l;
	return data;
	
}

function clearEventBubble(evt) { 

  if (evt.stopPropagation) 

    evt.stopPropagation(); 

  else 

    evt.cancelBubble = true; 

  if (evt.preventDefault) 

    evt.preventDefault(); 

  else 

    evt.returnValue = false; 

} 

function showSelDiv(arr) { 

  var count = 0; 

  var selInfo = ""; 

  for ( var i = 0; i < arr.length; i++) { 

    if (arr[i].className.indexOf("seled") != -1) { 

      count++; 

      selInfo += arr[i].innerHTML + "\n"; 

    } 

  } 

  alert("共选择 " + count + " 个文件，分别是：\n" + selInfo); 

} 

/**
 * 情空选中
 * @returns
 */
function cleanSelect(){
	//属性框控制
    parent.window.propertySignleConfig(true);
	var seledss = $(".seled");
	 for ( var ii = 0; ii < seledss.length; ii++) { 

		 var seled = seledss[ii];
		 seled.className = seled.className.replace("seled","");  
		 seled.className = seled.className.replace("pclickd","");  
		 
	} 
}

/**
 * 改变选中筛选条件
 * @param type
 * @returns
 */
function changeSelectSeledType(type){
	seledType = type;
	if(type!="span"){
		bindingFun();
	}else{
		removeBindingFun();
	}
}
