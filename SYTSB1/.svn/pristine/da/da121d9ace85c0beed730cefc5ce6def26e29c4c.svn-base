var __area__tree;
var __area__tree_dlg;
var __last_select_area__tree_value;
var __combo;
document.write('<style type="text/css">.l-tree .l-tree-icon-none img {height: 16px;margin: 3px;width: 16px;}</style>');
function showDepartmentList() {
    __combo = this;
    var areacode = $(this).data("areacode");
    if (areacode == undefined) {
        areacode = this.get("areacode");
        if (areacode == undefined) {
            areacode = "0046";
        }

    }
    if (__area__tree_dlg) {
        __area__tree_dlg.show();
    } else {
        __area__tree_dlg = $.ligerDialog.open({ title: '选择部门', width: 350, height: 300, content: "<div id='__area__tree_div'></div>", buttons: [
            {
                text: '确定', onclick: function (item, dialog) {
                    var selected = __area__tree.getSelected();
                    if (!selected) {
                        $.ligerDialog.warn('请选择值！');
                        return;
                    }
                    var data = __area__tree.getSelected().data;
                    __last_select_area__tree_value = data["id"];
                    __combo._changeValue(data["id"], data["text"]);
                    dialog.hide();
                }
            },
            {
                text: '取消', onclick: function (item, dialog) {
                    dialog.hide();
                }
            }
        ]
        });
        var imgUrl = "pub/rbac/img/";
        __area__tree = $("#__area__tree_div").ligerTree({
            checkbox: false,
            url: 'rbac/org/getSubordinate.do?orgid='+ areacode,
            attribute: [ "pid", "fullName" ],
            iconFieldName: "levels",
            iconParse: function (data) {
                return imgUrl + data["levels"] + ".png";
            },
            onBeforeExpand: function (node) {
                if (node.data.children && node.data.children.length == 0) {
                    __area__tree.loadData(node.target,"rbac/org/getSubordinate.do?orgid=" + node.data.id);
                }
            }
        });
    }
    return false;
}


