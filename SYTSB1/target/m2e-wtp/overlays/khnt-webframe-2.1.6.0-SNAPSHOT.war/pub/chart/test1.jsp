<%@ page import="com.khnt.utils.DateUtil" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<%@ taglib uri="http://khnt.com/tags/chart" prefix="chart" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <%@include file="/k/kui-base-list.jsp" %>
    <script test="text/javascript">
	$(function () {//jQuery页面载入事件
		$("#formObj").ligerForm();
	});
	function query(){
		var depName = $("#depName").val();
		var depid = $("#depName").ligerGetComboBoxManager().getValue();
		var param = "$T{depName}="+depName+","+depid;
		var src = "pub/chart/test2.jsp?paramValue="+param;
		$("#chart").attr("src",src)
	}
	</script>
</head>
<body>
<div class="item-tm">
	<div id="toolbar1"></div>
</div>
<div class="item-tm">
	<div id="titleElement" class="l-page-title2 has-icon has-note">
		<div class="l-page-title2-div"></div>
		<div class="l-page-title2-text"><h1>性别人数信息统计</h1></div>
		<div class="l-page-title2-note">以各部门为统计对象。</div>
		<div class="l-page-title2-icon"><img src="k/kui/images/icons/32/statistics.png" border="0"></div>
		<div class="l-page-title-content" style="top:15px;height:80px;">
			<form id="formObj" name="formObj">
				<table border="0" cellpadding="0" cellspacing="0" width="" class="l-table1">
					<tr>
						<td height="25" width="80" align="right">部门名称：</td>
						<td width="150" align="left">
							<input id="depName" name="depName" type="text" ltype="select" ligerui="{initValue:'402880aa47568721014756a82c5c0003',data:<u:dict sql="select id,dep_name text from sys_chart_testp"/>}"/>
						</td>
						<td width="80" align="right">&nbsp;&nbsp;<a id="search" class="l-button3 has-icon" onclick="query()"><span class="l-icon-search"></span>查询</a></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</div>
<div class="scroll-tm">
	<iframe style="overflow: hidden;width: 100%;height: 100%;border: 0px;" id="chart" src="pub/chart/test2.jsp?paramValue=$T{depName}=人事处,402880aa47568721014756a82c5c0003"></iframe>
</div>

</body>
</html>