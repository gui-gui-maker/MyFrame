<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@page import="java.util.HashMap"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page import="util.DownYFileUtil"%>
<head>
 <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>

<script  type="text/javascript" src="app/cloud_platform/down/js/jquery-1.9.1.min.js"></script>
<link href="app/cloud_platform/down/css/common.css" rel="stylesheet" type="text/css">
 <%
 String name = request.getParameter("name");
 HashMap<String,Object> searchList = DownYFileUtil.getListByName(name);
 String data = searchList.get("data").toString();
 HashMap<String, Object> map = DownYFileUtil.getNewsN(name,"7","1");
 String datan = map.get("data").toString();
 String sumCn = map.get("sumC").toString();
 String name1 = map.get("name").toString();
 %>
<title>单位查询列表</title>
<script type="text/javascript">
var p = 1;
var sump =<%=data%>.sumC;
var name = "<%=name%>";
var cloadList1 = <%=data%>.queryInfo;
document.onkeydown=function(event){
    var e = event || window.event || arguments.callee.caller.arguments[0];           
     if(e && e.keyCode==13){ // enter 键
    	 indexSerachClick();
    }
};
var name1 = encodeURI(name);
	$(function() {
		if("<%=name1%>"!=""){
			$("#search").val("<%=name1%>")
		}else if(name!=""){
			$("#search").val(name)
		}
		//加载文件信息
		var n = <%=data%>.queryInfoC;
		
		if(n!="0"){
			$("#cloadList").append('<div class="title"> <div class="icon_box"><a href="javascript:detailCloud()"><p>检索结果</p></a></div>'
					+' </div>'
				    +'<div class="Per_info">'
					+'<table width="100%" border="0" cellspacing="0" cellpadding="0"'+
					' class="per_tab" style="text-align: left;" id="cloadLists">'
					+'<tr>'
					+'<th>文件名称</th>'
					+'<th style="width: 100px;">下载</th>'
					+'</tr></table></div>	');
			var s3 = cloadList1.length;
			if(s3>8){
				s3=8
			}
			for (var i = 0; i < s3; i++) {
				var insp = cloadList1[i];
				var title = insp.infoName;
				var n = title.indexOf(name);
				var l1 = name.length;
				/* if(n!=-1){
					titleN = title.substring(0,n)+'<p style="color: red;text-align: left;display:inline; ">'+title.substring(n,n+l1)+'</p>'+title.substring(n+l1,title.length);
				}else{
					titleN = title;
				} */
				titleN = title;
				var td = '<tr  style="text-align: left;">'
			+'<td  style="text-align: left;">'+titleN
			+'</td>'
			+'<td style="width: 100px;text-align: left;">'
			if(insp.is_db=="1"){
				//磁盘库文件
				td = td + '<a  target="_blank" href="http://kh.scsei.org.cn/fileupload2/downloadByPathWw.do?id='+insp.id+'&fileName='+insp.infoName+'">下载</a>'
				
			}else{

				//非磁盘库文件
				td = td +'	<a  target="_blank" href="http://kh.scsei.org.cn/fileupload2/downloadCompress.do?id='+insp.id+'&proportion=0&fileName='+insp.infoName+'">下载</a>';
				
			}
			
			/* if(insp.resourceType=='2'){
				td = td + '/<a href="javascript:showFile(\''+insp.id+'\',\''+insp.infoName+'\',\''+insp.infoType+'\')">预览</a>';
			} */
			td = td + '</td><tr>'
			$("#cloadLists").append(td);
			
			}
			if(sump>1){
				$("#filePages").show();
				$("#sumC").text(sump+"");
			}
		}
		
		//加载新闻信息
		var newsData = <%=datan %>;
		if(newsData!=null){
			var l = 7;
			if(newsData.length<7){
				l = newsData.length;
			}
			if(newsData.length>0){
				$("#news").append("<div class='title'> <div class='icon_box'><a target='_blank' href='app/cloud_platform/down/news_list.jsp?status=add&name="+name1+"'><p>新闻内容</p></a></div>"+
						" <a   target='_blank' href='app/cloud_platform/down/news_list.jsp?status=add&name="+name1+"' style='width: 300px;text-align: left;'>"+
						 " <p class='more' style='width: 300px;text-align: right;color: black;'> 在网站为您云检索结果约"+<%=sumCn%>+"条，查看更多&gt;&gt;</p></a>"+
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
		
	})

	function moreNews(){
		//alert(1)
		//window.location.href= $("base").attr("href")+'app/cloud_platform/down/news_list.jsp?status=add&name='+name1;
	/* 	top.$.dialog({
			width : 1000,
			height :800,
			lock : true,
			title : "更多新闻信息",
			content : 'app/cloud_platform/down/news_list.jsp?status=add&name='+name1,
			data : {
				"window" : window
			}
		}).max(); */
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
		}else{
			return;
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

	function indexSerachClick(){
		if($("#search").val()==""){
			alert("请输入查询条件！")
			return;
		}
		//$("body").mask("正在查询数据，请稍后！");
		window.location.href= $("base").attr("href")+"app/cloud_platform/down/search_result_list.jsp?name="+encodeURI($("#search").val())+"&spaceType=9";
		
	}
	
	function hoverThis(ss){
		if(p==1&&$(ss).attr("id")=="pre"){
			$(ss).css("color","gray");
		}else if(p==sump&&$(ss).attr("id")=="next"){
			$(ss).css("color","gray");
		}else{
			$(ss).css("color","blue");
		}
		
	}
	function outThis(ss){
		if(p==1&&$(ss).attr("id")=="pre"){
			$(ss).css("color","gray");
		}else if(p==sump&&$(ss).attr("id")=="next"){
			$(ss).css("color","gray");
		}else{
		$(ss).css("color","black");
		}
	}
	function toNextPage(){
		if((p+1)<=sump){
			p= p+1;
			$("#nowPage").html(p);
			refreshData(p-1);
			/* $("body").mask("正在查询，请稍后！");
			 window.location.href=$("base").attr("href")+"app/research/news_list.jsp?status=add&name="+name1+"&page="+n; */
		}
	}
	
	function toPerPage(){
		if(p>1){
			p= p-1;
			$("#nowPage").html(p);
			refreshData(p-1);
			/* $("body").mask("正在查询，请稍后！");
			 window.location.href=$("base").attr("href")+"app/research/news_list.jsp?status=add&name="+name1+"&page="+n; */
			
		}
	}
	
	function refreshData(page){
		var s = page*8;
		var e = page*8+8;
		//alert(s+"---"+e+"--"+cloadList1.length )
		if(cloadList1.length<e){
			e=cloadList1.length;
		}
		
		var td  = "";
		for (var i = s; i < e; i++) {
			var insp = cloadList1[i];
			var title = insp.infoName;
			var n = title.indexOf(name);
			var l1 = name.length;
			if(n!=-1){
				titleN = title.substring(0,n)+'<p style="color: red;text-align: left;display:inline; ">'+title.substring(n,n+l1)+'</p>'+title.substring(n+l1,title.length);
			}else{
				titleN = title;
			}
			td = td + '<tr  style="text-align: left;">'
		+'<td  style="text-align: left;">'+titleN
		+'</td>'
		+'<td style="width: 100px;text-align: left;">'
		+'	<a  target="_blank" href="fileupload2/downloadCompress.do?id='+insp.id+'&proportion=0&fileName='+insp.infoName+'">下载</a>';
		/* if(insp.resourceType=='2'){
			td = td + '/<a href="javascript:showFile(\''+insp.id+'\',\''+insp.infoName+'\',\''+insp.infoType+'\')">预览</a>';
		} */
		td = td + '</td><tr>'
		
	
		
		}
		$("#cloadLists").html("");
		var th = +'<tr>'
		+'<th>文件名称</th>'
		+'<th style="width: 100px;">下载</th>'
		+'</tr>';
		$("#cloadLists").append(th);
		$("#cloadLists").append(td);
	}
	
	
</script>
</head>

<body style="overflow: auto;">


<div class="s_n_bg"></div>

<div class="ser_top">
	
	
	
	<div class="w854">
	
	<div class="ser_t_lo"></div>
	<div class="searchTextbg2">
            <input id="search" class="text ng-pristine ng-valid" type="text" ng-model="searchText" ng-class="{'promptTextRed':promptTextRed}" ng-focus="promptTextRedFocus()" ng-keyup="keyupClick($event)" placeholder="请输入文件名称">
            <input class="btn" type="button" onclick="indexSerachClick()">
    </div>
    </div>
	
</div>

<div class="ser_cont">
	<div class="search_box" id="news">
		   				
	</div>
	<div class="search_box" id="cloadList">
	
		  				
		</div>	




</div>
<div align="center" style="font-size: 16px;color: black;display: none;" id="filePages">
	<span style="cursor:default;" id="pre" onmouseover="hoverThis(this)"  onmouseout="outThis(this)" onclick="toPerPage()" >上一页</span>
	   <span style="margin-right: 20px;margin-left: 20px;font-size: 14px;" >第<span id="nowPage">1</span>页,共<span id="sumC">1</span>页</span>
	   <span style="cursor:default;" id="next" onmouseover="hoverThis(this)"  onmouseout="outThis(this)" onclick="toNextPage()">下一页</span></div>

</body>
</html>