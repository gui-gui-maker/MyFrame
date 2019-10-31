/**
 * jQuery ligerUI 1.1.9 ligerComboBox
 *
 * http://ligerui.com
 *
 * Author daomi 2012 [ gd_star@163.com ]
 *
 */
(function($) {

	$.fn.ligerComboBox = function(options) {
		return $.ligerui.run.call(this, "ligerComboBox", arguments);
	};

	$.fn.ligerGetComboBoxManager = function() {
		return $.ligerui.run.call(this, "ligerGetComboBoxManager", arguments);
	};

	$.ligerDefaults.ComboBox = {
		resize: true,                   //是否调整大小
		isMultiSelect: false,           //是否多选
		isShowCheckBox: false,         //是否选择复选框
		columns: false,                //表格状态
		selectBoxWidth: false,         //宽度
		selectBoxHeight: false,        //高度
		onBeforeSelect: false,         //选择前事件
		onSelected: null,              //选择值事件
		initValue: null,
		initText: null,
		valueField: 'id',
		textField: 'text',
		valueFieldID: null,
		isTextBoxMode:false,
		slide: true,                 //是否以动画的形式显示
		split: typeof KH__valueSplit == "undefined" ? "," : KH__valueSplit,//todo lybide
		data: null,
		tree: null,                    //下拉框以树的形式显示，tree的参数跟LigerTree的参数一致
		treeLeafOnly: true,            //下拉框以树的形式显示，展示树默认展开
		treeExpand: true,             //是否只选择叶子
		grid: null,                   //表格
		onStartResize: null,
		onEndResize: null,
		hideOnLoseFocus: true,
		url: null,               //数据源URL(需返回JSON)
		onSuccess: null,
		onError: null,
		onBeforeOpen: null,       //打开下拉框前事件，可以通过return false来阻止继续操作，利用这个参数可以用来调用其他函数，比如打开一个新窗口来选择值
		render: null,             //文本框显示html函数
		absolute: true,             //选择框是否在附加到body,并绝对定位
		linkIconShow:true,            //todo add by lybide 是否显示下拉框图标
		emptyOption: true,       //todo add by ttaomeng   是否第一组数据为空白选项,
		icon: 'trigger',         //todo add by ttaomeng   自定义图标,
		textModel: false             //todo add by ttaomeng   下拉框文本模式
	};

	//扩展方法
	$.ligerMethos.ComboBox = $.ligerMethos.ComboBox || {};

	$.ligerui.controls.ComboBox = function(element, options) {
		$.ligerui.controls.ComboBox.base.constructor.call(this, element, options);
	};
	$.ligerui.controls.ComboBox.ligerExtend($.ligerui.controls.Input, {
		__getType: function() {
			return 'ComboBox';
		},
		_extendMethods: function() {
			return $.ligerMethos.ComboBox;
		},
		_init: function() {
			$.ligerui.controls.ComboBox.base._init.call(this);
			var p = this.options;
			if (p.columns) {
				p.isShowCheckBox = true;
			}
			if (p.isMultiSelect) {
				p.isShowCheckBox = true;
			}
			p.icon= p.icon || "trigger";//2017年06月15日 16:58:07 星期四 lybide
		},
		_render: function() {
			var g = this, p = this.options;
			g.data = p.data;
			g.inputText = null;
			g.select = null;
			g.textFieldID = "";
			g.valueFieldID = "";
			g.valueField = null; //隐藏域(保存值)
			//文本框初始化
			if (this.element.tagName.toLowerCase() == "input") {
				this.element.readOnly = true;
				g.inputText = $(this.element);
				g.textFieldID = this.element.id;
			} else if (this.element.tagName.toLowerCase() == "select") {
				$(this.element).hide();
				g.select = $(this.element);
				p.isMultiSelect = false;
				p.isShowCheckBox = false;
				g.textFieldID = this.element.id + "_txt";
				g.inputText = $('<input type="text" readonly="true"/>');
				g.inputText.attr("id", g.textFieldID).insertAfter($(this.element));
			} else {
				//不支持其他类型
				return;
			}

			if (g.inputText[0].name == undefined) g.inputText[0].name = g.textFieldID;
			//隐藏域初始化
			g.valueField = null;
			if (p.valueFieldID) {
				g.valueField = $("#" + p.valueFieldID + ":input");
				if (g.valueField.length == 0) g.valueField = $('<input type="hidden"/>');
				g.valueField[0].id = g.valueField[0].name = p.valueFieldID;
			} else {
				g.valueField = $('<input type="hidden"/>');
				g.valueField[0].id = g.valueField[0].name = g.textFieldID + "_val";
			}
			if (g.valueField[0].name == undefined) g.valueField[0].name = g.valueField[0].id;
			if (p.explain) { //todo 添加自定义说明 13-1-17 下午1:30 lybide
				g.inputText.parent().append('<div class="l-text-explain"><div class="l-text-explain-triangle"></div><div class="l-text-explain-div">' + p.explain + '</div></div>');
			}

			//开关 //todo add by ttaomeng 自定义图标 2017年06月15日 16:36:38 星期四 lybide
			if (p.linkIconShow) {
				g.link = $('<div class="l-trigger"><div class="l-trigger-box iconfont l-icon ' + (p.icon.indexOf("l-icon") != -1 ? p.icon : 'l-icon-' + p.icon) + '"></div></div>');
			} else {
				g.link = $('');
			}

			//下拉框
			g.selectBox = $('<div class="l-box-select l-box-panel" onclick="event.cancelBubble=true;"><div class="l-box-select-inner"><table cellpadding="0" cellspacing="0" border="0" class="l-box-select-table"></table></div></div>');
			g.selectBox.table = $("table:first", g.selectBox);
			//外层
			g.wrapper = g.inputText.wrap('<div class="l-text l-text-combobox"></div>').parent();
			g.wrapper.append('<div class="l-text-l"></div><div class="l-text-r"></div>');
			g.wrapper.append(g.link);

			var w1 = g.inputText.wrap('<div class="l-text-sdi"></div>').parent(); //todo 再添加一个包裹，以支持显示 2013-5-10 下午5:38 lybide
			//todo 文本框可有套图标。2013-5-20 下午5:47 lybide 2013-5-21 下午2:55 lybide
			if (p.iconItems) {
				//var w1=g.inputText.wrap('<div class="l-text-sdi"></div>').parent();
				g.wrapper.addClass("l-text-has-icon-item");//2017年06月15日 13:02:03 星期四 lybide
				var twidth = 0;
				var tr = twidth;
				var gLinkFirstWidth=g.link.outerWidth(true)+parseFloat(g.link.css("right"));//2017年06月15日 12:56:28 星期四 lybide
				$.each(p.iconItems, function(i, item) {
					var gLink;
					if (item["img"]) {
						gLink = $('<div class="l-trigger l-trigger-custom"><div class="l-trigger-box iconfont l-icon" style="background:url(' + item["img"] + ') no-repeat center center;"></div></div>');
					} else if (item["icon"]) {
						gLink = $('<div class="l-trigger l-trigger-custom"><div class="l-trigger-box iconfont l-icon ' + (item["icon"].indexOf("l-icon") != -1 ? item["icon"] : 'l-icon-' + item["icon"]) + '"></div></div>');
					}
					item["id"] && gLink.attr("id",item["id"]);
					g.wrapper.append(gLink);
					if (i==0) { //2017年06月15日 12:45:35 星期四 lybide
						twidth=gLinkFirstWidth+gLink.outerWidth(true)*p.iconItems.length;
						tr=twidth;
					}
					tr -= gLink.outerWidth(true) + 0;
					gLink.css({
						"right": tr
					});

					if (item["click"]) {
						gLink.bind("click", function(e) {
							if (p.disabled) {
								return;
							}
							//可编辑表格，输入框有自定义图标绑定事件，事件必须为字符串 click:"function1" 2017年06月20日 17:24:23 星期二 lybide
							if (typeof item["click"]=="string") {
								eval(item["click"]+".call(null, g.getValue(), g, this);");
								return;
							}
							item["click"].call(null, g.getValue(), g, this);
						});
					}
					gLink.hover(function() { //todo 2013-5-21 上午11:14 lybide
						if (p.disabled) return;
						$(this).addClass("l-trigger-hover");
					}, function() {
						if (p.disabled) return;
						$(this).removeClass("l-trigger-hover");
					}).mousedown(function() {
						if (p.disabled) return;
						$(this).addClass("l-trigger-pressed");
					}).mouseup(function() {
						if (p.disabled) return;
						$(this).removeClass("l-trigger-pressed");
					});
				});
				w1.css({
					"margin-right": twidth + gLinkFirstWidth
				});
			}
			//添加个包裹，
			//g.textwrapper = g.wrapper.wrap('<div class="l-text-wrapper"></div>').parent();
			if (p.suffix) {
				g.textwrapper = g.wrapper.wrap("<table border='0' width='" + (p.width ? p.width : "100%") + "' cellspacing='0' cellpadding='0' class='l-text-suffix-wrap'><tr><td class='l-text-left'><div class='l-text-wrapper'></div></td><td class='l-text-suffix' " + (p.suffixWidth ? "style='width:" + p.suffixWidth + "px'" : "") + ">" + p.suffix + "</td></tr></table>").parent();
				//g.textwrapper=g.wrapper.wrap('<div class="l-text-suffix-wrap" style="'+(p.width? p.width:"")+'"><div class="l-text-wrapper l-text-suffix-core"></div><div class="l-text-suffix">'+p.suffix+'</div></div>').parent();
			} else { //todo 添加个包裹 13-1-11 下午3:29 lybide
				g.textwrapper = g.wrapper.wrap('<div class="l-text-wrapper"></div>').parent();
			}
			if (p.absolute) {
				g.selectBox.appendTo('body').addClass("l-box-select-absolute");
			} else {
				g.textwrapper.append(g.selectBox);
			}

			g.textwrapper.append(g.valueField);
			g.inputText.addClass("l-text-field");

			if (p.isShowCheckBox && !g.select) {
				$("table", g.selectBox).addClass("l-table-checkbox");
			} else {
				p.isShowCheckBox = false;
				$("table", g.selectBox).addClass("l-table-nocheckbox");
			}
			//开关 事件
			g.link.hover(function() {
				if (p.disabled) return;
				this.className = "l-trigger-hover";
			}, function() {
				if (p.disabled) return;
				this.className = "l-trigger";
			}).mousedown(function() {
				if (p.disabled) return;
				this.className = "l-trigger-pressed";
			}).mouseup(function() {
				if (p.disabled) return;
				this.className = "l-trigger-hover";
			}).click(function(e) {
				//e.stopPropagation(); //todo 阻止js事件冒泡的作用
				if (p.disabled) return;
				if (g.trigger('beforeOpen') == false) return false;
				if (p.textModel) {
					return;
				} //todo 2013-5-10 下午3:37 lybide
				g._toggleSelectBox(g.selectBox.is(":visible"));
			});
			g.inputText.click(function(e) {
				//e.stopPropagation(); //todo 阻止js事件冒泡的作用
				if (p.disabled) return;
				if (g.trigger('beforeOpen') == false) return false;
				if (p.textModel) {
					return;
				} //todo 2013-5-10 下午3:37 lybide
				g._toggleSelectBox(g.selectBox.is(":visible"));
			}).blur(function() {
				if (p.disabled) return;
				g.wrapper.removeClass("l-text-focus");
			}).focus(function() {
				if (p.disabled) return;
				g.wrapper.addClass("l-text-focus");
			}).keydown(function(e) {
				if (p.isTextBoxMode) {
					return;
				}
				//todo 添加键盘事件，delete & 回退键 删除下拉框的值。2013-6-20 下午1:51 lybide
				e.preventDefault();
				e.stopPropagation(); //阻止js事件冒泡的作用
				if (e.keyCode == 46 || e.keyCode == 8) {
					g._setValue("");
					g._toggleSelectBox(true);
				}
			});
			g.wrapper.hover(function() {
				if (p.disabled) return;
				g.wrapper.addClass("l-text-over");
			}, function() {
				if (p.disabled) return;
				g.wrapper.removeClass("l-text-over");
			});
			g.resizing = false;
			/*g.selectBox.hover(null, function (e)//todo 2013-5-17 下午10:50 lybide 删除
			 {
			 if (p.hideOnLoseFocus && g.selectBox.is(":visible") && !g.boxToggling && !g.resizing)
			 {
			 g._toggleSelectBox(true);
			 }
			 });*/
			//todo 2012年08月21日 星期二 17:18:20 lybide 计算下拉框高度
			var itemsleng = $("tr", g.selectBox.table).length;
			if (!p.selectBoxHeight && itemsleng < 8) p.selectBoxHeight = itemsleng * 30;
			if (p.selectBoxHeight) {
				g.selectBox.height(p.selectBoxHeight);
			}
			//下拉框内容初始化
			g.bulidContent();

			g.set(p);
			//g.link.css("top",(g.textwrapper.height()-g.link.height())/2-2);//todo 计算小图标top 13-1-22 上午9:42 lybide
			//下拉框宽度、高度初始化
			if (p.selectBoxWidth) {
				g.selectBox.width(p.selectBoxWidth);
			} else {
				g.selectBox.css('width', g.wrapper.css('width'));
			}
			if (p.isTextBoxMode) {
				g.inputText.removeAttr("readonly");
			}
			if (p.textModel) {
				g._dataInit();
			}
		},
		destroy: function() {
			if (this.wrapper) this.wrapper.remove();
			if (this.selectBox) this.selectBox.remove();
			this.options = null;
			$.ligerui.remove(this);
		},
		_setDisabled: function(value) {
			//禁用样式
			if (value) {
				this.wrapper.addClass('l-text-disabled');
			} else {
				this.wrapper.removeClass('l-text-disabled');
			}
		},
		//todo 2013-5-29 下午10:20 lybide
		_setReadonly: function(value) {
			var g = this, p = this.options;
			if (value) {
				g.inputText.attr("readonly", "readonly");
				g.wrapper.addClass("l-text-readonly");
			} else {
				g.inputText.removeAttr("readonly");
				g.wrapper.removeClass('l-text-readonly');
			}
		},
		_setLable: function(label) {
			var g = this, p = this.options;
			if (label) {
				if (g.labelwrapper) {
					g.labelwrapper.find(".l-text-label:first").html(label + ':&nbsp');
				} else {
					g.labelwrapper = g.textwrapper.wrap('<div class="l-labeltext"></div>').parent();
					g.labelwrapper.prepend('<div class="l-text-label" style="float:left;display:inline;">' + label + ':&nbsp</div>');
					g.textwrapper.css('float', 'left');
				}
				if (!p.labelWidth) {
					p.labelWidth = $('.l-text-label', g.labelwrapper).outerWidth();
				} else {
					$('.l-text-label', g.labelwrapper).outerWidth(p.labelWidth);
				}
				$('.l-text-label', g.labelwrapper).width(p.labelWidth);
				$('.l-text-label', g.labelwrapper).height(g.wrapper.height());
				g.labelwrapper.append('<br style="clear:both;" />');
				if (p.labelAlign) {
					$('.l-text-label', g.labelwrapper).css('text-align', p.labelAlign);
				}
				g.textwrapper.css({
					display: 'inline'
				});
				g.labelwrapper.width(g.wrapper.outerWidth() + p.labelWidth + 2);
			}
		},
		_setWidth: function(value) {
			var g = this;
			if (value > 20) {
				//g.wrapper.css({ width: value });
				//g.inputText.css({ width: value - 20 });//todo lybide 删除选择下拉框宽度
				g.textwrapper.css({
					width: value
				});
			}
		},
		_setHeight: function(value) {
			var g = this;
			if (value > 10) {
				g.wrapper.height(value);
				g.inputText.height(value - 2);
				//g.link.height(value - 4);//todo 2013-12-19 下午3:58 lybide 删除，因为css可以完全控制高度。不需要再计算
				g.textwrapper.css({
					height: value
				}); //todo 2013-5-20 上午10:54 lybide 修改此设置高度，原程序设置成width了。
			}
		},
		_setResize: function(resize) {
			//调整大小支持
			if (resize && $.fn.ligerResizable) {
				var g = this;
				g.selectBox.ligerResizable({
					handles: 'se,s,e',
					onStartResize: function() {
						g.resizing = true;
						g.trigger('startResize');
					},
					onEndResize: function() {
						g.resizing = false;
						if (g.trigger('endResize') == false) return false;
					}
				});
				g.selectBox.append("<div class='l-btn-nw-drop'></div>");
			}
		},
		//查找Text,适用多选和单选
		findTextByValue: function(value) {
			var g = this, p = this.options;
			if (value == undefined) return "";
			var texts = "";
			var contain = function(checkvalue) {
				var targetdata = value.toString().split(p.split);
				for (var i = 0; i < targetdata.length; i++) {
					if (targetdata[i] == checkvalue) return true;
				}
				return false;
			};
			$(g.data).each(function(i, item) {
				var val = item[p.valueField];
				var txt = item[p.textField];
				if (contain(val)) {
					texts += txt + p.split;
				}
			});
			if (texts.length > 0) texts = texts.substr(0, texts.length - 1);
			return texts;
		},
		removeItem: function() {
		},
		//查找Value,适用多选和单选 todo 该方法针对树形结构有问题
		findValueByText: function(text) {
			var g = this, p = this.options;
			if (!text && text == "") return "";
			var contain = function(checkvalue) {
				var targetdata = text.toString().split(p.split);
				for (var i = 0; i < targetdata.length; i++) {
					if (targetdata[i] == checkvalue) return true;
				}
				return false;
			};
			var values = "";
			$(g.data).each(function(i, item) {
				var val = item[p.valueField];
				var txt = item[p.textField];
				if (contain(txt)) {
					values += val + p.split;
				}
			});
			if (values.length > 0) values = values.substr(0, values.length - 1);
			return values;
		},
		insertItem: function() {
		},
		addItem: function() {

		},
		_setValue: function(value) {
			//if(!value)return;//todo lybide 值为空时，返回
			var g = this, p = this.options;
			if (g.options.textModel) { //todo modified by ttaomeng   文本模式
				$(g.element).val(value);
				ligerElementRequired($(g.element), value, g, p);
				return;
			}
			if (g.options.isTextBoxMode) {//todo 2017年06月16日 17:11:17 星期五 lybide
				$(g.element).val(value);
				ligerElementRequired($(g.element), value, g, p);
				return;
			}
			var text = g.findTextByValue(value);
			if (p.tree) {
				g.selectValueByTree(value);
			} else if (!p.isMultiSelect) {
				g._changeValue(value, text);
				$("tr[value=" + value + "] td", g.selectBox).addClass("l-selected");
				$("tr[value!=" + value + "] td", g.selectBox).removeClass("l-selected");
			} else {
				g._changeValue(value, text);
				if (!value) {
					value = "";
				} //todo 表格编辑时，多选没有值时，需要把空整成空值。2013-5-10 下午10:44 lybide
				var targetdata = value.toString().split(p.split);
				//$("table.l-table-checkbox :checkbox", g.selectBox).each(function () { this.setAttribute("checked",false); });//todo 2013-5-11 上午10:33 lybide
				$("table.l-table-checkbox :checkbox", g.selectBox).attr("checked", false).change(); //each(function () { this.setAttribute("checked",false); });//todo 2013-5-11 上午10:33 lybide
				for (var i = 0; i < targetdata.length; i++) {
					//$("table.l-table-checkbox tr[value=" + targetdata[i] + "] :checkbox", g.selectBox).each(function () { this.setAttribute("checked",true); });
					$("table.l-table-checkbox tr[value=" + targetdata[i] + "] :checkbox", g.selectBox).attr("checked", true).change(); //todo 简洁方式 2013-5-11 下午3:01 lybide
					//todo 2013-5-10 下午11:24 lybide
					//$("table.l-table-checkbox tr[value=" + targetdata[i] + "] .l-checkbox", g.selectBox).each(function () { $(this).addClass("l-checkbox-checked"); });
					//$("table.l-table-checkbox tr[value=" + targetdata[i] + "] .l-checkbox", g.selectBox).addClass("l-checkbox-checked");
					//$("table.l-table-checkbox tr[value=" + targetdata[i] + "]", g.selectBox).css("background","#FF0000");
				}
			}
		},
		selectValue: function(value) {
			this._setValue(value);
		},
		bulidContent: function() {
			var g = this, p = this.options;
			//todo 暂不打开，原生select也需要解析成ltype="select" 2013-9-10 下午4:44 lybide
			//if (!p.tree && !p.url && (!g.data || !g.data.length)) return;//todo 2013-6-5 下午1:38 lybide
			this.clearContent();
			if (g.select) {
				g.setSelect();
			} else if (g.data) {
				g.setData(g.data);
			} else if (p.tree) {
				g.setTree(p.tree);
			} else if (p.grid) {
				g.setGrid(p.grid);
			} else if (p.url) {
				$.ajax({
					type: 'post',
					url: p.url,
					cache: false,
					dataType: 'json',
					success: function(data) {
						//todo lybide 判断json中是否有data
						//[{"text":"中国","id":"1" },{"text":"美国","id":"2"}]
						//{"data":[{"text":"盐亭福利院","id":"510723"},{"text":"云溪镇福利院","id":"510723001"}],"success":true}
						if (data["data"]) {
							g.data = data["data"];
						} else {
							g.data = data;
						}
						//g.data = data;
						g.setData(g.data);
						g.trigger('success', [g.data]);
					},
					error: function(XMLHttpRequest, textStatus) {
						g.trigger('error', [XMLHttpRequest, textStatus]);
					}
				});
			}
		},
		clearContent: function() {
			var g = this, p = this.options;
			$("table", g.selectBox).html("");
			//g.inputText.val("");
			//g.valueField.val("");
		},
		setSelect: function() {
			var g = this, p = this.options;
			this.clearContent();
			$('option', g.select).each(function(i) {
				var val = $(this).val();
				var txt = $(this).html();
				var tr = $("<tr><td index='" + i + "' value='" + val + "'>" + txt + "</td>");
				$("table.l-table-nocheckbox", g.selectBox).append(tr);
				$("td", tr).hover(function() {
					$(this).addClass("l-over");
				}, function() {
					$(this).removeClass("l-over");
				});
			});
			$('td:eq(' + g.select[0].selectedIndex + ')', g.selectBox).each(function() {
				if ($(this).hasClass("l-selected")) {
					g.selectBox.hide();
					return;
				}
				$(".l-selected", g.selectBox).removeClass("l-selected");
				$(this).addClass("l-selected");
				if (g.select[0].selectedIndex != $(this).attr('index') && g.select[0].onchange) {
					g.select[0].selectedIndex = $(this).attr('index');
					g.select[0].onchange();
				}
				var newIndex = parseInt($(this).attr('index'));
				g.select[0].selectedIndex = newIndex;
				g.select.trigger("change");
				g.selectBox.hide();
				var value = $(this).attr("value");
				var text = $(this).html();
				if (p.render) {
					g.inputText.val(p.render(value, text));
				} else {
					g.inputText.val(text);
				}
			});
			g._addClickEven();
		},
		//生成下拉框内容，原程序是页面初始化已生成。
		setData: function(data) {
			var g = this, p = this.options;
			this.clearContent();
			//if (!p.tree && !p.url && (!g.data || !g.data.length)) return;//todo 2013-6-5 下午1:38 lybide
			if (p.emptyOption && !p.isMultiSelect) { //todo lybide
				var newDate = []; //把原来的空值需要全清空
				$.each(data, function(i, dataItem) { //todo 2013-5-10 下午10:17 lybide
					if (dataItem[p.valueField]) { //todo 2013-5-16 下午10:42 lybide 改进从原来的id变为p.valueField
						newDate.push(dataItem);
					}
				});
				data = newDate;
				var temp = {};
				for (var i in data[0]) {
					temp[i] = "";
				}
				data.unshift(temp);
			}
			if (g.data != data) g.data = data;
			if (p.columns) {
				g.selectBox.table.headrow = $("<tr class='l-table-headerow'><td width='18px'></td></tr>");
				g.selectBox.table.append(g.selectBox.table.headrow);
				g.selectBox.table.addClass("l-box-select-grid");
				for (var j = 0; j < p.columns.length; j++) {
					var headrow = $("<td columnindex='" + j + "' columnname='" + p.columns[j].name + "'>" + p.columns[j].header + "</td>");
					if (p.columns[j].width) {
						headrow.width(p.columns[j].width);
					}
					g.selectBox.table.headrow.append(headrow);

				}
			}
			for (var i = 0; i < data.length; i++) {
				var val = data[i][p.valueField]||"";//todo modify by jyl 修复表格值为undefined的情况
				var txt = data[i][p.textField]||"";//todo modify by jyl 修复表格值为undefined的情况
				if (!p.columns) {
					$("table.l-table-checkbox", g.selectBox).append("<tr value='" + val + "'><td style='width:18px;'  index='" + i + "' value='" + val + "' text='" + txt + "' ><input type='checkbox' /></td><td index='" + i + "' value='" + val + "' align='left'>" + txt + "</td>");
					$("table.l-table-nocheckbox", g.selectBox).append("<tr value='" + val + "'><td index='" + i + "' value='" + val + "' align='left'>" + txt + "</td>");
				} else {
					var tr = $("<tr value='" + val + "'><td style='width:18px;'  index='" + i + "' value='" + val + "' text='" + txt + "' ><input type='checkbox' /></td></tr>");
					$("td", g.selectBox.table.headrow).each(function() {
						var columnname = $(this).attr("columnname");
						if (columnname) {
							var td = $("<td>" + data[i][columnname] + "</td>");
							tr.append(td);
						}
					});
					g.selectBox.table.append(tr);
				}
			}
			//自定义复选框支持
			if (p.isShowCheckBox && $.fn.ligerCheckBox) {
				$("table input:checkbox", g.selectBox).ligerCheckBox();
				//todo 选择行，可选择复选框 2013-5-11 下午2:27 lybide
				$("table td:odd", g.selectBox).mousedown(function() {
					var $this = $(this).parent();
					$this.addClass("l-selected");
					var ckb = $this.find(":checkbox");
					ckb.attr("checked", ckb.attr("checked") ? false : true).change();
				});
			}
			$(".l-table-checkbox input:checkbox", g.selectBox).change(function() {
				if (this.checked && g.hasBind('beforeSelect')) {
					var parentTD = null;
					if ($(this).parent().get(0).tagName.toLowerCase() == "div") {
						parentTD = $(this).parent().parent();
					} else {
						parentTD = $(this).parent();
					}
					if (parentTD != null && g.trigger('beforeSelect', [parentTD.attr("value"), parentTD.attr("text")]) == false) {
						g.selectBox.slideToggle("fast");
						return false;
					}
				}
				if (!p.isMultiSelect) {
					if (this.checked) {
						$("input:checked", g.selectBox).not(this).each(function() {
							this.checked = false;
							$(".l-checkbox-checked", $(this).parent()).removeClass("l-checkbox-checked");
						});
						g.selectBox.slideToggle("fast");
					}
				} else { //todo 给行添加选择效果 2013-5-11 下午2:11 lybide
					if (this.checked) {
						$(this).parent().parent().parent().addClass("l-selected");
						$(".l-checkbox", $(this).parent()).addClass("l-checkbox-checked");
					} else {
						$(this).parent().parent().parent().removeClass("l-selected");
						$(".l-checkbox", $(this).parent()).removeClass("l-checkbox-checked");
					}
				}
				g._checkboxUpdateValue();
			});
			$("table.l-table-nocheckbox td,table.l-table-checkbox tr", g.selectBox).hover(function() {
				$(this).addClass("l-over");
			}, function() {
				$(this).removeClass("l-over");
			});
			g._addClickEven();
			//选择项初始化
			g._dataInit();
		},
		//树
		setTree: function(tree) {
			var g = this, p = this.options;
			this.clearContent();
			g.selectBox.table.remove();
			//todo add by ttaomeng
			//            tree.async=false;
			if (tree.checkbox != false) {
				tree.onCheck = function() {
					var nodes = g.treeManager.getChecked();
					var value = [];
					var text = [];
					$(nodes).each(function(i, node) {
						if (p.treeLeafOnly && node.data.children) return;
						value.push(node.data[p.valueField]);
						text.push(node.data[p.textField]);
					});
					g._changeValue(value.join(p.split), text.join(p.split));
				};
			} else {
				tree.onSelect = function(node) {
					if (p.treeLeafOnly && node.data.children) return;
					var value = node.data[p.valueField];
					var text = node.data[p.textField];
					g._changeValue(value, text);
					//todo add by jyl 单击选中关闭下拉框
					//g._toggleSelectBox(true);
				};
				tree.onCancelSelect = function(node) {
					g._changeValue("", "");
					//todo add by jyl 单击选中关闭下拉框
					//g._toggleSelectBox(true);
				};
			}
			tree.onAfterAppend = function(domnode, nodedata) {
				if (!g.treeManager) return;
				var value = null;
				if (p.initValue) {
					value = p.initValue;
				} else if (g.valueField.val() != "") value = g.valueField.val();
				g.selectValueByTree(value);
			};
			g.tree = $("<ul></ul>");
			$("div:first", g.selectBox).append(g.tree);
			g.tree.ligerTree(tree);
			g.treeManager = g.tree.ligerGetTreeManager();
			//todo by jyl 增加参数defaultExpand
			/*if(!p.treeExpand){
			 $(".l-expandable-open", g.tree).click();
			 $(".l-children", g.tree).hide();
			 }*/
		},
		selectValueByTree: function(value) {
			var g = this, p = this.options;
			if (value != null) {
				var text = "";
				var valuelist = value.toString().split(p.split);
				//todo modified by ttaomeng
				$(valuelist).each(function(i, item) {
					//                    g.treeManager.selectNode(item.toString());
					var temp = g.treeManager.getTextByID(item);
					text += temp == null ? "" : temp;
					if (i < valuelist.length - 1) text += p.split;
				});
				g.treeManager.selectNode(function(data) {
					if (!data["id"]) return false;
					for (var i in valuelist) { //todo 这里算法可以优化
						if (data["id"].toString() == valuelist[i]) return true;
					}
					return false;
				});
				g._changeValue(value, text);
			}
		},
		//表格
		setGrid: function(grid) {
			var g = this, p = this.options;
			this.clearContent();
			g.selectBox.table.remove();
			g.grid = $("div:first", g.selectBox);
			grid.columnWidth = grid.columnWidth || 120;
			grid.width = "100%";
			grid.height = "100%";
			grid.heightDiff = -2;
			grid.InWindow = false;
			g.gridManager = g.grid.ligerGrid(grid);
			p.hideOnLoseFocus = false;
			if (grid.checkbox != false) {
				var onCheckRow = function() {
					var rowsdata = g.gridManager.getCheckedRows();
					var value = [];
					var text = [];
					$(rowsdata).each(function(i, rowdata) {
						value.push(rowdata[p.valueField]);
						text.push(rowdata[p.textField]);
					});
					g._changeValue(value.join(p.split), text.join(p.split));
				};
				g.gridManager.bind('CheckAllRow', onCheckRow);
				g.gridManager.bind('CheckRow', onCheckRow);
			} else {
				g.gridManager.bind('SelectRow', function(rowdata, rowobj, index) {
					var value = rowdata[p.valueField];
					var text = rowdata[p.textField];
					g._changeValue(value, text);
				});
				g.gridManager.bind('UnSelectRow', function(rowdata, rowobj, index) {
					g._changeValue("", "");
				});
			}
			g.bind('show', function() {
				if (g.gridManager) {
					g.gridManager._updateFrozenWidth();
				}
			});
			g.bind('endResize', function() {
				if (g.gridManager) {
					g.gridManager._updateFrozenWidth();
					g.gridManager.setHeight(g.selectBox.height() - 2);
				}
			});
		},
		_getValue: function() {
			var g = this, p = this.options;
			//todo modified by ttaomeng   文本模式
			//return this.options.textModel ? g.inputText.val() : g.valueField.val();
			//return this.options.textModel?$(this.valueField).val():$(this.valueField).val();
			//var g = this, p = this.options;
			if (p.textModel || p.isTextBoxMode)
			{
				return g.inputText.val();
			}
			return $(this.valueField).val();
		},
		getValue: function() {
			//获取值
			return this._getValue();
		},
		updateStyle: function() {
			var g = this, p = this.options;
			g._dataInit();
		},
		_dataInit: function() {
			var g = this, p = this.options;
			var value = null;
			if (p.initValue != null && p.initText != null) {
				g._changeValue(p.initValue, p.initText); //todo modified by ttaomeng
				return;
			}
			//根据值来初始化
			if (p.initValue != null) {
				value = p.initValue;
				if (p.tree) {
					if (value) g.selectValueByTree(value);
				} else {
					var text = g.findTextByValue(value);
					g._changeValue(value, text);
				}
			}
			//根据文本来初始化
			else if (p.initText != null) {
				value = g.findValueByText(p.initText);
				g._changeValue(value, p.initText);
			} else if (g.valueField.val() != "") {
				value = g.valueField.val();
				if (p.tree) {
					if (value) g.selectValueByTree(value);
				} else {
					var text = g.findTextByValue(value);
					g._changeValue(value, text);
				}
			}
			if (!p.isShowCheckBox && value != null) {
				$("table tr", g.selectBox).find("td:first").each(function() {
					if (value == $(this).attr("value")) {
						$(this).addClass("l-selected");
					}
				});
			}
			if (p.isShowCheckBox && value != null) {
				$(":checkbox", g.selectBox).each(function() {
					var parentTD = null;
					var checkbox = $(this);
					if (checkbox.parent().get(0).tagName.toLowerCase() == "div") {
						parentTD = checkbox.parent().parent();
					} else {
						parentTD = checkbox.parent();
					}
					if (parentTD == null) return;
					var valuearr = value.toString().split(p.split);
					$(valuearr).each(function(i, item) {
						if (item == parentTD.attr("value")) {
							$(".l-checkbox", parentTD).addClass("l-checkbox-checked").parent().parent().parent().addClass("l-selected"); //todo 给多选择下拉增加选择效果 2013-5-11 下午2:29 lybide
							checkbox[0].checked = true;
						}
					});
				});
			}
		},
		//设置值到 文本框和隐藏域
		_changeValue: function(newValue, newText) {
			var g = this, p = this.options;
			g.valueField.val(newValue);
			ligerElementRequired(g.inputText, newValue, g, p);
			if (p.render) {
				g.inputText.val(p.render(newValue, newText));
			} else {
				g.inputText.val(newText);
			}
			g.selectedValue = newValue;
			g.selectedText = newText;
			g.inputText.trigger("change"); //.focus();//todo 激活input框 2012-8-6 下午1:36 lybide 删除
			g.trigger('selected', [newValue, newText]);
			g.trigger('change', [newValue, newText]); //todo 2015年8月14日 16:59:45 lybide 新增绑定onChange事件
		},
		//更新选中的值(复选框)
		_checkboxUpdateValue: function() {
			var g = this, p = this.options;
			var valueStr = [];
			var textStr = [];
			$("input:checked", g.selectBox).each(function() {
				var parentTD = null;
				if ($(this).parent().get(0).tagName.toLowerCase() == "div") {
					parentTD = $(this).parent().parent();
				} else {
					parentTD = $(this).parent();
				}
				if (!parentTD) return;
				//valueStr += parentTD.attr("value") + p.split;
				//textStr += parentTD.attr("text") + p.split;
				valueStr.push(parentTD.attr("value"));
				textStr.push(parentTD.attr("text"));
			});
			//if (valueStr.length > 0) valueStr = valueStr.substr(0, valueStr.length - 1);
			//if (textStr.length > 0) textStr = textStr.substr(0, textStr.length - 1);
			if (valueStr.length > 0) valueStr = valueStr.join(p.split); //todo 2013-5-11 下午2:36 lybide
			if (textStr.length > 0) textStr = textStr.join(p.split);
			g._changeValue(valueStr, textStr);
		},
		_addClickEven: function() {
			var g = this, p = this.options;
			//选项点击
			$(".l-table-nocheckbox td", g.selectBox).click(function() {
				var value = $(this).attr("value");
				var index = parseInt($(this).attr('index'));
				var text = $(this).html();
				if (g.hasBind('beforeSelect') && g.trigger('beforeSelect', [value, text]) == false) {
					if (p.slide) {
						g.selectBox.slideToggle("fast");
					} else {
						g.selectBox.hide();
					}
					return false;
				}
				if ($(this).hasClass("l-selected")) {
					if (p.slide) {
						g.selectBox.slideToggle("fast");
					} else {
						g.selectBox.hide();
					}
					return;
				}
				$(".l-selected", g.selectBox).removeClass("l-selected");
				$(this).addClass("l-selected");
				if (g.select) {
					if (g.select[0].selectedIndex != index) {
						g.select[0].selectedIndex = index;
						g.select.trigger("change");
					}
				}
				if (p.slide) {
					g.boxToggling = true;
					g.selectBox.hide("fast", function() {
						g.boxToggling = false;
					})
				} else {
					g.selectBox.hide();
				}
				$.ligerui.boxPanelIsShow = false;
				g._changeValue(value, text);
			});
		},
		updateSelectBoxPosition: function() {
			var g = this, p = this.options; //alert(g.wrapper.parent().attr("class"));
			if (p.absolute) {

				g.selectBox.css({
					left: g.wrapper.offset().left,
					top: g.wrapper.offset().top + 1 + g.wrapper.outerHeight()
				});
				//todo 2012-8-23 上午11:56 lybide
				if (!p.selectBoxWidth) {
					g.selectBox.css({
						width: g.wrapper.parent().width()
					});
				}
				if (!p.selectBoxHeight) {
					var oLen, oHeight;
					if (p.tree) {
						oHeight = 200;
					} else if (p.grid) {
						oHeight = 200;
					} else if (g.data) {
						oLen = g.data.length;
						var optionHeight = $(".l-box-select-table tr:first", g.selectBox).outerHeight(); //todo 2013-6-20 上午11:42 lybide
						//console.log(optionHeight);
						oHeight = oLen * optionHeight;
						//todo 2014-7-23 15:30 lybide 用于计算当前下拉层的高度，但必须显示下拉层，再认其隐藏
						g.selectBox.show();
						oHeight = $(".l-box-select-table", g.selectBox).outerHeight();
						g.selectBox.hide();
					}
					var ObjHeight = g.wrapper.outerHeight();
					var bodyHeight = $(document.body).outerHeight(true) < $(window).height() ? $(window).height() : $(document.body).outerHeight(true);
					var ObjTop = g.wrapper.offset().top;
					var ObjBottom = bodyHeight - ObjTop - ObjHeight;
					//alert([bodyHeight,oHeight,ObjTop,ObjBottom]);
					//alert([ObjTop+ObjHeight+oHeight,bodyHeight]);
					//todo 2012-8-23 下午4:12 lybide
					if (ObjTop > ObjBottom && oHeight > ObjBottom) { //上方
						var topPx = 0;
						if (oHeight > ObjTop) { //alert("有滚动条");
							oHeight = ObjTop - 20;
							$(".l-box-select-inner", g.selectBox).css("overflow", "auto");
						} else { //alert("无滚动条");
						}
						topPx = ObjTop - oHeight - 5;
						g.selectBox.css({
							top: topPx
						});
					} else { //下方
						if (ObjTop + ObjHeight + oHeight > bodyHeight) { //alert("有滚动条");
							oHeight = ObjBottom - 20;
							$(".l-box-select-inner", g.selectBox).css("overflow", "auto");
						} else { //alert("无滚动条");
						}
					}
					g.selectBox.css({
						height: oHeight
					});

				}
			} else {
				var topheight = g.wrapper.offset().top - $(window).scrollTop();
				var selfheight = g.selectBox.height() + textHeight + 4;
				if (topheight + selfheight > $(window).height() && topheight > selfheight) {
					g.selectBox.css("marginTop", -1 * (g.selectBox.height() + textHeight + 5));
				}
			}
			if (p.tree) { //todo 2013-6-21 下午6:04 lybide
				$(".l-box-select-inner", g.selectBox).css("overflow", "auto");
			}
		},
		//显示&隐藏下拉框
		_toggleSelectBox: function(isHide) {
			var g = this, p = this.options;
			//if (!p.tree && !p.url && (!g.data || !g.data.length)) return;//todo 2013-6-5 下午1:38 lybide
			//if (p.tree && (!p.tree.data || !p.tree.data.length)) {return;}//todo 2014年04月18日 13:07:31 星期五 lybide
			if (g.data && g.data.length == 0) return; //todo 2014年07月11日 15:58:48 星期五 lybide
			if (!p.tree && !p.url && !g.data) return; //todo 2013-6-5 下午1:38 lybide
			//必须显示无数据，做到这里了。
			var textHeight = g.wrapper.height();

			g.boxToggling = true; //console.log(isHide);
			$.ligerui.boxPanelIsShow = true;
			//$(".l-box-select-inner", g.selectBox).css("overflow", "hidden");
			if (isHide) { //隐藏
				if (p.slide) { //是否有动画
					g.selectBox.slideToggle('fast', function() {
						g.boxToggling = false;
						$.ligerui.boxPanelIsShow = false;
					});
				} else {
					g.selectBox.hide();
					g.boxToggling = false;
					$.ligerui.boxPanelIsShow = false;
				}
			} else { //显示
				g.updateSelectBoxPosition(); //alert(p.slide);
				if (p.slide) { //是否有动画
					g.selectBox.slideToggle('fast', function() {
						g.boxToggling = false;
						$.ligerui.boxPanelIsShow = true;
						if (!p.isShowCheckBox && $('td.l-selected', g.selectBox).length > 0) {
							var offSet = ($('td.l-selected', g.selectBox).offset().top - g.selectBox.offset().top);
							$(".l-box-select-inner", g.selectBox).animate({
								scrollTop: offSet
							}, function() {
								//$(this).css("overflow","auto");
							});
						}
					});
				} else {
					g.selectBox.show();
					g.boxToggling = false;
					$.ligerui.boxPanelIsShow = true;
					if (!g.tree && !g.grid && !p.isShowCheckBox && $('td.l-selected', g.selectBox).length > 0) {
						var offSet = ($('td.l-selected', g.selectBox).offset().top - g.selectBox.offset().top);
						$(".l-box-select-inner", g.selectBox).animate({
							scrollTop: offSet
						}, function() {
							//$(this).css("overflow","auto");
						});
					}
				}
			}
			g.isShowed = g.selectBox.is(":visible");
			g.trigger('toggle', [isHide]);
			g.trigger(isHide ? 'hide' : 'show');
		}
	});

	$.ligerui.controls.ComboBox.prototype.setValue = $.ligerui.controls.ComboBox.prototype.selectValue;
	//设置文本框和隐藏控件的值
	$.ligerui.controls.ComboBox.prototype.setInputValue = $.ligerui.controls.ComboBox.prototype._changeValue;
})(jQuery);