$(document).ready(function(){


	    //动态添加trade_count ul的宽度
    var count_ul = $(".sb_count ul");
	var count = $(".sb_count")
    for(var iUl = 0; iUl<count_ul.length; iUl++){
      var iLength = ($(".sb_count ul").eq(iUl).find('li')).length;
      $(".sb_count ul").eq(iUl).width(iLength * 40 + 25);
	  
	  $(".sb_count").eq(iUl).width(iLength * 40 + 25);

    }
	
	
	
    //
    //end
	
	
	

    // 无缝滚动
    //
    var todaytrade_ul = $('.showTable').html();
    todaytrade_ul += todaytrade_ul;
    $('.showTable').html(todaytrade_ul);

    var iTop=0;

    $(".showTable").mouseenter(function(){
      clearInterval(slideTime);

    });
    $(".showTable").mouseleave(function(){
      clearInterval(slideTime);
      slideTime = setInterval(slide_show,30);
    });

    var slide_show = function(){
        if(iTop == -($('.showTable').height()/2 - 5)){
            iTop=0;
            $('.showTable').css("top",iTop+"px");
        }
      else{
        iTop = iTop-1;
        $('.showTable').css("top",iTop+"px");
      }
    }
    var slideTime = setInterval(slide_show,30);
    //
    //end

});

