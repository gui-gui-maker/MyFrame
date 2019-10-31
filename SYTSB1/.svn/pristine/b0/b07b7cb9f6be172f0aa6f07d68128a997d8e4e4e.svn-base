<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*" %> 
<%@taglib uri="http://bpm.khnt.com" prefix="bpm" %>
<!DOCTYPE html>
<html>
<head>
<title>重大任务反馈</title>
<!-- 重大任务id -->
<% String zdrw_id=request.getParameter("zdrw_id");
    if(zdrw_id==null){
    	zdrw_id=(String)session.getAttribute("zdrw_id");
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
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>
<link rel="stylesheet" href="app/k/jqm/skins/default.css"/>
<script src="app/k/jqm/a-main.js" type="text/javascript"></script>
<!-- <script type="text/javascript" charset="utf-8" src="k/jqm/jquery2.js"></script>
<script type="text/javascript" charset="utf-8" src="k/jqm/jquery.mobile.js"></script> -->
<script src="app/k/km/lib/jquery.min.js"></script>
<script type="text/javascript">
var flowId='';
var param='';
  $(function(){
	    var zdrw_id='<%=zdrw_id %>';
	  	getcodetabl("TJY2_BG_RWFK","status");
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
// 		if(gzrw_id!="null"){
// 			$("#next1").hide();
// 			$("#formObj").transform("detail");
// 		       $("#formObj").setValues("${pageContext.request.contextPath}/office/ywhbsgzAction/detail.do?id="+gzrw_id,function(res){
// 		    });  
// 		}else{
// 			/* alert("对不起！你的微信号未与特检平台同步！"); */
// 		}
		
		//提交表单`
		$("#next1").click(function() {
			//完成情况
			var feedbackRemark=encodeURI(encodeURI($("#feedbackRemark").val()));
			//未完成原因
			var unfinishedTask=encodeURI(encodeURI($("#unfinishedTask").val()));
			//完成进度
			var status=$("#status").val();
			$.ajax({
				url : "${pageContext.request.contextPath}/task/Feedback/saveWXFK.do?id="+zdrw_id+"&status="+status+"&feedbackRemark="+feedbackRemark+"&unfinishedTask="+unfinishedTask,
				type : "post",
				datatype : "json",
				contentType: "application/json; charset=utf-8",
		        success : function (data) {
		        	if(data.success){        	
		        		id=data.data;
		        		$("#next1").hide();				
		        		dAlert("反馈成功！");
		 				/* $("#formObj").transform("detail");        	 
		 		        $("#formObj").setValues("${pageContext.request.contextPath}/task/Feedback/detail.do?id="+zdrw_id,function(res){}); */
		        		location.href="${pageContext.request.contextPath}/app/weixin/task/zdrw_list.jsp";
		        	}else{
		        		dAlert("出错了，反馈失败！");
		        		//$("body").unmask();
		        		dialogShow(data.msg, 300, 100);
		        	}
		        }
			});  		
		});
		document.getElementById("status").value = "JXZ";
		$("#tr-unfinishedTask").hide();  
		$("#tr-feedbackRemark").show();
  })
  
  function valueChange(){
	  var val = $("#status").val();
	  if(val=="WWC"){
		  $("#tr-feedbackRemark").hide();
	  	  $("#tr-unfinishedTask").show();
	  }else if(val=="YWC" || val=="JXZ"){
		  $("#tr-unfinishedTask").hide();  
		  $("#tr-feedbackRemark").show();
	  }
  }
	
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
			<h2>办公助手-重大任务反馈</h2>
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
							<td class="tdtext1">完成进度：</td>
							<td><select ltype="radioGroup" class="form-control" id="status" name="status" ext_isNull="1" ext_name=""  onchange="valueChange()"></select></td>
						</tr>
			            <tr class="tr1" id="tr-feedbackRemark">
							<td class="tdtext1">完成情况：</td>
							<td><input class="form-control" id="feedbackRemark"  name="feedbackRemark"   ext_isNull=""></td>
						</tr>
						 <tr class="tr1" id="tr-unfinishedTask">
							<td class="tdtext1">未完成原因：</td>
							<td><input class="form-control" id="unfinishedTask"  name="unfinishedTask"   ext_isNull=""></td>
						</tr>
			  </table>
		</form>
		</div>
		<div class="bt1">
			<div class="text-center row">
				<div class=""><a id="next1" class="button button-block button-rounded button-primary">提交</a></div>
			</div>
		</div>
		</div>
		</section>
		</div>
</body>
</html>

		