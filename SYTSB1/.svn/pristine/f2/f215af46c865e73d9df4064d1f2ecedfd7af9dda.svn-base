<%@page import="java.util.UUID"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>[$RtPageTitle]</title>
<%@include file="/rtbox/public/base.jsp"%>
[$RtPageCss]

[$RtPageJs] [$RtPageHead]

<script type="text/javascript">
	var fk_report_id="${param.fk_report_id}";
	var code_ext="${param.code_ext}";
	$("#code_ext").val("${param.code_ext}");
	
	[$RtPageRelColumn]
	
	$(function() {
		//页面布局
		$("#layout2").ligerLayout({
			rightWidth: 150,
			space: 3,
			allowTopResize: false
		});
		
		$("form").ligerForm();
		initForm();
		
		[$RtPageInitJs]
	});
</script>
<script type="text/javascript" src="rtbox/app/templates/default/tpl02.js?v=<%=UUID.randomUUID().toString()%>"></script>
<script type="text/javascript" src="rtbox/app/templates/default/SelectTool.js?v=<%=UUID.randomUUID().toString()%>"></script>
<script type="text/javascript" src="rtbox/app/templates/default/Label.js?v=<%=UUID.randomUUID().toString()%>"></script>
</head>

<body>
	<div id="layout2" style="width: 99.8%">
		<input type="hidden" id="inputFocus" name="inputFocus" />
		 <input type="hidden" id="code_ext" name="code_ext" /> 
		<div position="center"  style="overflow: auto;">
		    <form id="formObj" action="[$RtPageSaveAction]" getAction="[$RtPageGetAction][$RtThisPage]">
	[$RtPageHidden] 
			<input type="hidden" id="nextPage" name="nextPage" value="[$RtNextPage]"> 
			<br/>
			[$RtPageBody]
	
			</form>
	    </div>
	</div>
</body>
</html>