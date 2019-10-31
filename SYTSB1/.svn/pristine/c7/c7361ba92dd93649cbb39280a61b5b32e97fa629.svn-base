<%@ page import="com.khnt.security.CurrentSessionUser" %>
<%@ page import="com.khnt.security.util.SecurityUtil" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!--系统封装 标签配置-->
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<%@taglib uri="http://bpm.khnt.com" prefix="bpm" %>
<!--spring security 标签配置-->
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>存货领取</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/app/car/weixin/css/public.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/app/car/weixin/css/header.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/app/car/weixin/css/content.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath }/app/car/weixin/css/selectFilter.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath }/app/car/weixin/css/jquery.notice.css">

    <script type="text/javascript"
            src="${pageContext.request.contextPath }/app/car/weixin/js/jquery-1.7.min.js"></script>
    <script src="${pageContext.request.contextPath}/app/k/km/lib/jquery.min.js"></script>
    <%-- <link href="${pageContext.request.contextPath }/app/car/weixin/css/demo.css" rel="stylesheet" media="all" /> --%>
    <script type="text/javascript" src="${pageContext.request.contextPath }/app/car/weixin/js/selectFilter.js"></script>
    <script src="${pageContext.request.contextPath}/app/k/km/lib/kh-mobile.js"></script>

    <script type="text/javascript"
            src="${pageContext.request.contextPath }/app/car/weixin/js/jquery.notice.js"></script>

    <!-- 每个页面引入，页面编码、引入js，页面标题 -->
    <%-- <%@include file="/k/kui-base-form.jsp"%> --%>
    <%
        String id = request.getParameter("id");
        CurrentSessionUser user = SecurityUtil.getSecurityUser();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sf.format(new Date());
    %>
    <link href="${pageContext.request.contextPath }/app/car/weixin/css/demoe.css" rel="stylesheet" media="all"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/app/weixin/js/timejs/date.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/app/weixin/js/timejs/iscroll.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/app/weixin/js/timejs/mobiscroll.2.13.2.js"></script>
    <link href="${pageContext.request.contextPath}/app/weixin/js/timejs/mobiscroll.2.13.2.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/app/weixin/js/timejs/common.css"/>
    <script type="text/javascript">
        var pageStatus = '';
        var pageStatus = '${param.pageStatus}';
        var serviceId = '${param.id}';
        var activityId = "${param.activityId}";//流程id
        var processId = "${param.processId}";//
        var isTxje = false;
        <bpm:ifPer function="CH_LQ_YGJE" activityId="${param.activityId}">isTxje = true;
        </bpm:ifPer>
        if(pageStatus=='check'){
            $.ajax({
                url: "${pageContext.request.contextPath }/chlq/wx/chekcCanProcess.do?serviceId=" + serviceId + "&activityId=" + activityId+"&createorgId=${param.createorgId}",
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
        $(function () {
            initOrg('blqbmId');
            initData();
            initFlow();
        });

        function initFlow() {
            $.ajax({
                url: "${pageContext.request.contextPath }/chlq/wx/getFlowStep.do?id=<%=id%>",
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
//                             $("#splc").append('<li class="ad_mind"><div class="add_fir"><img src="images/user.png" /><span class="add_distance">' + flow["op_user_name"] + '</span>'
//                                 + '<span class="add_third2">' + flow["op_remark"] + '</span>'
//                                 + '</div><div class="add_sen">' + flow["op_time"].substring(0, 16).replace(/\-/g, '.') + '</div>'
//                                 + '<div class="add_third"><span class="add_third2"></span></div></li>');
                        }
                    }
                }
            });
        }

        function initData() {
            $.ajax({
                url: "${pageContext.request.contextPath }/chlq/detail.do?id=<%=id%>",
                type: "POST",
                async: false,
                success: function (data) {
                    if ( data["success"] ) {
                        data["data"]["createTime"] = data["data"]["createTime"].substring(0, 10);
                        $("#formObj").setValues(data["data"]);
                        $("#lqRemark").val(data["data"]["lqRemark"]);
                        $("#lqwp").val(data["data"]["lqwp"]);
                        var num = data["data"]["blqbmId"];   //获取input中输入的数字
                        var numbers = $("#blqbmId").find("option"); //获取select下拉框的所有值
                        for (var j = 1; j < numbers.length; j++) {
                            if ( $(numbers[j]).val() == num ) {
                                $(numbers[j]).attr("selected", "selected");
                            }
                        }
                        if ( isTxje || (data["data"]["lqzje"] !== '' && data["data"]["lqzje"] != null && typeof(data["data"]["lqzje"]) != 'undefined') ) {
                            $("#ygjeDiv").show();
                            if ( isTxje ) {
                                $("#lqzje").removeAttr("readonly");
                            }
                        }
                    }
                }
            });
        }

        //获取部门
        function initOrg(targetId) {
            //获取部门
            $.ajax({
                url: "${pageContext.request.contextPath }/car/apply/getOrgList.do",
                type: "POST",
                async: false,
                success: function (data) {
                    if ( data.success ) {
                        var sHtml1 = '<option value="">请选择</option>';//部门
                        var orgList = data.orgList;
                        for (var i = 0; i < orgList.length; i++) {
                            sHtml1 += "<option data-name=" + orgList[i][1] + " value=" + orgList[i][0] + ">" + orgList[i][1] + "</option>"
                        }
                        $("#" + targetId).html(sHtml1);
                    }
                }
            });
        }

        function shtg() {
            if ( isTxje ) {
                if ( $("#lqzje").val() <= 0 ) {
                    if ( confirm("预估金额为0，请确认是否提交？") ) {
                        _shtg(0)
                    }
                } else {
                    _shtg($("#lqzje").val());
                }
            } else {
                _shtg();
            }
        }

        function _shtg(lqzje) {
            $('#load').show();
            $.ajax({
                url: "${pageContext.request.contextPath }/chlq/shtg.do",
                data: {
                    serviceId: serviceId,
                    activityId: activityId,
                    processId: processId,
                    ygzje: lqzje,
                },
                dataType: 'json',
                contentType: 'application/x-www-form-urlencoded; charset=utf-8',
                async: true,
                success: function (data) {
                    $('#load').hide();
                    if ( data.success ) {
                        jQnotice('操作成功!', 3000);
                        back();
                    } else {

                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    $('#load').hide();
                }
            });
        }

        function shbtg() {
            $('#load').show();
            $.ajax({
                url: "${pageContext.request.contextPath }/chlq/shbtg.do",
                data: {
                    serviceId: serviceId,
                    activityId: activityId,
                    processId: processId,
                },
                dataType: 'json',
                contentType: 'application/x-www-form-urlencoded; charset=utf-8',
                type: "post",
                async: true,
                success: function (data) {
                    $('#load').hide();
                    if ( data.success ) {
                        jQnotice('操作成功!', 3000);
                        back();
                    } else {
                        alert(data.msg);
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    $('#load').hide();
                }
            });
        }


        function depChange(id, name) {
            $("#" + name).val($("#" + id + " option:selected").attr("data-name"));
        }

        //返回
        function back() {
            var url = "${pageContext.request.contextPath }/app/fwxm/recipients/wx/ch_wx_lq_handle_list.jsp";
            location.href = url;
        }
    </script>
    <style type="text/css">
        #load {
            position: fixed;
            top: 0px;
            right: 0px;
            bottom: 0px;
            filter: alpha(opacity=60);
            background-color: #777;
            z-index: 1002;
            left: 0px;
            display: none;
            opacity: 0.5;
            -moz-opacity: 0.5;
            padding-top: 100px;
            color: #000000
        }

        .textarea-inherit {
            width: 100%;
            overflow: auto;
            word-break: break-all;
        / / 解决兼容问题
        }

        .btn_one, .btn_two {
            width: 30%;
        }
    </style>
</head>
<body>
<div class="settings" style="display:none;">
    <select name="demo" id="demo"
            style="border: solid 0px #000;appearance:none;-moz-appearance:none;-webkit-appearance:none;">
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
        <section class="departmentCenter">
            <section class="top">
                <strong>领取单号：</strong>
                <input type="text" onfocus="this.blur()" name="lqBh" id="lqBh"
                       placeholder="自动生成" readonly="readonly" style="border:0px;">
            </section>
            <section class="bottom">
                <strong>填表日期：</strong>
                <input type="text" onfocus="this.blur()" name="createTime" id="createTime"
                       readonly="readonly" style="border:0px;" value="<%=date %>">
            </section>
        </section>
        <section class="departmentCenter">
            <section class="top">
                <strong>领取部门：</strong>
                <input type="hidden" id="lqOrgId" name="lqOrgId" value="<%=user.getDepartment().getId()%>"/>
                <input type="text" name="lqOrgName" id="lqOrgName"
                       value="<%=user.getDepartment().getOrgName()%>"
                       readonly="readonly" style="border:0px;">
            </section>
            <section class="bottom">
                <strong>领取人：</strong>
                <input type="hidden" id="lqId" name="lqId" value="<%=user.getId()%>"/>
                <input type="text" name="lqName" id="lqName" value="<%=user.getName()%>"
                       readonly="readonly" style="border:0px;">
            </section>
        </section>
        <section class="departmentCenter">
            <section class="top">
                <strong>存货来源：</strong>
                <input type="hidden" name="blqbm" id="blqbm"/>
                <select name="blqbmId" onchange="depChange('blqbmId','blqbm')" id="blqbmId"
                        style="border: solid 0px #000;appearance:none;-moz-appearance:none;-webkit-appearance:none;"
                        disabled="disabled"></select>
                <img src="images/dropDown.png">
            </section>
            <section id="ygjeDiv" style="display: none;" class="bottom">
                <strong>预估金额：</strong>
                <input type="text" name="lqzje" id="lqzje" value=""
                       readonly="readonly" style="border:0px;">元
            </section>
        </section>

        <section class="departmentCenter">
            <section class="top">
                <strong>领取用途：</strong>
            </section>
            <textarea class="textarea-inherit" readonly="readonly" disabled="disabled" rows="4" name="lqRemark"
                      id="lqRemark"
                      placeholder="请填写领取用途"></textarea>
            <section class="top">
                <strong>领取物品：</strong>
            </section>
            <textarea class="textarea-inherit" readonly="readonly" disabled="disabled" rows="4" name="lqwp" id="lqwp"
                      placeholder="请填写要领取的物品"></textarea>
        </section>
        <section class="department" id="splc_countent">
            <section class="departmentCenter" style="padding-bottom: 2rem;margin-top: -1rem;">
                <section class="add_bottom">
                    <ul id="splc">
                    </ul>
                </section>
            </section>
        </section>
    </form>
    <div id="load" align="center"><img src="images/loading.gif" width="45" height="45" align="center"
                                       style="margin-top: 50%;"/></div>

</section>
<c:if test="${param.pageStatus eq 'detail'}">
    <section id="apply" class="Button1">
        <a href="javascript:" onclick="back()">返回</a>
    </section>
</c:if>
<c:if test="${param.pageStatus ne 'detail'}">
    <section id="apply" class="Button3">
        <a href="javascript:" onclick="shtg()">同意</a>
        <a href="javascript:" onclick="shbtg()">不同意</a>
        <a href="javascript:" onclick="back()">返回</a>
    </section>
</c:if>
</body>
</html>
