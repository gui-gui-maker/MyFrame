<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>申请介入</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no"/>
<meta name="apple-mobile-web-app-capable" content="yes"/>
<meta name="apple-mobile-web-app-status-bar-style" content="black"/>
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/app/humanresources/wx_leave/03/css/public.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/app/humanresources/wx_leave/03/css/header.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/app/qualitymanage/manualReport/css/content.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/app/humanresources/wx_leave/03/css/selectFilter.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/app/humanresources/wx_leave/03/css/jquery.notice.css">

<link rel="stylesheet" href="${pageContext.request.contextPath}/app/weixin/js/timejs/common.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/app/weixin/js/timejs/mobiscroll.2.13.2.css"  />

<script type="text/javascript" src="${pageContext.request.contextPath}/app/k/km/lib/app-end.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/app/humanresources/wx_leave/03/laydate/laydate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/app/humanresources/wx_leave/03/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/app/humanresources/wx_leave/03/js/selectFilter.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/app/humanresources/wx_leave/03/js/jquery.notice.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/pub/bpm/js/util.js"></script>
<script src="${pageContext.request.contextPath}/app/k/km/lib/kh-mobile.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/app/weixin/js/timejs/date.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/app/weixin/js/timejs/iscroll.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/app/weixin/js/timejs/mobiscroll.2.13.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/app/k/jqm/a-main.js" ></script>

  <%
        String id = request.getParameter("id");
        CurrentSessionUser user = SecurityUtil.getSecurityUser();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sf.format(new Date());
    %>
