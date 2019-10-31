/* 框架函数 2016年10月25日 14:44:29 星期二 lybide */

/*
* 1 【必做】在pmain.jsp中载入库
*      <!--大数据效果，主体库-->
       <script type="text/javascript" src="app/bigdata/js/frame-main.js"></script>
       <link rel="stylesheet" href="app/bigdata/css/frame-main.css"/>
* 2 【必做】在pmain.jsp，sysMainPageComplate方法中调用 mBigData(); 方法
* 3 【选做】系统参数：MENUOPEN配置为false;
* 4 【选做】用户配置项 参考 http://doc.khnt.com/i-kdoc12-123.html
* */

var vowiejfiojaslkdfj=1;
function mBigData(url) {
	if (vowiejfiojaslkdfj==1) {
		mOpenFullWindow(url,null,null,"closeShow");
		vowiejfiojaslkdfj++;
	} else {
		$("#m-big-data-panel").show().animate({left:0,top:0},500,function(){});
	}
	$("#m-big-data-menu-a2").click(function(){});
	var welcomeBigDesktopShow=kui["welcomeBigDesktopShow"] || "1";//kui["user"]["welcomeBigDesktopShow"]=="" ? "" : kui["user"]["welcomeBigDesktopShow"];
	if (kui["user"]["welcomeBigDesktopShow"]) { //2016年11月29日 17:48:45 星期二 lybide 全局变量
		welcomeBigDesktopShow=kui["user"]["welcomeBigDesktopShow"];
	}
	var oneExe1=setTimeout(function(){
		$("#m-big-data-menu-a").click();
	},1000);
};

//打开全屏窗口 2016年10月26日 16:02:09 星期三 lybide
function mOpenFullWindow(url,action,openKeys,closeKeys) {
		
		var w1=$(window).width();
		var h1=$(window).height();
		var sdfwefw=$("#m-big-data-panel");
		if (sdfwefw.size()==0) {
			var bigDataIframe=$('<div id="m-big-data-panel" class="m-big-data-panel" style="width:'+w1+'px;height:'+h1+'px;top:'+h1+'px;left:-'+w1+'px;"><div id="m-big-data-panel-close" class="m-big-data-panel-close"><a href="javascript:void(0);" id="m-big-data-panel-close-a" style="background: url(/app/gis/scjy/v1/images/closebg-1.png) no-repeat 0 2px;" title="关闭大数据展示页面" class="l-a l-icon-add"><span>关闭大数据展示页面</span></a></div><iframe id="m-big-data-panel-iframe" name="m-big-data-panel-iframe" src="" width="100%" height="100%" scrolling="auto" frameborder="0" border="0"></iframe></div>');
		$("body").append(bigDataIframe);
		$("#m-big-data-panel-close-a").click(function(){
			mOpenFullWindow("","close");
		});
		$('#m-big-data-panel-iframe').bind("load",function(){
			var _this=$(this);
			//if (_this.is(":visible")) {//添加判断是否已打开

			//} else {//在没有引入kui时
			//closeMenuLoading();
			//_this.show();
			//$("#m-big-data-panel").show().css({"width":w1,"height":h1,"top":0,left:0});alert("234234")
			$("#m-big-data-panel").show().animate({left:0,top:0},500,function(){});
			//}
			//alert(document.getElementById('home').contentWindow.PAGE_STATUS_END)
			//closeMenuLoading();
			//$("#myhome").show();
		});
	}
	action=action||"open";
	if (action=="open") {
		var w1=$(window).width();
		var h1=$(window).height();
		top.$.dialog.loading("正在加载数据……");
		$("#m-big-data-panel-iframe").attr("src",url);

	} else if (action=="close") {
		var w1=$(window).width();
		var h1=$(window).height();
		//$("#m-big-data-panel").css({"top":"1080px","left":"-1980px"});
		//$("#m-big-data-panel-iframe").attr("src","");
		$("#m-big-data-panel").animate({left:-w1,top:h1},500,function() {
			//$("#m-big-data-panel-iframe").attr("src","");
			$("#m-big-data-panel").hide();
		});
	}
	//2016年10月26日 15:53:16 星期三 lybide
	function dbdpSize(){
		var w1=$(window).width();
		var h1=$(window).height();
		$("#m-big-data-panel").css({"width":w1,"height":h1});
	}
	dbdpSize();
	$(window).resize(function(){
		dbdpSize();
	});
}