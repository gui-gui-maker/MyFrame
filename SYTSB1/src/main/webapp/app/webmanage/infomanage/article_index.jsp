<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="main_head">
    <%@include file="/k/kui-base-list.jsp" %>
    <title></title>
    <script type="text/javascript">
        var menu;
        var actionNodeID = "";
        var Treenode = "";
        var siteID = "";
        var classGroup = "";
        $(function () {
            $("#layout1").ligerLayout({leftWidth:230, allowLeftCollapse:false, allowRightCollapse:false });
            Treenode = $("#tree1").ligerTree({
                checkbox:false,
                url:'infomanage/classtype/ClassViewTree.do',
                attribute:["url"],
                btnClickToToggleOnly:false,
                nodeWidth:150,
                slide:true,
                idFieldName :'id',
                parentIDFieldName :'pid',
                onAfterAppend:function (parentNode, newdata) {
                    Treenode.selectNode(newdata);			//初始化
                },
                onSelect:function (node) {
                    actionNodeID = node.data.id;
                    siteID = node.data.site;
                    classGroup = node.data.classGroup;
                    //alert("guestbook" + classGroup)
                    if ("guestbook" == classGroup) {//留言
                        $("#rightFrame").attr("src", "app/webmanage/infomanage/guestbook_index.jsp?&classId=" + node.data.id + "&siteID=" + siteID);
                    } else if ("vote" == classGroup) {//投票
                        $("#rightFrame").attr("src", "app/webmanage/infomanage/vote_index.jsp?&classId=" + node.data.id + "&siteID=" + siteID);
                    } else if ("questionnaire" == classGroup) {//问卷
                        $("#rightFrame").attr("src", "app/webmanage/infomanage/questionnaire_index.jsp?&classId=" + node.data.id + "&siteID=" + siteID);
                    } else if("setunit" == classGroup){
                    	 $("#rightFrame").attr("src", "app/webmanage/infomanage/questionnaire_index.jsp?&classId=" + node.data.id + "&siteID=" + siteID);
                    }else{//内容
                        $("#rightFrame").attr("src", "app/webmanage/infomanage/article_list.jsp?&classId=" + node.data.id + "&siteID=" + siteID);
                    }
                }
            });
        });
    </script>
</head>
<body>
<div id="toptoolbar"></div>
<div id="layout1">
    <div position="left" title="栏目" style="overflow:auto;">
        <ul id="tree1"></ul>
    </div>
    <div position="center" title="文章列表">
        <iframe marginwidth="0" id="rightFrame" marginheight="0" frameborder="0" valign=top src="" name="rtree"
                width=100% height=100% scrolling="no" allowTransparency></iframe>
    </div>
</div>
</body>
</html>