<script type="text/javascript">
var flowId="";
var flowName="";
  $(function(){
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
            
            //初始化数据
            $.get("${pageContext.request.contextPath }/disciplineSqjrAction/wx/detail.do?id=${param.id}",function(res) {
            	if(res.success){
    				var detailData = res.data;
    				 $("#formObj").setValues(detailData);
    				var jdsj= detailData.jdsj_start.substring(0,10).replace(/\-/g,'-');
    				 $("#jdsj_start").val(jdsj);
    				var jdsj_end= detailData.jdsj_end.substring(0,10).replace(/\-/g,'-');
    				 $("#jdsj_end").val(jdsj_end);
    				 $("#jdzl").attr({"disabled":"disabled"});
    				 $("#jdlb").attr({"disabled":"disabled"});
    				 $("#jdgzsy").attr({"disabled":"disabled"});
    			}
            });
          //判断是否该当前人审核
            var pageStatus = '${param.pageStatus}';
            if(pageStatus=='check'){
                $.ajax({
                    url: "${pageContext.request.contextPath }/disciplineSqjrAction/wx/chekcCanProcess.do?serviceId=${param.id}" ,
                    type: "POST",
                    async: false,
                    success: function (data) {
                        if ( !data["success"] ) {
                            back();
                        }
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        back();
                    }
                });
            }
            //查询流程id
            $.getJSON("${pageContext.request.contextPath }/bpm/serviceConfig/getFlowServiceConfig.do", {
				serviceCode : "ZDJC_SQJR_FLOW",
				orgId : ""
			}, function(resp) {
				if (resp.success) {
					if (resp.data.length > 0){
						flowId = resp.data[0].flowId;
						flowName = resp.data[0].flowName;
					}
				}
			});
            
            $("#detail").hide();
    		$("#apply").hide();
    		$("#add").hide();
	if(${param.pageStatus eq 'add'}){
		$("#add").show();
		$("#detail_jdsj").remove();
	}
	if(${param.pageStatus eq 'check'}){
		$("#apply").show();
		$("#jdsj").remove();
	}
	if(${param.pageStatus eq 'detail'}){
		$("#detail").show();
		$("#jdsj").remove();
	}
	
	$.ajax({
        url: "${pageContext.request.contextPath }/disciplineZdjrAction/wx/getFlowStep.do?id=${param.id}",
        type: "POST",
        async: false,
        success: function (data) {
            if ( data["success"] ) {
                var flowList = data["data"]["flowStep"];
                for (var i = 0; i < flowList.length; i++) {
                    var flow = flowList[i];
                    if(i==0){
                    	$("#splc").append('<li id="people" class="ad_mind"><div><div class="add_fir"><img src="${pageContext.request.contextPath}/app/humanresources/wx_leave/03/images/user.png" />'
                    			+'<span class="add_distance" id="peopleSign">'+ flow["op_user_name"] +'</span><span class="add_distance">发起申请</span></div><div class="add_sen" id="peopleSignDate">'+ flow["op_time"].substring(0, 16).replace(/\-/g, '.') +'</div></div></li>');
                    	
                    }else{
                    	$("#splc").append('<li id="ksfzr"><div ><div class="add_fir"><img src="${pageContext.request.contextPath}/app/humanresources/wx_leave/03/images/user.png" />'+
                    			'<span class="add_distance" id="ksfzryjSing">'+flow["op_user_name"]+'</span><span class="add_distance add_agree">审批</span></div>'
                    			+'<div class="add_sen" id="ksfzryjDate">'+flow["op_time"].substring(0, 16).replace(/\-/g, '.')+'</div>'+
                    			'<div class="add_third" id="one"><span class="add_third2"  id="ksfzryj">'+flow["op_remark"]+'</span></div></div></li>');
                    }
                }
            }
        }
    });
  });
  function submitForm(){
	  if($("#jdgzsy").val()=="" || $("#jdsj_start").val()==""|| $("#jdsj_end").val()==""){
		  alert("亲，带*号的是必填项，不能为空！");
			return false;
	  }

		var jdsj_start=$("#jdsj_start").val();
		var jdsj_end=$("#jdsj_end").val();
		var start = new Date(jdsj_start.replace("-", "/").replace("-", "/"));
		var end = new Date(jdsj_end.replace("-", "/").replace("-", "/"));
		if(end<start){
			alert("监督开始时间不能大于结束时间！");
			return false;  
		}
	  var formDatas = $("#formObj").getValues();
	  $.ajax({
          url: "${pageContext.request.contextPath }/disciplineSqjrAction/wx/saveAndSubmit1.do?flowId="+flowId,
          data: JSON.stringify(formDatas),
          dataType: 'json',
          contentType: 'application/json; charset=utf-8',
          type: "post",
          async: true,
          success: function (data) {
              $('#load').hide();
              if ( data.success ) {
                  jQnotice('操作成功!', 3000);
              } else {
                  alert(data.msg);
              }
          },error: function (XMLHttpRequest, textStatus, errorThrown) {
              $('#load').hide();
              alert("提交失败，请联系管理员处理");
          }
      });
	  
  }
  //返回
  function back() {
      var url = "${pageContext.request.contextPath }/app/discipline/wx/sqjr_wx_handle_list.jsp?skip=${param.skip}";
      location.href = url;
  }
  
  function shtg(){
	var bmfzryj=encodeURI($("#bmfzryj").val());
	var bmfgyyj=encodeURI($("#bmfgyyj").val());
	var jjfgyyj=encodeURI($("#jjfgyyj").val());
	var url="${pageContext.request.contextPath }/disciplineSqjrAction/wx/sqjrTg.do?id=${param.id}&activity_id=${param.activity_id}&bmfzryj="+bmfzryj+"&bmfgyyj="+bmfgyyj+"&jjfgyyj="+jjfgyyj;
    if(${param.state=="3"}){//纪检监察审核
   	 url="${pageContext.request.contextPath }/disciplineSqjrAction/wx/flowEnd.do?id=${param.id}&processId=${param.process_id}&type=1&bmfzryj="+bmfzryj+"&bmfgyyj="+bmfgyyj+"&jjfgyyj="+jjfgyyj;
    }
    
    $.ajax({
        url: url,
        type: "post",
        async: true,
        success: function (data) {
       	 if(data.success){
                jQnotice('操作成功!', 3000);
                back();
       	 }
        }
    });
    
  }
  function setleaveCount1(){}
  function shbtg(){
		var bmfzryj=encodeURI($("#bmfzryj").val());
		var bmfgyyj=encodeURI($("#bmfgyyj").val());
		var jjfgyyj=encodeURI($("#jjfgyyj").val());
		$.ajax({
	        url: "${pageContext.request.contextPath }/disciplineSqjrAction/wx/flowEnd.do?id=${param.id}&processId=${param.process_id}&type=1&bmfzryj="+bmfzryj+"&bmfgyyj="+bmfgyyj+"&jjfgyyj="+jjfgyyj,
	        type: "post",
	        async: true,
	        success: function (data) {
	       	 if(data.success){
	                jQnotice('操作成功!', 3000);
	                back();
	       	 }
	        }
	    });
	  
  }
  
</script>
</head>

<body >
<div class="settings" style="display:none;">
	<select name="demo" id="demo">
    	<option value="date">日期</option>
	</select>
