<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
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
						$.ligerDialog.error("操作失败！<br/>" + response.data);
					}
				}
			});
		});
    </script>
</head>
<body>
<form id="form1" action="dining/cardActive/push.do" getAction="dining/cardActive/getActive.do?id=${param.id}">
    <input type="hidden" id="id" name="id">
    <table cellpadding="3" class="l-detail-table">
        <tr>
            <td class="l-t-td-left">卡号：</td>
            <td class="l-t-td-right"><input name="card_no" type="text" ltype='text' validate="{required:true,maxlength:64}"/></td>
        </tr>
        <c:if test='${param.status=="add" }'>
		    <input name="active_type" type="hidden" value="${param.act}"/>
        </c:if>
        <c:if test='${param.status=="detail" }'>
	         <tr>
	            <td class="l-t-td-left">类型：</td>
	            <td class="l-t-td-right"><u:combo name="active_type" code='food_card_active_type'/></td>
	         </tr>
        	 <tr>
	            <td class="l-t-td-left">时间：</td>
	            <td class="l-t-td-right"><input name="active_time" type="text" ltype='date'/></td>
	         </tr>
	         <tr>
	            <td class="l-t-td-left">扣卡次数：</td>
	            <td class="l-t-td-right"><input name="coun" type="text" ltype='text'/></td>
	         </tr>
        </c:if>
    </table>
</form>
</body>
</html>
