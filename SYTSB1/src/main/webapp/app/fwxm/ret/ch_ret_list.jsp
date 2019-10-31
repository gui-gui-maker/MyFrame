<%--
  Created by IntelliJ IDEA.
  User: latiflan
  Date: 2018/10/17
  Time: 15:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>Title</title>
    <%@include file="/k/kui-base-list.jsp" %>
    <script type="text/javascript">
        var columns = [
            {
                text: '详情', click: detail, icon: 'detail', id: "detail"
            },
            {
                text: '新增退还单', click: add, icon: 'add', id: "add"
            },
            {
                text: '修改', click: edit, icon: 'edit', id: "edit"
            },
            {
                text: '提交生效', click: takeeffect, icon: 'check', id: "takeeffect"
            }];
        var qmUserConfig = {
            sp_fields: [
                {name: "showStatus", compare: "="},
                {name: "ckdbh", compare: "like"},
                {
                    group: [
                        {name: "lq_time", compare: ">=", value: ""},
                        {label: "到", name: "buy_date", compare: "<=", value: "", labelAlign: "center", labelWidth: 20}
                    ]
                }
            ],
            tbar: columns,
            listeners: {
                rowClick: function (rowData, rowIndex) {

                },
                rowDblClick: function (rowData, rowIndex) {

                },
                selectionChange: function (rowData, rowIndex) {
                    var count = Qm.getSelectedCount();
                    var status = Qm.getValueByName("status");
                    Qm.setTbar({
                        takeeffect: count == 1 && status == '0',
                        detail: count == 1,
                        edit: count == 1 && status == '0'
                    });
                }
            },
            rowAttrRender:function(rowData){
                if(rowData["status"]==0){
                    return "style='color:black'";
                }else if (rowData["status"]=='4'){
                    return "style='color:green'";
                }else {
                    return "style='color:black'";
                }
            }
        };
        $(function () {

        });

        function add() {
            winOpen({
                lock: true,
                title: "填写退还单",
                content: 'url:app/fwxm/ret/ch_ret_detail.jsp?status=add',
                data: {
                    "window": window,
                }
            }).max();
        }

        function takeeffect() {
            var id = Qm.getValueByName("id");
            $("body").mask("");
            $.ajax({
                url: "chreturn/takeeffect.do?id="+id,
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                type: "post",
                async: true,
                success: function (data) {
                    $("body").unmask("");
                    if ( data.success ) {
                        top.$.notice("提交成功");
                        Qm.refreshGrid();
                    } else {
                        $.ligerDialog.error(data.msg);
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    $("body").unmask("");
                    $.ligerDialog.error("提交失败，请于管理员联系！");
                }
            });
        }
        function detail(){
            var id = Qm.getValueByName("id");
            winOpen({
                lock: true,
                title: "详情",
                content: 'url:app/fwxm/ret/ch_ret_detail.jsp?status=detail&id='+id,
                data: {
                    "window": window,
                }
            }).max();
        }
        function edit(){
            var id = Qm.getValueByName("id");
            winOpen({
                lock: true,
                title: "详情",
                content: 'url:app/fwxm/ret/ch_ret_detail.jsp?status=edit&id='+id,
                data: {
                    "window": window,
                }
            }).max();
        }
    </script>
</head>
<body>
<div class="item-tm" id="titleElement">
    <div class="l-page-title">
        <div class="l-page-title-note">提示：列表数据项
            <font color="black">“黑色”</font>代表未提交，
            <font color="green">"绿色"</font>代表已退还入库
        </div>
    </div>
</div>
<qm:qm pageid="tjy2_ch_ret_list" script="false">
</qm:qm>
</body>
</html>
