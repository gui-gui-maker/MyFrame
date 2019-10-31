<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
    <%@include file="/k/kui-base-form.jsp" %>
</head>
<body>
<form name="formObj" id="formObj" method="post"
      action="infomanage/guestbook/GuestbookSave.do"  getAction="infomanage/guestbook/getDetail.do?id=${param.id}">
    <input name="id" type="hidden"/>
    <input  name='fkClasstypeId' type='hidden'>
    <table border="0" cellpadding="3" cellspacing="0" width="" height="" align="" class="l-detail-table">
        <tr>
            <td class="l-t-td-left"> 标题：</td>
            <td class="l-t-td-right" colspan="3"><input name="userTitle" type="text" ltype='text' validate="{required:false}" ligerui="{disabled:true}"/>
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left"> 昵称：</td>
            <td class="l-t-td-right"><input name="userNickname" type="text" ltype='text' validate="{required:false}" ligerui="{disabled:true}"/>
            </td>
            <td class="l-t-td-left"> 姓名：</td>
            <td class="l-t-td-right"><input name="userName" type="text" ltype='text' validate="{required:false}" ligerui="{disabled:true}"/>
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left"> IP地址：</td>
            <td class="l-t-td-right"><input name="userIp" type="text" ltype='text' validate="{required:false}" ligerui="{disabled:true}"/>
            </td>
            <td class="l-t-td-left"> Email：</td>
            <td class="l-t-td-right"><input name="userEmail" type="text" ltype='text' validate="{required:false}" ligerui="{disabled:true}"/>
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left"> QQ：</td>
            <td class="l-t-td-right"><input name="userQq" type="text" ltype='text' validate="{required:false}" ligerui="{disabled:true}"/>
            </td>
            <td class="l-t-td-left"> 性别：</td>
            <td class="l-t-td-right"><input name="userSex" type="text" ltype='text' validate="{required:false}" ligerui="{disabled:true}"/>
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left"> 联系电话：</td>
            <td class="l-t-td-right"><input name="userTel" type="text" ltype='text' validate="{required:false}" ligerui="{disabled:true}"/>
            </td>
            <td class="l-t-td-left"> 时间：</td>
            <td class="l-t-td-right"><input type="text" id="userTime" name="userTime" ltype="date"
                                            ligerui="{ labelWidth: 100, labelAlign: 'left',disabled:true}"
                                            validate="{required:false}"
                    />
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left"> 内容:</td>
            <td class="l-t-td-right" colspan="3"><textarea name="userContent" rows="9" class="l-text-disabled" readonly="true"
                                                           validate="{required:false}" ligerui="{disabled:true}"></textarea>
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left"> 回复人：</td>
            <td class="l-t-td-right"><input name="replyOp" type="text" ltype='text' validate="{required:true}" value="${currentSessionUser.name}"/>
            </td>
            <td class="l-t-td-left"> 回复时间：</td>
            <td class="l-t-td-right"><input type="text" id="replyTime" name="replyTime" ltype="date"
                                            ligerui="{labelWidth: 100, labelAlign: 'left',initValue:<%=new Date().getTime()%>}"
                                            validate="{required:true}"
                    />
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left"> 回复内容:</td>
            <td class="l-t-td-right" colspan="3"><textarea name="replyContent" rows="9" class="l-textarea"
                                                           validate="{required:true}"></textarea>
            </td>
        </tr>
    </table>
</form>
</body>
</html>