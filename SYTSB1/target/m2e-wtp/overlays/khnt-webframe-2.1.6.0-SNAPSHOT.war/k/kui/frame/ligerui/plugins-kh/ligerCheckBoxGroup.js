/**
 * add by ttaomeng ligerCheckBoxGroup
 *
 */
(function($) {

	$.fn.ligerCheckBoxGroup = function() {
		return $.ligerui.run.call(this, "ligerCheckBoxGroup", arguments);
	};

	$.fn.ligerCheckBoxGroupManager = function() {
		return $.ligerui.run.call(this, "ligerGetCheckBoxGroupManager", arguments);
	};

	$.ligerDefaults.CheckBoxGroup = {
		disabled: false,
		lineWrap: true,
		valueField: 'id',
		textField: 'text',
		split: typeof KH__valueSplit == "undefined" ? "," : KH__valueSplit
	};

	$.ligerMethos.CheckBoxGroup = {};

	$.ligerui.controls.CheckBoxGroup = function(element, options) {
		$.ligerui.controls.CheckBoxGroup.base.constructor.call(this, element, options);
	};
	$.ligerui.controls.CheckBoxGroup.ligerExtend($.ligerui.controls.Input, {
		__getType: function() {
			return 'CheckBoxGroup';
		},
		__idPrev: function() {
			return 'CheckBoxGroup';
		},
		_extendMethods: function() {
			return $.ligerMethos.CheckBoxGroup;
		},
		_render: function() {
			var g = this,
				p = this.options;
			g.input = $(g.element);
			g.wrapper = g.input.wrap('<div class="l-checkbox-group-wrapper" id="'+g.input.attr("name")+'-group"></div>').parent();
			if (p.data) {
				g._parseData(p.data);
			} else if (p.url) {
				$.ajax({
					async: false,
					dataType: 'json',
					url: p.url,
					success: function(resData) {
						g._parseData(resData);
					}
				});
			}
			g.set(p);
		},
		_parseData: function(data) {
			var g = this,
				p = this.options;
			p.data = data;
			$.each(data, function(i, d) {
				var id = $.ligerui.getId();
				var ele;
				if (i == 0) {
					ele = g.input;
					if (g.element.id) {
						id = g.element.id;
					} else {
						ele.attr("id", id);
					}
					ele.addClass("l-checkbox-group");
					ele.removeClass("requiredstar");
					ele.val(d["id"]);
				} else {
					ele = $('<input type="checkbox" id="' + id + '" name="' + g.input.attr("name") + '" value="' + d["id"] + '" ltype="checkboxGroup" groupChild=true ligeruiid="' + g.id + '"">');
					g.wrapper.append(ele);
				}
				ele.bind("change", function() { //g.getValue(),
					if (p.onChange) {
						p.onChange.call(g, g.getValue())
					}
				});
				//2013-9-10 下午5:35 lybide 加外层包裹
				var wrap = ele.wrap('<div class="cgbox-item"></div>').parent();
				var lable=$('<label for="' + id + '">' + d["text"] + '</label>');
				wrap.append(lable);
				if (d["title"]) {//2017年06月20日 12:47:27 星期二 lybide 添加
					lable.attr("title",d["title"]);
				}
				if (!p.lineWrap) {
					wrap.css({
						"clear": "both",
						"display": "block"
					});
				}
				/*if(!p.lineWrap){
					 ele.after('<label for="'+id+'">'+d["text"]+'</label><br>');
					 //                    ele.wrap("<div class='l-checkbox-group-lineWrap'></div>")
					 }else{
					 ele.after('<label for="'+id+'">'+d["text"]+'</label>');
					 }*/
			});
			if (p.initValue || p.value) {
				g.setValue(p.initValue || p.value);
			}
			if (p.onSuccess) {
				p.onSuccess.call(this, data, p.initValue || p.value);
			}
		},
		setValue: function(value) {
			var g = this,
				p = this.options;
			var values = value.split(p.split);
			var checkeds = 0;
			var gg = g.getCheckBoxGroup();
			gg.each(function() {
				if ($.inArray(this.value, values) == -1) {
					this.checked = false;
				} else {
					this.checked = true;
					checkeds++;
				}
			});
			//2013-5-27 下午9:43 lybide
			if (checkeds > 0) {
				ligerElementRequired(gg.first().parent(), value, g, p);
			} else {
				ligerElementRequired(gg.first().parent(), "", g, p);
			}
			if (p.onChange) {
				p.onChange.call(g, g.getValue())
			}
		},
		findTextByValue: function(value) {
			var g = this,
				p = this.options;
			if (value == undefined) return "";
			var texts = "";
			var contain = function(checkvalue) {
					var targetdata = value.toString().split(p.split);
					for (var i = 0; i < targetdata.length; i++) {
						if (targetdata[i] == checkvalue) return true;
					}
					return false;
				};
			$(p.data).each(function(i, item) {
				var val = item[p.valueField];
				var txt = item[p.textField];
				if (contain(val)) {
					texts += txt + p.split;
				}
			});
			if (texts.length > 0) texts = texts.substr(0, texts.length - 1);
			return texts;
		},
		getValue: function() {
			var g = this,
				p = this.options;
			var str = [];
			var gg = g.getCheckBoxGroup();
			gg.each(function() {
				if (this.checked) {
					str.push(this.value);
				}
			});
			return str.join(p.split);
		},
		getCheckBoxGroup: function() {
			var g = this,
				p = this.options;
			var formEle;
			if (g.input[0].form) formEle = g.input[0].form;
			else formEle = g.wrapper; //document;
			return $("input:checkbox[name='" + g.input[0].name + "']", formEle);
		},
		_setDisabled: function(value) {
			if (value) {
				this.getCheckBoxGroup().attr('disabled', true);
				this.options.disabled = true;
			} else {
				this.getCheckBoxGroup().attr('disabled', false);
				this.options.disabled = false;
			}
		}
	});
})(jQuery);