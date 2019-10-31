<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<title>质量监督管理部纠正结果</title>
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
	$(function() {
	    $("#formObj").initForm({
			success: function (response) {//处理成功
	    		if (response.success) {
					top.$.dialog.notice({
	             		content: "保存成功！"
	            	});	
			   		api.data.window.refreshGrid();
			     	api.close();  
	    		} else {
	           		$.ligerDialog.error('保存失败！<br/>' + response.msg);
	      		}
			}, getSuccess: function (response){

			}, toolbar: [
	      		{
	      			text: "纠正完成", icon: "save", click: function(){
				    	if ($("#formObj").validate().form()) {
				    		if(confirm("确认保存？保存后不能修改！")){
							    //表单提交
							    $("#formObj").submit();
							}
				    	}
	      			}
	      		},
				{
					text: "关闭", icon: "cancel", click: function(){
						api.close();
					}
				}
			], toolbarPosition: "bottom"
		});
	});
</script>
</head>
<body>
	<form name="formObj" id="formObj" method="post" action="report/cancel/qua_finish.do">
		<input type="hidden" name="id" id="id" value="${param.id}"/>
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>质量监督管理部纠正结果</div>
			</legend>
			<table cellpadding="3" cellspacing="0" class="l-detail-table">
				<tr>
					<td class="l-t-td-left">纠正结果：</td>
					<td class="l-t-td-right" colspan="3">
						<textarea name="qua_end_result" id="qua_end_result" rows="3" cols="25" class="l-textarea" validate="{required:true,maxlength:1000}"></textarea>				
					</td>								
				</tr>
			</table>
		</fieldset>
	</form>
</div>
</body>
</html>
