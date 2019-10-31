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
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/app/humanresources/wx_leave/02/css/public.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/app/humanresources/wx_leave/02/css/itemList.css">
<script src="${pageContext.request.contextPath}/app/humanresources/wx_leave/02/js/jquery.min.js"></script>
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
%>
<script type="text/javascript">
	
	function to_page(href){
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
            	location.href=href+"&allow="+allow;
            }
        });
	}
	</script>
</head>
<body>
<section id="web">
    <header id="header">
        <img src="${pageContext.request.contextPath}/app/humanresources/wx_leave/02/images/tb.png">
    </header>
    <section id="content">
        <section class="tab">
            <ul>
                <li class="active">待办理</li>
                <li>已办理</li>
                <li>我的申请</li>
            </ul>
        </section>
        <section class="handle block">
            <ul>
            	<%
            	if (undeal_list != null) {
            		for (int i = 0; i < undeal_list.size(); i++) {
            			Map<String, Object> map = undeal_list.get(i);
						%>
						<li>
		                    <a href="javascript:void(0);" onclick="javascript:to_page('${pageContext.request.contextPath}/app/humanresources/wx_leave/02/leave_check.jsp?leave_id=<%=map.get("id") %>')">
		                        <article>
		                            <p><span class="handleTitle"><%=map.get("leave_type") %> :</span><span class="yongcar1"><%=map.get("apply_status") %></span><span class="timer"><%=map.get("people_sign_date") %></span></p>
		                            <p><span><%=map.get("people_name") %></span><span class="department"><%=map.get("dep_name") %></span></p>
		                            <p><small><%=map.get("start_date") %></small>至<small><%=map.get("end_date") %></small><span class="day"><%=map.get("days") %>天</span></p>
		                        </article>
		                        <section class="Icon">
		                            <img src="${pageContext.request.contextPath}/app/humanresources/wx_leave/02/images/Icon_03.png" alt="">
		                        </section>
		                    </a>
		                </li>
						<%
					}
				}
            	%>
            </ul>
        </section>
        <section class="handle">
            <ul>
            	<%
            	if (dealed_list != null) {
            		for (int i = 0; i < dealed_list.size(); i++) {
            			Map<String, Object> map = dealed_list.get(i);
						%>
						<li>
							<a href="javascript:void(0);" onclick="javascript:to_page('${pageContext.request.contextPath}/app/humanresources/wx_leave/02/leave_add.jsp?leave_id=<%=map.get("id") %>&step=detail&allow=1')">
		                        <article>
		                            <p><span class="handleTitle"><%=map.get("leave_type") %> :</span><span class="yongcar1"><%=map.get("apply_status") %></span><span class="timer"><%=map.get("people_sign_date") %></span></p>
		                            <p><span><%=map.get("people_name") %></span><span class="department"><%=map.get("dep_name") %></span></p>
		                            <p><small><%=map.get("start_date") %></small>至<small><%=map.get("end_date") %></small><span class="day"><%=map.get("days") %>天</span></p>
		                        </article>
		                        <section class="Icon">
		                            <img src="${pageContext.request.contextPath}/app/humanresources/wx_leave/02/images/Icon_03.png" alt="">
		                        </section>
		                    </a>
		                </li>
						<%
					}
				}
            	%>
            </ul>
        </section>
        <section class="handle">
            <ul>
                <%
            	if (myleave_list != null) {
            		for (int i = 0; i < myleave_list.size(); i++) {
            			Map<String, Object> map = myleave_list.get(i);
						%>
						<li>
							<a href="javascript:void(0);" onclick="javascript:to_page('${pageContext.request.contextPath}/app/humanresources/wx_leave/02/leave_add.jsp?leave_id=<%=map.get("id") %>&step=detail&allow=1')">
		                        <article>
		                            <p><span class="handleTitle"><%=map.get("leave_type") %> :</span><span class="yongcar1"><%=map.get("apply_status") %></span><span class="timer"><%=map.get("people_sign_date") %></span></p>
		                            <p><span><%=map.get("people_name") %></span><span class="department"><%=map.get("dep_name") %></span></p>
		                            <p><small><%=map.get("start_date") %></small>至<small><%=map.get("end_date") %></small><span class="day"><%=map.get("days") %>天</span></p>
		                        </article>
		                        <section class="Icon">
		                            <img src="${pageContext.request.contextPath}/app/humanresources/wx_leave/02/images/Icon_03.png" alt="">
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
            <a href="javascript:void(0);" onclick="javascript:to_page('${pageContext.request.contextPath}/app/humanresources/wx_leave/02/leave_add.jsp?id=<%=employeeId%>&userId=<%=userId%>&step=add')">申请</a>
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