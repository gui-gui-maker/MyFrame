/**
 * 快捷菜单相关js实现
 */
var btnen = 1;
var menuShow = false;
var isload = false;
var setheight;
var setwidth;
function initShotcutMenu(){
	$("#dvDock").show();
	$('#dvDockListt').hide();
	$('#dvFullListt').hide();
	$('.bottom_left').hide();
	$.getJSON("pub/syspersonmenuset/getset.do",function(res){
		if(res.success&&res.data){
			if(res.data.showType!=null){
				btnen = parseInt(res.data.showType);
			}
		}
	})
	$("#dvDockStart").toggle(function() {
		if($("#menuconfig").is(":visible")){
			$("#menuconfig").hide("fast");
		}
		if($("#menuset").is(":visible")){
			$("#menuset").hide("fast");
		}
		if(!$("#dvFullListt .nui-fClear div[class='lf']").length>0){
			//加载数据
			$.getJSON("pub/syspersonmenu/getUserShotcutMenu.do",function(data){
				if(data.success){
					for(var i  in data.data ){
						$(".nui-fClear").each(function(j){
							var image = data.data[i].resource.image32;
							if(!image){
								image = "k/kui/images/icons/32/resources.png";
							}
							$(this).append("<div resid='"+data.data[i].resource.id+"' onmouseout=mouseout(this) onmouseover=mouseover(this) class='lf' url='"+data.data[i].resource.url+"' onclick=openurl('"+data.data[i].resource.url+"','"+data.data[i].menuName+"')>"
									+"<b class='js-component-icon gApps-productIco nui-ico'>"
									+"<img src='"+image+"'></b>"
									+"<p class='gB'>"+data.data[i].menuName+"</p>"
									+"<a class='open-menu-icon' style='cursor:pointer' title='删除'  onclick=delmenu('"+data.data[i].resource.id+"','"+data.data[i].menuName+"')><img src='k/kui/skins/default/images/basic/menu_open_icon.png' width='16' height='16' /></a>"
									+"</div>"
							);
						})
					}
				}
				$(".nui-fClear").each(function(j){
					$(this).append("<div onmouseout=mouseout(this) onmouseover=mouseover(this) class='lf' onclick=openset()>"
							+"<b class='js-component-icon gApps-productIco nui-ico'>"
							+"<img src='k/kui/images/icons/32/sys_start_menu_add.png'></b>"
							+"<p class='gB'>添加应用</p>"
							+"</div>"
					);
				})
				if(!$("#slider1").length>0){	
					var top = $("#mTop").height();
					var menuconfig = "<div id='menuconfig' style='overflow:auto;background-color: #ffffff;display:none;position:absolute;left:0;top:"+top+"px;width: 100%;'><div onclick=menuclose() title='关闭窗口' class='slj-menu-close'><span>X</span></div><div class='s-menu_settings' id='slider1'></div></div>";
					$(menuconfig).prependTo("body");
					var menuset  = "<div id='menuset' class='slj-menu-set' style='overflow:auto; padding:10px 15px; background-color: #ffffff;display:none;position:absolute;left:0px;top:0px;width:150px;height:105px'>"
					+"<strong>显示方式：</strong>"
					+"<div class='slj-menu-set-metion'><span><input type='radio' id='showType1' value='1' name='showType'><label for='showType1'>纵向展开</label></span>"
					+"<span><input type='radio' checked='false' id='showType3' value='3' name='showType'><label for='showType3'>始终最大化</label></span></div>"
					+"<div class='slj-menu-set-btn'><span class='l-button-main'><input type='button' name='menusetsave' value='保存' onclick=saveset()><span class='l-button-icon l-icon-save'></span></span>"
					+"<span class='l-button-main'><input type='button' name='menusetclose' value='关闭' onclick=menusetclose()><span class='l-button-icon l-icon-cancel'></span></span>"
					+"</div>"
					+"</div>"
					$(menuset).prependTo("body");
					adjustHeight();
				}
				//showMenu(btnen);
			});
		}
		//else{
		//showMenu(btnen);
		//}
		showMenu(btnen);
	}, function() {
		if($("#menuconfig").is(":visible")){
			$("#menuconfig").hide("fast");
		}
		if($("#menuset").is(":visible")){
			$("#menuset").hide("fast");
		}
		bindClick();
		$("#shotcutMask").hide();
		menuShow = false;
		var dx = $("#dvDockStart").hasClass('dX');
		if(!dx){
			$("#dvDockStart").click();
		}else{
			if (btnen == 1 || btnen == 2 || btnen == 3) {
				$("#dvDockStart").removeClass("dX");
				$('#dvDockListt').hide("fast");
				$('#dvFullListt').hide("fast");
			}
		}
	});
	
	$('.close').click(function() {
		/**
		winOpen({
			width: 400,
			height: 200,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "快捷菜单设置",
			content: 'url:pub/rbac/menu_config.jsp'
		});
		*/
		ajustMenusetPostion();
		$("#menuset").css("z-index","201").show("fast");
		//去掉x
		bindClick();
	});
	$('.closees').click(function() {
		/**
		winOpen({
			width: 400,
			height: 200,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "快捷菜单设置",
			content: 'url:pub/rbac/menu_config.jsp'
		});
		*/
		ajustMenusetPostion();
		$("#menuset").css("z-index","201").show("fast");
		//去掉x
		bindClick();
	});
	
	function ajustMenusetPostion(){
		if(btnen==1&&!$("#dvFullListt").is(":visible")){
			setheight = $("#sysMain").height()-$("#dvDockListt").height()-33;
			setwidth = $("#dvDockListt").width()+12;
		}
		if((btnen==1&&$("#dvFullListt").is(":visible"))||btnen==3){
			setheight = $("#sysMain").height()-$("#dvFullListt").height()-33;
			setwidth = $("#dvFullListt").width()+12;
		}
		if(btnen == 3 ){
			$("#showType3").attr('checked','checked');
			$("#showType1").removeAttr('checked');
		}
		if(btnen == 1 ){
			$("#showType1").attr('checked','checked');
			$("#showType3").removeAttr('checked');
		}
		$("#menuset").css("left",setwidth).css("top",setheight);
	}
	
	$('.open').click(function() {
		if (btnen == 1 || btnen == 3) {
			$("#dvDockStart").addClass("dX");
			$('#dvDockListt').hide("fast");
			$('#dvFullListt').show("fast");
		} else if (btnen == 2) {
			$("#dvDockStart").removeClass("hgx")
			$('#dvDockListt').hide("fast");
			$('#dvFullListt').show("fast");
		}
		if($("#menuset").is(":visible")){
			$("#menuset").hide("fast");
		}
		//去掉x
		bindClick();
	});
	$('.openes').click(function() {
		if (btnen == 1 ) {
			$("#dvDockStart").addClass("dX");
			$("#dvDockStart").removeClass("hgx")
			$('#dvDockListt').show("fast");
			$('#dvFullListt').hide("fast");
		} else if (btnen == 2) {
			$("#dvDockStart").addClass("hgx");
			$("#dvDockStart").removeClass("dx")
			$('#dvDockListt').show("fast");
			$('#dvFullListt').hide("fast");
		}else if(btnen == 3){
			
		}
		if($("#menuset").is(":visible")){
			$("#menuset").hide("fast");
		}
		//去掉x
		bindClick();

	});
	
	$(".edits").toggle(function(){
		$(this).parent().parent().find(".lf").each(function(){
			if($(this).find(".open-menu-icon").length>0){
				if($(this).find(".open-menu-icon").is(":hidden")){
					$(this).find(".open-menu-icon").show()
					$(this).removeAttr("onclick");
					$(this).unbind("click")
				}else{
					$(this).find(".open-menu-icon").hide()
					$(this).unbind("click");
					$(this).bind("click",function(){
						openurl($(this).attr("url"),$(this).find(".gB").text())
					});
				}
			}
		})
	},function(){
		$(this).parent().parent().find(".lf").each(function(){
			if($(this).find(".open-menu-icon").length>0){
				if($(this).find(".open-menu-icon").is(":hidden")){
					$(this).find(".open-menu-icon").show()
					$(this).removeAttr("onclick");
					$(this).unbind("click")
				}else{
					$(this).find(".open-menu-icon").hide()
					$(this).removeAttr("onclick");
					$(this).unbind("click");
					$(this).bind("click",function(){
						openurl($(this).attr("url"),$(this).find(".gB").text())
					});
				}
			}
		})
	})

	$('.hxbtn').click(function() {
		btnen = 2;
	})
	$('.zxbtn').click(function() {
		btnen = 1;
	})
	$('.zkbtn').click(function() {
		btnen = 3;
	})

	if ($.browser.msie && parseFloat($.browser.version) <= 8) {

		$("body").addClass("ps_ie8");

	}
}
//显示菜单
function showMenu(btnen){
	menuShow = true;
	if(!$("#shotcutMask").length>0){
		var shotcutMask = $("#mLoadingMask").clone().prependTo("body");
		shotcutMask.attr("id","shotcutMask").css("z-index","199").bind("mousedown",function(e) {
			menuShow = false;
			$("#dvDockStart").click();
			$("#shotcutMask").hide();
			$("#menuconfig").hide("fast");
		}).show();
	}else{
		$("#shotcutMask").show();
	}
	if (btnen == 1) {
		$("#dvDockAppLsit").height("302px");
		$("#dvDockAppLsit").css('overflow', 'hidden');
		$("#dvDockStart").addClass("dX");
		$('#dvDockListt').show("fast");
		$('#dvFullListt').hide("fast");
	} else if (btnen == 2) {
		$("#dvDockStart").addClass("dX");
		$('#dvDockListt').show("fast");
		$('#dvFullListt').hide("fast");
		$("#dvDockStart").removeClass("dx")
		$("#dvDockStart").addClass("hgx")
		$('.bottom_left').show();
		$("#dvDockListt").removeClass("hg");
		$("#dvDockListt").addClass("hx");
		$("#dvDockAppLsit").height("98px");
		$("#dvFullListt").removeClass("kW");
		$("#dvDockAppLsit").append('<span class="btn_lf"></span>');
		$("#dvDockAppLsit").append('<span class="btn_lr"></span>');
		$("#dvDockItems").addClass("tab_ct")
		var $cur = 1;
		var $i = 7;
		var $len = $('.tab_ct>div>div').length;
		var $pages = Math.ceil($len / $i);
		var $w = $('#dvDockAppLsit').width() - 50;
		var $showbox = $('.tab_ct');
		var $autoFun;

		$('.btn_lf').click(function() {

			if (!$showbox.is(':animated')) {
				if ($cur == 1) {
					$('this').stop();
				} else {
					$showbox.animate({
						left : '+=' + $w
					}, 500);
					$cur--;
				}

			}
		});
		$('.btn_lr').click(function() {

			if (!$showbox.is(':animated')) {
				if ($cur == $pages) {
					$('this').stop();
				} else {
					$showbox.animate({
						left : '-=' + $w
					}, 500);
					$cur++;
				}

			}
		});

	} else if (btnen == 3) {
		$("#dvDockStart").addClass("dX");
		$('#dvDockListt').hide("fast");
		$('#dvFullListt').show("fast");
	}
}
//绑定点击事件
function bindClick(){
	//去掉x
	$("#dvFullListt").find(".lf").each(function(){
		if($(this).find(".open-menu-icon").length>0){
			$(this).find(".open-menu-icon").hide()
			$(this).removeAttr("onclick");
			$(this).unbind("click");
			$(this).bind("click",function(){
				openurl($(this).attr("url"),$(this).find(".gB").text())
			});
		}
	})
}

