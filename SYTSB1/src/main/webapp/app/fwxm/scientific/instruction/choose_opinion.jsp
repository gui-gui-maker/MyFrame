<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>填写结论</title>
<%@ include file="/k/kui-base-form.jsp"%>

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

// 		if($("#remark").val().length>200){
// 			$.ligerDialog.error("备注超过200长度！！！");
// 		}
		result = {'opinion':opinion,'remark':remark};
	}
	return result;
}
$(function(){
	$("#formObj").initForm({    
		toolbar:null
	});
	
	
	
});

</script>

<style type="text/css">
    .l-tree .l-tree-icon-none img {
        height: 16px;
        margin: 3px;
        width: 16px;
    }
</style>
</head>
<body class="p5" style="overflow:auto">

<form id="formObj" action="formObj">

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
<!-- <input type="hidden" name="bglbText" id="bglbText"/> -->
	
</form>
</body>
</html>