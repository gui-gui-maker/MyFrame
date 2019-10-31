<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@include file="/k/kui-base-list.jsp" %>
	<title></title>
	<script test="text/javascript">
	$(function () {//jQuery页面载入事件
		pageTitle({to:"#title",text:"页面标题",note:"页面介绍",icon:"k/kui/images/icons/32/places.png",show:true});
	});
	</script>
</head>
<body>

<div class="item-tm">
	<div id="title"></div>
</div>

<div class="scroll-tm">
	<p class="abc1">这里来内容</p>
	<p>这里来内容</p>
	<p>这里来内容</p>
	<p>这里来内容</p>
</div>

</body>
</html>