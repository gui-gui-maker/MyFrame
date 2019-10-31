<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>申请介入</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link href="${pageContext.request.contextPath }/app/car/weixin/css/public.css" rel="stylesheet" media="all"/>
    <link href="${pageContext.request.contextPath }/app/car/weixin/css/itemList.css" rel="stylesheet" media="all"/>
    <script src="${pageContext.request.contextPath }/app/car/weixin/js/jquery.min.js"></script>
    <%
	//跳转参数
	String skip=request.getParameter("skip");
%>
    <script type="text/javascript">
        $(function () {
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
            //待办理
            $.ajax({
                url: "${pageContext.request.contextPath }/disciplineSqjrAction/wx/querycheck.do",
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
                url: "${pageContext.request.contextPath }/disciplineSqjrAction/wx/querychecked.do",
                type: "POST",
                async: false,
                success: function (data) {
                    if ( data.success ) {
                        var ybl = data.data;
                        getYBL(ybl);
                    }
                }
            });
//             //我的申请
            $.ajax({
                url: "${pageContext.request.contextPath }/disciplineSqjrAction/wx/querymy.do",
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
    		for(var i=0;i<data.length;i++){
    			var data_status="";
    			$("#Three").append(
    					"<li onclick='detail(\""+data[i]["id"]+"\",\""+data[i]["STATUS"]+"\")' >"
	    					+"<a href='#'>"
	    						+"<article>"
	                        		+"<p class='art_first'>"
	                        			+"<span class='handleTitle'>申请介入</span>"
	                        			+"<span class='timer'>"+data[i]['create_time'].substring(0,16).replace(/\-/g,'.')+"</span>"
	                        			+"<span class='"+getdata_status(data[i]["state"]).css+"'>"+getdata_status(data[i]["state"]).text+"</span>"
	                       			+"</p>"
	                        		+"<p class='art_padding'>"
	                        			+"<span class='art_border'></span>"
	                        			+"<span class='art_name'>"+data[i]["create_user_name"]+"</span>"
	                        			+"<span class='art_day' style='font-size: 1.2rem;'></span><small></small>"
	                       			+"</p>"
	                        		+"<p class='art_stamp art_padding'>"
	                        			+"<span class='city'>"+data[i]["create_org_name"]+"</span>"
	                        			+"<span class='art_times'>"+
	                        				"<span class='day1 day2'>"+data[i]["sn"]+"</span>"
	                        			+"</span>"
	                       			+"</p>"
	                   			+"</article>"
	                   			+"<section class='Icon'>"
	                        		+"<img src='app/car/weixin/images/Icon_03.png' alt=''>"
	                        	+"</section>"
                        	+"</a>"
                       	+"</li>");
    			
    		}
        }

        //已办理
        function getYBL(data) {
    		for(var i=0;i<data.length;i++){
    			var data_status="";
    			$("#two").append(
    					"<li onclick='detail(\""+data[i]["ID"]+"\")' >"
    						+"<a href='#'>"
    							+"<article>"
                        			+"<p class='art_first'>"
	                        			+"<span class='handleTitle'>申请介入</span>"
	                        			+"<span class='timer'>"+data[i]['CREATE_TIME'].substring(0,16).replace(/\-/g,'.')+"</span>"
	                        			+"<span class='"+getdata_status(data[i]["STATE"]).css+"'>"+getdata_status(data[i]["STATE"]).text+"</span>"
                        			+"</p>"
                        			+"<p class='art_padding'>"
	                        			+"<span class='art_border'></span>"
	                        			+"<span class='art_name'>"+data[i]["CREATE_USER_NAME"]+"</span>"
	                        			+"<span class='art_day' style='font-size: 1.2rem;'></span>"
	                        			+"<small></small>"
                        			+"</p>"
                        			+"<p class='art_stamp art_padding'>"
                        				+"<span class='city'>"+data[i]["CREATE_ORG_NAME"]+"</span>"
                        				+"<span class='art_times'>"
                        					+"<span class='day1 day2'>"+data[i]["SN"]+"</span>"
                        				+"</span>"
                       				+"</p>"
                   				+"</article>"
                       				+"<section class='Icon'>"
                        				+"<img src='app/car/weixin/images/Icon_03.png' alt=''>"
                        			+"</section> "
                   			+"</a> "
             			+"</li>");
    			
    		}
    		
        }

        //待办理
        function getDBL(data) {
    		for(var i=0;i<data.length;i++){
    			var data_status="";
    			$("#one").append(
    					"<li onclick='check(\""+data[i]["ID"]+"\",\""+data[i]["STATE"]+"\",\""+data[i]["ACTIVITY_ID"]+"\",\""+data[i]["PROCESS_ID"]+"\")' >"
    					+"<a href='#'>"
    						+"<article>"
                            	+"<p class='art_first'><span class='handleTitle'>申请介入</span><span class='timer'>"+data[i]['CREATE_TIME'].substring(0,16).replace(/\-/g,'.')+"</span></p>"
                            	+"<p class='art_padding'><span class='art_border'></span>"
                            	+"<span class='art_name'>"+data[i]["CREATE_USER_NAME"]+"</span>"
                            	+"<span class='art_day' style='font-size: 1.2rem;'></span><small></small></p>"
                            +"<p class='art_stamp art_padding'><span class='city'>"+data[i]["CREATE_ORG_NAME"]+"</span>"
                            	+"<span class='art_times'>"
                            		+"<span class='day1 day2'>"+data[i]["SN"]+"</span>"
                            	+"</span></p>"
                           	+"</article>"
                           	+"<section class='Icon'>"
                            +"<img src='app/car/weixin/images/Icon_03.png' alt=''>"
                            +"</section>"
                           +"</a>"
                          +"</li>");
    		}
        }
        
        function getdata_status(STATE) {
        	if ( STATE == '0' ) {
             return {"text": "未提交", "css": "timer_button timer_button2"};
         }
         if ( STATE == '1') {
             return {"text": "审核中", "css": "timer_button timer_button2"};
         }
         if ( STATE == '2' ) {
             return {"text": "审核中", "css": "timer_button timer_button2"};
         }
         if ( STATE == '3' ) {
             return {"text": "审核中", "css": "timer_button timer_button2"};
         }
         if ( STATE == '4' ) {
             return {"text": "审核通过", "css": "timer_button timer_button"};
         }
         if ( STATE == '5' ) {
             return {"text": "审核不通过", "css": "timer_button timer_button3"};
         }
         if (STATE == '6' ) {
             return {"text": "完结", "css": "timer_button timer_button"};
         }
        }

        //详情
        function detail(id, data_status) {
    		var selectedLi = $(".active").attr("id");
    		var skip = "pending";
    		if(selectedLi=="li1"){
    			skip = "pending";
    		}else if(selectedLi=="li2"){
    			skip = "handled";
    		}else if(selectedLi=="li3"){
    			skip = "mine";
    		}
            var url = "${pageContext.request.contextPath }/app/discipline/wx/sqjr_wx_handle_detail.jsp?pageStatus=detail&id=" + id + "&data_status=" + data_status+"&skip="+skip;
            location.href = url;
        }

        //审核
        function check(id,state,activity_id,process_id) {
     		var selectedLi = $(".active").attr("id");
    		var skip = "pending";
    		if(selectedLi=="li1"){
    			skip = "pending";
    		}else if(selectedLi=="li2"){
    			skip = "handled";
    		}else if(selectedLi=="li3"){
    			skip = "mine";
    		}
    		
    		
        	var url = "${pageContext.request.contextPath }/app/discipline/wx/sqjr_wx_handle_detail.jsp?pageStatus=check&id=" + id +"&skip="+skip+"&state="+state+"&activity_id="+activity_id+"&process_id="+process_id;
            location.href = url;
        }


        //申请
        function apply() {
    		var selectedLi = $(".active").attr("id");
    		var skip = "pending";
    		if(selectedLi=="li1"){
    			skip = "pending";
    		}else if(selectedLi=="li2"){
    			skip = "handled";
    		}else if(selectedLi=="li3"){
    			skip = "mine";
    		}
            var url = "${pageContext.request.contextPath }/app/discipline/wx/sqjr_wx_handle_detail.jsp?pageStatus=add&skip="+skip;
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
                <li id = "li1"  class="active">待办理</li>
                <li id = "li2" >已办理</li>
                <li id = "li3" >我的申请</li>
            </ul>
        </section>
        <section id = "section1" class="handle block">
            <ul id="one">
            </ul>
        </section>
        <section id = "section2" class="handle">
            <ul id="two">
            </ul>
        </section>
        <section id = "section3" class="handle">
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
