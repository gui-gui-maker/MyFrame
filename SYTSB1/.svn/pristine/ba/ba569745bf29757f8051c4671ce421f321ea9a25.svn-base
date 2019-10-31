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
<script type="text/javascript" src="app/common/lodop/LodopFuncs.js"></script>
  
  <script test="text/javascript">
    $(function () {//jQuery页面载入事件
		//打印
		$("#btn1").css({"height":"20px","width":"50px","padding-buttom":"10px"});
        //导出
		$("#btn2").css({"height":"20px","width":"50px","line-height":"18px"});
		$("#btn1").ligerButton({
            icon:"print",
            click: function (){
            	printInfo();
            },
            text:"打印"
        });
        $("#btn2").ligerButton({
            icon:"excel-export",
            click: function (){
                out();
            },
            text:"导出"
        });
		initGrid();
    });
    function initGrid(){
       var column=null;
    	   column =[
	{ display: '票号', name: 'INVOICE_NO',align: 'center', width: 78,totalSummary:{ render: function (e){return "" }}},
	{ display: '发票类型', name: 'CLSS',align: 'center', width: 50,totalSummary:{ render: function (e){return "" }}},
	{ display: '开票名称', name: 'COMPANY_NAME',align: 'center', width: 100,totalSummary:{ render: function (e){return "" }}},
	{ display: '检验部门', name: 'DEPT',align: 'center', width:100,totalSummary:{ render: function (e){return "" }}},
	{ display: '合同号/编号', name: 'PAY_NO',align: 'center', width: 100,totalSummary:{ render: function (e){return "" }}},
	{ display: '总金额', name: 'PAY_RECEIVED',align: 'center', width: 70,totalSummary:{ render: function (e){return "" }}},
	{ display: '收费方式', name: 'PAY_TYPE',align: 'center', width: 80,totalSummary:{ render: function (e){return "" }}},
	{ display: '现金', name: 'CASH_PAY',align: 'center', width: 70,totalSummary:{ render: function (e){return "" }}},
	{ display: '转账', name: 'REMARK',align: 'center', width: 70,totalSummary:{ render: function (e){return "" }}},
	{ display: 'POS', name: 'POS',align: 'center', width: 70,totalSummary:{ render: function (e){return "" }}},
	{ display: '上缴财政', name: 'HAND_IN',align: 'center', width: 70,totalSummary:{ render: function (e){return "" }}},
	{ display: '开票人', name: 'RECEIVE_MAN_NAME',align: 'center', width: 80,totalSummary:{ render: function (e){return "" }}},
	{ display: '开票日期', name: 'pay_date',align: 'center', width: 80,totalSummary:{ render: function (e){return "" }}}
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
      	var url = encodeURI("feeStatisticsAction/deptDetail.do?startDate=${param.startDate}&endDate=${param.endDate}&dept=${param.dept}&clss=${param.clss}");
        $.getJSON(url,function(res){
        	
        	var gridDataArr=new Array();
        	for(var i=0; i<res.rows.length;i++){
        		console.log(res.rows[i].BSDATE);
        		var rowData=new Object();
        		rowData.INVOICE_NO=res.rows[i].INVOICE_NO;
        		rowData.CLSS=res.rows[i].CLSS;
        		rowData.COMPANY_NAME=res.rows[i].COMPANY_NAME;
        		rowData.DEPT=res.rows[i].DEPT;
        		rowData.PAY_NO=res.rows[i].PAY_NO;
        		rowData.PAY_RECEIVED=res.rows[i].PAY_RECEIVED;
        		rowData.PAY_TYPE=res.rows[i].PAY_TYPE;
        		rowData.CASH_PAY=res.rows[i].CASH_PAY;
        		rowData.REMARK=res.rows[i].REMARK;
        		rowData.POS=res.rows[i].POS;
        		rowData.HAND_IN=res.rows[i].HAND_IN;
        		rowData.RECEIVE_MAN_NAME=res.rows[i].RECEIVE_MAN_NAME;
        		rowData.pay_date=res.rows[i].PAY_DATE;
        		gridDataArr.push(rowData);
        	}

		    if(gridDataArr!=null){
			   grid.loadData({Rows:gridDataArr});
		    }
		    $("body").unmask();
        	
        });
        
        
        
    }
    

	
	 function out(){
	        $("body").mask("正在导出数据,请等待..."); 
	        $("#form1").submit();
	        $("body").unmask();
	    };
	// 打印
	function printInfo(){
		CreateOneFormPage();
		LODOP.PREVIEW();	
		
	}

	function CreateOneFormPage(){	
		LODOP=getLodop();
		// 设置打印样式  
		var strBodyStyle="<style> table{border:2;text-align:center;margin-left:60px;} table,td { border: 0 solid #000000;border-collapse:collapse;font-size:12px } "+
		"</style>";
		// 设置打印方式
		LODOP.SET_PRINT_PAGESIZE(2, 0, 0,"B5");	// 1 纵向打印 2 横向打印
		var printReportContent = "";
		// 循环上一命令（页码）
		LODOP.SET_PRINT_STYLEA(0,"ItemType",1);	
		printReportContent += "<table width='1000' bordercolor='black' style='border-collapse:collapse;'  border='1' id='table'>";
		printReportContent += "<caption><center><font size='3'><b>按部门、开票类型统计收入明细</b></font></center></caption>";
		printReportContent += "<tr align='center' height='20px'><td width='30'>票号</td>"+
		"<td width='30'>发票类型</td><td width='30'>开票名称</td><td width='30'>检验部门</td><td width='65'>合同号/编号</td><td width='60'>总金额</td>"
		+"<td width='60'>收费方式</td><td width='30'>现金</td><td width='30'>转账</td><td width='30'>POS</td><td width='60'>上缴财政</td><td width='60'>开票人</td><td width='60'>开票日期</td>"
		+"</tr>";
		
		var data = {"startDate":'${param.startDate }',"endDate":'${param.endDate }',
				 "dept":'${param.dept}','clss':'${param.clss}'};

		 var rows = {};
		 $.ajax({
				type: "POST",
				url: "feeStatisticsAction/deptDetail.do", 
				data:data,
				async : false,
				success : function(res) {
					rows=res.rows;
				}

        });
		 for (var i=0;i<rows.length;i++){
			 printReportContent +="<tr align='center' height='20px'><td>"+rows[i].INVOICE_NO+"</td><td>"+rows[i].CLSS+"</td><td>"+rows[i].COMPANY_NAME.replace(/(^\s*)|(\s*$)/g, '')+"</td>"+
			 "<td>"+rows[i].DEPT.replace(/(^\s*)|(\s*$)/g, '')+"</td><td>"+rows[i].PAY_NO+"</td><td>"+rows[i].PAY_RECEIVED+"</td>"
			 +"<td>"+rows[i].PAY_TYPE+"</td><td>"+rows[i].CASH_PAY+"</td><td>"+rows[i].REMARK+"</td><td>"+rows[i].POS+"</td><td>"+rows[i].HAND_IN+"</td>"+
			 "<td>"+rows[i].RECEIVE_MAN_NAME+"</td><td>"+rows[i].PAY_DATE+"</td></tr>";
		 }
		 printReportContent +="</table>";
		// 获取打印内容
		var strFormHtml=strBodyStyle+"<body>"+printReportContent+"</body>";
		LODOP.NewPage();	// 强制分页
		// 打印表格（上边距、左边距、宽、高、打印内容）
		LODOP.ADD_PRINT_TABLE(20,0,"100%","100%",strFormHtml);		
	}
  </script>
</head>
<body onload="query()">


	<div style="height: 0px">
		<form name="form1" id="form1" action="feeStatisticsAction/exportDeptDetail.do"  target="_blank" method="post">
			<input name="dept" type="hidden" value="${param.dept}"/>
			<input name="clss" type="hidden" value="${param.clss}"/>
			<input name="startDate" type="hidden" value="${param.startDate }"/>
			<input name="endDate" type="hidden" value="${param.endDate }"/>
		</form>
	</div>

	
	
	<div class="item-tm">
    <div class="l-page-title2 has-icon has-note" style="height: 80px">
        <div class="l-page-title2-div"></div>
        <div class="l-page-title2-text"><h1>财务收入统计</h1></div>
        <div class="l-page-title2-note" style="height:20px;">按各开票类型，各部门收费统计（单位：<span>元</span>）</div>
        <div class="l-page-title2-icon">
        	<img src="k/kui/images/icons/32/statistics.png" border="0"/>
        </div>
        <div class="l-page-title-content" style="top:25px;left:300px;height:80px;"> 
            <table border="0" cellpadding="0" cellspacing="0" width="">
                <tr>
                  
                    
                    
                    
                    <td colspan="1" align="right">
                        <div id="btn1"></div>
                        <div id="btn2"></div>
                        <div id="btn3"></div>
                        <div id="btn4"></div>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>
	
	
	<div style="height: 0px">
		<form name="form1" id="form1" action="feeStatisticsAction/exportDeptDetail.do"  target="_blank" method="post">
			<input name="dept" type="hidden" value="${param.dept}"/>
			<input name="clss" type="hidden" value="${param.clss}"/>
			<input name="startDate" type="hidden" value="${param.startDate }"/>
			<input name="endDate" type="hidden" value="${param.endDate }"/>
		</form>
	</div>
	
	
<div id="container" position="center" style="display:none;width:99%;height:89%;">
    <div id="grid"></div>   
</div>
    	







<div id="checkGrid" style="overflow: auto;"></div>
</body>
</html>