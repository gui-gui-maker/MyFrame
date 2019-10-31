<%@page import="net.sf.json.JSONObject"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
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
	    String name = request.getAttribute("name").toString();
	    HashMap<String, Object> map = ReportUtil.getNewsN(name,"7","1");
	    String data = map.get("data").toString();
	    String sumC = map.get("sumC").toString();
	    List<JSONObject> comList =  (List<JSONObject>)(request.getAttribute("comList"));
	   // String data = ReportUtil.getNews(name); 
    %>
<title>单位查询列表</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
<link href="app/research/css/common.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="app/weixin/research/css/mui.min.css">
<link rel="stylesheet" type="text/css" href="app/weixin/research/css/app.css" />
<style>
	#searchContain{position:relative;}
	#searchButton{
					position:absolute;
					background-color:initial;
					width:30px;
					height:30px;
					border:0px;
					left:2px;
					top:2px;
				}
				input[type=search]{
				background-color: transparent;
			}
			
	.mui-bar .mui-btn{
		padding: 6px 6px;
		margin-top: 4px;
	}
	.mui-slider-indicator .mui-icon {
		border: 0px;
	}
	.mui-input-row.mui-search .mui-icon-clear {
		  top: 0px; 
	}
	.mui-search:before {
		top: 25px;
	}
	input[type=color], input[type=date], input[type=datetime-local], input[type=datetime], input[type=email], input[type=month], input[type=number], input[type=password], input[type=search], input[type=tel], input[type=text], input[type=time], input[type=url], input[type=week], select, textarea {
		margin-bottom: 0px;
	}
	.mui-btn, button, input[type=button], input[type=reset], input[type=submit] {
		  padding: 6px 0px;
	}
	.mui-bar~.mui-content .mui-fullscreen {
		top: 44px;
		height: auto;
	}
	.mui-pull-top-tips {
		position: absolute;
		top: -20px;
		left: 50%;
		margin-left: -25px;
		width: 40px;
		height: 40px;
		border-radius: 100%;
		z-index: 1;
	}
	.mui-bar~.mui-pull-top-tips {
		top: 24px;
	}
	.mui-pull-top-wrapper {
		width: 42px;
		height: 42px;
		display: block;
		text-align: center;
		background-color: #efeff4;
		border: 1px solid #ddd;
		border-radius: 25px;
		background-clip: padding-box;
		box-shadow: 0 4px 10px #bbb;
		overflow: hidden;
	}
	.mui-pull-top-tips.mui-transitioning {
		-webkit-transition-duration: 200ms;
		transition-duration: 200ms;
	}
	.mui-pull-top-tips .mui-pull-loading {
		/*-webkit-backface-visibility: hidden;
		-webkit-transition-duration: 400ms;
		transition-duration: 400ms;*/
		
		margin: 0;
	}
	.mui-pull-top-wrapper .mui-icon,
	.mui-pull-top-wrapper .mui-spinner {
		margin-top: 7px;
	}
	.mui-pull-top-wrapper .mui-icon.mui-reverse {
		/*-webkit-transform: rotate(180deg) translateZ(0);*/
	}
	.mui-pull-bottom-tips {
		text-align: center;
		background-color: #efeff4;
		font-size: 15px;
		line-height: 40px;
		color: #777;
	}
	.mui-pull-top-canvas {
		overflow: hidden;
		background-color: #fafafa;
		border-radius: 40px;
		box-shadow: 0 4px 10px #bbb;
		width: 40px;
		height: 40px;
		margin: 0 auto;
	}
	.mui-pull-top-canvas canvas {
		width: 40px;
	}
	.mui-slider-indicator.mui-segmented-control {
		background-color: #efeff4;
	}
	table{
		table-layout:fixed;
	}
	td{  
		/* white-space: nowrap; */ 
		 word-wrap:break-word;
		} 
