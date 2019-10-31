<%@page import="com.khnt.rbac.impl.bean.Employee"%>
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
	User user = (User)curUser.getSysUser();
	String userId = user.getId();		//用户ID
	String userName = user.getName();
	Employee employee = (Employee)user.getEmployee();
	String employee_id = employee.getId();//员工ID
	//跳转参数
	String skip=request.getParameter("skip");
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no"/>
<meta name="apple-mobile-web-app-capable" content="yes"/>
<meta name="apple-mobile-web-app-status-bar-style" content="black"/>
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/app/humanresources/wx_leave/03/css/public.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/app/humanresources/wx_leave/03/css/header.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/app/humanresources/wx_leave/03/css/content.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/app/humanresources/wx_leave/03/css/selectFilter.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/app/humanresources/wx_leave/03/css/jquery.notice.css">

<script type="text/javascript" src="${pageContext.request.contextPath}/app/humanresources/wx_leave/03/laydate/laydate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/app/humanresources/wx_leave/03/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/app/humanresources/wx_leave/03/js/selectFilter.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/app/humanresources/wx_leave/03/js/jquery.notice.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/pub/worktask/js/worktask.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/bpm/js/util.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/app/k/km/lib/app-end.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/app/k/km/lib/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/app/k/km/lib/kh-mobile.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/app/k/jqm/a-main.js" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/app/weixin/js/timejs/date.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/app/weixin/js/timejs/iscroll.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/app/weixin/js/timejs/mobiscroll.2.13.2.js"></script>


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
var flag = true;
var flowId='';
var param='';
var applyUserName;
  $(function(){
	  	$('#load').hide();
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
		if(leave_id!="null"&&leave_id!=""&&leave_id!="undefined"){
			$("#next1").hide();
			$("#next2").hide();
			$("#next3").hide();
			$("#ztsq").show();
			$("#formObj").transform("detail");
		    $("#formObj").setValues("${pageContext.request.contextPath}/WxLeaveAction/detail.do?id="+leave_id,function(res){
		    	if(res.data==null || res.data=="null" || res.data=="" || res.data=="undefined"){
		    		$("#next3").text("此申请已不存在，点击返回！");
					document.getElementById("btnGroup").className = "Button1"; 
					$("#next3").show();
		    	}else{
		    		document.getElementById("leaveReason").parentNode.className = "task"; 
		    		document.getElementById("total").parentNode.className = "task";
		    		$("#peopleNameAnddepName").html(res.data.peopleName+"（"+res.data.depName+"）"); 
			    	applyUserName = res.data.peopleName;
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
					 	//alert(applyStatus);
				    	if(applyStatus=="已提交" || applyStatus=="审批中"){
				    		$.ajax({
								url : "${pageContext.request.contextPath}/WxLeaveAction/getCheckName.do?id="+res.data.id+"&orgId="+res.data.depId,
								type : "post",
								datatype : "json",
								contentType: "application/json; charset=utf-8",
						        success : function (data) {
						        	if(data.name=="undefined" || data.name=="" || data.name==null){
						        		$("#applyStatus").html(applyStatus);
						        		$("#next3").text("获取审核人失败，无法处理！");
										document.getElementById("btnGroup").className = "Button1"; 
										$("#next3").show();
						        	}else{
						        		$("#applyStatus").html(applyStatus+"("+data.name+")");
						        		if(data.name=="<%=userName%>"){
						        			$("#next1").show();
											$("#next2").show();
											$("#next3").show();
						        		}else{
						        			$("#next3").text("您不为当前审核人，无法处理！");
											document.getElementById("btnGroup").className = "Button1"; 
											$("#next3").show();
						        		}
						        	}
						        },
						        error: function (resp) {
						        	$("#next3").text("获取审核人失败，无法处理！");
									document.getElementById("btnGroup").className = "Button1"; 
									$("#next3").show();
						        }
							}); 
						}else if(applyStatus=="未提交"){
							$("#next3").text("此申请未提交，不能处理！");
							document.getElementById("btnGroup").className = "Button1"; 
							$("#next3").show();
						}else if(applyStatus=="审批通过" || applyStatus=="审批不通过"){
							$("#next3").text("此申请已处理，无需再处理！");
							document.getElementById("btnGroup").className = "Button1"; 
							$("#next3").show();
						}else if(applyStatus=="已销假"){
							$("#next3").text("此申请已销假，无需处理！");
							document.getElementById("btnGroup").className = "Button1"; 
							$("#next3").show();
						}else if(applyStatus=="已撤销"){
							$("#next3").text("此申请已撤销，无需处理！");
							document.getElementById("btnGroup").className = "Button1"; 
							$("#next3").show();
						}else{
							$("#next3").text("未知数据状态，点击返回！");
							document.getElementById("btnGroup").className = "Button1"; 
							$("#next3").show();
						}
				    	// 申请及审批信息
					    if(res.data.peopleName!=null){
						    $("#peopleSign").html(res.data.peopleName); 
						}else{
							$("#people").hide();
						}
						if(res.data.peopleSignDate!=null){
						    $("#peopleSignDate").html(res.data.peopleSignDate.substring(0,res.data.peopleSignDate.lastIndexOf(":"))); 
						}
						if(res.data.leaveReason!=null){
						    $("#peopleLeaveReason").html(res.data.leaveReason); 
						}
					    if(res.data.ksfzryj!=null){
					    	$("#ksfzryj").html(res.data.ksfzryj); 
					    }else{
					    	$("#ksfzr").attr("class","ad_mind");
					    	$("#one").hide();
					    }
					    if(res.data.ksfzryjSing!=null){
					    	$("#ksfzryjSing").html(res.data.ksfzryjSing); 
					    }else{
							$("#ksfzr").hide();
						}
					    if(res.data.ksfzryjDate!=null){
					    	$("#ksfzryjDate").html(res.data.ksfzryjDate.substring(0,res.data.ksfzryjDate.lastIndexOf(":"))); 
					    }
					    if(res.data.rsyj!=null){
					    	$("#rsyj").html(res.data.rsyj); 
					    }else{
					    	$("#rs").attr("class","ad_mind")
					    	$("#two").hide();
					    }
					    if(res.data.rsyjSign!=null){
					    	$("#rsyjSign").html(res.data.rsyjSign); 
					    }else{
							$("#rs").hide();
						}
					    if(res.data.rsyjDate!=null){
					    	$("#rsyjDate").html(res.data.rsyjDate.substring(0,res.data.rsyjDate.lastIndexOf(":"))); 
					    }
					    if(res.data.fgyldyj!=null){
					    	$("#fgyldyj").html(res.data.fgyldyj); 
					    }else{
					    	$("#fgyld").attr("class","ad_mind")
					    	$("#three").hide();
					    }
					    if(res.data.fgyldyjSign!=null){
					    	$("#fgyldyjSign").html(res.data.fgyldyjSign); 
					    }else{
							$("#fgyld").hide();
						}
					    if(res.data.fgyldyjDate!=null){
					    	$("#fgyldyjDate").html(res.data.fgyldyjDate.substring(0,res.data.fgyldyjDate.lastIndexOf(":"))); 
					    }
					    if(res.data.yzyj!=null){
					    	$("#yzyj").html(res.data.yzyj); 
					    }else{
					    	$("#yz").attr("class","ad_mind")
					    	$("#four").hide();
					    }
					    if(res.data.yzyjSign!=null){
					    	$("#yzyjSign").html(res.data.yzyjSign); 
					    }else{
							$("#yz").hide();
						}
					    if(res.data.yzyjDate!=null){
					    	$("#yzyjDate").html(res.data.yzyjDate.substring(0,res.data.yzyjDate.lastIndexOf(":"))); 
					    }
		    	}
		    });  
		    
		}else{
			$("#next1").hide();
			$("#next2").hide();
			$("#formObj").transform("detail");
			$("#next3").text("无法获取数据，点击返回！");
			document.getElementById("btnGroup").className = "Button1"; 
			$("#next3").show();
		}
		
		<%-- //审批通过
		$("#next1").click(function() {
			$("#next1").hide();
			$("#next2").hide();
			$("#next3").hide();
			/* if($("#opinion").val()==""){
			    dialogShow("审批意见不能为空！", 300, 100);
				return false;
				} */
				
			$('#load').show();
			var opinion=encodeURI(encodeURI($("#opinion").val()));
			if($("#leaveType").text()=="公务外出"){
				$.ajax({
					url : "${pageContext.request.contextPath}/WxLeaveAction/subPassGwwc.do?opinion="+opinion+"&leave_id="+"<%=leave_id%>",
					type : "post",
					datatype : "json",
					contentType: "application/json; charset=utf-8",
			        success : function (data) {
			        	$('#load').hide();
			        	location.href="${pageContext.request.contextPath}/app/humanresources/wx_leave/03/leave_index.jsp";
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
			        	$('#load').hide();
			        	location.href="${pageContext.request.contextPath}/app/humanresources/wx_leave/03/leave_index.jsp";
		        		 dAlert("审核成功！");
			        }
				});
			}
		});
		//审批不通过
		$("#next2").click(function() {
			var opinion=encodeURI(encodeURI($("#opinion").val()));
			
			$('#load').show();
			if($("#leaveType").text()=="公务外出"){
				$.ajax({
					url : "${pageContext.request.contextPath}/WxLeaveAction/shbtgGwwc.do?opinion="+opinion+"&leave_id="+"<%=leave_id%>",
					type : "post",
					datatype : "json",
					contentType: "application/json; charset=utf-8",
			        success : function (data) {
			        	$('#load').hide();
			        	location.href="${pageContext.request.contextPath}/app/humanresources/wx_leave/03/leave_index.jsp";
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
			        	$('#load').hide();
			        	location.href="${pageContext.request.contextPath}/app/humanresources/wx_leave/03/leave_index.jsp";
		        		 dAlert("审核成功！");
			        }
				});
			}
		}); 
		//返回
		$("#next3").click(function() {
			location.href="${pageContext.request.contextPath}/app/humanresources/wx_leave/03/leave_index.jsp?id=<%=employee_id%>";
		}); --%>
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
  
  function to_page(url){
	  location.href = url+"?skip=<%=skip%>";
	}
</script>
</head>
<body>
<form id="formObj" name="formObj" method="post" action="" onsubmit="return false;" getAction="" pageStatus="">
<input id="depName" name="depName" type="hidden">
<input id="peopleName" name="peopleName" type="hidden">
<section id="web" class="holiday">
	<header id="header"> <img src="${pageContext.request.contextPath}/app/humanresources/wx_leave/03/images/tb.png"> </header>
	<section id="content">
		<section class="department">
			<section class="departmentCenter">
				<section class="top"><strong><b>*</b>姓&#12288;&#12288;名：</strong>
					<!-- <input type="text" name="name" placeholder="" class="task"> -->
					<input type="text" class="task" name="peopleNameAnddepName"  id="peopleNameAnddepName" ext_name="姓名" ext_isNull="1" readonly="readonly">
				</section>
				<section class="top"><strong><b>*</b>种&#12288;&#12288;类：</strong>
					<!-- <select>
						<option>休假</option>
					</select> -->
					<select id="leaveType" name="leaveType" ext_name="假期种类" ext_isNull="1" maxLength="18" ></select>
					<span class="arrow"><img src="${pageContext.request.contextPath}/app/humanresources/wx_leave/03/images/dropDown.png"></span></section>
				<section class="top"><strong><b>*</b>开始时间：</strong>
					<!-- <input type="text" class="task" placeholder="请选择日期" id="test1"> -->
					<input type="text" class="task" id="startDate" name="startDate" placeholder="时间格式为2018-01-01" ext_name="开始时间" ext_isNull="1" maxLength="18" >
				</section>
				<section class="top"><strong><b>*</b>结束时间：</strong>
					<!-- <input type="text" class="task" placeholder="请选择日期" id="test2"> -->
					<input type="text" class="task" id="endDate" name="endDate" placeholder="时间格式为2018-01-01" ext_name="结束时间" ext_isNull="1" maxLength="18">
				</section>
				<section class="top"><strong><b>*</b>天&#12288;&#12288;数：</strong>
					<!-- <input type="text" name="dates" placeholder="" class="task"> -->
					<input type="text" class="task" id="leaveCount1" name="leaveCount1" ext_name="假期天数" ext_isNull="1" maxLength="11" readonly="readonly" onclick="setleaveCount1();">
				</section>
				<section class="top"><strong><b>*</b>请假记录：</strong>
					<!-- <textarea class="task">bbbb</textarea> -->
					<textarea type="text" class="task" id="total" name="total" ext_name="已请假种类及天数" readonly="readonly" ext_isNull="1" style="line-height: 20px;"></textarea>
				</section>
				<section class="top"><strong><b>*</b>理&#12288;&#12288;由：</strong>
					<!-- <textarea class="task">bbbb</textarea> -->
					<textarea class="task" id="leaveReason"  name="leaveReason"   ext_name="请假理由" ext_isNull="1" style="line-height: 20px;"></textarea>
				</section>
				<section class="bottom" id="ztsq"><strong><b>*</b>申请状态：</strong>
					<select class="task" id="applyStatus" name="applyStatus" ext_name="请假状态" ext_isNull="1"></select>
				</section>
						<section class="add_bottom">
							<ul>
								<li id="people">
									<div>
										<div class="add_fir">
											<img
												src="${pageContext.request.contextPath}/app/humanresources/wx_leave/03/images/user.png" />
											<span class="add_distance" id="peopleSign"></span> <span
												class="add_distance">发起申请</span>
										</div>
										<div class="add_sen" id="peopleSignDate"></div>
<!-- 										<div class="add_third"> -->
<!-- 											<span class="add_third2" id="peopleLeaveReason"></span> -->
<!-- 										</div> -->
									</div>
								</li>
								<li  id="ksfzr">
									<div>
										<div class="add_fir">
											<img
												src="${pageContext.request.contextPath}/app/humanresources/wx_leave/03/images/user.png" />
											<span class="add_distance" id="ksfzryjSing"></span> <span
												class="add_distance add_agree">审批</span>
										</div>
										<div class="add_sen" id="ksfzryjDate"></div>
										<div class="add_third" id="one">
											<span class="add_third2" id="ksfzryj"></span>
										</div>
									</div>
								</li>
								<li id="rs">
									<div>
										<div class="add_fir">
											<img
												src="${pageContext.request.contextPath}/app/humanresources/wx_leave/03/images/user.png" />
											<span class="add_distance" id="rsyjSign"></span> <span
												class="add_distance add_agree">审批</span>
										</div>
										<div class="add_sen" id="rsyjDate"></div>
										<div class="add_third" id="two">
											<span class="add_third2" id="rsyj"></span>
										</div>
									</div>
								</li>
								<li  id="fgyld">
									<div>
										<div class="add_fir">
											<img
												src="${pageContext.request.contextPath}/app/humanresources/wx_leave/03/images/user.png" />
											<span class="add_distance" id="fgyldyjSign"></span> <span
												class="add_distance add_agree">审批</span>
										</div>
										<div class="add_sen" id="fgyldyjDate"></div>
										<div class="add_third" id="three">
											<span class="add_third2" id="fgyldyj"></span>
										</div>
									</div>
								</li>
								<li  id="yz">
									<div>
										<div class="add_fir">
											<img
												src="${pageContext.request.contextPath}/app/humanresources/wx_leave/03/images/user.png" />
											<span class="add_distance" id="yzyjSign"></span> <span
												class="add_distance add_agree">审批</span>
										</div>
										<div class="add_sen" id="yzyjDate"></div>
										<div class="add_third" id="four">
											<span class="add_third2" id="yzyj"></span>
										</div>
									</div>
								</li>
							</ul>
						</section>
					</section>
		</section>
	</section>
	<section class="Button3" id = "btnGroup"> 
		<a id="next1" href="javascript:void(0);" onclick="add_name({name:['同意','提交'],empty:true})">同意</a> 
		<a id="next2" href="javascript:void(0);" onclick="add_name({name:['','提交'],empty:false})">不同意</a> 
		<a id="next3" href="javascript:void(0);" onclick="to_page('${pageContext.request.contextPath}/app/humanresources/wx_leave/03/leave_index.jsp')">返回</a> 
	</section>
</section>
<div id="load" align="center"><img src="${pageContext.request.contextPath}/app/humanresources/wx_leave/03/images/loading3.gif" width="39" height="39" align="center" style="margin-top: 50%;"/></div>
</form>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/app/humanresources/wx_leave/03/js/time.js"></script>
<script>
	function add_name(options){
		//张展彬要求去掉审核时的意见弹出窗口
		/* $(".info_err").hide();
		var defaults={
			name:[],
			empty:true
		}
		defaults= options;
			_this = this;
	    	var strhtml = '<div class="maskbou"></div>'
			+'<div class="add_name">'
			+'<label class="pu_colse">x</label>'
			+'<div class="title"><span>请填写处理意见</span></div><div class="cont">'
			+'<div class="int_mo"><textarea id="moc_int">'+defaults.name[0]+'</textarea></div></div>'
			+'<div class="infobtn"><button class="btn_one " onclick="cback(2)" data-name="取消">取消</button><button class="btn_two mleft12" onclick="cback(1)" data-name="下一步">'+defaults.name[1]+'</button></div></div>'
			$("body").append(strhtml);
			_this.cback=function(num){
				if(num==1){
					var tet = $("#moc_int").val();
					if(!defaults.empty && tet ==''){
						alert("亲，不同意时，请填写处理意见！");
						//$(".info_err").show();
						return false;
					}
					if(defaults.empty){
						pass();
					}else{
						disagree();
					}
				}else if(num==2){
					_this.close1();
				}
			}
			_this.close1=function(num){
				$(".maskbou").remove();
	            $('.add_name').remove();
			}
			$(".pu_colse").click(function(){
				_this.close1();
			})
			$("#moc_int").focus(function(){
				$(".info_err").hide();
				$(this).css("border-color","#409abb")
			}) */
			var defaults={
					name:[],
					empty:true
				}
			defaults= options;
			if(defaults.empty){
				pass();
			}else{
				disagree();
			}
			function pass(){
				var opinion=encodeURI(encodeURI("同意"));
				//var opinion = $("#moc_int").val();
				var urlLink = "${pageContext.request.contextPath}/app/humanresources/wx_leave/03/leave_index.jsp?skip=handled";
				if($("#leaveType").text()=="公务外出"){
					if(flag){
						$('#load').show();
						flag=false;
						$.ajax({
							url : "${pageContext.request.contextPath}/WxLeaveAction/subPassGwwc.do?opinion="+opinion+"&leave_id="+"<%=leave_id%>"+"&userId="+"<%=userId%>",
							type : "post",
							datatype : "json",
							contentType: "application/json; charset=utf-8",
					        success : function (data) {
					        	$('#load').hide();
					        	if(data.success){
					        		jQnotice('审核成功！');
						        	location.href=urlLink;
					        	}else{
					        		flag=true;
					        		jQnotice('审核失败！');
					        	}
					        },
							error : function() {
					        	flag=true;
					        	$('#load').hide();
					        	jQnotice('审核失败！');
					        }
						});
					}else{
						alert("数据处理中，请勿多次提交！");
					}
				}else{
					if(flag){
						$('#load').show();
						flag = false;
						$.ajax({
							url : "${pageContext.request.contextPath}/WxLeaveAction/subPass.do?opinion="+opinion+"&leave_id="+"<%=leave_id%>"+"&userId="+"<%=userId%>",
							type : "post",
							datatype : "json",
							contentType: "application/json; charset=utf-8",
					        success : function (data) {
					        	$('#load').hide();
					        	if(data.success){
					        		jQnotice('审核成功！');
						        	location.href=urlLink;
					        	}else{
					        		flag=true;
					        		jQnotice('审核失败！');
					        	}
					        },
					        error : function() {
					        	flag=true;
					        	$('#load').hide();
					        	jQnotice('审核失败！');
					        }
						});
					}else{
						alert("数据处理中，请勿多次提交！");
					}
				}
			}
			
			function disagree(){
				var opinion=encodeURI(encodeURI("不同意"));
				//var opinion = $("#moc_int").val();
				var urlLink = "${pageContext.request.contextPath}/app/humanresources/wx_leave/03/leave_index.jsp?skip=handled";
				if($("#leaveType").text()=="公务外出"){
					if(flag){
						$('#load').show();
						flag = false;
						$.ajax({
							url : "${pageContext.request.contextPath}/WxLeaveAction/shbtgGwwc.do?opinion="+opinion+"&leave_id="+"<%=leave_id%>"+"&userId="+"<%=userId%>",
							type : "post",
							datatype : "json",
							contentType: "application/json; charset=utf-8",
					        success : function (data) {
					        	$('#load').hide();
					        	if(data.success){
					        		jQnotice('审核成功！');
						        	location.href=urlLink;
					        	}else{
					        		flag=true;
					        		jQnotice('审核失败！');
					        	}
					        },
					        error : function() {
					        	flag=true;
					        	$('#load').hide();
					        	jQnotice('审核失败！');
					        }
						});
					}else{
						alert("数据处理中，请勿多次提交！");
					}
				}else{
					if(flag){
						$('#load').show();
						flag = false;
						$.ajax({
							url : "${pageContext.request.contextPath}/WxLeaveAction/shbtg.do?opinion="+opinion+"&leave_id="+"<%=leave_id%>"+"&userId="+"<%=userId%>",
							type : "post",
							datatype : "json",
							contentType: "application/json; charset=utf-8",
					        success : function (data) {
					        	$('#load').hide();
					        	if(data.success){
					        		jQnotice('审核成功！');
						        	location.href=urlLink;
					        	}else{
					        		flag=true;
					        		jQnotice('审核失败！');
					        	}
					        },
					        error : function() {
					        	flag=true;
					        	$('#load').hide();
					        	jQnotice('审核失败！');
					        }
						});
					}else{
						alert("数据处理中，请勿多次提交！");
					}
				}
			}
    }
</script>
</body>
</html>

		