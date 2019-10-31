<%@page import="com.khnt.utils.StringUtil"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.khnt.utils.DateUtil"%>
<%@page import="java.util.Date"%>
<%@page import="com.scts.maintenance.bean.MaintenanceInfoDTO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="app/common/js/windowprint.js"></script>
<script type="text/javascript" src="app/common/lodop/LodopFuncs.js"></script>
<style type="text/css">
#table td{border:1px black solid}
</style>
<script type="text/javascript">
	$(function() {
		$("#form1").initForm({ //参数设置示例
			toolbar : [ {
				text : '预览打印',
				id : 'prn_preview',
				icon : 'print',
				click:prn_preview
			}, {
				text : '打印',
				id : 'prn_print',
				icon : 'print',
				click : prn_print
			}, {
				text : '关闭',
				id : 'close',
				icon : 'cancel',
				click : close
			} ],
			getSuccess : function(res) {
			
			}
		});
	})
	
	function prn_preview() {	
		CreateOneFormPage();	
		LODOP.PREVIEW();	
	};
	
	function prn_print() {		
		CreateOneFormPage();
		LODOP.PRINT();
		close();	
	};
	
	function CreateOneFormPage(){		
		// 获取打印对象
		LODOP=getLodop();
		// 设置打印样式  
		var strBodyStyle="<style> table{border:1;text-align:center;margin-left:25px;} table,td { border: 1 solid #000000;border-collapse:collapse;font-size:12px } "+
		"</style>";
		// 设置打印方式
		LODOP.SET_PRINT_PAGESIZE(1, 0, 0,"A4");	// 1 纵向打印 2 横向打印
			
		var printReportContent = "";
		var printReport = "";
		<%
			MaintenanceInfoDTO maintenanceInfoDTO = (MaintenanceInfoDTO)request.getSession().getAttribute("maintenanceInfoDTO");
			String task_attach_id = maintenanceInfoDTO.getTask_attach_id();		// 功能说明附件ID
			String update_attach_id = maintenanceInfoDTO.getUpdate_attach_id();	// 更新说明附件ID
			String task_attach_name = maintenanceInfoDTO.getTask_attach_name();	// 功能说明附件Name
			String pro_desc = maintenanceInfoDTO.getPro_desc();
			pro_desc = new String(pro_desc).trim();
			pro_desc = pro_desc.replaceAll("　", "");
			pro_desc = pro_desc.replaceAll("\n", "");
			pro_desc = pro_desc.replaceAll("\r\n", "");
			%>
			printReportContent += "<table width='600' cellpadding='0' cellspacing='0' align='center' border='1' id='table'>";
			printReportContent += "<caption><center><font size='5'><b>四川省特检院检验软件管理平台</b></font><br/><font size='4'><b>运行维护日志</b></font></center></caption>";
			printReportContent += "<div style='text-align:left;padding-top:30px;padding-left:30px'>业务编号：<%=StringUtil.isNotEmpty(maintenanceInfoDTO.getSn())?maintenanceInfoDTO.getSn():"" %></div>";
			printReportContent += "<tr align='center' height='30px'><td width='80px'>模块名称</td>";
			printReportContent += "<td><%=StringUtil.isNotEmpty(maintenanceInfoDTO.getFunc_name())?maintenanceInfoDTO.getFunc_name():"" %></td>";
			printReportContent += "<td>业务类型</td><td><%=StringUtil.isNotEmpty(maintenanceInfoDTO.getInfo_type())?maintenanceInfoDTO.getInfo_type():"" %></td></tr>";
			printReportContent += "<tr align='center' height='30px' width='80px'><td>来源</td>";
			printReportContent += "<td><%=StringUtil.isNotEmpty(maintenanceInfoDTO.getOrg_name())?maintenanceInfoDTO.getOrg_name():"" %>/<%=StringUtil.isNotEmpty(maintenanceInfoDTO.getAdvance_user_name())?maintenanceInfoDTO.getAdvance_user_name():"" %></td>";
			printReportContent += "<td>报告日期</td><td><%=maintenanceInfoDTO.getAdvance_date()!=null?DateUtil.format(maintenanceInfoDTO.getAdvance_date(), "yyyy-MM-dd"):"" %></td></tr>";
			printReportContent += "<tr align='center' height='30px' width='80px'><td >论证人</td>";
			printReportContent += "<td  align='center'>&nbsp;&nbsp;<%=StringUtil.isNotEmpty(maintenanceInfoDTO.getProve_user_name())?maintenanceInfoDTO.getProve_user_name():"" %></td>";
			printReportContent += "<td align='center' height='30px' width='80px'>受理人</td>";
			printReportContent += "<td  align='center'>&nbsp;&nbsp;<%=StringUtil.isNotEmpty(maintenanceInfoDTO.getProve_user_name())?maintenanceInfoDTO.getReceive_user_name():"" %></td></tr>";
			printReportContent += "<tr align='center' height='30px' width='80px'><td>论证结论</td><td><%=StringUtil.isNotEmpty(maintenanceInfoDTO.getProve_type())?maintenanceInfoDTO.getProve_type():"" %></td>";
			printReportContent += "<td>预计完成日期</td><td><%=maintenanceInfoDTO.getExpect_finish_date()!=null?DateUtil.format(maintenanceInfoDTO.getExpect_finish_date(), "yyyy-MM-dd"):"" %></td></tr>";
			printReportContent += "<tr align='center' height='30px' width='80px'><td >论证备注</td><td colspan='3' align='left'>&nbsp;&nbsp;<%=StringUtil.isNotEmpty(maintenanceInfoDTO.getProve_remark())?maintenanceInfoDTO.getProve_remark():"" %></td></tr>";
			printReportContent += "<tr align='center' height='240px' width='80px'><td>功能说明</td>";
			printReportContent += "<td colspan='3' align='left'>&nbsp;&nbsp;<%=StringUtil.isNotEmpty(pro_desc)?pro_desc:"" %></td></tr>";
			
			<%
				if(StringUtil.isNotEmpty(task_attach_id)){
					%>
					printReportContent += "<tr align='center' height='30px' width='80px'><td>上传附件</td>";
					printReportContent += "<td colspan='3' align='left'>&nbsp;&nbsp;<%=StringUtil.isNotEmpty(maintenanceInfoDTO.getTask_attach_name())?maintenanceInfoDTO.getTask_attach_name():"" %></td></tr>";
					<%
				}else{
					%>
					printReportContent += "<tr align='center' height='30px' width='80px'><td>上传附件</td>";
					printReportContent += "<td colspan='3' align='left'>&nbsp;&nbsp;无</td></tr>";
					<%
				}
				%>
				if(printReportContent == ""){
					printReportContent = "<table width='600' cellpadding='0' cellspacing='0' align='center' border='1' id='table'>";
				}
				printReportContent += "<tr align='center' height='30px' width='80px'><td>开发人员</td>";
				printReportContent += "<td><%=StringUtil.isNotEmpty(maintenanceInfoDTO.getDevelop_user_name())?maintenanceInfoDTO.getDevelop_user_name():"" %></td>";
				printReportContent += "<td>完成日期</td><td><%=maintenanceInfoDTO.getDevelop_end_date()!=null?DateUtil.format(maintenanceInfoDTO.getDevelop_end_date(), "yyyy-MM-dd"):"" %></td></tr>";
				printReportContent += "<tr align='center' height='30px' width='80px'><td>测试人员</td>";
				printReportContent += "<td><%=StringUtil.isNotEmpty(maintenanceInfoDTO.getTest_user_name())?maintenanceInfoDTO.getTest_user_name():"" %></td>";
				printReportContent += "<td>测试日期</td><td><%=maintenanceInfoDTO.getTest_date()!=null?DateUtil.format(maintenanceInfoDTO.getTest_date(), "yyyy-MM-dd"):"" %></td></tr>";
				printReportContent += "<tr align='center' height='240px' width='80px'><td>完成/更新说明</td>";
				printReportContent += "<td colspan='3' align='left' >&nbsp;&nbsp;<%=StringUtil.isNotEmpty(maintenanceInfoDTO.getDevelop_desc())?maintenanceInfoDTO.getDevelop_desc():"" %></td></tr>";
				if(printReportContent == ""){
					printReportContent = "<table width='600' cellpadding='0' cellspacing='0' align='center' border='1' id='table'>";
				}
				printReportContent += "<tr align='center' height='30px' width='80px'  ><td>填表人</td>";
				printReportContent += "<td><%=StringUtil.isNotEmpty(maintenanceInfoDTO.getWrite_user_name())?maintenanceInfoDTO.getWrite_user_name():"" %></td>";
				printReportContent += "<td>填表日期</td><td><%=maintenanceInfoDTO.getWrite_date()!=null?DateUtil.format(maintenanceInfoDTO.getWrite_date(), "yyyy-MM-dd"):"" %></td></tr></table>";
				
				
				// 获取打印内容
				var strFormHtml=strBodyStyle+"<body>"+printReportContent+"</body>";
				//LODOP.NewPage();	// 强制分页
				// 打印表格（上边距、左边距、宽、高、打印内容）
			
				LODOP.ADD_PRINT_TABLE(20,0,"100%","100%",strFormHtml);
				
				
				<%
				String fileIds="";
				String fileNames="";
				if(StringUtil.isNotEmpty(task_attach_id)){
					for(int i=0;i<task_attach_id.split("、").length;i++){
						String fileId=task_attach_id.split("、")[i];
						String fileName=task_attach_name.split("、")[i];
						String textName=fileName.substring(fileName.lastIndexOf(".")+1, fileName.length());
						if(textName.equals("jpg")||textName.equals("png")||textName.equals("gif")||textName.equals("tif")){
							if(i==0){
								fileIds=fileId;
								fileNames=fileName;
							}else{
								fileIds=fileIds+","+fileId;	
								fileNames=fileNames+","+fileName;
							}
						}
					}
					
				}
				%>
				
				<%
				if(StringUtil.isNotEmpty(fileIds)){
					%>
					printReport += "<table width='600' cellpadding='0' cellspacing='0' align='center' border='1' id='table'>";
					
					<%
					for(int i=0;i<fileIds.split(",").length;i++){
						String fileId=fileIds.split(",")[i];
						System.out.println("=====fileId=====:"+fileId);
						String fileName=fileNames.split(",")[i];
						System.out.println("=====fileName=====:"+fileName);
						String textName=fileName.substring(fileName.lastIndexOf(".")+1, fileName.length());
						if(textName.equals("jpg")||textName.equals("png")||textName.equals("gif")||textName.equals("tif")||textName.equals("jpeg")
								||textName.equals("bmp")||textName.equals("svg")){
							//Thread.currentThread().sleep(2000);
							%>
							printReport += "<tr align='left' ><td colspan='4' style='page-break-before:always' ><%=fileName%></td></tr>";
							printReport += "<tr align='center' ><td colspan='4' align='left'>&nbsp;&nbsp;<img style='width: 600px;max-height:1040px;' src=\"/fileupload/download.do?id=<%=fileId%>\" ></td></tr>";
							<%	
						}
					}
					%>
					printReport += "</table>";
					//alert(printReport);
					LODOP.NewPage();	// 强制分页
					setTimeout(doPrintPage1(strBodyStyle, printReport),5000);
					
					
					// 获取打印内容
					/* var formHtml=strBodyStyle+"<body>"+printReport+"</body>";
					LODOP.NewPage();	// 强制分页
					// 打印表格（上边距、左边距、宽、高、打印内容）
				
					LODOP.ADD_PRINT_TABLE(20,0,"100%","100%",formHtml); */
					<%
				}
				%>
				<%-- printReport += "<table width='600' cellpadding='0' cellspacing='0' align='center' border='1' id='table'>";
				<%
				if(StringUtil.isNotEmpty(task_attach_id)){
					for(int i=0;i<task_attach_id.split("、").length;i++){
						String fileId=task_attach_id.split("、")[i];
						String fileName=task_attach_name.split("、")[i];
						String textName=fileName.substring(fileName.lastIndexOf(".")+1, fileName.length());
						if(textName.equals("jpg")||textName.equals("png")||textName.equals("gif")||textName.equals("tif")){
							//Thread.currentThread().sleep(2000);
							%>
							printReport += "<tr align='left' ><td colspan='4' style='page-break-before:always' ><%=fileName%></td></tr>";
							printReport += "<tr align='center' ><td colspan='4' align='left'>&nbsp;&nbsp;<img style='width: 600px;height: 335px;' src=\"/fileupload/download.do?id=<%=fileId%>\" ></td></tr>";
							<%	
						}
					}
					%>
					printReport += "</table>";
					<%
				}else{
					%>
					printReport += "</table>";
					<%
				}
				%> --%>
				
	};
	
	function doPrintPage(strBodyStyle, printReportContent){
		// 获取打印内容
		if(printReportContent!=""){
			printReportContent = printReportContent+"</table>";
		}
		var strFormHtml=strBodyStyle+"<body>"+printReportContent+"</body>";
		LODOP.ADD_PRINT_TABLE(20,0,"100%","100%",strFormHtml);
	}
	
	function doPrintPage1(strBodyStyle, printReport){
		var formHtml=strBodyStyle+"<body>"+printReport+"</body>";
		LODOP.ADD_PRINT_TABLE(20,0,"100%","100%",formHtml);
	}
	
	function close(){	
		api.close();
	}
