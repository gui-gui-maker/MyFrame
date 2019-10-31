<%@ page contentType="text/html;charset=UTF-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
<base href="<%=basePath %>" /> 
<title>四川省特种设备检验云</title>
<meta name="description" content="">
<meta name="keywords" content="">
<link rel="stylesheet" type="text/css" href="app/gis/view/static_show_devices/css/stylegx.css" media="all" />
<link rel="stylesheet" type="text/css" href="app/gis/view/static_show_devices/css/animate.css" media="all" />
<script src="app/gis/view/static_show_devices/js/jquery.min.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=FDmrHK5fKK7ALGXCHRY4BMoT1ys8XO5U"></script> 
<script type="text/javascript" src="http://api.map.baidu.com/library/AreaRestriction/1.2/src/AreaRestriction_min.js"></script>
<script type="text/javascript" src="app/gis/view/static_show_devices/map.js"></script>
<script type="text/javascript" src="app/gis/view/static_show_devices/complexCustomOverlay.js"></script>
<link href="app/gis/view/static_show_devices/css/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css">
<style type="text/css">
     #cDiv{
     	position: absolute;
     	left: 0;
     	top: 0;
     	width: 100%;
     	height: 100%;
     	background: url(/app/gis/view/static_show_devices/images/bg.jpg);
     	/*background-color: #ff0000;*/
     }
</style>
<script type="text/javascript">

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

});

</script>
<!--[if IE 8]>
<link rel="stylesheet" type="text/css" href="css/style-ie8.css" media="all" />
<![endif]-->

</head>
<body>
<div id="sysMain" class="sysmain systongji">

<!-- nav-left -->
    <div class="nav ">

        <div class="nav-top">
            <div id="mini" class="min-img" ><img src="app/gis/view/static_show_devices/images/mini.png" ></div>
            
        </div>

        <div class="sys_userinfo">
            	<div class="userface"><img src="app/gis/view/static_show_devices/images/img_face.png"></div>
            	<div class="username">
            		<span class="user_t1">李小二 <i class="u_arr"></i>
					<div class="us_info_box">
            		    <a href="#">系统首页</a>
            			<a href="#">退出系统</a>
            		</div>
            		</span>
            		<span class="user_t2">系统管理员</span>
            		            	</div>

        </div>

        <div class="nav-weather"> 
			<div id="date">周二 12月26日 (实时：16℃)</div>
			<div style="height:80px;padding:20px 0;">
				<img id="night" src="">
				<img id="day" src="">
			</div>
			<div>
				<p id="temperature">5 ~ 12℃</p>
				<p id="weather">多云转阴</p>
				<p id="wind">微风3级</p>
			</div>
        </div>


    </div>


	<div class="s-skin-container s-skin-container-default" ></div>
	<div id="systemTitle" class="m-top-logo"> 
		
		<!-- <div id="systemTitleImg" class="m-top-logo-img iconfont icon-icon"></div> -->
		<div class="m-top-logo-txt"><span id="systemTitleText" class="text1"><span class="m-top-logo-img"><img src="app/gis/view/static_show_devices/images/tjy_lo.png"></span>四川省特种设备检验云</span></div>
		<div class="tj-logo-bg"></div>
	</div>
	



	<!--实时地图  start -->
	<!-- <div class="main-map">
		<div id="map1" class="chart-panel" style=" text-align:center; margin-top:100px;  background:url(images/map.png) no-repeat top center; background-size:auto;"> 
		
			<div class="livemap-mark anime-jump" style="left:500.00px;top:343.00px;">

				<a class="livemap-hostname" href="#" target="_blank" >
				  <span>特种设备核准 (审批)</span>
				</a>
				<a class="livemap-avatar" href="#" target="_blank" >
  				<img class="livemap-mark-avatar" src="images/2.gif">
				</a>			
			</div>

			<div class="map_point" style="left:492px;top:280px;">  
			    <div class="dot"></div>  
			    <div class="pulse"></div> 
     			<div class="pulse1"></div>
			</div> 

		</div>
	</div> -->
	<div id="map" class="main-map" style="height:100%;">
	</div>
	<!--总的统计数据-->
	<div class="m-counts">
		<div class="m_counts_bg">
			<div class="m-c-clumn">
				<div class="m-c-icon"><img src="app/gis/view/static_show_devices/images/in_ico01.png"></div>
				<div class="m-c-tit">今年报检</div>
				<div class="m-c-data fco1" id="m-ds1-num">72,334,210</div>
			</div>
			<div class="m-c-clumn">
				<div class="m-c-icon"><img src="app/gis/view/static_show_devices/images/in_ico02.png"></div>
			    <div class="m-c-tit">昨日检验</div>
				<div class="m-c-data fco2" id="m-ds2-num">60,450 </div>
			</div>
			<div class="m-c-clumn">
				<div class="m-c-icon"><img src="app/gis/view/static_show_devices/images/in_ico03.png"></div>
			    <div class="m-c-tit">今日检验</div>
				<div class="m-c-data fco3" id="m-ds3-num">41,178</div>
			</div>
		</div>
	</div>
	
	
	<div class="wen_box">
	    <div class="wen_close" id="w_close_btn"> <i></i></div>
		<div class="wen_tt"><i></i>操作说明</div>
		<div class="wen_cont">
		1. 操作指南做占位字符<br/>
		2. 操作指南做占位字符占位字符<br/>
		3. 操作指南做占位字符占位字符
		</div>
	</div>


