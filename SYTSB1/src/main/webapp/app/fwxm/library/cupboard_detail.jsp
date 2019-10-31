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
			afterParse:function(){
				if("${param.pageStatus}"!="add"){
					$("#qrcode").ligerGetComboBoxManager().setDisabled();
				}
			}
		});
	});
</script>
</head>
<body>
<form id="formObj" action="/cupboard/save.do" getAction="/cupboard/detail.do?id=${param.id}">
    <input type="hidden" id="id" name="id">
    <input name="status" id="status"  type="hidden" value="1"/>
    <c:if test="${param.pageStatus!='detail' }">
       	<input name="createBy" id="createBy" type="hidden" />
       	<input name="createTime" id="createTime" type="hidden"/>
    </c:if>
    <table cellpadding="3" class="l-detail-table">
        <tr>
            <td class="l-t-td-left">二维码：</td>
            <td class="l-t-td-right" colspan="3">
            	<input name="qrcode" id="qrcode" type="text" ltype="text" validate="{maxlength:128}" />
            </td>
        </tr>
        <tr>
        	<td class="l-t-td-left">描述：</td>
            <td class="l-t-td-right" colspan="3">
            	<textarea name="description" id="description" rows="2" ></textarea>
            </td>
        </tr>
        <c:if test="${param.pageStatus=='detail' }">
        <tr>
        	<td class="l-t-td-left">录入人：</td>
            <td class="l-t-td-right">
            	<input name="createBy" id="createBy" type="text" 
            		ltype="select" ligerui="{data:<u:dict sql='select id,name text from sys_user where 1=1'/>}" />
            </td>
            <td class="l-t-td-left">录入时间：</td>
            <td class="l-t-td-right">
            	<input name="createTime" id="createTime" 
            		type="text" ltype="date" ligerui="{format:'yyyy-MM-dd hh:mm:ss'}"/>
            </td>
        </tr>
        </c:if>
        
    </table>
</form>
</body>
</html>
