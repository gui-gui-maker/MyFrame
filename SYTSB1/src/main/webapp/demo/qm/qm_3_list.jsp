<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@include file="/k/kui-base-list.jsp" %>
    <title>通用查询示例</title>
    <script type="text/javascript">
        var qmUserConfig = {
            searchButton: {text: "覆盖默认查询", id: "search", icon: "search-list", click: function () {
                alert("查询前处理一些自己的逻辑");
                Qm.searchData();
            }},
            //sp_defaults: {columnWidth: 0.33, labelAlign: 'right', labelSeparator: '', labelWidth: 80, layout: "column"},//可以自己定义 layout:column,float
            sp_fields: [
                {group: [
                    {label: "日期1",id:"date1_begin", name: "date1", compare: ">=", value: "", width: "100"},
                    {label: "到",id:"date1_end", name: "date1", compare: "<=", value: "", labelAlign: "center", labelWidth: 20, width: "100"}
                ]},
                {label:"初始条件示例",name: "str1", compare: "like", value: "A"},
                {name: "str2", compare: "=", value: "", width: ""}
            ],
            //定义按钮，可以直接是extjs的按钮
            tbar: [
                { text: "选中记录数", icon: "add", click: function(){
                    tips("选中记录数：<font color=red>"+Qm.getSelectedCount()+"</font>");
                } },
                "-",
                { text: "通过JS设置查询条件", click: function () {
                    top.$.dialog.prompt("流水号等于",function(value){
                        Qm.setCondition({name: "id", compare: "=", value: value});
                        Qm.searchData();
                    });
                }},
                "-",
                { text: "清除JS设置的条件", click: function () {
                    Qm.setCondition([]);
                    Qm.searchData();
                }},
                "-",
                { text: "通过JS选中记录", click: function () {
                    top.$.dialog.prompt("输入流水号（多条记录逗号分隔）",function(value){
                        Qm.getQmgrid().selectRange("id", value.split(","))
                    });
                }},
                "-",
                { text: "设置查询面板的条件",  click: function () {
                    $.ligerui.get("date1_begin").setValue("2007-12-15");
                    $.ligerui.get("date1_end").setValue("2010-12-15");
                }},
                "-",
                { text: "按钮状态控件", id: "btn",icon:"detail", click: function () {
                    tips("通过selectionChange事件接口改变按钮状态，当前选中 "+Qm.getSelectedCount()+" 行");
                }}
            ],
            //提供以下4个事件
            listeners: {
                rowClick: function (rowData, rowIndex) {
                    //tips("点击第 "+(rowIndex+1)+" 行-"+rowData.id);
                },
                rowDblClick :function(rowdata,rowindex,rowDomElement){
                    tips("双击了第 "+(rowindex+1)+" 行，流水号："+rowdata["id"]);
                },
                selectionChange: function (rowData, rowIndex) {
                    var count = Qm.getSelectedCount();
                    var status = {};
                    if (count >0) {
                        status = {btn: true};
                    } else {
                        status = {btn: false};
                    }
                    Qm.setTbar(status);
                },
                beforeQmReady: function () {
                    tips("beforeQmReady事件发生在所有参数解析之前");
                },
                //可以邦定ligerui grid原生的事件和属性
                onAfterShowData: function () {
                    tips("数据展示完成触发的事件");
                    //Qm.getQmgrid().selectRange("id", [30, 2]);
                },
                rowAttrRender: function (item, rowid) {
                    //对行属性控制，可以附加样式等属性
                    if (item["id"] > 10 && item["id"] < 30)
                        return 'style="color:red;"';
                    else
                        return "";
                }
            }
        };

        var tip;
        function tips(content) {
            if (!tip) {
                tip = top.$.dialog.notice(content,5);
            }else{
                tip.content(content);
                tip.show();
            }
        }
    </script>
</head>
<body>
<qm:qm pageid="qm_01"/>

</body>
</html>