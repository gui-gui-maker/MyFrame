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
    <link href="app/css/common.css" rel="stylesheet" type="text/css" />
    
<%@ taglib uri="http://khnt.com/tags/chart" prefix="chart" %>
<link rel="stylesheet" type="text/css" href="pub/chart/css/chart.css" />
<script type="text/javascript" src="app/js/desktop.js"></script>
<script type="text/javascript" src="app/js/jquery.min.js?v=1.4.4"></script>
    <script type="text/javascript" src="app/js/jquery-powerSwitch.js"></script>
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
    alert(1)
    var userId = "<%=userid%>";
    	
    try{
    	parent.mPanelDispay({panel:'left',display:true,close:false});//隐藏父层菜单
    }catch (e){}
    
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
				height :700,
				lock : true,
				title : "文件查询",
				content : 'url:app/cloud_platform/owner/show_file_query.jsp?status=add',
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
    </script>
    </head>
    <body>
    <br />
     <div class="wrapper" style="width:30%; height:80%;display: inline;">
	     <div class="col1 clearfix">
	     	<div class="big folder">
		          <a href="app/cloud_platform/owner/show_file_index.jsp?status=modify&user=<%=sessionUser.getSysUser().getId() %>" >文件</a>
		          <span class="numb"></span>
		     </div>
		     	<div class="big picture">
			          <a href="app/cloud_platform/owner/show_file_picture.jsp" >图片</a>
			          <span class="numb"></span>
			      </div>
			      <div class="big office">
			          <a href="app/cloud_platform/owner/show_file_office.jsp" >OFFICE文档</a>
			          <span class="numb"></span>
			      </div>
			       <div class="big zip">
			          <a href="app/cloud_platform/owner/show_file_zip.jsp" >压缩文件</a>
			          <span class="numb"></span>
			      </div>
			       <div class="big recycle">
			          <a href="app/cloud_platform/owner/show_file_recycle.jsp" >回收站</a>
			          <span class="numb"></span>
			      </div>
		    </div>
			<div class="col3 clearfix">
				<div class='big no-shaw'>
				<br />
				<table cellpadding="3" cellspacing="3">
					<tr>
					<td style="width: 220px;">
					<input type="text" id="com_name" style="height: 25px;font-size: 20px;" name="com_name" ltype="text" validate="{required:true}"
						size="20" /> 
					</td>
					<td>
					<a id="search" class="l-button-warp l-button" ligeruiid="Button1012" style="width: 15px;height: 25px;">
					<span class="l-button-main l-button-hasicon"> <span
						class="l-button-text">查询</span> <span
						class="l-button-icon l-icon-search"></span> 
				</span>
				</a>
					</td>
					</tr>
				</table>
			     </div>
				 <div class="big video">
			          <a href="app/cloud_platform/owner/show_file_video.jsp">视频</a>
			          <span class="numb"></span>
			     </div>
			     <div class="big pdf">
			          <a href="app/cloud_platform/owner/show_file_pdf.jsp">PDF文档</a>
			          <span class="numb"></span>
			     </div>
		      	 <div class="big hidde">
			          <a href="javascript:showHiddenCheck()" >隐藏文件</a>
			          <span class="numb"></span>
			     </div>
			     <div class="big no-shaw"></div>
			     <div class="big no-shaw"></div>
	    	</div>
	</div>
	<div style="width:500px; height:80%;display: inline;position: absolute; border:hidden;background-color:transparent;" align="left"  class="wrapper" >
				<div id="div1" style="width:100%; height:100%; border:hidden;background-color:transparent;" ><br><br><font color="#999999">正在加载图形数据,请稍候...</font>
					<chart:chart chartNum="tjypt_space_chart" renderAt="div1" paramValue="${userId},1"/>
				</div>
	</div>
<script>
/* $("#wordSlide li").powerSwitch({
    autoTime: 2500,
    direction: "vertical",
    animation: "translate"
}); */
</script>
</body>
</html>
