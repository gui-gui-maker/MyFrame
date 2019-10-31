<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<%@ include file="/k/kui-base-form.jsp"%>
<title>审核意见</title>
<script type="text/javascript">

function getSelectResult() {
	var result = null;
	var opinion=$("#opinion").ligerGetRadioGroupManager().getValue();
	var remark=$("#remark").val();
	if(opinion=="1"){
		if($("#remark").val()==""||$("#remark").val()=="null"){
			$.ligerDialog.error("请填写不通过原因！！！");
			result = {'opinion':opinion,'remark':remark};
		}else{
			result = {'opinion':opinion,'remark':remark};
		}
	}else{
		result = {'opinion':opinion,'remark':remark};
	}
	return result;
}

$(function() {
	 $("#formObj").initForm({
			toolbar: null
	}); 
});

</script>
</head>
<body>
	<form name="formObj" id="formObj">
	<table  border="1" cellpadding="3" cellspacing="0"
			class="l-detail-table">
		<tr>
			<td class="l-t-td-left">结论：</td>
			<td class="l-t-td-right">
			<input id="opinion" name="opinion" type="radio" ltype='radioGroup'
			 ligerui="{initValue:'0',data:[{id:'0',text:'通过'},{id:'1',text:'不通过'}]}"/>
			</td>
		</tr>
			<tr>
						<td class="l-t-td-left" style="width:120px">备注：</td>
						<td class="l-t-td-right"><textarea name="remark" id="remark" rows="3" cols="" placeholder="请在此处填写不通过原因！" ext_type="string" ext_maxLength="200" ext_name="备注" isNull="Y" class="area_text" ></textarea></td>
						
					</tr>
	</table>
	</form>
</body>
</html>
