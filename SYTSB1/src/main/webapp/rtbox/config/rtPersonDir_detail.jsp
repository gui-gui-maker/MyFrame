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
<form id="formObj" action="com/rt/personDir/save.do" getAction="com/rt/personDir/detail.do?id=${param.id}">
    <input type="hidden" id="id" name="id">
    <table cellpadding="3" class="l-detail-table">
        <tr>
            <td class="l-t-td-left">业务数据ID：</td>
            <td class="l-t-td-right">
            	<input name="fkBusinessId" id="fkBusinessId" type="text" ltype="text" validate="{required:true,maxlength:32}" />
            </td>
            <td class="l-t-td-left">目录数据：</td>
            <td class="l-t-td-right">
            	<input name="rtDirJson" id="rtDirJson" type="text" ltype="text" validate="{required:true,maxlength:2000}" />
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left">报表代码：</td>
            <td class="l-t-td-right">
            	<input name="rtCode" id="rtCode" type="text" ltype="text" validate="{required:true,maxlength:50}" />
            </td>
            <td class="l-t-td-left">创建人：</td>
            <td class="l-t-td-right">
            	<input name="fkCreateUserId" id="fkCreateUserId" type="text" ltype="text" validate="{required:true,maxlength:32}" />
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left">创建时间：</td>
            <td class="l-t-td-right">
            	<input name="createTime" id="createTime" type="text" ltype="text" validate="{required:true,maxlength:7}" />
            </td>
        </tr>
    </table>
</form>
</body>
</html>
