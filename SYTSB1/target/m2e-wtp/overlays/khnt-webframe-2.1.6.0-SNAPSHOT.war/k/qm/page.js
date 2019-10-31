var win;
var pageadd = {
    id: 'pageadd',
    text: '添加',
    iconCls: 'add',
    handler: function () {
        openPage(true);
        fs.getForm().reset();
        Ext.get("_pageid").dom.readOnly = false;
        Ext.get("isnewpage").dom.value = "true";
    }
};
var pageaddMobil = {
    id: 'addmobile',
    text: '添加jqm',
    iconCls: 'addmobile',
    handler: function () {
        openPage(false);
        fs.getForm().reset();
        Ext.get("_pageid").dom.readOnly = false;
        Ext.get("isnewpage").dom.value = "true";
    }
};
var pageaddMobil_mui = {
	    id: 'addmobile_mui',
	    text: '添加mui',
	    iconCls: 'addmobile',
	    handler: function () {
	        openPage(false,true);
	        fs.getForm().reset();
	        Ext.get("_pageid").dom.readOnly = false;
	        Ext.get("isnewpage").dom.value = "true";
	    }
	};

var pageedit = {
    id: 'pageedit',
    text: '修改',
    iconCls: 'edit',
    handler: function () {
        rowDblClickFun();
    }
};
var pagedatadel = {
    id: 'pagedatadel',
    text: '删除',
    disabled: true,
    iconCls: 'delete',
    handler: function () {
        pageDelete();
    }
};
var repageconfig = {
    id: 'repageconfig',
    text: '重新生成配置文件',
    disabled: true,
    iconCls: 'config',
    handler: function () {
        rePageConfig();
    }
};
var pagefieldset = {
    id: 'pagefieldset',
    text: '字段设置',
    disabled: true,
    iconCls: 'pageset',
    handler: function () {
        openPagecol(Qm.getValueByName("pageid"));
    }
};
var pagepreview = {
    id: 'pagepreview',
    text: '预览',
    disabled: true,
    iconCls: 'view',
    handler: function () {
        preview(Qm.getValueByName("pageid"),Qm.getValueByName("types"));
    }
};
var pagecopy = {
    id: 'pagecopy',
    text: '复制',
    disabled: true,
    iconCls: 'copy',
    handler: function () {
        pageCopy();
    }
};

var tool = {
    text: '工具',
    iconCls: 'tool',
    menu: [
        {id: 'pageexport', text: '导出数据', disabled: true, iconCls: 'export', handler: exportPageDef},
        "-",
        {text: '导入数据', iconCls: 'import', handler: importPageDef},
        "-",
        {text: '生成示例数据', iconCls: 'demo_add', handler: createDemo},
        '-',
        {text: '删除示例数据', iconCls: 'demo_delete', handler: deleteDemo},
        '-',
        {text: '帮助', iconCls: 'help'}
    ]};
var cacheTool = {
    text: '清除缓存',
    iconCls: 'demo_delete',
    menu: [
        {id: 'deleteCache', text: '清除选中缓存', disabled: true, iconCls: 'demo_delete', handler: function(){opCache("deleteCache");}},
        {text: '清除所有缓存', iconCls: 'demo_delete', handler: function(){opCache("deleteAllCache");}},
        "-",
        {id: 'removeCache', text: '清除选中缓存及个性配置',disabled: true, iconCls: 'demo_delete', handler: function(){opCache("removeCache");}},
        {text: '清除所有缓存及个性配置', iconCls: 'demo_delete', handler: function(){opCache("removeAllCache");}}
    ]};

function selectionChangeFun() {
    var c = Qm.getSelectedCount();
    if (c == 0) {
        Qm.setTbar("pagedatadel,pageedit,pagefieldset,pagepreview,pagecopy,pageexport,deleteCache,removeCache", "0,0,0,0,0,0,0,0,0");
    } else if (c == 1) {
        Qm.setTbar("pagedatadel,pageedit,pagefieldset,pagepreview,pagecopy,pageexport,deleteCache,removeCache", "1,1,1,1,1,1,1,1,1");
    } else {
        Qm.setTbar("pagedatadel,pageedit,pagefieldset,pagepreview,pagecopy,pageexport,deleteCache,removeCache", "1,0,0,0,0,1,1,1,1");
    }
}

