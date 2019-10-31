<%@page import="java.util.Date"%>
<%@page import="com.khnt.utils.DateUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<%@ include file="/k/kui-base-form.jsp"%>
<%@include file="/pub/fileupload/kui-fileupload.jsp"%>
<script type="text/javascript" src="ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="ckeditor/adapters/jquery.js"></script>
<script type="text/javascript">
	$(function() {
		//绑定表单操作事件
		$('#content').ckeditor();
		
		//配置资源选择器
		$("#formObj").initForm({
			success : function(responseText) {//处理成功
				if (responseText.success) {
					$.ligerDialog.success('保存成功', function() {
						try{
							api.data.window.submitAction();
							api.close();
						}catch(e){
							top.$.dialog.closeAll();
						}
					});
				} else {
					$.ligerDialog.error('保存失败' + responseText)
				}
			},
			getSuccess : function(response) {
				if (response.attachs != null && response.attachs != undefined)
				{
					if("${param.status}"=='detail')	
					{
						listAttachsDetail(response.attachs);
					}else
					{
						listAttachs(response.attachs);	
					}
				}
			}
		});

		//附件上传配置
		configSetting("1gb", "所有OFFCIE文档，压缩文件",
				"doc,docx,xls,xlsx,ppt,pptx,rar,pdf,zip,txt,jpg,gif,png",
				"${param.id}");

	});
	function listAttachs(files) {
		$.each(files,function(i,el){
			$("#existFiles").append(
						"<span id='" + el.id + "'><a href='fileUpload/downloadByObjId.do?id="
						+ el.id + "'>[" + el.fileName
						+ "]</a><input type='button' value='删除' class='l-button' onclick='deleteFile(\"" 
						+ el.id +"\")';$(this.parentNode).remove()' /></span><br/>");
		});
	}
	function listAttachsDetail(files) {
		$.each(files, function() {
			$("#existFiles").append(
					"<a href='fileUpload/downloadByObjId.do?id=" + this.id
							+ "'>" + this.fileName + "</a>&nbsp;");
			
		});
	}
</script>
</head>
<body>
	<form name="formObj" id="formObj" method="post"
		action="app/zp/notice/saveNotice.do"
		getAction="app/zp/notice/detail.do?id=${param.id}">
		<input name="id" type="hidden" id="id" ltype='hidden' />

		<table border="1" cellpadding="3" cellspacing="0"
			class="l-detail-table">
			<tr>
				<td class="l-t-td-left">标题：</td>
				<td class="l-t-td-right" colspan="3"><input name="title" type="text" ltype='text' validate="{required:true,maxlength:255}" /></td>
			</tr>
			<tr>
				<td class="l-t-td-left">是否显示：</td>
				<td class="l-t-td-right" colspan="3">
					<u:combo name="sign" code="isOrNot" attribute="initValue:1"></u:combo>
				</td>
			</tr>
			<tr>
				<td class="l-t-td-left">附件：</td>
				<td class="l-t-td-right" colspan="3">
					<jsp:include page="/pub/fileupload/kui-fileupload-container.jsp" />
					<div id="existFiles" style="padding: 5px;"></div> 
				</td>
			</tr>
			<tr>
				<td class="l-t-td-left">内容：</td>
				<td class="l-t-td-right" colspan="3">
					<textarea name="content" id="content" class="l-textarea" ltype="textarea"></textarea> 
				</td>
			</tr>
		</table>
	</form>
</body>
</html>