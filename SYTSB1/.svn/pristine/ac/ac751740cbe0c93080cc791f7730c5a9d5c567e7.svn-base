<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="java.util.HashMap"%>
<%@page import="com.alibaba.fastjson.JSONArray"%>
<%@page import="com.alibaba.fastjson.JSONObject"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="util.ReportUtil"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@page import="java.util.Date"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@page import="com.khnt.utils.DateUtil"%>
<head pageStatus="${param.status}">
 <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>

    <%@include file="/k/kui-base-list.jsp"%>
   <% 
   String name = request.getAttribute("name").toString();
   HashMap<String, Object> map = ReportUtil.getNewsN(name,"7","1");
   String data = map.get("data").toString();
   String sumC = map.get("sumC").toString();
   List<JSONObject> comList =  (List<JSONObject>)(request.getAttribute("comList"));
   %>
<link href="app/research/css/common.css" rel="stylesheet" type="text/css">
<title>单位查询列表</title>
<script type="text/javascript" src="app/research/js/encode.js"></script>
<script type="text/javascript">
document.onkeydown=function(event){
    var e = event || window.event || arguments.callee.caller.arguments[0];           
     if(e && e.keyCode==13){ // enter 键
    	 indexSerachClick();
    }
};
var name = "${name}";
var name1 = EncodeUtf8("${name}");
	$(function() {
		if("${name}"!=""){
			$("#search").val("${name}")
		}
		//加载新闻信息
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
				if(n!=-1){
					titleN = title.substring(0,n)+'<p style="color: red;text-align: left;display:inline; ">'+title.substring(n,n+l1)+'</p>'+title.substring(n+l1,title.length);
				}else{
					titleN = title;
				}
				$("#newsList").append("<li><div class='n_l_box_list_bt'><a target='_blank'  href='"+newsData[i].url+"' >"+titleN+"</a></div>"+
						" <div class='n_l_box_time'>"+newsData[i].date+"</div></li>")
				
	            
			}
		}
		//加载使用单位信息
		var comList = <%=comList %>;
		var l=10
		if(comList.length<10){
			l = comList.length;
		}
		if(comList.length>0){
			$("#comList").append('<div class="title"> <div class="icon_box"><a href="javascript:moreUse()"><p>使用单位信息</p></a></div> '+
					'<a href="javascript:moreUse()" style="width: 300px;text-align: left;">'+
					'<p class="more" style="width: 300px;text-align: right;color: black;">'+
					' 在检验软件为您云检索结果约${comListC}条，查看更多&gt;&gt;</p></a>'
				  +' </div> <div class="Per_info"> <ul class="n_l_box_list" id="comLists">  </ul></div>	');
		}
		for (var i = 0; i < l; i++) {
			var com = comList[i];
			var title = com.com_name;
			var n = title.indexOf(name);
			var l1 = name.length;
			if(n!=-1){
				titleN = title.substring(0,n)+'<p style="color: red;text-align: left;display:inline; ">'+title.substring(n,n+l1)+'</p>'+title.substring(n+l1,title.length);
			}else{
				titleN = title;
			}
			$("#comLists").append('<li>'
					  +'<div class="n_l_box_list_bt">'
					  +'<a href="javascript:detailCom(\''+com.com_id+'\')" id="'+com.com_id+'">'+titleN+' </a>'
					  +'</div><div class="n_l_box_time">特种设备数量'+com.device_count+'台</div>'
					   +' </li>')
		}
		
		//加载维保单位信息
		var maintainComList = ${maintainComList};
		var l=10
		if(maintainComList.length<10){
			l = maintainComList.length;
		}
		if(maintainComList.length>0){
			$("#maintainComList").append('<div class="title"> <div class="icon_box"><a href="javascript:moreMainTain()"><p>维保单位信息</p></a></div> '+
					'<a href="javascript:moreMainTain()" style="width: 300px;text-align: left;">'+
					'<p class="more" style="width: 300px;text-align: right;color: black;">'+
					' 在检验软件为您云检索结果约${maintainComListC}条，查看更多&gt;&gt;</p></a>'
				  +' </div> <div class="Per_info"> <ul class="n_l_box_list" id="maintainComLists">  </ul></div>	');
		}
		for (var i = 0; i < l; i++) {
			var com = maintainComList[i];
			var title = com.com_name;
			var n = title.indexOf(name);
			var l1 = name.length;
			if(n!=-1){
				titleN = title.substring(0,n)+'<p style="color: red;text-align: left;display:inline; ">'+title.substring(n,n+l1)+'</p>'+title.substring(n+l1,title.length);
			}else{
				titleN = title;
			}
			$("#maintainComLists").append('<li>'
					  +'<div class="n_l_box_list_bt">'
					  +'<a href="javascript:detailCom(\''+com.com_id+'\')" id="'+com.com_id+'">'+titleN+' </a>'
					  +'</div><div class="n_l_box_time">特种设备数量'+com.device_count+'台</div>'
					   +' </li>')
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
					td = td + '<a  target="_blank" href="fileupload2/downloadByPath.do?id='+insp.id+'&fileName='+insp.infoName+'">下载('+insp.downTimes+')</a>'
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
					+'	<a  target="_blank" href="fileupload2/downloadCompress.do?id='+insp.id+'&proportion=0&fileName='+insp.infoName+'">下载('+insp.downTimes+')</a>';
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
				+'	<a  target="_blank" href="fileupload2/downloadCompress.do?id='+insp.id+'&proportion=0&fileName='+insp.infoName+'">下载('+insp.downTimes+')</a>';
				if(insp.resourceType=='2'){
					td = td + '/<a href="javascript:showFile(\''+insp.id+'\',\''+insp.infoName+'\',\''+insp.infoType+'\')">预览</a>';
				}
				td = td + '</td><tr>'
					$("#cloadLists").append(td);
				}
			}
		}
		/* 
		 */
	})
	
	function indexSerachClick(){
		if($("#search").val()==""){
			alert("请输入查询条件！")
			return;
		}
		$("body").mask("正在查询，请稍后！");
		window.location.href= $("base").attr("href")+"enterSearchAction/searchAll.do?name="+$("#search").val();
		
	}
	
	function detailCom(com_id){
		
		
		top.$.dialog({
			width : 1000,
			height :800,
			lock : true,
			title : "单位设备信息",
			content : 'url:enterSearchAction/searchDeviceByCom.do?com_id='+com_id,
			data : {
				"window" : window
			}
		}).max();
	}
	function detailMaintainCom(com_id){
		
		
		top.$.dialog({
			width : 1000,
			height :800,
			lock : true,
			title : "设备信息",
			content : 'url:enterSearchAction/searchDeviceByMCom.do?com_id='+com_id,
			data : {
				"window" : window
			}
		}).max();
	}
	function urlNews(url){
		//alert(url)
		top.$.dialog({
			width : 1000,
			height :800,
			lock : true,
			title : "新闻详细信息",
			content : "url:"+url,
			data : {
				"window" : window
			}
		}).max();
	}

	
