<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();

	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title></title>
<style>
.l-grid-hd-row {
	border-bottom: 1px solid #A3C0E8;
	height: 22px;
	line-height: 22px;
	background: #269ff1  ;
	overflow: hidden;
	width: 80%;
} 
</style>
<%@include file="/k/kui-base-form.jsp"%>
<link rel="stylesheet" type="text/css"
	href="/app/webmanage/CSS/style.css" />

<script src="/app/webmanage/js/ligerGrid.js" type="text/javascript"></script>
<script src="/app/webmanage/js/WdatePicker.js" type="text/javascript"></script>
<script src="/app/webmanage/js/InspectionSave.js" type="text/javascript"></script>
<script type="text/javascript">
var reportType =<u:dict sql="select distinct t.fk_report_id,t.report_name   from base_unit_flow t"/>;
var deviceType="";

</script>
</head>
<body onload="createdeviceGrid();">
<div class="top">
	<div class="logo" title="乐山市特种设备监督检验所"></div>
	<div class="flash">
		<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=10,0,0,0" width="998" height="240" id="noname1">
			<param name="movie" value="/app/webmanage/images/top.swf" />
			<!-- <param name="wmode" value="opaque" /> -->
			<param name="wmode" value="transparent" />
			<!-- JS弹窗和菜单被FLASH遮住解决方法 --> 
			<!-- <param name="allowScriptAccess" value="sameDomain" /> --><!-- 始终在中间，页面上最关键的属性 -->
			<param name="quality" value="High" />
			<param name="menu" value="True" />
			<param name="flashvars" value="bbb=1" />
			<embed src="/app/webmanage/images/top.swf" width="998" height="240" name="noname1" wmode="transparent" quality="high" allowScriptAccess="sameDomain" allowfullscreen="false" type="application/x-shockwave-flash" pluginspage="http://www.adobe.com/go/getflashplayer_cn" />
			<!-- 非IE调用方式 -->
		</object>
	</div>
</div>
<!--导航菜单-->
<div class="menu">
	<ul>
		<li><a href="/rsc/index.html">网站首页</a><span></span></li>
		<li><a href="/rsc/jysjj/index.html">检验所简介</a><span></span></li>
		<li><a href="/rsc/ywzl/index.html">业务指南</a><span></span></li>
	<li><a href="/rsc/bsdt/index.html">办事大厅</a><span></span></li>
		<li><a href="/rsc/xwzx/index.html">新闻中心</a><span></span></li>
		<li><a href="/rsc/xzzx/index.html">下载中心</a><span></span></li>
	
		<li><a href="/rsc/lxfs/index.html">联系方式</a></li>
	</ul>
</div>
<div class="wrap"> 
	<!--内页开始-->
	<div class="pages"> 
	<form name='formObj' method='post' action='' style='margin:0px;'>
	
		<table border="0" cellspacing="0" cellpadding="0"  width="998"  align="center"
			class="jzmtb01">
			<tr>
				<td align="right" class="tb01bj">使用单位</td>
				<td  class="tb01bj"><input id="com_name" 
					 name="com_name"> 
				</td>
				<td align="right" class="tb01bj">设备大类：</td>
				<td  class="tb01bj"><select id="device_type" name="device_type"
					style="width:100" class="ipt_01">

						<option value="1000" title="锅炉">锅炉</option>

						<option value="2000" title="压力容器">压力容器</option>

						<option value="3000" title="电梯">电梯</option>

						<option value="4000" title="起重机械">起重机械</option>

						<option value="5000" title="厂内机动车辆">厂内机动车辆</option>

						<option value="6000" title="大型游乐设施">大型游乐设施</option>

						<option value="8000" title="压力管道">压力管道</option>

						<option value="9000" title="客运索道">客运索道</option>

						<option value="99000" title="安全附件及其他">安全附件及其他</option>

				</select></td>
				
			</tr>
			<tr>
				
				<td align='right'  class="tb01bj">检验日期：</td>

				<td  class="tb01bj"><input name="start_check_date" id="start_check_date"
					type="text" ltype="date" ligerui="{format:'yyyy-MM-dd'}"
					onClick="WdatePicker()" validate="{disabled:true}" /></td>
				<td  class="tb01bj">至 <input name="end_check_date" id="end_check_date"
					type="text" ltype="date" ligerui="{format:'yyyy-MM-dd'}"
					onClick="WdatePicker()" validate="{disabled:true}" /></td>
					<td align="center" class="tb01bj"><INPUT id="ews1"
					type="BUTTON" value=查询 onclick="ReportSearch()">

			</tr>
			
		</table>
		
		<div id="device" width="998"></div>
		
	</form>
</div>
</div>
<div class="foot">
		<div class="footer">
			<p>&copy; (C) 2004-2013 版权所有: 乐山市特种设备监督检验所</p>
			<p>蜀ICP备12008332  地址：四川省乐山市中区龙游路北段269号</p>
			<p>技术支持：<a href="http://www.khnt.com" target="_blank">成都川大科鸿新技术研究所</a></p>
		</div>
	</div>
</div>
</body>
</html>
