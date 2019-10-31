<%@page import="java.text.SimpleDateFormat"%>
<%@page import="util.DateToChinese"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="java.util.Calendar"%>
<%@page import="com.khnt.utils.DateUtil"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%
	Date lastMonday = DateToChinese.getLastWeekMonday(new Date());
	Date lastSunday = DateToChinese.getLastWeekSunday(new Date());
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String firstDate = sdf.format(lastMonday);
    String curDate  = sdf.format(lastSunday);
%>
    <title></title>
    <%@include file="/k/kui-base-list.jsp" %>
    <%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
    <link rel="stylesheet" type="text/css" href="app/finance/css/stylegx.css" media="all" />
    <script type="text/javascript" src="app/finance/js/echarts.min.js"></script>
    <script test="text/javascript">
    var selected = null;
    var grid = null;
    var depts = [];
    var clsses = [];
    var rows = []; 
    var incomeClasses =[];
    var costClasses = [];
    $(function () {
        $("#btn1").ligerButton({
            icon:"count",
            click: function (){
            	getRows();
            },
            text:"统计"
        });
        $("#btn2").ligerButton({
            icon:"excel-export",
            click: function (){
                out();
            },
            text:"导出"
        });
        $("#btn3").ligerButton({
            img:"app/finance/image/qian.png",
            click: function (){
            	if($("#btn3").find('span.l-button-text').html()=='万元'){
            		$("#btn3").find('span.l-button-text').html('元');
            		$("div.l-page-title2-note span").html('万元');
            		loadGrid(rows,costClasses,incomeClasses,4);
            		drawChart(rows,4);
            	}else{
            		$("#btn3").find('span.l-button-text').html('万元');
            		$("div.l-page-title2-note span").html('元');
            		loadGrid(rows,costClasses,incomeClasses,2);
            		drawChart(rows,2);
            	}
            },
            text:"万元"
        });
        $("#btn4").ligerButton({
        	icon:'piechart',
    		click:function(){
    			var button = $('#btn4');
    			var sp1 = button.find("span.l-button-text");
    			var sp2 = button.find("span.l-button-icon");
    			if(sp1.html()=="表格"){
    				sp2.attr("class","l-button-icon iconfont l-icon-piechart");
    				sp1.html("图表");
    				$("#main").css("display","none");
    				$("#container").css("display","block");
    				if($("#btn3").find('span.l-button-text').html()=='万元'){
    					loadGrid(rows,costClasses,incomeClasses,2);
                	}else{
                		loadGrid(rows,costClasses,incomeClasses,4);
                	}
    				
    			}else{
    				sp2.attr("class","l-button-icon iconfont l-icon-table");
    				sp1.html("表格");
    				$("#main").css("display","block");
    				if($("#btn3").find('span.l-button-text').html()=='万元'){
                		drawChart(rows,2);
                	}else{
                		drawChart(rows,4);
                	}
    				$("#container").css("display","none");
    			}
    			
    		},
    		text:"图表"
    	});
        $("#form1").ligerForm();    
        getRows(true); 
    });
  /* //初始化下拉框函数
    function initSelect(depts){
    	var departments = [];
    	for(var i=0;i<depts.length;i++){
    		var item = {};
    		item.id = depts[i]; 
    		item.text = depts[i]; 
    		departments.push(item);
    	}
    	$("#dept").ligerGetComboBoxManager().setData(departments);
    	$("#dept").ligerGetComboBoxManager().setValue('');
    } */
  //将数据转成“万元”
    function tenThousand(rows){
    	var data = [];
		for(var i=0;i<rows.length;i++){
			var row = rows[i];
			var item = {};
			item.dept=row.dept;
			$.each(row,function(k,v){
				if(typeof v == 'number'){
					item[k]=parseFloat(v/10000).toFixed(4);
				}
			});
			data.push(item);
		}
		return data;
    }
    //isInitSelect 是否初始化部门选择框
    function getRows(isInitSelect){
        var startDate = $("#startDate").val();
        var endDate = $("#endDate").val();
        
        var unit = "特检院";//$("#unit").ligerGetComboBoxManager().getValue();
        var dept = $("#dept").val();
        $.post("feeStatisticsAction/inventory.do",
        		{"startDate":startDate,"endDate":endDate,"unit":unit,"dept":dept}, 
        		function(res){
        			if(res.success){
        				rows = []; 
    		            clsses = [];
    		            depts = [];
    		            incomeClasses = [];
    		            costClasses = [];
            			//接收数据
    		            var rows_in = res.rows_in;
    		            var rows_out = res.rows_out;
    		            data = rows_in.concat(rows_out);
    		            incomeClasses = res.clsses_in;
    		            costClasses = res.clsses_out;
    		            clsses = clsses.concat(costClasses,incomeClasses);
    		            depts = res.depts;
    		            /* if(isInitSelect){
    			            initSelect(depts);
    		            } */
    		            //组装数据
    		            for(var i=0;i<depts.length;i++){
    		            	var row = {};
    		            	row['dept'] = depts[i];
    		            	row['cost_total'] = 0;
    		            	row['income_total'] = 0;
    		            	for(var j=0;j<clsses.length;j++){
    		            		row[clsses[j]] = 0;
    		            		for(var k=0;k<data.length;k++){

    		            			if(data[k].DEPT == depts[i] && data[k].CLSS==clsses[j]){
    		            				var mny = parseFloat(parseFloat(data[k].MONEY).toFixed(2));
    		            				
    		            				if(data[k].CLSS=='入库'){
        		                      		row['income_total'] += mny;
        		                      		row['入库']  += mny;
    		            				}else{
        		                      		row['cost_total'] += mny;
        		                      		row['出库']  += mny;
    		            				}
    		            				
    		                      	}	
    		            		}
    		                }
    		            	
    		            	row['cost_total'] = parseFloat(row['cost_total'].toFixed(2));
    		            	row['income_total'] = parseFloat(row['income_total'].toFixed(2));
    		            	/* if("办公室"==depts[i]){
    		            		alert(row['income_total']+"---------"+row['cost_total']+"------------"+(row['income_total']-row['cost_total']))
    		            	} */
    		            	row['入库-出库'] = parseFloat((row['income_total']-row['cost_total']).toFixed(2));
    		            	row['lr_total'] = parseFloat((row['income_total']-row['cost_total']).toFixed(2));
    		            	rows.push(row);
    		            }
    		            if($("#btn3").find('span.l-button-text').html()=='万元'){
        					loadGrid(rows,costClasses,incomeClasses,2);
        					 drawChart(rows,2);
                    	}else{
                    		loadGrid(rows,costClasses,incomeClasses,4);
                    		 drawChart(rows,4);
                    	}
    		           /*  loadGrid(rows,costClasses,incomeClasses,2);
    		            drawChart(rows,2); */
        			}else{
        				alert(res.msg);
        			}
        });
    }
