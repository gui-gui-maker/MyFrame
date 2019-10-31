<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="java.util.HashMap"%>
<%@page import="util.ReportUtil"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@page import="java.util.Date"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.utils.DateUtil"%>
<head pageStatus="${param.status}">
 <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>

    <%@include file="/k/kui-base-list.jsp"%>
   <% 
   String name = request.getParameter("name").toString();
   String page1 = request.getParameter("page");
   HashMap<String, Object> map =  new HashMap<String, Object>();
   if(page1==null){
	   System.out.println("----------page1------"+page1);
	   map = ReportUtil.getNewsN(name,"15","1");
   }else{
	   String start = (Integer.parseInt(page1.toString())-1)*15+1+"";
	   System.out.println("----------page--start------"+start);
	   map = ReportUtil.getNewsN(name,"15",start);
   }
  
   String data = map.get("data").toString();
   String sumC = map.get("sumC").toString();
   int s = Integer.parseInt(sumC);
   int sump = 0;
   if(s%15>0){
	   sump = (s/15)+1;
   }else{
	   sump = s/15;
   }
   %>
<link href="app/research/css/common.css" rel="stylesheet" type="text/css">
<title>单位查询列表</title>

<script type="text/javascript" src="app/research/js/encode.js"></script>
<script type="text/javascript">
var p = "${param.page}"==""?1:("${param.page}"-0);

var name = "${param.name}";
var name1 = EncodeUtf8("${param.name}");
var sump = <%=sump%>;
<%-- if(((("<%=sumC%>"-0)/15)+"").split(".").length=2){
	sump = (((("<%=sumC%>"-0)/15)+"").split(".")[0]-0)+1;
}else{
	sump = ((("<%=sumC%>"-0)/15)+"").split(".")[0]-0;
} --%>
	$(function() {
		if(p==sump){
			$("#next").css("color","gray");
		}
		if(p==1){
			
			$("#pre").css("color","gray");
		}
		if("${name}"!=""){
			$("#search").val("${name}")
		}
		var newsData = <%=data %>;
		
		var l = newsData.length;
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
	
	})
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
			width : 800,
			height :600,
			lock : true,
			title : "设备信息",
			content : 'url:app/research/com_device_list.jsp?status=add&com_id='+com_id,
			data : {
				"window" : window
			}
		});
	}
	function detailMaintainCom(com_id){
		
		
		top.$.dialog({
			width : 800,
			height :600,
			lock : true,
			title : "设备信息",
			content : 'url:app/research/maintaincom_device_list.jsp?status=add&com_id='+com_id,
			data : {
				"window" : window
			}
		});
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
	function toNextPage(n){
		if(n==null&&(p+1)<=sump){
			n= p+1;
			$("body").mask("正在查询，请稍后！");
			 window.location.href=$("base").attr("href")+"app/research/news_list.jsp?status=add&name="+name1+"&page="+n;
		}
	}
	
	function toPerPage(n){
		if(n==null&&p!=1){
			n= p-1;
			$("body").mask("正在查询，请稍后！");
			 window.location.href=$("base").attr("href")+"app/research/news_list.jsp?status=add&name="+name1+"&page="+n;
			
		}
	}
	
</script>
</head>

<body style="overflow: auto;">


<div class="s_n_bg"></div>

<div class="search_box">
		   <div class="title">
			  <div class="icon_box"><a href="#" target="_blank"><p>新闻内容</p></a></div>
		   </div>
		   <div class="Per_info">
            <ul class="n_l_box_list" id="newsList">
             <%-- <c:forEach var="news" items="<%=data %>" begin="0" end="10">
              <li>
                <div class="n_l_box_list_bt">
                  <a href="#" >${news.title}</a>
                </div>
                <div class="n_l_box_time">${news.date}</div>
              </li>
            </c:forEach> --%>

            </ul>
         

	
		   
		   
		   
		   </div>
		   					
		</div>
	<br/>
	<div align="center" style="font-size: 16px;color: black;">
	<span style="cursor:default;" id="pre" onmouseover="hoverThis(this)"  onmouseout="outThis(this)" onclick="toPerPage()" >上一页</span>
	   <span style="margin-right: 20px;margin-left: 20px;font-size: 14px;" >第<%= (page1==null)?1:page1.toString()%>页,共<%= sump%>页</span>
	   <span style="cursor:default;" id="next" onmouseover="hoverThis(this)"  onmouseout="outThis(this)" onclick="toNextPage()">下一页</span></div>

</body>
</html>