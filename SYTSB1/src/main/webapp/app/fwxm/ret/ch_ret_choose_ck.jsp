<%@ page import="com.khnt.rbac.impl.bean.User" %>
<%@ page import="com.khnt.security.CurrentSessionUser" %>
<%@ page import="com.khnt.security.util.SecurityUtil" %><%--
  Created by IntelliJ IDEA.
  User: latiflan
  Date: 2018/10/10
  Time: 14:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>存货领取列表</title>
    <%@include file="/k/kui-base-list.jsp" %>
    <script type="text/javascript" src="pub/bpm/js/util.js"></script>
    <script type="text/javascript" src="pub/worktask/js/worktask.js"></script>
    <script type="text/javascript">
        var columns = [
            {
                text: '选取存货', click: choose, icon: 'check', id: "check"
            }];
        var qmUserConfig = {
            sp_fields: [
                {name: "lqr", compare: "like"},
                {name: "ckdbh", compare: "like"},
                {
                    group: [
                        {name: "create_time", compare: ">=", value: ""},
                        {
                            label: "到",
                            name: "create_time",
                            compare: "<=",
                            value: "",
                            labelAlign: "center",
                            labelWidth: 20
                        }
                    ]
                }
            ],
            tbar: columns,
            listeners: {
                rowClick: function (rowData, rowIndex) {

                },
                rowDblClick: function (rowData, rowIndex) {
                    choose();
                },
                selectionChange: function (rowData, rowIndex) {
                    var count = Qm.getSelectedCount();
                    var status = Qm.getValueByName("status");
                    Qm.setTbar({
                        check: count == 1,
                    });
                }
            },
            rowAttrRender: function (rowData, rowIndex) {
                return 'style="color:black"';
            }
        };

        function choose() {
            var id = Qm.getValueByName("id").toString();
            winOpen({
                lock: true,
                title: "选择需退还的存货",
                content: 'url:app/fwxm/ret/ch_ret_choose_ck_detail.jsp?status=modify&id=' + id,
                data: {
                    "window": window,
                    callback:_callback
                }
            }).max();
        }
        function _callback(data){
            api.close();
            api.data.callback(data);
        }
    </script>
</head>
<body>
<!-- 部门负责人 -->
<style type="text/css" id="sStyle">
    .l-grid-row-cell-inner {
        height: auto !important;
        max-height: 88px !important;
        white-space: normal !important;
    }
</style>
<qm:qm pageid="tjy2_ch_ck_list" script="false">
    <qm:param name="ckyjtype" compare="=" value="LQ"/>
    <qm:param name="status" compare="!=" value="未出库"/>
</qm:qm>
</body>
</html>
