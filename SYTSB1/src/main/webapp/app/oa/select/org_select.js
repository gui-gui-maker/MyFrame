var __org__tree;
var __org__tree_dlg;
var __last_select_area__tree_value;
var __combo;
var unitId = "";
var unitName = "";
document.write('<style type="text/css">.l-tree .l-tree-icon-none img {height: 16px;margin: 3px;width: 16px;}</style>');
function showOrgList() {
	unitId = $(this).data("unitId");
	unitName = $(this).data("unitName");
    __combo = this;
    if (__org__tree_dlg) {
        __org__tree_dlg.show();
    } else {
        __org__tree_dlg = $.ligerDialog.open({ title: '单位/部门选择', width: 300, height: 450, content: "<div id='__org__tree_div'></div>", buttons: [
            {
                text: '确定', onclick: function (item, dialog) {
                    var selected = __org__tree.getSelected();
                    var data={};
                    if(selected!=null&&selected!=undefined&&selected!=""){
                    	data = selected.data;
                    }else{
                    	data["id"]="";
                    	data["text"]="";
                    }
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
        __org__tree = $("#__org__tree_div").ligerTree({
            checkbox: false,
            attribute: [ "pid", "fullName" ],
            iconFieldName: "levels",
            iconParse: function (data) {
            	if(data["levels"]==0)
               		return "k/kui/images/icons/16/home.png";
            	else if(data["levels"]==1)
                  	return "k/kui/images/icons/16/org.png";
            	else
                	return "k/kui/images/icons/16/group.png";
            },
            onBeforeExpand: function (node) {
                if (node.data.children && node.data.children.length == 0) {
                    __org__tree.loadData(node.target,"rbac/org/getSubordinate.do?orgid=" + node.data.id);
                }
            }
        });
        $.getJSON("rbac/org/getSubordinate.do?orgid=" + unitId,function(dataList){
        	__org__tree.append(unitId,[{
    			id : unitId,
    			text : unitName,
    			levels : "0",
    			children : dataList
    		}]);
    	});
    }
    return false;
}


