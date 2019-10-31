<%@page import="com.khnt.utils.DateUtil"%>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <%@include file="/k/kui-base-list.jsp" %>
    
    <script test="text/javascript">
    $(function () {
    	var toDayDate="<%=DateUtil.getDate(new Date())%>";
    	/*$("#toolbar1").ligerToolBar({
			items: [
				"-",
				{icon: "date-bz", text: "本周", click: function () {
					dateNs("bz", "startDate", "endDate", toDayDate);
				}},
				{icon: "date-by", text: "本月", click: function () {
					dateNs("by", "startDate", "endDate", toDayDate);
				}},
				{icon: "date-bjd", text: "本季度", click: function () {
					dateNs("bjd", "startDate", "endDate", toDayDate);
				}},
				{icon: "date-bn", id: "wefwef", text: "本年", disabled: false, click: function () {
					dateNs("bn", "startDate", "endDate", toDayDate);
				}},
				"-",
				{icon: "date-sz", text: "上周", click: function () {
					dateNs("sz", "startDate", "endDate", toDayDate);
				}},
				{icon: "date-sy", text: "上月", click: function () {
					dateNs("sy", "startDate", "endDate", toDayDate);
				}},
				{icon: "date-sjd", text: "上季度", click: function () {
					dateNs("sjd", "startDate", "endDate", toDayDate);
				}},
				{icon: "date-sn", text: "上年", click: function () {
					dateNs("sn", "startDate", "endDate", toDayDate);
				}},
				
				"-"
			]
		});*/
    	
    	$("#btn1").css({"height":"20px","line-height":"20px"})
        $("#btn2").css({"height":"20px","line-height":"20px"})
      
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
            },text:"导出excel"
        });
        
        $("#form1").ligerForm();
        
        init();       
    });
    function init(){
    	$("body").mask("正在加载数据...");
    	var startDate = $("#startDate").val();
	    var endDate = $("#endDate").val();
	    $.post("department/basic/deviceCountByTanker.do",{"startDate":startDate,"endDate":endDate},function(data){
	    	inputGrid = $("#countGrid").ligerGrid({
	            columns: [
	                 { display: '罐车数量', name: 'totalCount',align: 'center', width: 150},
                     { display: '已检设备', name: 'checkCount',align: 'center', width: 150},
                     { display: '未检设备', name: 'uncheckCount',align: 'center', width: 150}  
	            ], 
	            onAfterShowData:function(currentData){
	            	$("body").unmask();
	            	$(".l-grid-totalsummary-cell").css({"border":"1px solid #C9D6E9"});
	            	var trs=$("#countGrid .l-grid-body-table tr");
	            	//for(i=0;i<trs.length;i++){
	            		//$($(trs[i]).find("td")[0]).css({"background":"#e0ecff"});
	            	//}
	            },
	            data:{Rows:eval(JSON.stringify(data.data))},//json格式的字符串转为对象
	            height:'100%',
	            usePager:false,
	            width:'100%'
	       	 },"json");
        });
    }	    
    
    </script>
	<style type="text/css">
	.top {
	    color: black;
	    height: 125px;
	}
	.counter{
	    font-weight:bold;
	    font-size: 14px;
	    text-decoration:none;
		padding: 0 5px;
	}
	</style>
</head>
<body>
<div class="item-tm">
	<div id="toolbar1"></div>
</div>
<form name="form1" id="form1" action="" getAction="" target="_blank">
<div class="item-tm" >
	<div class="l-page-title2 has-icon has-note" style="height: 80px">
		<div class="l-page-title2-div"></div>
		<div class="l-page-title2-text"><h1>设备报检统计</h1></div>
		<div class="l-page-title2-note">以设备是否报检为统计对象。</div>
		<div class="l-page-title2-icon"><img src="k/kui/images/icons/32/statistics.png" border="0"></div>
		<!-- 
		<div class="l-page-title-content" style="top:15px;height:80px;">
		
		<table border="0" cellpadding="0" cellspacing="0">
			 <tr>
							<td width="80" style="text-align:center">统计时间：从</td>
							<td width="" width="100"><input id="startDate" name="startDate" type="text" ltype="date" value="2014-01-01"  /></td>
							<td width="" align="center">至</td>
							<td width="" width="100"><input id="endDate" name="endDate" type="text" ltype="date" value="<%=DateUtil.getDate(new Date()) %>"  /></td>
							</tr>
							<tr>
							<td width=""></td>
							<td width="" style="text-align: right;float: left;padding-top: 5px"><div id="btn1" >  </div></td>
						</tr>
					</table>
		
		</div>
		 -->
	</div>
</div>
</form>
<div position="center"  >
        <div id="countGrid"  ></div>   
 </div>
</body>
</html>