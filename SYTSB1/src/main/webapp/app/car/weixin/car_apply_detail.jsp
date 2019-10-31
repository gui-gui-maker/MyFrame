<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!--系统封装 标签配置-->
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!--spring security 标签配置-->
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="java.util.Date"%>
<%@page import="com.khnt.rbac.impl.bean.Org"%>
<%@page import="com.khnt.rbac.impl.bean.Employee"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Map"%>
<!DOCTYPE html>
<html>
<head>
<title>检验派车</title>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/app/car/weixin/css/public.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/app/car/weixin/css/header.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/app/car/weixin/css/content.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/app/car/weixin/css/selectFilter.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/app/car/weixin/css/jquery.notice.css">

<script type="text/javascript" src="${pageContext.request.contextPath }/app/car/weixin/js/jquery-1.7.min.js"></script>
<script src="${pageContext.request.contextPath}/app/k/km/lib/jquery.min.js"></script>
<%-- <link href="${pageContext.request.contextPath }/app/car/weixin/css/demo.css" rel="stylesheet" media="all" /> --%>
<script type="text/javascript" src="${pageContext.request.contextPath }/app/car/weixin/js/selectFilter.js"></script>
<script src="${pageContext.request.contextPath}/app/k/km/lib/kh-mobile.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath }/app/car/weixin/js/jquery.notice.js"></script>

<!-- 每个页面引入，页面编码、引入js，页面标题 -->
<%-- <%@include file="/k/kui-base-form.jsp"%> --%>
<% 
	String id=request.getParameter("id");
	String data_status=request.getParameter("data_status");
	String optionType=request.getParameter("optionType");
	String businessId=request.getParameter("businessId");
	String businessStatus=request.getParameter("businessStatus");
	//跳转参数
	String skip=request.getParameter("skip");
	if(businessId !=null){
		id=businessId;
		data_status=businessStatus;
		if(businessStatus.equals("6") || businessStatus.equals("98") ||businessStatus.equals("99")){
			optionType="detail";
		}else{
			optionType="check";
		}
	}
	
	CurrentSessionUser cur_user = SecurityUtil.getSecurityUser();
	User user = (User)cur_user.getSysUser();
	Employee emp = (Employee)user.getEmployee();
	
	String orgId = cur_user.getDepartment().getId();
	String orgName = cur_user.getDepartment().getOrgName();
	String empId = emp.getId();
	String uId = user.getId();
	//测试用
// 	String orgId = "100082";//cur_user.getDepartment().getId();
// 	String orgName = "信息中心";//cur_user.getDepartment().getOrgName();
// 	String empId = "402883a046d0c7990146d0c88c3b0001";//emp.getId();
// 	String uId = "402884c4477c9bac01477fe0ae7b001a";//user.getId();

	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	String nowTime = dateFormat.format(new Date());
	
	Map<String, String> roles = cur_user.getRoles();
	String car_apply_check_status = "";
	try {
		for(String roleid : roles.keySet()){
			Object obj = roles.get(roleid);
			if("部门负责人".equals(obj)){
				if(data_status.equals("0")){
					car_apply_check_status = "0";
				}
			}
			if("公务用车申请-办公室负责人审核".equals(obj)){
				if(data_status.equals("0")){
					car_apply_check_status = "0";
				}else if(data_status.equals("1")){
					car_apply_check_status = "1";
				}
				break;
			}
			if("公务用车申请-分管院领导审核".equals(obj)){
				if(data_status.equals("2")){
					car_apply_check_status = "2";
				}
				break;
			}
			if("公务用车申请-车队派车/收车".equals(obj)){
				if(data_status.equals("4")){
					car_apply_check_status = "4";
				}else if(data_status.equals("5")){
					car_apply_check_status = "5";
				}
			}
			if("公务用车申请-车队负责人审核".equals(obj)){
				if(data_status.equals("3")){
					car_apply_check_status = "3";
				}else if(data_status.equals("4")){
					car_apply_check_status = "4";
				}else if(data_status.equals("5")){
					car_apply_check_status = "5";
				}
				break;
			}
			if("系统管理员".equals(obj)){
				car_apply_check_status = "-1";
			}
		}
	} catch (Exception e) {
	%>
		back("opBack");
	<%
	}
