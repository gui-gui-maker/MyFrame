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

RenderChart("demop004","div1","");
//使用RenderChart函数将"调用名称"为"demop004"显示在id为"div1"的div元素中，第三个参数为自定义参数，此处不用。

//行政区划onSelected事件触发更新图表数据函数setCharts
function setSearch(param)
{
	var p = $.parseJSON(param);//param是个JSON格式的字符串，使用前需要转换成JSON对象。
	$("#xzqh-txt").ligerGetComboBoxManager().selectValue(p.xzqh);
	//定义图表Set节点属性的linkScript属性为js
	//本方法名对应定义图表Set节点属性的link属性
	//xzqh参数在定义图表Set节点属性的linkParam属性中指定
}

</script>
</head>

<body>
<form id="formObj">
<div style="padding:10px;">
	<div style="width:100%;">
        <div class="wrap">
            <div class="cnwp">
                <div class="caption_div">查询条件设置</div>
                <div style="height:35px; overflow:auto;" id="review">
                    <table width="100%" border="0" cellspacing="2" cellpadding="0">
                      <tr>
                        <td class="l-t-td-left">省级：</td>
                        <td class="l-t-td-right"><input type="text" id="xzqh-txt" ltype="select" ligerui="{valueFieldID:'xzqh',readonly:true,data: [{text:'四川省',id:'510000'},{text:'贵州省',id:'520000'}]}"/></td>
                      </tr>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <div style="width:100%;">
        <div class="wrap">
            <div class="cnwp">
                <div class="caption_div">省级行政区划分布情况（图表调用名称：<span style="color:#F00">demop004</span>）</div>
                <div style="height:280px; overflow:auto;">
                    <div id="div1" style="width:100%; height:100%; border:hidden;" align="center"><br><br><font color="#999999">正在加载图形数据,请稍候...</font></div>
                </div>
            </div>
        </div>
    </div>
</div>
<div style="padding:10px;">
  <p style="color:#F00">点击图表中的“四川省”、“贵州省”时，将改变自定义查询条件“省级”下拉框的值。</p>
  <p style="color:#F00">实现方式：定义demop004图表时，将Set节点属性的linkScript属性设置为js，link属性设置本页面的js方法：setSearch，linkParam属性设置为setSearch需要的参数：xzqh:$d{id}（注：传递的参数为json格式字符串）</p>
</div>
</form>
</body>
</html>