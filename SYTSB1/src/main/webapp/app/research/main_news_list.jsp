<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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
   System.out.println("----------------------------------"+name);
   String data = ReportUtil.getMainOther(name); %>
<link href="app/research/css/common.css" rel="stylesheet" type="text/css">
<title>单位查询列表</title>
<script type="text/javascript">
var name = "${name}";
	$(function() {
		if("${name}"!=""){
			$("#search").val("${name}")
		}
		var newsData = <%=data %>;
		
		var l = newsData.length;
		for (var i = 0; i < l; i++) {
			var title = newsData[i].title;
			var n = title.indexOf(name);
			var l1 = name.length;
			var titleN = title.substring(0,n)+'<p style="color: red;text-align: left;display:inline; ">'+title.substring(n,n+l1)+'</p>'+title.substring(n+l1,title.length);
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

</script>
</head>

<body style="overflow: auto;">


<div class="s_n_bg"></div>

<div class="search_box">
		   <div class="title">
			  <div class="icon_box"><a href="#" target="_blank"><p>${param.name}</p></a></div>
		   </div>
		   <div class="Per_info">
            <ul class="n_l_box_list" id="newsList" style="overflow: auto;">
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




</div>

</body>
</html>