%>
<link href="${pageContext.request.contextPath }/app/car/weixin/css/demoe.css" rel="stylesheet" media="all" />
<script type="text/javascript" src="${pageContext.request.contextPath}/app/weixin/js/timejs/date.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/app/weixin/js/timejs/iscroll.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/app/weixin/js/timejs/mobiscroll.2.13.2.js"></script>
<link href="${pageContext.request.contextPath}/app/weixin/js/timejs/mobiscroll.2.13.2.css" rel="stylesheet" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/app/weixin/js/timejs/common.css"/>
<script type="text/javascript">
var flag = true;
$(function(){
	$('#load').hide();
	$('#aqiExplainCClose').hide();
	getOrgList();
	getUserList(document.getElementById("use_dep_id").value);
	getCarList();
	getDriverList()
	if("<%=optionType %>"=="apply"){
		$("#splc_countent").hide();
		getUserList("<%=orgId%>");
		$("#check").hide();
		$("#check2").hide();
		$("#receive").hide();
		$("#assigns").hide();
		$("#detail").hide();
		$("#out_date").removeClass();
		$("#back_date").removeClass();
		document.getElementById("use_dep_id").value = "<%=orgId%>";
		document.getElementById("use_dep_name").value = "<%=orgName%>";
		document.getElementById("use_user_id").value = "<%=uId%>";
		document.getElementById("use_user_name").value = "<%=user.getName()%>";
		document.getElementById("use_user_phone").value = "<%=emp.getMobileTel()%>";
// 		document.getElementById("use_user_name").value = "zhangs";
// 		document.getElementById("use_user_phone").value = "15928453039";
// 		$("#dep_deal_remark").attr('readonly', true);
// 		$("#office_deal_remark").attr('readonly', true);
// 		$("#leader_deal_remark").attr('readonly', true);
// 		$("#fleet_deal_remark").attr('readonly', true);
		
		$("#fk_car_id").attr('disabled', true);
		$("#driver_user_id").attr('disabled', true);
		$("#out_date").attr('readonly', true);
		$("#start_km").attr('readonly', true);
		$("#back_date").attr('readonly', true);
		$("#end_km").attr('readonly', true);
	}else if("<%=optionType %>"=="detail"){
		$("#apply").hide();
		$("#check").hide();
		$("#receive").hide();
		$("#assigns").hide();
		$("#check2").hide();
		$("#use_start_date").removeClass();
		$("#use_end_date").removeClass();
		$("#out_date").removeClass();
		$("#back_date").removeClass();
		$.ajax({
			url : "${pageContext.request.contextPath }/car/apply/getDetail.do?id=<%=id%>",
			type : "POST",
			async : false,
			success : function(data) {	
				if(data.success){
					var carApply= data.data[0];
					getOrgList();
					getUserList(carApply.use_dep_id,carApply.use_user_id,carApply.use_user_name);
					getCarList();
					$("#formObj").setValues(carApply);
					$("#apply_date").val(carApply.apply_date==null?"":carApply.apply_date.split(" ")[0]);
					$("#use_start_date").val(carApply.use_start_date==null?"":(carApply.use_start_date.indexOf(":")!=-1?carApply.use_start_date.substring(0,carApply.use_start_date.length-3):carApply.use_start_date.split(" ")[0]));
					$("#use_end_date").val(carApply.use_end_date==null?"":(carApply.use_end_date.indexOf(":")!=-1?carApply.use_end_date.substring(0,carApply.use_end_date.length-3):carApply.use_end_date.split(" ")[0]));
					$("#out_date").val(carApply.out_date==null?"":carApply.out_date.split(" ")[0]);
					$("#back_date").val(carApply.back_date==null?"":carApply.back_date.split(" ")[0]);
					$("#apply_date").attr('readonly', true);
					$("#use_start_date").attr('readonly', true);
					$("#use_end_date").attr('readonly', true);
					$("#use_dep_id").attr('disabled', true);
					$("#use_user_id").attr('disabled', true);
					$("#use_user_phone").attr('readonly', true);
					$("#apply_reason").attr('readonly', true);
					$("#drive_route").attr('readonly', true);
					$("#passengers_count").attr('readonly', true);
					$("#need_driver").attr('readonly', true);
					$("#apply_remark").attr('readonly', true);
// 					$("#dep_deal_remark").attr('readonly', true);
// 					$("#office_deal_remark").attr('readonly', true);
// 					$("#leader_deal_remark").attr('readonly', true);
// 					$("#fleet_deal_remark").attr('readonly', true);
					$("#fk_car_id").attr('disabled', true);
					$("#driver_user_id").attr('disabled', true);
					$("#out_date").attr('readonly', true);
					$("#start_km").attr('readonly', true);
					$("#back_date").attr('readonly', true);
					$("#end_km").attr('readonly', true);
				}
			} 
		 });
	}else if("<%=optionType %>"=="check"){
		$("#use_start_date").removeClass();
		$("#use_end_date").removeClass();
		$("#apply").hide();
		$("#detail").hide();
		$.ajax({
			url : "${pageContext.request.contextPath }/car/apply/getDetail.do?id=<%=id%>",
			type : "POST",
			async : false,
			success : function(data) {	
				if(data.success){
					var carApply= data.data[0];
					if(carApply==null){
						$("#apply").hide();
						$("#check").hide();
						$("#receive").hide();
						$("#assigns").hide();
						$("#check2").hide();
						$("#detail").show();
						$("#a_back").text("此申请已不存在，点击返回！");
						$("#apply_date").attr('readonly', true);
						$("#use_start_date").attr('readonly', true);
						$("#use_end_date").attr('readonly', true);
						$("#use_dep_id").attr('disabled', true);
						$("#use_user_id").attr('disabled', true);
						$("#use_user_phone").attr('readonly', true);
						$("#apply_reason").attr('readonly', true);
						$("#drive_route").attr('readonly', true);
						$("#passengers_count").attr('readonly', true);
						$("#need_driver").attr('readonly', true);
						$("#apply_remark").attr('readonly', true);
// 						$("#dep_deal_remark").attr('readonly', true);
// 						$("#office_deal_remark").attr('readonly', true);
// 						$("#leader_deal_remark").attr('readonly', true);
// 						$("#fleet_deal_remark").attr('readonly', true);
						$("#fk_car_id").attr('disabled', true);
						$("#driver_user_id").attr('disabled', true);
						$("#out_date").attr('readonly', true);
						$("#start_km").attr('readonly', true);
						$("#back_date").attr('readonly', true);
						$("#end_km").attr('readonly', true);
					}else{
						getOrgList();
						getUserList(carApply.use_dep_id,carApply.use_user_id,carApply.use_user_name);
						getCarList();
						$("#formObj").setValues(carApply);
						$("#apply_date").val(carApply.apply_date==null?"":carApply.apply_date.split(" ")[0]);
						$("#use_start_date").val(carApply.use_start_date==null?"":(carApply.use_start_date.indexOf(":")!=-1?carApply.use_start_date.substring(0,carApply.use_start_date.length-3):carApply.use_start_date.split(" ")[0]));
						$("#use_end_date").val(carApply.use_end_date==null?"":(carApply.use_end_date.indexOf(":")!=-1?carApply.use_end_date.substring(0,carApply.use_end_date.length-3):carApply.use_end_date.split(" ")[0]));
						$("#out_date").val(carApply.out_date==null?"":carApply.out_date.split(" ")[0]);
						$("#back_date").val(carApply.back_date==null?"":carApply.back_date.split(" ")[0]);
						$("#apply_date").attr('readonly', true);
						$("#use_start_date").attr('disabled', true);
						$("#use_end_date").attr('disabled', true);
						$("#use_dep_id").attr('disabled', true);
						$("#use_user_id").attr('disabled', true);
						$("#use_user_phone").attr('readonly', true);
						$("#apply_reason").attr('readonly', true);
						$("#drive_route").attr('readonly', true);	
						$("#passengers_count").attr('readonly', true);
						$("#need_driver").attr('disabled', true);
						$("#apply_remark").attr('readonly', true);
						if("<%=car_apply_check_status%>" =="0"){
							if(carApply.data_status=="0"){
								$("#out_date").removeClass();
								$("#back_date").removeClass();
								$("#assigns").hide();
								$("#receive").hide();
								$("#check2").hide();
							}else if(carApply.data_status=="98"){
								$("#out_date").removeClass();
								$("#back_date").removeClass();
								$("#check").hide();
								$("#check2").hide();
								$("#assigns").hide();
								$("#receive").hide();
								$("#detail").show();
								$("#a_back").text("此申请已退回，点击返回!");
							}else if(carApply.data_status=="99"){
								$("#out_date").removeClass();
								$("#back_date").removeClass();
								$("#check").hide();
								$("#check2").hide();
								$("#assigns").hide();
								$("#receive").hide();
								$("#detail").show();
								$("#a_back").text("此申请已作废，点击返回!");
							}else{
								$("#out_date").removeClass();
								$("#back_date").removeClass();
								$("#check").hide();
								$("#check2").hide();
								$("#assigns").hide();
								$("#receive").hide();
								$("#detail").show();
								$("#a_back").text("此申请已处理，点击返回!");
							}
// 							$("#office_deal_remark").attr('readonly', true);
// 							$("#leader_deal_remark").attr('readonly', true);
// 							$("#fleet_deal_remark").attr('readonly', true);
							$("#type_remark").val("dep_deal_remark");
							
							$("#fk_car_id").attr('disabled', true);
							$("#driver_user_id").attr('disabled', true);
							$("#out_date").attr('disabled', true);
							$("#start_km").attr('readonly', true);
							$("#end_km").attr('readonly', true);
							$("#back_date").attr('disabled', true);
						}else if("<%=car_apply_check_status%>" =="1"){
							if(carApply.data_status=="1"){
								$("#out_date").removeClass();
								$("#back_date").removeClass();
								$("#assigns").hide();
								$("#receive").hide();
								$("#check").hide();
								
							}else if(carApply.data_status=="98"){
								$("#out_date").removeClass();
								$("#back_date").removeClass();
								$("#check").hide();
								$("#check2").hide();
								$("#assigns").hide();
								$("#receive").hide();
								$("#detail").show();
								$("#a_back").text("此申请已退回，点击返回!");
							}else if(carApply.data_status=="99"){
								$("#out_date").removeClass();
								$("#back_date").removeClass();
								$("#check").hide();
								$("#check2").hide();
								$("#assigns").hide();
								$("#receive").hide();
								$("#detail").show();
								$("#a_back").text("此申请已作废，点击返回!");
							}else{
								$("#out_date").removeClass();
								$("#back_date").removeClass();
								$("#check").hide();
								$("#check2").hide();
								$("#assigns").hide();
								$("#receive").hide();
								$("#detail").show();
								$("#a_back").text("此申请已处理，点击返回!");
							}
// 							$("#dep_deal_remark").attr('readonly', true);
// 							$("#leader_deal_remark").attr('readonly', true);
// 							$("#fleet_deal_remark").attr('readonly', true);
							$("#type_remark").val("office_deal_remark");

							$("#fk_car_id").attr('disabled', true);
							$("#need_driver").attr('disabled', true);
							$("#driver_user_id").attr('disabled', true);
							$("#out_date").attr('disabled', true);
							$("#start_km").attr('readonly', true);
							$("#end_km").attr('readonly', true);
							$("#back_date").attr('disabled', true);
						}else if("<%=car_apply_check_status%>" =="2"){
							if(carApply.data_status=="2"){
								$("#out_date").removeClass();
								$("#back_date").removeClass();
								$("#assigns").hide();
								$("#check2").hide();
								$("#receive").hide();
							}else if(carApply.data_status=="98"){
								$("#out_date").removeClass();
								$("#back_date").removeClass();
								$("#check").hide();
								$("#check2").hide();
								$("#assigns").hide();
								$("#receive").hide();
								$("#detail").show();
								$("#a_back").text("此申请已退回，点击返回!");
							}else if(carApply.data_status=="99"){
								$("#out_date").removeClass();
								$("#back_date").removeClass();
								$("#check").hide();
								$("#check2").hide();
								$("#assigns").hide();
								$("#receive").hide();
								$("#detail").show();
								$("#a_back").text("此申请已作废，点击返回!");
							}else{
								$("#out_date").removeClass();
								$("#back_date").removeClass();
								$("#check").hide();
								$("#check2").hide();
								$("#assigns").hide();
								$("#receive").hide();
								$("#detail").show();
								$("#a_back").text("此申请已处理，点击返回!");
							}
// 							$("#dep_deal_remark").attr('readonly', true);
// 							$("#office_deal_remark").attr('readonly', true);
// 							$("#fleet_deal_remark").attr('readonly', true);
							$("#type_remark").val("leader_deal_remark");
							
							$("#fk_car_id").attr('disabled', true);
							$("#driver_user_id").attr('disabled', true);
							$("#out_date").attr('disabled', true);
							$("#start_km").attr('readonly', true);
							$("#end_km").attr('readonly', true);
							$("#back_date").attr('disabled', true);
						}else if("<%=car_apply_check_status%>" =="3"){
							if(carApply.data_status=="3"){
								$("#out_date").removeClass();
								$("#back_date").removeClass();
								$("#assigns").hide();
								$("#receive").hide();
								$("#check2").hide();
							}else if(carApply.data_status=="98"){
								$("#out_date").removeClass();
								$("#back_date").removeClass();
								$("#check").hide();
								$("#check2").hide();
								$("#assigns").hide();
								$("#receive").hide();
								$("#detail").show();
								$("#a_back").text("此申请已退回，点击返回!");
							}else if(carApply.data_status=="99"){
								$("#out_date").removeClass();
								$("#back_date").removeClass();
								$("#check").hide();
								$("#check2").hide();
								$("#assigns").hide();
								$("#receive").hide();
								$("#detail").show();
								$("#a_back").text("此申请已作废，点击返回!");
							}else{
								$("#out_date").removeClass();
								$("#back_date").removeClass();
								$("#check").hide();
								$("#check2").hide();
								$("#assigns").hide();
								$("#receive").hide();
								$("#detail").show();
								$("#a_back").text("此申请已处理，点击返回!");
							}
// 							$("#dep_deal_remark").attr('readonly', true);
// 							$("#office_deal_remark").attr('readonly', true);
// 							$("#leader_deal_remark").attr('readonly', true);
							$("#type_remark").val("fleet_deal_remark");
							
							$("#driver_user_id").attr('disabled', true);
							$("#out_date").attr('disabled', true);
							$("#start_km").attr('readonly', true);
							$("#end_km").attr('readonly', true);
							$("#back_date").attr('disabled', true);
						}else if("<%=car_apply_check_status%>" =="4"){
							if(carApply.data_status=="4"){
								if(carApply.need_driver==0){
									$("#driver_user_id").attr('disabled', true);
								}
								$("#back_date").removeClass();
								$("#check").hide();
								$("#check2").hide();
								$("#receive").hide();
							}else if(carApply.data_status=="98"){
								$("#out_date").removeClass();
								$("#back_date").removeClass();
								$("#check").hide();
								$("#check2").hide();
								$("#assigns").hide();
								$("#receive").hide();
								$("#detail").show();
								$("#a_back").text("此申请已退回，点击返回!");
							}else if(carApply.data_status=="99"){
								$("#out_date").removeClass();
								$("#back_date").removeClass();
								$("#check").hide();
								$("#check2").hide();
								$("#assigns").hide();
								$("#receive").hide();
								$("#detail").show();
								$("#a_back").text("此申请已作废，点击返回!");
							}else{
								$("#out_date").removeClass();
								$("#back_date").removeClass();
								$("#check").hide();
								$("#check2").hide();
								$("#assigns").hide();
								$("#receive").hide();
								$("#detail").show();
								$("#a_back").text("此申请已处理，点击返回!");
							}
// 							$("#dep_deal_remark").attr('readonly', true);
// 							$("#office_deal_remark").attr('readonly', true);
// 							$("#leader_deal_remark").attr('readonly', true);
// 							$("#fleet_deal_remark").attr('readonly', true);
							
							$("#end_km").attr('readonly', true);
							$("#back_date").attr('disabled', true);
						}else if("<%=car_apply_check_status%>" =="5"){
							if(carApply.data_status=="5"){
								$("#out_date").removeClass();
								$("#check").hide();
								$("#check2").hide();
								$("#assigns").hide();
							}else if(carApply.data_status=="98"){
								$("#out_date").removeClass();
								$("#back_date").removeClass();
								$("#check").hide();
								$("#check2").hide();
								$("#assigns").hide();
								$("#receive").hide();
								$("#detail").show();
								$("#a_back").text("此申请已退回，点击返回!");
							}else if(carApply.data_status=="99"){
								$("#out_date").removeClass();
								$("#back_date").removeClass();
								$("#check").hide();
								$("#check2").hide();
								$("#assigns").hide();
								$("#receive").hide();
								$("#detail").show();
								$("#a_back").text("此申请已作废，点击返回!");
							}else{
								$("#out_date").removeClass();
								$("#back_date").removeClass();
								$("#check").hide();
								$("#check2").hide();
								$("#assigns").hide();
								$("#receive").hide();
								$("#detail").show();
								$("#a_back").text("此申请已处理，点击返回!");
							}
// 							$("#dep_deal_remark").attr('readonly', true);
// 							$("#office_deal_remark").attr('readonly', true);
// 							$("#leader_deal_remark").attr('readonly', true);
// 							$("#fleet_deal_remark").attr('readonly', true);
							
							$("#fk_car_id").attr('disabled', true);
							$("#driver_user_id").attr('disabled', true);
							$("#out_date").attr('disabled', true);
							$("#start_km").attr('readonly', true);
						}else if("<%=car_apply_check_status%>" =="-1"){
							$("#out_date").removeClass();
							$("#back_date").removeClass();
							$("#check").hide();
							$("#check2").hide();
							$("#assigns").hide();
						    $("#receive").hide();
							$("#detail").show();
// 							$("#dep_deal_remark").attr('readonly', true);
// 							$("#office_deal_remark").attr('readonly', true);
// 							$("#leader_deal_remark").attr('readonly', true);
// 							$("#fleet_deal_remark").attr('readonly', true);

							$("#fk_car_id").attr('disabled', true);
							$("#driver_user_id").attr('disabled', true);
							$("#out_date").attr('disabled', true);
							$("#start_km").attr('readonly', true);
							$("#end_km").attr('readonly', true);
							$("#back_date").attr('disabled', true);
						}else{
							$("#out_date").removeClass();
							$("#back_date").removeClass();
							$("#check").hide();
							$("#check2").hide();
							$("#assigns").hide();
						    $("#receive").hide();
							$("#detail").show();
// 							$("#dep_deal_remark").attr('readonly', true);
// 							$("#office_deal_remark").attr('readonly', true);
// 							$("#leader_deal_remark").attr('readonly', true);
// 							$("#fleet_deal_remark").attr('readonly', true);
							
							$("#fk_car_id").attr('disabled', true);
							$("#driver_user_id").attr('disabled', true);
							$("#out_date").attr('disabled', true);
							$("#start_km").attr('readonly', true);
							$("#back_date").attr('disabled', true);
							$("#end_km").attr('readonly', true);
							$("#a_back").text("此申请已处理，点击返回!");
						}
					}
					
				}else{
					$("#apply").hide();
					$("#check").hide();
					$("#receive").hide();
					$("#assigns").hide();
					$("#check2").hide();
					$("#detail").show();
					$("#a_back").text("数据获取错误，点击返回!");
				}
			} 
		 });
	}
	
	/* var curr = new Date().getFullYear();
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
            $('#demo').trigger('change'); */
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
        $('.demo-test-' + demo).scroller('destroy').scroller($.extend(opt['datetime'], opt['default']));
        $('.demo').hide();
        $('.demo-' + demo).show();
    });
    $('#demo').trigger('change');
    
    
    
    

	$.ajax({
		url : "${pageContext.request.contextPath }/car/apply/getDetail.do?id=<%=id%>",
		type : "POST",
		async : false,
		success : function(data) {	

			var carApply= data.data[0];
			
			if(carApply.apply_user_name!=null){
				$("#splc").append('<li class="ad_mind"><div class="add_fir"><img src="images/user.png" /><span class="add_distance">'+carApply.apply_user_name+'</span>'
						+'<span class="add_distance">发起申请</span>'
					+'</div><div class="add_sen">'+carApply.apply_date.substring(0,16).replace(/\-/g,'.')+'</div>'
					+'<div class="add_third"><span class="add_third2"></span></div></li>');
			}
			if(carApply.dep_deal_name!=null){
				var li="<li>"; add_third='<div class="add_third"><span class="add_third2">'+carApply.dep_deal_remark+'</span></div>';
				if(carApply.dep_deal_remark==null || carApply.dep_deal_remark==""){
					li="<li  class='ad_mind'>";
					add_third="";
				}
				$("#splc").append(li+'<div class="add_fir"><img src="images/user.png" /><span class="add_distance">'+carApply.dep_deal_name+'</span>'
						+'<span class="add_distance">'+yj(carApply.dep_deal_result)+'</span>'
					+'</div><div class="add_sen">'+carApply.dep_deal_date.substring(0,16).replace(/\-/g,'.')+'</div>'
					+add_third+'</li>');
			}
			if(carApply.fleet_deal_name!=null){
				var li="<li>"; add_third='<div class="add_third"><span class="add_third2">'+carApply.fleet_deal_remark+'</span></div>';
				if(carApply.fleet_deal_remark==null || carApply.fleet_deal_remark==""){
					li="<li  class='ad_mind'>";
					add_third="";
				}
				
				$("#splc").append(li+'<div class="add_fir"><img src="images/user.png" /><span class="add_distance">'+carApply.fleet_deal_name+'</span>'
						+'<span class="add_distance">'+yj(carApply.fleet_deal_result)+'</span>'
					+'</div><div class="add_sen">'+carApply.fleet_deal_date.substring(0,16).replace(/\-/g,'.')+'</div>'
					+add_third+'</li>');
			}
			if(carApply.office_deal_name!=null){

				var li="<li>"; add_third='<div class="add_third"><span class="add_third2">'+carApply.office_deal_remark+'</span></div>';
				if(carApply.office_deal_remark==null || carApply.office_deal_remark==""){
					li="<li  class='ad_mind'>";
					add_third="";
				}
				$("#splc").append(li+'<div class="add_fir"><img src="images/user.png" /><span class="add_distance">'+carApply.office_deal_name+'</span>'
						+'<span class="add_distance">'+yj(carApply.office_deal_result)+'</span>'
					+'</div><div class="add_sen">'+carApply.office_deal_date.substring(0,16).replace(/\-/g,'.')+'</div>'
					+add_third+'</li>');
			}
			if(carApply.leader_deal_name!=null){

				var li="<li>"; add_third='<div class="add_third"><span class="add_third2">'+carApply.leader_deal_remark+'</span></div>';
				if(carApply.leader_deal_remark==null || carApply.leader_deal_remark==""){
					li="<li  class='ad_mind'>";
					add_third="";
				}
				$("#splc").append(li+'<div class="add_fir"><img src="images/user.png" /><span class="add_distance">'+carApply.leader_deal_name+'</span>'
						+'<span class="add_distance">'+yj(carApply.leader_deal_result)+'</span>'
					+'</div><div class="add_sen">'+carApply.leader_deal_date.substring(0,16).replace(/\-/g,'.')+'</div>'
					+add_third+'</li>');
			}
			
			}
		});
});
function yj(jg){
	if(jg=='1'){
		return '同意';
	}else{return '不同意';}
}
//获取部门
function getOrgList(){
	var sHtml1 = '<option value="">请选择</option>';//部门
	//获取部门
	$.ajax({
		url : "${pageContext.request.contextPath }/car/apply/getOrgList.do",
		type : "POST",
		async : false,
		success : function(data) {	
			if(data.success){
				var orgList= data.orgList;
				for(var i=0;i<orgList.length;i++){
					sHtml1+="<option value="+orgList[i][0]+">"+orgList[i][1]+"</option>"
				}
				$("#use_dep_id").html(sHtml1);
			}
		} 
	 });
 
}
//获取人员
function getUserList(data,user_id,user_Name){
	var sHtml2 = '<option value="">请选择</option>';//人员
	var name=$("#use_user_id").find("option:selected").text();
	var userId=$("#use_user_id").find("option:selected").val();
	//获取人员
	$.ajax({
		url : "${pageContext.request.contextPath }/car/apply/getUserList.do?org_id="+data,
		type : "POST",
		async : false,
		success : function(data) {	
			if(data.success){
				var userList= data.userList;
				if(userId!="undefined" && userId!="" && userId!=null){
					var show=true;
					for(var i=0;i<userList.length;i++){
						if(userId==userList[i][0]){
							show=false;
							break;
						}
					}
					 if(show){
						 sHtml2+="<option   value="+userId+" selected ='true'>"+name+"</option>";
					 }
				}
				if(user_id!="" && user_id!=null){
					sHtml2+="<option value="+user_id+" selected ='true'>"+user_Name+"</option>";
				}
				for(var i=0;i<userList.length;i++){
					
					if(userId==userList[i][0]){
						sHtml2+="<option value="+userList[i][0]+" selected ='true'>"+userList[i][1]+"</option>"
					}else{
						sHtml2+="<option value="+userList[i][0]+">"+userList[i][1]+"</option>"
					}
					
				}
				$("#use_user_id").html(sHtml2);
			}
		} 
	 });
}
//获取车辆
function getCarList(){
	var sHtml3 = '<option value="">请选择</option>';//车辆
	//获取车辆
	$.ajax({
		url : "${pageContext.request.contextPath }/car/apply/getCarList.do",
		type : "POST",
		async : false,
		success : function(data) {	
			if(data.success){
				var carList= data.carList;
				for(var i=0;i<carList.length;i++){
					sHtml3+="<option value="+carList[i][0]+">"+carList[i][1]+"</option>"
				}
				$("#fk_car_id").html(sHtml3);
			}
		} 
	 });
}
//获取驾驶员
function getDriverList(){
	var sHtml4 = '<option value="">请选择</option>';//驾驶员
	//获取驾驶员
	$.ajax({
		url : "${pageContext.request.contextPath }/car/apply/getUserList.do?org_id=100040",
		type : "POST",
		async : false,
		success : function(data) {	
			if(data.success){
				var driverList= data.userList;
				for(var i=0;i<driverList.length;i++){
					sHtml4+="<option value="+driverList[i][0]+">"+driverList[i][1]+"</option>"
				}
				$("#driver_user_id").html(sHtml4);
			}
		} 
	 });
 
}
function depChange(){
	var org_id=document.getElementById("use_dep_id").value;
	//$("#use_dep_name").val($("#use_dep_id").find("option:selected").text());
	//$("#use_user_phone").val("");
	getUserList(org_id);
}
function userChange(){
	$("#use_user_name").val($("#use_user_id").find("option:selected").text());
	var user_id = document.getElementById("use_user_id").value;
	$.ajax({
		url : "${pageContext.request.contextPath }/car/apply/queryMobileTel.do?user_id="+user_id,
		type : "POST",
		async : false,
		success : function(data) {	
			if(data.success){
				var mobile_tel= data.data;
				document.getElementById("use_user_phone").value = mobile_tel;
				/* $("#use_user_phone").val(mobile_tel); */
			}
		} 
	 });
}
function carChange(){
	$("#plate_number").val($("#fk_car_id").find("option:selected").text());
}
function driverChange(){
	$("#driver_user_name").val($("#driver_user_id").find("option:selected").text());
}
function setleaveCount1(){
	
}

