<%@ page contentType="text/html;charset=UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="java.util.Date"%>
<%@page import="com.khnt.rbac.impl.bean.Org"%>
<%@page import="com.khnt.rbac.impl.bean.Employee"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Map"%>
<%
	CurrentSessionUser cuser = SecurityUtil.getSecurityUser();
	User user = (User) cuser.getSysUser();
	String userId = user.getId();
	Employee emp = (Employee) user.getEmployee();
	String empId = emp.getId();
	String orgId = cuser.getDepartment().getId();
	String orgName = cuser.getDepartment().getOrgName();
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	String nowTime = dateFormat.format(new Date());
	
	
%>
<!DOCTYPE html>
<html>
<head>
<title>手工出报告申请</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/app/qualitymanage/manualReport/css/public.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/app/qualitymanage/manualReport/css/header.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/app/qualitymanage/manualReport/css/content.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/app/qualitymanage/manualReport/css/selectFilter.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/app/qualitymanage/manualReport/css/jquery.notice.css">

<script type="text/javascript"
	src="${pageContext.request.contextPath }/app/qualitymanage/manualReport/js/jquery-1.7.min.js"></script>
<script src="${pageContext.request.contextPath}/k/km/lib/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/app/qualitymanage/manualReport/js/selectFilter.js"></script>
<script src="${pageContext.request.contextPath}/k/km/lib/kh-mobile.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/app/qualitymanage/manualReport/js/jquery.notice.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/app/qualitymanage/manualReport/js/jquery.util.js"></script>
<link
	href="${pageContext.request.contextPath }/app/qualitymanage/manualReport/css/demoe.css"
	rel="stylesheet" media="all" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/app/weixin/js/timejs/date.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/app/weixin/js/timejs/iscroll.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/app/weixin/js/timejs/mobiscroll.2.13.2.js"></script>
<link
	href="${pageContext.request.contextPath}/app/weixin/js/timejs/mobiscroll.2.13.2.css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/app/weixin/js/timejs/common.css" />
<style type="text/css">
.sel{
	border: solid 0px #000;
	appearance: none;
	-moz-appearance: none;
	-webkit-appearance: none;
}
</style>
<script type="text/javascript">
var businessId = "${param.id}";//提交数据的id
var flowActivityId = "";//流程id
var processId = "";
var areaFlag = 0;
var flowId = "";//流程模板
var flowName = "";
var typeCode="";//检验类别
//设备种类
var equipmentType = <u:dict code = "TJY2_SBZL"/>;
//设备品种
var equipmentVariety = <u:dict code = "TJY2_SBPZ"/>;
//检验类别
var equipmentCategory = <u:dict code = "TJY2_JYLB"/>;
//报告类别
var reportCategory = <u:dict sql = "SELECT test_coding id,test_coding||'-'||type||'-'||variety||'-'||category as text FROM TJY2_QUALITY_SGCJJYBG_NUM where IDENTIFIER_TYPE = '1' and STATUS = '0' and test_coding <> 'W' ORDER BY id"/>;


