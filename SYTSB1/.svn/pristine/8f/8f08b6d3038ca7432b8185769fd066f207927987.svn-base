Ext.BLANK_IMAGE_URL =  Ext.isIE6 || Ext.isIE7 || Ext.isAir ? 'qm/extjs/images/default/s.gif' :'data:image/gif;base64,R0lGODlhAQABAID/AMDAwAAAACH5BAEAAAAALAAAAAABAAEAAAICRAEAOw==','';

Qm = {
    version:"1.2.3.3.0",
    versionDetail:{major:1,minor:2,patch:"3.3.0"},
    pagingUrlPre:"qm?__method=",
    config:{},
    userConfig:{},
    getColumnValueType:function(obj){
        var type="string";
        if(Ext.isArray(obj.binddata)){
           if(obj.binddata.length>0){
              if(obj.binddata[0].text){
                  type="multipletree";
              }else{
                  type="multiple";
              }
           }
        }else{
            type=obj.datatype;
        }
        return type;
    },
    getDateFormat:function(formater){//实现前后台日期格式转换
        var format="Y-m-d";
        if(formater!=""){
            format=formater.replace(/yyyy/g,"Y").replace(/yy/g,"y").replace(/MM/g,"m").replace(/M/g,"n").replace(/dd/g,"d").replace(/d/g,"j");
            format=format.replace(/HH/g,"H").replace(/H/g,"G").replace(/hh/g,"h").replace(/h/g,"g").replace(/mm/g,"i").replace(/ss/g,"s").replace(/s/g,"s");
        }
        return format;
    },
///////////////////以下函数可以在自己的页面调用//////////////////
    /**
     * public 设置工具条按钮状态
     */
    setTbar : function() {
    },
    /**
     * public 查询数据
     */
    searchData : function() {
    },
    /**
     * public 刷新表格数据
     */
    refreshGrid : function() {
    },
    /**
     * 得到选中行数
     */
    getSelectedCount: function() {
    },

    getValueByName : function (name) {
    },
    /**
     * public
     */
    getValuesByName : function(name) {
    },
    /**
     * public 刷新页面
     */
    refreshPage : function() {
    }
};
Qm.RowNumberer = Ext.extend(Object, {
    header: "",
    width: 28,
    sortable: false,
    constructor : function(config){
        Ext.apply(this, config);
        if(this.rowspan){
            this.renderer = this.renderer.createDelegate(this);
        }
    },
    fixed:true,
    hideable: false,
    menuDisabled:true,
    dataIndex: '',
    id: 'numberer',
    rowspan: undefined,
    renderer : function(value, metaData, record, rowIndex, colIndex, store){
        if(this.rowspan){
            p.cellAttr = 'rowspan="'+this.rowspan+'"';
        }
        return store.reader.jsonData.start+rowIndex+1;
    }
});
Qm.Group = new Ext.util.MixedCollection();

Qm.PagingToolbar = Ext.extend(Ext.PagingToolbar, {

    beforePagesizeText:"每页",
    afterPagesizeText:"条，共 {0} 条 ",

    pageid: "",

    defaultSearchConditionName:"defaultSearchCondition",
    manmadeSearchConditionName:"manmadeSearchCondition",
    searchParaName:"searchPara",
    sortInfoName:"sortInfo",
    sortInfo:[],

    defaultSearchCondition:[],
    manmadeSearchCondition:[],
    searchPara:[],
    advancedSearchPara:[],

    sessionCondition:[],
    sessionAdvancedCondition:[],
    //在列表上显示高级组合查询的配置项，如果用户有配置面板将会被覆盖
    advancedPanel:false,

    conChange:false,
    setConChange:function(conChange) {
        this.conChange = conChange;
    },
    getConChange:function() {
        return this.conChange;
    },
    //public 设置排序参数
    setSortInfo:function(sortInfo) {
        this.sortInfo = sortInfo;
    },
    getSortInfo:function() {
        return this.sortInfo;
    },
    setCondition:function(con){
        var defaultCon={label:"",logic:"and",name:"",compare:"=",dataType:"string",value:""}
         this.manmadeSearchCondition=[];
         if(Ext.isArray(con)){
             for(var i=0;i<con.length;i++){
                this.manmadeSearchCondition.push(Ext.applyIf(con[i],defaultCon));
             }
         }else{
             this.manmadeSearchCondition.push(Ext.applyIf(con,defaultCon));
         }
    },
    setSessionCondition:function(con){
         this.sessionCondition=con;
    },
    setSessionAdvancedCondition:function(con){
         this.sessionAdvancedCondition=con;
    },
    getSessionCondition:function(){
        return this.sessionCondition;
    },
    getSessionAdvancedCondition:function(){
        return this.sessionAdvancedCondition;
    },
    //public 设置是否需要传回“在列表上显示查询面板”参数
//    setSearchParaName:function(b) {
//        this.advancedPanel = b;
//    },
    //public 设置查询参数
    setSearchPara:function(para) {
        this.searchPara = para;
    },
    //public 取查询参数
    getSearchPara:function() {
        return this.searchPara;
    },
    gridRefresh:function() {
        this.doLoad(this.cursor);
    },

    initComponent : function() {
        this.paramNames = {start: 'start', limit: 'pagesize',pageid:"pageid"},
                this.afterPageText = "/ {0} 页";
        Qm.PagingToolbar.superclass.initComponent.call(this);
        this.items.insert(10, this.afterPagesizeItem = new Ext.Toolbar.TextItem({
            text: this.beforePagesizeText
        }));
        this.items.insert(11, this.pagesizeItem = new Ext.form.NumberField({
            cls: 'x-tbar-page-number',
            minLength :1,
            maxLength :999,
//            grow :true,
            value:this.pageSize,
            allowDecimals: false,
            allowNegative: false,
            enableKeyEvents: true,
            selectOnFocus: true,
            submitValue: false,
            listeners: {
                scope: this,
                keydown: this.onPagesizeKeyDown,
                blur: this.onPagesizeBlur
            }
        }));

        this.items.insert(12, this.afterPagesizeItem = new Ext.Toolbar.TextItem({
            text: String.format(this.afterPagesizeText, 0)
        }));

    },
    getParam:function(start) {
        var o = {}, pn = this.paramNames;
        o[pn.pageid] = this.pageid;
        if (start == undefined)
            start = this.cursor;
        o[pn.start] = start;
        o[pn.limit] = this.pageSize;
        o["conChange"] = this.conChange;
        o[this.defaultSearchConditionName] = Ext.encode(this.defaultSearchCondition);
        o[this.manmadeSearchConditionName] = Ext.encode(this.manmadeSearchCondition);
        o["sessionCondition"] = Ext.encode(this.sessionCondition);
        o["sessionAdvancedCondition"] = Ext.encode(this.sessionAdvancedCondition);

        o[this.sortInfoName] = Ext.encode(this.sortInfo);
        o[this.searchParaName] = Ext.encode(this.searchPara);
        return o;
    },
    // private
    doLoad : function(start) {
        var o = {};
        if (this.fireEvent('beforechange', this, o) !== false) {
            this.store.load({params:this.getParam(start)});
        }
    },
    // private
    onPagesizeBlur : function(e) {
        this.pageSize = this.pagesizeItem.getValue();
        if (this.pageSize < 1) {
            this.pageSize = 1;
            this.pagesizeItem.setValue(this.pageSize);
        }
        if (this.pageSize > 999) {
            this.pageSize = 999;
            this.pagesizeItem.setValue(this.pageSize);
        }
    },
    // private
    onPagesizeKeyDown : function(field, e) {
        var k = e.getKey();
        this.pageSize = this.pagesizeItem.getValue();
        if (k == e.RETURN) {
            e.stopEvent();
            this.pageSize = this.pagesizeItem.getValue();
            this.doLoad(0);
        } else if (k == e.UP || k == e.PAGEUP || k == e.DOWN || k == e.PAGEDOWN) {
            e.stopEvent();
            var increment = e.shiftKey ? 10 : 1;
            if (k == e.DOWN || k == e.PAGEDOWN) {
                increment *= -1;
            }
            this.pageSize += increment;
            if (this.pageSize < 1)this.pageSize = 1;
            if (this.pageSize > 999)this.pageSize = 999;
            field.setValue(this.pageSize);
        }
    },
    // private
    updateInfo : function() {
        this.afterPagesizeItem.setText(String.format(this.afterPagesizeText, this.store.getTotalCount()));
        Qm.PagingToolbar.superclass.updateInfo.call(this);
    }
});
Ext.reg('qmpaging', Qm.PagingToolbar);

