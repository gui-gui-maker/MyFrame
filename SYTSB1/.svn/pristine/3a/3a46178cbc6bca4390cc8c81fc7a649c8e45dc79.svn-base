<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*" %> 
<%@taglib uri="http://bpm.khnt.com" prefix="bpm" %>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<!DOCTYPE html>
<html>
<head>
<title>重大任务详情</title>
<!-- 重大任务id -->
<% String zdrw_id=request.getParameter("id");
    if(zdrw_id==null){
    	zdrw_id=(String)session.getAttribute("id");
    }
%>
<!-- 用户id -->
<% String id=request.getParameter("eId");
    if(id==null){
    	id=(String)session.getAttribute("eId");
    }
%>
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no"/>
<meta name="apple-mobile-web-app-capable" content="yes"/>
<meta name="apple-mobile-web-app-status-bar-style" content="black"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/app/k/km/lib/app-end.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/app/k/km/lib/kh-mobile.css"/>
<script src="${pageContext.request.contextPath}/app/k/km/lib/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/app/k/km/lib/kh-mobile.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/bpm/js/util.js"></script>
 
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/app/k/km/lib/kh-mobile.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/bpm/js/util.js"></script>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>
<link rel="stylesheet" href="app/k/jqm/skins/default.css"/>
<script src="app/k/jqm/a-main.js" type="text/javascript"></script>
<!-- <script type="text/javascript" charset="utf-8" src="k/jqm/jquery2.js"></script>
<script type="text/javascript" charset="utf-8" src="k/jqm/jquery.mobile.js"></script> -->
<script src="app/k/km/lib/jquery.min.js"></script>
<script src="app/k/km/lib/kh-mobile.js"></script>
<script src="app/k/km/lib/kh-mobile-list.js"></script>
<script type="text/javascript">
var flowId='';
var param='';
  $(function(){
	    var zdrw_id='<%=zdrw_id %>';
	    var eId = '<%=id%>';
	  	getcodetabl("TJY2_BG_RWZT","status");
		function getcodetabl(code,id){   
		   $.ajax({
			url : "${pageContext.request.contextPath}/WxLeaveAction/getcodetabl.do?tablname="+code,
			type : "POST",
			async : false,
			success : function(json) {		
				var	s="";
				if(json.success){
				for(var i in json.data) {	
		
				s+="<option value='"+json.data[i].value+"'>"+json.data[i].name+"</option>";				    				  				
				}
				$("#"+id).html(s);	
				
				}
			} 
		 });
		}
		if(zdrw_id!="null"){
			$("#next1").hide();
			$("#next2").hide();
			$("#formObj").transform("detail");
		    $("#formObj").setValues("${pageContext.request.contextPath}/weighty/Task/detail.do?id="+zdrw_id,function(res){
				var st=res.data.status;
			 	if(st=="WKS"){
			 		$("#next1").show();
			 	}else if(st=="JXZ"){
			 		$("#next2").show();
			 	}
		    	var b=res.data.startTime;
				if(b==null){
				 	  $("#startTime").html();
				 	}else{
				 	 $("#startTime").html(b.substring(b.indexOf("/")+1,b.lastIndexOf(" ")));
				 	}
				 var c=res.data.endTim;
				 	if(b==null){
				 	  $("#endTim").html();
				 	}else{
				 	 $("#endTim").html(c.substring(c.indexOf("/")+1,c.lastIndexOf(" ")));
				 	}
		    });  
		}else{
			/* alert("对不起！你的微信号未与特检平台同步！"); */
		}
		
		//接收任务
		$("#next1").click(function() {
			$.ajax({
				url : "${pageContext.request.contextPath}/weighty/Task/receive.do?id="+zdrw_id+"&eId="+eId,
				type : "post",
				datatype : "json",
				contentType: "application/json; charset=utf-8",
		        success : function (data) {
		        	if(data.success){        	
		        	id=data.data;
		        	$("#next1").hide();				
		        	dAlert("接收成功！");
		 			$("#formObj").transform("detail");        	 
		 		    $("#formObj").setValues("${pageContext.request.contextPath}/weighty/Task/detail.do?id="+zdrw_id,function(res){
		 			var st=res.data.status;
	 				 	if(st=="WKS"){
					 		$("#next1").show();
					 	}else if(st=="JXZ"){
					 		$("#next2").show();
					 	}
		 		    	  var b=res.data.startTime;
		 				 	if(b==null){
		 				 	  $("#startTime").html();
		 				 	}else{
		 				 	 $("#startTime").html(b.substring(b.indexOf("/")+1,b.lastIndexOf(" ")));
		 				 	}
		 				 var c=res.data.endTim;
		 				 	if(b==null){
		 				 	  $("#endTim").html();
		 				 	}else{
		 				 	 $("#endTim").html(c.substring(c.indexOf("/")+1,c.lastIndexOf(" ")));
		 				 	}
		 		       });     
		        	}else{
		        		dAlert("出错了，接收失败！");
		        		//$("body").unmask();
		        		dialogShow(data.msg, 300, 100);
		        	}
		        }
			});  		
		});
		//反馈任务
		$("#next2").click(function() {
			location.href="${pageContext.request.contextPath}/app/weixin/task/zdrwfk_detail.jsp?zdrw_id="+zdrw_id;

		});
  })
  
  
	
