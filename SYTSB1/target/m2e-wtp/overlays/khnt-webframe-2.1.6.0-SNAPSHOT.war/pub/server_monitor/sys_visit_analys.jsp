<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<%@ include file="/k/kui-base-form.jsp"%>
<%@ include file="/k/kui-base-chart.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script language="javascript">
$(function() {
	$("#form input").ligerDateEditor();
	RenderChart("pub_wrv_stsc","review");
	RenderChart("pub_wrv_os","clientOSAnalys");
	RenderChart("pub_wrv_bs","clientBSAnalys");
});
function openList(uri){
	winOpen({
		title: "系统资源访问记录",
		width: $(top).width()-100,
		height: $(top).height()-100,
		lock: true,
		content:"url:pub/server_monitor/sys_visit_list.jsp?showUrl=1&url=" + uri
	});
}
function doQuery(){
	var cparam = "{";
	if($("#startTime").val()!=""){
		cparam += "startTime:\"" + $("#startTime").val() +" 00:00:00\",";
	}
	if($("#endTime").val()!=""){
		cparam += "endTime:\"" + $("#endTime").val() +" 23:59:59\",";
	}
	cparam += "ppp:0}";
	RenderChart("pub_wrv_stsc","review",cparam);
	RenderChart("pub_wrv_os","clientOSAnalys",cparam);
	RenderChart("pub_wrv_bs","clientBSAnalys",cparam);
}
</script>
<style type="text/css">
.wrap{display:inline-block;}
.l-grid-row-cell-inner{
	padding: 4px 0!important;
}
.l-text{padding:3px 5px!important;}
.l-button{padding:3px 8px!important;}
</style>
</head>
<body class="p5" style="overflow:auto;">
	<div style="height:36px;">
		<table id="form">
			<tr>
				<td>访问日期从:</td>
				<td><input type="text" ltype="date" id="startTime" /></td>
				<td>到</td>
				<td><input type="text" ltype="date" id="endTime" /></td>
				<td><button type="button" onclick="doQuery()" class="l-button">确定</button></td>
			</tr>
		</table>
	</div>
	<div class="wrap" style="width:100%">
        <div class="cnwp">
            <div class="caption_div">功能模块访问量排名情况（TOP20）</div>
            <div style="height:300px;" id="review">
				<div id="div1" style="width:100%; height:100%; border:hidden;" align="center">
					<font color="#999999">正在加载图形数据,请稍候...</font>
                </div>
            </div>
        </div>
	</div>
	<div class="wrap" style="width:49.5%">
        <div class="cnwp">
            <div class="caption_div">客户端平台分析</div>
            <div style="height:350px;" id="clientOSAnalys">
				<div id="div1" style="width:100%; height:100%; border:hidden;" align="center">
					<font color="#999999">正在加载图形数据,请稍候...</font>
                </div>
            </div>
        </div>
	</div>
	<div class="wrap" style="width:50%;float:right;">
        <div class="cnwp">
            <div class="caption_div">客户端浏览器分析</div>
            <div style="height:350px;" id="clientBSAnalys">
				<div id="div1" style="width:100%; height:100%; border:hidden;" align="center">
					<font color="#999999">正在加载图形数据,请稍候...</font>
                </div>
            </div>
        </div>
    </div>
</body>
</html>