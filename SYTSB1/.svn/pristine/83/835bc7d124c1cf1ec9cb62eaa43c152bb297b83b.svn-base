<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>留言列表</title>
    <%@include file="/k/kui-base-list.jsp" %>
	<style type="text/css">
		.l-icon-go {
		background: url("k/kui/skins/icons/go.gif")
		}
    </style>
    <script type="text/javascript">
        var qmUserConfig = {
            //自定义查询面板样式参数
            tbar:[
                { text:'回复', id:'modify', click:btn_op, icon:'go'},
                "-",
                { text:'删除', id:'del', click:btn_op , icon:'delete'},
                "-",
                { text:'查看', id:'detail', click:btn_op, icon:'detail' }
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
                    if(item.reply_time=="")
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
                Qm.setTbar({ modify:false, detail:false, del:false});
            } else if (count == 1) {
                Qm.setTbar({ modify:true, detail:true, del:true});
            } else {
                Qm.setTbar({ modify:false, detail:false, del:true});
            }
        }
        function btn_op(item) {
            if (item.id == "del") {
                //$.del("你是否要删除？", "infomanage/guestbook/deleteGuestbook.do", {"ids":Qm.getValuesByName("id").toString(),"isaudit":Qm.getValuesByName("isaudit").toString()});
            	if(window.confirm("确认要删除吗？")){
                	$.post("infomanage/guestbook/deleteGuestbook.do", 
                			{"ids":Qm.getValuesByName("id").toString(),"isaudit":Qm.getValuesByName("isaudit").toString()}, 
                			function (data) {
                				Qm.refreshGrid();
                    });
                	//执行删除操作
                }
            }
            else {
                width = top.$("body").width() * 0.8;
                height = top.$("body").height() * 0.8;
                var windows = top.$.dialog({
                    width:width,
                    height:height,
                    lock:true,
                    title:item.text + "${param.tempText}",
                    content:'url:app/webmanage/infomanage/guestbook_detail.jsp?&pageStatus=' + item.id + "&id=" + Qm.getValueByName("id"),
                    data:{"window":window}//把当前页面窗口传入下一个窗口，以便调用。
                });
            }
        }
    </script>
</head>
<body>
<qm:qm pageid="web_gbook" script="false" singleSelect="false">
    <qm:param name="fk_classtype_id" compare="=" value="${param.classId}"/>
</qm:qm>

</body>
</html>
