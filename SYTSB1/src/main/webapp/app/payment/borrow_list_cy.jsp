<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>承压借票</title>
    <%@include file="/k/kui-base-list.jsp" %>
    <script type="text/javascript">
        var qmUserConfig = {
            sp_defaults: {columnWidth: 0.25, labelAlign: 'right', labelSeparator: '', labelWidth: 80},	// 默认值，可自定义
            sp_fields: [
                {name: "com_name", compare: "like"},
                {name: "check_dep_id", id: "check_dep_id", compare: "=", treeLeafOnly: false},
                {name: "borrow_name", id: "borrow_name", compare: "like"},
                {
                    group: [
                        {name: "borrow_date", id: "borrow_date", compare: ">=", value: ""},
                        {
                            label: "到",
                            name: "borrow_date",
                            id: "borrow_date1",
                            compare: "<=",
                            value: "",
                            labelAlign: "center",
                            labelWidth: 20
                        }
                    ]
                },
                {
                    group: [
                        {name: "invoice_no", id: "invoice_no", compare: ">=", value: ""},
                        {
                            label: "到",
                            name: "invoice_no",
                            id: "invoice_no1",
                            compare: "<=",
                            value: "",
                            labelAlign: "center",
                            labelWidth: 20
                        }
                    ]
                },
                {name: "invoice_no", id: "invoice_no2", compare: "="},
                {name: "unpay_amount", compare: "="},
                {name: "pay_no", id: "pay_no", compare: "="},
                {name: 'invoice_type', compare: "=", isMultiSelect: true}
            ],
            tbar: [
                {text: '借票', id: 'borrow', click: doBorrow, icon: 'role'},
                '-',
                {text: '收费', id: 'doPayment', click: doPayment, icon: 'add'},
                '-',
                {text: '修改', id: 'modifyBorrow', click: modifyBorrow, icon: 'modify'},
                '-',
                {text: '取消借票', id: 'delBorrow', click: delBorrow, icon: 'delete'},
                //'-',
                //{ text : '详情', id : 'detail', icon : 'detail', click : detail},
                '-',
                {text: '金额合计', id: 'unpay_amount', click: doTotal1, icon: 'help'},
                '-',
                {text: '借票导出', id: 'exportBorrow', click: exportBorrow, icon: 'excel-export'}//,
                //'-',
                //{ text:'借票日志', id:'turnHistory',icon:'follow', click: turnHistory}
            ],
            listeners: {
                selectionChange: function (rowData, rowIndex) {
                    var count = Qm.getSelectedCount();//行选择个数
                    Qm.setTbar({
                        detail: count == 1,
                        modifyBorrow: count == 1,
                        unpay_amount: count > 0,
                        delBorrow: count > 0,
                        turnHistory: count == 1,
                        doPayment: count > 0
                    });
                },
                rowDblClick: function (rowData, rowIndex) {
                    detail();
                },
                rowAttrRender: function (rowData, rowid) {
                    /*
                    var fontColor="black";
                    // 2：已借票
                    if (rowData.status == "2"){
                        fontColor="black";
                    }
                    // 99：取消借票
                    if (rowData.status == "99"){
                        fontColor="red";
                    }
                    return "style='color:"+fontColor+"'";
                    */
                },
                pageSizeOptions: [10, 20, 30, 50, 100, 200]
            }
        };

        // 查看详情
        function detail() {
            var id = Qm.getValueByName("id").toString();
            top.$.dialog({
                width: 800,
                height: 300,
                lock: true,
                title: "详情",
                data: {
                    "window": window
                },
                content: 'url:app/payment/report_borrow_cy.jsp?status=detail&id=' + id
            });
        }

        // 修改
        function modifyBorrow() {
            var id = Qm.getValueByName("id").toString();
            top.$.dialog({
                width: 800,
                height: 340,
                lock: true,
                title: "修改借票",
                data: {
                    "window": window
                },
                content: 'url:app/payment/report_borrow_cy.jsp?status=modify&id=' + id
            });
        }

        // 借票
        function doBorrow() {
            top.$.dialog({
                width: 800,
                height: 320,
                lock: true,
                title: "通用借票",
                data: {
                    "window": window
                },
                content: 'url:app/payment/report_borrow_cy.jsp?status=add'
            });
        }

        function doBorrowAgain(formData) {
            winOpen({
                width: 800,
                height: 320,
                lock: true,
                title: "通用借票",
                content: 'url:app/payment/report_borrow_cy.jsp?status=add',
                data: {
                    "window": window,
                    formData: formData,
                }
            });
        }

        // 借票收费
        function doPayment() {
            var ids = Qm.getValuesByName("id").toString();
            var remaining = ids.split(",");
            var id = remaining[0];
            remaining.splice(0, 1);
            winOpen({
                width: 800,
                height: 600,
                lock: true,
                title: "借票收费",
                data: {
                    "window": window,
                    remaining: remaining,
                },
                content: 'url:app/payment/payment_detail_cy1.jsp?status=modify&id=' + id
            });
        }

        function doPaymentAgain(remaining, formDatas) {
            Qm.refreshGrid();
            debugger;
            if ( remaining.length > 0 ) {
                top.$.notice("您还有【" + remaining.length + "】条借票记录未进行收费",10);
                var id = remaining[0];
                remaining.splice(0, 1);
                winOpen({
                    width: 800,
                    height: 600,
                    lock: true,
                    title: "借票收费",
                    data: {
                        "window": window,
                        remaining: remaining,
                        formDatas: $.parseJSON(formDatas),
                    },
                    content: 'url:app/payment/payment_detail_cy1.jsp?status=modify&id=' + id
                });
            } else {
                top.$.notice("收费完毕，请重新勾选要收费的借票记录！");
            }
        }

        // 取消借票
        function delBorrow() {
            var ids = Qm.getValuesByName("id").toString();	// 借票ID
            $.del("亲，您确定要取消借票吗？", "report/borrow/delBorrowCY.do", {
                "ids": ids
            });
        }

        // 金额合计
        function doTotal1() {
            var ids = Qm.getValuesByName("unpay_amount").toString();
            doTotal(ids, "借票金额");
        }

        function doTotal(ids, title) {
            var str = new Array();
            str = ids.split(",");
            var total = 0;
            if ( str != null && str.length > 0 ) {
                for (var i = 0; i < str.length; i++) {
                    if ( str[i] == '' || str[i] == null ) {
                        str[i] = 0;
                    }
                    total = total + parseFloat(str[i]);
                }
                $.ligerDialog.alert(title + '合计：' + total.toFixed(2) + "元。");
            }
        }

        // 借票导出
        function exportBorrow() {
            var org_id = $("input[name='check_dep_id-txt']").ligerGetComboBoxManager().getValue();
            $("#org_id").val(org_id);
            $("#borrow_start_date").val($("#borrow_date").val());
            $("#borrow_end_date").val($("#borrow_date1").val());
            $("body").mask("正在导出数据,请等待...");
            $("#form1").attr("action", "report/borrow/exportBorrowCY.do");
            $("#form1").submit();
            $("body").unmask();
        }

        // 借票日志
        function turnHistory() {
            top.$.dialog({
                width: 400,
                height: 500,
                lock: true,
                title: "借票日志",
                content: 'url:report/borrow/getFlowStep.do?id=' + Qm.getValueByName("id"),
                data: {"window": window}
            });
        }

        // 刷新Grid
        function refreshGrid() {
            Qm.refreshGrid();
        }
    </script>
