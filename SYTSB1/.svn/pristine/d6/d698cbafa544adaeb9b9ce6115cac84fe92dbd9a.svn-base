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
    <script src="app/fwxm/scientific/js/echarts.js"></script>
    <script src="app/statistics/js/china.js"></script>
    <script test="text/javascript">
 
    $(function () {
        $("#btn1").css({"height":"20px","line-height":"18px"})
      
        $("#btn1").ligerButton({
            icon:"count",
            click: function (){
            	getMtMoney();
            },text:"统计"
        });
        $("#form1").ligerForm();    
        
        var data = <u:dict sql="select t.id code,t.org_name text from SYS_ORG t order by t.orders"/>;
        var tt = new Array();
        tt.push({id:'all',text:'全部'});
        for(var i in data){
           tt.push(data[i]);
        }
        getMtMoney();
    });
    
  //每天统计数据
    function getMtMoney(){
    	  var startDate = $("#startDate").val();
          var endDate = $("#endDate").val();
    	var years = [];
    	var counts = [];
    	$.getJSON("/deviceTjAction/tj/getDateMtMoney.do?unit_id=${param.unit_id}&startDate="+startDate+"&endDate="+endDate,function(res){
    		if(res.success){
    			 var data=res.list;
    			for(x in data){
    				var d=data[x].dates;
    				years.push(d.substring(5,10));
    				counts.push(data[x].money);
    			};
    			console.log(years);
    			console.log(counts);
    			draw2(years,counts);
    		}else{
    			
    		}
    	})
    }

    function out()
    {
        var org_id=$("#org_id2").val();
        if(org_id==""){
            $.ligerDialog.alert("请先选择部门！");
            return;
        }
        $("#org_id1").val(org_id);
        $("body").mask("正在导出数据,请等待...");
        $("#form1").attr("action","tj/exportCount.do"); 
        $("#form1").submit();
        $("body").unmask();
    };
    function selectOrg(){
    	 top.$.dialog({
	         width: 350,
	         height: 400,
	         lock: true,
	         parent: api,
	         data: {
	       	 window: window
	         },
	         title: "部门",
	         content: 'url:app/finance/cw_bmfytj_org.jsp'
    	  });
    }
    function clickNodeId(unitId,unitName){
    	$("#org_id").val(unitName);
    	$("#org_id2").val(unitId);
    }
    </script>
    <style>
    div{margin: 0.5px;}
    .l-button {padding:0 8px 2px 9px;}
    </style>
</head>
<%
    String firstDate = DateUtil.getFirstDateStringOfYear("yyyy-MM-dd");
    String curDate  = DateUtil.getDateTime("yyyy-MM-dd", new Date());
%>
<body>
<form name="form1" id="form1" action="" getAction="" target="_blank">
<input type="hidden" name="org_id1" id="org_id1"/>
<input type="hidden" name="org_id2" id="org_id2"/>
<div class="item-tm">
    <div class="l-page-title2 has-icon has-note" style="height: 80px">
        <div class="l-page-title2-div"></div>
        <div class="l-page-title2-text"><h1>按时间统计</h1></div>
        <div class="l-page-title2-note">以收款时间为统计对象。</div>
        <div class="l-page-title2-icon"><img src="k/kui/images/icons/32/statistics.png" border="0"/></div>
        <div class="l-page-title-content" style="top:25px;height:80px;"> 
            <table border="0" cellpadding="0" cellspacing="0" width="">
                <tr>
                    <td style="text-align: right;width:120px;">开始时间从：</td>
                    <td width="100">
                            <input id="startDate" name="startDate" type="text" ltype="date" value="<%=firstDate %>"/>
                    </td>
                    <td align="center">&nbsp;至&nbsp;</td>
                    <td width="100">
                        <input id="endDate" name="endDate" type="text" ltype="date" value="<%=curDate %>"/>
                    </td>
                    <td colspan="1" align="right">
                        <div id="btn1"></div><div id="btn2"></div><div id="btn3"></div>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>
</form>
<div id="container"  position="center" style="display:none">
    <div id="countGrid"></div>   
</div>
<div id="main"  style="width:100%;height:80%;"></div> 
<script>
// 路径配置
 function draw2(years,counts){
	 var myChart = echarts.init(document.getElementById('main'));
	 var data_val=[2220, 1682, 2791, 3000, 4090, 3230, 2910],
	    xAxis_val=['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'];
	var data_val1=[0,0,0,0,0,0,0];
	var option = {
	    grid:{
	        left:10,
	        top:'10%',
	        bottom:0,
	        right:40,
	        containLabel:true
	    },
	    tooltip:{
	        show:true,
	        backgroundColor:'#384157',
	        borderColor:'#384157',
	        borderWidth:1,
	        formatter:'{b}:{c}',
	        extraCssText:'box-shadow: 0 0 5px rgba(0, 0, 0, 1)'
	    },
	   
	    title: {
	        text: '',
	        x:'4.5%',
	        top: '1%',
	        textStyle:{
	        color :'#5c6076'
	        }
	    },
	    xAxis: {
	        data: years,
	        boundaryGap:false,
	        axisLine:{
	            show:false
	        },
	         axisLabel: {
	            textStyle: {
	                color: '#5c6076'
	            }  
	        },
	        axisTick:{
	            show:false
	        }
	    },
	    yAxis: { 
	        ayisLine:{
	            show:false
	        },
	         axisLabel: {
	            textStyle: {
	                color: '#5c6076'
	            }  
	        },
	        splitLine:{
	            show:true,
	            lineStyle:{
	                color:'#2e3547'
	            }
	        },
	        axisLine: {
	                lineStyle: {
	                    color: '#384157'
	                }
	            }
	    },
	    
	    series: [
	        {
	            type: 'bar',
	            name:'linedemo',
	            
	            
	            tooltip:{
	                show:false
	            },
	            animation:false,
	            barWidth:1.4,
	            hoverAnimation:false,
	            data:counts,
	            itemStyle:{
	                normal:{
	                    color:'#f17a52',
	                    opacity:0.6,
	                    label:{
	                        show:false
	                    }
	                }
	            }
	        },
	        {
	            type: 'line',
	            name:'距离',
	            
	            animation:false,
	            symbol:'circle',
	    
	            hoverAnimation:false,
	            data:counts,
	            itemStyle:{
	                normal:{
	                    color:'#f17a52',
	                    opacity:0,
	                }
	            },
	            lineStyle:{
	                normal:{
	                    width:1,
	                    color:'#384157',
	                    opacity:1
	                }
	            }
	        },
	        {
	            type: 'line',
	            name:'linedemo',
	            smooth:true,
	            symbolSize:10,
	            animation:false,
	            lineWidth:1.2,
	            hoverAnimation:false,
	            data:counts,
	            symbol:'circle',
	            itemStyle:{
	                normal:{
	                    color:'#f17a52',
	                    shadowBlur: 40,
	                    label:{
	                        show:true,
	                        position:'top',
	                        textStyle:{
	                            color:'#f17a52',
	                    
	                        }
	                    }
	                }
	            },
	           areaStyle:{
	                normal:{
	                    color:'#f17a52',
	                    opacity:0.08
	                }
	            }
	            
	        }
	    ]
	};
	myChart.setOption(option);
 }
</script>
</body>

</html>