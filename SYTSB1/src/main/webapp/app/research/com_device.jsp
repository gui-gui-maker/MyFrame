<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@page import="java.util.Date"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.utils.DateUtil"%>
<head pageStatus="${param.status}">
 <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>

    <%@include file="/k/kui-base-list.jsp"%>
<link href="app/research/css/common.css" rel="stylesheet" type="text/css">
<title>人员信息</title>
<script type="text/javascript">
	$(function() {
		
	})
	function deviceInsp(id){
		top.$.dialog({
			width : 1000,
			height :700,
			lock : true,
			title : "设备检验报告信息",
			content : 'url:enterSearchAction/searchInspectionByDevice.do?device_id='+id,
			data : {
				"window" : window
			}
		}).max();
	}
</script>
</head>

<body style="overflow: auto;">


<div class="s_n_bg"></div>

<div class="ser_cont">

	<div class="search_box">
		   <div class="title">
			  <div class="icon_box"><a href="#" target="_blank"><p>单位设备信息</p></a></div>
		   </div>
		   <div class="Per_info">
				
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="per_tab">
					<tr>
						<th>设备名称</th>
						<th>设备注册代码</th>
						<th>使用登记证号</th>
						<th>设备型号</th>
						<th>检验报告数量</th>
						<th>详情</th>
					</tr>
				 	<c:forEach var="insp" items="${deviceList}">
						   <tr>
							<td>${insp.device_name}</td>
							<td>${insp.device_registration_code}</td>
							<td>${insp.registration_num}</td>
							<td>${insp.device_model}</td>
							<td>${insp.report_count}</td>
							<td> <a href="javascript:deviceInsp('${insp.id}')"><p class="more">查看报告&gt;&gt;</p></a></td>
					</tr>
					   
					   </c:forEach>
					
				</table>
		   </div>
		  </div>	
</div>

</body>
</html>