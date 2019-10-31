<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="main_head">
<%@include file="/k/kui-base-list.jsp"%>
<title></title>
<script type="text/javascript">
    $(function() {
        $("#layout1").ligerLayout({
            leftWidth : 200,
            space : 4,
            allowLeftCollapse : false,
            allowRightCollapse : false
        });
        $("#tree1").ligerTree({
            checkbox: false,
            selectCancel : false,
            idFieldName: "id",
            parentIDFieldName: "parentId",
            textFieldName: "typeName",
            url : 'bpm/flowType/viewTree.do',
            onSelect: function(node) {
                var win = $("#rightFrame").get(0).contentWindow.window;
                win.loadGridData(node.data.id);
            }
        });
    });

    //确定选择
    function getSelect() {
        return $("#rightFrame").get(0).contentWindow.window.getSelect();
    }
</script>
</head>
<body class="p5">
    <div id="layout1">
        <div position="left" title="流程分类" class="overflow-auto">
            <ul id="tree1"></ul>
        </div>
        <div position="center">
            <iframe id="rightFrame" frameborder="0"
                src="pub/bpm/flow_definition_select_list.jsp" name="rtree"
                width=100% height=100% scrolling="no"></iframe>
        </div>
    </div>
</body>
</html>