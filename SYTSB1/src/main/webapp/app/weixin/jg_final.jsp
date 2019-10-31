<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, width=device-width">
<meta content="yes" name="apple-mobile-web-app-capable" />
<meta content="black" name="apple-mobile-web-app-status-bar-style" />
<meta content="telephone=no" name="format-detection" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>
<script src="app/weixin/js/jquery-1.10.1.min.js" type="text/javascript"></script>

<style>

body{ max-width:720px; margin:0 auto; background:#193f73;}

*{ padding:0; margin:0; font-size:100%; font-family:"微软雅黑";}
a{ text-decoration:none}
ul li{ list-style:none}
.clear{ clear:both}
img{ border:none; display:block;}

/****mainCss***/
.pneal{ width:100%;padding:0;	position:relative;}
.pneal img {
	width: 100%;
	display: block;
}

.lm_title{ background:url(../images/lm_bg.jpg) no-repeat; background-size:100%;  line-height:2.1em; font-size:0.345em;text-align:center; color:#fff;}

.lm2 img{width: 100%;display: block;}

.pneal .pneals{ width:46%; float:left; box-sizing:border-box; margin:0 2%; background:#e74c3c ; border-radius:5px;}
.pneal .pneals:nth-child(1) a{ box-sizing:border-box;}
.pneal .pneals a{ font-size:0.75em; text-align:center; color:#fff; display:block; width:100%; height:3em; line-height:3em; font-weight:bold}
.blist{ margin:0em 0 0.5em; padding:0.5em; box-sizing:border-box; background:#ecf0f5; border-radius:0 0 5px 5px}
.blistheader{ font-size:0.875em; background:#2d3e50; margin:0; padding:0.5em 5%; margin-top:0.4em ; border-radius:5px 5px 0 0; color:#fff;}
.pneanin{ padding:0 2%}
.blistin{ overflow:hidden;}
.blistin li{ float:left; width:16%; margin:0.4em 2% ; background:#ff8981; border-radius:100px;}
.blistin li a{ font-size:0.75em; text-align:center; color:#fff; display:block; width:100%; height:100%; height:2em; line-height:2em;}
 .blistin .sactive{ background:#e74c3c }

 .bigboxin{ float:left; width:30%; height:100%; margin-right:3%; margin-bottom:0.625em; background:url(../images/user_bg.png) no-repeat; background-size: 100%;  position:relative; text-align:center;}
 .bigboxin:nth-child(1n){ margin-left:1%;} 
  .bigboxin:nth-child(3n){ margin-right:0;} 
 .bigboxh{ font-size:1.175em; color:#fff; text-align:center; line-height:2em;}
 .boxb{text-align:center;margin:0 auto;}
  .boxb img {width: 90%; padding:5% 0 0 5%; }
  .red{background:#f00!important }
 .Green { background:#16a085!important}
 .Belize{ background:#3498db !important}
 .fen{ background:#f9649c !important}
 .lv{ background:#f6c20e !important;}
 .Amethyst{ background:#9b59b6 !important}
  .boxbtn{ text-align:center;}
  .boxbtn input{ font-weight:bold;font-size:0.875em;width:100%;  color:#000;text-align:center; padding:0 1em;  line-height:2em;background:url(../images/tp_but.png) no-repeat center;background-size:contain; border:none; }
 
.alertbox{ position: absolute; width:100%; height:2em; background:rgba(44,64,80,0.6); border-radius:5px; margin:auto; top:30%; left:0; right:0; transform-origin:center bottom; transform:scaleY(0); -webkit-transform-origin:center bottom; -webkit-transform:scaleY(0);}
.thua{ animation:shangtan 0.5s linear forwards; -webkit-animation:shangtan 0.5s linear forwards;}
.box-text{ color:rgba(255,255,255,0.8); text-align:center; font-size:0.75em; line-height:2.3em;}
@keyframes shangtan{
	0%{ transform:scaleY(0)}
	50%{ transform:scaleY(1)}
	80%{ transform:scaleY(0.8)}
	100%{ transform:scaleY(1)}
	
	
	}
	@-webkit-keyframes shangtan{
	0%{ -webkit-transform:scaleY(0)}
	50%{ -webkit-transform:scaleY(1)}
	80%{ -webkit-transform:scaleY(0.8)}
	100%{ -webkit-transform:scaleY(1)}
	
	
	}

.item {
	line-height: 2em;
	width: 100%;
	margin-bottom: 1em;
}	

.name_i{display: inline-block;width: 15%;overflow: hidden;vertical-align: middle;font-size:0.875em; text-align:center;}

.name {
	width:60%;
	text-align: right;
	display: inline-block;
	overflow: hidden;
	vertical-align: middle;
	border-radius:60%;
	border:2px solid #267cca;
}


.txt_n{ text-align:left; color:#fff; display: inline-block; vertical-align:middle; width:15%; font-size:.912em;}

.name img{width: 100%;}


.count {
	color: #aaa;
	font-size:0.675em;
	overflow: hidden;
	width: 10%;
}
.bar {
	background-color: #0e1e52;
	 height:15px;
	font-size: 0;
	line-height: 10px;
	display: inline-block;
	width: 55%;
	margin: 0 1%;
	vertical-align: middle;
}
.bar i {
	display: inline-block;
	background-color:#366ee1;
	height:15px;
}


.form_ctrl {
	margin: .5em;
	text-align:center;
}
.ctrl_title {
	font-size: 1.2em;
	color: #363837;
	display: block;
	line-height: 1.2em;
	font-weight: 600;
	margin: .4em 0
}

input[type="submit"] {
border: hidden;
width: 100%;
box-sizing: border-box;
padding: .8em 0;
background-color: #ff6c00;
color: white;
font-weight: bold;
border-radius: .15em;
}

/*----------------------------
xpopup 

-------------------------------- */
.img-replace {
  /* replace text with an image */
  display: inline-block;
  overflow: hidden;
  text-indent: 100%;
  color: transparent;
  white-space: nowrap;
}


.cd-popup {
  position: fixed;
  left: 0;
  top: 0;
  height: 100%;
  width: 100%;
  background-color: rgba(94, 110, 141, 0.9);
  opacity: 0;
  visibility: hidden;
  -webkit-transition: opacity 0.3s 0s, visibility 0s 0.3s;
  -moz-transition: opacity 0.3s 0s, visibility 0s 0.3s;
  transition: opacity 0.3s 0s, visibility 0s 0.3s;
}
.cd-popup.is-visible {
  opacity: 1;
  visibility: visible;
  -webkit-transition: opacity 0.3s 0s, visibility 0s 0s;
  -moz-transition: opacity 0.3s 0s, visibility 0s 0s;
  transition: opacity 0.3s 0s, visibility 0s 0s;
}

.cd-popup-container {
  position: relative;
  width: 90%;
  max-width: 400px;
  margin: 10em auto;
  background: #FFF;
  border-radius: .25em .25em .4em .4em;
  text-align: center;
  box-shadow: 0 0 20px rgba(0, 0, 0, 0.2);
  -webkit-transform: translateY(-40px);
  -moz-transform: translateY(-40px);
  -ms-transform: translateY(-40px);
  -o-transform: translateY(-40px);
  transform: translateY(-40px);
  /* Force Hardware Acceleration in WebKit */
  -webkit-backface-visibility: hidden;
  -webkit-transition-property: -webkit-transform;
  -moz-transition-property: -moz-transform;
  transition-property: transform;
  -webkit-transition-duration: 0.3s;
  -moz-transition-duration: 0.3s;
  transition-duration: 0.3s;
}
.cd-popup-container p {
  padding: 3em 1em;
}
.cd-popup-container .cd-buttons:after {
  content: "";
  display: table;
  clear: both;
}
.cd-popup-container .cd-buttons li {
  float: left;
  width: 100%;
}
.cd-popup-container .cd-buttons a {
  display: block;
  height: 60px;
  line-height: 60px;
  text-transform: uppercase;
  color: #FFF;
  -webkit-transition: background-color 0.2s;
  -moz-transition: background-color 0.2s;
  transition: background-color 0.2s;
}
.cd-popup-container .cd-buttons li:first-child a {
  background: #fc7169;
  border-radius: 0 0 0 .25em;
}
.no-touch .cd-popup-container .cd-buttons li:first-child a:hover {
  background-color: #fc8982;
}
.cd-popup-container .cd-buttons li:last-child a {
  background: #b6bece;
  border-radius: 0 0 .25em 0;
}
.no-touch .cd-popup-container .cd-buttons li:last-child a:hover {
  background-color: #c5ccd8;
}
.cd-popup-container .cd-popup-close {
  position: absolute;
  top: 8px;
  right: 8px;
  width: 30px;
  height: 30px;
}
.cd-popup-container .cd-popup-close::before, .cd-popup-container .cd-popup-close::after {
  content: '';
  position: absolute;
  top: 12px;
  width: 14px;
  height: 3px;
  background-color: #8f9cb5;
}
.cd-popup-container .cd-popup-close::before {
  -webkit-transform: rotate(45deg);
  -moz-transform: rotate(45deg);
  -ms-transform: rotate(45deg);
  -o-transform: rotate(45deg);
  transform: rotate(45deg);
  left: 8px;
}
.cd-popup-container .cd-popup-close::after {
  -webkit-transform: rotate(-45deg);
  -moz-transform: rotate(-45deg);
  -ms-transform: rotate(-45deg);
  -o-transform: rotate(-45deg);
  transform: rotate(-45deg);
  right: 8px;
}
.is-visible .cd-popup-container {
  -webkit-transform: translateY(0);
  -moz-transform: translateY(0);
  -ms-transform: translateY(0);
  -o-transform: translateY(0);
  transform: translateY(0);
}




</style>
<title>投票结果</title>
<script  type="text/javascript">
jQuery(document).ready(function($){
	getStep();
	
});

function getStep(){
	$.get("voteRoundOneAction/getStep.do",function(res){
		//alert(JSON.stringify(res));
		$("#top2").show();
		$("#top1").hide();
		var step ="";
		if("1"==res.step){
			step ="一";
		}else if("2"==res.step){
			step ="二";
		}else if("3"==res.step){
			step ="三";
		}else{
			$("#top1").show();
			$("#top2").hide();
		}
		$("#step").html(step);
		$("#voteNum").val("第"+step+"轮已投票【"+res.stepVoted+"】人");
	})
}
</script>
</head>
<%
	Map<String,Object> map = (HashMap<String,Object>)request.getAttribute("resultMap");
	List<Map.Entry<String, String>> mappingList = (List<Map.Entry<String, String>>)map.get("list");
	Map<String,String> voteMap = new HashMap<String,String>();
	Integer total = (Integer)map.get("total");
	int voteNum = total.intValue();
	String step = (String)request.getAttribute("step");
	
%>
<body>

<div class="pneal">
<img src="app/weixin/images/top_01.jpg" />
</div>
<div class="lm_title" style="font-size: 25px;" id="top1">停止投票</div>
<div class="lm_title" style="font-size: 25px;" id="top2">现场投票：第<span id="step"></span>轮投票 </div>
<div class="lm2"><img src="app/weixin/images/lm_02.jpg" /></div>
<div class="pneanin dome1">
	<% for (int i = 0 ; i < mappingList.size() ; i++) { 
		Map.Entry<String, String> entry = mappingList.get(i);
		voteMap.put(entry.getKey(),entry.getValue());
	%>
		<%if(entry.getKey().equals("0")){ %>	
  			<div class="item"><div class="name_i"><i class="name"><img src="app/weixin/images/杨东01_x.png" border="0"></i></div><span class="txt_n">杨东</span><span class="bar"><i id="num_0" ></i></span><em class="count"><%=entry.getValue()%>票</em></div>
		<%} else if (entry.getKey().equals("1")){%>
			<div class="item"><div class="name_i"><i class="name"><img src="app/weixin/images/任琼02_x.png" border="0"></i></div><span class="txt_n">任琼</span><span class="bar"><i id="num_1"  ></i></span><em class="count"><%=entry.getValue()%>票</em></div>
		<%} else if (entry.getKey().equals("2")){%>
			<div class="item"><div class="name_i"><i class="name"><img src="app/weixin/images/明子涵08_x.png" border="0"></i></div><span class="txt_n">明子涵</span><span class="bar"><i id="num_2" ></i></span><em class="count"><%=entry.getValue()%>票</em></div>
  		<%} else if (entry.getKey().equals("3")){%>
  			<div class="item"><div class="name_i"><i class="name"><img src="app/weixin/images/易伟琪16_x.png" border="0"></i></div><span class="txt_n">易伟琪</span><span class="bar"><i id="num_3" ></i></span><em class="count"><%=entry.getValue()%>票</em></div>
		<%} else if (entry.getKey().equals("4")){%>
			<div class="item"><div class="name_i"><i class="name"><img src="app/weixin/images/祝月华05_x.png" border="0"></i></div><span class="txt_n">祝月华</span><span class="bar"><i id="num_4"  ></i></span><em class="count"><%=entry.getValue()%>票</em></div>
		<%} else if (entry.getKey().equals("5")){%>
			<div class="item"><div class="name_i"><i class="name"><img src="app/weixin/images/王光明21_x.png" border="0"></i></div><span class="txt_n">王光明</span><span class="bar"><i id="num_5" ></i></span><em class="count"><%=entry.getValue()%>票</em></div>
		<%} else if (entry.getKey().equals("6")){%>
			<div class="item"><div class="name_i"><i class="name"><img src="app/weixin/images/李学贵22_x.png" border="0"></i></div><span class="txt_n">李学贵</span><span class="bar"><i id="num_6" ></i></span><em class="count"><%=entry.getValue()%>票</em></div>
		<%} else if (entry.getKey().equals("7")){%>
  			<div class="item"><div class="name_i"><i class="name"><img src="app/weixin/images/周青13_x.png" border="0"></i></div><span class="txt_n">周青</span><span class="bar"><i id="num_7" ></i></span><em class="count"><%=entry.getValue()%>票</em></div>
		<%} else if (entry.getKey().equals("8")){%>
			<div class="item"><div class="name_i"><i class="name"><img src="app/weixin/images/刘摇14_x.png" border="0"></i></div><span class="txt_n">刘摇</span><span class="bar"><i id="num_8" ></i></span><em class="count"><%=entry.getValue()%>票</em></div>
		<%} else if (entry.getKey().equals("9")){%>
			<div class="item"><div class="name_i"><i class="name"><img src="app/weixin/images/彭朝华15_x.png" border="0"></i></div><span class="txt_n">彭朝华</span><span class="bar"><i id="num_9" ></i></span><em class="count"><%=entry.getValue()%>票</em></div>
  		<%} %>
	<%} %>


<div class="cd-popup" role="alert">
	<div class="cd-popup-container">
		<p id="alertContent"></p>
		<ul class="cd-buttons">
			<li><a id="okButton">确定</a></li>
            <li><a class="cd-popup-cancel" id="closeButton" >关闭</a></li>
		</ul>
        <a class="cd-popup-close img-replace">Close</a>
	</div> <!-- cd-popup-container -->
</div>

<script type="text/javascript">
var baseurl = $("base").attr("href") ;
jQuery(document).ready(function($){
	$('#num_0').css("width",Percentage("<%=voteMap.get("0")%>"));
	$('#num_1').css("width",Percentage("<%=voteMap.get("1")%>"));
	$('#num_2').css("width",Percentage("<%=voteMap.get("2")%>"));
	$('#num_3').css("width",Percentage("<%=voteMap.get("3")%>"));
	$('#num_4').css("width",Percentage("<%=voteMap.get("4")%>"));
	$('#num_5').css("width",Percentage("<%=voteMap.get("5")%>"));
	$('#num_6').css("width",Percentage("<%=voteMap.get("6")%>"));
	$('#num_7').css("width",Percentage("<%=voteMap.get("7")%>"));
	$('#num_8').css("width",Percentage("<%=voteMap.get("8")%>"));
	$('#num_9').css("width",Percentage("<%=voteMap.get("9")%>"));
});


function Percentage(num) { 
    return (Math.round(num / 750 * 10000) / 100.00 + "%");// 小数点后两位百分比
   
}


function reSetVote(){
	var url = "voteRoundOneAction/reSetRoundFinal.do?step=<%=step%>";
	$.ajax({
		url : url,
		type : "post",
		async : false,
		success : function(data) {
			if (data.success) {
				$('#alertContent').text("重置投票成功，点击确定按钮返回重新投票");
				$('.cd-popup').addClass('is-visible');
				$('#okButton').unbind("click");
				$('#okButton').click(function(){
					location.href = baseurl+"voteRoundOneAction/wUserInfoFinal.do";
		    	});
				$('#closeButton').click(function(){
					$('.cd-popup').removeClass('is-visible');
		    	});
				
			} else {
				$('#alertContent').text("重置投票成功失败");
				$('.cd-popup').addClass('is-visible');
				$('#okButton').unbind("click");
				$('#okButton').click(function(){
		    		$('.cd-popup').removeClass('is-visible');
		    	});
				$('#closeButton').click(function(){
					$('.cd-popup').removeClass('is-visible');
		    	});
			}
		}
	});
}

</script>

 <div class="form_ctrl form_submit" id="8" title="投票人数">
    <label class="ctrl_title"></label>
    <input type="submit" name="submit" id="voteNum" value="已投票人数： 【 <%=voteNum%> 】 人"  style="color: white;  background-color:#f1a30f;">
  </div>
  
<div style="text-align:center; padding-right:.6em;margin-top:1em;margin-bottom:.5em; font-size:1.2em; color: white">
<p>技术支持：信息宣传中心</p>
</div>
</body>
</html>
