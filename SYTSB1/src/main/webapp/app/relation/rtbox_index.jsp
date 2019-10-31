<%@page import="com.khnt.rtbox.template.constant.RtPath"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u"%>
<%
	String basePath = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+request.getServletPath().substring(0,request.getServletPath().lastIndexOf("/")+1);   
	String rtPage = request.getParameter("rtPage");
	String rtCode = request.getParameter("rtCode");
	String position = request.getParameter("position");
	String pre_path = "left".equals(position)?RtPath.tplRecordPageDir:RtPath.tplPageDir;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>报表对应工具之报表首页</title>
<%@include file="/rtbox/public/base.jsp" %>

<meta name="keywords" content="报表工具" />
<meta name="description" content="报表工具">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Theme CSS -->
<link rel="stylesheet" type="text/css" href="rtbox/app/templates/default/assets/skin/default_skin/css/theme.css">
<script type="text/javascript">
	var basePath = "<%=basePath%>";
	var pre_path = "<%=pre_path%>";
	var rtPage = "<%=rtPage%>";
	var rtCode = "<%=rtCode%>";
	var position = "<%=position%>";
	var focusInputEl;
	var focusInputId;
	var focusInputName;

	$(function() {
		/* if("left" == position){
			$("#rightFrame").attr("src", pre_path + "/show_right.jsp?code=" + rtCode + "&pageName=" + rtPage);
		}else{
			$("#rightFrame").attr("src", pre_path + rtCode + "/" + rtPage);
		} */
		$("#rightFrame").attr("src", pre_path + "/show_right.jsp?code=" + rtCode + "&pageName=" + rtPage+"&pageCode=${param.pageCode}");
	});

	function getFocusElement() {
		var fsEl = window.rightFrame.focus_element;
		if (fsEl == "") {
			fsEl = focusInputEl;
		}
		return fsEl;
	}

	function getFocusElementId() {
		var fsEl = window.rightFrame.focus_element;
		if (fsEl == "") {
			fsEl = focusInputEl;
		}
		return fsEl.id;
	}

	function getFocusElementName() {
		var fsEl = window.rightFrame.focus_element;
		if (fsEl == "") {
			fsEl = focusInputEl;
			if (fsEl == null || fsEl == "" || fsEl == undefined) {
				fsEl = window.rightFrame.document.getElementById(focusInputId);
			}
		}
		return fsEl.name;
	}

	function getElements(element_type) {
		// document.getElementById('rightFrame').contentWindow
		var fsEl = window.rightFrame.document
				.getElementsByTagName(element_type);
		return fsEl;
	}

	function setCssToFocusElement() {
		var fsEl = window.rightFrame.focus_element;
		if (fsEl == "") {
			focusInputEl = window.rightFrame.document
					.getElementById(focusInputId);
			fsEl = focusInputEl;
		}
		addBorderToElement(fsEl.id);
	}

	function removeCssToFocusElement() {
		var fsEl = window.rightFrame.focus_element;
		if (fsEl == "") {
			fsEl = focusInputEl;
		}
		removeBorderToElement(fsEl.id);
	}

	function setCssToElement(id) {
		addBorderToElement(id);
	}

	function removeCssToElement(id) {
		removeBorderToElement(id);
	}

	function addBorderToElement(id) {
		if(window.rightFrame.document.getElementById(id)!=null){
			//window.rightFrame.document.getElementById(fsEl.id).style.background = "#FFFFCC";
			getIframeDoc().getElementById(id).style.borderStyle = "solid"; // 边框样式
			window.rightFrame.document.getElementById(id).style.borderColor = "#ff0000"; // 边框颜色
			window.rightFrame.document.getElementById(id).style.borderWidth = "2px"; // 边框宽度
		}
	}

	function removeBorderToElement(id) {
		if(window.rightFrame.document.getElementById(id)!=null){
			window.rightFrame.document.getElementById(id).style.borderStyle = ""; // 边框样式
			window.rightFrame.document.getElementById(id).style.borderColor = ""; // 边框颜色
			window.rightFrame.document.getElementById(id).style.borderWidth = ""; // 边框宽度
		}
	}

	function getIframeDoc() {
		var doc;
		if (document.all) { // IE 
			doc = document.frames["rightFrame"].document;
		} else { // 标准
			doc = document.getElementById("rightFrame").contentDocument;
		}
		return doc;
	}

	// 刷新当前框架
	function refreshIframe() {
		//支持IE
		document.getElementById('rightFrame').contentWindow.location.reload();
	}

	function f_batch_s() {
		var fsEl = window.rightFrame.focus_element;
		parent.window.doSignStart(position, fsEl);
	}

	function f_batch_e() {
		var fsEl = window.rightFrame.focus_element;
		parent.window.doSignEnd(position, fsEl);
	}
</script>
</head>
<body class="dashboard-page">	
<input type="hidden" id="inputFocus" name="inputFocus" />		
<iframe id="rightFrame" name="rightFrame" marginwidth="0" marginheight="0" frameborder="0" valign=top src="" name="rtree"
	                width=1024 height=1500 scrolling="no" allowTransparency></iframe>
</body>
</html>
