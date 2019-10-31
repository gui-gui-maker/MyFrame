<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>存货领取</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link href="${pageContext.request.contextPath }/app/car/weixin/css/public.css" rel="stylesheet" media="all"/>
    <link href="${pageContext.request.contextPath }/app/car/weixin/css/itemList.css" rel="stylesheet" media="all"/>
    <script src="${pageContext.request.contextPath }/app/car/weixin/js/jquery.min.js"></script>
    <script type="text/javascript">
        $(function () {
            //待办理
            $.ajax({
                url: "${pageContext.request.contextPath }/chlq/wx/querycheck.do",
                type: "POST",
                async: false,
                success: function (data) {
                    if ( data.success ) {
                        var dbl = data.data;
                        getDBL(dbl);
                    }
                }
            });

            //已办理
            $.ajax({
                url: "${pageContext.request.contextPath }/chlq/wx/querychecked.do",
                type: "POST",
                async: false,
                success: function (data) {
                    if ( data.success ) {
                        var ybl = data.data;
                        getYBL(ybl);
                    }
                }
            });
            //我的申请
            $.ajax({
                url: "${pageContext.request.contextPath }/chlq/wx/querymy.do",
                type: "POST",
                async: false,
                success: function (data) {
                    if ( data.success ) {
                        var my = data.data;
                        getMy(my);
                    }
                }
            });

        })

        function getMy(data) {
            for (var i = 0; i < data.length; i++) {
                $("#Three").append("<li onclick='detail(" + '"' + data[i]["id"] + '","' + data[i]["status"] + '"' + ")' ><a href='#'> <article>"
                    + "<p class='art_first'><span class='handleTitle'>存货领取申请</span><span class='timer'>" + data[i]["createTime"].substring(0, 16).replace(/\-/g, '.') + "</span></p>"
                    + "<p class='art_padding'><span class='art_border'></span><span class='art_name'>" + data[i]["lqName"] + "</span><span class='" + getdata_status(data[i]).css + "'>" + getdata_status(data[i]).text + "</span></p>"
                    + "<p class='art_stamp art_padding'><span class='city'>" + data[i]["lqOrgName"] + "</span>"
                    + "<span class='art_times'>" +
                    "<span class='day1 day2'>" + data[i]["lqBh"] + "</span><span class='day1 day3'></span>"
                    + "</span></p></article><section class='Icon'>"
                    + "<img src='${pageContext.request.contextPath }/app/car/weixin/images/Icon_03.png' alt=''></section> </a> </li>");
            }
        }

        //已办理
        function getYBL(data) {
            for (var i = 0; i < data.length; i++) {
                $("#two").append("<li onclick='detail(" + '"' + data[i]["id"] + '","' + data[i]["status"] + '"' + ")' ><a href='#'> <article>"
                    + "<p class='art_first'><span class='handleTitle'>存货领取申请</span><span class='timer'>" + data[i]["createTime"].substring(0, 16).replace(/\-/g, '.') + "</span></p>"
                    + "<p class='art_padding'><span class='art_border'></span><span class='art_name'>" + data[i]["lqName"] + "</span><span class='" + getdata_status(data[i]).css + "'>" + getdata_status(data[i]).text + "</span></p>"
                    + "<p class='art_stamp art_padding'><span class='city'>" + data[i]["lqOrgName"] + "</span>"
                    + "<span class='art_times'>" +
                    "<span class='day1 day2'>" + data[i]["lqBh"] + "</span><span class='day1 day3'></span>"
                    + "</span></p></article><section class='Icon'>"
                    + "<img src='${pageContext.request.contextPath }/app/car/weixin/images/Icon_03.png' alt=''></section> </a> </li>");
            }
        }

        //待办理
        function getDBL(data) {
            for (var i = 0; i < data.length; i++) {
                $("#one").append("<li onclick='check(" + '"' + data[i]["id"] + '","' + data[i]["status"] + '","' + data[i]["activityId"] + '","' + data[i]["processId"] + '","'+data[i].createOrgId + '"'+")' ><a href='#'> <article>"
                    + "<p class='art_first'><span class='handleTitle'>存货领取申请</span><span class='timer'>" + data[i]["createTime"].substring(0, 16).replace(/\-/g, '.') + "</span></p>"
                    + "<p class='art_padding'><span class='art_border'></span><span class='art_name'>" + data[i]["lqName"] + "</span></p>"
                    + "<p class='art_stamp art_padding'><span class='city'>" + data[i]["lqOrgName"] + "</span>"
                    + "<span class='art_times'>" +
                    "<span class='day1 day2'>" + data[i]["lqBh"] + "</span><span class='day1 day3'></span>"
                    + "</span></p></article><section class='Icon'>"
                    + "<img src='${pageContext.request.contextPath }/app/car/weixin/images/Icon_03.png' alt=''></section> </a> </li>");
            }
        }

        function getdata_status(data) {
            if ( data["status"] == '0' ) {
                return {"text": "未提交", "css": "timer_button timer_button2"};
            }
            if ( data["status"] == '1' ) {
                return {"text": "已提交", "css": "timer_button timer_button2"};
            }
            if ( data["status"] == '2' ) {
                return {"text": "审核中", "css": "timer_button timer_button2"};
            }
            if ( data["status"] == '3' ) {
                return {"text": "审核通过未出库", "css": "timer_button timer_button"};
            }
            if ( data["status"] == '4' ) {
                return {"text": "审核通过已出库", "css": "timer_button timer_button"};
            }
            if ( data["status"] == '5' ) {
                return {"text": "未通过", "css": "timer_button timer_button3"};
            }
            if ( data["status"] == '6' ) {
                return {"text": "库管删除", "css": "timer_button timer_button3"};
            }
        }

        //详情
        function detail(id, data_status) {
            var url = "${pageContext.request.contextPath }/app/fwxm/recipients/wx/ch_wx_lq_handle_view.jsp?pageStatus=detail&id=" + id + "&data_status=" + data_status;
            location.href = url;
        }

        //审核
        function check(id, data_status,activityId,processId,createorgId) {
        	var url = "${pageContext.request.contextPath }/app/fwxm/recipients/wx/ch_wx_lq_handle_view.jsp?pageStatus=check&id=" + id + "&data_status=" + data_status + "&activityId=" + activityId + "&processId=" + processId+"&createorgId="+createorgId;
            location.href = url;
        }


        //申请
        function apply() {
            var url = "${pageContext.request.contextPath }/app/fwxm/recipients/wx/ch_wx_lq_detail.jsp";
            location.href = url;
        }

    </script>
</head>
<body>
<section id="web">
    <header id="header">
        <img src="${pageContext.request.contextPath }/app/car/weixin/images/tb.png">
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
            <ul id="one">
            </ul>
        </section>
        <section class="handle">
            <ul id="two">


            </ul>
        </section>
        <section class="handle">
            <ul id="Three">

            </ul>
        </section>
    </section>
    <section class="btnBg">
        <section class="btn">
            <a href="#" onclick="apply()">申请</a>
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
