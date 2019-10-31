<%@page import="java.math.RoundingMode"%><%@page import="java.math.BigDecimal"%><%@page
	import="java.text.SimpleDateFormat"%><%@page import="java.text.DateFormat"%><%@page import="java.util.Date"%><%@page
	import="org.hyperic.sigar.SigarException"%><%@page import="org.hyperic.sigar.CpuPerc"%><%@page
	import="org.hyperic.sigar.Sigar"%><%@page contentType="text/plain;charset=UTF-8"%><%
	Sigar sigar = new Sigar();
	String result = "&label=";
	Date cdate = new Date();
	DateFormat df = new SimpleDateFormat("mm:ss");
	result += df.format(cdate) + "&value=";
	try {
		CpuPerc[] cpuList = sigar.getCpuPercList();
		for (int i = 0; i < cpuList.length; i++) {
			if (i > 0)
				result += "|";
			result += new BigDecimal(cpuList[i].getCombined() * 100).setScale(1, RoundingMode.HALF_UP)
					.toPlainString();
		}
	}
	catch (Exception e) {
		result += "0";
	}
	out.print(result);
%>