</script>
</head>
<body >
<form id="form1" style="height:99%;">
<div style="overflow:hidden;text-align:center" id='printPaymentDiv'>
	<table width="700" cellpadding="6" cellspacing="0" align="center" border="1" id="table">
		<caption><center><font size="3"><b>四川省特检院检验软件管理平台<br/>运行维护日志</b></font></center></caption>
			<div style="text-align:left;padding-left:30px">业务编号：<c:out value="${sessionScope.maintenanceInfoDTO.sn}"></c:out></div>
       		<tr align="center">
       			<td>模块名称</td>
         		<td><c:out value="${sessionScope.maintenanceInfoDTO.func_name}"></c:out></td>
         		<td>业务类型</td>
              	<td><c:out value="${sessionScope.maintenanceInfoDTO.info_type}"></c:out></td>
       		</tr>	
       		<tr align="center">
       			<td>来源</td>
         		<td><c:out value="${sessionScope.maintenanceInfoDTO.org_name}"></c:out>/<c:out value="${sessionScope.maintenanceInfoDTO.advance_user_name}"></c:out></td>
         		<td>报告日期</td>
              	<td><c:out value="${sessionScope.maintenanceInfoDTO.advance_date}"></c:out></td>
       		</tr>	
       		<tr align="center">
       			<td>论证人</td>
         		<td  align="center">&nbsp;&nbsp;<c:out value="${sessionScope.maintenanceInfoDTO.prove_user_name}"></c:out></td>
         		<td>受理人</td>
         		<td align="center">&nbsp;&nbsp;<c:out value="${sessionScope.maintenanceInfoDTO.receive_user_name}"></c:out></td>          		
       		</tr>
       		<tr align="center">
       			<td>论证结论</td>
              	<td><c:out value="${sessionScope.maintenanceInfoDTO.prove_type}"></c:out></td>
       			<td>预计完成日期</td>
         		<td><c:out value="${sessionScope.maintenanceInfoDTO.expect_finish_date}"></c:out></td>
       		</tr>
       		<tr align="center">
       			<td>论证备注</td>
              	<td colspan="3" align="left">&nbsp;&nbsp;<c:out value="${sessionScope.maintenanceInfoDTO.prove_remark}"></c:out></td>
            </tr>
       		<tr align="center">
       			<td>功能说明</td>
         		<td colspan="3" align="left">&nbsp;&nbsp;<c:out value="${sessionScope.maintenanceInfoDTO.pro_desc}"></c:out></td>
       		</tr>
       		<c:if test="${sessionScope.maintenanceInfoDTO.task_attach_id != '' && sessionScope.maintenanceInfoDTO.task_attach_id != null}">
       		<tr align="center">
       			<td>上传附件</td>
         		<td colspan="3" align="left">&nbsp;&nbsp;<c:out value="${sessionScope.maintenanceInfoDTO.task_attach_name}"></c:out></td>
       		</tr>
       		</c:if>
   			<tr align="center">
       			<td>开发人员</td>
         		<td><c:out value="${sessionScope.maintenanceInfoDTO.develop_user_name}"></c:out></td>
         		<td>完成日期</td>
              	<td><c:out value="${sessionScope.maintenanceInfoDTO.develop_end_date}"></c:out></td>
       		</tr>
       		<tr align="center">
       			<td>测试人员</td>
         		<td><c:out value="${sessionScope.maintenanceInfoDTO.test_user_name}"></c:out></td>
         		<td>测试日期</td>
              	<td><c:out value="${sessionScope.maintenanceInfoDTO.test_date}"></c:out></td>
       		</tr>
       		<tr align="center">
       			<td>完成/更新情况</td>
         		<td colspan="3" align="left">&nbsp;&nbsp;<c:out value="${sessionScope.maintenanceInfoDTO.develop_desc}"></c:out></td>
       		</tr>
       		<tr align="center">
       			<td>填表人</td>
         		<td><c:out value="${sessionScope.maintenanceInfoDTO.write_user_name}"></c:out></td>
         		<td>填表日期</td>
              	<td><c:out value="${sessionScope.maintenanceInfoDTO.write_date}"></c:out></td>
       		</tr>
       		<%-- <tr align="center">
       			<td>上传附件</td>
         		<td colspan="3" align="left">&nbsp;&nbsp;<c:out value="${sessionScope.maintenanceInfoDTO.task_attach_name}"></c:out></td>
       		</tr> --%>
	</table>
</div>
</form>
</body>
</html>