function loadGrid(rows,costClasses,incomeClasses,fix){
	if(fix == 4){
		rows = tenThousand(rows);
	}
	
	gird = null;
	//定义头部
    var columns = [];
	var cost_columns = [];
	var income_columns = [];
	var lr_columns = [];
	
    columns.push({
    	display : '部门', 
    	name : 'dept',
    	align : 'center', 
    	width : 200,
    	totalSummary:{
				render : function (e){  
    			return "<div>合计</div>"; 
    		} 
        		}
    });
    for(var i = 0;i<costClasses.length;i++){
    	columns.push({display: costClasses[i], 
    		name: costClasses[i],
    		align: 'center', 
    		width: 120, 
    		totalSummary:{
                type : 'sum',
       			render : function (e){  
       				var tt = e.sum;
       				tt = parseFloat(tt).toFixed(fix);
            		return "<div>" + tt +"</div>"; 
            	} 
	        }
    	});	
    }
  /*   cost_columns.push({display: '支出小计', 
    	name : 'cost_total',
    	align : 'center',
    	width : 120,
    	totalSummary:{
        	type : 'sum',
        	render : function (e) {  
        		var tt = e.sum;
        		tt = parseFloat(tt).toFixed(fix);
        		return "<div>" + tt + "</div>"; 
        	} 
        }
    }); */
    //支出列
   /*  columns.push({
    	display : '支出', 
    	columns : cost_columns
    }); */
    for(var i = 0;i<incomeClasses.length;i++){
    	if((selected!=null&&selected[incomeClasses[i]]==true)||selected==null){
	    	columns.push({display: incomeClasses[i], 
	    		name: incomeClasses[i],
	    		align: 'center', 
	    		width: 120, 
	    		totalSummary:{
	                type : 'sum',
	       			render : function (e){  
	       				var tt = e.sum;
	       				tt = parseFloat(tt).toFixed(fix);
	            		return "<div>" + tt +"</div>"; 
	            	} 
		        }
	    	});
    	}
    }
    if((selected!=null&&selected['入库-出库']==true)||selected==null){
    columns.push({display: '入库-出库', 
    		name: '入库-出库',
    		align: 'center', 
    		width: 120, 
    		totalSummary:{
                type : 'sum',
       			render : function (e){  
       				var tt = e.sum;
       				tt = parseFloat(tt).toFixed(fix);
            		return "<div>" + tt +"</div>"; 
            	} 
	        }
    	});	
    }
   /*  income_columns.push({display: '收入小计', 
    	name : 'income_total',
    	align : 'center',
    	width : 120,
    	totalSummary:{
        	type : 'sum',
        	render : function (e) {  
        		var tt = e.sum;
        		tt = parseFloat(tt).toFixed(fix);
        		return "<div>" + tt + "</div>"; 
        	} 
        }
    }); */
  //支出列
 /*  columns.push({
    	display : '收入', 
    	columns : income_columns
    });
    columns.push({
    	display : '利润', 
    	columns : lr_columns
    });  */
    //创建表格
	grid = $("#grid").ligerGrid({
							        columns: columns, 
							        data:{Rows:rows},
							        height:'100%',
							        usePager:false,
							        width:'100%'
							     });
}
    function out()
    {
        $("body").mask("正在导出数据,请等待...");
        $("#form1").attr("action","feeStatisticsAction/exportInventory.do"); 
        if(selected!=null){
            var item='';
            for (var key in selected) {
    			if(selected[key]==true){
    					if(item==""){
    						item = key;
    					}else{
    						item = item+","+key;
    					}
    			}
    		}
            $("#form1").append('<input type="hidden" name="items" id="items" value="'+item+'"/>');
           }
        $("#form1").submit();
        $("body").unmask();
    };
    </script>
   
