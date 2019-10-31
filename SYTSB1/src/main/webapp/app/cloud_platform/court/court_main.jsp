<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="java.util.Map"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=8"/>
    <title></title>
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>

    <%@include file="/k/kui-base-list.jsp"%>
<link rel="stylesheet" type="text/css" href="app/cloud_platform/owner/css/stylegx2.css" />
<link rel="stylesheet" type="text/css" href="app/cloud_platform/owner/css/animate.css" />
   
<script type="text/javascript" src="app/js/desktop.js"></script>
<script type="text/javascript" src="app/js/jquery.min.js?v=1.4.4"></script>
    <script type="text/javascript">
    loadCoreLibrary({css:"main-desktop.css"});
    </script>
     <%
    CurrentSessionUser sessionUser=(CurrentSessionUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    String userid= sessionUser.getId();

    String user=sessionUser.getSysUser().getName();
    String userBm= sessionUser.getDepartment().getId();
    Map<String,String> roles=sessionUser.getRoles();
    %>
    <script type="text/javascript">
    var userId = "<%=userid%>";
    	
    try{
    	parent.mPanelDispay({panel:'left',display:true,close:false});//隐藏父层菜单
    }catch (e){}
    document.onkeydown=function(event){
        var e = event || window.event || arguments.callee.caller.arguments[0];           
         if(e && e.keyCode==13){ // enter 键
        	 $("#search").click();
        }
    };	
    $(function() {
    
	    $("#search").click(function(){
	    	var com_name = $("#com_name").val();
	    	if(com_name==""){
	    		//$.ligerDialog.
	    		alert('请填写查询条件！');
				return;
	    	}
	    	top.$.dialog({
				width : 800,
				height :600,
				lock : true,
				title : "文件查询",
				content : 'url:app/cloud_platform/owner/show_file_query.jsp?status=add&spaceType=9',
				data : {
					"window" : window,name:com_name
				}
			});
	    })
    })

    	function openDialog(title,url){
			top.$.dialog({
				width : $(top).width() * 0.8,
				height : $(top).height() * 0.8,
				lock : true,
				title : title,
				data : {
					"window" : window
				},
				content : 'url:' + url
			});
		}
    	function showHiddenCheck(){
	    	top.$.dialog({
				width : 400,
				height : 150,
				lock : true,
				title : "密码验证",
				content : 'url:app/cloud_platform/owner/check_password2.jsp?status=add',
				data : {
					"window" : window,userId:userId
				}
			});
    	}
    	
    	function spaceExpand(){
    		top.$.dialog({
				width : 400,
				height : 250,
				lock : true,
				title : "空间扩容申请",
				content : 'url:app/cloud_platform/owner/space_expand.jsp?status=add',
				data : {
					"window" : window
				}
			});
    	}
    </script>
</head>
<body>
<div id="sysMain" class="sysmain">

<div class="top_ser_bg">

	<div class="m-top-upfun">

  <div class="wrapper-orange">
    <div class="searchMeme-button-right orange-normal  searchMeme-round-right "  id="search" >
      <div class="searchMeme-button-icon searchMeme-button-inner">搜 索</div>
    </div>
    <div class="searchMeme-input-right">
      <input type="text" id="com_name" class="searchMeme-water-mark" value="搜索您的文件" style="color:#9C9A9C;" onfocus="if(this.value=='搜索您的文件'){this.value='';this.style.color = '#000'};" onblur="if(this.value==''){this.value='搜索您的文件';this.style.color='#9C9A9C'};" >
    </div>
  </div>
  
   <div class="m-top-user">
    <ul class="remaining-quota">
     <%--  <li class="g-clearfix bar">
        <div node-type="quota-full" class="remainingSpaceUi"> <span class="remainingSpaceUi_span" style="width: ${bl}%; background-color: rgb(146, 239, 85); background-position: initial initial; background-repeat: initial initial;"></span> </div>
        <div class="remainingSpace remaining-space"> <span class="bold"></span><span></span> </div>
      </li> --%>
    </ul>
   
    </div>
    
     <div class="add1"> <a href="javascript:spaceExpand()"></a> </div> 
  </div>


</div>

  <!--中间主要内容区  start -->
  <div class="m-center">
    <div class="m-center-wrap wow bounceInUp">
      <div class="m-center-main">
        <div class="iconboxes icobig ico_shfl wow fadeInUpBig"> 
        <a href="app/cloud_platform/owner/show_file_index.jsp?status=modify&spaceType=9&user=<%=sessionUser.getSysUser().getId() %>" class="nav_img">
          <div class="icon-bg  iconbg1"></div>
          <div class="icon-pic"><img src="app/cloud_platform/owner/images/wenjian.png" /></div>
          <div class="icon-txt">全部文件</div>
          </a> </div>
        <div class="iconboxes icosml ico_shjz wow fadeInUpBig" data-wow-delay="0.3s">
         <a href="app/cloud_platform/owner/show_file_picture.jsp?spaceType=9" class="nav_img">
          <div class="icon-bg iconbg2"></div>
           <div class="icon-pic"><img src="app/cloud_platform/owner/images/img.png" /></div>
          <div class="icon-txt">图片</div>
          </a> </div>
        <div class="iconboxes icoorg ico_yfaz wow fadeInUpBig" data-wow-delay="0.6s">
         <a href="app/cloud_platform/owner/show_file_video.jsp?spaceType=9" class="nav_img ">
          <div class="icon-bg iconbg4"></div>
        <div class="icon-pic"><img src="app/cloud_platform/owner/images/vid.png" /></div>
          <div class="icon-txt">视频</div>
          </a> </div>
        <div class="iconboxes icosml ico_jzjj wow fadeInUpBig" data-wow-delay="0.8s" >
         <a href="app/cloud_platform/owner/show_file_office.jsp?spaceType=9" class="nav_img ">
          <div class="icon-bg iconbg3"></div>
          <div class="icon-pic"><img src="app/cloud_platform/owner/images/office.png" /></div>
          <div class="icon-txt">Office文档</div>
          </a> </div>
        <div class="iconboxes icosml ico_shsw wow fadeInUpBig" data-wow-delay="1.0s">
         <a href="app/cloud_platform/owner/show_file_pdf.jsp?spaceType=9" class="nav_img ">
          <div class="icon-bg iconbg5"></div>
          <div class="icon-pic"><img src="app/cloud_platform/owner/images/PDF.png" /></div>
          <div class="icon-txt">Pdf文档</div>
          </a> </div>
        <div class="iconboxes icosml ico_shzz wow fadeInUpBig" data-wow-delay="1.2s"> 
        <a href="app/cloud_platform/owner/show_file_zip.jsp?spaceType=9" class="nav_img ">
          <div class="icon-bg iconbg6"></div>
          <div class="icon-pic"><img src="app/cloud_platform/owner/images/rar.png" /></div>
          <div class="icon-txt">压缩文件</div>
          </a> </div>
          
        <div class="iconboxes icobig ico_qhdm wow fadeInUpBig" data-wow-delay="1.5s">
         <a href="app/cloud_platform/owner/show_file_recycle.jsp?spaceType=9" class="nav_img ">
          <div class="icon-bg iconbg8"></div>
           <div class="icon-pic"><img src="app/cloud_platform/owner/images/dell.png" /></div>
          <div class="icon-txt">回收站</div>
          </a> </div>
          <%if(roles.get("402883a058a93e3f0158aa1c7d842956")!=null||roles.get("402883a058a93e3f0158aa1cda392963")!=null){ %>
          <div class="iconboxes icoorg ico_jczq wow fadeInUpBig" data-wow-delay="1.4s">
          <a href="app/cloud_platform/owner/need_recevice.jsp?status=add&spaceType=9&type=1" class="nav_img ">
          <div class="icon-bg iconbg7"></div>
          <div class="icon-pic"><img src="app/cloud_platform/owner/images/rar.png" /></div>
          <div class="icon-txt">审核文件</div>
          </a> </div>
          <%} %>
      </div>
    </div>
  </div>
  <!--中间主要内容区  end -->
  
</div>
</body>
</html>
