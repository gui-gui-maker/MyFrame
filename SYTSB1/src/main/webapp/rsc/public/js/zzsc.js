	$(function(){
		// ��ȡ�� #abgne-block-20120527 �������P�^�K��Ԫ��
		// �K��Ӌ���ÿ�ȷݵČ���
		var _slices = 9,	// �гɎ׵ȷ�
			_index = 0,		// �A�O�@ʾ�ڎׂ�
			_zIndex = 999, 
			$block = $('#zzsc').css('position', 'relative'), 
			$slides = $block.find('a').css('z-index', _zIndex).hide(), 
			_width = $block.width(), 
			_height = $block.height(), 
			_sliceWidth = _width / _slices,	// ÿ�ȷݵČ���
			_lastSliceWidth = _sliceWidth + (_width - _sliceWidth * _slices),	// ʣ춵Č���
			_img = $slides.eq(_index).show().find('img').attr('src'), 
			timer, 
			speed = 2000,	// ݆���ٶ�
			_animateSpeed = 600,	// �Ӯ��ٶ�
			_isHover = false,	// �����Ƿ��Ƶ� $block ��
			_isComplete = true;	// �Ӯ��Ƿ���ȫ��������

		// �� _slices ������a���������� div �^�K
		var _sliceDiv = '', _control = '';
		for(var i=0;i<_slices;i++){
			var _w = i == _slices - 1 ? _lastSliceWidth : _sliceWidth, _l = i * _sliceWidth;
			_sliceDiv += '<div class="abgne-slice slide-' + i + '" style="left:' + _l + 'px;top:0;width:' + _w + 'px;height:100%;background-image:url(' + _img + ');background-position:-' + _l + 'px 0;position:absolute;background-repeat:no-repeat;"></div>';
		}
		
		// �� $slides ������a�����o
		for(var i=0;i<$slides.length;i++){
			_control += '<li class="abgne-control control-' + (i + 1) + '">' + (i + 1) + '</li>';
		}
		
		// �քe�� div �^�K�����o���뵽 $block ��
		var $abgneSlides = $block.append(_sliceDiv, '<ul class="abgne-controls">' + _control + '</ul>').find('.abgne-slice'), 
			$abgneControls = $block.find('.abgne-controls').css('z-index', _zIndex + 2).find('li').eq(_index).addClass('current').end();
		
		// ���c���� .abgne-controls li �r
		$abgneControls.click(function(){
			// ��Ӯ�δ���ǰ�����������µ��¼�
			if(!_isComplete) return;
			
			var $this = $(this), 
				$slide = $slides.eq($this.index()), 
				_completeTotal = 0;
			
			// ���F���@ʾ�ĸ��c��������ͬһ���r, �Ͳ�̎��
			if($this.hasClass('current')) return;

			// ���c������ li ���� .current, �K�Ƴ���һ�� .current 
			$this.addClass('current').siblings('.current').removeClass('current');
			_isComplete = false;
			_index = $this.index();
			
			// ȡ���������ĈDƬ��·��
			_img = $slide.find('img').attr('src');
			// ��׌ÿһ���^�K�ı����DƬ�鄂��ȡ�õĈDƬ
			// �K�M�ЄӮ�
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
						// ���Ӯ�����һ���]���Ƶ� $block �ϕr, �ن���Ӌ�r��
						if(!_isHover)timer = setTimeout(auto, speed);
					}
				});
			});
		});
		
		$block.hover(function(){
			// ���������� $block �rֹͣӋ�r��
			_isHover = true;
			clearTimeout(timer);
		}, function(){
			// �������Ƴ� $block �r����Ӌ�r��
			_isHover = false;
			timer = setTimeout(auto, speed);
		});
		
		// �Ԅ�݆��ʹ��
		function auto(){
			_index = (_index + 1) % $slides.length;
			$abgneControls.eq(_index).click();
		}
		
		// ����Ӌ�r��
		timer = setTimeout(auto, speed);
	});