$(function(){
	$('#load').hide();
	$('#aqiExplainCClose').hide();
	//控制操作按钮
	$(".act-button").hide();
	if("${param.pageStatus}"=="add"||("${param.pageStatus}"=="detail"&&"${param.dataStatus}"=="WTJ")){
		//申请
		$("#add").show();
	}else if("${param.pageStatus}"=="check"){
		//根据状态显示按钮
		$("#check").show();
	}else{
		//不是待处理，则全部显示为详情
		$("#detail").show();
	}
	//初始化下拉框
	var ets = $("select.equipmentType").get();
	$.each(ets,function(i){
		initSelect(ets[i],equipmentType);
	});
	var evs = $("select.equipmentVariety").get();
	$.each(evs,function(i){
		initSelect(evs[i],equipmentVariety);
	});
	var ecs = $("select.equipmentCategory").get();
	$.each(ecs,function(i){
		initSelect(ecs[i],equipmentCategory);
	});
	var categoryObjs = $("select[name='reportCategory']").get();
	$.each(categoryObjs,function(i){
		initSelect(categoryObjs[i],reportCategory);
	});
	//
	$.post("${pageContext.request.contextPath}/quality/sgcjjybg/getWorktask.do",{"id":"${param.id}"},function(res){
		if(res.success){
			flowActivityId = res["activity"].id;
			processId = res["activity"].process;
			if(res["activity"]["function"].indexOf("TJY2_ZL_SGCJJYBG_YWFWBJBR")!=-1){
				areaFlag = 3;
				$("section.reportCategorySel").show();
			}else if(res["activity"]["function"].indexOf("TJY2_ZL_SGCJJYBG_BMFZR")!=-1){
				areaFlag = 1;
			}else if(res["activity"]["function"].indexOf("TJY2_ZL_SGCJJYBG_JYRJFZR")!=-1){
				areaFlag = 2;
			}else if(res["activity"]["function"].indexOf("TJY2_ZL_SGCJJYBG_ZLFZR")!=-1){
				areaFlag = 4;
			}
			
		}else{
			if("${param.pageStatus}"=="check"){
				$(".act-button").hide();
				//不是待处理，则全部显示为详情
				$("#detail").show();
			}
		}
	});
	
	//初始化数据
	if("${param.pageStatus}"!="add"){
		var url = "${pageContext.request.contextPath }/quality/sgcjjybg/detail.do?id=${param.id}";
		$.get(url,{},function(res) {
			if(res.success){
				var detailData = res.data;
				//先判断是法定检验/委托检验
				if(detailData.statusa=="0"){
					typeCode="TJY2_ZL_SGCJJYBG";
					
					$("section.fdjy select").attr({"validate":"requried"});
					$("section.fdjy").show();
					
					$("section.wtjy input").attr({"validate":""});
					$("section.wtjy").hide();
				}else if(detailData.statusa=="1"){
					typeCode="TJY2_ZL_SGCJJYBG_WT";
					
					$("section.fdjy select").attr({"validate":""});
					$("section.fdjy").hide();
					
					$("section.wtjy input").attr({"validate":"requried"});
					$("section.wtjy").show();
				}
				//微信消息审核控制
				if(detailData.status=='NO_PASS'||detailData.status=='PASS'){
					$(".act-button").hide();
					//不是待处理，则全部显示为详情
					$("#detail").show();
				}
				//
				$.getJSON("${pageContext.request.contextPath }/bpm/serviceConfig/getFlowServiceConfig.do", {
					serviceCode : typeCode,
					orgId : ""
				}, function(resp) {
					if (resp.success) {
						if (resp.data.length > 0){
							flowId = resp.data[0].flowId;
							flowName = resp.data[0].flowName;
						}
					}
				});
				//检查数据项
				if(detailData["equipmentName2"]){
					$("#device2").show();
				}
				if(detailData["equipmentName3"]){
					$("#device3").show();
				}
				if(detailData["equipmentName4"]){
					$("#device4").show();
				}
				if(detailData["equipmentName5"]){
					$("#device5").show();
				}
				if(detailData["equipmentName6"]){
					$("#device6").show();
				}
				if(detailData["equipmentName7"]){
					$("#device7").show();
				}
				//初始化表单数据
				initFormData(detailData);
				//显示流程信息
				showDataFlow(detailData);
				if(res.data.contractNumber==null){$(".xq").hide();}
				if(res.data.contractNumber2==null){$(".xq2").hide();}
				if(res.data.contractNumber3==null){$(".xq3").hide();}
				if(res.data.contractNumber4==null){$(".xq4").hide();}
				if(res.data.contractNumber5==null){$(".xq5").hide();}
				if(res.data.contractNumber6==null){$(".xq6").hide();}
				if(res.data.contractNumber7==null){$(".xq7").hide();}
			}
		});
		//禁止编辑项
		$("#statusa").attr({"disabled":"disabled"});
		$("#userName").attr({"readOnly":"readOnly"});
		$("#bjwtsj").removeClass().attr({"readOnly":"readOnly"});
		$("#reason").attr({"disabled":"disabled"});
		$("input.equipmentName").attr({"readOnly":"readOnly"});
		$("input.deviceCount").attr({"readOnly":"readOnly"});
		$("select.equipmentType").attr({"disabled":"disabled"});
		$("select.equipmentVariety").attr({"disabled":"disabled"});
		$("select.equipmentCategory").attr({"disabled":"disabled"});
		$("input.contractNumber").attr({"readOnly":"readOnly"});
		$("input.rwdSn").attr({"readOnly":"readOnly"});
	}

	//初始化时间框
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
});



