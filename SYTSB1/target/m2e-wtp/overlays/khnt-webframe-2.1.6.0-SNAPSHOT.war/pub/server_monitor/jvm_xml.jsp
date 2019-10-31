<%@page contentType="text/xml;charset=GBK"%>
<%
	Runtime runtime = Runtime.getRuntime();
	long totalJvmMemory = runtime.totalMemory()/1024/1024;
	long freeJvmMemory = runtime.freeMemory() / 1024 / 1024;
	long maxMemory = runtime.maxMemory() / 1024 / 1024;
	long usedMemory = totalJvmMemory - freeJvmMemory;
%>
<chart manageresize="1" baseFontSize="12" palette="3" lowerlimit="0" upperlimit="<%=maxMemory%>" majortmcolor="333333"  ledsize="5" ledgap="5" ticksbelowgauge="0" 
		majortmalpha="100" majortmheight="10" majortmthickness="2" minortmcolor="666666" charttopmargin="30" chartbottommargin="20" 
		minortmalpha="100" minortmheight="7" usesamefillcolor="1" datastreamurl="pub/server_monitor/jvm.jsp" refreshinterval="2" numberSuffix="MB"
		chartTopMargin="30" chartBottomMargin="40">
	<colorRange>
		<color minValue="0" maxValue="<%=totalJvmMemory*0.6%>" code="99FF66" />
		<color minValue="<%=totalJvmMemory*0.9%>" maxValue="<%=totalJvmMemory%>" code="FF9900"/>
		<color minValue="<%=maxMemory*0.6%>" maxValue="<%=maxMemory%>" code="FF654F"/>
	</colorRange>
	<value><%=usedMemory%></value>
</chart>