

var buttons=document.getElementsByTagName("button");

var RtCtrlKey = window.RtCtrlKey;
var RtKeyCode=window.RtKeyCode;
var RtMousedownX;
var RtMousedownY;

function initWindow(){
	window.document.onkeydown = function(event) {
		event = window.event || event;
		var code = event.keyCode||event.which||event.charCode;
		// console.log("event.ctrlKey :"+event.ctrlKey);
		if (event.ctrlKey) {
			window.RtCtrlKey = true;
			//console.log("Rtkey:"+window.RtCtrlKey);
		}else{
			window.RtCtrlKey = null;
		}
		
		//console.log("code:"+code);
		if(code=="89"&&window.RtCtrlKey ){
			//Y
			//console.log("window redo");
			rtDrawer.redo();
			
		}else if(code=="90"&&window.RtCtrlKey ){
			//Z
			//console.log("window undo");
			rtDrawer.undo();
		}else if(code=="46"){
			//DEL
			//console.log("window delete");
			rtDrawer.delete();
		}else if(code=="27"){
			//取消绘制
			document.getElementById("handButton").click();
			//cancelDraw();
		}else if(code=="67"&&window.RtCtrlKey ){
			//copy
			//console.log("window copy");
			copy();
			
		}else if(code=="86"&&window.RtCtrlKey ){
			//paste
			//console.log("window paste");
			paste();
			
		}else if(code=="37"){
			event.preventDefault(); 				
			rtDrawer.keyMove("left");					
		}else if(code=="38"){ 
			event.preventDefault(); 
			rtDrawer.keyMove("up");
			
		}else if(code=="39"){ 		
			event.preventDefault(); 
			rtDrawer.keyMove("right");
			
		}else if(code=="40"){ 	
			event.preventDefault(); 
			rtDrawer.keyMove("down");
			
		}
	};
	window.document.onkeyup = function(event) {
		window.RtCtrlKey = null;
		if (!window.RtCtrlKey) {
			//console.log("Rtkey is null ");
		}
	};
	
}



initWindow();


function beginDraw(func){
	if(func=="hand"){
		cancelDraw();
	}else{
		canvas.style.cursor = "crosshair";
		document.body.style.cursor="crosshair"; 					
		
		for (var i in buttons ){
			var button=buttons[i];
			if(button&&button.style){
				button.style.cursor="crosshair";
			}						
		}
		
	}
	selectedFunction=func;
	
}

function cancelDraw(){		
	selectedFunction="hand";
	canvas.style.cursor = "default";
	document.body.style.cursor="default"; 
	for (var i in buttons ){
		var button=buttons[i];
		if(button&&button.style){
			button.style.cursor="default";
		}						
	}

}

//绘制层

function hand(){
	console.log("hand  onmousedown");
	beginDraw("hand");
}

/*文字*/

function writeText(){
	console.log("writeText  onmousedown  写字定位不准 还有BUG....");
	beginDraw(RtText);
}

function cleanText(){

}

/*钢笔*/
function drawPen() {
	console.log("drawPen  onmousedown");				
	beginDraw(RtPen);
}


/*矩形*/
function drawRect() {
	console.log("drawRect  onmousedown");
	beginDraw(RtRect);
}

/*圆角矩形*/
function drawRectRound() {
	console.log("drawRectRound  onmousedown");
	beginDraw(RtRectRound);
}


/*直线*/
function drawLine() {
	console.log("drawLine  onmousedown");
	beginDraw(RtLine);

}

/*三角形*/
function drawTriangle(){
	console.log("drawTriangle  onmousedown");
	beginDraw(RtTriangle);

}

/*五角形*/
function drawPentangle(){
	console.log("drawPentangle  onmousedown 五角形还没写好。。。");
	beginDraw(RtPentangle);

}

function drawArrow(){
	
	console.log("drawArrow  onmousedown");
	beginDraw(RtArrow);
}

function drawArrowDouble(){
	
	console.log("drawArrowDouble  onmousedown");
	beginDraw(RtArrowDouble);
}



function drawCircle() {
	console.log("RtCircle  onmousedown");
	beginDraw(RtCircle);

}

