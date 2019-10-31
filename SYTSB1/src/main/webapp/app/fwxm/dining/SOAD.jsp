<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
    <title></title>
    <%@include file="/k/kui-base-form.jsp" %>
    <script type="text/javascript">
    var foods = api.data.foods;
			$("#form1").initForm({    //参数设置示例
				success: function (response) {
					if(response.success){
						top.$.notice("保存成功！",3);
						api.data.window.Qm.refreshGrid();
						api.close();
					}
					else{
						$.ligerDialog.error("操作失败！<br/>" + response.msg);
					}
				}
			});
    </script>
</head>
<body>
<div></div>
<form id="form1" action="dining/seatOrder/saveSeatOrder.do" method="post" getAction="dining/seatOrder/getSeatOrder.do?id=${param.id}">
    <input type="hidden" id="id" name="id">
    <table cellpadding="3" class="l-detail-table">
		<tr>
			<td class="l-t-td-left">就餐时间：</td>
			<td class="l-t-td-right"><input name="use_time" type="text" ltype="date" ligerUi="{format:'yyyy-MM-dd hh:mm'}" validate="{required:true}"/></td>
			<td class="l-t-td-left">就餐人数：</td>
			<td class="l-t-td-right"><input name="use_num" type="text" ltype="text" validate="{required:true,digits:true}"/></td>
		</tr>
		<tr>
			<td class="l-t-td-left" >菜品名称：</td>
			<td class="l-t-td-right" colspan="3">
			<input name="food_ids" 
					type="checkbox" 
					ltype="checkboxGroup"
					validate="{required:true}"
					ligerui="{
						readonly:true,
						data:foods
						}"
					/>
			</td>
			
	    </tr>
	    <c:if test="${param.status=='detail'}">
			<tr>
				<td class="l-t-td-left">订餐时间：</td>
				<td class="l-t-td-right"><input name="order_time" type="text" ltype="date" ligerUi="{format:'yyyy-MM-dd hh:mm'}"/></td>
				<td class="l-t-td-left">状态：</td>
				<td class="l-t-td-right"><u:combo name="is_used" code="seatOrderStatus"/></td>
			</tr>
		</c:if>
		<c:if test="${param.status=='detail'}">
			<tr>
				<td class="l-t-td-left">实际就餐时间：</td>
				<td class="l-t-td-right"><input name="real_use_time" type="text" ltype="date" ligerUi="{format:'yyyy-MM-dd hh:mm'}"/></td>
				<td class="l-t-td-left">订餐人：</td>
				<td class="l-t-td-right"><input name="order_man" type="text" ltype="text"/></td>
			</tr>
		</c:if>
		<tr>
			<td class="l-t-td-left">备注：</td>
			<td class="l-t-td-right"><textarea name="remark" rows="5" validate="{maxlength:512}"></textarea></td>
			<td class="l-t-td-left">订餐电话：</td>
			<td class="l-t-td-right"><input name="tel" type="text" ltype="text" validate="{required:true}"/></td>
		</tr>
    </table>
</form>
</body>
</html>
