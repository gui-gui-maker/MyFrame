<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@taglib uri="http://bpm.khnt.com" prefix="bpm" %>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<head>
    <title></title>
    <%@include file="/k/kui-base-list.jsp" %>
    <script type="text/javascript" src="pub/bpm/js/util.js"></script>
    <%@page import="com.khnt.security.util.SecurityUtil" %>
    <%@page import="com.khnt.security.CurrentSessionUser" %>
    <%@page import="com.khnt.rbac.impl.bean.User" %>
    <%
        CurrentSessionUser usee = SecurityUtil.getSecurityUser();
        User uu = (User) usee.getSysUser();
        com.khnt.rbac.impl.bean.Employee e = (com.khnt.rbac.impl.bean.Employee) uu.getEmployee();
        e.getId();
        String userId = e.getId();

        String uId = SecurityUtil.getSecurityUser().getId();
    %>
    <script type="text/javascript">
        var qmUserConfig = {
            sp_defaults: {columnWidth: 0.3, labelAlign: 'right', labelSeparator: '', labelWidth: 120},	// 默认值，可自定义
            sp_fields: [
                {name: "name", compare: "like"},
                {name: "xl", compare: "like"},
                {name: "brqr", compare: "like"}
            ],
            tbar: [{
                text: '详情', id: 'detail', icon: 'detail', click: detail
            }<sec:authorize access="hasRole('TJY2_RL_GJJ')">
                , "-", {
                    text: '新增', id: 'add', icon: 'add', click: add
                }, "-", {
                    text: '修改', id: 'edit', icon: 'modify', click: edit
                }, "-", {
                    text: '删除', id: 'del', icon: 'delete', click: del
                }</sec:authorize>, "-", {
                    text: '确认', id: 'submit', icon: 'submit', click: submit
                }<sec:authorize access="hasRole('sys_administrate')">
                , "-", {
                    text: '手动更新', id: 'hand', icon: 'f5', click: hand
                }</sec:authorize>],

            listeners: {
                rowClick: function (rowData, rowIndex) {
                },
                rowDblClick: function (rowData, rowIndex) {
                    detail();
                },
                selectionChange: function (rowData, rowIndex) {
                    var count = Qm.getSelectedCount();
                    Qm.setTbar({
                        detail: count == 1,
                        edit: count == 1,
                        del: count > 0,
                        submit: count == 1

                    });
                }, selectionChange: function (rowData, rowIndex) {
                    var count = Qm.getSelectedCount();//行选择个数
                    var up_status = Qm.getValueByName("brqr");
                    var sp_status = Qm.getValueByName("sp_status");
                    //alert(sp_status);
                    var status = {};
                    if (count == 0) {
                        status = {detail: false, edit: false, del: false, submit: false};
                    } else if (count == 1) {
                        if ("已确认" == up_status) {
                            status = {detail: true, edit: true, del: true, submit: false};
                        } else {
                            status = {detail: true, edit: true, del: true, submit: true};
                        }
                    } else {
                        if ("已提交" == sp_status) {
                            status = {detail: false, edit: false, del: true, submit: false};
                        } else {
                            status = {detail: false, edit: false, del: false, submit: false};
                        }
                    }
                    Qm.setTbar(status);
                },
                rowAttrRender: function (rowData, rowid) {
                    var fontColor = "black";
                    if (rowData.brqr == '未确认') {
                        fontColor = "#8E8E8E";
                    }
                    if (rowData.zjf_js == '' && rowData.brqr ==='未确认') {
                        fontColor = "red";
                    }

                    // 离职标示为橙色
                    if (rowData.data_status === '98') {
                        fontColor = 'orange';
                    }
                    // 关键字修改标示为蓝色
                    if (rowData.data_status === '97' && rowData.brqr ==='未确认') {
                        fontColor = 'blue';
                    }
                    return "style='color:" + fontColor + "'";
                }
            }
        };

        function submit() {
            var id = Qm.getValueByName("id");
            $.ligerDialog.confirm('是否要确认？', function (yes) {
                if (!yes) {
                    return false;
                }
                top.$.ajax({
                    url: "Tjy2GjjAction/RL/submit.do?id=" + id,
                    type: "GET",
                    dataType: 'json',
                    async: false,
                    success: function (data) {
                        if (data.success) {
                            //  $.ligerDialog.success("提交成功！");
                            top.$.notice('确认成功！！！', 3);
                            Qm.refreshGrid();//刷新
                        } else {
                            $.ligerDialog.warn("出错了！请重试！!");
                        }
                    },
                    error: function () {
                        //$.ligerDialog.warn("提交失败！");
                        $.ligerDialog.error("出错了！请重试！！！");
                    }
                });
            });
        }

        function hand() {
            $.ligerDialog.confirm('是否要手动更新？', function (yes) {
                if (!yes) {
                    return false;
                }
                top.$.ajax({
                    url: "Tjy2GjjAction/RL/updateBasicByHand.do",
                    type: "GET",
                    dataType: 'json',
                    async: false,
                    success: function (data) {
                        if (data.success) {
                            top.$.notice(data.msg, 3);
                            Qm.refreshGrid();//刷新
                        } else {
                            $.ligerDialog.warn(data.msg);
                        }
                    },
                    error: function () {
                        //$.ligerDialog.warn("提交失败！");
                        $.ligerDialog.error("出错了！请重试！！！");
                    }
                });
            });
        }

        function add() {
            top.$.dialog({
                width: 900,
                height: 550,
                lock: true,
                parent: api,
                data: {
                    window: window
                },
                title: "新增",
                content: 'url:app/humanresources/gjj_datail.jsp?pageStatus=add'
            });
        }

        function edit() {
            var id = Qm.getValueByName("id");
            top.$.dialog({
                width: 900,
                height: 550,
                lock: true,
                parent: api,
                data: {
                    window: window
                },
                title: "修改",
                content: 'url:app/humanresources/gjj_datail.jsp?id=' + id + '&pageStatus=modify'
            });
        }

        function detail() {
            var id = Qm.getValueByName("id");
            top.$.dialog({
                width: 900,
                height: 550,
                lock: true,
                parent: api,
                data: {
                    window: window
                },
                title: "详情",
                content: 'url:app/humanresources/gjj_datail.jsp?id=' + id + '&pageStatus=detail'
            });
        }

        function del() {
            var id = Qm.getValueByName("id");
            $.ligerDialog.confirm('是否要确认删除？', function (yes) {
                if (!yes) {
                    return false;
                }
                top.$.ajax({
                    url: "Tjy2GjjAction/RL/delete.do?id=" + id,
                    type: "GET",
                    dataType: 'json',
                    async: false,
                    success: function (data) {
                        if (data.success) {
                            top.$.notice('删除成功！！！', 3);
                            Qm.refreshGrid();//刷新
                        } else {
                            $.ligerDialog.warn("出错了！请重试！!");
                        }
                    },
                    error: function () {
                        //$.ligerDialog.warn("提交失败！");
                        $.ligerDialog.error("出错了！请重试！！！");
                    }
                });
            });
        }

        function refreshGrid() {
            Qm.refreshGrid();
        }

        function close() {
            api.close();
        }
    </script>
</head>
<body>
<div class="item-tm" id="titleElement">
    <div class="l-page-title">
        <div class="l-page-title-note">提示：列表数据项
            <font color="black">“黑色”</font>代表本人已确认，
            <font color="#8E8E8E">“灰色”</font>代表本人未确认。
            <font color="red">“红色”</font>代表新增未修改数据。
            <font color="blue">“蓝色”</font>代表新关键字段值被修改数据。
            <font color="orange">“橙色”</font>代表离职人员数据。
        </div>
    </div>
</div>
<qm:qm pageid="TJY2_GJJ" singleSelect="true">
    <sec:authorize access="!hasRole('TJY2_RL_GJJ')">
        <qm:param name="name_id" value="<%=userId%>" compare="="/>
    </sec:authorize>
    <qm:param name="data_status" value="1" compare="="/>
    <%--增加展示离职人员公积金数据--%>
    <qm:param name="data_status" value="98" compare="=" logic="or"/>
    <%--增加展示关键字段被修改的人员公积金数据--%>
    <qm:param name="data_status" value="97" compare="=" logic="or"/>
</qm:qm>
</body>
</html>