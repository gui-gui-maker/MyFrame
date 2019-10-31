//ios
//if (/(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent)) {
// //alert(navigator.userAgent); 
// 
// //add a new meta node of viewport in head node
//	head = document.getElementsByTagName('head');
//	viewport = document.createElement('meta');
//	viewport.name = 'viewport';
//	viewport.content = 'target-densitydpi=device-dpi, width=' + 640 + 'px, user-scalable=no';
//	head.length > 0 && head[head.length - 1].appendChild(viewport);    
// 
//}
//android
//if (/(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent)) {
//  //alert(navigator.userAgent);  
//  window.location.href ="iPhone.html";
//} else if (/(Android)/i.test(navigator.userAgent)) {
//  //alert(navigator.userAgent); 
//  window.location.href ="Android.html";
//} else {
//  window.location.href ="pc.html";
//};



$(function(){

	
//	$('.layout').css('min-height',$(window).height());
	//$('.container').css('min-height',$(window).height());
	

})



var Ohtml = document.documentElement;
getSize();// 页面加载完执行一次

function getSize() {// 获取屏幕的宽度,设定rem的基准值
        var screenWidth = Ohtml.clientWidth; 
        Ohtml.style.fontSize = screenWidth / (750 / 40) + 'px';
}
window.onresize = function() {
        getSize();
}




