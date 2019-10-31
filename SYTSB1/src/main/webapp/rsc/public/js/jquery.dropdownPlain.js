$(function(){

    $("ul.dropdown li").hover(function(){
    
        $(this).addClass("hover");
        //$('ul:first',this).css('visibility', 'visible');
        $('ul:first',this).show();//fadeIn
    
    }, function(){
    
        $(this).removeClass("hover");
        //$('ul:first',this).css('visibility', 'hidden');
		$('ul:first',this).hide();
    
    });
    
    //$("ul.dropdown li ul li:has(ul)").find("a:first").append(" &raquo; ");
	$("ul.dropdown li ul li:has(ul)").find(":parent:first").append(' <div class="ext">>></div> ');

});