//比较日期大小  
function compareDate(checkStartDate, checkEndDate) {
	var arys1 = new Array();
	var arys2 = new Array();
	if (checkStartDate != null && checkEndDate != null) {
		arys1 = checkStartDate.split('-');
		var sdate = new Date(arys1[0], parseInt(arys1[1] - 1), arys1[2]);
		arys2 = checkEndDate.split('-');
		var edate = new Date(arys2[0], parseInt(arys2[1] - 1), arys2[2]);
		if (sdate > edate) {
			return false;
		} else {
			return true;
		}
	}
}
//保存
function save(){
 	if($("#use_start_date").val()==""||$("#use_end_date").val()==""||$("#apply_reason").val()==""||$("#drive_route").val()==""){
 	    alert("请填写完整后提交！");	
 		return false;
 	}
	if(flag){
		$('#load').show();
		flag=false;
		$.ajax({
	        type: "POST",//方法类型
	        dataType: "json",//预期服务器返回的数据类型
	        url: "${pageContext.request.contextPath }/car/apply/saveBasic.do" ,
	        data: $('#formObj').serialize(),
	        success: function (result) {
	        	$('#load').hide();
	        	if(result.success){
					jQnotice('操作成功!',3000,"save");
	        	}else{
	        		flag=true;
	        		setTimeout("alert('"+result.msg+"')", 150 );
	        	}
	        },
	        error : function() {
	        	flag=true;
	        	$('#load').hide();
	        	setTimeout("alert('操作异常！')", 150 );
	        }
	    });
	}else{
		alert("数据处理中，请勿多次提交！");
	}
}
//提交
function submit(){
	save();
}
//同意
function pass(text){
	if("<%=car_apply_check_status%>" =="0"){
		$("#dep_deal_result").val("1");
	}else if("<%=car_apply_check_status%>" =="1"){
		$("#office_deal_result").val("1");
	}else if("<%=car_apply_check_status%>" =="2"){
		$("#leader_deal_result").val("1");
	}else if("<%=car_apply_check_status%>" =="3"){
		$("#fleet_deal_result").val("1");
	}else if("<%=car_apply_check_status%>" =="-1"){
		
	}
	var data=$('#formObj').serialize();
	//leader_deal_remark
	var leader_deal_remark=$("#leader_deal_remark").val();
	var dep_deal_remark=$("#dep_deal_remark").val();
	var fleet_deal_remark=$("#fleet_deal_remark").val();
	var office_deal_remark=$("#office_deal_remark").val();
	var type_remark=$("#type_remark").val();
	
	var th="";
	if(type_remark=='leader_deal_remark'){
		th='leader_deal_remark='+leader_deal_remark;
	}
	if(type_remark=='dep_deal_remark'){
		th='dep_deal_remark='+dep_deal_remark;
	}
	if(type_remark=='fleet_deal_remark'){
		th='fleet_deal_remark='+fleet_deal_remark;
	}
	if(type_remark=='office_deal_remark'){
		th='office_deal_remark='+office_deal_remark;
	}
	var endDate="";
	if(th!=""){
		endDate=data.replace(th,type_remark+"="+text);
	}else{
		endDate=$('#formObj').serialize();
	}
	
	if(flag){
		$('#load').show();
		flag = false;
		$.ajax({
			type: "POST",//方法类型
	        dataType: "json",//预期服务器返回的数据类型
	        url: "${pageContext.request.contextPath }/car/apply/wxChecks.do?id=<%=id%>&deal_result=1" ,
	        data: endDate,
	        success: function (data, stats) {
	        	$('#load').hide();
	            if (data["success"]) {
	            	//cback(2);
					jQnotice('操作成功!',3000,"pass");
	            } else {
	            	flag = true;
	            	setTimeout("alert('"+result.msg+"')", 150 );
	            }
	        },
	        error: function (data) {
	        	flag = true;
	        	$('#load').hide();
	        	setTimeout("alert('"+data.msg+"')", 150 );
	        }
	    });
	}else{
		alert("数据处理中，请勿多次提交！");
	}
}
//不同意
function fail(text){
	if("<%=car_apply_check_status%>" =="0"){
		$("#dep_deal_result").val("2");
	}else if("<%=car_apply_check_status%>" =="1"){
		$("#office_deal_result").val("2");
	}else if("<%=car_apply_check_status%>" =="2"){
		$("#leader_deal_result").val("2");
	}else if("<%=car_apply_check_status%>" =="3"){
		$("#fleet_deal_result").val("2");
	}else if("<%=car_apply_check_status%>" =="-1"){
		
	}
	
	

	var data=$('#formObj').serialize();
	var leader_deal_remark=$("#leader_deal_remark").val();
	var dep_deal_remark=$("#dep_deal_remark").val();
	var fleet_deal_remark=$("#fleet_deal_remark").val();
	var office_deal_remark=$("#office_deal_remark").val();
	var type_remark=$("#type_remark").val();
	
	var th="";
	if(type_remark=='leader_deal_remark'){
		th='leader_deal_remark='+leader_deal_remark;
	}
	if(type_remark=='dep_deal_remark'){
		th='dep_deal_remark='+dep_deal_remark;
	}
	if(type_remark=='fleet_deal_remark'){
		th='fleet_deal_remark='+fleet_deal_remark;
	}
	if(type_remark=='office_deal_remark'){
		th='office_deal_remark='+office_deal_remark;
	}
	var endDate="";
	if(th!=""){
		endDate=data.replace(th,type_remark+"="+text);
	}else{
		endDate=$('#formObj').serialize();
	}
	
	if(flag){
		$('#load').show();
		flag = false;
		$.ajax({
			type: "POST",//方法类型
	        dataType: "json",//预期服务器返回的数据类型
	        url: "${pageContext.request.contextPath }/car/apply/wxChecks.do?id=<%=id%>&deal_result=0" ,
	        data: endDate,
	        success: function (data, stats) {
	        	$('#load').hide();
	            if (data["success"]) {
	            	//cback(2);
					jQnotice('操作成功!',3000,"fail");
	            } else {
	            	flag = true;
	            	setTimeout("alert('"+result.msg+"')", 150 );
	            }
	        },
	        error: function (data) {
	        	flag = true;
	        	$('#load').hide();
	        	setTimeout("alert('"+data.msg+"')", 150 );
	        }
	    });
	}else{
		alert("数据处理中，请勿多次提交！");
	}
}
//派车
function assigns(){
	if("<%=data_status%>"=="4"){
		var need_driver=$("#need_driver").val();
		var fk_car_id=$("#fk_car_id").val();
		var driver_user_id=$("#driver_user_id").val();
		var out_date=$("#out_date").val();
		var start_km=$("#start_km").val();
		if(need_driver=="1"){
			//需要驾驶员时的验证
			if(fk_car_id==null || fk_car_id=="" || fk_car_id=="undefined" ||
					driver_user_id==null || driver_user_id=="" || driver_user_id=="undefined" ||
					out_date==null || out_date=="" || out_date=="undefined" ||
					start_km==null || start_km=="" || start_km=="undefined"){
				alert("请填写完整派车信息");
				return false;
			}
		}else{
			//不需要驾驶员时的验证
			if(fk_car_id==null || fk_car_id=="" || fk_car_id=="undefined" ||
					out_date==null || out_date=="" || out_date=="undefined" ||
					start_km==null || start_km=="" || start_km=="undefined"){
				alert("请填写完整派车信息");
				return false;
			}
		}
	}
	if(flag){
		$('#load').show();
		flag = false;
		$.ajax({
			type: "POST",//方法类型
	        dataType: "json",//预期服务器返回的数据类型
	        url: "${pageContext.request.contextPath }/car/apply/wxAssigns.do?id=<%=id%>" ,
	        data: $('#formObj').serialize(),
	        success: function (data, stats) {
	        	$('#load').hide();
	            if (data["success"]) {
					jQnotice('操作成功!',3000,"assigns");
	            } else {
	            	flag = true;
	            	setTimeout("alert('"+result.msg+"')", 150 );
	            }
	        },
	        error: function (data) {
	        	flag = true;
	        	$('#load').hide();
	        	setTimeout("alert('"+data.msg+"')", 150 );
	        }
	    });
	}else{
		alert("数据处理中，请勿多次提交！");
	}
}
//收车
function receive(){
	if("<%=data_status%>"=="5"){
		var back_date=$("#back_date").val();
		var end_km=$("#end_km").val();
		if(back_date==null || back_date=="" || back_date=="undefined" ||end_km==null || end_km=="" || end_km=="undefined" ){
			alert("请填写完整收车信息");
			return false;
		}
	}
	if(flag){
		$('#load').show();
		flag = false;
		$.ajax({
			type: "POST",//方法类型
	        dataType: "json",//预期服务器返回的数据类型
	        url: "${pageContext.request.contextPath }/car/apply/wxReceives.do?id=<%=id%>" ,
	        data: $('#formObj').serialize(),
	        success: function (data, stats) {
	        	$('#load').hide();
	            if (data["success"]) {
					jQnotice('操作成功!',3000,"receive");
	            } else {
	            	flag = true;
	            	setTimeout("alert('"+result.msg+"')", 150 );
	            }
	        },
	        error: function (data) {
	        	flag = true;
	        	$('#load').hide();
	        	setTimeout("alert('"+data.msg+"')", 150 );
	        }
	    });
	}else{
		alert("数据处理中，请勿多次提交！");
	}
}
//返回
function back(opType){
	var url="${pageContext.request.contextPath }/app/car/weixin/car_apply_pending.jsp";
	if(opType=="opBack"){
		url=url+"?skip=<%=skip%>";
	}else if(opType=="save"){
		url=url+"?skip=mine";
	}else if(opType=="pass" || opType=="fail" || opType=="assigns" || opType=="receive" || opType=="zuofei"){
		url=url+"?skip=handled";
	}
	location.href=url;
}