function menuset(obj){
	btnen = obj;
	$("#shotcutMask").hide();
}
function menuclose(){
	$("#dvDockStart").click();
	$("#shotcutMask").hide();
}
function menusetclose(){
	$("#menuset").hide("fast");
}
function mouseout(obj){
	obj.className = "lf";
}
function mouseover(obj){
	obj.className="lf fI"
}
function openurl(url,title){
	//采用最大化方式弹窗打开
	winOpen({
		width:800,
		lock: true,
		parent: api,
		data: {
			window: window
		},
		title: title,
		content: 'url:'+url
	}).max();
	$("#dvDockStart").click();
	$("#shotcutMask").hide();
}
function openset(){
	//去掉x
	bindClick();
	/**
		 winOpen({
			width: 800,
			height: 400,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "添加应用",
			content: 'url:pub/rbac/menu_custom_config.jsp'
		}).max();
	 */
	$("#menuconfig").css("z-index","201").show("fast");
	if(!isload){
         $.getScript("k/kui/public/shortcut-menu/shortcut-menu-config.js", function(){
        	 isload = true;
        	 $("#slider1").empty();
        	 initMenuConfig();
         });
    }else{
    	$("#slider1").empty();
    	initMenuConfig();
    }
}
function addmenu(data){
	$(".nui-fClear").each(function(j){
		$(this).prepend("<div resid='"+data.resId+"' onmouseout=mouseout(this) onmouseover=mouseover(this) class='lf' url='"+data.resUrl+"' onclick=openurl('"+data.resUrl+"','"+data.resName+"')>"
				+"<b class='js-component-icon gApps-productIco nui-ico'>"
				+"<img src='"+data.resImage+"'></b>"
				+"<p class='gB'>"+data.resName+"</p>"
				+"<a class='open-menu-icon' style='cursor:pointer' title='删除'  onclick=delmenu('"+data.resId+"','"+data.resName+"')><img src='k/kui/skins/default/images/basic/menu_open_icon.png' width='16' height='16' /></a>"
				+"</div>")
	})
}

function delmenu(obj,title){
	$.getJSON("pub/syspersonmenu/deletemenu.do",{resourceId:obj},function(res){
		if(res.success){
			//变换样式
			$("div[resid='"+obj+"']").remove();
		}
	})
}
function saveset(){
	var showType = $('input:radio:checked').val();
	$.getJSON("pub/syspersonmenuset/savemenuset.do",{showType:showType},function(res){
		if(res.success){
			menuset(parseInt(res.data.showType));
			menuclose();
		}
	})
}

function adjustHeight() {
    var bodyHeight = $("body").height();
    var height = bodyHeight - $("#mFoot").height() - $("#mTop").height();
    $("#menuconfig").height(height);
}