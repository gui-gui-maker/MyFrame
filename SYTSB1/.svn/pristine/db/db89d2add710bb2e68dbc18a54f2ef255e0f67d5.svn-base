<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
<title></title>
<%@include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
$(function () {
	$("#form1").initForm({    //参数设置示例
		success: function (res) {
			if(res.success){
				top.$.dialog.notice({
             		content: "保存成功！"
            	});		
				api.data.window.Qm.refreshGrid();
				api.close();
			}else{
				$.ligerDialog.error('保存失败！<br/>' + res.msg);
			}
		},
		afterParse:function(formObj){//form表单完成渲染后，回调函数
       	 	if(api.data.account){
       	 		$("#account_name").ligerGetTextBoxManager().setValue(api.data.account);
       	 		$("#account_name").ligerGetTextBoxManager().setDisabled();
       	 		//$("#account_name").val(api.data.account).attr({"disabled":"disabled"});
       	 	}
		}
	});
});
</script>
</head>
<body>
	<form id="form1"
		action="cwBankAccountAction/save.do?pageStatus=${param.pageStatus}"
		method="post" getAction="cwBankAccountAction/detail.do?id=${param.id}">
		<input type="hidden" name="id" id="id" value="${param.id }">
		<table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
			<tr>
				<td class="l-t-td-left">转账账户：</td>
				<td class="l-t-td-right">
					<input name="account_name" id="account_name" ltype="text" validate="{required:true}">
				</td>
				<td class="l-t-td-left">转账人：</td>
				<td class="l-t-td-right">
					<input name="transfer_person" id="transfer_person" ltype="text" validate="{required:true}">
				</td>
			</tr>
			<tr>
				<td class="l-t-td-left">转账人电话：</td>
				<td class="l-t-td-right">
					<input name="transfer_person_tel" id="transfer_person_tel" type="text" ltype='text' validate="{required:true,maxlength:30}">
				</td>
				<td class="l-t-td-left">转账人地址信息：</td>
				<td class="l-t-td-right">
					<input name="transfer_address" id="transfer_address" type="text" ltype='text'>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
