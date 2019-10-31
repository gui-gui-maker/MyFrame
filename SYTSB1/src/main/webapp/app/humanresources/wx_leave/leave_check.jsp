<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*" %> 
<%@taglib uri="http://bpm.khnt.com" prefix="bpm" %>

<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<!DOCTYPE html>
<html>
<head>
<title>请休假申请审核</title>
<!-- 请休假id -->
<% String leave_id=request.getParameter("leave_id");
    if(leave_id==null){
    	leave_id=(String)session.getAttribute("leave_id");
    }
    String intoType = request.getParameter("intoType");
%>
	<%
	CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
	User uu = (User)curUser.getSysUser();
	com.khnt.rbac.impl.bean.Employee e = (com.khnt.rbac.impl.bean.Employee)uu.getEmployee();
	String userId=e.getId();//员工ID
	String uId = SecurityUtil.getSecurityUser().getId();//用户ID
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no"/>
<meta name="apple-mobile-web-app-capable" content="yes"/>
<meta name="apple-mobile-web-app-status-bar-style" content="black"/>
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/app/k/km/lib/app-end.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/app/k/km/lib/kh-mobile.css"/>
<script src="${pageContext.request.contextPath}/app/k/km/lib/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/app/k/km/lib/kh-mobile.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/pub/bpm/js/util.js"></script>
<link rel="stylesheet" href="app/k/jqm/skins/default.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/app/weixin/js/timejs/common.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/app/weixin/js/timejs/date.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/app/weixin/js/timejs/iscroll.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/app/weixin/js/timejs/mobiscroll.2.13.2.js"></script>
<link href="${pageContext.request.contextPath}/app/weixin/js/timejs/mobiscroll.2.13.2.css" rel="stylesheet" />
<script src="app/k/jqm/a-main.js" type="text/javascript"></script>
<script type="text/javascript" src="pub/bpm/js/util.js"></script>
<script type="text/javascript" src="pub/worktask/js/worktask.js"></script>   
<script type="text/javascript">
//jQuery页面载入事件
var serviceId = "${requestScope.serviceId}";//提交数据的id
var activityId = "${requestScope.activityId}";//流程id
var processId = "${requestScope.processId}";//过程
var areaFlag;//改变状态
<bpm:ifPer function="TJY2_LEAVE_BMFZRQZ" activityId = "${requestScope.activityId}">areaFlag="2";</bpm:ifPer>//部门负责人签字
<bpm:ifPer function="TJY2_LEAVE_RSYJ" activityId = "${requestScope.activityId}">areaFlag="3";</bpm:ifPer>//人事意见
<bpm:ifPer function="TJY2_LEAVE_FGLDYJ" activityId = "${requestScope.activityId}">areaFlag="4";</bpm:ifPer>//分管领导意见
<bpm:ifPer function="TJY2_LEAVE_YZYJ" activityId = "${requestScope.activityId}">areaFlag="5";</bpm:ifPer>//院长意见
//公务外出
<bpm:ifPer function="GWWC_BZSH" activityId = "${requestScope.activityId}">areaFlag="2";</bpm:ifPer>//部长审核
<bpm:ifPer function="GWWC_FHLDSH" activityId = "${requestScope.activityId}">areaFlag="4";</bpm:ifPer>//分管领导审核
<bpm:ifPer function="GWWC_YZSH" activityId = "${requestScope.activityId}">areaFlag="5";</bpm:ifPer>//院长审核

