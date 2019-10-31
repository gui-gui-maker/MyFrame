var bowls;
(function($) {
  $.fn.myScroll = function(options) {
    //默认配置
    var defaults = {
      speed: 40,
      //滚动速度,值越大速度越慢
      rowHeight: 24 //每行的高度
    };
    var opts = $.extend({}, defaults, options),
      intId = [];

    function marquee(obj, step) {
      obj.find("ul").animate({
        marginTop: '-=1'
      }, 0, function() {
        var s = Math.abs(parseInt($(this).css("margin-top")));
        if (s >= step) {
          $(this).find("li").slice(0, 1).appendTo($(this));
          $(this).css("margin-top", 0);
        }
      });
    }

    this.each(function(i) {
    						var sh = opts["rowHeight"],speed = opts["speed"],_this = $(this);
    						_this.find("ul").stop(true).css("margin-top","0");
    						clearInterval(bowls);
    						bowls = setInterval(function() {
    														if (_this.find("ul").height() <= _this.height()) {
    															clearInterval(bowls);
    														} else {
    															marquee(_this, sh);
    														}
    											}, speed);
					      //滚动被鼠标指向事件
					      _this.hover(function() {
					    	  						clearInterval(bowls);
					      			  }, function() {
					      				  			clearInterval(bowls);
					      				  			bowls = setInterval(function() {
					      				  											if (_this.find("ul").height() <= _this.height()) {
					      				  												clearInterval(bowls);
					      				  											} else {
					      				  												marquee(_this, sh);
					      				  											}
					      				  			}, speed);
					      			  });
    });

  }

})(jQuery);


var resizeTimer=null;
$(window).on("resize",function(){
  /*if (resizeTimer) {
    clearTimeout(resizeTimer)
  }
  resizeTimer = setTimeout(function(){
    window.location.reload();
  },400);*/
});



$(function(){

    //nav-mini切换
    $('#mini').on('click',function(){
        if (!$('.nav').hasClass('nav-mini')) {
            $('.nav').addClass('nav-mini');
        }else{
            $('.nav').removeClass('nav-mini');
        }
    });

  var bb = 0;
  $("#shrink").click(function(){ 
    if(bb==0){
      $("#ms_right").show();
      $(".wen_box").css({"right":"340px"});
      $("#shrink").removeClass("shrink_2");
      $("#shrink").attr("title","隐藏侧边");
      bb=1;
    }
    else if (bb==1) {
      $("#ms_right").hide();
      $(".wen_box").css({"right":"100px"});
      $("#shrink").addClass("shrink_2");
      $("#shrink").attr("title","显示侧边");      
      bb=0;
    }
  });


  $("#w_close_btn").click(function(){
      $(".wen_box").hide();
    });

  var h1=$(window).height()/2-50;
  $("#content-2.content").css({"height":h1+"px"});
  
  

});


$(window).on("resize",function(){
    var h1=$(window).height()/2-50;
    $("#content-2.content").css({"height":h1+"px"}); 
   
    //重启启动滚动
    $("ul.gg-list").height($(window).height()*0.5);
    if(bowls){
    	clearInterval(bowls);
    }
    //startScroll();
});



$(document).ready(function(){

    $(".ta-list-cont").height($("#m-r-list-tab").height()-40);
    var liHeight = $('.ta-list-cont').find('li').eq(0).outerHeight(true);
    $("#demohq_a").myScroll({
      speed: 100,
      //数值越大，速度越慢
      rowHeight:liHeight  //li的高度
    });


   /* $(".tzgglist").height($(window).height()*0.4);
    var liHeight2 = $('.gg-list').find('li').eq(0).outerHeight(true);
    $("#tzgglist").myScroll({
      speed: 100,
      //数值越大，速度越慢
      rowHeight:liHeight2  //li的高度
    });*/



})
function startScroll(){
	$(".tzgglist").height($(window).height()*0.45);
    var liHeight2 = $('.gg-list').find('li').eq(0).outerHeight(true);
    $("#tzgglist").myScroll({
      speed: 100,
      //数值越大，速度越慢
      rowHeight:liHeight2  //li的高度
    });
}

