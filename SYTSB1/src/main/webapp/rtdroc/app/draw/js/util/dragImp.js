/*
*通过拖拽本地文件导入
*/

document.ondragover = function (e) {
	e.preventDefault();  // 只有在ondragover中阻止默认行为才能触发 ondrop 而不是 ondragleave
};
document.ondrop = function (e) {
	e.preventDefault();  // 阻止 document.ondrop的默认行为 *** 在新窗口中打开拖进的图片
};
// 导入画图文件
var canvasContainer = document.getElementById("canvasContainer");
if(canvasContainer){
	canvasContainer.addEventListener("dragover", function(event) {
		event.preventDefault();
	}, false);

	// 拖拽结束时触发
	canvasContainer.addEventListener("drop", function(event) {
		// 拖拽（转移）的对象列表
		event=event||window.event;
		var list = event.dataTransfer.files;
		
		// for (var i = 0; i < list.length; i++) {
			var f = list[0];
			reader(f);
		// }
		
	}, false);
}


// 此事件是必须的，且要阻止默认事件
canvas.addEventListener("dragover", function(event) {
	event.preventDefault();
}, false);

// 拖拽结束时触发
canvas.addEventListener("drop", function(event) {
	// 拖拽（转移）的对象列表
	event=event||window.event;
	var list = event.dataTransfer.files;
	
	// for (var i = 0; i < list.length; i++) {
		var f = list[0];
		reader(f);
	// }
	
}, false);

 function reader(f) {
	var reader = new FileReader();
	if(/rtd\/\w+/.test(f.type)){
        reader.readAsText(f);
		reader.onload = function () {
		   console.log("read file:");	   
		    rtDrawer.imp(reader.result);		
		}  
      
    }else if(/image\/\w+/.test(f.type)){
//    	var fileMaxSize = 700;// 1M
//    	var fileSize =f.size;
//        var size = fileSize / 1024;
//        if (size > fileMaxSize) {
//            alert("请不要大于1M！");
//            return false;
//        }
        reader.readAsDataURL(f);
        /*
		 * reader.onload = function(e){ var img=document.createElement("img");
		 * img.src="http://baike.baidu.com/cms/rc/240x112dierzhou.jpg";
		 * d1.appendChild(img); document.body.appendChild = '<img
		 * src="'+this.result+'" width="0"/>'; }
		 */

        reader.onload=function(e){
        	var rtImage=new RtImage(rtLayer,new Point(0,0),true);
			rtImage.draw(e.target.result);
          /*
			 * var img=new Image(); img.onload=function(){
			 * cxt.drawImage(this,0,0); //图片绘制和初始化 } img.src=e.target.result;
			 */
		}
		reader.onerror=function(e){
		  // 异常处理

		}
	
    }else{
    	alert("无效文件");
    }

	
}
 
