var Qm={};
//Qm.config={};
var Ext = {};
Ext.onReady = $;

(function ($) {
	$.toJSON = typeof JSON === 'object' && JSON.stringify ? JSON.stringify : function (o) {
		var f = function (n) {
				return n < 10 ? '0' + n : n;
			},
			escapable = /[\\\"\x00-\x1f\x7f-\x9f\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g,
			quote = function (value) {
				escapable.lastIndex = 0;
				return escapable.test(value) ? '"' + value.replace(escapable,
					function (a) {
						var c = meta[a];
						return typeof c === 'string' ? c : '\\u' + ('0000' + a.charCodeAt(0).toString(16)).slice(-4);
					}) + '"' : '"' + value + '"';
			};
		if (o === null) return 'null';
		var type = typeof o;
		if (type === 'undefined') return undefined;
		if (type === 'string') return quote(o);
		if (type === 'number' || type === 'boolean') return '' + o;
		if (type === 'object') {
			if (typeof o.toJSON === 'function') {
				return $.toJSON(o.toJSON());
			}
			if (o.constructor === Date) {
				return isFinite(this.valueOf()) ? this.getUTCFullYear() + '-' + f(this.getUTCMonth() + 1) + '-' + f(this.getUTCDate()) + 'T' + f(this.getUTCHours()) + ':' + f(this.getUTCMinutes()) + ':' + f(this.getUTCSeconds()) + 'Z' : null;
			}
			var pairs = [];
			if (o.constructor === Array) {
				for (var i = 0,l = o.length; i < l; i++) {
					pairs.push($.toJSON(o[i]) || 'null');
				}
				return '[' + pairs.join(',') + ']';
			}
			var name, val;
			for (var k in o) {
				type = typeof k;
				if (type === 'number') {
					name = '"' + k + '"';
				} else if (type === 'string') {
					name = quote(k);
				} else {
					continue;
				}
				type = typeof o[k];
				if (type === 'function' || type === 'undefined') {
					continue;
				}
				val = $.toJSON(o[k]);
				pairs.push(name + ':' + val);
			}
			return '{' + pairs.join(',') + '}';
		}
	};
})(jQuery);

Qm.init=function(){
	//可在外部配置项
	Qm.config.splitPageSetTimeout=Qm.config.splitPageSetTimeout || 500;//分页延迟毫秒数
	getData(1);
	$(window).scrollTop(0);
}


var KM_L_LIST_DATA_INDEX=0;
function getData(page, scroller,loadElement) {
	//Qm.pagingUrlPre="qm?__method=";
	//Qm.config.listModel="listview";
	//Qm.config.listModelTpl="<a href=\"#\">Inbox <span class=\"ui-li-count\">12<\/span><\/a>";
	var rows = parseInt(Qm.config.pagesize);
	var start = (page - 1) * rows;
	var o = [];
	o.push({name: "pageid", value: Qm.config.pageid});
	o.push({name: "start", value: start});
	o.push({name: "pagesize", value: Qm.config.pagesize});
	o.push({name: "searchPara", value: $.toJSON(Qm.config.searchPara)});
	o.push({name: "defaultSearchCondition", value: $.toJSON(Qm.config.defaultSearchCondition)});
	o.push({name: "sessionAdvancedCondition", value:$.toJSON(Qm.config.sessionAdvancedCondition)});

	//$.getJSON("/"+Qm.pagingUrlPre + "q", o, function (res) {
	//http://192.168.0.50:8083/qm?__method=q&pageid=qm_mb_01&start=0&pagesize=20&searchPara=&defaultSearchCondition=%5B%5D&sessionAdvancedCondition=
	var base=$("base").attr("href")?$("base").attr("href"):"/";
	//var url1=base+ "qmNoSecurity?__method=q"+"&"+ jQuery.param(o);
	//$("#pa1").attr("href",url1);
	var url=base+ "qmNoSecurity?__method=q";//"xxx.php?x=111&r="+Math.random();
	$.ajax({
		url:url,
		data:o,
		dataType:"json",//XML、html、json、jsonp、script、text
		success:function(res,textStatus,jqXHR){
			//eval("var d="+data+"");
			//var d=data;
			var total = res["total"];
			Qm.config.pages = parseInt((total + Qm.config.pagesize - 1) / Qm.config.pagesize);
			Qm.config.currentPage = res.page;
			Qm.config.note = "第 " + Qm.config.currentPage + "/" + Qm.config.pages + " 页，共 " + total + " 条";
			$("#km-list-loading .text").html("正在加载："+Qm.config.note);
			Qm.config.data = res.rows;
			if (Qm.config.listModel == "listview") {
				var list = $("#__qm_list")//.empty();
				if (res.rows.length > 0) {
					for (var i = 0; i < res.rows.length; i++) {
						KM_L_LIST_DATA_INDEX++;
						var rows=$('<li data-index="' + KM_L_LIST_DATA_INDEX + '">' + nano(Qm.config.listModelTpl, res.rows[i]) + '</li>');
						rows.click(function(e){
							Qm.listClick(e,$(this));
						})
						list.append(rows);
					}
					//$("li", list).on("taphold", Qm.listClick);
					//$("li", list).bind("click",function(e){Qm.listClick(e,$(this));});
				} else {
					list.append('<li>没有数据！</li>');
				}
				//list.listview("refresh");
			} else {
				var list = $("#__qm_list tbody").empty();
				if (res.rows.length > 0) {
					var html = "";
					for (var i = 0; i < res.rows.length; i++) {
						html += '<tr data-index="' + i + '">';

						$.each(Qm.config.columnsInfo, function (index, item) {
							if (item["visible"] == "1") {
								html += '<td>' + res.rows[i][item["columm"]] + '</td>';
							}
						});

						html += '</tr>';
					}
					list.append(html);
					$("tr", list).on("taphold", Qm.listClick);
				} else {
					list.append('<li>没有数据！</li>');
				}
				$("#__qm_list").table("refresh");
			}
			if (loadElement) {
				loadElement.hide();
			}
			return;
		},
		error:function(XMLHttpRequest,textStatus,errorThrown){
			var eStr1="获取数据出错，请联系系统管理员";
			eStr1+=XMLHttpRequest+"===="+textStatus+"===="+errorThrown;
			alert(eStr1);
		}
	});
	return;
	/*$.getJSON(base+ "qmNoSecurity?__method=q", o, function (res) {
		var total = res["total"];
		Qm.config.pages = parseInt((total + Qm.config.pagesize - 1) / Qm.config.pagesize);
		Qm.config.currentPage = res.page;
		Qm.config.note = "第 " + Qm.config.currentPage + "/" + Qm.config.pages + " 页，共 " + total + " 条";
		Qm.config.data = res.rows;
		if (Qm.config.listModel == "listview") {
			var list = $("#__qm_list")//.empty();
			if (res.rows.length > 0) {
				for (var i = 0; i < res.rows.length; i++) {
					KM_L_LIST_DATA_INDEX++;
					var rows=$('<li data-index="' + KM_L_LIST_DATA_INDEX + '">' + nano(Qm.config.listModelTpl, res.rows[i]) + '</li>');
					rows.click(function(e){
						Qm.listClick(e,$(this));
					})
					list.append(rows);
				}
				//$("li", list).on("taphold", Qm.listClick);
				//$("li", list).bind("click",function(e){Qm.listClick(e,$(this));});
			} else {
				list.append('<li>没有数据！</li>');
			}
			//list.listview("refresh");
		} else {
			var list = $("#__qm_list tbody").empty();
			if (res.rows.length > 0) {
				var html = "";
				for (var i = 0; i < res.rows.length; i++) {
					html += '<tr data-index="' + i + '">';

					$.each(Qm.config.columnsInfo, function (index, item) {
						if (item["visible"] == "1") {
							html += '<td>' + res.rows[i][item["columm"]] + '</td>';
						}
					});

					html += '</tr>';
				}
				list.append(html);
				$("tr", list).on("taphold", Qm.listClick);
			} else {
				list.append('<li>没有数据！</li>');
			}
			$("#__qm_list").table("refresh");
		}
		if (loadElement) {
			loadElement.hide();
		}
		return;
		var temp = scroller;
		if (temp) {
			temp.iscrollview.refresh();
		} else {
			try{
				$(".iscroll-wrapper").iscroll("refresh");
			}catch(e){}
		}
	});*/
}

function nano(template, data) {
	return template.replace(/\{([\w\.]*)\}/g, function(str, key) {
		var keys = key.split("."),
			v = data[keys.shift()];
		for (var i = 0, l = keys.length; i < l; i++) v = v[keys[i]];
		return (typeof v !== "undefined" && v !== null) ? v : "";
	});
}

var KM_L_LIST_PAGE=0;
$(function () {
	var loadElement=$("<div class='km-list-loading' id='km-list-loading'><div class='mask'></div><div class='text'>正在加载数据……</div></div>");
	$("body").append(loadElement);
	var ONEEXE1__1;
	var scrollCount=1;
	$(window).scroll(function () {

		//$(window).scrollTop()这个方法是当前滚动条滚动的距离
		//$(window).height()获取当前窗体的高度
		//$(document).height()获取当前文档的高度
		var bot = 0; //bot是底部距离的高度
		if ((bot + $(window).scrollTop()) >= ($(document).height() - $(window).height())) {
			KM_L_LIST_PAGE++;
			//console.log(Qm.config.currentPage,scrollCount);
			//当底部基本距离+滚动的高度〉=文档的高度-窗体的高度时；
			//我们需要去异步加载数据了
			//$.getJSON("url", { page: "2" }, function (str) { alert(str); });

			if (Qm.config.currentPage<scrollCount) {
				return;
			}
			loadElement.show();
			if (Qm.config.currentPage == Qm.config.pages) {
				loadElement.hide();
				return;
			}
			ONEEXE1__1=setTimeout(function(){
				doSrcollGetData(loadElement);
				//clearTimeout(ONEEXE1__1);
			},Qm.config.splitPageSetTimeout);
			scrollCount++;
		}
	});
});

function doSrcollGetData(loadElement) {
	var page = countPage(1);
	getData(page,null,loadElement);
};

function countPage(n) {
	if (!Qm.config.currentPage) {
		Qm.config.currentPage = 1;
	}
	var page = parseInt(Qm.config.currentPage);
	page = isNaN(page) ? 1 : page;
	page += n;
	if (page < 1)page = 1;
	if (page > Qm.config.pages)page = Qm.config.pages;
	Qm.config.currentPage = page;
	return page;
}