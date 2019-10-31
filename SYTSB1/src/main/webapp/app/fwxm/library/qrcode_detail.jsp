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
			},
			getSuccess:function(res){
				if("modify"=='${param.pageStatus}'){
					$("#code").ligerGetTextBoxManager().setDisabled();
					$("#isPrint").ligerGetComboBoxManager().setDisabled();
					$("#printBy").ligerGetComboBoxManager().setDisabled();
					$("#reprintBy").ligerGetComboBoxManager().setDisabled();
					$("#createTime").ligerGetDateBoxManager().setDisabled();
					$("#printTime").ligerGetDateBoxManager().setDisabled();
					$("#reprintTime").ligerGetDateBoxManager().setDisabled();
				}
			}
		});
	});
</script>
</head>
<body>
<form id="formObj" action="lib/qrcode/save.do" getAction="lib/qrcode/detail.do?id=${param.id}">
    <input type="hidden" id="id" name="id">
    <input type="hidden" id="isDel" name="isDel">
	
    <table cellpadding="3" class="l-detail-table">
        <tr>
            <td class="l-t-td-left">二维码：</td>
            <td class="l-t-td-right">
            	<input name="code" id="code" type="text" ltype="text" />
            </td>
            <td class="l-t-td-left">类型：</td>
            <td class="l-t-td-right">
            	<input name="type" id="type" type="text" ltype="select" 
            		ligerui="{data:<u:dict code='lib_qrcode_type'/>}"/>
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left">创建时间：</td>
            <td class="l-t-td-right">
            	<input name="createTime" id="createTime" type="text" 
            		ltype="date" ligerui="{format:'yyyy-MM-dd hh:mm:ss'}"/>
            </td>
            <td class="l-t-td-left">是否打印：</td>
            <td class="l-t-td-right">
            	<input name="isPrint" id="isPrint" type="text" ltype="select" 
            		ligerui="{data:[{id:'0',text:'否'},{id:'1',text:'是'}]}"/>
            </td>
        </tr>
        <tr>
        	<td class="l-t-td-left">打印人：</td>
            <td class="l-t-td-right">
            	<input name="printBy" id="printBy" 
            		type="text" ltype="select" 
            		ligerui="{data:<u:dict sql="select id,name text from sys_user where 1=1"/>}"/>
            </td>
            <td class="l-t-td-left">打印时间：</td>
            <td class="l-t-td-right">
            	<input name="printTime" id="printTime" 
            		type="text" ltype="date" ligerui="{format:'yyyy-MM-dd hh:mm:ss'}" />
            </td>
        </tr> 
        <tr>
        	<td class="l-t-td-left">补打人：</td>
            <td class="l-t-td-right">
            	<input name="reprintBy" id="reprintBy" 
            		type="text" ltype="select" 
            		ligerui="{data:<u:dict sql="select id,name text from sys_user where 1=1"/>}"/>
            </td>
            <td class="l-t-td-left">补打时间：</td>
            <td class="l-t-td-right">
            	<input name="reprintTime" id="reprintTime" 
            		type="text" ltype="date" ligerui="{format:'yyyy-MM-dd hh:mm:ss'}" />
            </td>
        </tr>
    </table>
</form>
</body>
</html>