</head>
<%-- <%
    String firstDate = DateUtil.getFirstDateStringOfYear("yyyy-MM-dd");
    String curDate  = DateUtil.getDateTime("yyyy-MM-dd", new Date());
%> --%>
<body>
<div id="tccontent">
	<div id="light" class="white_content md-show">
		<div class="close">
			<a id="t-close-btn" class="iconfont icon-esc"
				href="javascript:void(0)" title="关闭">x</a>
		</div>
		<div class="tankuang"></div>
		<div class="wtctbg"></div>
	</div>
	<div id="fade" class="black_overlay" style="display: none;"></div>
</div>
<form name="form1" id="form1" action="" getAction="" target="_blank" method="post">
<input type="hidden" name="org_id1" id="org_id1"/>
<input type="hidden" name="org_id2" id="org_id2"/>
<div class="item-tm">
    <div class="l-page-title2 has-icon has-note" style="height: 80px">
        <div class="l-page-title2-div"></div>
        <div class="l-page-title2-text"><h1>财务收入统计</h1></div>
        <div class="l-page-title2-note" style="height:20px;">各部门收支对比统计（单位：<span>元</span>）</div>
        <div class="l-page-title2-icon">
        	<img src="k/kui/images/icons/32/statistics.png" border="0"/>
        </div>
        <div class="l-page-title-content" style="top:25px;height:80px;"> 
            <table border="0" cellpadding="0" cellspacing="0" width="">
                <tr>
                    <!--  <td width="100" style="text-align: right;width:50px;">单位：</td>
                    <td width="120px">
                       <input id="unit" name="unit"
							type="text" ltype="select" value=""
							ligerui="{isTextBoxMode:true,width:325,
							initValue:'检验院',
							data:[{'id':'检验院','text':'四川省特种设备检验研究院'},{'id':'协会','text':'四川省特种设备检验检测协会'}],
							isMultiSelect:false}" />

                    </td> -->
                     <td width="60" style="text-align: right;width:50px;">部门：</td>
                    <td width="110px">
                       <input id="dept" name="dept"
							type="text" ltype="select" value=""
							ligerui="{width:325,
							initValue:'检验院',
							tree:{checkbox:true,data:<u:dict sql="select o.id, o.parent_id pid, o.id code, o.org_name text
									  from sys_org o
									 where o.id not in ('100042', '100047')
									   and o.parent_id not in ('100042', '100047')
									   and o.status= 'used' and o.property='dep'
									 order by o.orders"/>,
							isMultiSelect:false}}" />

                    </td>
                    <td width="70" style="text-align: right;">时间从：</td>
                    <td width="110">
                            <input id="startDate" name="startDate" type="text" ltype="date" value="<%=firstDate %>"/>
                    </td>
                    <td align="center">&nbsp;至&nbsp;</td>
                    <td width="110">
                        <input id="endDate" name="endDate" type="text" ltype="date" value="<%=curDate %>"/>
                    </td>
                    <td width="" style="text-align: right;float: left;padding-left: 5px;">
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
</form>
<div id="container" position="center" >
    <div id="grid"></div>   
