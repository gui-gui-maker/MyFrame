<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>问卷调查列表</title>
    <%@include file="/k/kui-base-list.jsp" %>
    <script type="text/javascript">
        var qmUserConfig = {
        		sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	        		
            //自定义查询面板样式参数
            sp_fields:[
                {name:"name", compare:"=", value:"" },
                {name:"identity", compare:"=", value:"" },
                {name:"yfxz", compare:"=", value:"" },
                {name:"ywnl", compare:"=", value:"" },
                {name:"fwtd", compare:"=", value:"" },
                {name:"gzxl", compare:"=", value:"" },
                {name:"gzzf", compare:"=", value:"" },
                {name:"zwgk", compare:"=", value:"" },
                { name:"ljzl", compare:"=", value:"" }
            ],
            tbar:[
                { text:'删除', id:'del', click:btn_op, icon:'del'},
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
                }

            }
        }
        //行选择改变事件
        function selectionChange() {
            var count = Qm.getSelectedCount();//行选择个数
            if (count == 0) {
                Qm.setTbar({ detail:false, del:false});
            } else if (count == 1) {
                Qm.setTbar({detail:true, del:true});
            } else {
                Qm.setTbar({detail:false, del:true});
            }
        }
        function btn_op(item) {
            if (item.id == "del") {
                $.del("你是否要删除？", "infomanage/questionnaire/delete.do", {"ids":Qm.getValuesByName("id").toString()});
            }
            else {
                var windows = top.$.dialog({
                    width:730,
                    height:580,
                    lock:true,
                    title:item.text + "${param.tempText}",
                    content:'url:app/webmanage/infomanage/questionnaire_detail.jsp?id=' + Qm.getValueByName("id"),
                    data:{"window":window}//把当前页面窗口传入下一个窗口，以便调用。
                });
            }
        }
    </script>
</head>
<body>
<qm:qm pageid="web_quest" singleSelect="false">
    <qm:param name="fk_classtype_id" compare="=" value="${param.classId}"/>
</qm:qm>

</body>
</html>
