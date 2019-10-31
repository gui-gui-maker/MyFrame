	var dMMKey=0;
	var cl_width=0;
	var cl_pShow=false;
	$(function() {//jQuery页面载入事件
		//ie6 iframe 页面滚动条不正常解决方案。
		if ($.browser.msie && parseFloat($.browser.version) <= 6) {
			if ($("body").height() > $(window).height()) {
				$("body").width($("body").width() - 18);
			}
		}

		var doLi=$("#dot-list li");

		//$("#index_numIco").hide();

		//2013-11-8 下午3:56 lybide 新写法 sId=1 必须对应。
		/*
		<div class="btn_arlt" style="display:none;" sId="2"></div>
		<div class="btn_arlt" style="display:none;" sId="3"></div>
		<ul id="dot-list">
			<li class="dot" role="button" title="" sId="2">
				<div>分析统计</div>
			</li>
			<li class="dot" role="button" title="" sId="3">
				<div>综合信息</div>
			</li>
		</ul>
		*/
		var doLiLen=doLi.length;
		if (doLiLen>1) {
			$("#page-switcher-start").click(function(){
				var bb=$("#dot-list li[sId="+dMMKey+"]").prev();
				bb.click();
				if (bb.length==0) {
					$("#dot-list li:last").click();
				}
			});
			$("#page-switcher-end").click(function(){
				var bb=$("#dot-list li[sId="+dMMKey+"]").next();
				bb.click();
				if (bb.length==0) {
					$("#dot-list li:eq(0)").click();
				}
			});
		} else if (doLiLen<=1) {
			$("#page-switcher-start,#page-switcher-end,#index_numIco").hide();
		}

		//2013-8-29 下午4:07 lybide
		doLi.click(function(){
			var sId=$(this).attr("sId");
			//doListC(sId);
			doLi.removeClass("selected")
			$("#dot-list li[sId="+sId+"]").addClass("selected");
			$(".btn_arlt:visible").hide("",function(){
				//alert(123)
			});
			//$(".btn_arlt:eq("+(sId)+")").show().animate({right: "+="+$(window).width()+""}, "slow");return;
			$(".btn_arlt[sId="+(sId)+"]").show("slow",function(){
				/*var w1=$(".btn_arlt:eq("+(sId)+")").width();
				var h1=$(".btn_arlt:eq("+(sId)+")").height()+$("#newwrap").offset().top;//alert($("#newwrap").offset().top);
				var h2=$(window).height();
				//2013-8-30 上午10:19 lybide
				if (h1>h2) {
					$("#newwrap").height(h1).attr("oldHeight",h2);
					$("#index_numIco").css({"top":(h1+18)+"px"});
				} else {
					//$("#newwrap").height($("#newwrap").attr("oldHeight"));
					$("#newwrap").height("auto");
					$("#index_numIco").css({"top":"auto"});
				}
				$("#layer1").html("滚屏:"+w1+"=="+h1+"=="+h2);*/
				dMMKey=sId;
				wSize();
			});

		}).eq(0).click();
		//2013-10-17 下午5:24 lybide
		var cl=$("#content_list");
		if (cl) {
			var licl=$("li",cl);
			var w1=0;
			licl.each(function(i){
				w1+=$(this).outerWidth();
			});
			cl_width=w1;
			//alert(w1)
			//var b=licl.length;
			cl.width(w1);
		}
		//天气
		setTimeout(function(){
			$("#tqyb1").html('<iframe name="weather_inc" src="http://tianqi.xixik.com/cframe/2" width="290" height="70" frameborder="0" marginwidth="0" marginheight="0" scrolling="no"></iframe>');
		},100);
		//待处理（逾期）任务 事件绑定
		$(".task_lists li").click(function(){
			dbClick($(this).attr("id"));
		});
		//$("body").append('<div id="layer1" style="position:absolute;right:10px;top:10px;height:30px;z-index:1;border:1px solid #96c2f1;background:#eff7ff;padding:5px;">dsfasdfasdf</div>');
		wSize();
		$(window).resize(function() {
			wSize();
		})
	});
	function wSize() {
		var w1=$(window).width();
		var h1=$(window).height();

		if (dMMKey==0) {
			var panleWidth=$("#product").width()-$("#pPrev").width()-$("#pNext").width();//alert([panleWidth,cl_width]);
			if (cl_width>panleWidth) {
				//$("#product .prev,#product .next").show();
				var w1=panleWidth;
				var w2=($("#content_list li:eq(0)").outerWidth());
				//console.log(parseInt(w1/w2),Math.round(w1/w2),w2);
				$("#content").width(parseInt(w1/w2)*w2);
				cl_pShow=true;
			} else {
				//$("#product .prev,#product .next").hide();
				$("#content").css({width:"auto"});
				cl_pShow=false;
				$("#content_list").css({"left":"auto"});
			}

		}

		/*if (w1<998) {
			$("#newwrap").width(800).css({"margin-left":-(800/2)+"px"});
		}
		if (w1>=998 && w1<1240) {
			$("#newwrap").width(998).css({"margin-left":-(998/2)+"px"});
		}
		if (w1>=1240 && w1<1366) {
			$("#newwrap").width(1200).css({"margin-left":-(1200/2)+"px"});
		}
		if (w1>=1366 && w1<1450) {
			$("#newwrap").width(1300).css({"margin-left":-(1300/2)+"px"});
		}
		if (w1>=1450 && w1<2000) {
			$("#newwrap").width(1400).css({"margin-left":-(1400/2)+"px"});
		}
		if (w1>=2000) {
			$("#newwrap").width(1500).css({"margin-left":-(1500/2)+"px"});
		};//$("#newwrap").width("90%");*/

		/*if (h1<=475) {
			$("#newwrap").height(420).css({"margin-top":"0px","top":"0px"});
		}
		if (h1>475 && h1<540) {
			$("#newwrap").height(460).css({"margin-top":"0px","top":"0px"});
		}
		if (h1>520 && h1<540) {
			$("#newwrap").height(460).css({"margin-top":"0px","top":"0px"});
		}
		if (h1>550) {
			//h1=h1-120;
			//$("#newwrap").height(h1).css({"margin-top":-(parseInt(h1/2))+"px","top":"50%"});
		}*/
		//2013-8-30 下午2:41 lybide
		var obj=$(".btn_arlt:eq("+(dMMKey)+")");
		var w2=obj.width();
		var h2=obj.height();
		//$("#layer1").html("window Size:"+w1+"=="+h1+",btn_arlt Size:"+w2+"=="+h2+""+" , #newwrap "+$("#newwrap").width()+"=="+$("#newwrap").height());
		var top1={"margin-top":((h2/2)-100)+"px"};//alert(h1/2-h2/2)
		if (h2>h1) {
			top1={"margin-top":"0px","top":"0px"};
			$("#index_numIco").css({"top":(h2+18)+"px"});
		} else {
			//top1={"margin-top":-(h2/2+200)+"px"};
			$("#index_numIco").css({"top":"auto"});
		}
		//$("#newwrap").animate(top1);
		if ($("#newwrap").offset().top<60) {
			$("#newwrap").css({"margin-top":"auto","top":"auto"})
		}
		//$("#newwrap").css({"margin-top":0,"top":0});
		return;
	}