<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="util.ReportUtil"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@page import="java.util.Date"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.utils.DateUtil"%>
<head>
 <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>

    <%@include file="/k/kui-base-list.jsp"%>
<link href="app/research/css/common.css" rel="stylesheet" type="text/css">
<title>单位查询列表</title>
<script type="text/javascript">
var name = "${name}";
	$(function() {
		//加载使用单位信息
		var comList = ${comList};
		var l= comList.length;
		if(comList.length>0){
			$("#comList").append('<div class="title"> <div class="icon_box"><a href="javascript:moreUse()"><p>使用单位信息</p></a></div> '+
					' </div> <div class="Per_info"> <ul class="n_l_box_list" id="comLists">  </ul></div>	');
		}else{
			$("#comList").append('<div class="title"> <div class="icon_box"><a href="javascript:moreUse()"><p>使用单位信息</p></a></div> '+
					'<a href="javascript:moreUse()" style="width: 300px;text-align: left;">'+
					'<p class="more" style="width: 300px;text-align: right;color: black;">'+
					' 在检验软件为您云检索结果约${comListC}条，查看更多&gt;&gt;</p></a>'
				  +' </div> <div class="Per_info"> <ul class="n_l_box_list" id="comLists">   无数据！  </ul></div>	');
		
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
	})
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
		});
	}
</script>
</head>

<body style="overflow: auto;">


<div class="s_n_bg"></div>


<div class="ser_cont">
		<div class="search_box"  id="comList">
		   					
			</div>
	
</div>

</body>
</html>