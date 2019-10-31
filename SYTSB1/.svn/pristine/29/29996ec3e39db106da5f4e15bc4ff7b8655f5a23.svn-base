
//2013-5-27 下午11:00 lybide 添加
(function (config) {
	config['extendDrag'] = true; // 注意，此配置参数只能在这里使用全局配置，在调用窗口的传参数使用无效
	config['lock'] = true;
	config['fixed'] = true;
	config['okVal'] = '确定';
	config['cancelVal'] = '关闭';
	//config['width'] = "100%";
	//config['height'] = "100%";
	config['data'] = {window:window};
	config['parent'] = typeof api=="undefined" ? "" :api;
	if (typeof kFrameConfig=="undefined") {
		
	} else {
		config['path'] = kFrameConfig["base"]+'k/kui/images/icons/dialog/';
	}
	
	// [more..]
})($.dialog.setting);

(function ($) {
	top.$.notice = function (content,time,icon,fun,tops) {
		//var win = W == undefined ? $ : W.$
		$.dialog.tips(content,time?time:1,icon?icon:'k/kui/images/icons/dialog/32X32/succ.png',fun?fun:null,tops?tops:5);
        return;
		/*top.$.dialog.notice({
			icon:'k/kui/images/icons/dialog/32X32/succ.png',
			content:content,
			lock:false
		});*/
	};

	top.$.dialog.notice = function (options,time,icon,fun,tops) {
		if (typeof options=="object") {
			$.dialog.tips(options.content,options.time?options.time:3,options.icon?options.icon:'k/kui/images/icons/dialog/32X32/succ.png',options.fun?options.fun:null,options.tops?options.tops:5);
		} else {
			$.dialog.tips(options,time?time:1,icon?icon:'k/kui/images/icons/dialog/32X32/succ.png',fun?fun:null,tops?tops:5);
		}
        return;
		/*var opts = options || {},
			aConfig, hide, wrap, top,
			duration = opts.duration || 800;

		var config = {
			id:'Notice',
			left:'50%',
			//width: 220,
			top:'0',
            icon:'k/kui/images/icons/dialog/32X32/succ.png',
			fixed:true,
			drag:false,
			resize:false,max:false,min:false,close:false,
			height:30,
			time:3,
			zIndex: lhgdialog.setting.zIndex,
			lock:false,
			//parent:api,
			//data:{"window":window},
			title:'提示：',
			init:function (here) {
				api = this;
				aConfig = api.config;
				wrap = api.DOM.wrap;
				top = parseInt(wrap[0].style.top);
				hide = top - wrap[0].offsetHeight;

				wrap.css('top', hide + 'px')
					.animate({top:top + 'px'}, duration, function () {
						opts.init && opts.init.call(api, here);
					});
			},
			close:function (here) {
				wrap.animate({top:hide + 'px'}, duration, function () {
					opts.close && opts.close.call(this, here);
					aConfig.close = $.noop;
					api.close();
				});
				return false;
			}
		};

		for (var i in opts) {
			if (config[i] === undefined) config[i] = opts[i];
		}
		return $.dialog(config);*/
	};

	/**
	 * @description 页面载入遮照
	 * @fileOverview 简单的类对象标注示例
	 * @author lybide
	 * @version 0.1
	*/
	top.$.dialog.loading = function (str) {
		//新 loading | lybide 13-4-12 下午9:23 lybide
		var lo=$("#mLoading");
		if (lo.length>0) {
			lo.show();
			$("#mLoadingMask").show();
		} else {
			lo=$('<div id="mLoading" class="m-loading"><a href="javascript:void(0);" class="m-w-button"><span>x</span></a><div class="m-w-loading"><div class="m-w-loading-div"></div></div></div>');
			lo.hover(function () {
				var $this=$(this);
				$("a",$this).show();
			},
			function () {
				var $this=$(this);
				$("a",$this).hide();

			}).show();
			$("body").append(lo);
			lo.find("a").click(function(){$("#mLoading,#mLoadingMask").hide();});


			lo=$('<div id="mLoadingMask" class="m-loading-mask"><div class="m-loading-mask-div"></div></div>').show();
			$("body").append(lo);//添加遮罩效果 13-4-20 下午4:13 lybide

		}
		var mWLoading=$(".m-w-loading",lo);
		mWLoading.empty();
		if (str) {//2013-12-20 上午11:36 lybide
			mWLoading.append('<div class="m-w-loading-content" id="mLoadingTextContent"><table border="0" cellpadding="0" cellspacing="0" width="100%" height="100%" align="center"><tr><td class="m-w-loading-content-icon"><div></div></td><td class="m-w-loading-content-text"><div>'+str+'</div></td></tr></table></div>');
		} else {
			mWLoading.append('<div class="m-w-loading-div"></div>');
		}
		return 1;
		//老式loading
		var config = {
			id:'loadingMask',
			left:'50%',
			//width: 220,height:30,
			top:'50%',
			fixed:true,
			drag:true,
			resize:false,
			lock:false,
			title:"正在载入数据……",
			close:true,max:false,min:false,
			//parent:api,
			//data:{"window":window},
			content: '<div id="mLoading" class="m-w-loading"><div class="m-w-loading-div"></div></div>'
		};
		$.extend(config,options);
		return $.dialog(config);
	};

	top.$.dialog.loadingClose = function () {
		top.$("#mLoading,#mLoadingMask").hide();
		return 0;
	};

	top.$.dialog.closeAll=function(){
		setTimeout(function(){
			var list = top.$.dialog.list;
			for( var i in list ){
				list[i].close();
			}
		},10);//延迟解决回调函数出错。2012年05月11日 星期五 17:42:58 lybide
	}
})(jQuery);