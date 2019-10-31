jQuery(document).ready(function($){
	//cache some jQuery objects
	var modalTrigger = $('.cd-modal-trigger'),
		transitionLayer = $('#cd-transition-layer'),
		transitionBackground = transitionLayer.children(),
		modalWindow = $('#cd-modal');
	
	var modalTrigger1 = $('#cd-modal-trigger1'),
	transitionLayer1 = $('#cd-transition-layer1'),
	transitionBackground1 = transitionLayer1.children(),
	modalWindow1 = $('#cd-modal1');

	var frameProportion = 1.78, //png frame aspect ratio
		frames = 25, //number of png frames
		resize = false;

	//set transitionBackground dimentions
	setLayerDimensions();
	$(window).on('resize', function(){
		if( !resize ) {
			resize = true;
			(!window.requestAnimationFrame) ? setTimeout(setLayerDimensions, 300) : window.requestAnimationFrame(setLayerDimensions);
		}
	});

	//open modal window
	modalTrigger.on('click', function(event){	
		event.preventDefault();
		transitionLayer.addClass('visible opening');
		var delay = ( $('.no-cssanimations').length > 0 ) ? 0 : 600;
		setTimeout(function(){
			modalWindow.addClass('visible');
		}, delay);
	});
	//open modal window
	modalTrigger1.on('click', function(event){	
		event.preventDefault();
		transitionLayer1.addClass('visible opening');
		var delay = ( $('.no-cssanimations').length > 0 ) ? 0 : 600;
		setTimeout(function(){
			modalWindow1.addClass('visible');
		}, delay);
	});

	//close modal window
	modalWindow.on('click', '#modal-close', function(event){
		event.preventDefault();
		transitionLayer.addClass('closing');
		modalWindow.removeClass('visible');
		transitionBackground.one('webkitAnimationEnd oanimationend msAnimationEnd animationend', function(){
			transitionLayer.removeClass('closing opening visible');
			transitionBackground.off('webkitAnimationEnd oanimationend msAnimationEnd animationend');
		});
	});
	//close modal window
	modalWindow1.on('click', '#modal-close1', function(event){
		event.preventDefault();
		transitionLayer1.addClass('closing');
		modalWindow1.removeClass('visible');
		transitionBackground1.one('webkitAnimationEnd oanimationend msAnimationEnd animationend', function(){
			transitionLayer1.removeClass('closing opening visible');
			transitionBackground1.off('webkitAnimationEnd oanimationend msAnimationEnd animationend');
		});
	});

	function setLayerDimensions() {
		var windowWidth = $(window).width(),
			windowHeight = $(window).height(),
			layerHeight, layerWidth;

		if( windowWidth/windowHeight > frameProportion ) {
			layerWidth = windowWidth;
			layerHeight = layerWidth/frameProportion;
		} else {
			layerHeight = windowHeight*1.2;
			layerWidth = layerHeight*frameProportion;
		}

		transitionBackground.css({
			'width': layerWidth*frames+'px',
			'height': layerHeight+'px',
		});
		transitionBackground1.css({
			'width': layerWidth*frames+'px',
			'height': layerHeight+'px',
		});

		resize = false;
	}
});