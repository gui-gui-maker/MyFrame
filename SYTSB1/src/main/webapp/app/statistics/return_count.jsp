<%@ page import="com.khnt.utils.DateUtil" %>
<%@ page import="java.util.Date" %>

<%@ page import="java.text.SimpleDateFormat" %>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://khnt.com/tags/qm" prefix="q" %>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <%@include file="/k/kui-base-list.jsp" %>
    <script test="text/javascript">
	var toDayDate = '<%=DateUtil.getDateTime("yyyy-MM-dd",new Date())%>';
	var grid;
    $(function () {
    	
    	$("#toolbar1").ligerToolBar({
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
		});
    	
    	
    	
    	
    	
    	
    	
    	
    	
        $(".layout").ligerLayout({
            height : "100%",
            topHeight : 40,
            space : 0
        });
  
        $("#startDate").ligerDateEditor({ label: '', labelWidth: 100, labelAlign: 'right',format: "yyyy-MM-dd" ,width:150});
        $("#endDate").ligerDateEditor({ label: '', labelWidth: 100, labelAlign: 'right',format: "yyyy-MM-dd" ,width:150});
        init();        
    });
   
	function searchBusiness(){
		var startDate = $("#startDate").val();
		var endDate = $("#endDate").val();
        var areaid = arguments[0];
        top.$.dialog({
            width:1000,
            height:600,
            lock:true,
            title:"详情",
            data:{"window":window},//把当前页面窗口传入下一个窗口，以便调用。
            content: 'url:'+arguments[1]+'&froModel=statis&area='+areaid+"&startDate="+startDate+"&endDate="+endDate,
            button:[{id:"cancel",name: '关闭'}]
        });
    }

    function init(){
	    var startDate = $("#startDate").val();
	    var endDate = $("#endDate").val();
	    var areaCode = $("#depCode").val();
	   // alert(areaCode);
	    $.post("report/query/queryReturn.do",{"startDate":startDate,"endDate":endDate,"depCode":depCode}, function(response){
               grid = $("#maingrid4").ligerGrid({
               columns: [
				  	 { display: '录入人员', name: 'inputName',align: 'center', width: 100, frozen: true},
              		 { display: '返回次数', name: 'inputCount',align: 'center', width: 200, frozen: true},
                 
              		
              		 { display: '审核人员', name: 'checkName',align: 'center', width: 100, frozen: true},
              		 { display: '返回次数', name: 'checkCount',align: 'center', width: 200, frozen: true}
                 	
              		
               		
               		
               ], 
               data: {Rows:eval(response.data)},//json格式的字符串转为对象
               height:'100%',
               usePager:false
           },"json");
        });
	}

    function preview(){
	    var startDate = $("#startDate").val();
	    var endDate = $("#endDate").val();
	    var reportName = "leagueTotalReport";
	    top.$.dialog({
		    width:$(top).width(),
		    height:$(top).height()-50,
		    lock:true,
		    title:"详情",
		    data:{"window":window},//把当前页面窗口传入下一个窗口，以便调用。
		    content: 'url:app/ngo/league/ngoPrincipalApp_search.jsp?reportName='+reportName+'&startDate='+startDate+"&endDate="+endDate
	    });
    }
   
    function search(){
       var startDate = $("#startDate").val();
       var endDate = $("#endDate").val();
       $.post("yf/business/clinicfoot/specialSubsidyTypeStatistics.do", {"startDate":startDate,"endDate":endDate},function(data){
    	   var ddd = data;
           grid.set({ data: { Rows: data.Rows} }); 
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
	</script>
</head>
<body>
<%String[] dates = DateUtil.getFirstOrLashDayByMonth(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));%>
<div class="item-tm">
	<div id="toolbar1"></div>
</div>

<div class="item-tm">
	<div class="l-page-title2 has-icon has-note">
		<div class="l-page-title2-div"></div>
		<div class="l-page-title2-text"><h1>返写信息统计</h1></div>
		<div class="l-page-title2-note">以检验报告退回现象为统计对象。</div>
		<div class="l-page-title2-icon"><img src="k/kui/images/icons/32/statistics.png" border="0"></div>
		<div class="l-page-title-content" style="top:15px;height:80px;">
			<form id="formObj" name="formObj">
				<table border="0" cellpadding="0" cellspacing="0" width="" class="l-table1">
					<tr>
		
						<td height="25" width="80">时间段：从</td>
						<td width="100"><input id="startDate" name="startDate" type="text" value="<%=dates[0]%>" ltype="date"/></td>
						<td width="20" align="center">至</td>
						<td width="100"><input id="endDate" name="endDate" type="text" value="<%=dates[1]%>" ltype="date"/></td>
					<td width="">&nbsp;</td>
					<td width="60"><a class="l-button3 has-icon" id="dfgsde4" onclick="alert(123);"><span class="l-icon-search"></span>查询</a></td>
					<a href="excle.jsp">导出到Excel</a>
					</tr>
				</table>
			</form>
		</div>
	</div>
</div>



<div class="item-tm itm2">
	<div class="p5">
		第二种样式
	</div>
</div>

<div position="center" >
        <div id="maingrid4" style="overflow: auto"></div>
    </div>

</body>
</html>