Qm.QmPanel = Ext.extend(Ext.Viewport, {
    id:Ext.id(),
    pageid:"",
    pagesize:20,
    pageTitle:"",
	//margin:"10px 10px 10px 10px",
	bodyStyle:"padding:10px;border:1px solid #FF0000;",
    defaultSearchCondition:[],
    manmadeSearchCondition:[],
    sortInfo:[],
    searchPara:[],
    sessionCondition:[],
    sessionAdvancedCondition:[],

    columnsInfo:{},
    qmUserConfig:{},

    sm:{},
    qmgrid:{},
    bar:{},

    toolbartype:0,
    //在列表上显示高级组合查询的配置项，如果用户有配置面板将会被覆盖
    advancedPanel:false,
    advanced:false,
    expexcel:false,
    sessionCon:false,
    selfhood:false,
    columNum:false,
    singleSelect: false,
    showTitle: true,
    seachOnload:true,

//    empMsg:"没有符合条件的数据！",
//    viewConfig:{emptyText:"没有符合条件的数据！",getRowClass:function(record, rowIndex, rowParams, store) {alert(2)}},
//            viewConfig:Ext.apply({emptyText:"没有符合条件的数据！"},Qm.userConfig.viewConfig),
    exportIframe:false,
    exportForm:false,
    exportToExcel:function (exportAll)  {
        var param = this.bar.getParam();
        var url = document.getElementsByTagName("base")[0]["href"]+Qm.pagingUrlPre + "exportToExcel" +(exportAll ? "&exportAll=true" : "");
        if (!this.exportForm) {
            this.exportForm = Ext.DomHelper.append(Ext.getBody(),'<form method="post">',true);
        }
        this.exportForm.set({"action":url}).update("");
        Ext.DomHelper.append(this.exportForm,{tag:"input",type:"hidden",name:"pageTitle",value:Qm.config.pageTitle});
        // 条件附加到Form
        for (var i in param){
            var temp=Ext.DomHelper.append(this.exportForm,{tag:"input",type:"hidden",name:i,value:""});
            temp.value=param[i];
        }
        this.exportForm.dom.submit();
    },
    openFilterWin:function() {
        var filterWin = Ext.getCmp("qm_filterWin");
        if (!filterWin) {
            filterWin = new Qm.QmBuilder({id:"qm_filterWin",bar:this.bar});
        }
        filterWin.show();
    },
    getQmgrid:function() {
        return this.qmgrid;
    },
    getSm:function() {
        return this.sm;
    },
    //没有数据的时候示滚动条
    initComponent: function() {
        var selectionchangeFun = function() {
        };
        if (this.qmUserConfig && this.qmUserConfig.listeners && this.qmUserConfig.listeners.selectionchange) {
            selectionchangeFun = this.qmUserConfig.listeners.selectionchange;
        }
        this.sm = new Ext.grid.CheckboxSelectionModel({
            singleSelect: this.singleSelect,
            sortable:false,
            listeners: {
                selectionchange:selectionchangeFun
            }
        });
        var columns = this.columnsInfo;
        var cmArr = new Array();
        var recorderArr = new Array();
        if(this.columNum){
           cmArr[0] = new Qm.RowNumberer();
        }else{
           cmArr[0] = this.sm;
        }
        var j = 0;
        for (var v in columns) {
            cmArr[j + 1 ] = {header:columns[v].columndisplay,dataIndex:columns[v].columm,width:columns[v].width,align:columns[v].align,hidden:(columns[v].visible == "0"),hideable:(columns[v].config == "1"),sortable:true};
            if (columns[v].formater != "") {
                cmArr[j + 1 ].renderer = columns[v].formater;
            }
            recorderArr[j] = {name:columns[v].columm};
            j++;
        }
        var cmConfig = {
            listeners: {
//            widthchange: this.columnResize,
                columnmoved: this.columnMove.createDelegate(this),
                hiddenchange: this.columnHidden.createDelegate(this)
            },
            columns: cmArr
        };

        var cm = new Ext.grid.ColumnModel(cmConfig);
//        var cm = new Ext.ux.grid.LockingColumnModel(cmArr);
//        cm.on("hiddenchange", columnHidden);

        var recorder = Ext.data.Record.create(recorderArr);
        ///
        var ds = new Ext.data.Store({
            proxy: new Ext.data.HttpProxy({
                url:Qm.pagingUrlPre + "q",
                listeners:{
                    loadexception:function(hp, options, response, e){
                        Ext.Msg.show({
                           title:'提示',
                           msg: "数据加载错误！<br>错误信息："+Ext.util.JSON.decode(response.responseText).msg,
                           buttons: Ext.Msg.OK,
                           icon: Ext.MessageBox.ERROR
                        });
                    }
                }
            }),
            reader: new Ext.data.JsonReader({
                totalProperty: 'total',
                root: 'rows'
            }, recorder),
            pruneModifiedRecords : true,
            remoteSort: true,
//            pbar:this.bar,
            sortInfo:this.sortInfo.length>0?this.sortInfo[0]:{}
        });

        //    var sm = new Ext.grid.CheckboxSelectionModel({singleSelect:true});
        var barButton = ['->'];
        if (this.advanced === true) {
            barButton[barButton.length] = {
                text:'高级',
                tooltip:'提供多重组合查询和多级排序功能',
                tooltipType:'title',
                iconCls:'advanced',
                id:'searchBarButton',
                handler:this.openFilterWin.createDelegate(this)
            };
            barButton[barButton.length] = "-";
        }
        if (this.expexcel === true) {
            barButton[barButton.length] = {
                text: '导出',
                p:this,
                xtype:'splitbutton',
                handler: function(b) {
                    b.p.exportToExcel(true);
                },
                iconCls: 'exp',
                // Menus can be built/referenced by using nested menu config objects
                menu : {items: [
                    {text: '导出本页数据', p:this,iconCls: 'exp',handler: function(b) {
                        b.p.exportToExcel(false);
                    }},
                    '-',
                    {text: '导出全部数据',p:this,iconCls: 'exp', handler: function(b) {
                        b.p.exportToExcel(true);
                    }}
                ]}
            };
        }
        this.bar = new Qm.PagingToolbar({
            pageid: this.pageid,
            pageSize: this.pagesize,
            store: ds,
            displayInfo: false,
            defaultSearchCondition:this.defaultSearchCondition,
            moreSorterInfo:this.moreSorterInfo,
            searchPara:this.searchPara,
            items:barButton
        });
        //    bar.addItem(barButton);

//        var hiddenDiv = Ext.get("hiddenDiv");

        //    Ext.override(Ext.grid.GridView, {
        //        renderBody : function() {
        //            var markup = this.renderRows();
        //            if (markup == "")
        //                markup = "<div align='center' disabled='true' style='padding-top:5pt;font-size:12pt;width:" + this.getTotalWidth() + "'>"+empMsg+"</div>";
        //            return this.templates.body.apply({
        //                rows : markup
        //            });
        //        }
        //    });
        //    this.viewConfig.emptyText="<div align='center' style='font-size:12pt'>"+empMsg+"</div>"; //
//        this.pageTitle = "<div class='pageTitle'>" + this.pageTitle + "</div>";

        var rowClickFun = Ext.emptyFn;
        if (this.qmUserConfig && this.qmUserConfig.listeners && this.qmUserConfig.listeners.rowclick) {
            rowClickFun = this.qmUserConfig.listeners.rowclick;
        }
        var rowDblClickFun = Ext.emptyFn;
        if (this.qmUserConfig && this.qmUserConfig.listeners && this.qmUserConfig.listeners.rowdblclick) {
            rowDblClickFun = this.qmUserConfig.listeners.rowdblclick;
        }

        this.qmgrid = new Ext.grid.GridPanel({
            ds: ds,
            cm: cm,
            sm: this.sm,
//            headerCfg :(!this.showTitle || this.qmUserConfig.sp_fields) ? "" : {tag:'div',cls:'pageTitle'},
            title:(!this.showTitle || this.qmUserConfig.sp_fields) ? "" : this.pageTitle,
            frame:false,
			columnLines:true,
			//bodyStyle:"background:#3078d1;border:1px solid #FF0000;padding:8px;",
//            enableColLock : true,
//            monitorResize :true,
            //        trackMouseOver:false,
            enableOverflow: true,
            tbar:this.qmUserConfig.tbar ? this.qmUserConfig.tbar : undefined,
            stripeRows : true,
            viewConfig : new Ext.grid.GridView(Ext.apply({emptyText:"没有符合条件的数据！"},Qm.userConfig.viewConfig)),
//            view: new Ext.ux.grid.LockingGridView(),

            loadMask: true,
            listeners: {
                rowclick :rowClickFun,
                rowdblclick :rowDblClickFun,
                columnresize :this.columnResize
                //            columnmove :columnMove
            },
            bbar:this.bar
        });

        if(!this.qmUserConfig.sp_fields&&this.advancedPanel===true){
            this.qmUserConfig.sp_fields=this.advancedSearchPara;
            this.sessionCondition=this.sessionAdvancedCondition;
        }
        var items = [];
        if (this.showTitle && this.qmUserConfig.sp_fields && this.qmUserConfig.sp_fields.length>0) {
            var searchItems = [];
            var canSetValue=(this.qmUserConfig.sp_fields.length==this.sessionCondition.length);
            Ext.each(this.qmUserConfig.sp_fields, function(obj, index) {
                var col = this.columnsInfo[obj.name];
                if (col) {
                    var valueType=Qm.getColumnValueType(col);
                    if(canSetValue)obj.value=this.sessionCondition[index].value;
                    obj.id = 'p-q-f' + index;
                    obj.anchor = '100%';
                    if (obj.label) {
                        obj.fieldLabel = obj.label;
                    } else {
                        obj.fieldLabel = col.columndisplay;
                    }
                    if (valueType=="multiple") {
                        col.binddata.unshift({code:"",value:"请选择"});
                        obj.xtype = "combo";
                        obj.hiddenName = obj.name;
                        obj.valueField = 'code';
                        obj.displayField = 'value';
                        obj.editable = false;
                        obj.mode = 'local';
                        obj.triggerAction = 'all';
                        obj.selectOnFocus = true;
                        obj.store = new Ext.data.ArrayStore({
                            fields: ['code', 'value'],
                            data : col.binddata
                        })
                    }
                    else if (valueType=="multipletree") {
                        obj.xtype = "kh_comboxtree";
                        obj.editable=true,
                        obj.data=col.binddata;
                    }
                    else if (valueType == "date") {
                        obj.xtype = "datetimefield";
                        obj.format = Qm.getDateFormat(col.formater);
                    }
                    else if (valueType == "number") {
                        obj.xtype = "numberfield";
                    } else {
                        obj.xtype = "textfield";
                    }
                }
                searchItems.push({items:obj});
            }, this);
            items.push({
                id:this.id + '_search_form',
                xtype:'form',
                title:'<div align="center">'+this.pageTitle+'</div>',
//                        border:false,
//                headerCfg :{tag:'div',cls:'pageTitle'},
                        bodyStyle:'border:1px solid #99bbe8;border-top:0px;border-bottom:0px;padding:0 10 0 10px;',
//                baseCls :'qm-search-panel',
//                autoHeight:true,
                collapsible:true,
                titleCollapse:true,
                layout:'column',
                items: [
                    {
                        xtype:'panel',
                        columnWidth:1,
                        border:false,
                        layout:'column',
                        defaults:Ext.apply({style:"padding:5px;",layout:"form",border:false,columnWidth:0.33,labelAlign:'right',labelSeparator:'',labelWidth:100}, this.qmUserConfig.sp_defaults || {}),
                        items: searchItems
                    },
                    {
                        xtype:'panel',
                        width:70,
                        id:'searchbutttonpanel',
                        border:false,
                        layout: {
                            type:'vbox',
                            padding:'5',
                            pack:'center',
                            align:'center'
                        },
                        items:{
                            xtype:'button',
                            text:'查询',
                            iconCls:'search',
                            //width:70,
                            handler:this.searchData.createDelegate(this)
                        }
                    }
                ],
                listeners:{
                    collapse:function(p) {
                        p.findParentByType("qm").qmresize();
                    },
                    expand:function(p) {
                        p.findParentByType("qm").qmresize();
                    }
                },
                keys:{
                    key: Ext.EventObject.ENTER,
                    fn: this.searchData,
                    scope: this
                }
            });
//            this.csp=searchItems;
            items.push({html:'<div class="qm-line"></div>',unstyled :true});
        }
        items.push(this.qmgrid);
        this.items = items;
//        customFunFun();
        var customfun = function() {
        };
        if (this.qmUserConfig && this.qmUserConfig.listeners && this.qmUserConfig.listeners.customfun) {
            customfun = this.qmUserConfig.listeners.customfun;
        }
        customfun(this.qmgrid);
        Qm.QmPanel.superclass.initComponent.call(this);
    },

    afterRender : function() {
        Qm.QmPanel.superclass.afterRender.call(this);
        if(this.seachOnload){
            this.searchData();
        }
        this.qmresize();
        var fp = Ext.getCmp(this.id + "_search_form");
        if(fp){
            Ext.getCmp("searchbutttonpanel").setHeight(fp.getInnerHeight())
        }
        this.qmgrid.getView().hmenu.add("-",{id:"reset", text: "恢复到默认值",iconCls:"tool",handler:this.resetConfig});
    },
    qmresize: function () {
        var fp = Ext.getCmp(this.id + "_search_form");
        this.qmgrid.setSize(Ext.getBody().getWidth(), Ext.getBody().getHeight() - (fp ? (0 + fp.getHeight()) : 0));
        if (fp) {
            fp.setWidth(Ext.getBody().getWidth());
//            fp.syncSize();
        }
    },
    refreshGrid: function() {
        this.bar.doRefresh();
        this.sm.clearSelections();
    },
    setCondition: function(con) {
        this.bar.setCondition(con);
    },
    searchData: function() {
        var para = [];
        var sp = Ext.getCmp(this.id + "_search_form");
        var form;
        if (sp) {
            form = sp.getForm();
        }
        Ext.each(this.qmUserConfig.sp_fields, function(obj, index) {
            var col = this.columnsInfo[obj.name];
            if (col) {
                var field = form.findField('p-q-f' + index);
                var p = {};
                p.logic = obj.logic ? obj.logic : "and";
                p.label = obj.label ? obj.label : col.columndisplay;
                p.name = obj.name;
                p.compare = obj.compare;
                p.dataType = col.datatype;
                p.value = field.getValue();

                if (p.dataType == "date" && !Ext.isEmpty(p.value)) {
                    p.value = p.value.format(Qm.getDateFormat(col.formater));
                }
                para.push(p);
            }
        }, this);
        this.bar.setSessionCondition(para);
        this.bar.setSearchPara(para);
        this.bar.doLoad(0);
    },
    columnResize:function (columnIndex, newSize) {
        Ext.Ajax.request({
            url: Qm.pagingUrlPre + 'changeWidth',
            params: {pageid:Qm.config.pageid, column: this.getColumnModel().getDataIndex(columnIndex),width:newSize }
        });
    },
    columnMove:function (cm) {
        var count = cm.getColumnCount();
        var str = "";
        for (var i = 1; i < count; i++) {
            if (!cm.isHidden(i))
                str += cm.getDataIndex(i) + (i < count - 1 ? "," : "");
        }
        Ext.Ajax.request({
            url: Qm.pagingUrlPre + 'changeOrder',
            params: {pageid:Qm.config.pageid, columns:str }
        });
    },
    columnHidden:function (cm, columnIndex, hidden) {
        Ext.Ajax.request({
            url: Qm.pagingUrlPre + 'changeVisable',
            params: {pageid:Qm.config.pageid, column:cm.getColumnById(columnIndex).dataIndex,visable:hidden ? "0" : "1" },
            bar:this.bar,
            success:function() {
                if (!hidden) {
                    Qm.refreshGrid();
                }
            }
        });
    },
    resetConfig:function() {
    Ext.MessageBox.confirm('确认', '您确定要恢复到系统的默认配置吗？', function(btn, text) {
        if (btn == "yes") {
            Ext.Ajax.request({
                url: Qm.pagingUrlPre + 'resetConfig',
                params: {pageid:Qm.config.pageid},
                success: function(response) {
                    var obj = Ext.util.JSON.decode(response.responseText);
                    if (obj.success) {
                        Qm.refreshPage();
                    }
                }
            });
        }
    });
}
});
Ext.reg("qm", Qm.QmPanel);

