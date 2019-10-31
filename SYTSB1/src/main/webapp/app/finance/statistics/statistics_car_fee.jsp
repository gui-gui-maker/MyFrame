<%@page import="java.text.SimpleDateFormat"%>
<%@page import="util.DateToChinese"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.khnt.utils.DateUtil"%>
<%@page import="java.util.Date"%>
<%
	Date lastMonday = DateToChinese.getLastWeekMonday(new Date());
	Date lastSunday = DateToChinese.getLastWeekSunday(new Date());
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String firstDate = sdf.format(lastMonday);
    String curDate  = sdf.format(lastSunday);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <%@ include file="/k/kui-base-list.jsp" %>
    <%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
    <link rel="stylesheet" type="text/css" href="app/finance/css/stylegx.css" media="all" />
    <script type="text/javascript" src="app/finance/js/echarts.min.js"></script>
    <script test="text/javascript">
    var selected = null;
    var grid = null;
    var rows = []; 
    var clsses = [];
    var car_ids = [];
    $(function () {
        /* $("#btn1").css({"height":"20px","line-height":"18px"});
        $("#btn2").css({"height":"20px","line-height":"18px"});
        $("#btn3").css({"height":"20px","line-height":"18px"});
        $("#btn4").css({"height":"20px","line-height":"18px"}); */
        $("#btn1").ligerButton({
            icon:"count",
            click: function (){
            	getRows(true);
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
            		loadGrid(rows,clsses,4);
            		drawChart(clsses,rows,4);
            	}else{
            		$("#btn3").find('span.l-button-text').html('万元');
            		$("div.l-page-title2-note span").html('元');
            		loadGrid(rows,clsses,2);
            		drawChart(clsses,rows,2);
            	}
            },
            text:"万元"
        });
        $("#btn4").ligerButton({
        	icon:"piechart",
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
    					loadGrid(rows,clsses,2);
                	}else{
                		loadGrid(rows,clsses,4);
                	}
    			}else{
    				sp2.attr("class","l-button-icon iconfont l-icon-table");
    				sp1.html("表格");
    				$("#main").css("display","block");
    				$("#container").css("display","none");
    				if($("#btn3").find('span.l-button-text').html()=='万元'){
                		drawChart(clsses,rows,2);
                	}else{
                		drawChart(clsses,rows,4);
                	}
    			}
    			
    		},
    		text:"图表"
    	});
        $("#form1").ligerForm();    
        getRows(true); 
    });
 
  //将数据转成“万元”
    function tenThousand(rows){
    	var data = [];
		for(var i=0;i<rows.length;i++){
			var row = rows[i];
			var item = {};
			item.car_id=row.car_id;
			item.total = parseFloat(row.total/10000).toFixed(4);
			$.each(row,function(k,v){
				if(typeof v == 'number'){
					item[k]=parseFloat(v/10000).toFixed(4);
				}
			});
			data.push(item);
		}
		return data;
    }
  //获取数据
    function getRows(flag){
        var startDate = $("#startDate").val();
        var endDate = $("#endDate").val();
        $.post("feeStatisticsAction/statisticsCarFee.do",
        		{"start":startDate,"end":endDate,"carIds":$("#carId").ligerGetComboBoxManager().getValue()}, 
        		function(res){
        			rows = []; 
		            clsses = [];
		            car_ids = [];
        			//接收数据
		            var data = res.rows;
		            clsses = res.clsses;
		            car_ids = res.carId;
		            //组装数据
		            for(var i=0;i<car_ids.length;i++){
		            	var row = {};
		            	row['car_id'] = car_ids[i];
		            	row['total'] = 0;
		            	for(var j=0;j<clsses.length;j++){
		            		row[clsses[j]] = 0;
		            		for(var k=0;k<data.length;k++){
		            			if(data[k].CAR_ID == car_ids[i] && data[k].CLSS==clsses[j]){
	                      			row[clsses[j]] = data[k].MONEY;
		                      		row['total'] += data[k].MONEY;
		                      	}	
		            		}
		                }
		            	if(typeof row.total == 'number'){
		            		row.total = row.total.toFixed(2);
		            	}
		            	rows.push(row);
		            }
		            
		            if($("#btn3").find('span.l-button-text').html()=='万元'){
		            	 loadGrid(rows,clsses,2);
                		drawChart(clsses,rows,2);
                	}else{
                		 loadGrid(rows,clsses,4);
                		drawChart(clsses,rows,4);
                	}
		            //赋值下拉框项
		            if(flag){
			            var selectItems = [];
			            for(var i in car_ids){
			            	selectItems.push({id:car_ids[i],text:car_ids[i]});
			            }
			            $("#carId").ligerGetComboBoxManager().setData(selectItems);
		            }
        });
    }
  //生成报表
	function loadGrid(rows,clsses,fix){
		if(fix == 4){
			rows = tenThousand(rows);
		}
		gird = null;
		//定义头部
	    var columns = [];
	    columns.push({
	    	display : '车牌号', 
	    	name : 'car_id',
	    	align : 'center', 
	    	width : 200,
	    	totalSummary:{
					render : function (e){  
	    			return "<div>合计</div>"; 
	    		} 
	        		}
	    });
	    for(var i = 0;i<clsses.length;i++){
	    	if((selected!=null&&selected[clsses[i]]==true)||selected==null){
	    	columns.push({display: clsses[i], 
	    		name: clsses[i],
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
	    columns.push({display: '小计', 
	    	name : 'total',
	    	align : 'center',
	    	width : 120,
	    	totalSummary:{
	        	type : 'sum',
	        	render : function (e) {  
	        		var tt = e.sum;
	        		tt = parseFloat(tt).toFixed(fix);
	        		return "<div>" + tt +"</div>";  
	        	} 
	        }
	    });	
	    //创建表格
		grid = $("#grid").ligerGrid({
								        columns: columns, 
								        data:{Rows:rows},
								        height:'100%',
								        usePager:false,
								        width:'100%'
								     });
	}
  //导出报表excel
    function out()
    {
        $("body").mask("正在导出数据,请等待...");
        $("#form1").attr("action","feeStatisticsAction/exportCarFee.do"); 
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
        <div class="l-page-title2-text"><h1>车辆费用统计</h1></div>
        <div class="l-page-title2-note" style="height:20px;">按车辆牌照，费用类别统计（单位：<span>元</span>）</div>
        <div class="l-page-title2-icon">
        	<img src="k/kui/images/icons/32/statistics.png" border="0"/>
        </div>
        <div class="l-page-title-content" style="top:25px;height:80px;"> 
            <table border="0" cellpadding="0" cellspacing="0" width="">
                <tr>
                 	<td width="60" style="text-align: right;">车牌：</td>
                    <td width="110px">
                       <input id="carId" name="carId"
							type="text" ltype="select" value="" 
							ligerui="{width:325,checkbox:true,data:[],isMultiSelect:true}" />
                    </td>
                    <td width="90" style="text-align: right;">报销时间从：</td>
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
<div id="container" position="center" style="width:99%;height:89%;">
    <div id="grid"></div>   
</div>
<div id="main" style="width:100%;height:80%;display:none;"></div>
<script type="text/javascript">
 	var barChart = null;
 	
 	function drawChart(clsses,rows,fix){
 		$("#main").find("div").remove();
 		var div = document.createElement('div');
 		$("#main").append(div);
 		$(div).css({width:'100%',height:'90%'});
 		if(fix == 4){
 			rows = tenThousand(rows);
 		}
 		barChart = echarts.init(div);
 		var carIds = [];
 		var ss = [];

 		var colors = [
 			'#0000FF','#3D59AB','#1E90FF','#0B1746','#03A89E',
 			'#191970','#33A1C9','#8A2BE2','#DA70D6','#F0E68C',
 			'#BC8F8F','#C76114','#734A12','#5E2612','#2E8B57',
 			'#00FF7F','#40E0D0','#6A5ACD','#4682B4','#1E90FF',
 			'#483D8B','#CD5555','#FF1493','#8B8989','#CD6889',
 			'#CDB5CD'

 		]
 		for(var i=0;i<clsses.length;i++){
 			var s = {};
 			s.name = clsses[i];
			s.type = 'bar';
 			s.stack = 'fee';
 			s.yAxisIndex = 0;
            s.barMaxWidth = 50, 
            s.barGap = '10%', 
            s.color=colors[i],
            s.itemStyle = {
                 "normal": {
                     "barBorderRadius": 0, 
                    /* 是否显示数值 */
                     "label": {
                         "show": false, 
                         "textStyle": {
                             "color": "rgba(0,0,0,1)"
                         }, 
                         "position": "insideTop",
                         formatter : function(p) {
 	                                                return p.value > 0 ? (p.value ): '';
 	                                            }
                     }
                 }
             }, 
 			s.data = [];
 			for(var j=0;j<rows.length;j++){
 				if(carIds.length<rows.length){
 					carIds.push(rows[j]['car_id']);
 				}
 				s.data.push(rows[j][clsses[i]]?rows[j][clsses[i]]:0);
 			}
 			ss.push(s);
 		}
 		   option = {
 		    		 backgroundColor: '#eee',
 		    	   "tooltip" : {
 	 			        trigger: 'axis',
 	 			        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
 	 			            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
 	 			        } ,
 	 			       formatter: function (params,ticket,callback) {  
 	 		                 var res = params[0].axisValue + '<br>';
 	 		                 var tvalue = 0;
 	 		                 for(var i=0;i<params.length;i++){
 	 		                	if(params[i].value == 0){
 	 		                		continue;
 	 		                	}else{
 	 		                		res += params[i].marker + params[i].seriesName + ':' + params[i].value +'<br>';
 	 		                		tvalue += parseFloat(params[i].value);
 	 		                	}
 	 		                 }
 	 		               	 res += "合计" + ':' + tvalue +'<br>';
 	 	 		             return res;  
 	 		             }   
 	 			    },
 		    	    grid: {

 	 			        left: '18%',
 	 			        right: '3%',
 	 			        bottom: '1%',
 	 			        containLabel: true
 	 			    },
 		    	    legend: {
 				        orient: 'vertical',
 				        left: 10,
 				        top: 50,
 				        bottom: 20,
 				        data: clsses
 				    }, 
 		    	    "toolbox": {
 		    	        "show": true, 
 		    	        "feature": {
 		    	            "restore": { }, 
 		    	            "saveAsImage": { }
 		    	        },
 		    	        "top":"50px"
 		    	    },  
 		    	    "calculable": true, 
 		    	    "xAxis": [
 		    	        {
 		    	            "type": "category", 
 		    	            "splitLine": {
 		    	                "show": false
 		    	            }, 
 		    	            "axisTick": {
 		    	                "show": false
 		    	            }, 
 		    	            "splitArea": {
 		    	                "show": false
 		    	            }, 
 		    	            "axisLabel": {
 		    	                "interval": 0, 
 		    	                "rotate": 45, 
 		    	                "show": true, 
 		    	                "splitNumber": 15, 
 		    	                "textStyle": {
 		    	                    "fontFamily": "微软雅黑", 
 		    	                    "fontSize": 12
 		    	                }
 		    	            }, 
 		    	            "data": carIds,
 		    	        }
 		    	    ], 
 		    	    "yAxis": [
 		    	        {
 		    	            "type": "value", 
 		    	            "splitLine": {
 		    	                "show": false
 		    	            }, 
 		    	            "axisLine": {
 		    	                "show": true
 		    	            }, 
 		    	            "axisTick": {
 		    	                "show": false
 		    	            }, 
 		    	            "splitArea": {
 		    	                "show": false
 		    	            }
 		    	        }
 		    	    ], 
 		    	    "series": ss
 		    	}

 		barChart.setOption(option);
 		barChart.on('click', function (param) {
 		 	
 			//弹框加事件
 			$(".white_content").addClass("md-show");
    		$("#light,#tccontent").show();
    		var ligh=$(window).height()*0.8;
    		$(".tankuang").css({"height":ligh+"px"}); 
    		$("#fade").show();
    		//画出饼图
    		for(var i=0;i<rows.length;i++){
 				if(rows[i].car_id == param.name){
 					drawPie(rows[i]);
 					break;
 				}
 			} 

 		});
 	    //legend点击选中事件
        barChart.on('legendselectchanged', function (param){
	        selected = param["selected"];
        });  	
    	$("#fade,#t-close-btn").click(function(){
    		$("#light,#tccontent,#fade").hide();
    	});
 	}
 	var pie = null;
 	function drawPie(row){
 		var data = {};
 		data.legendData = [];
 		data.seriesData = [];
 		data.selected = {};
 		$.each(row,function(k,v){
 			for(var i=0;i<clsses.length;i++){
 				if(v == 0 || k == 'total'){
 					break;
 				}else if(k == 'other'){
 					var obj = {};
 	 				obj.name='其他';
 	 				obj.value=v;
 	 				data.seriesData.push(obj);
 	 				data.legendData.push('其他');
 					data.selected['其他']=true;
 	 				break;
 				}else if(k == clsses[i]){
 	 				var obj = {};
 	 				obj.name=k;
 	 				obj.value=v;
 	 				data.seriesData.push(obj);
 	 				data.legendData.push(clsses[i]);
 					data.selected[clsses[i]]=true;
 	 				break;
 	 			}
 			}
 		});
 		var el = $(".tankuang")[0];
 		pie = echarts.init(el);
 		var option = {
 			    title : {
 			        text: row.car_id,
 			        subtext: '车辆费用类别',
 			        x:'center',
 			        textStyle: {
 			            color: '#ccc'
 			        }
 			    },
 			    tooltip : {
 			        trigger: 'item',
 			        formatter: "{a} <br/>{b} : {c} ({d}%)"
 			    },
 			    legend: {
 			        type: 'scroll',
 			        orient: 'vertical',
 			        right: 20,
 			        top: 100,
 			        bottom: 20,
 			        data: data.legendData,
 			        selected: data.selected
 			    },
 			    
 			    series : [
 			        {
 			            name: '经济类型',
 			            type: 'pie',
 			            radius : '55%',
 			            center: ['40%', '50%'],
 			            data: data.seriesData.sort(function (a, b) { return a.value - b.value; }),
 			            itemStyle: {
 			                emphasis: {
 			                    shadowBlur: 10,
 			                    shadowOffsetX: 0,
 			                    shadowColor: 'rgba(0, 0, 0, 0.5)'
 			                }
 			            }
 			        }
 			    ]
 			};
 		pie.setOption(option);
 	}
</script>
</body>
</html>