</div>
<div id="main" style="width:100%;height:100%;display:none;"></div>
<script type="text/javascript">
 	var barChart = null;
 	
 	function drawChart(rows,fix){
 		if(fix == 4){
 			rows = tenThousand(rows);
 		}
 		$("#main").find("div").remove();
 		var div = document.createElement('div');
 		$("#main").append(div);
 		$(div).css({width:'100%',height:'90%'});
 		barChart = echarts.init(div);
 		var dpt = [];
 		var cst = [];
 		var inc = [];
 		var pft = [];
 		for(var i=0;i<rows.length;i++){
 			dpt.push(rows[i].dept);
 			cst.push(-rows[i].cost_total);
 			inc.push(rows[i].income_total);
 			pft.push(rows[i].income_total-rows[i].cost_total);
 		}
 		var option = {
 			    tooltip : {
 			        trigger: 'axis',
 			        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
 			            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
 			        }
 			    },
 			    legend: {
 			        data:['入库-出库', '出库', '入库']
 			    },
 			    grid: {
 			        left: '3%',
 			        right: '4%',
 			        bottom: '3%',
 			        containLabel: true
 			    },
 			    xAxis : [
 			        {
 			            type : 'value'
 			        }
 			    ],
 			    yAxis : [
 			        {
 			            type : 'category',
 			            axisTick : {show: false},
 			            data : dpt
 			        }
 			    ],
 			   dataZoom: [
 			             {
 			                 show: true,
 			                 yAxisIndex: 0,
 			                 filterMode: 'empty',
 			                 width: 30,
 			                 height: '80%',
 			                 left: '97%',
 			                 start: 0,
 			                 end: (rows.length<10?100:rows.length>10&&rows.length<30?40:20)
 			             }
 			         ],
 			    series : [
 			        {
 			            name:'入库-出库',
 			            type:'bar',
 			            label: {
 			                normal: {
 			                    show: true,
 			                    position: 'inside'
 			                }
 			            },
 			            data:pft
 			        },
 			        {
 			            name:'入库',
 			            type:'bar',
 			            stack: '总量',
 			            label: {
 			                normal: {
 			                    show: true,
 			                    position: 'right'
 			                }
 			            },
 			            data:inc
 			        },
 			        {
 			            name:'出库',
 			            type:'bar',
 			            stack: '总量',
 			            label: {
 			                normal: {
 			                    show: true,
 			                    position: 'left'
 			                }
 			            },
 			            data:cst
 			        }
 			    ]
 			};

 		barChart.setOption(option);
 	    //legend点击选中事件
        barChart.on('legendselectchanged', function (param){
	        selected = param["selected"];
        });  
 	}
</script>
</body>
</html>