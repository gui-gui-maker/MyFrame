<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://bpm.khnt.com" prefix="bpm" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
    <title></title>
    <script type="text/javascript">
        function chooseEmp() {
            /** 当事人选择 */
            winOpen({
                width: 900,
                height: 450,
                lock: true,
                parent: api,
                title: "选择当事人",
                content: 'url:app/humanresources/employee_choose_list.jsp',
                cancel: true,
                ok: function(){
                    var p = this.iframe.contentWindow.getSelectedPerson();
                    console.log(p);
                    if(!p){
                        top.$.notice("请选当事人！", 3, "k/kui/images/icons/dialog/32X32/hits.png");
                        return false;
                    }
                    $("#fkEmployeeId_positionTitle").val(p.id);
                    $("#signedMan").val(p.name);

                }
            });
        }
    </script>
</head>
<body>

<form id="form_contract" action="positionTitleAction/savePositionTitle.do?empId=${param.id}&renew=${param.renew}"
      getAction="positionTitleAction/detailPositionTitle.do?empId=${param.id}">
    <input type="hidden" id="conId" name="id"/>
    <input type="hidden" id="fkEmployeeId_positionTitle" name="fkEmployeeId"/>
    <table border="1" cellpadding="3" cellspacing="0" width=""
           class="l-detail-table">
        <tr>
            <td class="l-t-td-left">当事人</td>
            <td class="l-t-td-right" colspan="3">
                <input id="signedMan" type="text" ltype="text" name="signedMan" readonly="readonly"
                       ligerui="{iconItems:[{icon:'user',click:chooseEmp}]}"/></td>
        </tr>
        <tr>
            <td class="l-t-td-left">职称类型</td>
            <td class="l-t-td-right" colspan="3"><u:combo name="positionTitleType" code="RLZY_RYZC"
                                                          validate="required:true"></u:combo></td>
        </tr>
        <tr>
            <td class="l-t-td-left">聘用开始日期</td>
            <td class="l-t-td-right"><input type="text" ltype="date" name="positionTitleStartDate"/></td>
            <td class="l-t-td-left">聘用结束日期</td>
            <td class="l-t-td-right"><input type="text" ltype="date" name="positionTitleStopDate"/></td>
        </tr>
        <tr>
            <td class="l-t-td-left">试用开始日期</td>
            <td class="l-t-td-right"><input type="text" ltype="date" name="trialStartDate"/></td>
            <td class="l-t-td-left">试用终止日期</td>
            <td class="l-t-td-right"><input type="text" ltype="date" name="trialStopDate"/></td>
        </tr>
        <!-- <tr>
            <td class="l-t-td-left">试用工资</td>
            <td class="l-t-td-right"><input type="text" ltype="text" name="trialMoney" validate="{required:true}" /></td>
            <td class="l-t-td-left">转正工资</td>
            <td class="l-t-td-right"><input type="text" ltype="text"  name="positiveMoney" validate="{required:true}"/></td>
        </tr> -->
        <tr height="80px">
            <td class="l-t-td-left">聘书文档</td>
            <td class="l-t-td-right" colspan="3">
                <input name="documentName" type="hidden" id="documentName" validate="{maxlength:1000}"/>
                <input name="documentDoc" type="hidden" id="documentDoc" validate="{maxlength:1000}"/>
                <c:if test='${param.pageStatus!="detail" }'>
                    <p id="procufilesDIV">
                        <a class="l-button" id="procufilesBtn">
                            <span class="l-button-main"><span class="l-button-text">选择文件</span></span>
                        </a>
                    </p>
                </c:if>
                <div class="l-upload-ok-list">
                    <ul id="procufiles3"></ul>
                </div>
            </td>
        </tr>
    </table>

</form>
</body>
</html>