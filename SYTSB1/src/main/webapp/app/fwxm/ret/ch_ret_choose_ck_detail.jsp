<%@ page import="com.khnt.security.CurrentSessionUser" %>
<%@ page import="com.khnt.security.util.SecurityUtil" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: songxuemao
  Date: 16/5/25
  Time: 19:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
    CurrentSessionUser user = SecurityUtil.getSecurityUser();
    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String date = sf.format(new Date());
%>
<html>
<head pageStatus="${param.status}">
    <title>出库详情</title>
    <%@ include file="/k/kui-base-form.jsp" %>
    <script type="text/javascript">
        var formData;
        $(function () {
            $("#form1").initForm({
                toolbar: [
                    {
                        text: "确认", icon: "check", click: choose
                    },
                    {
                        text: "取消", icon: "cancel", click: function () {
                        api.close();
                    }
                    }
                ],
                success: function (resp) {
                    if ( resp.success ) {

                    } else {
                        $.ligerDialog.error(resp.msg);
                    }
                },
                getSuccess: function (resp) {
                    if ( resp.success ) {
                        var Rows = [];
                        for (var i = 0; i < resp.data.goods.length; i++) {
                            Rows.push({
                                id: resp.data.goods[i]["goods"]["id"],
                                wpmc: resp.data.goods[i]["goods"]["wpmc"],
                                ggjxh: resp.data.goods[i]["goods"]["ggjxh"],
                                warehousing_num: resp.data.goods[i]["goods"]["warehousing_num"],
                                je: resp.data.goods[i]["goods"]["je"],
                                se: resp.data.goods[i]["goods"]["se"],
                                sl: resp.data.goods[i]["sl"],
                                bz: resp.data.goods[i]["bz"]
                            })
                        }
                        mainGrid.loadData({Rows: Rows});
                        formData = {
                            lqbm: resp.data["lqbm"],
                            lqr: resp.data["lqr"],
                            ckdbh: resp.data["ckdbh"],
                            id: resp.data["id"],
                            ckyjdh:resp.data["ckyjdh"],
                            ckyjtype:resp.data["ckyjtype"]
                        }
                        $("#form1").setValues(formData);
                    } else {
                        $.ligerDialog.error(resp.msg);
                    }
                }
            });
            initGrid();
        });
        var mainGrid;

        function initGrid() {
            var canEdit = '${param.status}' != 'detail';
            var columns = [{display: "id", name: "id", hide: true, align: "left"},
                {display: "存货名称", name: "wpmc", width: '20%', align: "center"},
                {
                    display: "规格及型号", name: "ggjxh", width: '20%', align: "center"
                },
                {display: "入库单号", name: "warehousing_num", width: '150', align: "left"},
                {display: "单价", name: "je", width: '10%', align: "right"},
                {
                    display: "数量",
                    name: "sl",
                    width: '5%',
                    align: "right",
                    type: 'int',
                    editor: {type: 'spinner', minValue: '1'},
                    min: 1,
                    required: true,
                    maxlength: 200
                },
                {display: "备注", name: "bz", width: '10%', align: "left", editor: {type: 'text'}, maxlength: 2000}
            ];
            mainGrid = $("#goodsGrid").ligerGrid({
                columns: columns,
                enabledEdit: false,
                rownumbers: true,
                frozenRownumbers: false,
                colDraggable: false,
                rowDraggable: false,
                isScroll: true,
                usePager: false,
                checkbox: true,
                data: {Rows: []},
            });
        }

        function choose() {
            var data = {};
            data["ckd"] = formData;
            data["Rows"] = mainGrid.getSelectedRows();
            api.close();
            api.data.callback(data);
        }
    </script>
    <style type="text/css" id="sStyle">
        .l-grid-row-cell-inner {
            height: auto !important;
            max-height: 88px !important;
            white-space: normal !important;
        }
    </style>
</head>
<body>
<form name="form1" id="form1" method="post"
      getAction="chck/detail.do?id=${param.id}">
    <fieldset class="l-fieldset">
        <legend class="l-legend">
            <div>出库单</div>
        </legend>
        <table class="l-detail-table">
            <tr>
                <td class="l-t-td-left">出库单编号：</td>
                <td class="l-t-td-right"><input name="ckdbh" type="text" ltype="text"
                                                ligerui="{disabled:true,readonly:true}"/>
                <td class="l-t-td-left">领取时间：</td>
                <td class="l-t-td-right"><input name="lqsj" type="text" ltype="text"
                                                ligerui="{disabled:true,readonly:true}"
                                                ligerui="{format:'yyyy-MM-dd hh:mm:ss'}"/></td>
            </tr>
            <tr>
                <td class="l-t-td-left">领取部门：</td>
                <td class="l-t-td-right"><input name="lqbm" type="text" ltype="text"
                                                ligerui="{disabled:true,readonly:true}"/></td>
                <td class="l-t-td-left">领取人：</td>
                <td class="l-t-td-right"><input name="lqr" type="text" ltype="text"
                                                ligerui="{disabled:true,readonly:true}"/>
                </td>
            </tr>
            <tr>
                <td class="l-t-td-left">库管：</td>
                <td class="l-t-td-right"><input name="kg" type="text" ltype="text"
                                                ligerui="{disabled:true,readonly:true}"/>
                </td>
                <td class="l-t-td-left">部门负责人：</td>
                <td class="l-t-td-right"><input name="bmfzr" type="text" ltype="text"
                                                ligerui="{disabled:true,readonly:true}"/>
                </td>
            </tr>
        </table>
    </fieldset>
    <fieldset class="l-fieldset">
        <legend class="l-legend">
            <div>领取物品明细</div>
        </legend>
        <div id="goodsGrid"></div>
    </fieldset>
</form>
</body>
</html>