<!doctype html>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<head>
<meta charset="UTF-8"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="renderer" content="webkit">
<link href="css/default.css" rel="stylesheet" />
  <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>
 <%@include file="/k/kui-base-list.jsp"%>
<script type='text/javascript' >
setInterval("refreshData()",1000*60)
$(function() {
	//1000*60
	
})

function refreshData(){
	$.post("department/basic/getWeixinInfo.do",function(res){
		if(res.success){
			var data = res.flowData;
			var htmll = "";
			for (var i = 0; i < data.length; i++) {
				var aa = data[i];
			/* 	alert(aa.toString()) */
			/* alert(aa[0]+"---"+aa[1]+"---"+aa[2]+"---"+aa[3]) */
				htmll = htmll +'<div class="swiper-slide swiper-slide-visible swiper-slide-active" style="width:80%; ">'
									+'<div class="item clearfix" data-index="57" style="">'
									+	'<div class="left fl">'
									+	'<div class="avatar"> <img id="userhead_57" src="app/weixininfo/images/default/avatar.png" alt=""> </div>'
									+	'</div>'
									+	'<div class="right">'
										+	'<div class="name">'+aa[0]+'  <span style="margin-left:20px; font-size:25px;" >'+aa[1]+'</span></div>'
										+	'<div class="message" style="font-size:30px;"> '+aa[2]+' </div>'
										+'</div>'
										+'<div class="corner"></div>'
										+'<div class="index"></div>'
									+'</div>'
								+'</div>';
								//#e8f1f9
				htmll = htmll +'<div class="swiper-slide " style="width:100%;">'
						+'<div class="item clearfix" data-index="58" style=" width:80%; float:right; background:#90EE90; ">'
						+'<div class="right1 fr" > <div class="avatar"><img id="userhead_58" src="app/weixininfo/images/default/logo.png" alt=""></div></div>'
						+'<div class="left1">'
						+'<div class="name">四川特检微信公众账号自动回复 </div>'
						+'<div class="message"  style="font-size:30px;"> '+aa[3]+' </div>'
						+'</div>'
						+'<div class="corner"></div>'
						+'</div>'
						+'</div>';
								
				
			}
			//alert(1)
			//$("#itemData").html(htmll);
			$("#itemData").append(htmll);
			var sum1 = res.sum;
			if(sum1==undefined){
				sum1 = '10625';
			}
			 $("#sumC").html("四川特检微信公众号已提供<span style='color:#ffd600;'>"+sum1+"</span>次报告书便民查询")
		/* 	 var mySwiper1 = new Swiper ('.swiper-container', {
			     direction : 'vertical',
			 //  pagination: '.swiper-pagination',
			  // paginationClickable : false,
			   mousewheelControl : true,
			   slidesPerView : 'auto',
				//loopedSlides :data.length,
				autoHeight: true,
			   speed: 2000,
			           // loop: true,
			            observer:true,
			            observeParents:true,
			            autoplayDisableOnInteraction : false,
			            autoplay:1500,
			   //loop: true,
			   onInit: function(swiper){
			   swiperAnimateCache(swiper);
			   swiperAnimate(swiper);
				  },
			   onSlideChangeEnd: function(swiper){
				swiperAnimate(swiper);
			    },

				
				  
			})  */       
 


			
			}
		})

}

