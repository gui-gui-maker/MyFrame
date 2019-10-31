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
<link href="app/weixin/css/style_hxwpx.css" rel="stylesheet" type="text/css">
<script src="app/weixin/js/jquery-1.10.1.min.js" type="text/javascript"></script>
<title>投票结果</title>
</head>
<%
	Map<String,Object> map = (HashMap<String,Object>)request.getAttribute("resultMap");
	List<Map.Entry<String, String>> mappingList = (List<Map.Entry<String, String>>)map.get("list");
	Map<String,String> voteMap = new HashMap<String,String>();
	Integer total = (Integer)map.get("total");
	int voteNum = total.intValue()/13;
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
<img src="app/weixin/images_hxwpx/top_01.jpg" />
</div>
<div class="lm_title">投票结果</div>
<div class="lm2"><img src="app/weixin/images/lm_02.jpg" /></div>
<div class="pneanin dome1">
	<% for (int i = 0 ; i < mappingList.size() ; i++) { 
		Map.Entry<String, String> entry = mappingList.get(i);
		voteMap.put(entry.getKey(),entry.getValue());
	%>
		<%if(entry.getKey().equals("0")){ %>	
  			<div class="item"><div class="name_i"><i class="name"><img src="app/weixin/images_hxwpx/bgs.jpg" border="0"></i></div><span class="txt_n">蒋青一行赴金牛区市场监管局、驻金牛区工作站调研</span><span class="bar"><i id="num_0" ></i></span><em class="count"><%=entry.getValue()%>票</em></div>
		<%} else if (entry.getKey().equals("1")){%>
			<div class="item"><div class="name_i"><i class="name"><img src="app/weixin/images_hxwpx/rlzyb.jpg" border="0"></i></div><span class="txt_n">我院召开老干部光荣退休欢送座谈会</span><span class="bar"><i id="num_1"  ></i></span><em class="count"><%=entry.getValue()%>票</em></div>
		<%} else if (entry.getKey().equals("2")){%>
			<div class="item"><div class="name_i"><i class="name"><img src="app/weixin/images_hxwpx/cwglb.jpg" border="0"></i></div><span class="txt_n">秦长海副局长一行到四川省特检院调研财务预算工作</span><span class="bar"><i id="num_2" ></i></span><em class="count"><%=entry.getValue()%>票</em></div>
  		<%} else if (entry.getKey().equals("3")){%>
  			<div class="item"><div class="name_i"><i class="name"><img src="app/weixin/images_hxwpx/zlb.jpg" border="0"></i></div><span class="txt_n">我院召开2015年第四季度质量安全风险排查例会</span><span class="bar"><i id="num_3" ></i></span><em class="count"><%=entry.getValue()%>票</em></div>
		<%} else if (entry.getKey().equals("4")){%>
			<div class="item"><div class="name_i"><i class="name"><img src="app/weixin/images_hxwpx/kgb.jpg" border="0"></i></div><span class="txt_n">四川省特检院2015年科技工作会议顺利召开</span><span class="bar"><i id="num_4"  ></i></span><em class="count"><%=entry.getValue()%>票</em></div>
		<%} else if (entry.getKey().equals("5")){%>
  <div class="item"><div class="name_i"><i class="name"><img src="app/weixin/images_hxwpx/fzb.jpg" border="0"></i></div><span class="txt_n">林涛一行赴中石化达州普光气田、川气东送管道分公司进行业务交流</span><span class="bar"><i id="num_5" ></i></span><em class="count"><%=entry.getValue()%>票</em></div>
		<%} else if (entry.getKey().equals("6")){%>
  <div class="item"><div class="name_i"><i class="name"><img src="app/weixin/images_hxwpx/bzb.jpg" border="0"></i></div><span class="txt_n">我院新增多款劳保神器为检验一线提供安全防护保障</span><span class="bar"><i id="num_6" ></i></span><em class="count"><%=entry.getValue()%>票</em></div>
		<%} else if (entry.getKey().equals("7")){%>
  <div class="item"><div class="name_i"><i class="name"><img src="app/weixin/images_hxwpx/fwb.jpg" border="0"></i></div><span class="txt_n">省特检院“娘子军”的服务之道</span><span class="bar"><i id="num_7" ></i></span><em class="count"><%=entry.getValue()%>票</em></div>
		<%} else if (entry.getKey().equals("8")){%>
  <div class="item"><div class="name_i"><i class="name"><img src="app/weixin/images_hxwpx/dcs.jpg" border="0"></i></div><span class="txt_n"> 督察室积极组织干部职工参加警示教育活动</span><span class="bar"><i id="num_8" ></i></span><em class="count"><%=entry.getValue()%>票</em></div>
		<%} else if (entry.getKey().equals("9")){%>
  <div class="item"><div class="name_i"><i class="name"><img src="app/weixin/images_hxwpx/xxxczx.jpg" border="0"></i></div><span class="txt_n">我们都是特检人 不怕山高水又深——宜宾天然气管道检验纪实</span><span class="bar"><i id="num_9" ></i></span><em class="count"><%=entry.getValue()%>票</em></div>
		<%} else if (entry.getKey().equals("10")){%>
  <div class="item"><div class="name_i"><i class="name"><img src="app/weixin/images_hxwpx/jjb.jpg" border="0"></i></div><span class="txt_n">蒋青院长一行视察“省特检平台”项目建设工地</span><span class="bar"><i id="num_10" ></i></span><em class="count"><%=entry.getValue()%>票</em></div>
		<%} else if (entry.getKey().equals("11")){%>
  <div class="item"><div class="name_i"><i class="name"><img src="app/weixin/images_hxwpx/jd1.jpg" border="0"></i></div><span class="txt_n">黄坚副院长率队赴甘肃特检院开展检验技术交流和比对</span><span class="bar"><i id="num_11" ></i></span><em class="count"><%=entry.getValue()%>票</em></div>
		<%}else if (entry.getKey().equals("12")){%>
  <div class="item"><div class="name_i"><i class="name"><img src="app/weixin/images_hxwpx/jd2.jpg" border="0"></i></div><span class="txt_n">省特检院全力保障全国第九届残运会比赛场地电梯运行安全</span><span class="bar"><i id="num_12" ></i></span><em class="count"><%=entry.getValue()%>票</em></div>
		<%}else if (entry.getKey().equals("13")){%>
  <div class="item"><div class="name_i"><i class="name"><img src="app/weixin/images_hxwpx/jd3.jpg" border="0"></i></div><span class="txt_n">双流国际机场T2航站楼定期检验顺利完成</span><span class="bar"><i id="num_13" ></i></span><em class="count"><%=entry.getValue()%>票</em></div>
		<%}else if (entry.getKey().equals("14")){%>
  <div class="item"><div class="name_i"><i class="name"><img src="app/weixin/images_hxwpx/jd4.jpg" border="0"></i></div><span class="txt_n">杜艳雄率队赴广东省特检院开展电梯检验比对活动</span><span class="bar"><i id="num_14" ></i></span><em class="count"><%=entry.getValue()%>票</em></div>
		<%}else if (entry.getKey().equals("15")){%>
  <div class="item"><div class="name_i"><i class="name"><img src="app/weixin/images_hxwpx/jd5.jpg" border="0"></i></div><span class="txt_n">起重机实作培训基地在金堂授牌</span><span class="bar"><i id="num_15" ></i></span><em class="count"><%=entry.getValue()%>票</em></div>
		<%}else if (entry.getKey().equals("16")){%>
  <div class="item"><div class="name_i"><i class="name"><img src="app/weixin/images_hxwpx/cy1.jpg" border="0"></i></div><span class="txt_n">省特检院完成达州普光气田压力容器检验项目</span><span class="bar"><i id="num_16" ></i></span><em class="count"><%=entry.getValue()%>票</em></div>
		<%}else if (entry.getKey().equals("17")){%>
  <div class="item"><div class="name_i"><i class="name"><img src="app/weixin/images_hxwpx/cy2.jpg" border="0"></i></div><span class="txt_n">承压二部与陕西省锅检所进行超高压水晶釜定期检验比对</span><span class="bar"><i id="num_17" ></i></span><em class="count"><%=entry.getValue()%>票</em></div>
		<%}else if (entry.getKey().equals("18")){%>
  <div class="item"><div class="name_i"><i class="name"><img src="app/weixin/images_hxwpx/cy3.jpg" border="0"></i></div><span class="txt_n">世界首台600MW超临界循环流化床锅炉内部检验项目顺利完成</span><span class="bar"><i id="num_18" ></i></span><em class="count"><%=entry.getValue()%>票</em></div>
		<%}else if (entry.getKey().equals("19")){%>
  <div class="item"><div class="name_i"><i class="name"><img src="app/weixin/images_hxwpx/cy4.jpg" border="0"></i></div><span class="txt_n">承压四部赴磨溪净化厂进行检验交流工作</span><span class="bar"><i id="num_19" ></i></span><em class="count"><%=entry.getValue()%>票</em></div>
		<%}else if (entry.getKey().equals("20")){%>
  <div class="item"><div class="name_i"><i class="name"><img src="app/weixin/images_hxwpx/cy5.jpg" border="0"></i></div><span class="txt_n">省特检院完成成都市油气输送压力管道隐患整治试点检验工作</span><span class="bar"><i id="num_20" ></i></span><em class="count"><%=entry.getValue()%>票</em></div>
		<%}else if (entry.getKey().equals("21")){%>
  <div class="item"><div class="name_i"><i class="name"><img src="app/weixin/images_hxwpx/xh.jpg" border="0"></i></div><span class="txt_n">中国特检协会副秘书长姜国锋出席四川省特检协会2015年培训考试工作总结暨研讨会</span><span class="bar"><i id="num_21" ></i></span><em class="count"><%=entry.getValue()%>票</em></div>
		<%}else if (entry.getKey().equals("22")){%>
  <div class="item"><div class="name_i"><i class="name"><img src="app/weixin/images_hxwpx/sfjd.jpg" border="0"></i></div><span class="txt_n">自纠自查重实效，查漏补缺保鉴定</span><span class="bar"><i id="num_22" ></i></span><em class="count"><%=entry.getValue()%>票</em></div>
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
	$('#num_12').css("width",Percentage("<%=voteMap.get("12")%>"));
	$('#num_13').css("width",Percentage("<%=voteMap.get("13")%>"));
	$('#num_14').css("width",Percentage("<%=voteMap.get("14")%>"));
	$('#num_15').css("width",Percentage("<%=voteMap.get("15")%>"));
	$('#num_16').css("width",Percentage("<%=voteMap.get("16")%>"));
	$('#num_17').css("width",Percentage("<%=voteMap.get("17")%>"));
	$('#num_18').css("width",Percentage("<%=voteMap.get("18")%>"));
	$('#num_19').css("width",Percentage("<%=voteMap.get("19")%>"));
	$('#num_20').css("width",Percentage("<%=voteMap.get("20")%>"));
	$('#num_21').css("width",Percentage("<%=voteMap.get("21")%>"));
	$('#num_22').css("width",Percentage("<%=voteMap.get("22")%>"));
});

function Percentage(num) {
    return (Math.round(num / "<%=map.get("total")%>" * 10000) / 100.00 + "%");// 小数点后两位百分比
   
}


function reSetVote(){
	var url = "voteGoodnewsAction/reSetRoundOne.do";
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
					location.href = baseurl+"voteGoodnewsAction/wUserInfoFinal.do";
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
