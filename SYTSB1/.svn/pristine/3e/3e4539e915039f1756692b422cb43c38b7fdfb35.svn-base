<%@page import="com.khnt.utils.DateUtil"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://khnt.com/tags/qm" prefix="q" %>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
   <%@include file="/k/kui-base-list.jsp"%>
    <script test="text/javascript">
	var toDayDate = '<%=DateUtil.getDateTime("MM",new Date())%>';
	$(function(){//jQuery页面载入事件
		$("#formObj").ligerForm();
		$("#button1").ligerButton({icon:"count",text:"统计",click:function(){
			count();
		}});
		initGrid();
	});
	
	
	function initGrid(){
		  var year = $("#year").val();
		  var month = $("#month").val().split("月")[0];
		  grid = $("#countGrid").ligerGrid({
          columns: [
                   { display: '车辆id', name: 'carId',hide:true}, 
                   { display: '单位id', name: 'unitId',hide:true}, 
                   { display: '车牌号', name: 'carNum',align: 'left', width: 80}, 
				   { display: '管理部门', name: 'manageDep',align: 'left', width: 150},
					{ display: '购车时间', name: 'buyDate',align: 'center', width: 80},
					{ display: '厂牌车型', name: 'carBrand',align: 'left', width: 150},
					{ display: '行驶总里程(公里)', name: 'carMil',align: 'right', width: 120},
					{ display: '本月行驶里程(公里)', name: 'carMilMonth',align: 'right', width: 120,totalSummary:{render: function (suminf, column, cell)
                        {
                        return '<div> ' + suminf.sum + '公里</div>';
                    }} },
					{ display: '月实用汽油金额(元)', name: 'carOilMonth',align: 'right', width: 120,totalSummary:{render: function (suminf, column, cell)
                        {
                        return '<div> ' + suminf.sum + '元</div>';
                    }} },
					{ display: '百公里平均油耗(升)', name: 'carOil100',align: 'right', width: 120,totalSummary:{render: function (suminf, column, cell)
                        {
                        return '<div> ' + suminf.avg + '升</div>';
                    }} },
					{ display: '过路过桥费(元)', name: 'carRoadFee',align: 'right', width: 120,totalSummary:{render: function (suminf, column, cell)
                        {
                        return '<div> ' + suminf.sum + '元</div>';
                    }} },
					{ display: '洗车费(元)', name: 'carWashFee',align: 'right', width: 100,totalSummary:{render: function (suminf, column, cell)
                        {
                        return '<div> ' + suminf.sum + '元</div>';
                    }} },
                   { display: '停车费(元)', name: 'carParkFee',align: 'right', width: 80,totalSummary:{render: function (suminf, column, cell)
                       {
                       return '<div> ' + suminf.sum + '元</div>';
                   }} },
                   { display: '维修费(元)', name: 'carRepairFee',align: 'right', width: 80,totalSummary:{render: function (suminf, column, cell)
                       {
                       return '<div> ' + suminf.sum + '元</div>';
                   }} },
                   { display: '审车费(元)', name: 'carYearFee',align: 'right', width: 80,totalSummary:{render: function (suminf, column, cell)
                       {
                       return '<div> ' + suminf.sum + '元</div>';
                   }} },
                   { display: '保养费(元)', name: 'carBeautiFee',align: 'right', width: 80,totalSummary:{render: function (suminf, column, cell)
                       {
                       return '<div> ' + suminf.sum + '元</div>';
                   }} },
                   { display: '保险费(元)', name: 'carBxFee',align: 'right', width: 80,totalSummary:{render: function (suminf, column, cell)
                       {
                       return '<div>' + suminf.sum + '元</div>';
                   }} }
            ], 
            height:'100%',
            usePager:false,
            rownumbers:true,
            url:encodeURI("oa/car/apply/countCarAllCost.do?year="+year+"&month="+month)
     });
	}
	
	//执行统计
	function count(){
		 var year = $("#year").val();
		 var month = $("#month").val().split("月")[0];
		 $("body").mask("正在加载...");
		 $.getJSON("oa/car/apply/countCarAllCost.do?year="+year+"&month="+month,function(response){
			  $("body").unmask()
			  if(response.success){
				   var gridData=response.Rows;
				   if(gridData!=null){
					   grid.loadData({Rows:response.Rows});
				   }
			  }
		  });
	}
	
	</script>
</head>
<body>
<%
Calendar calendar = Calendar.getInstance();
int year = calendar.get(Calendar.YEAR);
int month = calendar.get(Calendar.MONTH);
%>
<div class="item-tm">
	<div id="toolbar1"></div>
</div>

<div class="item-tm">
	<div class="l-page-title2 has-icon has-note">
		<div class="l-page-title2-div"></div>
		<div class="l-page-title2-text"><h1>车辆费用统计</h1></div>
		<div class="l-page-title2-note">统计指定年份中指定月份车辆产生的费用情况</div>
		<div class="l-page-title2-icon"><img src="k/kui/images/icons/32/statistics.png" border="0"></div>
		<div class="l-page-title-content" style="top:5px;">
			<form id="formObj" name="formObj">
				<table border="0" cellpadding="0" cellspacing="0" width="" class="l-table1">
					<tr>
						<td width="60" align="right">年度&nbsp;&nbsp;</td>
						<td width="60">
						<input type="text" ltype="select" name="year" id="year" validate="{required:true}" 
							ligerui="{initValue:'<%=year%>', data: [  
	                            { text:'<%=year-5%>', id:'<%=year-5%>' }
	                            , { text:'<%=year-4%>', id:'<%=year-4%>' }
	                            , { text:'<%=year-3%>', id:'<%=year-3%>' }
	                            , { text:'<%=year-2%>', id:'<%=year-2%>' }
	                            , { text:'<%=year-1%>', id:'<%=year-1%>' }
	                            , { text:'<%=year%>', id:'<%=year%>' }
	                            , { text:'<%=year+1%>', id:'<%=year+1%>' }
	                            , { text:'<%=year+2%>', id:'<%=year+2%>' }
	                            , { text:'<%=year+2%>', id:'<%=year+3%>' }
	                            , { text:'<%=year+4%>', id:'<%=year+4%>' }
	                            , { text:'<%=year+5%>', id:'<%=year+5%>' }] }" />
						</td>
						<td width="60" align="right">月份&nbsp;&nbsp;</td>
	                    <td width="60">
	                      <input type="text" ltype="select" name="month" id="month" validate="{required:true}" 
											ligerui="{initValue:'<%=DateUtil.getDateTime("MM", new Date()) %>', data: [  
                                                                { text:'01月', id:'01' }
                                                                , { text:'02月', id:'02' }
                                                                , { text:'03月', id:'03' }
                                                                , { text:'04月', id:'04' }
                                                                , { text:'05月', id:'05' }
                                                                , { text:'06月', id:'06' }
                                                                , { text:'07月', id:'07' }
                                                                , { text:'08月', id:'08' }
                                                                , { text:'09月', id:'09' }
                                                                , { text:'10月', id:'10' }
                                                                , { text:'11月', id:'11' }
                                                                , { text:'12月', id:'12' }] }" />
	                    </td>
	                    <td width=""><div id="button1"></div></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div id="countGrid" style="overflow: auto"></div>
</div>
</body>
</html>