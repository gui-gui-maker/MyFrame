/**
 * jQuery ligerUI 1.3.2 ligerButton
 *
 * http://ligerui.com
 *
 * Author daomi 2015 [ gd_star@163.com ]
 *
 */
(function($) {

	$.fn.ligerButton = function(options) {
		return $.ligerui.run.call(this, "ligerButton", arguments);
	};
	$.fn.ligerGetButtonManager = function() {
		return $.ligerui.run.call(this, "ligerGetButtonManager", arguments);
	};

	$.ligerDefaults.Button = {
		width: "auto",//默认为：60。改成：auto。因无需要对按钮进行宽度定义 2017年03月09日 17:53:54 星期四 lybide
		text: '',//默认没有按钮文字。Button 2017年06月09日 17:56:23 星期五 lybide
		disabled: false,
		click: null,
		icon: null
	};

	$.ligerMethos.Button = {};

	$.ligerui.controls.Button = function(element, options) {
		$.ligerui.controls.Button.base.constructor.call(this, element, options);
	};
	$.ligerui.controls.Button.ligerExtend($.ligerui.controls.Input, {
		//todo 2016年11月18日 11:15:45 星期五 lybide
		icon: "",
		scale: 'small',
		allowedScales: ['small', 'medium', 'large'],
		iconAlign: 'left',
		arrowAlign: 'right',
		arrowCls: 'arrow',
		pressedCls: 'pressed',
		overCls: 'over',
		focusCls: 'focus',
		items: null,
		__getType: function() {
			return 'Button';
		},
		__idPrev: function() {
			return 'Button';
		},
		_extendMethods: function() {
			return $.ligerMethos.Button;
		},
		_rendered: function() { //todo 13-4-15 下午7:46 lybide
			var g = this,
				p = this.options;
			if (this.element && !p.items) {
				$(this.element).attr("ligeruiid", this.id);
			}
		},
		/*_render: function() { //todo 2016年11月18日 11:23:59 星期五 lybide 关闭原版
			var g = this, p = this.options;
			g.button = $(g.element);
			g.button.addClass("l-button");
			g.button.append('<div class="l-button-l"></div><div class="l-button-r"></div><span></span>');
			g.button.hover(function() {
				if (p.disabled) return;
				g.button.addClass("l-button-over");
			}, function() {
				if (p.disabled) return;
				g.button.removeClass("l-button-over");
			});
			p.click && g.button.click(function() {
				if (!p.disabled) p.click();
			});
			g.set(p);
		},*/
		_render: function() {
			var g = this,
				p = this.options;
			g.button = $(g.element);

			if (p.items) {
				//todo 13-4-7 下午4:50 lybide 新增可初始化多个按钮
				//var s=g.button.html();alert(s+"============创建button了");
				//g.button.html("");
				$("a[ligeruiid]", g.button).each(function(item) { //todo 13-4-16 上午11:05 lybide
					var $this = $(this);
					$.ligerui.get($this).destroy();
				});
				g.button.html("");
				p.items && $(p.items).each(function(i, item) {
					var p = item; //todo 13-4-13 下午4:04 lybide
					if (p == "-") {
						var l = $('<a class="l-button-split"></a>');
						g.button.append(l); //return;
					} else {
						//g.buttonGroup(item);
						//item=$.extend({place:"child"}, item);
						//g.addButtonItem(item);
						//alert(g.button.attr("id"))
						//$('<button id="'+item.id+'" class="l-button-warp l-button"></button>').appendTo(g.button).ligerButton(item);
						var b = $('<a class="l-button-warp l-button"></a>');
						p.id && b.attr("id", p.id);
						b.appendTo(g.button).ligerButton(p);
					}
				});
			} else {
				//g.button.append('<button class="l-button-warp l-button">'+icon+'<div class="l-button-text">'+p.text+'</div>'+'</button>');
				//g.button.addClass("x-btn floater x-btn-default-"+ (p.scale||this.scale)+" x-icon-text-left x-btn-icon-text-left x-btn-default-"+ (p.scale||this.scale)+"-icon-text-left");
				//g.button.addClass("x-btn x-btn-default-"+ (p.scale||this.scale)+"");
				/*var tpl='<em class="">';
				 tpl+='<button class="x-btn-center" type="button" hidefocus="true"  autocomplete="off">';
				 tpl+='<span class="x-btn-inner">' + p.text +'</span>';
				 if (p.icon!=undefined && p.icon!="") {//todo 2012-4-13 11:39 lybide
				 tpl+='<span class="x-btn-icon l-icon-'+p.icon+'">&#160;</span>';
				 }
				 tpl+='</button>';
				 tpl+='</em>';*/
				g.button.addClass("l-button");
				//p.id || g.button.attr("id", p.id); //todo 2013-5-16 下午5:49 lybide
				//if (p.icon!==undefined && p.icon!="") {g.button.append('<div class="l-button-icon l-icon-'+p.icon+'"></div>');}
				if (p.img !== undefined && p.img != "") {
					g.button.find(".l-button-icon").css('background', 'url("' + p.img + '")');
				}
				//todo 13-4-13 下午1:09 lybide 把button标签换为a + span
				g.button.append('<span class="l-button-main"><span class="l-button-text">' + (p.text?p.text:"&nbsp;") + '</span></span>');
				g.setButton(p);
				return;
			}
		},
		addButtonItem: function(options) { //todo 按扭结构 新增按钮 13-1-23 下午3:56 lybide
			var g = this,
				p = options;
			p = $.extend({
				place: "end"
			}, p, options);
			//var b=$('<a id="'+p.id+'" class="l-button-warp l-button"><span class="l-button-main"><span class="l-button-text">'+p.text+'</span></span></a>');
			var b = $('<a class="l-button-warp l-button"></a>');
			//p.id && b.attr("id", p.id);
			b.append('<span class="l-button-main"><span class="l-button-text">' + (p.text?p.text:"&nbsp;") + '</span></span>');
			if (p.place == "end") {
				g.button.after(b);
				g.button = g.button.next(); //todo 13-1-24 上午10:55 lybide
			} else if (p.place == "child") {
				if (i > 0) {
					g.button = g.button.parent();
				}
				g.button.append(b);
				g.button = b;
			} else {
				g.button.before(b).next();
				g.button = g.button.prev();
			}
			g.setButton(options);
		},
		buttonGroup: function(item) {
			return;
			var g = this,
				p = item;
			var b = $('<a class="l-button-warp l-button"><span class="l-button-main"><span class="l-button-text">' + (p.text?p.text:"&nbsp;") + '</span></span></a>');
			g.button.append(b);
			p.id && b.attr("ligeruiid", p.id);
			p.id && b.attr("id", p.id);
			if (p.icon !== undefined && p.icon != "") {
				b.find(".l-button-main").append('<span class="l-button-icon iconfont l-icon-' + p.icon + '"></span>').addClass("l-button-hasicon");
			}
			b.hover(function() {
				$(this).addClass("l-button-over");
			}, function() {
				$(this).removeClass("l-button-over");
			});
			p.click && b.click(function() {
				if (!p.disabled) p.click();
			});
			//g.set(p);
			//g.setButton(p);
		},
		setButton: function(options) { //todo 13-4-8 上午9:54 lybide 新增功能
			var g = this,
				p = options; //alert(this);
			//if (p.icon!==undefined && p.icon!="") {g.button.find(".l-button-main").append('<span class="l-button-icon l-icon-'+p.icon+'"></span>').addClass("l-button-hasicon");}
			var m = g.button.find(".l-button-main");
			p.icon && m.append('<span class="l-button-icon iconfont l-icon-' + p.icon + '"></span>').addClass("l-button-hasicon");
			p.img && m.append('<span class="l-button-icon" style="background:url(' + p.img + ') no-repeat center center;"></span>').addClass("l-button-hasicon");
			p.title && g.button.attr("title", p.title);
			p.id && g.button.attr("ligeruiid", p.id); //todo 给按钮添加Id 13-1-29 上午10:30 lybide
			p.id && g.button.attr("id", p.id); //todo 给按钮添加Id 2014-3-13 下午2:44 lybide!p.text && m.addClass("l-button-notText");
			!p.text && m.addClass("l-button-notText");
			g.button.hover(function() {
				$(this).addClass("l-button-over");
			}, function() {
				$(this).removeClass("l-button-over");
			});
			p.click && g.button.click(function() {
				if (!p.disabled) p.click();
			});
			g.set(p);
		},
		/*_setIcon: function(url) { //todo 2016年11月18日 11:46:59 星期五 lybide 关闭原版
			var g = this;
			if (!url) {
				g.button.removeClass("l-button-hasicon");
				g.button.find('img').remove();
			} else {
				g.button.addClass("l-button-hasicon");
				g.button.append('<img src="' + url + '" />');
			}
		},*/
		_setEnabled: function(value) {
			if (value) {
				this.button.removeClass("l-button-disabled");
			}
		},
		/*_setDisabled: function(value) { //todo 2016年11月18日 11:38:24 星期五 lybide 关闭原版
			if (value) {
				this.button.addClass("l-button-disabled");
				this.options.disabled = true;
			} else {
				this.button.removeClass("l-button-disabled");
				this.options.disabled = false;
			}
		},*/
		_setDisabled: function(value) {
			var g = this;
			if (value) {
				g.button.attr("disabled", true);
				g.button.addClass("l-button-disabled");
				g.button.removeClass("l-button");
				g.button.unbind("mouseenter mouseleave");
				g.options.disabled = true;
			} else {
				g.button.attr("disabled", false);
				g.button.removeClass("l-button-disabled");
				g.button.addClass("l-button");
				g.button.hover(function() {
					$(this).addClass("l-button-over");
				}, function() {
					$(this).removeClass("l-button-over");
				});
				this.options.disabled = false;
			}
		},
		_setWidth: function(value) {
			if (value=="auto") {
				return;
			}
			this.button.width(value);
		},
		_setText: function(value) {
			//$("span", this.button).html(value);//todo lybide 删除原版
			if (!value) {//2017年06月25日 12:49:56 星期日 lybide
				return;
			}
			$(".l-button-text", this.button).html(value);//todo lybide
		},
		setValue: function(value) {
			this.set('text', value);
		},
		getValue: function() {
			return this.options.text;
		},
		setEnabled: function() {
			this.set('disabled', false);
		},
		setDisabled: function() {
			this.set('disabled', true);
		}
	});


})(jQuery);