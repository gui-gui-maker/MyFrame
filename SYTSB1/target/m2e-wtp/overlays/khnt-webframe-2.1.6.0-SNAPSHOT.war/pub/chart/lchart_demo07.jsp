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

RenderChart("demop008","div1","");
//使用RenderChart函数将"调用名称"为"demop001"显示在id为"div1"的div元素中，第三个参数为自定义参数，此处不用。
</script>
</head>

<body>
<form id="formObj">
<div style="padding:10px;">
    <div style="width:100%;">
        <div class="wrap">
            <div class="cnwp">
                <div class="caption_div">行政区划钻取（图表调用名称：<span style="color:#F00">demop008</span>）</div>
                <div style="height:400px; overflow:auto;" >
                    <div id="div1" style="width:100%; height:100%; border:hidden;" align="center"><br><br><font color="#999999">正在加载图形数据,请稍候...</font></div>
                </div>
            </div>
        </div>
    </div>
    <div style="width:100%; color:#F00">
      <p><br />
        <br />
        下钻：点击“四川省”柱子会在弹出窗口中调用demop009进一步钻取，其它柱子会在本图中向下钻取。（配置方法详见demop008的Set节点属性中的link相关属性）。<br />
        <br />
      </p>
      <p>上钻（返回）：</p>
      <p>&nbsp;&nbsp;&nbsp;&nbsp;方法一，在图表上任何位置鼠标右键，点击“返回上一层”会向上钻取。（配置方法详见demop008的“图表功能属性”中的showAboutMenuItem、aboutMenuItemLabel两个属性）。</p>
      <p>&nbsp;&nbsp;&nbsp;&nbsp;方法二，自定义按钮调用开放接口函数 LC_GotoBack('demop008') 实现上钻，例如：<button type="button" id="cback" onclick="LC_GotoBack('demop008')">返回</button></p>
      <p>&nbsp;</p>
      <p>另外，可以通过覆盖开放接口函数 LC_NoSubChart(chartid,params) 和LC_NoTopChart(chartid)分别实现钻取到最低层和上钻到最顶层后的提示信息。</p>
    </div>
</div>
</form>
</body>
</html>