<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
    <title></title>
    <%@include file="/k/kui-base-form.jsp" %>
    <script type="text/javascript">
        $(function () {
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
		});
    </script>
</head>
<body>
<form id="form1" action="dining/foodCard/recharge.do" method="post">
    <input type="hidden" id="id" name="id" value="${param.id}"/>
    
    <table cellpadding="3" class="l-detail-table">
		<tr>
	       <td class="l-t-td-left">次数：</td>
	       <td class="l-t-td-right">
	       	<input name="addCount" type="text" ltype="text" value="0" validate="required:true"/>
		  </td>
	    </tr>
    </table>
</form>
</body>
</html>