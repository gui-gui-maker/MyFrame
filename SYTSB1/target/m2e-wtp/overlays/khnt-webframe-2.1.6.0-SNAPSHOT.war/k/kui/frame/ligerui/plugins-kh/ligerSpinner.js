/**
 * jQuery ligerUI 1.1.9 ligerSpinner
 *
 * http://ligerui.com
 *
 * Author daomi 2012 [ gd_star@163.com ]
 *
 */
(function($) {
	$.fn.ligerSpinner = function() {
		return $.ligerui.run.call(this, "ligerSpinner", arguments);
	};
	$.fn.ligerGetSpinnerManager = function() {
		return $.ligerui.run.call(this, "ligerGetSpinnerManager", arguments);
	};

	$.ligerDefaults.Spinner = {
		type: 'int',
		//类型 float:浮点数 int:整数 time:时间 todo lybide改默认值为int
		isNegative: true,
		//是否负数
		decimalplace: 2,
		//小数位 type=float时起作用
		step: 0.1,
		//每次增加的值
		interval: 50,
		//间隔，毫秒
		onChangeValue: false,
		//改变值事件
		minValue: null,
		//最小值
		maxValue: null,
		//最大值
		disBlank: true,
		//是否允许返回值为空值。true 允许返回值为空，默认；false 空返回值为数字0
		disabled: false
	};

	$.ligerMethos.Spinner = {};

	$.ligerui.controls.Spinner = function(element, options) {
		$.ligerui.controls.Spinner.base.constructor.call(this, element, options);
	};
	$.ligerui.controls.Spinner.ligerExtend($.ligerui.controls.Input, {
		__getType: function() {
			return 'Spinner';
		},
		__idPrev: function() {
			return 'Spinner';
		},
		_extendMethods: function() {
			return $.ligerMethos.Spinner;
		},
		_init: function() {
			$.ligerui.controls.Spinner.base._init.call(this);
			var p = this.options;
			if (p.type == 'float') {
				//p.step = 0.1;//todo lybide 删除
				p.interval = 50;
			} else if (p.type == 'int') {
				p.step = 1;
				p.interval = 100;
			} else if (p.type == 'time') {
				p.step = 1;
				p.interval = 100;
			}
			if ($(this.element).attr("readonly") || p.readonly) {
				p.readonly = true;
			}
		},
		_render: function() {
			var g = this,
				p = this.options;
			g.interval = null;
			g.inputText = null;
			g.value = null;
			g.textFieldID = "";
			if (this.element.tagName.toLowerCase() == "input" && this.element.type && this.element.type == "text") {
				g.inputText = $(this.element);
				if (this.element.id) g.textFieldID = this.element.id;
			} else {
				g.inputText = $('<input type="text"/>');
				g.inputText.appendTo($(this.element));
			}
			if (g.textFieldID == "" && p.textFieldID) g.textFieldID = p.textFieldID;

			g.link = $('<div class="l-trigger"><div class="l-spinner-up"><div class="l-spinner-icon"></div></div><div class="l-spinner-split"></div><div class="l-spinner-down"><div class="l-spinner-icon"></div></div></div>');
			if (p.explain) { //todo 添加自定义说明 13-1-17 下午1:30 lybide
				g.inputText.parent().append('<div class="l-text-explain"><div class="l-text-explain-triangle"></div><div class="l-text-explain-div">' + p.explain + '</div></div>');
			}
			//todo modified by ttaomeng
			g.wrapper = g.inputText.wrap('<div class="l-text"></div>').parent();
			g.wrapper.append('<div class="l-text-l"></div><div class="l-text-r"></div>');
			g.wrapper.append(g.link).after(g.selectBox).after(g.valueField);
			//todo add by ttaomeng
			if (p.suffix) {
				g.textwrapper = g.wrapper.wrap("<table border='0' width='" + (p.width ? p.width : "100%") + "' cellspacing='0' cellpadding='0' class='l-text-suffix-wrap'><tr><td class='l-text-left'><div class='l-text-wrapper'></div></td><td class='l-text-suffix' " + (p.suffixWidth ? "style='width:" + p.suffixWidth + "px'" : "") + ">" + p.suffix + "</td></tr></table>").parent();
			} else { //todo 添加个包裹 13-1-11 下午3:29 lybide
				g.textwrapper = g.wrapper.wrap('<div class="l-text-wrapper"></div>').parent();
			}
			g.link.up = $(".l-spinner-up", g.link);
			g.link.down = $(".l-spinner-down", g.link);
			g.inputText.addClass("l-text-field");
			g.inputText.wrap('<div class="l-text-sdi"></div>'); //todo 再添加一个包裹，以支持显示 2013-5-10 下午5:38 lybide
			//todo 2013-5-29 下午9:40 lybide
			if (p.readonlyText) {
				g.inputText.attr("readonly", true);
			}

			if (p.disabled) {
				g.wrapper.addClass("l-text-disabled");

				// 2015-06-12 下午15:11 zhp
				g.inputText.attr("readonly", true);
			}
			//初始化
			if (!g._isVerify(g.inputText.val())) {
				g.value = g._getDefaultValue();
				g.inputText.val(g.value);
			}
			//todo 2013-6-5 下午3:36 lybide
			p.initValue = p.initValue || g.inputText.val();
			if (p.initValue || p.value) {
				g.setValue(p.initValue || p.value);
			}
			//事件
			g.link.up.hover(function() {
				if (!p.disabled) $(this).addClass("l-spinner-up-over");
			}, function() {
				clearInterval(g.interval);
				$(document).unbind("selectstart.spinner");
				$(this).removeClass("l-spinner-up-over");
			}).mousedown(function() {
				if (!p.disabled) {
					g._uping.call(g);
					g.interval = setInterval(function() {
						g._uping.call(g);
					}, p.interval);
					$(document).bind("selectstart.spinner", function() {
						return false;
					});
				}
			}).mouseup(function() {
				clearInterval(g.interval);
				g.inputText.trigger("change").focus();
				$(document).unbind("selectstart.spinner");
			});
			g.link.down.hover(function() {
				if (!p.disabled) $(this).addClass("l-spinner-down-over");
			}, function() {
				clearInterval(g.interval);
				$(document).unbind("selectstart.spinner");
				$(this).removeClass("l-spinner-down-over");
			}).mousedown(function() {
				if (!p.disabled) {
					g.interval = setInterval(function() {
						g._downing.call(g);
					}, p.interval);
					$(document).bind("selectstart.spinner", function() {
						return false;
					});
				}
			}).mouseup(function() {
				clearInterval(g.interval);
				g.inputText.trigger("change").focus();
				$(document).unbind("selectstart.spinner");
			});

			g.inputText.change(function() {
				var value = g.inputText.val();
				if (!value) {
					return;
				} //todo 2013-6-18 上午11:29 lybide 调整器可为空值
				g.value = g._getVerifyValue(value);
				g.trigger('changeValue', [g.value]);
				g.trigger('change', [g.value]); //todo 2015年8月14日 16:59:45 lybide 新增绑定onChange事件
				g.inputText.val(g.value);
			}).blur(function() {
				g.wrapper.removeClass("l-text-focus");
			}).focus(function() {
				g.wrapper.addClass("l-text-focus");
			});
			g.wrapper.hover(function() {
				if (!p.disabled) g.wrapper.addClass("l-text-over");
			}, function() {
				g.wrapper.removeClass("l-text-over");
			});
			g.set(p);
			//g.link.css("top",(g.textwrapper.height()-g.link.height())/2-2);//todo 计算小图标top 13-1-22 上午9:42 lybide
		},
		_setWidth: function(value) {
			var g = this;
			if (isNaN(value)) {
				g.textwrapper.css({
					width: value
				});
			} else if (value > 20) {
				//g.wrapper.css({ width: value });//todo lybide 删除调整器宽度
				//g.inputText.css({ width: value - 20 });
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
			}
		},
		_setDisabled: function(value) {
			if (value) {
				this.wrapper.addClass("l-text-disabled");
			} else {
				this.wrapper.removeClass("l-text-disabled");
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
		setValue: function(value) {
			var g = this,
				p = this.options;
			g.inputText.val(g._getVerifyValue(value));
			g.trigger('change', [g.value]);
			ligerElementRequired($(g.inputText), value, g, p);
		},
		getValue: function() {
			return this.inputText.val();
		},
		_round: function(v, e) {
			var g = this,
				p = this.options;
			var t = 1;
			if (e > 0) {
				return new Number(v).toFixed(e);
			}
			for (; e > 0; t *= 10, e--);
			for (; e < 0; t /= 10, e++);
			return Math.round(v * t) / t;
		},
		_isInt: function(str) {
			var g = this,
				p = this.options;
			var strP = p.isNegative ? /^-?\d+$/ : /^\d+$/;
			if (!strP.test(str)) return false;
			if (parseFloat(str) != str) return false;
			return true;
		},
		_isFloat: function(str) {
			var g = this,
				p = this.options;
			var strP = p.isNegative ? /^-?\d+(\.\d+)?$/ : /^\d+(\.\d+)?$/;
			if (!strP.test(str)) return false;
			if (parseFloat(str) != str) return false;
			return true;
		},
		_isTime: function(str) {
			var g = this,
				p = this.options;
			var a = str.match(/^(\d{1,2}):(\d{1,2})$/);
			if (a == null) return false;
			if (a[1] > 24 || a[2] > 60) return false;
			return true;

		},
		_isVerify: function(str) {
			var g = this,
				p = this.options;
			if (p.type == 'float') {
				if (!g._isFloat(str)) return false;
				var value = parseFloat(str);
				if (p.minValue != undefined && p.minValue > value) return false;
				if (p.maxValue != undefined && p.maxValue < value) return false;
				return true;
			} else if (p.type == 'int') {
				if (!g._isInt(str)) return false;
				var value = parseInt(str);
				if (p.minValue != undefined && p.minValue > value) return false;
				if (p.maxValue != undefined && p.maxValue < value) return false;
				return true;
			} else if (p.type == 'time') {
				return g._isTime(str);
			}
			return false;
		},
		_getVerifyValue: function(value) {
			var g = this,
				p = this.options;
			var newvalue = null;
			if (p.type == 'float') {
				newvalue = g._round(value, p.decimalplace);
			} else if (p.type == 'int') {
				newvalue = parseInt(value);
			} else if (p.type == 'time') {
				newvalue = value;
			}
			if (!g._isVerify(newvalue)) {
				return g.value;
			} else {
				return newvalue;
			}
		},
		_isOverValue: function(value) {
			var g = this,
				p = this.options;
			if (p.minValue != null && p.minValue > value) return true;
			if (p.maxValue != null && p.maxValue < value) return true;
			return false;
		},
		_getDefaultValue: function() {
			var g = this,
				p = this.options;
			if (p.type == 'float' || p.type == 'int') {
				return "";
			} else if (p.type == 'time') {
				return "00:00";
			}
		},
		_addValue: function(num) {
			var g = this,
				p = this.options;
			var value = g.inputText.val() || 0;
			value = parseFloat(value) + num;
			if (g._isOverValue(value)) return;
			g.inputText.val(value);
			g.inputText.trigger("change");
		},
		_addTime: function(minute) {
			var g = this,
				p = this.options;
			var value = g.inputText.val();
			var a = value.match(/^(\d{1,2}):(\d{1,2})$/);
			newminute = parseInt(a[2]) + minute;
			if (newminute < 10) newminute = "0" + newminute;
			value = a[1] + ":" + newminute;
			if (g._isOverValue(value)) return;
			g.inputText.val(value);
			g.inputText.trigger("change");
		},
		_uping: function() {
			var g = this,
				p = this.options;
			if (p.type == 'float' || p.type == 'int') {
				g._addValue(p.step);
			} else if (p.type == 'time') {
				g._addTime(p.step);
			}
		},
		_downing: function() {
			var g = this,
				p = this.options;
			if (p.type == 'float' || p.type == 'int') {
				g._addValue(-1 * p.step);
			} else if (p.type == 'time') {
				g._addTime(-1 * p.step);
			}
		},
		_isDateTime: function(dateStr) {
			var g = this,
				p = this.options;
			var r = dateStr.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);
			if (r == null) return false;
			var d = new Date(r[1], r[3] - 1, r[4]);
			if (d == "NaN") return false;
			return (d.getFullYear() == r[1] && (d.getMonth() + 1) == r[3] && d.getDate() == r[4]);
		},
		_isLongDateTime: function(dateStr) {
			var g = this,
				p = this.options;
			var reg = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2})$/;
			var r = dateStr.match(reg);
			if (r == null) return false;
			var d = new Date(r[1], r[3] - 1, r[4], r[5], r[6]);
			if (d == "NaN") return false;
			return (d.getFullYear() == r[1] && (d.getMonth() + 1) == r[3] && d.getDate() == r[4] && d.getHours() == r[5] && d.getMinutes() == r[6]);
		}
	});
})(jQuery);