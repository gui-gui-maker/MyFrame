<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>投票列表</title>
    <%@include file="/k/kui-base-list.jsp" %>
    <script type="text/javascript">
        var qmUserConfig = {
            //自定义查询面板样式参数
            tbar:[
                { text:'新增', id:'add', click:btn_op },
                "-",
                { text:'修改', id:'modify', click:btn_op },
                "-",
                { text:'删除', id:'del', click:btn_op },
                "-",
                { text:'查看', id:'detail', click:btn_op }
            ],
////            //提供以下4个事件
            listeners:{
                rowClick:function (rowData, rowIndex) {
                }, rowDblClick:function (rowData, rowIndex) {
                    //btn_op("{id:'detail'}");
                }, selectionChange:function (rowData, rowIndex) {
                    selectionChange()
                },
                rowAttrRender:function (item, rowid) {
                    if(item.is_off=="打开")
                        return "style='color:red'";
                    else
                        return "";
                }
                }
            }
        //行选择改变事件
        function selectionChange() {
            var count = Qm.getSelectedCount();//行选择个数
            if (count == 0) {
                Qm.setTbar({ add:true, modify:false, detail:false, del:false});
            } else if (count == 1) {
                Qm.setTbar({add:true, modify:true, detail:true, del:true});
            } else {
                Qm.setTbar({ add:true,modify:false, detail:false, del:true});
            }
        }
        function btn_op(item) {
            if (item.id == "del") {
                $.del("你是否要删除？", "infomanage/vote/deleteVote.do", {"ids":Qm.getValuesByName("id").toString(),"isOff":Qm.getValuesByName("is_off").toString()});
            }
            else {
                var windows = top.$.dialog({
                    width:730,
                    height:480,
                    lock:true,
                    title:item.text + "${param.tempText}",
                    content:'url:app/webmanage/infomanage/vote_detail.jsp?&classId=${param.classId}&pageStatus=' + item.id + "&id=" + Qm.getValueByName("id"),
                    data:{"window":window}//把当前页面窗口传入下一个窗口，以便调用。
                });
            }
        }
    </script>
</head>
<body>
<qm:qm pageid="web_vote" script="false" singleSelect="false">
    <qm:param name="fk_classtype_id" compare="=" value="${param.classId}"/>
</qm:qm>

</body>
</html>
