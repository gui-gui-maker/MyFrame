<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*" %> 
<%@taglib uri="http://bpm.khnt.com" prefix="bpm" %>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%-- <%CurrentSessionUser useres = SecurityUtil.getSecurityUser();
String user=useres.getName();
String orgName=useres.getDepartment().getOrgName();
String userid= useres.getId();
%> --%>
<!DOCTYPE html>
<html>
<head>
<title>请休假申请</title>
<!-- 请休假id -->
<% String leave_id=request.getParameter("leave_id");
    if(leave_id==null){
    	leave_id=(String)session.getAttribute("leave_id");
    }
%>
<!-- 用户id -->
<% String id=request.getParameter("id");
    if(id==null){
    	id=(String)session.getAttribute("id");
    }
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no"/>
<meta name="apple-mobile-web-app-capable" content="yes"/>
<meta name="apple-mobile-web-app-status-bar-style" content="black"/>
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/k/km/lib/app-end.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/k/km/lib/kh-mobile.css"/>
<script src="${pageContext.request.contextPath}/k/km/lib/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/k/km/lib/kh-mobile.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/pub/bpm/js/util.js"></script>
<link rel="stylesheet" href="k/jqm/skins/default.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/app/weixin/js/timejs/common.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/app/weixin/js/timejs/date.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/app/weixin/js/timejs/iscroll.js"></script>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/app/weixin/js/timejs/jquery-1.9.1.js"></script> --%>
    <script type="text/javascript" src="${pageContext.request.contextPath}/app/weixin/js/timejs/mobiscroll.2.13.2.js"></script>
    <link href="${pageContext.request.contextPath}/app/weixin/js/timejs/mobiscroll.2.13.2.css" rel="stylesheet" />

<%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/app/weixin/js/timejs/mobiscroll.custom-2.5.0.min.css"/> --%>
<%-- <script src="${pageContext.request.contextPath}/app/weixin/js/timejs/jquery.mobile-1.3.0.min.js"></script> --%>
<%-- <script src="${pageContext.request.contextPath}/app/weixin/js/timejs/mobiscroll.js"></script> --%>
<%-- <script src="${pageContext.request.contextPath}/app/weixin/js/timejs/jquery-1.9.1.min.js"></script> --%>

