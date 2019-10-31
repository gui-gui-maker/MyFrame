<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
    <title>Account Detail</title>
    <%@include file="/k/kui-base-form.jsp" %>
    <script type="text/javascript">
        $(function () {
			$("#form1").initForm({    //参数设置示例
				success: function (response) {
					if(response.success){
						top.$.notice("保存成功！",3);
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
<form id="form1" action="dining/foodCardDayToDayAccount/save.do" method="post" getAction="dining/foodCardDayToDayAccount/detail.do?id=${param.id}">
    <input type="hidden" id="id" name="id">
    <table cellpadding="3" class="l-detail-table">
		<tr>
	       <td class="l-t-td-left">卡号：</td>
	       <td class="l-t-td-right"><input name="cardNo" type="text" ltype="text"  validate="{required:true}"/></td>
	    </tr>
		<tr>
	       <td class="l-t-td-left">原账户：</td>
	       <td class="l-t-td-right"><input name="oldCount" type="text"  ltype="text"  validate="{required:true,number:true}"/></td>
	    </tr>
		<tr>
	       <td class="l-t-td-left">加：</td>
	       <td class="l-t-td-right"><input name="countAdd" type="text" ltype="text"  validate="{required:true,number:true,}"/></td>
	    </tr>
		<tr>
			<td class="l-t-td-left">减：</td>
			<td class="l-t-td-right"><input name="countDecrease" type="text" ltype="text" validate="{required:true,number:true}" value=""/></td>
		</tr>
		<tr>
			<td class="l-t-td-left">摘要：</td>
			<td class="l-t-td-right"><input name="summary" type="text" ltype="text" validate="{required:true}" value=""/></td>
		</tr>
		<tr>
			<td class="l-t-td-left">时间：</td>
			<td class="l-t-td-right"><input name="actionTime" type="text" ltype="date" ligerUi="{format:'yyyy-MM-dd HH:mm:ss'}"/></td>
		</tr>
		<tr>
			<td class="l-t-td-left">现账户：</td>
			<td class="l-t-td-right"><input name="newCount" type="text" ltype="text" validate="{required:true,number:true}" value=""/></td>
		</tr>
		<tr>
			<td class="l-t-td-left">操作人：</td>
			<td class="l-t-td-right"><input name="operator" type="text" ltype="text" validate="{required:true}" value=""/></td>
		</tr>
    </table>
</form>
</body>
</html>
