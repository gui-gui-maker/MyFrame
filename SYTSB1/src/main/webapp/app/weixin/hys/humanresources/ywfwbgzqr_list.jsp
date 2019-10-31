<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://khnt.com/tags/qm" prefix="q" %>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<% 
String id6=request.getParameter("id");
if(id6==null){
	id6=(String)session.getAttribute("id");
}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>
<title>会议室申请信息</title>
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no"/>
<meta name="apple-mobile-web-app-capable" content="yes"/>
<meta name="apple-mobile-web-app-status-bar-style" content="black"/>
<link rel="stylesheet" href="k/km/lib/kh-mobile.css"/>
<script src="k/jqm/jquery2.js"></script>
<script src="k/km/lib/kh-mobile.js"></script>
<script src="k/km/lib/kh-mobile-list.js"></script>

<script type="text/javascript">
//Qm.config.pageid="qm_mb_01";

Qm.listClick=function(e,sThis){
	//alert(sThis.find('input[name="id"]').val());
	var selectedId = sThis.find('input[name="id"]').val();
	
	window.location.href("${pageContext.request.contextPath}/app/weixin/hys/humanresources/ywfwbgzqr_detail.jsp?id="+selectedId)
	
}

</script>


</head>
<body>
<%=id6 %>
<div id="wrapper" class="wrapper">
	<section id="reg1" class="">
		<div class="container">

			<div class="page-panel" id="m-page-panel">
				<div class="km-list">
					<ul id="__qm_list" class="qm-list">

					</ul>
				</div>
			</div>
			<%--<div id="navigation" align="center">         <!-- 页面导航-->  --%>
				<%--<a id="pa1" href="a.jsp?page=1">下一页</a>        <!-- 此处可以是url，可以是action，要注意不是每种html都可以加，是跟当前网页有相同布局的才可以。另外一个重要的地方是page参数，这个一定要加在这里，它的作用是指出当前页面页码，没加载一次数据，page自动+1,我们可以从服务器用request拿到他然后进行后面的分页处理。-->--%>
			<%--</div> --%>
			<!--<div class="bt1">
				<div class="text-center row">
					<div class=""><a id="next1" class="button button-block button-rounded button-primary">下一步</a></div>
				</div>
			</div>-->
		</div>
	</section>
</div>
<q:km pageid="TJY2_YWFWBGZQRB">
<q:param name="<%=id6 %>" value="" compare="="/>
</q:km>
</html>
<script type="text/javascript" src="k/km/lib/app-end.js"></script>
