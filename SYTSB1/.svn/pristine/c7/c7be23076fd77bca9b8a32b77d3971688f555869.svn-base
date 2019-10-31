<%@page import="com.khnt.utils.DateUtil"%>
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
						api.data.window.refreshGrid();
						api.close();
					}
					else{
						$.ligerDialog.error("保存失败！<br/>" + response.msg);
					}
				},getSuccess:function(res){
				}
			});
		});
    </script>
</head>
<body>
<form id="form1" action="pub/scheduleJob/updateCronTime.do">
    <input type="hidden"  name="id" value="${param.id}">
    <input type="hidden"  name="jobName" value="${param.jobName}">
    <input type="hidden"  name="jobGroup" value="${param.jobGroup}">
    <table cellpadding="3" class="l-detail-table">
        <tr>
            <td class="l-t-td-left">时间表达式：</td>
            <td class="l-t-td-right">
            	<input type="text" ltype="text" name="cronExpression" value="${param.cron}" validate="{required:true,maxlength:512}"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
