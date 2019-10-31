<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head pageStatus="modify">
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
					api.data.window.Qm.refreshGrid();
					api.close();
				} else {
					$.ligerDialog.error('处理失败' + responseText.msg);
				}
			},
			getSuccess : function(response) {
				$.getJSON("oa/car/info/detail.do?id=${param.car_id}",function(data){
					if(data.success){
						if(data.data.carMileage){
							$("input[name='startKm']").val(data.data.carMileage)
						}
					}
				})
			}
		});
	});
	
	
</script>

</head>
<body>
	<form name="formObj" id="formObj" method="post"
		action="oa/car/apply/sendCar.do"
		getAction="oa/car/apply/detail.do?id=${param.id}">
		<input name="id" type="hidden" id="id" value="${param.id}"/>

		<table border="1" cellpadding="3" cellspacing="0"
			class="l-detail-table">
			<tr>
				<td class="l-t-td-left">起始公里数：</td>
				<td class="l-t-td-right"><input type="text" ltype="spinner" name="startKm" validate="{required:true,maxlength:20}"  ligerui="{type:'float',isNegative:false,suffix:'公里'}"/></td>
				
			</tr>
			<tr>
				<td class="l-t-td-left">实际开始时间：</td>
				<td class="l-t-td-right"><input type="text" ltype='date' name="startTime" validate="{required:true}" ligerui="{format:'yyyy-MM-dd hh:mm'}"/></td>
				
			</tr>
		</table>

	</form>
</body>
</html>

