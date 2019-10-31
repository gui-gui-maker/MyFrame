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
    <script test="text/javascript">
    $(function () {
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
            },text:"导出"
        });
        
        $("#form1").ligerForm();     
        init();       
    });
    
    
    function init(){
		var startDate = $("#startDate").val();
	    var endDate = $("#endDate").val();
	   
	    $.post("sta/analyse/devicePrintedCount.do",{"startDate":startDate,"endDate":endDate}, function(resp){
	    	inputGrid = $("#countGrid").ligerGrid({
	            columns: [
					{ display: '机电（电梯）种类打印', columns:[
						{ display: '电梯类别', name: 'device_name',align: 'center', width: 150/*,totalSummary:
                     		{
		                         render: function (e) {  
		                        	return "<div>合计</div>"; 
		                        	} 
		                     }*/},
                     	{ display: '机电一部（高新）', columns:[
							 { display: '定期检验', name: 'jd1_dj_count',align: 'center', width: 100/*,totalSummary:
								{
		                        	type: 'sum',
		                         	render: function (e) {  
		                        		return "<div>" + e.sum + "</div>"; 
		                        	} 
		                     	}*/
		                     },
			           		 { display: '监督检验', name: 'jd1_jj_count',align: 'center', width: 100/*,totalSummary:
		                     	{
		                         	type: 'sum',
		                         	render: function (e) {  
		                        		return "<div>" + e.sum + "</div>"; 
		                        	} 
		                     	}*/
		                     }
                     	]},
                     	{ display: '机电二部（金牛、地铁四号线）', columns:[
							 { display: '定期检验', name: 'jd2_dj_count',align: 'center', width: 100/*,totalSummary:
								{
		                        	type: 'sum',
		                         	render: function (e) {  
		                        		return "<div>" + e.sum + "</div>"; 
		                        	} 
		                     	}*/
		                     },
			           		 { display: '监督检验', name: 'jd2_jj_count',align: 'center', width: 100/*,totalSummary:
		                     	{
		                         	type: 'sum',
		                         	render: function (e) {  
		                        		return "<div>" + e.sum + "</div>"; 
		                        	} 
		                     	}*/
		                     }
                     	]},
                     	{ display: '机电三部（双流）', columns:[
							 { display: '定期检验', name: 'jd3_dj_count',align: 'center', width: 100/*,totalSummary:
								{
		                        	type: 'sum',
		                         	render: function (e) {  
		                        		return "<div>" + e.sum + "</div>"; 
		                        	} 
		                     	}*/
		                     },
			           		 { display: '监督检验', name: 'jd3_jj_count',align: 'center', width: 100/*,totalSummary:
		                     	{
		                         	type: 'sum',
		                         	render: function (e) {  
		                        		return "<div>" + e.sum + "</div>"; 
		                        	} 
		                     	}*/
		                     }
                     	]},
                     	{ display: '机电四部（锦江、地铁二号线）', columns:[
							 { display: '定期检验', name: 'jd4_dj_count',align: 'center', width: 100/*,totalSummary:
								{
		                        	type: 'sum',
		                         	render: function (e) {  
		                        		return "<div>" + e.sum + "</div>"; 
		                        	} 
		                     	}*/
		                     },
			           		 { display: '监督检验', name: 'jd4_jj_count',align: 'center', width: 100/*,totalSummary:
		                     	{
		                         	type: 'sum',
		                         	render: function (e) {  
		                        		return "<div>" + e.sum + "</div>"; 
		                        	} 
		                     	}*/
		                     }
                     	]},
                     	{ display: '机电五部', columns:[
							 { display: '定期检验', name: 'jd5_dj_count',align: 'center', width: 100/*,totalSummary:
								{
		                        	type: 'sum',
		                         	render: function (e) {  
		                        		return "<div>" + e.sum + "</div>"; 
		                        	} 
		                     	}*/
		                     },
			           		 { display: '监督检验', name: 'jd5_jj_count',align: 'center', width: 100/*,totalSummary:
		                     	{
		                         	type: 'sum',
		                         	render: function (e) {  
		                        		return "<div>" + e.sum + "</div>"; 
		                        	} 
		                     	}*/
		                     }
                    	]},
		           		 { display: '合计', name: 'total',align: 'center', width: 100/*,totalSummary:
	                     	{
	                         	type: 'sum',
	                         	render: function (e) {  
	                        		return "<div>" + e.sum + "</div>"; 
	                        	} 
	                     	}*/
	                     }
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
    	$("#form1").attr("action","sta/analyse/exportPrintedCount.do");
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
		<div class="l-page-title2-text"><h1>各部门已打印的电梯定检、监检报告统计表</h1></div>
		<div class="l-page-title2-note">以各检验部门、检验类别、设备类别为统计对象，已打印检验报告数量单位：台。</div>
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
					<td width="" style="text-align: right;float: left;padding-left: 5px;padding-top: 5px">
						<div id="btn1"></div><div id="btn2"></div>
					</td>
				</tr>
			</table>
		</div>
	</div>
</div>
</form>
<div position="center">
	<div id="countGrid"></div>   
</div>
</body>
</html>