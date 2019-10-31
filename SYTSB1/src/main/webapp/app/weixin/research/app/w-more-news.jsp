<%@ page import="util.ReportUtil"%>
<%@ page import="java.util.Date"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.khnt.utils.DateUtil"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<!DOCTYPE html>
<html>
<head pageStatus="${param.status}">
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>

    <%@include file="/k/kui-base-list.jsp"%>
    <% 
	    String name = request.getParameter("name").toString();
	  //  String data = ReportUtil.getNews(name); 
    %>
	<title>单位查询列表</title>
	<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<link href="app/research/css/common.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="app/weixin/research/css/mui.min.css">
	<link rel="stylesheet" type="text/css" href="app/weixin/research/css/app.css" />
	<style type="text/css">
	.page-hide{
		display:none;
	}
	.page-display{
		display:block;
	}
	</style>
<script type="text/javascript">
	$(function() {
		if("${name}"!=""){
			$("#search").val("${name}")
		}
	})
	function fillData(news){
		
		if(news!=null){
			var newsObj = $.parseJSON(news);
			var len = newsObj.length;
			if(len>0){
				$("#news").empty();
				$("#news").append("<div class='title'> <div class='icon_box'><a href='#'><p>新闻内容</p></a></div>"+
						"</div> <div class='Per_info'> <ul class='n_l_box_list' id='newsList'> </ul></div>")
				
			}
			
			 for (var i = 0; i < len; i++) {
				$("#newsList").append("<li><div class='n_l_box_list_bt'><a href='javascript:urlNews(\""+newsObj[i].url+"\")' >"+newsObj[i].title+"</a></div>"+
						" <div class='n_l_box_time'>"+newsObj[i].date+"</div></li>");
			} 
		}
	}
	function urlNews(url){
		//alert(url)
		top.$.dialog({
			width : 900,
			height :700,
			lock : true,
			title : "新闻详细信息",
			content : "url:"+url,
			data : {
				"window" : window
			}
		}).max();
	}
</script>
</head>

<body>
	<header id="header" class="mui-bar mui-bar-nav">
			<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
			<h1 class="mui-title">云检索</h1>
	</header>
	<div class="mui-content mui-scroll">
		<div class="search_box" id="news"></div>
		<div class="mui-content-padded" style="width:80%;margin:10px auto;">
				<ul class="mui-pagination mui-pagination-lg">
					
				</ul>
		</div>
	</div>
</body>
<script src="app/weixin/research/js/mui.min.js"></script>
<script src="app/weixin/research/js/mui.pullToRefresh.js"></script>
<script src="app/weixin/research/js/mui.pullToRefresh.material.js"></script>
<script type="text/javascript">
	mui.init();
	//分页
	var data;//新闻数据
	var size = 8;//页标显示数量
	var pageSize = 10;//页容量
	var pages;//总页数
	var page = 1;//初始页
	var name = "<%=name%>";//查询主题
	mui.post('pagination/newsPagination.do',{name:name,page:page,pageSize:pageSize},function(res){
		pages = res.pages;
		data = res.data;
		fillData(data);
		$('.mui-pagination').append('<li class="mui-previous mui-disabled"><a href="#">&laquo;</a></li>');
		for(var i=1;i<=pages;i++){
			if(i==1){
				$('.mui-pagination').append('<li class="mui-active page-display" style="display:inline;"><a href="#">'+i+'</a></li>');
			}else if(i!=1&&i<=size){
				$('.mui-pagination').append('<li class="page-display" style="display:inline;"><a href="#">'+i+'</a></li>');
			}else{
				$('.mui-pagination').append('<li class="page-hide" style="display:none;"><a href="#">'+i+'</a></li>');
			}
		};
		$('.mui-pagination').append('<li class="mui-next"><a href="#">&raquo;</a></li>');
		
	},'json');
	function changePage(size,page,flag){//flag(true next,false previous)
		
			$('.mui-pagination li.page-display').removeClass('page-display').addClass('page-hide').css({'display':'none'});
			var hides = $('.mui-pagination li.page-hide'); 
			if(flag){
				for(var i=0;i<size;i++){
					var text = page+i+1+'';
					$.each(hides,function(index){
						if($(this).find('a').html()==text){
							//alert(this);
							$(this).removeClass('page-hide').addClass('page-display').css("display","inline");
						}
					});
				};
			}else{
				for(var i=0;i<size;i++){
					var text = page-i-1+'';
					$.each(hides,function(index){
						if($(this).find('a').html()==text){
							$(this).removeClass('page-hide').addClass('page-display').css("display","inline");
						}
					});
				};
			}
		
	}
	//分页动作
	(function($) {
		$('.mui-pagination').on('tap', 'a', function() {
			
			var li = this.parentNode;
			var classList = li.classList;
			if (!classList.contains('mui-active') && !classList.contains('mui-disabled')) {
				var active = li.parentNode.querySelector('.mui-active');
				if (classList.contains('mui-previous')) {//previous
					var currentPage = parseInt(active.children[0].innerHTML);
					if(currentPage%size==1&&currentPage/size-1>0){
						changePage(size,currentPage,false);
					}
					if (active) {
						var previous = active.previousElementSibling;
						console.log('previous', previous);
						if (previous && !previous.classList.contains('mui-previous')) {
							$.trigger(previous.querySelector('a'), 'tap');
						} else {
							classList.add('mui-disabled');
						}
					}
				
					
				} else if (classList.contains('mui-next')) {//next
					var currentPage =  parseInt(active.children[0].innerHTML);
					if(currentPage%size==0&&currentPage/size<pages/size){
						changePage(size,currentPage,true);
						
					}
					if (active) {
						var next = active.nextElementSibling;
						if (next && !next.classList.contains('mui-next')) {
							$.trigger(next.querySelector('a'), 'tap');
						} else {
							classList.add('mui-disabled');
						}
					}
					
					
				} else {//page
					//var curPage = li.children[0].innerHTML;
					page = parseInt(this.innerText);
					mui.post('pagination/newsPagination.do',{name:name,pageSize:pageSize,page:page},function(res){
						fillData(res.data);
					},'json');
					active.classList.remove('mui-active');
					classList.add('mui-active');
					var previousPageElement = li.parentNode.querySelector('.mui-previous');
					var nextPageElement = li.parentNode.querySelector('.mui-next');
					previousPageElement.classList.remove('mui-disabled');
					nextPageElement.classList.remove('mui-disabled');
					if (page <= 1) {
						previousPageElement.classList.add('mui-disabled');
					} else if (page >= pages) {
						nextPageElement.classList.add('mui-disabled');
					}
				}
			}
			
		});
	})(mui);
</script>
</html>