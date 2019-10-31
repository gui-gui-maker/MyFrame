$(function(){
	var bc = BROWSER_INFO;
	var ie = bc.ie,
		xt = bc.system,
		xtms = bc.systemx,
		dm = bc.docMode,
		iever = bc.ieversion;
	var ddd={"welcomeDesktop":{"titleIcon":"icon-minzheng iconfont"}};
	$.extend(kui,ddd);
	var sysName=kui["name"];
	sysName=sysName.replace(/<[^>].*?>/g," ");
	document.title = sysName;
	$("#systemTitleImg").addClass(kui["welcomeDesktop"]["titleIcon"]);
	if (kui["welcomeMainBg"]) {
		if (ie && parseFloat(iever) <= 8) {
			$("#systemBgImage").html('<img src="'+kui["welcomeMainBg"]+'" width="100%" border="0">');
		} else {
			$("#systemBgImage").css("background-image","url("+kui["welcomeMainBg"]+")");
		}
	} else {
		if (ie && parseFloat(iever) <= 8) {
			$("#systemBgImage").addClass("s-skin-container-default");
		} else {
			$("#systemBgImage").addClass("s-skin-container-default");
		}
	}
	$("#systemTitleText").html(sysName);
	if (kui["user"]["show"]) {
		var userIcon=kui["user"]["icon"].replace("default.gif","default.png");
		if (ie && parseFloat(iever) <= 8) {
			$("#userHeadImg").html('<img src="'+userIcon+'" width="100%" border="0">');
		} else {
			$("#userHeadImg").css("background-image","url('"+userIcon+"')");
		}
	} else {
		$("#userHeadImg").hide();
	}
	var userName=loginUserName["name"];
	$("#mUserInfoName").html(userName).attr("title",userName);
	$("#bCopyrightTech").html(kui["copyCom"]);
	$("#m-top-set").click(function(){
		top.userSetInit({"infoSetting":true,"skinSetting":false,"welcomeMainBgSetting":true,"sysMainBgSetting":false,"bgTransparentSetting":false});
	})
	var f5link=kui["f5linkIframe"] || kui["kuiBase"]+"f5link.jsp";
	if (f5link) {
		$("body").append('<div class="f5link" style="display:none;"><iframe name="f5linkIframe" id="f5linkIframe" width="100%" height="100%" scrolling="auto" frameborder="0" border="0"></iframe></div>');
		setInterval(function(){$("#f5linkIframe").attr("src",f5link);},180000)//13-3-14 下午2:54 lybide
	}

	//天气
	var oneExe1=setTimeout(function(){
		$.getScript('http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js', function(_result) {
			if (remote_ip_info.ret == '1') {
				var city=remote_ip_info.city;
				if (!city) {
					return;
				}
				var url = "http://wthrcdn.etouch.cn/WeatherApi?city=" + city;
				$.ajax({
					url: url,
					dataType: 'xml',
					success: function(res) {
						var xml=$(res);
						var city= xml.find("city").text();
						var wd= xml.find("wendu").text();
						var kqzl=xml.find("environment>quality").text();
						var today=xml.find("forecast>weather:first");
						var high=today.find("high").text();
						var low=today.find("low").text();
						var th=today.find("day>type").text();
						$("#sWeather").html(createWeatherElement());
						$("#sp-weather-city").html('<h2>'+city+'</h2><span>'+wd+'℃</span>');
						$("#sp-weather-zl span").css("background-color",getColorByDj(kqzl)).html(kqzl);
						$("#sp-weather-today-wd").html(low+"~"+high);
						$("#sp-weather-today-tq").html(th);
						$("#sp-weather-img").css("background-image","url('"+getImage(th)+"')");
					},
					error: function(err) {}
				});
			}
		});
	},1);
});


function getColor(num) {
	var color = "";
	if (num >= 0 && num <= 50) {
		color = "#00ba00";//a-color1
	} else if (num > 50 && num <= 100) {
		color = "#fbd12a";//aa-color2
	} else if (num > 100 && num <= 150) {
		color = "#ffa641";//a-color3
	} else if (num > 150 && num <= 200) {
		color = "#eb5b13";//a-color4
	} else if (num > 200 && num <= 300) {
		color = "#960453";//a-color5
	} else if (num > 300 && num <= 500) {
		color = "#580422";//a-color6
	} else {
		color="rgba(255,255,255,0.6)";
	}
	return color;
}

