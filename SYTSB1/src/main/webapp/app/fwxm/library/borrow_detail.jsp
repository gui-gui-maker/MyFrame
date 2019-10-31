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
<form id="formObj" action="lib/borrow/save.do" getAction="lib/borrow/detail.do?id=${param.id}">
    <input type="hidden" id="id" name="id">
    <input type="hidden" name="putOnShelf" id="putOnShelf"  />
    <input type="hidden" name="bookId" id="bookId"  />
    <input type="hidden" name="bookQrcode" id="bookQrcode"  />
    <table cellpadding="3" class="l-detail-table">
        <tr>
            <td class="l-t-td-left">书名：</td>
            <td class="l-t-td-right">
            	<input name="bookName" id="bookName" type="text" ltype="text" />
            </td>
            <td class="l-t-td-left">借阅人：</td>
            <td class="l-t-td-right">
            	<input name="borrowedMan" id="borrowedMan" type="text" ltype="select" 
            		ligerui="{data:<u:dict sql='select id,name text from sys_user where 1=1'/>}"/>
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left">期限：</td>
            <td class="l-t-td-right">
            	<input name="timeLimit" id="timeLimit" type="text" 
            		ltype="number" validate="{number:true,maxlength:22}" />
            </td>
            <td class="l-t-td-left">借阅时间：</td>
            <td class="l-t-td-right">
            	<input name="borrowedTime" id="borrowedTime" type="text" 
            		ltype="date" ligerui="{format:'yyyy-MM-dd hh:mm:ss'}" />
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left">书架（下）：</td>
            <td class="l-t-td-right">
            	<input name="pullOffShelf" id="pullOffShelf" type="text" 
            		ltype="text" validate="{maxlength:128}" />
            </td>
            <td class="l-t-td-left">借出人：</td>
            <td class="l-t-td-right">
            	<input name="borrowRecordBy" id="borrowRecordBy" type="text" ltype="select"
            		ligerui="{data:<u:dict sql='select id,name text from sys_user where 1=1'/>}"/>
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left">归还人：</td>
            <td class="l-t-td-right">
            	<input name="returnedMan" id="returnedMan" type="text" ltype="select"
            		ligerui="{data:<u:dict sql='select id,name text from sys_user where 1=1'/>}"/>
            </td>
            <td class="l-t-td-left">归还时间：</td>
            <td class="l-t-td-right">
            	<input name="returnedTime" id="returnedTime" type="text" 
            		ltype="date" ligerui="{format:'yyyy-MM-dd hh:mm:ss'}" />
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left">接收人：</td>
            <td class="l-t-td-right">
            	<input name="returnRecordBy" id="returnRecordBy" type="text" ltype="select"
            		ligerui="{data:<u:dict sql='select id,name text from sys_user where 1=1'/>}" />
            </td>
            <td class="l-t-td-left">是否发送信息：</td>
            <td class="l-t-td-right">
            	<input name="isSendMail" id="isSendMail" type="text" 
            		ltype="select" ligerui="{data:[{id:'0',text:'否'},{id:'1',text:'是'}]}" />
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left">备注：</td>
            <td class="l-t-td-right" colspan="3">
            	<textarea name="remarks" id="remarks" rows="2" ltype="text" validate="{maxlength:512}" ></textarea>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
