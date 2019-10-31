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
                text: '详情', click: detail, icon: 'detail', id: "detail"
            }, "-",
            {
                text: '领取存货', click: add, icon: 'add', id: "add"
            }<sec:authorize access="hasRole('CH_LQ_WITH_OUT_FLOW')">
            , {text: '直接出库', click: addToOut, icon: 'add', id: "add"}
            </sec:authorize>,
            {
                text: '修改领取单', click: edit, icon: 'edit', id: "edit"
            },
            {
                text: '取消领取', click: del, icon: 'del', id: "del"
            }, "-",
            {
                text: '提交', click: submit, icon: 'submit', id: "submit"
            }<sec:authorize access="hasRole('CH_LQ_CL')">
            , "-", {text: '处理', id: 'deal', icon: 'dispose', click: deal}
            </sec:authorize>,
            {
                text: '流转过程', click: turnHistory, icon: 'follow', id: "turnHistory"
            }];
        var qmUserConfig = {
            sp_fields: [
                {name: "status", compare: "="},
                {name: "lq_bh", compare: "like"},
                {
                    group: [
                        {name: "lq_time", compare: ">=", value: ""},
                        {label: "到", name: "lq_time", compare: "<=", value: "", labelAlign: "center", labelWidth: 20}
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
                        detail: count == 1,
                        add: true,
                        edit: count == 1 && status == '未提交',
                        del: count == 1 && status == '未提交',
                        follow: count == 1 && status != '未提交',
                        submit: count == 1 && status == '未提交',
                        turnHistory: count == 1 && status != '未提交',
                        deal: count == 1 && status != '未提交' && status != '审核通过未出库' && status != '未通过' && status != '已出库' && status != '库管已删除'
                    });
                }
            },
            rowAttrRender: function (rowData, rowIndex) {
                if ( rowData.status == "未提交" ) {
                    return 'style="color:black;"';
                } else if ( rowData.status == "审核中" ) {
                    return 'style="color:orange;"';
                } else if ( rowData.status == "审核通过未出库" ) {
                    return 'style="color:green;"';
                } else if ( rowData.status == "已出库" ) {
                    return 'style="color:gray;"';
                } else if ( rowData.status == "未通过" || rowData.status == '库管已删除' ) {
                    return 'style="color:red;"';
                } else {
                    return 'style="color:black;"';
                }
            }
        };

        function deal() {
            var list;
            var id;
            var title;
            var service_id = Qm.getValueByName("id");
            $.ajax({
                url: "equipMaintainAction/queryMainId.do?id=" + service_id,
                type: "POST",
                success: function (resp) {
                    if ( resp.success ) {
                        list = resp.list;
                        id = list[0].id;
                        title = list[0].title;
                        var config = {
                            width: 800,
                            height: 630,
                            id: id,
                            title: title
                        }
                        // 调用流程的方法
                        openWorktask(config);
                    } else {
                        $.ligerDialog.error('没有流程数据！');
                    }
                },
                error: function (data, stats) {
                    $.ligerDialog.error('提示：' + data.msg);
                }
            });
        }

        function submit() {
            var id = Qm.getValueByName("id");
            if ( $.kh.isNull(id) ) {
                $.ligerDialog.error("请选择一张领取单");
                return;
            }
            var fgldtj = false;//是否是分管领导
            <sec:authorize access="hasRole('TJY2_RL_FGLDSH')">fgldtj = true</sec:authorize>;
            if ( fgldtj ) {
                _submit(true);
            } else {
                winOpen({
                    width: 350,
                    height: 250,
                    lock: true,
                    title: "",
                    content: 'url:app/fwxm/recipients/ch_lq_isyg.jsp',
                    data: {
                        "window": window,
                        id: id,
                        Qm: Qm,
                        userId: Qm.getValueByName("create_id"),
                        callback: _submit
                    }
                });
            }
        }

        function _submit(flag) {
            var id = Qm.getValueByName("id");
            var userId = Qm.getValueByName("create_id");
            $("body").mask("正在提交...请稍后");
            getServiceFlowConfig("tjy2_ch_lq", "", function (result, data) {
                if ( result ) {
                    $.ajax({
                        url: "chlq/subflow.do",
                        data: {
                            serviceId: id,
                            userId: userId,
                            flowId: data.id,
                            isYgje: flag,
                        },
                        dataType: 'json',
                        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                        type: "post",
                        async: true,
                        success: function (data) {
                            $("body").unmask("");
                            if ( data.success ) {
                                top.$.notice('提交成功！！！');
                                Qm.refreshGrid();
                            } else {
                                $.ligerDialog.error(data.msg);
                            }
                        },
                        error: function (XMLHttpRequest, textStatus, errorThrown) {
                            $("body").unmask("");
                            $.ligerDialog.error("提交失败，请与管理员联系");
                        }
                    });
                } else {
                    $.ligerDialog.error('出错了！请重试！', 3);
                    $("body").unmask();
                }
            });

        }

        function edit() {
            var id = Qm.getValueByName("id");
            winOpen({
                lock: true,
                title: "领取单",
                content: 'url:app/fwxm/recipients/ch_lq_detail.jsp?status=edit&id=' + id,
                data: {
                    "window": window,
                }
            });
        }

        function del() {
            $.del(kFrameConfig.DEL_MSG, "chlq/delete.do", {
                "ids": Qm.getValuesByName("id").toString()
            });
        }

        function detail() {
            var id = Qm.getValueByName("id");
            var isMax = Qm.getValueByName("status") == '已出库';
            var win = winOpen({
                lock: true,
                title: "领取单",
                content: 'url:app/fwxm/recipients/ch_lq_detail.jsp?status=detail&id=' + id + '&isMax=' + isMax,
                data: {
                    "window": window,
                }
            });
            if ( isMax ) {
                win.max();
            }
        }

        /**
         * 走流程审核
         */
        function add() {
            winOpen({
                lock: true,
                title: "领取单",
                content: 'url:app/fwxm/recipients/ch_lq_detail.jsp?status=add',
                data: {
                    "window": window,
                }
            });
        }

        /**
         * 不走流程审核
         */
        function addToOut() {
            winOpen({
                lock: true,
                title: "领取单",
                content: 'url:app/fwxm/recipients/ch_lq_detail_without_flow.jsp?status=add',
                data: {
                    "window": window,
                }
            });
        }

        function turnHistory() {
            winOpen({
                width: 400,
                height: 700,
                lock: true,
                title: "流程卡",
                content: 'url:chlq/getFlowStep.do?id=' + Qm.getValueByName("id"),
                data: {
                    "window": window,
                }
            });
        }
    </script>
