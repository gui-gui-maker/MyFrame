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

RenderChart("demop005","div1","");
//使用RenderChart函数将"调用名称"为"demop005"显示在id为"div1"的div元素中，第三个参数为自定义参数，此处不用。
RenderChart("demop002","div3","");
//使用RenderChart函数将"调用名称"为"demop002"显示在id为"div3"的div元素中，第三个参数为自定义参数，此处不用。
RenderChart("demop003","div2","");
//使用RenderChart函数将"调用名称"为"demop003"显示在id为"div2"的div元素中，第三个参数为自定义参数，此处不用。

//行政区划onSelected事件触发更新图表数据函数setCharts
function setCharts()
{
	var p=$("#xzqh").val();
	if (p=="") p="000000";
	RefreshChart("demop005","{pid:'"+p+"'}");
	//如果页面上图表已经存在，建议使用RefreshChart函数仅更新图表数据而不重新渲染，第二个参数为JSON格式，其中pid是图表所属domain的全局参数里边的pid参数，若有其它参数需要更新，方法类似
	//同样可以根据自定义条件来改变其它图表的数据，如下：
	//RefreshChart("demop002","{spid:'"+p+"'}");
	//RefreshChart("demop003","{spid:'"+p+"'}");
}

function setCharts2()
{
	//自定义扩展其它方法
}

//覆盖点击每个图表后都会触发的方法：LC_ClickChart，linkScript为url和chart时有效
function LC_ClickChart(param)
{
	var p = $.parseJSON(param);//param是个JSON格式的字符串，使用前需要转换成JSON对象。
	if (p.chartid=="demop005")//当linkParam参数中设置的chartid为demop005时（LC_ClickChart函数在每个图表被点击都会被触发，加条件是为了过滤事件）
	{
		$("#xzqhs-txt").ligerGetComboBoxManager().selectValue(p.spid);
	}
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
                <div style="height:80px; overflow:auto;" id="review">
                    <table width="100%" border="0" cellspacing="2" cellpadding="0">
                      <tr>
                        <td class="l-t-td-left">省级：</td>
                        <td class="l-t-td-right"><input type="text" id="xzqh-txt" ltype="select" ligerui="{valueFieldID:'xzqh',readonly:true,data: [{text:'四川省',id:'510000'},{text:'贵州省',id:'520000'}],onSelected:function(){setCharts();}}"/></td>
                        <td class="l-t-td-left">市级：</td>
                        <td class="l-t-td-right"><input type="text" id="xzqhs-txt" ltype="select" ligerui="{valueFieldID:'xzqhs',readonly:true,data: [{text:'成都市',id:'510100'},{text:'自贡市',id:'510300'}],onSelected:function(){setCharts2();}}"/></td>
                      </tr>
                      <tr>
                        <td class="l-t-td-left">&nbsp;</td>
                        <td colspan="3" class="l-t-td-right"><p style="color:#F00">设置“省级”条件为“四川省”后，图表demop005的数据会被刷新。</p>
                        <p style="color:#F00">点出图表demop005中的“成都市”时，“市级”条件会被更新，同时demop002和demop003的数据同时被更新（需要覆盖开放接口函数LC_ClickChart，详见代码注释）</p></td>
                      </tr>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <div style="width:100%;">
        <div class="wrap">
            <div class="cnwp">
                <div class="caption_div">省级行政区划分布情况（图表调用名称：<span style="color:#F00">demop005</span>）</div>
                <div style="height:250px; overflow:auto;" >
                    <div id="div1" style="width:100%; height:100%; border:hidden;" align="center"><br><br><font color="#999999">正在加载图形数据,请稍候...</font></div>
                </div>
            </div>
        </div>
    </div>

    <div style="width:30%;float:left;">
        <div class="wrap">
            <div class="cnwp">
                <div class="caption_div">地市级区划分布（图表调用名称：<span style="color:#F00">demop003</span>）</div>
                <div style="height:250px; overflow:auto;" >
                    <div id="div2" style="width:100%; height:100%; border:hidden;" align="center"><br><br><font color="#999999">正在加载图形数据,请稍候...</font></div>
                </div>
            </div>
        </div>
    </div>
    <div style="width:70%;white-space:nowrap;float:right;">
        <div class="wrap" style="width:97%">
            <div class="cnwp">
                <div class="caption_div">地市级区划排名（图表调用名称：<span style="color:#F00">demop002</span>）</div>
                <div style="height:250px; overflow:auto;" >
                    <div id="div3" style="width:100%; height:100%; border:hidden;" align="center"><br><br><font color="#999999">正在加载图形数据,请稍候...</font></div>
                </div>
            </div>
        </div>
    </div>
</div>
</form>
</body>
</html>