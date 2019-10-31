function autoEnter(el){
	var div =document.getElementById("textareaAutoer");
	div.style.fontSize = FUNC_SIZE()+"px"; //RtParam.js
	var text=el.value;
	var w=el.offsetWidth-4;
	
	var newText="";
	var newLine="";
	for(i=0;i<text.length;i++){
		newLine+=text.charAt(i);
		div.innerHTML=newLine;		
		
		var dw=div.offsetWidth+FUNC_SIZE();
	
		if(dw>w){
		   newText+=newLine+"\n";
		   newLine="";
		}
	}
	newText+=newLine;
	return {text:newText,lineHeight:div.offsetHeight};
}

 
// textarea 自适应高度  
function makeExpandingArea(el) {
	 var div = document.createElement("div");
	 div.id="textareaAutoer";
	 div.style.cssText="position:absolute;visibility:hidden;";
     document.body.appendChild(div);

    var setStyle = function(el) {  	
		console.log("setStyle=======");
		
        el.style.height = 'auto';  
        el.style.height = el.scrollHeight + 'px';  
		
    }  
    var delayedResize = function(el) {  
        window.setTimeout(function() {  
                setStyle(el)  
            },  
            0);  
    }  
    if (el.addEventListener) {  
        el.addEventListener('input', function() {  
            setStyle(el)  
        }, false);  
        setStyle(el)  
    } else if (el.attachEvent) {	
        el.attachEvent('onpropertychange', function() {  
            setStyle(el)  
        });  
        setStyle(el)  
    }  
    if (window.VBArray && window.addEventListener) { //IE9  
        el.attachEvent("onkeydown", function() {  
            var key = window.event.keyCode;  
            if (key == 8 || key == 46) delayedResize(el);  

        });  
        el.attachEvent("oncut", function() {  
            delayedResize(el);  
        }); //处理粘贴  
    }  
}  


 