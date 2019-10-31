<%@ page import="com.khnt.security.CurrentSessionUser" %>
<%@ page import="com.khnt.security.util.SecurityUtil" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!--系统封装 标签配置-->
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
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
        boolean isFgld = user.getPermissions().values().contains("TJY2_RL_FGLDSH");
    %>
    <link href="${pageContext.request.contextPath }/app/car/weixin/css/demoe.css" rel="stylesheet" media="all"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/app/weixin/js/timejs/date.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/app/weixin/js/timejs/iscroll.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/app/weixin/js/timejs/mobiscroll.2.13.2.js"></script>
    <link href="${pageContext.request.contextPath}/app/weixin/js/timejs/mobiscroll.2.13.2.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/app/weixin/js/timejs/common.css"/>
    <script type="text/javascript">
        $(function () {
            initData();
            initOrg('blqbmId');
            var pageStatus = '${pageStatus}';
        });

        function initData() {
            $.ajax({
                url: "${pageContext.request.contextPath }/chlq/detail.do?id=<%=id%>",
                type: "POST",
                async: false,
                success: function (data) {

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

        //获取人员
        function getUserList(data, user_id, user_Name) {
            var sHtml2 = '<option value="">请选择</option>';//人员
            var name = $("#use_user_id").find("option:selected").text();
            var userId = $("#use_user_id").find("option:selected").val();
            //获取人员
            $.ajax({
                url: "${pageContext.request.contextPath }/car/apply/getUserList.do?org_id=" + data,
                type: "POST",
                async: false,
                success: function (data) {
                    if ( data.success ) {
                        var userList = data.userList;
                        if ( userId != "undefined" && userId != "" && userId != null ) {
                            var show = true;
                            for (var i = 0; i < userList.length; i++) {
                                if ( userId == userList[i][0] ) {
                                    show = false;
                                    break;
                                }
                            }
                            if ( show ) {
                                sHtml2 += "<option   value=" + userId + " selected ='true'>" + name + "</option>";
                            }
                        }
                        if ( user_id != "" && user_id != null ) {
                            sHtml2 += "<option value=" + user_id + " selected ='true'>" + user_Name + "</option>";
                        }
                        for (var i = 0; i < userList.length; i++) {

                            if ( userId == userList[i][0] ) {
                                sHtml2 += "<option value=" + userList[i][0] + " selected ='true'>" + userList[i][1] + "</option>"
                            } else {
                                sHtml2 += "<option value=" + userList[i][0] + ">" + userList[i][1] + "</option>"
                            }
                        }
                        $("#use_user_id").html(sHtml2);
                    }
                }
            });
        }


        function depChange(id, name) {
            var org_id = $("#" + id).val();
            $("#" + name).val($("#" + id + " option:selected").attr("data-name"));
            getUserList(org_id);
        }

        function userChange() {
            $("#use_user_name").val($("#use_user_id").find("option:selected").text());
            var user_id = document.getElementById("use_user_id").value;
            $.ajax({
                url: "${pageContext.request.contextPath }/car/apply/queryMobileTel.do?user_id=" + user_id,
                type: "POST",
                async: false,
                success: function (data) {
                    if ( data.success ) {
                        var mobile_tel = data.data;
                        document.getElementById("use_user_phone").value = mobile_tel;
                    }
                }
            });
        }

        var _this;

        function submitForm() {
            var result = preSave();
            if ( !result["success"] ) {
                alert(result["msg"]);
                return;
            }
            if(<%=isFgld%>){
                _submitForm(false);
                return;
            }
            _this = this;
            var strhtml = '<div class="maskbou"></div>'
                + '<div class="add_name">'
                + '<label class="pu_colse">x</label>'
                + '<div class="title"><span><h1>是否需要提交到保障部进行金额预估？</h1></span></div><div class="cont">'
                + '<div class="int_mo" style="margin-left: 5px;margin-right: 5px;">' +
                '            <font size="3px"><b>说明：</b></font><br>选择“是”，提交到保障部进行金额预估，预估完毕后提交到部门负责人审核，金额超过1000需要分管领导审核<br>\n' +
                '            选择“否”，提交到部门负责人进行审核。</div></div>'
                + '<div class="infobtn"><button class="btn_one " onclick="cback(3)" >取消</button><button class="btn_two " onclick="cback(2)" >是</button><button class="btn_two" onclick="cback(1)" >否</button></div></div>'
            $("body").append(strhtml);
            _this.cback = function (num) {
                _this.close1();
                if ( num == 1 ) {
                    _submitForm(false);
                } else if ( num == 2 ) {
                    _submitForm(true);
                }
            }
            _this.close1 = function (num) {
                $(".maskbou").remove();
                $('.add_name').remove();
            }
            $(".pu_colse").click(function () {
                _this.close1();
            });
        }

        function preSave() {
            if ( $("#blqbmId").val() == '' ) {
                return {success: false, msg: '请选择存货来源'};
            }
            if ( $("#lqRemark").val() == '' ) {
                return {success: false, msg: '请填写领取用途'};
            }
            if ( $("#lqwp").val() == '' ) {
                return {success: false, msg: '请填写要领取的物品'};
            }
            return {success: true};
        }

        //提交
        function _submitForm(isYgje) {
            var formDatas = $("#formObj").getValues();
            formDatas["dataFrom"] = '1';//微信端提交
            $('#load').show();
            $.ajax({
                url: "${pageContext.request.contextPath }/chlq/wx/saveAndSubmit.do?isYgje=" + isYgje,
                data: JSON.stringify(formDatas),
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
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
                    alert("提交失败，请联系管理员处理");
                }
            });
        }

        function starfFlow() {

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
                <input type="text" name="lqBh" id="lqBh"
                       placeholder="自动生成" readonly="readonly" style="border:0px;">
            </section>
            <section class="bottom">
                <strong>填表日期：</strong>
                <input type="text" name="createTime" id="createTime"
                       readonly="readonly" style="border:0px;" value="<%=date %>">
            </section>
        </section>
        <section class="departmentCenter">
            <section class="top">
                <strong>领取部门：</strong>
                <input type="hidden" id="lqOrgId" name="lqOrgId" value="<%=user.getDepartment().getId()%>"/>
                <input type="text" unselectable="on" name="lqOrgName" id="lqOrgName"
                       value="<%=user.getDepartment().getOrgName()%>"
                       readonly="readonly" style="border:0px;">
            </section>
            <section class="bottom">
                <strong>领取人：</strong>
                <input type="hidden" id="lqId" name="lqId" value="<%=user.getId()%>"/>
                <input type="text" unselectable="on" name="lqName" id="lqName" value="<%=user.getName()%>"
                       readonly="readonly" style="border:0px;">
            </section>
        </section>
        <section class="departmentCenter">
            <section class="top">
                <strong>存货来源：</strong>
                <input type="hidden" name="blqbm" id="blqbm"/>
                <select name="blqbmId" onchange="depChange('blqbmId','blqbm')" id="blqbmId"
                        style="border: solid 0px #000;appearance:none;-moz-appearance:none;-webkit-appearance:none;"
                        style="border:0px"></select>
                <img src="images/dropDown.png">
            </section>
        </section>

        <section class="departmentCenter">
            <section class="top">
                <strong>领取用途：</strong>
            </section>
            <textarea class="textarea-inherit" rows="4" name="lqRemark" id="lqRemark" placeholder="请填写领取用途"></textarea>
            <section class="top">
                <strong>领取物品：</strong>
            </section>
            <textarea class="textarea-inherit" rows="4" name="lqwp" id="lqwp" placeholder="请填写要领取的物品"></textarea>
        </section>
    </form>
    <div id="load" align="center"><img src="images/loading.gif" width="45" height="45" align="center"
                                       style="margin-top: 50%;"/></div>

</section>

<section id="apply" class="Button2">
    <a href="javascript:" onclick="submitForm()">提交</a>
    <a href="javascript:" onclick="back()">返回</a>
</section>
<%--<section id="check" class="Button3">
    <a href="javascript:" name="bc" onclick="add_name({type:['1','保存'],empty:false})">同意</a>
    <a href="javascript:" name="TJ" onclick="add_name({type:['0','保存'],empty:false})">不同意</a>
    <a href="javascript:" name="fh" onclick="back()">返回</a>
</section>
<section id="check2" class="Button3">
    <a href="javascript:" name="bc" onclick="add_name({type:['1','保存'],empty:false})">同意</a>
    <a href="javascript:" name="TJ" onclick="zuofei()">作废</a>
    <a href="javascript:" name="fh" onclick="back()">返回</a>
</section>
<section id="assigns" class="Button2">
    <a href="javascript:" name="TJ" onclick="assigns()">派车</a>
    <a href="javascript:" name="fh" onclick="back()">返回</a>
</section>
<section id="receive" class="Button2">
    <a href="javascript:" name="TJ" onclick="receive()">收车</a>
    <a href="javascript:" name="fh" onclick="back()">返回</a>
</section>
<section id="detail" class="Button1">
    <a id="a_back" href="javascript:" name="fh" onclick="back()">返回</a>
</section>--%>
<%-- </section> --%>
</body>
</html>