Qm.QmBuilder = Ext.extend(Ext.Window,{
//    id:'qm_filterWin',
    bar:{},
    title:'高级',
    iconCls:'advanced',
    layout:'fit',
    autoScroll:true,
    width:600,
    height:250,
    minWidth : 550,
    minHeight :200,
    closeAction:'hide',
    maximizable :true,
    rowId:0,
    sortRowId:0,
    animateTarget: 'searchBarButton',

    fieldNameComboStore:{},
    operators : {
        defaults : [
            ['等于', '='],
            ['不等于', '!='],
            ['大于', '>'],
            ['大于等于', '>='],
            ['小于', '<'],
            ['小于等于', '<=']
        ],
        string : [
            ['等于', '='],
            ['不等于', '!='],
            ['开始于', "llike'"],
            ['结束于', "rlike"],
            ['包含', "like"],
            ['等于其中', 'in']
        ],
        number : [
            ['等于', '='],
            ['不等于', '!='],
            ['大于', '>'],
            ['大于等于', '>='],
            ['小于', '<'],
            ['小于等于', '<=']
        ],
        date : [
            ['等于', '='],
            ['不等于', '!='],
            ['大于', '>'],
            ['大于等于', '>='],
            ['小于', '<'],
            ['小于等于', '<=']
        ],
        multiple : [
            ['等于', '='],
            ['不等于', '!=']
        ],
        multipletree : [
            ['等于', '='],
            ['不等于', '!=']
        ]
    },
    initComponent: function() {
        var columns = Qm.config.columnsInfo;
        var data = new Array();
        var j = 0;
        for (var v in columns) {
            if(columns[v].config=="1")
            data[j++ ] = {code:columns[v].columm,value:columns[v].columndisplay};
        }
        this.fieldNameComboStore = new Ext.data.JsonStore({
            fields: ['code', 'value'],
            data : data
        });
        this.queryRowTemplate = new Ext.Template(
                '<tr ext_id="{rowId}" id = "' + this.id + '_queryBuilderRow{rowId}">',
                '<td>',
                '<div style="display:inline;width:30px" id="' + this.id + '_removeBox{rowId}"></div>',
                '</td>',
                '<td>',
                '<div style="display:inline" id="' + this.id + '_andOr{rowId}"></div>',
                '</td>',
                '<td>',
                '<div style="display:inline" id="' + this.id + '_fieldNameBox{rowId}"></div>',
                '</td>',
                '<td>',
                '<div style="display:inline" id="' + this.id + '_operatorBox{rowId}"></div>',
                '</td>',
                '<td>',
                '<div style="display:inline" id="' + this.id + '_valueBox{rowId}" ></div>',
                '</td>',
                '</tr>'
        );
        this.sortRowTemplate = new Ext.Template(
                '<tr ext_id="{rowId}" id = "' + this.id + '_sortBuilderRow{rowId}">',
                '<td>',
                '<div style="display:inline;width:30px" id="' + this.id + '_removeSortBox{rowId}"></div>',
                '</td>',
                '<td>',
                '<div style="display:inline" id="' + this.id + '_sortFieldNameBox{rowId}"></div>',
                '</td>',
                '<td>',
                '<div style="display:inline" id="' + this.id + '_sortBox{rowId}"></div>',
                '</td>',
                '</tr>'
        );
        this.items = {
            xtype:'tabpanel',
            activeTab: 0,
            items: [
                {
                    title: '组合查询',
                    iconCls:'searchtab',
                    autoScroll :true,
                    extid:'searchtab',
                    html : '<div style="padding: 10px;"><table cellspacing="3"><tbody id="' + this.id + '_FilterContainer"></tbody></table></div>',
                    bbar :[
                        {
                            text:'新增',
                            iconCls:'rowAdd',
                            handler:this.addRow.createDelegate(this)
                        },
                        "-",
                        {
                            text:'清除',
                            iconCls:'rowDel',
                            qmBuilder:this,
                            handler:function() {
                                var qmBuilder=this.qmBuilder;
                                Ext.MessageBox.confirm('确认', '您确定要清除所有查询条件吗？', function(btn, text) {
                                    if (btn == "yes") {
                                        qmBuilder.removeAllRow();
                                    }
                                });
                            }
                        },
                        "-",
                        {
                            text:'恢复默认',
                            iconCls:'default',
                            handler:function() {
                                Ext.MessageBox.confirm('确认', '您确定要恢复到系统设置的默认查询条件吗？', function(btn, text) {
                                    if (btn == "yes") {
                                        Ext.Ajax.request({
                                            url: Qm.pagingUrlPre + "filterData&pageid=" + Qm.config.pageid,
                                            success: function(response) {
                                                var obj = Ext.util.JSON.decode(response.responseText);
//                                                bar.setSearchPara(obj.conditions);
//                                                delFilterRow();
//                                                reAddFilterRow();
                                            }
                                        });
                                    }
                                });
                            }
                        },
                        "->",
                        {
                            text:'执行',
                            iconCls:'run',
                            handler:this.run.createDelegate(this)
                        }
                    ],
                    keys:{
                        key: Ext.EventObject.ENTER,
                        fn: this.run,
                        scope: this
                    }
                },
                {
                    title: '多级排序',
                    iconCls:'sorttab',
                    autoScroll :true,
                    html : '<div style="padding: 10px;"><table cellspacing="3"><tbody id="' + this.id + '_SorterContainer"></tbody></table></div>',
                    bbar :[
                        {
                            text:'新增',
                            iconCls:'rowAdd',
                            handler:this.addSortRow.createDelegate(this)
                        },
                        "-",
                        {
                            text:'清除',
                            iconCls:'rowDel',
                            qmBuilder:this,
                            handler:function() {
                                var qmBuilder=this.qmBuilder;
                                Ext.MessageBox.confirm('确认', '您确定要清除所有排序条件吗？', function(btn, text) {
                                    if (btn == "yes") {
                                        qmBuilder.removeAllSortRow();
                                    }
                                });
                            }
                        },
                        "->",
                        {
                            text:'执行',
                            iconCls:'run',
                            handler:this.runSort.createDelegate(this)
                        }
//                ,new Ext.form.Checkbox({boxLabel:"在列表上显示查询面板",checked:Qm.config.searchpanel == "1",listeners: {
//                check: function(t, b) {
//                    Qm.config.searchpanel = b ? "1" : "0";
//                    document.getElementById("seachPanelDiv").innerHTML = b ? getSeachPanelTableHTML(bar.getSearchPara()) : "";
//                    seachPanel1.setVisible(!b);
//                    seachPanel2.setVisible(b);
//                    qmresize();
//                }
//            }})
                    ],
                    keys:{
                        key: Ext.EventObject.ENTER,
                        fn: this.runSort,
                        scope: this
                    }
                }
            ]
        },
        this.queryRowTemplate.compile() ;
        this.sortRowTemplate.compile() ;
        Qm.QmBuilder.superclass.initComponent.call(this);

    },
    afterRender : function() {
        Qm.QmBuilder.superclass.afterRender.call(this);
        var con=Qm.config.advancedSearchPara;
        if(Qm.config.sessionAdvancedCondition)con=Qm.config.sessionAdvancedCondition;
        if (con.length > 0) {
            for(var i=0;i<con.length;i++)
                this.addRow(con[i]);
        } else {
            this.addRow();
        }
    },
    addRow:function(con) {
        if(con){
            if (!con.logic) {
                con = undefined;
            }
        }
//        alert(Ext.encode(con))
        //var con={label:"日期1",logic:"and",name:"date1",compare:">",dataType:"date",value:""};
        this.rowId++;
        this.queryRowTemplate.append(this.id + '_FilterContainer', {rowId: this.rowId});
        if (this.rowId == 1) {
            new Ext.Button({
                id: this.id + '_addRowButton' + this.rowId,
                iconCls : 'rowAdd',
                tooltip:'增加查询项',
                renderTo: this.id + '_removeBox' + this.rowId,
                qmBuilder: this,
                handler: function() {
                    this.qmBuilder.addRow();
                }
            });
            Ext.get(this.id + '_andOr' + this.rowId).hide();
        } else {
            new Ext.Button({
                id: this.id + '_removeRowButton' + this.rowId,
                iconCls : 'rowDel',
                tooltip:'删除查询项',
                renderTo: this.id + '_removeBox' + this.rowId,
                qmBuilder:this,
                rowId:this.rowId,
                handler: function() {
                    var rowId = this.rowId;
                    this.qmBuilder.removeRow(rowId);
                }
            });

            new Ext.form.ComboBox({
                id: this.id + '_andOrCombo' + this.rowId,
                store:  new Ext.data.ArrayStore({
                    fields: ['code', 'value'],
                    data : [
                        ["and","并且"],
                        ["or","或者"]
                    ]
                }),
                displayField: 'value',
                valueField: 'code',
                editable :false,
                width: 50,
                mode: 'local',
                forceSelection: true,
                triggerAction: 'all',
                value: con?con.logic:'and',
                selectOnFocus:true,
                allowBlank: false,
                hiddenName:this.id + '_andOrCombo',
                renderTo: this.id + '_andOr' + this.rowId
            });
        };

        new Ext.form.ComboBox({
            id: this.id + '_fieldNameCombo' + this.rowId,
            name: 'columnName',
            hiddenName:'columnName',
            displayField:'value',
            valueField: 'code',
            triggerAction: 'all',
            editable :false,
            mode:'local',
            allowBlank: false,
            queryBuilderField: true,
            qmBuilder: this,
            emptyText: '选择查询列',
            renderTo: this.id + '_fieldNameBox' + this.rowId,
            store: this.fieldNameComboStore,
            rowId:this.rowId,
            formID:this.id,
            value: con?con.name:undefined,
            listeners: {
                change: function(thisField, newVal, oldVal) {

//                    opCombo.fireEvent('change');
                    if (Ext.getCmp(this.formID + '_searchValueTextField' + this.rowId)) {
                        Ext.destroy(Ext.getCmp(this.formID + '_searchValueTextField' + this.rowId));
                    }
                    this.qmBuilder.addField(Qm.config.columnsInfo[newVal], this.rowId);
                    this.qmBuilder.reloadOperator(this.formID, this.rowId,newVal);

                }
            }
        });
        var opdata = this.operators.defaults;
        if (con) {
            var obj = Qm.config.columnsInfo[con.name];
            var valueType = Qm.getColumnValueType(obj);
            opdata = this.operators[valueType];
            this.addField(obj, this.rowId, con.value);
        }
        new Ext.form.ComboBox({
            id:this.id + '_operatorsCombo' + this.rowId,
            store: new Ext.data.SimpleStore({
                fields: [ 'txt', 'val'],
                data: opdata
            }),
            displayField:'txt',
            valueField:'val',
            value:'=',
            editable: false,
            allowBlank: false,
            queryBuilderField:true,
            width: 80,
            mode: 'local',
            forceSelection: true,
            triggerAction: 'all',
            emptyText:'选择条件',
            selectOnFocus:true,
            renderTo: this.id + '_operatorBox' + this.rowId,
            qmBuilder:this,
            value: con?con.compare:undefined
        });

    },
	removeRow: function(rowId) {
        if (Ext.getCmp(this.id + '_searchValueTextField' + rowId)) {
            Ext.destroy(Ext.getCmp(this.id + '_searchValueTextField' + rowId));
        }
        Ext.getCmp(this.id + '_fieldNameCombo' + rowId).destroy();
        Ext.getCmp(this.id + '_operatorsCombo' + rowId).destroy();
        if (rowId > 1) {
            Ext.getCmp(this.id + '_andOrCombo' + rowId).destroy();
            Ext.getCmp(this.id + '_removeRowButton' + rowId).destroy();
        }else{
            Ext.getCmp(this.id + '_addRowButton' + rowId).destroy();
        }
        var row = Ext.select('#' + this.id + '_queryBuilderRow' + rowId);
        row.removeElement(this.id + '_queryBuilderRow' + rowId, true);
    },
	run: function() {
        var para = [];
        var table = document.getElementById(this.id + '_FilterContainer');
        for (var i = 0; i < table.rows.length; i++) {
            var rowId = table.rows[i].getAttribute("ext_id");

            var p = {};
            p.name = Ext.getCmp(this.id + '_fieldNameCombo' + rowId).getValue();
            var col = Qm.config.columnsInfo[p.name];
            if (col) {
                var vf = Ext.getCmp(this.id + '_searchValueTextField' + rowId);
                p.logic = i == 0 ? "and" : Ext.getCmp(this.id + '_andOrCombo' + rowId).getValue();
                p.label = col.columndisplay;
//                p.name = Ext.getCmp(this.id + '_fieldNameCombo' + rowId).getValue();
                p.compare = Ext.getCmp(this.id + '_operatorsCombo' + rowId).getValue();
                p.dataType = col.datatype;
                p.value = vf ? vf.getValue() : "";

                if (p.dataType == "date" && !Ext.isEmpty(p.value)) {
                    p.value = p.value.format(Qm.getDateFormat(col.formater));
                }
                para.push(p);
            }
        }
        this.bar.setSessionAdvancedCondition(para);
        this.bar.setSearchPara(para);
        this.bar.setConChange(true);
        this.bar.doLoad(0);
        this.hide();
    },
	removeAllRow: function() {
        var table=document.getElementById(this.id+'_FilterContainer');
        while (table.rows.length > 0) {
            this.removeRow(table.rows[0].getAttribute("ext_id"));
        }
        this.rowId=0;
        this.run();
    },
    addSortRow:function() {
        this.sortRowId++;
        this.sortRowTemplate.append(this.id + '_SorterContainer', {rowId: this.sortRowId});
        if (this.sortRowId == 1) {
            new Ext.Button({
                id: this.id + '_addSortRowButton' + this.sortRowId,
                iconCls : 'rowAdd',
                tooltip:'增加排序项',
                renderTo: this.id + '_removeSortBox' + this.sortRowId,
                qmBuilder: this,
                handler: function() {
                    this.qmBuilder.addSortRow();
                }
            });
        } else {
            new Ext.Button({
                id: this.id + '_removeSortRowButton' + this.sortRowId,
                iconCls : 'rowDel',
                tooltip:'删除排序项',
                renderTo: this.id + '_removeSortBox' + this.sortRowId,
                qmBuilder:this,
                rowId:this.sortRowId,
                handler: function() {
                    var rowId = this.rowId;
                    this.qmBuilder.removeSortRow(rowId);
                }
            });
        }
        new Ext.form.ComboBox({
            id: this.id + '_sortFieldNameCombo' + this.sortRowId,
            name: 'columnName',
            hiddenName:'columnName',
            displayField:'value',
            valueField: 'code',
            triggerAction: 'all',
            editable :false,
            mode:'local',
            allowBlank: false,
            queryBuilderField: true,
            qmBuilder: this,
            emptyText: '选择排序列',
            renderTo: this.id + '_sortFieldNameBox' + this.sortRowId,
            store: this.fieldNameComboStore
        });
        new Ext.form.ComboBox({
            id:this.id + '_sortCombo' + this.sortRowId,
            store: new Ext.data.SimpleStore({
                fields: [  'val','txt'],
                data: [
                    ["ASC","升序"],
                    ["DESC","降序"]
                ]
            }),
            displayField:'txt',
            valueField:'val',
            value:'ASC',
            editable: false,
            allowBlank: false,
            width: 80,
            mode: 'local',
            forceSelection: true,
            triggerAction: 'all',
            emptyText:'排序方式',
            selectOnFocus:true,
            renderTo: this.id + '_sortBox' + this.sortRowId
        });
    },
	removeSortRow: function(rowId) {
        Ext.getCmp(this.id + '_sortCombo' + rowId).destroy();
        Ext.getCmp(this.id + '_sortFieldNameCombo' + rowId).destroy();
        if (rowId > 1) {
            Ext.getCmp(this.id + '_removeSortRowButton' + rowId).destroy();
        }
        var row = Ext.select('#' + this.id + '_sortBuilderRow' + rowId);
        row.removeElement(this.id + '_sortBuilderRow' + rowId, true);
    },
    removeAllSortRow: function() {
        var table=document.getElementById(this.id+'_SorterContainer');
        while (table.rows.length > 0) {
            this.removeSortRow(table.rows[0].getAttribute("ext_id"));
        }
        this.sortRowId=0;
        this.runSort();
    },
    addField: function(obj,rowId,value) {
        var valueType=Qm.getColumnValueType(obj);
        var config={id: this.id + '_searchValueTextField' + rowId,renderTo: this.id + '_valueBox' + rowId,width: 215};
        if(value){
            config.value=value;
        }
        if (valueType == 'multiple') {
            new Ext.form.ComboBox(Ext.apply(config,{
                store:new Ext.data.ArrayStore({
                    fields: ['code', 'value'],
                    data : obj.binddata
                }),
                hiddenName:'valueName',
                displayField: 'value',
                valueField: 'code',
                typeAhead: true,
                mode: 'local',
                forceSelection: true,
                triggerAction: 'all',
                selectOnFocus: true
            }));
        }
        else if (valueType == 'multipletree') {
            new kh.us.ComboBoxTree(Ext.apply(config,{
                data : obj.binddata,
                editable:true,
                name:'valueName'
            }));
        } else {
            switch (valueType) {
                case 'number':
                    new Ext.form.NumberField(Ext.apply(config,{}));
                    break;
                case 'date':
                    new Ext.form.DateTimeField(Ext.apply(config,{
                        format:Qm.getDateFormat(obj.formater)
                    }));
                    break;
                default:
                    new Ext.form.TextField(Ext.apply(config,{}));
                    break;
            }
        }
    },
    reloadOperator:function(formID, rowId, val) {
        var opCombo = Ext.getCmp(formID + '_operatorsCombo' + rowId);
        opCombo.clearValue();
        var obj=Qm.config.columnsInfo[val];
        var valueType=Qm.getColumnValueType(obj);
        opCombo.store.removeAll();
        opCombo.store.loadData(this.operators[valueType]);
        opCombo.setValue("=");
        switch (valueType) {
                case 'string':
                    opCombo.setValue("like");
                    break;
                case 'date':
                    opCombo.setValue(">");
                    break;
                default:
                    opCombo.setValue("=");
                    break;
            }
    },
    runSort:function(){
        var para = [];
        var table = document.getElementById(this.id + '_SorterContainer');
        for (var i = 0; i < table.rows.length; i++) {
            var rowId = table.rows[i].getAttribute("ext_id");
            var p = {};
            p.field = Ext.getCmp(this.id + '_sortFieldNameCombo' + rowId).getValue();
            p.direction = Ext.getCmp(this.id + '_sortCombo' + rowId).getValue();
            para.push(p);
        }
        this.bar.setSortInfo(para);
        if(para.length>0)
            this.bar.store.setDefaultSort(para[0].field,para[0].direction);
        else
            this.bar.store.setDefaultSort("","");
        this.bar.doLoad(0);
        this.hide();
    }
});

