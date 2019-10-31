//$(function(){
$(window).load(function(){//2014骞�04鏈�13鏃� 22:47:18 鏄熸湡鏃� lybide
	//return;
	$("#loop1>div:first").fadeIn("fast");
	var len1=$("#loop1>div").length;
	var bs=$("#loop1b ul");
	$("#loop1").css("background-image","none");
	for (var i = 0; i < len1; i++) {
		bs.append('<li keys='+i+'></li>');
	}
	var sp1=$("#loop1b").parent();//2014骞�03鏈�31鏃� 17:29:48 鏄熸湡涓€ lybide
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
	//闅忔満
	function GetRandomNum(Min, Max) {
		var Range = Max - Min;
		var Rand = Math.random();
		return (Min + Math.round(Rand * Range));
	}
	var num = GetRandomNum(0, len1-1);
	ll.eq(num).click();

	var loopExe1;
	function goLoop1(){
		//loop1Loop();
		loopExe1=setInterval(function(){
			loop1Loop();//2014骞�04鏈�10鏃� 10:49:22 鏄熸湡鍥� lybide
		},4000);
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
	
});

$(function(){
	$("#loop1>div").hide();
});








/**
 * jQuery jslides 1.1.0
 *
 * http://www.cactussoft.cn
 *
 * Copyright (c) 2009 - 2013 Jerry
 *
 * Dual licensed under the MIT and GPL licenses:
 *   http://www.opensource.org/licenses/mit-license.php
 *   http://www.gnu.org/licenses/gpl.html
 
$(function(){
	var numpic = $('#slides li').size()-1;
	var nownow = 0;
	var inout = 0;
	var TT = 0;
	var SPEED = 5000;


	$('#slides li').eq(0).siblings('li').css({'display':'none'});


	var ulstart = '<ul id="pagination">'+'<div>',
		ulcontent = '',
		ulend = '</div>'+ '</ul>';
	ADDLI();
	var pagination = $('#pagination li');
	var paginationwidth = $('#pagination').width();
	//$('#pagination').css('margin-left',(470-paginationwidth))
	
	pagination.eq(0).addClass('current')
		
	function ADDLI(){
		//var lilicount = numpic + 1;
		for(var i = 0; i <= numpic; i++){
			//ulcontent += '<li>' + '<a href="#">' + (i+1) + '</a>' + '</li>';
			ulcontent += '<li iKey="'+(i)+'">' + '<a href="#">' + (i+1) + '</a>' + '</li>';
		}
		
		$('#slides').after(ulstart + ulcontent + ulend);	
	}

	//pagination.on('click',DOTCHANGE())
	pagination.click(function(){
		DOTCHANGE($(this).attr('iKey'));
	});
	
	function DOTCHANGE(cn){

		var changenow = cn;

		
		$('#slides li').eq(nownow).css('z-index','900');
		$('#slides li').eq(changenow).css({'z-index':'800'}).show();
		pagination.eq(changenow).addClass('current').siblings('li').removeClass('current');
		$('#slides li').eq(nownow).fadeOut(400,function(){$('#slides li').eq(changenow).fadeIn(500);});
		nownow = changenow;
	}
	
	pagination.mouseenter(function(){
		inout = 1;
	})
	
	pagination.mouseleave(function(){
		inout = 0;
	})
	
	function GOGO(){
		
		var NN = nownow+1;
		//clearTimeout(TT);
		
		if( inout == 1 ){
			} else {
			if(nownow < numpic){
			$('#slides li').eq(nownow).css('z-index','900');
			$('#slides li').eq(NN).css({'z-index':'800'}).show();
			pagination.eq(NN).addClass('current').siblings('li').removeClass('current');
			$('#slides li').eq(nownow).fadeOut(400,function(){$('#slides li').eq(NN).fadeIn(500);});
			nownow += 1;

		}else{
			NN = 0;
			$('#slides li').eq(nownow).css('z-index','900');
			$('#slides li').eq(NN).stop(true,true).css({'z-index':'800'}).show();
			$('#slides li').eq(nownow).fadeOut(400,function(){$('#slides li').eq(0).fadeIn(500);});
			pagination.eq(NN).addClass('current').siblings('li').removeClass('current');

			nownow=0;

			}
		}
		TT = setTimeout(GOGO, SPEED);
	}
	
	TT = setTimeout(GOGO, SPEED); 

})*/