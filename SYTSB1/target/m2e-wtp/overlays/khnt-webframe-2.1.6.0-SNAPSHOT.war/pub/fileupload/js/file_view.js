$(function() {
	_resizeWin();
	$(window).resize(_resizeWin);
	// 加载预览文件列表
	loadFileList(api.data);
});

function _resizeWin() {
	_resetFileListView();
	// $("#file-viewer").height($(window).height()-70);
}

function loadFileList(data) {
	if (!data.files)
		return;

	$.each(data.files, function() {
		$("#file-list").append(
				"<li onclick='__viewFile($(this))' id='" + this.id
						+ "' title='" + this.name
						+ "' style='background-image:url(\""
						+ _getPreviewImage(this, 64) + "\")'></li>");
	});

	var first = data.first || 0;
	var ff = $("#file-list").find("#" + data.files[first].id);
	__viewFile(ff);
	$(".file-viewer-pre").click(function() {
		_loadPreNextFile(false);
	});
	$(".file-viewer-next").click(function() {
		_loadPreNextFile(true);
	});
	$(".small-img-pre").click(function() {
		_moveFileListView(false);
	});
	$(".small-img-next").click(function() {
		_moveFileListView(true);
	});
}

function _moveFileListView(fw) {
	if (fw) {
		var left = $("#file-list li:last").offset().left;
		if (left <= $("#file-list").parent().width() - 80)
			return;
	} else {
		var left = $("#file-list li:first").offset().left;
		if (left >= 32)
			return;
	}
	var left = parseInt($("#file-list").css("margin-left")) + (fw ? -92 : 92);
	$("#file-list").animate({
		"margin-left" : left + "px"
	}, "normal");
}

function _resetFileListView() {
	$(".file-viewer-wrap").height($(window).height());
	var idx = $("#file-list li.current").prevAll().length;
	var fileNum = $("#file-list li").length;
	// 导航列表图总长度
	var imgWidth = fileNum * 92;
	// 导航图片总宽度小于容器宽度，居中显示
	if (imgWidth <= $("#file-list").parent().width()) {
		$("#file-list").css(
				{
					"margin-left" : (0 - (($("#file-list").width() - $(
							"#file-list").parent().width()) / 2))
							+ "px"
				}, "normal");
	} else if (idx * 112 <= $("#file-list").parent().width() / 2) {
		// 首先显示的文件位于队列的前部，在队列视图界面未超过可视区域的一半,此时，从队列第一个文件开始显示
		$("#file-list")
				.animate(
						{
							"margin-left" : (0 - ($("#file-list").width() - imgWidth) / 2)
									+ "px"
						}, 'normal');
	} else {
		if ((fileNum - idx) * 82 < $("#file-list").parent().width() / 2) {
			var left = ($("#file-list").width() - imgWidth) / 2 + imgWidth
					- $("#file-list").parent().width();
			$("#file-list").animate({
				"margin-left" : (0 - left) + "px"
			}, "normal");
		} else {
			// 首先显示的文件位于队列后部，其前面的小图总长度超过了可视宽度的一半，并且后续小图总宽超过另一半宽度
			var left = ($("#file-list").width() - imgWidth) / 2 + idx * 92
					- $("#file-list").parent().width() / 2;
			$("#file-list").animate({
				"margin-left" : (0 - left - 46) + "px"
			}, 'normal');
		}
	}
}

// 文件导航，上一个、下一个
function _loadPreNextFile(fw) {
	var crt = $("#file-list li.current");
	if (crt.length == 0)
		return;
	var nf = fw ? crt.next() : crt.prev();
	if (nf.length == 0)
		return;
	__viewFile(nf);
}