function validate(reg,text){
	if(reg.test(text)){
		return true;
	}
	return false;
}
function save(_this){
	$('#load').show();
	if($(_this).attr("disable")==true){
		$('#load').hide(); 
		return;	
	}
	$(_this).attr({"disable":true});
	//表单验证
	var req = $("#formObj").find("input[validate='required'],select[validate='required']").get();
	var flag = false;
	for(var i=0;i<req.length;i++){
		var display = $(req[i]).parents("section.department").css("display");
		if(display =="none"){
			continue;
		}
		if(req[i].value==""){
			var lab = $(req[i]).prev("strong").html();
			alert(lab+'不能为空!');
	 		flag=true;
	 		break;
		}
		if($(req[i]).hasClass("deviceCount")){
			//数字验证
			var reg = /^[1-9]*$/;
			var val = parseInt($(req[i]).val());
			if(!reg.test(val)){
				alert("拟检台数必须填数字！");
				flag=true;
		 		break;
			}
		}
	}

	if(flag){
		$(_this).attr({"disable":false});
		$('#load').hide();
		return;
	}
    var formData = $('#formObj').serializeFormJSON();

	var rwdSn1=$("#rwdSn1").val();
	if(rwdSn1==""){
    	alert('请填写检验任务单，如有疑问请联系业务发展部。');
		$(_this).attr({"disable":false});
		$('#load').hide();
    	return;
	 }
// 	var zbzl=["BJ","YLRQ","YLGD","AQFJJAQBHZZ","YLGDYJ","GL","CL"];//承压必填
//     if(rwdSn1=="" && $.inArray(type, zbzl)>-1){
    
	$.ajax({
        url: "${pageContext.request.contextPath }/quality/sgcjjybg/weixinSave.do?id="+businessId
  		 +"&typeCode="+typeCode
   		 +"&flowId="+flowId
   		 +"&activityId="+flowActivityId
   		 +"&areaFlag="+areaFlag,
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        contentType: "application/json;charset=utf-8",
        data:JSON.stringify(formData),
        success: function (result) {
        	if(result.success){
				jQnotice('操作成功!',2000,"save");
        	}else{
        		alert(result.msg);
        	}
        	$(_this).attr({"disable":false});
        	$('#load').hide();
        },
        error : function() {
        	alert('操作异常！');
        	$(_this).attr({"disable":false});
        	$('#load').hide();
        }
    });
}
//同意
function pass(_this){
	$('#load').show();
	if($(_this).attr("disable")==true){
		$('#load').hide(); 
		return;	
	}
	$(_this).attr({"disable":true});
	if(flowActivityId==""){
		alert("未获取到业务id！");
		$(_this).attr({"disable":false});
		$('#load').hide();
		return;
	}
	if(flowId==""){
		alert("未获取到流程！");
		$(_this).attr({"disable":false});
		$('#load').hide();
		return;
	}
	if(typeCode==""){
		$(_this).attr({"disable":false});
		$('#load').hide();
		return;
	}
	var datas = {
	   		"id":businessId,
	   		"typeCode":typeCode,	
	  		"activityId":flowActivityId,	
	  		"areaFlag":areaFlag,	
	  		"bgbh":$("#bgbh").val(),	
	  		"bgbh2":$("#bgbh2").val(),	
	  		"bgbh3":$("#bgbh3").val(),	
	  		"bgbh4":$("#bgbh4").val(),	
	  		"bgbh5":$("#bgbh5").val(),	
	  		"bgbh6":$("#bgbh6").val(),	
	  		"bgbh7":$("#bgbh7").val()
	    };
	if(areaFlag == 0){
		$(_this).attr({"disable":false});
		$('#load').hide();
		alert("数据加载未完成！");
		return;
	}else if(areaFlag == 3){
		var objects = $("input.fwbjhbg").get();
		for(var i=0;i<objects.length;i++){
			if($(objects[i]).parents("section.department").css("display")=="block"
					&&($(objects[i]).val()==null||$(objects[i]).val()=='')){
	   			$(_this).attr({"disable":false});
	   			alert("请选择报告/证书编号！");
	   			$('#load').hide();
	   			return;
	   		}
		}
    }else if(areaFlag == 4){
    	datas["zlshyj"]="同意";
    }
   
    $.ajax({
        url: "${pageContext.request.contextPath }/quality/sgcjjybg/check.do",
        type: "post",
        dataType:'json',
        data:datas,
        async: false,
        success:function (data) {
            if (data.success) {
                jQnotice('审核成功!',2000,"check");
                back();
            }else{
            	alert(data.msg);
           		$('#load').hide();
            }
        }
 	});
}

