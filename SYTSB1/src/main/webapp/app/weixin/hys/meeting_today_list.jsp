<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://khnt.com/tags/qm" prefix="q" %>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html>
<html>
<head>
	<title></title>
    <%@include file="/app/k/jqm/mobile-base.jsp" %>
    <script src="app/k/jqm/jquery2.js"></script>
	<script src="app/k/jqm/a-main.js" type="text/javascript"></script>
	<link rel="stylesheet" href="app/k/km/lib/kh-mobile.css"/>
	<script src="app/k/km/lib/kh-mobile.js"></script>
	<script src="app/k/km/lib/kh-mobile-list.js"></script>
	<% String id=request.getParameter("id");
    if(id==null){
    	id=(String)session.getAttribute("id");
    }
%>
<script type="text/javascript">
Qm.listClick=function(e,sThis){
	var selectedId = sThis.find('input[name="id"]').val();
	var status = sThis.find('input[name="status"]').val();
	var dj_peoppleid = sThis.find('input[name="dj_peoppleid"]').val();
	window.parent.location.href="${pageContext.request.contextPath}/app/weixin/hys/meeting_detail.jsp?id="+selectedId+"&status="+status+"&dj_peoppleid="+dj_peoppleid+"&eId="+'<%=id%>';
	<%-- location.href="${pageContext.request.contextPath}/app/weixin/hys/meeting_detail.jsp?id="+selectedId+"&status="+status+"&dj_peoppleid="+dj_peoppleid+"&eId="+'<%=id%>'; --%>
};
</script>
	
</head>
<body >
<div class="km-list">
<ul id="__qm_list" class="qm-list">

</ul>
</div>
<q:km pageid="TJY2_HYS_LIST">
</q:km>
</body>
</html>