</script>
</head>
<body>
<div class="wrapper remodal-bg" id="wrapper">
	<div class="container background_img" id="background_img" style="background-image:url(app/weixininfo/images/default/wxc_bg.jpg)"> </div>
	<div class="container browser_tips hide" id="browser_tips">
		<div class="row clearfix">
			<div class="browser_logo fl"> <img src="app/weixininfo/images/default/logo_black.png"> </div>
			<div class="browser_download">
				<p>您的浏览器对大屏幕上墙效果支持不佳，或者使用的浏览器版本过低，建议更换新版chrome浏览器获得更好的上墙体验</p>
				<a href="http://xiazai.sogou.com/detail/34/8/6262355089742005676.html">点击去下载chrome浏览器 <img src="app/weixininfo/images/default/chrome.png"></a> </div>
		</div>
	</div>
	<div class="container stage">
		<div class="row header clearfix" id="header" style="justify-content: center;">
			<div class="title fl">
			<br/>
			<br/>
				<div align="center"><!-- <span style="margin-left:200px;font-size: 40px;">检验互联网+</span> -->
				<span id="sumC" style="margin-left:200px;font-size: 40px;"></span>
				<br/>
				</div>
				<!-- <div align="center">
					<span id="sumC" style="margin-left:355px;font-size: 40px;"></span>
				</div> -->
			</div>
		</div>
		<div class="row main clearfix">
			<div class="viewport" style="zoom: 1;">
				<div id="message" class="view clearfix">
					<div class="sidebar fl" id="sidebar">
						<div class="sb-wrapper">
							<p class="comment account"><span>特检互联网+</span></p>
							<div class="c-wrapper" id="mask_btn"> <img src="app/weixininfo/images/default/qrcode_for_gh_66df99189817_430.jpg"> </div>
							<p class="comment account"><span>四川特检</span></p>
							<p class="comment"></p>
							<p class="comment"></p>
						</div>
					</div>
					<div id="mask" class="mask hide">
						<div class="mask-wrapper"> <img src="app/weixininfo/images/default/wxc_erm.jpg" width="460" height="460"> </div>
					</div>
					<div id="message_wrapper" class="fl">
						<div class="message-loading-con hide">
							<p>正在加载上墙消息...</p>
							<div class="message-loading-progress">
								<div class="message-loading-progress-bar" style="width: 100%;">100%</div>
							</div>
						</div>
						<div class="message-con swiper-container" id="container_list" style="height: 882px;">
							<div class="swiper-wrapper" style="transform: translate3d(0px, -145px, 0px); transition-duration: 0.5s;"
								id="itemData">
							
							
								
							</div>
						</div>
						<div class="message-con swiper-container hide" id="container_detail" style="height: 882px;">
							<div class="swiper-wrapper"> </div>
						</div>
					</div>
				</div>
			</div>
			<div class="blur"> </div>
		</div>
	</div>
	<div class="container" id="ctrlbar"> </div>
	<div class="gallery-con invisible">
		<div id="comment" class="abp">
			<div id="comment-stage" class="container" style="perspective: 805.536px;"></div>
		</div>
	</div>
</div>
<script src="app/weixininfo/js/jquery.js"></script> 
<script src="app/weixininfo/js/swiper.min.js"></script> 
<script src="app/weixininfo/js/swiper.animate1.0.2.min.js"></script> 
<script>  
$.post("department/basic/getWeixinInfo.do",function(res){
	if(res.success){
		var data = res.flowData;
		var htmll = "";
		for (var i = 0; i < data.length; i++) {
			var aa = data[i];
		/* 	alert(aa.toString()) */
		/* alert(aa[0]+"---"+aa[1]+"---"+aa[2]+"---"+aa[3]) */
			htmll = htmll +'<div class="swiper-slide swiper-slide-visible swiper-slide-active" style="width:80%; ">'
								+'<div class="item clearfix" data-index="57" style="">'
								+	'<div class="left fl">'
								+	'<div class="avatar"> <img id="userhead_57" src="app/weixininfo/images/default/avatar.png" alt=""> </div>'
								+	'</div>'
								+	'<div class="right">'
									+	'<div class="name">'+aa[0]+'  <span style="margin-left:20px; font-size:25px;" >'+aa[1]+'</span></div>'
									+	'<div class="message" style="font-size:30px; "> '+aa[2]+' </div>'
									+'</div>'
									+'<div class="corner"></div>'
									+'<div class="index"></div>'
								+'</div>'
							+'</div>';
							
			htmll = htmll +'<div class="swiper-slide " style="width:100%;">'
					+'<div class="item clearfix" data-index="58" style=" width:80%; float:right; background:#90EE90; ">'
					+'<div class="right1 fr" > <div class="avatar"><img id="userhead_58" src="app/weixininfo/images/default/logo.png" alt=""></div></div>'
					+'<div class="left1">'
					+'<div class="name">四川特检微信公众账号自动回复  </div>'
					+'<div class="message"  style="font-size:30px;"> '+aa[3]+' </div>'
					+'</div>'
					+'<div class="corner"></div>'
					+'</div>'
					+'</div>';
							
			
		}
		$("#itemData").append(htmll);
		var sum1 = res.sum;
		if(sum1==undefined){
			sum1 = '10625';
		}
		  $("#sumC").html("四川特检微信公众号已提供<span style='color:#ffd600;'>"+sum1+"</span>次报告书便民查询")
		  var mySwiper = new Swiper ('.swiper-container', {
		     direction : 'vertical',
		 //  pagination: '.swiper-pagination',
		  // paginationClickable : false,
		   //mousewheelControl : true,
		   slidesPerView : 'auto',
			//loopedSlides :data.length,
			autoHeight: true,
		   speed: 2000,
		 //  mousewheelReleaseOnEdges : true,
		            //loop: true,
		            observer:true,
		            observeParents:true,
		            autoplayDisableOnInteraction : false,
		            autoplay:1500,
		  // loop: true,
		   onInit: function(swiper){
		   swiperAnimateCache(swiper);
		   swiperAnimate(swiper);
			  },
		   onSlideChangeEnd: function(swiper){
			swiperAnimate(swiper);
		    },

			
			  
		})      
		}
	})
  </script>
</body>
</html>