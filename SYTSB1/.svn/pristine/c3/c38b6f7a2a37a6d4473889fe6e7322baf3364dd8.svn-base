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
		$("#formObj").ligerForm();
		$("#button1").ligerButton({icon: "count", text: "统计", click: function () {
			alert("开始统计");
		}});
	});
	function changeRadio(value) {
		dateNs(value, "startDate", "endDate", toDayDate);
	}
	;
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
		<div class="l-page-title-content" style="top:5px;">
			<form id="formObj" name="formObj">
				<table border="0" cellpadding="0" cellspacing="0" width="" class="l-table1">
					<tr>
						<td height="25" width="80">时间段：从</td>
						<td width="100"><input id="startDate" name="startDate" type="text" value="2013-4-1" ltype="date"/></td>
						<td width="20" align="center">至</td>
						<td width="100"><input id="endDate" name="endDate" type="text" value="2013-4-30" ltype="date"/></td>
						<td width=""><div id="button1"></div></td>
					</tr>
				</table>
				<table border="0" cellpadding="0" cellspacing="0" width="" class="l-table1">
					<tr>
						<td height="25" width="80"></td>
						<td width="200"><input type="radio" name="radio1" ltype="radioGroup" ligerui="{data:[
							{text:'本周',id:'bz'},
							{text:'本月',id:'by'},
							{text:'本季度',id:'bjd'},
							{text:'本年',id:'bn'}
						]
						,onChange:function(value){
							changeRadio(value)
						}}"/></td>
					</tr>
				</table>
			</form>
		</div>
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