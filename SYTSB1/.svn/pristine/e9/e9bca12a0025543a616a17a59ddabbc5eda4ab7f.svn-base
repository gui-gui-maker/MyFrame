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
<link href="app/weixin/css/style_jmtp.css" rel="stylesheet" type="text/css">
<script src="app/weixin/js/jquery-1.10.1.min.js" type="text/javascript"></script>
<title>投票结果</title>
</head>
<%
	Map<String,Object> map = (HashMap<String,Object>)request.getAttribute("resultMap");
	List<Map.Entry<String, String>> mappingList = (List<Map.Entry<String, String>>)map.get("list");
	Map<String,String> voteMap = new HashMap<String,String>();
	Integer total = (Integer)map.get("total");
	int voteNum = total.intValue()/4;
%>
<body>
<style>
.txt_n{ text-align:left; color:#fff; display: inline-block; vertical-align:middle; width:25%; font-size:0.750em;}
.bar {
	background-color: #0e1e52;
	 height:15px;
	font-size: 0;
	line-height: 10px;
	display: inline-block;
	width: 45%;
	margin: 0 1%;
	vertical-align: middle;
}
</style>
<div class="pneal">
<img src="app/weixin/image_jmtp/top_01.jpg" />
</div>
<div class="lm_title">投票结果</div>
<div class="lm2"><img src="app/weixin/images/lm_02.jpg" /></div>
<div class="pneanin dome1">
	<% for (int i = 0 ; i < mappingList.size() ; i++) { 
		Map.Entry<String, String> entry = mappingList.get(i);
		voteMap.put(entry.getKey(),entry.getValue());
	%>
		<%if(entry.getKey().equals("0")){ %>	
  			<div class="item"><div class="name_i"><i class="name"><img src="app/weixin/image_jmtp/2.jpg" border="0"></i></div><span class="txt_n">party</span><span class="bar"><i id="num_0" ></i></span><em class="count"><%=entry.getValue()%>票</em></div>
		<%} else if (entry.getKey().equals("1")){%>
			<div class="item"><div class="name_i"><i class="name"><img src="app/weixin/image_jmtp/1.jpg" border="0"></i></div><span class="txt_n">cups</span><span class="bar"><i id="num_1"  ></i></span><em class="count"><%=entry.getValue()%>票</em></div>
		<%} else if (entry.getKey().equals("2")){%>
			<div class="item"><div class="name_i"><i class="name"><img src="app/weixin/image_jmtp/5.jpg" border="0"></i></div><span class="txt_n">都是语言惹的祸</span><span class="bar"><i id="num_2" ></i></span><em class="count"><%=entry.getValue()%>票</em></div>
  		<%} else if (entry.getKey().equals("3")){%>
  			<div class="item"><div class="name_i"><i class="name"><img src="app/weixin/image_jmtp/3.jpg" border="0"></i></div><span class="txt_n">风情西班牙</span><span class="bar"><i id="num_3" ></i></span><em class="count"><%=entry.getValue()%>票</em></div>
		<%} else if (entry.getKey().equals("4")){%>
			<div class="item"><div class="name_i"><i class="name"><img src="app/weixin/image_jmtp/4.jpg" border="0"></i></div><span class="txt_n">新闻联播</span><span class="bar"><i id="num_4"  ></i></span><em class="count"><%=entry.getValue()%>票</em></div>
		<%} else if (entry.getKey().equals("5")){%>
  <div class="item"><div class="name_i"><i class="name"><img src="app/weixin/image_jmtp/7.jpg" border="0"></i></div><span class="txt_n">小毅寻爱奇遇记</span><span class="bar"><i id="num_5" ></i></span><em class="count"><%=entry.getValue()%>票</em></div>
		<%} else if (entry.getKey().equals("6")){%>
  <div class="item"><div class="name_i"><i class="name"><img src="app/weixin/image_jmtp/10.jpg" border="0"></i></div><span class="txt_n">特检院的格桑花</span><span class="bar"><i id="num_6" ></i></span><em class="count"><%=entry.getValue()%>票</em></div>
		<%} else if (entry.getKey().equals("7")){%>
  <div class="item"><div class="name_i"><i class="name"><img src="app/weixin/image_jmtp/8.jpg" border="0"></i></div><span class="txt_n">同学会</span><span class="bar"><i id="num_7" ></i></span><em class="count"><%=entry.getValue()%>票</em></div>
		<%} else if (entry.getKey().equals("8")){%>
  <div class="item"><div class="name_i"><i class="name"><img src="app/weixin/image_jmtp/11.jpg" border="0"></i></div><span class="txt_n"> 相亲相爱</span><span class="bar"><i id="num_8" ></i></span><em class="count"><%=entry.getValue()%>票</em></div>
		<%} else if (entry.getKey().equals("9")){%>
  <div class="item"><div class="name_i"><i class="name"><img src="app/weixin/image_jmtp/9.jpg" border="0"></i></div><span class="txt_n">特检之家</span><span class="bar"><i id="num_9" ></i></span><em class="count"><%=entry.getValue()%>票</em></div>
		<%} else if (entry.getKey().equals("10")){%>
  <div class="item"><div class="name_i"><i class="name"><img src="app/weixin/image_jmtp/6.jpg" border="0"></i></div><span class="txt_n">开心魔法</span><span class="bar"><i id="num_10" ></i></span><em class="count"><%=entry.getValue()%>票</em></div>
		<%} else if (entry.getKey().equals("11")){%>
  <div class="item"><div class="name_i"><i class="name"><img src="app/weixin/image_jmtp/12.jpg" border="0"></i></div><span class="txt_n">如花似锦的青春</span><span class="bar"><i id="num_11" ></i></span><em class="count"><%=entry.getValue()%>票</em></div>
		<%}%>
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
	$('#num_10').css("width",Percentage("<%=voteMap.get("10")%>"));
	$('#num_11').css("width",Percentage("<%=voteMap.get("11")%>"));
});

function Percentage(num) {
    return (Math.round(num / "<%=map.get("total")%>" * 10000) / 100.00 + "%");// 小数点后两位百分比
   
}


function reSetVote(){
	var url = "votePlayAction/reSetRoundOne.do";
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
					location.href = baseurl+"votePlayAction/wUserInfoFinal.do";
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
    <input type="submit" name="submit" value="已投票人数： 【 <%=total%> 】 人"  style="color: white;  background-color:#f1a30f;">
  </div>
  
<div style="text-align:center; padding-right:.6em;margin-top:1em;margin-bottom:.5em; font-size:1.2em; color: white">
<p>技术支持：信息宣传中心</p>
</div>
</body>
</html>
