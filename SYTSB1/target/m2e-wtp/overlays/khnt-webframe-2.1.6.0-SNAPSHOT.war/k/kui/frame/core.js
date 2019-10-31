
var startTime,endTime;
var d1=new Date();
startTime=d1.getTime();
var IS_AM;//是否管理员

//alert(document.getElementsByTagName("base")[0]);
var kFrameConfig=kui=window['__systemParams'];

//=======================================================================================
if (typeof jQuery == 'undefined') {
	document.write(unescape('%3Cscript type="text/javascript" src="'+kui["kuiBase"]+'/kui/frame/jquery.min.js"%3E%3C/script%3E'));
	document.write(unescape('%3Cscript type="text/javascript" src="'+kui["kuiBase"]+'/kui/frame/jquery.easing.js"%3E%3C/script%3E'));
}
//document.write(unescape('%3Cscript type="text/javascript" src="'+kui["kuiBase"]+'/kui/frame/extjs/ext-base.js"%3E%3C/script%3E'));
//document.write(unescape('%3Cscript type="text/javascript" src="'+kui["kuiBase"]+'/kui/frame/extjs/ext-all.js"%3E%3C/script%3E'));
if (typeof swfobject == 'undefined') {
	document.write(unescape('%3Cscript type="text/javascript" src="'+kui["kuiBase"]+'/kui/frame/swfobject.js"%3E%3C/script%3E'));
}
//=======================================================================================

//=======================================================================================
//此文件直接放入页面引用，因各系统可自行配置文件位置 2014年09月19日 13:43:43 星期五 lybide
//document.write(unescape('%3Cscript type="text/javascript" src="k/kui/frame/config.js"%3E%3C/script%3E'));//载入系统配置、用户配置项
//=======================================================================================