</head>
<body>
<form name="form1" id="form1" action="" getAction="" target="_blank">
    <input type="hidden" name="org_id" id="org_id" value=""/>
    <input type="hidden" name="borrow_start_date" id="borrow_start_date" value=""/>
    <input type="hidden" name="borrow_end_date" id="borrow_end_date" value=""/>
</form>
<!--
<div class="item-tm" id="titleElement">
    <div class="l-page-title">
        <div class="l-page-title-note">提示：列表数据项
            <font color="black">“黑色”</font>代表已借票，
            <font color="red">“红色”</font>代表取消借票。
        </div>
    </div>
</div>
 -->
<qm:qm pageid="borrow_list_cy" script="false">
</qm:qm>
<script type="text/javascript">
    // 根据 sql或码表名称获得Json格式数据
    <%--var aa=<u:dict code="community_type"></u:dict>;--%>
    //Qm.config.columnsInfo.area_id.binddata=<u:dict sql='select id,parent_id pid,regional_code code, regional_name text from V_AREA_CODE'></u:dict>;
    Qm.config.columnsInfo.check_dep_id.binddata =<u:dict sql="select id,parent_id pid,id code, ORG_NAME text from SYS_ORG where (ORG_CODE like 'jd%' or ORG_CODE like 'cy%' or org_code='caiwu' or ORG_CODE = 'human' or ORG_CODE like 'ywfz%') and ORG_CODE!='cy4_1' order by orders "></u:dict>;
    Qm.config.columnsInfo.borrow_status.binddata =<u:dict code="BORROW_STATUS_CY"></u:dict>;
</script>
</body>
</html>
