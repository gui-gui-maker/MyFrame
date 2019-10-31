<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="java.util.HashMap"%>
<%@page import="util.ReportUtil"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@page import="java.util.Date"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.utils.DateUtil"%>
<head pageStatus="${param.status}">
 <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>

    <%@include file="/k/kui-base-list.jsp"%>
    <% 
   String name = request.getAttribute("name").toString();
    HashMap<String, Object> map = ReportUtil.getNewsN(name,"7","1");
    String data = map.get("data").toString();
    String sumC = map.get("sumC").toString(); %>
<link href="app/research/css/common.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="app/research/js/encode.js"></script>
<title>人员信息</title>
<script type="text/javascript">
document.onkeydown=function(event){
    var e = event || window.event || arguments.callee.caller.arguments[0];           
     if(e && e.keyCode==13){ // enter 键
    	 indexSerachClick();
    }
};
var role = ${role};
var rsrole = ${rsrole};

var name = "${name}";
var name1 = EncodeUtf8("${name}");
	$(function() {
		if("${name}"!=""){
			$("#search").val("${name}")
		}
	var newsData = <%=data %>;
		if(newsData!=null){
			var l = 7;
			if(newsData.length<7){
				l = newsData.length;
			}
			
			if(newsData.length>0){
				$("#news").append("<div class='title'> <div class='icon_box'><a href='javascript:moreNews()'><p>新闻内容</p></a></div>"+
						" <a href='javascript:moreNews()' style='width: 300px;text-align: left;'>"+
						 " <p class='more' style='width: 300px;text-align: right;color: black;'> 在网站为您云检索结果约"+<%=sumC%>+"条，查看更多&gt;&gt;</p></a>"+
						"</div> <div class='Per_info'> <ul class='n_l_box_list' id='newsList'> </ul></div>")
				
			}
			for (var i = 0; i < l; i++) {
				var title = newsData[i].title;
				var n = title.indexOf(name);
				var l1 = name.length;
				var titleN = "";
				if(n!=-1){
					titleN = title.substring(0,n)+'<p style="color: red;text-align: left;display:inline; ">'+title.substring(n,n+l1)+'</p>'+title.substring(n+l1,title.length);
				}else{
					titleN = title;
				}
				
				$("#newsList").append("<li><div class='n_l_box_list_bt'><a target='_blank'  href='"+newsData[i].url+"' >"+titleN+"</a></div>"+
						" <div class='n_l_box_time'>"+newsData[i].date+"</div></li>")
				
	            
	          
			}
		}
	
		//加载文件信息
		var n = "${cloadList1C>0||cloadList11C>0||cloadList2C>0}";
		if(n=="true"){
			var m = 0
			var cloadList11 = ${cloadList11};
			var s1=4
			if(cloadList11.length<4-m){
				s1 = cloadList11.length;
			}
				$("#cloadList").append('<div class="title"> <div class="icon_box"><a href="javascript:detailCloud()"><p>资料</p></a></div>'
						+ ' <a href="javascript:detailCloud()" style="width: 350px;text-align: left;">'
						+ ' <p class="more" style="width: 350px;text-align: right;color: black;">'
						+ '在电子资料云平台为您云检索结果约${(cloadList1C-0)+(cloadList11C-0)+(cloadList2C-0)}条，查看更多&gt;&gt;</p></a>'
					  	+' </div>'
					    +'<div class="Per_info">'
						+'<table width="100%" border="0" cellspacing="0" cellpadding="0"'+
						' class="per_tab" style="text-align: left;" id="cloadLists">'
						+'<tr>'
						+'<th>文件名称</th>'
						+'<th style="width: 100px;">下载</th>'
						+'</tr></table></div>	');
			for (var i = 0; i < s1; i++) {
				m++;
				var insp = cloadList11[i];
				var title = insp.infoName;
				var n = title.indexOf(name);
				var l1 = name.length;
				if(n!=-1){
					titleN = title.substring(0,n)+'<p style="color: red;text-align: left;display:inline; ">'+title.substring(n,n+l1)+'</p>'+title.substring(n+l1,title.length);
				}else{
					titleN = title;
				}
				var td = ' <tr  style="text-align: left;">'
						+'<td  style="text-align: left;">';
				if(insp.infoType=='4'){
					td = td + '<a href="javascript:openfolder(\''+insp.id+'\',\''+insp.infoName+'\',\''+insp.resourceType+'\')"> '
					+titleN+'</a>'
				}else{
					td = td + titleN;
				}
				td = td + '</td><td style="width: 100px;text-align: left;">'
			
				if(insp.infoType=='4'){
					td = td + '<a href="javascript:openfolder(\''+insp.id+'\',\''+insp.infoName+'\',\''+insp.resourceType+'\')"> 打开</a>'
				}else{
					td = td + '<a  target="_blank" href="fileupload2/downloadByPath.do?id='+insp.id+'&fileName='+insp.infoName+'">下载</a>'
					if(insp.resourceType=='2'){
						td = td + '/<a href="javascript:showFile(\''+insp.id+'\',\''+insp.infoName+'\',\''+insp.infoType+'\')">预览</a>'
					}
				}
				td = td + '</td><tr>'
				$("#cloadLists").append(td);
			}
			
			 if(m<4){
				var cloadList1 = ${cloadList1};
				var s2=4
				if(cloadList1.length<4-m){
					s2 = cloadList1.length;
				}
				for (var i = 0; i < s2; i++) {
					m++;
					var insp = cloadList1[i];
					var title = insp.infoName;
					var n = title.indexOf(name);
					var l1 = name.length;
					if(n!=-1){
						titleN = title.substring(0,n)+'<p style="color: red;text-align: left;display:inline; ">'+title.substring(n,n+l1)+'</p>'+title.substring(n+l1,title.length);
					}else{
						titleN = title;
					}
					var td = '<tr  style="text-align: left;">'
					+'<td  style="text-align: left;">'+titleN
					+'</td>'
					+'<td style="width: 100px;text-align: left;">'
					+'	<a  target="_blank" href="fileupload2/downloadCompress.do?id='+insp.id+'&proportion=0&fileName='+insp.infoName+'">下载</a>';
					if(insp.resourceType=='2'){
						td = td + '/<a href="javascript:showFile(\''+insp.id+'\',\''+insp.infoName+'\',\''+insp.infoType+'\')">预览</a>';
					}
					td = td + '</td><tr>'
					$("#cloadLists").append(td);
				}
			}
			
			if(m<4){
				var cloadList2 = ${cloadList2};
				var s3=4
				if(cloadList2.length<4-m){
					s3 = cloadList2.length;
				}
				for (var i = 0; i < s3; i++) {
					m++;
					var insp = cloadList2[i];
					var title = insp.infoName;
					var n = title.indexOf(name);
					var l1 = name.length;
					if(n!=-1){
						titleN = title.substring(0,n)+'<p style="color: red;text-align: left;display:inline; ">'+title.substring(n,n+l1)+'</p>'+title.substring(n+l1,title.length);
					}else{
						titleN = title;
					}
					var td = '<tr  style="text-align: left;">'
				+'<td  style="text-align: left;">'+titleN
				+'</td>'
				+'<td style="width: 100px;text-align: left;">'
				+'	<a  target="_blank" href="fileupload2/downloadCompress.do?id='+insp.id+'&proportion=0&fileName='+insp.infoName+'">下载</a>';
				if(insp.resourceType=='2'){
					td = td + '/<a href="javascript:showFile(\''+insp.id+'\',\''+insp.infoName+'\',\''+insp.infoType+'\')">预览</a>';
				}
				td = td + '</td><tr>'
					$("#cloadLists").append(td);
				}
			}
		}
	
	})
	
	function indexSerachClick(){
		if($("#search").val()==""){
			alert("请输入查询条件！")
			return;
		}
		$("body").mask("正在查询，请稍后！");
		window.location.href= $("base").attr("href")+"enterSearchAction/searchAll.do?name="+$("#search").val();
		
	}
	
	function detailUser(){
		top.$.dialog({
			width : 1024,
			height :800,
			lock : true,
			title : "人员详细信息",
			content : 'url:enterSearchAction/searchAll.do?name='+name1+'&status=add'+
					"&view=app/research/ser_user_show",
			data : {
				"window" : window
			}
		}).max();
	}
	
	function detailInsp(){
		top.$.dialog({
			width : 900,
			height :700,
			lock : true,
			title : "更多检验信息",
			content : 'url:enterSearchAction/searchAll.do?name='+name1+'&status=add'+
					"&view=app/research/ser_user_indp_list",
			data : {
				"window" : window
			}
		}).max();
	}
	function moreNews(){
		top.$.dialog({
			width : 800,
			height :600,
			lock : true,
			title : "更多新闻信息",
			content : 'url:app/research/news_list.jsp?status=add&name='+name1,
			data : {
				"window" : window
			}
		}).max();
	}
	function urlNews(url){
		//alert(url)
		top.$.dialog({
			width : 900,
			height :700,
			lock : true,
			title : "新闻详细信息",
			content : "url:"+url,
			data : {
				"window" : window
			}
		}).max();
	}
	function detailCloud(){
		top.$.dialog({
			width : 1024,
			height :800,
			lock : true,
			title : "文件信息",
			content :'url:enterSearchAction/searchAll.do?name='+name1+'&status=add'+
					"&view=app/research/more_cloud_list",
			data : {
				"window" : window
			}
		}).max();
	}

	function openfolder(id,name,resourceType){
		top.$.dialog({
			width : 1024,
			height :800,
			lock : true,
			title : "文件信息",
			content :'url:app/cloud_platform/owner/show_file_query.jsp',
			data : {
				"window" : window,pid:EncodeUtf8(id),name:name,resourceType:resourceType
			}
		}).max();
	}
	function showFile(id,name,infoType){
		var selects = [];
		var ids = [];
		ids["id"]=id;
		ids["name"]=name;
		selects[selects.length]=ids;
		var previewData = {
			     first: 0,           //首先显示的文件序号（数组元素序号）
			     images: selects
			};
		var url="url:pub/fileupload1/fileupload/preview2.jsp";
		if(infoType=="3"){
			url = url +"?resource_type=3";
		}
			
		
		top.$.dialog({
		     title: name,
		      width: $(top).width()-100,
		      height: $(top).height()-100,
		      resize: false,
		      max: false,
		      min: false,
		      content: url,
		      data: previewData
		  });
	}


