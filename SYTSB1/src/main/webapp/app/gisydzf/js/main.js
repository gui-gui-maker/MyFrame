var $ = window.jQuery;

var $window = $(window);
var sectionTop = $('.top').outerHeight() + 20;
var FIRST_NAME,FIRST_URL,P_MAPSTYLE;
var P_TOOLS=false;
var P_SHOW_STAR=true;

/*function capitalize (string) {
  return string.charAt(0).toUpperCase() + string.slice(1);
}*/

//window.hljs.initHighlightingOnLoad();

/*
 * 设置图层名称
 */
function setLayer(t,s){
	openLoading();
	$("#layerTitle").html(t);
	$("#mapFrame").attr("src",((s=="")?"app/gisydzf/bmap.jsp":s));
	//setMenuCookie(t,s);//记录cookie
}

function setMapStyle(s)
{
	try{
		$("#mapFrame")[0].contentWindow._pub_setDTStyle(s);
	}
	catch(e){}
	P_MAPSTYLE = s;
	setMapStyleCookie(s);
}

function initNormalMenu()
{
//	var name=getCookie("kh_Gis__Meun01");
//	if(name==null) name = FIRST_NAME;
//	var url=getCookie("kh_Gis__Meun02");
//	if(url==null) url = FIRST_URL;
	setLayer("test","app/gisydzf/gs_ydzf/index.jsp");
//	var nr = name.split("-")[0];
//	$(".mainlevel > a").each(function(){
//		if ($(this).text()==nr)
//		{
//			$(this).addClass("active");
//		}
//	});
}

function initNormalMapStyle()
{
	var sty = getCookie("kh_Gis__Style");
	if(sty==null) sty = "normal";
	P_MAPSTYLE = sty;
	$("#dtys").val(sty);
}

function getCookie(name)
{
	var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
	if(arr=document.cookie.match(reg))
		return unescape(arr[2]);
	else
		return null;
}

function setMenuCookie(t,s){
	var exp = new Date();  
  exp.setTime(exp.getTime() + 2 * 24 * 60 * 60 * 1000);//过期时间 2天
	document.cookie="kh_Gis__Meun01="+escape(t)+";expires="+exp.toGMTString();
	document.cookie="kh_Gis__Meun02="+escape(s)+";expires="+exp.toGMTString();
}

function setMapStyleCookie(s)
{
	var exp = new Date();  
  exp.setTime(exp.getTime() + 30 * 24 * 60 * 60 * 1000);//过期时间 30天
	document.cookie="kh_Gis__Style="+escape(s)+";expires="+exp.toGMTString();
}

$(document).ready(function(){
	/*$('li.mainlevel').mousemove(function(){
		$(this).find('.sub_nav_01').slideDown("1000");//you can give it a speed
	});
	$('li.mainlevel').mouseleave(function(){
		$(this).find('.sub_nav_01').slideUp("fast");
	});*/
	/*$("#switch-tools").bootstrapSwitch();
	$("#switch-star").bootstrapSwitch();
	$("#switch-road").bootstrapSwitch();
	$(".bootstrap-switch-handle-on").html("开");
	$(".bootstrap-switch-handle-off").html("关");*/
	/*
	 * 工具显示开关
	 */
	$(".bootstrap-switch-id-switch-tools").on("switchChange.bootstrapSwitch",function(e,state){
		P_TOOLS = state;
		$("#mapFrame")[0].contentWindow._pub_setDrawManager(state);
	});
	/*
	 * 区划名称显示开关
	 */
	/*$(".bootstrap-switch-id-switch-qxmc").on("switchChange.bootstrapSwitch",function(e,state){
		if(state){
			PUB_QHMC_SHOW=true;
			openQhmcLabel();
		}
		else{
			PUB_QHMC_SHOW=false;
			closeQhmcLabel();
		}
	});*/
	/*
	 * 闪点图层显示开关
	 */
	$(".bootstrap-switch-id-switch-star").on("switchChange.bootstrapSwitch",function(e,state){
		P_SHOW_STAR = state;
		$("#mapFrame")[0].contentWindow._pub_setStarLayer(state);
	});
	/*
	 * 路径规划清空开关
	 */
	/*$(".bootstrap-switch-id-switch-road").on("switchChange.bootstrapSwitch",function(e,state){
		if(state){
			closeRoute();//清空路径等标记
		}
		else{
			window.setTimeout(function(){
				$("#switch-road").bootstrapSwitch("state",true);
			},500);
		}
	});*/
	
});

function setMouse()
{
	$('li.mainlevel').mousemove(function(){
		$(this).find('.sub_nav_01').slideDown("1000");//you can give it a speed
	});
	$('li.mainlevel').mouseleave(function(){
		$(this).find('.sub_nav_01').slideUp("fast");
	});	
}

$(function(){
	//初始化菜单控制
	if(share.data("gis_qymc")){ 
		//有此共享值表示是detail页面调用的，不需要关闭父级菜单
	}else{
		closeSysMenu();
	}
	//getMenu();
	//$(".sub_nav_01").height($(window).height());
	////////////////setMouse();
	initNormalMenu();
	//////////////$("#mainlevel_01").find(".xxx").addClass("active");
	/////////////setMenuFocus();
	//初始化地图样式控制
	initNormalMapStyle();
});

