<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head pageStatus="${param.pageStatus}">
    <!-- 每个页面引入，页面编码、引入js，页面标题 -->
    <%@include file="/k/kui-base-form.jsp" %>
    <script type="text/javascript">
        //jQuery页面载入事件
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
                            window.location = "/app/webmanage/webconfig/siteconfig_detail.jsp?pageStatus=detail&stieType=" + responseText.data.id;
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
<form name="formObj" id="formObj" method="post" action="webconfig/siteconfig/saveWebConfig.do"
      getAction="webconfig/siteconfig/detail.do?id=${param.stieType}">
    <input name="id" type="hidden" ltype="hidden"/>
    <c:choose>
        <c:when test="${param.pageStatus=='add'}">
            <input type="hidden" name="siteConfig.id" value="${param.pid}" ltype="hidden">
        </c:when>
        <c:otherwise>
            <input type="hidden" name="siteConfig.id" ltype="hidden">
        </c:otherwise>
    </c:choose>
    <table border="0" cellpadding="3" cellspacing="0" width="" height="" align="" class="l-detail-table">
        <tr>
            <td class="l-t-td-left"> 网站名称：</td>
            <td class="l-t-td-right" colspan="3"><input id="name" name="name" type="text" ltype='text'
                                                        validate="{required:true,maxlength:128}"/>
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left"> 网站标题名称：</td>
            <td class="l-t-td-right" colspan="3"><input name="titlename" type="text" ltype='text'
                                                        validate="{required:false,maxlength:128}"/>
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left"> 网站标题扩展：</td>
            <td class="l-t-td-right" colspan="3"><input name="titleexpand" type="text" ltype='text'
                                                        validate="{required:false,maxlength:256}"/>
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left"> 网页描述：</td>
            <td class="l-t-td-right" colspan="3"><textarea name="description" rows="4"
                                                           class="l-textarea" ltype="textarea"
                                                           validate="{required:false,maxlength:512}"
                    ></textarea>
            </td>
        </tr>
        <%--<tr>
            <td class="l-t-td-left"> 网站关键字：</td>
            <td class="l-t-td-right" colspan="3"><textarea name="keywords" rows="4"
                                                           class="l-textarea" ltype="textarea"
                                                           validate="{required:false}"
                    ></textarea>
            </td>
        </tr>--%>
        <%--<tr>
            <td class="l-t-td-left"> 网站域名：</td>
            <td class="l-t-td-right"><input name="domain" type="text" ltype='text'
                                            validate="{required:false}"/>
            </td>
            <td class="l-t-td-left"> 网站地址：</td>
            <td class="l-t-td-right"><input name="url" type="text" ltype='text'
                                            validate="{required:false}"/>
            </td>
        </tr>--%>
         <tr>
            <td class="l-t-td-left"> 网站构架：</td>
            <td class="l-t-td-right"><input type="radio" name="htmlAction" ltype="radioGroup"
                                            validate="{required:false}"
                                            ligerui="{disabled:true,value:'html', data: [ { text:'全静态', id:'html' }, { text:'全动态', id:'action' } ]}"/>
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left"> 留言审核开关：</td>
            <td class="l-t-td-right"><input type="radio" name="messageAuditOff" ltype="radioGroup"
                                            validate="{required:false}"
                                            ligerui="{value:'off', data: [ { text:'打开', id:'no' }, { text:'关闭', id:'off' }]}"/>
            </td>
            <td class="l-t-td-left"> 全站评论开关：</td>
            <td class="l-t-td-right">
                <input type="radio" name="reviewOff" ltype="radioGroup" validate="{required:false}"
                       ligerui="{value:'off', data: [ { text:'打开', id:'no' }, { text:'关闭', id:'off' }]}"/>
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left"> 审核评论开关：</td>
            <td class="l-t-td-right"><input type="radio" name="auditOff" ltype="radioGroup"
                                            validate="{required:false}"
                                            ligerui="{value:'off', data: [ { text:'打开', id:'no' }, { text:'关闭', id:'off' }]}"/>
                </td>
            <%-- <td class="l-t-td-left"> 用户名关键字：</td>
<td class="l-t-td-right"><input name="usercorename" type="text" ltype='text'
            validate="{required:false}"/></td>--%>
        </tr>
        <tr>
            <td class="l-t-td-left"> 内容页面评论条数：</td>
            <td class="l-t-td-right"><input name="reviewdetailcount" type="text" ltype='text'
                                            validate="{required:false,maxlength:4,number:true}" value="10"/></td>
            <td class="l-t-td-left"> 列表评论显示条数：</td>
            <td class="l-t-td-right"><input name="reviewlistcount" type="text" ltype='text'
                                            validate="{required:false,maxlength:4,number:true}" value="10"/></td>
        </tr>
        <tr>
            <td class="l-t-td-left"> 静态页面存放目录：</td>
            <td class="l-t-td-right"><input name="siteHtmlDir" type="text" ltype='text'
                                            validate="{required:false,maxlength:32}"/></td>
            <td class="l-t-td-left"> 排序：</td>
            <td class="l-t-td-right"><input type="text" name="sort" ltype="text"
                                            validate="{required:true,maxlength:16}"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>