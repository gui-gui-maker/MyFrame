<%@page import="com.khnt.utils.StringUtil" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="com.khnt.security.CurrentSessionUser" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	String code = ((CurrentSessionUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getDepartment().getAreaCode();
	String id = "";
	id = request.getParameter("id");
	String StepNo = "";
	if (StringUtil.isNotEmpty(id)) {
		Cookie cookies[] = request.getCookies();
		Cookie tmpCookie = null;
		for (int i = 0; i < cookies.length; i++) {
			tmpCookie = cookies[i];
			if (tmpCookie.getName().equals(id)) {
				StepNo = tmpCookie.getValue();
			}
		}
	}
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="main_head">
<title>低保新申请</title>
<%@include file="/k/kui-base-form.jsp" %>

<script type="text/javascript">
//引入组件
loadComp("nav-process");
</script>

<script type="text/javascript">
    var nav;
	$(function () {
		var options = {
			//readerTo为可选参数，表示导航的渲染位置，以下为默认值，设置后默认被覆盖
//            readerTo: "#nav1" ,
//           返回false取消点击上一步
//            beforePrevClick: function () {
//                return true
//            },
//            返回false取消点击下一步
			beforeNextClick: function (index, id) {
				alert(index);
				alert(id);
				return true
			},
//            返回false取消上边导航的点击
//            beforeClick: function () {
//                return true
//            },
			//buttons为可选参数，以下为默认值，设置后默认被覆盖
//            buttons: [
//                {text: "上一步", icon: "nav-prev", id: "prev", click: prevClick},
//                {text: "下一步", icon: "nav-next", id: "next", click: nextClick}
//            ],
			//extButtons表示在默认button后追加
			extButtons: [
				{text: "关 闭", icon: "close", click: function () {
					api.close();
				}}
			],
			//pages的每组数据里都可以设置buttons或extButtons
			pages: [
				{title: "家庭基本资料", id: "nav1", selected: true, url: "dibao/home_new_detail.jsp?pageStatus=${param.pageStatus}"},
				{title: "家庭成员信息", id: "nav2", url: "dibao/home_user_detail.jsp?pageStatus=${param.pageStatus}&id=<%=id%>"},
				{title: "家庭赡抚扶养信息", id: "nav3", url: "dibao/home_support_detail.jsp?pageStatus=${param.pageStatus}&id=<%=id%>"},
				{title: "家庭财产信息", id: "nav4", url: "dibao/home_property_detail.jsp?pageStatus=${param.pageStatus}&id=<%=id%>"},
				{title: "家庭房产信息", id: "nav5", url: "dibao/home_house_detail.jsp?pageStatus=${param.pageStatus}&id=<%=id%>"},
				{title: "证明材料", id: "nav6", url: "dibao/home_data_detail.jsp?pageStatus=${param.pageStatus}&id=<%=id%>", extButtons: [
					{text: "完成", click: function () {
						nav.clearCookie()
					}}
				]
				}
			]
		}
		nav = new Nav(options, "tt");

	});
	function add(i) {
		nav.addPage(i, {title: "家庭基本资料", id: "nav8", url: "dibao/home_new_detail.jsp?pageStatus=${param.pageStatus}"});
	}
	function del(i) {
		nav.removePage(i);
	}
</script>

</head>
<body>
    <input type="button" value="动态增加" onclick="add(6)" style="position: absolute">
    <input type="button" value="动态删除" onclick="del(6)" style="position: absolute;left: 80px">
	<div class="item-tm">
		<div id="nav1"></div>
	</div>
	<div class="scroll-tm"><iframe id="rightFrame" name="rtree" src="" width="100%" height="100%" scrolling="auto" frameborder="0" border="0"></iframe></div>
	<div class="toolbar-tm">
		<div class="toolbar-tm-bottom">
			<div id="toolbar1" style="text-align:center;"></div>
		</div>
	</div>

</body>
</html>
