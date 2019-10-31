window.Qm = {};
var Ext = {};
Ext.onReady = $;
$.toJSON = JSON.stringify;
window.Qm = {
    version: "1.3.1.1.9",
    versionDetail: {major: 1, minor: 2, patch: "1.1.9", plugin: "jquerymobile"},
///////////////////以下函数可以在自己的页面调用//////////////////
    /**
     * public 设置工具条按钮状态
     */
    setTbar: function () {
    },
    /**
     * public 查询数据
     */
    searchData: function () {
    },
    /**
     * public 刷新表格数据
     */
    refreshGrid: function () {
    },
    /**
     * 得到选中行数
     */
    getSelectedCount: function () {
    },
    /**
     * 取值
     * @param name
     */
    getValueByName: function (name) {
    },
    /**
     * public
     */
    getValuesByName: function (name) {
    },
    /**
     * public 刷新页面
     */
    refreshPage: function () {
    },
////////////////////////////////////////////////////////////////
    pagingUrlPre: "qm?__method=",
    config: {},
    userConfig: {},
    separator: {line: true},
    getColumnValueType: function (obj) {
        var type = "string";
        if (obj) {
            if ($.isArray(obj.binddata)) {
                if (obj.binddata.length > 0) {
                    type = "multiple";
                    for (var i in obj.binddata) {
                        if (obj.binddata[i].children) {
                            type = "multipletree";
                            break;
                        }
                    }
                }
            } else {
                type = obj.datatype;
            }
        }
        return type;
    },
    parseSearchGroup: function (group) {
        for (var i in group) {
            Qm.parseSearchItem(group[i]);
        }
    },
    parseSearchItem: function (obj) {
        if (obj.group) {
            Qm.parseSearchGroup(obj.group);
        }
        var col = Qm.config.columnsInfo[obj.name];
        var valueType = Qm.getColumnValueType(col);
        if(col){
            obj["columndisplay"] = col["columndisplay"];
        }
        if (valueType == "multiple") {
            if (col.binddata.length > 0 && col.binddata[0].id) {
                if (obj.xtype == "radioGroup") {
                    col.binddata.unshift({id: "", text: "全部"});
                }
            } else {
                if (obj.xtype == "radioGroup") {
                    col.binddata.unshift(["", "全部"]);
                }
            }

            obj.xtype = "combo";
            obj.data = Qm.parseComboData(col.binddata);
        }
        else if (valueType == "multipletree") {
            obj.xtype = "comboxtree";
            obj.tree = {data: col.binddata, checkbox: false};
            obj.data=Qm.parseComboTreeData(col.binddata);
        }
        else if (valueType == "date") {
            obj.xtype = "date";
            obj.format = Qm.getDateFormat(col.formater);
            if (/mm/.test(obj.format)) {
                obj.showTime = true;
            }
        }
        else if (valueType == "number") {
            obj.xtype = "number";
            if (!obj.value && !obj.initValue)
//                obj.value="";
                obj.type = "int";
        } else {
            obj.xtype = "text";
        }

    },
    getSearchItemInput: function (item) {
        var divStr = '';
        if (item.xtype == "combo" || item.xtype == "comboxtree") {
            divStr = '<select name="' + item.name + '" id="' + item.id + '" data-iconpos="left">'
            for (var d in item.data) {
                divStr += '<option value="' + item.data[d]["id"] + '">' + item.data[d]["text"] + '</option>';
            }
            divStr += '</select>';
        } else if (item.xtype == "radioGroup") {
            divStr += " type='radio' name='" + item.id + "-txt'";
        } else if (item.xtype == "checkboxGroup") {
            divStr += " type='checkbox' name='" + item.id + "-txt'";
        } else if (item.xtype == "date") {
            divStr += "<input type='date' data-iconpos='left' id='" + item.id + "' data-role='date' placeholder='请输入" + item["columndisplay"] + "' name='" + item["name"] + "'/>";
        } else {
            var placeholder="请输入"+item["columndisplay"];
            if(item["placeholder"]){
                placeholder=item["placeholder"];
            }
            divStr += "<input type='search' id='" + item.id + "' data-role='search' placeholder='" + placeholder + "' name='" + item["name"] + "'/>";
        }
        return divStr;
    },
    getDateFormat: function (formater) {//实现前后台日期格式转换
        return formater == "" ? "yyyy-MM-dd" : formater.replace("HH", "hh");
    },
    parseStyleWidth: function (width) {
        var cWidth = width;
        if (this.config.sp_defaults.layout == "column") {
            if (!cWidth) {//2013-6-3 下午4:41 lybide
                return "";
            }
            if (!isNaN(cWidth)) {
                if (cWidth <= 1) {
                    cWidth = cWidth * 100 + "%";
                } else if (cWidth > 1) {
                    cWidth = cWidth + "px";
                } else {
                    cWidth = "";
                }
            }
        } else if (this.config.sp_defaults.layout == "float") {
            if (!isNaN(cWidth)) {
                if (cWidth == 1) {
                    cWidth = "auto";
                } else if (cWidth > 1) {
                    cWidth = cWidth + "px";
                } else {
                    cWidth = "";
                }
            }
        }
        return cWidth;
    },
    parseComboData: function (data) {
        if (data) {
            if (data.length > 0 && !$.isArray(data[0])) {
                return data;
            }
            var json = [];
            for (var i = 0; i < data.length; i++) {
                json.push({id: data[i][0], text: data[i][1]});
            }
            return json;
        }
    },
    parseComboTreeData: function (data) {
        if (data) {
            var json = [];
            for (var i = 0; i < data.length; i++) {
                json.push({id: data[i]["id"], text: data[i]["text"]});
                if(data[i].children){
                    $.merge(json, this.parseComboTreeData(data[i].children));
                }
            }
            return json;
        }
    },
    //构建工具条
    buildToolbar: function () {
        var tools = this.userConfig.tbar || [];
        if (tools.length > 0) {
            for (var i = 0; i < tools.length; i++) {
                if (typeof tools[i] == "string") {
                    if (tools[i] == "-") {
                        tools[i] = this.separator;
                    }

                }
            }
        }
        return tools;

    },
    getParams: function () {
        var params = [];
        $.each(this.config.searchItems, function (index, items) {
            var item = items;
            if (items.group) {
                for (var i in items.group) {
                    item = items.group[i];
                    params.push({
                        "logic": item.logic || "and",
                        "label": item.fieldLabel,
                        "name": item.name,
                        "compare": item.compare,
                        "dataType": item.dataType,
                        "value": item.ele.getValue()
                    });
                }
            } else {
                params.push({
                    "logic": item.logic || "and",
                    "label": item.fieldLabel,
                    "name": item.name,
                    "compare": item.compare,
                    "dataType": item.dataType,
                    "value": item.ele.getValue()
                });
            }
        });
        return params;
    },
    getRow: function (a, b, c, d, e) {
        return a;
    },
    getThisValue: function (a, b, c, d, e) {
        return c;
    },
    advance: function () {
        if(!Qm.qmBuilder){
            Qm.qmBuilder=new Qm.QmBuilder();
        }
        Qm.qmBuilder.show();
    },
    setSessionAdvancedCondition:function(con){
        this.config.sessionAdvancedCondition=con;
    },
    getSessionAdvancedCondition:function(){
        return this.config.sessionAdvancedCondition;
    },
    setSearchPara:function(con){
        this.config.searchPara=con;
    },
    getSearchPara:function(){
        return this.config.searchPara;
    },
    init: function () {
        //Qm.config.qmUserConfig = Qm.userConfig;
        if (Qm.userConfig.title) {
            Qm.config.pageTitle = Qm.userConfig.title;
        }

        var columns = Qm.config.columnsInfo;
        var cmArr = [];
        var j = 0;
        var width = 0;
        var width1 = 0;
        for (var v in columns) {
            width += columns[v].width;
            if (columns[v].visible != "0")
                width1 += columns[v].width;
            cmArr[j] = {
                display: columns[v].columndisplay,
                name: columns[v].columm,
                align: columns[v].align,
                width: columns[v].width,
                hide: (columns[v].visible == "0"),
                sortable: true,
                resizable: Qm.config.selfhood || false,
                isAllowHide: (columns[v].config == "1")
            };
            if (columns[v].formater != "" && columns[v].datatype != "date") {
                cmArr[j].render = columns[v].formater;
            }
            j++;
        }

        Qm.config = $.extend(true, {
            pageid: "",
            pageSize: Qm.config.pagesize,

            defaultSearchConditionName: "defaultSearchCondition",
            manmadeSearchConditionName: "manmadeSearchCondition",
            sessionConditionName: "sessionCondition",
            sessionAdvancedConditionName: "sessionAdvancedCondition",
            searchParaName: "searchPara",
            sortInfoName: "sortInfo",
            sortInfo: [],

            defaultSearchCondition: [],
            manmadeSearchCondition: [],
            searchPara: [],
            advancedSearchPara: [],

            sessionCondition: [],
            sessionAdvancedCondition: [],

            searchItems: []

        }, Qm.config, Qm.userConfig);


        if (Qm.config.sortInfo.length > 0) {
            var sortInfo = Qm.config.sortInfo;
            $.extend(Qm.config, {sortName: sortInfo[0].field, sortOrder: sortInfo[0].direction.toLowerCase()});
        }
        getData(1);
    }
};
(function ($) {
    /////////////////////////////////////////////////方法实现/////////////////////////////////////////////////////////////////////////////
    $(document).on("pagebeforecreate", function (event) {
        if ($("#__qm_iscroll_content").size() == 0) {
            var container = '<div data-iscroll="" role="main" id="__qm_iscroll_content" data-role="content" ><div class="iscroll-pulldown"> <span class="iscroll-pull-icon"></span><span class="iscroll-pull-label"></span></div> ';
            if (Qm.config.listModel == "listview") {
                container += '<ul data-role="listview" id="__qm_list" data-filter="true" data-input="#__qm_search"></ul>';
            } else {
                container += '<table data-role="table" id="__qm_list" data-mode="reflow" class="ui-responsive table-stroke"> <thead> <tr> ';
                var i = 1;
                $.each(Qm.config.columnsInfo, function (index, item) {
                    if (item["visible"] == "1") {
                        if (item["width"] == -1) {
                            container += '<th>' + item["columndisplay"] + '</th> ';
                        } else {
                            container += '<th data-priority="' + (i++) + '">' + item["columndisplay"] + '</th> ';
                        }
                    }
                });
                container += '</thead><tbody></tbody></table>';
            }
            container += '<div class="iscroll-pullup"> <span class="iscroll-pull-icon"></span><span class="iscroll-pull-label"></span></div>';
            try {
                Qm.userConfig = qmUserConfig || {};
            } catch (e) {
            }
            if (Qm.userConfig.sp_fields && Qm.userConfig.sp_fields.length > 0) {
                var field = Qm.userConfig.sp_fields[0];
                if (!field["id"]) {
                    field["id"] = "__qm_search_id_0";
                }
                Qm.parseSearchItem(field);
                var searchItem = Qm.getSearchItemInput(field);
                container = '<div id="__qm_search" data-id="header" data-position="fixed" data-role="header" data-transition="none">' +
                '<table><tr>' +
                '<td>' + searchItem + '</td>' +
                '<td class="search-tool">' +
                '<button id="_qmSearch"class="ui-btn ui-icon-search ui-btn-icon-notext ui-corner-all" ></button>' +
                '<button id="_qmSerQuery" class="ui-btn ui-icon-gear ui-btn-icon-notext ui-corner-all" ></button>' +
                '</td>' +
                '</tr></table>' +
                '</div>' +
                '<div data-role="popup" data-history="false" id="advancePopup" data-overlay-theme="b" data-theme="a" data-tolerance="15,15" class="ui-content">' +
                '</div>' + container;
            }
            $(container).appendTo(".qm-page");
            $("#__qm_search").find("#_qmSearch").unbind("fastClick").bind("fastClick",function(){
            	Qm.searchData();
            })
            $("#__qm_search").find("#_qmSerQuery").unbind("fastClick").bind("fastClick",function(){
            	Qm.advance();
            })
        }
    });
    $(document).delegate("div.qm-page", "pageinit",
        function bindShortPullPagePullCallbacks(event) {
            $(".iscroll-wrapper", this).unbind().bind({
                iscroll_onpulldown: onPullDown,
                iscroll_onpullup: onPullUp,
                iscroll_onpulldownreset: parseNote,
                iscroll_onpullupreset: parseNote,
                iscroll_onbeforescrollstart: beforeScrollStart
            });
        });
    /**
     * public 设置工具条状态
     */
    Qm.setTbar = function (status) {

    }
    /**
     * public 得到ligerui原生Grid
     */
    Qm.getQmgrid = function () {
        return $("#__qm_list");
    }
    /**
     * public 设置查询条件 可以是数组或单个json对象
     *  {label:"",logic:"and",name:"",compare:"=",dataType:"string",value:""}
     */
    Qm.setCondition = function (con) { //todo
    }
    /**
     * public 查询数据
     */
    Qm.searchData = function () {
        var para=getParams();
        Qm.setSearchPara(para);
        getData(1);
    }
    /**
     * public 刷新表格数据
     */
    Qm.refreshGrid = function () {
        getData(1);
    }

    Qm.getSelectedCount = function () {
        return 1;
    }

    Qm.getValueByName = function (name) {
        if (Qm.config.currentData) {
            return Qm.config.currentData[name];
        }
        return "";
    }

    /**
     * public
     */
    Qm.getValuesByName = function (name) {
        var arr = [];
        if (Qm.config.currentData) {
            arr[0] = Qm.config.currentData[name];
        }
        return arr;
    }

    /**
     * public 刷新页面
     */
    Qm.refreshPage = function () {
        window.location.reload();
    }

    Qm.listClick = function () {
        var index = $(this).jqmData("index");
        Qm.config.currentData = Qm.config.data[index];
        if (Qm.userConfig.tbar) {
            if (Qm.userConfig.tbar.length > 1) {
                var html = '<div data-role="popup" id="popupMenu" data-theme="b">' +
                    '<ul data-role="listview" data-inset="true" style="min-width:200px;">' +
                    '<li data-role="list-divider">请选择</li>';
                $.each(Qm.userConfig.tbar, function (index, item) {
                	var li = '<li index="'+index+'" data-icon="' + item["icon"] + '"><a  data-btn-icon="plus">' + item["text"] + '</a></li>';
                	html += li;
                });
                html += '</ul> </div>';
                if(!$("#popupMenu").length>0){
                	$(html).appendTo($.mobile.pageContainer).trigger("create").trigger('refresh');
                }
                /*$("#popupMenu").popup({history:false}).popup("open").find("li").unbind("tap").bind("tap",function(){
        			Qm.userConfig.tbar[$(this).attr("index")].click();
        			$("#popupMenu").popup("close");
        		})*/
                $("#popupMenu").popup({history:false}).popup("open").find("li").unbind("fastClick").bind("fastClick",function(){
        			Qm.userConfig.tbar[$(this).attr("index")].click();
        			$("#popupMenu").popup("close");
        		})
            } else {
                Qm.userConfig.tbar[0].click();
            }
        }
    }
})(jQuery);


