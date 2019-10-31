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
    var countData;
    var model='0';//"0"默认状态为图表模式
    $(function () {
    	/* $("#btn1").css({"height":"20px","line-height":"20px"});
        $("#btn2").css({"height":"20px","line-height":"20px"});
      	$("#btn3").css({"height":"20px","line-height":"20px"}); */
      	
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
        
        $("#btn3").ligerButton({
        	icon:"table",
    		click:function(){
    			var button = $('#btn3');
    			var sp1 = button.find("span.l-button-text");
    			var sp2 = button.find("span.l-button-icon");
    			//var span2 = $(el).next("span");
    			if(sp1.html()=="表格"){
    				sp2.attr("class","l-button-icon iconfont l-icon-piechart");
    				sp1.html("图表");
    				$("#main").css("display","none");
    				$("#container").css("display","block");
    				model='1';//表格模式
    				init();
    			}else{
    				sp2.attr("class","l-button-icon iconfont l-icon-table");
    				sp1.html("表格");
    				$("#main").css("display","block");
    				draw(countData);
    				$("#container").css("display","none");
    				model='0';//图表模式
    			}
    			
    		},
    		text:"表格"
    	});
        
        $("#form1").ligerForm();    
        $("input[name='_device_type-txt']").ligerComboBox().selectValue('3000');  
        init();       
    });
    
    
    function init(){
		var startDate = $("#startDate").val();
	    var endDate = $("#endDate").val();
	    var device_type = $("#device_type").val();
	    if(device_type==""){
	    	$.ligerDialog.alert("请先选择设备类别！");
	    	return;
	    }
	    var selText = $("input[id='device_type-txt']").ligerComboBox().findTextByValue(device_type);
	    $.post("sta/analyse/deviceCheckedCount.do",{"startDate":startDate,"endDate":endDate,"device_type":device_type}, function(resp){
	    	countData = resp.data;
	    	if(model=='0'){
	    		draw(countData);
	    	}else if(model=='1'){
	    		inputGrid = $("#countGrid").ligerGrid({
		            columns: [
						{ display: selText, columns:[
							{ display: '检验部门', name: 'check_unit',align: 'center', width: 150,totalSummary:
	                     		{
			                         render: function (e) {  
			                        	return "<div>合计</div>"; 
			                        	} 
			                     }},
	                     	{ display: '检验类别', columns:[
								 { display: '定期检验', name: 'cur_dj_count',align: 'center', width: 100,totalSummary:
									{
			                        	type: 'sum',
			                         	render: function (e) {  
			                        		return "<div>" + e.sum + "</div>"; 
			                        	} 
			                     	}
			                     },
				           		 { display: '监督检验', name: 'cur_jj_count',align: 'center', width: 100,totalSummary:
			                     	{
			                         	type: 'sum',
			                         	render: function (e) {  
			                        		return "<div>" + e.sum + "</div>"; 
			                        	} 
			                     	}
			                     },
				           		 { display: '委托检验', name: 'cur_wj_count',align: 'center', width: 100,totalSummary:
			                     	{
			                         	type: 'sum',
			                         	render: function (e) {  
			                        		return "<div>" + e.sum + "</div>"; 
			                        	} 
			                     	}
			                     }
	                     	]},
			           		 { display: '合计', name: 'cur_jy_total',align: 'center', width: 100,totalSummary:
		                     	{
		                         	type: 'sum',
		                         	render: function (e) {  
		                        		return "<div>" + e.sum + "</div>"; 
		                        	} 
		                     	}
		                     },
			           		 { display: '去年同期合计', name: 'last_jy_total',align: 'center', width: 120,totalSummary:
		                     	{
		                         	type: 'sum',
		                         	render: function (e) {  
		                        		return "<div>" + e.sum + "</div>"; 
		                        	} 
		                     	}
		                     },
			           		 { display: '与去年同期比较', name: 'compare_count',align: 'center', width: 120,totalSummary:
		                     	{
		                         	type: 'sum',
		                         	render: function (e) {  
		                        		return "<div>" + e.sum + "</div>"; 
		                        	} 
		                     	}
		                     }
	                     ]}
		            ], 
		            data:{Rows:eval(JSON.stringify(resp.data))},//json格式的字符串转为对象
		            height:'100%',
		            usePager:false,
		            width:'100%'
		       	 },"json");
	    	}
        });
    }	    
    
    function out()
    {
    	var device_type = $("#device_type").val();
	    if(device_type==""){
	    	$.ligerDialog.alert("请先选择设备类别！");
	    	return;
	    }
	    var selText = $("input[id='device_type-txt']").ligerComboBox().findTextByValue(device_type);
	    $("#device_name").val(selText);
    	$("body").mask("正在导出数据,请等待...");
    	$("#form1").attr("action","sta/analyse/exportCheckedCount.do");
    	$("#form1").submit();
    	$("body").unmask();
    };
    </script>
</head>
<%
	String firstDate = DateUtil.getFirstDateStringOfYear("yyyy-MM-dd");
	String curDate  = DateUtil.getDateTime("yyyy-MM-dd", new Date());
