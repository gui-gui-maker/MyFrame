<%@page import="java.net.InetAddress"%>
<%@page import="java.util.TreeSet"%>
<%@page import="java.util.Set"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.Properties"%>
<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
	Runtime runtime = Runtime.getRuntime();
	Properties props = System.getProperties();
%>
<head>
<%@include file="/k/kui-base-list.jsp"%>
<script src="pub/chart/js/kh_fc.js"></script>
<script type="text/javascript">
	$(function() {
		$("#page_wrap").ligerLayout({
			rightWidth: 600,
			allowRightResize: false,
			space: 5
		});
		$("#char_view").ligerLayout({
			bottomHeight: 210,
			allowBottomResize: false,
			space: 5
		});
		
		<%int i = 1;%>
		var infoData = [
			{id:"<%=i++%>",item:"操作系统",val:"<%=props.get("os.name")%>，<%=props.get("sun.arch.data.model")%>位，版本:<%=props.get("os.version")%>，架构:<%=props.get("os.arch")%>"},
			{id:"<%=i++%>",item:"用户",val:"<%=props.get("user.name")%>"},
			{id:"<%=i++%>",item:"服务器IP",val:"<%=InetAddress.getLocalHost().getHostAddress()%>"},
			{id:"<%=i++%>",item:"应用服务器",val:"<%=session.getServletContext().getServerInfo()%>"},
			{id:"<%=i++%>",item:"监听端口",val:"<%=request.getServerPort()%>"},
			{id:"<%=i++%>",item:"Servlet版本",val:"<%=session.getServletContext().getMajorVersion()%>.<%=session.getServletContext().getMinorVersion()%>"},
			{id:"<%=i++%>",item:"应用目录",val:"<%=session.getServletContext().getRealPath("/")
					.replace("\\", "\\\\")%>"},
			{id:"<%=i++%>",item:"临时目录",val:"<%=props.get("java.io.tmpdir").toString()
					.replace("\\", "\\\\")%>"},
			{id:"<%=i++%>",item:"JAVA环境",val:"<%=props.get("java.runtime.name")%> <%=props.get("java.runtime.version")%>"},
			{id:"<%=i++%>",item:"JVM",val:"<%=props.get("java.vm.name")%> <%=props.get("java.vm.version")%>"},
			{id:"<%=i++%>",item:"JVM路径",val:"<%=props.get("java.home").toString().replace("\\", "\\\\")%>"},
			{id:"<%=i++%>",item:"JVM提供商",val:"<%=props.get("java.vm.vendor")%>"},
			{id:"<%=i++%>",item:"JVM最大内存",val:"<%=runtime.maxMemory() / 1024 / 1024%>MB"}
		];
		$("#info_grid").ligerGrid({
			columns : [ {
				display : '序号',
				name : 'id',
				align : 'center',
				width : "7%"
			}, {
				display : '项目',
				name : 'item',
				align : 'left',
				width : "28%"
			}, {
				display : '值',
				name : 'val',
				align : 'left',
				width : "63%",
				editor : {
					type : "text"
				}
			} ],
			data : {
				Rows : infoData
			},
			width : '100%',
			enabledEdit : true,
			height : '100%',
			usePager : false,
		});
		var cpuAg = new FusionCharts("pub/chart/FusionCharts/Charts/RealTimeLine.swf", "cpu_monitor", "100%", "100%", "0", "1");
		cpuAg.setXMLUrl("pub/server_monitor/cpu_xml.jsp");
		cpuAg.render("cpu_status");
		var jvmAg = new FusionCharts("pub/chart/FusionCharts/Charts/HLED.swf", "jvm_monitor", "100%",$("#jvm_memery").parent().height()-20, "0", "1");
		jvmAg.setXMLUrl("pub/server_monitor/jvm_xml.jsp");
		jvmAg.render("jvm_memery");
	});
</script>
<style type="text/css">
.l-layout-right{
border-top:none;
border-right:none;
border-left:none;
border-bottom: 1px solid #BED5F3 ;
}
.l-layout-bottom{
	border-right: 1px solid #BED5F3;
}
</style>
</head>
<body class="p5" style="background-color:#ecf7ff;overflow-y:auto">
	<div id="page_wrap">
		<div position="center" title="服务器信息" id="info_grid"></div>
		<div position="right" id="char_view" style="background-color:#ecf7ff;">
			<div id="cpu_status" position="center" title="CPU使用率" style="text-align:center;"></div>
			<div id="jvm_memery" position="bottom" title="JVM内存使用" style="text-align:center;background-color:red;"></div>
		</div>
	</div>
</body>
</html>