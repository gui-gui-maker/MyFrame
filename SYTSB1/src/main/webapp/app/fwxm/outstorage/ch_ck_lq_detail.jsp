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
    <title>领取出库单列表</title>
    <%@ include file="/k/kui-base-form.jsp" %>
    <script type="text/javascript">
        $(function () {
            $("#form1").initForm({
                toolbar: [
                    {
                        text: "出库", icon: "save", click: function () {
                            submitForm();
                        }
                    },
                    {
                        text: "取消", icon: "cancel", click: function () {
                            api.close();
                        }
                    }
                ],
                success: function (resp) {
                    if (resp.success) {

                    } else {
                        $.ligerDialog.error(resp.msg);
                    }
                },
                getSuccess: function (resp) {
                    if (resp.success) {
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
                                bz: resp.data.goods[i]["bz"],
                                kcsys: resp.data.goods[i]["goods"]["sl"]
                            })
                            ids.push(resp.data.goods[i]["goods"]["id"]);
                        }
                        mainGrid.loadData({Rows: Rows});
                        var formData = {
                            lqbm: resp.data["lqOrgName"],
                            lybm: resp.data["lqOrgName"],
                            lqr: resp.data["lqName"],
                            lqSqsj: resp.data["createTime"]
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
                {display: "存货名称", name: "wpmc", width: 150, align: "center"},
                {
                    display: "规格及型号", name: "ggjxh", width: 200, align: "center"
                },
                {display: "入库单号", name: "warehousing_num", width: 150, align: "left"},
                {display: "单价", name: "je", width: 80, align: "right"},
                {
                    display: "领取数量",
                    name: "sl",
                    width: 50,
                    align: "right",
                    type: 'int',
                    editor: {type: 'spinner', minValue: '1'},
                    min: 1,
                    required: true,
                    maxlength: 200
                }, {display: "库存剩余数量", name: "kcsys", width: 80, align: "right"}
                , {display: "备注", name: "bz", width: 200, align: "left", editor: {type: 'text'}, maxlength: 2000}
            ];
            if ('detail' != '${param.status}') {
                columns.push({
                    display: "<a class='l-a iconfont l-icon-add' href='javascript: chooseGoods();'><span>增加</span></a>",
                    width: '50',
                    isSort: false,
                    align: "center",
                    render: function (item, index) {
                        return '<a class="l-a l-icon-del" href="javascript:deleteMainGridRow()"><span>删除</span></a>';
                    }
                });
            }

            mainGrid = $("#goodsGrid").ligerGrid({
                columns: columns,
                enabledEdit: canEdit,
                rownumbers: true,
                frozenRownumbers: false,
                colDraggable: false,
                rowDraggable: false,
                isScroll: true,
                usePager: false,
                data: {Rows: []}
            });
        }

        function chooseGoods() {
            winOpen({
                lock: true,
                title: "选择存货",
                content: 'url:app\\fwxm\\recipients\\ch_lq_choose.jsp?orgId=<%=user.getDepartment().getId()%>&showSL=true',
                data: {
                    "window": window,
                    callback: addToMainGrid,
                    goodsContent: $("#lqwp").val()
                }
            }).max();
        }

        var ids = [];

        function addToMainGrid(datas) {
            for (var i = 0; i < datas.length; i++) {
                //去重添加
                if ($.inArray(datas[i]["id"], ids) < 0) {
                    mainGrid.addRow(datas[i]);
                    ids.push(datas[i]["id"]);
                }
            }
        }

        function deleteMainGridRow() {
            $.ligerDialog.confirm("确定删除该数据？", function (yes) {
                if (yes) {
                    var gridManager = $("#goodsGrid").ligerGetGridManager();
                    var data = gridManager.getData();
                    var row = gridManager.getSelectedRow();
                    $("#" + row["__id"]).remove(); //移除对应的区域
                    data.splice(row["__index"], 1);
                    ids.splice($.inArray(row["id"], ids), 1);
                    var data1 = {
                        Rows: data
                    };
                    gridManager.loadData(data1);
                }
            });
        }

        function submitForm() {
            if ($("#lqSqsj").val() > $("#lqsj").val()) {
                $.ligerDialog.error("申请日期不得大于出库日期");
                return;
            }
            var formDatas = $("#form1").getValues();
            var goodsDatas = $("#goodsGrid").ligerGetGridManager().getData();
            if (goodsDatas.length <= 0) {
                $.ligerDialog.error("请选择存货");
                return;
            }
            $("body").mask("正在保存请稍后...");
            var goods = [];
            for (var i = 0; i < goodsDatas.length; i++) {
                goods.push({
                    goods: goodsDatas[i],
                    sl: goodsDatas[i]["sl"],
                    fk_goods_id: goodsDatas[i]["id"],
                    bz: goodsDatas[i]["bz"]
                })
            }
            formDatas["ckyjtype"] = "LQ";
            formDatas["goods"] = goods;
            $.ajax({
                url: "chck/outstorage.do",
                data: JSON.stringify(formDatas),
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                type: "post",
                async: true,
                success: function (data) {
                    $("body").unmask("");
                    if (data.success) {
                        api.data.window.Qm.refreshGrid();
                        api.close();
                    } else {
                        $.ligerDialog.error(data.msg);
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    $("body").unmask("");
                    $.ligerDialog.error("网络出现问题，请联系管理员或刷新后再试");
                }
            });
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
      getAction="chlq/detail.do?id=${param.id}">
    <fieldset class="l-fieldset">
        <legend class="l-legend">
            <div>出库单</div>
        </legend>
        <table class="l-detail-table">
            <input type="hidden" name="id"/>
            <input type="hidden" name="lqId" value="<%=user.getId()%>"/>
            <input type="hidden" name="lqOrgId" value="<%=user.getDepartment().getId()%>"/>
            <input type="hidden" name="lqUnitId" value="<%=user.getUnit().getId()%>"/>
            <input type="hidden" name="status"/>
            <tr>
                <td class="l-t-td-left">领取部门：</td>
                <td class="l-t-td-right"><input name="lqbm" type="text" ltype="text" ligerui="{disabled:true}"/></td>
                <td class="l-t-td-left">领取人：</td>
                <td class="l-t-td-right"><input name="lqr" type="text" ltype="text" ligerui="{disabled:true}"/>
                </td>
            </tr>
            <tr>
                <td class="l-t-td-left">申请日期：</td>
                <td class="l-t-td-right"><input name="lqSqsj" id="lqSqsj"  ligerui="{disabled:true}" type="text" ltype="date"
                                                validate="{required:true}"
                                                ligerui="{format:'yyyy-MM-dd'}"/></td>
                <td class="l-t-td-left">部门负责人：</td>
                <td class="l-t-td-right"><input name="bmfzr" type="text" ltype="text" ligerui="{disabled:true}"/>
                </td>
            </tr>
            <tr>
                <td class="l-t-td-left">领取用途：</td>
                <td class="l-t-td-right" colspan="3"><textarea rows="3" id="lqRemark" name="lqRemark" type="text"
                                                               ltype="text"
                                                               ligerui="{disabled:true}"></textarea></td>
            </tr>
            <tr>
                <td class="l-t-td-left">领取物品：</td>
                <td class="l-t-td-right" colspan="3"><textarea rows="3" id="lqwp" name="lqwp" type="text" ltype="text"
                                                               ligerui="{disabled:true}"></textarea></td>
            </tr>
            <tr>
                <td class="l-t-td-left">库管：</td>
                <td class="l-t-td-right"><input name="kg" type="text" ltype="text" value="<%=user.getName()%>"
                                                ligerui="{disabled:true}"/>
                </td>
                <td class="l-t-td-left">出库日期：</td>
                <td class="l-t-td-right"><input id="lqsj" name="lqsj" type="text" ltype="date"
                                                ligerui="{format:'yyyy-MM-dd'}" validate="{required:true}"
                                                value="<%=new SimpleDateFormat("yyyy-MM-dd").format(new Date())%>"/>
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