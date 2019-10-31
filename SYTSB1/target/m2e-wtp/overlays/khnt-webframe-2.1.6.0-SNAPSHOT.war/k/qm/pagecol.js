if('function' !== typeof RegExp.escape) {
    RegExp.escape = function(s) {
        if('string' !== typeof s) {
            return s;
        }
        // Note: if pasting from forum, precede ]/\ with backslash manually
        return s.replace(/([.*+?^=!:${}()|[\]\/\\])/g, '\\$1');
    }; // eo function escape
}
// create namespace
Ext.ns('Ext.ux.form');

/**
 * Creates new LovCombo
 * @constructor
 * @param {Object} config A config object
 */
Ext.ux.form.LovCombo = Ext.extend(Ext.form.ComboBox, {

    // {{{
    // configuration options
    /**
     * @cfg {String} checkField Name of field used to store checked state.
     * It is automatically added to existing fields.
     * (defaults to "checked" - change it only if it collides with your normal field)
     */
    checkField:'checked'

    /**
     * @cfg {String} separator Separator to use between values and texts (defaults to "," (comma))
     */
    ,separator:','

    /**
     * @cfg {String/Array} tpl Template for items.
     * Change it only if you know what you are doing.
     */
    // }}}
    // {{{
    ,constructor:function(config) {
        config = config || {};
        config.listeners = config.listeners || {};
        Ext.applyIf(config.listeners, {
            scope:this
            ,beforequery:this.onBeforeQuery
            ,blur:this.onRealBlur
        });
        Ext.ux.form.LovCombo.superclass.constructor.call(this, config);
    } // eo function constructor
    // }}}
    // {{{
    ,initComponent:function() {

        // template with checkbox
        if(!this.tpl) {
            this.tpl =
                '<tpl for=".">'
                    +'<div class="x-combo-list-item">'
                    +'<img src="' + Ext.BLANK_IMAGE_URL + '" '
                    +'class="ux-lovcombo-icon ux-lovcombo-icon-'
                    +'{[values.' + this.checkField + '?"checked":"unchecked"' + ']}">'
                    +'<div class="ux-lovcombo-item-text">{' + (this.displayField || 'text' )+ ':htmlEncode}</div>'
                    +'</div>'
                    +'</tpl>'
            ;
        }

        // call parent
        Ext.ux.form.LovCombo.superclass.initComponent.apply(this, arguments);

        // remove selection from input field
        this.onLoad = this.onLoad.createSequence(function() {
            if(this.el) {
                var v = this.el.dom.value;
                this.el.dom.value = '';
                this.el.dom.value = v;
            }
        });

    } // eo function initComponent
    // }}}
    // {{{
    /**
     * Disables default tab key bahavior
     * @private
     */
    ,initEvents:function() {
        Ext.ux.form.LovCombo.superclass.initEvents.apply(this, arguments);

        // disable default tab handling - does no good
        this.keyNav.tab = false;

    } // eo function initEvents
    // }}}
    // {{{
    /**
     * Clears value
     */
    ,clearValue:function() {
        this.value = '';
        this.setRawValue(this.value);
        this.store.clearFilter();
        this.store.each(function(r) {
            r.set(this.checkField, false);
        }, this);
        if(this.hiddenField) {
            this.hiddenField.value = '';
        }
        this.applyEmptyText();
    } // eo function clearValue
    // }}}
    // {{{
    /**
     * @return {String} separator (plus space) separated list of selected displayFields
     * @private
     */
    ,getCheckedDisplay:function() {
        var re = new RegExp(this.separator, "g");
        return this.getCheckedValue(this.displayField).replace(re, this.separator + ' ');
    } // eo function getCheckedDisplay
    // }}}
    // {{{
    /**
     * @return {String} separator separated list of selected valueFields
     * @private
     */
    ,getCheckedValue:function(field) {
        field = field || this.valueField;
        var c = [];

        // store may be filtered so get all records
        var snapshot = this.store.snapshot || this.store.data;

        snapshot.each(function(r) {
            if(r.get(this.checkField)) {
                c.push(r.get(field));
            }
        }, this);

        return c.join(this.separator);
    } // eo function getCheckedValue
    // }}}
    // {{{
    /**
     * beforequery event handler - handles multiple selections
     * @param {Object} qe query event
     * @private
     */
    ,onBeforeQuery:function(qe) {
        qe.query = qe.query.replace(new RegExp(RegExp.escape(this.getCheckedDisplay()) + '[ ' + this.separator + ']*'), '');
    } // eo function onBeforeQuery
    // }}}
    // {{{
    /**
     * blur event handler - runs only when real blur event is fired
     * @private
     */
    ,onRealBlur:function() {
        this.list.hide();
        var rv = this.getRawValue();
        var rva = rv.split(new RegExp(RegExp.escape(this.separator) + ' *'));
        var va = [];
        var snapshot = this.store.snapshot || this.store.data;

        // iterate through raw values and records and check/uncheck items
        Ext.each(rva, function(v) {
            snapshot.each(function(r) {
                if(v === r.get(this.displayField)) {
                    va.push(r.get(this.valueField));
                }
            }, this);
        }, this);
        this.setValue(va.join(this.separator));
        this.store.clearFilter();
    } // eo function onRealBlur
    // }}}
    // {{{
    /**
     * Combo's onSelect override
     * @private
     * @param {Ext.data.Record} record record that has been selected in the list
     * @param {Number} index index of selected (clicked) record
     */
    ,onSelect:function(record, index) {
        if(this.fireEvent('beforeselect', this, record, index) !== false){

            // toggle checked field
            record.set(this.checkField, !record.get(this.checkField));

            // display full list
            if(this.store.isFiltered()) {
                this.doQuery(this.allQuery);
            }

            // set (update) value and fire event
            this.setValue(this.getCheckedValue());
            this.fireEvent('select', this, record, index);
        }
    } // eo function onSelect
    // }}}
    // {{{
    /**
     * Sets the value of the LovCombo. The passed value can by a falsie (null, false, empty string), in
     * which case the combo value is cleared or separator separated string of values, e.g. '3,5,6'.
     * @param {Mixed} v value
     */
    ,setValue:function(v) {
        if(v) {
            v = '' + v;
            if(this.valueField) {
                this.store.clearFilter();
                this.store.each(function(r) {
                    var checked = !(!v.match(
                            '(^|' + this.separator + ')' + RegExp.escape(r.get(this.valueField))
                                +'(' + this.separator + '|$)'))
                        ;

                    r.set(this.checkField, checked);
                }, this);
                this.value = this.getCheckedValue();
                this.setRawValue(this.getCheckedDisplay());
                if(this.hiddenField) {
                    this.hiddenField.value = this.value;
                }
            }
            else {
                this.value = v;
                this.setRawValue(v);
                if(this.hiddenField) {
                    this.hiddenField.value = v;
                }
            }
            if(this.el) {
                this.el.removeClass(this.emptyClass);
            }
        }
        else {
            this.clearValue();
        }
    } // eo function setValue
    // }}}
    // {{{
    /**
     * Selects all items
     */
    ,selectAll:function() {
        this.store.each(function(record){
            // toggle checked field
            record.set(this.checkField, true);
        }, this);

        //display full list
        this.doQuery(this.allQuery);
        this.setValue(this.getCheckedValue());
    } // eo full selectAll
    // }}}
    // {{{
    /**
     * Deselects all items. Synonym for clearValue
     */
    ,deselectAll:function() {
        this.clearValue();
    } // eo full deselectAll
    // }}}
    ,assertValue : function(){
        var val = this.getRawValue(),
            rec,arr_rec,i=0;
        // 分离value为数组，循环取rec
        var arr_val = val.split(this.separator);
        var arr_value = this.value.split(this.separator);
        for(;i<arr_val.length;i++){
            if(this.valueField && Ext.isDefined(arr_value[i])){
                rec = this.findRecord(this.valueField, arr_value[i]);
            }
            if(!rec || rec.get(this.displayField) != arr_val[i].trim()){
                rec = this.findRecord(this.displayField, arr_val[i].trim());
            }
            if(rec && !arr_rec){
                arr_rec = [];
            }
            if(rec){
                arr_rec.push(rec);
            }
        }
        if(!arr_rec && this.forceSelection){
            if(val.length > 0 && val != this.emptyText){
                this.el.dom.value = Ext.value(this.lastSelectionText, '');
                this.applyEmptyText();
            }else{
                this.clearValue();
            }
        }else{
            if(arr_rec && this.valueField){
                // onSelect may have already set the value and by doing so
                // set the display field properly.  Let's not wipe out the
                // valueField here by just sending the displayField.
                if (this.value == val){
                    return;
                }
                i = 0;
                val = "";
                var ival;
                for(;i<arr_rec.length;i++){
                    ival = arr_rec[i].get(this.valueField);
                    if(!ival){
                        ival = arr_rec[i].get(this.displayField);
                    }
                    if(i ==0 ){
                        val = ival;
                    }else{
                        val = val+","+ival;
                    }
                }
            }
            this.setValue(val);
        }
    }
}); // eo extend

