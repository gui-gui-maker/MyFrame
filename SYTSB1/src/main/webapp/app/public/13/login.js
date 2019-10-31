
$(window).load(function(){//2014年07月04日 20:34:04 星期五 lybide 移值
	var len1=$("#loop1>div").length;
	var bs=$("#loop1b ul");
	var ll=bs.find("li");

	//随机
	function GetRandomNum(Min, Max) {
		var Range = Max - Min;
		var Rand = Math.random();
		return (Min + Math.round(Rand * Range));
	}
	var num = GetRandomNum(0, len1-1);
	ll.eq(num).click();
})


$(function(){
//jQuery页面所有元素载入后执行事件
//$(window).load(function(){//2014年04月13日 22:47:18 星期日 lybide
	//return;

	/*for (var i = 0, l=imageJson.length; i < l; i++) {
		var item=imageJson[i];
		var str1='<div class="i-main-datu" style="background-image:url('+item["img"]+');"></div>';
		if (BROWSER_INFO.ie && BROWSER_INFO.ieversion<=8) {
			str1='<div class="i-main-datu"><img src="'+item["img"]+'" alt=""/></div>';
		}
		$("#loop1").append(str1);
	}*/


	$("#loop1>div:first").fadeIn("fast");
	var len1=$("#loop1>div").length;
	if (len1<=1) {//2016年7月6日 11:46:14 lybide 解决只有一张图片
		$("#loop1").css("background-image","none");
		$("#loop1>div:first").show("fast");
		return;
	}
	var bs=$("#loop1b ul");
	$("#loop1").css("background-image","none");
	for (var i = 0; i < len1; i++) {
		bs.append('<li keys='+i+'></li>');
	}
	var sp1=$("#loop1b").parent();//2014年03月31日 17:29:48 星期一 lybide
	sp1.css("margin-left","-"+(sp1.width()/2)+"px");

	var loop1bKey=0;
	var ll=bs.find("li");
	var gtLen=ll.length;
	ll.click(function(){
		clearInterval(loopExe1);
		var that=$(this);
		loop1bKey=that.attr("keys");
		ggClick("click");
		goLoop1();
	});

	//picContent(loop1bKey);

	var loopExe1;
	var goLoop1sit=4000;
	if (typeof $("#loop1").attr("sit")!="undefined") {
		goLoop1sit=$("#loop1").attr("sit");
	}
	function goLoop1(){
		//loop1Loop();
		loopExe1=setInterval(function(){
			loop1Loop();//2014年04月10日 10:49:22 星期四 lybide
		},goLoop1sit);
	}


	function loop1Loop() {
		var gt=bs.find("li");
		loop1bKey++;
		if (gtLen==loop1bKey) {
			loop1bKey=0;
		}
		//gt.eq(loop1bKey).click();
		//alert(loop1bKey);
		ggClick("loop");
	}

	function ggClick(){
		var that=ll.eq(loop1bKey);
		//var loop1bKey=that.attr("keys");
		$("#loop1>div").hide();$("#loop1>div").eq(loop1bKey).fadeIn();
		/*if (loop1bKey>0) {
			$("#loop1 div").eq(loop1bKey).prev().animate({width:0},"slow",function(){
				$("#loop1 div").eq(loop1bKey).width(0).show();
				$("#loop1 div").eq(loop1bKey).animate({width:$("#pageMain").width()},"slow");
			});
		} else if (loop1bKey==0) {
			$("#loop1 div").last().hide();
			$("#loop1 div").eq(loop1bKey).width(0).show();
			$("#loop1 div").eq(loop1bKey).animate({width:$("#pageMain").width()},"slow");
		} else if (loop1bKey==gtLen) {
			$("#loop1 div").eq(loop1bKey).width(0).show();
			$("#loop1 div").eq(loop1bKey).animate({width:$("#pageMain").width()},"slow");
		}*/
		ll.removeClass("current");
		that.addClass("current");
	}

	function picContent(keys){
		pageKey=keys;
		$(".js-silder-num > ul > li").removeClass("active");
		$("#js-silder-a-p"+(keys)).addClass("active");
		//console.log(keys);//调试信息
		var list=data["list"];
		var main=$(".js-silder-content");
		var nextkey=keys+1;
		if (nextkey>=list.length) {
			nextkey=0;
		}
		var prevkey=keys-1;
		if (prevkey<0) {
			prevkey=list.length-1;
		}
		var ul=main.find(">ul");
		ul.html("");
		var html='';
		html+='<li id="'+list[prevkey]["id"]+'" style="display:none;"><div><a href="javascript:void(0);"><span><img src="'+list[prevkey]["img"]+'" border="0" alt=""/></span></a></div></li>';
		html+='<li id="js-silder-content-li-'+list[keys]["id"]+'"><div class="img"><a href="'+list[keys]["img"]+'" target="_blank"><span><img src="'+list[keys]["img"]+'" border="0" alt="" height1="'+$(".js-silder-main").height()+'"/></span></a></div>'+(list[keys]["text"]?'<div class="text">'+list[keys]["text"]+'</div>':'')+'</li>';
		html+='<li id="'+list[nextkey]["id"]+'" style="display:none;"><div><a href="javascript:void(0);"><span><img src="'+list[nextkey]["img"]+'" border="0" alt=""/></span></a></div></li>';
		ul.append(html);

	}


});

$(function(){
	$("body,html").addClass("index-body");
	$("#pageMain").addClass("index-main");
	$(".foot").removeClass("p-foot");
	//$("#loop1>div").hide();

	wChange();

	$("#iYDT li").hover(
		function () {
			$(this).find(".txt2").animate({height:130},400);
		},
		function () {
			$(this).find(".txt2").animate({height:0},200);
		}
	);

	$(window).resize(function(){
		wChange();
	});

});
function wChange(){
	//return;
	var dh1=$(window).height();
	var dw1=$(window).width();
	//$(".k-login-layer").height(dh1);
	//$(".k-login-layer").width(dw1);

	var h1=$(".top").height()+$(".toppictm-page").height()+$(".main-new").height()+$(".foot").height()+420;//alert($(".main-menu").height());

	if (h1>dh1) {
		$(".index-main").addClass("o1");
		$("body,html").addClass("show-scroll");
		//$(".intro-txt,.intro-txt-tm").hide();
		//$(".k-login-middle").height(dh1-$(".k-login-top").height()-$(".k-login-foot").height());
	} else {
		$(".index-main").removeClass("o1");
		$("body,html").addClass("show-scroll");
		//$(".intro-txt,.intro-txt-tm").show();
		//$(".k-login-middle").height(450)
	}
}