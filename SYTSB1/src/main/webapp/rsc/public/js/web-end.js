//alert("123");
function siteTJ() {//2012年09月12日 星期三 17:04:48 lybide
	var _bdhmProtocol = (("https:" == document.location.protocol) ? "https://" : "http://");
	var str1=unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3Fbc23b66c7d4c41dfa91e050eb41558fa' type='text/javascript'%3E%3C/script%3E");
	//document.write(unescape());
	//alert(str1);
	str1='<div id="tj1" class="tj1" style="display:none;">'+str1+'</div>';
	document.write(str1);
	//document.getElementById("tj1").innerHTML=str1;
}
siteTJ();
$(function(){//jQuery页面载入事件
	$("#tj1").hide();
});