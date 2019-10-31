<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>信息列表</title>
    <%@include file="/k/kui-base-list.jsp" %>
    <style type="text/css">
        .l-icon-html {
            background: url("k/kui/skins/icons/html.gif") no-repeat center
        }

            /*.l-icon-go {*/
            /*background: url("k/kui/skins/icons/go.gif")*/
            /*}*/
        .l-icon-forbid {
            background: url("k/kui/skins/icons/forbid.gif") no-repeat center
        }
    </style>
    <script type="text/javascript">
        var qmUserConfig = {
            //自定义查询面板样式参数
            tbar:[
                { text:'增加', id:'add', click:btn_op, icon:'add'},
                "-",
                { text:'修改', id:'modify', click:btn_op, icon:'edit'},
                "-",
                { text:'删除', id:'del', click:btn_op, icon:'delete' },
                "-",
                { text:'查看', id:'detail', click:btn_op, icon:'detail'}
//                "-",
//                { text:'头条', id:'top', click:btn_op, icon:'up'},

               
                ,"-",
                { text:'发布', id:'issued', click:btn_op, icon:'html'},
                "-",
                { text:'取消发布', id:'unIssued', click:btn_op, icon:'forbid'}
               

            ],
////            //提供以下4个事件
            listeners:{
                rowClick:function (rowData, rowIndex) {
                }, selectionChange:function (rowData, rowIndex) {
                    selectionChange()
                },
                rowAttrRender:function (item, rowid) {
                    if (item.audit_off == "")
                        return "style='color:blue'";
                    else
                        return "";

                }
            }
        }
        //行选择改变事件
        function selectionChange() {
            var count = Qm.getSelectedCount();//行选择个数
            if (count == 0) {
                Qm.setTbar({add:true, modify:false, detail:false, del:false, top:false, issued:false, unIssued:false});
            } else if (count == 1) {
                Qm.setTbar({add:true, modify:true, detail:true, del:true, top:true, issued:true, unIssued:true});
            } else {
                Qm.setTbar({add:true, modify:false, detail:false, del:true, top:false, issued:true, unIssued:true});
            }
        }
        function btn_op(item) {
            if (item.id == "del") {
                //$.del("你是否要删除？", "infomanage/article/deleteArticle.do", {"ids":Qm.getValuesByName("id").toString()});
                if(window.confirm("确认要删除吗？")){
                	$.post("infomanage/article/deleteArticle.do", 
                			{"ids":Qm.getValuesByName("id").toString()}, 
                			function (data) {
                				Qm.refreshGrid();
                    });
                	//执行删除操作
                }
            }
            else if (item.id == "issued") {
                //alert(1);
            	//$("body").mask("正在发布,请稍候……");
                $.post("mark/Issued.do", {"classId":"${param.classId}", "acId":Qm.getValuesByName("id").toString()}, function (data) {
                    //$("body").unmask();
                    Qm.refreshGrid();
                    if (data.success) {
                        $.ligerDialog.alert('发布成功！', "success");
                    } else {
                        $.ligerDialog.alert('发布失败！', "error");
                    }
                });
            }
            else if (item.id == "unIssued") {
                //$("body").mask("正在取消发布,请稍候……");

                $.post("mark/unIssued.do", {"classId":"${param.classId}", "acId":Qm.getValuesByName("id").toString()}, function (data) {
                    //$("body").unmask();
                    Qm.refreshGrid();
                    if (data.success) {
                        $.ligerDialog.alert('取消成功！', "success");
                    } else {
                        $.ligerDialog.alert('取消失败！', "error");
                    }

                });
            }
            else {
                width = top.$("body").width() * 0.8;
                height = top.$("body").height() * 0.9;
                var windows = top.$.dialog({
                    width:width,
                    height:height,
                    lock:true,
                    title:item.text + "${param.tempText}",
                    data:{"window":window},//把当前页面窗口传入下一个窗口，以便调用。
                    content:'url:app/webmanage/infomanage/article_detail.jsp?classId=${param.classId}&siteID=${param.siteID}&pageStatus=' + item.id + "&id=" + Qm.getValueByName("id")
                   
                });
            }
        }



    </script>
</head>
<body>
<qm:qm pageid="web_art1" script="false" singleSelect="false">
    <qm:param name="fk_classtype_id" compare="=" value="${param.classId}"/>
</qm:qm>
</body>
</html>
