<%@page import="com.khnt.pub.fileupload.bean.Attachment"%>
<%@page import="java.util.List"%>
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
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<style type="text/css">
	#table td{border:1px black solid}
</style>
<%
	MaintenanceInfoDTO maintenanceInfoDTO = (MaintenanceInfoDTO) request.getSession()
			.getAttribute("maintenanceInfoDTO");
	List<Attachment> attachment_list = maintenanceInfoDTO.getAttachment_list();
	String attachment_ids = "";
	if (!attachment_list.isEmpty()) {
		for (Attachment attachment : attachment_list) {
			String file_path = attachment.getFilePath();
			if (attachment.getFileType().contains("image") || file_path.endsWith("jpg")
					|| file_path.endsWith("png") || file_path.endsWith("jpeg") || file_path.endsWith("bmp")
					|| file_path.endsWith("gif")) {
				if (StringUtil.isNotEmpty(attachment_ids)) {
					attachment_ids += ",";
				}
				attachment_ids += attachment.getId();
			}
		}
	}
%>
<script type="text/javascript">
	var atta_ids = "<%=attachment_ids%>";
	function init(){
		
		getImgs(atta_ids);
		
		var readyInput=document.createElement("input");
        readyInput.type = "hidden";
		readyInput.id = "initReadyFlag";
	
		var isIE = (navigator.userAgent.indexOf('MSIE')>=0) || (navigator.userAgent.indexOf('Trident')>=0);
		if(!isIE){
			var imgs=document.getElementsByTagName("img");
			//debugger;
			var imgCount=imgs.length;
			if(imgCount>0){
				 
				var waitFor = setInterval(function(){
					var flag=0;
					for(var idx=0;idx<imgs.length;idx++){
						var img=imgs[idx];
						if(!img.complete){
							console.log("img no complete:"+img.src);
							break;
						}
						flag++;
					}
					
					if(flag==imgCount){
						document.body.appendChild(readyInput);
						clearInterval(waitFor);
					}
					
				}, 250);
			}else{
				document.body.appendChild(readyInput);
			}
		}else{
			document.body.appendChild(readyInput);
		}
	}
	
	function getImgs(img_ids){
		var img_arr = img_ids.split(",");
		var width = 90;
		var img_html = "";
		if(img_ids.length>0){
			for ( var i = 0; i <img_arr.length; i++){
				img_html += "<img src='${pageContext.request.contextPath}/fileupload/download.do?id="+img_arr[i]+"' width='90%'/>";
				if(i!=img_arr.length-1){
					img_html += "<br/>";
				}
			}
		}
		if(img_html!=""){
			document.getElementById("images").innerHTML=img_html;
		}else{
			document.getElementById("img_tr").style.display = 'none';
		}
	}
