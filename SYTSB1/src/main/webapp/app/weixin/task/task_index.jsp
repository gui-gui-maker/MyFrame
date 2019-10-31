
<!DOCTYPE html>
<html>
<head>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no"/>
<meta name="apple-mobile-web-app-capable" content="yes"/>
<meta name="apple-mobile-web-app-status-bar-style" content="black"/>
<link rel="stylesheet" href="app/k/jqm/skins/default.css"/>
<script type="text/javascript" charset="utf-8" src="app/k/jqm/jquery2.js"></script>
<script type="text/javascript" charset="utf-8" src="app/k/jqm/jquery.mobile.js"></script>
<script src="app/k/jqm/a-main.js" type="text/javascript"></script>
	<script type="text/javascript">
	<%
	String phone = (String)session.getAttribute("Phone");
	String name = (String)session.getAttribute("Name");
	String accessToken = (String)session.getAttribute("AccessToken");
	%>
	</script>
	<script type="text/javascript">
	var id = "";
	$(function() {
		$.ajax({
			url : "${pageContext.request.contextPath}/employee/basic/searchEmployee.do?q=18750939263",
			type : "post",
			datatype : "json",
			contentType: "application/json; charset=utf-8",
	        success : function (data) {
	        	var a = data.data;
	        	id = a[0].id;
	      }
		});
	});
	function tiao(href){
		location.href=href+"?id="+id;
		}
	</script>
</head>
<body>
<div data-role="page" id="a-index" class="a-index">
	<div data-role="header">
		<div class="bg"></div>
		<div class="logo"></div>
		<div class="user">
			<h1>四川特检</h1>
			<h2>工作台帐管理</h2>
			
		</div>
	</div>
	<div data-role="content">
		<div id="a-guide-menu" class="a-guide-menu">
			 <!--<div class="a-menu-nav" id="a-menu-nav"><div class="d-big" id="aMenu_big_nav"><a class="a">菜单主页 &gt; </a></div></div>--> 
			<div class="a-menu">
				<div id="a-menu-loading" class="a-menu-loading" style="display: none;">loading</div>
				<div id="app-menu" class="app-menu"></div>
			</div>
			<div id="a_menu_item_an_ywbl" class="bar bar02">
					<div class="item" onclick="tiao('${pageContext.request.contextPath}/app/weixin/task/gzrw_list.jsp');">
						<div id="aMenu_wb_new" class="a" href="javascript:void(0);"
							menuid="wb_new" url="">
							<div class="icon clr_1" style___="background-color:#787521;">
								<img src="k/kui/images/icons/16/edit.png" alt="">
							</div>
							<div class="bartxt">工作任务列表</div>
						</div>
					</div>
					<div class="item" onclick="tiao('${pageContext.request.contextPath}/app/weixin/task/zdrw_list.jsp');">
						<div id="aMenu_wb_new" class="a" href="javascript:void(0);"
							menuid="wb_new" url="">
							<div class="icon clr_1" style___="background-color:#787521;">
								<img src="k/kui/images/icons/16/control_repeat_blue.png" alt="">
							</div>
							<div class="bartxt">重大任务列表</div>
						</div>
					</div>
					 
				</div>
		</div>
	</div>
	<!--<div data-role="footer">
		<h1>在此处写入页脚文本</h1>
	</div>-->
</div>
</body>
</html>