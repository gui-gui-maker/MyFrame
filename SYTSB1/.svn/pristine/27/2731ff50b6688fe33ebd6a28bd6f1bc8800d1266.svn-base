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
                text: '选取出库单', click: choose, icon: 'check', id: "check"
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
                    //choose();
                },
                selectionChange: function (rowData, rowIndex) {
                    var count = Qm.getSelectedCount();
                    Qm.setTbar({
                        check: count >= 1,
                    });
                }
            },
            rowAttrRender: function (rowData, rowIndex) {
                return 'style="color:black"';
            }
        };

        function f_isChecked(rowdata)
        {
            var rkbh=api.data.ckdids.split(",");
            for(var i=0;i<rkbh.length;i++){
                if(rowdata.id==rkbh[i]){
                    return true;
                }
            }
            return false;
        }

        $(function(){
            //Qm设置默认选中
            Qm.config.isChecked=f_isChecked
        });

        function choose() {
            var id = Qm.getValuesByName("id").toString();
            var ckdbh = Qm.getValuesByName("ckdbh").toString();
            if (id == "" || typeof (id) == 'undefined') {
                $.ligerDialog.error("请选择出库单");
                return;
            }
            $("body").mask("正在获取出库单总金额，请稍后");
            $.ajax({
                url: "chck/getchcktotalamount.do?ids="+id,
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                type: "post",
                async: true,
                success: function (data) {
                    $("body").unmask("");
                    if (data.success) {
                        api.data.callback({
                            ids:id,
                            ckdbhs:ckdbh,
                            totalAmount:data["data"]
                        });
                        api.close();
                    } else {
                        $.ligerDialog.error(data.msg);
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    $("body").unmask("");
                    $.ligerDialog.error("请刷新后再试，或者联系管理员处理！");
                }
            });
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
<qm:qm pageid="tjy2_ch_ck_cw_list" script="false">
    <qm:param name="ckyjtype" compare="=" value="LQ"/>
    <qm:param name="bx_status" compare="=" value="0"/>
</qm:qm>
</body>
</html>
