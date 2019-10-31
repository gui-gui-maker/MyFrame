<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://khnt.com/tags/qm" prefix="qm" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>通用查询</title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>

    <script type="text/javascript" src="k/kui/frame/core.js"></script>
    <script type="text/javascript" src="k/kui/frame/main.js"></script>

    <script type="text/javascript">
        loadCoreLibrary("list");
    </script>
    <script test="text/javascript">
        var qmUserConfig = {
//            //自定义查询面板样式参数
//            title:"重命名",
//            sp_defaults:{columnWidth:0.2,labelAlign:'top',labelSeparator:'',labelWidth:100},// 可以自己定义
//            自定义查询面板查询字段
            sp_fields: [
                {label: "重命名测试", name: "date2", compare: ">", value: "2007-01-01"},
                {label: "带时间测试", name: "date3", compare: ">", value: ""},
                {name: "str1", compare: "like", value: ""},
                {label: "初始值测试", name: "str2", compare: "like", value: "X"},
                {label: "下拉框测试", name: "str3", compare: "=", value: ""},
                {label: "下拉树测试", name: "num1", compare: "="}
            ],
//            //定义按钮，可以直接是extjs的按钮
            tbar: [
                { text: '增加', id: 'add', icon: 'add', click: test },
                "-",
                { text: '修改', id: 'edit', icon: 'modify', click: test, disable: true},
                "-",
                { text: '删除', id: 'del', icon: 'delete', click: test, disable: true},
                "-",
                { text: '设置查询条件', id: 'test', click: function () {
                    Qm.setCondition({name: "id", compare: "=", value: "2"});
                    Qm.searchData();
                }},
                "-",
                { text: '清除条件', id: 'clear', click: function () {
                    Qm.setCondition([]);
                    Qm.searchData();
                }},
                "-",
                { text: 'test', id: 'test', click: function () {
                    tab();
                }}

            ],
////            //提供以下4个事件
            listeners: {
//                rowClick :function(rowData,rowIndex){tips("点击第 "+(rowIndex+1)+" 行-"+rowData.id);}
                rowDblClick: function (rowData, rowIndex) {
                    tips("双击第 " + (rowIndex + 1) + " 行");
                }, selectionChange: function (rowData, rowIndex) {
                    var count = Qm.getSelectedCount();
                    var status = {};
                    if (count == 0) {
                        status = {edit: false, del: false};
                    } else if (count == 1) {
                        status = {edit: true, del: true};
                    } else {
                        status = {edit: false, del: true};
                    }
                    Qm.setTbar(status);
                    tips("选中 " + count + " 行");
                }, beforeQmReady: function () {
                    tips("beforeQmReady事件发生在所有参数解析之前");
                }
                //可以邦定ligerui grid原生的事件和属性
                , onAfterShowData: function () {
                    Qm.getQmgrid().selectRange("id", [30, 2]);
                }, rowAttrRender: function (item, rowid) {
                    if (item.id < 100)
                        return "style='background-color:red'";
                    else
                        return "";
                }
            }
        };
        var tip;
        function tips(content) {
            if (!tip) {
                tip = $.ligerDialog.tip({ title: '提示信息', content: content });
            }
            tip.show();
            tip.set('content', content);
        }
        function test(item) {
            tips(item.id + "=" + item.text);
        }
        var tab1 = '<div id="tab1"><div title="首页"><br>1<br>1<br>1<br>1<br>1<br>1<br>1<br>1<br>1<br>1<br>1<br>1<br>1<br>1<br>1<br>1<br>1<br>1<br>1<br>1<br>1<br>1<br>1<br>1<br>1<br>1<br>1<br>1<br>1<br>1<br>1<br>1<br>1<br>1<br>1<br>1<br>1<br>1' +
                '</div><div title="说明"></div></div>';
        var dlg;
        function tab() {
            if (!dlg) {
                $.ligerDialog.open({ content: tab1, height: 300, width: 600, buttons: [
                    { text: '确定', onclick: function (item, dialog) {
                        alert(item.text);
                    } },
                    { text: '取消', onclick: function (item, dialog) {
                        dialog.close();
                    } }
                ], isResize: true
                });
                $("#tab1").ligerTab({width: 600, height: 250});
            }
            dlg.show();

        }
    </script>
</head>
<body>


<qm:qm pageid="qmtest-2" singleSelect="false">
    <qm:param name="str1" compare="like" value="A"/>
</qm:qm>

</body>

</html>