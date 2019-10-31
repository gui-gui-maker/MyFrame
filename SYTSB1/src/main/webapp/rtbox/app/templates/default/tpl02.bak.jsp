<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>[$RtPageTitle]</title>
<%@include file="/rtbox/public/base.jsp"%>
<link href="rtbox/app/templates/default/supsub.css" rel="stylesheet" type="text/css">
[$RtPageCss]

[$RtPageJs] [$RtPageHead]

<script type="text/javascript">
	var fk_report_id="${param.fk_report_id}";
	var code_ext="${param.code_ext}";
	
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
<script type="text/javascript" src="rtbox/app/templates/default/tpl02.js"></script>
<!-- <script type="text/javascript" src="rtbox/app/templates/default/jquery.js"></script> -->
<script type="text/javascript" src="rtbox/app/templates/default/SelectTool.js"></script>
</head>

<body>
	<div id="layout2" style="width: 99.8%">
		<input type="hidden" id="inputFocus" name="inputFocus" />
		<div position="center"  style="overflow: auto;">
		    <form id="formObj" action="[$RtPageSaveAction]" getAction="[$RtPageGetAction]">
	[$RtPageHidden] 
			<input type="hidden" id="nextPage" name="nextPage" value="[$RtNextPage]"> 
			[$RtPageBody]
	
			</form>
	    </div>
		<div position="right" title="文档结构" class="overflow-auto">
			<input type="button" id="sub" name="sub" value="提交" onclick="submitForm();">
			<input type="button" id="markColor" name="markColor" value="上色" onclick="markColor();">
			<input type="button" id="unmarkColor" name="unmarkColor" value="去色" onclick="unmarkColor();">
			<input type="button" id="sup" name="sup" value="上标" onclick="sup();">
			<input type="button" id="sub" name="sub" value="下标" onclick="sub();">
				<input type="button" id="fontBold" name="fontBold" value="加粗" onclick="fontBold();">
			<input type="button" id="fontItalic" name="fontItalic" value="倾斜" onclick="fontItalic();">
			<select  id="fontSizes" onchange="fontSizes();">
                    <option value="6">5</option><option value="7">5.5</option><option value="8">6.5</option><option value="10">7.5</option>
                    <option value="12">9</option><option value="14">10.5</option><option value="16">12</option><option value="18">14</option>
                    <option value="20">15</option><option value="21">16</option><option value="24">18</option><option value="29">22</option>
                    <option value="32">24</option><option value="34">26</option><option value="48">36</option><option value="56">42</option>
    </select>
    <select  id="fontFamilys" onchange="fontFamilys();">
                    <option value="宋体">宋体</option><option value="黑体">黑体</option><option value="微软雅黑">微软雅黑</option><option value="微软正黑体">微软正黑体</option>
                    <option value="新宋体">新宋体</option><option value="新细明体">新细明体</option><option value="细明体">细明体</option><option value="标楷体">标楷体</option>
                    <option value="仿宋">仿宋</option><option value="楷体">楷体</option>
                    
                    
    </select>
		</div>
	</div>
</body>
</html>