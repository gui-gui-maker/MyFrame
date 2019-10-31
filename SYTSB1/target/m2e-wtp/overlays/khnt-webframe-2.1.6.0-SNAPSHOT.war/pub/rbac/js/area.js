var __area__tree;
var __area__tree_dlg;
var __last_select_area__tree_value;
var __last_select_area__tree_text;
var __combo;
var defaultAreacode=kFrameConfig.defaultAreaCode||'000000';
document.write('<style type="text/css">.l-tree .l-tree-icon-none img {height: 16px;margin: 3px;width: 16px;}</style>');
function showAreaList(callback) {
    __combo = this;
    var areacode = $(this).data("areacode");
    if (areacode == undefined || areacode=="null" || areacode==null) {
        areacode = this.get("areacode");
        if (areacode == undefined) {
            areacode = defaultAreacode;
        }
    }
    var url = "rbac/area/getAreaTreeWithAsync.do?code=" + areacode;
    
    var multiple = $(this).data("multiple")||false;
    var levels = $(this).data("levels");
    if (levels != undefined && levels!="null" && levels!=null) {
    	url = "rbac/area/getAreaTreeByCode1.do?code="+areacode+"&levels="+levels;
    }
    if (__area__tree_dlg) {
        __area__tree_dlg.show();
    } else {
        __area__tree_dlg = $.ligerDialog.open({ title: '选择地址', width: 350, height: 300, content: "<div id='__area__tree_div'></div>", buttons: [
            {
                text: '确定', onclick: function (item, dialog) {
                	if(!multiple){
                		var selected = __area__tree.getSelected();
                        if (!selected) {
                            $.ligerDialog.warn('请选择值！');
                            return;
                        }
                        var data = __area__tree.getSelected().data;
                        __last_select_area__tree_value = data["code"];
                        try{
                        	__combo._changeValue(data["code"], data["fullName"]);
                        }catch(e){}
                        if(callback){
                        	callback(data["code"],data["fullName"]);
                        }
                	}else{
                		var checked = __area__tree.getChecked();
                        if (checked.length<1) {
                            $.ligerDialog.warn('请选择值！');
                            return;
                        }
                        var data = __area__tree.getChecked();
                        __last_select_area__tree_value = "";
                        __last_select_area__tree_text = "";
                        
                        for(var i in data){
                        	__last_select_area__tree_value += data[i].data.code+",";
                        	__last_select_area__tree_text += data[i].data.text+",";
                        }
                        var scode = __last_select_area__tree_value.substring(0,__last_select_area__tree_value.length-1);
                        var stext = __last_select_area__tree_text.substring(0,__last_select_area__tree_text.length-1);
                        try{
                        	__combo._changeValue(scode, stext);
                        }catch(e){}
                        if(callback){
                        	callback(scode,stext);
                        }
                	}
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
            checkbox: multiple,
            url: url,
            attribute: [ "pid", "fullName" ],
            iconFieldName: "levels",
            iconParse: function (data) {
                return imgUrl + data["levels"] + ".png";
            },
            onBeforeExpand: function (node) {
            	if (levels == undefined || levels=="null" || levels==null) {
            		if (node.data.children && node.data.children.length == 0) {
                        __area__tree.loadData(node.target, "rbac/area/getAreaTreeByPid.do?id=" + node.data.id);
                    }
            	}
            }
        });
    }
    return false;
}

function showAreaListBy() {
}