function zuofei(){
	if(flag){
		$('#load').show();
		flag = false;
		$.ajax({
			type: "POST",//方法类型
	        dataType: "json",//预期服务器返回的数据类型
	        url: "${pageContext.request.contextPath }/car/apply/zuoFei.do" ,
	        data: $('#formObj').serialize(),
	        success: function (data, stats) {
	        	$('#load').hide();
	            if (data["success"]) {
					jQnotice('操作成功！',3000,"zuofei");
	            } else {
	            	flag = true;
	            	setTimeout("alert('"+result.msg+"')", 150 );
	            }
	        },
	        error: function (data) {
	        	flag = true;
	        	$('#load').hide();
	        	setTimeout("alert('"+data.msg+"')", 150 );
	        }
	    });
	}else{
		alert("数据处理中，请勿多次提交！");
	}
}
</script>
<style type="text/css">
	#load{position:fixed; top: 0px; right:0px; bottom:0px;filter: alpha(opacity=60); background-color: #777;
	z-index: 1002; left: 0px; display:none;
	opacity:0.5; -moz-opacity:0.5;padding-top:100px;color: #000000}
</style>
</head>
<body>
<div class="settings" style="display:none;">
          <select name="demo" id="demo" style="border: solid 0px #000;appearance:none;-moz-appearance:none;-webkit-appearance:none;">
              <option value="date">日期</option>
          </select>
        </div>
