<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
    <%@include file="/k/kui-base-form.jsp" %>
</head>
<body>
        <form name="formObj" id="formObj" method="post"
              action="tempmanage/temp/saveTemp.do"  getAction="tempmanage/temp/detail.do?id=${param.id}">
            <input name="id" type="hidden"/>
            <input name="tempType" type="hidden"  validate="{required:true}" value="${param.tempType}"/>
            <input name="fkStyleId" type="hidden"  validate="{required:true}" value="${param.styleId}"/>
            <table border="0" cellpadding="3" cellspacing="0" width="" height="" align="" class="l-detail-table">
                <tr>
                    <td class="l-t-td-left"> 模板名称：</td>
                    <td class="l-t-td-right"><input name="name" type="text" ltype='text' validate="{required:true,maxlength:64}"/>
                    </td>
                    <td class="l-t-td-left"> 模板调用名称：</td>
                    <td class="l-t-td-right"><input name="callName" type="text" ltype='text' validate="{required:true,maxlength:32}"/>
                    </td>
                </tr>
                <tr>
                    <td class="l-t-td-left"> 模板内容:</td>
                    <td class="l-t-td-right" colspan="3"><textarea name="content" rows="28" class="l-textarea"
                                                                   validate="{required:true}"></textarea>
                    </td>
                </tr>

            </table>
        </form>
</body>
</html>