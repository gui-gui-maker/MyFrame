//定义画布 =============================
var canvas = document.getElementById("canvas");

var cxt = canvas.getContext('2d'); 
//cxt.globalAlpha=0.8;  
 cxt.golbalCompositeOperation="destination-over";  
// alert(cxt);
var width = canvas.width, height = canvas.height;
if (window.devicePixelRatio) {
	//canvas.style.width = width + "px";
	//canvas.style.height = height + "px";
	//canvas.height = height * window.devicePixelRatio;
//	canvas.width = width * window.devicePixelRatio;
//	cxt.scale(window.devicePixelRatio, window.devicePixelRatio);
}

cxt.fillStyle="white";




var selectedFunction="hand";//选中的功能
var selectedFunctionCustoms=null;//选中的自定义的功能
var drawInstance;//正在画的实例
var drawDragging=false;//正在画的是否拖动
var GRID_HORIZONTAL_SPACING = 10,
	GRID_VERTICAL_SPACING = 10,
	GRID_LINE_COLOR = 'rgb(0, 0, 200)'; //画背景布

var selectedShape=false;


var handMoveDrag="undrag";//鼠标手：移动模式1）未拖动，2）拖动选中移动物体(复制) undrag drag
var handMoveType=null;


var drawWriting=false;//是否正在输入文字

var GROUP_INSIDE_BEGIN=false;//准备进入组合内部模式
var GROUP_INSIDE_MODE=false;//组合内部模式
var GROUP_INSIDE_GROUP=null;//当前选中的组合
var GROUP_INSIDE_PRIMITIVE=null;//当前选中的组合内部元素


var GUIDEWIRES=true;//辅助线

function FUNC_SIZE(){
	var value=document.getElementById("fontSize").value;
	if(!value){
		value=12;
	}
	return parseInt(value);
};//字体大小

function FUNC_LINE_WIDTH(){
	var value=document.getElementById("strokeWidth").value;
	if(!value){
		value=1;
	}
	return parseInt(value)-1;
};//线宽


