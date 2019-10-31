<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="detail">
<title></title>
<!-- 每个页面引入，页面编码、引入js，页面标题 -->
<%@include file="/k/kui-base-form.jsp"%>
<link rel="stylesheet" type="text/css" href="pub/chart/css/chart.css" />
<%@ taglib uri="http://khnt.com/tags/chart" prefix="chart" %>
<script type="text/javascript">

function testEvent(param){
	var selected = param.selected;
	for(var i in selected){
		if (selected[i]) {
           alert(i)
          	$("#chart").attr('src',"pub/chart/test2.jsp?paramValue=$T{depName}="+i+",402880aa47568721014756a82c5c0003");
        }
	}
}
</script>
</head>
<body class="chart_global" style="margin-top:30px">
<table width="98%" border="0" cellspacing="20" cellpadding="0" align="center" class="chart_wel_ge">
	<tr>
		<td>
            <table border="0" cellspacing="0" cellpadding="0" class="wrap" align="center">
                <tr>
                    <td><table border="0" cellspacing="0" cellpadding="0" class="cnwp">
                            <tr>
                                <td class="caption">echarts地图测试</td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                  <td>
                  	<div id="div1" style="height:450px; border:hidden;" align="center"><br><br><font color="#999999">正在加载图形数据,请稍候...</font></div>
                  	<chart:chart chartNum="maptest" renderAt="div1" width="100%" height="100%"/>
                  </td>
              </tr>
            </table>        
		</td>
		<td>
            <table border="0" cellspacing="0" cellpadding="0" class="wrap" align="center">
                <tr>
                    <td><table border="0" cellspacing="0" cellpadding="0" class="cnwp">
                            <tr>
                                <td class="caption">fusioncharts测试</td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                  <td style="height:450px">
                  	<iframe style="overflow: hidden;width: 100%;height: 100%;border: 0px;" id="chart" src=""></iframe>
                  </td>
              </tr>
            </table>        
		</td>
	</tr>
</table>
</body>
</html>