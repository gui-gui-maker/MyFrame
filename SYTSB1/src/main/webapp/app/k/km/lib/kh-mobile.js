
var _JSON_PARSE=JSON.parse;
JSON.parse=function(a){
	try {
		return _JSON_PARSE(a + "");
	}catch(e){
		return eval("("+a+")");
	}
}

//弹出窗 2014年05月22日 13:33:08 星期四 lybide
function dialogShow(element, w, h, fun, closebKey) {
	//qiExplainContent
	var _aqiExplain = $("#aqiExplain");
	var _dialogMask = $("#dialogMask");

	if (element) {
		$("#aqiExplainContent").html(element);
	}
	//2014年05月22日 10:22:14 星期四 lybide
	var w1 = w || $(window).width();
	var h1 = h || $(window).height();
	var w2 = w1 - 0;
	var h2 = h1 - 0;
	//_aqiExplain.css({"width":w2,"height":h2,margin:"-"+(h2/2)+"px 0px 0px -"+(w2/2)+"px"});
	_aqiExplain.css({
		"width": w2,
		"height": h2,
		"margin": "-" + (h2 / 2) + "px 0px 0px -" + (w2 / 2) + "px"
	});
	//_aqiExplain.find(".aqi-explain-wrap").height(h2-30-50);
	_aqiExplain.show();
	closebKey = closebKey == false ? false : true;
	_dialogMask.show();
	if (closebKey) {
		$(".cclose", _aqiExplain).show();
		$("#aqiExplainCClose").unbind("click").click(function(e) {
			_aqiExplain.hide();
			_dialogMask.hide();
			if (typeof fun == "function") {
				fun.call(this, e);
			}
		});
	} else {
		$(".cclose", _aqiExplain).hide();
	}
	$("#aqiExplainCClose").focus();
	return _aqiExplain;
}

function dAlert(str) {
	dialogShow("<div class='msg'>"+str+"</div>",300,110);
}

//关闭弹出窗
function dialogClose() {
	$("#aqiExplain").hide();
	$("#dialogMask").hide();
}

window.addEventListener("onorientationchange" in window ? "orientationchange" : "resize", function(event) {
	event.stopPropagation();
	event.cancelBubble = true;
	if (window.orientation === 180 || window.orientation === 0) {
		//alert('竖屏状态！');
	}
	if (window.orientation === 90 || window.orientation === -90) {
		//alert('横屏状态！');
	}
}, false);

function isWeiXin() {
	var ua = window.navigator.userAgent.toLowerCase();
	if (ua.match(/MicroMessenger/i) == 'micromessenger') {
		return true;
	} else {
		return false;
	}
}

$(function() {
	singlePageNav();
	var bb = window.location.hash;
	if (bb) {
		$(bb).show();
	} else {
		$("#wrapper > section:first").show();
	}
	//cSize();

	/*if(isWeiXin()){
		//<h1>如果用微信浏览器打开可以看到下面的文字</h1>
		var p = document.getElementsByTagName('p');
		p[0].innerHTML = window.navigator.userAgent;
	}*/
	pageCreateElement();
	//console.log($(document).height() + $(window).height());
	var bH=$(document).height();
	var wH=$(window).height();
	if (bH>wH) {
		$("#dialogMask").height(bH);
	}
});
function cSize() {
	if ($("#wrapper").height()>$(window).height()) {
		$(".container").addClass("c-fixed");
		$(".container > .bt1").addClass("b-fixed");
	}
}

function pageShow(hash) {
	$("#wrapper > section").hide();
	$(hash).show();//.addClass("seshow");
	window.location.hash = hash;
}

window.onhashchange=function(){
	var hash = window.location.hash;
	//var hash = location.hash.substr(1);
	if (hash == '') {
		return;
	}
	pageShow(hash);
}

function homeFullScreen() {
	var homeSection = $('.home');
	var windowHeight = $(window).outerHeight();
	if (homeSection.hasClass('home-fullscreen')) {
		$('.home-fullscreen').css('height', windowHeight);
	}
}

function pageCreateElement() {
	var str='';
	str+='<div class="aqi-explain" id="aqiExplain">';
	str+='<div class="bclose" id="aqiExplainBClose"></div>';
	str+='<div class="aqi-explain-wrap" id="aqiExplainContent">';
	str+='</div>';
	str+='<div class="cclose">';
	str+='	<div class="cclose-wrap"><button type="button" value="" class="button button-raised button-rounded button-primary button-pill button-small" id="aqiExplainCClose">关闭</button></div>';
	str+='</div>';
	str+='</div>';
	str+='<div class="dialog-mask" id="dialogMask"></div>';
	$("body").append(str);
	$("[ext_isNull=1]").addClass("bitian");
}