</style>
<script type="text/javascript">
var name = "${name}";
var name1 = encodeURI("${name}");
document.onkeydown=function(event){
    var e = event || window.event || arguments.callee.caller.arguments[0];           
     if(e && e.keyCode==13){ // enter 键
    	 indexSearchClick();
    }
};
	$(function() {
		if("${name}"!=""){
			$("#search").val("${name}")
		}
		var newsData = <%=data %>;
		if(newsData!=null){
			var l = 7;
			if(newsData.length<7){
				l = newsData.length;
			}
			if(newsData.length>0){
				$("#news").append("<div class='title'> <div class='icon_box'><a href='javascript:moreNews()'><p>新闻内容</p></a></div>"+
						" <a href='javascript:moreNews()'><p class='more'>约"+<%=sumC%>+"条结果，更多&gt;&gt;</p></a>"+
						"</div> <div class='Per_info'> <ul class='n_l_box_list' id='newsList'> </ul></div>")
				
			}
			for (var i = 0; i < l; i++) {
				$("#newsList").append("<li><div class='n_l_box_list_bt'><a href='javascript:urlNews(\""+newsData[i].url+"\")' >"+newsData[i].title+"</a></div>"+
						" <div class='n_l_box_time'>"+newsData[i].date+"</div></li>")
				
	            
	          
			}
		}
		
	
	})
	
	function indexSearchClick(){
		if($("#search").val()==""){
			alert("请输入查询条件！")
			return;
		}
		
		window.location.href= $("base").attr("href")+"enterSearchAction/wSearchAll.do?name="+$("#search").val();
		
	}
	
	function detailCom(com_id){
		window.location.href= $("base").attr("href")+"enterSearchAction/wSearchDeviceByCom.do?com_id="+com_id;
	}
	function detailMaintainCom(com_id){
		window.location.href= $("base").attr("href")+"enterSearchAction/wSearchDeviceByMCom.do?com_id="+com_id;
	}
	function detailUser(){
		
		window.location.href= $("base").attr("href")+"enterSearchAction/searchAll.do?name="+name1+"&status=add&view=app/weixin/research/app/w-ser_user_show";
		
	}
	function urlNews(url){
		window.location.href= url;
	/* 	top.$.dialog({
			width : 900,
			height :700,
			lock : true,
			title : "新闻详细信息",
			content : "url:"+url,
			data : {
				"window" : window
			}
		}).max(); */
	}

	
function moreNews(){
	window.location.href= $("base").attr("href")+"app/weixin/research/app/w-more-news.jsp?status=add&name="+name1;
	}
function moreMainTain(){
	window.location.href= $("base").attr("href")+"enterSearchAction/wSearchAll.do?name=${name}&status=add&view=app/weixin/research/app/w-more_usecom_list";
}
function moreUse(){
	window.location.href= $("base").attr("href")+"enterSearchAction/wSearchAll.do?name=${name}&status=add&view=app/weixin/research/app/w-more_maintaincom_list";
}
function detailCloud(){
	window.location.href= $("base").attr("href")+"enterSearchAction/searchAll.do?status=add&name="+name1+"&view=app/weixin/research/app/w-more_cloud_list";
	/* top.$.dialog({
		width : 1024,
		height :800,
		lock : true,
		title : "文件信息",
		content :'url:enterSearchAction/searchAll.do?name='+name1+'&status=add'+
				"&view=app/research/more_cloud_list",
		data : {
			"window" : window
		}
	}).max(); */
}

