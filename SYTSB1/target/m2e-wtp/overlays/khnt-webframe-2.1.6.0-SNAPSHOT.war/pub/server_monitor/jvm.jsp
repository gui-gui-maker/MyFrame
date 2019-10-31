<%@page contentType="text/plain; charset=UTF-8"%><%	Runtime runtime = Runtime.getRuntime();
	long freeJvmMemory = runtime.freeMemory() / 1024 / 1024;
	long totalJvmMemory = runtime.totalMemory() / 1024 / 1024;
	long usedMemory = totalJvmMemory - freeJvmMemory;
	out.print("&value=" + usedMemory);%>