/*
 * 读取菜单数据
 */
function getMenu()
{
	var url=encodeURI("rbac/user/getUserResources.do?userId="+USERID+"&type=1&m="+Math.random());
	var node,node2,node3,node4,htmlStr;
	$.ajax({
		url: url,
		type: "POST",
		async:false,
		datatype: "json",
		success: function (res) {
			node = jQuery.parseJSON(res);
			for (var i=0;i<node.length;i++)
			{
				if (node[i].code=="root")
				{
					node2 = node[i].children;
					for (var j=0;j<node2.length;j++)
					{
						if (node2[j].code=="SY-GIS_YDZF")
						{
							node3 = node2[j].children;
							for (var k=0;k<node3.length;k++)
							{
								htmlStr = "";
								htmlStr += "<li class='mainlevel'><a href='javascript:void(0);' class='xxx'><em class='i1'></em>"+node3[k].text+"</a>";
								htmlStr += "<div class='sub_nav_01'>";
								htmlStr += "<ul>";
								node4 = node3[k].children;
								for (var l=0;l<node4.length;l++)
								{
									if (k==0 && l==0)
									{
										FIRST_NAME=node3[k].text+"-"+node4[l].text;
										FIRST_URL=node4[l].url;
									}
									htmlStr += "<li><a href='javascript:void(0);' onclick=setLayer('"+node3[k].text+"-"+node4[l].text+"','"+node4[l].url+"')><em class='ia'></em>"+node4[l].text+"</a></li>";
								}
								htmlStr += "</ul>";
								htmlStr += "<div class='subgtwo'></div>";
								htmlStr += "</div>";
								htmlStr += "</li>";
								$("#nav").append(htmlStr);
							}
						}
					}
				}
			}
		},
		error: function (res) {
			$.ligerDialog.error("菜单信息读取失败！","提示");
		}
	});
}

function setMenuFocus(){
	$(".sub_nav_01 ul li a").click(function(){
		$(".xxx").removeClass('active');
		$(this).parents(".mainlevel").find(".xxx").addClass("active");
		//$(this).parent().parent().parent().parent().find(".xxx").addClass("active");
	});
}

function setMapStyleFocus(){
	$(".styleMenu ul li a").click(function(){
		$(".mem").removeClass('mec');
		$(this).find(".mem").addClass("mec");
		//$(".styleMenu").css("display","none");
	});
}

var leftM=true;
function setShrinkLeft()
{
	if(leftM)
	{
		$(".side_left").css("display","none");
		$(".shrinkLeft").css("left","0");
		$(".shrinkLeft a").css("background","url(app/gis/img/sbg.png) no-repeat -11px center");
		leftM=false;
		$("#layerTitle").css("left","10px");
		//$("#mapFrame").contents().find("#beginDiv00").css("left","0");
		//$("#mapFrame").contents().find("#beginDiv").css("width",window.innerWidth-$("#mapFrame").contents().find(".side_right").width());
	}
	else
	{
		$(".side_left").css("display","block");
		$(".shrinkLeft").css("left","100px");
		$(".shrinkLeft a").css("background","url(app/gis/img/sbg.png) no-repeat left center");
		leftM=true;
		$("#layerTitle").css("left","110px");
		//$("#mapFrame").contents().find("#beginDiv").css("left","100px");
		//$("#mapFrame").contents().find("#beginDiv00").css("left","100px");
		//$("#mapFrame").contents().find("#beginDiv").css("width",window.innerWidth-$("#mapFrame").contents().find(".side_right").width()-100);
	}
	$("#mapFrame")[0].contentWindow._pub_shrinkNaveCtl(leftM);
}

/*
 * 打开加载等待图层
 */
function openLoading()
{
	$("#loader").css("display","block");
}

/*
 * 关闭加载等待图层
 */
function closeLoading()
{
	$("#loader").css("display","none");
}
/*
 * 隐藏父层菜单
 */
function closeSysMenu(){
		try{
			parent.mPanelDispay({panel:'left',display:false,close:true});
		}catch (e){alert(e)}
	}

function initViewData(json)
{
	var html = "";
	//var json = jQuery.parseJSON(obj);
	for(var i=0;i<json.length;i++)
	{
		html += "<li><a href=\"javascript:void(0);\" onClick=\"viewClick('"+json[i].dm+"')\">"+json[i].mc+"</a></li>";
	}
	html += "<li><a href=\"javascript:void(0);\" onClick=\"viewClick('0')\">[清空]</a></li>";
	$("#viewdata").html(html);
}
function viewClick(dm)
{
	$("#mapFrame")[0].contentWindow._pub_changeView(dm);
}

/**
 * 通过用户名密码获取jsession
 */
function getJsession(){
	var account = "cdkh";
	var password = "cdkh";
	 $.ajax({
         type:'POST',
         url: "http://124.129.19.117:8088/StandardApiAction_login.action?account="+account+"&password="+password+"",
         cache:false,
         dataType:'JSONP', 
         success: function (data) {
        	// data = eval('(' + data + ')');
            if(data.result == 0){
            	jsession = data.jsession;
            } else {
            	$.ligerDialog.warn("获取会话ID失败","提示");
            }
    },
 });
	
}