</script>
<style>
.wrapper{ margin-top:28%;}

</style>
</head>
<body>
<div id="a-index" class="a-index">
	<div class="header">
		<div class="bg"></div>
		<div class="logo"></div>
		<div class="user">
			<h1>四川省特检院</h1>
			<h2>办公助手-重大任务详情</h2>
		</div>
	</div>
	</div>
<div id="wrapper" class="wrapper">
	<section id="reg1" class="">	
		<div class="container">		
		<div class="page-header" align="center">
			<h1></h1>
		</div>
			<div class="page-panel" id="m-page-panel">
				<form id="formObj" name="formObj" method="post" action="" onsubmit="return false;" getAction="" pageStatus="detail">
					<table border="0" cellpadding="0" cellspacing="0" width="100%" height="" align="">
						<tr class="tr1">
							<td width="100" class="tdtext1">主责任人：</td>
							<td><input type="text" class="form-control" name="mainDutyName"  id="mainDutyName" ext_name="主责任人" ext_isNull="1"></td>
						</tr>				
				        <tr class="tr1">
							<td class="tdtext1">牵头部门：</td>
							<td><input type="text" class="form-control" id="mainDep" name="mainDep" ext_name="牵头部门" ext_isNull="1" maxLength="18"></td>
						</tr>
						<tr class="tr1">
							<td class="tdtext1">牵头领导：</td>
							<td><input type="text" class="form-control" id="mainLeadName" name="mainLeadName" ext_name="牵头部门" ext_isNull="1" maxLength="18"></td>
						</tr>
			            <tr class="tr1">
							<td class="tdtext1">任务开始时间：</td>
							<td><input type="text" class="form-control" id="startTime" name="startTime" placeholder="时间格式为AAAA-BB-CC" ext_name="任务开始时间" ext_isNull="1" maxLength="18" ></td>
						</tr>
			             <tr class="tr1">
							<td class="tdtext1">任务结束时间：</td>
							<td><input type="text" class="form-control" id="finishTime" name="finishTime" placeholder="时间格式为AAAA-BB-CC" ext_name="任务结束时间" ext_isNull="1" maxLength="18" ></td>
						</tr>
			            <tr class="tr1">
							<td class="tdtext1">任务内容：</td>
							<td>
							 <input class="form-control" id="taskContent"  name="taskContent"   ext_isNull="1"></td>
						</tr>
						<tr class="tr1">
							<td class="tdtext1">完成进度：</td>
							<td>
							 <select class="form-control" id="status"  name="status"   ext_isNull="1"></td>
						</tr>
						<tr class="tr1">
							<td class="tdtext1">完成情况：</td>
							<td>
							 <input class="form-control" id="performance"  name="performance"   ext_isNull="1"></td>
						</tr>
			  </table>
		</form>
		</div>
		<div class="bt1">
			<div class="text-center row">
				<div class=""><a id="next1" class="button button-block button-rounded button-primary">接收</a></div>
			</div>
			<div class="text-center row">
				<div class=""><a id="next2" class="button button-block button-rounded button-primary">反馈</a></div>
			</div>
		</div>
		</div>
		</section>
		</div>
</body>
</html>

		