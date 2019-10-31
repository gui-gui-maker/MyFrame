//判断浏览器
var browser={
    versions:function(){
        var u = navigator.userAgent, app = navigator.appVersion;
        return {         //移动终端浏览器版本信息
             trident: u.indexOf('Trident') > -1, //IE内核
            presto: u.indexOf('Presto') > -1, //opera内核
            webKit: u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核
            gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1, //火狐内核
            mobile: !!u.match(/AppleWebKit.*Mobile.*/), //是否为移动终端
            ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
            android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android终端或uc浏览器
            iPhone: u.indexOf('iPhone') > -1 , //是否为iPhone或者QQHD浏览器
            iPad: u.indexOf('iPad') > -1, //是否iPad
            webApp: u.indexOf('Safari') == -1 //是否web应该程序，没有头部与底部
        };
     }(),
     language:(navigator.browserLanguage || navigator.language).toLowerCase()
} 
$(function(){
	//验证
	if ($.validator) {
		$.validator.setDefaults({
			ignore : "",
			errorPlacement : function(error, element) {
				$(element).removeClass("requiredstar");
				$(element).attr("title", error.html()).addClass("l-text-invalid");
				var error = $("<div class='errorMsg' style='position:absolute;right:0;bottom:1em;padding:5px 5px 5px 5px;background-color:#ffdbc5;border:1px solid red;border-radius: 5px 5px 5px 5px;'>"+error.html()+"</div>")
				error.insertAfter(element);
			},
			success : function(lable, ele) {
				$(ele).removeClass("l-text-invalid")
				$(ele).removeClass("requiredstar");
				$(ele).parent().find(".errorMsg").remove();
			}
		});
	}
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
				if (status == "detail") {
					$.fn.setDivValues(obj, $this);
				}
				else {
					$.fn.setValues.setWithData(obj, $this, bxpepei);
				}
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
						}
						$("body").unmask();
						suc(data);
					},
					error: function(XMLHttpRequest, textStatus, errorThrown) {
						alert([XMLHttpRequest, textStatus, errorThrown]);
						$("body").unmask();
					}
				});
			} else { 
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
		$this.validate({
			submitHandler : function() {
				$("body").mask("正在保存数据……");
				$.post($this.attr("action") || config["action"], $.transValues($
						.extend($this.getValues(),
								_getParaValues(config.actionParam))),
						function(data) {
							$("body").unmask();
							config.success(data); // todo 事件
						}, "json");
				return false;
			}
		});
		/**
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
		*/
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
			if (v === undefined||v==null) return;
			var tempvalue="";
			if(!isNull(ele.attr("codeValue"))){
				eval("codevalue="+ele.attr("codeValue"));
				for(var i=0;i<codevalue.length;i++){
					var temp;
					if(typeof codevalue[i]=="object"){
						temp = codevalue[i]
					}else{
						temp = $.parseJSON(codevalue[i]);
					}
					for(var j=0;j<v.split(",").length;j++){
						if(temp.id == v.split(",")[j]){
							tempvalue +=temp.text+",";
						}
					}
				}
				$(this).setDivValue(isNull(tempvalue)?tempvalue:tempvalue.substring(0, tempvalue.length-1));
			}else{
				$(this).setDivValue(v);
			}
			
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

		$(":input", t).not(":submit, :reset, :image,:button ").each(function() {

			var ele = $(this);
			var ltype = ele.attr("ltype");
			var name = ele.attr("name");
			if (!name) return;
			var v = parseName(name, data);
			if (v === null) v = "";
			if (v == undefined && bxpepei) { 
				return;
			}
			//通过jquery val获取值
			if(ele.is(":password")
			   ||ele.is(":text")||ele.is("textarea")){
				if (v !== undefined) {
					ele.val(v);
					ele.textinput();
				}
				return;
			}else if(ele.is(":hidden")){
				if (v !== undefined) {
					ele.val(v);
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
					var selectedName="";
					var num = 0;
					if(ele.attr("multiple")=='multiple'){
						ele.find("option").each(function(){
							for(var i =0; i<v.split(",").length;i++){
								if($(this).val()==v.split(",")[i]){
									$(this).attr("selected","selected");
									selectedName+=$(this).text()+",";
									num++;
								}
							}
						})
						if(!isNull(selectedName)){
							selectedName = selectedName.substring(0,selectedName.length-1)
						}
						ele.parent().find("a span:first").html(selectedName);
						ele.parent().find("a .ui-li-count").html(num);
						if(num>1){
							ele.parent().find("a .ui-li-count").show();
						}
						ele.selectmenu("refresh");
					}else{
						ele.val(v);
					}
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
	        theme: 'b',        //加载器主题样式a-e  
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
						if(!isNull(jinput.attr("code"))){
							var codeValues=[];
							jinput.parent().parent().find("label").each(function(){
								var tempid = $(this).attr("for");
								var tempval = $("#"+tempid).val();
								codeValues.push("{\"id\":\""+tempval+"\",\"text\":\""+$(this).text()+"\"}")
							})
							div.attr("codeValue",JSON.stringify(codeValues));
						}
						$("input[name='"+name+"']").each(function(){
							var labelId = $(this).attr("id");
							$("label[for='"+labelId+"']").remove();
							$(this).remove();
						})
					}
					else if(jinput.is(":checkbox")){
						if(!isNull(jinput.attr("code"))){
							var codeValues=[];
							jinput.parent().parent().find("label").each(function(){
								var tempid = $(this).attr("for");
								var tempval = $("#"+tempid).val();
								codeValues.push("{\"id\":\""+tempval+"\",\"text\":\""+$(this).text()+"\"}")
							})
							div.attr("codeValue",JSON.stringify(codeValues));
						}
						$("input[name='"+name+"']").each(function(){
							var labelId = $(this).attr("id");
							$("label[for='"+labelId+"']").remove();
							$(this).remove();
						})
					}
					else if(jinput.is("select")){
						if(!isNull(jinput.attr("code"))){
							var codeValues=[];
							jinput.find("option").each(function(){
								codeValues.push("{\"id\":\""+$(this).val()+"\",\"text\":\""+$(this).text()+"\"}")
							})
							var temp = "<div class='input' name='"+name+"' codeValue='"+JSON.stringify(codeValues)+"'"
							+ (id ? (" id='" + id + "'") : "")
							+ ">&nbsp;</div>"
							div = $(temp);
						}
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
	/**
	 * 
		setSelectOption = function(obj,code,callback){
			$.getJSON("pub/codetable/getCodeTable.do",{code:code},function(res){
				if(res.success){
					$.each(res.data,function(i,data){
						obj.append("<option value='"+data.id+"'>"+data.text+"</option>")
					})
				}
				if (typeof callback == 'function'){
					callback();
				}
			})
		}
	 */
	//页面加载初始化form信息 html方式。在页面加载时
	pageinit = function(){
		if(browser.versions.android){
			try{
				window.KHANJS.changeTitle($("title").text())
			}
			catch(e){}
		}
		var forms = $("form");
		if (forms.size() == 1) {
			forms.parseForm(1);
		}
	};
	//html方式打开对话框
	openDialog = function (option) {
        var href = option.href || "about:blank";
        var transition = option.transition || "none";
        $('body').append("<a id='tPushDialog' href='" + option.href + "' data-rel=\"dialog\" data-transition=\"" + option.transition + "\" style='display:none;'>Open dialog</a> ");
        $("#tPushDialog").trigger('click');
        $('body').find('#tPushDialog').remove();
        $(document).on("pageshow","#"+option.dialog,function(){
        	if (typeof option.callback == 'function')
            	option.callback();
        })
    };
    isNull = function(s){
    	return s == "" || s === undefined || s === null;
    }
    
    openMsg = function(option,timeout){
    	$.mobile.loading('show', {  
	        text: option.text, //加载器中显示的文字  
	        textVisible: option.textVisible||true, //是否显示文字  
	        theme: option.theme||"b",        //加载器主题样式a-e  
	        textonly: option.textonly||false
	    }); 
		setTimeout(function(){
			$.mobile.loading('hide');  
		},timeout||1000);
    }
    
    $.del = function(des, url, data){
    	var html = '<div data-role="popup" data-history="false" id="popupDelDiv" data-overlay-theme="a"  data-theme="c" style="max-width:400px;" class="ui-corner-all">'+
    			   '<div data-role="header" data-theme="b" class="ui-corner-top">'+
    			   '<h1>提示</h1>'+
    			   '</div>'+
    			   '<div data-role="content" data-theme="d" class="ui-corner-bottom ui-content">'+
    			   '<p class="ui-title">'+des+'</p>'+
    			   '<a id="tDelOk" data-role="button" data-inline="true" data-transition="flow" data-theme="b" data-mini="true">删除</a> <a id="tDCancel" data-mini="true" data-role="button" data-inline="true"  data-theme="c">取消</a> </div>'+
    			   '</div>';
        if(!$("#popupDelDiv").length>0){
        	$(html).appendTo($.mobile.pageContainer).trigger("create");
        }
    	setTimeout(function(){
    		$("#popupDelDiv").popup().popup("open");
    		$("#popupDelDiv").find("#tDCancel").unbind("fastClick").bind("fastClick",function(){
    			$("#popupDelDiv").popup("close");
    		})
    		$("#popupDelDiv").find("#tDelOk").unbind("fastClick").bind("fastClick",function(){
    			$("#popupDelDiv").popup("close")
    			$.getJSON(url,data,function(data){
        			if(data.success){
        				Qm.refreshGrid();
        				openMsg({text:"删除成功!"},1000)
        			}else{
        				var msg = "删除失败！";
    					if (data.msg) {
    						msg += "<br>" + data.msg;
    					} else if (data.data) {
    						msg += "<br>" + data.data;
    					}
    					openMsg({text:msg},1000)
        			}
        		})
    		});
    	},100)
	}
})
//原始方式页面加载执行
jQuery.event.add(window, "load", function() {
	if(browser.versions.android){
		try{
			//如果有标题则用标题
			if($("title").length==1 && $("title").text())
				window.KHANJS.changeTitle($("title").text())
		}
		catch(e){}
	}
	var forms = $("form");
	if (forms.size() == 1) {
		forms.parseForm(1);
	}
});
$(document).on("pageshow",function(){
	var forms = $("form");
	if(forms.size()>0){
		forms.each(function(i,ele){
			 $("input,select,textarea", $(ele)).each(function() {
				var jinput = $(this);
				var attroptions = {};
		        if (jinput.attr("validate")) {
		            try {
		                attroptions = $.trim(jinput.attr("validate"));
		                if (attroptions.indexOf('{') < 0) {
		                    attroptions = "{" + attroptions + "}";
		                }
		                eval("attroptions = " + attroptions + ";");
		                if (attroptions.required && (!jinput.is(":hidden"))) {
		                }
		                if (attroptions) { //2013-5-25 下午2:18 lybide
		                    jinput.attr(attroptions);
		                    jinput.removeAttr("validate");
		                }
		                if (attroptions.required && (!jinput.is(":hidden"))) {
		                    ligerElementRequired(jinput, jinput.val()); //2013-6-21 下午4:03 lybide
		                }
		            } catch(e) {}
		        }
	         });
			 $(ele).validate();
		})
	}
	//格式化时间
	//$.datepicker.setDefaults({dateFormat: 'yy-mm-dd'})// - 全局设置日期选择插件的参数.
	//将系统所有包含有data-rel=back的按钮全部初始化为快速点击fastclick
	 $("a[data-rel=back]").unbind("fastClick").bind("fastClick",function(event){
		 $.mobile.back();
		 return false;
	 })
})
//必填项检查 2013-5-25 下午4:34 lybide
function ligerElementRequired(element, value, g, p) {
    var ti = element;
    required = ti.attr("required");
    if (required && !value) {
        ti.addClass("requiredstar");
    } else {
        ti.removeClass("requiredstar");
        ti.removeClass("l-text-invalid");
    }
};
