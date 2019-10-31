<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<%@ include file="/k/kui-base-form.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script language="javascript">
$(function() {
	$("#form input").ligerDateEditor();
	window["dataGrid"]=$("#dataGrid").ligerGrid({
		columns: [{
			id:"name", name:"name", display:"资源名称", width:255, align:"left",render:function(rowData,idx){
				if(rowData.url){
					return "<a href='javascript:openList(\""+rowData.url+"\")'>" + rowData.name + "</a>";
				}else{
					return rowData.name
				}
			}
		},{
			name:"url", display:"资源地址", width:412, align:"left"
		},{
			name:"count", display:"访问量", width:80, align:"center", render:function(rowdata,idx){
				if(rowdata.url && !rowdata.count) return "0";
				else return rowdata.count;
			}
		}],
		usePager: false,
		url: "pub/wrv/visit_stsc.do",
		height: "100%",
		tree: {
			columnId : 'name',
            idField: 'id',
            parentIDField: 'pid'
		}
	});
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
	$("body").mask("正在查询，请稍候...");
	$.getJSON("pub/wrv/visit_stsc.do?startTime=" + $("#startTime").val() + "&endTime=" + $("#endTime").val(),function(resp){
		if(resp.Rows){
			window["dataGrid"].loadData(resp);
		}
		$("body").unmask();
	});
}
</script>
<style type="text/css">
.l-grid-row-cell-inner{
	padding: 4px 0 !important;
}
.l-text{padding:3px 5px!important;}
.l-button{padding:3px 8px!important;}
</style>
</head>
<body>
<table id="form" style="margin:3px;">
	<tr>
		<td>访问日期从:</td>
		<td><input type="text" ltype="date" id="startTime" /></td>
		<td>到</td>
		<td><input type="text" ltype="date" id="endTime" /></td>
		<td><button type="button" onclick="doQuery()" class="l-button">确定</button></td>
	</tr>
</table>
<div id="dataGrid"></div>
</body>
</html>