<div id="shrink" class="shrink shrink_2" title="隐藏侧边">隐藏侧边</div>
<div id="ms_right" class="ms_right">

	<div class="serch_wrap">
		<div class="tit"><i></i> <span>数据检索</span></div>
		<div class="item_box">
			
			<div class="in_serform">
				<div><span>企业名称： </span><input type="text" /></div>
				<div><span>企业类型： </span><input type="text" /></div>
				<div><span>锅炉类型： </span><input type="text" /></div>
				<div><span>燃吨时间： </span><input type="text" /></div>
				<div><span>燃煤量： </span><input type="text" /></div>
				<div class="btns"><a href="javascript:void(0)" class="btn btn-big">查询</a><a href="javascript:void(0)"  class="btn btn-big btn-gray" >重置</a></div>
			</div>

			
		</div>
	</div>

	<div class="yw_item">
		<div class="tit"><i></i> <span>待处理任务 </span></div>
		<div class="item_box dcl_yw">
			<div id="content-2" class="content_2 content mCustomScrollbar" >
		<ul>
			<li class="mzyw_tabindex">
               <a href="mb-detail.html" class="a-open"> 
                  <div href="mb-detail.html" class=" ywartitle" title=""> 中国特检协会西南地区特检机构年度会议暨《西部特种设备》杂志发行座谈会顺利召开 </div> 
                  <span class="point"> <b> </b> </span>
                  <span class="yw_span"> 12-24 </span>
               </a>
            </li>
            			<li class="mzyw_tabindex">
               <a href="mb-detail.html" class="a-open"> 
                  <div href="mb-detail.html" class=" ywartitle" title=""> 耗材年度供应商比选项目公告 </div> 
                  <span class="point"> <b> </b> </span>
                  <span class="yw_span"> 12-13 </span>
               </a>
            </li>
            			<li class="mzyw_tabindex">
               <a href="mb-detail.html" class="a-open"> 
                  <div href="mb-detail.html" class=" ywartitle" title=""> 关于2017年第九期聚乙烯（PE）焊工培训考试的通知 </div> 
                  <span class="point"> <b> </b> </span>
                  <span class="yw_span"> 12-03 </span>
               </a>
            </li>
            			<li class="mzyw_tabindex">
               <a href="mb-detail.html" class="a-open"> 
                  <div href="mb-detail.html" class=" ywartitle" title=""> 四川省特种设备检验研究院耗材年度供应商比选项目公告 </div> 
                  <span class="point"> <b> </b> </span>
                  <span class="yw_span"> 11-08 </span>
               </a>
            </li>
            			<li class="mzyw_tabindex">
               <a href="mb-detail.html" class="a-open"> 
                  <div href="mb-detail.html" class=" ywartitle" title=""> 四川省质监局2015-2016年度地（市）级政府质量考核工作提出明确要求 </div> 
                  <span class="point"> <b> </b> </span>
                  <span class="yw_span"> 11-22 </span>
               </a>
            </li>
            			<li class="mzyw_tabindex">
               <a href="mb-detail.html" class="a-open"> 
                  <div href="mb-detail.html" class=" ywartitle" title=""> 四川省特种设备检验研究院耗材年度供应商比选项目公告 </div> 
                  <span class="point"> <b> </b> </span>
                  <span class="yw_span"> 11-08 </span>
               </a>
            </li>
            			<li class="mzyw_tabindex">
               <a href="mb-detail.html" class="a-open"> 
                  <div href="mb-detail.html" class=" ywartitle" title=""> 四川省质监局2015-2016年度地（市）级政府质量考核工作提出明确要求 </div> 
                  <span class="point"> <b> </b> </span>
                  <span class="yw_span"> 11-22 </span>
               </a>
            </li>


        </ul>
        </div>


		</div>
	</div>
	


</div>



</div>
<script src="app/gis/view/static_show_devices/js/jquery.mCustomScrollbar.concat.min.js"></script>
<script>
    (function($){
        $(window).load(function(){
            $(".content_2").mCustomScrollbar({

					set_width:"100%",

					scrollButtons:{

						enable:true

					},

					theme:"light-2"

				}
			);
        });
      //加载天气
    	//跨域是浏览器的安全策略.
    	//我现在是jQuery ，jQuery 怎么去解决
    	//jQuery 解决的方式.
    	$.ajax({
    			url:"http://api.map.baidu.com/telematics/v3/weather",
    			type:"get",
    			data:{
    				  location:"成都",
    				  output:'json',
    				  ak:'6tYzTvGZSOpYB5Oc2YGGOKt8'
    			},
    			/*预期服务器端返回的数据类型，假设我现在跨域了，我就改成jsonp 就可以了 */
    			dataType:"jsonp",
    			success:function(data){
    				//百度那边的 数据已经回来，我现在要解析这个数据.
    				var weatherData=data.results[0].weather_data[0];
    				console.log(weatherData);
    				$("#date").html(weatherData.date);
    				$("#night").attr("src",weatherData.nightPictureUrl);
    				$("#day").attr("src",weatherData.dayPictureUrl);
    				$("#temperature").html(weatherData.temperature);
    				$("#weather").html(weatherData.weather);
    				$("#wind").html(weatherData.wind);
    			}
    	});
    })(jQuery);
	

</script>
</body>
</html>