Qm.init = function() {

    Ext.apply(Qm.config, {});
    try {
        Qm.userConfig = qmUserConfig || {};
        if(Qm.userConfig.listeners){
             if((typeof Qm.userConfig.listeners.beforeQmReady)=="function"){
                   Qm.userConfig.listeners.beforeQmReady();
             }
        }
    } catch(e) {
    }
    Qm.config.qmUserConfig = Qm.userConfig;
    if(Qm.userConfig.title){
        Qm.config.pageTitle=Qm.userConfig.title;
    }
    Ext.QuickTips.init();
    var pgrid = new Qm.QmPanel(Qm.config);
    window.onresize = function() {
        pgrid.qmresize();
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * public 得到Ext原生Grid
     */
    Qm.setTbar = function(names, status) {
        var ids = names.split(",");
        var ss = status.split(",");
        for (var i = 0; i < ids.length; i++) {
            var obj=Ext.getCmp(ids[i]);
            if(obj)
            obj.setDisabled(ss[i] == "0");
        }
    }
    /**
     * public 得到Ext原生Grid
     */
    Qm.getQmgrid = function() {
        return pgrid.getQmgrid();
    }
    /**
     * public 设置查询条件 可以是数组或单个json对象
     *  {label:"",logic:"and",name:"",compare:"=",dataType:"string",value:""}
     */
    Qm.setCondition = function(con) {
        pgrid.setCondition(con);
    }
    /**
     * public 查询数据
     */
    Qm.searchData = function() {
        pgrid.searchData();
    }
    /**
     * public 刷新表格数据
     */
    Qm.refreshGrid = function() {
        pgrid.refreshGrid();
    }

    Qm.getSelectedCount = function() {
        return pgrid.getSm().getCount();
    }

    Qm.getValueByName = function (name) {
        var arr = Qm.getValuesByName(name);
        if (arr.length > 0)
            return arr[0];
        else
            return "";
    }

    /**
     * public
     */
    Qm.getValuesByName = function(name) {
        var arr = [];
        var records = pgrid.getSm().getSelections();
        var l = records.length;
        for (var i = 0; i < l; i++) {
            arr[i] = records[i].get(name);
        }
        return arr;
    }

    /**
     * public 刷新页面
     */
    Qm.refreshPage = function() {
        window.location.reload();
    }

};


/////////////////////////////////////////////////////////