function _getPreviewImage(f, size) {
	var ps = size || 64;
	if (f.name.match(/\.(jpg|png|gif|bmp|jpeg)$/gi) != null) {
		return "fileupload/previewImage.do?id=" + f.id;
	} else {
		var suffixIdx = f.name.lastIndexOf(".");
		var suffix = suffixIdx == -1 ? "file" : f.name.substring(suffixIdx)
				.toLowerCase().substring(1);
		// 从系统参数【文件后缀图标集合】中查找
		if (suffixIdx > -1 && window["kFrameConfig"]
				&& kFrameConfig["suffixIcon"]
				&& kFrameConfig.suffixIcon.indexOf(suffix) == -1)
			suffix = "file";
		return "k/kui/images/file-type/" + ps + "/" + suffix + ".png";
	}
}

function __viewFile(obj) {
	if(obj.hasClass("current")) return;
	_stopMediaPlay();//停止所有媒体播放
	obj.addClass("current").siblings().removeClass("current");
	var f = {
		id : obj.attr("id"),
		name : obj.attr("title")
	};
	if (f.name.match(/\.(jpg|png|gif|bmp|jpeg)$/i)) {
		_viewImageFile(f);
	} else if (f.name.match(/\.(mp3|mav|ogg)$/i)) {
		_playAudioFile(f);
	} else if (f.name.match(/\.(avi|mp4|wmv|mkv|rmvb)$/i)) {
		_playVideoFile(f);
	} else if (f.name.match(/\.(doc|docx|xls|xlsx|ppt|pptx|wps|et|dps|avi|mp4|wmv|mkv|rmvb|pdf)$/i)) {
		_viewOnlineFile(f);
	} else {
		_viewDownFile(f);
	}

	// 调整导航图片位置
	_resetFileListView();
}

function _viewImageFile(img) {
	$("#file-viewer > div").not("#image-viewer").data("show", false).fadeOut();
	var imgViewer = $("#image-viewer").data("show", true).fadeIn().imageViewer();
	imgViewer.loadImage(img);
}

function _playAudioFile(afile){
	$("#file-viewer > div").not("#audio-player").data("show", false).fadeOut();
	var player = $("#audio-player").data("show", true).fadeIn().audioPlayer();
	player.play(afile);
}

function _playVideoFile(vfile){
	$("#file-viewer > div").not("#video-player").data("show", false).fadeOut();
	var player = $("#video-player").data("show", true).fadeIn().videoPlayer();
	player.play(vfile);
}

function _viewOnlineFile(ofile) {
	$("#file-viewer > div").not("#online-viewer").data("show", false).fadeOut();
	$("#online-viewer").data("show", true).fadeIn().onlineViewer().loadFile(ofile);
}

function _viewDownFile(vf) {
	$("#file-viewer > div").not("#download-viewer").data("show", false).fadeOut();
	$("#download-viewer").data("show", true).fadeIn().onlineViewer().loadFile(vf);
}

function _stopMediaPlay(){
	var vp = $("#video-player").data("videoPlayer");
	if(vp) vp.stop();
	var ap = $("#audio-player").data("audioPlayer");
	if(ap) ap.stop();
}

/**
 * 在线预览、下载
 * 
 * @param $
 */
