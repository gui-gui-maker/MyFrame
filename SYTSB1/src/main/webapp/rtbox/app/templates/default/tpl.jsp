<%@page import="java.util.UUID"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>[$RtPageTitle]</title>
<%@include file="/rtbox/public/base.jsp"%>
[$RtPageCss]
<link href="rtbox/app/templates/default/tpl.css?v=<%=UUID.randomUUID().toString()%>" rel="stylesheet" />
[$RtPageJs] [$RtPageHead]

<script type="text/javascript">
	var fk_report_id="${param.fk_report_id}";
	
	$(function() {
		$("form").ligerForm();
		initForm();
		
		[$RtPageInitJs]
	});
</script>
<script type="text/javascript" src="rtbox/app/templates/default/tpl.js?v=<%=UUID.randomUUID().toString()%>"></script>

</head>
<body>
	<form id="formObj" action="[$RtPageSaveAction]" getAction="[$RtPageGetAction]">
[$RtPageHidden] 
		<input type="hidden" id="nextPage" name="nextPage" value="[$RtNextPage]"> 
		[$RtPageBody]
	</form>
	<input type="button" id="sub" name="sub" value="提交" onclick="submitForm();">
</body>
</html>