//不同意
function fail(_this){
	$('#load').show();
	if($(_this).attr("disable")==true){
		$('#load').hide(); 
		return;	
	}
	$(_this).attr({"disable":true});
	if(flowActivityId==""){
		$(_this).attr({"disable":false});
		$('#load').hide(); 
		alert("未获取到业务id！");
		return;
	}
	if(flowId==""){
		$(_this).attr({"disable":false});
		$('#load').hide(); 
		alert("未获取到流程！");
		return;
	}
	if(typeCode==""){
		$(_this).attr({"disable":false});
		$('#load').hide(); 
		return;
	}
    $.ajax({
        url: "${pageContext.request.contextPath }/quality/sgcjjybg/sgcjjybgth.do?id="+businessId
       		 +"&typeCode="+typeCode
       		 +"&status="
       		 +"&activityId="+flowActivityId
       		 +"&areaFlag="+areaFlag
       		 +"&processId="+processId,
        type: "GET",
        dataType:'json',
        data:(areaFlag=="4"?{"zlshyj":"不同意"}:{}),
        async: false,
        success:function (data) {
            if (data.success) {
                jQnotice('审核成功!',2000,"check");
                back();
            }else{
	           	$('#load').hide(); 
           	 	alert('出错了:'+data.msg);
            }
        }
    });
}
//返回
function back(_this){
	if($(_this).attr("disable")==true){
		return;	
	}
	$(_this).attr({"disable":true});
	$('#load').show();
	location.href="${pageContext.request.contextPath }/app/qualitymanage/manualReport/mr-index.jsp?skip=${param.skip}";
}
//切换法定检验、委托检验
function changeReport(obj){
	if(obj.value=="0"){
		typeCode="TJY2_ZL_SGCJJYBG";
		$("section.fdjy select").attr({"validate":"requried"});
		$("section.fdjy").show();
		
		$("section.wtjy input").val("").attr({"validate":""});
		$("section.wtjy").hide();
	}else if(obj.value=="1"){
		typeCode="TJY2_ZL_SGCJJYBG_WT";
		
		$("section.fdjy select").val("").attr({"validate":""});
		$("section.fdjy").hide();
		
		$("section.wtjy input").attr({"validate":"requried"});
		$("section.wtjy").show();
	}
	$.getJSON("${pageContext.request.contextPath }/bpm/serviceConfig/getFlowServiceConfig.do", {
		serviceCode : typeCode,
		orgId : ""
	}, function(resp) {
		if (resp.success) {
			if (resp.data.length > 0){
				flowId = resp.data[0].flowId;
				flowName = resp.data[0].flowName;
			}
		}
	});
}
function callBackReport(_this,code){
	var device = $(_this).parents("section.department").attr("name");
	if(code==""){
		$("#fwbjhbg"+(device=="1"?"":device)).val("");
		$("#bgbh"+(device=="1"?"":device)).val("");
		return;
	}
	$.ajax({
		url: "${pageContext.request.contextPath }/quality/sgcjjybg/setbgbh.do?id=${param.id}&test_coding="
				+code+"&njts="+$(_this).parents("section.departmentCenter").find("input.deviceCount").val()+"&row="+device,
		type: "POST",
		dataType:'json',
		async: false,
		success:function (data) {
			if (data.success) {
				$("#fwbjhbg"+(device=="1"?"":device)).val(data.bgbh);
				$("#bgbh"+(device=="1"?"":device)).val(data.bgbh);
			}
		}
	});
}	
</script>
<style type="text/css">
#load {
	position: fixed;
	top: 0px;
	right: 0px;
	bottom: 0px;
	filter: alpha(opacity = 60);
	background-color: #777;
	z-index: 1002;
	left: 0px;
	display: none;
	opacity: 0.5;
	-moz-opacity: 0.5;
	padding-top: 100px;
	color: #000000
}
</style>
</head>
<body>
	<div class="settings" style="display: none;">
		<select name="demo" id="demo"
			style="border: solid 0px #000; appearance: none; -moz-appearance: none; -webkit-appearance: none;">
			<option value="date">日期</option>
		</select>
	</div>
	<header id="header">
		<img
			src="${pageContext.request.contextPath }/app/car/weixin/images/tb.png">
	</header>
	<section id="content">
		<form name="formObj" id="formObj" method="post" action="" getAction="">
			<input type="hidden" id="bgbh" name="bgbh" /> 
			<input type="hidden" id="bgbh2" name="bgbh2" /> 
			<input type="hidden" id="bgbh3" name="bgbh3" /> 
			<input type="hidden" id="bgbh4" name="bgbh4" /> 
			<input type="hidden" id="bgbh5" name="bgbh5" /> 
			<input type="hidden" id="bgbh6" name="bgbh6" /> 
			<input type="hidden" id="bgbh7" name="bgbh7" /> 
			<input type="hidden" id="id" name="id" /> 
			<input type="hidden" id="status" name="status" /> 
			 
			<input type="hidden" id="registrant" name="registrant" /> 
			<input type="hidden" id="registrantId" name="registrantId" /> 
			<input type="hidden" id="registrantDate" name="registrantDate" /> 
			<input type="hidden" id="departmentId" name="departmentId" value="<%=orgId%>" /> 
			<input type="hidden" id="applyManId" name="applyManId" value="<%=empId%>" />
			
			<section class="department">
				<section class="departmentCenter">
					<section class="top">
						<strong>编号：</strong> 
						<input type="text" name="identifier" id="identifier"
							unselectable="on" onfocus="this.blur()" readonly="readonly"
							style="border: 0px;width:80%;" placeholder="自动生成" > 
					</section>
					<section class="top">
						<strong>申请日期：</strong> 
						<input type="text" name="applyTime" id="applyTime"
							unselectable="on" onfocus="this.blur()" readonly="readonly"
							value="<%=nowTime%>" style="border: 0px;">
					</section>
					<section class="top">
						<strong>申请部门：</strong> 
						<input type="text" name="department" id="department"  
							onfocus="this.blur()" unselectable="on" readonly="readonly"
							value="<%=orgName%>"  style="border: 0px;">
					</section>
					<section class="bottom">
						<strong>申请人：</strong> 
						<input type="text" name="applyName" id="applyName" 
							onfocus="this.blur()" unselectable="on" readonly="readonly"
							value="<%=user.getName()%>" style="border: 0px;">
					</section>
				</section>
			</section>
			
			<section class="department">
				<section class="departmentCenter">
					<section class="top">
						<strong>检验性质：</strong> 
						<select name="statusa" id="statusa" validate="required" class="sel" onChange="changeReport(this)">
							<option value="">请选择</option>
							<option value="0">法定检验报告</option>
							<option value="1">委托检验报告</option>
						</select>
						<img src="images/dropDown.png">
					</section>
				</section>
			</section>
			<section class="department">
				<section class="departmentCenter">
					<section class="top">
						<strong>用户名称：</strong> 
						<input type="text" name="userName"
							id="userName" placeholder="" class="task" validate="required">
					</section>
					<section class="top fdjy">
						<strong>原因：</strong> 
						<select name="reason" id="reason" validate="required" class="sel">
							<option value="">请选择</option>
							<option value="01">检验软件无此项功能</option>
							<option value="02">检验软件故障</option>
							<option value="03">其他</option>
						</select>
						<img src="images/dropDown.png">
					</section>
					<section class="bottom">
						<strong>报检/委托时间：</strong> 
						<input type="text" name="bjwtsj"
							id="bjwtsj" value="<%=nowTime%>" validate="required"
							class="form-control demo-test-date demo-test-datetime demo-test-time demo-test-credit"
							placeholder="请选择时间" ext_isNull="1" maxLength="18" style="" >
					</section>
				</section>
			</section>
			<section name='1' class="department">
				<section class="departmentCenter">
					<section class="top">
						<strong>序号：</strong> 
						<input type="text" name="num" id="num" class="task" value="1" readOnly="readonly">
					</section>
					<section class="top">
						<strong>设备名称：</strong> 
						<input type="text" name="equipmentName" id="equipmentName" 
							class="task equipmentName" validate="required">
					</section>
					<section class="top">
						<strong>拟检台数：</strong> 
						<input type="text" name="njts" id="njts" class="task deviceCount" value="" validate="required" >
					</section>
					<section class="top fdjy">
						<strong>设备种类：</strong> 
						<select name="equipmentType" id="equipmentType" class="sel equipmentType"  validate="required"></select> 
						<img src="images/dropDown.png">
					</section>
					<section class="top fdjy">
						<strong>设备品种：</strong> 
						<select name="equipmentVariety" id="equipmentVariety" class="sel equipmentVariety" validate="required"></select> 
						<img src="images/dropDown.png">
					</section>
					<section class="top fdjy">
						<strong>检验类别：</strong> 
						<select name="equipmentCategory" id="equipmentCategory" class="sel equipmentCategory" validate="required"></select> 
						<img src="images/dropDown.png">
					</section>
					<c:if test="${param.pageStatus ne 'add' }">
					<section class="top wtjy xq" style="display:none;">
						<strong>合同/协议编号：</strong> 
						<input type="text" class="contractNumber" name="contractNumber" id="contractNumber" style="border:0px;" validate="">
					</section>
					</c:if>
					<section class="top">
						<strong>任务单编号：</strong>
						<input name="rwdId1" id="rwdId1"  type="hidden">
						<input name="rwdSn1" id="rwdSn1"  type="text"   class="task rwdSn" style="border: 0px;">
					</section>
					<section class="top reportCategorySel" style="display:none;">
						<strong>报告/证书：</strong> 
						<select name="reportCategory" class="sel" onChange="callBackReport(this,this.value)"></select> 
						<img src="images/dropDown.png">
					</section>
					<section class="top">
						<strong>报告/证书编号：</strong> 
						<input type="text" name="fwbjhbg" id="fwbjhbg" class="fwbjhbg" placeholder="" readOnly="readOnly" style="border:0px;">
					</section>
					<section class="bottom">
						<strong>备注：</strong> 
						<input type="text" name="remarks"
							id="remarks" placeholder="" class="task">
					</section>
				</section>
			</section>
			<section id="device2" name='2'  class="department" style="display:none;">
				<section class="departmentCenter">
					<section class="top">
						<strong>序号：</strong> 
						<input type="text" name="num2" id="num2" class="task" value="2" readOnly="readonly">
					</section>
					<section class="top">
						<strong>设备名称：</strong> 
						<input type="text" name="equipmentName2" id="equipmentName2" 
							class="task equipmentName" validate="required">
					</section>
					<section class="top">
						<strong>拟检台数：</strong> 
						<input type="text" name="njts2" id="njts2"
							placeholder="" class="task deviceCount" value="" validate="required" >
					</section>
					<section class="top fdjy">
						<strong>设备种类：</strong> 
						<select name="equipmentType2" id="equipmentType2" class="sel equipmentType" validate="required"></select> 
						<img src="images/dropDown.png">
					</section>
					<section class="top fdjy">
						<strong>设备品种：</strong> 
						<select name="equipmentVariety2" id="equipmentVariety2" class="sel equipmentVariety" validate="required"></select> 
						<img src="images/dropDown.png">
					</section>
					<section class="top fdjy">
						<strong>检验类别：</strong> 
						<select name="equipmentCategory2" id="equipmentCategory2" class="sel equipmentCategory" validate="required"></select> 
						<img src="images/dropDown.png">
					</section>
					<section class="top">
						<strong>任务单编号：</strong>
						<input name="rwdId2" id="rwdId2" type="hidden">
						<input name="rwdSn2" id="rwdSn2" type="text" class="rwdSn" style="border: 0px;">
					</section>
					<c:if test="${param.pageStatus ne 'add' }">
					<section class="top wtjy xq2" style="display:none;">
						<strong>合同/协议编号：</strong> 
						<input type="text" class="contractNumber" name="contractNumber2" id="contractNumber2" style="border:0px;" validate="">
					</section>
					</c:if>
					
					<section class="top reportCategorySel" style="display:none;">
						<strong>报告/证书：</strong> 
						<select name="reportCategory"  class="sel" onChange="callBackReport(this,this.value)"></select> 
						<img src="images/dropDown.png">
					</section>
					<section class="top">
						<strong>报告/证书编号：</strong> 
						<input type="text" class="fwbjhbg" name="fwbjhbg2" id="fwbjhbg2" readOnly="readOnly" style="border:0px;">
					</section>
					<section class="bottom">
						<strong>备注：</strong> 
						<input type="text" name="remarks2"
							id="remarks2" placeholder="" class="task">
					</section>
				</section>
			</section>
			<section id="device3" name='3' class="department" style="display:none;">
				<section class="departmentCenter">
					<section class="top">
						<strong>序号：</strong> 
						<input type="text" name="num3" id="num3" class="task" value="3" readOnly="readonly">
					</section>
					<section class="top">
						<strong>设备名称：</strong> 
						<input type="text" name="equipmentName3" id="equipmentName3" 
							class="task equipmentName" validate="required">
					</section>
					<section class="top">
						<strong>拟检台数：</strong> 
						<input type="text" name="njts3" id="njts3"
							placeholder="" class="task deviceCount" value="" validate="required" >
					</section>
					<section class="top fdjy">
						<strong>设备种类：</strong> 
						<select name="equipmentType3" id="equipmentType3" class="sel equipmentType" validate="required"></select> 
						<img src="images/dropDown.png">
					</section>
					<section class="top fdjy">
						<strong>设备品种：</strong> 
						<select name="equipmentVariety3" id="equipmentVariety3" class="sel equipmentVariety" validate="required"></select> 
						<img src="images/dropDown.png">
					</section>
					<section class="top fdjy">
						<strong>检验类别：</strong> 
						<select name="equipmentCategory3" id="equipmentCategory3" class="sel equipmentCategory" validate="required"></select> 
						<img src="images/dropDown.png">
					</section>
					<section class="top">
						<strong>任务单编号：</strong>
						<input name="rwdId3" id="rwdId3" type="hidden">
						<input name="rwdSn3" id="rwdSn3" type="text"  class="rwdSn" style="border: 0px;">
					</section>
					<c:if test="${param.pageStatus ne 'add' }">
					<section class="top wtjy3" style="display:none;">
						<strong>合同/协议编号：</strong> 
						<input type="text" name="contractNumber3" id="contractNumber3" class="contractNumber"  style="border:0px;" validate="">
					</section>
					</c:if>
					<section class="top reportCategorySel" style="display:none;">
						<strong>报告/证书：</strong> 
						<select name="reportCategory" class="sel" onChange="callBackReport(this,this.value)"></select> 
						<img src="images/dropDown.png">
					</section>
					<section class="top">
						<strong>报告/证书编号：</strong> 
						<input type="text" class="fwbjhbg" name="fwbjhbg3" id="fwbjhbg3" placeholder="" readOnly="readOnly" style="border:0px;">
					</section>
					<section class="bottom">
						<strong>备注：</strong> 
						<input type="text" name="remarks3"
							id="remarks3" placeholder="" class="task">
					</section>
				</section>
			</section>
			<section id="device4" name='4' class="department" style="display:none;">
				<section class="top">
					<strong>序号：</strong> 
					<input type="text" name="num4" id="num4" class="task" value="4" readOnly="readonly">
				</section>
				<section class="departmentCenter">
					<section class="top">
						<strong>设备名称：</strong> 
						<input type="text" name="equipmentName4" id="equipmentName4" 
							class="task equipmentName" validate="required">
					</section>
					<section class="top">
						<strong>拟检台数：</strong> 
						<input type="text" name="njts4" id="njts4"
							placeholder="" class="task deviceCount" value="" validate="required" >
					</section>
					<section class="top fdjy">
						<strong>设备种类：</strong> 
						<select name="equipmentType4" id="equipmentType4" class="sel equipmentType" validate="required"></select> 
						<img src="images/dropDown.png">
					</section>
					<section class="top fdjy">
						<strong>设备品种：</strong> 
						<select name="equipmentVariety4" id="equipmentVariety4" class="sel equipmentVariety" validate="required"></select> 
						<img src="images/dropDown.png">
					</section>
					<section class="top fdjy">
						<strong>检验类别：</strong> 
						<select name="equipmentCategory4" id="equipmentCategory4" class="sel equipmentCategory" validate="required"></select> 
						<img src="images/dropDown.png">
					</section>
					<section class="top">
						<strong>任务单编号：</strong>
						<input name="rwdId4" id="rwdId4" type="hidden">
						<input name="rwdSn4" id="rwdSn4" type="text"  class="rwdSn" style="border: 0px;">
					</section>
					<c:if test="${param.pageStatus ne 'add' }">
					<section class="top wtjy xq4" style="display:none;">
						<strong>合同/协议编号：</strong> 
						<input type="text" name="contractNumber4" id="contractNumber4" class="contractNumber"  style="border:0px;" validate="">
					</section>
					</c:if>
					<section class="top reportCategorySel" style="display:none;">
						<strong>报告/证书：</strong> 
						<select name="reportCategory" class="sel" onChange="callBackReport(this,this.value)"></select> 
						<img src="images/dropDown.png">
					</section>
					<section class="top">
						<strong>报告/证书编号：</strong> 
						<input type="text" name="fwbjhbg4" id="fwbjhbg4" class="fwbjhbg" placeholder="" readOnly="readOnly" style="border:0px;">
					</section>
					<section class="bottom">
						<strong>备注：</strong> 
						<input type="text" name="remarks4"
							id="remarks4" placeholder="" class="task">
					</section>
				</section>
			</section>
			<section id="device5" name='5' class="department" style="display:none;">
				<section class="departmentCenter">
					<section class="top">
						<strong>序号：</strong> 
						<input type="text" name="num5" id="num5" class="task" value="5" readOnly="readonly">
					</section>
					<section class="top">
						<strong>设备名称：</strong> 
						<input type="text" name="equipmentName5" id="equipmentName5" 
							class="task equipmentName" validate="required">
					</section>
					<section class="top">
						<strong>拟检台数：</strong> 
						<input type="text" name="njts5" id="njts5"
							placeholder="" class="task deviceCount" value="" validate="required" >
					</section>
					<section class="top fdjy">
						<strong>设备种类：</strong> 
						<select name="equipmentType5" id="equipmentType5" class="sel equipmentType" validate="required"></select> 
						<img src="images/dropDown.png">
					</section>
					<section class="top fdjy">
						<strong>设备品种：</strong> 
						<select name="equipmentVariety5" id="equipmentVariety5" class="sel equipmentVariety" validate="required"></select> 
						<img src="images/dropDown.png">
					</section>
					<section class="top fdjy">
						<strong>检验类别：</strong> 
						<select name="equipmentCategory5" id="equipmentCategory5" class="sel equipmentCategory" validate="required"></select> 
						<img src="images/dropDown.png">
					</section>
					<section class="top">
						<strong>任务单编号：</strong>
						<input name="rwdId5" id="rwdId5" type="hidden">
						<input name="rwdSn5" id="rwdSn5" type="text"  class="rwdSn" style="border: 0px;">
					</section>
					<c:if test="${param.pageStatus ne 'add' }">
					<section class="top wtjy xq5" style="display:none;">
						<strong>合同/协议编号：</strong> 
						<input type="text" name="contractNumber5" id="contractNumber5" class="contractNumber"  style="border:0px;" validate="">
					</section>
					</c:if>
					<section class="top reportCategorySel" style="display:none;">
						<strong>报告/证书：</strong> 
						<select name="reportCategory" class="sel" onChange="callBackReport(this,this.value)"></select> 
						<img src="images/dropDown.png">
					</section>
					<section class="top">
						<strong>报告/证书编号：</strong> 
						<input type="text" name="fwbjhbg5" id="fwbjhbg5" class="fwbjhbg" placeholder="" readOnly="readOnly" style="border:0px;">
					</section>
					<section class="bottom">
						<strong>备注：</strong> 
						<input type="text" name="remarks5"
							id="remarks5" placeholder="" class="task">
					</section>
				</section>
			</section>
			<section id="device6" name='6' class="department" style="display:none;">
				<section class="departmentCenter">
					<section class="top">
						<strong>序号：</strong> 
						<input type="text" name="num6" id="num6" class="task" value="6" readOnly="readonly">
					</section>
					<section class="top">
						<strong>设备名称：</strong> 
						<input type="text" name="equipmentName6" id="equipmentName6" 
							class="task equipmentName" validate="required">
					</section>
					<section class="top">
						<strong>拟检台数：</strong> 
						<input type="text" name="njts6" id="njts6"
							placeholder="" class="task deviceCount" value="" validate="required" >
					</section>
					<section class="top fdjy">
						<strong>设备种类：</strong> 
						<select name="equipmentType6" id="equipmentType6" class="sel equipmentType" validate="required"></select> 
						<img src="images/dropDown.png">
					</section>
					<section class="top fdjy">
						<strong>设备品种：</strong> 
						<select name="equipmentVariety6" id="equipmentVariety6" class="sel equipmentVariety" validate="required"></select> 
						<img src="images/dropDown.png">
					</section>
					<section class="top fdjy">
						<strong>检验类别：</strong> 
						<select name="equipmentCategory6" id="equipmentCategory6" class="sel equipmentCategory" validate="required"></select> 
						<img src="images/dropDown.png">
					</section>
					<section class="top">
						<strong>任务单编号：</strong>
						<input name="rwdId6" id="rwdId6" type="hidden">
						<input name="rwdSn6" id="rwdSn6" type="text"  class="rwdSn" style="border: 0px;">
					</section>
					<c:if test="${param.pageStatus ne 'add' }">
					<section class="top wtjy xq6" style="display:none;">
						<strong>合同/协议编号：</strong> 
						<input type="text" name="contractNumber6" id="contractNumber6" class="contractNumber" style="border:0px;" validate="">
					</section>
					</c:if>
					<section class="top reportCategorySel" style="display:none;">
						<strong>报告/证书：</strong> 
						<select name="reportCategory" class="sel" onChange="callBackReport(this,this.value)"></select> 
						<img src="images/dropDown.png">
					</section>
					<section class="top">
						<strong>报告/证书编号：</strong> 
						<input type="text" name="fwbjhbg6" id="fwbjhbg6" class="fwbjhbg" placeholder="" readOnly="readOnly" style="border:0px;">
					</section>
					<section class="bottom">
						<strong>备注：</strong> 
						<input type="text" name="remarks6"
							id="remarks6" placeholder="" class="task">
					</section>
				</section>
			</section>
			<section id="device7" name='7' class="department" style="display:none;">
				<section class="departmentCenter">
					<section class="top">
						<strong>序号：</strong> 
						<input type="text" name="num7" id="num7" class="task" value="7" readOnly="readonly">
					</section>
					<section class="top">
						<strong>设备名称：</strong> 
						<input type="text" name="equipmentName7" id="equipmentName7" 
							class="task equipmentName" validate="required">
					</section>
					<section class="top">
						<strong>拟检台数：</strong> 
						<input type="text" name="njts7" id="njts7"
							placeholder="" class="task deviceCount" value="" validate="required" >
					</section>
					<section class="top fdjy">
						<strong>设备种类：</strong> 
						<select name="equipmentType7" id="equipmentType7" class="sel equipmentType" validate="required"></select> 
						<img src="images/dropDown.png">
					</section>
					<section class="top fdjy">
						<strong>设备品种：</strong> 
						<select name="equipmentVariety7" id="equipmentVariety7" class="sel equipmentVariety" validate="required"></select> 
						<img src="images/dropDown.png">
					</section>
					<section class="top fdjy">
						<strong>检验类别：</strong> 
						<select name="equipmentCategory7" id="equipmentCategory7" class="sel equipmentCategory" validate="required"></select> 
						<img src="images/dropDown.png">
					</section>
					<section class="top">
						<strong>任务单编号：</strong>
						<input name="rwdId7" id="rwdId7" type="hidden">
						<input name="rwdSn7" id="rwdSn7" type="text"  class="rwdSn" style="border: 0px;">
					</section>
					<c:if test="${param.pageStatus ne 'add' }">
					<section class="top wtjy xq7" style="display:none;">
						<strong>合同/协议编号：</strong> 
						<input type="text" name="contractNumber7" id="contractNumber7" class="contractNumber"  style="border:0px;" validate="">
					</section>
					</c:if>
					<section class="top reportCategorySel" style="display:none;">
						<strong>报告/证书：</strong> 
						<select name="reportCategory" class="sel" onChange="callBackReport(this,this.value)"></select> 
						<img src="images/dropDown.png">
					</section>
					<section class="top">
						<strong>报告/证书编号：</strong> 
						<input type="text" name="fwbjhbg7" id="fwbjhbg7" class="fwbjhbg" placeholder="" readOnly="readOnly" style="border:0px;">
					</section>
					<section class="bottom">
						<strong>备注：</strong> 
						<input type="text" name="remarks7"
							id="remarks7" placeholder="" class="task">
					</section>
				</section>
			</section>
			<section class="department" id="splc_countent">
				<section class="departmentCenter"
					style="padding-bottom: 2rem; margin-top: -1rem;">
					<section class="add_bottom">
						<ul id="splc">

						</ul>
					</section>
				</section>
			</section>

			<section class="department">
				<section class="departmentCenter"></section>
			</section>
			<section class="department">
				<section class="departmentCenter">
					<section class="top">
						<strong>部门负责人:</strong> 
						<input type="text" name="departmentMan" readonly="readonly"  onfocus="this.blur()" unselectable="on"
							id="departmentMan" placeholder="" class="task">
					</section>
					<section class="top">
						<strong>日期:</strong> 
						<input type="text" unselectable="on"
							onfocus="this.blur()" name="departmentManDate"
							id="departmentManDate" readonly="readonly" style="border: 0px;">
					</section>
					<section class="top">
						<strong>质量监督管理部意见:</strong> 
						<input type="text" name="zlshyj" id="zlshyj"
							readonly="readonly"  onfocus="this.blur()" unselectable="on"
							style="border: 0px;">
					</section>
					<section class="top">
						<strong>质量监督管理部签字:</strong> 
						<input type="text" unselectable="on"
							onfocus="this.blur()" name="zlshman" id="zlshman"
							readonly="readonly" style="border: 0px;">
					</section>
					<section class="top">
						<strong>日期:</strong> <input type="text" unselectable="on"
							onfocus="this.blur()" name="zlshtime" id="zlshtime"
							readonly="readonly" style="border: 0px;">
					</section>
					<section class="top">
						<strong>检验软件责任人确认:</strong> <input type="text" unselectable="on"
							onfocus="this.blur()" name="jyrjfzr" id="jyrjfzr"
							readonly="readonly" style="border: 0px;">
					</section>
					<section class="top">
						<strong>日期:</strong> <input type="text" unselectable="on"
							onfocus="this.blur()" name="jyrjfzrDate" id="jyrjfzrDate"
							readonly="readonly" style="border: 0px;">
					</section>
					<section class="top">
						<strong>业务服务部经办人签字:</strong> <input type="text" unselectable="on"
							onfocus="this.blur()" name="ywfwbjbr" id="ywfwbjbr"
							readonly="readonly" style="border: 0px;">
					</section>
					<section class="top">
						<strong>日期:</strong> <input type="text" unselectable="on"
							onfocus="this.blur()" name="ywfwbjbrDate" id="ywfwbjbrDate"
							readonly="readonly" style="border: 0px;">
					</section>
				</section>
			</section>
		</form>
	</section>
	<section id="add" class="act-button Button2">
		<a href="javascript:void(0);" onclick="save(this)">提交</a> 
		<a href="javascript:void(0);" onclick="back(this)">返回</a>
	</section>
	<section id="detail" class="act-button Button1">
		<a id="a_back" href="javascript:void(0);" onclick="back(this)">返回</a>
	</section>
	<section id="check" class="act-button Button3">
		<a href="javascript:void(0);" onclick="pass(this)">同意</a> 
		<a href="javascript:void(0);" onclick="fail(this)">不同意</a> 
		<a href="javascript:void(0);" onclick="back(this)">返回</a>
	</section>
	<div id="load" align="center">
		<img src="images/loading.gif" width="45" height="45" align="center" style="margin-top: 50%;" />
	</div>
</body>
</html>
