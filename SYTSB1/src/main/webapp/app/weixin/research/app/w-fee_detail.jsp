<%@ page import="java.util.Date"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ page import="com.khnt.utils.DateUtil"%>
<%@ taglib uri="http://khnt.com/tags/chart" prefix="chart" %>
<!DOCTYPE html>
<html>
	<head pageStatus="${param.status}">
	<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>
	<%@include file="/k/kui-base-list.jsp"%>  
	<title>人员信息</title>
	<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">

	<link rel="stylesheet" type="text/css" href="pub/chart/css/chart.css" />
	<link href="app/research/css/common.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="app/weixin/research/css/mui.min.css">
	<link rel="stylesheet" type="text/css" href="app/weixin/research/css/app.css"/>
	<style type="text/css">
		.mui-content{
			height:100%;
		}
	</style>
<script type="text/javascript">

	$(function() {
		if("${name}"!=""){
			$("#search").val("${name}")
		}
	})
	
	function indexSerachClick(){
		if($("#search").val()==""){
			alert("请输入查询条件！")
			return;
		}
		
		window.location.href= $("base").attr("href")+"enterSearchAction/searchAll.do?name="+$("#search").val();
		
	}
	
	function detailUser(){
		top.$.dialog({
			width : 800,
			height :600,
			lock : true,
			title : "设备信息",
			content : 'url:enterSearchAction/searchAll.do?name=${name}&status=add',
			data : {
				"window" : window,view:'app/research/ser_user_indp_list'
			}
		});
	}
	
	function detailInsp(){
		top.$.dialog({
			width : 900,
			height :700,
			lock : true,
			title : "设备信息",
			content : 'url:enterSearchAction/searchAll.do?name=${name}&status=add'+
					"&view=app/research/ser_user_indp_list",
			data : {
				"window" : window
			}
		});
	}
	
</script>
</head>

<body>
<header class="mui-bar mui-bar-nav">
			<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
			<h1 class="mui-title">报销详情</h1>
		</header>
		
		<div class="mui-content mui-scroll">
		   	<div id="div1" style="width:100%; height:100%; border:hidden;background-color:transparent;" ><br><br><font color="#999999">正在加载图形数据,请稍候...</font>
				<chart:chart chartNum="fee_detail_chart" renderAt="div1"  paramValue="${param.years},${param.user}"/>
			</div>
		</div>


</body>
<script src="app/weixin/research/js/mui.min.js"></script>
<script>
	mui.init();
</script>
</html>