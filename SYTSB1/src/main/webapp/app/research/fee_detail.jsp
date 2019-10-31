<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@page import="java.util.Date"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@page import="com.khnt.utils.DateUtil"%>
<head pageStatus="${param.status}">
 <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>

    <%@include file="/k/kui-base-list.jsp"%>
    
<%@ taglib uri="http://khnt.com/tags/chart" prefix="chart" %>
<link rel="stylesheet" type="text/css" href="pub/chart/css/chart.css" />
<link href="app/research/css/common.css" rel="stylesheet" type="text/css">
<title>人员信息</title>
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

<body style="overflow: auto;">
<div class="s_n_bg2"></div>

	   <div  class="charts">
	   
			<div>
			   	<div id="div1" style="width:100%; height:80%; border:hidden;background-color:transparent;" ><br><br><font color="#999999">正在加载图形数据,请稍候...</font>
					<chart:chart chartNum="fee_detail_chart" renderAt="div1"  paramValue="${param.years},${param.user}"/>
				</div>
			</div>
			
	   </div>


</body>
</html>