//加载文件信息
/*
$(function(){
	var n = "${cloadList1C>0||cloadList11C>0||cloadList2C>0}";
	if(n=="true"){
		var m = 0
		var cloadList11 = ${cloadList11};
		var s1=4
		if(cloadList11.length<4-m){
			s1 = cloadList11.length;
		}
			$("#cloadList").append('<div class="title"> <div class="icon_box"><a href="javascript:detailCloud()"><p>资料</p></a></div>'
					+ ' <a href="javascript:detailCloud()" style="width: 350px;text-align: left;">'
					+ ' <p class="more" style="width: 350px;text-align: right;color: black;">'
					+ '约${(cloadList1C-0)+(cloadList11C-0)+(cloadList2C-0)}条结果，更多&gt;&gt;</p></a>'
				  	+' </div>'
				    +'<div class="Per_info">'
					+'<table width="100%" border="0" cellspacing="0" cellpadding="0"'+
					' class="per_tab" style="text-align: left;" id="cloadLists">'
					+'<tr>'
					+'<th>文件名称</th>'
					+'<th style="width: 100px;">下载</th>'
					+'</tr></table></div>	');
		for (var i = 0; i < s1; i++) {
			m++;
			var insp = cloadList11[i];
			var title = insp.infoName;
			var n = title.indexOf(name);
			var l1 = name.length;
			if(n!=-1){
				titleN = title.substring(0,n)+'<p style="color: red;text-align: left;display:inline; ">'+title.substring(n,n+l1)+'</p>'+title.substring(n+l1,title.length);
			}else{
				titleN = title;
			}
			var td = ' <tr  style="text-align: left;">'
					+'<td  style="text-align: left;">';
			if(insp.infoType=='4'){
				td = td + '<a href="javascript:openfolder(\''+insp.id+'\',\''+insp.infoName+'\',\''+insp.resourceType+'\')"> '
				+titleN+'</a>'
			}else{
				td = td + titleN;
			}
			td = td + '</td><td style="width: 100px;text-align: left;">'
		
			if(insp.infoType=='4'){
				td = td + '<a href="javascript:openfolder(\''+insp.id+'\',\''+insp.infoName+'\',\''+insp.resourceType+'\')"> 打开</a>'
			}else{
				td = td + '<a  target="_blank" href="fileupload2/downloadByPath.do?id='+insp.id+'&fileName='+insp.infoName+'">下载</a>'
				if(insp.resourceType=='2'){
					td = td + '/<a href="javascript:showFile(\''+insp.id+'\',\''+insp.infoName+'\',\''+insp.infoType+'\')">预览</a>'
				}
			}
			td = td + '</td><tr>'
			$("#cloadLists").append(td);
		}
		
		 if(m<4){
			var cloadList1 = ${cloadList1};
			var s2=4
			if(cloadList1.length<4-m){
				s2 = cloadList1.length;
			}
			for (var i = 0; i < s2; i++) {
				m++;
				var insp = cloadList1[i];
				var title = insp.infoName;
				var n = title.indexOf(name);
				var l1 = name.length;
				if(n!=-1){
					titleN = title.substring(0,n)+'<p style="color: red;text-align: left;display:inline; ">'+title.substring(n,n+l1)+'</p>'+title.substring(n+l1,title.length);
				}else{
					titleN = title;
				}
				var td = '<tr  style="text-align: left;">'
				+'<td  style="text-align: left;">'+titleN
				+'</td>'
				+'<td style="width: 100px;text-align: left;">'
				+'	<a  target="_blank" href="fileupload2/downloadCompress.do?id='+insp.id+'&proportion=0&fileName='+insp.infoName+'">下载</a>';
				if(insp.resourceType=='2'){
					td = td + '/<a href="javascript:showFile(\''+insp.id+'\',\''+insp.infoName+'\',\''+insp.infoType+'\')">预览</a>';
				}
				td = td + '</td><tr>'
				$("#cloadLists").append(td);
			}
		}
		
		if(m<4){
			var cloadList2 = ${cloadList2};
			var s3=4
			if(cloadList2.length<4-m){
				s3 = cloadList2.length;
			}
			for (var i = 0; i < s3; i++) {
				m++;
				var insp = cloadList2[i];
				var title = insp.infoName;
				var n = title.indexOf(name);
				var l1 = name.length;
				if(n!=-1){
					titleN = title.substring(0,n)+'<p style="color: red;text-align: left;display:inline; ">'+title.substring(n,n+l1)+'</p>'+title.substring(n+l1,title.length);
				}else{
					titleN = title;
				}
				var td = '<tr  style="text-align: left;">'
			+'<td  style="text-align: left;">'+titleN
			+'</td>'
			+'<td style="width: 100px;text-align: left;">'
			+'	<a  target="_blank" href="fileupload2/downloadCompress.do?id='+insp.id+'&proportion=0&fileName='+insp.infoName+'">下载</a>';
			if(insp.resourceType=='2'){
				td = td + '/<a href="javascript:showFile(\''+insp.id+'\',\''+insp.infoName+'\',\''+insp.infoType+'\')">预览</a>';
			}
			td = td + '</td><tr>'
				$("#cloadLists").append(td);
			}
		}
	} 
	 
});
*/
</script>
</head>

