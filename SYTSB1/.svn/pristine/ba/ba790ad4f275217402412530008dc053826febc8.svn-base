<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.Date"%>
<%@ page import="com.khnt.utils.DateUtil"%>
<%@ page import="com.khnt.security.CurrentSessionUser"%>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

	CurrentSessionUser sessionUser = (CurrentSessionUser) SecurityContextHolder.getContext().getAuthentication()
			.getPrincipal();
	String user = sessionUser.getSysUser().getName();
	String userBm = sessionUser.getDepartment().getId();

	String firstDate = DateUtil.getFirstDateStringOfYear("yyyy-MM-dd");
	String curDate = DateUtil.getDateTime("yyyy-MM-dd", new Date());
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="description" content="">
<meta name="keywords" content="">
<base href="<%=basePath%>">
<title>四川省特种设备检验云</title>
<%-- <%@include file="/k/kui-base-list.jsp"%> --%>
<link rel="stylesheet" type="text/css" href="app/gis/scjy/d4/css/animate.css" media="all" />
<link rel="stylesheet" type="text/css" href="app/gis/scjy/d4/css/stylegx.css" media="all" />
<link rel="stylesheet" type="text/css" href="app/gis/scjy/d4/css/d4.css" media="all" />

<link rel="stylesheet" type="text/css" href="app/gis/scjy/d4/easyui1556/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="app/gis/scjy/d4/easyui1556/themes/icon.css">
<link rel="stylesheet" type="text/css" href="app/gis/scjy/d4/easyui1556/demo/demo.css">
<script src="app/gis/scjy/d4/easyui1556/jquery.min.js"></script>
<script type="text/javascript" src="app/gis/scjy/d4/easyui1556/jquery.easyui.min.js"></script> 

<script src="app/gis/scjy/d4/js/echarts.min.js"></script>
<script src="/app/gis/scjy/d4/js/maxPopUpBox.js"></script>
<script src="/app/gis/scjy/d4/js/util.js"></script>
<script src="app/gis/scjy/d4/js/draw.js"></script>
<script test="text/javascript">
$.fn.datebox.defaults.formatter = function(date){
	var y = date.getFullYear();
	var m = date.getMonth()+1;
	var d = date.getDate();
	if(m<10){
		m = "0"+m;
	}
	if(d<10){
		d = "0"+d;
	}
	return y+'-'+m+'-'+d;
} 
$.fn.datebox.defaults.parser = function(s){
    if (!s) return new Date();
    var ss = (s.split('-'));
    var y = parseInt(ss[0],10);
    var m = parseInt(ss[1],10);
    var d = parseInt(ss[2],10);
    if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
        return new Date(y,m-1,d);
    } else {
        return new Date();
    }
}
function count(){
	getRows1();
	getRows2();
	getRows3();
	getRows4();
	getRows5();
}

$(function () {
	//初始化时间框
	$('#startDate').datebox('setValue', "<%=firstDate%>");	
	$('#endDate').datebox('setValue', "<%=curDate%>");
	//统计图表
	count();
	setInterval(function(){
		count();
	}, 600000);
    //初始化小图点击事件
    $(".m-area-left>div").bind("click",function(){
	   	var chartId = $(this).find(".m-a-main div").attr("id");
	   	var label = $(this).find(".m-a-boxes-tit").html();
	   	$(".m-a-boxes-big .m-a-boxes-tit").html(label);
	   	if(chartId == "chart1"){
	   		bigInit = "chart1";
	   		drawChart1(document.getElementById('bigChart'), clsses1, rows1,2, "l");
	   	}else if(chartId == "chart2"){
	   		bigInit = "chart2";
	   		drawChart2(document.getElementById("bigChart"), clsses2, rows2,2, "l");
	   	}else{
	   		bigInit = "chart3";
	   		drawChart3(document.getElementById('bigChart'), rows, 2);
	   	}
   	
    });
    $(".m-area-right>div").bind("click",function(){
	   	var chartId = $(this).find(".m-a-main div").attr("id");
	   	var label = $(this).find(".m-a-boxes-tit").html();
	    	$(".m-a-boxes-big .m-a-boxes-tit").html(label);
	    	if(chartId == "chart4"){
	    		bigInit = "chart4";
	    		drawChart4(document.getElementById("bigChart"), labels, salary);
	   	}else if(chartId == "chart5"){
	   		bigInit = "chart5";
	   		drawChart5(document.getElementById("bigChart"),rows5,yAxisCls,xAxisCls);
	   	}
    });
     
});
</script>
<!--[if IE 8]>
<link rel="stylesheet" type="text/css" href="css/style-ie8.css" media="all" />
<![endif]-->

