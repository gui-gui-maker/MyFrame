<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
    <%@include file="/k/kui-base-form.jsp" %>
</head>
<body>
<form name="formObj" id="formObj" method="post"
      action="webconfig/style/save.do" getAction="webconfig/style/detail.do?id=${param.id}">
    <input name="id" type="hidden" value="${param.id}"/>
    <input type="hidden" name="fkSiteConfigId" value="${param.siteId}" ltype='hidden'>
    <table border="0" cellpadding="3" cellspacing="0" width="" height="" align="" class="l-detail-table">
        <tr>
            <td class="l-t-td-left">风格名称：</td>
            <td class="l-t-td-right"><input name="styleName" type="text" ltype='text'
                                            validate="{required:true,maxlength:64}"/>
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left">首页模板：</td>
            <td class="l-t-td-right"><input type="text" name="fkIndexTemp-txt" id="fkIndexTemp-txt" ltype="select" validate="{required:true}"
                                            ligerui="{ valueFieldID:'fkIndexTemp',url:'webconfig/style/getIndexTemp.do?styleId=${param.id}' }"/>
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left">搜索模板：</td>
            <td class="l-t-td-right"><input type="text" name="fkSearchTemp-txt" id="fkSearchTemp-txt" ltype="select" validate="{required:true}"
                                            ligerui="{ valueFieldID:'fkSearchTemp',url:'webconfig/style/getSearchTemp.do?styleId=${param.id}' }"/>
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left">备注:</td>
            <td class="l-t-td-right"><textarea name="remark" rows="5" class="l-textarea"
                                               ltype="textarea" validate="{required:false,maxlength:4000}"></textarea>
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left">是否默认风格：</td>
            <td class="l-t-td-right"><input id="isDefault" type="checkbox" name="isDefault" value="y"/>
                <label for="isDefault"></label>
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left">排序：</td>
            <td class="l-t-td-right"><input name="sort" type="text" ltype='text'
                                            validate="{required:true,maxlength:8}"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
