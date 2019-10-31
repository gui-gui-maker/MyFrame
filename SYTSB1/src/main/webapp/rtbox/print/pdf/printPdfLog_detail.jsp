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
<form id="formObj" action="com/rt/printPdfLog/save.do" getAction="com/rt/printPdfLog/detail.do?id=${param.id}">
    <input type="hidden" id="id" name="id">
    <input type="hidden" id="fkTaskId" name="fkTaskId">
    <table cellpadding="3" class="l-detail-table">
        <tr>
            <td class="l-t-td-left">报告ID：</td>
            <td class="l-t-td-right">
            	<input name="fkInspectionId" id="fkInspectionId" type="text" ltype="text" validate="{required:true,maxlength:32}" />
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left">报告编号：</td>
            <td class="l-t-td-right">
            	<input name="reportSn" id="reportSn" type="text" ltype="text" validate="{required:true,maxlength:100}" />
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left">报告分页编号：</td>
            <td class="l-t-td-right">
            	<input name="pageNo" id="pageNo" type="text" ltype="text" validate="{required:true,maxlength:100}" />
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left">报告URL：</td>
            <td class="l-t-td-right">
            	<input name="pageUrl" id="pageUrl" type="text" ltype="text" validate="{required:true,maxlength:500}" />
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left">报告保存地址：</td>
            <td class="l-t-td-right">
            	<input name="pagePath" id="pagePath" type="text" ltype="text" validate="{required:true,maxlength:100}" />
            </td>
         </tr>
        <tr>
            <td class="l-t-td-left">打印结果：</td>
            <td class="l-t-td-right">
            	<input name="result" id="result" type="text" ltype="text" validate="{required:true,maxlength:20}" />
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left">错误类别：</td>
            <td class="l-t-td-right">
            	<input name="errorType" id="errorType" type="text" ltype="text" validate="{required:true,maxlength:20}" />
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left">打印日志：</td>
            <td class="l-t-td-right">
            	<input name="log" id="log" type="text" ltype="text" validate="{required:true,maxlength:4000}" />
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left">打印时间：</td>
            <td class="l-t-td-right">
            	<input name="printTime" id="printTime" type="text" ltype="text" validate="{required:true,maxlength:7}" />
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left">打印批次：</td>
            <td class="l-t-td-right">
            	<input name="printBatch" id="printBatch" type="text" ltype="text" validate="{required:true,maxlength:20}" />
            </td>
        </tr>
        <tr>
        </tr>
    </table>
</form>
</body>
</html>