</head>
<body>
<%
    CurrentSessionUser user = SecurityUtil.getSecurityUser();
    User uu = (User) user.getSysUser();
    String userId = user.getId();
    com.khnt.rbac.impl.bean.Employee e = (com.khnt.rbac.impl.bean.Employee) uu.getEmployee();
    String employeeId = e.getId();
    StringBuffer sb = new StringBuffer();
    String sql = "select t.*,t.status as showStatus from TJY2_CH_RECIPIENTS t where status != 99 and create_id = '" + userId + "' order by lq_bh desc";
    String departmentid = user.getDepartment().getId();
    String userDep = "";
%>
<!-- 部门负责人 -->
<sec:authorize access="hasRole('TJY2_BMFZR')">
    <%
        sql = "select * from ( select * from (select b.*,t.handler_id from TJY2_CH_RECIPIENTS b,v_pub_worktask t where " +
                "b.id = t.SERVICE_ID and t.WORK_TYPE like 'tjy2_ch_lq' and t.status = '0' and b.status != 99 ) s where s.handler_id = '" + userId + "' and s.create_org_id = '" + departmentid + "' union " +
                "select b.*,create_id from TJY2_CH_RECIPIENTS b where b.create_id = '" + userId + "' and b.status != 99 union " +
                "select b.*,create_id from TJY2_CH_RECIPIENTS b where b.create_org_id = '" + departmentid + "' and (status = '3' or status='4') and b.status != 99) t order by status,create_time desc,create_org_name,create_name";
        if (user.getName().equals("孙宇艺")) {
            userDep = "100025";
            if (user.getName().equals("韩绍义")) {
                userDep = "100030";
            }
            sql = "select * from (select*from (select b.*,t.handler_id,t.org_id from TJY2_CH_RECIPIENTS b,v_pub_worktask_add_position t " +
                    "where b.id=t.SERVICE_ID   and  t.WORK_TYPE  like   'tjy2_ch_lq' " +
                    "and t.status='0' and b.status != 99 ) s where s.handler_id = '" + userId + "' and s.org_id = '" + departmentid + "' and s.create_org_id = '" + userDep + "' union " +
                    "select b.*,create_id from TJY2_CH_RECIPIENTS b where b.create_id = '" + userId + "' and b.status != 99 union " +
                    "select b.*,create_id from TJY2_CH_RECIPIENTS b where b.create_org_id = '" + userDep + "' and (status = '3' or status='4') and b.status != 99 ) order by status,create_time desc,CREATE_ORG_NAME,CREATE_NAME";
        }
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@部门负责人@@@@@@@@@@@@@@@@@@@@@@@@！");
        System.out.println(sql.toString());
    %>
