<%@ page import="com.khnt.utils.DateUtil" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://khnt.com/tags/qm" prefix="q" %>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <%@include file="/k/kui-base-list.jsp" %>
    <script test="text/javascript">
	var toDayDate = '<%=DateUtil.getDateTime("yyyy-MM-dd",new Date())%>';
	$(function () {//jQuery页面载入事件
		$("#toolbar1").ligerToolBar({
			items: [
				"-",
				{id: "detail", icon: "detail", disabled: true, text: "详情"},
				{id: "search", icon: "search", text: "搜索"},
				"-",
				{id: "edit", icon: "edit", text: "编辑"},
				"->",
				"-",
				{icon: "date-bz", text: "本周", click: function () {
					dateNs("bz", "startDate", "endDate", toDayDate);
				}},
				{icon: "date-by", text: "本月", click: function () {
					dateNs("by", "startDate", "endDate", toDayDate);
				}},
				{icon: "date-bjd", text: "本季度", click: function () {
					dateNs("bjd", "startDate", "endDate", toDayDate);
				}},
				{icon: "date-bn", id: "wefwef", text: "本年", disabled: false, click: function () {
					dateNs("bn", "startDate", "endDate", toDayDate);
				}},
				"-",
				{icon: "date-sz", text: "上周", click: function () {
					dateNs("sz", "startDate", "endDate", toDayDate);
				}},
				{icon: "date-sy", text: "上月", click: function () {
					dateNs("sy", "startDate", "endDate", toDayDate);
				}},
				{icon: "date-sjd", text: "上季度", click: function () {
					dateNs("sjd", "startDate", "endDate", toDayDate);
				}},
				{icon: "date-sn", text: "上年", click: function () {
					dateNs("sn", "startDate", "endDate", toDayDate);
				}},
				{icon: "count", text: "开始统计", click: function () {
					alert("统计了吗")
				}},
				"-"
			]
		});
		$("#formObj").ligerForm();
		$("#formObj2").ligerForm();
	});
	</script>
</head>
<body>

<div class="item-tm">
	<div id="toolbar1"></div>
</div>

<div class="item-tm">
	<div id="titleElement" class="l-page-title2 has-icon has-note">
		<div class="l-page-title2-div"></div>
		<div class="l-page-title2-text"><h1>固定资产信息统计</h1></div>
		<div class="l-page-title2-note">以固定资产再生产过程的经济现象为统计对象。</div>
		<div class="l-page-title2-icon"><img src="k/kui/images/icons/32/statistics.png" border="0"></div>
		<div class="l-page-title-content" style="top:15px;height:80px;">
			<form id="formObj" name="formObj">
				<table border="0" cellpadding="0" cellspacing="0" width="" class="l-table1">
					<tr>
						<td height="25" width="80">时间段：从</td>
						<td width="100"><input id="startDate" name="startDate" type="text" value="2013-4-1" ltype="date"/></td>
						<td width="20" align="center">至</td>
						<td width="100"><input id="endDate" name="endDate" type="text" value="2013-4-30" ltype="date"/></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</div>

<div class="item-tm itm1">
	<div class="p5">
		<form id="formObj2" name="formObj2">
			<table border="0" cellpadding="0" cellspacing="0" width="100%" class="l-table1">
				<tr>
					<td height="25" width="80">时间段：从</td>
					<td width="100"><input id="startDate2" name="startDate" type="text" value="2013-4-1" ltype="date"/></td>
					<td width="20" align="center">至</td>
					<td width="100"><input id="endDate2" name="endDate" type="text" value="2013-4-30" ltype="date"/></td>
					<td width="">&nbsp;</td>
					<td width="60"><a class="l-button3 has-icon" id="dfgsde4" onclick="alert(123);"><span class="l-icon-search"></span>查询</a></td>
				</tr>
			</table>
		</form>
	</div>
</div>

<div class="item-tm itm2">
	<div class="p5">
		第二种样式
	</div>
</div>

<div class="item-tm itm3">
	<div class="p5">
		第三种样式
	</div>
</div>

<div class="scroll-tm">
	<p>增写内容</p>
	<p>增写内容</p>
	<p>增写内容</p>
	<p>增写内容</p>
	<p>增写内容</p>
	<p>增写内容</p>
	<p>增写内容</p>
	<p>增写内容</p>
	<p>增写内容</p>
	<p>增写内容</p>
	<p>增写内容</p>
	<p>增写内容</p>
	<p>增写内容</p>
	<p>增写内容</p>
	<p>增写内容</p>
	<p>增写内容</p>
	<p>增写内容</p>
	<p>增写内容</p>
	<p>增写内容</p>
	<p>增写内容</p>
	<p>增写内容</p>
	<p>增写内容</p>
	<p>增写内容</p>
	<p>增写内容</p>
	<p>增写内容</p>
</div>

</body>
</html>