(function($) {
	$.OnlineViewer = function(el) {
		this.element = $(el);
		this.file = null;
		this.atag = null;
		this._init();
	};
	$.OnlineViewer.prototype = {
		_init : function() {
			var _this = this;
			var awrap = $("<div class='view-icon'></div>").appendTo(
					this.element);
			this.atag = $("<a></a>").appendTo(awrap);
			var buttons = $(
					"<div class='view-button'><a class='preview'>在线查看</a><a class='download'>下&nbsp;&nbsp;载</a></div>")
					.insertAfter(awrap);
			buttons.find("a.download").click(function() {
				_this._download();
			});
			buttons.find("a.preview").click(function() {
				_this._preview();
			});
			$(window).resize(function() {
				_this.element.find(".view-icon").css("padding-top",(_this.element.height() - 300) / 2);
			});
		},
		_download : function() {
			if (this.file) {
				window.open($("base").attr("href") + "fileupload/download.do?id=" + this.file.id);
			}
		},
		_preview : function() {
			if (this.file.name
					.match(/\.(doc|docx|xls|xlsx|ppt|pptx|wps|et|dps)$/i)) {
				this._viewOffice();
			} else if (this.file.name.match(/\.(avi|mp4|wmv|mkv|rmvb)$/i)) {
				this._viewVideo();
			}  else if (this.file.name.match(/\.(mp3|mav|ogg)$/i)) {
				this._viewAudio();
			} else if (this.file.name.match(/\.pdf$/i)) {
				this._viewPdf();
			}
		},
		_openOnlineViewDialog : function(url, winParam) {
			top.$.dialog({
				width : $(top).width(),
				height : $(top).height(),
				title : this.file.name,
				lock : false,
				reSize : false,
				max : false,
				min : false,
				content : url,
				parent : api,
				data : winParam
			});
		},
		// 在线预览PDF
		_viewPdf : function() {
			if($.browser.msie && $.browser.version < 9.0){
				window.open($("base").attr("href") + "fileupload/download.do?id=" + this.file.id);
		    }else{
		    	this._openOnlineViewDialog("url:pub/pdfjs/viewer.jsp?fid="
						+ this.file.id, {}, "win-flat");
		    }
		},
		// 打开office文档
		_viewOffice : function() {
			this._openOnlineViewDialog("url:pub/fileupload/ntko_editor.jsp", {
				fid : this.file.id,
				status : 'detail'
			}, "");
		},
		// 在线播放视频
		_viewVideo : function() {
			this._openOnlineViewDialog("url:pub/fileupload/video_player.jsp?fid=" + this.file.id,{
				fid : this.file.id,
				fname : this.file.name
			});
		},
		loadFile : function(f) {
			this.file = f;
			this.atag.text(f.name);
			var iconUrl = _getPreviewImage(f, 128);
			this.atag.css("background-image", 'url("' + iconUrl + '")');
			this.element.find(".view-icon").css("padding-top",
					(this.element.height() - 300) / 2);
		}
	};
	$.fn.onlineViewer = function() {
		var viewer = $(this).data('onlineViewer');
		if (viewer)
			return viewer;
		viewer = new $.OnlineViewer(this);
		$(this).data('onlineViewer', viewer);
		return viewer;
	};
})(jQuery);

/**
 * 音频播放器
 * @param $
 * @returns
 */
(function($){
	$.AudioPlayer = function(el){
		if(!window["Audio5js"]){
			$("head").append('<script type="text/javascript" src="k/libs/audio.js/audio5.min.js"></script>');
		}
		this.container = $(el);
		this.player;
		this._init();
	}
	$.AudioPlayer.prototype = {
		_init: function(){
			var _this = this;
			this.container.find(".player-title").css("margin-top",(this.container.height()) / 2 - 90);
			$(window).resize(function() {
				this.container.find(".player-title").css("margin-top",(this.container.height()) / 2 - 90);
			});
			this.player = new Audio5js({
		        ready: function(){
		        	_this.container.find(".player-btn").on("click",function(){
						_this.player.playPause();
						//_this.container.find(".player-btn").toggleClass("player-btn-play").toggleClass("player-btn-pause");
					});
		        	this.on("play",function(){
						_this.container.find(".player-btn").removeClass("player-btn-play")
							.addClass("player-btn-pause").removeClass("player-btn-loading");
		        	});
		        	this.on("ended",function(){
						_this.container.find(".player-btn").addClass("player-btn-play")
							.removeClass("player-btn-pause").removeClass("player-btn-loading");
		        	});
		        	this.on("pause",function(){
						_this.container.find(".player-btn").addClass("player-btn-play")
							.removeClass("player-btn-pause").removeClass("player-btn-loading");
		        	});
		        	/*this.on("progress",function(){
						_this.container.find(".player-btn").addClass("player-btn-loading")
							.removeClass("player-btn-pause").removeClass("player-btn-play");
		        	});*/
		        }
			});
		},
		play: function(afile){
			this.container.find(".player-title").text(afile.name?afile.name:"未知名称");
			this.player.load(afile.url?afile.url:("fileupload/download.do?id=" + afile.id));
		},
		stop: function(){
			this.player.pause();
		}
	}

	$.fn.audioPlayer = function() {
		var player = $(this).data('audioPlayer');
		if (player) return player;
		player = new $.AudioPlayer(this);
		$(this).data('audioPlayer', player);
		return player;
	};
})(jQuery);