<body>
	<header id="header" class="mui-bar mui-bar-nav">
			<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
			<h1 class="mui-title">云检索</h1>
	</header>
	<div class="mui-content">
	<div id="slider" class="mui-slider mui-fullscreen">
		<!-- 搜索框 -->
		<div id="sliderSegmentedControl" class="mui-scroll-wrapper mui-slider-indicator mui-segmented-control mui-segmented-control-inverted">
			<div style="background-color: white;padding-bottom: 5px;padding-top: 5px;">
				<div class="mui-input-row mui-search" style="margin-right: 60px;">
					<input id="search" type="search" class="mui-input-clear" placeholder="输入查询内容" value="">
				</div>
				<button type="button" class="mui-btn" style="position: absolute;top:6px;right: 5px;width: 45px;" onclick="indexSearchClick()">
					查询
				</button>
			</div>
		</div>
		<!-- 内容 -->
		<div class="mui-slider-group" style="margin-top: 10px;">
		<div id="item1mobile" class="mui-slider-item mui-control-content mui-active">
		<div id="scroll1" class="mui-scroll-wrapper">
	<div class="mui-scroll">
			<c:if test="${not empty emp}">
			<div class="search_box">
			   <div class="title">
				  <div class="icon_box"><a href="#" target="_blank"><p>个人信息</p></a></div>
				  
			   </div>
			   <div class="Per_info">
			   		<ul class="n_l_box_list">
							<li>
								<div class='n_l_box_list_bt'>
									<a href="#">姓名：</a>
								</div>
								<div class='n_l_box_time'>${emp.empName}</div>
							</li>
							<li>
								<div class='n_l_box_list_bt'>
									<a href="#">职称：</a>
								</div>
								<div class='n_l_box_time'>${emp.empTitle}</div>
							</li>
							<li>
								<div class='n_l_box_list_bt'>
									<a href="#">籍贯：</a>
								</div>
								<div class='n_l_box_time'>${emp.empNativePlace}</div>
							</li>
							<li>
								<div class='n_l_box_list_bt'>
									<a href="#">政治面貌：</a>
								</div>
								<div class='n_l_box_time'>${emp.empPolitical}</div>
							</li>
							<li>
								<div class='n_l_box_list_bt'>
									<a href="#">身份证号：</a>
								</div>
								<div class='n_l_box_time'>${emp.empIdCard}</div>
							</li>
							<li>
								<div class='n_l_box_list_bt'>
									<a href="#">参加工作时间：</a>
								</div>
								<div class='n_l_box_time'>${fn:substring(emp.joinWorkDate, 0, 10)}</div>
							</li>
							<li>
								<div class='n_l_box_list_bt'>
									<a href="#">电话：</a>
								</div>
								<div class='n_l_box_time'>${emp.mobilePhone}</div>
							</li>
						 	<c:if test="${role=='true'}"> 
								<li>
									<div class='n_l_box_list_bt'>
										<a href="javascript:detailUser();" class="more">查看TA的简历>></a>
									</div>
									<div class='n_l_box_time'></div>
								</li>
							</c:if> 
			        </ul>
			   </div>					
			</div>
			</c:if>
		<c:if test="${comListC>0}">
			<div class="search_box">
			   <div class="title">
				  <div class="icon_box"><a href="#" target="_blank"><p>使用单位信息</p></a></div>
				   <a href="javascript:moreMainTain()"><p class="more">约${comListC}条结果，更多&gt;&gt;</p></a>
			   </div>
		   		<div class="Per_info">
					<ul class="n_l_box_list">
						<c:forEach var="com" items="${comList}"  begin="0" end="${comListC<10?comListC:10}">
							<li>
								<div class='n_l_box_list_bt'>
									<a href="javascript:detailCom('${com.com_id}')" id="${com.com_id}">${com.com_name } </a>
								</div>
								<div class='n_l_box_time'>特种设备数量${com.device_count }台</div>
							</li>
						</c:forEach>
			        </ul>
			   </div>					
			</div>
		</c:if>
		<c:if test="${maintainComListC>0}">
			<div class="search_box">
			   <div class="title">
				  <div class="icon_box"><a href="#" target="_blank"><p>维保单位信息</p></a></div>
				   <a href="javascript:moreUse()"><p class="more">约${maintainComListC}条结果，更多&gt;&gt;</p></a>
			   </div>
			   <div class="Per_info">
	           	<ul class="n_l_box_list">
		            <c:forEach var="com" items="${maintainComList}"  begin="0" end="${maintainComListC<10?maintainComListC:10}">
		            	<li>
							<div class='n_l_box_list_bt'>
								<a href="javascript:detailMaintainCom('${com.com_id}')" id="${com.com_id}">${com.com_name } </a>
							</div>
							<div class='n_l_box_time'>特种设备数量${com.device_count }台</div>
						</li>
					</c:forEach>
	            </ul>
			   </div>					
			</div>
		</c:if>
		<div class="search_box" id="news"></div>
		<div class="search_box" id="cloadList"></div>
		</div>
		</div>
		</div>
		</div>
		</div>
	</div>
</body>
<script src="app/weixin/research/js/mui.min.js"></script>
<script src="app/weixin/research/js/mui.pullToRefresh.js"></script>
<script src="app/weixin/research/js/mui.pullToRefresh.material.js"></script>
<script type="text/javascript">
	mui.init();
	//滚动，固定搜索框
	(function($) {
		//阻尼系数
		var deceleration = mui.os.ios?0.003:0.0009;
		$('.mui-scroll-wrapper').scroll({
			bounce: false,
			indicators: true, //是否显示滚动条
			deceleration:deceleration
		});
		$.ready(function() {
		});
	})(mui);
	 //搜索
	$("#searchButton").bind('click', function(e) {
		indexSearchClick();
	});
</script>
</html>