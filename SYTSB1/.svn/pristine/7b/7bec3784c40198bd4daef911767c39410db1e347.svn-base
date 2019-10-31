<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="main_head">
    <%@include file="/k/kui-base-list.jsp" %>
    <title></title>
    <script type="text/javascript">
        var menu;
        var actionNodeID = "";
        var siteID = "";//站点id
        var fkId = "";//父id
        var Treenode = "";
        $(function () {
            $("#toptoolbar").ligerToolBar({ items:[
                { text:'增加', click:itemclick, id:"add", icon:'add'},
                { line:true },
                { text:'修改', click:itemclick, id:"modify", icon:'edit'},
                { line:true },
                { text:'删除', click:itemclick, id:"del", icon:'delete'}
            ]
            });
            $("#layout1").ligerLayout({leftWidth:340, allowLeftCollapse:false, allowRightCollapse:false });
            Treenode = $("#tree1").ligerTree({
                checkbox:false,
                url:'infomanage/classtype/ClassViewTree.do',
                attribute:["url"],
                btnClickToToggleOnly:false,
                nodeWidth:200,
                slide:true,
                idFieldName :'id',
                parentIDFieldName :'pid',
                onAfterAppend:function (parentNode, newdata) {

                    Treenode.selectNode(newdata);			//初始化
                },
                onSelect:function (node) {
                	
                    actionNodeID = node.data.id;
                    siteID = node.data.site;
                    fkId = node.data.pid;
                    $("#rightFrame").attr("src", "app/webmanage/infomanage/classtype_detail.jsp?pageStatus=detail&classId=" + node.data.id);
                }
            });

        });
        function itemclick(item) {
            //alert(item.id)
            switch (item.id) {
                case "add": //增加
                {
                    if (actionNodeID == "") {
                        $.ligerDialog.error('请选择父节点!')
                        return false;
                    }
                    var pid = actionNodeID == "" ? "top" : actionNodeID;
                    $("#rightFrame").attr("src", "app/webmanage/infomanage/classtype_detail.jsp?pageStatus=add&pid=" + pid + "&siteID=" + siteID);
                    break;
                }
                case "modify": //修改
                {
                    if (actionNodeID == "") {
                        $.ligerDialog.error('请选择需要修改的节点!')
                        return false;
                    }
                    $("#rightFrame").attr("src", "app/webmanage/infomanage/classtype_detail.jsp?pageStatus=modify&classId=" + actionNodeID + "&siteID=" + siteID);
                    break;
                }
                case "del": //删除
                {
                    if (actionNodeID == "") {
                        $.ligerDialog.error('请选择需要删除的节点!')
                        return false;
                    }
                    else {
                        if (fkId == "top") {
                            $.ligerDialog.error('不能删除站点!')
                            return false;
                        } else {
                        	alert(1);
                            $.post("infomanage/classtype/deleteClassType.do", {ids:actionNodeID}, function (data) {
                                if (data.success) {
                                    var node = Treenode.getSelected();
                                    if (node)
                                        Treenode.remove(node.target);
                                    $("#rightFrame").attr("src", "app/webmanage/infomanage/classtype_detail.jsp?pageStatus=detail&classId=top");
                                    $.ligerDialog.alert(data.msg, "success");
                                    actionNodeID = "";
                                } else {
                                    $.ligerDialog.alert(data.msg, "error");
                                }
                            });
                        }
                    }
                    break;
                }
            }
        }
    </script>
</head>
<body>
<div id="toptoolbar"></div>
<div id="layout1">
    <div position="left" title="栏目" style="overflow:auto;">
        <ul id="tree1"></ul>
    </div>
    <div position="center" title="详细内容">
        <iframe marginwidth="0" id="rightFrame" marginheight="0" frameborder="0" valign=top src="" name="rtree"
                width=100% height=100% scrolling="no" allowTransparency></iframe>
    </div>
</div>
</body>
</html>
