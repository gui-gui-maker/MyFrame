<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<title>资源编辑</title>
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="pub/fileviewer/fileviewer.js"></script>
<script type="text/javascript">

	$(function() {
		$("#formObj").initForm({
			success : function(response) {//处理成功
				if (response.success) {
					top.$.notice('保存成功', 2);
					if("${param.copy}"=="1"){
						api.data.window.refresh(response.data, "copy");
					}else{
						api.data.window.refresh(response.data, "${param.status}");
					}
					api.close();
				} else {
					$.ligerDialog.error('保存失败' + response.msg != null ? response.msg : response)
				}
			},getSuccess:function(res){
			}
		});
	});
	function showFiles(val,e,srcObj){
		getFilesPath('chart/type/getFiles.do',e);
	}
</script>
</head>
<body>
	<form name="formObj" id="formObj" method="post" action="<c:if test="${param.copy=='1' }">chart/type/copy.do</c:if><c:if test="${param.copy!='1' }">chart/type/save.do</c:if>"
		getAction="chart/type/detail.do?id=${param.id}">
		<c:choose>
			<c:when test="${param.status=='add'}">
				<input type="hidden" name="chartType.id" value='${param.pid}'>
			</c:when>
			<c:otherwise>
				<input type="hidden" name="chartType.id">
				<input name="id" type="hidden"  />
			</c:otherwise>
		</c:choose>
		<table cellpadding="3" cellspacing="0" class="l-detail-table">
			<tr>
				<td class="l-t-td-left" style="width:100px;">类型名称：</td>
				<td class="l-t-td-right">
					<input name="name" type="text" ltype='text' validate="{required:true,maxlength:64}" />
				</td>
			</tr>
			<tr>
				<td class="l-t-td-left">对应图表：</td>
				<td class="l-t-td-right" >
					<input name="icon" id="icon" type="text" ltype='text' validate="{maxlength:128}"  
						ligerui="{iconItems:[{icon:'picture',click:function(val,e,srcObj){showFiles(val,e,srcObj)}}]}"/>
				</td>
			</tr>
			<tr>
				<td class="l-t-td-left">对应swf文件<br/>或类型：</td>
				<td class="l-t-td-right">
					<input name="swfName" type="text" ltype='text' validate="{maxlength:1024}" />
				</td>
			</tr>
			<tr>
				<td class="l-t-td-left">所属类型：</td>
				<td class="l-t-td-right">
					<input name="type" type="text" ltype='select' ligerui="{data:[{id:'SIGLE',text:'单系列图'},{id:'MULTI',text:'多系列图'},{id:'MMS',text:'双dataset系列'},{id:'SCATTER',text:'散点图'},{id:'BUBBLE',text:'气泡图'},{id:'ECHARTS',text:'ECharts'}]}" />
				</td>
			</tr>
			<tr>
				<td class="l-t-td-left">编号：</td>
				<td class="l-t-td-right">
					<input name="code" type="text" ltype='text'  />
				</td>
			</tr>
			<tr>
				<td class="l-t-td-left">排序：</td>
				<td class="l-t-td-right">
					<input name="sorts" type="text" ltype='text' />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
