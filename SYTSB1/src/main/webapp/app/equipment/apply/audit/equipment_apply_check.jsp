<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<title>设备申请审核</title>
<%@ include file="/k/kui-base-form.jsp"%>
<%
	String apply_type = request.getParameter("apply_type");
%>
<SCRIPT type=text/javascript src="ueditor/ueditor.config.js"></SCRIPT>  
<SCRIPT type=text/javascript src="ueditor/ueditor.all.min.js"></SCRIPT>
<script type="text/javascript" src="app/common/js/idCard.js"></script>
<script type="text/javascript" src="pub/fileupload/js/fileupload.js"></script>
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
				if (response.attachs != null && response.attachs != undefined)
					showAttachFile(response.attachs);
			}, toolbar: [
	      		{
	      			text: "保存", icon: "save", click: function(){
	      				//表单验证
				    	if ($("#formObj").validate().form()) {
				    		if(checkForm()){
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

	function closewindow(){
		api.close();
	}
	
	function checkForm(){
		var apply_audit_result = $("#apply_audit_result").val();
		var apply_audit_desc = $("#apply_audit_desc").val();
		if("" == apply_audit_result){
			$.ligerDialog.alert("请选择审核结果！");
			$("#apply_audit_result").focus();
			return false;
		}
		if("1" == apply_audit_result){
			if("" == apply_audit_desc){
				$.ligerDialog.alert("请填写审核意见！");
				$("#apply_audit_desc").focus();
				return false;
			}
		}else{
			<%
				if(!"01".equals(apply_type) && !"03".equals(apply_type)){
					%>
					var need_out = $("#need_out").val();
					if("" == need_out){
						$.ligerDialog.alert("请选择是否需要占用出库！");
						$("#need_out").focus();
						return false;
					}	
					<%
				}
			%>
		}
		return true;
	}
</script>
</head>
<body>
	<form name="formObj" id="formObj" method="post" action="equipment2ApplyAction/check.do?status=${param.status}"
		getAction="equipment2ApplyAction/detail.do?id=${param.id}">
		<input type="hidden" name="id" id="id" value="${param.id}"/>
		<input type="hidden" id="apply_type" name="apply_type" value="${param.apply_type}"/>
		<input type="hidden" id="apply_reason" name="apply_reason"/>
		<input type="hidden" id="apply_need_date" name="apply_need_date"/>
		<input type="hidden" id="apply_end_date" name="apply_end_date"/>
		<input type="hidden" id="apply_date" name="apply_date"/>
		<input type="hidden" id="apply_unit_id" name="apply_unit_id"/>
		<input type="hidden" id="apply_unit_name" name="apply_unit_name"/>
		<input type="hidden" id="apply_id" name="apply_id"/>
		<input type="hidden" id="apply_name" name="apply_name"/>
		<input type="hidden" id="apply_submit_object_id" name="apply_submit_object_id"/>
		<input type="hidden" id="apply_submit_object_name" name="apply_submit_object_name"/>	
		<input type="hidden" id="create_by" name="create_by"/>
  		<input type="hidden" id="create_date" name="create_date"/>
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>设备申请审核</div>
			</legend>
			<table cellpadding="3" cellspacing="0" class="l-detail-table">
				<tr>
					<td class="l-t-td-left">审核结果：</td>
					<td class="l-t-td-right"><u:combo name="apply_audit_result" code="BASE_EQ_APPLY_RESULT" validate="required:true"/></td>	
					<c:if test="${'02' eq param.apply_type}">
						<td class="l-t-td-left">是否需要占用出库：</td>
						<td class="l-t-td-right"><u:combo name="need_out" code="BASE_EQ_NEED" validate="required:true"/></td>		
					</c:if>										
				</tr>
				<tr>
					<td class="l-t-td-left">审核意见：</td>
					<td class="l-t-td-right" colspan="3">
						<textarea name="apply_audit_desc" id="apply_audit_desc" rows="2" cols="25" class="l-textarea" validate="{maxlength:256}"></textarea>
					</td>	
				</tr>
			</table>
		</fieldset>
	</form>
</div>
</body>
</html>
