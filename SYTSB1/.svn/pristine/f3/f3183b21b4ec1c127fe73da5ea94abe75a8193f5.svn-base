<%@ page import="com.khnt.rtbox.template.constant.*" %>
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
<form id="formObj" action="com/rt/template/save.do" getAction="com/rt/template/detail.do?id=${param.id}">
    <input type="hidden" id="id" name="id">
    <input name="status" id="status" type="hidden" value="<%=RtSign.STATUS_ENABLE%>"/>
    <input name="createdById" id="createdById" type="hidden" />
   	<input name="createdBy" id="createdBy" type="hidden" />
   	<input name="createdDate" id="createdDate" type="hidden" />
   	<input name="lastUpdById" id="lastUpdById" type="hidden" />
   	<input name="lastUpdBy" id="lastUpdBy" type="hidden" />
   	<input name="lastUpdDate" id="lastUpdDate" type="hidden" />
    <table cellpadding="3" class="l-detail-table">
        <tr>
            <td class="l-t-td-left">报表代码：</td>
            <td class="l-t-td-right">
            	<input name="rtCode" id="rtCode" type="text" ltype="text" validate="{required:true,maxlength:50}" placeholder="用来做调用的，请用非中文字符填写，如:106_zdxl" /></td>
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left">报表名称：</td>
            <td class="l-t-td-right">
            	<input name="rtName" id="rtName" type="text" ltype="text" validate="{required:true,maxlength:200}" />
            </td>
        </tr>
    </table>
</form>
</body>
</html>