function drawEllipse(){
	console.log("drawEllipse  onmousedown");
	beginDraw(RtEllipse);
}

function drawArc(){
	console.log("drawArc  onmousedown");
	beginDraw(RtArc);
}


function drawRule(){
	console.log("drawRule onmousedown");
	
	beginDraw(RtRule);
	selectedFunctionCustoms="RtTriangle,RtText";
	

}


function undo(){
	rtDrawer.undo();
}

function redo(){
	rtDrawer.redo();
}

function del(){
	rtDrawer.delete();
}

function fillColor(){
	var color=document.getElementById("fillColor").value;
	cxt.fillStyle=color;
	rtDrawer.fillColor(color);
	canvas.focus();

}

function strokeColor(){
	var color=document.getElementById("strokeColor").value;
	cxt.strokeStyle=color;
	rtDrawer.strokeColor(color);
	canvas.focus();

}


/*描边线宽*/
function strokeWidth(){
	var width=document.getElementById("strokeWidth").value;
	cxt.lineWidth=width;
	rtDrawer.setLineWidth(width);
	canvas.focus();
}
/*设置秒变线宽*/
function setStrokeWidth(width){
	document.getElementById("strokeWidth").value=width;
	strokeWidth();
}

function fontSize(){
	var size=document.getElementById("fontSize").value;
	var fontSelect=document.getElementById("fontType");
	var index=fontSelect.selectedIndex;
	var type=fontSelect.options[index].value;
	var fontStyle=size+"px "+type;
	cxt.font=fontStyle;
	rtDrawer.setFontStyle(fontStyle);
	canvas.focus();
}

function fontType(){
	var size=document.getElementById("fontSize").value;
	var fontSelect=document.getElementById("fontType");
	var index=fontSelect.selectedIndex;
	var type=fontSelect.options[index].value;
	var fontStyle=size+"px "+type;
	cxt.font=fontStyle;
	rtDrawer.setFontStyle(fontStyle);
	canvas.focus();

}
/*实线虚线*/
function lineDashType(){
	var lineDashValue=document.getElementById("lineDashType").value;
	//alert(lineDashValue);
	if(!lineDashValue){
		cxt.setLineDash([]);
		rtDrawer.setLineDashType([]);
	}else{
		cxt.setLineDash(lineDashValue.split(","));
		rtDrawer.setLineDashType(lineDashValue.split(","));
	}
	
	canvas.focus();
}
function setLineDashType(dashType){
	document.getElementById("lineDashType").value=dashType;
	lineDashType();
}
 

function copy(){
	rtDrawer.copy();
}

function paste(){
	rtDrawer.paste();
}

function savebak(type){
	if(type=='img'){
		  var save_href = document.getElementById("save_href");
		   var img= document.getElementById("save_img");
		   
		   //var img= new Image();
		   
		  var tempSrc = canvas.toDataURL("image/png");
		//  img.src=tempSrc;
		 // img.setAttribute("crossOrigin",'Anonymous')
		  //var test=	document.getElementById("test");
		//test.innerHTML=img;
		 save_href.href=tempSrc;
		  save_href.click();
		  
		  
	
	}else if(type=='file'){
		var json=rtDrawer.exp();
		alert("打印到控制台了,要去复制。。");
	}
}

function leftAlign(){
	rtDrawer.moveAlign('leftAlign');
}
function rightAlign(){
	rtDrawer.moveAlign('rightAlign');
}


function topAlign(){
	rtDrawer.moveAlign('topAlign');
}
function bottomAlign(){
	rtDrawer.moveAlign('bottomAlign');
}



function horizontallyAlign(){
	rtDrawer.moveCenterAlign('horizontallyAlign');
}
function verticallyAlign(){
	rtDrawer.moveCenterAlign('verticallyAlign');
}
function centerAlign(){
	rtDrawer.moveCenterAlign('centerAlign');
}

function horizontallyAvg(){
	rtDrawer.horizontallyAvg();
}
function verticallyAvg(){
	rtDrawer.verticallyAvg();
}



function group(){			

	rtDrawer.drawingLayer.group();
	
}

