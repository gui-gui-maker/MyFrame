<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<%@include file="/k/kui-base-form.jsp" %>
	<title>图片查看</title>
	<script type="text/javascript" src="k/kui/frame/jquery.mousewheel.js"></script>
	<script type="text/javascript">
		var P_IS_MOUSE_DRAY=false,_current_image_index=0;
		(function($){
			//拖拽插件,参数:id或object
			$.Move = function(_this){
				if(typeof(_this)=='object'){
					_this=_this;
				}else{
					_this=$("#"+_this);
				}
				if(!_this){return false;}

				_this.css({'position':'absolute'}).hover(function(){$(this).css("cursor","move");},function(){$(this).css("cursor","default");})
				_this.mousedown(function(e){//e鼠标事件
					var offset = $(this).offset();
					var x = e.pageX - offset.left;
					var y = e.pageY - offset.top;
					_this.css({'opacity':'0.3'});
					$(document).bind("mousemove",function(ev){//绑定鼠标的移动事件，因为光标在DIV元素外面也要有效果，所以要用doucment的事件，而不用DIV元素的事件
						_this.bind('selectstart',function(){return false;});
						var _x = ev.pageX - x;//获得X轴方向移动的值
						var _y = ev.pageY - y;//获得Y轴方向移动的值
						_this.css({'left':_x+"px",'top':_y+"px"});
						P_IS_MOUSE_DRAY=true;//2015/2/2 17:25 lybide
					});
				});

				$(document).mouseup(function(){
					$(this).unbind("mousemove");
					_this.css({'opacity':''});
				})
			};
		})(jQuery);


		$(function(){
			var oldw;
			var oldh;
			var gts = 0;
			var bc = BROWSER_INFO;
			var ie = bc.ie,
				xt = bc.system,
				xtms = bc.systemx,
				dm = bc.docMode,
				iever = bc.ieversion;
			var tbars=[];
			$(".nav").hide();
			tbars = tbars.concat([
				/* "-",
				{img:"pub/fileupload/images/shape_rotate_anticlockwise.png",id:"rotLeft",text:"逆时针旋转",click:function(){
					if (ie && parseFloat(iever) <= 8) {
						gts = gts==0?3:gts-1;
						document.getElementById("imgWrap").style.filter="progid:DXImageTransform.Microsoft.BasicImage(rotation=" + gts + ");";
					} else {
						gts = gts == 0 ? 270 : gts - 90;
						var rotate = "rotate(" + gts + "deg)";
						$("#imgWrap").css("-moz-transform",rotate);
						$("#imgWrap").css("-webkit-transform",rotate);
						$("#imgWrap").css("-o-transform",rotate);
						$("#imgWrap").css("-ms-transform",rotate);
						$("#imgWrap").css("transform",rotate);
					}
				}},
				{img:"pub/fileupload/images/shape_rotate_clockwise.png",id:"rotRight",text:"顺时针旋转",click:function(){
					if (ie && parseFloat(iever) <= 8) {
						gts = gts==3?0:gts+1;
						document.getElementById("imgWrap").style.filter="progid:DXImageTransform.Microsoft.BasicImage(rotation=" + gts + ");";
					} else {
						gts = gts == 270 ? 0 : gts + 90;
						var rotate = "rotate(" + gts + "deg)";
						$("#imgWrap").css("-moz-transform",rotate);
						$("#imgWrap").css("-webkit-transform",rotate);
						$("#imgWrap").css("-o-transform",rotate);
						$("#imgWrap").css("-ms-transform",rotate);
						$("#imgWrap").css("transform",rotate);
					}
				}},"-", */
				{img:"pub/fileupload/images/magnifier_zoom_in.png",id:"fd",text:"放大",click:function(){
					var imgWidth=$('#imgWrap img.current').width()+200;
					imageChangeSize(imgWidth);
				}},
				{img:"pub/fileupload/images/magnifier_zoom_out.png",id:"sx",text:"缩小",click:function(){
					var imgWidth=$('#imgWrap img.current').width()-200;
					if (imgWidth<=0) {
						return;
					}
					imageChangeSize(imgWidth);
				}},"-",
				{img:"pub/fileupload/images/resize-actual.png",id:"shdx",text:"适合大小",click:function(){
					$('#imgWrap img.current').css({"width":"","height":""}).removeAttr("width").height($(".scroll-tm").height());
					$("#imgPlane").css({"left":$("#scroll-tm").width()/2-$('#imgPlane').width()/2,top:$("#scroll-tm").height()/2-$("#imgPlane").height()/2});
				}},
				{img:"pub/fileupload/images/resize.png",id:"sjdx",text:"实际大小",click:function(){
					$('#imgWrap img.current').css({"width":"","height":""}).removeAttr("width").removeAttr("height");
					$("#imgPlane").css({"left":$("#scroll-tm").width()/2-$('#imgPlane').width()/2,top:$("#scroll-tm").height()/2-$("#imgPlane").height()/2});
				}}/* ,
				"-",
				{img:"pub/fileupload/images/empty.png",id:"hydx",text:"还原",click:function(){
					P_IS_MOUSE_DRAY=false;
					if (ie && parseFloat(iever) <= 8) {
						document.getElementById("imgWrap").style.removeAttribute("filter");
					} else {
						$("#imgWrap").css({"transform":""});
					}
					imageReSize();
				}},
				"-",
				{id:"bgcolor",menus:[{img:"pub/fileupload/images/color_swatch.png", id:"hydx", text:"背景色", menu: {
					width: 120,
					items:[
						{ text: '白色', img:"pub/fileupload/images/bullet.png", click: function(){
							$("#scroll-tm").css("background-color","#ffffff");
						}},
						{ text: '黑色', img:"pub/fileupload/images/bullet.png", click: function(){
							$("#scroll-tm").css("background-color","#000000");
						}},
						{ text: '灰色', img:"pub/fileupload/images/bullet.png", click: function(){
							$("#scroll-tm").css("background-color","#cccccc");
						}}
					]
				}}]},*/
				,"-",
				{text:"<span style='color:red;font-weight:bold;'>下载</span>",icon:'picture-save',click:function(){
	                 window.open($("#imgWrap img.current").attr("src"));
	            }}/* ,"->",
				{text:"<span style='color:red'>支持图片拖动、滚动缩放</span>"}  */
			]);
			$("#pToolbar").attr({"oncontextmenu":"return false;","onselectstart":"return false;","ondragstart":"return false;"}).css("-moz-user-select","none").ligerToolBar({
				items: tbars
			});

			$("#imgPlane").attr({"ondragstart":"return false;","onselectstart":"return false;"});

			$(window).resize(function(){
				var oneExe1=setTimeout(function(){
					imageReSize();
				},1);
			});
			
			// 注册图片拖拽
			$.Move($('#imgPlane'));

			// 页面初始化
			pageInit();
			
			$("#p_closeLoading").click(function(){
				$("#imgLoading").hide();
				return false;
			});

			//检查网络连接，如超20秒，还没有反应，需要友情提示
			var MAIN_LINK_SERVER_STATUS_COUNT=0;
			var linkTime=kui["sysMainBadLinkCueHH"] || 20;//20秒没有反应，提示网络不能连接。
			linkTime=parseInt(linkTime);
			var MAIN_LINK_SERVER_STATUS_COUNT_LOOP_FUNCTION=setInterval(function(){
				var obj=$("#imgLoading");
				if (obj.is(":visible") && MAIN_LINK_SERVER_STATUS_COUNT>linkTime) {
					MAIN_LINK_SERVER_STATUS_COUNT=0;
					clearInterval(MAIN_LINK_SERVER_STATUS_COUNT_LOOP_FUNCTION);
					$("#imgLoading").hide();
					$("#imgWrap").html('<span style="color:red;font-size:18px;border:1px solid #FF0000;padding:5px;">图片连接超时，请重试或刷新。</span>');
				}
				if (obj.is(":hidden")) {
					clearInterval(MAIN_LINK_SERVER_STATUS_COUNT_LOOP_FUNCTION);
				}
				//console.log(MAIN_LINK_SERVER_STATUS_COUNT);
				MAIN_LINK_SERVER_STATUS_COUNT++;
			},1000);//总变量，这个必须不写或者写在startX函数外，stopX才有效
		});

		function loadImage(url, callback) {
			/*建立图像对象：图像对象名称=new image([宽度],[高度])
			图像对象的属性： border complete height hspace lowsrc name src vspace width
			图像对象的事件：onabort onerror onkeydown onkeypress onkeyup onload*/
			var img = new Image(); //创建一个Image对象，实现图片的预下载
			img.src = url;

			if(img.complete) { // 如果图片已经存在于浏览器缓存，直接调用回调函数
				callback.call(img);
				return; // 直接返回，不用再处理onload事件
			}
			img.onload = function () { //图片下载完毕时异步调用callback函数。
				callback.call(img);//将回调函数的this替换为Image对象
			};
		};
	
		//图片缩放
		function imageReSize(){
			$('#imgWrap img.current').css({"width":"","height":""}).removeAttr("width").removeAttr("height");
			var imageH1=$("#imgWrap img.current").height();
			var imageW1=$("#imgWrap img.current").width();
			var tmH1=$("#scroll-tm").height();
			var tmW1=$("#scroll-tm").width();
			if (imageH1<tmH1 && imageW1<tmW1) {
				$('#imgWrap img.current').css({"height":"","width":""}).removeAttr("height").removeAttr("width");
			}
			if (imageH1>tmH1) {
				$('#imgWrap img.current').css({"height":tmH1,"width":""}).removeAttr("width");
			}
			if (imageW1>tmW1) {
				$('#imgWrap img.current').css({"width":tmW1,"height":""}).removeAttr("height");
			}
			if (P_IS_MOUSE_DRAY) {
				return;
			}
			$("#imgPlane").css({"left":$("#scroll-tm").width()/2-$("#imgPlane").width()/2,top:$("#scroll-tm").height()/2-$("#imgPlane").height()/2});
		}

		//图片载入接口
		function renderImage(t,url) {
			if ((t==1 && $.kh.isNull(url)) || (t==2 && $.kh.isNull(api.data) && $.kh.isNull(api.data.images) && api.data.images.length==0)) {
				$("#imgLoading").hide();
				$("#imgWrap").html('<span style="color:red;font-size:18px;border:1px solid #FF0000;padding:5px;">没有图片，显示错误。</span>');
				return;
			}
			$("#imgLoading").show();
			var url1 = "fileupload/downloadCompress.do?proportion=0&id=";
			if("${param.resource_type}"=="3"){
				url1 = "fileupload/downloadByPath.do?id="; 
			}
			loadImage(t==1?url:url1,function(){
				var hmtl1 = this.outerHTML;
				$("#imgWrap img.current").attr("src",this.src);
				window.setTimeout(function(){
					screenImage();
					$("#imgLoading").hide();
				},300);
			});
		};
		
		//上一张
		function prevImage(){
			if(_current_image_index <=0)return;
			_current_image_index--;
			var current = $("#imgWrap img.current").removeClass("current").addClass("not-current").prev().removeClass("not-current").addClass("current");
			if(!current.prev().attr("src"))
				renderImage(2);
			api.title(api.data.images[_current_image_index].name);
		}

		//下一张
		function nextImage(){
			if(_current_image_index >= api.data.images.length - 1)return;
			_current_image_index++;
			var current = $("#imgWrap img.current").removeClass("current").addClass("not-current").next().removeClass("not-current").addClass("current");
			if(!current.next().attr("src"))
				renderImage(2);
			api.title(api.data.images[_current_image_index].name);
		}
		
		//适合屏幕
		function screenImage(){
			$('#imgWrap img.current').css({"width":"","height":""}).removeAttr("width").height($(".scroll-tm").height());
			$("#imgPlane").css({"left":$("#scroll-tm").width()/2-$('#imgPlane').width()/2,top:$("#scroll-tm").height()/2-$("#imgPlane").height()/2});
		}
	
		//改变图片大小
		function imageChangeSize(width) {
			var imgWidth=width || $('#imgWrap img.current').width();
			$('#imgWrap img.current').width(imgWidth).css({"height":""}).removeAttr("height");
			if (P_IS_MOUSE_DRAY) {
				return;
			}
			$("#imgPlane").css({"left":$("#scroll-tm").width()/2-imgWidth/2,top:$("#scroll-tm").height()/2-$("#imgPlane").height()/2});
		};
		
		// 页面初始化
		function pageInit(){
			//鼠标滚动处理，放大缩小图片
			$("#scroll-tm").bind('mousewheel', function(event, delta) {
				var dir = delta > 0 ? 'Up' : 'Down',
						vel = Math.abs(delta);
				var imgWidth=0;
				if (dir=="Up") {
					imgWidth=$('#imgWrap img.current').width() + 200;
					imageChangeSize(imgWidth);
				}
				if (dir=="Down") {
					imgWidth=$('#imgWrap img.current').width() - 200;
					if (imgWidth<=0) {
						return;
					}
					imageChangeSize(imgWidth);
				}
				return false;
			});
			
			var urlId = "${param.id}",imageUrl= $.kh.request("url");
			
			//最高优先级，取url参数
			if (imageUrl) {
				$("#imgWrap").append("<img class='current' />");
				renderImage(1,imageUrl);
			}else if (urlId){
				$("#imgWrap").append("<img class='current' />");
				renderImage(1,"fileupload/downloadCompress.do?id=" + urlId+"&proportion=0");
			}else if(api.data && api.data.images){
				_current_image_index = api.data.first;
				$.each(api.data.images,function(i){
					$("#imgWrap").append("<img class='" + (api.data.first==i?"current":"not-current") + "' />");
				});
				api.title(api.data.images[_current_image_index].name);
				renderImage(2);
			}
		}
	</script>