/**
 * 视频播放器
 * @param
 * @returns
 */
(function($){
	$.VideoPlayer = function(el){
		if(!window["videojs"]){
			$("head").append('<link rel="stylesheet" type="text/css" href="k/libs/vedio.js/video-js.css">'
					+'<script type="text/javascript" src="k/libs/vedio.js/videojs-ie8.min.js"></script>'
					+'<script type="text/javascript" src="k/libs/vedio.js/video.js"></script>');
		}
		this.container = $(el);
		this.player;
		this._init();
	}
	$.VideoPlayer.prototype = {
		_init: function(){
			var _this = this;
			$("#my-video-player").height($(this.container).height()-160).width($(this.container).width()-200);
			$(window).resize(function() {
				$("#my-video-player").height($(_this.container).height()-160).width($(_this.container).width()-200);
				$("#my-video-player video").height($(_this.container).height()-160).width($(_this.container).width()-200);
			});
			this.player = videojs('my-video-player');
		},
		play: function(vfile){
			this.container.find('.video-title').text(vfile.name?vfile.name:"未命名视频");
			this.player.src(vfile.url?vfile.url:("fileupload/download.do?id="+vfile.id));
			this.player.play();
		},
		stop: function(){
			this.player.pause();
		}
	}

	$.fn.videoPlayer = function() {
		var player = $(this).data('videoPlayer');
		if (player) return player;
		player = new $.VideoPlayer(this);
		$(this).data('videoPlayer', player);
		return player;
	};
})(jQuery);

/**
 * 图片预览
 * 
 * @param $
 */
