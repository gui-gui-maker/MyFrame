/**
 * jQuery ligerUI 1.3.2 ligerToolBar
 *
 * http://ligerui.com
 *
 * Author daomi 2015 [ gd_star@163.com ]
 *
 */
(function($) {

	$.fn.ligerToolBar = function(options) {
		return $.ligerui.run.call(this, "ligerToolBar", arguments);
	};

	$.fn.ligerGetToolBarManager = function() {
		return $.ligerui.run.call(this, "ligerGetToolBarManager", arguments);
	};

	$.ligerDefaults.ToolBar = {};

	$.ligerMethos.ToolBar = {};

	$.ligerui.controls.ToolBar = function(element, options) {
		$.ligerui.controls.ToolBar.base.constructor.call(this, element, options);
	};
	$.ligerui.controls.ToolBar.ligerExtend($.ligerui.core.UIComponent, {
		__getType: function() {
			return 'ToolBar';
		},
		__idPrev: function() {
			return 'ToolBar';
		},
		_extendMethods: function() {
			return $.ligerMethos.ToolBar;
		},
		_render: function() {
			var g = this, p = this.options;
			g.toolbarItemCount = 0;
			g.toolBar = $(this.element);
			g.toolBar.addClass("l-toolbar");
			g.set(p);
		},
		//todo 13-5-7 下午3:50 lybide
		//工具条支持以组的的形式，如左右都可以
		_setItems: function(groups) {
			var g = this;
			var te = $(this.element); //todo 2013-5-31 下午10:13 lybide
			var teId = te.attr("id") || this.id;
			var tb = $('<table id="' + teId + '_table1" border="0" cellpadding="0" cellspacing="0" width="100%" class="l-toolbar-table"><tr></tr></table>');
			te.append(tb);
			var td = $('<td id="' + teId + '_td0" class="td1"><div class="l-toolbar-container"></div></td>');
			var items = [];
			var index = 0;
			$(groups).each(function(i, group) {
				if (group != "->") {
					items.push(group);
				} else {
					tb.find("tr").append(td);
					g.toolBar = td.find("div");
					g.loopCreateItems(items);
					g.toolBar.css("float", index == 0 ? "left" : "right");
					td = $('<td id="' + teId + '_td' + (index + 1) + '"><div class="l-toolbar-container"></div></td>');
					items = [];
					index++;
				}
			});
			tb.find("tr").append(td);
			g.toolBar = td.find("div");
			g.loopCreateItems(items);
			g.toolBar.css("float", index == 0 ? "left" : "right");
			g.toolBar = te; //todo 生成好后，返回，13-5-7 下午4:25 lybide
		},
		//todo lybide add
		loopCreateItems: function(items) {
			var g = this;
			$(items).each(function(i, item) {
				g.addItem(i, item);
			});
		},
		_setItems__: function(items) { //todo 删除原版 2016年11月30日 16:10:35 星期三 lybide
			var g = this;
			g.toolBar.html("");
			$(items).each(function(i, item) {
				g.addItem(item);
			});
		},
		removeItem: function(itemid) {
			var g = this, p = this.options;
			$("> .l-toolbar-item[toolbarid=" + itemid + "]", g.toolBar).remove();
		},
		setEnabled__: function(itemid) {
			var g = this, p = this.options;
			$("> .l-toolbar-item[toolbarid=" + itemid + "]", g.toolBar).removeClass("l-toolbar-item-disable");
		},
		setDisabled: function(itemid) {
			var g = this, p = this.options;
			$("> .l-toolbar-item[toolbarid=" + itemid + "]", g.toolBar).addClass("l-toolbar-item-disable");
		},
		isEnable: function(itemid) {
			var g = this, p = this.options;
			return !$("> .l-toolbar-item[toolbarid=" + itemid + "]", g.toolBar).hasClass("l-toolbar-item-disable");
		},
		/*
		//todo 删除原版 2016年11月30日 16:21:25 星期三 lybide
		addItem: function(item) {
			var g = this, p = this.options;
			if (item.line || item.type == "line") {
				g.toolBar.append('<div class="l-bar-separator"></div>');
				return;
			}
			if (item.type == "text") {
				g.toolBar.append('<div class="l-toolbar-item l-toolbar-text"><span>' + item.text || "" + '</span></div>');
				return;
			}
			var ditem = $('<div class="l-toolbar-item l-panel-btn"><span></span><div class="l-panel-btn-l"></div><div class="l-panel-btn-r"></div></div>');
			g.toolBar.append(ditem);
			if (!item.id) item.id = 'item-' + (++g.toolbarItemCount);
			ditem.attr("toolbarid", item.id);
			if (item.img) {
				ditem.append("<img src='" + item.img + "' />");
				ditem.addClass("l-toolbar-item-hasicon");
			}
			else if (item.icon) {
				ditem.append("<div class='l-icon l-icon-" + item.icon + "'></div>");
				ditem.addClass("l-toolbar-item-hasicon");
			}
			else if (item.color) {
				ditem.append("<div class='l-toolbar-item-color' style='background:" + item.color + "'></div>");
				ditem.addClass("l-toolbar-item-hasicon");
			}
			item.text && $("span:first", ditem).html(item.text);
			item.disable && ditem.addClass("l-toolbar-item-disable");
			item.click && ditem.click(function() {
				if ($(this).hasClass("l-toolbar-item-disable")) return;
				item.click(item);
			});
			if (item.menu) {
				item.menu = $.ligerMenu(item.menu);
				ditem.hover(function() {
					if ($(this).hasClass("l-toolbar-item-disable")) return;
					g.actionMenu && g.actionMenu.hide();
					var left = $(this).offset().left;
					var top = $(this).offset().top + $(this).height();
					item.menu.show({top: top, left: left});
					g.actionMenu = item.menu;
					$(this).addClass("l-panel-btn-over");
				}, function() {
					if ($(this).hasClass("l-toolbar-item-disable")) return;
					$(this).removeClass("l-panel-btn-over");
				});
			}
			else {
				ditem.hover(function() {
					if ($(this).hasClass("l-toolbar-item-disable")) return;
					$(this).addClass("l-panel-btn-over");
				}, function() {
					if ($(this).hasClass("l-toolbar-item-disable")) return;
					$(this).removeClass("l-panel-btn-over");
				});
			}
		},*/
		addItem: function(i, item) {
			var g = this, p = this.options;
			//todo 工具条可有菜单 lybide
			if (item.menus) {

				//alert(JSON.stringify(item["id"]));
				var ditem = $('<div class="l-toolbar-item" id="' + item["id"] + '"></div>');

				g.toolBar.append(ditem);
				var bb = item.menus;

				$(function() { //jQuery页面载入事件
					//alert([item["id"],"#"+item["id"]+"",JSON.stringify(bb)])
					$("#" + item["id"] + "").ligerMenuBar({
						items: bb
					});
					$("#" + item["id"] + "").removeClass("l-menubar");
				});
				return;
			}
			if (item.line || item == "-") {
				g.toolBar.append('<div class="l-bar-separator"></div>');
				return;
			}
			if (item.html) {
				g.toolBar.append('<div class="l-toolbar-item">' + item.html + '</div>');
				return;
			}
			if (item.ltype) {
				$('<div class="l-toolbar-item"><input type="text"/></div>').appendTo(g.toolBar).find("input").ligerTextBox(item);
				return;
			}
			var ditem = $('<div class="l-toolbar-item l-panel-btn"><div class="l-panel-btn-l"></div><div class="l-panel-btn-r"></div></div>');
			g.toolBar.append(ditem);
			item.id && ditem.attr("toolbarid", item.id).attr("id",""+item.id);//todo 添加id属性 2017年06月09日 16:05:00 星期五 lybide for lasa
			if (item.img) {
				//ditem.append("<div class='l-icon' style='background:url("+$.ligerMethos.getImgSrc(item.img)+") no-repeat center center;'></div>");
				ditem.append("<div class='l-icon' style='background:url(" + item.img + ") no-repeat center center;'></div>");
				ditem.addClass("l-toolbar-item-hasicon");
			} else if (item.icon) {
				ditem.append("<div class='l-icon iconfont l-icon-" + item.icon + "'></div>");
				ditem.addClass("l-toolbar-item-hasicon");
			}
			if (item.title) { //todo 13-4-7 下午2:06 lybide 添加title效果
				ditem.attr("title", item.title);
			}
			//item.text && $("span:first", ditem).html(item.text);
			//item.text && ditem.prepend('<span>' + item.text + '</span>');
			ditem.prepend('<span class="l-toolbar-text">' + (item.text?item.text:"&nbsp;") + '</span>');//2017年06月25日 12:39:48 星期日 lybide
			!item.text && ditem.addClass("l-toolbar-item-notext");
			ditem.hover(function() {
				$(this).addClass("l-panel-btn-over");
			}, function() {
				$(this).removeClass("l-panel-btn-over");
			});
			if (item.disabled || item.disable) { //todo 移到所有元素初始化完成后。13-1-29 上午10:13 lybide
				//ditem.addClass("l-toolbar-item-disable");
				g._setEnabled(item.id, false);
			}
			item.click && ditem.click(function() {
				if (!$(this).attr("disabled")) { //todo 禁用按钮就不执行 13-5-7 下午5:24 lybide
					item.click(item);
				}

			});
		},
		//todo add by ttaomeng
		_setEnabled: function(itemId, enable) {
			var g = this, p = this.options;
			//alert(itemId+"=="+enable+"\n\n"+g.toolBar.html());
			//todo 13-5-7 下午4:28 lybide 删除迭代，提高效率。
			//var items=p.items;
			//$.each(items,function (i, item)
			//{
			//    if(itemId==item["id"]){
			//todo 13-4-7 下午1:47 lybide 添加 g.toolBar 范围，解决初始化时不显示禁用 也提高执行效率。
			var ditem = $("div[toolbarid=" + itemId + "]", g.toolBar);
			if (enable === true) { //启用
				//ditem.removeClass("l-toolbar-item-disable");
				//ditem.unbind('click');
				//ditem.click(function () { item.click(item); });
				//todo 给工具条添加禁用样式1 13-1-25 下午2:39 lybide
				ditem.attr("disabled", false);
				ditem.removeClass("l-panel-btn-disable");
				ditem.addClass("l-panel-btn");
				ditem.hover(function() {
					$(this).addClass("l-panel-btn-over");
				}, function() {
					$(this).removeClass("l-panel-btn-over");
				});
				//g.options.disabled = false;
			} else { //禁用
				//ditem.addClass("l-toolbar-item-disable");
				//ditem.unbind('click');
				//todo 给工具条添加禁用样式2 13-1-25 下午2:39 lybide
				ditem.attr("disabled", true);
				ditem.addClass("l-panel-btn-disable");
				ditem.removeClass("l-panel-btn l-panel-btn-over l-panel-btn-select");
				ditem.unbind("mouseenter mouseleave");
				//g.options.disabled = true;
			}
			return false;
			//    }
			//});
		},
		setEnabled: function(items) {
			var g = this;
			$.each(items, function(i, item) {
				g._setEnabled(i, item);
			});
		}
	});
	//旧写法保留
	$.ligerui.controls.ToolBar.prototype.setEnable = $.ligerui.controls.ToolBar.prototype.setEnabled;
	$.ligerui.controls.ToolBar.prototype.setDisable = $.ligerui.controls.ToolBar.prototype.setDisabled;
})(jQuery);