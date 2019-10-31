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
			afterParse:function(res){
				if("detail"!="${param.pageStatus}"){
					$("#qrcode").ligerGetTextBoxManager().setDisabled();
					$("#currentPosition").ligerGetTextBoxManager().setDisabled();
					$("#status").ligerGetComboBoxManager().setDisabled();
					$("#createBy").ligerGetComboBoxManager().setDisabled();
					$("#receiveBy").ligerGetComboBoxManager().setDisabled();
					$("#invalidBy").ligerGetComboBoxManager().setDisabled();
					$("#invalidReason").ligerGetTextBoxManager().setDisabled();
					$("#createTime").ligerGetDateEditorManager().setDisabled();
					$("#receiveTime").ligerGetDateEditorManager().setDisabled();
					$("#invalidTime").ligerGetDateEditorManager().setDisabled();
				}
				if("add"=="${param.pageStatus}"){
					$("#qrcode").ligerGetTextBoxManager().setValue("不填二维码自动生成");
				}
			}
		});
	});
	
</script>
</head>
<body>
<form id="formObj" action="lib/book/save.do" getAction="lib/book/detail.do?id=${param.id}">
    <input type="hidden" id="id" name="id">
    <input type="hidden" id="receiveRecordBy" name="receiveRecordBy">
    <input name="identifier" id="identifier" type="hidden" value="${param.identifier}"/>
    
    <table cellpadding="3" class="l-detail-table">
    	<c:if test="${param.pageStatus == 'add' }">
	        <tr>
	        	<td class="l-t-td-left">请输入本数：</td>
	            <td class="l-t-td-right">
	            	<input name="numb" id="numb" type="text" ltype="text"  value="1"
	            		validate="{digits:true,required:true,maxlength:2}"/>
	            </td>
	        </tr>
        </c:if>
        <tr>
            <td class="l-t-td-left">书名：</td>
            <td class="l-t-td-right">
            	<input name="name" id="name" type="text" ltype="text" validate="{required:true,maxlength:512}" />
            </td>
            <td class="l-t-td-left">二维码：</td>
            <td class="l-t-td-right">
            	<input name="qrcode" id="qrcode" type="text" ltype="text" />
            </td>
            
        </tr>
        <tr>
        	<td class="l-t-td-left">种类：</td>
            <td class="l-t-td-right">
            	<input name="category" id="category" type="text" ltype="select" 
            		validate="{required:true}" ligerui="{data:<u:dict code='lib_category'/>}"/>
            </td>
            <td class="l-t-td-left">类型：</td>
            <td class="l-t-td-right">
            	<input name="content" id="content" type="text" ltype="select" validate="{required:true}"
            		ligerui="{data:<u:dict code='lib_category2'/>}"/>
            </td>
        </tr>
        <tr>
	        <td class="l-t-td-left">书架：</td>
            <td class="l-t-td-right">
            	<input name="currentPosition" id="currentPosition" type="text" ltype="text" validate="{maxlength:64}" />
            </td>
            <td class="l-t-td-left">状态：</td>
            <td class="l-t-td-right">
            	<input name="status" id="status" type="text" ltype="select" ligerui="{data:<u:dict code='book_status'/>}" />
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left">录入人：</td>
            <td class="l-t-td-right">
            	<input name="createBy" id="createBy" type="text" ltype="select" 
            		ligerui="{data:<u:dict sql='select id,name text from sys_user where 1=1'/>}"/>
            </td>
            <td class="l-t-td-left">录入时间：</td>
            <td class="l-t-td-right">
            	<input name="createTime" id="createTime" type="text" ltype="date" 
            		ligerui="{format:'yyyy-MM-dd hh:mm:ss'}"/>
            </td>
        </tr>
        <tr>
        	<td class="l-t-td-left">领取人：</td>
            <td class="l-t-td-right">
            	<input name="receiveBy" id="receiveBy" type="text" 
            		ltype="select" ligerui="{data:<u:dict sql='select id,name text from sys_user where 1=1'/>}" />
            </td>
            <td class="l-t-td-left">领取时间：</td>
            <td class="l-t-td-right">
            	<input name="receiveTime" id="receiveTime" type="text" ltype="date" 
            		ligerui="{format:'yyyy-MM-dd hh:mm:ss'}"  />
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left">作废人：</td>
            <td class="l-t-td-right">
            	<input name="invalidBy" id="invalidBy" type="text" ltype="select" ligerui="{data:<u:dict sql='select id,name text from sys_user where 1=1'/>}"  />
            </td>
            <td class="l-t-td-left">作废时间：</td>
            <td class="l-t-td-right">
            	<input name="invalidTime" id="invalidTime" type="text" ltype="date" ligerui="{format:'yyyy-MM-dd hh:mm:ss'}"  />
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left">作废原因：</td>
            <td class="l-t-td-right" colspan="3">
            	<textarea name="invalidReason" id="invalidReason" rows="2"></textarea>
            </td>
        </tr>
        
    </table>
</form>
</body>
</html>
