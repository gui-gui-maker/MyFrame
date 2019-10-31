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
$(function() {
	$("#formObj").initForm({
		toolbar:null
	});
});//需要使用框架的下拉列表，但又要去掉表单的各种功能按钮

RenderChart("demop006","div1","");
//使用RenderChart函数将"调用名称"为"demop006"显示在id为"div1"的div元素中，第三个参数为自定义参数，此处不用。
RenderChart("demop007","div2","");
//使用RenderChart函数将"调用名称"为"demop007"显示在id为"div2"的div元素中，第三个参数为自定义参数，此处不用。

</script>
</head>

<body>
<form id="formObj">
<div style="padding:10px;">
    <div style="width:100%;">
        <div class="wrap">
            <div class="cnwp">
                <div class="caption_div">弹出图表（图表调用名称：<span style="color:#F00">demop006</span>）</div>
                <div style="height:280px; overflow:auto;" >
                    <div id="div1" style="width:100%; height:100%; border:hidden;" align="center"><br><br><font color="#999999">正在加载图形数据,请稍候...</font></div>
                </div>
            </div>
        </div>
    </div>
	<div style="width:100%;">
	  <p style="color:#F00">点击上图的各柱子，会出现指定的弹出窗口，配置方法详见demop006的Set节点属性中的link相关属性配置。</p>
	</div>
    <div style="width:100%;">
      <div class="wrap">
            <div class="cnwp">
                <div class="caption_div">新开窗口链接（图表调用名称：<span style="color:#F00">demop007</span>）</div>
                <div style="height:280px; overflow:auto;">
                    <div id="div2" style="width:100%; height:100%; border:hidden;" align="center"><br><br><font color="#999999">正在加载图形数据,请稍候...</font></div>
                </div>
            </div>
        </div>
    </div>
    <div style="width:100%;">
    <p style="color:#F00">点击上图的各柱子，会新开窗口出现指定的URL地址页面，配置方法详见demop007的Set节点属性中的link相关属性配置。</p>
    </div>
</div>
</form>
</body>
</html>