(function($) {
	$.ImageViewer = function(el) {
		this.container = $(el);
		this.image;
		this.imgElement;
		this.rotateDegree = 0;
		this.zooming = false;
		this.scalex = 1;
		this.scaley = 1;
		this._init();
	};
	$.ImageViewer.prototype = {
		_init : function() {
			var _this = this;
			var imgwrap = this.container.find(".image-wrap");
			// imgwrap.height(this.container.height());
			this.imgElement = $("<img src='' />").appendTo(
					imgwrap.find(".image-rotate"));
			imgwrap.on("mousewheel", function(event, delta) {
				_this._zoom(delta > 0 ? 0.2 : -0.2);
			});
			this._createToolbar();
			// 注册拖拽
			imgwrap.find(".image-drag").drag({
				before : function(e) {
					$(this).css({
						'opacity' : '0.8',
						"cursor" : "move"
					});
					_this.draged = true;
				},
				end : function(e) {
					$(this).css({
						'opacity' : '',
						"cursor" : "default"
					});
				}
			});
			$(window).resize(function() {
				if (_this.container.data('show'))
					_this._resize("screen");
			});
		},
		_createToolbar : function() {
			var $this = this;
			var $tbar = $("#image-viewer .image-tbar");
			$("<a class='rotate-left' title='逆时针旋转'></a>").click(
					function() {
						$this.rotate("left");
					}).appendTo($tbar);
			$("<a class='rotate-right' title='顺时针旋转'></a>").click(
					function() {
						$this.rotate("right");
					}).appendTo($tbar);
			$("<a class='room-in' title='放大'></a>").click(function() {
				$this._zoom(0.25);
			}).appendTo($tbar);
			$("<a class='room-out' title='缩小'></a>").click(function() {
				$this._zoom(-0.25);
			}).appendTo($tbar);
			$("<a class='full-screen' title='全屏显示'></a>").click(
					function() {
						$this._fullscreen();
					}).appendTo($tbar);
			$("<a class='resize-screen' title='适合屏幕'></a>").click(
					function() {
						$this._resize("screen");
					}).appendTo($tbar);
			$("<a class='resize-original' title='实际大小'></a>").click(
					function() {
						$this._resize("original");
					}).appendTo($tbar);
			$("<a class='scalex' title='横向翻转'></a>").click(function() {
				$this._scale('x');
			}).appendTo($tbar);
			$("<a class='scaley' title='纵向翻转'></a>").click(function() {
				$this._scale('y');
			}).appendTo($tbar);
			$("<a class='download' title='下载图片'></a>").click(function() {
				$this._download();
			}).appendTo($tbar);
		},
		// 图片旋转
		rotate : function(dir) {
			var bc = BROWSER_INFO;
			var ie = bc.ie, iever = bc.ieversion;
			var rdg = this.rotateDegree;
			if (ie && parseFloat(iever) <= 8) {
				rdg = rdg == 0 ? 3 : rdg + (dir == "left" ? 1 : -1);
			} else {
				if (dir == "left")
					rdg = rdg == 0 ? 270 : rdg - 90;
				else
					rdg = rdg == 270 ? 0 : rdg + 90;
			}
			this._rotate(rdg);
		},
		_rotate : function(rdg) {
			var bc = BROWSER_INFO;
			var ie = bc.ie, iever = bc.ieversion;
			var imgwrap = this.container.find(".image-rotate");
			if (ie && parseFloat(iever) <= 8) {
				imgwrap.get(0).style.filter = "progid:DXImageTransform.Microsoft.BasicImage(rotation="
						+ rdg + ");";
			} else {
				var target = rdg;
				var start = this.rotateDegree;
				if (target == 0 && this.rotateDegree == 270)
					target = 360;
				else if (target == 270 && this.rotateDegree == 0)
					start = 360;
				var rate = target - start > 0 ? 5 : -5;
				var _ritl = window.setInterval(function() {
					start += rate;
					if ((rate > 0 && start > target)
							|| (rate < 0 && start < target)) {
						window.clearInterval(_ritl);
					} else {
						var rotate = "rotate(" + start + "deg)";
						imgwrap.css("-moz-transform", rotate);
						imgwrap.css("-webkit-transform", rotate);
						imgwrap.css("-o-transform", rotate);
						imgwrap.css("-ms-transform", rotate);
						imgwrap.css("transform", rotate);
					}
				}, 20);
			}
			this.rotateDegree = rdg;
		},
		_zoom : function(rate) {
			if (this.zooming)
				return;
			this.zooming = true;
			var imgWidth = this.imgElement.width();
			var imgHeight = this.imgElement.height();
			if (rate < 0 && (imgWidth < 100 || imgHeight < 100)) {
				this.zooming = false;
				return;
			}
			var r = rate / 20;
			var count = 1;
			var _this = this;
			var imgDrag = this.container.find(".image-drag");
			var _ztl = window.setInterval(function() {
				var iw = _this.imgElement.width();
				var ih = _this.imgElement.height();
				_this.imgElement.css({
					width : iw * (1 + r),
					height : ih * (1 + r)
				});
				imgDrag.css({
					left : imgDrag.position().left - iw * r / 2,
					top : imgDrag.position().top - ih * r / 2
				});
				_this._showScaleText();
				if (count++ > 20) {
					window.clearInterval(_ztl);
					_this.zooming = false;
					_this._hideScaleText();
				}
			}, 15);
		},
		_showScaleText : function() {
			var zoomtxt = this.container.find(".zoom-text");
			if (zoomtxt.data("show")) {
				zoomtxt.text((this.imgElement.width() / this.image.width * 100)
						.toFixed(0)
						+ "%");
			} else {
				zoomtxt.data("show", true).css({
					left : ($(window).width() - 80) / 2,
					top : ($(window).height() - 105 - 36) / 2
				}).text(
						(this.imgElement.width() / this.image.width * 100).toFixed(0) + "%").fadeIn(200);
			}
		},
		_hideScaleText : function() {
			var zoomtxt = this.container.find(".zoom-text").data("show", false);
			window.setTimeout(function() {
				if (zoomtxt.data("show") == false)
					zoomtxt.fadeOut(200);
			}, 2000);
		},
		_resize : function(type) {
			var vw = $(window).width();
			var vh = $(window).height();
			var width, height, left, top;
			var isRotate = this.rotateDegree == 1 || this.rotateDegree == 3
					|| this.rotateDegree == 90 || this.rotateDegree == 270;
			var iw = this.imgElement.width(), ih = this.imgElement.height();
			var _this = this;
			if (type == "screen") {
				height = vh - 180;
				width = iw * height / ih;
				if (width > vw - 200) {
					width = vw - 200;
					height = ih * width / iw;
				}
				top = (vh - 105 - height) / 2;
				left = (vw - width) / 2;
			} else {
				width = isRotate ? this.image.height : this.image.width;
				height = isRotate ? this.image.width : this.image.height;
				if (width == iw)
					return;
				left = 0 - (width - vw) / 2;
				top = 0 - (height - vh) / 2 - 105;
			}
			this.container.find(".image-drag").animate({
				left : left + "px",
				top : top + "px"
			}, "normal");
			this.imgElement.animate({
				width : width + "px",
				height : height + "px"
			}, "normal", function() {
				_this._showScaleText();
				_this._hideScaleText();
			});
		},
		_reLocation : function() {
			// 恢复图片位置，以图片中心为原点
			var vw = $(window).width();
			var vh = $(window).height();
			var imgDrag = this.container.find(".image-drag");
			var left = (vw - imgDrag.width()) / 2;
			var top = (vh - 105 - imgDrag.height()) / 2;
			imgDrag.css({
				left : left,
				top : top
			});
		},
		_changeBackColor : function() {
			if ($("#image-viewer").css("background-color").indexOf('255') >= 0) {
				$("#image-viewer").css("background-color", "#999999");
			} else if ($("#image-viewer").css("background-color")
					.indexOf('153') >= 0) {
				$("#image-viewer").css("background-color", "#000000");
			} else {
				$("#image-viewer").css("background-color", "#ffffff");
			}
		},
		_fullscreen : function() {
			var documentElement = top.document.documentElement;
			if (!document.fullscreenElement 
					&& !document.mozFullScreenElement
					&& !document.webkitFullscreenElement
					&& !document.msFullscreenElement) {
				if (documentElement.requestFullscreen) {
					documentElement.requestFullscreen();
				} else if (documentElement.msRequestFullscreen) {
					documentElement.msRequestFullscreen();
				} else if (documentElement.mozRequestFullScreen) {
					documentElement.mozRequestFullScreen();
				} else if (documentElement.webkitRequestFullscreen) {
					documentElement.webkitRequestFullscreen(Element.ALLOW_KEYBOARD_INPUT);
				}
				$("#image-viewer .esc-tips").slideDown(1000).delay(5000).slideUp(1000);
			}
		},
		_scale : function(f) {
			var _this = this;
			var rate = 0.1;
			if ((f == 'x' && this.scalex >= 1)
					|| (f == 'y' && this.scaley >= 1))
				rate = -0.1;
			var _stl = window.setInterval(function() {
				if (f == "x") {
					_this.scalex += rate;
					if (_this.scalex > 1) {
						_this.scalex = 1;
						window.clearInterval(_stl);
					} else if (_this.scalex < -1) {
						_this.scalex = -1;
						window.clearInterval(_stl);
					}
				} else {
					_this.scaley += rate;
					if (_this.scaley > 1) {
						_this.scaley = 1;
						window.clearInterval(_stl);
					} else if (_this.scaley < -1) {
						_this.scaley = -1;
						window.clearInterval(_stl);
					}
				}
				_this.imgElement.css({
					"-moz-transform" : "scaleX(" + _this.scalex + ") scaleY("
							+ _this.scaley + ")",
					"-webkit-transform" : "scaleX(" + _this.scalex
							+ ") scaleY(" + _this.scaley + ")",
					"-o-transform" : "scaleX(" + _this.scalex + ") scaleY("
							+ _this.scaley + ")",
					"-ms-transform" : "scaleX(" + _this.scalex + ") scaleY("
							+ _this.scaley + ")",
					"transform" : "scaleX(" + _this.scalex + ") scaleY("
							+ _this.scaley + ")",
					"filter" : "FlipH"
				});

			}, 30);
		},
		_download : function() {
			window.open(this.image.src);
		},
		_loadImage : function(imgfile) {
			this.imgElement.attr("src", this.image.src).css({
				width : this.image.width,
				height : this.image.height
			}).show();
			this.container.find(".file-name").text(
					imgfile.name + " (" + this.image.width + " X "
							+ this.image.height + ")");
			this.container.find(".image-drag").css({
				left : 0,
				top : 0
			});
			this._rotate(0);
			this._resize("screen");
			$("body").unmask();
		},
		_reset : function() {
			this._rotate(0);
			this._resize("screen");
		},
		loadImage : function(img) {
			var $this = this;
			$("body").mask("正在加载，请稍后...");
			this.imgElement.attr("src", "").hide();
			this.image = new Image();
			this.image.src = img.url?img.url:("fileupload/download.do?id=" + img.id);
			if (this.image.complete) {
				this._loadImage(img);
			} else {
				this.image.onload = function() {
					$this._loadImage(img);
				}
			}
		}
	};

	$.fn.imageViewer = function() {
		var viewer = $(this).data('imageViewer');
		if (viewer)
			return viewer;
		viewer = new $.ImageViewer(this);
		$(this).data('imageViewer', viewer);
		return viewer;
	}
})(jQuery);

