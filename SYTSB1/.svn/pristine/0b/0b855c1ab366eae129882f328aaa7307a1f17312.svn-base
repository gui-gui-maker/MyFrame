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
<form id="formObj" action="com/rt/printPdfTask/saveTask.do" getAction="com/rt/printPdfTask/detail.do?id=${param.id}">
    <input type="hidden" id="id" name="id">
    <table cellpadding="3" class="l-detail-table">
        <tr>
            <td class="l-t-td-left">报告ID：</td>
            <td class="l-t-td-right">
            	<input name="fkInspectionInfoId" id="fkInspectionInfoId" type="text" ltype="text" validate="{required:true,maxlength:32}" />
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left">报告编号：</td>
            <td class="l-t-td-right">
            	<input name="reportSn" id="reportSn" type="text" ltype="text" validate="{required:true,maxlength:100}" />
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left">报告类型：</td>
            <td class="l-t-td-right">
            	<input name="reportType" id="reportType" type="text" ltype="text" validate="{required:false,maxlength:100}" />
            </td>
         </tr>
        <tr>
            <td class="l-t-td-left">报告模板ID：</td>
            <td class="l-t-td-right">
            	<input name="reportTplId" id="reportTplId" type="text" ltype="text" validate="{required:true,maxlength:32}" />
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left">报告模板编号：</td>
            <td class="l-t-td-right">
            	<input name="reportTplNo" id="reportTplNo" type="text" ltype="text" validate="{required:false,maxlength:100}" />
            </td>
         </tr>
        <tr>
            <td class="l-t-td-left">报告附件ID：</td>
            <td class="l-t-td-right">
            	<input name="pdfAtt" id="pdfAtt" type="text" ltype="text" validate="{required:false,maxlength:32}" />
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left">报告保存地址：</td>
            <td class="l-t-td-right">
            	<input name="pdfPath" id="pdfPath" type="text" ltype="text" validate="{required:false,maxlength:200}" />
            </td>
         </tr>
        <tr>
            <td class="l-t-td-left">创建时间：</td>
            <td class="l-t-td-right">
            	<input name="createTime" id="createTime" type="text" ltype="text" validate="{required:false,maxlength:7}" />
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left">任务状态：</td>
            <td class="l-t-td-right">
            	<input name="status" id="status" type="text" ltype="text" validate="{required:false,maxlength:20}" />
            </td>
         </tr>
        <tr>
            <td class="l-t-td-left">打印时间：</td>
            <td class="l-t-td-right">
            	<input name="printTime" id="printTime" type="text" ltype="text" validate="{required:false,maxlength:7}" />
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left">尝试次数：</td>
            <td class="l-t-td-right">
            	<input name="tryCount" id="tryCount" type="text" ltype="number" validate="{required:false,maxlength:22}" />
            </td>
         </tr>
        <tr>
            <td class="l-t-td-left">备注：</td>
            <td class="l-t-td-right">
            	<input name="remark" id="remark" type="text" ltype="text" validate="{required:false,maxlength:2000}" />
            </td>
        </tr>
        <tr>
        </tr>
    </table>
</form>
</body>
</html>
