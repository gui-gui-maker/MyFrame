<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@page import="java.util.Date"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.utils.DateUtil"%>
<head pageStatus="${param.status}">
 <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>

    <%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="app/js/desktop.js"></script>
<script type="text/javascript" src="app/js/jquery-powerSwitch.js"></script>
<script type="text/javascript" src="app/research/js/encode.js"></script>
<link href="app/cloud_platform/down/css/common.css" rel="stylesheet" type="text/css">
<title>云检索</title>
<script type="text/javascript">
try{
	parent.mPanelDispay({panel:'left',display:false,close:true});//隐藏父层菜单
}catch (e){}
document.onkeydown=function(event){
    var e = event || window.event || arguments.callee.caller.arguments[0];           
     if(e && e.keyCode==13){ // enter 键
    	 indexSerachClick();
    }
};	
	$(function() {
		
	})
	
	function indexSerachClick(){
		if($("#search").val()==""){
			alert("请输入查询条件！")
			return;
		}
		//$("body").mask("正在查询数据，请稍后！");
		window.location.href= $("base").attr("href")+"resourceInfo/queryDownResource.do?name="+encodeURI($("#search").val())+"&spaceType=9";
		
	}
</script>


</head>

<body>


<div class="s_n_bg"></div>


<div class="searchHomeBG">
    <div class="homeCenter">
        <div class="logo"></div>
        
        <div class="searchTextbg">
            <input id="search" class="text ng-pristine ng-valid" type="text" ng-model="searchText" ng-class="{'promptTextRed':promptTextRed}" ng-focus="promptTextRedFocus()" ng-keyup="keyupClick($event)" placeholder="请输入文件名称">
            <input class="btn" type="button" onclick="indexSerachClick()">
        </div>
        <div align="center" style="margin-top: 40px;font-size: 20px;">
        	<a href="resourceInfo/queryDownFile.do?spaceType=9">下载中心</a>
        </div>
        <div align="center" style="margin-top: 20px;">
        	<img src="app/cloud_platform/down/images/qrcode_for_gh.jpg" alt="" width="120px;"/>
        </div>
        <br />
        <br />
        <br />
        <div align="center" style="color: black;font-size: 20px;">技术支持：信息宣传中心</div>
    </div>

    
</div>
     




</body>
</html>
