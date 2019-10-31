$(document).ready(function() {
	//初始化打开设置
	$('.tbar-panel-cart').css({'visibility':'visible','z-index':1});
	$('.tbar-tab-cart').click(function() {

		var info = "<em class='tab-text '>特检云</em>";
		$('.toolbar-wrap').removeClass('toolbar-open');
		$(this).append(info);
		$(this).removeClass('tbar-tab-click-selected');
		$('.tbar-panel-cart').css({
			'visibility': "hidden",
			"z-index": "-1"
		});

	});
	$('.tbar-tab-cart').find('.tab-text').remove();
	//初始化打开设置 end
	
	$('.tbar-cart-item').hover(function() {
		$(this).find('.p-del').show();
	}, function() {
		$(this).find('.p-del').hide();
	});
	$('.jth-item').hover(function() {
		$(this).find('.add-cart-button').show();
	}, function() {
		$(this).find('.add-cart-button').hide();
	});
	$('.toolbar-tab').hover(function() {
		$(this).find('.tab-text').addClass("tbar-tab-hover");
		$(this).find('.footer-tab-text').addClass("tbar-tab-footer-hover");
		$(this).addClass("tbar-tab-selected");
	}, function() {
		$(this).find('.tab-text').removeClass("tbar-tab-hover");
		$(this).find('.footer-tab-text').removeClass("tbar-tab-footer-hover");
		$(this).removeClass("tbar-tab-selected");
	});
	$('.tbar-tab-cart').mouseover(function() {
		if ($('.toolbar-wrap').hasClass('toolbar-open')) {
			if ($(this).find('.tab-text').length > 0) {
				if (!$('.tbar-tab-follow').find('.tab-text').length > 0) {
					var info = "<em class='tab-text '>操作手册</em>";
					$('.tbar-tab-follow').append(info);
					$('.tbar-tab-follow').removeClass('tbar-tab-click-selected');
					$('.tbar-panel-follow').css({
						'visibility': "hidden",
						"z-index": "-1"
					});
				}
				if (!$('.tbar-tab-history').find('.tab-text').length > 0) {
					var info = "<em class='tab-text '>其它</em>";
					$('.tbar-tab-history').append(info);
					$('.tbar-tab-history').removeClass('tbar-tab-click-selected');
					$('.tbar-panel-history').css({
						'visibility': "hidden",
						"z-index": "-1"
					});
				}
				$(this).addClass('tbar-tab-click-selected');
				$(this).find('.tab-text').remove();
				$('.tbar-panel-cart').css({
					'visibility': "visible",
					"z-index": "1"
				});

			} else {
				$('.tbar-tab-cart').click(function() {

				var info = "<em class='tab-text '>特检云</em>";
				$('.toolbar-wrap').removeClass('toolbar-open');
				$(this).append(info);
				$(this).removeClass('tbar-tab-click-selected');
				$('.tbar-panel-cart').css({
					'visibility': "hidden",
					"z-index": "-1"
				});

				});
			}


		} else {
			$(this).addClass('tbar-tab-click-selected');
			$(this).find('.tab-text').remove();
			$('.tbar-panel-cart').css({
				'visibility': "visible",
				"z-index": "1"
			});
			$('.tbar-panel-follow').css('visibility', 'hidden');
			$('.tbar-panel-history').css('visibility', 'hidden');
			$('.toolbar-wrap').addClass('toolbar-open');
		}
	});
	$('.tbar-tab-follow').mouseover(function() {
		if ($('.toolbar-wrap').hasClass('toolbar-open')) {
			if ($(this).find('.tab-text').length > 0) {
				if (!$('.tbar-tab-cart').find('.tab-text').length > 0) {
					var info = "<em class='tab-text '>特检云</em>";
					$('.tbar-tab-cart').append(info);
					$('.tbar-tab-cart').removeClass('tbar-tab-click-selected');
					$('.tbar-panel-cart').css({
						'visibility': "hidden",
						"z-index": "-1"
					});
				}
				if (!$('.tbar-tab-history').find('.tab-text').length > 0) {
					var info = "<em class='tab-text '>其它</em>";
					$('.tbar-tab-history').append(info);
					$('.tbar-tab-history').removeClass('tbar-tab-click-selected');
					$('.tbar-panel-history').css({
						'visibility': "hidden",
						"z-index": "-1"
					});
				}
				$(this).addClass('tbar-tab-click-selected');
				$(this).find('.tab-text').remove();
				$('.tbar-panel-follow').css({
					'visibility': "visible",
					"z-index": "1"
				});

			} else {
				$('.tbar-tab-follow').click(function() {
				var info = "<em class='tab-text '>操作手册</em>";
				$('.toolbar-wrap').removeClass('toolbar-open');
				$(this).append(info);
				$(this).removeClass('tbar-tab-click-selected');
				$('.tbar-panel-follow').css({
					'visibility': "hidden",
					"z-index": "-1"
				});

				});
			}


		} else {
			$(this).addClass('tbar-tab-click-selected');
			$(this).find('.tab-text').remove();
			$('.tbar-panel-cart').css('visibility', 'hidden');
			$('.tbar-panel-follow').css({
				'visibility': "visible",
				"z-index": "1"
			});
			$('.tbar-panel-history').css('visibility', 'hidden');
			$('.toolbar-wrap').addClass('toolbar-open');
		}
	});
	$('.tbar-tab-history').mouseover(function() {
		if ($('.toolbar-wrap').hasClass('toolbar-open')) {
			if ($(this).find('.tab-text').length > 0) {
				if (!$('.tbar-tab-follow').find('.tab-text').length > 0) {
					var info = "<em class='tab-text '>操作手册</em>";
					$('.tbar-tab-follow').append(info);
					$('.tbar-tab-follow').removeClass('tbar-tab-click-selected');
					$('.tbar-panel-follow').css({
						'visibility': "hidden",
						"z-index": "-1"
					});
				}
				if (!$('.tbar-tab-cart').find('.tab-text').length > 0) {
					var info = "<em class='tab-text '>特检云</em>";
					$('.tbar-tab-cart').append(info);
					$('.tbar-tab-cart').removeClass('tbar-tab-click-selected');
					$('.tbar-panel-cart').css({
						'visibility': "hidden",
						"z-index": "-1"
					});
				}
				$(this).addClass('tbar-tab-click-selected');
				$(this).find('.tab-text').remove();
				$('.tbar-panel-history').css({
					'visibility': "visible",
					"z-index": "1"
				});

			} else {
				var info = "<em class='tab-text '>其它</em>";
				$('.toolbar-wrap').removeClass('toolbar-open');
				$(this).append(info);
				$(this).removeClass('tbar-tab-click-selected');
				$('.tbar-panel-history').css({
					'visibility': "hidden",
					"z-index": "-1"
				});
			}

		} else {
			$(this).addClass('tbar-tab-click-selected');
			$(this).find('.tab-text').remove();
			$('.tbar-panel-cart').css('visibility', 'hidden');
			$('.tbar-panel-follow').css('visibility', 'hidden');
			$('.tbar-panel-history').css({
				'visibility': "visible",
				"z-index": "1"
			});
			$('.toolbar-wrap').addClass('toolbar-open');
		}
	});

	$('.close-panel').click(function() {

		$('.tbar-panel-cart').css({'visibility': "hidden","z-index": "-1"});
		$('.tbar-panel-follow').css({'visibility': "hidden","z-index": "-1"});
		$('.toolbar-wrap').removeClass('toolbar-open');
		$('.tbar-tab-follow').removeClass('tbar-tab-click-selected');
		$('.tbar-tab-cart').removeClass('tbar-tab-click-selected');


		
	});


});