<%-- <section id="web"> --%>
	<header id="header">
		<img src="${pageContext.request.contextPath }/app/car/weixin/images/tb.png">
	</header>
	<section id="content">
	<form name="formObj" id="formObj" method="post" action="" getAction="">
	<input type="hidden" name="id" id="id"/>
				<!-- <input type="hidden" name="apply_sn" id="apply_sn"/> -->
				<input type="hidden" name="apply_dep_id" id="apply_dep_id"/>
				<input type="hidden" name="apply_dep_name" id="apply_dep_name"/>
				<input type="hidden" name="apply_user_id" id="apply_user_id"/>
				<input type="hidden" name="apply_user_name" id="apply_user_name"/>
				<input type="hidden" name="use_dep_name" id="use_dep_name"/>
				<input type="hidden" name="use_user_name" id="use_user_name"/>
				<input type="hidden" name="use_days" id="use_days"/>
				<input type="hidden" name="destination" id="destination"/>
				
				<input type="hidden" name="dep_deal_id" id="dep_deal_id"/>
				<input type="hidden" name="dep_deal_name" id="dep_deal_name"/>
				<input type="hidden" name="dep_deal_result" id="dep_deal_result"/>
				<input type="hidden" name="dep_deal_date" id="dep_deal_date"/>
				<input type="hidden" name="dep_deal_remark" id="dep_deal_remark"/>
				
				<input type="hidden" name="office_deal_id" id="office_deal_id"/>
				<input type="hidden" name="office_deal_name" id="office_deal_name"/>
				<input type="hidden" name="office_deal_result" id="office_deal_result"/>
				<input type="hidden" name="office_deal_date" id="office_deal_date"/>
				<input type="hidden" name="office_deal_remark" id="office_deal_remark"/>
				
				<input type="hidden" name="leader_deal_id" id="leader_deal_id"/>
				<input type="hidden" name="leader_deal_name" id="leader_deal_name"/>
				<input type="hidden" name="leader_deal_result" id="leader_deal_result"/>
				<input type="hidden" name="leader_deal_date" id="leader_deal_date"/>
				<input type="hidden" name="leader_deal_remark" id="leader_deal_remark"/>
				
				<input type="hidden" name="fleet_deal_id" id="fleet_deal_id"/>
				<input type="hidden" name="fleet_deal_name" id="fleet_deal_name"/>
				<input type="hidden" name="fleet_deal_result" id="fleet_deal_result"/>
				<input type="hidden" name="fleet_deal_date" id="fleet_deal_date"/>
				<input type="hidden" name="fleet_deal_remark" id="fleet_deal_remark"/>
				
				<input type="hidden" name="send_deal_id" id="send_deal_id"/>
				<input type="hidden" name="send_deal_name" id="send_deal_name"/>
				<input type="hidden" name="send_date" id="send_date"/>
				<input type="hidden" name="plate_number" id="plate_number"/>
				
				<input type="hidden" name="driver_user_name" id="driver_user_name"/>
				<input type="hidden" name="receive_deal_id" id="receive_deal_id"/>
				<input type="hidden" name="receive_deal_name" id="receive_deal_name"/>
				<input type="hidden" name="receive_date" id="receive_date"/>
				<input type="hidden" name="return_user_id" id="return_user_id"/>
				<input type="hidden" name="return_user_name" id="return_user_name"/>
				<input type="hidden" name="return_date" id="return_date"/>
				<input type="hidden" name="data_status" id="data_status"/>
		<section class="number">
			<section class="numberCenter">
				<strong>编号：</strong>
				<input type="text" unselectable="on" onfocus="this.blur()" name="apply_sn" id="apply_sn" class="bianhao" placeholder="自动生成" readonly="readonly" style="border:0px;">
				<strong>填表日期：</strong>
				<input type="text" unselectable="on" onfocus="this.blur()" name="apply_date" id="apply_date" readonly="readonly" style="border:0px;" value="<%=nowTime %>">
			</section>
		</section>
		<section class="car">
			<section class="numberCenter">
				<strong>用车时间：</strong>
				<span>起</span>
				<input type="text" name="use_start_date" id="use_start_date" style="" class="form-control demo-test-date demo-test-datetime demo-test-time demo-test-credit" placeholder="请选择时间" ext_isNull="1" maxLength="18" >
				<br><span style="padding-left:80px">止</span>
				<input type="text" name="use_end_date" id="use_end_date" style="" class="form-control demo-test-date demo-test-datetime demo-test-time demo-test-credit" placeholder="请选择时间" ext_isNull="1" maxLength="18" >
			</section>
		</section>
		<section class="department">
			<section class="departmentCenter">
				<section class="top">
					<strong>用车部门：</strong>
					<select name="use_dep_id" onchange="depChange()" id="use_dep_id" style="border: solid 0px #000;appearance:none;-moz-appearance:none;-webkit-appearance:none;" style="border:0px"></select>
					<img src="images/dropDown.png">
				</section>
				<section class="bottom">
					<strong>联系人：</strong>
			
					<select name="use_user_id" id="use_user_id" style="border: solid 0px #000;appearance:none;-moz-appearance:none;-webkit-appearance:none;" onchange="userChange()" style="border:0px;margin-top:6%"></select>
					<input type="text" name="use_user_phone" id="use_user_phone" placeholder="请输入电话号码" data-ng-model="address.name" class=" ng-empty
					ng-dirty ng-valid-parse ng-invalid ng-invalid-required ng-touched ">
				</section>
			</section>
		</section>
		<section class="department">
			<section class="departmentCenter">
				<section class="top">
					<strong>用车任务：</strong>
					<input type="text" name="apply_reason" id="apply_reason" placeholder="" class="task">
				</section>
				<section class="top">
					<strong>行驶路线：</strong>
					<input type="text" name="drive_route" id="drive_route" placeholder="" class="task">
				</section>
				<section class="top">
					<strong>乘车人数：</strong>
					<input type="text" name="passengers_count" id="passengers_count" placeholder="" class="task">
				</section>
				<section class="top">
					<strong>驾驶员：</strong>
					<select name="need_driver" id="need_driver" style="border: solid 0px #000;appearance:none;-moz-appearance:none;-webkit-appearance:none;margin-top:6%">
						<option value="0">否</option>
						<option value="1">是</option>
					</select>
					<!-- <input type="text" name="need_driver" id="need_driver" placeholder="" class="task"> -->
				</section>
				<section class="bottom">
					<strong>备注：</strong>
					<input type="text" name="apply_remark" id="apply_remark" placeholder="" class="task">
				</section>
			</section>
		</section>
				<section class="department" id="splc_countent">
				<section class="departmentCenter" style="padding-bottom: 2rem;margin-top: -1rem;">
					<section class="add_bottom">
					<ul id="splc">
						
					</ul>
					</section>
				</section>
				</section>
		
