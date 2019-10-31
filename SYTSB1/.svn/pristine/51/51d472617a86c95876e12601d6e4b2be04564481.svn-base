<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="main_head">
    <!-- 每个页面引入，页面编码、引入js，页面标题 -->
    <%@include file="/k/kui-base-list.jsp"%>
    <title></title>
    <script type="text/javascript">
        var menu;
        var actionNodeID;
        var Treenode="";
        $(function () {
            $("#layout1").ligerLayout({leftWidth:230, allowLeftCollapse:false, allowRightCollapse:false });
            Treenode =$("#tree1").ligerTree({
                checkbox:false,
                collapseAll:true,
                url:'tempmanage/temp/SiteStyleTplTree.do',
                attribute:["url"],
                nodeWidth:200,
                onAfterAppend:function (parentNode, newdata) {
                    Treenode.selectNode(newdata);			//初始化
                },
                onSelect:function (node) {
                    var parentId =node.data.prentId;
                    $("#rightFrame").attr("src","app/webmanage/tempmanage/temp_list.jsp?tempType="+node.data.id+"&styleId="+parentId);
                }
            });
        });

    </script>
</head>
<body>
<div id="layout1">
    <div position="left" title="模板类型" style="overflow:auto;">
        <ul id="tree1"></ul>
    </div>
    <div position="center" title="模板">
        <iframe marginwidth="0" id="rightFrame" marginheight="0" frameborder="0" valign=top src="" name="rtree"
                width=100% height=100% scrolling="no" allowTransparency></iframe>
    </div>
</div>
</body>
</html>
