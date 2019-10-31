<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<%@ taglib uri="http://khnt.com/tags/chart" prefix="chart" %>
<%
  String startDate = new SimpleDateFormat("yyyy").format(new Date())+"-01-01";
  String endDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <title></title>
  <%@include file="/k/kui-base-list.jsp" %>
  <%@ include file="/k/kui-base-form.jsp"%>
  <%@ include file="/k/kui-base-chart.jsp"%>
  
  <script test="text/javascript">
    $(function () {//jQuery页面载入事件
    	init();
    });
    function init(){
        var column=null;
     	   column =[
		 			{ display: '编号', name: 'bh',align: 'center', width: 78,totalSummary:{ render: function (e){return "" }}},
		 			{ display: '费用类型', name: 'fylx',align: 'center', width: 180,totalSummary:{ render: function (e){return "" }}},
		 			{ display: '报销人', name: 'bxr',align: 'center', width: 80,totalSummary:{ render: function (e){return "" }}},
		 			{ display: '报销日期', name: 'bxrq',align: 'center', width: 80,totalSummary:{ render: function (e){return "" }}},
		 			{ display: '单位', name: 'dw',align: 'center', width: 180,totalSummary:{ render: function (e){return "" }}},
		 			{ display: '部门', name: 'bm',align: 'center', width: 180,totalSummary:{ render: function (e){return "" }}},
		 			{ display: '金额', name: 'je',align: 'center', width: 80,totalSummary:{ render: function (e){return "" }}},
		 			{ display: '处理人', name: 'clr',align: 'center', width: 80,totalSummary:{ render: function (e){return "" }}},
		 			{ display: '处理时间', name: 'clsj',align: 'center', width: 80,totalSummary:{ render: function (e){return "" }}}
		 			
 				]
    		grid = $("#checkGrid").ligerGrid({
             columns:column, 
             enabledEdit: true,
             data:{Rows:[]},
             rownumbers:true,
             frozenRownumbers: false,
             usePager: false,
             height:'90%'
 	  	});
    }
    function query(){
      	$("body").mask("正在统计数据,请等待...");
        $.getJSON(encodeURI("feeStatisticsAction/deptFeeDetail.do?startDate=${param.startDate}&endDate=${param.endDate}&dept=${param.dept}"),function(res){
        	var gridDataArr=new Array();
        	for(var i=0; i<res.rows.length;i++){
        		var rowData=new Object();
        		rowData.bh=res.rows[i].IDENTIFIER;
        		rowData.fylx=res.rows[i].CLSS;
        		rowData.bxr=res.rows[i].PEOPLECONCERNDE;
        		rowData.bxrq=res.rows[i].BSDATE;
        		rowData.dw=res.rows[i].UNIT;
        		rowData.bm=res.rows[i].DEPARTMENT;
        		rowData.je=res.rows[i].MONEY;
        		rowData.clr=res.rows[i].HANDLE_NAME;
        		rowData.clsj=res.rows[i].HANDLE_TIME;
        		gridDataArr.push(rowData);
        	}

		    if(gridDataArr!=null){
			   grid.loadData({Rows:gridDataArr});
		    }
		    $("body").unmask();
        	
        });
    }
  </script>
</head>
<body onload="query()">
	<div class="item-tm">
    <div class="l-page-title2 has-icon has-note" style="height: 80px">
        <div class="l-page-title2-div"></div>
        <div class="l-page-title2-text"><h1>财务报账费用统计</h1></div>
        <div class="l-page-title2-note" style="height:20px;">按各经济类型，各部门报销费用统计（单位：<span>元</span>）</div>
        <div class="l-page-title2-icon">
        	<img src="k/kui/images/icons/32/statistics.png" border="0"/>
        </div>
    </div>
</div>
<div id="checkGrid" style="overflow: auto;"></div>
</body>
</html>