</sec:authorize>

<!-- 分管领导 -->
<sec:authorize access="hasRole('TJY2_RL_FGLDSH')">
    <%
        sql = "select * from (select s.* from ( " +
                "select b.*,t.handler_id from TJY2_CH_RECIPIENTS b, v_pub_worktask t " +
                "where b.id=t.SERVICE_ID   and  t.WORK_TYPE  like   'tjy2_ch_lq' and t.STATUS='0' and b.status != 99 ) s " +
                "where s.handler_id = '" + userId + "' and instr((select d.fk_sys_org_id from TJY2_RL_ORGID_LEADERID d " +
                "where d.fk_rl_emplpyee_id='" + employeeId + "'), s.create_org_id) > 0" +
                "union " +
                "select b.*,create_id from TJY2_CH_RECIPIENTS b where create_id = '" + userId + "' and b.status != 99 " +
                "union " +
                "select b.*,create_id from TJY2_CH_RECIPIENTS b " +
                "where (status = '3' or status='4') and b.status != 99 and " +
                "instr((select d.fk_sys_org_id from TJY2_RL_ORGID_LEADERID d " +
                "where d.fk_rl_emplpyee_id='" + employeeId + "'), b.create_org_id) > 0) order by status ,create_time desc,CREATE_ORG_NAME,CREATE_NAME";
    %>
    <%
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@分管领导@@@@@@@@@@@@@@@@@@@@@@@@！");
        System.out.println(sql.toString());
    %>