(function($) {
	var old = $.fn.drag;

	function Drag(element, options) {
		this.ver = '1.0';
		this.$element = $(element);
		this.options = $.extend({}, $.fn.drag.defaults, options);
		this.init();
	}

	Drag.prototype = {
		constructor : Drag,
		init : function() {
			var options = this.options;
			this.$element.on('touchstart.drag.founder mousedown.drag.founder',function(e) {
				var ev = e.type == 'touchstart' ? e.originalEvent.touches[0] : e, 
					startPos = $(this).position(), 
					disX = ev.pageX - startPos.left, 
					disY = ev.pageY - startPos.top, 
					that = this;

				// 记录初始位置,以便复位使用
				$(this).data('startPos', startPos);

				if (options.before && $.isFunction(options.before)) {
					options.before.call(that, ev);
				}

				$(document).on('touchmove.drag.founder mousemove.drag.founder',function(e) {
					var ev = e.type == 'touchmove' ? e.originalEvent.touches[0] : e, 
						$this = $(that), 
						$parent = $this .offsetParent(), 
						$parent = $parent.is(':root') ? $(window) : $parent, 
						pPos = $parent.offset(), 
						pPos = pPos ? pPos: {left : 0, top : 0}, 
						left = ev.pageX - disX - pPos.left, 
						top = ev.pageY - disY - pPos.top, 
						r = $parent.width() - $this.outerWidth(true), 
						d = $parent.height() - $this.outerHeight(true);

					// left = left < 0 ? 0 :
					// left > r ? r : left;
					// top = top < 0 ? 0 : top >
					// d ? d : top;

					$(that).css({
						left : left + 'px',
						top : top + 'px'
					});

					if (options.process && $.isFunction(options.process)) {
						options.process.call(that, ev);
					}

					e.preventDefault();
				});

				$(document).on('touchend.drag.founder mouseup.drag.founder',function(e) {
					var ev = e.type == 'touchend' ? e.originalEvent.changedTouches[0]: e;

					if (options.end && $.isFunction(options.end)) {
						options.end.call(that,ev);
					}

					$(document).off('.drag.founder');
				});
				e.preventDefault();
			});
		}
	};

	// jQ插件模式
	$.fn.drag = function(options) {
		return this.each(function() {
			var $this = $(this), instance = $this.data('drag');

			if (!instance) {
				instance = new Drag(this, options);
				$this.data('drag', instance);
			} else {
				instance.init();
			}

			if (typeof options === 'string') {
				// instance.options[options].call(this);
			}

		});
	};

	$.fn.drag.defaults = {
		before : $.noop,
		process : $.noop,
		end : $.noop
	};

	$.fn.drag.noConflict = function() {
		$.fn.drag = old;
		return this;
	};
})(jQuery);