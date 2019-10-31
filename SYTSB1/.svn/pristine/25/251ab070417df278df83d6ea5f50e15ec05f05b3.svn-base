<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*" %> 
<%@taglib uri="http://bpm.khnt.com" prefix="bpm" %>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
	<%
	CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
	User uu = (User)curUser.getSysUser();
	com.khnt.rbac.impl.bean.Employee e = (com.khnt.rbac.impl.bean.Employee)uu.getEmployee();
	String userId=e.getId();//员工ID
	String uId = SecurityUtil.getSecurityUser().getId();//用户ID
%>
<!DOCTYPE html>
<html>
<head>
<title>请休假申请</title>
<% 
	/* 请休假id */
	String leave_id=request.getParameter("leave_id");
    if(leave_id==null){
    	leave_id=(String)session.getAttribute("leave_id");
    }
	/* 用户id */
	String id=request.getParameter("id");
    if(id==null){
    	id=(String)session.getAttribute("id");
    }
    /* add页面步骤 */
    String step=request.getParameter("step");
    /* 是否公务外出 */
    String allow=request.getParameter("allow");
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
<link rel="stylesheet" href="${pageContext.request.contextPath}/app/weixin/js/timejs/common.css"/>
<link rel="stylesheet" href="app/k/jqm/skins/default.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/app/weixin/js/timejs/date.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/app/weixin/js/timejs/iscroll.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/app/weixin/js/timejs/mobiscroll.2.13.2.js"></script>
<link href="${pageContext.request.contextPath}/app/weixin/js/timejs/mobiscroll.2.13.2.css" rel="stylesheet" />
<script src="app/k/jqm/a-main.js" type="text/javascript"></script>
<script type="text/javascript">
var flowId='';
var param='';
  $(function(){
	    var leave_id="<%=leave_id %>";
	    var id = "<%=id%>";
	    <%
			if("0".equals(allow)){
		%>
			getcodetabl("TJY2_BG_LEAVE_TYPE","leaveType");
		<%
			}else if("1".equals(allow)){
		%>
	  	getcodetabl("TJY2_RL_LEAVE_TYPE","leaveType");
	  	<%
			}
		%>
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
		if(id!="null"){
			$("#ztsq").hide();
			$.ajax({
				url : "${pageContext.request.contextPath}/WxLeaveAction/loadUser.do?id="+id,
				type : "POST",
				async : false,
				success : function(resp) {		
					$("#peopleName").val(resp.peopleName);
					$("#depName").val(resp.workDepartmentName);
					$("#peopleNameAnddepName").val(resp.peopleName+"（"+resp.workDepartmentName+"）");
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
			    	if(applyStatus=="未提交" || applyStatus=="已撤销" || applyStatus=="审批不通过" || applyStatus=="审批通过" ||applyStatus=="已销假"){
						$("#next2").hide();
					}else{
						$.ajax({
							url : "${pageContext.request.contextPath}/WxLeaveAction/getCheckName.do?id="+res.data.id+"&orgId="+res.data.depId,
							type : "post",
							datatype : "json",
							contentType: "application/json; charset=utf-8",
					        success : function (data) {
					        	if(data.name!=""&&data.name!="undefined"&&data.name!=null){
					        		$("#applyStatus").html(applyStatus+"("+data.name+")");
					        	}
					        }
						});
						$("#next2").show();
					}
		    });
		}else{
			$("#next2").hide();
		}
		
		//提交表单
		$("#next1").click(function() {
			removeSave();
			saveInfo();
		});
		//撤销申请
		$("#next2").click(function() {
			$.ajax({
				url : "${pageContext.request.contextPath}/WxLeaveAction/revokeLeave.do?id="+'<%=leave_id%>',
				type : "post",
				datatype : "json",
				contentType: "application/json; charset=utf-8",
		        success : function (data) {
		        	if(data.success){
		        		 $("#next1").hide();
		        		 $("#next2").hide();
		        		 location.href="${pageContext.request.contextPath}/app/humanresources/wx_leave/leave_index.jsp";
		        		 dAlert("撤销成功！");
		 				<%-- $("#formObj").transform("detail");        	 
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
		 		       }); --%>     
		        	}else{
		        		dAlert("出错了，撤销失败！");
		        		dialogShow(data.msg, 300, 100);
		        	}
		        }
			});  
		
		});
		//返回
		$("#next3").click(function() {
			if("<%=step%>"=="add"){
				location.href="${pageContext.request.contextPath}/app/humanresources/wx_leave/leave_index.jsp";
			}else if("<%=step%>"=="detail"){
				location.href="${pageContext.request.contextPath}/app/humanresources/wx_leave/leave_list.jsp?id=<%=userId%>";
			}
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
            $('#leaveType').change(function(){
            	var type=$("#leaveType").val();
                if(type=='SHIJ'||type=='TQJ'){
                	$.ajax({
        	        	url: "BgLeaveAction/queryYearDays.do?peopleId="+"<%=userId %>",
        	            type: "POST",
        	            datatype: "json",
        	            contentType: "application/json; charset=utf-8",
        	            success: function (resp) {
        	            	if(resp.success){
        	            		var yearDays=resp.yearDays;
        	            		if(yearDays>0){
        	            			dAlert("你还有"+yearDays+"天年假可休"+"，不能休“事假”或“探亲假”！");
        	            			<%
	        	            			if("0".equals(allow)){
	        	            		%>
	        	            			getcodetabl("TJY2_BG_LEAVE_TYPE","leaveType");
	        	            		<%
	        	            			}else if("1".equals(allow)){
	        	            		%>
	        	            	  		getcodetabl("TJY2_RL_LEAVE_TYPE","leaveType");
	        	            	  	<%
	        	            			}
	        	            		%>
        	            		}
        	            	}else{
        	            		dAlert("获取数据出错！");
        	            	}
        	            },
        	            error: function (data) {
        	            	dAlert("出错了！请重试！");
        	            }
        	        });
                }
 
            })
	
  })
  
  
  function saveInfo(){
	  if($("#leaveType").val()==""||$("#startDate").val()==""||$("#endDate").val()==""||$("#peopleName").val()==""||$("#depName").val()==""||$("#leaveCount1").val()==""||$("#total").val()==""||$("#leaveReason").val()==""){
		    dialogShow("必填项不能为空！", 300, 100);
			addSave();
			return false;
		}
		var leaveType=$("#leaveType").val(); 
		var startDate=$("#startDate").val();      
		var endDate=$("#endDate").val();
		if(!compareDate(startDate,endDate) ){
			var days = "开始时间不能大于结束时间！";
			 dAlert(days); 
			 addSave();
			 return false;
		}
		var leaveCount1=$("#leaveCount1").val();
		var leaveReason=encodeURI(encodeURI($("#leaveReason").val()));
		var total=encodeURI(encodeURI($("#total").val()));
		param="id="+"<%=id%>"+"&leaveType="+leaveType+"&startDate="+startDate+"&endDate="+endDate+"&leaveCount1="+leaveCount1
		         +"&leaveReason="+leaveReason+"&eid="+"<%=id%>"+"&total="+total;
		$.ajax({
			url : "${pageContext.request.contextPath}/WxLeaveAction/mobileSubmit.do?"+param,
			type : "post",
			datatype : "json",
			contentType: "application/json; charset=utf-8",
	        success : function (data) {
	        	if(data.success){        	
	        		 id=data.data;
	        		 $("#next1").hide();				
	        		 location.href="${pageContext.request.contextPath}/app/humanresources/wx_leave/leave_list.jsp?id=<%=userId%>";
	        		 dAlert("提交成功！");
	     		     addSave();
	        		 /* $("#formObj").transform("detail");        	 
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
	 		       });  */    
	        	}else{
	        		dAlert("出错了，提交失败！");
	     		    addSave();
	        		dialogShow(data.msg, 300, 100);
	        	}
	        }
		});  
  }
  function setleaveCount1(){
	  var startDate=$("#startDate").val();
	  var endDate=$("#endDate").val();
	  var leaveType=$("#leaveType").val();
	  if(startDate!=null&&endDate!=null&&startDate!=""&&endDate!=""&&leaveType!=null&&leaveType!=""){
		  if(startDate!=null&&endDate!=null&&startDate!=""&&endDate!=""){
			  if(compareDate(startDate,endDate) ){
			  	countLeave(startDate,endDate,"<%=id%>",leaveType);
			  } else {
				  var days = "开始时间不能大于结束时间！";
				  dAlert(days); 
			  }
		  } else {
			  return false;
		  }
	  }
  }
  
  function checkdate(){
	  var startDate=$("#startDate").val();
	  var endDate=$("#endDate").val();
	  var leaveType=$("#leaveType").val();
	  if(startDate!=null&&endDate!=null&&leaveType!=null&&startDate!=""&&endDate!=""&&leaveType!=""){
		  if(compareDate(startDate,endDate) ){
		  	countLeave(startDate,endDate,"<%=id%>",leaveType);
		  } else {
			  var days = "开始时间不能大于结束时间！";
			  dAlert(days);
		  }
	  } else {
		  return ;
	  }
  }
	
  function compareDate(startDate,endDate){
	  startDate = new Date(startDate.replace(/-/g, "/"));
	  endDate = new Date(endDate.replace(/-/g, "/"));
	  var flag = false ;
	  var time = endDate.getTime() - startDate.getTime();
	  if(time>=0){
		  flag = true ;
	  }
	  return flag ;
  }
  function countLeave(startDate,endDate,peopleId,leaveType){
		
		var leaveType = $("#leaveType").val();
		$.ajax({
			url : "${pageContext.request.contextPath}/BgLeaveAction/countDays.do?peopleId="+peopleId+"&startDate="+$("#startDate").val()+"&endDate="+$("#endDate").val()+"&leaveType="+leaveType,
			type : "POST",
			datatype : "json",
			contentType: "application/json; charset=utf-8",
			success : function (data) {
	        	if(data.success){
	        		$("#leaveCount1").val(data.days);
	        		queryLeave(peopleId,$("#startDate").val());
	        	}else{
	        		dAlert(data.notice);
	        	}
	        } 
		 });
	}
	//获取已请假种类及天数
	function queryLeave(peopleId,startDate){
		if(peopleId!=""&&peopleId!="null"){
			$.ajax({
	        	url: "${pageContext.request.contextPath}/WxLeaveAction/queryLeave.do?peopleId="+peopleId+"&startDate="+startDate,
	            type: "POST",
	            datatype: "json",
	            contentType: "application/json; charset=utf-8",
	            success: function (resp) {
	            	if(resp.success){
	            		$("#total").val(resp.leaveInfo);
	            	}else{
	            		dAlert(resp.leaveInfo);
	            	}
	            },
	            error: function (data) {
	            	dAlert("出错了！请重试！");
	            }
	        }); 
		}else{
			dAlert("人员信息不正确！");
		}
	}	
	
	function removeSave(){
		$("#next1").hide();
		/* $("#next1").click(function() {
			
		}); */
	}
	
	function addSave(){
		$("#next1").show();
		/* $("#next1").click(function() {
			saveInfo();
		}); */
	}
	
  /* function countLeave(startDate,endDate){
	startDate = new Date(startDate.replace(/-/g, "/"));
	endDate = new Date(endDate.replace(/-/g, "/"));
	var leaveType = $("#leaveType").val();
	if(leaveType=="NJ"||leaveType=="SHIJ"){
		$.ajax({
			url : "${pageContext.request.contextPath}/BgLeaveAction/countDays.do?startDate="+$("#startDate").val()+"&endDate="+$("#endDate").val(),
			type : "POST",
			datatype : "json",
			contentType: "application/json; charset=utf-8",
			success : function (data) {
	        	if(data.success){
	        		$("#leaveCount1").val(data.days);
	        	}else{
	        		dAlert(data.notice);
	        	}
	        } 
		 });
	}else{
		var time = endDate.getTime() - startDate.getTime();
		if(time>=0){
			var days = parseInt(time / (1000 * 60 * 60 * 24))+1;
		}else{
			var days = "开始时间不能大于结束时间！";
			dAlert(days);
		}
	}
}
//获取已请假种类及天数
function queryLeave(peopleId,startDate){
	if(peopleId!=""&&peopleId!="null"){
		$.ajax({
        	url: "${pageContext.request.contextPath}/WxLeaveAction/queryLeave.do?peopleId="+peopleId+"&startDate="+startDate,
            type: "POST",
            datatype: "json",
            contentType: "application/json; charset=utf-8",
            success: function (resp) {
            	if(resp.success){
            		$("#total").val(resp.leaveInfo);
            	}else{
            		dAlert(resp.leaveInfo);
            	}
            },
            error: function (data) {
            	$.ligerDialog.alert("出错了！请重试！");
            }
        }); 
	}else{
		$.ligerDialog.alert("人员信息不正确！");
	}
}	 */


</script>
<style>
.wrapper{ margin-top:28%;}
.tr1{ font-size: 18px;}
</style>
</head>
<body >
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
								<td><input type="text" class="form-control" name="peopleNameAnddepName"  id="peopleNameAnddepName" ext_name="姓名" ext_isNull="1" readonly="readonly">
								
								</td>
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
								<td><input type="text" class="form-control demo-test-date demo-test-datetime demo-test-time demo-test-credit" id="startDate" name="startDate" placeholder="时间格式为AAAA-BB-CC" ext_name="假期开始时间" ext_isNull="1" maxLength="18" ></td>
							</tr>
				             <tr class="tr1">
								<td class="tdtext1">结束时间：</td>
								<td><input type="text" class="form-control demo-test-date demo-test-datetime demo-test-time demo-test-credit" id="endDate" name="endDate"  placeholder="时间格式为AAAA-BB-CC" ext_name="假期结束时间" ext_isNull="1" maxLength="18"></td>
							</tr>
							<tr class="tr1">
								<td class="tdtext1">天数：</td>
								<td><input type="text" class="form-control" id="leaveCount1" name="leaveCount1" ext_name="假期天数" ext_isNull="1" maxLength="11" readonly="readonly" onclick="setleaveCount1();"></td>
							</tr>
							<tr class="tr1">
								<td class="tdtext1">请假记录：</td>
						    	<td><textarea type="text" class="form-control" id="total" name="total" ext_name="已请假种类及天数" readonly="readonly" ext_isNull="1" style="height: 60px;"></textarea></td>
							</tr>
				            <tr class="tr1">
								<td class="tdtext1">理由：</td>
								<td>
								 <textarea class="form-control" id="leaveReason"  name="leaveReason"   ext_name="请假理由" ext_isNull="1" style="height: 60px;"></textarea></td>
							</tr>
							<tr class="tr1" id="ztsq">
								<td class="tdtext1">申请状态：</td>
								<td><select class="form-control" id="applyStatus" name="applyStatus" ext_name="请假状态" ext_isNull="1"></select></td>
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
						<div class="">
							<div class="">&nbsp;&nbsp;&nbsp;</div>
							<div class=""><a id="next3" class="button button-block button-rounded button-primary">返回</a></div>
						</div>
					</div>
				</div>
			</section>
	</div>
</body>
</html>

		