</sec:authorize>
<!-- 既包含部门负责人又包含 分管领导-->
<sec:authorize ifAllGranted="TJY2_BMFZR,TJY2_RL_FGLDSH">
    <%
        sql = "select * from (select s.* from ( " +
                "select b.*,t.handler_id from TJY2_CH_RECIPIENTS b, v_pub_worktask t " +
                "where b.id=t.SERVICE_ID   and  t.WORK_TYPE  like 'tjy2_ch_lq' and t.STATUS='0' and b.status != 99 ) s " +
                "where s.handler_id = '" + userId + "' and instr((select d.fk_sys_org_id from TJY2_RL_ORGID_LEADERID d " +
                "where d.fk_rl_emplpyee_id='" + employeeId + "'), s.create_org_id) > 0" +
                "union " +
                "select b.*,create_id from TJY2_CH_RECIPIENTS b where create_id = '" + userId + "' and b.status != 99 " +
                "union " +
                "select b.*,create_id from TJY2_CH_RECIPIENTS b " +
                "where (status = '3' or status='4') and b.status != 99 and " +
                "instr((select d.fk_sys_org_id from TJY2_RL_ORGID_LEADERID d " +
                "where d.fk_rl_emplpyee_id='" + employeeId + "'), b.create_org_id) > 0) order by status,create_time desc,CREATE_ORG_NAME,CREATE_NAME";
        if (e.getName().equals("孙宇艺")) {
            sql = "select * from (select*from (select b.*,t.handler_id, t.org_id from TJY2_CH_RECIPIENTS b, v_pub_worktask_add_position t " +
                    "where b.id=t.SERVICE_ID   and  t.WORK_TYPE  like   'tjy2_ch_lq' " +
                    "and t.STATUS='0' and b.status != 99 ) s where s.handler_id = '" + userId + "' and s.create_org_id in('100025','100048') union " +
                    "select s.* from ( " +
                    "select b.*,t.handler_id,b.CREATE_ORG_ID org_id from TJY2_CH_RECIPIENTS b, v_pub_worktask t " +
                    "where b.id=t.SERVICE_ID   and  t.WORK_TYPE  like   'tjy2_ch_lq' and t.STATUS='0'  and b.status != 99) s " +
                    "where s.handler_id = '" + userId + "' and instr((select d.fk_sys_org_id from TJY2_RL_ORGID_LEADERID d " +
                    "where d.fk_rl_emplpyee_id='" + employeeId + "'), s.create_org_id) > 0 union " +
                    "select b.*,b.create_id, b.create_org_id org_id from TJY2_CH_RECIPIENTS b where create_id = '" + userId + "' and b.status != 99 union " +
                    "select b.*,b.create_id, b.create_org_id org_id from TJY2_CH_RECIPIENTS b " +
                    "where (status = '3' or status='4') and b.status != 99 and " +
                    "instr((select d.fk_sys_org_id from TJY2_RL_ORGID_LEADERID d " +
                    "where d.fk_rl_emplpyee_id='" + employeeId + "'), b.create_org_id) > 0" +
                    " )";
        }
        if (e.getName().equals("韩绍义")) {
            sql = "select * from (select*from (select b.*,t.handler_id, t.org_id from TJY2_CH_RECIPIENTS b, v_pub_worktask_add_position t " +
                    "where b.id=t.SERVICE_ID   and  t.WORK_TYPE  like   'tjy2_ch_lq' " +
                    "and t.STATUS='0' and b.status != 99 ) s where s.handler_id = '" + userId + "' and s.create_org_id in('100030') union " +
                    "select s.* from ( " +
                    "select b.*,t.handler_id,b.CREATE_ORG_ID org_id from TJY2_CH_RECIPIENTS b, v_pub_worktask t " +
                    "where b.id=t.SERVICE_ID   and  t.WORK_TYPE  like   'tjy2_ch_lq' and t.STATUS='0' and b.status != 99) s " +
                    "where s.handler_id = '" + userId + "' and instr((select d.fk_sys_org_id from TJY2_RL_ORGID_LEADERID d " +
                    "where d.fk_rl_emplpyee_id='" + employeeId + "'), s.create_org_id) > 0 union " +
                    "select b.*,b.create_id, b.create_org_id org_id from TJY2_CH_RECIPIENTS b where create_id = '" + userId + "' and b.status != 99 union " +
                    "select b.*,b.create_id, b.create_org_id org_id from TJY2_CH_RECIPIENTS b " +
                    "where (status = '3' or status='4') and b.status != 99 and " +
                    "instr((select d.fk_sys_org_id from TJY2_RL_ORGID_LEADERID d " +
                    "where d.fk_rl_emplpyee_id='" + employeeId + "'), b.create_org_id) > 0" +
                    " )";
        }
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@" + e.getName() + "@@@@@@@@@@@@@@@@@@@@@@@@！");
        System.out.println(sql.toString());
    %>
</sec:authorize>

<sec:authorize access="hasRole('CH_LQ_JEYG')">
    <%
        sql = "select * from ( select * from (select b.*,t.handler_id from TJY2_CH_RECIPIENTS b,v_pub_worktask t where " +
                "b.id = t.SERVICE_ID and t.WORK_TYPE like 'tjy2_ch_lq' and t.status = '0' and b.status != 99 ) s where s.handler_id = '" + userId + "' union " +
                "select b.*,create_id from TJY2_CH_RECIPIENTS b where b.create_id = '" + userId + "' and b.status != 99)";
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@金额核定@@@@@@@@@@@@@@@@@@@@@@@@！");
        System.out.println(sql.toString());
    %>
</sec:authorize>
<style type="text/css" id="sStyle">
    .l-grid-row-cell-inner {
        height: auto !important;
        max-height: 88px !important;
        white-space: normal !important;
    }
</style>
<div class="item-tm" id="titleElement">
    <div class="l-page-title">
        <div class="l-page-title-note">提示：列表数据项
            <font color="black">“黑色”</font>代表未提交，
            <font color="orange">“橙色”</font>代表审批中，
            <font color="green">"绿色"</font>代表审批通过未出库，
            <font color="gray">"灰色"</font>代表审批通过已出库，
            <font color="red">“红色”</font>代表审批不通过或已被库管删除。
        </div>
    </div>
</div>
<qm:qm pageid="tjy2_ch_lq_list" script="false" sql="<%=sql.toString()%>">
</qm:qm>
</body>
</html>
