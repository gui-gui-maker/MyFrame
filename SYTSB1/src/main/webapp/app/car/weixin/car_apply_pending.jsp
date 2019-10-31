<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%
	//跳转参数
	String skip=request.getParameter("skip");
%>
    <meta charset="UTF-8">
    <title>检验派车</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link href="${pageContext.request.contextPath }/app/car/weixin/css/public.css" rel="stylesheet" media="all" />
    <link href="${pageContext.request.contextPath }/app/car/weixin/css/itemList.css" rel="stylesheet" media="all" />
    <script src="${pageContext.request.contextPath }/app/car/weixin/js/jquery.min.js"></script>
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
		//待办理
    		$.ajax({
    			url : "${pageContext.request.contextPath }/car/apply/queryCheckList.do",
    			type : "POST",
    			async : false,
    			success : function(data) {	
    				if(data.success){
    					var carApplyList= data.data;
    					getnoApplicationData(carApplyList);
    				}
    			} 
    		 });
    		
			//已办理
    		$.ajax({
    			url : "${pageContext.request.contextPath }/car/apply/queryYblList.do",
    			type : "POST",
    			async : false,
    			success : function(data) {	
    				if(data.success){
    					var carApplyList= data.data;
    					getnoApplicationData1(carApplyList);
    				}
    			} 
    		 });
    		//我的申请
    		$.ajax({
    			url : "${pageContext.request.contextPath }/car/apply/queryWdsqList.do",
    			type : "POST",
    			async : false,
    			success : function(data) {	
    				if(data.success){
    					var carApplyList= data.data;
    					getnoApplicationData2(carApplyList);
    				}
    			} 
    		 });
    		
    	})
    	
    	function getnoApplicationData2(data){
    		for(var i=0;i<data.length;i++){
    			var data_status="";
    			$("#Three").append("<li onclick='detail("+'"'+data[i].id+'","'+data[i].data_status+'"'+")' ><a href='#'> <article>"
                        +"<p class='art_first'><span class='handleTitle'>用车申请</span><span class='timer'>"+data[i].apply_date.substring(0,16).replace(/\-/g,'.')+"</span><span class='"+getdata_status(data[i].data_status).css+"'>"+getdata_status(data[i].data_status).text+"</span></p>"
                        +"<p class='art_padding'><span class='art_border'></span><span class='art_name'>"+data[i].use_user_name+"</span><span class='art_day' style='font-size: 1.2rem;'>天</span><small>"+data[i].use_days+"</small></p>"
                        +"<p class='art_stamp art_padding'><span class='city'>"+data[i].use_dep_name+"</span>"
                        	+"<span class='art_times'>"+
                        	"<span class='day1 day2'>"+data[i].use_start_date.substring(0,10).replace(/\-/g,'.')+" - </span><span class='day1 day3'>"+data[i].use_end_date.substring(0,10).replace(/\-/g,'.')+"</span>"
                        	+"</span></p></article><section class='Icon'>"
                        +"<img src='${pageContext.request.contextPath }/app/car/weixin/images/Icon_03.png' alt=''></section> </a> </li>");
    			
//     			$("#Three").append(" <li  onclick='detail("+'"'+data[i].id+'","'+data[i].data_status+'"'+")'> <a href='#'> <article>"+
//     					"<p><span class='handleTitle'>用车申请 :</span><span>"+getdata_status(data[i].data_status)+"</span><span class='timer'>"+data[i].apply_date.substring(0,16).replace(/\-/g,'.')+"</span></p>"+
//                         "<p><span>"+data[i].use_user_name+"</span><span class='department'>"+data[i].use_dep_name+"</span><small>"+data[i].use_start_date.substring(0,10).replace(/\-/g,'.')+"</small></p>"+
//                          "<p><span class='city'>"+data[i].drive_route+"</span><span class='day'>"+data[i].use_days+"天</span></p></article> <section class='Icon'>"+
//                                 "<img src='${pageContext.request.contextPath }/app/car/weixin/images/Icon_03.png' alt=''></section> </a> </li>");
    		}
    	}
    	//已办理
    	function getnoApplicationData1(data){
    		for(var i=0;i<data.length;i++){
    			var data_status="";
    			$("#two").append("<li onclick='detail("+'"'+data[i].id+'","'+data[i].data_status+'"'+")' ><a href='#'> <article>"
                        +"<p class='art_first'><span class='handleTitle'>用车申请</span><span class='timer'>"+data[i].apply_date.substring(0,16).replace(/\-/g,'.')+"</span><span class='"+getdata_status(data[i].data_status).css+"'>"+getdata_status(data[i].data_status).text+"</span></p>"
                        +"<p class='art_padding'><span class='art_border'></span><span class='art_name'>"+data[i].use_user_name+"</span><span class='art_day' style='font-size: 1.2rem;'>天</span><small>"+data[i].use_days+"</small></p>"
                        +"<p class='art_stamp art_padding'><span class='city'>"+data[i].use_dep_name+"</span>"
                        	+"<span class='art_times'>"+
                        	"<span class='day1 day2'>"+data[i].use_start_date.substring(0,10).replace(/\-/g,'.')+" - </span><span class='day1 day3'>"+data[i].use_end_date.substring(0,10).replace(/\-/g,'.')+"</span>"
                        	+"</span></p></article><section class='Icon'>"
                        +"<img src='${pageContext.request.contextPath }/app/car/weixin/images/Icon_03.png' alt=''></section> </a> </li>");
    			
//     			$("#two").append(" <li  onclick='detail("+'"'+data[i].id+'","'+data[i].data_status+'"'+")'> <a href='#'> <article>"+
//     					"<p><span class='handleTitle'>用车申请 :</span><span>"+getdata_status(data[i].data_status)+"</span><span class='timer'>"+data[i].apply_date.substring(0,16).replace(/\-/g,'.')+"</span></p>"+
//                         "<p><span>"+data[i].use_user_name+"</span><span class='department'>"+data[i].use_dep_name+"</span><small>"+data[i].use_start_date.substring(0,10).replace(/\-/g,'.')+"</small></p>"+
//                          "<p><span class='city'>"+data[i].drive_route+"</span><span class='day'>"+data[i].use_days+"天</span></p></article> <section class='Icon'>"+
//                                 "<img src='${pageContext.request.contextPath }/app/car/weixin/images/Icon_03.png' alt=''></section> </a> </li>");
    		}
    	}
    	//待办理
    	function getnoApplicationData(data){
    		for(var i=0;i<data.length;i++){
    			var data_status="";//getdata_status(data[i].data_status)
    			$("#one").append("<li onclick='check("+'"'+data[i].id+'","'+data[i].data_status+'"'+")' ><a href='#'> <article>"
                            +"<p class='art_first'><span class='handleTitle'>用车申请</span><span class='timer'>"+data[i].apply_date.substring(0,16).replace(/\-/g,'.')+"</span></p>"
                            +"<p class='art_padding'><span class='art_border'></span><span class='art_name'>"+data[i].use_user_name+"</span><span class='art_day' style='font-size: 1.2rem;'>天</span><small>"+data[i].use_days+"</small></p>"
                            +"<p class='art_stamp art_padding'><span class='city'>"+data[i].use_dep_name+"</span>"
                            	+"<span class='art_times'>"+
                            	"<span class='day1 day2'>"+data[i].use_start_date.substring(0,10).replace(/\-/g,'.')+" - </span><span class='day1 day3'>"+data[i].use_end_date.substring(0,10).replace(/\-/g,'.')+"</span>"
                            	+"</span></p></article><section class='Icon'>"
                            +"<img src='${pageContext.request.contextPath }/app/car/weixin/images/Icon_03.png' alt=''></section> </a> </li>");
//     			$("#one").append(" <li onclick='check("+'"'+data[i].id+'","'+data[i].data_status+'"'+")'> <a href='#'> <article>"+
//     					"<p><span class='handleTitle'>用车申请 :</span><span>"+getdata_status(data[i].data_status)+"</span><span class='timer'>"+data[i].apply_date.substring(0,16).replace(/\-/g,'.')+"</span></p>"+
//                         "<p><span>"+data[i].use_user_name+"</span><span class='department'>"+data[i].use_dep_name+"</span><small>"+data[i].use_start_date.substring(0,10).replace(/\-/g,'.')+"</small></p>"+
//                          "<p><span class='city'>"+data[i].drive_route+"</span><span class='day'>"+data[i].use_days+"天</span></p></article> <section class='Icon'>"+
//                                 "<img src='${pageContext.request.contextPath }/app/car/weixin/images/Icon_03.png' alt=''></section> </a> </li>");
    		}
    	}

    	//详情
    	function detail(id,data_status){
    		var selectedLi = $(".active").attr("id");
    		var skip = "pending";
    		if(selectedLi=="li1"){
    			skip = "pending";
    		}else if(selectedLi=="li2"){
    			skip = "handled";
    		}else if(selectedLi=="li3"){
    			skip = "mine";
    		}
    		var url="${pageContext.request.contextPath }/app/car/weixin/car_apply_detail.jsp?optionType=detail&id="+id+"&data_status="+data_status+"&skip="+skip;
    		location.href=url;
    	}
    	//审核
    	function check(id,data_status){
    		var selectedLi = $(".active").attr("id");
    		var skip = "pending";
    		if(selectedLi=="li1"){
    			skip = "pending";
    		}else if(selectedLi=="li2"){
    			skip = "handled";
    		}else if(selectedLi=="li3"){
    			skip = "mine";
    		}
    		var url="${pageContext.request.contextPath }/app/car/weixin/car_apply_detail.jsp?optionType=check&id="+id+"&data_status="+data_status+"&skip="+skip;
    		location.href=url;
    	}
    	

    	//申请
    	function apply(){
    		var selectedLi = $(".active").attr("id");
    		var skip = "pending";
    		if(selectedLi=="li1"){
    			skip = "pending";
    		}else if(selectedLi=="li2"){
    			skip = "handled";
    		}else if(selectedLi=="li3"){
    			skip = "mine";
    		}
    		var url="${pageContext.request.contextPath }/app/car/weixin/car_apply_detail.jsp?optionType=apply&id=&data_status=&skip="+skip;
    		location.href=url;
    	}
    	function getdata_status(data_status){
    		//0：提交申请/用车部门负责人待审核 2：分管院领导待审核 3：车队负责人待审核 4：派车中  1：办公室负责人待审核  5：用车中 6：已收车 98：已退回 99：已作废）
			if(data_status=='0'){
				return {"text":"待审核","css":"timer_button timer_button2"};//用车部门负责人
			}
			if(data_status=='2'){
				return  {"text":"待审核","css":"timer_button timer_button2"};//分管院领导
			}
			if(data_status=='3'){
				return  {"text":"待审核","css":"timer_button timer_button2"};//车队负责人
			}
			if(data_status=='4'){
				return {"text":"派车中","css":"timer_button timer_button2"};
			}
			if(data_status=='1'){
				return {"text":"待审核","css":"timer_button timer_button2"};//办公室负责人
			}
			if(data_status=='5'){
				return {"text":"用车中","css":"timer_button timer_button2"};
			}
			if(data_status=='6'){
				return {"text":"已收车","css":"timer_button"};
			}
			if(data_status=='98'){
				return {"text":"已退回","css":"timer_button timer_button3"};
			}
			if(data_status=='99'){
				return {"text":"已作废","css":"timer_button timer_button3"};
			}
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
                <li id = "li1" class="active">待办理</li>
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
