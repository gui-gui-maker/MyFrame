<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="util.ReportUtil"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@page import="java.util.Date"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@page import="com.khnt.utils.DateUtil"%>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>
    <%@include file="/k/kui-base-list.jsp"%>
	<link href="app/research/css/common.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="app/weixin/research/css/mui.min.css">
	<link rel="stylesheet" type="text/css" href="app/weixin/research/css/app.css" />
	<style>
		table{
			table-layout:fixed;
		}
		td{  
			word-wrap:break-word;
			text-indent: 5px;
			padding: 5px;
		} 
	</style>
<script type="text/javascript" src="app/research/js/encode.js"></script>
<title>云资料列表</title>
<script type="text/javascript">
var name = "${name}";
	$(function() {
		//加载文件信息
		var n = "${cloadList1C>0||cloadList11C>0||cloadList2C>0}";
		if(n=="true"){
			var cloadList11 = ${cloadList11};
			var s1= cloadList11.length;
				$("#cloadList").append('<div class="title"> <div class="icon_box"><a href="javascript:detailCloud()"><p>资料</p></a></div>'
						+' </div>'
					    +'<div class="Per_info">'
						+'<table width="100%" border="0" cellspacing="0" cellpadding="0"'+
						' class="per_tab" style="text-align: left;" id="cloadLists">'
						+'<tr>'
						+'<th>文件名称</th>'
						+'<th style="width: 100px;">下载</th>'
						+'</tr></table></div>	');
			for (var i = 0; i < s1; i++) {
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
			var cloadList1 = ${cloadList1};
			
				var s2= cloadList1.length;
				for (var i = 0; i < s2; i++) {
					var insp = cloadList1[i];
					var title = insp.infoName;
					var n = title.indexOf(name);
					var l1 = name.length;
					if(n!=-1){
						titleN = title.substring(0,n)+'<p style="color: red;text-align: left;display:inline; ">'+title.substring(n,n+l1)+'</p>'+title.substring(n+l1,title.length);
					}else{
						titleN = title;
					}
					var td =  '<tr  style="text-align: left;">'
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

				var cloadList2 = ${cloadList2};
				var s3 = cloadList2.length;
				for (var i = 0; i < s3; i++) {
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
	})
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


<!-- <div class="ser_cont"> -->
	<div class="mui-content mui-scroll">
		<div class="search_box" id="cloadList"></div> 				
	</div>	
<!-- </div> -->

</body>
</html>