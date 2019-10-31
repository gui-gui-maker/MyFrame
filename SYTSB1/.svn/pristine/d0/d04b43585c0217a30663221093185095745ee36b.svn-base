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
                                nodes.push({ text:$("#name").val(), id:responseText.data.id, site:$("#fkSiteConfigId").val()});
                                if (parent.Treenode.getSelected())
                                    parent.Treenode.append(parent.Treenode.getSelected().target, nodes);
                                else
                                    parent.Treenode.append(null, nodes);
                            }
                            else {
                                parent.Treenode.update(parent.Treenode.getSelected().target, { text:$("#name").val()});
                            }
                            window.location = "/app/webmanage/infomanage/classtype_detail.jsp?stieType=detail&pageStatus=detail&classId=" + responseText.data.id;
                        })
                    } else {
                        $.ligerDialog.error('保存失败!' + responseText.msg)
                    }
                }
            });
        });
        function SelectImage(siteID) {
            top.$.dialog({
                width:800,
                height:450,
                //type:'donne',
                title:'资源管理器',
                content:'url:app/webmanage/FileManager/FileView.jsp?siteID=' + siteID,
                data:{"window":window},//把当前页面窗口传入下一个窗口，以便调用。
                ok:function (w) {
                    var iframe = this.iframe.contentWindow;
                    $("#img").val(iframe.returnValue);
                    return true;
                }, cancel:function (w) {//取消按钮函数
                    //alert("列表页面的全局对象："+w.xx)
                }
            });
        }
    </script>
</head>
<body>

<form name="formObj" id="formObj" method="post"
      action="infomanage/classtype/save.do" getAction="infomanage/classtype/detail.do?id=${param.classId}">
    <input name="id" type="hidden"/>
    <c:choose>
        <c:when test="${param.pageStatus=='add'}">
            <input type="hidden" name="classType.id" value="${param.pid}">
            <input type="hidden" name="fkSiteConfigId" id="fkSiteConfigId" value="${param.siteID}">
        </c:when>
        <c:otherwise>
            <input type="hidden" name="classType.id" ltype="hidden">
            <input type="hidden" name="fkSiteConfigId" id="fkSiteConfigId" ltype="hidden">
        </c:otherwise>
    </c:choose>
    <table border="0" cellpadding="3" cellspacing="0" width="" height="" align="" class="l-detail-table">
        <tr>
            <td class="l-t-td-left"> 栏目名称：</td>
            <td class="l-t-td-right" colspan="3"><input id="name" name="name" type="text" ltype='text'
                                                        validate="{required:true,maxlength:64}"/>
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left"> 栏目类型：</td>
            <td class="l-t-td-right"><input type="text" name="classGroup" ltype="select"
                                            validate="{required:true}"
                                            ligerui="{data:[{ text:'内容栏目', id:'content' }, { text:'投诉留言栏目', id:'guestbook'}]}"/>
            </td>

            <td class="l-t-td-left"> 栏目图片：</td>
            <td class="l-t-td-right">
                <c:choose>
                    <c:when test="${param.pageStatus=='detail'}">
                        <input name="img" id="img" type="text" ltype='text' validate="{required:false,disabled:true,maxlength:256}"
                                />
                    </c:when>
                    <c:otherwise>
                        <input name="img" id="img" type="text" ltype='text' validate="{required:false}"
                               onclick="SelectImage('${param.siteID}')"/>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left"> 目录名称：</td>
            <td class="l-t-td-right"><input name="dirName" type="text" ltype='text'
                                            validate="{required:true,maxlength:50}"/></td>
            <td class="l-t-td-left"> 数据是否需要审核：</td>
            <td class="l-t-td-right"><input type="radio" name="isAudit" ltype="radioGroup"
                                            validate="{required:false}"
                                            ligerui="{value:'on', data: [ { text:'是', id:'on' }, { text:'否', id:'off' }]}"/>
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left"> 每页图片记录条数：</td>
            <td class="l-t-td-right"><input name="listImgeNum" type="text" ltype='text'
                                            validate="{required:true,maxlength:4,number:true}" value="2"/>
            </td>
            <td class="l-t-td-left"> 每页显示记录条数：</td>
            <td class="l-t-td-right"><input name="listPageCount" type="text" ltype='text'
                                            validate="{required:true,maxlength:4,number:true}" value="10"/></td>
        </tr>
       <%-- <tr>
            <td class="l-t-td-left"> 相关信息条数：</td>
            <td class="l-t-td-right"><input name="detailMutualityNum" type="text" ltype='text'
                                            validate="{required:true}" value="5"/></td>
            <td class="l-t-td-left"> 本栏目最新信息条数：</td>
            <td class="l-t-td-right"><input name="detailRefreshNum" type="text" ltype='text'
                                            validate="{required:true}" value="5"/>
            </td>
        </tr>--%>
        <tr>
            <td class="l-t-td-left"> 列表是否为动态：</td>
            <td class="l-t-td-right"><input type="radio" name="listIsHtml" ltype="radioGroup"
                                            validate="{required:false}"
                                            ligerui="{disabled:true,value:'html', data: [ { text:'静态', id:'html' }, { text:'动态', id:'action' }]}"/>
            </td>
            <td class="l-t-td-left"> 详情是否为动态：</td>
            <td class="l-t-td-right"><input type="radio" name="detailIsHtml" ltype="radioGroup"
                                            validate="{required:false}"
                                            ligerui="{disabled:true,value:'html', data: [ { text:'静态', id:'html' }, { text:'动态', id:'action' }]}"/>
            </td>
        </tr>
        <%--<tr>
            <td class="l-t-td-left"> 列表导航文字：</td>
            <td class="l-t-td-right"><input name="navListStr" type="text" ltype='text'
                                            validate="{required:true}"/>
            <td class="l-t-td-left"> 详情导航文字：</td>
            <td class="l-t-td-right"><input name="navDetailStr" type="text" ltype='text'
                                            validate="{required:true}"/></td>
            </td>
        </tr>--%>
        <tr>
            <td class="l-t-td-left"> 列表页面排序方式：</td>
            <td class="l-t-td-right"><input type="text" name="listInfoSort" ltype="select"
                                            validate="{required:false}"
                                            ligerui="{disabled:true,value:'time_desc', data:[{ text:'时间倒序', id:'time_desc' }, { text:'下载次数', id:'down_num' }]}"/>
            </td>
            <td class="l-t-td-left"> 排序：</td>
            <td class="l-t-td-right"><input name="sort" type="text" ltype='text'
                                            validate="{required:true,maxlength:8}"/></td>
        </tr>
        <tr>
            <td class="l-t-td-left"> 其他链接：</td>
            <td class="l-t-td-right" colspan="3"><textarea name="otherUrl" rows="6" class="l-textarea"
                                                           validate="{required:false,maxlength:1024} "
                    ></textarea>
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left"> 说明：</td>
            <td class="l-t-td-right" colspan="3"><textarea name="remark" rows="10" class="l-textarea"
                                                           validate="{required:false,maxlength:4000} "
                    ></textarea>
            </td>
        </tr>

    </table>
</form>
</body>
</html>