</script>
</head>

<body style="overflow: auto;">
<div class="s_n_bg"></div>




<div class="ser_top">
	
	
	
	<div class="w854">
	
	<div class="ser_t_lo"></div>
	<div class="searchTextbg2">
            <input id="search"  class="text ng-pristine ng-valid" type="text" ng-model="searchText" ng-class="{'promptTextRed':promptTextRed}" ng-focus="promptTextRedFocus()" ng-keyup="keyupClick($event)" placeholder="请输入姓名 / 单位 / 设备 / 等">
            <input class="btn" type="button" onclick="indexSerachClick()">
    </div>
    </div>
	
</div>

<div class="ser_cont">

	<div class="search_box">
	   <div class="title">
		  <div class="icon_box"><a href="#" target="_blank"><p>个人信息</p></a></div>
		  
	   </div>
	   <div class="Per_info">
	   		
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="per_tab">
				<tr>
					<td width="10%" rowspan="4" align="center" style="padding:5px;"><img  style="width: 85px;height: 120px;" src="upload/${emp.empPhoto}" /></td>
					<td width="15%" class="l_info_td">姓名：</td>
					<td width="30%" class="r_info_td" style="color: red;">${emp.empName}</td>
					<td width="15%" class="l_info_td">职称：</td>
					<td width="30%" class="r_info_td">${emp.empTitle}</td>
				</tr>
				<tr>
					<td class="l_info_td">籍贯：</td>
					<td class="r_info_td">${emp.empNativePlace}</td>
					<td class="l_info_td">政治面貌：</td>
					<td class="r_info_td">${emp.empPolitical}</td>
				</tr>
				<tr>
					<td class="l_info_td">身份证号：</td>
					<td class="r_info_td">${fn:substring(emp.empIdCard, 0, 6)}************</td>
					<td class="l_info_td">参加工作时间： </td>
					<td class="r_info_td"> ${fn:substring(emp.joinWorkDate, 0, 10)}</td>
				</tr>
				<tr>
					<td class="l_info_td">电话：</td>
					<td class="r_info_td">${emp.mobilePhone}</td>
					<td class="l_info_td">&nbsp;</td>
					<td class="r_info_td">
					<%-- <c:if test="${role=='true'||rsrole=='true'}"> --%>
					<a href="javascript:detailUser()" class="more">查看TA的简历>></a>
					<%-- </c:if> --%>
					</td>
				</tr>
			</table>

	   
	   
	   
	   </div>					
	</div>
	