function rowDblClickFun() {
    var isWeb = Qm.getValueByName("types") == "0";
    var ismui = Qm.getValueByName("types") == "2"
    openPage(isWeb,ismui);
    fs.getForm().reset();
    Ext.get("_pageid").dom.readOnly = true;
    Ext.get("isnewpage").dom.value = "false";
    Ext.Ajax.request({
        url: Qm.pagingUrlPre + 'pageDef&pageid=' + Qm.getValueByName("pageid"),
        callback: function (options, success, response) {
            var obj = Ext.util.JSON.decode(response.responseText);
            fs.getForm().setValues(obj);
            if (obj.options)    {
                fs.getForm().setValues(obj.options);
            }
        }
    });
}
var exportIframe;
function exportPageDef() {
    var url = Qm.pagingUrlPre + "exportPageDef&pageid=" + Qm.getValuesByName("pageid").toString();
    if (!exportIframe) {
        exportIframe = document.createElement("iframe");
        exportIframe.width = 0;
        exportIframe.height = 0;
        exportIframe.style.display = "none";
        Ext.getBody().dom.appendChild(exportIframe);
    }
    exportIframe.src = url;
}
var fs;
var _isWeb = true;
function openPage(isWeb,mui) {
//    if (!win) {
    _isWeb = isWeb;
    var items = [
        {
            items: {
                id: 'isnewpage',
                xtype: 'hidden'
            }
        },
        {
            items: {
                name: 'types',
                xtype: 'hidden',
                value: isWeb ? "0" : !mui?"1":"2"
            }
        },
        {
            items: { anchor: '100%',
                fieldLabel: '编号',
                id: '_pageid',
                name: 'pageid',
                allowBlank: false,
                maxLength: 50,
                xtype: 'textfield'
            }
        },
        {
            items: { anchor: '100%',
                fieldLabel: '名称',
                name: 'pagename',
                maxLength: 50,
                xtype: 'textfield'
            }
        },
        {
            items: { anchor: '100%',
                fieldLabel: '数据库表名',
                name: 'dbtablename',
                maxLength: 100,
                xtype: 'textfield'
            }
        },
        {
            items: {  anchor: '100%',
                fieldLabel: 'SQL',
                name: 'pagesql',
                maxLength: 4000,
                height: '120',
                grow:true,
                allowBlank: false,
                xtype: 'textarea'
            }
        }
    ];
    if (isWeb) {
        items.push({
            items: { anchor: '100%',
                fieldLabel: 'Script',
                name: 'script',
                maxLength: 1000,
                grow:true,
                xtype: 'textarea'
            }
        });
    }
    items.push({   columnWidth: 0.5,
        items: {
            fieldLabel: '每页记录数',
            name: 'pagesize',
            maxValue: 100,
            value: 20,
            minValue: 1,
            allowBlank: false,
            xtype: 'numberfield'
        }
    });
    if (isWeb) {
        items.push({  columnWidth: 0.5,
                items: {
                    fieldLabel: '工具条',
//                        name: 'toolbartype',
                    allowBlank: false,
                    xtype: 'combo',
                    store: new Ext.data.ArrayStore({
                        fields: ['code', 'value'],
                        data: [
                            ["0", "默认"],
                            ["1", "分页条和按钮在上方"],
                            ["2", "分页条和按钮在下方"],
                            ["3", "仅按钮在上方"],
                            ["4", "仅按钮在下方"]
                        ]
                    }),
                    displayField: 'value',
                    valueField: 'code',
                    editable: false,
                    mode: 'local',
                    forceSelection: true,
                    triggerAction: 'all',
                    value: '0',
                    selectOnFocus: true,
                    allowBlank: false,
                    hiddenName: 'toolbartype'
                }
            },
            {
                items: {
                    fieldLabel: '缺省查询条件',
                    name: 'defaultsearch',
                    xtype: 'hidden'
                }
            },
            {labelWidth: 500,
                items: {
                    fieldLabel: '允许在列表上显示高级组合查询条件<font color="red">（注意：当在页面自定义了查询面板时，此项被忽略）</font>',
                    name: 'advancedPanel',
                    xtype: 'checkbox',
                    inputValue: 'true'
                }
            },
            {columnWidth: 0.33,
                items: {
                    fieldLabel: '高级查询、排序',
                    name: 'advanced',
                    xtype: 'checkbox',
                    inputValue: 'true'
                }
            },
            {columnWidth: 0.33,
                items: {
                    fieldLabel: '导出Excel',
                    name: 'expexcel',
                    xtype: 'checkbox',
                    inputValue: 'true'
                }
            },//
            {columnWidth: 0.33,
                items: {
                    fieldLabel: '会话期内保存条件',
                    name: 'sessionCon',
                    xtype: 'checkbox',
                    inputValue: 'true'
                }
            },
            {columnWidth: 0.33,
                items: {
                    fieldLabel: '是否允许个性配置',
                    name: 'selfhood',
                    xtype: 'checkbox',
                    inputValue: 'true'
                }
            },
            {columnWidth: 0.33,
                items: {
                    fieldLabel: '单选',
                    name: 'singleSelect',
                    xtype: 'checkbox',
                    inputValue: 'true'
                }
            },
            {columnWidth: 0.33,
                items: {
                    fieldLabel: '行首显示序号',
                    name: 'columNum',
                    xtype: 'checkbox',
                    inputValue: 'true'
                }
            });
    }
    else {
        items.push({  columnWidth: 0.5,
                items: {
                    fieldLabel: '列表模式',
                    xtype: 'combo',
                    store: new Ext.data.ArrayStore({
                        fields: ['code', 'value'],
                        data: [
                            ["listview", "listview"],
                            ["table", "table"]
                        ]
                    }),
                    displayField: 'value',
                    valueField: 'code',
                    editable: false,
                    mode: 'local',
                    forceSelection: true,
                    triggerAction: 'all',
                    value: 'listview',
                    selectOnFocus: true,
                    hiddenName: 'listModel',
                    listeners: {
                        select: function (combo, record, index) {
                            var isVisible = record["data"]["code"] == "listview";
                            Ext.getCmp("modelId").setVisible(isVisible).setValue("");
                            Ext.getCmp("listViewModelId").setVisible(isVisible).setValue("");
                        }
                    }
                }
            },
            {

                items: {
                    id: 'listViewModelId',
                    anchor: '100%',
                    fieldLabel: 'listview常用模板',
                    xtype: 'combo',
                    store: new Ext.data.ArrayStore({
                        fields: ['code', 'value','url','model'],
                        data: [
                            ["1", "样式一","k/qm/img/1.png",'<a href="#">Inbox</a>'],
                            ["2", "样式二","k/qm/img/2.png",'<a href="#">Inbox <span class="ui-li-count">12</span></a>'],
                            ["3", "样式三","k/qm/img/3.png",'<a href="#"><img src="k/kui/images/head/default.png" alt="France" class="ui-li-icon ui-corner-none">France</a>'],
                            ["4", "样式四","k/qm/img/4.png",'<a href="#"><img src="k/kui/images/head/动态/3000(36).gif"><h2>Broken Bells</h2><p>Broken Bells</p></a><a href="#">Purchase album</a>'],
                            ["5", "样式五","k/qm/img/5.png",'<a href="#"><h2>Stephen Weber</h2><p><strong>Youve been invited to a meeting at Filament Group in Boston, MA</strong></p><p>Hey Stephen, if youre available at 10am tomorrow, weve got a meeting with the jQuery team.</p><p class="ui-li-aside"><strong>6:24</strong>PM</p></a>'],
                            ["6", "样式六","k/qm/img/6.png",'<li class="km-menu-icon"><div><img src="k/kui/images/file-type/128/net.png" border="0" alt=""/></div><div>点击图标文字</div></li>']
                        ]
                    }),
                    tpl: '<tpl for="."><div class="x-combo-list-item"><img src="{url}">&nbsp;{value}</div></tpl>',
                    displayField: 'value',
                    valueField: 'code',
                    editable: false,
                    mode: 'local',
                    forceSelection: true,
                    triggerAction: 'all',
                    value: '',
                    selectOnFocus: true,
                    hiddenName: 'listViewModel',
                    listeners: {
                        select: function (combo, record, index) {
                             Ext.getCmp("modelId").setValue(record["data"]["model"]);
                        }
                    }
                }
            },
            {
                items: {
                    anchor: '100%',
                    id:'modelId',
                    fieldLabel: '模板',
                    name: 'script',
                    maxLength: 1000,
                    height: '120',
                    grow: true,
                    xtype: 'textarea'
                }
            });
    }
    fs = new Ext.FormPanel({
        labelWidth: 100,
        frame: true,
        autoScroll : true,
        minWidth: 600,
        minHeight: 550,
        buttonAlign: 'right',
        layout: 'column',
        defaults: {layout: "form", border: false, columnWidth: 1, labelAlign: 'right', labelSeparator: '', labelWidth: 100},
        items: items
    });
    var values = ["isnewpage", "pageid", "pagename", "dbtablename", "pagesql", "script", "pagesize", "defaultsearch", "types","options"];
    var options = ["toolbartype", "advancedPanel", "advanced", "expexcel", "sessionCon", "selfhood", "singleSelect", "columNum"];
    var mobileOptions = ["listModel","listViewModel"];
    var submit = fs.addButton({
        text: '保 存',
        iconCls: 'save',
        handler: function () {
            if (!fs.getForm().isValid())return;
            var formValues = fs.getForm().getValues();
            var newValues = {};
            for (var i = 0; i < values.length; i++) {
                newValues[values[i]] = formValues[values[i]];
            }
            if (!_isWeb) {
                options=mobileOptions;
            }
            var ops = {};
            for (var i = 0; i < options.length; i++) {
                ops[options[i]] = formValues[options[i]];
            }
            newValues["options"] = Ext.encode(ops);

            Ext.Ajax.request({
                url: Qm.pagingUrlPre + 'savePage',
                params: newValues,
                callback: function (options, success, response) {
                    var obj = Ext.util.JSON.decode(response.responseText);
                    if (obj.success) {
                        Qm.refreshGrid();
                        win.close();
                    } else {
                        Ext.MessageBox.alert("提示", "保存失败！\n" + obj.msg);
                    }
                }
            });
        }
    });
    fs.addButton({
        text: '关 闭',
        iconCls: 'close',
        handler: function () {
            win.close();
        }
    });
    win = new Ext.Window({
//        el: 'pageWin',
        title: '编辑Page',
        iconCls: 'add',
        layout: 'fit',
        width: 600,
        height: 550,
        minWidth: 550,
        minHeight: 550,
        closeAction: 'close',
        monitorResize: true,
        maximized: true,
        items: fs
    });
//    }
    win.show();
}

