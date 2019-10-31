<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="java.util.Calendar"%>
<%@page import="com.khnt.utils.DateUtil"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <%@include file="/k/kui-base-list.jsp" %>
    <%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
    <script src="app/statistics/js/echarts.js"></script>
    <script test="text/javascript">
    $(function () {
    	/* $("#btn1").css({"height":"20px","line-height":"20px"});
        $("#btn2").css({"height":"20px","line-height":"20px"}); */
        $("#btn1").ligerButton({
        	icon:"count",
            click: function (){
            	init();
            },text:"统计"
        });
        
        $("#btn2").ligerButton({
        	icon:"excel-export",
            click: function (){
            	out();
            },text:"导出"
        });
        $("#form1").ligerForm();     
        init();       
    });
    
    function init(){
		var startDate = $("#startDate").val();
	    var endDate = $("#endDate").val();
	   
	    $.post("sta/analyse/all_count.do",{"startDate":startDate,"endDate":endDate}, function(resp){
	    	inputGrid = $("#countGrid").ligerGrid({
	            columns: [
					{ display: '<b>综合统计</b>', columns:[
						{ display: '<b>类别</b>', name: 'category',align: 'center', width: 150},                        
                     	{ display: '<b>打印检验报告</b>', columns:[
							 { display: '<b>台数</b>', name: 'dev_p_count',align: 'center', width: 220},
			           		 { display: '<b>份数</b>', name: 'rep_p_count',align: 'center', width: 220}
                     	]},
                     	{ display: '<b>发放检验报告</b>', columns:[
							 { display: '<b>台数</b>', name: 'dev_lq_count',align: 'center', width: 100},
			           		 { display: '<b>份数</b>', name: 'rep_lq_count',align: 'center', width: 100}
                     	]}
                     ]}
	            ], 
	            data:{Rows:eval(JSON.stringify(resp.data))},//json格式的字符串转为对象
	            height:'100%',
	            usePager:false,
	            width:'100%'
	       	 },"json");
        });
    }	    
    
    function out()
    {
    	$("body").mask("正在导出数据,请等待...");
    	$("#form1").attr("action","sta/analyse/exportAllCount.do");
    	$("#form1").submit();
    	$("body").unmask();
    };
    </script>
</head>
<%
	String firstDate = DateUtil.getFirstDateStringOfYear("yyyy-MM-dd");
	String curDate  = DateUtil.getDateTime("yyyy-MM-dd", new Date());
%>
<body>
<form name="form1" id="form1" action="" getAction="" target="_blank">
<div class="item-tm" >
	<div class="l-page-title2 has-icon has-note" style="height: 80px">
		<div class="l-page-title2-div"></div>
		<div class="l-page-title2-text"><h1>检验资料综合统计数据表</h1></div>
		<div class="l-page-title2-note">按设备类别、检验资料业务情况为统计对象，其中数量单位台为设备台数，单位份为报告份数。</div>
		<div class="l-page-title2-icon"><img src="k/kui/images/icons/32/statistics.png" border="0"/></div>
		<div class="l-page-title-content" style="top:15px;height:80px;">
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="80" style="text-align:center;">统计时间：</td>
					<td width="110">
							<input id="startDate" name="startDate" type="text" ltype="date" value="<%=firstDate %>"/>
					</td>
					<td width="" align="center">到</td>
					<td  width="110">
						<input id="endDate" name="endDate" type="text" ltype="date" value="<%=curDate %>"/>
					</td>
					<td width="" style="text-align: right;float: left;padding-left: 5px;">
						<div id="btn1"></div><div id="btn2"></div>
					</td>
				</tr>
			</table>
		</div>
	</div>
</div>
</form>
<div id="container" position="center">
	<div id="countGrid"></div>   
</div>
</body>
</html>