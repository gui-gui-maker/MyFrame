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
    var allData;
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
    				draw(allData);
    				$("#container").css("display","none");
    				model='0';//图表模式
    			}
    			
    		},
    		text:"表格"
    	});
        
        $("#form1").ligerForm();    
        
        var data = <u:dict sql="select t.id code,t.org_name text from SYS_ORG t where t.org_code like '%jd%' or t.org_code like '%cy%' or t.org_code = 'fuwu' or t.org_code = 'yld' order by t.orders"/>;
        var tt = new Array();
        tt.push({id:'all',text:'全部'});
		for(var i in data){
		   tt.push(data[i]);
		}
		$("#org_id").ligerComboBox().setData(tt);
		$("#org_id").ligerGetComboBoxManager().setValue("all");
        init();       
    });
    
    
    function init(){
		var startDate = $("#startDate").val();
	    var endDate = $("#endDate").val();
	    var org_id = $("input[name='org_id']").ligerGetComboBoxManager().getValue();
	    if(org_id==""){
	    	//$.ligerDialog.alert("请先选择部门！");
	    	//return;
	    }
	    $.post("sta/analyse/deviceCountByUser.do",{"startDate":startDate,"endDate":endDate,"org_id":org_id}, function(resp){
	    	allData = resp.data;
	    	if(model=='0'){
	    		draw(allData);
	    	}else if(model=='1'){
	    		inputGrid = $("#countGrid").ligerGrid({
		            columns: [
							{ display: '人员姓名', name: 'user_name',align: 'center', width: 150,totalSummary:
	                     		{
			                         render: function (e) {  
			                        	return "<div>合计</div>"; 
			                        	} 
			                     }},
			                 { display: '所在部门', name: 'org_name',align: 'center', width: 150},
			                 { display: '出具报告', name: 'lr_count',align: 'center', width: 100,totalSummary:
		                     	{
		                         	type: 'sum',
		                         	render: function (e) {  
		                        		return "<div>" + e.sum + "</div>"; 
		                        	} 
		                     	}
		                     },
			           		 { display: '审核报告', name: 'sh_count',align: 'center', width: 100,totalSummary:
		                     	{
		                         	type: 'sum',
		                         	render: function (e) {  
		                        		return "<div>" + e.sum + "</div>"; 
		                        	} 
		                     	}
		                     },
			           		 { display: '签发报告', name: 'qf_count',align: 'center', width: 120,totalSummary:
		                     	{
		                         	type: 'sum',
		                         	render: function (e) {  
		                        		return "<div>" + e.sum + "</div>"; 
		                        	} 
		                     	}
		                     },
		                     { display: '打印报告', name: 'dy_count',align: 'center', width: 100,totalSummary:
		                     	{
		                         	type: 'sum',
		                         	render: function (e) {  
		                        		return "<div>" + e.sum + "</div>"; 
		                        	} 
		                     	}
		                     },
		                     { display: '打印合格证', name: 'dyhgz_count',align: 'center', width: 100,totalSummary:
		                     	{
		                         	type: 'sum',
		                         	render: function (e) {  
		                        		return "<div>" + e.sum + "</div>"; 
		                        	} 
		                     	}
		                     },
			           		 { display: '领取报告', name: 'lq_count',align: 'center', width: 100,totalSummary:
		                     	{
		                         	type: 'sum',
		                         	render: function (e) {  
		                        		return "<div>" + e.sum + "</div>"; 
		                        	} 
		                     	}
		                     },
			           		 { display: '归档报告', name: 'gd_count',align: 'center', width: 120,totalSummary:
		                     	{
		                         	type: 'sum',
		                         	render: function (e) {  
		                        		return "<div>" + e.sum + "</div>"; 
		                        	} 
		                     	}
		                     }
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
    	var org_id = $("input[name='org_id']").ligerGetComboBoxManager().getValue();
	    if(org_id==""){
	    	//$.ligerDialog.alert("请先选择部门！");
	    	//return;
	    }
	    $("#org_id1").val(org_id);
    	$("body").mask("正在导出数据,请等待...");
    	$("#form1").attr("action","sta/analyse/exportCountByUser.do");
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
<input type="hidden" name="org_id1" id="org_id1"/>
<div class="item-tm" >
	<div class="l-page-title2 has-icon has-note" style="height: 80px">
		<div class="l-page-title2-div"></div>
		<div class="l-page-title2-text"><h1>各部门人员业务统计表</h1></div>
		<div class="l-page-title2-note">以各部门、人员、业务为统计对象。</div>
		<div class="l-page-title2-icon"><img src="k/kui/images/icons/32/statistics.png" border="0"/></div>
		<div class="l-page-title-content" style="top:15px;height:80px;">
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="60" style="text-align:center">部门：</td>
					<td width="110">
						<input type="text" name="org_id" id="org_id" ltype="select" ligerui="{
							readonly:true,
							tree:{checkbox:false,data: }
						}"/>
					</td>
					<td width="80" style="text-align:center;">统计时间：</td>
					<td width="110">
							<input id="startDate" name="startDate" type="text" ltype="date" value="<%=firstDate %>"/>
					</td>
					<td width="" align="center">到</td>
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
	<div id="countGrid"></div>   
</div>
<div id="main" style="width:100%;height:400px;"></div> 
</body>
<script type="text/javascript">
function draw(data){
	var xData = [];//x轴
	var lr_count = [];//'出具报告'
	var sh_count = [];//'审核报告'
	var qf_count = [];//'签发报告'
	var dy_count = [];//'打印报告'
	var dyhgz_count = []; //'打印合格证'
	var lq_count = []; //'领取报告'
	var gd_count = [];//归档报告
	
	$.each(data,function(index){
		var dd = data[index];
		
		xData.push(dd.user_name);
		lr_count.push(dd.lr_count);
		sh_count.push(dd.sh_count);
		qf_count.push(dd.qf_count);
		dy_count.push(dd.dy_count);
		dyhgz_count.push(dd.dyhgz_count);
		lq_count.push(dd.lq_count);
		gd_count.push(dd.gd_count);
	});
	var myChart = echarts.init(document.getElementById('main'));
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
		     "toolbox": {
		        feature: {
		            saveAsImage: {show: true}
		        }
		     }, 
		     "legend": {
		    	 	x: '4%',
			        top: '11%',
			        textStyle: {
			            color: '#90979c',
			        },
		        data:['出具报告','审核报告','签发报告','打印报告','打印合格证','领取报告','归档报告']
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
		            name: '业务', 
		            position: 'left',
		            axisLine: {
		                lineStyle: {
		                    color: colors[0]
		                }
		            },
		            axisLabel: {
		                formatter: '{value} 份'
		            }
		        }
		     ],
		     dataZoom: [
		                {
		                    show: true,
		                    start: 0,
		                    end: 30
		                },
		                {
		                    type: 'inside',
		                    start: 54,
		                    end: 100
		                }
		            ],
	    series:[
	            
	        {
	            name:'出具报告',
	            type:'bar',
	            stack:'报告',
	            data:lr_count
	        },{
	            name:'审核报告',
	            type:'bar',
	            stack:'报告',
	            data:sh_count
	        },
	        {
	            name:'签发报告',
	            type:'bar',
	            stack:'报告',
	            data:qf_count
	        },{
	            name:'打印报告',
	            type:'bar',
	            stack:'报告',
	            data:dy_count
	        },
	        {
	            name:'打印合格证',
	            type:'bar',
	            stack:'合格证',
	            data:dyhgz_count
	        },{
	            name:'领取报告',
	            type:'bar',
	            stack:'报告',
	            data:lq_count
	        },{
	            name:'归档报告',
	            type:'bar',
	            stack:'报告',
	            data:gd_count
	        }
	    ] 
	};
	
	myChart.setOption(option);
}
</script>
</html>