Qm.QmBuilder = function (options) {
    this.options = $.extend({
        searchRenderTo:"__qm_advanced__search__body",
        sortRenderTo:"__qm_advanced__sort__body"
    }, options);
    this.builderIndex=0;
    this.operators = {
        defaults: [
            ['等于', '='],
            ['不等于', '!='],
            ['大于', '>'],
            ['大于等于', '>='],
            ['小于', '<'],
            ['小于等于', '<=']
        ],
        string: [
            ['等于', '='],
            ['不等于', '!='],
            ['开始于', "llike'"],
            ['结束于', "rlike"],
            ['包含', "like"],
            ['等于其中', 'in']
        ],
        number: [
            ['等于', '='],
            ['不等于', '!='],
            ['大于', '>'],
            ['大于等于', '>='],
            ['小于', '<'],
            ['小于等于', '<=']
        ],
        date: [
            ['等于', '='],
            ['不等于', '!='],
            ['大于', '>'],
            ['大于等于', '>='],
            ['小于', '<'],
            ['小于等于', '<=']
        ],
        multiple: [
            ['等于', '='],
            ['不等于', '!=']
        ],
        multipletree: [
            ['等于', '='],
            ['不等于', '!=']
        ]
    };
    this.groupTable="<table class='qm-groupTable' border='0' cellpadding='0' cellspacing='0'>" +
    "<td class='qm-groupTable-td1' align='left'><input type='button' value='+'  id='advanceAdd' title='新增条件' class='qm-m-button'><input type='button' value='-'  id='advanceDel' title='删除所有条件' class='qm-m-button'></td>" +
    "<td class='qm-groupTable-td2' align='right'><input id='advanceRun' type='button' value='查询' class='qm-m-button'></td>" +
    "</tr>" +
    "<tr>" +
    "<td colspan='2' class='groupPanel'>" +
    "<div class='qm-conditionTable'></div>" +
    "</td>" +
    "</tr>" +
    "</table>";
    this.defaultColumn={};
    this.defaultCon=[];
    this.defaultCondition={};
    this.columns=[];
    this.dlg;
    this.conditionTable;
    this.initWindow=function() {
        var g = this, p = this.options;
        g.dlg = $("#advancePopup");
        g.conditionTable=g.dlg.append(g.groupTable).find(".qm-conditionTable");
       /* $("#advanceRun").on("click",function(){
            Qm.qmBuilder.run();
        });*/
        $("#advanceRun").unbind("fastClick").bind("fastClick",function(){
            Qm.qmBuilder.run();
        });
        g.addRow(g.conditionTable)
        g.dlg.popup("open");
    };
    this.init = function () {
        var g = this, p = this.options;

        var columns = Qm.config.columnsInfo;
        for (var v in columns) {
            if (columns[v].config == "1") {
                g.columns.push(columns[v]);
            }
        }
        if(g.columns.length>0) {
            g.defaultColumn= g.columns[0];
        }
        var compare=g.operators[Qm.getColumnValueType(g.defaultColumn)][0][1];
        g.defaultCondition={name:g.defaultColumn.columm,compare:compare,value:""};
        this.defaultCon=[{logic:"and",conditions:[g.defaultCondition]}];
        var defaultCon=this.defaultCon;
        var con = Qm.config.advancedSearchPara;
        if (Qm.config.sessionAdvancedCondition)con = Qm.config.sessionAdvancedCondition;//session 优先级高

        if(con&&con.length>0){
            defaultCon=con;
        }
        //for(var i in defaultCon){
        //    g.addGroup(defaultCon[i]);
        //}
        g.initWindow();
        /*$(document).on("click","#advanceDel",function(){
            var table=$(".qm-groupTable .qm-conditionTable").empty();
            //table.remove();
            //$("#"+ p.searchRenderTo).find("table:first").find(".groupLogic").hide();
        });*/
        $("#advanceDel").unbind("fastClick").bind("fastClick",function(){
        	var table=$(".qm-groupTable .qm-conditionTable").empty();
        })
        /*$(document).on("click","#advanceAdd",function(){
            //var table=$(this).parent().parent().parent().find(".qm-conditionTable");
            var table=$(this).parents(".qm-groupTable").find(".qm-conditionTable");
            g.addRow(table);
        });*/
        $(document).on("fastClick","#advanceAdd",function(){
            var table=$(this).parents(".qm-groupTable").find(".qm-conditionTable");
            g.addRow(table);
        })
        $(document).on("change",".__qm__search__name",function(){
            var tr=$(this).parent().parent();
            var td1=tr.find("div:eq(1)");
            var td2=tr.find("div:eq(2)");
            var name=$(this).val();
            var type = Qm.getColumnValueType(Qm.config.columnsInfo[name]);
            td1.empty().append(g.getLogicCombo(type));
            var obj = {name: name};
            g.parseValueField(obj,td2);
        });

        /*$(document).on("click",".__qm__search__row__del",function(){
            //$(this).parent().parent().remove();
            $(this).parent().parent().remove();
        });*/
        $(document).on("fastClick",".__qm__search__row__del",function(){
            //$(this).parent().parent().remove();
            $(this).parent().parent().remove();
        })

    };
    this.getNameCombo=function(name){
        var columnSelectStr="<select name='name' class='__qm__search__name'>";
        var columns=this.columns;
        for (var v in columns) {
            if (columns[v].config == "1") {
                columnSelectStr+="<option value='"+columns[v].columm+"'"+(columns[v].columm==name?"selected":"")+">"+columns[v].columndisplay+"</option>";
            }
        }
        columnSelectStr+="</select>";
        return columnSelectStr;
    };
    this.getLogicCombo=function(type,value){
        var opData = this.operators[type];
        if(!value)value=opData[0][1];
        var logicCombo = "<select name='name' class='__qm__search__compare'>";
        for (var i in opData) {
            logicCombo += "<option value='" + opData[i][1] + "' "+(opData[i][1]===value?"selected":"")+">" + opData[i][0] + "</option>";
        }
        return logicCombo+"</select>";
    };
    this.parseValueField=function parseValueField(obj,td2) {
        var g = this, p = this.options;
        obj.id=('a-p-q-f' + g.builderIndex++);
        Qm.parseSearchItem(obj);
        var valueField = $(Qm.getSearchItemInput(obj))
        td2.empty().append(valueField);
        //Qm.parseSearchItem2Ligerui(obj, td2);
    };
    this.addRow = function (conditionTable, con) {
        var g = this, p = this.options;
        var obj = {};
        if (!con) {
            con = g.defaultCondition;
        }
        $.extend(obj, con);
        var type = Qm.getColumnValueType(Qm.config.columnsInfo[obj.name]);
        var tr = $("<div class='row'>" +
        "<div class='d1 a'>" + this.getNameCombo(obj.name) + "</div>" +
        "<div class='d1 b'>" + this.getLogicCombo(type, obj.compare) + "</div>" +
        "<div class='d1 c'></div>" +
        "<div class='d1 d'><input type='button' value='-' class='__qm__search__row__del qm-m-button' title='删除条件'></div>" +
        "</div>");
        tr.appendTo(conditionTable);
        this.parseValueField(obj,tr.find("div:eq(2)"));
    };

    this.run = function () {
        var g = this, p = this.options;
        var para = [];
        var table = $(".qm-groupTable");
        table.each(function (i) {
            var con={};
            con["logic"]=i==0?"and":$(".groupLogic :checked",this).val();
            var conditions=[];
            var conditionTableTr=$(this).find(".qm-conditionTable div");
            conditionTableTr.each(function(){
                var tr=$(this);
                var p={name:tr.find("div:eq(0) select").val()};
                var col = Qm.config.columnsInfo[p.name];
                if (col) {
                    p.logic ="and";
                    p.label = col.columndisplay;
                    p.compare = tr.find("div:eq(1) select").val();
                    p.dataType = col.datatype;
                    p.value = tr.find("div:eq(2) :input").val();
                    conditions.push(p);
                }
            });
            con["conditions"]=conditions;
            para.push(con);
        });

        Qm.setSessionAdvancedCondition(para);
        Qm.setSearchPara(para);
        //Qm.getQmgrid().set("newPage", 1);
        getData(1);

        this.dlg.popup("close");
    };

    this.show=function(){
        this.dlg.popup("open");
    };

    this.init();
}

