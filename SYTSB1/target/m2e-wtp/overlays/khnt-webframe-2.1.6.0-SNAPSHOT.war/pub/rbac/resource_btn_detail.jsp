﻿<%@ page contentType="text/html;charset=UTF-8"%>
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
					if("${param.copy}"=='1'){
						api.data.window.copyRes(response.data)
					}else{
						api.data.window.refresh(response.data, "${param.status}");
					}
					api.close();
				} else {
					$.ligerDialog.error('保存失败' + response.msg != null ? response.msg : response)
				}
			},getSuccess:function(res){
				if("${param.copy}"=='1'){
					$("#formObj").setValues({id:null})
				}
			}
		});
	});
	function showFiles(val,e,srcObj){
		var width = 600;
		var height = 400;
		var windows = top.$.dialog({
			parent : api,
			width : width,
			height : height,
			lock : true,
			data : {
				window : window
			},
			title : "选择图标",
			content : 'url:pub/rbac/select_icon.jsp'
		});
	}
	function setIcon(obj){
		$("#iconCss").val(obj);
	}
</script>
</head>
<body>
	<form name="formObj" id="formObj" method="post" action="rbac/resource/save.do"
		getAction="rbac/resource/detail.do?id=${param.pid}">
		<c:choose>
			<c:when test="${param.status=='add'}">
				<input type="hidden" name="resource.id" value='${param.pid}'>
			</c:when>
			<c:otherwise>
				<input type="hidden" name="resource.id">
				<input name="id" type="hidden"  />
			</c:otherwise>
		</c:choose>
		<table cellpadding="3" cellspacing="0" class="l-detail-table">
			<tr>
				<td class="l-t-td-left" style="width:100px;">资源名称：</td>
				<td class="l-t-td-right">
					<input name="name" type="text" ltype='text' validate="{required:true,maxlength:64}" />
				</td>
				<td class="l-t-td-left">资源类型：</td>
				<td class="l-t-td-right">
					<input type="text" name="type" ltype="select" ligerui="{initValue:'2',data:[{id:'2',text:'按钮资源'}]}" validate="{required:true}"/>
				</td>
			</tr>
			<tr>
				<td class="l-t-td-left" style="width:120px;">资源编号：</td>
				<td class="l-t-td-right"><input name="code" type="text" ltype='text' validate="{maxlength:32}" /></td>
				<td class="l-t-td-left">资源图标：</td>
				<td class="l-t-td-right">
					<input name="iconCss" id="iconCss" type="text" ltype='text' validate="{maxlength:128}"  
						ligerui="{iconItems:[{icon:'picture',click:function(val,e,srcObj){showFiles(val,e,srcObj)}}]}"/>
					<input name="image" id="image" type="hidden" value='k/kui/images/icons/16/plugin.png'/>
				</td>
			</tr>
			<tr>
				<td class="l-t-td-left">资源路径：</td>
				<td class="l-t-td-right" colspan="3">
					<input name="url" type="text" ltype='text' validate="{maxlength:1024}" />
				</td>
			</tr>
			<tr>
				<td class="l-t-td-left">资源事件：</td>
				<td class="l-t-td-right" colspan="3">
					<textarea rows="5" name="click" ltype="l-textarea" validate="{maxlength:4000}"></textarea>
				</td>
			</tr>
			<tr>
				<td class="l-t-td-left">显示分割线：</td>
				<td class="l-t-td-right">
					<input name="istext" type="radio" ltype="radioGroup" ligerui="{initValue:'true',data:[{id:'true',text:'是'},{id:'false',text:'否'}]}"/>
				</td>
				<td class="l-t-td-left">排序：</td>
				<td class="l-t-td-right"><input name="sort" type="text" ltype='text' validate="{maxlength:32}" /></td>
			</tr>
			<tr>
				<td class="l-t-td-left">备注：</td>
				<td class="l-t-td-right"colspan="3"><textarea name="remark" ltype='l-textarea' rows="2" validate="{maxlength:1024}"></textarea></td>
			</tr>
		</table>
	</form>
</body>
</html>