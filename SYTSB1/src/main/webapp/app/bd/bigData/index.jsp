<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en" class="no-js">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<link href='https://fonts.googleapis.com/css?family=PT+Sans:400,700' rel='stylesheet' type='text/css'>

	

	<title>成都质监局大数据分析</title>
	<%@ include file="/k/kui-base.jsp" %>
	<link rel="stylesheet" href="app/bd/bigData/reset.css"> <!-- CSS reset -->
	<link rel="stylesheet" href="app/bd/bigData/animate.css">
	<link rel="stylesheet" href="app/bd/bigData/style.css"> <!-- Resource style -->

		<style type="text/css">
		
		body{ background:#252837 ;  }

		.nav-mini{ width: 0px!important;/* border: 0;*/}
		.nav-mini::before{ border: 0; }
		.nav-mini li { display: none; }


		</style>

</head>

<script type="text/javascript">
	 
	var USERID = "<sec:authentication property="principal.id" htmlEscape="false"/>";
	$(function(){
		/* getMenu(); */
		
	$(".cd-tab").on("click",function(){
			var url = $(this).attr("href");
				$("#cd-iframe").attr("src", url);
			$(this).parent().siblings().children(".selected").removeClass("selected");
			$(this).addClass("selected");
		}) 
		//默认点击第一个tab
		$($(".cd-tab")[0]).click();
		
		//ZQ ADD 20180613 左边导航隐藏事件
		/* $('#mini').hover(function() {
			$("#cd-iframe").css({"padding-left":'90px'});
			$('.cd-side-navigation').removeClass('nav-mini');
		}, function() {
			$("#cd-iframe").css("padding-left",'0px');
			$('.cd-side-navigation').addClass('nav-mini');
		}); */
		
		/* $('#sx').click(function() {
			$("#cd-iframe").css({"padding-left":'0px'});
			$('.cd-side-navigation').addClass('nav-mini');
		}); */
		
		$("#sidbtna").bind("click",function(){	
			if($('.cd-side-navigation').hasClass('nav-mini')){
				$('.sidbtn').css("left","90px");
				$('.cd-side-navigation').removeClass('nav-mini');	
				$("#sidbtna").text("<");	
			}else{
				$('.cd-side-navigation').addClass('nav-mini');
				$('.sidbtn').css("left","0px");
				$("#sidbtna").text(">");	
			}
		});
	})

	
	
	
	function qyQm(data){
		var url = "zjpt/bd/bigData/qy_sydw_list.jsp?area_name=" + data;
		top.$.dialog({/* 
            width: 1500,
            height: $(top).height()>=850?800:$(top).height()-50, */
            lock: true,
            parent: api,
            data : {
   				window : window
   			},
            title: data,
            content: 'url:'+ url
        }).max();
	}

	function scdwQm(){
		var url = "zjpt/bd/bigData/qy_scdw_list.jsp";
		top.$.dialog({/* 
            width: 1500,
            height: $(top).height()>=850?800:$(top).height()-50, */
            lock: true,
            parent: api,
            data : {
   				window : window
   			},
            title: "生产单位",
            content: 'url:'+ url
        }).max();
	}
	
	/*
	 * 读取菜单数据
	 */
	 var iocArray = {
	 "1526544796098": '<path d="M93.090909 512v512h325.818182V768h186.181818v256h325.818182V512h-69.818182v442.181818h-186.181818V698.181818H349.090909v256H162.909091V512H93.090909zM698.181818 139.636364V69.818182h256v232.727273h-69.818182V139.636364h-186.181818zM0 465.454545l46.545455 46.545455L512 93.090909l465.454545 418.909091 46.545455-46.545455L512 0z" fill="#4a5261" p-id="9054"></path>'
	 ,"1526545229610": '<path d="M857.088 208.896c-10.24-9.216-23.04-14.336-36.864-14.336h-3.584c-0.512 0-9.216 0.512-23.04 0.512-22.528 0-66.56-1.536-108.032-11.776-53.76-13.312-115.712-74.24-134.144-86.016-9.216-6.144-19.456-9.216-30.208-9.216-10.752 0-20.992 3.072-30.208 9.216-2.048 1.536-67.072 70.656-130.56 86.016-41.472 10.24-86.528 11.776-109.056 11.776-13.824 0-22.528-0.512-23.04-0.512h-3.584c-13.824 0-27.136 5.12-36.864 14.336-10.752 10.24-16.896 24.576-16.896 39.424v134.144c0 496.64 327.168 560.64 340.992 562.688 3.072 0.512 6.656 1.024 9.728 1.024s6.656-0.512 9.728-1.024c13.824-2.56 343.552-66.56 343.552-562.688V247.808c-1.024-14.848-7.168-29.184-17.92-38.912z m-37.376 173.568c0 457.216-299.008 509.952-299.008 509.952S224.256 839.68 224.256 382.464V247.808s10.24 0.512 26.624 0.512c28.16 0 76.288-2.048 121.856-13.312 74.752-18.432 147.968-93.696 147.968-93.696s76.288 75.264 151.04 93.696c45.568 11.264 92.672 13.312 121.344 13.312 16.384 0 26.624-0.512 26.624-0.512v134.656z" fill="#4a5261"></path><path d="M522.24 289.792c-63.488 0-115.2 47.616-115.2 107.008 0 58.88 51.712 107.008 115.2 107.008s115.2-48.128 115.2-107.008-51.712-107.008-115.2-107.008M560.64 557.056h-76.8c-61.952 0-117.248 27.648-152.576 70.144 66.048 75.776 190.976 117.248 190.976 117.248s124.928-41.472 190.976-117.248c-34.816-42.496-90.112-70.144-152.576-70.144z" p-id="22978" fill="#4a5261"></path>'
	 ,"1526544023870": '<path d="M256 832h512V512a256 256 0 1 0-512 0v320zM192 512a320 320 0 1 1 640 0v384H192V512z m-192 448h1024v64H0v-64zM480 0h64v128h-64V0zM0 416v-64h128v64H0z m896 0v-64h128v64h-128zM137.344 118.656l45.312-45.312 96 96-45.312 45.312-96-96z m704-45.312l45.312 45.312-96 96-45.312-45.312 96-96z" fill="#4a5261"  ></path><path d="M625.28 766.592l77.76-310.848-62.08-15.488-50.304 201.152-192-192-77.696 310.848 62.08 15.488 50.304-201.152z" fill="#4a5261" ></path>'
	 ,"1526545560016": '<path d="M64.4 495.3l362-332.8 212.7 204.3 199.4-228.4-71.4-75.6h192l-0.1 192-67.9-68-252 279.3-212.9-212-315.3 294.6-46.5-53.4zM127.1 635.1h64.1v323.5h-64.1V635.1z" p-id="23671" fill="#4a5261" ></path><path d="M383.1 450.8h64.1v507.7h-64.1V450.8zM638.9 575.7H703v382.8h-64.1V575.7zM894.8 383.3h64.1v575.3h-64.1V383.3z" p-id="23672" fill="#4a5261" ></path>'
	 ,"1526545560016": '<path d="M64.4 495.3l362-332.8 212.7 204.3 199.4-228.4-71.4-75.6h192l-0.1 192-67.9-68-252 279.3-212.9-212-315.3 294.6-46.5-53.4zM127.1 635.1h64.1v323.5h-64.1V635.1z" p-id="23671" fill="#4a5261" ></path><path d="M383.1 450.8h64.1v507.7h-64.1V450.8zM638.9 575.7H703v382.8h-64.1V575.7zM894.8 383.3h64.1v575.3h-64.1V383.3z" p-id="23672" fill="#4a5261" ></path>'};
	 
	function getMenu()
	{
		var url=encodeURI("rbac/user/getUserResources.do?userId="+USERID+"&type=1&m="+Math.random());
		var node,node2,node3,node4,htmlStr;
		$.ajax({
			url: url,
			type: "POST",
			async:false,
			datatype: "json",
			success: function (res) {
				node = jQuery.parseJSON(res);
				for (var i=0;i<node.length;i++)
				{
					if (node[i].code=="root")
					{
						node2 = node[i].children;
						for (var j=0;j<node2.length;j++)
						{
							if (node2[j].code=="cdzj_bigdata")
							{
								node3 = node2[j].children;
								for (var k=0;k<node3.length;k++)
								{
									htmlStr = "";
									htmlStr+=' <li>';
									htmlStr+=' <a class="cd-tab" href="'+ node3[k].url +'" onclick="return false;">';
									htmlStr+='	<svg t="'+node3[k].image+'" class="nc-icon outline" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" width="28" height="28">';
									htmlStr+=	iocArray[node3[k].image];
									htmlStr+='	</svg>';
									htmlStr+=node3[k].text;
									htmlStr+='</a>';
								    htmlStr+='</li>';
								    
									FIRST_NAME=node3[k].text;
									FIRST_URL=node3[k].url;
									$("#nav").append(htmlStr);
								}
							}
						}
					}
				}
			},
			error: function (res) {
				$.ligerDialog.error("菜单信息读取失败！","提示");
			}
		});
	}

</script>
<style>
	.sx{cursor:pointer;}
	.sx:hover{
	    color: #fff;
	    transition: all .4s;
	}
</style>
<body>
	<nav class="cd-side-navigation nav-mini" id="mini">
		<!-- <div id="sx" class="sx" style="position:absolute; right: 10px; top: 50%; padding: 1em 0 1em; font-size: 2rem;">《</div> -->
		
		<ul id="nav">
			 <li    >
				<a  class="cd-tab selected" href="app/gis/scjy/v6/bmtj.jsp"  data-menu="index" onclick="return false;">				
					
					<svg t="1526544796098" class="nc-icon outline" style="" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="9053" xmlns:xlink="http://www.w3.org/1999/xlink" width="28" height="28"><path d="M93.090909 512v512h325.818182V768h186.181818v256h325.818182V512h-69.818182v442.181818h-186.181818V698.181818H349.090909v256H162.909091V512H93.090909zM698.181818 139.636364V69.818182h256v232.727273h-69.818182V139.636364h-186.181818zM0 465.454545l46.545455 46.545455L512 93.090909l465.454545 418.909091 46.545455-46.545455L512 0z" fill="#4a5261" p-id="9054"></path></svg>
					D1
				</a>
			</li> 

			<li>
				<a class="cd-tab " href="app/gis/scjy/v2/bmtj.jsp" data-menu="page2"  onclick="return false;" >
					<svg t="1526545229610" class="nc-icon outline" style="" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="22976" xmlns:xlink="http://www.w3.org/1999/xlink" width="32" height="32">
					<path d="M857.088 208.896c-10.24-9.216-23.04-14.336-36.864-14.336h-3.584c-0.512 0-9.216 0.512-23.04 0.512-22.528 0-66.56-1.536-108.032-11.776-53.76-13.312-115.712-74.24-134.144-86.016-9.216-6.144-19.456-9.216-30.208-9.216-10.752 0-20.992 3.072-30.208 9.216-2.048 1.536-67.072 70.656-130.56 86.016-41.472 10.24-86.528 11.776-109.056 11.776-13.824 0-22.528-0.512-23.04-0.512h-3.584c-13.824 0-27.136 5.12-36.864 14.336-10.752 10.24-16.896 24.576-16.896 39.424v134.144c0 496.64 327.168 560.64 340.992 562.688 3.072 0.512 6.656 1.024 9.728 1.024s6.656-0.512 9.728-1.024c13.824-2.56 343.552-66.56 343.552-562.688V247.808c-1.024-14.848-7.168-29.184-17.92-38.912z m-37.376 173.568c0 457.216-299.008 509.952-299.008 509.952S224.256 839.68 224.256 382.464V247.808s10.24 0.512 26.624 0.512c28.16 0 76.288-2.048 121.856-13.312 74.752-18.432 147.968-93.696 147.968-93.696s76.288 75.264 151.04 93.696c45.568 11.264 92.672 13.312 121.344 13.312 16.384 0 26.624-0.512 26.624-0.512v134.656z" fill="#4a5261"></path>
					<path d="M522.24 289.792c-63.488 0-115.2 47.616-115.2 107.008 0 58.88 51.712 107.008 115.2 107.008s115.2-48.128 115.2-107.008-51.712-107.008-115.2-107.008M560.64 557.056h-76.8c-61.952 0-117.248 27.648-152.576 70.144 66.048 75.776 190.976 117.248 190.976 117.248s124.928-41.472 190.976-117.248c-34.816-42.496-90.112-70.144-152.576-70.144z" p-id="22978" fill="#4a5261"></path>
					</svg>
						D2
				</a>
			</li>

			<li>
				<a class="cd-tab" href="app/gis/scjy/v3/bmtj.jsp" data-menu="page3" onclick="return false;" >
					<svg t="1526544023870" class="nc-icon outline" style="" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" width="28" height="28">
					<path d="M256 832h512V512a256 256 0 1 0-512 0v320zM192 512a320 320 0 1 1 640 0v384H192V512z m-192 448h1024v64H0v-64zM480 0h64v128h-64V0zM0 416v-64h128v64H0z m896 0v-64h128v64h-128zM137.344 118.656l45.312-45.312 96 96-45.312 45.312-96-96z m704-45.312l45.312 45.312-96 96-45.312-45.312 96-96z" fill="#4a5261"  ></path>
					<path d="M625.28 766.592l77.76-310.848-62.08-15.488-50.304 201.152-192-192-77.696 310.848 62.08 15.488 50.304-201.152z" fill="#4a5261" ></path>
					</svg>
					D3
				</a>
			</li>

			<li>
				<a class="cd-tab" href="app/gis/scjy/v4/bmtj.jsp" data-menu="page4" onclick="return false;">
					<svg t="1526545560016" class="nc-icon outline" style="" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="23670" xmlns:xlink="http://www.w3.org/1999/xlink" width="30" height="30"><defs><style type="text/css"></style></defs><path d="M64.4 495.3l362-332.8 212.7 204.3 199.4-228.4-71.4-75.6h192l-0.1 192-67.9-68-252 279.3-212.9-212-315.3 294.6-46.5-53.4zM127.1 635.1h64.1v323.5h-64.1V635.1z" p-id="23671" fill="#4a5261" ></path><path d="M383.1 450.8h64.1v507.7h-64.1V450.8zM638.9 575.7H703v382.8h-64.1V575.7zM894.8 383.3h64.1v575.3h-64.1V383.3z" p-id="23672" fill="#4a5261" ></path></svg>
						D4
				</a>
			</li>
			
			
			<li>
				<a class="cd-tab" href="app/gis/scjy/v5/bmtj.jsp" data-menu="page4" onclick="return false;">
					<svg t="1526545560016" class="nc-icon outline" style="" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="23670" xmlns:xlink="http://www.w3.org/1999/xlink" width="30" height="30"><defs><style type="text/css"></style></defs><path d="M64.4 495.3l362-332.8 212.7 204.3 199.4-228.4-71.4-75.6h192l-0.1 192-67.9-68-252 279.3-212.9-212-315.3 294.6-46.5-53.4zM127.1 635.1h64.1v323.5h-64.1V635.1z" p-id="23671" fill="#4a5261" ></path><path d="M383.1 450.8h64.1v507.7h-64.1V450.8zM638.9 575.7H703v382.8h-64.1V575.7zM894.8 383.3h64.1v575.3h-64.1V383.3z" p-id="23672" fill="#4a5261" ></path></svg>
						D5
				</a>
			</li>
				<li>
				<a class="cd-tab" href="app/gis/scjy/v6/bmtj.jsp" data-menu="page4" onclick="return false;">
					<svg t="1526545560016" class="nc-icon outline" style="" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="23670" xmlns:xlink="http://www.w3.org/1999/xlink" width="30" height="30"><defs><style type="text/css"></style></defs><path d="M64.4 495.3l362-332.8 212.7 204.3 199.4-228.4-71.4-75.6h192l-0.1 192-67.9-68-252 279.3-212.9-212-315.3 294.6-46.5-53.4zM127.1 635.1h64.1v323.5h-64.1V635.1z" p-id="23671" fill="#4a5261" ></path><path d="M383.1 450.8h64.1v507.7h-64.1V450.8zM638.9 575.7H703v382.8h-64.1V575.7zM894.8 383.3h64.1v575.3h-64.1V383.3z" p-id="23672" fill="#4a5261" ></path></svg>
					D6
				</a>
			</li>
		</ul>
		<div class="sidbtn"><a href="javascript:void(0);" id="sidbtna">></a></div>
	</nav> <!-- .cd-side-navigation -->
	
	<main class="cd-main">
		<!-- <section class="cd-section index visible" > -->
            <iframe id="cd-iframe" class="cd-section index visible" width="100%" height="100%" >
            
            </iframe>
			<!-- .cd-content -->
		<!-- </section> --> <!-- .cd-section -->
	</main> <!-- .cd-main -->

</body>
</html>