</head>
<body>
	<div id="sysMain" class="sysmain systongji">
		<div class="s-skin-container s-skin-container-bm"></div>
		<div id="systemTitle" class="m-top-logo">
			<div class="m-top-logo-txt">
				<span id="systemTitleText" class="text1">
					<span class="m-top-logo-img">
						<img src="app/gis/scjy/d4/images/tjy_lo.png">
					</span>四川省特检院智慧特检大数据-D4(测试版)
				</span>
			</div>
		</div>
		<div class="m-area-left">
			<div class="m-a-boxes">
				<div class="m-a-boxes-tit" id="c1">部门收入统计</div>
				<div class="m-a-main">
					<div id="chart1" style="width: 100%; height: 100%;"></div>
				</div>
				<div class="m-a-pl1"></div>
				<div class="m-a-pl2"></div>
				<div class="m-a-pr1"></div>
				<div class="m-a-pr2"></div>
				<div class="m-a-beij"></div>
				<div class="m-a-border"></div>
			</div>
			<div class="m-a-boxes">
				<div class="m-a-boxes-tit" id="c2">部门支出统计</div>
				<div class="m-a-main">
					<div id="chart2" style="width: 100%; height: 100%;"></div>
				</div>
				<div class="m-a-pl1"></div>
				<div class="m-a-pl2"></div>
				<div class="m-a-pr1"></div>
				<div class="m-a-pr2"></div>
				<div class="m-a-beij"></div>
				<div class="m-a-border"></div>
			</div>
			<div class="m-a-boxes">
				<div class="m-a-boxes-tit" id="c3">部门收支对比统计</div>
				<div class="m-a-main">
					<div id="chart3" style="width: 100%; height: 100%;"></div>
				</div>
				<div class="m-a-pl1"></div>
				<div class="m-a-pl2"></div>
				<div class="m-a-pr1"></div>
				<div class="m-a-pr2"></div>
				<div class="m-a-beij"></div>
				<div class="m-a-border"></div>
			</div>
		</div>
		<div class="m-area-right">
			<div class="m-a-boxes-big">
				<div class="m-a-boxes-tit" id="big">部门收支统计</div>
				<div class="m-a-main">
					<div id="bigChart" style="width: 100%; height: 100%;"></div>
				</div>
				<div class="m-a-pl1"></div>
				<div class="m-a-pl2"></div>
				<div class="m-a-pr1"></div>
				<div class="m-a-pr2"></div>
				<div class="m-a-beij"></div>
				<div class="m-a-border"></div>
			</div>
			<div class="m-a-boxes1">
				<div class="m-a-boxes-tit" id="c4">部门总工资对比统计</div>
				<div class="m-a-main">
					<div id="chart4" style="width: 100%; height: 100%;"></div>
				</div>
				<div class="m-a-pl1"></div>
				<div class="m-a-pl2"></div>
				<div class="m-a-pr1"></div>
				<div class="m-a-pr2"></div>
				<div class="m-a-beij"></div>
				<div class="m-a-border"></div>
			</div>
			<div class="m-a-boxes2">
				<div class="m-a-boxes-tit" id="c5">个人工资水平分布</div>
				<div class="m-a-main">
					<div id="chart5" style="width: 100%; height: 100%;"></div>
				</div>
				<div class="m-a-pl1"></div>
				<div class="m-a-pl2"></div>
				<div class="m-a-pr1"></div>
				<div class="m-a-pr2"></div>
				<div class="m-a-beij"></div>
				<div class="m-a-border"></div>
			</div>
		</div>
		<div class="m-area-top">
			<div class="m-a-tit">省特检院财务大数据</div>
			<div class="manysys" id="manysys" style="left: 200px !important; top: 10px; height: 80px;">
				<div class="sys-text" id="sys-text"></div>
				<div class="sys-data" id="sys-data" style="overflow-y: auto; height: 600px; width: 300px">
					<div class="msjt"></div>
					<%-- <ul id="bmxz"></ul> --%>
				</div>
			</div>
			<div class="m-top-l-img"></div>
			<div class="m-top-r-img"></div>
		</div>
		
	</div>
	  
	<!-- rgba(117, 158, 163, 1) -->
	<div class="easyui-panel" style="background-color:#32a1ef;border:#20c1ff;">
        <div style="display:inline-block;width:40%;">
            <input id="startDate" class="easyui-datebox" label="日期从:" labelPosition="left" style="width:100%;">
        </div>
        <div style="display:inline-block;width:40%;">
            <input id="endDate" class="easyui-datebox" label="到:" labelPosition="left" style="width:100%;">
        </div>
        <div style="display:inline-block;width:15%;">
            <a href="javascript:count();" class="easyui-linkbutton" plain="true" iconCls="icon-search">统计</a>
        </div>
      
    </div>
</body>
</html>
