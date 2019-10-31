	$(function(){
		// 先取得 #abgne-block-20120527 及其相關區塊及元素
		// 並依計算出每等份的寬度
		var _slices = 9,	// 切成幾等份
			_index = 0,		// 預設顯示第幾個
			_zIndex = 999, 
			$block = $('#zzsc').css('position', 'relative'), 
			$slides = $block.find('a').css('z-index', _zIndex).hide(), 
			_width = $block.width(), 
			_height = $block.height(), 
			_sliceWidth = _width / _slices,	// 每等份的寬度
			_lastSliceWidth = _sliceWidth + (_width - _sliceWidth * _slices),	// 剩於的寬度
			_img = $slides.eq(_index).show().find('img').attr('src'), 
			timer, 
			speed = 2000,	// 輪播速度
			_animateSpeed = 600,	// 動畫速度
			_isHover = false,	// 滑鼠是否移到 $block 上
			_isComplete = true;	// 動畫是否已全部執行完

		// 依 _slices 數量來產生相對應的 div 區塊
		var _sliceDiv = '', _control = '';
		for(var i=0;i<_slices;i++){
			var _w = i == _slices - 1 ? _lastSliceWidth : _sliceWidth, _l = i * _sliceWidth;
			_sliceDiv += '<div class="abgne-slice slide-' + i + '" style="left:' + _l + 'px;top:0;width:' + _w + 'px;height:100%;background-image:url(' + _img + ');background-position:-' + _l + 'px 0;position:absolute;background-repeat:no-repeat;"></div>';
		}
		
		// 依 $slides 數量來產生按鈕
		for(var i=0;i<$slides.length;i++){
			_control += '<li class="abgne-control control-' + (i + 1) + '">' + (i + 1) + '</li>';
		}
		
		// 分別把 div 區塊及按鈕加入到 $block 中
		var $abgneSlides = $block.append(_sliceDiv, '<ul class="abgne-controls">' + _control + '</ul>').find('.abgne-slice'), 
			$abgneControls = $block.find('.abgne-controls').css('z-index', _zIndex + 2).find('li').eq(_index).addClass('current').end();
		
		// 當點擊到 .abgne-controls li 時
		$abgneControls.click(function(){
			// 若動畫未完成前不接受其它新的事件
			if(!_isComplete) return;
			
			var $this = $(this), 
				$slide = $slides.eq($this.index()), 
				_completeTotal = 0;
			
			// 若現在顯示的跟點擊到的是同一個時, 就不處理
			if($this.hasClass('current')) return;

			// 幫點擊到的 li 加上 .current, 並移除上一個 .current 
			$this.addClass('current').siblings('.current').removeClass('current');
			_isComplete = false;
			_index = $this.index();
			
			// 取得相對應的圖片的路徑
			_img = $slide.find('img').attr('src');
			// 先讓每一個區塊的背景圖片為剛剛取得的圖片
			// 並進行動畫
			$abgneSlides.each(function(i){
				var $ele = $(this);
				$ele.css({
					top: i % 2 == 0 ? _height : -_height,
					opacity: 0, 
					zIndex: _zIndex + 1, 
					backgroundImage: 'url(' + _img + ')'
				}).stop().animate({
					top: 0, 
					opacity: 1
				}, _animateSpeed, function(){
					$ele.css('zIndex', _zIndex - 1);
					if(i == _slices - 1){
						$block.css('background-image', 'url(' + _img + ')');
						$slide.show().siblings('a:visible').hide();
						_isComplete = true;
						// 當動畫完成且滑鼠沒有移到 $block 上時, 再啟動計時器
						if(!_isHover)timer = setTimeout(auto, speed);
					}
				});
			});
		});
		
		$block.hover(function(){
			// 當滑鼠移入 $block 時停止計時器
			_isHover = true;
			clearTimeout(timer);
		}, function(){
			// 當滑鼠移出 $block 時啟動計時器
			_isHover = false;
			timer = setTimeout(auto, speed);
		});
		
		// 自動輪播使用
		function auto(){
			_index = (_index + 1) % $slides.length;
			$abgneControls.eq(_index).click();
		}
		
		// 啟動計時器
		timer = setTimeout(auto, speed);
	});