</div>
<form id="formObj" name="formObj" method="post" action="" onsubmit="return false;" getAction="" pageStatus="">
			<input  type="hidden" name="id" value="${param.id }"/>
			<input  type="hidden" name="create_time"/>
			<input  type="hidden" name="create_user_id"/>
			<input  type="hidden" name="create_user_name"/>
			<input  type="hidden" name="create_org_id"/>
			<input  type="hidden" name="create_org_name"/>
			<input  type="hidden" name="state"/>
			<input  type="hidden" name="bmfgy_time"/>
			<input  type="hidden" name="bmfzr_time"/>
			<input  type="hidden" name="jjfgy_time"/>
			<input  type="hidden" name="cz_user_ids"/>
			<input  type="hidden" name="cz_user_names"/>
	<section id="web" class="holiday">
		<header id="header"> <img src="${pageContext.request.contextPath}/app/humanresources/wx_leave/03/images/tb.png"> </header>
		<section id="content">
			<section class="department">
				<section class="top">
                <strong>编&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</strong>
                <input type="text" onfocus="this.blur()" name="sn" id="sn"
                       placeholder="自动生成" readonly="readonly" style="border:0px;">
            	</section>
            	<section class="top">
                <strong><b>*</b>申&nbsp;请&nbsp;人：</strong>
                <input type="hidden" id="sqr_id" name="sqr_id" value="<%=user.getId()%>"/>
                <input type="text" name="sqr" id="sqr" value="<%=user.getName()%>" class="task" readonly="readonly" style="border:0px;">
            	</section>
				<section class="top">
                <strong><b>*</b>所在部门：</strong>
                <input type="hidden" id="szbm_id" name="szbm_id" value="<%=user.getDepartment().getId()%>"/>
                <input type="text" name="szbm" id="szbm" value="<%=user.getDepartment().getOrgName()%>" class="task" readonly="readonly" style="border:0px;">
            	</section>
				<section class="top">
                <strong><b>*</b>监督类别：</strong>
                <select name="jdlb" id="jdlb"  class="task"  style="border: solid 0px #000;appearance:none;-moz-appearance:none;-webkit-appearance:none;">
						<option value="1">决策事项</option>
						<option value="2">干部任免</option>
						<option value="3">项目安排</option>
						<option value="4">资金使用</option>
						<option value="5">其它</option>
					</select>
        		</section>
					 <section class="top">
                <strong><b>*</b>监督种类：</strong>
                 <select name="jdzl" id="jdzl" style="border: solid 0px #000;appearance:none;-moz-appearance:none;-webkit-appearance:none;">
						<option value="1">部门要求介入</option>
						<option value="2">事物工作需要全程监督</option>
						<option value="3">针对工作一环节的监督</option>
						<option value="4">其它</option>
					</select>
            </section>
            <section id="top" class="top">
                <strong style="width:42%;"><b>*</b>监督工作事由事项：</strong>
               <textarea class="task" rows="2" style="width: 100%;height: 50px; border: 1px" name="jdgzsy" id="jdgzsy"></textarea>
            </section>
			<section class="top"><strong style="width:35%;"><b>*</b>监督开始时间：</strong>
				<input type="text" id="jdsj_start" name="jdsj_start" <c:if test="${param.pageStatus eq 'add'}"> class='form-control demo-test-date demo-test-datetime demo-test-time demo-test-credit' </c:if>style="height:60px;border:none;outline:medium;" placeholder="时间格式为2019-01-01" readonly="readonly"  ext_isNull="1" maxLength="18" >
			</section>
			<section class="top"><strong style="width:35%;"><b>*</b>监督结束时间：</strong>
				<input type="text" id="jdsj_end" name="jdsj_end" <c:if test="${param.pageStatus eq 'add'}">  class='form-control demo-test-date demo-test-datetime demo-test-time demo-test-credit' </c:if> style="height:60px;border:none;outline:medium;" placeholder="时间格式为2019-01-01" readonly="readonly"  ext_isNull="1" maxLength="18" >
			</section>
            <section id="top" class="top">
                <strong>部门意见：</strong>
                <input type="text" name="bmfzryj" id="bmfzryj" class="task" <c:if test="${param.state ne '1' }"> readonly='readonly' </c:if>  style="border:0px; " class="task">
            </section>
			<section class="top">
                <strong style="width:38%;">部门分管院意见：</strong>
                <input type="text" name="bmfgyyj" id="bmfgyyj"  style="border:0px;width: 60%;" <c:if test="${param.state ne '2' }"> readonly='readonly' </c:if>   class="task">
            </section>
                
            <section class="top" id="top">
                <strong style="width:38%;">纪检分管院意见：</strong><input type="text" name="jjfgyyj" id="jjfgyyj"   <c:if test="${param.state ne '3' }"> readonly='readonly' </c:if> style="border:0px; width: 60%;" class="task">
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
				</section>
			</section>
		</section>
		
	</section>
	<div id="load" align="center"><img src="${pageContext.request.contextPath}/app/humanresources/wx_leave/03/images/loading3.gif" width="39" height="39" align="center" style="margin-top: 50%;"/></div>
	</form>
	
	<section id="add" class="Button2">
    <a href="javascript:" onclick="submitForm()">提交</a>
    <a href="javascript:" onclick="back()">返回</a>
	</section>
	<section id="apply" class="Button3">
        <a href="javascript:" onclick="shtg()">同意</a>
        <a href="javascript:" onclick="shbtg()">不同意</a>
        <a href="javascript:" onclick="back()">返回</a>
    </section>
    <section id=detail class="Button1">
        <a href="javascript:" onclick="back()">返回</a>
    </section>
</body>
</html>