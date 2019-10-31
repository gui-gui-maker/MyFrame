<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.rbac.impl.bean.Employee"%>
<%@page import="java.util.Map"%>
<%@page import="util.WxLeaveUtil"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-capable" content="yes"/>
<meta name="apple-mobile-web-app-status-bar-style" content="black"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/app/humanresources/wx_leave/03/css/public.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/app/humanresources/wx_leave/03/css/itemList.css">
<script src="${pageContext.request.contextPath}/app/humanresources/wx_leave/03/js/jquery.min.js"></script>
<%
	CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
	User user = (User)curUser.getSysUser();
	String userId = user.getId();		//用户ID	
	Employee employee = (Employee)user.getEmployee();
	String employeeId = employee.getId();	//员工ID
	

	// 获取待处理数据
	List<Map<String, Object>> undeal_list = WxLeaveUtil.getUndealLeaveList();
	// 获取已处理数据
	List<Map<String, Object>> dealed_list = WxLeaveUtil.getDealedLeaveList();
	// 获取我申请的数据
	List<Map<String, Object>> myleave_list = WxLeaveUtil.getMyLeaveList();
	//跳转参数
	String skip=request.getParameter("skip");
%>
<script type="text/javascript">
$(function(){
	if("pending"=="<%=skip%>"){
		
	}else if("handled"=="<%=skip%>"){
		document.getElementById("li1").className = "";
		document.getElementById("li2").className = "active";
		document.getElementById("section1").className = "handle";
		document.getElementById("section2").className = "handle block";
	}else if("mine"=="<%=skip%>"){
		document.getElementById("li1").className = "";
		document.getElementById("li3").className = "active";
		document.getElementById("section1").className = "handle";
		document.getElementById("section3").className = "handle block";
	}
})	
	function to_page(href){
		var selectedLi = $(".active").attr("id");
		var skip = "pending";
		if(selectedLi=="li1"){
			skip = "pending";
		}else if(selectedLi=="li2"){
			skip = "handled";
		}else if(selectedLi=="li3"){
			skip = "mine";
		}
		var allow = "0";
    	$.ajax({
        	url: 'employeeBaseAction/getWorkTitle1.do?id=<%=employeeId%>',
            type: "POST",
            datatype: "json",
            contentType: "application/json; charset=utf-8",
            success: function (data) {
            	var workTitle=data.workTitle;
            	if(workTitle!=""&&workTitle!=null&&workTitle!="undefined"){
            		if(workTitle.indexOf("部长")>=0 || workTitle.indexOf("副总工")>=0 || workTitle.indexOf("主持工作")>=0 || workTitle.indexOf("主任")>=0 || workTitle.indexOf("助理")>=0 || workTitle.indexOf("院长")>=0 ){
                    		allow="1";
                    	}
            	}
            	location.href=href+"&allow="+allow+"&skip="+skip;
            }
        });
	}
	</script>
