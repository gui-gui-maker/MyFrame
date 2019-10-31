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
<link href="app/research/css/common.css" rel="stylesheet" type="text/css">
<title>云检索</title>
<script type="text/javascript">
try{
	parent.mPanelDispay({panel:'left',display:false,close:true});//隐藏父层菜单
}catch (e){}
	$(function() {
		
	})
	
	function indexSerachClick(){
		if($("#search").val()==""){
			alert("请输入查询条件！")
			return;
		}
		$("body").mask("正在查询数据，请稍后！");
		window.location.href= $("base").attr("href")+"enterSearchAction/searchAll.do?name="+$("#search").val();
		
	}
</script>


</head>

<body>


<div class="s_n_bg"></div>


<div class="searchHomeBG">
    <div class="homeCenter">
        <div class="logo"></div>
        
        <div class="searchTextbg">
            <input id="search" class="text ng-pristine ng-valid" type="text" ng-model="searchText" ng-class="{'promptTextRed':promptTextRed}" ng-focus="promptTextRedFocus()" ng-keyup="keyupClick($event)" placeholder="请输入姓名 / 单位 / 设备 / 等">
            <input class="btn" type="button" onclick="indexSerachClick()">
        </div>
        <div class="promptText" ng-class="{'promptTextRed':promptTextRed}">
            查询说明：以关键词进行模糊查询时，名称关键字必须大于2位。关键词不能只输入关键字如：“成都”、“成都市”、“公司”、有限公司”、“有限责任公司”等，关键字只能是汉字、字母、空格组成，多关键词用空格隔开。
        </div>
    </div>

    
</div>
     




</body>
</html>