function pageCopy() {
    Ext.MessageBox.prompt('Page复制', '请输入新的Page编号：', function (btn, text) {
        if (btn == "ok") {
            Ext.Ajax.request({
                url: Qm.pagingUrlPre + 'pageCopy&pageid=' + Qm.getValueByName("pageid") + "&newPageId=" + text,
                callback: function (options, success, response) {
                    var obj = Ext.util.JSON.decode(response.responseText);
                    if (obj.success) {
                        Ext.MessageBox.alert("提示", "复制成功！");
                        Qm.refreshGrid();
                    } else {
                        Ext.MessageBox.alert("提示", "复制失败！\n" + obj.msg);
                    }
                }
            });
        }
    });
}

function pageDelete() {
    Ext.MessageBox.confirm('确认', '您确定要删除所选数据吗？', function (btn) {
        if (btn == "yes") {
            Ext.Ajax.request({
                url: Qm.pagingUrlPre + 'pageDelete&pageid=' + Qm.getValuesByName("pageid"),
                callback: function (options, success, response) {
                    var obj = Ext.util.JSON.decode(response.responseText);
                    if (obj.success) {
                        Ext.MessageBox.alert("提示", "删除成功！");
                        Qm.refreshGrid();
                    } else {
                        Ext.MessageBox.alert("提示", "删除失败！\n" + obj.msg);
                    }
                }
            });
        }
    });
}
function rePageConfig() {
    Ext.MessageBox.confirm('确认', '您确定要重新生成移动终端配置文件吗？', function (btn) {
        if (btn == "yes") {
            Ext.Ajax.request({
                url: Qm.pagingUrlPre + 'reGenerateConfig&pageid=' + Qm.getValuesByName("pageid"),
                callback: function (options, success, response) {
                    var obj = Ext.util.JSON.decode(response.responseText);
                    if (obj.success) {
                        Ext.MessageBox.alert("提示", "生成成功！");
                        Qm.refreshGrid();
                    } else {
                        Ext.MessageBox.alert("提示", "生成失败！\n" + obj.msg);
                    }
                }
            });
        }
    });
}
var previewWin;
var previewWinTitle = "（在本页面所作的调整如无特殊说明均为全局调整）";
function preview(pageid,types) {
    if (!previewWin) {
        previewWin = new Ext.Window({
            el: 'previewWin',
            iconCls: 'view',
            layout: 'fit',
            width: 800,
            height: 550,
            //minWidth: 550,
            //minHeight: 550,
            closeAction: 'hide',
            maximized:true,

            monitorResize: true,
            //maximized: true,
            html: "<iframe id='previewFrame' width='100%' height='100%' scrolling='no'></iframe>"
        });
    }
    previewWin.setTitle("预览：" + pageid + "--" + previewWinTitle);
    previewWin.show();
    var url="k/qm/"+(types=="1"?"previewMobile.jsp":"preview.jsp")+"?pageid=" + pageid;
    document.getElementById("previewFrame").src = url;
}
var importWin , importForm;
function importPageDef() {
    if (!importWin) {
        importForm = new Ext.form.FormPanel({
            labelAlign: 'right',
            fileUpload: true,
            url: Qm.pagingUrlPre + "importPageDef",
            defaultType: 'textfield',
            items: [
                {
                    fieldLabel: '请选择',
                    inputType: 'file',
                    anchor: '100%'
                }
            ]
        });
        importWin = new Ext.Window({
            title: '导入',
            width: 400,
            height: 100,
            minWidth: 400,
            layout: 'fit',
            iconCls: 'import',
            buttonAlign: 'center',
            closeAction: 'hide',
            items: importForm,
            buttons: [
                {
                    text: '导入',
                    handler: function () {
                        importForm.getForm().submit({
                            url: Qm.pagingUrlPre + "importPageDef&pageid=undefined",
                            waitTitle: '请稍候……',
                            waitMsg: '正在导入数据……',
                            failure: showMsg,
                            success: showMsg
                        });
                    }
                },
                {
                    text: '取消',
                    handler: function () {
                        importWin.hide();
                    }
                }
            ]
        });
    }
    importWin.show();
}
function showMsg(form, action) {
    Ext.MessageBox.alert("提示", action.result.msg);
    if (action.result.success) {
        Qm.refreshGrid();
        importWin.hide();
    }
}
//创建系统
function createSystem() {
    Ext.MessageBox.confirm('提示', '在当前数据库中未发现通用查询相关系统表，是否现在创建这些数据？', function (btn) {
        if (btn == "yes") {
            Ext.Ajax.request({
                url: Qm.pagingUrlPre + 'createSystemTable&pageid=undefined',
                callback: function (options, success, response) {
                    var obj = Ext.util.JSON.decode(response.responseText);
                    if (obj.success) {
                        window.location.reload();
                    } else {
                        Ext.MessageBox.alert("提示", "创建失败！\n" + obj.msg);
                    }
                }
            });
        }
    });
}
//示例数据
function createDemo() {
    Ext.Ajax.request({
        url: Qm.pagingUrlPre + 'createDemo&pageid=undefined',
        callback: function (options, success, response) {
            var obj = Ext.util.JSON.decode(response.responseText);
            if (obj.success) {
                Qm.refreshGrid();
            } else {
                Ext.MessageBox.alert("提示", "生成示例数据失败！\n" + obj.msg);
            }
        }
    });
}
//示例数据
function deleteDemo() {
    Ext.MessageBox.confirm('确认', '您确定要删除示例数据吗？', function (btn) {
        if (btn == "yes") {
            Ext.Ajax.request({
                url: Qm.pagingUrlPre + 'deleteDemo&pageid=undefined',
                callback: function (options, success, response) {
                    var obj = Ext.util.JSON.decode(response.responseText);
                    if (obj.success) {
                        Qm.refreshGrid();
                    } else {
                        Ext.MessageBox.alert("提示", "删除示例数据失败！\n" + obj.msg);
                    }
                }
            });
        }
    });
}
//示例数据
function opCache(op) {
    Ext.MessageBox.confirm('确认', '您确定要清除数据吗？', function (btn) {
        if (btn == "yes") {
            Ext.Ajax.request({
                url: Qm.pagingUrlPre +op+ '&pageid='+ Qm.getValuesByName("pageid"),
                callback: function (options, success, response) {
                    var obj = Ext.util.JSON.decode(response.responseText);
                    if (!obj.success) {
                        Ext.MessageBox.alert("提示", "清除缓存数据失败！\n" + obj.msg);
                    }
                }
            });
        }
    });
}

Ext.onReady(function(){//2013-03-26 15:25:39 lybide
	try{top.closeMenuLoading();}catch (e){}
});