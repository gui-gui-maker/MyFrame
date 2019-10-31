/**
 * jQuery ligerUI 1.1.9 ligerTextBox
 *
 * http://ligerui.com
 *
 * Author daomi 2012 [ gd_star@163.com ]
 *
 */
(function($) {
	$.fn.ligerTextBox = function() {
		return $.ligerui.run.call(this, "ligerTextBox", arguments);
	};

	$.fn.ligerGetTextBoxManager = function() {
		return $.ligerui.run.call(this, "ligerGetTextBoxManager", arguments);
	};

	$.ligerDefaults.TextBox = {
		onChangeValue: null,
		width: null,
		disabled: false,
		//value: null,     //初始化值
		nullText: null,
		//不能为空时的提示
		digits: false,
		//是否限定为数字输入框
		number: false //是否限定为浮点数格式输入框
	};

	$.ligerui.controls.TextBox = function(element, options) {
		$.ligerui.controls.TextBox.base.constructor.call(this, element, options);
	};

	$.ligerui.controls.TextBox.ligerExtend($.ligerui.controls.Input, {
		__getType: function() {
			return 'TextBox'
		},
		__idPrev: function() {
			return 'TextBox';
		},
		_init: function() {
			$.ligerui.controls.TextBox.base._init.call(this);
			var g = this,
				p = this.options;
			if (!p.width) {
				//todo 2012-7-25 11:20 lybide 根据新css规则，不需要设置宽度。
				//p.width = $(g.element).width();
			}
			if ($(this.element).attr("readonly") || p.readonly) {
				p.readonly = true;
			}
		},
		_render: function() {
			var g = this,
				p = this.options;
			g.inputText = $(this.element);
			if (p.explain) { //todo 添加自定义说明 13-1-17 下午1:30 lybide
				g.inputText.parent().append('<div class="l-text-explain"><div class="l-text-explain-triangle"></div><div class="l-text-explain-div">' + p.explain + '</div></div>');
			}
			//外层
			g.wrapper = g.inputText.wrap('<div class="l-text"></div>').parent();
			g.wrapper.append('<div class="l-text-l"></div><div class="l-text-r"></div>');
			//todo 文本框可有小图标。2013-5-20 下午5:07 lybide
			/*if (p.iconCls) {
			 g.link = $('<div class="l-trigger"><div class="l-trigger-icon '+ (p.iconCls.indexOf("l-icon")>-1?p.iconCls:'l-icon-'+p.iconCls)+'"></div></div>');
			 g.wrapper.append(g.link);
			 g.inputText.wrap('<div class="l-text-sdi"></div>');
			 g.link.bind("click",function(e){
			 if (p.disabled) {return;}
			 p.iconClick.call(g,g.getValue(),g);
			 })
			 }*/
			//todo 文本框可有套图标。2013-5-20 下午5:47 lybide
			if (p.iconItems) {
				var w1 = g.inputText.wrap('<div class="l-text-sdi"></div>').parent();
				g.wrapper.addClass("l-text-has-icon-item");//2017年06月15日 13:02:03 星期四 lybide
				var twidth = 0;
				var tr = twidth;
				var gLinkFirstWidth=-1;//g.link.width();//2017年06月15日 12:56:28 星期四 lybide
				$.each(p.iconItems, function(i, item) {
					var gLink;
					if (item["img"]) {
						gLink = $('<div class="l-trigger l-trigger-custom"><div class="l-trigger-box iconfont l-icon" style="background:url(' + item["img"] + ') no-repeat center center;"></div></div>');
					} else if (item["icon"]) {
						gLink = $('<div class="l-trigger l-trigger-custom"><div class="l-trigger-box iconfont l-icon ' + (item["icon"].indexOf("l-icon") > -1 ? item["icon"] : 'l-icon-' + item["icon"]) + '"></div></div>');
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
			//todo add by ttaomeng
			if (p.suffix) {
				g.textwrapper = g.wrapper.wrap("<table border='0' width='" + (p.width ? p.width : "100%") + "' cellspacing='0' cellpadding='0' class='l-text-suffix-wrap'><tr><td class='l-text-left'><div class='l-text-wrapper'></div></td><td class='l-text-suffix' " + (p.suffixWidth ? "style='width:" + p.suffixWidth + "px'" : "") + ">" + p.suffix + "</td></tr></table>").parents(".l-text-suffix-wrap");
			} else {
				g.textwrapper = g.wrapper.wrap('<div class="l-text-wrapper"></div>').parent(); //todo 2013-5-20 上午11:01 lybide 再添加包裹
			}
			if (!g.inputText.hasClass("l-text-field")) {
				g.inputText.addClass("l-text-field");
			}
			//todo 输入框自动下拉框 13-2-27 下午1:27 lybide
			if (p.autocomplete) {
				//alert(JSON.stringify(p.autocomplete.option));
				var a = $(g.inputText).autocomplete(p.autocomplete.data || p.autocomplete.url, p.autocomplete.option);
				if (p.autocomplete.result) {
					a.result(p.autocomplete.result);
				}
			}
			//todo 初始值 2014年07月04日 12:08:18 星期五 lybide
			if (p.initValue || p.value) {
				g.setValue(p.initValue || p.value);
			}
			//todo 2013-5-29 下午9:40 lybide
			this._setEvent();
			g.set(p);
			g.checkValue();
		},
		_getValue: function() { //todo 20150407 modified by ttaomeng
			var g = this,
				p = this.options;
			if (p.nullText) {
				if (g.inputText.val() == p.nullText) {
					return "";
				}
			}
			return this.inputText.val();
		},
		_setNullText: function() {
			this.checkNotNull();
		},
		checkValue: function() {
			var g = this,
				p = this.options;
			var v = g.inputText.val();
			if (p.number && !/^-?(?:\d+|\d{1,3}(?:,\d{3})+)(?:\.\d+)?$/.test(v) || p.digits && !/^\d+$/.test(v)) {
				g.inputText.val(g.value || 0);
				return;
			}
			g.value = v;
		},
		checkNotNull: function() {
			var g = this,
				p = this.options;
			if (p.nullText && !p.disabled) {
				if (!g.inputText.val()) {
					//todo 解决如存在 nullText，验证validate时，没有进行验证的问题 2016年5月3日 14:42:12 lybide
					//添加或删除自定义属性 nullText
					g.inputText.addClass("l-text-field-null").val(p.nullText).attr("nullText",p.nullText);
				}
			}
		},
		_setEvent: function() {
			var g = this,
				p = this.options;
			g.inputText.bind('blur.textBox', function() {
				g.trigger('blur');
				g.checkNotNull();
				g.checkValue();
				g.wrapper.removeClass("l-text-focus");
			}).bind('focus.textBox', function() {
				g.trigger('focus');
				if (p.nullText) {
					if ($(this).hasClass("l-text-field-null")) {
						$(this).removeClass("l-text-field-null").val("").removeAttr("nullText");
					}
				}
				g.wrapper.addClass("l-text-focus");
			}).change(function() {
				g.trigger('changeValue', [this.value]);
				g.trigger('change', [this.value]); //todo 2015年8月14日 16:59:45 lybide 新增绑定onChange事件
			});
			g.wrapper.hover(function() {
				g.trigger('mouseOver');
				g.wrapper.addClass("l-text-over");
			}, function() {
				g.trigger('mouseOut');
				g.wrapper.removeClass("l-text-over");
			});
		},
		_setDisabled: function(value) {
			var g = this,
				p = this.options;
			if (value) {
				g.inputText.attr("readonly", "readonly");
				g.wrapper.addClass("l-text-disabled");
			} else {
				g.inputText.removeAttr("readonly");
				g.wrapper.removeClass('l-text-disabled');
			}
		},
		//todo 2013-5-29 下午10:20 lybide
		_setReadonly: function(value) {
			var g = this,
				p = this.options;
			if (value) {
				g.inputText.attr("readonly", "readonly");
				g.wrapper.addClass("l-text-readonly");
			} else {
				g.inputText.removeAttr("readonly");
				g.wrapper.removeClass('l-text-readonly');
			}
		},
		_setWidth: function(value) {
			var g = this,
				p = this.options;
			if (isNaN(value)) {
				g.wrapper.css({
					width: value
				});
			} else if (value > 20) {
				//g.wrapper.css({ width: value });
				//g.inputText.css({ width: value - 4 });//todo lybide 删除文本框宽度
				g.textwrapper.css({
					width: value
				})
			}
		},
		_setHeight: function(value) {
			var g = this,
				p = this.options;
			if (value > 10) {
				g.wrapper.height(value);
				g.inputText.height(value - 2);
			}
		},
		_setValue: function(value) {
			var g = this,
				p = this.options;
			var ti = g.inputText;

			if (value != null) {
				ti.val(value);
				//todo 解决在页面编辑状态回显值时，nullText属性input框显示不正常的问题 2016年5月4日 10:18:03 lybide
				if (p.nullText) {
					//if ($(this).hasClass("l-text-field-null")) {
						ti.removeClass("l-text-field-null").removeAttr("nullText");
					//}
				}
			}
			ligerElementRequired(ti, value, g, p);
		},
		_setLabel: function(value) {
			var g = this,
				p = this.options;
			if (!g.labelwrapper) {
				g.labelwrapper = g.wrapper.wrap('<div class="l-labeltext"></div>').parent();
				var lable = $('<div class="l-text-label" style="float:left;">' + value + ':&nbsp</div>');
				g.labelwrapper.prepend(lable);
				g.wrapper.css('float', 'left');
				if (!p.labelWidth) {
					p.labelWidth = lable.width();
				} else {
					g._setLabelWidth(p.labelWidth);
				}
				lable.height(g.wrapper.height());
				if (p.labelAlign) {
					g._setLabelAlign(p.labelAlign);
				}
				g.labelwrapper.append('<br style="clear:both;" />');
				g.labelwrapper.width(p.labelWidth + p.width + 2);
			} else {
				g.labelwrapper.find(".l-text-label").html(value + ':&nbsp');
			}
		},
		_setLabelWidth: function(value) {
			var g = this,
				p = this.options;
			if (!g.labelwrapper) return;
			g.labelwrapper.find(".l-text-label").width(value);
		},
		_setLabelAlign: function(value) {
			var g = this,
				p = this.options;
			if (!g.labelwrapper) return;
			g.labelwrapper.find(".l-text-label").css('text-align', value);
		},
		updateStyle: function() {
			var g = this,
				p = this.options;
			if (g.inputText.attr('disabled') || g.inputText.attr('readonly')) {
				g.wrapper.addClass("l-text-disabled");
				g.options.disabled = true;
			} else {
				g.wrapper.removeClass("l-text-disabled");
				g.options.disabled = false;
			}
			if (g.inputText.hasClass("l-text-field-null") && g.inputText.val() != p.nullText) {
				g.inputText.removeClass("l-text-field-null").removeAttr("nullText");
			}
			g.checkValue();
		}
	});
})(jQuery);