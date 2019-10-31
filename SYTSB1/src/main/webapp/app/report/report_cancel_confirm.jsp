﻿<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<title>业务服务部确认纠正措施完成情况</title>
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
	$(function() {
	    $("#formObj").initForm({
			success: function (response) {//处理成功
	    		if (response.success) {
					top.$.dialog.notice({
	             		content: "确认成功！"
	            	});	
			   		api.data.window.refreshGrid();
			     	api.close();  
	    		} else {
	           		$.ligerDialog.error('提交失败！<br/>' + response.msg);
	      		}
			}, getSuccess: function (response){

			}, toolbar: [
	      		{
	      			text: "确认", icon: "save", click: function(){
				    	if ($("#formObj").validate().form()) {
				    		if(confirm("确认提交？提交后不能修改！")){
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
	<form name="formObj" id="formObj" method="post" action="report/cancel/confirm.do">
		<input type="hidden" name="id" id="id" value="${param.id}"/>
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>业务服务部确认纠正措施完成情况</div>
			</legend>
			<table cellpadding="3" cellspacing="0" class="l-detail-table">
				<tr>
					<td class="l-t-td-left">纠正措施完成情况：</td>
					<td class="l-t-td-right" colspan="3">
						<input type="checkbox" name="confirm_result"  id="confirm_result" ltype="checkboxGroup"
							ligerui="{data: [ { text:'原报告号作废', id:'1' }, { text:'原报告盖作废章并存档', id:'2' }, { text:'追回报告销毁', id:'3' }] }"/>					
					</td>								
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>