function beforeScrollStart(event, data) {
    var title = "";
    if (Qm.config.currentPage == 1 || Qm.config.currentPage == Qm.config.pages) {
        title = Qm.config.note;
    } else {
        title = "释放加载数据 " + Qm.config.note;
    }
    data.iscrollview.options.pullDownPulledText = data.iscrollview.options.pullUpPulledText = title;
}

function onPullDown(event, data) {
    if (Qm.config.currentPage == 1) {
        data.iscrollview.refresh();
        return;
    }
    var page = countPage(-1);
    getData(page, data);
}

function onPullUp(event, data) {
    if (Qm.config.currentPage == Qm.config.pages) {
        data.iscrollview.refresh();
        return;
    }
    var page = countPage(1);
    getData(page, data);
}

function countPage(n) {
    if (!Qm.config.currentPage) {
        Qm.config.currentPage = 1;
    }
    var page = parseInt(Qm.config.currentPage);
    page = isNaN(page) ? 1 : page;
    page += n;
    if (page < 1)page = 1;
    if (page > Qm.config.pages)page = Qm.config.pages;
    Qm.config.currentPage = page;
    return page;
}


function getParams() {
    var params = [];
    if (Qm.userConfig.sp_fields && Qm.userConfig.sp_fields.length > 0) {
        $.each(Qm.userConfig.sp_fields, function (index, items) {
            var item = items;
            if (items.group) {
                for (var i in items.group) {
                    item = items.group[i];
                    if ($("#" + item.id).val())
                        params.push({
                            "logic": item.logic || "and",
                            "label": item.fieldLabel,
                            "name": item.name,
                            "compare": item.compare,
                            "dataType": item.dataType,
                            "value": $("#" + item.id).val()
                        });
                }
            } else {
                params.push({
                    "logic": item.logic || "and",
                    "label": "",
                    "name": item.name,
                    "compare": item.compare,
                    "dataType": item.dataType,
                    "value": $("#" + item.id).val()
                });
            }
        });
    }
    return params;
}
function getData(page, scroller) {
    var rows = parseInt(Qm.config.pagesize);
    var start = (page - 1) * rows;
    var o = [];
    o.push({name: "pageid", value: Qm.config.pageid});
    o.push({name: "start", value: start});
    o.push({name: "pagesize", value: Qm.config.pagesize});
    o.push({name: "searchPara", value: $.toJSON(Qm.getSearchPara())});
    o.push({name: "defaultSearchCondition", value: $.toJSON(Qm.config.defaultSearchCondition)});
    o.push({name: "sessionAdvancedCondition", value:$.toJSON(Qm.getSessionAdvancedCondition())});

    $.getJSON(Qm.pagingUrlPre + "q", o, function (res) {
        var total = res["total"];
        Qm.config.pages = parseInt((total + Qm.config.pageSize - 1) / Qm.config.pageSize);
        Qm.config.currentPage = res.page;
        Qm.config.note = "第 " + Qm.config.currentPage + "/" + Qm.config.pages + " 页，共 " + total + " 条";
        Qm.config.data = res.rows;
        if (Qm.config.listModel == "listview") {
            var list = $("#__qm_list").empty();
            if (res.rows.length > 0) {
                for (var i = 0; i < res.rows.length; i++) {
                    list.append('<li data-index="' + i + '">' + nano(Qm.config.listModelTpl, res.rows[i]) + '</li>');
                }
                $("li", list).on("vclick", Qm.listClick);
               /* $("li", list).fastClick(Qm.listClick);*/
            } else {
                list.append('<li>没有数据！</li>');
            }
            list.listview("refresh");
        } else {
            var list = $("#__qm_list tbody").empty();
            if (res.rows.length > 0) {
                var html = "";
                for (var i = 0; i < res.rows.length; i++) {
                    html += '<tr data-index="' + i + '">';

                    $.each(Qm.config.columnsInfo, function (index, item) {
                        if (item["visible"] == "1") {
                            html += '<td>' + res.rows[i][item["columm"]] + '</td>';
                        }
                    });

                    html += '</tr>';
                }
                list.append(html);
                $("tr", list).on("vclick", Qm.listClick);
                /*$("tr", list).fastClick( Qm.listClick);*/
            } else {
                list.append('<li>没有数据！</li>');
            }
            $("#__qm_list").table("refresh");
        }
        var temp = scroller;
        if (temp) {
            temp.iscrollview.refresh();
        } else {
        	try{
        		$(".iscroll-wrapper").iscroll("refresh");
        	}catch(e){}
        }
    });
}
function nano(template, data) {
    return template.replace(/\{([\w\.]*)\}/g, function (str, key) {
        var keys = key.split("."), v = data[keys.shift()];
        for (var i = 0, l = keys.length; i < l; i++) v = v[keys[i]];
        return (typeof v !== "undefined" && v !== null) ? v : "";
    });
}
function parseNote() {
    if (Qm.config.note) {
        var label = $("#__qm_iscroll_content .iscroll-pull-label");
        label.html("<span style='color: blue;'>" + Qm.config.note + "</span>");
    }
}

/////////////////////////////////////////////////////////