<%-- 		<section class="department"> --%>
<%-- 			<section class="departmentCenter"> --%>
<%-- 				<section> --%>
<%-- 					<section class="user"> --%>
<%-- 						<img src="${pageContext.request.contextPath }/app/car/weixin/images/user.png"> --%>
<%-- 					</section> --%>
<%-- 					<section class="dinput"> --%>
<!-- 						<input type="text" name="dep_deal_remark" readonly="true"  id="dep_deal_remark" placeholder="用车部门负责人意见"> -->
<%-- 					</section> --%>
<%-- 					<section class="clear"></section> --%>
<%-- 				</section> --%>
<%-- 				<section> --%>
<%-- 					<section class="user"> --%>
<!-- 						<img src="images/user.png"> -->
<%-- 					</section> --%>
<%-- 					<section class="dinput"> --%>
<!-- 						<input type="text" name="fleet_deal_remark" id="fleet_deal_remark" readonly="true" placeholder="车队负责人审核"> -->
<%-- 					</section> --%>
<%-- 					<section class="clear"></section> --%>
<%-- 				</section> --%>
<%-- 				<section> --%>
<%-- 					<section class="user"> --%>
<!-- 						<img src="images/user.png"> -->
<%-- 					</section> --%>
<%-- 					<section class="dinput"> --%>
<!-- 						<input type="text" name="office_deal_remark" id="office_deal_remark" readonly="true" placeholder="办公室负责人审核"> -->
<%-- 					</section> --%>
<%-- 					<section class="clear"></section> --%>
<%-- 				</section> --%>
<%-- 				<section> --%>
<%-- 					<section class="user"> --%>
<!-- 						<img src="images/user.png"> -->
<%-- 					</section> --%>
<%-- 					<section class="dinput"> --%>
<!-- 						<input type="text" name="leader_deal_remark" id="leader_deal_remark" readonly="true" placeholder="分管院领导审批" class="yjnone"> -->
<%-- 					</section> --%>
<%-- 					<section class="clear"></section> --%>
<%-- 				</section> --%>
<%-- 			</section> --%>
<%-- 		</section> --%>
		<section class="department">
			<section class="departmentCenter">
				<section class="departmentT">
					<section class="LicensePlate"> 
					<strong>派车:</strong> 
						<select name="fk_car_id" id="fk_car_id" style="border: solid 0px #000;appearance:none;-moz-appearance:none;-webkit-appearance:none;" onchange="carChange()" >
						</select> 
					</section>
					<section class="LicensePlate" >
						<strong>驾驶员</strong>
						<select name="driver_user_id" id="driver_user_id" style="border: solid 0px #000;appearance:none;-moz-appearance:none;-webkit-appearance:none;" onchange="driverChange()" >
						</select>
					</section>
				</section>
				
				<section class="departmentT">
					<section class="outTime">
						<strong>出车时间:</strong>
						<input type="text" name="out_date" id="out_date" class="form-control demo-test-date demo-test-datetime demo-test-time demo-test-credit" placeholder="请选择时间" ext_isNull="1" maxLength="18" >
					</section>
					<section class="Count">
						<strong>起数(KM):</strong>
						<input type="text" name="start_km" id="start_km" placeholder="">
					</section>
					<section class="clear"></section>
				</section>
				<section class="departmentTa">
					<section class="outTime">
						<strong>返回时间:</strong>
						<input type="text" name="back_date" id="back_date" style="" class="form-control demo-test-date demo-test-datetime demo-test-time demo-test-credit" placeholder="请选择时间" ext_isNull="1" maxLength="18" >
					</section>
					<section class="Count">
						<strong>止数(KM):</strong>
						<input type="text" name="end_km" id="end_km" placeholder="">
					</section>
					<section class="clear"></section>
				</section>
			</section>
	</section>
	</form>
