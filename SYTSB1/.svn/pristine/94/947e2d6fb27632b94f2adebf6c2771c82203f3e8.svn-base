
/**
 * a4分割线相关代码
 * @returns
 */
	//A4分割线JS
	function asldkfjwoef() {
		var bc = typeof BROWSER_INFO != "undefined" ? BROWSER_INFO : getBrowserInfo();
		var ie = bc.ie,
			xt = bc.system,
			xtms = bc.systemx,
			dm = bc.docMode,
			bsType=bc.type,
			version=bc.version,
			iever = bc.ieversion;
		//alert([bsType,version])
		if (bsType=="firefox") {
			var str1='<style>.a4-endwise{height:1444px;border:1px solid #ff0000;}</style>';
			//document.write(str1);
		}
		if (bsType=="opera") {
			var str1='<style>.a4-endwise{height:1300px;border:1px solid #ff0000;}</style>';
			//document.write(str1);
		}
		if (ie || bsType=="edge") {
			var str1='<style>.a4-endwise{height:1628px;}</style>';
			//document.write(str1);
			if (ie && parseFloat(iever) <= 7) { //浏览器检测 2012年06月19日 星期二 14:04:54 lybide
				
			}; //alert([iever,dm])
			if (ie && (parseInt(iever) > 6 && parseInt(iever) < 11) && (7 > parseInt(dm))) {
			
			}
		}
	}
	asldkfjwoef();

	function right(mainStr,lngLen) {
		if(mainStr.length-lngLen>=0 && mainStr.length>=0 && mainStr.length-lngLen<=mainStr.length){ 
			return mainStr.substring(mainStr.length-lngLen,mainStr.length)
		} else {
			return null;
		} 
	} 

	$(function(){
		$(".cont_center").height(1075);
		//打开横屏
		$(".container").removeClass("a4-endwise").addClass("a4-broadwise");
		//生成数据
		//for (var i = 0, l=168; i < l; i++) {
			//$(".a4-main").append('<p>打印测试 成都川大科鸿新技术研究所 '+right("000000"+i,6)+'</p>')
		//}
		//监控页面 2018年09月28日 16:22:48 星期五 lybide
		if (pageStatus != undefined &&pageStatus != ""&& pageStatus == "detail") {
			
		}else{
			var loopExe1=setInterval(function(){
				pageA4Line();
			},2000);
		}
		
		pageA4Line();
	});

	function pageA4Line() {
		var bc = typeof BROWSER_INFO != "undefined" ? BROWSER_INFO : getBrowserInfo();
		var ie = bc.ie,
			xt = bc.system,
			xtms = bc.systemx,
			dm = bc.docMode,
			bsType=bc.type,
			version=bc.version,
			iever = bc.ieversion;
		var w=document.body.offsetWidth;//$(window).width()+$(window).scrollLeft();
		//var h=document.body.offsetHeight;//$(window).height()+$(window).scrollTop();
		var h = $("#formObj").height();
		//console.log(h);
		var a4MainEndwise=$(".a4-main:first").parent().hasClass("a4-endwise");
			if(a4MainEndwise){
				var contentHeight = 1075*(938/660) + 52;
					if (bsType=="firefox") {
						contentHeight=contentHeight+550;
					}
					if (bsType=="opera") {
						contentHeight=contentHeight-10;
					}
					if (ie || bsType=="edge") {
						contentHeight=contentHeight+48;
					}
				//console.log(contentHeight,22)
			}else{
				var contentHeight = 1569*(934/1324)-10;
				if (bsType=="firefox") {
						contentHeight=contentHeight+120;
					}
					if (bsType=="opera") {
						contentHeight=contentHeight-10;
					}
					if (ie || bsType=="edge") {
						contentHeight=contentHeight-30;
					}
				//console.log(contentHeight,23)
			}

		if (h>contentHeight) {
			if ($("#a4-line-main1").size()>0) {
				
			} else {
				$("#formObj").append('<div id="a4-line-main1" class="a4-line-main" style="top:'+contentHeight+'px;"><div class="a4-line-no">'+1+'</div></div>');
			}
		}
		var hwiw=parseInt(h/contentHeight) + 1;
		//console.log(h,contentHeight,hwiw);//调试信息
		if (hwiw>1) {
			for (var i = 2, l=hwiw; i < l; i++) {
				if ($("#a4-line-main"+i).size()>0) {
				} else {
					if(!a4MainEndwise){
						var cont = contentHeight*(i);
						if (ie || bsType=="edge") {
						var cont = contentHeight*(i)-2*(i-1) ;
						}
						if (bsType=="firefox") {
						var cont = contentHeight*(i)+20*(i-1);
					}
					}else{
						var cont = contentHeight*(i) -10*(i-1);
					if (bsType=="firefox") {
						var cont = contentHeight*(i)-40;
					}
					if (bsType=="opera") {
						contentHeight=contentHeight-10;
					}
					if (ie || bsType=="edge") {
						var cont = contentHeight*(i)+8*(i-1) ;
					}
					}
					$("body").append('<div id="a4-line-main'+i+'" class="a4-line-main" style="top:'+cont+'px;"><div class="a4-line-no">'+(i)+'</div></div>');
				}
				
			}
		}
	}

