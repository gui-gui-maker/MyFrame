<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
    <title>订餐</title>
    <!-- 只有订餐用了这张页面 -->
    <%@include file="/k/kui-base-form.jsp" %>
    <script type="text/javascript">
    	var obj = api.data.foods;
    	var quantum = api.data.quan;
    	
        $(function () {
			for(var i=0;i<quantum.length;i++){
				
				$("#quan").append('&nbsp&nbsp<lebal>'+(quantum[i][0]==1?"早点":"晚点")+'：</lebal>&nbsp&nbsp<span>('+quantum[i][1]+')</span>');
			} 
			$("#form1").initForm({    //参数设置示例
				success: function (response) {
					if(response.success){
						top.$.notice("保存成功！",3);
						//api.data.window.Qm.refreshGrid();
						api.close();
					}
					else{
						$.ligerDialog.error("操作失败！<br/>" + response.msg);
					}
				}
			});
		});
    </script>
</head>
<body>
<div></div>
<form id="form1" action="dining/foodOrder/saveOrder.do" method="post">
    <input type="hidden" id="id" name="id">
    <input type="hidden" name="order_type" id="order_type" value="0">
    <input type="hidden" name="fpo_id" id="fpo_id" value="${param.id}"/>
    <input type="hidden" name ="fnames" id="fnames"/>
    <table cellpadding="3" class="l-detail-table">
    	<tr>
			<td class="l-t-td-left">订餐人数情况：</td>
			<td id = "quan" class="l-t-td-right"></td>
		</tr>
		<tr>
	       <td class="l-t-td-left">菜品名称：</td>
	       <td class="l-t-td-right">
	       <input name="fpm_ids" 
					id="fpm_ids" 
					type="checkbox" 
					ltype="checkboxGroup"
					validate="{required:true}"
					ligerui="{
						readonly:true,
						data:obj
						}"
					/>
		  </td>
	    </tr>
		<tr>
			<td class="l-t-td-left">预计餐点：</td>
			<td class="l-t-td-right"><u:combo name="quantum"  code="food_quantum" validate="{required:true}"/></td>
		</tr>
		<tr>
			<td class="l-t-td-left">份数：</td>
			<td class="l-t-td-right"><input type="text" name="count" ltype="text" validate="{required:true,number:true,maxlength:2}" value=""/></td>
		</tr>
		<tr>
			<td class="l-t-td-left">期望菜品：</td>
			<td class="l-t-td-right"><textarea name="remark" rows="5" validate="{maxlength:512}"></textarea></td>
		</tr>
    </table>
</form>
</body>
</html>