</section>
<!--两个按钮情况下-->
	<!--<section id="apply" class="Button Button1">
		<input type="button" name="TJ" onclick="submit()" value="提交">
		<input type="button" name="fh" onclick="back()" value="返回">
	</section>-->
	<!--一个按钮情况下-->
	<%-- <section id="apply" class="Button Button2">
		<input type="button" name="TJ" onclick="submit()" value="提交">
	</section> --%>
	<!--三个按钮情况下-->
	<section id="apply" class="Button2">
		<a href="javascript:" onclick="submit()" >提交</a>
		<a href="javascript:" onclick="back('opBack')" >返回</a>
	</section>
	<section id="check" class="Button3">
<!-- 	pass -->

		<a href="javascript:" name="bc" onclick="add_name({type:['1','保存'],empty:false})" >同意</a>
<!-- 		fail -->
		<a href="javascript:" name="TJ" onclick="add_name({type:['0','保存'],empty:false})" >不同意</a>
		<a href="javascript:" name="fh" onclick="back('opBack')" >返回</a>
	</section>
	<section id="check2" class="Button3">
		<a href="javascript:" name="bc" onclick="add_name({type:['1','保存'],empty:false})" >同意</a>
		<a href="javascript:" name="TJ" onclick="zuofei()" >作废</a>
		<a href="javascript:" name="fh" onclick="back(opBack)" >返回</a>
	</section>
	
	<section id="assigns" class="Button2">
		<a href="javascript:" name="TJ" onclick="assigns()" >派车</a>
		<a href="javascript:" name="fh" onclick="back('opBack')" >返回</a>
	</section>
	<section id="receive" class="Button2">
		<a href="javascript:" name="TJ" onclick="receive()" >收车</a>
		<a href="javascript:" name="fh" onclick="back('opBack')" >返回</a>
	</section>
	<section id="detail" class="Button1">
		<a id="a_back" href="javascript:" name="fh" onclick="back('opBack')" >返回</a>
	</section>
