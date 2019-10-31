window.Qm = {};
var __checkedRows = [];
(function ($) {
    $.toJSON = $.ligerui.toJSON;
    window.Qm = {
        version:"1.2.0.1.1.9",
        versionDetail:{major:1, minor:2.0, patch:"1.1.9", plugin:"ligerui"},
        ///////////////////以下函数可以在自己的页面调用//////////////////
        /**
         * public 设置工具条按钮状态
         */
        setTbar:function () {
        },
        /**
         * public 查询数据
         */
        searchData:function () {
        },
        /**
         * public 刷新表格数据
         */
        refreshGrid:function () {
        },
        /**
         * 得到选中行数
         */
        getSelectedCount:function () {
        },
        /**
         * 取值
         * @param name
         */
        getValueByName:function (name) {
        },
        /**
         * public
         */
        getValuesByName:function (name) {
        },
        /**
         * public 刷新页面
         */
        refreshPage:function () {
        },
		getCheckedData:function(){
		},
        ////////////////////////////////////////////////////////////////
        pagingUrlPre:"qm?__method=",
        config:{},
        userConfig:{},
        separator:{line:true },
        getColumnValueType:function (obj) {
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
		parseSearchGroup:function(group){
			for(var i in group){
				Qm.parseSearchItem(group[i]);
			}
		},
        parseSearchItem: function (obj) {
			if (obj.group) {
				Qm.parseSearchGroup(obj.group);
			}
            var col = Qm.config.columnsInfo[obj.name];
            var createXtype = obj.xtype ? false : true;
            var valueType = Qm.getColumnValueType(col);

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

                if (createXtype)obj.xtype = "combo";
                obj.data = Qm.parseComboData(col.binddata);
            }
            else if (valueType == "multipletree") {
                if (createXtype)obj.xtype = "comboxtree";
                obj.tree = $.extend({data: col.binddata, checkbox: false},obj.tree);
            }
            else if (valueType == "date") {
                if (createXtype)obj.xtype = "date";
                obj.format = Qm.getDateFormat(col.formater);
                if (/mm/.test(obj.format)) {
                    obj.showTime = true;
                }
            }
            else if (valueType == "number") {
                if (createXtype)obj.xtype = "number";
                if(!obj.value&&!obj.initValue)
//                obj.value="";
                obj.type = "int";
            } else {
                if (createXtype) obj.xtype = "text";
            }
        },
        getSearchItemInput: function (item) {
            var divStr = '<input ';
            if (item.xtype == "combo" || item.xtype == "comboxtree") {
                divStr += " type='text' name='" + item.id + "-txt'";
            } else if (item.xtype == "radioGroup") {
                divStr += " type='radio' name='" + item.id + "-txt'";
            } else if (item.xtype == "checkboxGroup") {
                divStr += " type='checkbox' name='" + item.id + "-txt'";
            } else {
                divStr += " type='text' id='" + item.id + "'";
            }
            divStr += '>';
            return divStr;
        },
        parseSearchItem2Ligerui: function (item, div) {
            delete item["label"];
            if (item.xtype == "text") {
                item.ele = $('#' + item.id, div).ligerTextBox(item);
            } else if (item.xtype == "combo" || item.xtype == "comboxtree") {
                item.valueFieldID = item.id;
                item.ele = $('input', div).ligerComboBox(item);
            } else if (item.xtype == "date") {
                item.ele = $('#' + item.id, div).ligerDateEditor(item);
            } else if (item.xtype == "radioGroup") {
                item.ele = $('input', div).ligerRadioGroup(item);
            } else if (item.xtype == "checkboxGroup") {
                item.ele = $('input', div).ligerCheckBoxGroup(item);
            } else if (item.xtype == "number") {
                item.ele = $('#' + item.id, div).ligerSpinner(item);
            }
        },
        builderIndex:1,
        parseValueField: function (obj, el) {
            obj.id = ('a-p-q-f' + this.builderIndex++);
            Qm.parseSearchItem(obj);
            var valueField = $(Qm.getSearchItemInput(obj))
            el.empty().append(valueField);
            Qm.parseSearchItem2Ligerui(obj, el);
        },
        getDateFormat:function (formater) {//实现前后台日期格式转换
            return formater == "" ? "yyyy-MM-dd" : formater.replace("HH", "hh");
        },
        parseStyleWidth:function (width) {
            var cWidth = width;
			if (this.config.sp_defaults.layout=="column") {
				if (!cWidth) {//2013-6-3 下午4:41 lybide
					return "";
				}
				if (!isNaN(cWidth)) {
					if (cWidth <= 1) {
						cWidth = cWidth * 100 + "%";
					} else if (cWidth > 1) {
						cWidth = cWidth + "px";
					} else {
						cWidth="";
					}
				}
			} else if (this.config.sp_defaults.layout=="float") {
				if (!isNaN(cWidth)) {
					if (cWidth == 1) {
						cWidth = "auto";
					} else if (cWidth > 1) {
						cWidth = cWidth + "px";
					}  else {
						cWidth="";
					}
				}
			}
            return cWidth;
        },
        parseComboData:function (data) {
            if (data) {
                if (data.length > 0 && !$.isArray(data[0])) {
                    return data;
                }
                var json = [];
                for (var i = 0; i < data.length; i++) {
                    json.push({id:data[i][0], text:data[i][1]});
                }
                return json;
            }
        },
        //构建工具条
        buildToolbar:function () {
            var tools = this.userConfig.tbar || [];
            if (tools.length > 0) {
                for (var i = 0; i < tools.length; i++) {
                    if (typeof tools[i] == "string") {
                        if (tools[i] == "-") {
                            tools[i] = this.separator;
                        }

                    }
                }
				var sl=this.config.searchItems.length;
				//tools.unshift("-");//在最前插入分隔符 13-4-15 下午3:14 lybide
				if (sl>0) { //13-4-17 上午9:09 lybide
					tools.push("->");
					tools.push("-");
					tools.push(this.config.searchButton);
					//2013-5-31 下午10:03 lybide 采用新的方法编写
					tools.push({id:"listSearchBT2",icon:"list-search-ss",click:function(){
						var $this=$(this);
						var qmsp=$("#qm-search-wrap");
						var h1=$(window).height();
						var h2=qmsp.outerHeight();
						var h3=$("#__qmtoolbar").outerHeight(true);//alert(h1+"=="+h2+"===="+h3);
						var ss=$("[toolbarid=listSearchBT2] .l-icon",tbar);

						if (qmsp.attr("display")=="1") {
							qmsp.show();
							$("[toolbarid=listSearchBT1]",tbar).show();
							$(".l-toolbar-list-search-3j",tbar).show();
							//$(".l-bar-separator:last",tbar).show();
							qmsp.attr("display","0");
							ss.attr("title","隐藏搜索条件区域");
							ss.removeClass("l-icon-list-search-ss2");
							//g.setHeight(h1-h3-h2);
							tbar.removeClass("l-toolbar-list-b2");
						} else {
							qmsp.hide();
							$("[toolbarid=listSearchBT1]",tbar).hide();
							$(".l-toolbar-list-search-3j",tbar).hide();
							//$(".l-bar-separator:last",tbar).hide();
							qmsp.attr("display","1");
							ss.attr("title","显示搜索条件区域");
							ss.addClass("l-icon-list-search-ss2");
							//g.setHeight(h1-h3);
							tbar.addClass("l-toolbar-list-b2")
						}
						//$.ligerui.get("#qmgrid").getSelectedRow();
						var g=Qm.getQmgrid();
						g._onResize();
					}});
				}
                var tbar = $('<div id="__qmtoolbar" position="top" onselectstart="return false;" ondragstart="return false;" style="-moz-user-select:none;"></div>');
				tbar.addClass("l-toolbar-list");
                tbar.ligerToolBar({ items:tools });
				if (sl>0) {
					//$("td:last",tbar).width("100");
					$("td:last>div",tbar)
							//.addClass("l-toolbar-search-list-bt1")
							.append('<div class="l-toolbar-list-search-3j"></div>' +
								'<!--<div id="ltlsss1" class="l-toolbar-list-search-ss" title="隐藏搜索条件区域"><a href="javascript:void(0)"></a></div>-->');
						//$(".l-bar-separator:last",tbar).addClass("l-toolbar-search-list-bt2");
				} else {
					tbar.addClass("l-toolbar-list-b2");
				}
                var font=$.cookie.get("mSysFont") || 12;//Cookies.getCookie("mSysFont") || 12;//2017年06月25日 18:08:31 星期日 lybide
				var w1=($.kh.getBytesLength(this.config.searchButton.text)*(parseInt(font/2)))+32+32+20;//2013-8-28 下午3:00 lybide
				$("td:last",tbar).width(w1);//右边查询宽度，来自查询 所在单元格宽度
				return tbar;
            }

        },
		//搜索条件面版配置项：
        //layout:"column;float" column 表示列显示，flaot 表示块显示。
		//columnWidth：每组输入项的总宽度，可以是小于1的百分比，可是大于1的像素值
        //sp_defaults:{columnWidth:0.33, labelAlign:'right', labelSeparator:'', labelWidth:100,layout:"column"},
		//searchButton:{text:"查询",id:"listSearchBT1",icon:"search-list",click:function(){Qm.searchData();}},
        //searchItems:[],
        buildSearchPanel:function () {
        	// var width = window.screen.width ;//根据分辨率 
        	var width = $(window).width(); // 根据当前window尺寸
			for(var i in this.config.media_screen){
				if( width < this.config.media_screen[i].width){
					this.config.sp_defaults = this.config.media_screen[i].sp_defaults;
					break;
				}
			}
            //初始化查询面板
            var cfg = this.config;//document.write(JSON.stringify(cfg.columnsInfo));
            if (cfg.showTitle && this.config.sp_fields && this.config.sp_fields.length > 0) {
				//if (this.config.sp_defaults.layout=="float") {
				//	$.extend(this.sp_defaults, {width:150},this.userConfig.sp_defaults);
				//}
                //var style = $.extend(this.sp_defaults, this.userConfig.sp_defaults);
                var style = this.config.sp_defaults;
                var searchItems = [];
                var canSetValue = (this.config.sp_fields.length == cfg.sessionCondition.length);//todo
				var indexId=0;
				function parseObj(obj){
                    var col = cfg.columnsInfo[obj.name];
                    if(!col){
                        col={datatype:"string",columndisplay:""};
                    }
                    if (col) {
                        if (canSetValue)obj.value = cfg.sessionCondition[indexId].value;
                        obj.id = obj.id ? obj.id : 'p-q-f' + indexId;
                        //todo modifyid by jyl 解决查询条件自定义dataType方式
                        obj.dataType = $.kh.isNull(obj.dataType)?col.datatype:obj.dataType;

                        if (obj.label) {
                            obj.fieldLabel = obj.label;
                            delete obj.label;
                        } else {
                            obj.fieldLabel = col.columndisplay;
                        }
                        Qm.parseSearchItem(obj);

                    }

                    indexId++;
				}
                $.each(this.config.sp_fields, function (index, obj) {
					if(obj.group){
						for(var i in obj.group){
							var ogi=obj.group[i];
                            parseObj(ogi);

                            obj.group[i]=$.extend({}, style, ogi);
                        }
					}else{
                        parseObj(obj);
                    }
                    searchItems.push($.extend({}, style, obj));
                });
                this.config.searchItems = searchItems;
                //搜索
                var searchPanel = $('<div id="qm-search-panel" class="qm-search-panel"><div id="qm-search-wrap" class="qm-search-wrap"><table id="qm-search-p" class="qm-search-table" border="0" cellpadding="0" cellspacing="0" width="100%">' +
					'<tr>' +
					'<td class="qm-search-table-td1"></td>' +
					//'<td class="l-list-search"><button class="l-button-warp l-button" id="listSearch"></button></td>' +
					'</tr>' +
					'</table></div></div>');
				if (!this.config.tbar || this.config.tbar<=0) {//如没有工具条，在查询表格里自动添加按钮 2013-7-8 下午1:29 lybide
					$("#qm-search-p >tbody >tr >td:last",searchPanel).after('<td class="qm-search-table-td2"><a id="listSearch"></a></td>');
					$("#listSearch",searchPanel).ligerButton(this.config.searchButton);
				}
                searchPanel.keypress(function (event) {
                    if (event.keyCode == '13') {
                        Qm.searchData();
                    }
                });

				function parseItem(container,item,group){
					if (!/(left|center|right)/ig.test(item.labelAlign)) {//2013-6-3 下午4:07 lybide
						item.labelAlign="right";
					}
					if (Qm.config.sp_defaults.layout=="column" && group) {
						item.columnWidth=1;//2013-6-3 下午4:55 lybide
						//item.width="100%";
					}
					if (Qm.config.sp_defaults.layout=="float" && item.width>0) {
						item.columnWidth=1;//2013-6-3 下午4:55 lybide
					}
					//2013-6-17 下午2:19 lybide
					if (item.group) {
						function getItemAtt(item){//2013-6-17 下午4:09 lybide
							if (Qm.config.sp_defaults.layout=="column") {
								//item.columnWidth=1;
								item.width=null;
							}
							if (Qm.config.sp_defaults.layout=="float" && item.width>0) {
								//item.columnWidth=1;
							}
							return item;
						}
						//{group:[{label:"带时间测试",name:"date3",compare:">",value:"",width:"100"},{label:"到",name:"date3",compare:"<",value:"",labelAlign:"center",labelWidth:20,width:"100"}]}
						var itemTemp=item;
						var divStr = '<div class="column" style="width:' + Qm.parseStyleWidth(item.columnWidth) + '; ">';
						item=item.group[0];
						divStr+='<div class="label-left" style="width:' + Qm.parseStyleWidth(item.labelWidth) + '; text-align:' + item.labelAlign + '">' + item.fieldLabel + item.labelSeparator + '</div><div class="field-left-'+item.layout+'"'+(" style='width:auto;margin:3px 3px 3px "+(item.labelWidth+3)+"px;'")+'>' ;
						item=itemTemp.group;//alert(JSON.stringify(item[0].xtype));
						divStr+='<table border="0" cellspacing="0" cellpadding="0" width="100%"><tr>';
						divStr+='<td style="padding:0px;">'+Qm.getSearchItemInput(getItemAtt(item[0]))+'</td>';
						divStr+='<td style="padding:0px;" width="'+item[1].labelWidth+'" align="'+item[1].labelAlign+'">'+item[1].fieldLabel+'</td>';
						divStr+='<td style="padding:0px;">'+Qm.getSearchItemInput(getItemAtt(item[1]))+'</td>';
						divStr+='</tr></table>';
						divStr+='</div></div>';
						var div=$(divStr);
						for (k in item) {
							Qm.parseSearchItem2Ligerui(item[k], div);
						};
					} else {
						var divStr = '<div class="column" style="width:' + Qm.parseStyleWidth(item.columnWidth) + '; "><div class="label-left" style="width:' + Qm.parseStyleWidth(item.labelWidth) + '; text-align:' + item.labelAlign + ';">' + item.fieldLabel + item.labelSeparator + '</div><div class="field-left-'+item.layout+'"'+(" style='width:auto;margin:3px 3px 3px "+(item.labelWidth+3)+"px;'")+'>' ;
						divStr+=Qm.getSearchItemInput(item);
						divStr+='</div></div>';
						var div=$(divStr);
						Qm.parseSearchItem2Ligerui(item, div);
					}
                    div.appendTo(container);
					//$(".field-left-column",div).css("border","1px solid");
				}
                var td = $("td:first", searchPanel);

                $.each(searchItems, function (index, item) {
					if (item.newRow && Qm.config.sp_defaults.layout=="float") {//搜索条件换行
						td.append('<div class="newrow" style="clear:both;"></div>');
					}
					//if(item.group){
						//var groupDiv=$('<div class="column"></div>');
						//groupDiv.width(Qm.parseStyleWidth(item.columnWidth));
						//for(var i in item.group){
							//parseItem(td,item.group[0],true);
						//}
                        //groupDiv.appendTo(td);

					//}else{
						parseItem(td,item,false);
					//}
                });
                cfg.searchPara=this.getParams();
                return searchPanel;
            }
        },
        
        getParams:function () {
            var params = [];
            $.each(this.config.searchItems, function (index, items) {
                var item=items;
                if(items.group){
                    for(var i in items.group){
                        item=items.group[i];
                        params.push({"logic":item.logic || "and", "label":item.fieldLabel, "name":item.name, "compare":item.compare, "dataType":item.dataType, "value":item.ele.getValue()});
                    }
                } else{
                    params.push({"logic":item.logic || "and", "label":item.fieldLabel, "name":item.name, "compare":item.compare, "dataType":item.dataType, "value":item.ele.getValue()});
                }
            });
            return params;
        },
        getRow:function (a, b, c, d, e) {
            return a;
        },
        getThisValue:function (a, b, c, d, e) {
            return c;
        },
        exportIframe:false,
        exportForm:false,
        exportToExcel:function (exportAll)  {
			var bc=BROWSER_INFO;
			if (bc.ie) {
				top.$.dialog.loading("正在导出Excel数据，请稍等……");
			}
            var param = this.getQmgrid().get("parms");
            var url = $("base").attr("href")+Qm.pagingUrlPre + "exportToExcel" +(exportAll ? "&exportAll=true" : "");
            if (!this.exportIframe) {
                this.exportIframe = document.createElement("iframe");
                this.exportIframe.width = 0;
                this.exportIframe.height = 0;
                this.exportIframe.style.display = "none";
                $("body").append(this.exportIframe);
                this.exportForm = document.createElement("form");
                $(this.exportIframe).append(this.exportForm);
            }
            //modified by jyl 解决参数过长，不能下载的问题 取消get提交方式改成post提交方式
            var exportForm = $(this.exportForm).attr('action',encodeURI(url)).attr('method','post');
			// 条件附加到Form
            $("input",exportForm).remove();
            exportForm.append($("<input type='hidden' name='pageTitle'/>").val(Qm.config.pageTitle));
            for (var i in param){
                exportForm.append($("<input type='hidden' name='"+param[i].name+"'/>").val(param[i].value));
            }
            if(kui.QM2EXCEL_TYPE=="poi_asyn"){
            	//这里需要修改成ajaxsubmit
                $(this.exportForm).ajaxSubmit({
                	type:'post',
                	beforeSubmit:function(){
                		top.$.notice(Qm.config.pageTitle+"正在导出Excel数据，请稍等……",2);
                	}
                })
            }else{
            	//同步提交数据
            	$(this.exportForm).submit();
            }
            if (bc.ie) {
            	top.$.dialog.loadingClose();
			} 
        },
        exportToPdf:function (exportAll)  {
			var bc=BROWSER_INFO;
			if (bc.ie) {
				top.$.dialog.loading("正在导出pdf数据，请稍等……");
			}
            var param = this.getQmgrid().get("parms");
            var url = $("base").attr("href")+Qm.pagingUrlPre + "exportToPdf" +(exportAll ? "&exportAll=true" : "");
            if (!this.exportIframe) {
                this.exportIframe = document.createElement("iframe");
                this.exportIframe.width = 0;
                this.exportIframe.height = 0;
                this.exportIframe.style.display = "none";
                $("body").append(this.exportIframe);
                this.exportForm = document.createElement("form");
                $(this.exportIframe).append(this.exportForm);
            }
            //modified by jyl 解决参数过长，不能下载的问题 取消get提交方式改成post提交方式
            var exportForm = $(this.exportForm).attr('action',encodeURI(url)).attr('method','post');
			// 条件附加到Form
            $("input",exportForm).remove();
            exportForm.append($("<input type='hidden' name='pageTitle'/>").val(Qm.config.pageTitle));
            for (var i in param){
                exportForm.append($("<input type='hidden' name='"+param[i].name+"'/>").val(param[i].value));
            }
            $(this.exportForm).submit();
            if (bc.ie) {
            	top.$.dialog.loadingClose();
			} 
        }
    };
    /////////////////////////////////////////////////方法实现/////////////////////////////////////////////////////////////////////////////


    /**
     * public 设置工具条状态
     */
    Qm.setTbar = function (status) {
		if (!this.userConfig.tbar || this.userConfig.tbar.length<=0) {return;}//2013-7-8 下午1:03 lybide
        $("#__qmtoolbar").ligerGetToolBarManager().setEnabled(status);
        //alert($.ligerui.find("MenuBar").length)
        var menbBar=$.ligerui.find("MenuBar");
        for (var bar in menbBar) {
            menbBar[bar].setEnabled(status);
        };
        //$("#mMenu1").ligerGetMenuBarManager().setEnabled(status);
    }
    /**
     * public 得到ligerui原生Grid
     */
    Qm.getQmgrid = function () {
        return $("#qmgrid").ligerGetGridManager();
    }
    /**
     * public 设置查询条件 可以是数组或单个json对象
     *  {label:"",logic:"and",name:"",compare:"=",dataType:"string",value:""}
     */
    Qm.setCondition = function (con) { //todo
        var defaultCon = {label:"", logic:"and", name:"", compare:"=", dataType:"string", value:""}
        var manmadeSearchCondition = [];
        if ($.isArray(con)) {
            for (var i = 0; i < con.length; i++) {
                manmadeSearchCondition.push($.extend({}, defaultCon, con[i]));
            }
        } else {
            manmadeSearchCondition.push($.extend(defaultCon, con));
        }
        this.getQmgrid().set("manmadeSearchCondition", manmadeSearchCondition);
    }
    /**
     * public 查询数据
     */
    Qm.searchData = function () {
        this.getQmgrid().set("newPage", 1);
        this.getQmgrid().setSearchPara(this.getParams());
        this.refreshGrid();
        this.checkHeaderSearchChangeed();
        Qm.advancedSearchTip.hide();
    }
    /**
     * public 刷新表格数据
     */
    Qm.refreshGrid = function () {
        this.getQmgrid().loadData(true);
        __checkedRows= [];
    }

    Qm.getSelectedCount = function () {
		if(!Qm.config.singleSelect){
			return this.getCheckedData().length;
		}else{
			return this.getQmgrid().getSelecteds().length;
		}
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
    Qm.getValuesByName = function (name) {
        var arr = [];
        var records = this.getQmgrid().getSelecteds();
        if(!Qm.config.singleSelect){
        	records = this.getCheckedData();
		}
        var l = records.length;
        for (var i = 0; i < l; i++) {
            arr[i] = records[i][name];
        }
        return arr;
    }

    /**
     * public 刷新页面
     */
    Qm.refreshPage = function () {
        window.location.reload();
    }

	Qm.getCheckedData = function(){
		return __checkedRows;
	}
})(jQuery);

var Ext = {};
Ext.onReady = $;
$(function () {
    $.extend($.ligerMethos.Grid, {
        _parseParam:function () {
            var o = [];
            var start = 0;
            var page = parseInt(this.get("newPage"));
            page = isNaN(page) ? 1 : page;
            var rows = parseInt(this.get("pageSize"));
            start = (page - 1) * rows;
            o.push({name:"pageid", value:this.get("pageid")});
            o.push({name:"start", value:start});
            o.push({name:"pagesize",value: rows});
            o.push({name:"conChange", value:this.get("conChange")});
            o.push({name:this.get("searchParaName"), value:$.toJSON(this.get("searchPara"))});
            o.push({name:this.get("defaultSearchConditionName"), value:$.toJSON(this.get("defaultSearchCondition"))});
            o.push({name:this.get("manmadeSearchConditionName"), value:$.toJSON(this.get("manmadeSearchCondition"))});
            o.push({name:this.get("sessionConditionName"), value:$.toJSON(this.get("sessionCondition"))});
            o.push({name:this.get("sessionAdvancedConditionName"), value:$.toJSON(this.get("sessionAdvancedCondition"))});
            o.push({name:this.get("sortInfoName"), value:$.toJSON(this.get("sortInfo"))});
            this.set("parms", o);
        },
        //public 设置查询参数
        setSearchPara:function(para) {
            var g = this, p = this.options;
            p.searchPara = para;
        },
        setSessionCondition:function(con){
            var g = this, p = this.options;
            p.sessionCondition=con;
        },
        setSessionAdvancedCondition:function(con){
            var g = this, p = this.options;
            p.sessionAdvancedCondition=con;
        },
        getSessionCondition:function(){
            var g = this, p = this.options;
            return p.sessionCondition;
        },
        getSessionAdvancedCondition:function(){
            var g = this, p = this.options;
            return p.sessionAdvancedCondition;
        },
        //public 设置排序参数
        setSortInfo:function(sortInfo) {
            var g = this, p = this.options;
            p.sortInfo = sortInfo;
        },
        getSortInfo:function() {
            var g = this, p = this.options;
            return p.sortInfo;
        },
        selectRange:function (key, values) {
            var g = this, p = this.options;
            var datas = g.data.rows;
            var i = 0;
            for (var d in datas) {
                if (g.isSelected(i))
                    g.unselect(i);
                for (var j = 0; j < values.length; j++) {
                    if (datas[d][key] == values[j]) {
                        values.splice(j, 1);
                        g.select(i);
                        break;
                    }
                }
                i++;
            }
        }
    });
});
Qm.init = function () {
    try {
        Qm.userConfig = qmUserConfig || {};
        if (Qm.userConfig.listeners) {
            if ((typeof Qm.userConfig.listeners.beforeQmReady) === "function") {
                Qm.userConfig.listeners.beforeQmReady();
            }
        }
    } catch (e) {
    }
    //Qm.config.qmUserConfig = Qm.userConfig;
    if (Qm.userConfig.title) {
        Qm.config.pageTitle = Qm.userConfig.title;
    }
    var cfg = Qm.config;
    if (!Qm.userConfig.sp_fields && cfg &&cfg.advancedPanel === true) {
        Qm.userConfig.sp_fields = cfg.advancedSearchPara;
        cfg.sessionCondition = cfg.sessionAdvancedCondition;
    }

    var columns = Qm.config.columnsInfo;
    var cmArr = [];
    var j = 0;
    var width = 0;
    var width1 = 0;
    var frozen=false;
    for (var v in columns) {
        width += columns[v].width;
        if (columns[v].visible != "0")
            width1 += columns[v].width;
        cmArr[j ] = {display:columns[v].columndisplay,id:columns[v].columm, name:columns[v].columm, align:columns[v].align, width:columns[v].width, hide:(columns[v].visible == "0"), sortable:true, resizable:Qm.config.selfhood||false, isAllowHide:(columns[v].config == "1"),frozen:(columns[v].frozen=="1")};
        if(columns[v].frozen=="1"){
            frozen=true;
        }
        if (columns[v].formater != "" && columns[v].datatype != "date") {
            cmArr[j].render = columns[v].formater;
        }
        if(columns[v].totalSummary){
        	var temp = eval("(" + columns[v].totalSummary + ")");  // 转换为json对象 
        	cmArr[j].totalSummary = temp;
        }
        j++;
    }
    //{align:'center',render:function(a,b,c){return '<div style=\"color:red\">最大数:'+a.max+'</div><div>平均数：'+a.avg+'</div>'}}
    //$.ligerDefaults.GridString.pageStatMessage = '显示从 {from} 到 {to}，共 {total} 条';
	//列表配置项。
    Qm.config = $.extend(true,{
        pageid:"",
        pageSize:Qm.config.pagesize,
        defaultSearchConditionName:"defaultSearchCondition",
        manmadeSearchConditionName:"manmadeSearchCondition",
        sessionConditionName:"sessionCondition",
        sessionAdvancedConditionName:"sessionAdvancedCondition",
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
        advanced:false,
        expexcel:false,
        sessionCon:false,
        selfhood:false,
        columNum:false,
        //fixedCellHeight:false,//todo lybide 是否固定单元格的高度
        //singleSelect: false,
        //showTitle: true,
        //seachOnload:true,

        //width:"100%",
        height:"100%",
        minColumnWidth:30,
        root:'rows',
        record:'total',
        sortnameParmName:'sort',
        sortorderParmName:'dir',
        parms:[],
        url:Qm.pagingUrlPre + "q",
        columns:cmArr,
        checkbox:!Qm.config.singleSelect,
        delayLoad:!Qm.config.seachOnload,
        allowUnSelectRow:true,
        showTableToggleBtn:true,
        showTitle:true,
        alternatingRow:false,
        frozen:frozen,
        pageSizeOptions: [10, 20, 30, 40, 50 , 100],
        switchPageSizeApplyComboBox:false,
        rownumbers:Qm.config.columNum,
        usePager:Qm.config.usePager,
        colDraggable :Qm.config.selfhood||false,
        allowHideColumn:Qm.config.selfhood||false,
        colSearch:false,
        colSearchOnChange:false,

		titleElement:{text:"",icon:"",note:""},
		sp_defaults:{columnWidth:0.33, labelAlign:'right', labelSeparator:'', labelWidth:100,layout:"column"},
		media_screen:[
			{width:600,sp_defaults: {columnWidth: 1, labelAlign: 'right', labelSeparator: '', labelWidth: 80, layout: "column"}},
			{width:750,sp_defaults: {columnWidth: 0.5, labelAlign: 'right', labelSeparator: '', labelWidth: 80, layout: "column"}},
			{width:1600,sp_defaults: {columnWidth: 0.33, labelAlign: 'right', labelSeparator: '', labelWidth: 80, layout: "column"}},
			{width:1920,sp_defaults: {columnWidth: 0.25, labelAlign: 'right', labelSeparator: '', labelWidth: 80, layout: "column"}},
			{width:3000,sp_defaults: {columnWidth: 0.2, labelAlign: 'right', labelSeparator: '', labelWidth: 80, layout: "column"}}
		],
		searchButton:{text:"查询",id:"listSearchBT1",icon:"search",click:function(){Qm.searchData();}},
		searchItems:[],
        onSuccess:function(data){
            if(data.success===false){
                this.serverFail=true;
            }
        },
        onComplete:function(){
            if(this.serverFail){
                var html="<div class='l-grid-data-search-error'><div>查询失败，请重试！</div></div>";//todo lybide
                if($(".l-grid-empty", this.body).length==2){
                    $(".l-grid-empty:eq(1)", this.body).html(html);
                }else{
                    $(".l-grid-empty", this.body).html(html);
                }
            }
        }
    },Qm.config, Qm.userConfig/*,{colSearch:false,colSearchOnChange:false}*/);
    if (Qm.config.sortInfo.length > 0) {
        var sortInfo = Qm.config.sortInfo;
        $.extend(Qm.config, {sortName:sortInfo[0].field, sortOrder:sortInfo[0].direction.toLowerCase()});
    }


    $(function () {
		$("body").append('<style>.l-panel {border:0px;} .l-layout-left, .l-layout-right, .l-layout-center, .l-layout-top, .l-layout-bottom {border:0px;}</style>');
		//$("body").append('<div id="layout1"></div>');
		var qmSearchPanel = Qm.buildSearchPanel();
        var tbar = Qm.buildToolbar();
        if (tbar) {
            tbar.appendTo('body');
			//tbar.appendTo("#layout1");
        }
		//var sp = Qm.buildSearchPanel();
		//$("#layout1").append('<div id="listCenter" position="center"></div>');
		$("body").append('<div id="l-grid-qm-main" class="l-grid-qm-main" position="center"><div id="listCenter" class="l-grid-qm-wrap"></div></div>');
        if (qmSearchPanel) {
            //qmSearchPanel.appendTo('body');
            //qmSearchPanel.appendTo("#listCenter");
            $("#l-grid-qm-main").before(qmSearchPanel);//todo lybide
        }
		$("#listCenter").append('<div id="qmgrid" ></div>');
        //var grid = $('<div id="qmgrid" ></div>');
        var ln = Qm.userConfig.listeners || {};
        var eventCfg = {
            onBeforeLoading:function (loadParams) {
                this.selected = [];
                this._parseParam();
            },
            onDblClickRow:function (rowData) {
                if (ln.rowDblClick) {
					//Qm.getQmgrid().(false);
					var g=Qm.getQmgrid();
					for (var rowid in g.records) {//清除已选择的行。双击只选择一行。lybide 13-5-3 下午3:13 lybide
						//if (uncheck)
							g.unselect(g.records[rowid]);
						//else
						//	g.select(g.records[rowid]);
					}
                    Qm.getQmgrid().select(rowData["__index"]);
                    ln.rowDblClick.call(this, rowData, rowData["__index"]);
                }
            },
            onLoaded:function () {
                if (ln.selectionChange) {
                    ln.selectionChange.call(this);
                }
                this.toggleLoading.ligerDefer(this, 10, [false]);
                $("#selectCount").text(Qm.getSelectedCount());
            },
            
            onSelectRow:function (rowData) {
            	Qm.addCheckedRows(rowData);
            	$("#selectCount").text(Qm.getSelectedCount())
				if (ln.selectionChange) {
					ln.selectionChange.call(this, rowData, rowData["__index"]);
				}
				if (ln.rowClick) {
					ln.rowClick.call(this, rowData, rowData["__index"]);
				}
            },
            onUnSelectRow:function (rowData) {
            	Qm.removeCheckedRows(rowData);
            	$("#selectCount").text(Qm.getSelectedCount())
				if (ln.selectionChange) {
					ln.selectionChange.call(this, rowData, rowData["__index"]);
				}
				if (ln.rowClick) {
					ln.rowClick.call(this, rowData, rowData["__index"]);
				}
            },
            onError:function(){
                //todo
            }
        };
		if(!Qm.config.singleSelect){
        	$.extend(eventCfg,{
        		isChecked:function (rowdata) { //todo
        			if (Qm.findCheckedRows(rowdata) == -1)
						return false;
					return true;
                }
            });
        }
		if (ln.rowDblClick) {//todo 13-5-2 下午10:37 lybide
			//eventCfg.onDblClickRow=ln.rowDblClick;
		}
        if(Qm.config.colSearch&&!Qm.config.selfhood){
            $.extend(eventCfg,{
                //移动列
                onAfterChangeCol:function (columns) { //todo
                    Qm.initColumnCondition();
                }});
        }
        if(Qm.config.selfhood){
            $.extend(eventCfg,{
                //移动列
                onAfterChangeCol:function (columns) { //todo
                    var g = this, p = this.options;
                    var cols = [];
                    $(p.columns).each(function (i, c) {
                        if (c["hide"] === false) {
                            cols.push(c["name"]);
                        }
                    });
                    $.ajax({
                        url:Qm.pagingUrlPre + 'changeOrder',
                        type:'post',
                        data:{pageid:Qm.config.pageid, columns:cols.toString() }
                    });
                    if(Qm.config.colSearch){
                        Qm.initColumnCondition();
                    }
                },
                onToggleCol:function (column, visible) {
                    column.hide = !visible;
                    column._hide = !visible;
                    $.ajax({
                        url:Qm.pagingUrlPre + 'changeVisable',
                        type:'post',
                        data:{pageid:Qm.config.pageid, column:column.name, visable:visible ? "1" : "0" },
                        success:function () {
                            if (visible) {
                                Qm.refreshGrid();
                            }
                        }
                    });
                },
                onAfterChangeColumnWidth:function (field, width) {
                    $.ajax({
                        url:Qm.pagingUrlPre + 'changeWidth',
                        type:'post',
                        data:{pageid:Qm.config.pageid, column:field.name, width:width }
                    });
                },onResetConfig:function(){
					$.ligerDialog.confirm('您确定要恢复到系统的默认配置吗？', '确认', function (yes) {
						if (yes) {
							$.ajax({
								url:Qm.pagingUrlPre + 'resetConfig',
								type:'post',
								data:{pageid:Qm.config.pageid},
								success:function (res) {
									var obj = $.parseJSON(res);
									if(obj.success){
										Qm.refreshPage();
									}
								}
							});
						}
					})
					
				}
                
            });
        }
       
        
        //grid.ligerGrid($.extend(eventCfg, Qm.config, Qm.userConfig.listeners));
		var grid=$("#qmgrid").ligerGrid($.extend(eventCfg, Qm.config, Qm.userConfig.listeners));//todo 采用原生方法 2017年06月05日 18:05:11 星期一 lybide

        //grid.appendTo('body');
        //grid.appendTo("#listCenter");
        
        //添加选中数量
        var selectCount = '<div class="l-bar-separator"></div><div class="l-bar-group select-count">共选中 <span id="selectCount">0</span> 条</div><div class="l-bar-separator"></div>';
        $(selectCount).insertBefore("#qmgrid .l-panel-bbar-inner .l-bar-group:last")

        Qm.advancedSearchTip=$("<div style='display: none' class='qm-advanced-search-tip'><div class='text'>提示：高级查询条件应用中！</div></div>").appendTo(".l-panel-bbar-inner");

        var tools=[];
//        Qm.config.advanced=true;
        var dlg;
        var qmBuilder;
        if (Qm.config.advanced) {

            tools.push({ text:'高级',icon:'advanced',title:'提供多重组合查询和多级排序功能',click:function(){
                if(!qmBuilder){
                    qmBuilder=new Qm.QmBuilder();
                }
                qmBuilder.show();
            }});
        }
//        Qm.config.expexcel=true;
        if (Qm.config.expexcel) {
            if(tools.length>0)tools.push("-");
            function showMenu() {
                var m =  $("[toolbarid=_qm_exp_id]");
                var left = m.offset().left-72;
                var top = m.offset().top - 60;
                if(Qm.userConfig.exppdf){
            		top = m.offset().top - 114;
                }
                menu.show({ top:top, left:left });
            }
            tools.push({ text:'导出', id:'_qm_exp_id',icon:'excel-import',click:function(){
                setTimeout(function(){showMenu();},100);
            }});
            var items = new Array();
            items.push({ id:'exp', icon:'table-excel', text:'导出全部数据', click:function () {
						Qm.exportToExcel(true)
					} });
            items.push({ line:true });
            items.push({ id:'exp1', icon:'table-export', text:'导出本页数据', click:function () {
				Qm.exportToExcel(false)
			} });
            
            if(Qm.userConfig.exppdf){
            	items.push({ line:true });
            	items.push({ id:'exp2', icon:'pdf', text:'导出本页数据', click:function () {
					Qm.exportToPdf(false)
				} });
            	items.push({ line:true });
            	items.push({ id:'exp3', icon:'pdf', text:'导出全部数据', click:function () {
					Qm.exportToPdf(true)
				} })
            }
            
            var menu = $.ligerMenu({ top:100, left:100, width:120, items:items
//				onshowEnd:function(){ditem.addClass("l-panel-btn-over l-panel-btn-selected").siblings(".l-menubar-item").removeClass("l-panel-btn-selected");},
//				onhideEnd:function(){ditem.removeClass("l-panel-btn-over l-panel-btn-selected");}
            });
//            var ditem = $('<div id="_qm_exp_id" class="l-menubar-item l-panel-btn l-toolbar-item-hasicon"><span>导出</span><div class="l-panel-btn-l"></div><div class="l-panel-btn-r"></div><div class="l-menubar-item-down"></div><div class="l-icon l-icon-exp l-icon-left"></div></div>');
            //ditem.toggle(showMenu, function () {menu.hide();});//todo 点击导出事件绑定 13-1-25 下午4:01 lybide
//            ditem.click(function () {
//				var b=$(this);
//                setTimeout(function(){showMenu(b);},100);//todo 需要延时一下，要不然菜单出来不到。13-1-25 下午4:37 lybide
//            });
            //ditem.insertBefore(".l-clear");
//			$(".l-panel-bbar-inner>.l-bar-right").append(ditem);//todo 修改列表导出按钮位置
        }
        if (tools.length>0) {
            $(".l-panel-bbar-inner>.l-bar-right",grid.grid).css({padding:"0","border-bottom":"0","background":"url('')"}).ligerToolBar({ items:tools});
            $(">.l-panel-bar>:first",grid.grid).addClass("has-right-botton");//lybide 给有左边按钮添加样式
        }
        if (Qm.userConfig.listeners) {
            if ((typeof Qm.userConfig.listeners.afterQmReady) === "function") {
                Qm.userConfig.listeners.afterQmReady();
            }
        }

		var g=Qm.getQmgrid();
		g._onResize();
        if(Qm.config.colSearch){
            Qm.initColumnCondition();
        }
		//try{jsEndTime("列表渲染完成");}catch (e){};
        pagesEnd("list");
    });
    Qm.colSearchData=function(){
        this.searchData();
        this.colSearchClose();
    }
    Qm.checkHeaderSearchChangeed=function(){
        var grid=Qm.getQmgrid().grid;
        var gridHead=$(".l-grid-header",grid);
        //对已有值的条件所在头部单元格，进行标记操作 2015年9月14日 15:22:31 lybide
        var ltd=gridHead.find(".l-grid-header-inner td");
        ltd.find(".l-grid-hd-cell-search-changeed").addClass("hide");
        var b=Qm.config.searchItems;//改进对象取值 2015年9月15日 14:15:23 lybide
        //var b=this.getParams();
        for (var s in b) {
            var obj=b[s];
            var name=obj["name"];
            var id=obj["id"];
            if (!name && !$.ligerui.get(id)) {
                continue;
            }
            var value=$.ligerui.get(id).getValue();
            //var gsw=$("#l-grid-col-panel > div> [name="+name+"] input");
            if (value!="") {
                ltd.filter("[columnname="+name+"]").find(".l-grid-hd-cell-search-changeed").removeClass("hide");
            }
        };
    }
    Qm.colSearchClose=function(){
        $("#l-grid-col-panel").attr("__columnname",null).hide();
        var grid=Qm.getQmgrid().grid;
        var gridHead=$(".l-grid-header",grid);
        gridHead.find(".l-grid-hd-cell-sprite").hide();
    }
    Qm.changeSort=function(sort){
        var name=$(".sprite").attr("__columnname");
        this.getQmgrid().changeSort(name,sort);
    }
    Qm.initColumnCondition = function () {
        //var sprite=$('<div class="sprite"></div>').appendTo("body");
        //2015年9月14日 17:32:17 lybide 存在元素不执行
        var grid=Qm.getQmgrid().grid;//取得原生表格对象。2015年9月15日 11:56:13 lybide
        if ($("#l-grid-col-panel").size()>0) {
        	
        } else {
            var lgcp=$('<div id="l-grid-col-panel" class="l-grid-col-panel">' +
            '<div class="p-body"><div class="p-body-wrap"></div></div>' +
            '<div class="p-button"><div class="p-button-wrap">' +
            '<a class="a1" onclick="Qm.colSearchData();">确定</a>'+
            '<a class="a2" onclick="Qm.colSearchClose();">关闭</a>'+
                //'<input type="button" value="确定" onclick="Qm.colSearchData()">' +
                //'<input type="button" value="关闭" onclick="Qm.colSearchClose();">' +
            '</div></div>' +
            '</div>');
            lgcp.appendTo("body");
            /*$(".l-grid-header-inner td").append('<div class="sprite"></div>');
             var sprite=$(".sprite");
             $(".l-grid-header-inner td").hover(function(){
             var _this=$(this);
             _this.children(".sprite").show();
             },function(){
             *//*var _this=$("this");
             if (_this.children(".l-grid-col-panel").is(":visible")) {

             }
             $(".sprite").hide();*//*
             });*/
            $(document).bind("click.qmsprite", function (e) {
                //g._onClick.call(g, e);
                /*if (e.which != 1) {
                 return;
                 }*/
                //2015年8月13日 13:55:31 lybide
                if ($("#l-grid-col-panel").is(":hidden")) {
                    return;
                }
                var src = _getSrcElementByEvent(e);
                if (src.out) {
                    //if (g.editor.editing && !$.ligerui.win.masking) g.endEdit();
                    //if (p.allowHideColumn) g.popup.hide();
                    return;
                }
                if (src.cellsprite || src.editing) {
                    return;
                }
                if (src.colpanel) {
                    return;
                }
                Qm.colSearchClose();
            });
        }
        //2015年8月12日 15:11:15 lybide
        function _getSrcElementByEvent(e) {
            var g = this;
            var obj = (e.target || e.srcElement);
            var jobjs = $(obj).parents().add(obj);
            var fn = function (parm) {
                for (var i = 0, l = jobjs.length; i < l; i++) {
                    if (typeof parm == "string") {
                        if ($(jobjs[i]).hasClass(parm)) return jobjs[i];
                    } else if (typeof parm == "object") {
                        if (jobjs[i] == parm) return jobjs[i];
                    }
                }
                return null;
            };
            if (fn("l-grid-editor") || fn("l-box-dateeditor") || fn("l-box-select") || fn("l-table-checkbox")) return {
                editing: true,
                editor: fn("l-grid-editor")
            };
            if (jobjs.index(this.element) == -1) return {
                out: true
            };
            var r = {
                colpanel: fn("l-grid-col-panel"),
                cellsprite: fn("l-grid-hd-cell-sprite"),
                indetail: false
            };
            return r;
        };
        var gridHead=$(".l-grid-header",grid);//优化head查找方式 2015年9月15日 10:53:22 lybide
        var sprite=$('<div class="l-grid-hd-cell-sprite"></div><div class="l-grid-hd-cell-search-changeed hide" title="有自定义查询条件"></div>');
        sprite.click(function(){
            var sprite=$(this);
            var name=sprite.attr("__columnname");
            var panel=$("#l-grid-col-panel").show();
            panel.attr("__columnname",name);
            gridHead.find(".l-grid-hd-cell-sprite").hide();
            sprite.show();
            var p=sprite.offset();
            //panel.css({left:p.left,top:p.top+sprite.height()}).show();
            var body=$(">.p-body",panel);
            var button=$(">.p-button",panel);
            var div=body.find("div[name='"+name+"']");
            var divLength=div.length;
            if(divLength==0){
                var iSearchDataKey=0;
                //Qm.config.colSearchOnChange=true;
                var div=$("<div class='c-item' name='"+name+"'></div>").appendTo(body.find(">.p-body-wrap"));
                var type = Qm.getColumnValueType(Qm.config.columnsInfo[name]);
                if(type=="date"||type=="number"){
                    var obj={name:name,compare:">=",value:"",dataType:Qm.config.columnsInfo[name].datatype};//2016年08月3日 17:35:18 星期三 lybide 把原 obj1 改为 obj
                    var obj2={name:name,compare:"<=",value:"",dataType:Qm.config.columnsInfo[name].datatype};
                    if(Qm.config.colSearchOnChange) {
                        if(type=="date") {
                            obj.onChangeDate =obj2.onChangeDate = function () {
                                if (iSearchDataKey==1) {
                                    return;
                                }
                                Qm.searchData();
                            };
                        }else{
                            obj.onChangeValue =obj2.onChangeValue = function () {
                                if (iSearchDataKey==1) {
                                    return;
                                }
                                Qm.searchData();
                            };
                        }
                    }
                    var div1=$("<div class='n-item n1'><div style='float:left;width:20px'>&gt;=</div><div style='float:left;width:170px'></div></div>").appendTo(div).find("div:eq(1)");
                    var div2=$("<div class='n-item'><div style='float:left;width:20px'>&lt;=</div><div style='float:left;width:170px'></div></div>").appendTo(div).find("div:eq(1)");
                    Qm.parseValueField(obj,div1);
                    Qm.parseValueField(obj2,div2);
                    Qm.config.searchItems.push(obj);
                    Qm.config.searchItems.push(obj2);
                } else {
                    var obj={name:name,compare:"like",value:"",dataType:Qm.config.columnsInfo[name].datatype};
                    if(Qm.config.colSearchOnChange) {
                        obj.onChange= function () {
                            if (iSearchDataKey==1) {
                            	return;
                            }
                            Qm.searchData();
                        };
                    }
                    if (type == "multiple") {
                        obj.xtype = "checkboxGroup";
                        obj.compare = "=";
                    } else if (type == "multipletree") {
                        obj.tree={checkbox:true};
                        if(Qm.config.colSearchOnChange) {
                            var treeS1 = null;
                            obj.onSelected = function (value, text) {
                                if (!treeS1) {
                                    treeS1++;
                                    return;
                                }
                                Qm.searchData();
                            };
                        }
                        obj.compare = "=";
                    }
                    Qm.parseValueField(obj, div);
                    Qm.config.searchItems.push(obj);
                }
                //绑定焦点事件 2016年08月04日 13:58:42 星期四 lybide
                if (obj.xtype=="text") {
                    //var inputObj=div.find("input:first");
                    //console.log(obj);
                    var inputObj=obj.ele.inputText;
                    inputObj.keydown(function(e){
                        var ek=e.keyCode;//alert(ek);
                        if (ek==13) {
                            //2016年09月09日 13:35:32 星期五 lybide 添加判断解决keydown与change冲突
                            iSearchDataKey=1;
                            Qm.searchData();
                            //alert($(this).val())
                            //return true;
                            setTimeout(function(){
                                iSearchDataKey=0;
                            },11);
                        } else {
                            //return false;
                        }
                    })//.unbind("change");
                    var oneExe1=setTimeout(function(){
                        inputObj.focus();
                    },1);
                }
            } else {
                //绑定焦点事件
                var inputObj=div.find("input:first");
                var oneExe1=setTimeout(function(){
                    inputObj.focus();
                },1);
            }
            div.siblings().hide();
            div.show();
            //计算位置
            panel.height("");
            body.height("");
            var rMenu=panel;
            var opt={offsetX:0,offsetY:sprite.height()};
            var l = p.left + opt.offsetX,
                t = p.top+opt.offsetY,
                p={
                    wh:$(window).height(),
                    ww:$(window).width(),
                    mh:rMenu.height(),
                    mw:rMenu.width()
                };
            t=(t+p.mh)>=p.wh?(t-=p.mh+sprite.height()+2):t;//当菜单超出窗口边界时处理
            l=(l+p.mw)>=p.ww?(l-=p.mw-sprite.width()):l;
            //console.log(e.pageX,e.pageY,t,l);
            //2015年8月11日 16:04:26 lybide
            t=t<0?10:t;
            if (p.mh> p.wh) {
                panel.height(p.wh-20);
                //console.log(p.wh,button.outerHeight());
                body.height(p.wh-20-button.outerHeight());//显示按钮区，滚动数据区 2015年9月15日 15:16:45 lybide
            }
            panel.css({left:l, top:t}).show();
        });
        gridHead.find(".l-grid-hd-cell-inner").append(sprite);
        gridHead.find(".l-grid-hd-cell-rownumbers,.l-grid-hd-cell-checkbox").find(" .l-grid-hd-cell-sprite,.l-grid-hd-cell-search-changeed").hide();//2017年06月07日 18:20:33 星期三 lybide
        gridHead.find(".l-grid-header-inner td").live({
                mouseover: function () {
                    //$(".l-grid-col-panel").hide();
                    var td = $(this);
                    var name = td.attr("columnname");
                    if (name) {
                        var p=td.position();
                        //var sprite = $(".sprite").attr("__columnname",name).css({left: p.left+td.width()-14,top: p.top,height:td.height()});
                        var sprite=td.find(".l-grid-hd-cell-sprite");
                        sprite.attr("__columnname",name);//console.log(name);
                        sprite.show();
                    }
                },
                mouseout:function(){
                    var td = $(this);
                    var sprite=td.find(".l-grid-hd-cell-sprite");
                    var name= sprite.attr("__columnname");
                    if($("#l-grid-col-panel").attr("__columnname")==name){
                        return;
                    }
                    sprite.hide();
                }
            }
        );
        this.checkHeaderSearchChangeed();
        //$(".l-grid-header-table").mouseout(function(){$(".sprite").hide();});
    }
	
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
        this.groupTable="<div class='p5'><table class='qm-groupTable' border='1' cellpadding='5' cellspacing='0'>" +
            "<tr class='qm-groupTable-tr1'><td class='qm-groupTable-td1'><div class='groupLogic'></div></td>" +
            "<td class='qm-groupTable-td2' align='right'><div class='__qm__search__row_bar1'><input type='button' value='' class='l-icon-groupAdd __qm__search__row__add' title='新增子条件'></div><div class='__qm__search__row_bar1'><input type='button' value='' class='l-icon-clear __qm__group__del' title='删除分组'></div></td>" +
            "</tr>" +
            "<tr>" +
            "<td colspan='2' class='groupPanel'>" +
            "<table class='qm-conditionTable' border='0' cellpadding='0' cellspacing='0'></table>" +
            "</td>" +
            "</tr>" +
            "</table></div>";
        this.defaultColumn={};
        this.defaultCon=[];
        this.defaultCondition={};
        this.columns=[];
        this.dlg;
        this.initWindow=function() {
            var g = this, p = this.options;
            var tab1 = '<div id="__qm_advanced__panel">' +
                '<div title="组合查询">' +
                '<div id="__qm_advanced__search__body" class="overflow-auto"></div>' +
                '<div id="__qm_advanced__search__bar" class="l-toolbar-top1px"></div>' +
                '</div>' +
                '<div title="多级排序">' +
                '<div id="__qm_advanced__sort__body" class="overflow-auto"></div>' +
                '<div id="__qm_advanced__sort__bar" class="l-toolbar-top1px"></div>' +
                '</div>' +
                '</div>';
            var searchToolBar = {
                items: [
                    "-",{text: "增加分组", icon: "groupAdd", click: function () {
                        g.addGroup();
                    }},
                    "-",
                    {text: "清除所有", icon: "clear", click: function () {
                        $.ligerDialog.confirm('您确定要清除所有查询条件吗？', '确认', function (yes) {
                            if (yes) {
                                g.clearGroup();
                                g.run();
                            }
                        })
                    }},
//                    "-",
//                    {text: "恢复默认", icon: "reset", click: function () {
//                        $.ligerDialog.confirm('您确定要恢复到系统设置的默认查询条件吗？', '确认', function (yes) {
//                            if (yes) {
//                                Ext.Ajax.request({
//                                    url: Qm.pagingUrlPre + "filterData&pageid=" + Qm.config.pageid,
//                                    success: function (response) {
//                                        var obj = Ext.util.JSON.decode(response.responseText);
////                                                bar.setSearchPara(obj.conditions);
////                                                delFilterRow();
////                                                reAddFilterRow();
//                                    }
//                                });
//                            }
//                        });
//                    }},
                    "->","-",
                    {text: "执行", icon: "run", click: function () {
                        g.run();
                    }}
                ]
            }
            var sortToolBar = {
                items: [
                    "-",{text: "增加", icon: "groupAdd", click: function () {
                        g.addSort();
                    }},
                    "-",
                    {text: "清除所有", icon: "clear", click: function () {
                        $.ligerDialog.confirm('您确定要清除所有排序条件吗？', '确认', function (yes) {
                            if (yes) {
                                g.clearSort();
                                g.runSort();
                            }
                        })
                    }},
                    "->","-",
                    {text: "执行", icon: "run", click: function () {
                        g.runSort();
                    }}
                ]
            }
            var width=700;
            var height=width*0.618;
            g.dlg = $.ligerDialog.open({title:"高级", content: tab1, height:parseInt(700*0.618), width: 700, isResize: true,showMax:true,isDrag:true});
			$("#__qm_advanced__panel").ligerTab({height:"100%"});
            $("#__qm_advanced__search__bar").ligerToolBar(searchToolBar);
            $("#__qm_advanced__sort__bar").ligerToolBar(sortToolBar);
			//var w=$("#__qm_advanced__search__body").parent().width();
			var h=$("#__qm_advanced__search__body").parent().height();
			var h1=h-$("#__qm_advanced__search__bar").outerHeight();
			$("#__qm_advanced__search__body").height(h1);
			$("#__qm_advanced__sort__body").height(h1);
            g.dlg.show();
            if($(window).height()<height||$(window).width()<width){
                g.dlg.max();
            }
            g.dlg._onReisze=function(){//2013-5-15 上午10:07 lybide
				var g = this, p = this.options;
				var gc=g.dialog.content;
				var h=gc.height();
				$("#__qm_advanced__panel",gc).height(h);
				$(".l-tab-content,.l-tab-content-item",gc).height(h-$(".l-tab-links",gc).height());
				var ltci=$(".l-tab-content-item",gc);
				$("#__qm_advanced__search__body",gc).height(ltci.height()-33);
				$("#__qm_advanced__sort__body",gc).height(ltci.height()-33);
			};
        };
        this.init = function () {
            var g = this, p = this.options;
            g.initWindow();
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
            for(var i in defaultCon){
                g.addGroup(defaultCon[i]);
            }
            //2017年07月31日 11:15:01 星期一 lybide todo del 删除 以下事件绑定已不起效果
            /*$(".__qm__group__del").live("click",function(){
                //$(this).parent().parent().parent().parent().parent().parent().remove();
				var table=$(this).parents(".qm-groupTable").parent();table.remove();
                $("#"+ p.searchRenderTo).find("table:first").find(".groupLogic").hide();
            });
            $(".__qm__search__name").live("change",function(){
                var tr=$(this).parent().parent();
                var td1=tr.find("td:eq(1)");
                var td2=tr.find("td:eq(2)");
                var name=$(this).val();
                var type = Qm.getColumnValueType(Qm.config.columnsInfo[name]);
                td1.empty().append(g.getLogicCombo(type));
                var obj = {name: name};
                Qm.parseValueField(obj,td2);
            });

            $(".__qm__search__row__add").live("click",function(){
                //var table=$(this).parent().parent().parent().find(".qm-conditionTable");
				var table=$(this).parents(".qm-groupTable").find(">tbody>tr:eq(1) .qm-conditionTable");
                g.addRow(table);
            });
            $(".__qm__search__row__del").live("click",function(){alert(123123)
                //$(this).parent().parent().remove();
                $(this).parents(".__qm__search__row_tr1").remove();
            });*/
        };
        this.addGroup = function (con) {
            var g = this, p = this.options;
            if(!con)con= g.defaultCon[0];
            var name = "__qm__logic__" + this.builderIndex;
            var logicStr= "<input type='radio' value='and' "+(con.logic=="and"?"checked='true'":"")+" name='" + name + "' id='__qm__logic__id__" + (++this.builderIndex) + "'><label for='__qm__logic__id__" + this.builderIndex + "'>并且</label> " +
                "<input type='radio' value='or' "+(con.logic=="or"?"checked='true'":"")+" name='" + name + "' id='__qm__logic__id__" + (++this.builderIndex) + "'><label for='__qm__logic__id__" + this.builderIndex + "'>或者</label>";

            var gTable=$(g.groupTable).appendTo("#"+ p.searchRenderTo);
            //2017年07月31日 11:18:24 星期一 lybide todo add
            //=============================================================================================
            $(".__qm__group__del",gTable).on("click",function(){
                //$(this).parent().parent().parent().parent().parent().parent().remove();
                var table=$(this).parents(".qm-groupTable").parent();table.remove();
                $("#"+ p.searchRenderTo).find("table:first").find(".groupLogic").hide();
            });
            $(".__qm__search__row__add",gTable).on("click",function(){
                //var table=$(this).parent().parent().parent().find(".qm-conditionTable");
                var table=$(this).parents(".qm-groupTable").find(">tbody>tr:eq(1) .qm-conditionTable");
                g.addRow(table);
            });
            //=============================================================================================
            gTable.find(".groupLogic").append(logicStr);
            var table=gTable.find(".qm-conditionTable");
            var conditions=con.conditions;
            for(var i in conditions){
                g.addRow(table,conditions[i]);
            }
            $("#"+ p.searchRenderTo).find("table:first .groupLogic").hide();
        };
        this.clearGroup=function(){
            var g = this, p = this.options;
            $("#"+ p.searchRenderTo).empty();
        };
        this.getNameCombo=function(name){
            var columnSelectStr="<select name='name' class='__qm__search__name'>";
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

        this.addRow = function (conditionTable, con) {
            var g = this, p = this.options;
            var obj = {};
            if (!con) {
                con = this.defaultCondition;
            }
            $.extend(obj, con);
            var type = Qm.getColumnValueType(Qm.config.columnsInfo[obj.name]);
            var tr = $("<tr class='__qm__search__row_tr1'>" +
                "<td class='qm-conditionTable-td1'>" + this.getNameCombo(obj.name) + "</td>" +
                "<td class='qm-conditionTable-td2'>" + this.getLogicCombo(type, obj.compare) + "</td>" +
                "<td class='qm-conditionTable-td3'></td>" +
                "<td class='qm-conditionTable-td4'><div class='__qm__search__row_bar1'><input type='button' value='' class='l-icon-del __qm__search__row__del' title='删除条件'></div></td>" +
                "</tr>");
            //2017年07月31日 11:36:28 星期一 lybide todo add
            //=============================================================================================
            $(".__qm__search__name",tr).on("change",function(){
                var tr=$(this).parent().parent();
                var td1=tr.find("td:eq(1)");
                var td2=tr.find("td:eq(2)");
                var name=$(this).val();
                var type = Qm.getColumnValueType(Qm.config.columnsInfo[name]);
                td1.empty().append(g.getLogicCombo(type));
                var obj = {name: name};
                Qm.parseValueField(obj,td2);
            });
            $(".__qm__search__row__del",tr).on("click",function(){
                //$(this).parent().parent().remove();
                $(this).parents(".__qm__search__row_tr1").remove();
            });
            //=============================================================================================
            tr.appendTo(conditionTable);
            Qm.parseValueField(obj,tr.find("td:eq(2)"));
        };

        this.run = function () {
            var g = this, p = this.options;
            var para = [];
            var table = $("#"+ p.searchRenderTo + " .qm-groupTable");
            table.each(function (i) {
                var con={};
                con["logic"]=i==0?"and":$(".groupLogic :checked",this).val();
                var conditions=[];
                var conditionTableTr=$(this).find(".qm-conditionTable tr");
                conditionTableTr.each(function(){
                    var tr=$(this);
                    var p={name:tr.find("td:eq(0) select").val()};
                    var col = Qm.config.columnsInfo[p.name];
                    if (col) {
                        p.logic ="and";
                        p.label = col.columndisplay;
                        p.compare = tr.find("td:eq(1) select").val();
                        p.dataType = col.datatype;
                        p.value = $.ligerui.get(tr.find("td:eq(2) :text")).getValue();
                        conditions.push(p);
                    }
                });
                con["conditions"]=conditions;
                para.push(con);
            });

            Qm.getQmgrid().setSessionAdvancedCondition(para);
            Qm.getQmgrid().setSearchPara(para);
            Qm.getQmgrid().set("newPage", 1);
            Qm.refreshGrid();
            Qm.advancedSearchTip.show();
            this.dlg.hidden();
        };

        this.addSort = function (con) {
            var g = this, p = this.options;
            //$("#"+ p.sortRenderTo).empty();
            if (!con) {
                con = {field:g.defaultCondition.name,direction:"asc"};
            }
            var sortTable=$("#"+p.sortRenderTo+" table");
            if(sortTable.length==0){
                sortTable=$("<div class='p5'><table class='qm-sortTable' border='0' cellspacing='0' cellpadding='0'></table></div>").appendTo("#"+p.sortRenderTo);
				sortTable=sortTable.children();
            }
            var tr = $("<tr class='__qm__search__row_tr1'>" +
                "<td class='qm-sortTable-td1'>" + this.getNameCombo(con.field).replace("__qm__search__name","__qm__search__sort__name") + "</td>" +
                "<td class='qm-sortTable-td2'>" + this.getSortCombo(con.direction) + "</td>" +
                //"<td class='qm-sortTable-td3'></td>" +
                "<td class='qm-sortTable-td4'><div class='__qm__search__row_bar1'><input type='button' value='' class='l-icon-del __qm__search__row__del' title='删除条件'></div></td>" +
                "</tr>");
            //2017年07月31日 11:36:28 星期一 lybide todo add
            $(".__qm__search__row__del",tr).on("click",function(){
                //$(this).parent().parent().remove();
                $(this).parents(".__qm__search__row_tr1").remove();
            });
            tr.appendTo(sortTable);
        };
        this.getSortCombo=function(value){
            return "<select name='name' class='__qm__search__sort'>" +
                "<option value='asc' "+("asc"===value.toLowerCase()?"selected":"")+">升序</option>" +
                "<option value='desc' "+("desc"===value.toLowerCase()?"selected":"")+">降序</option>" +
                "</select>";
        };
        this.clearSort=function () {
            var g = this, p = this.options;
            $("#"+ p.sortRenderTo).empty();
        };
        this.runSort = function () {
            var g = this, p = this.options;
            var para = [];
            var tableTr = $("#"+ p.sortRenderTo+" tr");
            tableTr.each(function(){
                var tr=$(this);
                para.push({field:tr.find("td:eq(0) select").val(),direction:tr.find("td:eq(1) select").val()});
            });

            Qm.getQmgrid().setSortInfo(para);
            if (para.length > 0){
                Qm.getQmgrid().set("sortName",para[0].field);
                Qm.getQmgrid().set("sortOrder", para[0].direction);
            }
            else{
                Qm.getQmgrid().set("sortName","");
                Qm.getQmgrid().set("sortOrder", "");
            }
            g.run();
        };
        this.show=function(){
            this.dlg.show();
        };

        this.init();

    }

	Qm.addCheckedRows = function(data){
		if(Qm.findCheckedRows(data) == -1)
            __checkedRows.push(data);
	}
	Qm.removeCheckedRows = function(data){
		var i = Qm.findCheckedRows(data);
        if(i==-1) return;
		__checkedRows.splice(i,1);
	}


	Qm.findCheckedRows = function(data){
		for(var i =0;i<__checkedRows.length;i++)
		{
			var checkedId = Qm.userConfig.checkRowId||"id";
			if(data[checkedId]==__checkedRows[i][checkedId]){
				return i;
			}
		}
		return -1;
	}
	
}


/////////////////////////////////////////////////////////