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
    <style>
    div{margin: 0.5px;}
    .l-button {padding:0 8px 2px 9px;}
    </style>
</head>

<body>
<h4>项目名称：电梯制停距离测试仪</h4>
<h4>项目类别：一般</h4>
<h4>专业类别：标准化</h4>
<h4>项目负责人：都比</h4>
<h4>起止日期：2017年04月07日-2018年04月19日</h4>
<h4>论文数量：10</h4>
<h4>专利数量：5</h4>
<div id="main"  style="width:100%;height:60%;"></div> 
<script>
$(function () {
	
	draw(1)
});

function draw(data){
    				
    				var myChart = echarts.init(document.getElementById('main'));
    				 // 添加点击事件  
    				option = {
    					    tooltip: {
    					        trigger: 'item',
    					        formatter: "{a} <br/>{b} : {c} ({d}%)"
    					    },
    					    series: [{
    					        name: '费用情况',
    					        type: 'pie',
    					        radius: '68%',
    					        center: ['50%', '60%'],
    					        clockwise: false,
    					        data: [{
    					            value: 45,
    					            name: '差旅费'
    					        }, {
    					            value: 25,
    					            name: '设备费'
    					        }, {
    					            value: 15,
    					            name: '出版/文献/信息'
    					        }, {
    					            value: 8,
    					            name: '劳务费'
    					        }, {
    					            value: 7,
    					            name: '其他费用'
    					        }],
    					        label: {
    					            normal: {
    					                textStyle: {
    					                    color: '#999',
    					                    fontSize: '14px',
    					                }
    					            }
    					        },
    					        labelLine: {
    					            normal: {
    					                show: false
    					            }
    					        },
    					        itemStyle: {
    					            normal: {
    					                borderWidth: 4,
    					                borderColor: '#ffffff',
    					            },
    					            emphasis: {
    					                borderWidth: 0,
    					                shadowBlur: 10,
    					                shadowOffsetX: 0,
    					                shadowColor: 'rgba(0, 0, 0, 0.5)'
    					            }
    					        }
    					    }],
    					    color: [
    					        '#00acee',
    					        '#52cdd5',
    					        '#79d9f1',
    					        '#a7e7ff',
    					        '#c8efff'
    					    ]
    					};
    				
    				myChart.setOption(option);
    			}
function eConsole(param) {    
   alert(param.name)
}
</script>
</body>

</html>