<%-- </section> --%>
<input type="hidden" id="type_remark"/>
<div id="load" align="center"><img src="images/loading.gif" width="45" height="45" align="center" style="margin-top: 50%;"/></div>
</body>

<script>
	function add_name(options){
		//张展彬要求去掉审核时的意见弹出窗口
		/* $(".info_err").hide();
		var defaults={
			type:[],
			empty:true
		}
		defaults= options;
			_this = this;
		var text='';
			if(defaults.type[0]=='1'){
			text='同意';	
			}
			
	    	var strhtml = '<div class="maskbou"></div>'
			+'<div class="add_name">'
			+'<label class="pu_colse">x</label>'
			+'<div class="title"><span>请填写处理意见</span></div><div class="cont">'
			+'<div class="int_mo"><textarea id="moc_int">'+text+'</textarea></div></div>'
			+'<div class="infobtn"><button class="btn_one " onclick="cback(2)" data-name="取消">取消</button><button class="btn_two mleft12" onclick="cback(1)" data-name="下一步">提交</button></div></div>'
			$("body").append(strhtml);
			_this.cback=function(num){
				if(num==1){
					var tet = $("#moc_int").val();
					if(defaults.type[0]=='1'){
						pass(tet);
					}else{
						fail(tet);
					}
					if(!defaults.empty&&tet==''){
						$(".info_err").show();
						return false;
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
				type:[],
				empty:true
			}
			defaults= options;
			if(defaults.type[0]=='1'){
				pass("同意");
			}else{
				fail("不同意");
			}
    }
</script>
</html>