</head>
<body>
<div class="item-tm">
	<div id="pToolbar"></div>
</div>
<style type="text/css">
	#imgLoading {background:url('k/kui/images/loading-s5.gif') no-repeat transparent center center;width:100%;height:100%;z-index:8;position:absolute;}
	#imgLoading .close {position:absolute;top:5px;right:5px;width:16px;height:16px;background:url("k/kui/skins/default/images/basic/close2.png") repeat-x top left;}
	#imgLoading .close a {display:block;width:16px;height:16px;}
	#imgLoading .close:hover {background-position:0px -16px;}
	#imgLoading .close span {display:none;}
	#imgPlane {position:absolute;z-index:1;}
	.scroll-tm {text-align:center;position:relative;overflow:auto;}
	.nav{width:50px;height:100%;cursor:pointer;position:fixed;top:33px;z-index:999;opacity:0.4;filter: alpha(opacity=40);}
	.nav.nav-prev{left:0;background: url("pub/fileupload/images/dsk2_switcherlt.png") no-repeat center center;}
	.nav.nav-next{right:0;background: url("pub/fileupload/images/dsk2_switcherrt.png") no-repeat center center;}
	.nav.nav-prev:HOVER {background: url("pub/fileupload/images/dsk2_switcherlt_h.png") no-repeat #EDEDED center center;}
	.nav.nav-next:HOVER {background: url("pub/fileupload/images/dsk2_switcherrt_h.png") no-repeat #EDEDED center center;}
	#imgWrap img.not-current{display:none;}
	#imgWrap img.current{display:inline;height:100%}
</style>
<div class="scroll-tm" id="scroll-tm">
	<div class="nav nav-prev" onclick="prevImage()"></div>
	<div id="imgLoading">
		<div class="close" id="p_closeLoading"><a class="a" href="javascript:void(0);"><span>关闭</span></a></div>
	</div>
	<div id="imgPlane">
		<div id="imgWrap"></div>
	</div>
	<div class="nav nav-next" onclick="nextImage()"></div>
</div>
</body>
</html>
