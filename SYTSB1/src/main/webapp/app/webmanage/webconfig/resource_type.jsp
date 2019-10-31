<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="main_head">
    <!-- 每个页面引入，页面编码、引入js，页面标题 -->
    <%@include file="/k/kui-base-list.jsp" %>
    <title></title>
    <script type="text/javascript">
        var siteId;
        var actionNodeID = "";
        var Treenode = "";
        var fkId;

        $(function () {
            $("#toptoolbar").ligerToolBar({ items:[
                { text:'增加', click:itemclick, id:"add", icon:'add'},
                { line:true },
                { text:'修改', click:itemclick, id:"modify", icon:'edit'},
                { line:true },
                { text:'删除', click:itemclick, id:"del", icon:'delete'}
            ]
            });
            $("#layout1").ligerLayout({leftWidth:200, allowLeftCollapse:false, allowRightCollapse:false });
            Treenode = $("#tree1").ligerTree({
                checkbox:false,
                url:'webconfig/resourcetype/RecourceTypeTree.do?siteId=${param.siteId}',
                attribute:["url"],
                onAfterAppend:function (parentNode, newdata) {
                    //alert(Treenode.getParent(""));
                    Treenode.selectNode(newdata);			//初始化
                },
                onSelect:function (node) {
                    actionNodeID = node.data.id;
                    siteId = node.data.siteId;
                    fkId = node.data.pid;
                    $("#rightFrame").attr("src", "app/webmanage/webconfig/resource_detail.jsp?pageStatus=detail&resource=" + node.data.id);
                }
            });


        });

        function itemclick(item) {

            switch (item.id) {
                case "add": //新增
                {
                    if (actionNodeID == "") {
                        $.ligerDialog.error('请选择父节点!')
                        return false;
                    }
                    $("#rightFrame").attr("src", "app/webmanage/webconfig/resource_detail.jsp?pageStatus=add&pid=" + actionNodeID + "&siteId=" + siteId);
                    break;
                }
                case "modify": //修改
                {
                    if (actionNodeID == "") {
                        $.ligerDialog.error('请选择需要修改的节点!')
                        return false;
                    }
                    $("#rightFrame").attr("src", "app/webmanage/webconfig/resource_detail.jsp?pageStatus=modify&resource=" + actionNodeID + "&siteId=" + siteId);
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
                            $.post("webconfig/resourcetype/delete.do", {ids:actionNodeID}, function (data) {

                                if (data.success) {
                                    var node = Treenode.getSelected();
                                    if (node)
                                        Treenode.remove(node.target);
                                    $("#rightFrame").attr("src", "app/webmanage/webconfig/resource_detail.jsp?pageStatus=detail&resource=top");
                                    actionNodeID = "";
                                } else {
                                    $.ligerDialog.alert('删除失败', "error");
                                }
                            });
                        }

                    }

                }
            }
        }

    </script>
</head>
<body>

<div id="toptoolbar"></div>

<div id="layout1">
    <div position="left" title="资源分类" style="overflow:auto;">
        <ul id="tree1"></ul>
    </div>
    <div position="center" title="分类信息" style="padding: 10px">
        <iframe marginwidth="0" id="rightFrame" marginheight="0" frameborder="0" valign=top src="" name="rtree"
                width=100% height=100% scrolling="no" allowTransparency></iframe>
    </div>
</div>
</body>
</html>
