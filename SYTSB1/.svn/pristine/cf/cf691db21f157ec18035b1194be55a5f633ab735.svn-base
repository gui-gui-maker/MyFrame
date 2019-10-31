<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head pageStatus="${param.status}">
<!-- 每个页面引入，页面编码、引入js，页面标题 -->
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">
	//jQuery页面载入事件

	var beanData;
	$(function() {

		$("#formObj").initForm({
			success : function(responseText) {//处理成功
				if (responseText.success) {
					top.$.notice("处理成功");
					if(api.data.window.Qm){
						api.data.window.Qm.refreshGrid();
					}
					api.close();
				} else {
					$.ligerDialog.error('处理失败' + responseText.msg);
				}
			},
			getSuccess : function(response) {
				if (response.success)
					beanData = response.data;
				else {
					$.ligerDialog.error(response.msg);
					return;
				}
			}
		});
	});
	
	
</script>

</head>
<body>
	<form name="formObj" id="formObj" method="post"
		action="oa/car/apply/handle.do">
		<input name="id" type="hidden" id="id" value="${param.id}"/>
		<table border="1" cellpadding="3" cellspacing="0"
			class="l-detail-table">
			<tr>
				<td class="l-t-td-left">处理结果：</td>
				<td class="l-t-td-right">
				<input type="radio" name="state" value="否" ltype="radioGroup"
					ligerui="{value:'同意',data:[{text:'同意',id:'同意'},{text:'不同意',id:'不同意'}]}">
				</td>
			</tr>
			<tr>
				<td class="l-t-td-left">意见：</td>
				<td class="l-t-td-right" >
				<textarea name="remark" rows="5" cols="7"></textarea>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>

