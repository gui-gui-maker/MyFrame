/**
 * kui nav 2.0
 *
 * http://www.khnt.com
 *
 * Author lybide 2013 [ lyb@khnt.com ]
 *
 */

var Nav = function (options, cookieId) {
    var g = this;
    this.options = $.extend({
        readerTo: "#nav1",
        iframe: "#rightFrame",
        toolbar: "#toolbar1",

        params:{},
        pageStatus: $("head").attr("pageStatus"),
        beforePrevClick: function (index, id) {
            return true
        },
        beforeNextClick: function (index, id) {
            return true
        },
        beforeClick: function (index, id) {
            return true
        },
        pages: [
//            {title:"",id:"nav1",selected:true,url:"",buttons:[{text:"test"}]}
        ],
        buttons: [
            {text: "上一步", icon: "nav-prev", id: "prev", click: function () {
                g.prevClick.call(g);
            }},
            {text: "下一步", icon: "nav-next", id: "next", click: function () {
                g.nextClick.call(g);
            }}
        ],
		divId:"nav1Pad",
		divClass:"process2",
        extButtons: []
    }, options);

    this.currentIndex = 0;
    this.currentIndexId = 0;
    this.cookiesId = cookieId || "";
    this.prevClick = function () {
        var g = this, p = g.options;
        if (p.beforePrevClick.call(g, g.currentIndex, p.pages[g.currentIndex]["id"])) {
            g.skip(--g.currentIndex);
        }
    };
    this.nextClick = function () {
        var g = this, p = g.options;
        if (p.beforeNextClick.call(g, g.currentIndex, p.pages[g.currentIndex ]["id"])) {
            if (g.currentIndex == p.pages.length - 1) {
                g.clearCookie();
            } else {
                g.skip(++g.currentIndex);
            }
        }
    };
    this.skipByCookies = function () {
        var g = this, p = this.options;
        var index = 0;
        if (g.cookiesId != "") {
            index = Cookies.getCookie(g.cookiesId);
            if ($.isNumeric(index)) {
                if (index >= p.pages.length) {
                    index = p.pages.length - 1;
                }
            } else {
                index = 0;
            }
        }
        if (index < p.pages.length) {
            g.skip(index);
        }
    }
    //设置cookies键值
    this.setCookiesId = function (id) {
        var g = this, p = this.options;
        if (id) g.cookiesId = id;
    }
    //清除cookies
    this.clearCookie = function () {
        var g = this, p = this.options;
        if (g.cookiesId != "")
            Cookies.setCookie(g.cookiesId, null);
    }
    //跳转到指定步骤
    this.skip = function (index) {
        if(top.$.dialog) {
            top.$.dialog.loading();
        }
        var g = this, p = g.options;
        if (index < 0)index = 0;
        if (index >= p.pages.length)index = p.pages.length - 1;
        $("#"+p.divId+" li").eq(index).addClass("active").siblings().removeClass("active");
        var url=p.pages[index]["url"];
        if(url.indexOf("?")==-1)url+="?";
        else url+="&";
        url+= $.param(p.params);
        $(p.iframe).attr("src", url).show();
		//setTimeout(function(){$(p.iframe).attr("src", url).show();},0)
        g.currentIndex = index;
        g.currentIndexId = p.pages[index]["id"];
        if (p.pageStatus != "detail") {
            var buttons=p.pages[index].buttons;
            $(p.toolbar).ligerButton({
                items: buttons
            });
            if (g.cookiesId != "") {
                Cookies.setCookie(g.cookiesId, index);
            }
            for(var i in buttons){
                if(buttons[i].id)
                    $.ligerui.get(buttons[i].id).set("disabled", true);
            }
            if (g.currentIndex == p.pages.length - 1) {
                $.ligerui.get("next").set("text", "完 成");
            }else{
                $.ligerui.get("next").set("text", "下一步");
            }

        }

    };
    this.setParams=function(params){
        var g = this, p = this.options;
        p.params=params;
    }
	this.getParams=function(){
		var p = this.options;
		return p.params;
	}
    this.init = function () {
        var g = this, p = this.options;
        $(p.iframe).bind('load', function () {
            if (p.pageStatus != "detail") {
                var buttons = p.pages[g.currentIndex].buttons;
                for (var i in buttons) {
                    if (buttons[i].id)
                        $.ligerui.get(buttons[i].id).set("disabled", false);
                }
                if (g.currentIndex == 0) {
                    $.ligerui.get("prev").set("disabled", true);
                }
            }
        }).hide();
        p.buttons = p.buttons.concat(p.extButtons || []);
        for (var i in p.pages) {
            var btns = p.pages[i].buttons || p.buttons;
            p.pages[i].buttons = btns.concat(p.pages[i].extButtons || []);
        }
        g.render();
        g.skipByCookies();
    }
    this.render=function(){
        var g = this, p = this.options;
        var html = '';
        for (var i = 0; i < p.pages.length; i++) {
            var jpi = p.pages[i];
            html += '<li index="' + i + '" indexId="' + jpi["id"] + '" href="' + jpi["url"] + '"><div><a><span>' + jpi["title"] + '</span></a></div></li>';
            if (jpi["selected"]) {
                g.currentIndex = i;
            }
            if (g.currentIndexId==jpi["id"]) {
                g.currentIndex = i;
                g.currentIndexId = jpi["id"];
            }
        }
		var divId= p.readerTo.replace(/#|\.|\s/ig,"");//2013-7-12 上午10:36 lybide
		p.divId=divId+"Pad";
        html = '<div id="'+ p.divId+'" class="'+ p.divClass+'"><ul>' + html + '</ul></div>';
        $(p.readerTo).html(html).find("#"+p.divId+" li").eq(g.currentIndex).addClass("active").siblings().removeClass("active");
        $(p.readerTo + " li").click(function () {
            var i = $(this).attr("index");
            if(g.currentIndex ==i){
                return;
            }
            if (p.beforeClick.call(g, i, p.pages[i]["id"]))
                g.skip(i);
        });
        if ($.ligerui.get("next")) {
            if (g.currentIndex == p.pages.length - 1) {
                $.ligerui.get("next").set("text", "完 成");
            } else {
                $.ligerui.get("next").set("text", "下一步");
            }
        }
    }
    this.addPage=function(i,page){
        var g = this, p = g.options;
        var btns = page.buttons || p.buttons;
        page.buttons = btns.concat(page.extButtons || []);
        var arr1=p.pages.slice(0,i);
        var arr2=p.pages.slice(i,p.pages.length);
        p.pages=arr1.concat([page],arr2);
        g.render();
    }
    this.removePage=function(i){
        var g = this, p = g.options;
        p.pages.splice(i,1);
        g.render();
    }
	this.getPages=function(){
		var g = this, p = g.options;
		return p.pages;
	}

    this.init();
}