function getColorByDj(dj) {
	if (dj == "优") {
		return "#00ba00";
	}
	else if (dj == "良") {
		return "#fbd12a";
	}
	else if (dj == "轻度污染") {
		return "#ffa641";
	}
	else if (dj == "中度污染") {
		return "#eb5b13";
	}
	else if (dj == "重度污染") {
		return "#960453";
	}
	else if (dj == "严重污染") {
		return "#580422";
	}
	return "";
}

function getImage(s1){
	var s2="";
	var style_img="k/kui/images/icons/weather/b1.png";
	if(s1.indexOf("多云")!==-1&&s2.indexOf("晴")!==-1){style_img="k/kui/images/icons/weather/s_1.png";}
	else if(s1.indexOf("多云")!==-1&&s2.indexOf("阴")!==-1){style_img="k/kui/images/icons/weather/s_2.png";}
	else if(s1.indexOf("阴")!==-1&&s2.indexOf("雨")!==-1){style_img="k/kui/images/icons/weather/s_3.png";}
	else if(s1.indexOf("晴")!==-1&&s2.indexOf("雨")!==-1){style_img="k/kui/images/icons/weather/s_12.png";}
	else if(s1.indexOf("晴")!==-1&&s2.indexOf("雾")!==-1){style_img="k/kui/images/icons/weather/s_12.png";}
	else {
		if(s1.indexOf("晴")!==-1){style_img="k/kui/images/icons/weather/s_13.png";}
		else if(s1.indexOf("多云")!==-1){style_img="k/kui/images/icons/weather/s_2.png";}
		else if(s1.indexOf("阵雨")!==-1){style_img="k/kui/images/icons/weather/s_3.png";}
		else if(s1.indexOf("小雨")!==-1){style_img="k/kui/images/icons/weather/s_3.png";}
		else if(s1.indexOf("中雨")!==-1){style_img="k/kui/images/icons/weather/s_4.png";}
		else if(s1.indexOf("大雨")!==-1){style_img="k/kui/images/icons/weather/s_5.png";}
		else if(s1.indexOf("暴雨")!==-1){style_img="k/kui/images/icons/weather/s_5.png";}
		else if(s1.indexOf("冰雹")!==-1){style_img="k/kui/images/icons/weather/s_6.png";}
		else if(s1.indexOf("雷阵雨")!==-1){style_img="k/kui/images/icons/weather/s_7.png";}
		else if(s1.indexOf("小雪")!==-1){style_img="k/kui/images/icons/weather/s_8.png";}
		else if(s1.indexOf("中雪")!==-1){style_img="k/kui/images/icons/weather/s_9.png";}
		else if(s1.indexOf("大雪")!==-1){style_img="k/kui/images/icons/weather/s_10.png";}
		else if(s1.indexOf("暴雪")!==-1){style_img="k/kui/images/icons/weather/s_10.png";}
		else if(s1.indexOf("扬沙")!==-1){style_img="k/kui/images/icons/weather/s_11.png";}
		else if(s1.indexOf("沙尘")!==-1){style_img="k/kui/images/icons/weather/s_11.png";}
		else if(s1.indexOf("雾")!==-1){style_img="k/kui/images/icons/weather/s_12.png";}
		else{style_img="k/kui/images/icons/weather/s_2.png";}
	}
	return style_img;
}

function createWeatherElement(){
	var str1='';
	str1+= '<div class="sp-weather">';
	str1+= '	<div class="mleft">';
	str1+= '	<div class="wtname" id="sp-weather-city"></div>';
	str1+= '	</div>';
	str1+= '	<div class="mright">';
	str1+= '		';
	str1+= '		<div id="sp-day_1" class="wt" name="wt">';
	str1+= '		<div class="img" id="sp-weather-img"><!--<img align="absmiddle" src="" class="pngtqico">--></div>';
	str1+= '		<div class="taday02">';
	str1+= '			<ul>';
	str1+= '				<li class="taday02li"><span class="day">今</span><span class="bt" id="sp-weather-today-tq"></span><span id="sp-weather-today-wd" class="wd"></span></li>';
	str1+= '			</ul>';
	str1+= '		</div>';
	str1+= '		</div>';
	str1+= '	<div class="zl" id="sp-weather-zl">空气质量：<span style="background:#ffbb17" class="f1">良</span></div>';
	str1+= '</div>';
	return str1;
}