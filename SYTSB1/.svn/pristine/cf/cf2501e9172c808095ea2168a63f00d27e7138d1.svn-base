<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="main_head">
    <!-- 每个页面引入，页面编码、引入js，页面标题 -->
    <%@include file="/k/kui-base-list.jsp" %>
    <title></title>
    <script type="text/javascript">
        var menu;
        var actionNodeID="";
        var Treenode="";

        $(function () {
            $("#toptoolbar").ligerToolBar({ items:[
                { text:'增加', click:itemclick, id:"add", icon:'add'},
                { line:true },
                { text:'修改', click:itemclick, id:"modify", icon:'edit'},
                { line:true },
                { text:'删除', click:itemclick, id:"del", icon:'delete'}
            ]
            });
            $("#layout1").ligerLayout({leftWidth:230, allowLeftCollapse:false, allowRightCollapse:false });
            Treenode = $("#tree1").ligerTree({
                checkbox:false,
                url:'webconfig/siteconfig/SiteCfgViewTree.do',
                attribute:["url"],
                nodeWidth:200,
                onAfterAppend:function () {
                    Treenode.selectNode("top");			//初始化
                },
                onSelect:function (node) {
                    actionNodeID = node.data.id;
                    $("#rightFrame").attr("src", "app/webmanage/webconfig/siteconfig_detail.jsp?pageStatus=detail&stieType=" + node.data.id);
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
                    $("#rightFrame").attr("src", "app/webmanage/webconfig/siteconfig_detail.jsp?pageStatus=add&pid=" + pid);
                    break;
                }
                case "modify": //修改
                {
                    if (actionNodeID == "") {
                        $.ligerDialog.error('请选择需要修改的节点!')
                        return false;
                    }
                    $("#rightFrame").attr("src", "app/webmanage/webconfig/siteconfig_detail.jsp?pageStatus=modify&stieType=" +actionNodeID);
                    break;
                }
                case "del": //删除
                {
                    if (actionNodeID == "") {
                        $.ligerDialog.error('请选择需要删除的节点!')
                        return false;
                    }
                    else {
                        $.post("/webconfig/siteconfig/deleteSiteCfg.do", {ids:actionNodeID}, function (data) {

                            if (data.success) {
                                var node = Treenode.getSelected();
                                if (node)
                                    Treenode.remove(node.target);
                                $("#rightFrame").attr("src", "app/webmanage/webconfig/siteconfig_detail.jsp?pageStatus=detail&stieType=top");
                                $.ligerDialog.alert(data.msg, "success");
                                actionNodeID="";
                            } else {
                                $.ligerDialog.alert(data.msg, "error");
                            }
                        });
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
    <div position="left" title="站点" style="overflow:auto;">
        <ul id="tree1"></ul>
    </div>
    <div position="center" title="详细配置">
        <iframe marginwidth="0" id="rightFrame" marginheight="0" frameborder="0" valign=top src="" name="rtree"
                width=100% height=100% scrolling="no" allowTransparency></iframe>
    </div>
</div>
</body>
</html>
