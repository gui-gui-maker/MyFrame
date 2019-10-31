<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>模板管理</title>
    <!-- 每个页面引入，页面编码、引入js，页面标题 -->
    <%@include file="/k/kui-base-list.jsp" %>
    <script type="text/javascript">
        var qmUserConfig = {
            //自定义查询面板样式参数
            tbar:[
                { text:'增加', id:'add', click:btn_op, icon:'add'},
                "-",
                { text:'修改', id:'modify', click:btn_op, icon:'edit' },
                "-",
                { text:'删除', id:'del', click:btn_op, icon:'delete' },
                "-",
                { text:'查看', id:'detail', click:btn_op, icon:'detail' },
                "-",
                { text:'刷新模板', id:'refreshTpl', click:btn_op, icon:'refresh' }
            ],
////            //提供以下4个事件
            listeners:{
                rowClick:function (rowData, rowIndex) {
                }, rowDblClick:function (rowData, rowIndex) {
                    //btn_op({id:'detail'});
                }, selectionChange:function (rowData, rowIndex) {
                    selectionChange()
                }
            }
        }
        
        //行选择改变事件
        function selectionChange() {
            var count = Qm.getSelectedCount();//行选择个数
            if (count == 0) {
                Qm.setTbar({add:true, modify:false, detail:false, del:false,refreshTpl:true});
            } else if (count == 1) {
                Qm.setTbar({add:true, modify:true, detail:true, del:true,refreshTpl:true});
            } else {
                Qm.setTbar({add:true, modify:false, detail:false, del:true,refreshTpl:true});
            }
        }
        function btn_op(item) {

            if (item.id == "refreshTpl") {
                $.post("tempmanage/temp/refreshTpl.do", function (data) {
                    if (data.success) {
                        $.ligerDialog.alert(data.msg, "success");
                    } else {
                        $.ligerDialog.alert(data.msg, "error");
                    }
                });
            }
            else if (item.id == "del") {
                $.del("你是否要删除？", "tempmanage/temp/delete.do", {"ids":Qm.getValuesByName("id").toString()});
            }
            else {
                width = top.$("body").width() * 0.8;
                height = top.$("body").height() * 0.8;
                var windows = top.$.dialog({
                    width:width,
                    height:height,
                    lock:true,
                    title:item.text,
                    content:'url:app/webmanage/tempmanage/temp_detail.jsp?tempType=${param.tempType}&pageStatus=' + item.id + "&styleId=${param.styleId}" + "&id=" + Qm.getValuesByName("id").toString(),
                    data:{"window":window}//把当前页面窗口传入下一个窗口，以便调用。
                });
            }
        }
    </script>
</head>
<body>
<qm:qm pageid="web_temp" script="false" singleSelect="false">
    <qm:param name="temp_type" compare="=" value="${param.tempType}"/>
    <qm:param name="fk_style_id" compare="=" value="${param.styleId}"/>
</qm:qm>
</body>
</html>