function cancelGroup(){
	rtDrawer.drawingLayer.groupCancel();

}

function moveUp(){
	rtDrawer.drawingLayer.moveUp();
}
function moveDown(){
	rtDrawer.drawingLayer.moveDown();
}
function moveTop(){
	rtDrawer.drawingLayer.moveTop();
}
function moveBottom(){
	rtDrawer.drawingLayer.moveBottom();
}
//取消撤销
function redo(){
	rtDrawer.redo();
}
//撤销
function undo(){
	rtDrawer.undo();
}
//锁定  ZQ ADD 20190310
function locked(){
	rtDrawer.locked();
}

//锁定  ZQ ADD 20190310
function unlocked(){
	rtDrawer.unlocked();
}

function selectPrimitive(id){
	rtDrawer.selectPrimitive(id);
	
}

//新建
function newPage(){
	var layers=rtDrawer.layers;
	if(layers&&layers.length>0){
		var r=confirm("确定已保存当前画板了吗？")
	    if (r==true)
	    {	
	    	document.getElementById("handButton").click();
	    	rtDrawer.newPage();
	    }
		
	}	
}

//保存
function save(){
	 
	 var drawData = rtDrawer.exp();
	 
	 var imgCanvas = document.getElementById('imgCanvas'); 
	 var height=$(".center").height();
     var width=$(".center").width();
     imgCanvas.height=height;
     imgCanvas.width=width;
	 var imgContext = imgCanvas.getContext('2d'); 
	 var imgRtDrawer = new RtDrawer(imgCanvas);
	 imgRtDrawer.imp(drawData);
	 var imgData  = imgCanvas.toDataURL("image/png");
	// debugger
	// imgContext.lineCap ='round'; 
	 //用白色背景填充
	 imgContext.save(); 
	 imgContext.fillStyle ='#fff'; 
	 imgContext.fillRect(0,0,imgContext.canvas.width,imgContext.canvas.height); 
	 imgContext.restore(); 
	 //console.log("打印到控制台了,要去复制。。");
	 var result={};
//	 result.imgData=imgData;
//	 result.drawData=drawData;
	 var imageId=document.getElementById("imageId").value;
	 $.ajax({
			url : "com/rtd/files/saveAuto.do",
			type : 'post',
			async:false,
			dataType : "json",
			data : {
				fkAttIdImg:imageId,
				imgData:imgData,
				drawData:drawData
			},
			success : function(response) {
				if (response.success) {
					var rtdFile=response.data;
					imageId=rtdFile.fkAttIdImg;
					console.log("imageId:"+imageId);
				}
			}
		});
	 result.imageId=imageId;
	 console.log("end imageId:"+imageId);
	 return result;
	 
}

function saveDataOnly(){
	 
	 var drawData = rtDrawer.exp();
	 
	 var imgCanvas = document.getElementById('imgCanvas'); 
	 var height=$(".center").height();
    var width=$(".center").width();
    imgCanvas.height=height;
    imgCanvas.width=width;
	 var imgContext = imgCanvas.getContext('2d'); 
	 var imgRtDrawer = new RtDrawer(imgCanvas);
	 imgRtDrawer.imp(drawData);
	 var imgData  = imgCanvas.toDataURL("image/png");
	// debugger
	// imgContext.lineCap ='round'; 
	 //用白色背景填充
	 imgContext.save(); 
	 imgContext.fillStyle ='#fff'; 
	 imgContext.fillRect(0,0,imgContext.canvas.width,imgContext.canvas.height); 
	 imgContext.restore(); 
	 //console.log("打印到控制台了,要去复制。。");
	 var result={};
	 result.imgData=imgData;
	 result.drawData=drawData;
	 return result;
}


//
function importComponent(data){
	 rtDrawer.importComponent(data);
	 document.getElementById("handButton").click();
}
//
function importPage(data){
	 rtDrawer.imp(data);
	 document.getElementById("handButton").click();
}


//=======================

function drawBackground() {
	drawGrid(cxt, GRID_LINE_COLOR, 10, 10);
}
//必须
drawBackground();