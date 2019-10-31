$(function(){
	$.fn.setValues = function(obj, onSuccess, bxpepei) {
		if (!obj) {
			$("body").unmask();
			return;
		};
		bxpepei = bxpepei || false;
		return this.each(function() {
			var type = typeof obj;
			var $this = $(this);
			var status = $this.attr("pageStatus") || $("head").attr("pageStatus");
			var suc = function(d) {};
			var url = $this.attr("getAction");
			if (type == "string") {
				url = obj;
			} else if (type == "object") {
				if (!obj) return;
				if (status == "detail") $.fn.setDivValues(obj, $this);
				else $.fn.setValues.setWithData(obj, $this, bxpepei);
				return;
			} else if (type == "function") {
				suc = obj;
			}
			if (typeof onSuccess == "function") {
				suc = onSuccess;
			}
			if (url) {
				$.ajax({
					url: url,
					$this: $this,
					dataType: 'json',
					success: function(data) {
						if (data.success) {
							if (status == "detail") $.fn.setDivValues(data.data, this.$this);
							else $.fn.setValues.setWithData(data.data, this.$this);
						} else {
							var msg = data.msg || data.data;
							//$.ligerDialog.warn(msg);
						}
						$("body").unmask();
						suc(data);
					},
					error: function(XMLHttpRequest, textStatus, errorThrown) {
						alert([XMLHttpRequest, textStatus, errorThrown]);
						$("body").unmask();
						//$.ligerDialog.error('加载数据失败！' + textStatus, "error");
					}
				});
			} else { //13-5-2 下午9:18 lybide
				$("body").unmask();
			}

		});
	};
	//获取指定元素下面所有表单元素不包含提交rest，image，button
	$.fn.getValues = function(){
		var data = {};
		$(":input", this).not(":submit, :reset, :image,:button").each(function() {
			var ele = $(this);
			var name = ele.attr("name");
			if(!name) return;
			//通过jquery val获取值
			if(ele.is(":hidden")||ele.is(":password")
			   ||ele.is(":text")||ele.is("textarea")
			){
				if(data[name]===undefined){
					data[name] = ele.val();
				}else{
					if (!$.isArray(data[name])) {
						data[name] = [data[name]];
					}
					data[name].push(ele.val());
				}
			}
			else if(ele.is(":radio")){
				if(ele.is(":checked")){
					if(data[name]===undefined){
						data[name] =ele.val()
					}else{
						if (!$.isArray(data[name])) {
							data[name] = [data[name]];
						}
						data[name].push(ele.val());
					}
				}
			}else if(ele.is(":checkbox")){
				if(ele.is(':checked')){
					if(data[name]===undefined){
						data[name] = ele.val();
					}else{
						if (!$.isArray(data[name])) {
							data[name] = [data[name]];
						}
						data[name].push(ele.val());
					}
				}
			}
			else if(ele.is("select")){
				if(data[name]===undefined){
					data[name] = ele.val();
				}else{
					if (!$.isArray(data[name])) {
						data[name] = [data[name]];
					}
					data[name].push(ele.val());
				}
			}else{
				if(data[name]===undefined){
					data[name] = ele.val();
				}else{
					if (!$.isArray(data[name])) {
						data[name] = [data[name]];
					}
					data[name].push(ele.val());
				}
			}
		});
		return data;
	}
	$.fn.parseForm = function(key) {
		$("body").mask("正在初始化数据，请稍候……");
		var $this = $(this);
		var options = $this.data("formOptions");
		$this.parseFormSingle(options,key);//alert("渲染单个form表单");
	}
	$.fn.initForm = function(options) {
		var $this = $(this);
		$this.data("formOptions", options);
	}
	$.fn.parseFormSingle = function(options,key) {
		var $this = $(this);
		var config = c = $.extend({
			transformOnly:false,
			autoClose : $("form").size() == 1,
			status : $this.attr("pageStatus") || $("head").attr("pageStatus"),
			action : $this.attr("action"), actionParam : {},
			afterParse:function(formObj){//form表单完成渲染后，回调函数
			},
			getSuccess : function() {
			}, success : function(data) {
			}
		}, options);

		if (!(config.getAction == "" || config.getAction === undefined || config.getAction === null)) {
			$this.attr("getAction",config.getAction);
		}
		$this.submit(function(){
			$("body").mask("正在保存数据……");
			$.post($this.attr("action") || config["action"], $.transValues($
					.extend($this.getValues(),
							_getParaValues(config.actionParam))),
					function(data) {
						$("body").unmask();
						config.success(data); // todo 事件
					}, "json");
			return false;
		})
		$this.data("initForm", true);
		var status = config.status;
		$this.transform(status);
		$("body").unmask();
		if (status == "edit" || status == "modify" || status == "detail") {
			$("body").mask("正在初始化数据……");
			$this.setValues(config.getSuccess);
		}
		config.afterParse($this);
		
		function _getParaValues(param) {
			// 检查关联字段
			var para = {};
			$.each(param, function(i, el) {
				var ele = $(el);
				if (ele.length > 0) {
					para[i] = ele.val();
				}
			});
			return para;
		}
	}
	$.transValues = function(datas) {
		var dataArray = [];
		$.each(datas,
		function(index, d) {
			if ($.isArray(d)) {
				$.each(d,
				function(i, dd) {
					dataArray.push({
						name: index,
						value: dd
					});
				});
			} else {
				dataArray.push({
					name: index,
					value: d
				});
			}
		});
		return dataArray;
	}
	$.fn.setDivValues = function(data, t) {
		if (data == undefined) return;
		$("div.input", t).each(function() {
			var ele = $(this);
			var name = ele.attr("name");
			if (!name) return;
			var v = parseName(name, data);
			if (v === undefined) return;
			$(this).setDivValue(v);
		});

	};
	function parseName(name, data) {
		if (!data) {
			return undefined;
		}
		if (data[name] !== undefined) return data[name];
		var names = name.split(".");
		var value = data;
		for (var i = 0; i < names.length; i++) {
			if (value[names[i]] != undefined) value = value[names[i]];
			else return undefined;
		}
		return value;
	}
	$.fn.setDivValue = function(v) {
		var ele = $(this);
		if (v === null) {
			ele.html("&nbsp;");
			return;
		}
		if (isNaN(v)) {
			ele.html(v == "" ? "&nbsp;": v.replace(/\n/g, "<br>"));
		} else {
			ele.html(v + "&nbsp;");
		}
	};
	$.fn.setValues.setWithData = function(data, t, bxpepei) {
		if (data === undefined) return;
		function parseName(name, data) {
			if (!data) {
				return undefined;
			}
			if (data[name] !== undefined) return data[name];
			var names = name.split(".");
			var value = data;
			for (var i = 0; i < names.length; i++) {
				if (value[names[i]] != undefined) {
					value = value[names[i]];
				} else {
					return undefined;
				}
			}
			return value;
		}

		$(":input", t).not(":submit, :reset, :image,:button, [disabled]").each(function() {

			var ele = $(this);
			var ltype = ele.attr("ltype");
			var name = ele.attr("name");
			if (!name) return;
			var v = parseName(name, data);
			if (v === null) v = "";
			if (v == undefined && bxpepei) { //下拉自选，无值时退出 13-4-15 下午11:11 lybide
				return;
			}
			//通过jquery val获取值
			if(ele.is(":hidden")||ele.is(":password")
			   ||ele.is(":text")||ele.is("textarea")){
				if (v !== undefined) {
					ele.val(v);
					ele.textinput();
				}
				return;
			}
			else if(ele.is(":radio")){
				if(ele.val()==v){
					ele.attr("checked","checked");
				}
				$("input[name='"+name+"']").checkboxradio("refresh");
			}else if(ele.is(":checkbox")){
				if(v!==undefined)
					for(var i=0; i< v.split(",").length;i++){
						if(ele.val()==v.split(",")[i]){
							ele.attr("checked","checked").checkboxradio("refresh");
						}
					}
			}
			else if(ele.is("select")){
				if (v !== undefined) {
					if(ele.attr("multiple")=='multiple'){
						ele.find("option").each(function(){
							for(var i =0; i<v.spilt(",").length;i++){
								if($(this).val()==v.split(",")[i]){
									$(this).attr("selected","selected");
								}
							}
						})
						ele.selectmenu("refresh");
					}
					ele.val(v);
					if(ele.attr("data-role")=='slider'){
						ele.slider("refresh");
					}else{
						ele.selectmenu("refresh");
					}
				};
			}else{
				if (v !== undefined) {
					ele.val(v)
					if(ele.attr("data-type")=="range"){
						ele.slider("refresh");
					}
				};
				return;
			}
		});
	};
	$.fn.unmask = function() {
		 $.mobile.loading('hide');  
	}
	$.fn.mask = function(title) {
		title = title || "正在加载数据……";
		$.mobile.loading('show', {  
	        text: title, //加载器中显示的文字  
	        textVisible: true, //是否显示文字  
	        theme: 'a',        //加载器主题样式a-e  
	        textonly: false,   //是否只显示文字  
	        html: title           //要显示的html内容，如图片等  
	    });  
	}
	$.fn.transform = function(status) {
		var $this = $(this);
        $this.attr("pageStatus",status);
		if (status == "detail") {
			$(":input", $this).each(function() {
					if($(this).is(":hidden")){
						return;
					}
					if($(this).is(":submit")||$(this).is(":button")||$(this).is(":reset")||$(this).is(":image")){
						$(this).hide();
					}
					var jinput = $(this);
					var initValue = jinput.val();
					var id = jinput.attr("id");
					var name = jinput.attr("name");
					var div;
					var temp = "<div class='input' name='"+name+"'"
								+ (id ? (" id='" + id + "'") : "")
								+ ">&nbsp;</div>"
					div = $(temp);
					if (initValue) {
						div.setDivValue(initValue);
					}
					div.insertAfter(jinput);
					if(jinput.is(":radio")){
						$("input[name='"+name+"']").each(function(){
							var labelId = $(this).attr("id");
							$("label[for='"+labelId+"']").remove();
							$(this).remove();
						})
					}
					else if(jinput.is(":checkbox")){
						$("input[name='"+name+"']").each(function(){
							var labelId = $(this).attr("id");
							$("label[for='"+labelId+"']").remove();
							$(this).remove();
						})
					}
					else if(jinput.is("select")){
						if(jinput.attr("data-role")=='slider'){
							$(this).parent().find(".ui-slider").remove();
						}
						$("select[name='"+name+"']").remove();
						div.insertAfter($("label[for='"+id+"']"))
					}
					else if(jinput.attr("data-type")=='range'){
						$(this).parent().find('.ui-slider-track').remove();
						jinput.remove();
					}else{
						jinput.remove();
					}
				}
			);
		}
	}
})
jQuery.event.add(window, "load", function() {
	try{
		window.injs.changeTitle($("title").text());
	}catch(e){}
	var forms = $("form");
	if (forms.size() == 1) {
		forms.parseForm(1);
	}
});