function moreNews(){
		top.$.dialog({
			width : 1000,
			height :800,
			lock : true,
			title : "更多新闻信息",
			content : 'url:app/research/news_list.jsp?status=add&name='+name1,
			data : {
				"window" : window
			}
		}).max();
	}
function moreUse(){
	top.$.dialog({
		width : 1024,
		height :800,
		lock : true,
		title : "设备信息",
		content : 'url:enterSearchAction/searchAll.do?name='+name1+'&status=add'+
				"&view=app/research/more_usecom_list",
		data : {
			"window" : window
		}
	}).max();
}

function moreMainTain(){
	top.$.dialog({
		width : 1024,
		height :800,
		lock : true,
		title : "设备信息",
		content :'url:enterSearchAction/searchAll.do?name='+name1+'&status=add'+
				"&view=app/research/more_maintaincom_list",
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
            <input id="search" class="text ng-pristine ng-valid" type="text" ng-model="searchText" ng-class="{'promptTextRed':promptTextRed}" ng-focus="promptTextRedFocus()" ng-keyup="keyupClick($event)" placeholder="请输入姓名 / 单位 / 设备 / 等">
            <input class="btn" type="button" onclick="indexSerachClick()">
    </div>
    </div>
	
</div>

<div class="ser_cont">

		<div class="search_box" id="comList">
		<%-- 	<c:if test="${comListC>0}">
		   <div class="title">
			  <div class="icon_box"><a href="javascript:moreUse()"><p>使用单位信息</p></a></div>
			   <a href="javascript:moreUse()" style="width: 300px;text-align: left;">
			  <p class="more" style="width: 300px;text-align: right;color: black;"> 在检验软件为您云检索结果约${comListC}条，查看更多&gt;&gt;</p></a>
		   </div>
		   <div class="Per_info">

	            <ul class="n_l_box_list" >
		            <c:forEach var="com" items="${comList}"  begin="0" end="${comListC<10?comListC:10}">
		              <li>
		                <div class="n_l_box_list_bt">
		                  <a href="javascript:detailCom('${com.com_id}')" id="${com.com_id}">${com.com_name } </a>
		                </div>
		                <div class="n_l_box_time">特种设备数量${com.device_count }台</div>
		              </li>
					</c:forEach>
	            </ul>
			   </div>	
			   	</c:if>	 --%>			
			</div>

	
	<div class="search_box" id="maintainComList">
	<%-- <c:if test="${maintainComListC>0}">
		   <div class="title">
			  <div class="icon_box"><a href="javascript:moreMainTain()"><p>维保单位信息</p></a></div>
			   <a href="javascript:moreMainTain()" style="width: 300px;text-align: left;">
			  <p class="more" style="width: 300px;text-align: right;color: black;"> 在检验软件为您云检索结果约${maintainComListC}条，查看更多&gt;&gt;</p></a>
		   </div>
		   <div class="Per_info">
            <ul class="n_l_box_list" >
	            <c:forEach var="com" items="${maintainComList}"  begin="0" end="${maintainComListC<10?maintainComListC:10}">
	              <li>
	                <div class="n_l_box_list_bt">
	                  <a href="javascript:detailMaintainCom('${com.com_id}')" id="${com.com_id}">${com.com_name } </a>
	                </div>
	                <div class="n_l_box_time">特种设备数量${com.device_count }台</div>
	              </li>
				</c:forEach>
            </ul>

		   </div>	
		   </c:if> --%>				
		</div>


<div class="search_box" id="news">
		   				
		</div>


	<div class="search_box" id="cloadList">
		<%-- 		<c:if test="${cloadList1C>0||cloadList11C>0||cloadList2C>0}">
		   <div class="title">
			  <div class="icon_box"><a href="javascript:detailCloud()"><p>资料</p></a></div>
			  <a href="javascript:detailCloud()" style="width: 350px;text-align: left;">
			  <p class="more" style="width: 350px;text-align: right;color: black;"> 在电子资料云平台为您云检索结果约${(cloadList1C-0)+(cloadList11C-0)+(cloadList2C-0)}条，查看更多&gt;&gt;</p></a>
		   </div>
		   <div class="Per_info">
				
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="per_tab" style="text-align: left;">
					<tr>
						<th>文件名称</th>
						<!-- <th>修改日期</th>
						<th>大小</th> -->
						<th style="width: 100px;">下载</th>
					</tr>
					<c:forEach var="insp" items="${cloadList11}" begin="0" end="${cloadList11C<5?cloadList11C:4}">
						   <tr  style="text-align: left;">
							<td  style="text-align: left;">
							<c:if test="${insp.infoType=='4'}">
								<a href="javascript:openfolder('${insp.id}','${insp.infoName}','${insp.resourceType}')"> ${insp.infoName}</a>
							</c:if>
							<c:if test="${insp.infoType!='4'}">
								${insp.infoName}
							</c:if>
							</td>
							<td  style="text-align: left;">${fn:substring(insp.last_update_date, 0, 10)}</td>
							<td  style="text-align: left;">${insp.infoSize}KB</td>
							<td style="width: 100px;text-align: left;">
							<c:if test="${insp.infoType=='4'}">
								<a href="javascript:openfolder('${insp.id}','${insp.infoName}','${insp.resourceType}')">打开</a>
							</c:if>
							<c:if test="${insp.infoType!='4'}">
								<a  target="_blank" href="fileupload2/downloadByPath.do?id=${insp.id}&fileName=${insp.infoName}">下载</a>
								<c:if test="${insp.resourceType=='2'}">
								/<a href="javascript:showFile('${insp.id}','${insp.infoName}','${insp.infoType}')">预览</a>
							</c:if>
							</c:if>
							
							</td>
					</tr>
			 		</c:forEach>
				 	<c:forEach var="insp" items="${cloadList1}" begin="0" end="${cloadList1C<5?cloadList1C:4}">
						   <tr  style="text-align: left;">
							<td  style="text-align: left;">${insp.infoName}
							</td>
							<td  style="text-align: left;">${fn:substring(insp.last_update_date, 0, 10)}</td>
							<td  style="text-align: left;">${insp.infoSize}KB</td>
							<td style="width: 100px;text-align: left;">
								<a  target="_blank" href="fileupload2/downloadCompress.do?id=${insp.id}&proportion=0&fileName=${insp.infoName}">下载</a>
								<c:if test="${insp.resourceType=='2'}">
								/<a href="javascript:showFile('${insp.id}','${insp.infoName}','${insp.infoType}')">预览</a>
							</c:if>
							</td>
					</tr>
			 </c:forEach>
			 <c:forEach var="insp" items="${cloadList2}" begin="0" end="${cloadList2C<5?cloadList2C:4}">
						   <tr  style="text-align: left;">
							<td  style="text-align: left;">${insp.infoName}</td>
							<td  style="text-align: left;">${fn:substring(insp.last_update_date, 0, 10)}</td>
							<td  style="text-align: left;">${insp.infoSize}KB</td>
							<td style="width: 100px;text-align: left;"><a  target="_blank"
							 href="fileupload2/downloadCompress.do?id=${insp.id}&proportion=0&fileName=${insp.infoName}">下载</a>
							 <c:if test="${insp.resourceType=='2'}">
								/<a href="javascript:showFile('${insp.id}','${insp.infoName}','${insp.infoType}')">预览</a>
							</c:if></td>
					</tr>
			 </c:forEach>
					
				</table>

	
		   
		   
		   
		   </div>	
	</c:if>	 --%>
		</div>	




</div>

</body>
</html>