<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
    <%@include file="/k/kui-base-form.jsp" %>
    <script type="text/javascript">
        $(function () {
            $("#formObj").initForm({
                toolbarPosition:"top",
                success:function (responseText) {//处理成功
                    if (responseText.success) {
                        $.ligerDialog.success('保存成功', function () {
                            var nodes = [];
                            if ($("head").attr("pageStatus") == "add") {
                                nodes.push({ text:$("#name").val(), id:responseText.data.id});
                                if (parent.Treenode.getSelected())
                                    parent.Treenode.append(parent.Treenode.getSelected().target, nodes);
                                else
                                    parent.Treenode.append(null, nodes);
                            }
                            else {
                                parent.Treenode.update(parent.Treenode.getSelected().target, { text:$("#name").val()});
                            }
                            window.location = "/app/webmanage/webconfig/resource_detail.jsp?pageStatus=detail&resource=" + responseText.data.id;
                        })
                    } else {
                        $.ligerDialog.error('保存失败' + responseText)
                    }
                }
            });
        });
    </script>
</head>
<body>
<form name="formObj" id="formObj" method="post" action="webconfig/resourcetype/save.do"
      getAction="webconfig/resourcetype/detail.do?id=${param.resource}">
    <input name="id" type="hidden" ltype="hidden"/>
    <c:choose>
        <c:when test="${param.pageStatus=='add'}">
            <input name="fkSiteConfigId" type="hidden" ltype="hidden" value="${param.siteId}"/>
            <input type="hidden" name="resourceType.id" value="${param.pid}" ltype="hidden">
        </c:when>
        <c:otherwise>
            <input name="fkSiteConfigId" type="hidden" ltype="hidden" value=""/>
            <input type="hidden" name="resourceType.id" ltype="hidden">
        </c:otherwise>
    </c:choose>
    <table border="0" cellpadding="3" cellspacing="0" width="" height="" align="" class="l-detail-table">
        <tr>
            <td class="l-t-td-left"> 分类名称：</td>
            <td class="l-t-td-right"><input id="name" name="name" type="text" ltype='text'
                                            validate="{required:true,maxlength:32}"/>
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left"> 排序：</td>
            <td class="l-t-td-right"><input name="sort" type="text" ltype='text'
                                            validate="{required:true,maxlength:8}"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>