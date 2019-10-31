<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head pageStatus="${param.status}">
    <title>公开招聘面试资格审核</title>
    <!-- 每个页面引入，页面编码、引入js，页面标题 -->
    <%@include file="/k/kui-base-form.jsp" %>
    <script type="text/javascript">
        var status='${param.status}';
        $(function () {

            $("#formInterview").initForm({
                toolbar:[
                    { text:'保存', click:function(){saveInterview();}, icon:'save'},
                    { text:'关闭', click:function(){api.close();}, icon:'delete'}
                ],
                toolbarPosition:'bottom',
            	success : function(responseText) {//处理成功
                     if (responseText.success) {
                         top.$.dialog.notice({content:'操作成功！'});
                         api.data.window.submitAction();
                         api.close();
                     } else {
                         $.ligerDialog.error(responseText.msg)
                     }
                 }
            });
        });
        //发布信息保存
        function saveInterview(obj){
            $('#formInterview').submit();
        }
    </script>
</head>
<body>
<form id="formInterview" action="app/zp/jltd/saveInterviewPayment.do?status=29"  >
    <input type="hidden" name="id" value="${zpJltd.id}"/>
    <fieldset class="l-fieldset">
        <legend class="l-legend">
            <div>报考基本信息</div>
        </legend>
        <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
            <tr>
                <td class="l-t-td-left">姓名:</td>
                <td class="l-t-td-right">
                    ${zpJlxx.name}
                </td>
                <td class="l-t-td-left">身份证号码:</td>
                <td class="l-t-td-right">${zpJlxx.certificatesNum}
                </td>
            </tr>

            <tr>
                <td class="l-t-td-left">投递岗位:</td>
                <td class="l-t-td-right" colspan="3">
                    ${zpXqxx.gwNum}-${zpXqxx.gwName}-${zpXqxx.depName}-${zpXqxx.orgName}
                </td>

            </tr>


        </table>
    </fieldset>

    <fieldset class="l-fieldset">
        <legend class="l-legend">
            <div>审核情况</div>
        </legend>
        <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
            <tr>
                <td class="l-t-td-left">审核是否通过:</td>
                <td class="l-t-td-right" colspan="3">
                        <c:if test="${zpJltd.sign=='7'}">未审核
                        </c:if>
                        <c:if test="${zpJltd.sign=='8'}">审核通过</c:if>
                        <c:if test="${zpJltd.sign=='9'}">审核不通过</c:if>
                </td>

            </tr>


        </table>
    </fieldset>
    <fieldset id="notPass" class="l-fieldset" <c:if test="${zpJltd.sign!='9'}">style="display: none"</c:if>>
        <legend class="l-legend">
            <div>不通过原因</div>
        </legend>
        <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
            <tr>
                <td class="l-t-td-left">不通过原因:</td>
                <td class="l-t-td-right" colspan="3">
                    <c:if test="${param.status!='detail'}">
                        <textarea name="bonusReason" id="bonusReason" title="不通过原因" cols="70" rows="3" class="l-textarea"
                                  style="width:100%" validate="{required:true}"  >/</textarea>
                    </c:if>
                    <c:if test="${param.status=='detail'}">
                        ${bonusReason}
                    </c:if>
                </td>

            </tr>
        </table>
    </fieldset>

    <fieldset id="assign" class="l-fieldset" <c:if test="${zpJltd.sign=='7' and param.status=='detail' }">style="display: none"</c:if><c:if test="${zpJltd.sign=='9'}">style="display: none"</c:if>>
        <legend class="l-legend">
            <div>缴费金额</div>
        </legend>
        <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
            <tr>
                <td class="l-t-td-left">缴费金额:</td>
                <td class="l-t-td-right">
                    <c:if test="${param.status!='detail'}">
                        <input name="interviewMoney" id="interviewMoney" type="text" ltype="text" value="${system.assessMoney}" ligerui="{disabled:true}" />
                    </c:if>
                    <c:if test="${param.status=='detail'}">
                        ${examRoomIdtext}
                    </c:if>

                </td>
            </tr>
        </table>
    </fieldset>
</form>
</body>
</html>
