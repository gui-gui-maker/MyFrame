
$(function() { 

if (window.PIE) { 

        $('.rounded').each(function() { 

PIE.attach(this); 

        }); 

    } 

});


function box2aa(){
var screenWidth = $(window).width(); 
if(screenWidth <= 1440){

// $(".m-center").css("top","35%");

}

if(screenWidth <= 1024){


 $(".m-top-logo-txt").css({"font-size":"35px","line-height":"35px"});
 $(".scene-logo-bg").css({"width":"640px","margin":"0 0 0 -340px"});
 //$(".m-center").css({"top":"35%","margin":"0 0 0 -450px"});
// $(".m-center-wrap").css("width","900px");
 
}

}

$(window).resize(function(){
	box2aa();
});
$(document).ready(function(){
	box2aa();
});


