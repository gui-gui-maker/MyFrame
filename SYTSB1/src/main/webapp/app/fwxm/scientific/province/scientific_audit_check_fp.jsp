<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<title>科研项目申请书审核</title>
<%@ include file="/k/kui-base-form.jsp"%>
<%
	String apply_type = request.getParameter("apply_type");
%>
<SCRIPT type=text/javascript src="app/common/ueditor/ueditor.config.js"></SCRIPT>  
<SCRIPT type=text/javascript src="app/common/ueditor/ueditor.all.min.js"></SCRIPT>
<script type="text/javascript" src="app/common/js/idCard.js"></script>
<script type="text/javascript" src="pub/fileupload/js/fileupload.js"></script>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">
	$(function() {
		$("#tr").hide();
	    $("#formObj").initForm({
			success: function (response) {//处理成功
	    		if (response.success) {
	            	top.$.dialog.notice({
	             		content: "保存成功！"
	            	});
	            	api.data.window.Qm.refreshGrid();
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
				    			$("#formObj").submit();
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
	function auditJg(flag){
		if(flag!="1"){
			$("#tr").show();
		}else{
			$("#tr").hide();
		}
	}

</script>
</head>
<body>
	<form name="formObj" id="formObj" method="post" action="tjy2ScientificResearchAction/auditPass.do?id=${param.id}">
		<input type="hidden" name="id" id="id"/>
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>科研项目申请书审核</div>
			</legend>
			<table cellpadding="3" cellspacing="0" class="l-detail-table">
				<tr>
					<td class="l-t-td-left">审核意见：</td>
					<td class="l-t-td-right" colspan="3">
						<textarea id="remark" rows="2" cols="25" name="remark" class="l-textarea" validate="{maxlength:256}" ></textarea>
					</td>	
				</tr>
			</table>
		</fieldset>
	</form>
</div>
</body>
</html>