<%-- <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/k/jqm/jquery2.js"></script> --%>
<%-- <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/k/jqm/jquery.mobile.js"></script> --%>
<script src="k/jqm/a-main.js" type="text/javascript"></script>
<script type="text/javascript">
var flowId='';
var param='';
  $(function(){
	    var leave_id="<%=leave_id %>";
	    var id = '<%=id%>';
	    //alert(leave_id);
	  	getcodetabl("TJY2_BG_LEAVE_TYPE","leaveType");
	  	getcodetabl("TJY2_BG_LEAVE_STATUS","applyStatus");
		function getcodetabl(code,id){   
			/* alert(1); */
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
// 		 $("#formObj").initForm({
// 			 getSuccess: function (res){
// 					if(res.data==null||res.success=="false"){
// 						alert("对不起！暂时没有你的个人档案");
// 					}
// 			}
//     	});	
		if(id!="null"){
			/* alert(id); */
			$("#ztsq").hide();
			$.ajax({
				url : "${pageContext.request.contextPath}/WxLeaveAction/loadUser.do?id="+id,
				type : "POST",
				async : false,
				success : function(resp) {		
					$("#peopleName").val(resp.peopleName);
					$("#depName").val(resp.workDepartmentName);
				} 
			 });
		}else{
			/* alert("对不起！你的微信号未与特检平台同步！"); */
		}
		if(leave_id!="null"){
			$("#ztsq").show();
			$("#next1").hide();
			$("#formObj").transform("detail");
		    $("#formObj").setValues("${pageContext.request.contextPath}/WxLeaveAction/detail.do?id="+leave_id,function(res){
		    	 var b=res.data.startDate;
				 	if(b==null){
				 	  $("#startDate").html();
				 	}else{
				 	 $("#startDate").html(b.substring(b.indexOf("/")+1,b.lastIndexOf(" ")));
				 	}
				 var c=res.data.endDate;
				 	if(b==null){
				 	  $("#endDate").html();
				 	}else{
				 	 $("#endDate").html(c.substring(c.indexOf("/")+1,c.lastIndexOf(" ")));
				 	}
				 	//状态
				 	var applyStatus=$("#applyStatus").text();
			    	if(applyStatus=="未提交" || applyStatus=="已撤销" || applyStatus=="审核不通过" ||applyStatus=="审核通过"){
						$("#next2").hide();
					}else{
						$("#next2").show();
					}
		    });  
		}else{
			$("#next2").hide();
		}
		
		//提交表单`
		$("#next1").click(function() {
			   if($("#leaveType").val()==""||$("#startDate").val()==""||$("#endDate").val()==""||$("#peopleName").val()==""||$("#depName").val()==""||$("#leaveCount1").val()==""||$("#leaveReason").val()==""){
				    dialogShow("必填项不能为空！", 300, 100);
					return false;
					}
			var leaveType=$("#leaveType").val(); 
			var startDate=$("#startDate").val();      
			var endDate=$("#endDate").val();
			var leaveCount1=$("#leaveCount1").val();
			var leaveReason=encodeURI(encodeURI($("#leaveReason").val()));

// 				$.getJSON("${pageContext.request.contextPath}/bpm/serviceConfig/getFlowServiceConfig.do", {
// 					serviceCode : "TJY2_RL_LEAVE",
// 					orgId : ""
// 				}, function(resp) {
// 					if (resp.success) {
// 						flowId=resp.data[0].flowId;

						param="id="+id+"&leaveType="+leaveType+"&startDate="+startDate+"&endDate="+endDate+"&leaveCount1="+leaveCount1
						         +"&leaveReason="+leaveReason+"&eid="+id;
						$.ajax({
							url : "${pageContext.request.contextPath}/WxLeaveAction/mobileSubmit.do?"+param,
							type : "post",
							datatype : "json",
							contentType: "application/json; charset=utf-8",
					        success : function (data) {
					        	if(data.success){        	
					        		id=data.data;
					        		 $("#next1").hide();				
					        		 dAlert("提交成功！");
					 				$("#formObj").transform("detail");        	 
					 		       $("#formObj").setValues("${pageContext.request.contextPath}/WxLeaveAction/detail.do?id="+id,function(res){
					 		    	  var b=res.data.startDate;
					   				 	if(b==null){
					   				 	  $("#startDate").html();
					   				 	}else{
					   				 	 $("#startDate").html(b.substring(b.indexOf("/")+1,b.lastIndexOf(" ")));
					   				 	}
					   				 var c=res.data.endDate;
					   				 	if(b==null){
					   				 	  $("#endDate").html();
					   				 	}else{
					   				 	 $("#endDate").html(c.substring(c.indexOf("/")+1,c.lastIndexOf(" ")));
					   				 	}
					 		       });     
					        	}else{
					        		dAlert("出错了，提交失败！");
					        		//$("body").unmask();
					        		dialogShow(data.msg, 300, 100);
					        	}
					        }
						});  		

// 					} else {
// 						callback(false, resp.msg);
// 					}
// 				});
		
		});
		//撤销申请
		$("#next2").click(function() {
			//alert('<%=leave_id%>');
			$.ajax({
				url : "${pageContext.request.contextPath}/WxLeaveAction/revokeLeave.do?id="+'<%=leave_id%>',
				type : "post",
				datatype : "json",
				contentType: "application/json; charset=utf-8",
		        success : function (data) {
		        	if(data.success){
		        		 $("#next1").hide();
		        		 $("#next2").hide();
		        		 dAlert("撤销成功！");
		 				$("#formObj").transform("detail");        	 
		 		       $("#formObj").setValues("${pageContext.request.contextPath}/WxLeaveAction/detail.do?id="+'<%=leave_id%>',function(res){
		 		    	  var b=res.data.startDate;
		   				 	if(b==null){
		   				 	  $("#startDate").html();
		   				 	}else{
		   				 	 $("#startDate").html(b.substring(b.indexOf("/")+1,b.lastIndexOf(" ")));
		   				 	}
		   				 var c=res.data.endDate;
		   				 	if(b==null){
		   				 	  $("#endDate").html();
		   				 	}else{
		   				 	 $("#endDate").html(c.substring(c.indexOf("/")+1,c.lastIndexOf(" ")));
		   				 	}
		 		       });     
		        	}else{
		        		dAlert("出错了，撤销失败！");
		        		//$("body").unmask();
		        		dialogShow(data.msg, 300, 100);
		        	}
		        }
			});  
		
		});
// 		$('#startDate').date();
// 		$('#endDate').date();
		


            var curr = new Date().getFullYear();
            var opt={};
			opt.date = {preset : 'date'};
			opt.datetime = {preset : 'datetime'};
			opt.time = {preset : 'time'};

          opt.default = {
				theme: 'android-holo light', //皮肤样式
		        display: 'modal', //显示方式 
		        mode: 'scroller', //日期选择模式
				dateFormat: 'yyyy-mm-dd',
				lang: 'zh',
				showNow: true,
				nowText: "今天",
				stepMinute: 5,
		        startYear: curr - 0, //开始年份
		        endYear: curr + 20 //结束年份
			};
            $('.settings').bind('change', function() {
                var demo = 'datetime';
                if (!demo.match(/select/i)) {
                    $('.demo-test-' + demo).val('');
                }
                $('.demo-test-' + demo).scroller('destroy').scroller($.extend(opt['date'], opt['default']));
                $('.demo').hide();
                $('.demo-' + demo).show();
            });
            $('#demo').trigger('change');

	
  })
  function setleaveCount1(){
	  var startDate=$("#startDate").val();
	  var endDate=$("#endDate").val();
	  if(startDate!=null&&endDate!=null&&startDate!=""&&endDate!=""){
		  countLeave(startDate,endDate);
	  }
  }
  function countLeave(startDate,endDate){
	startDate = new Date(startDate.replace(/-/g, "/"));
	endDate = new Date(endDate.replace(/-/g, "/"));
	var time = endDate.getTime() - startDate.getTime();
	if(time>=0){
		var days = parseInt(time / (1000 * 60 * 60 * 24))+1;
	}else{
		var days = "开始时间不能大于结束时间！";
		dAlert(days);
	}
	$("#leaveCount1").val(days);
}
	
</script>
<style>
.wrapper{ margin-top:28%;}

</style>
</head>
<body>
	<div  id="a-index" class="a-index">
	<div class="header">
		<div class="bg"></div>
		<div class="logo"></div>
		<div class="user">
			<h1>四川省特检院</h1>
			<h2>请休假-请休假申请</h2>
		</div>
	</div>
	</div>
	<div class="settings" style="display:none;">
          <select name="demo" id="demo">
              <option value="date">日期</option>
          </select>
        </div>
	<div id="wrapper" class="wrapper">
			<section id="reg1" class="">	
				<div class="container">		
<!-- 				<div class="page-header" align="center"> -->
<!-- 				</div> -->
				<div class="page-panel" id="m-page-panel">
					<form id="formObj" name="formObj" method="post" action="" onsubmit="return false;" getAction="" pageStatus="">
						<table border="0" cellpadding="0" cellspacing="0" width="100%" height="" align="">
							<tr class="tr1">
								<td width="100" class="tdtext1">姓名：</td>
								<td><input type="text" class="form-control" name="peopleName"  id="peopleName" ext_name="姓名" ext_isNull="1" readonly="readonly"></td>
							</tr>				
					        <tr class="tr1">
								<td class="tdtext1">所在部门：</td>
								<td><input type="text" class="form-control" id="depName" name="depName" ext_name="所在部门" ext_isNull="1" maxLength="18" readonly="readonly"></td>
							</tr>
							<tr class="tr1">
								<td class="tdtext1">假期种类：</td>
								<td><select class="form-control" id="leaveType" name="leaveType" ext_name="假期种类" ext_isNull="1" maxLength="18" ></select></td>
							</tr>
				            <tr class="tr1">
								<td class="tdtext1">开始时间：</td>
								<td><input type="text" class="form-control demo-test-date demo-test-datetime demo-test-time demo-test-credit" id="startDate" name="startDate" placeholder="时间格式为AAAA-BB-CC" ext_name="会议开始时间" ext_isNull="1" maxLength="18" ></td>
							</tr>
				             <tr class="tr1">
								<td class="tdtext1">结束时间：</td>
								<td><input type="text" class="form-control demo-test-date demo-test-datetime demo-test-time demo-test-credit" id="endDate" name="endDate" placeholder="时间格式为AAAA-BB-CC" ext_name="会议结束时间" ext_isNull="1" maxLength="18"></td>
							</tr>
							<tr class="tr1">
								<td class="tdtext1">假期天数：</td>
								<td><input type="text" class="form-control" id="leaveCount1" name="leaveCount1" ext_name="假期天数" ext_isNull="1" maxLength="11" onclick="setleaveCount1();"></td>
							</tr>
							<!-- <tr class="tr1">
								<td class="tdtext1">已请假种类及天数：</td>
						    	<td><select class="form-control" id="total" name="total"  ext_name="是否需要制作座牌" ></select></td>
							</tr> -->
				            <tr class="tr1">
								<td class="tdtext1">请假理由：</td>
								<td>
								 <input class="form-control" id="leaveReason"  name="leaveReason"   ext_name="请假理由" ext_isNull="1"></td>
							</tr>
							<tr class="tr1" id="ztsq">
								<td class="tdtext1">申请状态：</td>
								<td><select class="form-control" id="applyStatus" name="applyStatus" ext_name="请假状态" ext_isNull="1"></td>
							</tr>
				  </table>
					</form>
					<div id="datePlugin"></div>
				</div>
				<div class="bt1">
						<div class="text-center row">
							<div class=""><a id="next1" class="button button-block button-rounded button-primary">提交</a></div>
						</div>
						<div class="text-center row">
							<div class=""><a id="next2" class="button button-block button-rounded button-primary">撤销</a></div>
						</div>
					</div>
				</div>
			</section>
	</div>
</body>
</html>

		