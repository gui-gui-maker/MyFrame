<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.lsts.inspection.bean.*"%>
<%@page import="com.lsts.inspection.service.*"%>
<%
try{
	CurrentSessionUser user = SecurityUtil.getSecurityUser();

	String ids = request.getParameter("id");
	String file_name = "";
	String[] id = new String[1];
	if(ids.indexOf(",")!=-1){
		id = ids.split(",",0);
	} else {
		id[0] = ids;
	}
	if(id.length > 0){
		InspectionInfoService inspectionInfoService = new InspectionInfoService();
		for(int i = 0 ; i < id.length ; i++){
			InspectionInfo inspectionInfo = inspectionInfoService.get(id[i]);
			file_name = inspectionInfo.getFile_name();
		}
	}
	
	//String returns = device_id + "," +device_sort_code +","+ device_status;
%>
<%=file_name%>
<%} catch (Exception e) {%>
<%="false"%>
<%}%>