</head>
<body>
<section id="web">
    <header id="header">
        <img src="${pageContext.request.contextPath}/app/humanresources/wx_leave/03/images/tb.png">
    </header>
    <section id="content">
        <section class="tab">
            <ul>
                <li id="li1" class="active">待办理</li>
                <li id="li2" >已办理</li>
                <li id="li3" >我的申请</li>
            </ul>
        </section>
        <section   id="section1" class="handle block">
            <ul>
            	<%
            	if (undeal_list != null) {
            		for (int i = 0; i < undeal_list.size(); i++) {
            			Map<String, Object> map = undeal_list.get(i);
						%>
		                <li>
		                    <a href="javascript:void(0);" onclick="javascript:to_page('${pageContext.request.contextPath}/app/humanresources/wx_leave/03/leave_check.jsp?leave_id=<%=map.get("id") %>')">
		                        <article>
		                            <p class="art_first"><span class="handleTitle"><%=map.get("leave_type") %></span><span class="timer"><%=map.get("people_sign_date") %></span><span class="timer_button"></span></p>
		                            <p class="art_padding clearfix "><span class="art_border"></span><span class="art_name"><%=map.get("people_name") %></span><span class="art_day" style="font-size: 1.2rem;">天</span><small><%=map.get("days") %></small></p>
		                            <p class="art_stamp art_padding"><span class="city"><%=map.get("dep_name") %></span>
		                            	<span class="art_times">
		                            	<span class="day1 day2"><%=map.get("start_date") %> ~ </span><span class="day1 day3"><%=map.get("end_date") %></span>
		                            	</span>
		                            </p>
		                        </article>
		                        <section class="Icon">
		                            <img src="${pageContext.request.contextPath}/app/humanresources/wx_leave/03/images/Icon_03.png" alt="">
		                        </section>
		                    </a>
		                </li>
						<%
					}
				}
            	%>
            </ul>
        </section>
        <section   id="section2" class="handle">
            <ul>
            	<%
            	if (dealed_list != null) {
            		for (int i = 0; i < dealed_list.size(); i++) {
            			Map<String, Object> map = dealed_list.get(i);
						%>
						<li>
		                    <a href="javascript:void(0);" onclick="javascript:to_page('${pageContext.request.contextPath}/app/humanresources/wx_leave/03/leave_add2.jsp?leave_id=<%=map.get("id") %>&step=detail&allow=1')">
		                        <article>
		                            <p class="art_first">
		                            <span class="handleTitle"><%=map.get("leave_type") %></span><span class="timer"><%=map.get("people_sign_date") %></span>
		                            <%
		                            	String apply_status = String.valueOf(map.get("apply_status"));
		                            	if(apply_status.indexOf("审批不通过") != -1){
		                            		%>
		                            		<span class="timer_button timer_button3"><%=apply_status %></span>
		                            		<% 		                            	
		                            	}else if(apply_status.indexOf("审批通过") != -1 || apply_status.indexOf("已销假") != -1){
		                            		%>
		                            		<span class="timer_button"><%=apply_status %></span>
		                            		<% 		                            	
		                            	}else{
		                            		%>
		                            		<span class="timer_button timer_button2"><%=apply_status %></span>
		                            		<% 
		                            	}
		                            %>
		                            </p>
		                            <p class="art_padding clearfix "><span class="art_border"></span><span class="art_name"><%=map.get("people_name") %></span><span class="art_day" style="font-size: 1.2rem;">天</span><small><%=map.get("days") %></small></p>
		                            <p class="art_stamp art_padding"><span class="city"><%=map.get("dep_name") %></span>
		                            	<span class="art_times">
		                            	<span class="day1 day2"><%=map.get("start_date") %> ~ </span><span class="day1 day3"><%=map.get("end_date") %></span>
		                            	</span>
		                            </p>
		                        </article>
		                        <section class="Icon">
		                            <img src="${pageContext.request.contextPath}/app/humanresources/wx_leave/03/images/Icon_03.png" alt="">
		                        </section>
		                    </a>
		                </li>
						<%
					}
				}
            	%>
            </ul>
        </section>
        <section  id="section3" class="handle">
            <ul>
                <%
            	if (myleave_list != null) {
            		for (int i = 0; i < myleave_list.size(); i++) {
            			Map<String, Object> map = myleave_list.get(i);
						%>
						<li>
		                    <a href="javascript:void(0);" onclick="javascript:to_page('${pageContext.request.contextPath}/app/humanresources/wx_leave/03/leave_add2.jsp?leave_id=<%=map.get("id") %>&step=detail&allow=1')">
		                        <article>
		                            <p class="art_first">
		                            <span class="handleTitle"><%=map.get("leave_type") %></span><span class="timer"><%=map.get("people_sign_date") %></span>
		                            <%
		                            	String apply_status = String.valueOf(map.get("apply_status"));
		                            	if(apply_status.indexOf("审批不通过") != -1){
		                            		%>
		                            		<span class="timer_button timer_button3"><%=apply_status %></span>
		                            		<% 		                            	
		                            	}else if(apply_status.indexOf("审批通过") != -1 || apply_status.indexOf("已销假") != -1){
		                            		%>
		                            		<span class="timer_button"><%=apply_status %></span>
		                            		<% 		                            	
		                            	}else{
		                            		%>
		                            		<span class="timer_button timer_button2"><%=apply_status %></span>
		                            		<% 
		                            	}
		                            %>
		                            </p>
		                            <p class="art_padding clearfix "><span class="art_border"></span><span class="art_name"><%=map.get("people_name") %></span><span class="art_day" style="font-size: 1.2rem;">天</span><small><%=map.get("days") %></small></p>
		                            <p class="art_stamp art_padding"><span class="city"><%=map.get("dep_name") %></span>
		                            	<span class="art_times">
		                            	<span class="day1 day2"><%=map.get("start_date") %> ~ </span><span class="day1 day3"><%=map.get("end_date") %></span>
		                            	</span>
		                            </p>
		                        </article>
		                        <section class="Icon">
		                            <img src="${pageContext.request.contextPath}/app/humanresources/wx_leave/03/images/Icon_03.png" alt="">
		                        </section>
		                    </a>
		                </li>
						<%
					}
				}
            	%>
            </ul>
        </section>
    </section>
    <section class="btnBg">
        <section class="btn">
            <%-- <a href="javascript:void(0);" onclick="javascript:to_page('${pageContext.request.contextPath}/app/humanresources/wx_leave/03/leave_add.jsp?id=<%=employeeId%>&userId=<%=userId%>&step=add')">申请</a> --%>
        	<a href="javascript:void(0);" onclick="javascript:to_page('${pageContext.request.contextPath}/app/humanresources/wx_leave/03/leave_add2.jsp?id=<%=employeeId%>&userId=<%=userId%>&step=add')">申请</a>
        </section>
    </section>
</section>
</body>
<script>
    $('.tab ul li').click(function () {
        var index = $(this).index();
        $('.tab ul li').eq(index).addClass('active').siblings().removeClass('active');
        $('.handle').eq(index).addClass('block').siblings().removeClass('block');
    })
</script>
</html>