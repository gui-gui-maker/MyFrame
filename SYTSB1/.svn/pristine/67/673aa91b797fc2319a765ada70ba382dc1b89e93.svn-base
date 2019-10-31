<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<!-- 每个页面引入，页面编码、引入js，页面标题 -->
<%@ include file="/k/kui-base-form.jsp"%>
<%@ include file="/k/kui-base-chart.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script language="javascript">
RenderChart("demop001","div1","");
//使用RenderChart函数将"调用名称"为"demop001"显示在id为"div1"的div元素中，第三个参数为自定义参数，此处不用。
RenderChart("demop002","div3","");
//使用RenderChart函数将"调用名称"为"demop002"显示在id为"div3"的div元素中，第三个参数为自定义参数，此处不用。
RenderChart("demop003","div2","");
//使用RenderChart函数将"调用名称"为"demop003"显示在id为"div2"的div元素中，第三个参数为自定义参数，此处不用。
</script>
</head>

<body style="overflow:auto">

<div style="padding:10px;">
    <div style="width:100%;">
        <div class="wrap">
            <div class="cnwp">
                <div class="caption_div">省级行政区划分布情况（图表调用名称：<span style="color:#F00">demop001</span>）</div>
                <div style="height:300px; overflow:auto;">
                    <div id="div1" style="width:100%; height:100%; border:hidden;" align="center"><br><br><font color="#999999">正在加载图形数据,请稍候...</font></div>
                </div>
            </div>
        </div>
    </div>
    <div>
      <p style="color:#F00">点击上图各柱子，下方两图会同时联动。</p>
      <p style="color:#F00">实现方式：定义图表demop001时，将Set节点属性的linkScript属性设置为chart，link属性设置为下面两图的调用名称：demop002,demop003，并给linkParam属性设置两个图的全局参数spid赋值：spid:$d{id}</p>
    </div>
    <div style="width:30%;float:left;">
        <div class="wrap">
            <div class="cnwp">
                <div class="caption_div">地市级区划分布（图表调用名称：<span style="color:#F00">demop003</span>）</div>
                <div style="height:300px; overflow:auto;" >
                    <div id="div2" style="width:100%; height:100%; border:hidden;" align="center"><br><br><font color="#999999">正在加载图形数据,请稍候...</font></div>
                </div>
            </div>
        </div>
    </div>
    <div style="width:70%;white-space:nowrap;float:right;">
        <div class="wrap" style="width:97%">
            <div class="cnwp">
                <div class="caption_div">地市级区划排名（图表调用名称：<span style="color:#F00">demop002</span>）</div>
                <div style="height:300px; overflow:auto;" >
                    <div id="div3" style="width:100%; height:100%; border:hidden;" align="center"><br><br><font color="#999999">正在加载图形数据,请稍候...</font></div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>