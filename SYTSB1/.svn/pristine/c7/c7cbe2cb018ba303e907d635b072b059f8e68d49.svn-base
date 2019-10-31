<%@page import="java.math.BigDecimal"%>
<%@page import="java.math.RoundingMode"%>
<%@page import="org.hyperic.sigar.CpuPerc"%>
<%@page import="org.hyperic.sigar.Sigar"%>
<%@page import="org.hyperic.sigar.SigarException"%>
<%@page contentType="text/xml;charset=UTF-8"%>
<chart manageresize="1" bgcolor="000000" bgalpha="100"
	canvasborderthickness="1" canvasbordercolor="008040" canvasbgcolor="000000"
	yaxismaxvalue="100" decimals="0" numdivlines="9" numvdivlines="28"
	numdisplaysets="30" divlinecolor="008040" vdivlinecolor="008040"
	divlinealpha="100" chartleftmargin="10" basefontcolor="00dd00"
	showrealtimevalue="0" datastreamurl="pub/server_monitor/cpu.jsp"
	refreshinterval="2" numbersuffix="%" labeldisplay="rotate" slantlabels="1"
	tooltipbgcolor="000000" tooltipbordercolor="008040" basefontsize="11"
	showalternatehgridcolor="0" legendbgcolor="000000" legendbordercolor="008040"
	legendpadding="12" showlabels="1">
	<categories>
		<category label="开始" />
	</categories>
	<%	
		String colors = "FF0000#00FF00#0000FF#FFFFFF#CCFF00#00FFFF#CC9900#F0FFFF#F5F5DC#FFE4C4#000000#FFEBCD#F0F8FF#0000FF#7FFF00#D2691E#FF7F50#6495ED#FFF8DC#DC143C#00FFFF#00008B#008B8B#B8860B#A9A9A9#006400#BDB76B#8B008B#556B2F#FF8C00#9932CC#8B0000#E9967A#8FBC8F#483D8B#2F4F4F#00CED1#9400D3#FF1493#00BFFF#696969#1E90FF#D19275#B22222#FFFAF0#228B22#FF00FF#DCDCDC#F8F8FF#FFD700#DAA520#808080#008000#ADFF2F#F0FFF0#FF69B4#CD5C5C#4B0082#FFFFF0#F0E68C#E6E6FA#FFF0F5";
		String [] colorArr = colors.split("#");
		Sigar sigar = new Sigar();
		CpuPerc [] cpuList = sigar.getCpuPercList();
		for(int i = 0;i< cpuList.length;i++){
	%>			
	<dataset color="<%=colorArr[i]%>" seriesname="CPU[<%=i+1%>]" showvalues="0"
		alpha="100" anchoralpha="0" linethickness="2">
		<set value="<%=new BigDecimal(cpuList[i].getCombined() * 100).setScale(1, RoundingMode.HALF_UP).toPlainString()%>" />
	</dataset>
	<%
		}
	%>
</chart>