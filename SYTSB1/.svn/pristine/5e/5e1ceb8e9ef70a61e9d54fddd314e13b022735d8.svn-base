<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<!-- 每个页面引入，页面编码、引入js，页面标题 -->
<%@ include file="/k/kui-base-form.jsp"%> 
<%@ include file="/k/kui-base-chart.jsp"%>  <!--引用chart公共资源文件-->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script language="javascript">
	RenderChart("demop002","div1","{n:'四川省'}"); 
	//使用RenderChart函数将“调用名称”为“demop001”显示在id为“div1”的div元素中，第三个参数为自定义参数，此处不用。
</script>
</head>

<body style="overflow:auto">

<div style="padding:10px;">
    <div style="width:100%;">
        <div class="wrap">
            <div class="cnwp">
                <div class="caption_div">行政区划数量排列情况（图表调用名称：<span style="color:#F00">demop002</span>）</div>
                <div style="height:300px; overflow:auto;" id="review">
                    <div id="div1" style="width:100%; height:100%; border:hidden;" align="center"><br><br><font color="#999999">正在加载图形数据,请稍候...</font></div>
                    
                </div>
            </div>
        </div>
    </div>
</div>
<div style="padding:10px;">
  <p>调用代码：</p><br />
  <p>1. 引入两个base文件：</p>
  <p style="color:#F00">&nbsp;&nbsp;&nbsp;&nbsp;&lt;%@ include file=&quot;/k/kui-base-form.jsp&quot;%&gt; <br />
  &nbsp;&nbsp;&nbsp;&nbsp;&lt;%@ include file=&quot;/k/kui-base-chart.jsp&quot;%&gt;
  <p>2. JS方法中调用RenderChart函数：</p>
  <p style="color:#F00">&nbsp;&nbsp;&nbsp;&nbsp;&lt;script language=&quot;javascript&quot;&gt;<br />
&nbsp;&nbsp;&nbsp;&nbsp;RenderChart(&quot;demop002&quot;,&quot;div1&quot;,&quot;&quot;); //调用名称为demop002的图表在“系统管理”-“开发管理”-“图表管理”中定义。<br />
&nbsp;&nbsp;&nbsp;&nbsp;//使用RenderChart函数将&quot;调用名称&quot;为&quot;demop002&quot;显示在id为&quot;div1&quot;的div元素中，第三个参数为自定义参数，此处不用。<br />
&nbsp;&nbsp;&nbsp;&nbsp;//注意：页面中必须存在尖为div1的div元素！<br />
&nbsp;&nbsp;&nbsp;&nbsp;&lt;/script&gt;</p>
</div>
</body>
</html>