/* 大图标下的小图标菜单圆弧效果函数 2016年10月25日 14:44:29 星期二 */

/*
* 1 MENUOPEN配置为false;
* 2 需要用户配置项 参考 http://doc.khnt.com/i-kdoc12-123.html
* */

$(function(){
		
		//2016年08月16日 16:04:12 星期二 lybide 读取菜单数据，并生成菜单
		$.getJSON("app/bigdata/data/dd-cool-show-menu.json", function(data){
			$.each(data.items, function(i,item){
				var obj=$("#mw-menu-"+item["id"]+"");
				if (item["disabled"]) {
					obj.addClass("icodisabled");
				}
				if (obj.size()>0) {
					obj.find(".icon-txt").html(item["text"]);
					if(item["fontIcon"]) {
						icon=item["fontIcon"];
					} else if (item["icon"]) {
						icon='<img src="'+item["icon"]+'" border="0"/>';
					}
					obj.find(".icon-pic").html((icon));
					obj.append('<div class="tooltip02"><ul id="mw-menu-'+item["id"]+'-wrap"></ul><div class="arrow"></div></div>');
					if (item["url"]) {
						obj.find(">a").attr("href",item["url"]);
					}
					var wrap=$("#mw-menu-"+item["id"]+"-wrap");
					var menu=item["menu"];
					for (var j=0 in menu) {
						var mItem=menu[j];
						var str1="";
						var icon="";
						if(mItem["fontIcon"]) {
							icon=mItem["fontIcon"];
						} else if (mItem["icon"]) {
							icon='<img src="'+mItem["icon"]+'" border="0"/>';
						}
						//str1+='<li><a href=""><i class="icon_list iconfont">&#xe600;</i>综合社会救助</a></li>';
						str1+="<li id='mw-menu-"+mItem["id"]+"-item'><a href='"+mItem["url"]+"'><i class='icon_list iconfont'>"+(icon)+"</i><span>"+mItem["text"]+"</span></a></li>"
						wrap.append(str1);
					}
				}
			});
			art();
		});
	});


//这段代码是弹出菜单子系统的弧形效果
function art(){
	var bc = BROWSER_INFO;
	var ie = bc.ie,
	xt = bc.system,
	xtms = bc.systemx,
	dm = bc.docMode,
	iever = bc.ieversion;
	if (ie) {
		if (ie && parseFloat(iever) <= 8) { //浏览器检测 2012年06月19日 星期二 14:04:54 lybide
			var ul = $(".iconboxes > div").removeClass("tooltip02").addClass("tooltip");
			return;
		}; //alert([iever,dm])
		if (ie && (parseInt(iever) > 6 && parseInt(iever) < 11) && (7 > parseInt(dm))) {
			
		}
	}
	//wSzie2();
	var ul = $(".iconboxes");
	var zindex1 = 200;
	//var li = $(".tooltip li");
	var swss_oneExe1;
	ul.mouseover(function() {
		clearTimeout(swss_oneExe1);
		$(".iconboxes .tooltip02").css("visibility","hidden");
		$(".iconboxes .tooltip02 li").removeAttr('style');
		var _this=$(this);
		var li=_this.find("li");
		var i = li.length;
		var n = i - 1;
		var r = 100;
		var avg=18;
		var begin=-2;
		if(i%2==0){
			avg*=2;
			begin=-i+1;
		}else{
			begin=-(parseInt(i/2))
		}
		if(i<=3){
			r=100;
		}else{
			r=125;
		}

		// if(_this.hasClass("ico_01")) {
		// 	r = 100;
		// }
		// if(_this.hasClass("ico_02")) {
		// 	r = 130;
		// 	//begin=0;
		// }
		// if(_this.hasClass("ico_03")) {
		// 	r = 140;
		// }
		// if(_this.hasClass("ico_04")) {
		// 	//begin=0;
		// }
		// if(_this.hasClass("ico_05")) {
		// 	r = 130;
		// 	//begin=0;
		// }
		// if(_this.hasClass("ico_06")) {
		// 	r = 120;
		// 	//begin=-2;
		// }
		// if(_this.hasClass("ico_07")) {
		// 	//begin=9;
		// }
		if(_this.hasClass("ico_09")) {
			// r = 130;
			begin=8;
		}
		// if(_this.hasClass("ico_09")) {
		// 	r = 130;
		// 	//begin=-1;
		// }
		var w1=$(window).width();
		var h1=$(window).height();

		
		/*if (w1 <= 1024) {
			r = 120;
			if(_this.hasClass("icobig")) {
				r = 140;
			}
			if(_this.hasClass("ico_06")) {
				r = 140;
			}
		}
		if (w1 >1024 && w1 <= 1280) {
			r = 120;
			if(_this.hasClass("icobig")) {
				r = 140;
			}
			if(_this.hasClass("ico_06")) {
				r = 140;
			}
		}
		if (w1 > 1280 && w1 <= 1366) {
			r = 90;
			if(_this.hasClass("icobig")) {
				r = 120;
			}
			if(_this.hasClass("ico_06")) {
				r = 120;
			}
		}*/
		if (w1 > 1366 && w1 <= 1440) {
			if(i<=3){
				r=105;
			}else{
				r=125;
			}
		}
		//if (w1 > 1440 && w1 <= 1600) {
		//}
		//if (w1 > 1600 && w1 <= 1920) {
		//}
		if (w1 >= 1920) {
			if(i<=3){
				r=120;
			}else{
				r=150;
			}
		}
		//alert(hudu)
		var ul=_this.find(".tooltip02");
		//_this.toggleClass('active');
		//if ($(this).hasClass('active')) {
			ul.css("visibility","visible");
			ul.parent(".iconboxes").css("z-index",zindex1++);
		//为了保持对称，偶数个数时间隔显示
			var ct=i%2==0?2:1;
			for (var b = 0; b < i; b++) {
				var a=b*ct;
				li.eq(b).css({
					'transition-delay': "" + (30 * b) + "ms",
					'-webkit-transition-delay': "" + (30 * b) + "ms",
					'-o-transition-delay': "" + (30 * b) + "ms",
					'-ms-transition-delay': "" + (30 * b) + "ms",
					'transform':"translate("+(r*Math.cos(2*Math.PI/avg*(a+begin)))+"px,"+(r*Math.sin(2*Math.PI/avg*(a+begin)))+"px",
			'-webkit-transform':"translate("+(r*Math.cos(2*Math.PI/avg*(a+begin)))+"px,"+(r*Math.sin(2*Math.PI/avg*(a+begin)))+"px",
				 '-o-transform':"translate("+(r*Math.cos(2*Math.PI/avg*(a+begin)))+"px,"+(r*Math.sin(2*Math.PI/avg*(a+begin)))+"px",
				'-ms-transform':"translate("+(r*Math.cos(2*Math.PI/avg*(a+begin)))+"px,"+(r*Math.sin(2*Math.PI/avg*(a+begin)))+"px"
				});
			}
		//} else {
		//	li.removeAttr('style');
		//	ul.css("visibility","hidden");
		//}
	}).mouseout(function(){
		var _this=$(this);
		swss_oneExe1=setTimeout(function(){
			
			var li=_this.find("li");
			var ul=_this.find(".tooltip02");
			li.removeAttr('style');
			ul.css("visibility","hidden");
		},400);
	});
}