%>
<body style="overflow: auto;">
<form name="form1" id="form1" action="" getAction="" target="_blank">
<input type="hidden" name="device_name" id="device_name"/>
<div class="item-tm" >
	<div class="l-page-title2 has-icon has-note" style="height: 80px">
		<div class="l-page-title2-div"></div>
		<div class="l-page-title2-text"><h1>各检验部门检验业务统计表</h1></div>
		<div class="l-page-title2-note">以各检验部门、检验类别、设备类别为统计对象，检验设备数量单位：台。</div>
		<div class="l-page-title2-icon"><img src="k/kui/images/icons/32/statistics.png" border="0"/></div>
		<div class="l-page-title-content" style="top:15px;height:80px;">
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="80" style="text-align:center">设备类别：</td>
					<td width="110">
						<u:combo name="device_type" code="device_classify" validate="required:true"   /> 
					</td>
					<td width="80" style="text-align:center;">统计时间：</td>
					<td width="110">
							<input id="startDate" name="startDate" type="text" ltype="date" value="<%=firstDate %>"/>
					</td>
					<td width="" align="center">至</td>
					<td  width="110">
						<input id="endDate" name="endDate" type="text" ltype="date" value="<%=curDate %>"/>
					</td>
					<td width="" style="text-align: right;float: left;padding-left: 5px;">
						<div id="btn1"></div><div id="btn2"></div><div id="btn3"></div>
					</td>
				</tr>
			</table>
		</div>
	</div>
</div>
</form>
<div id="container" position="center" style="display:none">
	<div id="countGrid" ></div>
</div>
<div id="main" style="width:100%;height:400px;"></div> 
 
</body>
<script type="text/javascript">
function draw(data){
	var dqData = [];
	var jdData = [];
	var wtData = [];
	var cur_total =[];
	var last_total = [];
	
	var myChart = echarts.init(document.getElementById('main'));
	//x轴 label
	var xData = function() {
	    var xdata = [];
	    for (var i = 0; i < data.length; i++) {
	    		xdata.push(data[i].check_unit);
		        dqData.push(data[i].cur_dj_count);
		        jdData.push(data[i].cur_jj_count);
		        wtData.push(data[i].cur_wj_count);
		        cur_total.push(data[i].cur_jy_total);
		        last_total.push(data[i].last_jy_total);
	    }
	    return xdata;
	}();
	var colors = ['#5793f3', '#d14a61', '#675bba'];

	option = {"tooltip": {
					        "trigger": "axis",
					        "axisPointer": {
					            "type": "shadow",
					            textStyle: {
					                color: "#fff"
					            }
		
					        }
			   	 		 },
		     "grid": {
			    	 "borderWidth": 0,
				     "top": 150,
				     "bottom": 95,
			         "textStyle": {
			            color: "#fff"
			         }
		     },
	     toolbox: {
	        feature: {
	            /* dataView: {show: true, readOnly: false},
	            restore: {show: true}, */
	            saveAsImage: {show: true}
	        }
	    }, 
	    legend: {
	    	 	x: '4%',
		        top: '11%',
		        textStyle: {
		            color: '#90979c',
		        },
	        data:['定期','监督','委托','合计','去年同期']
	    },
	    color: colors,
	    xAxis: [
	        {
	            type: 'category',
	            axisTick: {
	                alignWithLabel: true
	            },
	            data: xData
	        }
	    ],
	    yAxis: [
	        {
	            type: 'value',
	            name: '检验', 
	            position: 'left',
	            axisLine: {
	                lineStyle: {
	                    color: colors[0]
	                }
	            },
	            axisLabel: {
	                formatter: '{value} 台'
	            }
	        },
	        {
	            type: 'value',
	            name: '去年同期',
	            position: 'right',
	            axisLine: {
	                lineStyle: {
	                    color: colors[1]
	                }
	            },
	            axisLabel: {
	                formatter: '{value} 台'
	            }
	        },
	        {
	            type: 'value',
	            name: '合计',
	            position: 'right',
	            offset: 80,
	            axisLine: {
	                lineStyle: {
	                    color: colors[2]
	                }
	            },
	            axisLabel: {
	                formatter: '{value} 台'
	            }
	        }
	    ],
	    series: [
	        {
	            name:'定期',
	            type:'bar',
	            data:dqData
	        },
	        {
	            name:'监督',
	            type:'bar',
	            data:jdData
	        },
	        {
	            name:'委托',
	            type:'bar',
	            data:wtData
	        },
	        {
	            name:'合计',
	            type:'line',
	            yAxisIndex: 1,
	            data:cur_total,
	            itemStyle: {
	                "normal": {"color": colors[1]}
	        	}
	        },
	        {
	            name:'去年同期',
	            type:'line',
	            yAxisIndex: 2,
	            data:last_total,
	            itemStyle: {
	                "normal": {"color": colors[2]}
	        	}
	        }
	    ]
	};
	
	myChart.setOption(option);
}
</script>
</html>