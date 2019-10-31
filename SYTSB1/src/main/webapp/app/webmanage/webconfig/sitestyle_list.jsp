<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>风格管理</title>
    <!-- 每个页面引入，页面编码、引入js，页面标题 -->
    <%@include file="/k/kui-base-list.jsp" %>
    <script type="text/javascript">
        var qmUserConfig = {
            //定义按钮，可以直接是extjs的按钮
            tbar:[
                { text:'增加', id:'add', click:add, icon:'add'},
                "-",
                { text:'修改', id:'modify', click:modify, icon:'edit'},
                "-",
                { text:'删除', id:'del', click:del, icon:'delete'},
                "-",
                { text:'查看', id:'detail', click:detail, icon:'detail'},
                "-",
                { text:'设为默认', id:'setDefault', click:setDefault, icon:'true'},
                "-",
                { text:'设置栏目关联', id:'setClassTemp', click:setClassTemp, icon:'communication'}

            ],
////            //提供以下4个事件
            listeners:{
                rowClick:function (rowData, rowIndex) {
                }, rowDblClick:function (rowData, rowIndex) {
                    detail();
                }, selectionChange:function (rowData, rowIndex) {
                    selectionChange()
                }, rowAttrRender:function (item, rowid) {
                    if (item.is_default == "")
                        return "";
                    else
                        return "style='color:red'";
                }
            }
        };


        //行选择改变事件
        function selectionChange() {
            var count = Qm.getSelectedCount();//行选择个数
            if (count == 0) {
                Qm.setTbar({add:true, modify:false, detail:false, del:false, setDefault:false, setClassTemp:false});
            } else if (count == 1) {
                Qm.setTbar({add:true, modify:true, detail:true, del:true, setDefault:true, setClassTemp:true});
            } else {
                Qm.setTbar({add:true, modify:false, detail:false, del:false, setDefault:false, setClassTemp:false});
            }
        }
        //新增风格
        function add() {
            var windows = top.$.dialog({
                width:600,
                height:300,
                lock:true,
                title:"新增风格",
                content:'url:app/webmanage/webconfig/sitestyle_detail.jsp?pageStatus=add&siteId=${param.stieId}',
                data:{"window":window}//把当前页面窗口传入下一个窗口，以便调用。
            });
        }
        //修改风格
        function modify() {
            var windows = top.$.dialog({
                width:600,
                height:300,
                lock:true,
                title:"修改风格",
                content:'url:app/webmanage/webconfig/sitestyle_detail.jsp?pageStatus=modify&siteId=${param.stieId}&id=' + Qm.getValueByName("id"),
                data:{"window":window}//把当前页面窗口传入下一个窗口，以便调用。
            });
        }
        //查看
        function detail() {
            var windows = top.$.dialog({
                width:600,
                height:300,
                lock:true,
                title:"查看风格",
                content:'url:app/webmanage/webconfig/sitestyle_detail.jsp?pageStatus=detail&id=' + Qm.getValueByName("id"),
                data:{"window":window}//把当前页面窗口传入下一个窗口，以便调用。
            });
        }
        //删除任务节点功能
        function del() {
            $.del("你是否要删除风格？", "webconfig/style/deleteStyle.do", {"ids":Qm.getValuesByName("id").toString()});
        }
        function setDefault() {
            $.post("webconfig/style/setDefault.do", {"ids":Qm.getValuesByName("id").toString(), "siteId":Qm.getValueByName("fk_site_config_id").toString()}, function (data) {
                if (data.success) {
                    $.ligerDialog.alert(data.msg, "success");
                    Qm.refreshGrid();
                } else {
                    $.ligerDialog.alert(data.msg, "error");
                }
            });
        }
        //设置风格和栏目，模板关联
        function setClassTemp() {
            var windows = top.$.dialog({
                width:800,
                height:400,
                lock:true,
                title:'设置模板',
                content:'url:app/webmanage/webconfig/setClassTemp.jsp?pageStatus=modify&styleId=' + Qm.getValueByName("id").toString() + '&siteId=' + Qm.getValueByName("fk_site_config_id").toString(),
                data:{"window":window}//把当前页面窗口传入下一个窗口，以便调用。
            });
        }

    </script>
</head>
<body>
<qm:qm pageid="web_style" script="false" singleSelect="false">
    <qm:param name="fk_site_config_id" compare="=" value="${param.stieId}"/>
</qm:qm>
</body>
</html>