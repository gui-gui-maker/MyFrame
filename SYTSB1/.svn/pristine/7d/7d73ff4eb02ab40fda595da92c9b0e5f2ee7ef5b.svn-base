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
		getFilesPath('rbac/resource/getFiles.do',e);
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
					<input type="text" name="type" ltype="select" ligerui="{initValue:'1',data:[{id:'1',text:'普通资源'}]}" validate="{required:true}"/>
				</td>
			</tr>
			<tr>
				<td class="l-t-td-left">资源路径：</td>
				<td class="l-t-td-right" colspan="3">
					<input name="url" type="text" ltype='text' validate="{maxlength:1024}" />
				</td>
			</tr>
			<tr>
						<td class="l-t-td-left">菜单图片16*16：</td>
						<td class="l-t-td-right" colspan="3">
							<input name="image" id="image" type="text" ltype='text' validate="{maxlength:128}"  
								ligerui="{iconItems:[{icon:'picture',click:function(val,e,srcObj){showFiles(val,e,srcObj)}}]}"/>
						</td>
					</tr>
					<tr>
						<td class="l-t-td-left">菜单图片32*32：</td>
						<td class="l-t-td-right" colspan="3">
							<input name="image32" id="image32" type="text" ltype='text' validate="{maxlength:128}"  
								ligerui="{iconItems:[{icon:'picture',click:function(val,e,srcObj){showFiles(val,e,srcObj)}}]}"/>
						</td>
					</tr>
					<tr>
						<td class="l-t-td-left">菜单图片48*48：</td>
						<td class="l-t-td-right" colspan="3">
							<input name="image48" id="image48" type="text" ltype='text' validate="{maxlength:128}"  
								ligerui="{iconItems:[{icon:'picture',click:function(val,e,srcObj){showFiles(val,e,srcObj)}}]}"/>
						</td>
					</tr>
					<tr>
						<td class="l-t-td-left">菜单图片64*64：</td>
						<td class="l-t-td-right" colspan="3">
							<input name="image64" id="image64" type="text" ltype='text' validate="{maxlength:128}"  
								ligerui="{iconItems:[{icon:'picture',click:function(val,e,srcObj){showFiles(val,e,srcObj)}}]}"/>
						</td>
					</tr>
					<tr>
						<td class="l-t-td-left">菜单图片128*128：</td>
						<td class="l-t-td-right" colspan="3">
							<input name="image128" id="image128" type="text" ltype='text' validate="{maxlength:128}"  
								ligerui="{iconItems:[{icon:'picture',click:function(val,e,srcObj){showFiles(val,e,srcObj)}}]}"/>
						</td>
					</tr>
					<tr>
						<td class="l-t-td-left">菜单图片256*256：</td>
						<td class="l-t-td-right" colspan="3">
							<input name="image256" id="image256" type="text" ltype='text' validate="{maxlength:128}"  
								ligerui="{iconItems:[{icon:'picture',click:function(val,e,srcObj){showFiles(val,e,srcObj)}}]}"/>
						</td>
					</tr>
			<tr>
				<td class="l-t-td-left" style="width:120px;">资源编号：</td>
				<td class="l-t-td-right"><input name="code" type="text" ltype='text' validate="{maxlength:32}" /></td>
				<td class="l-t-td-left">排序：</td>
				<td class="l-t-td-right"><input name="sort" type="text" ltype='text' validate="{maxlength:32}" /></td>
			</tr>
			<tr>
				<td class="l-t-td-left">备注：</td>
				<td class="l-t-td-right" colspan="3"><textarea name="remark" ltype='l-textarea' rows="5" validate="{maxlength:1024}"></textarea></td>
			</tr>
		</table>
	</form>
</body>
</html>
