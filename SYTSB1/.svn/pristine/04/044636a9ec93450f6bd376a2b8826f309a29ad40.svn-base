<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>


 <script src="desk/js/jquery-1.4a2.min.js" type="text/javascript"></script>
<link href="css/default.css" rel="stylesheet" />
<script type='text/javascript' src='../../../js.aq.qq.com/js/aq_common.js'></script>
<script src="js/jquery.js"></script> 
<script src="js/swiper.min.js"></script> 
<script src="js/swiper.animate1.0.2.min.js"></script> 

<script type='text/javascript' >


		$(function(){
			
			showInfo();
			
			
		})
		
		function  showInfo(){
			alert(111);	
			
			$.getJSON("department/basic/getWeixinInfo.do", function(resp){
				if(resp.success){
						
						
						var item;  
	                    $.each(resp.flowData,function(i,result){  
	                    	
	                        item = "<tr><td align='center' width='30%'>"+result[0]+"</td><td align='center' width='22%'>"+result[1]+"</td><td align='center' width='10%'>"+result[2]+"</td><td align='center' width='14%'>"+result[3]+"</td><td align='center' width='10%'>报告领取</tr>";  
	                       
							
							
							
					
						
	                        
	                        
	                        $('#showinfo').append(item);  
	                    }); 
						
					
					alert	
						
					
			  	}else{
			  		$.ligerDialog.error(resp.msg);
			 	}
			})
		}
</script>
</head>
<body>
<div class="wrapper remodal-bg" id="wrapper">

						<table  id='showinfo'>
	    
    					</table>
</div>

<script>  

	  
  var mySwiper = new Swiper ('.swiper-container', {
     direction : 'vertical',
 //  pagination: '.swiper-pagination',
  // paginationClickable : false,
   mousewheelControl : true,
   slidesPerView : 'auto',
	loopedSlides :3,
	autoHeight: true,
   speed: 2000,
            loop: true,
            observer:true,
            observeParents:true,
            autoplayDisableOnInteraction : false,
            autoplay:1500,
   loop: true,
   onInit: function(swiper){
   swiperAnimateCache(swiper);
   swiperAnimate(swiper);
	  },
   onSlideChangeEnd: function(swiper){
	swiperAnimate(swiper);
    },

	
	  
})        




  </script>
</body>
</html>