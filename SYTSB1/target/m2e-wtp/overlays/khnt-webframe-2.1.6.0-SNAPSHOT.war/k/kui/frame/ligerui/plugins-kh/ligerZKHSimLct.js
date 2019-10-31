/**
 * jQuery ligerUI 1.1.9 ligerSimLct 小型流程图
 *
 * http://ligerui.com
 *
 * Author lybide 2013 [ lyb@khnt.com ] 2013-5-10 上午11:49 lybide
 *
 */

/*
 <script type="text/javascript" src="k/kui/frame/ligerSimLct.js"></script>
 <script type="text/javascript">
 function lookRecord2(id){
 alert(id)
 };
 $(function(){//jQuery页面载入事件
 var x=$("#lct1").ligerSimLct({items:[
 {id:"bet1",text:"入户调查记录",click:function(id){lookRecord2(id);},disabled:true},
 {id:"bet2",text:"民主评议记录",click:function(id){lookRecord2(id);},disabled:false},
 {id:"bet3",text:"乡镇审核意见",click:function(id){lookRecord2(id);},disabled:true},
 {id:"bet4",text:"公示结果",click:function(id){lookRecord2(id);},disabled:false},
 {id:"bet5",text:"审批意见",click:function(id){lookRecord2(id);},disabled:true}
 ]});
 x.setDisabled({bet1:false,bet3:false,bet2:true});
 });
 </script>
 */
(function($) {

	$.fn.ligerSimLct = function(options) {
		return $.ligerui.run.call(this, "ligerSimLct", arguments);
	};

	$.fn.ligerGetSimLctManager = function() {
		return $.ligerui.run.call(this, "ligerGetSimLctManager", arguments);
	};

	$.ligerDefaults.SimLct = {};

	$.ligerMethos.SimLct = {};

	$.ligerui.controls.SimLct = function(element, options) {
		$.ligerui.controls.SimLct.base.constructor.call(this, element, options);
	};
	$.ligerui.controls.SimLct.ligerExtend($.ligerui.core.UIComponent, {
		__getType: function() {
			return 'SimLct';
		},
		__idPrev: function() {
			return 'SimLct';
		},
		_extendMethods: function() {
			return $.ligerMethos.SimLct;
		},
		_render: function() {
			var g = this, p = this.options;
			g.lct = $(this.element);
			//g.lct.addClass("l-toolbar");
			var m = $('<div class="l-page-title-lct"><ul></ul></div>');
			g.lct.append(m);
			g.lctUl = $("ul", g.lct);
			g.set(p);
		},
		_setItems: function(items) { //2013-5-10 上午11:01 lybide
			var g = this, p = this.options;
			$.each(items, function(i, item) {
				var li = $('<li><div><a><span>' + item["text"] + '</span></a></div></li>');
				if (item.disabled) {
					li.addClass("lct-is-disabled").attr("disabled", true);
				}
				if (item.id) {
					li.attr("id", item.id);
				}
				if (item.click) {
					li.find("a").click(function() {
						if (!li.attr("disabled")) {
							item.click(item);
						}
					});
				}
				g.lctUl.append(li);
			});
			g.lctUl.find("li:last").addClass("lct-is-no");
		},
		addItem: function(i, item) {
			var g = this,
				p = this.options;

		},
		_setEnabled: function(key, value) {
			var g = this, p = this.options;
			var li = g.lctUl.find("#" + key);
			li.attr("disabled", value);
			if (value) {
				li.addClass("lct-is-disabled");
			} else {
				li.removeClass("lct-is-disabled");
			}
		},
		setEnabled: function(items) {
			var g = this,
				p = this.options;
			$.each(items, function(i, item) {
				g._setEnabled(i, item);
			});
		},
		setDisabled: function(items) {
			var g = this,
				p = this.options;
			$.each(items, function(key, value) {
				g._setEnabled(key, value);
			});
		}
	});
})(jQuery);