// register xtype
Ext.reg('lovcombo', Ext.ux.form.LovCombo);

// eof

var pagecolWin;
var store;
//var screenStore = new Ext.data.SimpleStore({
//
//    fields: ['v', 't'],
//    data : [
//        [ '480x800', '480x800'],
//        [ '800x1280', '800x1280'],
//        [ '320x400', '320x400']
//    ]
//});
var screenStore = new Ext.data.JsonStore({
    url: Qm.pagingUrlPre+"getMobileScreen&pageid=getMobileScreen",
    root:'data',
    fields: ['v', 't']
});
screenStore.load();
var typeStore = new Ext.data.SimpleStore({
    fields: ['v', 't'],
    data : [
        [ 'string', '字符串'],
        [ 'number', '数字'],
        [ 'date', '日期']
    ]
});
var bindtypeStore = new Ext.data.SimpleStore({
    fields: ['v', 't'],
    data : [
        [ '', '不邦定'],
        [ 'codetable', '码表'],
        [ 'treecodetable', '树形码表'],
        [ 'string', '字符串'],
        [ 'sql', '自定义SQL']
    ]
});
var alignStore = new Ext.data.SimpleStore({
    fields: ['v', 't'],
    data : [
        [ '', '默 认'],
        [ 'left', '左对齐'],
        [ 'center', '居中'],
        [ 'right', '右对齐']
    ]
});
var pagecolPageid;
function openPagecol(pageid) {
    pagecolPageid=pageid;
    var isWeb=Qm.getValueByName("types")=="0";
//    if (!pagecolWin) {
        var url = Qm.pagingUrlPre + 'pagecolDef';
        var fm = Ext.form;
        var checkColumn = new Ext.grid.CheckColumn({
            header: "显示",
            dataIndex: 'visible',
            width: 50
        });
        var sendColumn = new Ext.grid.CheckColumn({
            header: "发送",
            dataIndex: 'send',
            width: 50
        });
        var configColumn = new Ext.grid.CheckColumn({
            header: "可配",
            dataIndex: 'config',
            width: 50
        });
        var frozenColumn = new Ext.grid.CheckColumn({
            header: "冻结",
            dataIndex: 'frozen',
            width: 50
        });
        //todo add by jyl增加合计合计列
        var totalSummaryColumn = new Ext.grid.CheckColumn({
            header: "合计",
            dataIndex: 'totalSummary',
            width: 55
        });
        /////////////////////////////
        var sm = new Ext.grid.CheckboxSelectionModel();
        var columns=[sm,{
            header: "PAGEID",
            dataIndex: 'pageid',
            width: 100,
            hidden :true
        },{
            id:'columm',
            header: "列名",
            dataIndex: 'columm',
            width: 100,
            editor: new fm.TextField({
                allowBlank: false,
                emptyText :"列名"
            })
        },{
            header: "列显示名",
            dataIndex: 'columndisplay',
            width: 100,
            editor: new fm.TextField({
                allowBlank: false,
                emptyText :"列显示名"
            })
        },{
            header: "顺序",
            dataIndex: 'orderno',
            width: 50,
            editor: new fm.NumberField({
                allowBlank: false
            })
        },{
            header: "数据类型",
            dataIndex: 'datatype',
            width: 80,
            editor: new fm.ComboBox({
                store: typeStore,
                editable:false,
                valueField :'v',
                displayField:'t',
                hiddenName : 'datatype',
                typeAhead: true,
                mode: 'local',
                triggerAction: 'all',
                listClass: 'x-combo-list-small'
            }),
            renderer:function(value) {
                var mix = typeStore.query("v", value);
                if (mix.getCount() > 0)
                    return mix.get(0).get("t");
                else return "";
            }
        },{
            header: "宽度",
            dataIndex: 'width',
            width: 50,
            editor: new fm.NumberField({
                allowBlank: false,
                grow :true
            })
        },{
            header: "对齐方式",
            dataIndex: 'align',
            width: 80,
            editor: new fm.ComboBox({
                store: alignStore,
                editable:false,
                valueField :'v',
                displayField:'t',
                hiddenName : 'align',
                typeAhead: true,
                mode: 'local',
                triggerAction: 'all',
                listClass: 'x-combo-list-small'
            }),
            renderer:function(value) {
                var mix = alignStore.query("v", value);
                if (mix.getCount() > 0)
                    return mix.get(0).get("t");
                else return "";
            }
        },checkColumn,sendColumn,configColumn,frozenColumn,
            {
                header:"邦定类型",
                dataIndex:'bindtype',
                width:80,
                editor:new fm.ComboBox({
                    store:bindtypeStore,
                    editable:false,
                    valueField:'v',
                    displayField:'t',
                    hiddenName:'bindtype',
                    typeAhead:true,
                    mode:'local',
                    triggerAction:'all',
                    listClass:'x-combo-list-small'
                }),
                renderer:function (value) {
                    var mix = bindtypeStore.query("v", value);
                    if (mix.getCount() > 0)
                        return mix.get(0).get("t");
                    else return "";
                }
            }, {
                header:"邦定数据",
                dataIndex:'binddata',
                width:100,
                editor:new fm.TextField({
                })
            }, {
                header:"格式化串",
                dataIndex:'formatstr',
                width:150,
                editor:new fm.TextField({
                })
            }
            //todo add by jyl增加字段
            ,totalSummaryColumn,
            {
            	header:"合计格式化",
                dataIndex:'summaryFormat',
                width:150,
                editor:new fm.TextField({
                })
            }
        ];
        if(!isWeb){
            columns.push({
                    header: "在此分辨率下显示",
                    dataIndex: 'ext',
                    width: 150,
                    editor: new Ext.ux.form.LovCombo({
                        name: "ext",
                        store: screenStore,
                        mode: 'local',
                        triggerAction: 'all',
                        hideTrigger: false,
                        allowBlank: true,
                        valueField: 'v',
                        displayField: 't',
                        editable: false
                    }),
                    renderer: function (value) {
                        var store = screenStore.query("v", new RegExp(value.replace(/,/g,"|")));
                        var count=store.getCount();
                        var name="";
                        for(var i=0;i<count;i++){
                            name+=(i==0?"":",")+store.get(i).get("t");
                        }
                        return name;
                    }
                }
            )
        }

        var cm = new Ext.grid.ColumnModel(columns);
        // by default columns are sortable
        cm.defaultSortable = true;
        var Column = Ext.data.Record.create([
            {
                name: 'pageid',
                type: 'string'
            },
            {
                name: 'columm',
                type: 'string'
            },
            {
                name: 'columndisplay',
                type: 'string'
            },
            {
                name: 'orderno',
                type:'int'
            },
            {
                name: 'datatype',
                type: 'string'
            },
            {
                name: 'visible',
                type: 'string'
            },
            {
                name: 'align',
                type: 'string'
            },
            {
                name: 'width',
                type: 'int'
            },
            {
                name: 'bindtype',
                type: 'string'
            },
            {
                name: 'binddata',
                type: 'string'
            },
            {
                name: 'formatstr',
                type: 'string'
            },
            {
                name: 'send',
                type: 'string'
            },
            {
                name: 'config',
                type: 'string'
            },
            {
                name: 'frozen',
                type: 'string'
            },
            {
                name: 'ext',
                type: 'string'
            },{
                name: 'totalSummary',
                type: 'string'
            },
            {
                name: 'summaryFormat',
                type: 'string'
            }
        ]);

        store = new Ext.data.Store({
            url: url,
            sortInfo:{field: "orderno", direction: "ASC"},
            reader: new Ext.data.XmlReader({
                record: 'data'
            }, Column)
        });
        function orderDisnum() {
            var records = store.getRange(0, store.getCount());
            for (var i = 0,size = records.length; i < size; i++) {
                records[i].set("orderno", i + 1);
            }
        }

        // create the editor grid
        var grid = new Ext.grid.EditorGridPanel({
            store: store,
            cm: cm,
            sm: sm,
            enableDragDrop : true,
            ddGroup: 'pagecolgrid-dd',
            ddText : '<font color=red><b>{0}</b></font> 行被选中！',
            renderTo:'pagecolWin',
            autoWidth:true,
            height:300,
            frame:true,
            plugins:[checkColumn,sendColumn,configColumn,frozenColumn,totalSummaryColumn],
            clicksToEdit:1,
            viewConfig: {
                forceFit: true
            },
            tbar: [
                {
                    text: '添加',
                    iconCls:'add',
                    handler : function() {
                        var p = new Column({
                            pageid:pagecolPageid,
                            columm:'xxxx',
                            columndisplay:'XXXXXX',
                            orderno:1,
                            datatype:'string',
                            visible:'1',
                            width:100 ,
                            align:'left' ,
                            bindtype:'',
                            binddata:'',
                            formatstr:'',
                            send:'',
                            config:'',
                            ext:'',
                            totalSummary:'',
                            summaryFormat:''
                        });
                        grid.stopEditing();
                        store.insert(0, p);
                        grid.startEditing(0, 0);
                    }
                },
                "-",
                {
                    text: '删除',
                    iconCls:'delete',
                    handler : function() {
                        grid.stopEditing();
                        store.commitChanges();
                        var cols = sm.getSelections();
                        for (var i = 0; i < cols.length; i++)
                            store.remove(cols[i]);

                    }
                },
                "-",
                {
                    text: '重新生成顺序号',
                    iconCls:'orderno',
                    handler : orderDisnum
                },
                new Ext.Toolbar.Fill(),
                {
                    text: '保存',
                    iconCls:'save',
                    handler : function() {
                        grid.stopEditing();
                        store.commitChanges();
                        var records = store.getRange(0, store.getCount());
                        if (records.length==0) {
                            Ext.MessageBox.alert("提示", "没有字段信息，不能保存！");
                            return;
                        }
                        var json = "{pagecol:[";
                        for (var i = 0,size = records.length; i < size; i++) {
                            json += Ext.encode(records[i].data) + (i < size - 1 ? "," : "")
                        }
                        json += "]}";
                        Ext.Ajax.request({
                            url: Qm.pagingUrlPre + 'savePagecol&pageid=' + pageid,
                            callback:function(options, success, response) {
                                var obj = Ext.util.JSON.decode(response.responseText);
                                if (obj.success) {
                                    Ext.MessageBox.alert("提示", "保存成功！&nbsp;&nbsp;&nbsp;&nbsp;");
                                    pagecolWin.hide();
                                } else {
                                    Ext.MessageBox.alert("提示", "保存失败！\n" + obj.msg);
                                }
                            },
                            params: {pagecol:json}
                        });

                        //                alert(Ext.encode(records[0]));
                    }
                }
            ]
        });
        var ddrow = new Ext.dd.DropTarget(grid.getView().mainBody, {
            ddGroup : 'pagecolgrid-dd',
            notifyDrop : function(dd, e, data) {
                var sm = grid.getSelectionModel();
                var rows = sm.getSelections();
                var cindex = dd.getDragData(e).rowIndex;
                if (sm.hasSelection()) {
                    for (i = 0; i < rows.length; i++) {
                        store.remove(store.getById(rows[i].id));
                        store.insert(cindex, rows[i]);
                    }
                    sm.selectRecords(rows);
                }
                orderDisnum();
            }
        });
        pagecolWin = new Ext.Window({
            //            el:'pagecolWin',
            title:'字段设置（可以拖动排序，支持Ctrl，Shift键选择多行）',
            iconCls:'pageset',
            layout:'fit',
            width:850,
            height:500,
            minWidth : 550,
            minHeight :550,
//            closeAction:'hide',
//            maximizable :true,
            maximized:true,
            monitorResize :true,
            items: grid
        });
//    }
    pagecolWin.show();
    store.load({params:{pageid:pageid}});
}

Ext.grid.CheckColumn = function(config) {
    Ext.apply(this, config);
    if (!this.id) {
        this.id = Ext.id();
    }
    this.renderer = this.renderer.createDelegate(this);
};

Ext.grid.CheckColumn.prototype = {
    init : function(grid) {
        this.grid = grid;
        this.grid.on('render', function() {
            var view = this.grid.getView();
            view.mainBody.on('mousedown', this.onMouseDown, this);
        }, this);
    },

    onMouseDown : function(e, t) {
        if (t.className && t.className.indexOf('x-grid3-cc-' + this.id) != -1) {
            e.stopEvent();
            var index = this.grid.getView().findRowIndex(t);
            var record = this.grid.store.getAt(index);
            record.set(this.dataIndex, record.data[this.dataIndex] == "1" ? "0" : "1");
        }
    },

    renderer : function(v, p, record) {
        p.css += ' x-grid3-check-col-td';
        return '<div class="x-grid3-check-col' + (v == '1' ? '-on' : '') + ' x-grid3-cc-' + this.id + '">&#160;</div>';
    }
};