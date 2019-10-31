<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://khnt.com/tags/qm" prefix="q" %>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>
<meta name="apple-mobile-web-app-capable" content="yes"/>
<link rel="stylesheet" href="k/km/lib/kh-mobile.css"/>
<script src="k/jqm/jquery2.js"></script>
<script src="k/km/lib/kh-mobile.js"></script>
<script src="k/km/lib/kh-mobile-list.js"></script>

<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/k/km/lib/app-end.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/bpm/js/util.js"></script>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no"/>
<link rel="stylesheet" href="k/jqm/skins/default.css"/>
<script src="k/jqm/a-main.js" type="text/javascript"></script>
<script src="k/km/lib/kh-mobile-list.js"></script>
<% String id=request.getParameter("id");
    if(id==null){
    	id=(String)session.getAttribute("id");
    }
%>
<script type="text/javascript">
//Qm.config.pageid="qm_mb_01";


Qm.listClick=function(e,sThis){
	//alert(sThis.find('input[name="id"]').val());
	var selectedId = sThis.find('input[name="id"]').val() ;
	location.href="${pageContext.request.contextPath}/app/weixin/qxj/leave_add.jsp?leave_id="+selectedId;
	
};
</script>
<style>
.wrapper{ margin-top:28%;}

</style>

</head>
<body>
<div  id="a-index" class="a-index">
	<div class="header">
		<div class="bg"></div>
		<div class="logo"></div>
		<div class="user">
			<h1>四川省特检院</h1>
			<h2>请休假-申请列表</h2>
		</div>
	</div>
</div>
	
	<div >
		<div id="wrapper" class="wrapper">

			<div class="container">
				<div class="page-panel" id="m-page-panel">
					<div class="km-list">
						<ul id="__qm_list" class="qm-list">
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
<q:km pageid="TJY2_RL_LEAVE_M">
<q:param name="people_id" value="<%=id %>" compare="like"/>
</q:km>
</html>