<c:if test="${inspListC0>0}">
	<div class="search_box">
		
	
		   <div class="title">
			  <div class="icon_box"><a href="#" target="_blank"><p>报告信息</p></a></div>
			  <a href="javascript:detailInsp()" style="width: 300px;text-align: left;">
			  <p class="more" style="width: 300px;text-align: right;color: black;" > 在检验软件为您云检索结果约${inspListC0}条，查看更多&gt;&gt;</p></a>
		   </div>
		   <div class="Per_info">
				
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="per_tab">
					<tr>
						<th>报告书使用单位</th>
						<th>设备名称</th>
						<th>检验部门</th>
						<th>报告书名称</th>
						<th>参检人员</th>
					</tr>
				 	<c:forEach var="insp" items="${inspList0}" begin="0" end="${inspListC0<5?inspListC0:4}">
						   <tr>
							<td>${insp.com_name}</td>
							<td>${insp.device_name}</td>
							<td>${insp.org_name}</td>
							<td>${insp.report_name}</td>
							<td>${insp.check_op_name}</td>
					</tr>
					   
					   </c:forEach>
					
				</table>

	
		   
		   
		   
		   </div>		
		   	
	</div>	

	</c:if>		

<div class="search_box" id="news">
		   					
</div>


	<div class="search_box" id="cloadList">
				
	</div>	





</div>




     




</body>
</html>