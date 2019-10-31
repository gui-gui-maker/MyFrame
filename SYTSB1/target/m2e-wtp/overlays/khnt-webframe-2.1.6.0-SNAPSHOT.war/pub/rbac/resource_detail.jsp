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
				//引用资源
				if(res.data.type=='ref'){
					//$("#fromObj").setValues({refId:})
				}
			}
		});
	});
	function showFiles(val,e,srcObj){
		getFilesPath('rbac/resource/getFiles.do',e);
	}
	function resChange(val){
		if(val == '1'){
			$("#ref").show();
			$("#resourceUrl").hide();
			$('input[name="url"]').val("");
		}else{
			$("#ref").hide();
			$("#resourceUrl").show();
			$("#refName").val("");
			$("#refId").val("");
		}
	}
	
	function getResource(val,e,srcObj){
		top.$.dialog({
			width : 450,
			height : $(top).height(),
			lock : true,
			title : "资源",
			parent:api,
			content : 'url:pub/rbac/permission_Authorized_resource.jsp?perId='+"&checked=false",
			ok : function(w) {
				var iframe = this.iframe.contentWindow;
				var selectedNode = iframe.getSelectedResource();
				if(selectedNode&&selectedNode!=""&&selectedNode!=null){
					$("#refName").val(selectedNode[0].text);
					$("#refId").val(selectedNode[0].id)
				}else{
					$("#refName").val("");
					$("#refId").val("")
				}
				this.iframe.api.close();
				return false;
			},
			cancel : true
		});
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
				<td class="l-t-td-right"><input name="name" type="text" ltype='text' validate="{required:true,maxlength:64}" /></td>
				<td class="l-t-td-left">资源类型：</td>
				<td class="l-t-td-right"><u:combo name="type" code="sys_res_type" validate="required:true" /></td>
			</tr>
			<tr>
				<td class="l-t-td-left" style="width:120px;">资源编号：</td>
				<td class="l-t-td-right"><input name="code" type="text" ltype='text' validate="{maxlength:32}" /></td>
				<td class="l-t-td-left">菜单宽度：</td>
				<td class="l-t-td-right"><input name="width" type="text" ltype='text' validate="{maxlength:32}" /></td>
			</tr>
			<tr>
				<td class="l-t-td-left">是否引用资源：</td>
				<td class="l-t-td-right">
					<input name="isRef" type="radio" ltype='radioGroup' validate="{required:true}"  ligerui="{onChange:resChange,initValue:'0',data:[{id:'1',text:'是'},{id:'0',text:'否'}]}"/>
				</td>
				<td class="l-t-td-left">资源路径：</td>
				<td class="l-t-td-right" id="resourceUrl">
						<input name="url" type="text" ltype='text' validate="{maxlength:1024}" />
				</td>
				<td class="l-t-td-right" id="ref" style="display:none">
						<input id="refName" name="refResource.name" type="text" ltype='text' readonly="readonly" validate="{maxlength:32}" onclick="getResource()" 
						ligerui="{iconItems:[{img:'k/kui/images/icons/16/icon-down.png',click:function(val,e,srcObj){getResource(val,e,srcObj)}}]}"/>
						<input id="refId" name="refResource.id" type="hidden"/>
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
				<td class="l-t-td-left">图片css类：</td>
				<td class="l-t-td-right"><input name="iconCss" type="text" ltype='text' validate="{maxlength:256}" /></td>
				<td class="l-t-td-left">图片显示区域：</td>
				<td class="l-t-td-right">
					<input type="checkbox" name="display" ltype="checkboxGroup" validate="{required:true}"
						ligerui="{initValue:'1,2',data:[{text:'菜单',id:'1'},{text:'桌面',id:'2'}]}" />
				</td>
			</tr>
			<tr>
				<td class="l-t-td-left">桌面弹窗宽高：</td>
				<td class="l-t-td-right"><input name="winwh" type="text" ltype='text' validate="{maxlength:256}"
						title="填写格式800,600 表示宽800 高600"/></td>
				<td class="l-t-td-left">是否文本：</td>
				<td class="l-t-td-right">
					<input name="istext" type="radio" ltype="radioGroup" ligerui="{initValue:'false',data:[{id:'true',text:'是'},{id:'false',text:'否'}]}"/>
				</td>
			</tr>
			<tr>
				<td class="l-t-td-left">桌面弹窗URL：</td>
				<td class="l-t-td-right" colspan="3"><input name="desktopUrl" type="text" ltype='text' validate="{maxlength:1024}" /></td>
			</tr>
			<tr>
				<td class="l-t-td-left">排序：</td>
				<td class="l-t-td-right" colspan="3"><input name="sort" type="text" ltype='text' validate="{maxlength:32}" /></td>
			</tr>
			<tr>
				<td class="l-t-td-left">备注：</td>
				<td class="l-t-td-right"colspan="3"><textarea name="remark" ltype='l-textarea' rows="2" validate="{maxlength:1024}"></textarea>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
