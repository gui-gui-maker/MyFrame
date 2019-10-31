<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
<title>详情页面</title>
<%@include file="/k/kui-base-form.jsp" %>
<script type="text/javascript">
	$(function () {
		$("#formObj").initForm({    //参数设置示例
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
<form id="formObj" action="com/rt/dir/save.do" getAction="com/rt/dir/detail.do?id=${param.id}">
    <input type="hidden" id="id" name="id">
    <table cellpadding="3" class="l-detail-table">
        <tr>
            <td class="l-t-td-left">报表代码：</td>
            <td class="l-t-td-right">
            	<input name="rtCode" id="rtCode" type="text" ltype="text" validate="{required:true,maxlength:50}" />
            </td>
            <td class="l-t-td-left">目录数据：</td>
            <td class="l-t-td-right">
            	<input name="rtDirJson" id="rtDirJson" type="text" ltype="text" validate="{required:true,maxlength:2000}" />
            </td>
        </tr>
        <tr>
        </tr>
    </table>
</form>
</body>
</html>