</script>
</head>
<body onload="javascript:init();">
<form id="form1" style="height:99%;">
<div style="overflow:hidden;text-align:center" id='printPaymentDiv'>
	<table width="700" cellpadding="6" cellspacing="0" align="center" border="1" id="table">
		<caption><center><font size="3"><b>四川省特检院检验软件管理平台<br/>运行维护日志</b></font></center></caption>
			<div style="position:absolute; top:20px;right:550px;bottom:20px;left:20px">业务编号：<c:out value="${sessionScope.maintenanceInfoDTO.sn}"></c:out></div>
       		<tr align="center">
       			<td style="width:80px">模块名称</td>
         		<td><c:out value="${sessionScope.maintenanceInfoDTO.func_name}"></c:out></td>
         		<td>业务类型</td>
              	<td><c:out value="${sessionScope.maintenanceInfoDTO.info_type}"></c:out></td>
       		</tr>	
       		<tr align="center">
       			<td width="80px">来源</td>
         		<td><c:out value="${sessionScope.maintenanceInfoDTO.org_name}"></c:out>/<c:out value="${sessionScope.maintenanceInfoDTO.advance_user_name}"></c:out></td>
         		<td>报告日期</td>
              	<%-- <td><c:out value="${sessionScope.maintenanceInfoDTO.advance_date}"></c:out></td> --%>
              	<td>${fn:split(sessionScope.maintenanceInfoDTO.advance_date," ")[0]}</td>
       		</tr>	
       		<tr align="center">
       			<td width="80">论证人</td>
         		<td>&nbsp;&nbsp;<c:out value="${sessionScope.maintenanceInfoDTO.prove_user_name}"></c:out></td>         
         		<td >受理人</td>
         		<td>&nbsp;&nbsp;<c:out value="${sessionScope.maintenanceInfoDTO.receive_user_name}"></c:out></td>         		
       		</tr>
       		<tr align="center">
       			<td width="80px">论证结论</td>
              	<td><c:out value="${sessionScope.maintenanceInfoDTO.prove_type}"></c:out></td>
       			<td>预计完成日期</td>
         		<%-- <td><c:out value="${sessionScope.maintenanceInfoDTO.expect_finish_date}"></c:out></td> --%>
         		<td>${fn:split(sessionScope.maintenanceInfoDTO.expect_finish_date," ")[0]}</td>
       		</tr>
       		<tr align="center">
       			<td width="80">论证备注</td>
              	<td colspan="3" align="left" width="520">&nbsp;&nbsp;<div style="width:500px;word-wrap:break-word;"><c:out value="${sessionScope.maintenanceInfoDTO.prove_remark}"></c:out></div></td>
            </tr>
       		<tr align="center">
       			<td width="80">功能说明</td>
         		<td colspan="3" align="left" width="520">&nbsp;&nbsp;<div style="width:500px;word-wrap:break-word;"><c:out value="${sessionScope.maintenanceInfoDTO.pro_desc}"></c:out></div></td>
       		</tr>
       		<c:if test="${sessionScope.maintenanceInfoDTO.task_attach_id != '' && sessionScope.maintenanceInfoDTO.task_attach_id != null}">
       		<tr align="center">
       			<td width="80">上传附件</td>
         		<td colspan="3" align="left" width="520">&nbsp;&nbsp;<div style="width:500px;word-wrap:break-word;"><c:out value="${sessionScope.maintenanceInfoDTO.task_attach_name}"></c:out></div></td>
       		</tr>
       		</c:if>
   			<tr align="center">
       			<td width="80px">开发人员</td>
         		<td><c:out value="${sessionScope.maintenanceInfoDTO.develop_user_name}"></c:out></td>
         		<td>完成日期</td>
              	<%-- 	<td><c:out value="${sessionScope.maintenanceInfoDTO.develop_end_date}"></c:out></td> --%>
              	<td>${fn:split(sessionScope.maintenanceInfoDTO.develop_end_date," ")[0]}</td>
       		</tr>
       		<tr align="center">
       			<td width="80px">测试人员</td>
         		<td><c:out value="${sessionScope.maintenanceInfoDTO.test_user_name}"></c:out></td>
         		<td>测试日期</td>
              	<%-- <td><c:out value="${sessionScope.maintenanceInfoDTO.test_date}"></c:out></td> --%>
              	<td>${fn:split(sessionScope.maintenanceInfoDTO.test_date," ")[0]}</td>
       		</tr>
       		<tr align="center">
       			<td width="80">完成/更新情况</td>
         		<td colspan="3" align="left" width="520">&nbsp;&nbsp;<c:out value="${sessionScope.maintenanceInfoDTO.develop_desc}"></c:out></td>
       		</tr>
       		<tr align="center">
       			<td width="80px">填表人</td>
         		<td><c:out value="${sessionScope.maintenanceInfoDTO.write_user_name}"></c:out></td>
         		<td>填表日期</td>
              	<%-- <td><c:out value="${sessionScope.maintenanceInfoDTO.write_date}"></c:out></td> --%>
              	<td>${fn:split(sessionScope.maintenanceInfoDTO.write_date," ")[0]}</td>
       		</tr>
       		<tr align="center" height="500px" width="700px" id="img_tr">
       			<td colspan="4" align="center">
       				<div id="images"></div>
       			</td>
       		</tr>
	</table>
</div>
</form>
</body>
</html>