var flowId='';
var param='';
  $(function(){
	    var leave_id="<%=leave_id %>";
	  	getcodetabl("TJY2_RL_LEAVE_TYPE","leaveType");
	  	getcodetabl("TJY2_BG_LEAVE_STATUS","applyStatus");
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
		if("<%=intoType%>"=="link"){
	    	$("#next3").hide();
	    }
		if(leave_id!="null"){
			$("#next1").hide();
			$("#next2").hide();
			$("#formObj1").hide();
			$("#ztsq").show();
			$("#formObj").transform("detail");
		    $("#formObj").setValues("${pageContext.request.contextPath}/WxLeaveAction/detail.do?id="+leave_id,function(res){
		    	$("#peopleNameAnddepName").html(res.data.peopleName+"（"+res.data.depName+"）");  
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
			    	if(applyStatus=="已提交" || applyStatus=="审批中"){
			    		$.ajax({
							url : "${pageContext.request.contextPath}/WxLeaveAction/getCheckName.do?id="+res.data.id+"&orgId="+res.data.depId,
							type : "post",
							datatype : "json",
							contentType: "application/json; charset=utf-8",
					        success : function (data) {
					        	if(data.name=="undefined" || data.name=="" || data.name==null){
					        		$("#applyStatus").html(applyStatus);
					        	}else{
					        		$("#applyStatus").html(applyStatus+"("+data.name+")");
					        	}
					        }
						}); 
						$("#next1").show();
						$("#next2").show();
						$("#formObj1").show();
					}else{
					}
		    });  
		    
		}else{
			$("#next1").hide();
			$("#next2").hide();
		}
		
		//审批通过
		$("#next1").click(function() {
			$("#next1").hide();
			$("#next2").hide();
			$("#next3").hide();
			/* if($("#opinion").val()==""){
			    dialogShow("审批意见不能为空！", 300, 100);
				return false;
				} */
			var opinion=encodeURI(encodeURI($("#opinion").val()));
			if($("#leaveType").text()=="公务外出"){
				$.ajax({
					url : "${pageContext.request.contextPath}/WxLeaveAction/subPassGwwc.do?opinion="+opinion+"&leave_id="+"<%=leave_id%>",
					type : "post",
					datatype : "json",
					contentType: "application/json; charset=utf-8",
			        success : function (data) {
			        	location.href="${pageContext.request.contextPath}/app/humanresources/wx_leave/leave_index.jsp";
		        		 dAlert("审核成功！");
			        }
				});
			}else{
				$.ajax({
					url : "${pageContext.request.contextPath}/WxLeaveAction/subPass.do?opinion="+opinion+"&leave_id="+"<%=leave_id%>",
					type : "post",
					datatype : "json",
					contentType: "application/json; charset=utf-8",
			        success : function (data) {
			        	location.href="${pageContext.request.contextPath}/app/humanresources/wx_leave/leave_index.jsp";
		        		 dAlert("审核成功！");
			        }
				});
			}
		});
		//审批不通过
		$("#next2").click(function() {
			var opinion=encodeURI(encodeURI($("#opinion").val()));
			if($("#leaveType").text()=="公务外出"){
				$.ajax({
					url : "${pageContext.request.contextPath}/WxLeaveAction/shbtgGwwc.do?opinion="+opinion+"&leave_id="+"<%=leave_id%>",
					type : "post",
					datatype : "json",
					contentType: "application/json; charset=utf-8",
			        success : function (data) {
			        	location.href="${pageContext.request.contextPath}/app/humanresources/wx_leave/leave_index.jsp";
		        		 dAlert("审核成功！");
			        }
				});
			}else{
				$.ajax({
					url : "${pageContext.request.contextPath}/WxLeaveAction/shbtg.do?opinion="+opinion+"&leave_id="+"<%=leave_id%>",
					type : "post",
					datatype : "json",
					contentType: "application/json; charset=utf-8",
			        success : function (data) {
			        	location.href="${pageContext.request.contextPath}/app/humanresources/wx_leave/leave_index.jsp";
		        		 dAlert("审核成功！");
			        }
				});
			}
		}); 
		//返回
		$("#next3").click(function() {
			location.href="${pageContext.request.contextPath}/app/humanresources/wx_leave/leave_pending.jsp?id=<%=userId%>";
		});
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
</script>
<style>
.wrapper{ margin-top:28%;}
.tr1{ font-size: 18px;}
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
						<input id="depName" name="depName" type="hidden">
						<input id="peopleName" name="peopleName" type="hidden">
						<table border="0" cellpadding="0" cellspacing="0" width="100%" height="" align="">
							<tr class="tr1">
								<td width="100" class="tdtext1">姓名：</td>
								<td><input type="text" class="form-control" name="peopleNameAnddepName"  id="peopleNameAnddepName" ext_name="姓名" ext_isNull="1" readonly="readonly"></td>
							</tr>				
					        <!-- <tr class="tr1">
								<td class="tdtext1">部门：</td>
								<td><input type="text" class="form-control" id="depName" name="depName" ext_name="所在部门" ext_isNull="1" maxLength="18" readonly="readonly"></td>
							</tr> -->
							<tr class="tr1">
								<td class="tdtext1"><b>种类：</b></td>
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
								<td class="tdtext1">天数：</td>
								<td><input type="text" class="form-control" id="leaveCount1" name="leaveCount1" ext_name="假期天数" ext_isNull="1" maxLength="11" readonly="readonly" onclick="setleaveCount1();"></td>
							</tr>
							<tr class="tr1">
								<td class="tdtext1">请假记录：</td>
						    	<td><input type="text" class="form-control" id="total" name="total" ext_name="已请假种类及天数" readonly="readonly"></td>
							</tr>
				            <tr class="tr1">
								<td class="tdtext1">理由：</td>
								<td>
								 <input class="form-control" id="leaveReason"  name="leaveReason"   ext_name="请假理由" ext_isNull="1"></td>
							</tr>
							<tr class="tr1" id="ztsq">
								<td class="tdtext1">申请状态：</td>
								<td><select class="form-control" id="applyStatus" name="applyStatus" ext_name="请假状态" ext_isNull="1"></select></td>
							</tr>
				  </table>
					</form>
					<!-- <form id="formObj1" name="formObj1" method="post" action="" onsubmit="return false;" getAction="" pageStatus="">
						<table border="0" cellpadding="0" cellspacing="0" width="100%" height="" align="">
						<tr class="tr1">
								<td class="tdtext1">审批意见：</td>
								<td>
								 <textarea class="form-control" id="opinion"  name="opinion"   ext_name="审批意见" ext_isNull="1" style="height: 60px;"></textarea></td>
								 <input class="form-control" id="opinion"  name="opinion"   ext_name="审批意见" ext_isNull="1"></td>
							</tr>
							</table>
					</form> -->
					<div id="datePlugin"></div>
				</div>
				<div class="bt1">
						<div class="text-center row">
							<div class=""><a id="next1" class="button button-block button-rounded button-primary">同意</a></div>
							<div >&nbsp;&nbsp;&nbsp;</div>
							<div class=""><a id="next2" class="button button-block button-rounded button-primary">不同意</a></div>
							<div class="">&nbsp;&nbsp;&nbsp;</div>
							<div class=""><a id="next3" class="button button-block button-rounded button-primary">返回</a></div>
						</div>
					</div>
				</div>
			</section>
	</div>
</body>
</html>

		