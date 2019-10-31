<%@ page import="util.ReportUtil"%>
<%@ page import="java.util.Date"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.khnt.utils.DateUtil"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>
	<%@include file="/k/kui-base-list.jsp"%>
	<title>单位查询列表</title>
	<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<link href="app/research/css/common.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="app/weixin/research/css/mui.min.css">
	<link rel="stylesheet" type="text/css" href="app/weixin/research/css/app.css"/>
	<script type="text/javascript">
	$(function() {
		
	})
	function detailMaintainCom(com_id){
		window.location.href= $("base").attr("href")+"enterSearchAction/wSearchDeviceByMCom.do?com_id="+com_id;
	}
	</script>
</head>

<body>
	<header id="header" class="mui-bar mui-bar-nav">
			<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
			<h1 class="mui-title">云检索</h1>
	</header>
	<div class="mui-content mui-scroll">
		<c:if test="${maintainComListC>0}">
			<div class="search_box">
				   <div class="title">
					  <div class="icon_box"><a href="#" target="_blank"><p>维保单位信息</p></a></div>
				   </div>
				   <div class="Per_info">
		            <ul class="n_l_box_list" >
			            <c:forEach var="com" items="${maintainComList}">
			              <li>
			                <div class="n_l_box_list_bt">
			                  <a href="javascript:detailMaintainCom('${com.com_id}')" id="${com.com_id}">${com.com_name } </a>
			                </div>
			                <div class="n_l_box_time">特种设备数量${com.device_count }台</div>
			              </li>
						</c:forEach>
		            </ul>
				  </div>					
			</div>
		</c:if>
	</div>

</body>
<script src="app/weixin/research/js/mui.min.js"></script>
	<script>
		mui.init({
			swipeBack:true //启用右滑关闭功能
		});
	</script>
</html>