function filterPath(string) {
	return string.replace(/^\//, '').replace(/(index|default).[a-zA-Z]{3,4}$/, '').replace(/\/$/, '');
}

function singlePageNav(fw) {

	var ews=fw || "body";

	$('a[href*=#]',ews).each(function() {
		if (filterPath(location.pathname) == filterPath(this.pathname) && location.hostname == this.hostname && this.hash.replace(/#/, '')) {
			var $targetId = $(this.hash),
				$targetAnchor = $('[name=' + this.hash.slice(1) + ']');
			var $target = $targetId.length ? $targetId : $targetAnchor.length ? $targetAnchor : false;

			//if ($target) {
				$(this).click(function() {
					var _this=$(this);
					if (_this.attr("data-openhtml")) {
						openHtml(_this,_this.attr("data-openhtml"));
						return false;
					}
					$("#wrapper > section").hide();
					$targetId.show();
				});
			//}
		}
	});
}

//2015年08月27日 13:55:05 星期四 lybide
function openHtml(obj, url) {
	var _this = $(obj); //alert(_this.attr("href"))
	var ids = _this.attr("href");
	if ($(ids).size()>0) {
		pageShow(ids);
		return;
	}
	var bb = $.get(url, function(data) {
		//alert(data);
		$("#wrapper").append(data);
		singlePageNav(ids)
		pageShow(ids);
	});
	return false;
}

//=======================================================================================================================================

function ckFormElement(objarr) {
	var idAll = objarr; //["#tel1","#pass1","#yzcode1"]
	for (var i = 0, l = idAll.length; i < l; i++) {
		var input = idAll[i];
		input = $(input);
		if (input.val() == "") {
			try {
				//input.focus();
				//alert(input.attr("ext_name")+"不能为空");
				dialogShow("<div class='msg'>" + input.attr("ext_name") + "不能为空</div>", 300, 110, function(e) {
					input.focus();
				});
				return false;
			} catch (e) {}
		}
	}
	return true;
}

//检查整个form表单
function ckForm(fObj) {
	var idAll=fObj.find("[ext_isNull=1]");
	for (var i = 0, l = idAll.length; i < l; i++) {
		var input = idAll.eq(i);
		//input = $(input);
		if ((input.val() || input.text()) == "") {
			try {
				//input.focus();
				//alert(input.attr("ext_name")+"不能为空");
				dialogShow("<div class='msg'>" + input.attr("ext_name") + "不能为空</div>", 300, 110, function(e) {
					input.focus();
				});
				return false;
			} catch (e) {}
		}
	}
	return true;
}

var idCardNoUtil = {
	provinceAndCitys: {
		11: "北京",
		12: "天津",
		13: "河北",
		14: "山西",
		15: "内蒙古",
		21: "辽宁",
		22: "吉林",
		23: "黑龙江",
		31: "上海",
		32: "江苏",
		33: "浙江",
		34: "安徽",
		35: "福建",
		36: "江西",
		37: "山东",
		41: "河南",
		42: "湖北",
		43: "湖南",
		44: "广东",
		45: "广西",
		46: "海南",
		50: "重庆",
		51: "四川",
		52: "贵州",
		53: "云南",
		54: "西藏",
		61: "陕西",
		62: "甘肃",
		63: "青海",
		64: "宁夏",
		65: "新疆",
		71: "台湾",
		81: "香港",
		82: "澳门",
		91: "国外"
	},
	powers: ["7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7", "9", "10", "5", "8", "4", "2"],
	parityBit: ["1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"],
	genders: {
		male: "男",
		female: "女"
	},
	checkAddressCode: function(addressCode) {
		var check = /^[1-9]\d{5}$/.test(addressCode);
		if (!check) return false;
		if (idCardNoUtil.provinceAndCitys[parseInt(addressCode.substring(0, 2))]) {
			return true;
		} else {
			return false;
		}
	},
	checkBirthDayCode: function(birDayCode) {
		var check = /^[1-9]\d{3}((0[1-9])|(1[0-2]))((0[1-9])|([1-2][0-9])|(3[0-1]))$/.test(birDayCode);
		if (!check) return false;
		var yyyy = parseInt(birDayCode.substring(0, 4), 10);
		var mm = parseInt(birDayCode.substring(4, 6), 10);
		var dd = parseInt(birDayCode.substring(6), 10);
		var xdata = new Date(yyyy, mm - 1, dd);
		if (xdata > new Date()) {
			return false; //生日不能大于当前日期
		} else if ((xdata.getFullYear() == yyyy) && (xdata.getMonth() == mm - 1) && (xdata.getDate() == dd)) {
			return true;
		} else {
			return false;
		}
	},
	getParityBit: function(idCardNo) {
		var id17 = idCardNo.substring(0, 17);
		var power = 0;
		for (var i = 0; i < 17; i++) {
			power += parseInt(id17.charAt(i), 10) * parseInt(idCardNoUtil.powers[i]);
		}
		var mod = power % 11;
		return idCardNoUtil.parityBit[mod];
	},
	checkParityBit: function(idCardNo) {
		var parityBit = idCardNo.charAt(17).toUpperCase();
		if (idCardNoUtil.getParityBit(idCardNo) == parityBit) {
			return true;
		} else {
			return false;
		}
	},
	checkIdCardNo: function(idCardNo) {
		//15位和18位身份证号码的基本校验
		var check = /^[0-9]{15}|([0-9]{17}([0-9]|x|X))$/.test(idCardNo);
		if (!check) return false;
		//判断长度为15位或18位
		if (idCardNo.length == 15) {
			return idCardNoUtil.check15IdCardNo(idCardNo);
		} else if (idCardNo.length == 18) {
			return idCardNoUtil.check18IdCardNo(idCardNo);
		} else {
			return false;
		}
	},
	//校验15位的身份证号码
	check15IdCardNo: function(idCardNo) {
		//15位身份证号码的基本校验
		//在JavaScript中，正则表达式只能使用"/"开头和结束，不能使用双引号
		var check = /^[1-9][0-9]{7}((0[1-9])|(1[0-2]))((0[1-9])|([1-2][0-9])|(3[0-1]))[0-9]{3}$/.test(idCardNo);
		if (!check) return false;
		//校验地址码
		var addressCode = idCardNo.substring(0, 6);
		check = idCardNoUtil.checkAddressCode(addressCode);
		if (!check) return false;
		var birDayCode = '19' + idCardNo.substring(6, 12);
		//校验日期码
		return idCardNoUtil.checkBirthDayCode(birDayCode);
	},
	//校验18位的身份证号码
	check18IdCardNo: function(idCardNo) {
		//18位身份证号码的基本格式校验
		//在JavaScript中，正则表达式只能使用"/"开头和结束，不能使用双引号
		var objReg = /^[1-9][0-9]{5}[1-9][0-9]{3}((0[1-9])|(1[0-2]))((0[1-9])|([1-2][0-9])|(3[0-1]))[0-9]{3}([0-9]|x|X)$/i;
		var check = objReg.test(idCardNo);
		if (!check) return false;
		//校验地址码
		var addressCode = idCardNo.substring(0, 6);
		check = idCardNoUtil.checkAddressCode(addressCode);
		if (!check) return false;
		//校验日期码
		var birDayCode = idCardNo.substring(6, 14);
		check = idCardNoUtil.checkBirthDayCode(birDayCode);
		if (!check) return false;
		//验证校检码
		return idCardNoUtil.checkParityBit(idCardNo);
	},
	formateDateCN: function(day) {
		var yyyy = day.substring(0, 4);
		var mm = day.substring(4, 6);
		var dd = day.substring(6);
		return yyyy + '-' + mm + '-' + dd;
	},
	//获取信息
	getIdCardInfo: function(idCardNo) {
		var idCardInfo = {
			gender: "",
			//性别
			birthday: "" // 出生日期(yyyy-mm-dd)
		};
		if (idCardNo.length == 15) {
			var aday = '19' + idCardNo.substring(6, 12);
			idCardInfo.birthday = idCardNoUtil.formateDateCN(aday);
			if (parseInt(idCardNo.charAt(14)) % 2 == 0) {
				idCardInfo.gender = idCardNoUtil.genders.female;
			} else {
				idCardInfo.gender = idCardNoUtil.genders.male;
			}
		} else if (idCardNo.length == 18) {
			var aday = idCardNo.substring(6, 14);
			idCardInfo.birthday = idCardNoUtil.formateDateCN(aday);
			if (parseInt(idCardNo.charAt(16)) % 2 == 0) {
				idCardInfo.gender = idCardNoUtil.genders.female;
			} else {
				idCardInfo.gender = idCardNoUtil.genders.male;
			}
		}
		return idCardInfo;
	},
	getId15: function(idCardNo) {
		if (idCardNo.length == 15) {
			return idCardNo;
		} else if (idCardNo.length == 18) {
			return idCardNo.substring(0, 6) + idCardNo.substring(8, 17);
		} else {
			return null;
		}
	},
	getId18: function(idCardNo) {
		if (idCardNo.length == 15) {
			var id17 = idCardNo.substring(0, 6) + '19' + idCardNo.substring(6);
			var parityBit = idCardNoUtil.getParityBit(id17);
			return id17 + parityBit;
		} else if (idCardNo.length == 18) {
			return idCardNo;
		} else {
			return null;
		}
	}
};
//验证护照是否正确
function checknumber(number) {
	var str = number;
	//在JavaScript中，正则表达式只能使用"/"开头和结束，不能使用双引号
	var Expression = /(P\d{7})|(G\d{8})/;
	var objExp = new RegExp(Expression);
	if (objExp.test(str) == true) {
		return true;
	} else {
		return false;
	}
};

//验证日期
function RQcheck(RQ) {
	var date = RQ;
	var result = date.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);

	if (result == null) return false;
	var d = new Date(result[1], result[3] - 1, result[4]);
	return (d.getFullYear() == result[1] && (d.getMonth() + 1) == result[3] && d.getDate() == result[4]);

}

function isInteger(obj) {
	reg = /^[-+]?\d+$/;
	if (!reg.test(obj)) {
		$("#test").html("<b>Please input correct figures</b>");
	} else {
		$("#test").html("");
	}
}

function isEmail(obj) {
	reg = /^\w{3,}@\w+(\.\w+)+$/;
	if (!reg.test(obj)) {
		$("#test").html("<b>请输入正确的邮箱地址</b>");
	} else {
		$("#test").html("");
	}
}

function isString(obj) {
	reg = /^[a-z,A-Z]+$/;
	if (!reg.test(obj)) {
		$("#test").html("<b>只能输入字符</b>");
	} else {
		$("#test").html("");
	}
}

function isTelephone(obj) {
	reg = /^(\d{3,4}\-)?[1-9]\d{6,7}$/;
	if (!reg.test(obj)) {
		$("#test").html("<b>请输入正确的电话号码！</b>");
	} else {
		$("#test").html("");
	}
}

function isMobile(obj) {
	reg = /^(\+\d{2,3}\-)?\d{11}$/;
	if (!reg.test(obj)) {
		return false;
	} else {
		return true;
	}
}

function isUri(obj) {
	reg = /^http:\/\/[a-zA-Z0-9]+\.[a-zA-Z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^<>\"\"])*$/;
	if (!reg.test(obj)) {
		$("#test").html($("#uri").val() + "请输入正确的inernet地址");
	} else {
		$("#test").html("");
	}
}

//=============================================================================

(function ($) {
	$.getFormatDate = function(date, dateformat) {
		if (isNaN(date)) {
			if (typeof date === "string") {
				date = new Date(date.replace(/-/g, "/"));
			}
		} else {
			date = new Date(date);
		}
		if (!date instanceof Date) return null;
		var format = dateformat || "yyyy-MM-dd";
		var o = {
			"M+": date.getMonth() + 1,
			"d+": date.getDate(),
			"h+": date.getHours(),
			"m+": date.getMinutes(),
			"s+": date.getSeconds(),
			"q+": Math.floor((date.getMonth() + 3) / 3),
			"S": date.getMilliseconds()
		}
		if (/(y+)/.test(format)) {
			format = format.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
		}
		for (var k in o) {
			if (new RegExp("(" + k + ")").test(format)) {
				format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
			}
		}
		return format;
	}
	$.fn.initForm=function(options){
		var $this = $(this);
		$this.data("formOptions", options);
		//$("body").mask("正在初始化数据，请稍候……");
		//var $this = $(this);
		var options = $this.data("formOptions");
		$this.parseFormSingle(options,1);//alert("渲染单个form表单");
	}
	$.fn.parseFormSingle = function(options,key) {
		var $this = $(this);
		var config = c = $.extend({
			transformOnly: false,
			autoClose: $("form").size() == 1,
			status: $this.attr("pageStatus") || $("head").attr("pageStatus") || "edit",
			action: $this.attr("action"),
			actionParam: {},
			afterParse: function(formObj) { //form表单完成渲染后，回调函数
			},
			getSuccess: function() {},
			success: function(data) {}
		}, options);

		if (!(config.getAction == "" || config.getAction === undefined || config.getAction === null)) {
			$this.attr("getAction",config.getAction);
		}
		$this.data("initForm", true);
		var status = config.status;
		$this.transform(status);
		
		if (status == "edit" || status == "modify" || status == "detail") {
			$this.setValues(config.getSuccess);
		}
		config.afterParse($this);
	}
	$.fn.setValues = function(obj, onSuccess, bxpepei) {
		if (!obj) {
			//$("body").unmask();
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
							if (status == "detail") {
								$.fn.setDivValues(data.data, this.$this);
							} else {
								$.fn.setValues.setWithData(data.data, this.$this);
							}
						} else {
							var msg = data.msg || data.data;
						}
						$this.addClass("mm-"+status);//2015年09月22日 17:07:03 星期二 lybide
						//$("body").unmask();
						suc(data);
					},
					error: function(XMLHttpRequest, textStatus, errorThrown) {
						alert([XMLHttpRequest, textStatus, errorThrown]);
						//$("body").unmask();
					}
				});
			} else { 
				//$("body").unmask();
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
			if(ele.is(":hidden")||ele.is(":password")||ele.is(":text")||ele.is("textarea")){
				if(data[name]===undefined){
					data[name] = ele.val();
				}else{
					if (!$.isArray(data[name])) {
						data[name] = [data[name]];
					}
					data[name].push(ele.val());
				}
			} else {
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

	$.isNull = function(s){
		return s == "" || s === undefined || s === null;
	}

	$.parseName=function(name, data) {
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

	$.fn.setDivValues = function(data, t) {
		if (data == undefined) return;
		$("div.input", t).each(function() {
			var ele = $(this);
			var name = ele.attr("name");
			if (!name) return;
			var v = $.parseName(name, data);
			if (v === undefined||v==null) return;
			var tempvalue="";
			if(!$.isNull(ele.attr("codeValue"))){
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
				$(this).setDivValue($.isNull(tempvalue)?tempvalue:tempvalue.substring(0, tempvalue.length-1));
			}else{
				$(this).setDivValue(v);
			}
			
		});

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
			var ltype = ele.attr("type");
			var name = ele.attr("name");
			if (!name) return;
			var v = $.parseName(name, data);
			if (v === null) v = "";
			if (v == undefined && bxpepei) { 
				return;
			}
			//通过jquery val获取值
			if(ele.is(":hidden")||ele.is(":password")||ele.is(":text")||ele.is("textarea")||ele.is("select")||ele.is("[type=date]")||ele.is("[type=time]")){
				if (v !== undefined) {
					//2015年09月22日 16:22:54 星期二 lybide
					if (ele.is("[type=date]")) {
						v=$.getFormatDate(v);
					}
					if (ele.is("[type=time]")) {
						v=$.getFormatDate(v,"hh:mm");
					}
					ele.val(v);
					//ele.textinput();
				}
				return;
			}
		});
	};
	
	$.fn.transform = function(status) {
		var $this = $(this);
        $this.attr("pageStatus",status);
		if (status == "detail") {
			$(":input", $this)
			//.not(":hidden")
			.not("[type=hidden]")//todo 解决tab第二或以上input框不初始化的效果 13-5-2 下午10:45 lybide
			.each(
				function() {
					var jinput = $(this);
					var initValue = jinput.val();
					var ltype = jinput.attr("type") || (jinput.is("select")?"select":"");
					var id = jinput.attr("id");
					var name = jinput.attr("name");
					//console.log(jinput,initValue,ltype,id,name);//调试信息
					//jinput.parent().nextAll().hide();

					

					div = $("<div class='input' xtype='" + ltype
								+ "' name='" + name + "'"
								+ (id ? (" id='" + id + "'") : "")
								+ ">&nbsp;</div>");

					if (ltype == "select") {
						var op=jinput.find("option");
						var bb={};
						op.each(function(i){
							var _this=$(this);
							bb[_this.attr("value")]=_this.text();
						})
						div.data("data",bb);
					}

					jinput.replaceWith($("<div class='input-warp'></div>").wrapInner(div));
					

					return;					

					var ligerui = parseLigerui(jinput);
					if (ligerui)
						initValue = initValue || ligerui.initValue;

					if (ltype == "select") {
						if (ligerui.textModel)
							ltype = "text";
						else if (ligerui.valueFieldID)
							name = ligerui.valueFieldID;
					}
					var div, ele;
					/*if (ligerui && ligerui.suffixDetail) {//2013-5-10 下午2:16 lybide
						ligerui.suffix=ligerui.suffixDetail;
					}*/
					if (ligerui && ligerui.suffix) {
						div = $("<table border='0' class='l-text-suffix-wrap'><tr><td class='l-text-left'><div class='input' xtype='"
								+ ltype
								+ "' name='"
								+ name
								+ "'"
								+ (id ? (" id='" + id + "'") : "")
								+ ">&nbsp;</div></td><td class='l-text-suffix'"
								+ (ligerui.suffixWidth ? " style='width:"
										+ ligerui.suffixWidth + "px'"
										: "")
								+ ">"
								+ ligerui.suffix
								+ "</td></tr></table>");
						ele = $(".input", div);
					} else {
						div = $("<div class='input' xtype='" + ltype
								+ "' name='" + name + "'"
								+ (id ? (" id='" + id + "'") : "")
								+ ">&nbsp;</div>");
						if (ligerui && ligerui.lineWrap === false)
							div.addClass("lineWrap");
						ele = div;
					}

					switch (ltype) {
					case "radioGroup":
						bindData(ele, ligerui);
						break;
					case "checkboxGroup":
						bindData(ele, ligerui);
						break;
					case "select":
						bindData(ele, ligerui);
						break;
					case "combobox":
						bindData(ele, ligerui);
						break;
					case "date": {
						div.data("format", "yyyy-MM-dd");
						if (ligerui && ligerui.format) {
							ele.data("format", ligerui.format);
						}
					}
						break;
					case "text":
					case "spinner":
						bindData(ele, ligerui);
						break;
					case "float":
					case "number":
					case "int":
					case "digits":
						break;
					default: {
						if (!jinput.is("textarea")) {
							jinput.hide();
							return;
						}
					}
						break;
					}
					if (initValue!="" && initValue!==undefined) {
						ele.setDivValue(initValue);
					}
					jinput.replaceWith($("<div class='input-warp'></div>").wrapInner(div));
				}
			);
		} else {
			//$this.ligerForm();
		}
		
	}

	$.fn.setDivValue = function(v) {
		var ele = $(this);
		var xtype = ele.attr("xtype");
		//        var name = ele.attr("name");
		//        if (!name)return;
		//        var v = parseName(name, data);
		//        if (v === undefined) return;
		if (v === null) {
			ele.html("&nbsp;");
			return;
		}
		if (xtype == "combobox" || xtype == "select" || xtype == "checkboxGroup" || xtype == "radioGroup") {
			/*var ligerData = ele.data("ligerui");
			if (ligerData.tree) {
				ligerData.data = ligerData.tree.data || ligerData.data;
			}*/
			var bindData = ele.data("data");
			if (bindData) {
				v = bindData[v];
			}
		} else if (xtype == "date"||xtype == "time") {
			/*if (isNaN(v)) {
				var flength = ele.data("format").length;
				if (v.length > flength) {
					v = v.substring(0, flength);
				}
			} else {
				v = $.kh.getFormatDate(new Date(v), ele.data("format"));
			}*/
			if (xtype == "date") {
				v=$.getFormatDate(v);
			}
			if (xtype == "time") {
				v=$.getFormatDate(v,"hh:mm");
			}
		} else if (xtype == "spinner") { //2013-9-12 下午4:21 lybide
			/*var ligerData = ele.data("ligerui");
			///todo modify by jyl
			if(v!=''&&v!=null&&v!=undefined){
				if (ligerData.type == "int") {
					v = parseInt(v);
				} else if (ligerData.type == "float") {
					v = new Number(v).toFixed(ligerData.decimalplace || 2);
				}
			}*/
		}
		if (isNaN(v)) {
			ele.html(v == "" ? "&nbsp;": v.replace(/\n/g, "<br>"));
		} else {
			ele.html(v + "&nbsp;");
		}

	};
	
})(jQuery);