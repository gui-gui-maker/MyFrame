<%@ page import="com.khnt.security.CurrentSessionUser" %>
<%@ page import="com.khnt.security.util.SecurityUtil" %><%--
  Created by IntelliJ IDEA.
  User: songxuemao
  Date: 16/5/25
  Time: 19:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://bpm.khnt.com" prefix="bpm" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
    CurrentSessionUser user = SecurityUtil.getSecurityUser();
%>
<html>
<head pageStatus="modify">
    <title></title>
    <%@ include file="/k/kui-base-form.jsp" %>
    <script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
    <script type="text/javascript" src="pub/bpm/js/util.js"></script>
    <script type="text/javascript">
        var serviceId = "${requestScope.serviceId}";//提交数据的id
        var activityId = "${requestScope.activityId}";//流程id
        var processId = "${requestScope.processId}";//
        var isTxje = false;
        <bpm:ifPer function="CH_LQ_YGJE" activityId="${requestScope.activityId}">isTxje = true;
        </bpm:ifPer>
        $(function () {
            var tbar = [
                {text: '审核不通过', id: 'shbtg', icon: 'del', click: shbtg},
                {text: '审核通过', id: 'shtg', icon: 'submit', click: shtg},
                {
                    text: '关闭', id: 'close', icon: 'cancel', click: function () {
                    api.close();
                }
                }];
            $("#form1").initForm({
                toolbar: tbar,
                success: function (resp) {
                    if ( resp.success ) {

                    } else {
                        $.ligerDialog.error(resp.msg);
                    }
                }, getSuccess: function (resp) {
                    $("#detailPart").setValues(resp.data);
                }
            });
            if ( !isTxje ) {
                $("#detailPart").transform("detail");
            }
        });


        function shooseOrg() {

        }

        function shtg() {
            if ( isTxje ) {
                if ( $("#lqzje").val() <= 0 ) {
                    $.ligerDialog.confirm("预估金额为0，请确认是否提交？", function (yes) {
                        if ( yes ) {
                            _shtg($("#lqzje").val())
                        }
                    });
                } else {
                    _shtg($("#lqzje").val());
                }
            } else {
                _shtg();
            }
        }

        function _shtg(lqzje) {
            $("body").mask("");
            $.ajax({
                url: "chlq/shtg.do",
                data: {
                    serviceId: serviceId,
                    activityId: activityId,
                    processId: processId,
                    ygzje: lqzje,
                },
                dataType: 'json',
                contentType: 'application/x-www-form-urlencoded; charset=utf-8',
                async: true,
                success: function (data) {
                    $("body").unmask("");
                    if ( data.success ) {
                        api.data.window.Qm.refreshGrid();
                        api.close();
                    } else {
                        $.ligerDialog.error(data.msg);
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    $("body").unmask("");
                }
            });
        }

        function shbtg() {
            $.ligerDialog.confirm('是否审核不通过？', function (yes) {
                if ( !yes ) {
                    return false;
                }
                $("body").mask("正在处理，请稍后！");
                $.ajax({
                    url: "chlq/shbtg.do",
                    data: {
                        serviceId: serviceId,
                        activityId: activityId,
                        processId: processId,
                    },
                    dataType: 'json',
                    contentType: 'application/x-www-form-urlencoded; charset=utf-8',
                    type: "post",
                    async: true,
                    success: function (data) {
                        $("body").unmask("");
                        if ( data.success ) {
                            api.data.window.Qm.refreshGrid();
                            api.close();
                        } else {
                            $.ligerDialog.error(data.msg);
                        }
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        $("body").unmask("");
                    }
                });
            });
        }
    </script>
</head>
<body>
<form name="form1" id="form1" method="post" action="chlq/savechlq.do"
      getAction="chlq/detail.do?id=${serviceId}">
    <fieldset class="l-fieldset">
        <legend class="l-legend">
            <div>领取单</div>
        </legend>
        <table class="l-detail-table" id="detailPart">
            <input type="hidden" name="id"/>
            <input type="hidden" name="lqId" value="<%=user.getId()%>"/>
            <input type="hidden" name="lqOrgId" value="<%=user.getDepartment().getId()%>"/>
            <input type="hidden" name="lqUnitId" value="<%=user.getUnit().getId()%>"/>
            <input type="hidden" id="blqbmId" name="blqbmId" value="100046"/>
            <input type="hidden" name="status"/>
            <tr>
                <td class="l-t-td-left">领取部门：</td>
                <td class="l-t-td-right"><input id="lqOrgName" name="lqOrgName" type="text" ltype="text"
                                                ligerui="{disabled:true}"
                                                value="<%=user.getDepartment().getOrgName()%>"/></td>
                <td class="l-t-td-left">领取人：</td>
                <td class="l-t-td-right"><input id="lqName" name="lqName" type="text" ltype="text"
                                                ligerui="{disabled:true}"
                                                value="<%=user.getName()%>"/>
                </td>
            </tr>
            <tr>
                <td class="l-t-td-left">领取时间：</td>
                <td class="l-t-td-right"><input id="lqTime" name="lqTime" type="text" ltype="text"
                                                ligerui="{disabled:true}"/></td>
                <td class="l-t-td-left">存货来源：</td>
                <td class="l-t-td-right"><input id="blqbm" name="blqbm" type="text" ltype="text" value="保障部"
                                                ligerui="{disabled:true}"
                                                ligerui="{iconItems:[{icon:'org',click:shooseOrg}]}"/></td>
            </tr>
            <tr>
                <td class="l-t-td-left">部门负责人：</td>
                <td class="l-t-td-right"><input name="bmfzr" ligerui="{disabled:true}" type="text" ltype="text"
                                                ligerui="{maxlength:128}"/>
                </td>
                <td class="l-t-td-left">物品预估总金额：</td>
                <td class="l-t-td-right"><input id="lqzje" name="lqzje" type="text" ltype="spinner"
                                                ligerui="{value:'',suffix:'元',minValue:0,type:'float'}"/>
                </td>
            </tr>
            <tr>
                <td class="l-t-td-left">领取用途：</td>
                <td class="l-t-td-right" colspan="3"><textarea rows="3" id="lqRemark" name="lqRemark" type="text"
                                                               ltype="text" validate="{maxlength:1000}"
                                                               ligerui="{disabled:true}"></textarea></td>
            </tr>
            <tr>
                <td class="l-t-td-left">领取物品：</td>
                <td class="l-t-td-right" colspan="3"><textarea rows="3" id="lqwp" name="lqwp" type="text" ltype="text"
                                                               validate="{maxlength:1000}"
                                                               ligerui="{disabled:true}"></textarea></td>
            </tr>
        </table>
    </fieldset>
</form>
</body>
</html>